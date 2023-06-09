package me.snitchon.router

import me.snitchon.documentation.Visibility
import me.snitchon.endpoint.*
import me.snitchon.http.*
import me.snitchon.parameter.*
import me.snitchon.path.Path

internal typealias Param<T> = Parameter<*, T>

object GetHttpMethods {
    fun <W : RequestWrapper,
            G : Group,
            P : ParametrizedPath<G, *>
            > Router<W, P>.GET(path: String = "") =
        getEndpoint<_, W>(prefix.group, listOf(PathElement.PathConstant(path)))

    @JvmName("get:0:0")
    fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1>,
            > Router<W, ParametrizedPath0>.GET(p: P1P) =
        getEndpoint<_, W>(prefix.group.with(p), listOf(PathElement.PathVariable(p)))

    @JvmName("get:0:1")
    fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1>,
            P : ParametrizedPath1<P1, P1P>
            > Router<W, ParametrizedPath0>.GET(p: P) =
        getEndpoint<_, W>(prefix.group.with(p.group.p1), p.path)

    @JvmName("get:0:2")
    fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1>,
            P2,
            P2P : Path<P2>,
            P : ParametrizedPath2<P1, P1P, P2, P2P>
            > Router<W, ParametrizedPath0>.GET(p: P) =
        getEndpoint<_, W>(prefix.group.with(p.group.p1).with(p.group.p2), p.path)

    @JvmName("get:0:3")
    fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1>,
            P2,
            P2P : Path<P2>,
            P3,
            P3P : Path<P3>,
            P : ParametrizedPath3<P1, P1P, P2, P2P, P3, P3P>
            > Router<W, ParametrizedPath0>.GET(p: P) =
        getEndpoint<_, W>(prefix.group.with(p.group.p1).with(p.group.p2).with(p.group.p3), p.path)

    @JvmName("get:1:1")
    fun <
            W : RequestWrapper,
            P1P,
            P1 : Path<P1P>,
            PP,
            Ph : Path<PP>,
            t : ParametrizedPath1<P1P, P1>,
            P : ParametrizedPath1<PP, Ph>> Router<W, t>.GET(p: P) =
        getEndpoint<_, W>(prefix.group.with(p.group.p1), p.path)

    @JvmName("get:2:1")
    fun <
            W : RequestWrapper,
            P1P,
            P1 : Path<P1P>,
            P2P,
            P2 : Path<P2P>,
            PP,
            Ph : Path<PP>,
            t : ParametrizedPath2<P1P, P1, P2P, P2>,
            P : ParametrizedPath1<PP, Ph>> Router<W, t>.GET(p: P) =
        getEndpoint<_, W>(prefix.group.with(p.group.p1), p.path)

    @JvmName("get:1:2")
    fun <
            W : RequestWrapper,
            P1P,
            P1 : Path<P1P>,
            PP,
            Ph : Path<PP>,
            PP2,
            Ph2 : Path<PP2>,
            t : ParametrizedPath1<P1P, P1>,
            P : ParametrizedPath2<PP, Ph, PP2, Ph2>> Router<W, t>.GET(p: P) =
        getEndpoint<_, W>(prefix.group.with(p.group.p1).with(p.group.p2), p.path)

    fun <G : Group, W : RequestWrapper> getEndpoint(group: G, path: List<PathElement>) = Endpoint<W, _, _, _>(
        EndpointMeta(
            HTTPMethod.GET,
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

fun String.ensureLeadingSlash() = if (!startsWith("/")) "/$this" else this


