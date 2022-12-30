package me.snitchon.router

import me.snitchon.documentation.Visibility
import me.snitchon.endpoint.*
import me.snitchon.http.*
import me.snitchon.parameter.*
import me.snitchon.path.Path

internal typealias PP<T> = Path<T, *>
internal typealias Par = Parameter<*, *>
internal typealias Param<T> = Parameter<*, T>

class HttpMethods<W : RequestWrapper> {
    fun GET(path: String = "") = Endpoint<W, _, _, _>(
        EndpointMeta(
            HTTPMethod.GET,
            if (path.isEmpty()) "" else path.ensureLeadingSlash(),
            "",
            description = null,
            visibility = Visibility.PUBLIC,
        ),
        null,
        Group0,
        body = BodyMarker(Nothing::class.java),
        response = Nothing::class
    )

    fun PUT(path: String) = Endpoint<W, _, _, _>(
        EndpointMeta(
            HTTPMethod.PUT,
            path.ensureLeadingSlash(),
            "",
            description = null,
            visibility = Visibility.PUBLIC,
        ),
        null,
        Group0,
        body = BodyMarker(Nothing::class.java),
        response = Nothing::class
    )

    fun POST(path: String) = Endpoint<W, Group0, _, _>(
        EndpointMeta(
            HTTPMethod.POST,
            path.ensureLeadingSlash(),
            "",
            description = null,
            visibility = Visibility.PUBLIC,
        ),
        null,
        Group0,
        body = BodyMarker(Nothing::class.java),
        response = Nothing::class
    )

    fun DELETE(path: String) = Endpoint<W, Group0, _, _>(
        EndpointMeta(
            HTTPMethod.DELETE,
            path.ensureLeadingSlash(),
            "",
            description = null,
            visibility = Visibility.PUBLIC,
        ),
        null,
        Group0,
        body = BodyMarker(Nothing::class.java),
        response = Nothing::class
    )

    context(ParameterMarkupDecorator)
    fun <P1P, P1 : Path<P1, P1P>>
            GET(path: P1) =
        Endpoint<W, Group1<P1P, P1>, _, _>(
            EndpointMeta(
                HTTPMethod.GET,
                path.markupName,
                null,
                null,
                Visibility.PUBLIC,
            ),
            null,
            Group1(path),
            body = BodyMarker(Nothing::class.java),
            response = Nothing::class,
        )

    fun <P1P, P1 : Path<P1, P1P>>
            GET(path: ParametrizedPath1<P1>) =
        Endpoint<W, _, _, _>(
            EndpointMeta(
                HTTPMethod.GET,
                path.path,
                null,
                null,
                Visibility.PUBLIC,
            ),
            null,
            Group1(path.p1),
            body = BodyMarker(Nothing::class.java),
            response = Nothing::class,
        )

    fun <P1 : PP<P1>>
            PUT(path: ParametrizedPath1<P1>) =
        Endpoint<W,_, _, _>(
            EndpointMeta(
                HTTPMethod.PUT,
                path.path,
                null,
                null,
                Visibility.PUBLIC,
            ),
            null,
            Group1(path.p1),
            body = BodyMarker(Nothing::class.java),
            response = Nothing::class,
        )

