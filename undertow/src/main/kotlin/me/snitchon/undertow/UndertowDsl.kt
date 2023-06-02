package com.snitch.undertow

import io.undertow.server.BlockingHttpExchange
import io.undertow.server.HttpServerExchange
import io.undertow.util.PathTemplateMatch
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.*
import me.snitchon.parsing.Parser
import me.snitchon.path.Path
import me.snitchon.router.BodyMarker
import java.net.URLDecoder
import java.util.concurrent.CountDownLatch

class UndertowRequestWrapper(val exchange: HttpServerExchange, private val body: Any?) : RequestWrapper {
    override  fun <T: Any> myBody(c: Class<T>): T {
        return body as T
    }

    override fun method(): HTTPMethod = HTTPMethod.fromString(exchange.requestMethod.toString())

    override fun <RAW, PARSED : Any?> getParam(param: Parameter<RAW, PARSED>): String? {
        return when (param) {
            is Path<*> -> {
                val params = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY)
                URLDecoder.decode(params.parameters.get(param.name))
            }
            is Query<*, *> -> exchange.queryParameters.get(param.name)?.firstOrNull()
            is Header<*, *> -> exchange.requestHeaders.get(param.name)?.firstOrNull()
            else -> TODO()
        }
    }
}

inline fun RequestWrapper.undertow() = this as UndertowRequestWrapper

//inline fun <T> RequestWrapper.bodyAsString(crossinline block: (String) -> T) = this.undertow().exchange.requestReceiver.receiveFullString { exchange, message ->
//    block(message)
//}

//context(BodyMarker<T>)
//inline fun <T, R> RequestWrapper.parsedBody(crossinline block: (T) -> R) = this.undertow().exchange.requestReceiver.receiveFullString { exchange, message ->
//    with (this.undertow().parser) {
//        block(message.parseJson(t))
//    }
//}


class UTResponseWrapper : ResponseWrapper {
    override fun setStatus(code: Int) {
    }

    override fun setBody(body: String) {
        TODO("Not yet implemented")
    }
}
