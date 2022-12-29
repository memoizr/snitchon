package me.snitchon.router

import me.snitchon.http.HttpResponse
import me.snitchon.config.Config
import me.snitchon.endpoint.*
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper

context(HttpMethods<W>)
data class Router<W: RequestWrapper>(val config: Config = Config(), val prefix: String = "") {
    val endpoints = mutableListOf<Endpoint<W,*>>()

    fun <T : Any> addEndpoint(endpoint: Endpoint<W, T>) {
        endpoints.add(endpoint)
    }

    inline fun <reified T : Any> body() = Body(T::class)

    val EndpointParameters.prefixed
        get() =
            copy(url = prefix + if (url.isEmpty()) "" else url.ensureLeadingSlash())

    fun <X : Endpoint<W, RETURN>, Y : Endpoint<W, RETURN>, RETURN> X.headers(block: context(X, OnlyHeader, ParameterAddition) () -> Y): Y =
        block(this@X, OnlyHeader, ParameterAddition)

    fun <X : Endpoint<W, RETURN>, Y : Endpoint<W, RETURN>, RETURN> X.queries(block: context(X, OnlyQuery, ParameterAddition) () -> Y): Y =
        block(this@X, OnlyQuery, ParameterAddition)

    inline fun <reified RETURN : Any>
            Endpoint0<W, Nothing>.isHandledBy(
        noinline handler: context (Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint0(
        params.prefixed,
        RETURN::class,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, reified RETURN : Any>
            Endpoint1<P1, W, Nothing>.isHandledBy(
        noinline handler: context(P1, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint1(
        params.prefixed,
        RETURN::class,
        p1,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, reified RETURN : Any>
            Endpoint2<P1, P2, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint2(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, reified RETURN : Any>
            Endpoint3<P1, P2, P3, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint3(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, reified RETURN : Any>
            Endpoint4<P1, P2, P3, P4, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint4(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, reified RETURN : Any>
            Endpoint5<P1, P2, P3, P4, P5, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint5(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, reified RETURN : Any>
            Endpoint6<P1, P2, P3, P4, P5, P6, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint6(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, reified RETURN : Any>
            Endpoint7<P1, P2, P3, P4, P5, P6, P7, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint7(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, reified RETURN : Any>
            Endpoint8<P1, P2, P3, P4, P5, P6, P7, P8, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint8(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, reified RETURN : Any>
            Endpoint9<P1, P2, P3, P4, P5, P6, P7, P8, P9, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint9(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, reified RETURN : Any>
            Endpoint10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint10(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, reified RETURN : Any>
            Endpoint11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint11(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, reified RETURN : Any>
            Endpoint12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint12(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, reified RETURN : Any>
            Endpoint13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint13(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, reified RETURN : Any>
            Endpoint14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint14(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, reified RETURN : Any>
            Endpoint15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint15(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, reified RETURN : Any>
            Endpoint16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint16(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, reified RETURN : Any>
            Endpoint17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint17(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        p17,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, reified RETURN : Any>
            Endpoint18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint18(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        p17,
        p18,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, reified RETURN : Any>
            Endpoint19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint19(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        p17,
        p18,
        p19,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, reified RETURN : Any>
            Endpoint20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint20(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        p17,
        p18,
        p19,
        p20,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, reified RETURN : Any>
            Endpoint21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint21(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        p17,
        p18,
        p19,
        p20,
        p21,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, reified RETURN : Any>
            Endpoint22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint22(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        p17,
        p18,
        p19,
        p20,
        p21,
        p22,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, reified RETURN : Any>
            Endpoint23<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint23(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        p17,
        p18,
        p19,
        p20,
        p21,
        p22,
        p23,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, reified RETURN : Any>
            Endpoint24<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint24(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        p17,
        p18,
        p19,
        p20,
        p21,
        p22,
        p23,
        p24,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, reified RETURN : Any>
            Endpoint25<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint25(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        p17,
        p18,
        p19,
        p20,
        p21,
        p22,
        p23,
        p24,
        p25,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, reified RETURN : Any>
            Endpoint26<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, W, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, Handler<W>) () -> HttpResponse<RETURN>
    ) = Endpoint26(
        params.prefixed,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6,
        p7,
        p8,
        p9,
        p10,
        p11,
        p12,
        p13,
        p14,
        p15,
        p16,
        p17,
        p18,
        p19,
        p20,
        p21,
        p22,
        p23,
        p24,
        p25,
        p26,
        handler
    ).also(::addEndpoint)
}