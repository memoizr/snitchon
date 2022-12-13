package me.snitchon.endpoint

import com.snitch.HttpResponse
import me.snitchon.documentation.Visibility
import me.snitchon.http.HTTPMethod
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import me.snitchon.router.Par
import kotlin.reflect.KClass

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint4<
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    val handler: (context(A, B, C, D, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, b, c, d, it)
    }

    infix fun <E : Par> with(parameter: E): Endpoint5<A, B, C, D, E, RETURN> =
        Endpoint5(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            d,
            parameter
        )

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)


    fun <E, BODY : Body<E>> with(body: BODY): Endpoint6<A, B, C, D, BODY, HasBody, RETURN> =
        Endpoint6(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            d,
            body,
            HasBody
        )
}