package me.snitchon.syntax

import Parameter
import me.snitchon.endpoint.Bundle
import me.snitchon.endpoint.EndpointBundle
import me.snitchon.endpoint.EndpointBundle1
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.router.Router
import me.snitchon.router.RouterContext
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService

class TestSnitchService : SnitchService {
    val service = mutableSetOf<Pair<TestRequest, (RequestWrapper) -> Any?>>()

    fun setRoutes(routerConfiguration: context(RouterContext, SnitchService) Router.() -> Unit): RoutedService {
        val router = with(RouterContext) {
            Router()
        }
        routerConfiguration(RouterContext, this, router)
        return RoutedService(this, router)
    }

    override fun registerMethod(bundle: Bundle, path: String) {
        when (bundle) {
            is EndpointBundle<*> -> service.add(
                TestRequest(bundle.endpoint.httpMethod, bundle.endpoint.url) to bundle.func
            )
            is EndpointBundle1<*, *> -> {
                bundle as EndpointBundle1<Parameter, Any>
                service.add(TestRequest(bundle.endpoint.httpMethod, bundle.endpoint.url) to bundle.func(bundle.endpoint.p1)
            )}
        }
    }

    fun makeRequest(request: TestRequest): Any? {
        val requestFunction1Pair = service.find { it.first.path.match(request.path) && it.first.method == request.method }

        val func = requestFunction1Pair?.second
        println(service)
        return func?.invoke(TestRequestWrapper(request, requestFunction1Pair.first.path))
    }

    operator fun <A, B, R> ((A,B)->R).invoke(a: A) = { b: B -> this(a, b) }
    operator fun <A, B, C, R> ((A,B,C)->R).invoke(a: A, b: B) = { c: C -> this(a, b, c) }
}


class TestRequestWrapper(val testRequest: TestRequest,
                         val path:  String) : RequestWrapper {
    override val body: String by lazy { "" }

    override fun method(): HTTPMethod = HTTPMethod.fromString("PUT")

    override fun params(name: String): String? = path.parse(testRequest.path)[name].also {println(it)}

    override fun headers(name: String): String? = ""//context.request().getHeader(name)

    override fun getParam(param: Parameter): String? {
        return ""
    }
}

data class TestRequest(val method: HTTPMethod, val path: String)

fun String.parse(url: String): Map<String, String> {
    val map = this.split('/').filter { it.isNotBlank() }
        .zip(url.split('/').filter { it.isNotBlank()})
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
