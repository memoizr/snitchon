package me.snitchon.http

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
