package me.snitchon.service

import com.snitch.MissingRequiredParameterException
import com.snitch.ValidationException
import me.snitchon.config.Config
import me.snitchon.endpoint.*
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parsing.Parser
import me.snitchon.router.Router
import me.snitchon.router.HttpMethods
import com.snitch.HttpResponse
import me.snitchon.router.ensureLeadingSlash

interface SnitchService {
    val config: Config get() = Config()
    fun registerMethod(bundle: Endpoint<*>, path: String)

    fun withRoutes(routerConfiguration: Router.() -> Unit) {}

    fun <T : Exception> handleException(exception: Class<T>, handler: (T, RequestWrapper, ResponseWrapper) -> Unit)
}

data class RoutedService(val service: SnitchService, val router: Router) {
    fun startListening(): RoutedService {
        router.endpoints.forEach {
            with(HttpMethods) {
                service.registerMethod(it, it.params.url.ensureLeadingSlash())
            }
        }

        return this
    }

    context(Parser)
    fun handleInvalidParams() {
        service.handleException(ValidationException::class.java) { e, req, res ->
            res.setBody(
                HttpResponse.ErrorHttpResponse<Any, List<String>>(
                    400,
                    listOf(e.invalidValue)
                ).jsonString
            )
            res.setStatus(400)
        }

        service.handleException(MissingRequiredParameterException::class.java) { e, req, res ->
            res.setBody(
                HttpResponse.ErrorHttpResponse<Any, List<String>>(
                    400,
                    listOf(e.message!!)
                ).jsonString
            )
            res.setStatus(400)
        }
    }
}

