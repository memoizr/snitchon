package me.snitchon.syntax

import com.snitch.HttpResponses
import me.snitchon.endpoint.*
import me.snitchon.http.*
import me.snitchon.router.Router
import me.snitchon.router.HttpMethods
import me.snitchon.router.SlashSyntax
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService

@Suppress("UNCHECKED_CAST")
class TestSnitchService : SnitchService {
    val service = mutableSetOf<Pair<TestRequest, context (Handler) () -> Any?>>()

    fun setRoutes(routerConfiguration: context(HttpMethods, SlashSyntax, SnitchService, HttpResponses) Router.() -> Unit): RoutedService {
        val router = with(HttpMethods) {
            Router()
        }
        routerConfiguration(HttpMethods, SlashSyntax, this, HttpResponses, router)
        return RoutedService(this, router)
    }

    override fun registerMethod(bundle: Endpoint<*>, path: String) {
        service.add(TestRequest(bundle.params.httpMethod, bundle.params.url) to bundle.invoke)
    }

    override fun <T : Exception> handleException(
        exception: Class<T>,
        handler: (T, RequestWrapper, ResponseWrapper) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    fun makeRequest(request: TestRequest): Any? {
        val requestFunction1Pair =
            service.find { it.first.path.match(request.path) && it.first.method == request.method }

        val func = requestFunction1Pair?.second
        val testRequestWrapper = TestRequestWrapper(request, requestFunction1Pair?.first?.path.orEmpty())
        val response = object : ResponseWrapper {
            override fun setStatus(code: Int) {
                TODO("Not yet implemented")
            }

            override fun setBody(body: String) {
                TODO("Not yet implemented")
            }
        }
        return func?.invoke(request.body?.let { EmbodiedEndpointCall(testRequestWrapper, response, it) }
            ?: DisembodiedEndpointCall(testRequestWrapper, response))
    }
}

fun String.parseQuery(): Map<String, String> {
    val map = this.dropWhile { it != '?'}
        .drop(1)
        .split('&')
        .map { val (name, value) = it.split('=')
            name to value
        }.toMap()
    return map
}


fun String.parse(url: String): Map<String, String> {
    val map = this.split('/').filter { it.isNotBlank() }
        .zip(url.takeWhile { it != '?'}.split('/').filter { it.isNotBlank() })
        .filter { it.first.startsWith(':') }
        .map { it.copy(first = it.first.drop(1)) }
        .toMap()
    return map
}

fun String.match(url: String): Boolean {
    val theseParts = this.split('/').filter { it.isNotBlank() }
    val otherParts = url.split('/').filter { it.isNotBlank() }

    return theseParts.size == otherParts.size &&
            theseParts
                .zip(otherParts)
                .filter { !it.first.startsWith(":") }
                .all { it.first == it.second }
}
