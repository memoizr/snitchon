package me.snitchon.router

import com.snitch.HttpResponse
import com.snitch.Validator
import me.snitchon.documentation.Visibility
import me.snitchon.endpoint.*
import me.snitchon.http.Handler
import me.snitchon.http.HTTPMethod
import me.snitchon.parameter.Parameter
import me.snitchon.parameter.ParametrizedPath1
import me.snitchon.parameter.ParametrizedPath2
import me.snitchon.parameter.PathParameter
import kotlin.reflect.KClass

internal typealias PP<T> = PathParameter<T, String>
internal typealias Par = Parameter<*, *>

data class Body<T : Any>(val klass: KClass<T>) : Bodied<T, Body<T>> {
    override val type: Class<*>
        get() = klass.java

    //, val customGson: Klaxon = klaxon)
    override val name: String
        get() = "Body"
    override val pattern: Validator<Any, T>
        get() = TODO()
    override val description: String
        get() = "body of the request"
    override val required: Boolean
        get() = TODO()
    override val emptyAsMissing: Boolean
        get() = TODO()
    override val invalidAsMissing: Boolean
        get() = TODO()
}

object HasBody : Parameter<Nothing, Nothing> {
    override val type: Class<*>
        get() = TODO("Not yet implemented")
    override val name: String
        get() = TODO("Not yet implemented")
    override val description: String
        get() = TODO("Not yet implemented")
    override val pattern: Validator<Nothing, Nothing>
        get() = TODO()
    override val required: Boolean
        get() = TODO()
    override val emptyAsMissing: Boolean
        get() = TODO()
    override val invalidAsMissing: Boolean
        get() = TODO()
}

interface Bodied<T : Any, A : Body<T>> : Parameter<Any, T> {
    context (Handler, A, HasBody)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val body
        get() = request.body<T>()
}

object RouterContext {
    fun GET( path: String, ) = Endpoint0(
        HTTPMethod.GET,
        path.ensureLeadingSlash(),
        "",
        description = null,
        visibility = Visibility.PUBLIC,
        response = Nothing::class
    )

    fun <P1 : PP<P1>>
            GET(path: ParametrizedPath1<P1>) =
        Endpoint1<P1, Nothing>(
            HTTPMethod.GET,
            path.path,
            null,
            null,
            Visibility.PUBLIC,
            {},
            {_,res -> res},
            Nothing::class,
            path.a
        )

    fun <A : PP<A>, B : PP<B>>
            GET(path: ParametrizedPath2<A, B>) =
        Endpoint2<A, B, Nothing>(HTTPMethod.GET, path.path,
            null,
            null,
            Visibility.PUBLIC,
            {},
            {_,res -> res},
            Nothing::class,
            path.a, path.b)


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
    fun <RETURN: Any> Endpoint0<Nothing>.isHandledBy(
        handler: context (Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint0(httpMethod, url, summary, description, visibility, before, after, response as KClass<RETURN>, handler)

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, reified RETURN: Any>
            Endpoint1<A, Nothing>.isHandledBy(
        noinline handler: context(A, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint1(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, handler)
            .also { endpoints.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par,
            B : Par, reified RETURN: Any>
            Endpoint2<A, B, Nothing>.isHandledBy(
        noinline handler: context(A, B, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint2(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, handler)
            .also { endpoints.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, reified RETURN: Any>
            Endpoint3<A, B, C, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint3(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, handler)
            .also(endpoints::add)

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, reified RETURN: Any>
            Endpoint4<A, B, C, D, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint4(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, handler)
            .also { endpoints.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, reified RETURN: Any>
            Endpoint5<A, B, C, D, E, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint5(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, e, handler)
            .also { endpoints.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, reified RETURN: Any>
            Endpoint6<A, B, C, D, E, F, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F,
        Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint6(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, e, f, handler)
            .also { endpoints.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, reified RETURN: Any>
            Endpoint7<A, B, C, D, E, F, G, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint7(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, e, f, g, handler)
            .also { endpoints.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, reified RETURN: Any>
            Endpoint8<A, B, C, D, E, F, G, H, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, H, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint8(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, e, f, g, h, handler)
            .also { endpoints.add(it) }

    context(Router) @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, I: Par, reified RETURN: Any>
            Endpoint9<A, B, C, D, E, F, G, H, I, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, H, I, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint9(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, e, f, g, h, i, handler)
            .also { endpoints.add(it) }

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