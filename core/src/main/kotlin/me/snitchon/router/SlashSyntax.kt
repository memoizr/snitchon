package me.snitchon.router

import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parameter.*
import me.snitchon.path.Path

class SlashSyntax<W : RequestWrapper> {

    context(Router<W, ParametrizedPath0>, ParameterMarkupDecorator, GetHttpMethods)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    operator fun String.div(block: Router<W, ParametrizedPath0>.() -> Unit): Router<W, ParametrizedPath0> {
        val prefix1 = prefix / this.ensureLeadingSlash()
        val router = Router<W, ParametrizedPath0>(config, prefix1)
        block(router)

        endpoints.addAll(router.endpoints)

        return router
    }

    context(Router<W, ParametrizedPath0>, ParameterMarkupDecorator)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    operator fun <PP, P : Path<PP>> P.div(block: Router<W, ParametrizedPath1<PP, P>>.() -> Unit): Router<W, ParametrizedPath1<PP, P>> {
        val router = Router<W, _>(config, prefix / this)
        block(router)

        endpoints.addAll(router.endpoints)

        return router
    }

    context(Router<W, ParametrizedPath1<P1P, P1>>, ParameterMarkupDecorator, GetHttpMethods)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    operator fun <
            P1P,
            P1: Path<P1P>,
            PP, P : Path<PP>> P.div(block: Router<W, ParametrizedPath2<P1P, P1, PP, P>>.() -> Unit): Router<W, ParametrizedPath2<P1P, P1, PP, P>> {
        val router = Router<W, _>(config, prefix / this)
        block(router)

        endpoints.addAll(router.endpoints)

        return router
    }

    operator fun String.div(path: String): String {
        return this.ensureLeadingSlash() + path.ensureLeadingSlash()
    }

    context(ParameterMarkupDecorator)
    operator fun <P1P, P1 : Path<P1P>> String.div(path: P1)
            : ParametrizedPath1<P1P, P1> {
        return ParametrizedPath1(this + path.markupName.ensureLeadingSlash(), path)
    }

    operator fun ParametrizedPath0.div(path: String): ParametrizedPath0 {
        return ParametrizedPath0(this.path + path.ensureLeadingSlash())
    }

    context(ParameterMarkupDecorator)
    operator fun <
            P1P, P1 : Path<P1P>,
            PP, P : Path<PP>
            > ParametrizedPath1<P1P,P1>.div(path: P)
            : ParametrizedPath2<P1P, P1, PP, P> {
        return ParametrizedPath2(this.path + path.markupName.ensureLeadingSlash(), p1, path)
    }

    context(ParameterMarkupDecorator)
    operator fun <
            P1P, P1 : Path<P1P>,
            > ParametrizedPath1<P1P,P1>.div(path: String)
            : ParametrizedPath1<P1P, P1> {
        return ParametrizedPath1(this.path + path.ensureLeadingSlash(), p1)
    }
}