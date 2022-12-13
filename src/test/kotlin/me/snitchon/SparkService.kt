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
            .apply {
                this.initExceptionHandler {
                    println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                    println(it.message)
                }
            }
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
            when (bundle.httpMethod) {
                HTTPMethod.GET -> {
                    http.get(sparkPath) { request, response ->
                        val call = EmbodiedEndpointCall(
                            SparkRequestWrapper(request),
                            SparkResponseWrapper(response),
                            bundle.response
                        )
                        (bundle.invoke(call) as? HttpResponse.SuccessfulHttpResponse<*>)?.body?.jsonString
                    }
                }

                HTTPMethod.PUT -> {
                    http.put(sparkPath) { request, response ->
                        val call = EmbodiedEndpointCall(
                            SparkRequestWrapper(request),
                            SparkResponseWrapper(response),
                            bundle.response
                        )
                        (bundle.invoke(call) as? HttpResponse.SuccessfulHttpResponse<*>)?.body?.jsonString
                    }
                }

                HTTPMethod.POST -> {
                    http.post(sparkPath) { request, response ->
                        val call = EmbodiedEndpointCall(
                            SparkRequestWrapper(request),
                            SparkResponseWrapper(response),
                            bundle.response
                        )
                        (bundle.invoke(call) as? HttpResponse.SuccessfulHttpResponse<*>)?.body?.jsonString
                    }
                }
//            HTTPMethod.PATCH -> http.patch(sparkPath, it.func)
//            HTTPMethod.HEAD -> http.head(sparkPath, it.func)
//            HTTPMethod.DELETE -> http.delete(sparkPath, it.func)
//            HTTPMethod.OPTIONS -> http.options(sparkPath, it.func)
                else -> TODO()
            }
        }
    }
}
