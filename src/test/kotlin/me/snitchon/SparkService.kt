package com.snitch.spark

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.snitch.Format
import com.snitch.HttpResponse
import com.snitch.HttpResponses
import me.snitchon.SparkMarkup
import me.snitchon.config.Config
import me.snitchon.endpoint.Endpoint
import me.snitchon.http.EmbodiedEndpointCall
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.Markup
import me.snitchon.router.Router
import me.snitchon.router.HttpMethods
import me.snitchon.router.SlashSyntax
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService
import me.snitchon.syntax.GsonJsonParser
import org.slf4j.LoggerFactory
import spark.Request
import spark.Response
import spark.Service


class SparkService(override val config: Config) : SnitchService {
    val http by lazy {
        val logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        logger.level = Level.INFO

        Service.ignite().port(config.port)
    }

    override fun <T : Exception> handleException(
        exception: Class<T>,
        handler: (T, RequestWrapper, ResponseWrapper) -> Unit
    ) {
        http.exception(exception) { ex, request, response ->
            handler(ex, SparkRequestWrapper(request), SparkResponseWrapper(response))
        }
    }


    fun setRoutes(
        routerConfiguration: context(
        Markup,
        HttpMethods,
        SlashSyntax,
        SnitchService,
        HttpResponses
        ) Router.() -> Unit
    ): RoutedService {
        val router = with(HttpMethods) { Router() }

        routerConfiguration(SparkMarkup(), HttpMethods, SlashSyntax, this@SparkService, HttpResponses, router)

        http.notFound { req, res ->
            res.type("application/json")
            "{\"message\":\"Custom 404\"}"
        }
        return RoutedService(this, router)
    }

    override fun registerMethod(bundle: Endpoint<*>, path: String) {
        val sparkPath = path.replace("{", ":").replace("}", "")
        with(GsonJsonParser) {
            val function: (request: Request, response: Response) -> String? = { request, response ->
                val call = EmbodiedEndpointCall(
                    SparkRequestWrapper(request),
                    SparkResponseWrapper(response),
                    bundle.response
                )


                val result = bundle.invoke(call)

                response.status(result.statusCode)

                when (result) {
                    is HttpResponse.SuccessfulHttpResponse<*> -> {
                        if (result._format == Format.Json)
                            result.body?.jsonString
                        else
                            result.body.toString()
                    }

                    is HttpResponse.ErrorHttpResponse<*, *> -> {
                        result.jsonString
                    }
                }
            }

            when (bundle.params.httpMethod) {
                HTTPMethod.GET -> {
                    http.get(sparkPath, function)
                }

                HTTPMethod.PUT -> {
                    http.put(sparkPath, function)
                }

                HTTPMethod.POST -> {
                    http.post(sparkPath, function)
                }

                HTTPMethod.PATCH -> {
                    http.patch(sparkPath, function)
                }

                HTTPMethod.DELETE -> {
                    http.delete(sparkPath, function)
                }

                HTTPMethod.OPTIONS -> {
                    http.options(sparkPath, function)
                }

                HTTPMethod.HEAD -> {
                    http.head(sparkPath, function)
                }
            }
        }
    }
}
