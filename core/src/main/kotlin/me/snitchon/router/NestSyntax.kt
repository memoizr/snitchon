package me.snitchon.router

import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.*
import me.snitchon.path.Path

object NestSyntax {
    @JvmName("nest:0:string")
    infix fun <
            W : RequestWrapper> Router<W, ParametrizedPath0>.nest(path: String) =
        Router(config, ParametrizedPath0(prefix.path + PathElement.PathConstant(path)), endpoints)

    context(ParameterMarkupDecorator)
    @JvmName("nest:0:p")
    infix fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1>,
            > Router<W, ParametrizedPath0>.nest(path: P1P) =
        Router(config, ParametrizedPath1(prefix.path + PathElement.PathVariable(path), path), endpoints)

    context(ParameterMarkupDecorator)
    @JvmName("nest:1:p")
    infix fun <
            W : RequestWrapper,
            P1P,
            P1 : Path<P1P>,
            PP,
            P : Path<PP>,
            > Router<W, ParametrizedPath1<P1P, P1>>.nest(path: P) =
        Router(
            config,
            ParametrizedPath2(prefix.path + PathElement.PathVariable(path), prefix.p1, path),
            endpoints
        )

    @JvmName("nest:1:string")
    infix fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1>,
            P : ParametrizedPath1<P1, P1P>
            > Router<W, P>.nest(path: String) =
        Router(config, ParametrizedPath1(prefix.path + PathElement.PathConstant(path), prefix.p1), endpoints)

    @JvmName("nest:2:string")
    infix fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1>,
            P2,
            P2P : Path<P2>,
            P : ParametrizedPath2<P1, P1P, P2, P2P>
            > Router<W, P>.nest(path: String) =
        Router(config, ParametrizedPath2(prefix.path + PathElement.PathConstant(path), prefix.p1, prefix.p2), endpoints)
}