package me.snitchon.router

import Parameter
import PathParameter
import me.snitchon.endpoint.*
import me.snitchon.http.EndpointCall
import me.snitchon.http.HTTPMethod
import me.snitchon.parameter.ParametrizedPath1
import me.snitchon.parameter.ParametrizedPath2
import kotlin.reflect.KClass

internal typealias PP<T> = PathParameter<T>
internal typealias Par = Parameter

data class Body<T : Any>(val klass: KClass<T>) : Bodied<T, Body<T>> {
    //, val customGson: Klaxon = klaxon)
    override val name: String
        get() = "Body"
    override val description: String
        get() = "Body of the request"

}

object HasBody : Parameter {
    override val name: String
        get() = TODO("Not yet implemented")
    override val description: String
        get() = TODO("Not yet implemented")
}

interface Bodied<T : Any, A : Body<T>> : Parameter {
    context (EndpointCall, A, HasBody)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val body
        get() = request.body<T>()
}

object RouterContext {
    fun GET(path: String) = Endpoint0<Nothing>(HTTPMethod.GET, path.ensureLeadingSlash())

    fun <P1 : PP<P1>>
            GET(path: ParametrizedPath1<P1>) =
        Endpoint1<P1, Nothing>(HTTPMethod.GET, path.path, path.a)

    fun <A : PP<A>, B : PP<B>>
            GET(path: ParametrizedPath2<A, B>) =
        Endpoint2<A, B, Nothing>(HTTPMethod.GET, path.path, path.a, path.b)


    operator fun String.div(path: String): String {
        return this.ensureLeadingSlash() + "/" + path
    }

    operator fun <P : PP<P>> String.div(path: P): ParametrizedPath1<P> {
        return ParametrizedPath1(this + ":${path.name}".ensureLeadingSlash(), path)
    }

    operator fun <P1 : PP<P1>> ParametrizedPath1<P1>.div(path: String): ParametrizedPath1<P1> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }

    operator fun <P1 : PP<P1>, P2 : PP<P2>> ParametrizedPath1<P1>.div(path: P2): ParametrizedPath2<P1, P2> {
        return ParametrizedPath2(this.path + path.name.ensureLeadingSlash(), this.a, path)
    }

    fun String.ensureLeadingSlash() = if (!startsWith("/")) "/$this" else this

    context(Router)
    fun <RETURN> Endpoint0<Nothing>.isHandledBy(
        handler: context (EndpointCall) () -> RETURN
    ) =
        Endpoint0(httpMethod, url, handler)
            .also { endpointss.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Par, RETURN>
            Endpoint1<A, Nothing>.isHandledBy(
        handler: context(A, EndpointCall) () -> RETURN
    ) =
        Endpoint1(httpMethod, url, a, handler)
            .also { endpointss.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Par, B : Par, RETURN>
            Endpoint2<A, B, Nothing>.isHandledBy(
        handler: context(A, B, EndpointCall) () -> RETURN
    ) =
        Endpoint2(httpMethod, url, a, b, handler)
            .also { endpointss.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Par, B : Par, C : Par, R>
            Endpoint3<A, B, C, Nothing>.isHandledBy(
        handler: context(A, B, C, EndpointCall) () -> R
    ) =
        Endpoint3(httpMethod, url, a, b, c, handler)
            .also(endpointss::add)

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Par, B : Par, C : Par, D : Par, RETURN>
            Endpoint4<A, B, C, D, Nothing>.isHandledBy(
        handler: context(A, B, C, D, EndpointCall) () -> RETURN
    ) =
        Endpoint4(httpMethod, url, a, b, c, d, handler)
            .also { endpointss.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Par, B : Par, C : Par, D : Par, E : Par, RETURN>
            Endpoint5<A, B, C, D, E, Nothing>.isHandledBy(
        handler: context(A, B, C, D, E, EndpointCall) () -> RETURN
    ) =
        Endpoint5(httpMethod, url, a, b, c, d, e, handler)
            .also { endpointss.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Par, B : Par, C : Par, D : Par, E : Par, F: Par, RETURN>
            Endpoint6<A, B, C, D, E, F, Nothing>.isHandledBy(
        handler: context(A, B, C, D, E, F, EndpointCall) () -> RETURN
    ) =
        Endpoint6(httpMethod, url, a, b, c, d, e, f, handler)
            .also { endpointss.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, RETURN>
            Endpoint7<A, B, C, D, E, F, G, Nothing>.isHandledBy(
        handler: context(A, B, C, D, E, F, G, EndpointCall) () -> RETURN
    ) =
        Endpoint7(httpMethod, url, a, b, c, d, e, f, g, handler)
            .also { endpointss.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, RETURN>
            Endpoint8<A, B, C, D, E, F, G, H, Nothing>.isHandledBy(
        handler: context(A, B, C, D, E, F, G, H, EndpointCall) () -> RETURN
    ) =
        Endpoint8(httpMethod, url, a, b, c, d, e, f, g, h, handler)
            .also { endpointss.add(it) }

//    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//    fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, RETURN>

//    fun <A : Par, B : Par, C : Par, D : Par, RETURN>
//            Endpoint3<A, B, C, RETURN>.with(parameter: D): Endpoint4<A, B, C, D, RETURN> =
//        Endpoint4(httpMethod, url, a, b, c, parameter)
//
//    fun <A : Par, B : Par, C : Par, D : Par, E : Par, RETURN>
//            Endpoint4<A, B, C, D, RETURN>.with(parameter: E): Endpoint5<A, B, C, D, E, RETURN> =
//        Endpoint5(httpMethod, url, a, b, c, d, parameter)
//
//    fun <A : Par, B : Par, C : Par, D : Par, E : Par, F: Par, RETURN>
//            Endpoint5<A, B, C, D, E, RETURN>.with(parameter: F): Endpoint6<A, B, C, D, E, F, RETURN> =
//        Endpoint6(httpMethod, url, a, b, c, d, e, parameter)
//
//    fun <A : Par, B : Par, C : Par, D : Par, E : Par, F: Par, G: Par, RETURN>
//            Endpoint6<A, B, C, D, E, F, RETURN>.with(parameter: G): Endpoint7<A, B, C, D, E, F, G, RETURN> =
//        Endpoint7(httpMethod, url, a, b, c, d, e, f, parameter)
//
//    // BODY
//
//    fun <A : Par, B : Par, C : Par, D, BODY : Body<D>, RETURN>
//            Endpoint3<A, B, C, RETURN>.with(body: BODY): Endpoint5<A, B, C, BODY, HasBody, RETURN> =
//        Endpoint5(httpMethod, url, a, b, c, body, HasBody)
//
//    fun <A : Par, B : Par, C : Par, D : Par, E, BODY : Body<E>, RETURN>
//            Endpoint4<A, B, C, D, RETURN>.with(body: BODY): Endpoint6<A, B, C, D, BODY, HasBody, RETURN> =
//        Endpoint6(httpMethod, url, a, b, c, d, body, HasBody)
//
//    fun <A : Par, B : Par, C : Par, D : Par, E : Par, F, BODY : Body<F>, RETURN>
//            Endpoint5<A, B, C, D, E, RETURN>.with(body: BODY): Endpoint7<A, B, C, D, E, BODY, HasBody, RETURN> =
//        Endpoint7(httpMethod, url, a, b, c, d, e, body, HasBody)
}