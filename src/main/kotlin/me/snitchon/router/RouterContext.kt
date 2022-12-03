package me.snitchon.router

import HeaderParameter
import Parameter
import PathParameter
import me.snitchon.endpoint.*
import me.snitchon.http.EmbodiedEndpointCall
import me.snitchon.http.EndpointCall
import me.snitchon.http.HTTPMethod
import me.snitchon.parameter.ParametrizedPath1
import me.snitchon.parameter.ParametrizedPath2
import kotlin.reflect.KClass

internal typealias PP<T> = PathParameter<T>

data class Body<T : Any>(val klass: KClass<T>): Bodied<T, Body<T>> {
    //, val customGson: Klaxon = klaxon)
    override val name: String
        get() = "Body"
    override val description: String
        get() = "Body of the request"

}
object HasBody: Parameter {
    override val name: String
        get() = TODO("Not yet implemented")
    override val description: String
        get() = TODO("Not yet implemented")
}

interface Bodied<T: Any, A: Body<T>>: Parameter {
    context (EndpointCall, A, HasBody)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val body get() = request.body<T>()
}


object RouterContext {
    fun GET(path: String) = Endpoint0<Any>(HTTPMethod.GET, path.ensureLeadingSlash())


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
    fun <Ret> Endpoint0<Any>.isHandledBy(block: context (EndpointCall) ()-> Ret): Endpoint0<Ret> {
        val end = Endpoint0<Ret>(httpMethod, url)
        endpoints.add(EndpointBundle(end, block))
        return end
    }

    context(Router)
    fun <A : Parameter, Ret> Endpoint1<A, Nothing>.isHandledBy(block: context(A, EndpointCall) () -> Ret): Endpoint1<A, Ret> {
        val end = Endpoint1<A, Ret>(httpMethod, url, p1)
        endpoints.add(EndpointBundle1(end, block))
        return end
    }

    context(Router)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Parameter, B: Parameter, Ret> Endpoint2<A, B, Nothing>.isHandledBy(block: context(A, B, EndpointCall) () -> Ret): Endpoint2<A, B, Ret> {
        val end = Endpoint2<A, B, Ret>(httpMethod, url, p1, p2)
        endpoints.add(EndpointBundle2(end, block))
        return end
    }

    context(Router)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Parameter, B: Parameter, C: Parameter, Ret> Endpoint3<A, B, C, Nothing>.isHandledBy(block: context(A, B, C, EndpointCall) () -> Ret): Endpoint3<A, B, C, Ret> {
        val end = Endpoint3<A, B, C, Ret>(httpMethod, url, p1, p2, p3)
        endpoints.add(EndpointBundle3(end, block))
        return end
    }

    context(Router)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Parameter, B: Parameter, C: Parameter, D: Parameter, Ret> Endpoint4<A, B, C, D, Nothing>.isHandledBy(block: context(A, B, C, D, EndpointCall) () -> Ret): Endpoint4<A, B, C, D, Ret> {
        val end = Endpoint4<A, B, C, D, Ret>(httpMethod, url, p1, p2, p3, p4)
        endpoints.add(EndpointBundle4(end, block))
        return end
    }

    context(Router)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Parameter, B: Parameter, C: Parameter, D: Parameter, E: Parameter, Ret> Endpoint5<A, B, C, D, E, Nothing>.isHandledBy(block: context(A, B, C, D, E, EndpointCall) () -> Ret): Endpoint5<A, B, C, D, E, Ret> {
        val end = Endpoint5<A, B, C, D, E, Ret>(httpMethod, url, p1, p2, p3, p4, p5)
        endpoints.add(EndpointBundle5(end, block))
        return end
    }

