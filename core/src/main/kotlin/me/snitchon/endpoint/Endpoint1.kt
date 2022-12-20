package me.snitchon.endpoint

import me.snitchon.documentation.Visibility
import me.snitchon.http.Handler

import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.Header
import me.snitchon.parameter.Query
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import me.snitchon.router.Par
import com.snitch.HttpResponse
import kotlin.reflect.KClass

internal typealias HP = Header<*, *>
internal typealias QP = Query<*, *>

object OnlyHeader
object OnlyQuery

context(Endpoint0<RETURN>)
infix operator fun <
        ONE : HP,
        TWO : HP,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint1<A, RETURN>)
infix operator fun <
        A : Par,
        ONE : HP,
        TWO : HP,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint2<A, B, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint3<A, B, C, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint4<A, B, C, D, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint5<A, B, C, D, E, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint6<A, B, C, D, E, F, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint7<A, B, C, D, E, F, G, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        G : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint8<A, B, C, D, E, F, G, H, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        G : Par,
        H : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)


fun <X : Endpoint<RETURN>, Y : Endpoint<RETURN>, RETURN> X.headers(block: context(X, OnlyHeader) () -> Y): Y =
    block(this@X, OnlyHeader)

fun <X : Endpoint<RETURN>, Y : Endpoint<RETURN>, RETURN> X.queries(block: context(X, OnlyQuery) () -> Y): Y =
    block(this@X, OnlyQuery)

typealias After = (RequestWrapper, ResponseWrapper) -> Unit

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint1<
        A : Par,
        RETURN : Any>(
    override val params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    val handler: (context(A, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, it)
    }

    fun <B : Par> with(parameter: B): Endpoint2<A, B, RETURN> =
        Endpoint2(
            params,
            before,
            after,
            response,
            a,
            parameter
        )

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint1<A, RETURN>) () -> X): X =
        block(this)

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint3<A, BODY, HasBody, RETURN> =
        Endpoint3(
            params,
            before,
            after,
            response,
            a,
            body,
            HasBody
        )
}
