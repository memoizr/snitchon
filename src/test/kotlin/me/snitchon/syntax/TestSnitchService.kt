package me.snitchon.syntax

import me.snitchon.http.HttpResponses
import me.snitchon.endpoint.*
import me.snitchon.http.*
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.router.Router
import me.snitchon.router.GetHttpMethods
import me.snitchon.router.SlashSyntax
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService
import me.snitchon.http.HttpResponse
import me.snitchon.parameter.ParametrizedPath0
import me.snitchon.parsers.GsonJsonParser.jsonString

class TestMarkup : ParameterMarkupDecorator {
    override fun decorate(name: String): String = ":$name"
}

@Suppress("UNCHECKED_CAST")
class TestSnitchService : SnitchService<TestRequestWrapper> {
    val service = mutableSetOf<Pair<TestRequest, Endpoint<TestRequestWrapper, Group, Any?, *>>>()

    override fun withRoutes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        GetHttpMethods<TestRequestWrapper>,
        SlashSyntax<TestRequestWrapper>,
        HttpResponses
        ) Router<TestRequestWrapper, ParametrizedPath0>.() -> Unit
    ): RoutedService<TestRequestWrapper> {
        val router = with(GetHttpMethods<TestRequestWrapper>()) {
            Router<TestRequestWrapper,ParametrizedPath0>(config, ParametrizedPath0(""))
        }
        routerConfiguration(TestMarkup(), GetHttpMethods(), SlashSyntax(), HttpResponses, router)
        return RoutedService(this, router)
    }

    override fun registerMethod(bundle: Endpoint<TestRequestWrapper, Group, Any?, *>, path: String) {
        service.add(TestRequest(bundle.meta.httpMethod, bundle.meta.url) to bundle)
    }

    override fun <T : Exception> handleException(
        exception: Class<T>,
        handler: (T) -> HttpResponse<*>
    ) {
        TODO("Not yet implemented")
    }

    fun makeRequest(request: TestRequest): Any? {
        val requestFunction1Pair =
            service.find { it.first.path.match(request.path) && it.first.method == request.method }

        val func = requestFunction1Pair?.second
        val testRequestWrapper = TestRequestWrapper(request, requestFunction1Pair?.first?.path.orEmpty())

        val result = func?.invoke?.invoke(func.group,
            func.body,
            request.body?.let { BodyHandler(testRequestWrapper, it) }
                ?: NoBodyHandler(testRequestWrapper)
        )

        return when (result) {
            is HttpResponse.SuccessfulHttpResponse<*> -> result.body?.jsonString
            is HttpResponse.ErrorHttpResponse<*, *> -> result.jsonString
            else -> TODO()
        }
    }
}

fun String.parseQuery(): Map<String, String> {
    val map = this.dropWhile { it != '?' }
        .drop(1)
        .split('&')
        .map {
            val (name, value) = it.split('=')
            name to value
        }.toMap()
    return map
}


fun String.parse(url: String): Map<String, String> {
    val map = this.split('/').filter { it.isNotBlank() }
        .zip(url.takeWhile { it != '?' }.split('/').filter { it.isNotBlank() })
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
