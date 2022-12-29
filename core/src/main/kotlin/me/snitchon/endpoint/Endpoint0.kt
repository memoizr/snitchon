package me.snitchon.endpoint

import me.snitchon.http.HttpResponse
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import kotlin.reflect.KClass

data class Endpoint0<W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    val func: (context(Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        func!!.invoke(it)
    }

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)

    fun <A : QP> with(parameter: A) =
        Endpoint1<_, W, _>(
            params,
            response,
            parameter
        )

    fun <A : HP> with(parameter: A) =
        Endpoint1<_, W, _>(
            params,
            response,
            parameter
        )

    fun <A, BODY : Body<A>> with(body: BODY) =
        Endpoint2<_, _, W,_>(
            params,
            response,
            body,
            HasBody
        )
}