    context(Router)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun <A : Parameter, B: Parameter, C: Parameter, D: Parameter, E: Parameter, F: Parameter, Ret> Endpoint6<A, B, C, D, E, F, Nothing>.isHandledBy(block: context(A, B, C, D, E, F, EndpointCall) () -> Ret): Endpoint6<A, B, C, D, E, F, Ret> {
        val end = Endpoint6<A, B, C, D, E, F, Ret>(httpMethod, url, p1, p2, p3, p4, p5, p6)
        endpoints.add(EndpointBundle6(end, block))
        return end
    }

//    context(Router)
//    fun <A : Parameter, BODY: Any, Ret> Endpoint1Bodied<A, BODY, Nothing>.isHandledBy(block: context(A, EmbodiedEndpointCall<BODY>) () -> Ret): Endpoint1<A, Ret> {
//        val end = Endpoint1<A, BODY, Ret>(httpMethod, url, p1, body)
//        endpoints.add(EndpointBundle1Bodied(end, block))
//        return end
//    }
//
//    context(Router)
//            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//    fun <A : Parameter, B: Parameter, BODY: Any, Ret> Endpoint2Bodied<A, B, BODY, Nothing>.isHandledBy(block: context(A, B, EmbodiedEndpointCall<BODY>) () -> Ret): Endpoint2Bodied<A, B, BODY, Ret> {
//        val end = Endpoint2Bodied<A, B, BODY, Ret>(httpMethod, url, p1, p2, body)
//        endpoints.add(EndpointBundle2Bodied(end, block))
//        return end
//    }

//    context(Router)
//    fun <A : Parameter, BODY: Any, Ret> Endpoint1<A, Nothing, Ret>.with(body: Body<BODY>): Endpoint1<A, BODY, Ret> {
//        return Endpoint1(httpMethod, url, p1, body)
//    }

//    context(Router)
//    fun <A : Parameter, B: Parameter, BODY: Any, Ret> Endpoint2<A, B, Ret>.with(body: Body<BODY>): Endpoint1Bodied<A, BODY, Ret> {
//        return Endpoint1Bodied(httpMethod, url, p1, body)
//    }

//    context(Router)

    fun <A : Parameter, B, C: Body<B>, Ret> Endpoint1<A, Ret>.with(body: C): Endpoint3<A, C, HasBody, Ret> {
        return Endpoint3(httpMethod, url, p1, body, HasBody)
    }

    fun <A : Parameter, B: Parameter, C, D: Body<C>, Ret> Endpoint2<A, B, Ret>.with(body: D): Endpoint4<A, B, D, HasBody, Ret> {
        return Endpoint4(httpMethod, url, p1, p2, body, HasBody)
    }

    fun <A : Parameter, B: Parameter, C: Parameter, D, E: Body<D>, Ret> Endpoint3<A, B, C, Ret>.with(body: E): Endpoint5<A, B, C, E, HasBody, Ret> {
        return Endpoint5(httpMethod, url, p1, p2, p3, body, HasBody)
    }

    fun <A : Parameter, B: Parameter, C: Parameter, D: Parameter, E, F: Body<E>, Ret> Endpoint4<A, B, C, D, Ret>.with(body: F): Endpoint6<A, B, C, D, F, HasBody, Ret> {
        return Endpoint6(httpMethod, url, p1, p2, p3, p4, body, HasBody)
    }

    fun <A : Parameter, B: Parameter, Ret> Endpoint1<A, Ret>.with(p: B): Endpoint2<A, B, Ret> {
        return Endpoint2(httpMethod, url, p1, p)
    }

    fun <A : Parameter, B: Parameter, D: Body<B>, Ret> Endpoint1<A, Ret>.with(p2: D): Endpoint2<A, D, Ret> {
        return Endpoint2(httpMethod, url, p1, p2)
    }

    fun <A : Parameter, B: Parameter, C: Parameter, Ret> Endpoint2<A, B, Ret>.with(p3: C): Endpoint3<A, B, C, Ret> {
        return Endpoint3(httpMethod, url, p1, p2, p3)
    }

    fun <A : Parameter, B: Parameter, C: Parameter, D: Parameter, Ret> Endpoint3<A, B, C, Ret>.with(p4:D): Endpoint4<A, B, C, D, Ret> {
        return Endpoint4(httpMethod, url, p1, p2, p3, p4)
    }

    fun <A : Parameter, B: Parameter, C: Parameter, D: Parameter, E: Parameter, Ret> Endpoint4<A, B, C, D, Ret>.with(p5:E): Endpoint5<A, B, C, D, E, Ret> {
        return Endpoint5(httpMethod, url, p1, p2, p3, p4, p5)
    }
}