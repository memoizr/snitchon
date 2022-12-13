package me.snitchon.http

import com.snitch.MissingRequiredParameterException
import me.snitchon.parameter.Parameter
import me.snitchon.parameter.kind
import me.snitchon.router.Body

interface RequestWrapper {
    fun <T: Any> body(c: Class<T>): T

    fun method() : HTTPMethod

    fun <RAW, T> getParam(param: Parameter<RAW,T>): String?

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
}

interface ResponseWrapper {
    fun setStatus(code: Int)
    fun setBody(body: String)
}

interface Handler {
    val request: RequestWrapper
    val response: ResponseWrapper
}

data class DisembodiedEndpointCall(
    override inline val request: RequestWrapper,
    override inline val response: ResponseWrapper,
): Handler

data class EmbodiedEndpointCall<T>(
    override inline val request: RequestWrapper,
    override inline val response: ResponseWrapper,
    inline val body: T
): Handler


data class Embodied<T: Any>(val body: Body<T>)