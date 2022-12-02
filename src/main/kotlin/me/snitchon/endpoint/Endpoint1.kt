package me.snitchon.endpoint

import Parameter
import QueryParameter

import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.router.PP

interface Endpoint<R> {
    val httpMethod: HTTPMethod
    val url: String
}

sealed interface Bundle

data class EndpointBundle<R>(val endpoint: Endpoint<R>, val func: (RequestWrapper) -> R) : Bundle

data class EndpointBundle1<A : Parameter, R>(
    val endpoint: Endpoint1<A, R>,
    val func: context(A, RequestWrapper) () -> R
) : Bundle

data class Endpoint0<R>(
    override val httpMethod: HTTPMethod,
    override val url: String,
) : Endpoint<R>

data class Endpoint1<
        P1 : Parameter,
        R>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val p1: P1,
) : Endpoint<R>

data class Endpoint2<
        P1 : Parameter,
        P2 : Parameter,
        >(
    val httpMethod: HTTPMethod,
    val url: String,
    inline val p1: P1,
    inline val p2: P2,
) {
    fun <C : QueryParameter> query(query: C) = Endpoint3(httpMethod, url, p1, p2, query)
}

data class Endpoint3<
        P1 : Parameter,
        P2 : Parameter,
        P3 : Parameter,
        >(
    val httpMethod: HTTPMethod,
    val url: String,
    inline val a: P1,
    inline val b: P2,
    inline val c: P3,
) {
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun isHandledBy(block: context(P1, P2) P3.(RequestWrapper) -> Unit) {
        val callback = { request: RequestWrapper ->
            block(a, b, c, request)
        }
    }
}
