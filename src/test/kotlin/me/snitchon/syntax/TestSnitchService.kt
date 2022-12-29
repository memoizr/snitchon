package me.snitchon.syntax

import me.snitchon.http.HttpResponses
import me.snitchon.endpoint.*
import me.snitchon.http.*
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.router.Router
import me.snitchon.router.HttpMethods
import me.snitchon.router.SlashSyntax
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService
import me.snitchon.http.HttpResponse
import me.snitchon.parsers.GsonJsonParser.jsonString

class TestMarkup : ParameterMarkupDecorator {
    override fun decorate(name: String): String = ":$name"
}

@Suppress("UNCHECKED_CAST")
class TestSnitchService : SnitchService<TestRequestWrapper> {
    val service = mutableSetOf<Pair<TestRequest, context (Handler<TestRequestWrapper>) () -> Any?>>()

    override fun withRoutes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        HttpMethods<TestRequestWrapper>,
        SlashSyntax<TestRequestWrapper>,
        HttpResponses
        ) Router<TestRequestWrapper>.() -> Unit
    ): RoutedService<TestRequestWrapper> {
        val router = with(HttpMethods<TestRequestWrapper>()) {
            Router()
        }
        routerConfiguration(TestMarkup(), HttpMethods(), SlashSyntax(), HttpResponses, router)
        return RoutedService(this, router)
    }

    override fun registerMethod(bundle: Endpoint<TestRequestWrapper,*>, path: String) {
        service.add(TestRequest(bundle.params.httpMethod, bundle.params.url) to bundle.invoke)
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

        val result = func?.invoke(request.body?.let { BodyHandler(testRequestWrapper, it) }
            ?: NoBodyHandler(testRequestWrapper))

        return when (result) {
            is HttpResponse.SuccessfulHttpResponse<*> -> result.body?.jsonString
            is HttpResponse.ErrorHttpResponse<*,*> -> result.jsonString
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
