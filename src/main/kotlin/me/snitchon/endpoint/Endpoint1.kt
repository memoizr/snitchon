package me.snitchon.endpoint

import me.snitchon.http.EndpointCall

import me.snitchon.http.HTTPMethod
import me.snitchon.router.Par

interface Endpoint<R> {
    val httpMethod: HTTPMethod
    val url: String
    val invoke: (EndpointCall) -> R
}

interface Endpoints<R> {
    val httpMethod: HTTPMethod
    val url: String
    val invoke: (EndpointCall) -> R
}

data class Endpoint0<RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    val func: (context(EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        func!!.invoke(it)
    }
}

data class Endpoint1<A : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val a: A,
    val handler: (context(A, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, it)
    }
}

data class Endpoint2<A : Par, B : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val a: A,
    inline val b: B,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val handler: (context(A, B, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, it)
    }
}

data class Endpoint3<A : Par, B : Par, C : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: (context(A, B, C, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        func!!.invoke(a, b, c, it)
    }
}

data class Endpoint4<A : Par, B : Par, C : Par, D : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: (context(A, B, C, D, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        func!!.invoke(a, b, c, d, it)
    }
}

data class Endpoint5<A : Par, B : Par, C : Par, D : Par, E : Par, R>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val handler: (context(A, B, C, D, E, EndpointCall) () -> R)? = null,
) : Endpoint<R> {
    override val invoke: (EndpointCall) -> R = {
        handler!!.invoke(a, b, c, d, e, it)
    }
}

data class Endpoint6<A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val handler: (context(A, B, C, D, E, F, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, c, d, e, f, it)
    }
}

data class Endpoint7<A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    inline val g: G,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val handler: (context(A, B, C, D, E, F, G, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, c, d, e, f, g, it)
    }
}

data class Endpoint8<A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    inline val g: G,
    inline val h: H,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val handler: (context(A, B, C, D, E, F, G, H, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, c, d, e, f, g, h, it)
    }
}
