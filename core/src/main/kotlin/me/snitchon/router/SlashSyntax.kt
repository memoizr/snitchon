package me.snitchon.router

import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parameter.*
import me.snitchon.path.Path

class SlashSyntax<W: RequestWrapper> {

    context(Router<W>, ParameterMarkupDecorator, HttpMethods<W>)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    operator fun String.div(block: context(ParametrizedPath0) Router<W>.() -> Unit): Router<W> {
        val router = Router(config, prefix + this.ensureLeadingSlash())
        block(ParametrizedPath0(prefix + this.ensureLeadingSlash()), router)

        endpoints.addAll(router.endpoints)

        return router
    }

    context(Router<W>, ParametrizedPath0, ParameterMarkupDecorator, HttpMethods<W>)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    operator fun <T : Path<T, *>> T.div(block: context(ParametrizedPath1<T>, T) Router<W>.() -> Unit): Router<W> {
        val router = Router(config, prefix + this.markupName.ensureLeadingSlash())
        block(ParametrizedPath1(prefix + this.markupName.ensureLeadingSlash(), this), this, router)

        endpoints.addAll(router.endpoints)

        return router
    }

    operator fun String.div(path: String): String {
        return this.ensureLeadingSlash() + "/" + path
    }

    context(ParameterMarkupDecorator)
    operator fun <P : PP<P>> String.div(path: P): ParametrizedPath1<P> {
        return ParametrizedPath1(this + path.markupName.ensureLeadingSlash(), path)
    }

    operator fun <P1 : PP<P1>> ParametrizedPath1<P1>.div(path: String): ParametrizedPath1<P1> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }

    operator fun <P1 : PP<P1>, P2 : PP<P2>> ParametrizedPath2<P1, P2>.div(path: String): ParametrizedPath2<P1, P2> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }

    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>> ParametrizedPath3<P1, P2, P3>.div(path: String): ParametrizedPath3<P1, P2, P3> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>> ParametrizedPath4<P1, P2, P3, P4>.div(path: String): ParametrizedPath4<P1, P2, P3, P4> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>> ParametrizedPath5<P1, P2, P3, P4, P5>.div(path: String): ParametrizedPath5<P1, P2, P3, P4, P5> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>> ParametrizedPath6<P1, P2, P3, P4, P5, P6>.div(path: String): ParametrizedPath6<P1, P2, P3, P4, P5, P6> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>> ParametrizedPath7<P1, P2, P3, P4, P5, P6, P7>.div(path: String): ParametrizedPath7<P1, P2, P3, P4, P5, P6, P7> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>> ParametrizedPath8<P1, P2, P3, P4, P5, P6, P7, P8>.div(path: String): ParametrizedPath8<P1, P2, P3, P4, P5, P6, P7, P8> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>> ParametrizedPath9<P1, P2, P3, P4, P5, P6, P7, P8, P9>.div(path: String): ParametrizedPath9<P1, P2, P3, P4, P5, P6, P7, P8, P9> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>> ParametrizedPath10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>.div(path: String): ParametrizedPath10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>> ParametrizedPath11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11>.div(path: String): ParametrizedPath11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>> ParametrizedPath12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>.div(path: String): ParametrizedPath12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>> ParametrizedPath13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>.div(path: String): ParametrizedPath13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>> ParametrizedPath14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14>.div(path: String): ParametrizedPath14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>> ParametrizedPath15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15>.div(path: String): ParametrizedPath15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>> ParametrizedPath16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16>.div(path: String): ParametrizedPath16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>> ParametrizedPath17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17>.div(path: String): ParametrizedPath17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>> ParametrizedPath18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18>.div(path: String): ParametrizedPath18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>> ParametrizedPath19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19>.div(path: String): ParametrizedPath19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>> ParametrizedPath20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20>.div(path: String): ParametrizedPath20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>> ParametrizedPath21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21>.div(path: String): ParametrizedPath21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }


    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>, P22 : PP<P22>> ParametrizedPath22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22>.div(path: String): ParametrizedPath22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22> {
        return this.copy(this.path + path.ensureLeadingSlash())
    }




















}