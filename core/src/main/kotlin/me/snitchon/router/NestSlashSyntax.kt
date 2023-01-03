package me.snitchon.router

import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.ParametrizedPath0
import me.snitchon.parameter.ParametrizedPath1
import me.snitchon.parameter.ParametrizedPath2
import me.snitchon.path.Path

object NestSlashSyntax {
    @JvmName("abc")
    operator fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1P, P1>,
            P : ParametrizedPath1<P1, P1P>
            > Router<W, P>.div(
        block: Router<W, P>.() -> Unit
    ): Router<W, P> {
        block(this)

        endpoints.addAll(this.endpoints)

        return this
    }

    @JvmName("abcde")
    operator fun <
            W : RequestWrapper,
            P1,
            P1P : Path<P1P, P1>,
            P2,
            P2P : Path<P2P, P2>,
            P : ParametrizedPath2<P1, P1P, P2, P2P>
            > Router<W, P>.div(
        block: Router<W, P>.() -> Unit
    ): Router<W, P> {
        block(this)

//        endpoints.addAll(this.endpoints)

        return this
    }

    @JvmName("ab")
    operator fun <
            W : RequestWrapper,
            P : ParametrizedPath0,
            > Router<W, P>.div(
        block: Router<W, P>.() -> Unit
    ): Router<W, P> {
        block(this)

//        endpoints.addAll(this.endpoints)

        return this
    }
}