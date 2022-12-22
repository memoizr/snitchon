package com.snitch.spark

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.snitch.Format
import com.snitch.HttpResponse
import com.snitch.HttpResponses
import me.snitchon.SparkMarkup
import me.snitchon.SparkRequestWrapper
import me.snitchon.SparkResponseWrapper
import me.snitchon.config.Config
import me.snitchon.endpoint.Endpoint
import me.snitchon.http.BodyHandler
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.router.Router
import me.snitchon.router.HttpMethods
import me.snitchon.router.SlashSyntax
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService
import me.snitchon.parsers.GsonJsonParser
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

    override fun withRoutes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        HttpMethods,
        SlashSyntax,
        HttpResponses
        ) Router.() -> Unit
    ): RoutedService {
        val router = with(HttpMethods) { Router() }

        routerConfiguration(SparkMarkup(), HttpMethods, SlashSyntax, HttpResponses, router)

        http.notFound { req, res ->
            res.type("application/json")
            "{\"message\":\"Custom 404\"}"
        }
        return RoutedService(this, router)
    }

    override fun registerMethod(bundle: Endpoint<*>, path: String) {
        with(GsonJsonParser) {
            val function: (request: Request, response: Response) -> String? = { request, response ->
                val call = BodyHandler(
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
                    http.get(path, function)
                }

                HTTPMethod.PUT -> {
                    http.put(path, function)
                }

                HTTPMethod.POST -> {
                    http.post(path, function)
                }

                HTTPMethod.PATCH -> {
                    http.patch(path, function)
                }

                HTTPMethod.DELETE -> {
                    http.delete(path, function)
                }

                HTTPMethod.OPTIONS -> {
                    http.options(path, function)
                }

                HTTPMethod.HEAD -> {
                    http.head(path, function)
                }
            }
        }
    }
}
