package me.snitchon.endpoint

//@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//data class Endpoint6<
//        A : Par,
//        B : Par,
//        C : Par,
//        D : Par,
//        E : Par,
//        F : Par,
//        RETURN : Any>(
//    override val params: EndpointParameters,
//    override val before: (RequestWrapper) -> Unit = {},
//    override val after: After = { _, res -> res },
//    override val response: KClass<RETURN>,
//    inline val a: A,
//    inline val b: B,
//    inline val c: C,
//    inline val d: D,
//    inline val e: E,
//    inline val f: F,
//    val handler: (context(A, B, C, D, E, F,
//    Handler) () -> HttpResponse<RETURN>)? = null,
//) : Endpoint<RETURN> {
//
//    override val invoke: (Handler) -> HttpResponse<RETURN> = {
//        handler!!.invoke(a, b, c, d, e, f, it)
//    }
//
//    fun <G : Par> with(parameter: G) =
//        Endpoint7(
//            params,
//            before,
//            after,
//            response,
//            a,
//            b,
//            c,
//            d,
//            e,
//            f,
//            parameter
//        )
//
//    context(OnlyHeader)
//    override infix operator fun <T : HP> plus(t: T) = with(t)
//
//    context(OnlyQuery)
//    override infix operator fun <T : QP> plus(t: T) = with(t)
//
//    fun <G, BODY : Body<G>> with(body: BODY) =
//        Endpoint8(
//            params,
//            before,
//            after,
//            response,
//            a,
//            b,
//            c,
//            d,
//            e,
//            f,
//            body,
//            HasBody
//        )
//}