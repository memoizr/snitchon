package me.snitchon.http

import com.snitch.MissingRequiredParameterException
import me.snitchon.parameter.Parameter
import me.snitchon.path.Path
import me.snitchon.parameter.Header
import me.snitchon.parameter.Query
import me.snitchon.router.Body


interface RequestWrapper {
    fun <T : Any> body(c: Class<T>): T

    fun method(): HTTPMethod

    fun <RAW, T> getParam(param: Parameter<RAW, T>): String?

    fun <RAW, PARSED : Any?> parseParam(param: Parameter<RAW, PARSED>): PARSED {
        val parsed = getParam(param)
            ?.takeUnless { param.emptyAsMissing && it.isEmpty() }
            .also {
                if (it == null && param.pattern.required)
                    throw MissingRequiredParameterException("${param.kind} parameter `${param.name}`")
            }
            .let { param.pattern.parse(it as RAW, "${param.kind} parameter `${param.name}`") }

        return parsed
    }

    private val <RAW : Any?, PARSED : Any?> Parameter<RAW, PARSED>.kind
        get() = when (this) {
            is Path<*, *> -> "Path"
            is Header<*, *> -> "Header"
            is Query<*, *> -> "Query"
            is Body<*> -> "Body"
            else -> TODO()
        }
}

interface ResponseWrapper {
    fun setStatus(code: Int)
    fun setBody(body: String)
}
