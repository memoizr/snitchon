package me.snitchon.endpoint

import com.snitch.HttpResponse
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import me.snitchon.router.Par
import kotlin.reflect.KClass

//@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//data class Endpoint2<
//        A : Par,
//        B : Par,
//        RETURN : Any>(
//    override val params: EndpointParameters,
//    override val before: (RequestWrapper) -> Unit = {},
//    override val after: After = { _, res -> res },
//    override val response: KClass<RETURN>,
//    inline val a: A,
//    inline val b: B,
//    val handler: (context(A, B, Handler) () -> HttpResponse<RETURN>)? = null,
//) : Endpoint<RETURN> {
//
//    override val invoke: (Handler) -> HttpResponse<RETURN> = {
//        handler!!.invoke(a, b, it)
//    }
//
//    infix fun <C : Par> with(parameter: C): Endpoint3<A, B, C, RETURN> =
//        Endpoint3(
//            params,
//            before,
//            after,
//            response,
//            a,
//            b,
//            parameter
//        )
//
//    context(OnlyHeader)
//    override infix operator fun <T : HP> plus(t: T) = with(t)
//
//    context(OnlyQuery)
//    override infix operator fun <T : QP> plus(t: T) = with(t)
//
//    fun <C, BODY : Body<C>> with(body: BODY): Endpoint4<A, B, BODY, HasBody, RETURN> =
//        Endpoint4(
//            params,
//            before,
//            after,
//            response,
//            a,
//            b,
//            body,
//            HasBody
//        )
//}