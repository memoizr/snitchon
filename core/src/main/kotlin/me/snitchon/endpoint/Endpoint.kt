package me.snitchon.endpoint

import com.snitch.HttpResponse
import me.snitchon.documentation.Visibility
import me.snitchon.http.HTTPMethod
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import kotlin.reflect.KClass

interface Endpoint<R : Any> {
    val httpMethod: HTTPMethod
    val url: String
    val invoke: (Handler) -> HttpResponse<R>
    context(OnlyHeader)
    infix operator fun <T : HP> plus(t: T): Endpoint<R>

    context(OnlyQuery)
    infix operator fun <T : QP> plus(t: T): Endpoint<R>

    val summary: String?
    val description: String?
    val visibility: Visibility
    val before: (RequestWrapper) -> Unit
    val after: (RequestWrapper, ResponseWrapper) -> Unit
    val response: KClass<R>
}