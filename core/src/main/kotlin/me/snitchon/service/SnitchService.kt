package me.snitchon.service

import me.snitchon.MissingRequiredParameterException
import me.snitchon.ValidationException
import me.snitchon.config.Config
import me.snitchon.endpoint.*
import me.snitchon.http.RequestWrapper
import me.snitchon.parsing.Parser
import me.snitchon.router.Router
import me.snitchon.router.HttpMethods
import me.snitchon.http.HttpResponse
import me.snitchon.http.HttpResponses
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.router.SlashSyntax
import me.snitchon.router.ensureLeadingSlash

interface SnitchService {
    val config: Config get() = Config()
    fun registerMethod(bundle: Endpoint<*>, path: String)

    fun withRoutes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        HttpMethods,
        SlashSyntax,
        HttpResponses
        )
        Router.() -> Unit
    ): RoutedService

    fun <T : Exception> handleException(exception: Class<T>, handler: (T) -> HttpResponse<*>)

    fun onStart() {}
}

data class RoutedService(val service: SnitchService, val router: Router) {
    fun startListening(): RoutedService {
        router.endpoints.forEach {
            service.registerMethod(it, it.params.url.ensureLeadingSlash())
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

