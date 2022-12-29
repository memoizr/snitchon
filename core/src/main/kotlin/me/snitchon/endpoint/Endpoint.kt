package me.snitchon.endpoint

import me.snitchon.documentation.Visibility
import me.snitchon.http.HTTPMethod
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.http.HttpResponse
import kotlin.reflect.KClass

interface Endpoint<W: RequestWrapper, R : Any> {
    var params: EndpointParameters
    val invoke: (Handler<W>) -> HttpResponse<R>
    val response: KClass<R>

    context(OnlyHeader)
    infix operator fun <T : HP> plus(t: T): Endpoint<W, R>

    context(OnlyQuery)
    infix operator fun <T : QP> plus(t: T): Endpoint<W, R>
}

fun <T : Any, W: RequestWrapper, E: Endpoint<W, T>> E.description(description: String) = apply { params = params.copy(description = description) }
fun <T : Any, W: RequestWrapper, E: Endpoint<W, T>> E.summary(summary: String) = apply { params = params.copy(summary = summary) }
fun <T : Any, W: RequestWrapper, E: Endpoint<T, W>> E.visibility(visibility: Visibility): E = apply { params = params.copy(visibility = visibility) }


data class EndpointParameters(
    val httpMethod: HTTPMethod,
    val url: String,
    val summary: String?,
    val description: String?,
    val visibility: Visibility,
)