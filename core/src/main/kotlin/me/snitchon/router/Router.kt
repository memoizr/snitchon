package me.snitchon.router

import me.snitchon.config.Config
import me.snitchon.endpoint.*
import me.snitchon.http.*

context(HttpMethods<W>)
data class Router<W : RequestWrapper>(val config: Config = Config(), val prefix: String = "") {
    val endpoints = mutableListOf<Endpoint<W, Group, Any?, *>>()

    fun <B: Any?, T : Any> addEndpoint(endpoint: Endpoint<W, Group, B, T>) {
        endpoints.add(endpoint as Endpoint<W, Group, Any?, *>)
    }

    inline fun <reified T : Any> body() = Body(T::class)

    val EndpointMeta.prefixed
        get() =
            copy(url = prefix + if (url.isEmpty()) "" else url.ensureLeadingSlash())

    fun <G: Group, X : Endpoint<W, G, BODY, RETURN>, Y : Endpoint<W, G, BODY, RETURN>, BODY, RETURN> X.headers(block: context(X, OnlyHeader, ParameterAddition) () -> Y): Y =
        block(this@X, OnlyHeader, ParameterAddition)

    fun <G: Group, X : Endpoint<W, G, BODY, RETURN>, Y : Endpoint<W, G, BODY, RETURN>, BODY, RETURN> X.queries(block: context(X, OnlyQuery, ParameterAddition) () -> Y): Y =
        block(this@X, OnlyQuery, ParameterAddition)

    inline fun <reified RETURN : Any, G : Group, B: Any?>
            Endpoint<W, G, B, Nothing>.isHandledBy(
        noinline handler: context (G, BodyMarker<B>, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint(
        meta.prefixed,
        handler,
        group,
        body,
        RETURN::class,
    ).also { addEndpoint(it as Endpoint<W, Group, *, *>) }

}
