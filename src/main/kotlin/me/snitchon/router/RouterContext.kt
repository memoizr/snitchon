package me.snitchon.router

import PathParameter
import me.snitchon.endpoint.*
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.ParametrizedPath1
import me.snitchon.parameter.ParametrizedPath2

internal typealias PP = PathParameter

object RouterContext {
    fun GET(path: String) = Endpoint0<Any>(HTTPMethod.GET, path.ensureLeadingSlash())


    fun <P1 : PP>
            GET(path: ParametrizedPath1<P1>) =
        Endpoint1<P1, Any>(HTTPMethod.GET, path.path, path.a)

    fun <A : PP, B : PP>
            GET(path: ParametrizedPath2<A, B>) =
        Endpoint2(HTTPMethod.GET, path.path, path.a, path.b)


    operator fun String.div(path: String): String {
        return this.ensureLeadingSlash() + "/" + path
    }

    operator fun <P : PP> String.div(path: P): ParametrizedPath1<P> {
        return ParametrizedPath1(this + ":${path.name}".ensureLeadingSlash(), path)
    }

    operator fun <P1 : PP> ParametrizedPath1<P1>.div(path: String): ParametrizedPath1<P1> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }

    operator fun <P1 : PP, P2 : PP> ParametrizedPath1<P1>.div(path: P2): ParametrizedPath2<P1, P2> {
        return ParametrizedPath2(this.path + path.name.ensureLeadingSlash(), this.a, path)
    }

    fun String.ensureLeadingSlash() = if (!startsWith("/")) "/$this" else this

    context(Router)
    fun <Ret> Endpoint0<Ret>.isHandledBy(block: (RequestWrapper) -> Ret): Endpoint0<Ret> {
        endpoints.add(EndpointBundle(this, block))
        return this
    }

    context(Router)
    fun <A : PathParameter, Ret> Endpoint1<A, Ret>.isHandledBy(block: context(A, RequestWrapper) () -> Ret): Endpoint1<A, Ret> {
        endpoints.add(EndpointBundle1(this, block))
        return this
    }
}