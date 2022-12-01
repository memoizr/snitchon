package me.snitchon.router

import PathParameter
import me.snitchon.endpoint.Endpoint0
import me.snitchon.endpoint.Endpoint1
import me.snitchon.http.HTTPMethod
import me.snitchon.parameter.ParametrizedPath1

object RouterContext {
    fun GET(path: String) = Endpoint0<Nothing>(HTTPMethod.GET, path)
    fun <A: PathParameter> GET(path: ParametrizedPath1<A>) = Endpoint1<A, Nothing>(HTTPMethod.GET, path.path, path.a)


    operator fun String.div(path: String) = this.leadingSlash + "/" + path
    operator fun <P: PathParameter>String.div(path: P) = ParametrizedPath1(this, path)

    private val String.leadingSlash get() = if (!startsWith("/")) "/$this" else this
}