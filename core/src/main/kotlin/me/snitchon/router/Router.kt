package me.snitchon.router

import com.snitch.HttpResponse
import me.snitchon.config.Config
import me.snitchon.endpoint.*
import me.snitchon.http.Handler

context (HttpMethods)
data class Router(val config: Config = Config(), val prefix: String = "") {
    val endpoints = mutableListOf<Endpoint<*>>()

    fun <T : Any> addEndpoint(endpoint: Endpoint<T>) {
        endpoints.add(endpoint)
    }

    inline fun <reified T : Any> body() = Body(T::class)

    val EndpointParameters.prefixed
        get() =
            copy(url = prefix + if (url.isEmpty()) "" else url.ensureLeadingSlash())

    inline fun <reified RETURN : Any>
            Endpoint0<Nothing>.isHandledBy(
        noinline handler: context (Handler) () -> HttpResponse<RETURN>
    ) = Endpoint0(
        params.prefixed,
        before,
        after,
        RETURN::class,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, reified RETURN : Any>
            Endpoint1<A, Nothing>.isHandledBy(
        noinline handler: context(A, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint1(
        params.prefixed,
        before,
        after,
        RETURN::class,
        a,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, reified RETURN : Any>
            Endpoint2<A, B, Nothing>.isHandledBy(
        noinline handler: context(A, B, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint2(
        params.prefixed,
        before,
        after,
        RETURN::class,
        a,
        b,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, reified RETURN : Any>
            Endpoint3<A, B, C, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint3(
        params.prefixed,
        before,
        after,
        RETURN::class,
        a,
        b,
        c,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, reified RETURN : Any>
            Endpoint4<A, B, C, D, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint4(
        params.prefixed,
        before,
        after,
        RETURN::class,
        a,
        b,
        c,
        d,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, reified RETURN : Any>
            Endpoint5<A, B, C, D, E, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint5(
        params.prefixed,
        before,
        after,
        RETURN::class,
        a,
        b,
        c,
        d,
        e,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par,
            reified RETURN : Any> Endpoint6<A, B, C, D, E, F, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint6(
        params.prefixed,
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
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, reified RETURN : Any>
            Endpoint7<A, B, C, D, E, F, G, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint7(
        params.prefixed,
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
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, reified RETURN : Any>
            Endpoint8<A, B, C, D, E, F, G, H, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, H, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint8(
        params.prefixed,
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
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, I : Par, reified RETURN : Any>
            Endpoint9<A, B, C, D, E, F, G, H, I, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, H, I, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint9(
        params.prefixed,
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
    ).also(::addEndpoint)
}