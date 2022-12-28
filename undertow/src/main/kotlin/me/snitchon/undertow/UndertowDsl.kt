package com.snitch.undertow

import com.snitch.*
import io.undertow.server.HttpServerExchange
import io.undertow.server.handlers.PathTemplateHandler
import io.undertow.util.PathTemplateMatch
import me.snitchon.http.Format
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.*
import me.snitchon.parsing.Parser
import me.snitchon.path.Path
import java.net.URLDecoder
import java.util.concurrent.CountDownLatch

class UndertowRequestWrapper(val exchange: HttpServerExchange) : RequestWrapper {
    private var b: Any? = null

    context(Parser)
    override fun <T: Any> body(body: Class<T>): T {
        if (b != null) return b as T
        val l = CountDownLatch(1)
        var s = ""
        exchange.requestReceiver.receiveFullString { e, m ->
            s = m
            l.countDown()
        }

        l.await()
        b = s.parseJson(body)

        return b as T
    }

    override fun method(): HTTPMethod = HTTPMethod.fromString(exchange.requestMethod.toString())

    override fun <RAW, PARSED : Any?> getParam(param: Parameter<RAW, PARSED>): String? {
        return when (param) {
            is Path<*, *> -> {
                val params = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY)
                URLDecoder.decode(params.parameters.get(param.name))
            }
            is Query<*, *> -> exchange.queryParameters.get(param.name)?.firstOrNull()
            is Header<*, *> -> exchange.requestHeaders.get(param.name)?.firstOrNull()
            else -> TODO()
        }
    }
}


class UTResponseWrapper : ResponseWrapper {
    override fun setStatus(code: Int) {
    }

    override fun setBody(body: String) {
        TODO("Not yet implemented")
    }
}
