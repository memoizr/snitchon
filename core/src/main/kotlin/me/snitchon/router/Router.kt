package me.snitchon.router

import me.snitchon.config.Config
import me.snitchon.documentation.Visibility
import me.snitchon.endpoint.*
import me.snitchon.http.*
import me.snitchon.parameter.*
import me.snitchon.path.Path

data class Router<W : RequestWrapper,
        P : ParametrizedPath<out Group, *>>(
    val config: Config = Config(),
    val prefix: P,
    val endpoints: MutableList<Endpoint<W, Group, Any?, *>> = mutableListOf()
) {


    fun addEndpoint(endpoint: Endpoint<*, *, *, *>) {
        endpoints.add(endpoint as Endpoint<W, Group, Any?, *>)
    }

    inline fun <reified T : Any> body() = Body(T::class)

    val EndpointMeta.prefixed
        get() =
            copy(url = prefix.path + if (url.isEmpty()) "" else url.ensureLeadingSlash())

    fun <G : Group, X : Endpoint<W, G, BODY, RETURN>, Y : Endpoint<W, G, BODY, RETURN>, BODY, RETURN> X.headers(block: context(X, OnlyHeader, ParameterAddition) () -> Y): Y =
        block(this@X, OnlyHeader, ParameterAddition)

    fun <G : Group, X : Endpoint<W, G, BODY, RETURN>, Y : Endpoint<W, G, BODY, RETURN>, BODY, RETURN> X.queries(block: context(X, OnlyQuery, ParameterAddition) () -> Y): Y =
        block(this@X, OnlyQuery, ParameterAddition)

    inline fun <reified RETURN : Any, G : Group, B : Any?>
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
