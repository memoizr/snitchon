package me.snitchon.springboot

import jakarta.servlet.http.HttpServletRequest
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.*
import me.snitchon.parsers.GsonJsonParser.parseJson
import me.snitchon.path.Path
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.paramOrNull

class SpringRequestWrapper(val request: ServerRequest) : RequestWrapper {
    var bd: Any? = null

    override fun <T : Any> body(body: Class<T>): T {
        return bd as? T ?: request.body(body).also { bd = it } as T
    }

    override fun method(): HTTPMethod = HTTPMethod.fromString(request.methodName())

    override fun <RAW, PARSED : Any?> getParam(param: Parameter<RAW, PARSED>): String? = when (param) {
        is Path<*, *> -> request.pathVariable(param.name)
        is Query<*, *> -> request.paramOrNull(param.name)
        is Header<*, *> -> request.headers().firstHeader(param.name)
        else -> TODO()
    }
}

class SpringServletRequestWrapper(val request: HttpServletRequest) : RequestWrapper {

    override fun <T : Any> body(body: Class<T>): T {
        val body1 = request.reader.readText().parseJson(body)
        return body1
    }

    override fun method(): HTTPMethod = HTTPMethod.fromString(request.method)

    override fun <RAW, PARSED : Any?> getParam(param: Parameter<RAW, PARSED>): String? = when (param) {
        is Path<*, *> -> request.getParameter(param.name)
        is Query<*, *> -> request.getParameter(param.name)
        is Header<*, *> -> request.getHeader(param.name)
        else -> TODO()
    }
}

class SpringResponseWrapper() : ResponseWrapper {
    override fun setBody(body: String) = TODO()
    override fun setStatus(code: Int) = TODO()
}