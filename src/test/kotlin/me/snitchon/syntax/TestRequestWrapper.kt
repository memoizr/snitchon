package me.snitchon.syntax

import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.Parameter
import me.snitchon.parameter.Query
import me.snitchon.parameter.Header
import me.snitchon.parsing.Parser
import me.snitchon.path.Path

data class TestRequestWrapper(
    val testRequest: TestRequest,
    val path: String
) : RequestWrapper {
    context(Parser)
    override fun <T : Any> body(body: Class<T>): T = testRequest.body as T

    override fun method(): HTTPMethod = HTTPMethod.fromString("PUT")

//    override fun params(name: String): T = path.parse(testRequest.path)[name].also { println(it) }
//
//    override fun headers(name: String): String? = testRequest.headers[name]

    override fun <RAW, PARSED> getParam(param: Parameter<RAW, PARSED>): String? {

        return when (param) {
            is Query<*, *> -> testRequest.path.parseQuery()[param.name]
                .let { param.pattern.parse(it as String, param.name) } as String
            is Header<*, *> -> testRequest.headers[param.name]
                .let { param.pattern.parse(it as String, param.name) } as String
            is Path<*, *> -> {
                println(path)
                println(testRequest.path)
                path.parse(testRequest.path)[param.name]
            }
            else -> TODO()
        }
    }
}