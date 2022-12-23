package me.snitchon.tests

import me.snitchon.parsers.GsonJsonParser
import me.snitchon.parsers.GsonJsonParser.parseJson
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*
import com.snitch.HttpResponses
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.router.Router
import me.snitchon.router.HttpMethods
import me.snitchon.router.SlashSyntax
import me.snitchon.service.SnitchService
import java.net.BindException
import java.net.ConnectException

private fun <T> retry(block: () -> T): T {
    fun go(): T {
        try {
            return block()
        } catch (b: BindException) {
            return go()
        } catch (e: ConnectException) {
            return go()
        }
    }
    return go()
}

typealias ServiceFactory = (Int) -> SnitchService

abstract class SnitchTest(
    val service: ServiceFactory
) {

    protected val whenPerform = this
    val port = Random().nextInt(5000) + 2000

    infix fun Get(endpoint: String): Expectation {
        return Expectation(port, HttpMethod.GET, endpoint)
    }

    infix fun Post(endpoint: String): Expectation {
        return Expectation(port, HttpMethod.POST, endpoint)
    }

    infix fun Delete(endpoint: String): Expectation {
        return Expectation(port, HttpMethod.DELETE, endpoint)
    }

    infix fun Put(endpoint: String): Expectation {
        return Expectation(port, HttpMethod.PUT, endpoint)
    }

    enum class HttpMethod {
        POST, GET, PUT, DELETE;
    }

    data class Expectation(
        val port: Int,
        private val method: HttpMethod,
        private val endpoint: String,
        private val headers: Map<String, String> = emptyMap(),
        private val body: Any? = null
    ) {

        fun call(
            url: String,
            headers: Map<String, String>,
            fn: HttpRequest.Builder.() -> HttpRequest.Builder
        ) =
            retry {
                clnt.send(
                    HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .apply {
                            headers.forEach {
                                setHeader(it.key, it.value)
                            }
                        }
                        .fn()
                        .build(), HttpResponse.BodyHandlers.ofString()
                )
            }

        fun get(url: String, headers: Map<String, String>) = call(url, headers) { GET() }
        fun post(url: String, headers: Map<String, String>, body: String?) =
            call(url, headers) { POST(HttpRequest.BodyPublishers.ofString(body.orEmpty())) }

        fun put(url: String, headers: Map<String, String>, body: String?) =
            call(url, headers) { PUT(HttpRequest.BodyPublishers.ofString(body.orEmpty())) }

        fun delete(url: String, headers: Map<String, String>) = call(url, headers) { DELETE() }

        val response: HttpResponse<String> by lazy {
            with(GsonJsonParser) {
                when (method) {
                    HttpMethod.GET -> get("http://localhost:${port}$endpoint", headers)
                    HttpMethod.PUT -> put("http://localhost:${port}$endpoint", headers, body?.jsonString)
                    HttpMethod.POST -> post("http://localhost:${port}$endpoint", headers, body?.jsonString)
                    HttpMethod.DELETE -> delete("http://localhost:${port}$endpoint", headers)
                }
            }
        }

        infix fun withBody(body: Any) = copy(body = body)

        infix fun withHeaders(headers: Map<String, Any?>) =
            copy(headers = headers.map { it.key to it.value.toString() }.toMap())

        infix fun expectBody(body: String) = apply {
            com.memoizr.assertk.expect that response.body() isEqualTo body
        }

        infix fun expectCode(code: Int) = apply {
            com.memoizr.assertk.expect that response.statusCode() isEqualTo code
        }

        infix fun expect(block: (HttpResponse<String>) -> Unit): Expectation {
            block(response)
            return this
        }

        infix inline fun <reified T : Any> expectBodyJson(body: T) = apply {
            val r = response.body()
            com.memoizr.assertk.expect that r.parseJson(T::class.java) isEqualTo body
        }
    }

    fun routes(
        router: context(
        ParameterMarkupDecorator,
        HttpMethods,
        SlashSyntax,
        HttpResponses
        ) Router.() -> Unit
    ) {
        retry {
            with(GsonJsonParser) {
                service(port).withRoutes(router).startListening().handleInvalidParams()
            }
        }
    }
}

private val clnt = java.net.http.HttpClient.newBuilder().build()
