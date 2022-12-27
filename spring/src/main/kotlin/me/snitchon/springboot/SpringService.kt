package me.snitchon.springboot

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import me.snitchon.http.Format
import me.snitchon.http.HttpResponse
import me.snitchon.http.HttpResponses
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.snitchon.config.Config
import me.snitchon.endpoint.Endpoint
import me.snitchon.http.BodyHandler
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parsing.Parser
import me.snitchon.router.HttpMethods
import me.snitchon.router.Router
import me.snitchon.router.SlashSyntax
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService
import me.snitchon.types.Sealed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanNameGenerator
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.boot.ApplicationContextFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.function.*
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver
import java.lang.reflect.Type

@SpringBootApplication(exclude = [JacksonAutoConfiguration::class]) // Exclude the automatic configuration of JACKSON
open class Application {
}

class SpringMarkup : ParameterMarkupDecorator {
    override fun decorate(name: String): String = "{$name}"
}

val route = RouterFunctions.route()
val exceptions = mutableMapOf<Class<*>, (Exception) -> HttpResponse<*>>()

context(Parser)
open class SpringService(override val config: Config = Config()) : SnitchService {

    init {
        parser = this@Parser
    }


    override fun <T : Exception> handleException(
        exception: Class<T>,
        handler: (T) -> HttpResponse<*>
    ) {
        exceptions.put(exception, handler as (Exception) -> HttpResponse<*>)
    }

    override fun withRoutes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        HttpMethods,
        SlashSyntax,
        HttpResponses
        ) Router.() -> Unit
    ): RoutedService {
        val router = with(HttpMethods) { Router(config) }

        routerConfiguration(SpringMarkup(), HttpMethods, SlashSyntax, HttpResponses, router)

//        http.notFound { req, res ->
//            res.type("application/json")
//            "{\"message\":\"Custom 404\"}"
//        }
        return RoutedService(this, router)
    }

    override fun registerMethod(bundle: Endpoint<*>, path: String) {
        val function: HandlerFunction<ServerResponse> = HandlerFunction { request: ServerRequest ->
            val call = BodyHandler(
                SpringRequestWrapper(request),
                SpringResponseWrapper(),
                bundle.response
            )

            val result = bundle.invoke(call)

//                response.status(result.statusCode)

            when (result) {
                is HttpResponse.SuccessfulHttpResponse<*> -> {
                    if (result._format == Format.Json)
                        ServerResponse.status(result.statusCode)
                            .contentType(MediaType.parseMediaType(Format.Json.type))
                            .body(result.body)
                    else
                        ServerResponse.status(result.statusCode)
                            .contentType(MediaType.parseMediaType(result._format.type))
                            .body(result.body)
                }

                is HttpResponse.ErrorHttpResponse<*, *> -> {
                    ServerResponse.status(result.statusCode).body(result.jsonString)
                }
            }
        }

        when (bundle.params.httpMethod) {
            HTTPMethod.GET -> {
                route.GET(path, function)
            }

            HTTPMethod.PUT -> {
                route.PUT(path, function)
            }

            HTTPMethod.POST -> {
                route.POST(path, function)
            }

            HTTPMethod.PATCH -> {
                route.PATCH(path, function)
            }

            HTTPMethod.DELETE -> {
                route.DELETE(path, function)
            }

            HTTPMethod.OPTIONS -> {
                route.OPTIONS(path, function)
            }

            HTTPMethod.HEAD -> {
                route.HEAD(path, function)
            }
        }
    }

    override fun onStart() {
        val app = SpringApplication(Application::class.java)
        app.setDefaultProperties(
            mapOf(
                "server.port" to config.port,
                "spring.main.allow-bean-definition-overriding" to true,
                "spring.mvc.converters.preferred-json" to "gson"
            )
        )
        app.run(*emptyArray())
    }
}

internal lateinit var parser: Parser

@Configuration
open class WebConfig : WebMvcConfigurer {
    @Bean
    open fun routerFunctionA(): RouterFunction<*> {
        return route.build()
    }

    @Bean
    open fun errors(): ExceptionHandlerExceptionResolver {
        val r: ExceptionHandlerExceptionResolver = object : ExceptionHandlerExceptionResolver() {
            override fun resolveException(
                request: HttpServletRequest,
                response: HttpServletResponse,
                handler: Any?,
                ex: java.lang.Exception
            ): ModelAndView? {
                return with(parser) {
                    exceptions[ex::class.java]
                        ?.invoke(ex)
                        ?.let {
                            response.writer.print(it.jsonString)
                            ModelAndView()
                        } ?: throw ex
                }
            }
        }
        r.setWarnLogCategory("logger")
        return r
    }

    @Bean
    open fun gsonBuilder(customizers: List<GsonBuilderCustomizer>): GsonBuilder {
        customizers.forEach {
            it.customize(builder)
        }
        return builder
//        return Gson().newBuilder()
    }

    override fun configureMessageConverters(converters: List<HttpMessageConverter<*>>) {
        // configure message conversion...
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        // configure CORS...
    }

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        // configure view resolution for HTML rendering...
    }
}

class SealedAdapter : JsonDeserializer<Sealed> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Sealed {
        val type = with(parser) { json?.asJsonObject?.get("type")?.jsonString }
        val rawType = TypeToken.get(typeOfT).rawType

        return rawType.kotlin.sealedSubclasses
            .firstOrNull { it.simpleName == type?.replace("\"", "") }
            ?.let {
                Gson().fromJson(json, it.java) as Sealed
            } ?: Gson().fromJson(json, rawType) as Sealed
    }
}


internal val builder = GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
    .registerTypeHierarchyAdapter(Sealed::class.java, SealedAdapter())
