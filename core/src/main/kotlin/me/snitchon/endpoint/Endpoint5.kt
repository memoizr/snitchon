package me.snitchon.endpoint

//@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//data class Endpoint5<
//        A : Par,
//        B : Par,
//        C : Par,
//        D : Par,
//        E : Par,
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
//    val handler: (context(A, B, C, D, E, Handler) () -> HttpResponse<RETURN>)? = null,
//) : Endpoint<RETURN> {
//
//    override val invoke: (Handler) -> HttpResponse<RETURN> = {
//        handler!!.invoke(a, b, c, d, e, it)
//    }
//
//    infix fun <F : Par> with(parameter: F): Endpoint6<A, B, C, D, E, F, RETURN> =
//        Endpoint6(
//            params,
//            before,
//            after,
//            response,
//            a,
//            b,
//            c,
//            d,
//            e,
//            parameter
//        )
//
//    context(OnlyHeader)
//    override infix operator fun <T : HP> plus(t: T) = with(t)
//
//    context(OnlyQuery)
//    override infix operator fun <T : QP> plus(t: T) = with(t)
//
//
//    fun <F, BODY : Body<F>> with(body: BODY): Endpoint7<A, B, C, D, E, BODY, HasBody, RETURN> =
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
//            body,
//            HasBody
//        )
//}