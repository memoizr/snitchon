package me.snitchon.endpoint

import me.snitchon.http.Handler

import me.snitchon.router.Body
import me.snitchon.router.HasBody
import me.snitchon.router.Par
import me.snitchon.http.HttpResponse
import me.snitchon.http.RequestWrapper
import kotlin.reflect.KClass

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint1<P1 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    val handler: (context(P1, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, it)
    }

    fun <B : Par> with(parameter: B): Endpoint2<P1, B, W, RETURN> =
        Endpoint2(
            params,
            response,
            p1,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint3<P1, BODY, HasBody, W, RETURN> =
        Endpoint3(
            params,


            response,
            p1,
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint1<P1, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint2<P1 : Par, P2 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    val handler: (context(P1, P2, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, it)
    }

    fun <B : Par> with(parameter: B): Endpoint3<P1, P2, B, W, RETURN> =
        Endpoint3(
            params,
            response,
            p1,
            p2,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint4<P1, P2, BODY, HasBody, W, RETURN> =
        Endpoint4(
            params,
            response,
            p1,
            p2,
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint2<P1, P2, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint3<P1 : Par, P2 : Par, P3 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    val handler: (context(P1, P2, P3, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    @JvmName("p1")
    fun r(p1: P1) = p1

    @JvmName("p2")
    fun r(p2: P2) = p2

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, it)
    }

    fun <B : Par> with(parameter: B): Endpoint4<P1, P2, P3, B, W, RETURN> =
        Endpoint4(
            params,
            response,
            p1,
            p2,
            p3,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint5<P1, P2, P3, BODY, HasBody, W, RETURN> =
        Endpoint5(
            params,
            response,
            p1,
            p2,
            p3,
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint3<P1, P2, P3, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = TODO() //with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = TODO() //with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint4<P1 : Par, P2 : Par, P3 : Par, P4 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    val handler: (context(P1, P2, P3, P4, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, it)
    }

    fun <B : Par> with(parameter: B): Endpoint5<P1, P2, P3, P4, B, W, RETURN> =
        Endpoint5(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint6<P1, P2, P3, P4, BODY, HasBody, W, RETURN> =
        Endpoint6(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint4<P1, P2, P3, P4, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint5<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    val handler: (context(P1, P2, P3, P4, P5, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, it)
    }

    fun <B : Par> with(parameter: B): Endpoint6<P1, P2, P3, P4, P5, B, W, RETURN> =
        Endpoint6(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint7<P1, P2, P3, P4, P5, BODY, HasBody, W, RETURN> =
        Endpoint7(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint5<P1, P2, P3, P4, P5, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint6<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    val handler: (context(P1, P2, P3, P4, P5, P6, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, it)
    }

    fun <B : Par> with(parameter: B): Endpoint7<P1, P2, P3, P4, P5, P6, B, W, RETURN> =
        Endpoint7(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint8<P1, P2, P3, P4, P5, P6, BODY, HasBody, W, RETURN> =
        Endpoint8(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint6<P1, P2, P3, P4, P5, P6, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint7<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, it)
    }

    fun <B : Par> with(parameter: B): Endpoint8<P1, P2, P3, P4, P5, P6, P7, B, W, RETURN> =
        Endpoint8(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint9<P1, P2, P3, P4, P5, P6, P7, BODY, HasBody, W, RETURN> =
        Endpoint9(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint7<P1, P2, P3, P4, P5, P6, P7, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint8<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, it)
    }

    fun <B : Par> with(parameter: B): Endpoint9<P1, P2, P3, P4, P5, P6, P7, P8, B, W, RETURN> =
        Endpoint9(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint10<P1, P2, P3, P4, P5, P6, P7, P8, BODY, HasBody, W, RETURN> =
        Endpoint10(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint8<P1, P2, P3, P4, P5, P6, P7, P8, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint9<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, it)
    }

    fun <B : Par> with(parameter: B): Endpoint10<P1, P2, P3, P4, P5, P6, P7, P8, P9, B, W, RETURN> =
        Endpoint10(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            p9,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint11<P1, P2, P3, P4, P5, P6, P7, P8, P9, BODY, HasBody, W, RETURN> =
        Endpoint11(
            params,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            p9,
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint9<P1, P2, P3, P4, P5, P6, P7, P8, P9, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint10<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, it)
    }

    fun <B : Par> with(parameter: B): Endpoint11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, B, W, RETURN> =
        Endpoint11(
            params,
            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, BODY, HasBody, W, RETURN> =
        Endpoint12(
            params,
            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint11<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, it)
    }

    fun <B : Par> with(parameter: B): Endpoint12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, B, W, RETURN> =
        Endpoint12(
            params,
            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, BODY, HasBody, W, RETURN> =
        Endpoint13(
            params,
            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint12<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, it)
    }

    fun <B : Par> with(parameter: B): Endpoint13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, B, W, RETURN> =
        Endpoint13(
            params,
            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, BODY, HasBody, W, RETURN> =
        Endpoint14(
            params,
            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint13<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, it)
    }

    fun <B : Par> with(parameter: B): Endpoint14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, B, W, RETURN> =
        Endpoint14(
            params,
            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, BODY, HasBody, W, RETURN> =
        Endpoint15(
            params,
            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint14<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, it)
    }

    fun <B : Par> with(parameter: B): Endpoint15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, B, W, RETURN> =
        Endpoint15(
            params,
            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, BODY, HasBody, W, RETURN> =
        Endpoint16(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint15<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, it)
    }

    fun <B : Par> with(parameter: B): Endpoint16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, B, W, RETURN> =
        Endpoint16(
            params,


            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, BODY, HasBody, W, RETURN> =
        Endpoint17(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint16<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, it)
    }

    fun <B : Par> with(parameter: B): Endpoint17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, B, W, RETURN> =
        Endpoint17(
            params,


            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, BODY, HasBody, W, RETURN> =
        Endpoint18(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint17<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    inline val p17: P17,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, it)
    }

    fun <B : Par> with(parameter: B): Endpoint18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, B, W, RETURN> =
        Endpoint18(
            params,


            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, BODY, HasBody, W, RETURN> =
        Endpoint19(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint18<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    inline val p17: P17,
    inline val p18: P18,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, it)
    }

    fun <B : Par> with(parameter: B): Endpoint19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, B, W, RETURN> =
        Endpoint19(
            params,


            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, BODY, HasBody, W, RETURN> =
        Endpoint20(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint19<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    inline val p17: P17,
    inline val p18: P18,
    inline val p19: P19,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, it)
    }

    fun <B : Par> with(parameter: B): Endpoint20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, B, W, RETURN> =
        Endpoint20(
            params,


            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, BODY, HasBody, W, RETURN> =
        Endpoint21(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint20<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    inline val p17: P17,
    inline val p18: P18,
    inline val p19: P19,
    inline val p20: P20,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, it)
    }

    fun <B : Par> with(parameter: B): Endpoint21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, B, W, RETURN> =
        Endpoint21(
            params,
            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, BODY, HasBody, W, RETURN> =
        Endpoint22(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint21<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    inline val p17: P17,
    inline val p18: P18,
    inline val p19: P19,
    inline val p20: P20,
    inline val p21: P21,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(
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
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, B, W, RETURN> =
        Endpoint22(
            params,


            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint23<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, BODY, HasBody, W, RETURN> =
        Endpoint23(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint22<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    inline val p17: P17,
    inline val p18: P18,
    inline val p19: P19,
    inline val p20: P20,
    inline val p21: P21,
    inline val p22: P22,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(
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
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint23<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, B, W, RETURN> =
        Endpoint23(
            params,


            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint24<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, BODY, HasBody, W, RETURN> =
        Endpoint24(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint23<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    inline val p17: P17,
    inline val p18: P18,
    inline val p19: P19,
    inline val p20: P20,
    inline val p21: P21,
    inline val p22: P22,
    inline val p23: P23,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(
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
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint24<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, B, W, RETURN> =
        Endpoint24(
            params,


            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint25<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, BODY, HasBody, W, RETURN> =
        Endpoint25(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint23<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint24<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    inline val p17: P17,
    inline val p18: P18,
    inline val p19: P19,
    inline val p20: P20,
    inline val p21: P21,
    inline val p22: P22,
    inline val p23: P23,
    inline val p24: P24,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(
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
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint25<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, B, W, RETURN> =
        Endpoint25(
            params,


            response,
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
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint26<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, BODY, HasBody, W, RETURN> =
        Endpoint26(
            params,


            response,
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
            body,
            HasBody
        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint24<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint25<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    inline val p17: P17,
    inline val p18: P18,
    inline val p19: P19,
    inline val p20: P20,
    inline val p21: P21,
    inline val p22: P22,
    inline val p23: P23,
    inline val p24: P24,
    inline val p25: P25,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(
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
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint26<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, B, W, RETURN> =
        Endpoint26(
            params,


            response,
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
            parameter
        )

//    fun <B, BODY : Body<B,W>> with(body: BODY): Endpoint27<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, BODY, HasBody, W, RETURN> =
//        Endpoint27(
//            params,
//
//
//            response,
//            p1,
//            p2,
//            p3,
//            p4,
//            p5,
//            p6,
//            p7,
//            p8,
//            p9,
//            p10,
//            p11,
//            p12,
//            p13,
//            p14,
//            p15,
//            p16,
//            p17,
//            p18,
//            p19,
//            p20,
//            p21,
//            p22,
//            p23,
//            p24,
//            p25,
//            body,
//            HasBody
//        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint25<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint26<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, W : RequestWrapper, RETURN : Any>(
    override var params: EndpointParameters,
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    inline val p9: P9,
    inline val p10: P10,
    inline val p11: P11,
    inline val p12: P12,
    inline val p13: P13,
    inline val p14: P14,
    inline val p15: P15,
    inline val p16: P16,
    inline val p17: P17,
    inline val p18: P18,
    inline val p19: P19,
    inline val p20: P20,
    inline val p21: P21,
    inline val p22: P22,
    inline val p23: P23,
    inline val p24: P24,
    inline val p25: P25,
    inline val p26: P26,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, Handler<W>) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<W, RETURN> {

    override val invoke: (Handler<W>) -> HttpResponse<RETURN> = {
        handler!!.invoke(
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
            it
        )
    }

//    fun <B : Par> with(parameter: B): Endpoint27<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, B, W, RETURN> =
//        Endpoint27(
//            params,
//
//
//            response,
//            p1,
//            p2,
//            p3,
//            p4,
//            p5,
//            p6,
//            p7,
//            p8,
//            p9,
//            p10,
//            p11,
//            p12,
//            p13,
//            p14,
//            p15,
//            p16,
//            p17,
//            p18,
//            p19,
//            p20,
//            p21,
//            p22,
//            p23,
//            p24,
//            p25,
//            p26,
//            parameter
//        )
//
//    fun <B, BODY : Body<B,W>> with(body: BODY): Endpoint28<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, BODY, HasBody, W, RETURN> =
//        Endpoint28(
//            params,
//
//
//            response,
//            p1,
//            p2,
//            p3,
//            p4,
//            p5,
//            p6,
//            p7,
//            p8,
//            p9,
//            p10,
//            p11,
//            p12,
//            p13,
//            p14,
//            p15,
//            p16,
//            p17,
//            p18,
//            p19,
//            p20,
//            p21,
//            p22,
//            p23,
//            p24,
//            p25,
//            p26,
//            body,
//            HasBody
//        )

    fun <X : Endpoint<W, RETURN>> with(block: context(Endpoint26<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, W, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = TODO() //with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = TODO() //with(t)
}


