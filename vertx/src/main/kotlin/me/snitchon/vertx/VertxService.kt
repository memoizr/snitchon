package me.snitchon.vertx


import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import me.snitchon.config.Config
import me.snitchon.documentation.bodyParam
import me.snitchon.endpoint.Endpoint
import me.snitchon.http.*
import me.snitchon.http.BodyHandler as SnitchBodyHandler
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.router.HttpMethods
import me.snitchon.router.Router
import me.snitchon.router.SlashSyntax
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService
import java.io.File
import io.vertx.ext.web.Router as VertxRouter


class Server(val port: Int) : AbstractVerticle() {
    val router by lazy {
        VertxRouter.router(vertx).also {
            val tmpDir = File(System.getProperty("java.io.tmpdir") + "/swagger-ui/docs")
            if (!tmpDir.exists()) {
                tmpDir.mkdirs()
            }
        }
    }

    override fun start() {
        router.route().handler(BodyHandler.create())
        vertx
            .createHttpServer()
            .exceptionHandler {
                exceptionHandler[it::class.java]?.invoke(it as Exception)
            }
            .requestHandler(router)
            .listen(port, "localhost")

    }
}


private val exceptionHandler = mutableMapOf<Class<*>, (Exception) -> HttpResponse<*>>()

context(me.snitchon.parsing.Parser)
class VertxService(override val config: Config = Config()) : SnitchService<VertxRequestWrapper> {
    val service by lazy { Server(config.port) }

    private inline val Endpoint<VertxRequestWrapper, Group, Any?, *>.func: (context: RoutingContext) -> Unit
        get() =
            { context ->
                val res = this.invoke?.invoke(
                    group,
                    body,
                    SnitchBodyHandler(
                        VertxRequestWrapper(context, this@Parser),
                        response
                    )
                )!!

                res.addBodyHandler(context)
            }

    private fun HttpResponse<*>.addBodyHandler(context: RoutingContext) {
        when (this) {
            is HttpResponse.SuccessfulHttpResponse<*> -> {
                context.response().setStatusCode(statusCode)
                context.response().putHeader("content-type", _format.type)
                context.response().end(body?.jsonString)
            }

            is HttpResponse.ErrorHttpResponse<*, *> -> {
                context.response().setStatusCode(statusCode)
                context.response().putHeader("content-type", Format.Json.type)
                context.response().end(jsonString)
            }
        }
    }

    override fun registerMethod(it: Endpoint<VertxRequestWrapper, Group, Any?, *>, path: String) {
        addBodyHandler(it, path)
        when (it.meta.httpMethod) {
            HTTPMethod.GET -> service.router.get(path).handler(it.func)
            HTTPMethod.POST -> service.router.post(path).handler(it.func)

            HTTPMethod.PUT -> service.router.put(path).handler(it.func)
            HTTPMethod.PATCH -> service.router.patch(path).handler(it.func)
            HTTPMethod.HEAD -> service.router.head(path).handler(it.func)
            HTTPMethod.DELETE -> service.router.delete(path).handler(it.func)
            HTTPMethod.OPTIONS -> service.router.options(path).handler(it.func)
        }.failureHandler {
            if (it.failed()) {
                exceptionHandler[it.failure()::class.java]?.invoke(it.failure() as Exception)?.addBodyHandler(it)
            }
        }
    }

    private fun addBodyHandler(it: Endpoint<VertxRequestWrapper, Group,*, *>, path: String) {
        if (it.bodyParam.klass != Nothing::class) {
            service.router.route(path).handler(BodyHandler.create())
        }
    }

    override fun withRoutes(routerConfiguration: context(ParameterMarkupDecorator, HttpMethods<VertxRequestWrapper>, SlashSyntax<VertxRequestWrapper>, HttpResponses) Router<VertxRequestWrapper>.() -> Unit): RoutedService<VertxRequestWrapper> {
        val tmpDir = File(System.getProperty("java.io.tmpdir") + "/swagger-ui/docs")
        if (!tmpDir.exists()) {
            tmpDir.mkdirs()
        }
        val router: Router<VertxRequestWrapper> = with(HttpMethods<VertxRequestWrapper>()) { Router(config) }
        routerConfiguration(VertxMarkup(), HttpMethods(), SlashSyntax(), HttpResponses, router)
        val routedService = RoutedService(this, router)

        return routedService
    }


    override fun <T : Exception> handleException(exception: Class<T>, handler: (T) -> HttpResponse<*>) {
        exceptionHandler.put(exception, handler as (Exception) -> HttpResponse<*>)
    }

    fun stop() {
        service.stop()
    }

    override fun onStart() {
        Vertx.vertx().deployVerticle(service)
    }
}


class VertxMarkup : ParameterMarkupDecorator {
    override fun decorate(name: String): String {
        return ":$name"
    }
}