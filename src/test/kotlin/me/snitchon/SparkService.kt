package com.snitch.spark

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.snitch.HttpResponse
import me.snitchon.config.Config
import me.snitchon.endpoint.Endpoint
import me.snitchon.http.EmbodiedEndpointCall
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.router.Router
import me.snitchon.router.RouterContext
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

    fun setRoutes(routerConfiguration: context(RouterContext, SnitchService) Router.() -> Unit): RoutedService {
        val router = with(RouterContext) {
            Router()
        }
        routerConfiguration(RouterContext, this, router)
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
                (bundle.invoke(call) as? HttpResponse.SuccessfulHttpResponse<*>)?.body?.jsonString
            }

            when (bundle.httpMethod) {
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