    fun <A : PP<A>, B : PP<B>>
            GET(path: ParametrizedPath2<A, B>) =
        Endpoint<W,_, _, _>(
            EndpointMeta(
                HTTPMethod.GET, path.path,
                null,
                null,
                Visibility.PUBLIC,
            ),
            null,
            Group2(path.p1, path.p2),
            body = BodyMarker(Nothing::class.java),
            response = Nothing::class,
        )

//    fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>> GET(path: ParametrizedPath3<P1, P2, P3>) =
//        Endpoint3<_, _, _, W, _>(
//            EndpointMeta(HTTPMethod.GET, path.path, null, null, Visibility.PUBLIC),
//            Nothing::class,
//            path.p1,
//            path.p2,
//            path.p3,
//        )
//
//    fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>> GET(path: ParametrizedPath4<P1, P2, P3, P4>) =
//        Endpoint4<_,_,_,_,W,_>(
//            EndpointMeta(HTTPMethod.GET, path.path, null, null, Visibility.PUBLIC),
//            Nothing::class,
//            path.p1,
//            path.p2,
//            path.p3,
//            path.p4,
//        )
//
//    fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>> GET(path: ParametrizedPath5<P1, P2, P3, P4, P5>) =
//        Endpoint5<_,_,_,_,_,W,_>(
//            EndpointMeta(HTTPMethod.GET, path.path, null, null, Visibility.PUBLIC),
//            Nothing::class,
//            path.p1,
//            path.p2,
//            path.p3,
//            path.p4,
//            path.p5,
//        )
//
//    fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>> GET(path: ParametrizedPath6<P1, P2, P3, P4, P5, P6>) =
//        Endpoint6<_,_,_,_,_,_,W,_>(
//            EndpointMeta(HTTPMethod.GET, path.path, null, null, Visibility.PUBLIC),
//            Nothing::class,
//            path.p1,
//            path.p2,
//            path.p3,
//            path.p4,
//            path.p5,
//            path.p6,
//        )
//
//    fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>> GET(path: ParametrizedPath7<P1, P2, P3, P4, P5, P6, P7>) =
//        Endpoint7<_,_,_,_,_,_,_,W,_>(
//            EndpointMeta(HTTPMethod.GET, path.path, null, null, Visibility.PUBLIC),
//            Nothing::class,
//            path.p1,
//            path.p2,
//            path.p3,
//            path.p4,
//            path.p5,
//            path.p6,
//            path.p7,
//        )
//
//    fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>> GET(
//        path: ParametrizedPath8<P1, P2, P3, P4, P5, P6, P7, P8>
//    ) =
//        Endpoint8<_,_,_,_,_,_,_,_,W,_>(
//            EndpointMeta(HTTPMethod.GET, path.path, null, null, Visibility.PUBLIC),
//            Nothing::class,
//            path.p1,
//            path.p2,
//            path.p3,
//            path.p4,
//            path.p5,
//            path.p6,
//            path.p7,
//            path.p8,
//        )
//
//    fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>> GET(
//        path: ParametrizedPath9<P1, P2, P3, P4, P5, P6, P7, P8, P9>
//    ) =
//        Endpoint9<_,_,_,_,_,_,_,_,_,W,_>(
//            EndpointMeta(HTTPMethod.GET, path.path, null, null, Visibility.PUBLIC),
//            Nothing::class,
//            path.p1,
//            path.p2,
//            path.p3,
//            path.p4,
//            path.p5,
//            path.p6,
//            path.p7,
//            path.p8,
//            path.p9,
//        )
//
//    fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>> GET(
//        path: ParametrizedPath10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>
//    ) =
//        Endpoint10<_,_,_,_,_,_,_,_,_,_,W,_>(
//            EndpointMeta(HTTPMethod.GET, path.path, null, null, Visibility.PUBLIC),
//            Nothing::class,
//            path.p1,
//            path.p2,
//            path.p3,
//            path.p4,
//            path.p5,
//            path.p6,
//            path.p7,
//            path.p8,
//            path.p9,
//            path.p10,
//        )
//
//    fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>> GET(
//        path: ParametrizedPath11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11>
//    ) =
//        Endpoint11<_,_,_,_,_,_,_,_,_,_,_,W,_>(
//            EndpointMeta(HTTPMethod.GET, path.path, null, null, Visibility.PUBLIC),
//            Nothing::class,
//            path.p1,
//            path.p2,
//            path.p3,
//            path.p4,
//            path.p5,
//            path.p6,
//            path.p7,
//            path.p8,
//            path.p9,
//            path.p10,
//            path.p11,
//        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>> ParametrizedPath1<P1>.div(path: P2): ParametrizedPath2<P1, P2> {
        return ParametrizedPath2(this.path + path.markupName.ensureLeadingSlash(), this.p1, path)
    }

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>> ParametrizedPath2<P1, P2>.div(path: P3): ParametrizedPath3<P1, P2, P3> =
        ParametrizedPath3(this.path + path.markupName.ensureLeadingSlash(), p1, p2, path)

