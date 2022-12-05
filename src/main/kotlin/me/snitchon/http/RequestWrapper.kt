package me.snitchon.http

import me.snitchon.parameter.Parameter
import me.snitchon.router.Body

interface RequestWrapper {
    fun <T> body(): T
    fun params(name: String): String?
    fun headers(name: String): String?

    fun method() : HTTPMethod

    fun getParam(param: Parameter<*,*>): String?
}

interface ResponseWrapper {
}

interface EndpointCall {
    val request: RequestWrapper
    val response: ResponseWrapper
}

data class DisembodiedEndpointCall(
    override inline val request: RequestWrapper,
    override inline val response: ResponseWrapper,
): EndpointCall

data class EmbodiedEndpointCall<T>(
    override inline val request: RequestWrapper,
    override inline val response: ResponseWrapper,
    inline val body: T
): EndpointCall


data class Embodied<T: Any>(val body: Body<T>)