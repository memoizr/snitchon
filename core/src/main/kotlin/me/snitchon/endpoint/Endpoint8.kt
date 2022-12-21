package me.snitchon.endpoint

import com.snitch.HttpResponse
import me.snitchon.documentation.Visibility
import me.snitchon.http.HTTPMethod
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.router.Par
import kotlin.reflect.KClass

//@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//data class Endpoint8<
//        P1 : Par,
//        P2 : Par,
//        P3 : Par,
//        P4 : Par,
//        P5 : Par,
//        P6 : Par,
//        P7 : Par,
//        P8 : Par,
//        RETURN : Any>(
//    override val params: EndpointParameters,
//    override val before: (RequestWrapper) -> Unit = {},
//    override val after: After = { _, res -> res },
//    override val response: KClass<RETURN>,
//    inline val a: P1,
//    inline val b: P2,
//    inline val c: P3,
//    inline val d: P4,
//    inline val e: P5,
//    inline val f: P6,
//    inline val g: P7,
//    inline val h: P8,
//    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, Handler) () -> HttpResponse<RETURN>)? = null,
//) : Endpoint<RETURN> {
//
//    override val invoke: (Handler) -> HttpResponse<RETURN> = {
//        handler!!.invoke(a, b, c, d, e, f, g, h, it)
//    }
//
//    fun <I : Par> with(parameter: I) =
//        Endpoint9(
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
//            parameter
//        )
//
//    context(OnlyHeader)
//    override infix operator fun <T : HP> plus(t: T) = with(t)
//
//    context(OnlyQuery)
//    override infix operator fun <T : QP> plus(t: T) = with(t)
//
//}