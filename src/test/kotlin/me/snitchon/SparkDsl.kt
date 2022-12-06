package com.snitch.spark

import com.snitch.*
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.HeaderParameter
import me.snitchon.parameter.Parameter
import me.snitchon.parameter.PathParameter
import me.snitchon.parameter.QueryParameter
import me.snitchon.syntax.GsonJsonParser.parseJson
import spark.Request
import spark.Response

class SparkRequestWrapper(val request: Request) : RequestWrapper {

    override fun <T: Any> body(body: Class<T>): T {
        val body1 = request.body()
        return body1.parseJson(body)
    }

    override fun method(): HTTPMethod = HTTPMethod.fromString(request.requestMethod())

    override fun params(name: String): String? = request.params(name)

    override fun headers(name: String): String? = request.headers(name)

    override fun getParam(param: Parameter<*, *>): String? {
        return when (param) {
            is PathParameter<*,*> ->
                request.params(param.name)
                    .let { if (it != null && param.emptyAsMissing && it.isEmpty()) null else it }
            is QueryParameter<*, *> ->
                request.queryParams(param.name)//.filterValid(param)
            is HeaderParameter<*,*> ->
                request.headers(param.name)//.filterValid(param)
            else -> TODO()
        }
    }
}

class SparkResponseWrapper(val response: Response): ResponseWrapper {
//    override fun setStatus(code: Int) = response.status(code)
//    override fun setType(type: Format) = response.type(type.type)
}