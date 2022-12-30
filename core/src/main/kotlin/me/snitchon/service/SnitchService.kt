package me.snitchon.service

import me.snitchon.MissingRequiredParameterException
import me.snitchon.ValidationException
import me.snitchon.config.Config
import me.snitchon.endpoint.*
import me.snitchon.http.Group
import me.snitchon.parsing.Parser
import me.snitchon.http.HttpResponse
import me.snitchon.http.HttpResponses
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.router.*

interface SnitchService<W: RequestWrapper> {
    val config: Config get() = Config()
    fun registerMethod(bundle: Endpoint<W, Group, Any?, *>, path: String)

    fun withRoutes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        HttpMethods<W>,
        SlashSyntax<W>,
        HttpResponses
        )
        Router<W>.() -> Unit
    ): RoutedService<W>

    fun <T : Exception> handleException(exception: Class<T>, handler: (T) -> HttpResponse<*>)

    fun onStart() {}
}

data class RoutedService<W: RequestWrapper>(val service: SnitchService<W>, val router: Router<W>) {
    fun startListening(): RoutedService<W> {
        router.endpoints.forEach {
            service.registerMethod(it, it.meta.url.ensureLeadingSlash())
        }

        service.onStart()

        return this
    }

    context(Parser)
    fun handleInvalidParams() {
        service.handleException(ValidationException::class.java) { e ->
            HttpResponse.ErrorHttpResponse<Any, List<String>>(
                400,
                listOf(e.invalidValue)
            )
        }

        service.handleException(MissingRequiredParameterException::class.java) { e ->
            HttpResponse.ErrorHttpResponse<Any, List<String>>(
                400,
                listOf(e.message!!)
            )
        }
    }
}

