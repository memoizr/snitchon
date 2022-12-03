package me.snitchon.endpoint

import Parameter
import me.snitchon.http.EmbodiedEndpointCall
import me.snitchon.http.EndpointCall

import me.snitchon.http.HTTPMethod
import me.snitchon.router.Body

interface Endpoint<R> {
    val httpMethod: HTTPMethod
    val url: String
}

sealed interface Bundle

data class EndpointBundle<R>(val endpoint: Endpoint<R>, val func: (EndpointCall) -> R) : Bundle

data class EndpointBundle1<A : Parameter, R>(
    val endpoint: Endpoint1<A, R>,
    val func: context(A, EndpointCall) () -> R
) : Bundle

data class EndpointBundle2<A : Parameter, B : Parameter, R>(
    val endpoint: Endpoint2<A, B, R>,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: context(A, B, EndpointCall) () -> R
) : Bundle

data class EndpointBundle3<A : Parameter, B : Parameter, C : Parameter, R>(
    val endpoint: Endpoint3<A, B, C, R>,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: context(A, B, C, EndpointCall) () -> R
) : Bundle

data class EndpointBundle4<A : Parameter, B : Parameter, C : Parameter, D: Parameter, R>(
    val endpoint: Endpoint4<A, B, C, D, R>,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: context(A, B, C, D, EndpointCall) () -> R
) : Bundle

data class EndpointBundle5<A : Parameter, B : Parameter, C : Parameter, D: Parameter, E: Parameter, R>(
    val endpoint: Endpoint5<A, B, C, D, E, R>,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: context(A, B, C, D, E, EndpointCall) () -> R
) : Bundle

data class EndpointBundle6<A : Parameter, B : Parameter, C : Parameter, D: Parameter, E: Parameter, F: Parameter, R>(
    val endpoint: Endpoint6<A, B, C, D, E, F, R>,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: context(A, B, C, D, E, F, EndpointCall) () -> R
) : Bundle

data class EndpointBundle1Bodied<A : Parameter, BODY : Any, R>(
    val endpoint: Endpoint1<A, R>,
    val func: context(A, EmbodiedEndpointCall<BODY>) () -> R
) : Bundle

data class EndpointBundle2Bodied<
        A : Parameter,
        B : Parameter,
        BODY : Any, R>(
    val endpoint: Endpoint2Bodied<A, B, BODY, R>,
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val func: context(A, B, EmbodiedEndpointCall<BODY>) () -> R
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

data class Endpoint1Bodied<
        P1 : Parameter,
        BODY : Any,
        R>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val p1: P1,
    inline val body: Body<BODY>,
) : Endpoint<R>

data class Endpoint2<
        P1 : Parameter,
        P2 : Parameter,
        R
        >(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val p1: P1,
    inline val p2: P2,
) : Endpoint<R>


data class Endpoint2Bodied<
        P1 : Parameter,
        P2 : Parameter,
        BODY : Any,
        R>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    inline val p1: P1,
    inline val p2: P2,
    inline val body: Body<BODY>,
) : Endpoint<R>

data class Endpoint3<
        P1 : Parameter,
        P2 : Parameter,
        P3 : Parameter,
        R
        >(
    val httpMethod: HTTPMethod,
    val url: String,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
)

data class Endpoint4<
        P1 : Parameter,
        P2 : Parameter,
        P3 : Parameter,
        P4 : Parameter,
        R
        >(
    val httpMethod: HTTPMethod,
    val url: String,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
)
data class Endpoint5<
        P1 : Parameter,
        P2 : Parameter,
        P3 : Parameter,
        P4 : Parameter,
        P5 : Parameter,
        R
        >(
    val httpMethod: HTTPMethod,
    val url: String,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
)

data class Endpoint6<
        P1 : Parameter,
        P2 : Parameter,
        P3 : Parameter,
        P4 : Parameter,
        P5 : Parameter,
        P6 : Parameter,
        R
        >(
    val httpMethod: HTTPMethod,
    val url: String,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
)
