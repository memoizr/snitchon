package me.snitchon.endpoint

//@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//data class Endpoint9<
//        A : Par,
//        B : Par,
//        C : Par,
//        D : Par,
//        E : Par,
//        F : Par,
//        G : Par,
//        H : Par,
//        I : Par,
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
//    inline val g: G,
//    inline val h: H,
//    inline val i: I,
//    val handler: (context(A, B, C, D, E, F, G, H, I, Handler) () -> HttpResponse<RETURN>)? = null,
//) : Endpoint<RETURN> {
//    override val invoke: (Handler) -> HttpResponse<RETURN> = {
//        handler!!.invoke(a, b, c, d, e, f, g, h, i, it)
//    }
//
//    fun <I : Par> with(parameter: I) =
//        Endpoint10(
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
//            g,
//            h,
//            i,
//            parameter
//        )
//
//    context(OnlyHeader)
//    override infix operator fun <T : HP> plus(t: T) = TODO()
//
//    context(OnlyQuery)
//    override infix operator fun <T : QP> plus(t: T) = TODO()
//
//}