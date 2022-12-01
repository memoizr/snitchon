package me.snitchon.endpoint

import Parameter
import PathParameter
import QueryParameter

import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper

interface Endpoint<BODY, R> {
    val httpMethod: HTTPMethod
    val url: String
}

sealed interface Bundle
data class EndpointBundle<BODY, R>(val endpoint: Endpoint<BODY, R>, val func: (RequestWrapper) -> R): Bundle
data class EndpointBundle1<A: Parameter, BODY, R>(val endpoint: Endpoint1<A, BODY, R>, val func: context(A) (RequestWrapper) -> R): Bundle

data class Endpoint0<BODY, R>(
    override val httpMethod: HTTPMethod,
    override val url: String,
): Endpoint<BODY, R>

data class Endpoint1<A: Parameter, BODY, R>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val a: A,
): Endpoint<BODY, R> {
//    fun isHandledBy(block: A.(RequestWrapper) -> Unit) {
//        val callback = { request: RequestWrapper ->
//            block(a,request)
//        }
//    }
}

data class Endpoint2<
        A: PathParameter,
        B: PathParameter,
        BODY>(
    val httpMethod: HTTPMethod,
    val url: String,
    inline val a: A,
    inline val b: B,
) {
    fun isHandledBy(block: context(A) B.(RequestWrapper) -> Unit) {
        val callback = { request: RequestWrapper ->
            block(a, b,request)
        }
    }

    fun <C: QueryParameter> query(query: C) = Endpoint3<A,B,C,BODY>(httpMethod, url, a, b, query)

}
data class Endpoint3<
        A: Parameter,
        B: Parameter,
        C: Parameter,
        BODY>(
    val httpMethod: HTTPMethod,
    val url: String,
    inline val a: A,
    inline val b: B,
    inline val c: C,
) {
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun isHandledBy(block: context(A, B) C.(RequestWrapper) -> Unit) {
        val callback = { request: RequestWrapper ->
            block(a, b, c, request)
        }
    }
}
