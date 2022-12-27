package me.snitchon.vertx

import io.vertx.core.impl.logging.LoggerFactory
import io.vertx.ext.web.RoutingContext
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.*
import me.snitchon.parsing.Parser
import me.snitchon.path.Path

class VertxRequestWrapper(private val context: RoutingContext) : RequestWrapper {
    private lateinit var b: Any

    context(Parser)
    override fun <T : Any> body(body: Class<T>): T {
       if (!this::b.isInitialized) b = context.body().asString().parseJson(body)
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

class VertxResponseWrapper : ResponseWrapper {
    override fun setStatus(code: Int) {
        TODO("Not yet implemented")
    }

    override fun setBody(body: String) {
        TODO("Not yet implemented")
    }
}