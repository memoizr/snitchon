package me.snitchon.router

import PathParameter
import me.snitchon.endpoint.Endpoint0
import me.snitchon.endpoint.Endpoint1
import me.snitchon.endpoint.Endpoint2
import me.snitchon.endpoint.EndpointBundle
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.ParametrizedPath1
import me.snitchon.parameter.ParametrizedPath2

object RouterContext {
    fun GET(path: String) = Endpoint0<Nothing, Any>(HTTPMethod.GET, path)
    fun <A: PathParameter> GET(path: ParametrizedPath1<A>) = Endpoint1<A, Nothing>(HTTPMethod.GET, path.path, path.a)
    fun <A: PathParameter,
            B: PathParameter,
            > GET(path: ParametrizedPath2<A,B>) = Endpoint2<A, B, Nothing>(HTTPMethod.GET, path.path, path.a, path.b)


    operator fun String.div(path: String) = this.leadingSlash + "/" + path

    operator fun <P: PathParameter>String.div(path: P) = ParametrizedPath1(this + path.name.leadingSlash, path)
    operator fun <P: PathParameter>ParametrizedPath1<P>.div(path: String) = this.copy(this.path+path.leadingSlash)

    operator fun <A: PathParameter, B: PathParameter>ParametrizedPath1<A>.div(path: B) = ParametrizedPath2(this.path + path.name.leadingSlash, this.a, path)

    private val String.leadingSlash get() = if (!startsWith("/")) "/$this" else this

    context(Router)
    fun <BODY, Ret> Endpoint0<BODY, Ret>.isHandledBy(block: (RequestWrapper) -> Ret): Endpoint0<BODY, Ret> {
        endpoints.add(EndpointBundle(this, block))
        return this
    }
}