package com.snitch.undertow

import io.undertow.Handlers
import io.undertow.Undertow
import io.undertow.server.HttpServerExchange
import io.undertow.server.RoutingHandler
import io.undertow.server.handlers.ExceptionHandler
import io.undertow.util.HttpString
import me.snitchon.config.Config
import me.snitchon.endpoint.Endpoint
import me.snitchon.http.*
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parameter.ParametrizedPath0
import me.snitchon.router.GetHttpMethods
import me.snitchon.router.Router
import me.snitchon.router.SlashSyntax
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService
import java.io.File


context(me.snitchon.parsing.Parser)
class UndertowService(override val config: Config = Config()) : SnitchService<UndertowRequestWrapper> {
    val routingHandler = RoutingHandler()
    val service: Undertow.Builder by lazy {
        Undertow
            .builder()
            .addHttpListener(config.port, "localhost")
    }

    private val Endpoint<UndertowRequestWrapper, Group, Any?, *>.func: (exchange: HttpServerExchange) -> Unit
        get() {
            return { exchange: HttpServerExchange ->

                val result = invoke!!.invoke(
                    group,
                    body,
                    BodyHandler(
                        UndertowRequestWrapper(exchange, this@Parser),
                        response,
                    )
                )

                result.addBodyHandler(exchange)
//                when (result) {
//                    is HttpResponse.SuccessfulHttpResponse<*> -> {
//                        //                        exchange.responseCode = result.statusCode
//                        exchange.setStatusCode(result.statusCode)
//                        exchange.responseHeaders.put(HttpString("Content-Type"), result._format.type)
//                        exchange.responseSender.send(result.body?.jsonString)
//                    }
//
//                    is HttpResponse.ErrorHttpResponse<*, *> -> {
//                        //                        exchange.responseCode = result.statusCode
//                        exchange.setStatusCode(result.statusCode)
//                        exchange.responseHeaders.put(HttpString("Content-Type"), Format.Json.type)
//                        exchange.responseSender.send(result.jsonString)
//                    }
//                }
            }
        }

    override fun registerMethod(it: Endpoint<UndertowRequestWrapper, Group, Any?, *>, path: String) {
        val handler: RoutingHandler =
            when (it.meta.httpMethod) {
                HTTPMethod.GET -> routingHandler.get(path, it.func)
                HTTPMethod.POST -> routingHandler.post(path, it.func)
                HTTPMethod.PUT -> routingHandler.put(path, it.func)
                HTTPMethod.PATCH -> TODO()// routingHandler.patch(path, it.func)
                HTTPMethod.HEAD -> TODO() //routingHandler.head(path, it.func)
                HTTPMethod.DELETE -> routingHandler.delete(path, it.func)
                HTTPMethod.OPTIONS -> TODO() //routingHandler.options(path, it.func)
            }

        val x: ExceptionHandler = Handlers.exceptionHandler(handler)
            .also {
                it.addExceptionHandler(Exception::class.java) {
                    val ex: Throwable = it.getAttachment(ExceptionHandler.THROWABLE)
                    exceptionHandler[ex::class.java]?.invoke(ex as Exception)?.addBodyHandler(it)
                }
            }

        handlers.add(x)
    }

    private val handlers = mutableListOf<ExceptionHandler>()

    override fun withRoutes(
        routerConfiguration: context(ParameterMarkupDecorator,
        GetHttpMethods,
        SlashSyntax<UndertowRequestWrapper>,
        HttpResponses) Router<UndertowRequestWrapper, ParametrizedPath0>.() -> Unit
    ): RoutedService<UndertowRequestWrapper> {
        val tmpDir = File(System.getProperty("java.io.tmpdir") + "/swagger-ui/docs")
        if (!tmpDir.exists()) {
            tmpDir.mkdirs()
        }
//        http.externalStaticFileLocation(tmpDir.absolutePath)

        val router = with(GetHttpMethods) { Router<UndertowRequestWrapper, _>(config, ParametrizedPath0()) }
        routerConfiguration(
            UndertowMarkup(),
            GetHttpMethods,
            SlashSyntax<UndertowRequestWrapper>(),
            HttpResponses,
            router
        )
        val routedService = RoutedService(this, router)


        return routedService
    }

    override fun onStart() {
        val builder = handlers.fold(service) { acc, routingHandler ->
            acc.setHandler(routingHandler)
        }
        builder
            .build()
            .start()
    }

    override val markup: ParameterMarkupDecorator = UndertowMarkup()

    override fun <T : Exception> handleException(exception: Class<T>, handler: (T) -> HttpResponse<*>) {
        exceptionHandler.put(exception, handler as (Exception) -> HttpResponse<*>)
    }

    private fun HttpResponse<*>.addBodyHandler(exchange: HttpServerExchange) {
        when (this) {
            is HttpResponse.SuccessfulHttpResponse<*> -> {
                exchange.setStatusCode(this.statusCode)
                exchange.responseHeaders.put(HttpString("Content-Type"), this._format.type)
                if (this._format == Format.Json) {
                    exchange.responseSender.send(this.body?.jsonString)
                } else {
                    exchange.responseSender.send(this.body.toString())
                }
            }

            is HttpResponse.ErrorHttpResponse<*, *> -> {
                exchange.setStatusCode(this.statusCode)
                exchange.responseHeaders.put(HttpString("content-type"), Format.Json.type)
                exchange.responseSender.send(jsonString)
            }
        }
    }
}

private val exceptionHandler = mutableMapOf<Class<*>, (Exception) -> HttpResponse<*>>()

class UndertowMarkup : ParameterMarkupDecorator {
    override fun decorate(name: String): String {
        return "{$name}"
    }
}
