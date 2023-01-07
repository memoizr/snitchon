package me.snitchon.spark

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import me.snitchon.config.Config
import me.snitchon.endpoint.Endpoint
import me.snitchon.http.*
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parameter.ParametrizedPath0
import me.snitchon.parsing.Parser
import me.snitchon.router.Router
import me.snitchon.router.GetHttpMethods
import me.snitchon.router.SlashSyntax
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService
import org.slf4j.LoggerFactory
import spark.Request
import spark.Response
import spark.Service


class SparkMarkup : ParameterMarkupDecorator {
    override fun decorate(name: String): String = ":$name"
}

context(Parser)
class SparkService(override val config: Config = Config()) : SnitchService<SparkRequestWrapper> {
    val http by lazy {
        val logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        logger.level = Level.INFO

        Service.ignite().port(config.port)
    }
    override val markup: ParameterMarkupDecorator = SparkMarkup()

    override fun <T : Exception> handleException(
        exception: Class<T>,
        handler: (T) -> HttpResponse<*>
    ) {
        http.exception(exception) { ex, request, response ->
            handler(ex).also {
                response.status(it.statusCode)
                when (it) {
                    is HttpResponse.SuccessfulHttpResponse<*> -> response.body(it.body?.jsonString)
                    is HttpResponse.ErrorHttpResponse<*, *> -> response.body(it.jsonString)
                }
            }
        }
    }

    override fun withRoutes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        GetHttpMethods,
        SlashSyntax<SparkRequestWrapper>,
        HttpResponses
        ) Router<SparkRequestWrapper, ParametrizedPath0>.() -> Unit
    ): RoutedService<SparkRequestWrapper> {
        val httpMethods = GetHttpMethods
        val router = Router<SparkRequestWrapper, _>(config, ParametrizedPath0())

        routerConfiguration(SparkMarkup(), httpMethods, SlashSyntax(), HttpResponses, router)

        http.notFound { req, res ->
            res.type("application/json")
            "{\"message\":\"Custom 404\"}"
        }
        return RoutedService(this, router)
    }

    override fun onStart() {
        println(http.routes().map { it.matchUri })
    }

    override fun registerMethod(bundle: Endpoint<SparkRequestWrapper, Group, Any?, *>, path: String) {
        val function: (request: Request, response: Response) -> String? = { request, response ->
            val call = BodyHandler(
                SparkRequestWrapper(request, this@Parser),
                bundle.response
            )

            val result = bundle.invoke?.invoke(bundle.group, bundle.body, call)!!

            response.status(result.statusCode)

            when (result) {
                is HttpResponse.SuccessfulHttpResponse<*> -> {
                    if (result._format == Format.Json) {
                        response.type(result._format.type)
                        result.body?.jsonString
                    } else {
                        response.type(result._format.type)
                        result.body.toString()
                    }
                }

                is HttpResponse.ErrorHttpResponse<*, *> -> {
                    result.jsonString
                }
            }
        }

        when (bundle.meta.httpMethod) {
            HTTPMethod.GET -> http.get(path, function)
            HTTPMethod.PUT -> http.put(path, function)
            HTTPMethod.POST -> http.post(path, function)
            HTTPMethod.PATCH -> http.patch(path, function)
            HTTPMethod.DELETE -> http.delete(path, function)
            HTTPMethod.OPTIONS -> http.options(path, function)
            HTTPMethod.HEAD -> http.head(path, function)
        }
    }
}
