package me.snitchon.router

import me.snitchon.parameter.*

object SlashSyntax {
    context(Router, Markup, HttpMethods)
    operator fun String.div(block: context(ParametrizedPath0) Router.() -> Unit): Router {
        val router = Router(config, prefix + this.ensureLeadingSlash())
        block(ParametrizedPath0(prefix + this.ensureLeadingSlash()), router)

        endpoints.addAll(router.endpoints)

        return router
    }

    context(Router, ParametrizedPath0, Markup, HttpMethods)
    operator fun <T : Path<T, *>> T.div(block: context(ParametrizedPath1<T>, T) Router.() -> Unit): Router {
        val router = Router(config, prefix + this.markupName.ensureLeadingSlash())
        block(ParametrizedPath1(prefix + this.markupName.ensureLeadingSlash(), this), this, router)

        endpoints.addAll(router.endpoints)

        return router
    }

    operator fun String.div(path: String): String {
        return this.ensureLeadingSlash() + "/" + path
    }

    context(Markup)
    operator fun <P : PP<P>> String.div(path: P): ParametrizedPath1<P> {
        return ParametrizedPath1(this + path.markupName.ensureLeadingSlash(), path)
    }

    operator fun <P1 : PP<P1>> ParametrizedPath1<P1>.div(path: String): ParametrizedPath1<P1> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }

    operator fun <
            P1 : PP<P1>,
            P2 : PP<P2>,
            > ParametrizedPath2<P1, P2>.div(path: String): ParametrizedPath2<P1, P2> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }
}