package me.snitchon.endpoint

import PathParameter

import me.snitchon.http.HTTPMethod

data class Endpoint0<BODY>(
    val httpMethod: HTTPMethod,
    val url: String,
) {
    fun isHandledBy(block: () -> Unit) {
        block()
    }
}

data class Endpoint1<A: PathParameter, BODY>(
    val httpMethod: HTTPMethod,
    val url: String,
    inline val a: A,
) {
    fun isHandledBy(block: A.() -> Unit) {
        block(a)
    }
}