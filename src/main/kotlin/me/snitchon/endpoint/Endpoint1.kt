package me.snitchon.endpoint

import Parameter
import me.snitchon.http.EmbodiedEndpointCall
import me.snitchon.http.EndpointCall

import me.snitchon.http.HTTPMethod
import me.snitchon.router.Body

interface Endpoint<R> {
    val httpMethod: HTTPMethod
    val url: String
    val invoke: (EndpointCall) -> R
}

//sealed interface Bundle<R> {
//    val endpoint: Endpoint<R>
//    val invoke: (EndpointCall) -> R
//}

//data class EndpointBundle<R>(
//    override val endpoint: Endpoint<R>, val func: (EndpointCall) -> R
//) : Bundle<R> {
//    override val invoke: (EndpointCall) -> R = {
//        func(it)
//    }
//}
//
//data class EndpointBundle1<A : Parameter, R>(
//    override val endpoint: Endpoint1<A, R>,
//    val handler: context(A, EndpointCall) () -> R
//) : Bundle<R> {
//    override val invoke: (EndpointCall) -> R = {
//        handler(
//            endpoint.p1,
//            it
//        )
//    }
//}
//
//data class EndpointBundle2<A : Parameter, B : Parameter, R>(
//    override val endpoint: Endpoint2<A, B, R>,
//    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//    val func: context(A, B, EndpointCall) () -> R
//) : Bundle<R> {
//    override val invoke: (EndpointCall) -> R = {
//        func(
//            endpoint.p1, endpoint.p2, it
//        )
//    }
//}
//
//data class EndpointBundle3<A : Parameter, B : Parameter, C : Parameter, R>(
//    override val endpoint: Endpoint3<A, B, C, R>,
//    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//    val func: context(A, B, C, EndpointCall) () -> R
//) : Bundle<R> {
//    override val invoke: (EndpointCall) -> R = {
//        func(
//            endpoint.p1, endpoint.p2, endpoint.p3, it
//        )
//    }
//}
//
//data class EndpointBundle4<A : Parameter, B : Parameter, C : Parameter, D : Parameter, R>(
//    override val endpoint: Endpoint4<A, B, C, D, R>,
//    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//    val func: context(A, B, C, D, EndpointCall) () -> R
//) : Bundle<R> {
//    override val invoke: (EndpointCall) -> R = {
//        func(endpoint.p1, endpoint.p2, endpoint.p3, endpoint.p4, it )
//    }
//}
//
//data class EndpointBundle5<A : Parameter, B : Parameter, C : Parameter, D : Parameter, E : Parameter, R>(
//    override val endpoint: Endpoint5<A, B, C, D, E, R>,
//    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//    val func: context(A, B, C, D, E, EndpointCall) () -> R
//) : Bundle<R> {
//    override val invoke: (EndpointCall) -> R = {
//        func(
//            endpoint.p1, endpoint.p2, endpoint.p3, endpoint.p4, endpoint.p5, it
//        )
//    }
//}
//
//data class EndpointBundle6<A : Parameter, B : Parameter, C : Parameter, D : Parameter, E : Parameter, F : Parameter, R>(
//    override val endpoint: Endpoint6<A, B, C, D, E, F, R>,
//    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//    val func: context(A, B, C, D, E, F, EndpointCall) () -> R
//) : Bundle<R> {
//    override val invoke: (EndpointCall) -> R = {
//        func(
//            endpoint.p1, endpoint.p2, endpoint.p3, endpoint.p4, endpoint.p5, endpoint.p6, it
//        )
//    }
//}

data class Endpoint0<R>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    val func: (context(EndpointCall) () -> R)? = null,
) : Endpoint<R>
{
    override val invoke: (EndpointCall) -> R = {
        func!!.invoke(
            it )
    }
}


data class Endpoint1<
        P1 : Parameter,
        R>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val p1: P1,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: (context(P1, EndpointCall) () -> R)? = null,
) : Endpoint<R>
{
    override val invoke: (EndpointCall) -> R = {
        func!!.invoke(
            p1,
            it )
    }
}

data class Endpoint2<
        P1 : Parameter,
        P2 : Parameter,
        R>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val p1: P1,
    inline val p2: P2,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: (context(P1, P2, EndpointCall) () -> R)? = null,
) : Endpoint<R>
{
    override val invoke: (EndpointCall) -> R = {
        func!!.invoke(
            p1,
            p2,
            it )
    }
}

data class Endpoint3<
        P1 : Parameter,
        P2 : Parameter,
        P3 : Parameter,
        R
        >(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: (context(P1, P2, P3, EndpointCall) () -> R)? = null,
) : Endpoint<R>
{
    override val invoke: (EndpointCall) -> R = {
        func!!.invoke(
            p1,
            p2,
            p3,
            it )
    }
}

data class Endpoint4<
        P1 : Parameter,
        P2 : Parameter,
        P3 : Parameter,
        P4 : Parameter,
        R
        >(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: (context(P1, P2, P3, P4, EndpointCall) () -> R)? = null,
) : Endpoint<R>
{
    override val invoke: (EndpointCall) -> R = {
        func!!.invoke(
            p1,
            p2,
            p3,
            p4,
            it )
    }
}

data class Endpoint5<
        P1 : Parameter,
        P2 : Parameter,
        P3 : Parameter,
        P4 : Parameter,
        P5 : Parameter,
        R
        >(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: (context(P1, P2, P3, P4, P5, EndpointCall) () -> R)? = null,
) : Endpoint<R>
{
    override val invoke: (EndpointCall) -> R = {
        func!!.invoke(
            p1,
            p2,
            p3,
            p4,
            p5,
            it )
    }
}

data class Endpoint6<
        P1 : Parameter,
        P2 : Parameter,
        P3 : Parameter,
        P4 : Parameter,
        P5 : Parameter,
        P6 : Parameter,
        R
        >(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: (context(P1, P2, P3, P4, P5, P6, EndpointCall) () -> R)? = null,
) : Endpoint<R>
{
    override val invoke: (EndpointCall) -> R = {
        func!!.invoke(
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            it )
    }
}
