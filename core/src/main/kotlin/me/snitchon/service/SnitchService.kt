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
import me.snitchon.parameter.ParametrizedPath0
import me.snitchon.parameter.PathElement
import me.snitchon.router.*

interface SnitchService<W : RequestWrapper> {
    val config: Config get() = Config()
    val markup: ParameterMarkupDecorator
    fun registerMethod(bundle: Endpoint<W, Group, Any, *>, path: String)

    fun withRoutes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        GetHttpMethods,
        SlashSyntax<W>,
        HttpResponses
        )
        Router<W, ParametrizedPath0>.() -> Unit
    ): RoutedService<W>

    fun <T : Exception> handleException(exception: Class<T>, handler: (T) -> HttpResponse<*>)

    fun onStart() {
    }
}

context(ParameterMarkupDecorator)
private val List<PathElement>.stringRep
    get() =
        let { if ((it.lastOrNull() as? PathElement.PathConstant)?.constant?.isEmpty() == true) it.dropLast(1) else it }
            .map {
                when (it) {
                    is PathElement.PathConstant -> it.constant.ensureLeadingSlash()
                    is PathElement.PathVariable<*> -> it.path.markupName.ensureLeadingSlash()
                }
            }.joinToString("")

data class RoutedService<W : RequestWrapper>(
    val service: SnitchService<W>,
    val router: Router<W, ParametrizedPath0>,
) {
    fun startListening(): RoutedService<W> {
        router.endpoints.forEach {
            service.registerMethod(it, with(service.markup) { it.meta.path.stringRep.ensureLeadingSlash() })
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
        service.handleException(Exception::class.java) { e ->
            HttpResponse.ErrorHttpResponse<Any, List<String>>(
                500,
                listOf(e.message!!)
            )
        }
    }
}

