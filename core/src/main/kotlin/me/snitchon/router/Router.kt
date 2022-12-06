package me.snitchon.router

import com.snitch.HttpResponse
import me.snitchon.config.Config
import me.snitchon.endpoint.*
import me.snitchon.http.Handler

context (RouterContext)
class Router(val config: Config = Config()) {
    val endpoints = mutableListOf<Endpoint<*>>()

    inline fun <reified T : Any> body() = Body(T::class)

    inline fun <reified RETURN : Any> Endpoint0<Nothing>.isHandledBy(
        noinline handler: context (Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint0(httpMethod, url, summary, description, visibility, before, after, RETURN::class, handler)
            .also { endpoints.add(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, reified RETURN: Any>
            Endpoint1<A, Nothing>.isHandledBy(
        noinline handler: context(A, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint1(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, handler)
            .also { endpoints.add(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par,
            B : Par, reified RETURN: Any>
            Endpoint2<A, B, Nothing>.isHandledBy(
        noinline handler: context(A, B, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint2(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, handler)
            .also { endpoints.add(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, reified RETURN: Any>
            Endpoint3<A, B, C, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint3(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, handler)
            .also(endpoints::add)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, reified RETURN: Any>
            Endpoint4<A, B, C, D, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint4(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, handler)
            .also { endpoints.add(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, reified RETURN: Any>
            Endpoint5<A, B, C, D, E, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint5(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, e, handler)
            .also { endpoints.add(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, reified RETURN: Any>
            Endpoint6<A, B, C, D, E, F, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F,
        Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint6(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, e, f, handler)
            .also { endpoints.add(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, reified RETURN: Any>
            Endpoint7<A, B, C, D, E, F, G, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint7(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, e, f, g, handler)
            .also { endpoints.add(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, reified RETURN: Any>
            Endpoint8<A, B, C, D, E, F, G, H, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, H, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint8(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, e, f, g, h, handler)
            .also { endpoints.add(it) }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, I: Par, reified RETURN: Any>
            Endpoint9<A, B, C, D, E, F, G, H, I, Nothing>.isHandledBy(
        noinline handler: context(A, B, C, D, E, F, G, H, I, Handler) () -> HttpResponse<RETURN>
    ) =
        Endpoint9(httpMethod, url, summary, description, visibility, before, after, RETURN::class, a, b, c, d, e, f, g, h, i, handler)
            .also { endpoints.add(it) }
}