package me.snitchon.endpoint

//@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//data class Endpoint3<
//        A : Par,
//        B : Par,
//        C : Par,
//        RETURN : Any>(
//    override val params: EndpointParameters,
//    override val before: (RequestWrapper) -> Unit = {},
//    override val after: After = { _, res -> res },
//    override val response: KClass<RETURN>,
//    inline val a: A,
//    inline val b: B,
//    inline val c: C,
//    val func: (context(A, B, C, Handler) () -> HttpResponse<RETURN>)? = null,
//) : Endpoint<RETURN> {
//
//    override val invoke: (Handler) -> HttpResponse<RETURN> = {
//        func!!.invoke(a, b, c, it)
//    }
//
//    infix fun <D : Par> with(parameter: D): Endpoint4<A, B, C, D, RETURN> =
//        Endpoint4(
//            params,
//            before,
//            after,
//            response,
//            a,
//            b,
//            c,
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
//    fun <D, BODY : Body<D>> with(body: BODY): Endpoint5<A, B, C, BODY, HasBody, RETURN> =
//        Endpoint5(
//            params,
//            before,
//            after,
//            response,
//            a,
//            b,
//            c,
//            body,
//            HasBody
//        )
//}