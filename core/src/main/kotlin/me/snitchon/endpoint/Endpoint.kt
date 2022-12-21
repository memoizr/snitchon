package me.snitchon.endpoint

import me.snitchon.documentation.Visibility
import me.snitchon.http.HTTPMethod
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import com.snitch.HttpResponse
import kotlin.reflect.KClass

interface Endpoint<R : Any> {
    val params: EndpointParameters
    val invoke: (Handler) -> HttpResponse<R>
    val before: (RequestWrapper) -> Unit
    val after: (RequestWrapper, ResponseWrapper) -> Unit
    val response: KClass<R>

    context(OnlyHeader)
    infix operator fun <T : HP> plus(t: T): Endpoint<R>

    context(OnlyQuery)
    infix operator fun <T : QP> plus(t: T): Endpoint<R>

//    abstract fun queries(function: () -> R): Any
}

data class EndpointParameters(
    val httpMethod: HTTPMethod,
    val url: String,
    val summary: String?,
    val description: String?,
    val visibility: Visibility,
)