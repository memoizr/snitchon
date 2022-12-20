package me.snitchon.endpoint

import com.snitch.HttpResponse
import me.snitchon.documentation.Visibility
import me.snitchon.http.HTTPMethod
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.router.Par
import kotlin.reflect.KClass

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint8<
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        G : Par,
        H : Par,
        RETURN : Any>(
    override val params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    inline val g: G,
    inline val h: H,
    val handler: (context(A, B, C, D, E, F, G, H, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {
    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, b, c, d, e, f, g, h, it)
    }

    fun <I : Par> with(parameter: I) =
        Endpoint9(
            params,
            before,
            after,
            response,
            a,
            b,
            c,
            d,
            e,
            f,
            g,
            h,
            parameter
        )

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)

}