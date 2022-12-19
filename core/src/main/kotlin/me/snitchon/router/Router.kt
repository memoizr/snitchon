package me.snitchon.router

import com.snitch.HttpResponse
import me.snitchon.config.Config
import me.snitchon.endpoint.*
import me.snitchon.http.Handler
import me.snitchon.parameter.Markup
import me.snitchon.parameter.ParametrizedPath0
import me.snitchon.parameter.ParametrizedPath1
import me.snitchon.parameter.Path

context (RouterContext)
data class Router(val config: Config = Config(), val prefix: String = "") {
    val endpoints = mutableListOf<Endpoint<*>>()

    fun <T : Any> addEndpoint(endpoint: Endpoint<T>) {
        endpoints.add(endpoint)
    }

    inline fun <reified T : Any> body() = Body(T::class)

    context(Markup)
    operator fun String.div(block: context(ParametrizedPath0) Router.() -> Unit): Router {
        val router = Router(config, prefix + this.ensureLeadingSlash())
        block(ParametrizedPath0(prefix + this.ensureLeadingSlash()), router)

        endpoints.addAll(router.endpoints)

        return router
    }

    context(ParametrizedPath0, Markup)
    operator fun <T : Path<T, *>> T.div(block: context(ParametrizedPath1<T>, T) Router.() -> Unit): Router {
        val router = Router(config, prefix + this.markupName.ensureLeadingSlash())
        block(ParametrizedPath1(prefix + this.markupName.ensureLeadingSlash(), this), this, router)

        endpoints.addAll(router.endpoints)

        return router
    }

    fun prefixed(url: String) = prefix + if (url.isEmpty()) "" else url.ensureLeadingSlash()

    inline fun <reified RETURN : Any> Endpoint0<Nothing>.isHandledBy(
        noinline handler: context (Handler) () -> HttpResponse<RETURN>

    ) = Endpoint0(
        httpMethod,
        prefixed(url),
        summary,
        description,
        visibility,
        before,
        after,
        RETURN::class,
        handler
    ).also { addEndpoint(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, reified RETURN : Any> Endpoint1<A, Nothing>.isHandledBy(
        noinline handler: context(A, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint1(
        httpMethod,
        prefixed(url),
        summary,
        description,
        visibility,
        before,
        after,
        RETURN::class,
        a,
        handler
    ).also { addEndpoint(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, reified RETURN : Any> Endpoint2<A, B, Nothing>.isHandledBy(
        noinline handler: context(A, B, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint2(
        httpMethod, prefixed(url), summary, description, visibility, before, after, RETURN::class, a, b, handler
    ).also { addEndpoint(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, reified RETURN : Any> Endpoint3<A, B, C, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint3(
        httpMethod, prefixed(url), summary, description, visibility, before, after, RETURN::class, a, b, c, handler
    ).also { addEndpoint(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, reified RETURN : Any> Endpoint4<A, B, C, D, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint4(
        httpMethod, prefixed(url), summary, description, visibility, before, after, RETURN::class, a, b, c, d, handler
    ).also { addEndpoint(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, reified RETURN : Any> Endpoint5<A, B, C, D, E, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint5(
        httpMethod,
        prefixed(url),
        summary,
        description,
        visibility,
        before,
        after,
        RETURN::class,
        a,
        b,
        c,
        d,
        e,
        handler
    ).also { addEndpoint(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, reified RETURN : Any> Endpoint6<A, B, C, D, E, F, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint6(
        httpMethod,
        prefixed(url),
        summary,
        description,
        visibility,
        before,
        after,
        RETURN::class,
        a,
        b,
        c,
        d,
        e,
        f,
        handler
    ).also { addEndpoint(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, reified RETURN : Any> Endpoint7<A, B, C, D, E, F, G, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint7(
        httpMethod,
        prefixed(url),
        summary,
        description,
        visibility,
        before,
        after,
        RETURN::class,
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        handler
    ).also { addEndpoint(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, reified RETURN : Any> Endpoint8<A, B, C, D, E, F, G, H, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, H, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint8(
        httpMethod,
        prefixed(url),
        summary,
        description,
        visibility,
        before,
        after,
        RETURN::class,
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h,
        handler
    ).also { addEndpoint(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, I : Par, reified RETURN : Any> Endpoint9<A, B, C, D, E, F, G, H, I, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, H, I, Handler)
            () -> HttpResponse<RETURN>
    ) = Endpoint9(
        httpMethod,
        prefixed(url),
        summary,
        description,
        visibility,
        before,
        after,
        RETURN::class,
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h,
        i,
        handler
    ).also { addEndpoint(it) }
}