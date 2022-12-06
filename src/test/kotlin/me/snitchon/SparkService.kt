package com.snitch.spark

import ch.qos.logback.classic.Level
import com.dslplatform.json.processor.LogLevel
import me.snitchon.config.Config
import me.snitchon.documentation.bodyParam
import me.snitchon.endpoint.Endpoint
import me.snitchon.http.EmbodiedEndpointCall
import me.snitchon.http.HTTPMethod
import me.snitchon.router.Router
import me.snitchon.router.RouterContext
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService
import ch.qos.logback.classic.Logger
import com.snitch.HttpResponse
import me.snitchon.syntax.DslJsonParser.jsonString
import me.snitchon.syntax.GsonJsonParser
import me.snitchon.syntax.GsonJsonParser.jsonString
import org.slf4j.LoggerFactory
import spark.Request
import spark.Response
import spark.Service
import java.io.File


class SparkService(override val config: Config) : SnitchService {
    val http by lazy {
        val logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        logger.level = Level.INFO

        Service.ignite().port(config.port)
    }

    fun setRoutes(routerConfiguration: context(RouterContext, SnitchService) Router.() -> Unit): RoutedService {
        val router = with(RouterContext) {
            Router()
        }
        routerConfiguration(RouterContext, this, router)
        return RoutedService(this, router)
    }

    override fun registerMethod(bundle: Endpoint<*>, path: String) {
        val sparkPath = path.replace("{",":").replace("}", "")
        with(GsonJsonParser) {
            when (bundle.httpMethod) {
                HTTPMethod.GET -> {
//                println("getting - port: ${http.port()}")
                    http.get(sparkPath) { request, response ->
                        val call = EmbodiedEndpointCall(
                            SparkRequestWrapper(request),
                            SparkResponseWrapper(response),
                            bundle.response
                        )
                        bundle.invoke(call)
                    }
                }
//            HTTPMethod.POST -> http.post(sparkPath, it.func)
                HTTPMethod.PUT ->
//                println("getting - port: ${http.port()}")
                {
                    http.put(sparkPath) { request, response ->
                        val call = EmbodiedEndpointCall(
                            SparkRequestWrapper(request),
                            SparkResponseWrapper(response),
                            bundle.response
                        )
                        (bundle.invoke(call) as? HttpResponse.SuccessfulHttpResponse<*>)?.body?.jsonString.also {println(it)}
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

//    fun makeRequest(request: TestRequest): Any? {
//        val requestFunction1Pair =
//            service.find { it.first.path.match(request.path) && it.first.method == request.method }
//
//        val func = requestFunction1Pair?.second
//        val testRequestWrapper = TestRequestWrapper(request, requestFunction1Pair?.first?.path.orEmpty())
//        val response = object : ResponseWrapper {}
//        return func?.invoke(request.body?.let { EmbodiedEndpointCall(testRequestWrapper, response, it) }
//            ?: DisembodiedEndpointCall(testRequestWrapper, response))
//    }
}


//class SparkSnitchService(override val config: Config) : SnitchService {
//    val http by lazy { Service.ignite().port(config.port) }
//
////    private val Router.EndpointBundle<*>.func: (request: Request, response: Response) -> Any
////        get() =
////            { request, response ->
////                function(
////                    SparkRequestWrapper(request),
////                    SparkResponseWrapper(response)
////                )
////            }
//
//    fun setRoutes(routerConfiguration: Router.() -> Unit): RoutedService {
//        val tmpDir = File(System.getProperty("java.io.tmpdir") + "/swagger-ui/docs")
//        if (!tmpDir.exists()) {
//            tmpDir.mkdirs()
//        }
//        println(tmpDir)
//        http.externalStaticFileLocation(tmpDir.absolutePath)
//        val logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
//        logger.level = config.logLevel
//
//        val router = Router(config, this)
//        routerConfiguration(router)
//        return RoutedService(this, router).startListening()
//    }
//
//    override fun registerMethod(it: Router.EndpointBundle<*>, path: String) {
//        val sparkPath = path.replace("{",":").replace("}", "")
//        when (it.endpoint.httpMethod) {
//            HTTPMethod.GET -> { http.get(sparkPath, it.func) }
//            HTTPMethod.POST -> http.post(sparkPath, it.func)
//            HTTPMethod.PUT -> http.put(sparkPath, it.func)
//            HTTPMethod.PATCH -> http.patch(sparkPath, it.func)
//            HTTPMethod.HEAD -> http.head(sparkPath, it.func)
//            HTTPMethod.DELETE -> http.delete(sparkPath, it.func)
//            HTTPMethod.OPTIONS -> http.options(sparkPath, it.func)
//        }
//    }
//
//    fun stop() {
//        http.stop()
//    }
//}