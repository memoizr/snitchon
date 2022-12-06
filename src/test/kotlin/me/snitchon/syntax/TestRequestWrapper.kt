package me.snitchon.syntax

import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.Parameter

data class TestRequestWrapper(
    val testRequest: TestRequest,
    val path: String
) : RequestWrapper {
    override fun <T: Any> body(body: Class<T>): T = testRequest.body as T

    override fun method(): HTTPMethod = HTTPMethod.fromString("PUT")

    override fun params(name: String): String? = path.parse(testRequest.path)[name].also { println(it) }

    override fun headers(name: String): String? = testRequest.headers[name]

    override fun getParam(param: Parameter<*,*>): String? {
        return testRequest.path.parseQuery()[param.name]
    }
}