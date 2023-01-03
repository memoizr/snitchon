package me.snitchon.router

import me.snitchon.documentation.Visibility
import me.snitchon.endpoint.*
import me.snitchon.http.*
import me.snitchon.parameter.*
import me.snitchon.path.Path

object PutHttpMethods {
    fun <W : RequestWrapper,
            G : Group,
            P : ParametrizedPath<G, *>
            > Router<W, P>.PUT(path: String = "") =
        putEndpoint<_, W>(prefix.group, path)

    context(ParameterMarkupDecorator)
    @JvmName("z")
    fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1>,
            P : ParametrizedPath1<P1, P1P>
            > Router<W, ParametrizedPath0>.PUT(p: P) =
        putEndpoint<_, W>(prefix.group.with(p.group.p1), p.path)

    context(ParameterMarkupDecorator)
    @JvmName("put:1:1")
    fun <
            W : RequestWrapper,
            P1P,
            P1 : Path<P1P>,
            PP,
            Ph : Path<PP>,
            t : ParametrizedPath1<P1P, P1>,
            P : ParametrizedPath1<PP, Ph>> Router<W, t>.PUT(p: P) =
        putEndpoint<_, W>(prefix.group.with(p.group.p1), p.path)

    context(ParameterMarkupDecorator)
    @JvmName("put:2:1")
    fun <
            W : RequestWrapper,
            P1P,
            P1 : Path<P1P>,
            P2P,
            P2 : Path<P2P>,
            PP,
            Ph : Path<PP>,
            t : ParametrizedPath2<P1P, P1, P2P, P2>,
            P : ParametrizedPath1<PP, Ph>> Router<W, t>.PUT(p: P) =
        putEndpoint<_, W>(prefix.group.with(p.group.p1), p.path)

    context(ParameterMarkupDecorator)
    @JvmName("put:1:2")
    fun <
            W : RequestWrapper,
            P1P,
            P1 : Path<P1P>,
            PP,
            Ph : Path<PP>,
            PP2,
            Ph2 : Path<PP2>,
            t : ParametrizedPath1<P1P, P1>,
            P : ParametrizedPath2<PP, Ph, PP2, Ph2>> Router<W, t>.PUT(p: P) =
        putEndpoint<_, W>(prefix.group.with(p.group.p1).with(p.group.p2), p.path)

    fun <G : Group, W : RequestWrapper> putEndpoint(group: G, path: String) = Endpoint<W, _, _, _>(
        EndpointMeta(
            HTTPMethod.PUT,
            path,
            "",
            description = null,
            visibility = Visibility.PUBLIC,
        ),
        null,
        group,
        body = BodyMarker(Nothing::class.java),
        response = Nothing::class
    )
}


