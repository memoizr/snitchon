package me.snitchon.spark

import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.*
import me.snitchon.parsing.Parser
import me.snitchon.path.Path
import spark.Request
import spark.Response

class SparkRequestWrapper(val request: Request, val parser: Parser) : RequestWrapper {

    override fun <T : Any> myBody(body: Class<T>): T {
        val body1 = request.body()
        return with(parser) { body1.parseJson(body) }
    }

    override fun method(): HTTPMethod = HTTPMethod.fromString(request.requestMethod())

    override fun <RAW, PARSED : Any?> getParam(param: Parameter<RAW, PARSED>): String? = when (param) {
        is Path<*> -> request.params(param.name)
        is Query<*, *> -> request.queryParams(param.name)
        is Header<*, *> -> request.headers(param.name)
        else -> TODO()
    }
}

class SparkResponseWrapper(val response: Response) : ResponseWrapper {
    override fun setBody(body: String) = response.body(body)
    override fun setStatus(code: Int) = response.status(code)
}