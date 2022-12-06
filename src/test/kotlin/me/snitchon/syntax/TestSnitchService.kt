package me.snitchon.syntax

import me.snitchon.endpoint.*
import me.snitchon.http.*
import me.snitchon.router.Router
import me.snitchon.router.RouterContext
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService

@Suppress("UNCHECKED_CAST")
class TestSnitchService : SnitchService {
    val service = mutableSetOf<Pair<TestRequest, context (Handler) () -> Any?>>()

    fun setRoutes(routerConfiguration: context(RouterContext, SnitchService) Router.() -> Unit): RoutedService {
        val router = with(RouterContext) {
            Router()
        }
        routerConfiguration(RouterContext, this, router)
        return RoutedService(this, router)
    }

    override fun registerMethod(bundle: Endpoint<*>, path: String) {
        service.add(TestRequest(bundle.httpMethod, bundle.url) to bundle.invoke)
    }

    fun makeRequest(request: TestRequest): Any? {
        val requestFunction1Pair =
            service.find { it.first.path.match(request.path) && it.first.method == request.method }

        val func = requestFunction1Pair?.second
        val testRequestWrapper = TestRequestWrapper(request, requestFunction1Pair?.first?.path.orEmpty())
        val response = object : ResponseWrapper {}
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
