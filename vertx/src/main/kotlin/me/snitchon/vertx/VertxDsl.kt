package me.snitchon.vertx

import io.vertx.ext.web.RoutingContext
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.*
import me.snitchon.path.Path

class VertxRequestWrapper(val context: RoutingContext, val body: Any? = null) : RequestWrapper {
    override fun <T : Any?> myBody(c: Class<T>): T {
        return body as T
    }

    override fun method(): HTTPMethod = HTTPMethod.fromString(context.request().method().name())

    override fun <RAW, PARSED : Any?> getParam(param: Parameter<RAW, PARSED>): String? = when (param) {
        is Path<*> -> context.pathParam(param.name)
        is Query<*, *> -> context.queryParam(param.name).firstOrNull()
        is Header<*, *> -> context.request().getHeader(param.name)
        else -> TODO()
    }
}
