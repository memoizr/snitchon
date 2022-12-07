package com.snitch.spark

import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.*
import me.snitchon.syntax.GsonJsonParser.parseJson
import spark.Request
import spark.Response

class SparkRequestWrapper(val request: Request) : RequestWrapper {

    override fun <T : Any> body(body: Class<T>): T {
        val body1 = request.body()
        return body1.parseJson(body)
    }

    override fun method(): HTTPMethod = HTTPMethod.fromString(request.requestMethod())

    override fun <RAW, PARSED : Any?> getParam(param: Parameter<RAW, PARSED>): PARSED {
        println("parsing")
        val value = when (param) {
            is Path<*, *> -> request.params(param.name)
            is Query<*, *> -> request.queryParams(param.name)//.filterValid(param)
            is Header<*, *> -> request.headers(param.name)//.filterValid(param)
            else -> TODO()
        }

        return value
            .let { if (it != null && param.emptyAsMissing && it.isEmpty()) null else it }
            .let { param.pattern.parse(it as RAW) }


//        return when (param) {
//            is PathParameter<*, PARSED> ->
//                request.params(param.name)
//                    .let { if (it != null && param.emptyAsMissing && it.isEmpty()) null else it }
//                    .let { param.pattern.parse(it!!) }
//
//            is QueryParameter<*, PARSED> ->
//                request.queryParams(param.name)//.filterValid(param)
//                    .let { if (it != null && param.emptyAsMissing && it.isEmpty()) null else it }
//                    .let {
//                        if (it == null)
//                            param.default?.let { param.pattern.parse(GsonJsonParser, it.invoke()) }!!
//                        else
//                            param.pattern.parse(GsonJsonParser, it)
//                    }
//            is OptionalQueryParameter<*, *> ->
//                request.queryParams(param.name)//.filterValid(param)
//                    .let { if (it != null && param.emptyAsMissing && it.isEmpty()) null else it }
//                    .let {
//                        if (it == null)
//                            param.default?.let { param.pattern.parse(GsonJsonParser, it.invoke())}
//                        else
//                            param.pattern.parse(GsonJsonParser, it)
//                    } as PARSED

//            is HeaderParameter<*, PARSED> ->
//                request.headers(param.name)//.filterValid(param)
//                    .let { if (it != null && param.emptyAsMissing && it.isEmpty()) null else it }
//                    .let {
//                        if (it == null)
//                            param.default?.let { param.pattern.parse(GsonJsonParser, it.invoke()) }!!
//                        else
//                            param.pattern.parse(it)
//                    }

//            is OptionalHeaderParameter<*, *> ->
//                request.headers(param.name)//.filterValid(param)
//                    .let { if (it != null && param.emptyAsMissing && it.isEmpty()) null else it }
//                    .let {
//                        if (it == null)
//                            param.default?.let { param.pattern.parse(GsonJsonParser, it.invoke())}
//                        else
//                            param.pattern.parse(GsonJsonParser, it) } as PARSED

        }
}

class SparkResponseWrapper(val response: Response) : ResponseWrapper {
//    override fun setStatus(code: Int) = response.status(code)
//    override fun setType(type: Format) = response.type(type.type)
}