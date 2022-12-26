package me.snitchon.router

import me.snitchon.http.HttpResponse
import me.snitchon.config.Config
import me.snitchon.endpoint.*
import me.snitchon.http.Handler

context (HttpMethods)
data class Router(val config: Config = Config(), val prefix: String = "") {
    val endpoints = mutableListOf<Endpoint<*>>()

    fun <T : Any> addEndpoint(endpoint: Endpoint<T>) {
        endpoints.add(endpoint)
    }

    inline fun <reified T : Any> body() = Body(T::class)

    val EndpointParameters.prefixed
        get() =
            copy(url = prefix + if (url.isEmpty()) "" else url.ensureLeadingSlash())

    fun <X : Endpoint<RETURN>, Y : Endpoint<RETURN>, RETURN> X.headers(block: context(X, OnlyHeader, ParameterAddition) () -> Y): Y =
        block(this@X, OnlyHeader, ParameterAddition)

    fun <X : Endpoint<RETURN>, Y : Endpoint<RETURN>, RETURN> X.queries(block: context(X, OnlyQuery, ParameterAddition) () -> Y): Y =
        block(this@X, OnlyQuery, ParameterAddition)

    inline fun <reified RETURN : Any>
            Endpoint0<Nothing>.isHandledBy(
        noinline handler: context (Handler) () -> HttpResponse<RETURN>
    ) = Endpoint0(
        params.prefixed,
        before,
        after,
        RETURN::class,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, reified RETURN : Any>
            Endpoint1<P1, Nothing>.isHandledBy(
        noinline handler: context(P1, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint1(
        params.prefixed,
        before,
        after,
        RETURN::class,
        p1,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, reified RETURN : Any>
            Endpoint2<P1, P2, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint2(
        params.prefixed,
        before,
        after,
        RETURN::class,
        p1,
        p2,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, reified RETURN : Any>
            Endpoint3<P1, P2, P3, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint3(
        params.prefixed,
        before,
        after,
        RETURN::class,
        p1,
        p2,
        p3,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, reified RETURN : Any>
            Endpoint4<P1, P2, P3, P4, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint4(
        params.prefixed,
        before,
        after,
        RETURN::class,
        p1,
        p2,
        p3,
        p4,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, reified RETURN : Any>
            Endpoint5<P1, P2, P3, P4, P5, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint5(
        params.prefixed,
        before,
        after,
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
            Endpoint6<P1, P2, P3, P4, P5, P6, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint6(
        params.prefixed,
        before,
        after,
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
            Endpoint7<P1, P2, P3, P4, P5, P6, P7, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint7(
        params.prefixed,
        before,
        after,
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
            Endpoint8<P1, P2, P3, P4, P5, P6, P7, P8, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint8(
        params.prefixed,
        before,
        after,
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
            Endpoint9<P1, P2, P3, P4, P5, P6, P7, P8, P9, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint9(
        params.prefixed,
        before,
        after,
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
            Endpoint10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint10(
        params.prefixed,
        before,
        after,
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
            Endpoint11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint11(
        params.prefixed,
        before,
        after,
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
            Endpoint12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint12(
        params.prefixed,
        before,
        after,
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
            Endpoint13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint13(
        params.prefixed,
        before,
        after,
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
            Endpoint14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint14(
        params.prefixed,
        before,
        after,
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
            Endpoint15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint15(
        params.prefixed,
        before,
        after,
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
            Endpoint16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint16(
        params.prefixed,
        before,
        after,
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
            Endpoint17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint17(
        params.prefixed,
        before,
        after,
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
            Endpoint18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint18(
        params.prefixed,
        before,
        after,
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
            Endpoint19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint19(
        params.prefixed,
        before,
        after,
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
            Endpoint20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint20(
        params.prefixed,
        before,
        after,
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
            Endpoint21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint21(
        params.prefixed,
        before,
        after,
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
            Endpoint22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint22(
        params.prefixed,
        before,
        after,
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
            Endpoint23<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint23(
        params.prefixed,
        before,
        after,
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
            Endpoint24<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint24(
        params.prefixed,
        before,
        after,
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
            Endpoint25<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint25(
        params.prefixed,
        before,
        after,
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
            Endpoint26<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint26(
        params.prefixed,
        before,
        after,
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

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, reified RETURN : Any>
            Endpoint27<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint27(
        params.prefixed,
        before,
        after,
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
        p27,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, reified RETURN : Any>
            Endpoint28<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint28(
        params.prefixed,
        before,
        after,
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
        p27,
        p28,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, reified RETURN : Any>
            Endpoint29<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint29(
        params.prefixed,
        before,
        after,
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
        p27,
        p28,
        p29,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, P30 : Par, reified RETURN : Any>
            Endpoint30<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint30(
        params.prefixed,
        before,
        after,
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
        p27,
        p28,
        p29,
        p30,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, P30 : Par, P31 : Par, reified RETURN : Any>
            Endpoint31<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint31(
        params.prefixed,
        before,
        after,
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
        p27,
        p28,
        p29,
        p30,
        p31,
        handler
    ).also(::addEndpoint)

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    inline fun <P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, P30 : Par, P31 : Par, P32 : Par, reified RETURN : Any>
            Endpoint32<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, Nothing>.isHandledBy(
        noinline handler: context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, Handler) () -> HttpResponse<RETURN>
    ) = Endpoint32(
        params.prefixed,
        before,
        after,
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
        p27,
        p28,
        p29,
        p30,
        p31,
        p32,
        handler
    ).also(::addEndpoint)

}