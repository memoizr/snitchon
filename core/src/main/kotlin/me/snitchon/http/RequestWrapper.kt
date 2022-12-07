package me.snitchon.http

import me.snitchon.parameter.Parameter
import me.snitchon.router.Body

interface RequestWrapper {
    fun <T: Any> body(c: Class<T>): T
//    fun <T> params(name: String): T
//    fun <T> headers(name: String): T

    fun method() : HTTPMethod

    fun <RAW, T> getParam(param: Parameter<RAW,T>): T
}

interface ResponseWrapper {
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