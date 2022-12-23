package me.snitchon.springboot

import com.snitch.Format
import com.snitch.HttpResponse
import com.snitch.HttpResponses
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.snitchon.config.Config
import me.snitchon.endpoint.Endpoint
import me.snitchon.http.BodyHandler
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.parsers.GsonJsonParser.jsonString
import me.snitchon.router.HttpMethods
import me.snitchon.router.Router
import me.snitchon.router.SlashSyntax
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
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

@SpringBootApplication
open class Application {
}

class SpringMarkup : ParameterMarkupDecorator {
    override fun decorate(name: String): String = "{$name}"
}

val route = RouterFunctions.route()
val exceptions = mutableMapOf<Class<*>, (Exception, RequestWrapper) -> HttpResponse<*>>()

class SpringService(override val config: Config = Config()) : SnitchService {
    val http by lazy {
    }

    override fun <T : Exception> handleException(
        exception: Class<T>,
        handler: (T, RequestWrapper) -> HttpResponse<*>
    ) {
        exceptions.put(exception, handler as (Exception, RequestWrapper) -> HttpResponse<*>)
    }

    override fun withRoutes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        HttpMethods,
        SlashSyntax,
        HttpResponses
        ) Router.() -> Unit
    ): RoutedService {
        val router = with(HttpMethods) { Router() }

        routerConfiguration(SpringMarkup(), HttpMethods, SlashSyntax, HttpResponses, router)

//        http.notFound { req, res ->
//            res.type("application/json")
//            "{\"message\":\"Custom 404\"}"
//        }
        return RoutedService(this, router)
    }

    override fun registerMethod(bundle: Endpoint<*>, path: String) {
        with(GsonJsonParser) {
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
                            ServerResponse.status(result.statusCode).body(result.body)
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
    }

    override fun onStart() {
        val app = SpringApplication(Application::class.java)
        app.setDefaultProperties(
            mapOf(
                "server.port" to config.port,
                "spring.main.allow-bean-definition-overriding" to true,
            )
        )
        app.run(*emptyArray())
    }
}

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
                logger.error("9999999999999999999999999999999")
                return exceptions[ex::class.java]
                    ?.invoke(ex, SpringServletRequestWrapper(request))
                    ?.let {
                        response.writer.print(it.jsonString)
                        ModelAndView()
                    } ?: throw ex
            }
        }
        r.setWarnLogCategory("logger")
        return r
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