package me.snitchon.router

import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parameter.ParametrizedPath0
import me.snitchon.parameter.ParametrizedPath1
import me.snitchon.parameter.ParametrizedPath2
import me.snitchon.path.Path

object NestSyntax {
    @JvmName("nest:0:string")
    infix fun <
            W : RequestWrapper> Router<W, ParametrizedPath0>.nest(path: String) =
        Router(config, ParametrizedPath0(prefix.path + path.ensureLeadingSlash()), endpoints)

    context(ParameterMarkupDecorator)
    @JvmName("nest:0:p")
    infix fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1P, P1>,
            > Router<W, ParametrizedPath0>.nest(path: P1P) =
        Router(config, ParametrizedPath1(prefix.path + path.markupName.ensureLeadingSlash(), path), endpoints)

    context(ParameterMarkupDecorator)
    @JvmName("nest:1:p")
    infix fun <
            W : RequestWrapper,
            P1P,
            P1 : Path<P1, P1P>,
            PP,
            P : Path<P, PP>,
            > Router<W, ParametrizedPath1<P1P, P1>>.nest(path: P) =
        Router(
            config,
            ParametrizedPath2(prefix.path + path.markupName.ensureLeadingSlash(), prefix.p1, path),
            endpoints
        )

    @JvmName("nest:1:string")
    infix fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1P, P1>,
            P : ParametrizedPath1<P1, P1P>
            > Router<W, P>.nest(path: String) =
        Router(config, ParametrizedPath1(prefix.path + path.ensureLeadingSlash(), prefix.p1), endpoints)

    @JvmName("nest:2:string")
    infix fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1P, P1>,
            P2,
            P2P : Path<P2P, P2>,
            P : ParametrizedPath2<P1, P1P, P2, P2P>
            > Router<W, P>.nest(path: String) =
        Router(config, ParametrizedPath2(prefix.path + path.ensureLeadingSlash(), prefix.p1, prefix.p2), endpoints)
}