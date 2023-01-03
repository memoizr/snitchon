package me.snitchon.vertx

import io.vertx.ext.web.RoutingContext
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.*
import me.snitchon.parsing.Parser
import me.snitchon.path.Path

class VertxRequestWrapper(val context: RoutingContext, val parser: Parser) : RequestWrapper {
    private var b: Any? = null

    override fun <T : Any?> myBody(body: Class<T>): T {
        if (b == null) {
            b = with(parser) { context.body().asString().parseJson(body) }
        }
        return b as T
    }

    override fun method(): HTTPMethod = HTTPMethod.fromString("PUT")

    override fun <RAW, PARSED : Any?> getParam(param: Parameter<RAW, PARSED>): String? = when (param) {
        is Path<*, *> -> context.pathParam(param.name)
        is Query<*, *> -> context.queryParam(param.name).firstOrNull()
        is Header<*, *> -> context.request().getHeader(param.name)
        else -> TODO()
    }
}
