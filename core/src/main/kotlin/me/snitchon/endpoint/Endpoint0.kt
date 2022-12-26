package me.snitchon.endpoint

import com.snitch.HttpResponse
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import kotlin.reflect.KClass

data class Endpoint0<RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    val func: (context(Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        func!!.invoke(it)
    }

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)

    fun <A : QP> with(parameter: A) =
        Endpoint1(
            params,
            before,
            after,
            response,
            parameter
        )

    fun <A : HP> with(parameter: A) =
        Endpoint1(
            params,
            before,
            after,
            response,
            parameter
        )

    fun <A, BODY : Body<A>> with(body: BODY) =
        Endpoint2(
            params,
            before,
            after,
            response,
            body,
            HasBody
        )
}