//    context(ParameterMarkupDecorator)
//    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>> ParametrizedPath3<P1, P2, P3>.div(path: P4): ParametrizedPath4<P1, P2, P3, P4> =
//        ParametrizedPath4(this.path + path.markupName.ensureLeadingSlash(), p1, p2, p3, path)

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>> ParametrizedPath4<P1, P2, P3, P4>.div(
        path: P5
    ): ParametrizedPath5<P1, P2, P3, P4, P5> =
        ParametrizedPath5(this.path + path.markupName.ensureLeadingSlash(), p1, p2, p3, p4, path)

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>> ParametrizedPath5<P1, P2, P3, P4, P5>.div(
        path: P6
    ): ParametrizedPath6<P1, P2, P3, P4, P5, P6> =
        ParametrizedPath6(this.path + path.markupName.ensureLeadingSlash(), p1, p2, p3, p4, p5, path)

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>> ParametrizedPath6<P1, P2, P3, P4, P5, P6>.div(
        path: P7
    ): ParametrizedPath7<P1, P2, P3, P4, P5, P6, P7> =
        ParametrizedPath7(this.path + path.markupName.ensureLeadingSlash(), p1, p2, p3, p4, p5, p6, path)

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>> ParametrizedPath7<P1, P2, P3, P4, P5, P6, P7>.div(
        path: P8
    ): ParametrizedPath8<P1, P2, P3, P4, P5, P6, P7, P8> =
        ParametrizedPath8(this.path + path.markupName.ensureLeadingSlash(), p1, p2, p3, p4, p5, p6, p7, path)

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>> ParametrizedPath8<P1, P2, P3, P4, P5, P6, P7, P8>.div(
        path: P9
    ): ParametrizedPath9<P1, P2, P3, P4, P5, P6, P7, P8, P9> =
        ParametrizedPath9(this.path + path.markupName.ensureLeadingSlash(), p1, p2, p3, p4, p5, p6, p7, p8, path)

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>> ParametrizedPath9<P1, P2, P3, P4, P5, P6, P7, P8, P9>.div(
        path: P10
    ): ParametrizedPath10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> =
        ParametrizedPath10(this.path + path.markupName.ensureLeadingSlash(), p1, p2, p3, p4, p5, p6, p7, p8, p9, path)

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>> ParametrizedPath10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>.div(
        path: P11
    ): ParametrizedPath11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> = ParametrizedPath11(
        this.path + path.markupName.ensureLeadingSlash(),
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
        path
    )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>> ParametrizedPath11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11>.div(
        path: P12
    ): ParametrizedPath12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> = ParametrizedPath12(
        this.path + path.markupName.ensureLeadingSlash(),
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
        path
    )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>> ParametrizedPath12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>.div(
        path: P13
    ): ParametrizedPath13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> = ParametrizedPath13(
        this.path + path.markupName.ensureLeadingSlash(),
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
        path
    )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>> ParametrizedPath13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>.div(
        path: P14
    ): ParametrizedPath14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> = ParametrizedPath14(
        this.path + path.markupName.ensureLeadingSlash(),
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
        path
    )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>> ParametrizedPath14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14>.div(
        path: P15
    ): ParametrizedPath15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> = ParametrizedPath15(
        this.path + path.markupName.ensureLeadingSlash(),
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
        path
    )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>> ParametrizedPath15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15>.div(
        path: P16
    ): ParametrizedPath16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> = ParametrizedPath16(
        this.path + path.markupName.ensureLeadingSlash(),
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
        path
    )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>> ParametrizedPath16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16>.div(
        path: P17
    ): ParametrizedPath17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17> =
        ParametrizedPath17(
            this.path + path.markupName.ensureLeadingSlash(),
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
            path
        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>> ParametrizedPath17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17>.div(
        path: P18
    ): ParametrizedPath18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18> =
        ParametrizedPath18(
            this.path + path.markupName.ensureLeadingSlash(),
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
            path
        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>> ParametrizedPath18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18>.div(
        path: P19
    ): ParametrizedPath19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19> =
        ParametrizedPath19(
            this.path + path.markupName.ensureLeadingSlash(),
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
            path
        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>> ParametrizedPath19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19>.div(
        path: P20
    ): ParametrizedPath20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20> =
        ParametrizedPath20(
            this.path + path.markupName.ensureLeadingSlash(),
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
            path
        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>> ParametrizedPath20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20>.div(
        path: P21
    ): ParametrizedPath21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21> =
        ParametrizedPath21(
            this.path + path.markupName.ensureLeadingSlash(),
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
            path
        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>, P22 : PP<P22>> ParametrizedPath21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21>.div(
        path: P22
    ): ParametrizedPath22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22> =
        ParametrizedPath22(
            this.path + path.markupName.ensureLeadingSlash(),
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
            path
        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>, P22 : PP<P22>, P23 : PP<P23>> ParametrizedPath22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22>.div(
        path: P23
    ): ParametrizedPath23<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23> =
        ParametrizedPath23(
            this.path + path.markupName.ensureLeadingSlash(),
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
            path
        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>, P22 : PP<P22>, P23 : PP<P23>, P24 : PP<P24>> ParametrizedPath23<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23>.div(
        path: P24
    ): ParametrizedPath24<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24> =
        ParametrizedPath24(
            this.path + path.markupName.ensureLeadingSlash(),
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
            path
        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>, P22 : PP<P22>, P23 : PP<P23>, P24 : PP<P24>, P25 : PP<P25>> ParametrizedPath24<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24>.div(
        path: P25
    ): ParametrizedPath25<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25> =
        ParametrizedPath25(
            this.path + path.markupName.ensureLeadingSlash(),
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
            path
        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>, P22 : PP<P22>, P23 : PP<P23>, P24 : PP<P24>, P25 : PP<P25>, P26 : PP<P26>> ParametrizedPath25<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25>.div(
        path: P26
    ): ParametrizedPath26<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26> =
        ParametrizedPath26(
            this.path + path.markupName.ensureLeadingSlash(),
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
            path
        )

//    context(ParameterMarkupDecorator)
//    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>, P22 : PP<P22>, P23 : PP<P23>, P24 : PP<P24>, P25 : PP<P25>, P26 : PP<P26>, P27 : PP<P27>> ParametrizedPath26<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26>.div(path: P27): ParametrizedPath27<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27> = ParametrizedPath27(this.path + path.markupName.ensureLeadingSlash(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, path)

//    context(ParameterMarkupDecorator)
//    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>, P22 : PP<P22>, P23 : PP<P23>, P24 : PP<P24>, P25 : PP<P25>, P26 : PP<P26>, P27 : PP<P27>, P28 : PP<P28>> ParametrizedPath27<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27>.div(path: P28): ParametrizedPath28<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28> = ParametrizedPath28(this.path + path.markupName.ensureLeadingSlash(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, path)
//
//    context(ParameterMarkupDecorator)
//    operator fun <P1 : PP<P1>, P2 : PP<P2>, P3 : PP<P3>, P4 : PP<P4>, P5 : PP<P5>, P6 : PP<P6>, P7 : PP<P7>, P8 : PP<P8>, P9 : PP<P9>, P10 : PP<P10>, P11 : PP<P11>, P12 : PP<P12>, P13 : PP<P13>, P14 : PP<P14>, P15 : PP<P15>, P16 : PP<P16>, P17 : PP<P17>, P18 : PP<P18>, P19 : PP<P19>, P20 : PP<P20>, P21 : PP<P21>, P22 : PP<P22>, P23 : PP<P23>, P24 : PP<P24>, P25 : PP<P25>, P26 : PP<P26>, P27 : PP<P27>, P28 : PP<P28>, P29 : PP<P29>> ParametrizedPath28<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28>.div(path: P29): ParametrizedPath29<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29> = ParametrizedPath29(this.path + path.markupName.ensureLeadingSlash(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, path)
}

fun String.ensureLeadingSlash() = if (!startsWith("/")) "/$this" else this


