package me.snitchon.endpoint

import me.snitchon.http.Handler

import me.snitchon.http.RequestWrapper
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import me.snitchon.router.Par
import me.snitchon.http.HttpResponse
import kotlin.reflect.KClass

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint1<P1 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val p1: P1,
    val handler: (context(P1, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, it)
    }

    fun <B : Par> with(parameter: B): Endpoint2<P1, B, RETURN> =
        Endpoint2(
            params,
            before,
            after,
            response,
            p1,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint3<P1, BODY, HasBody, RETURN> =
        Endpoint3(
            params,
            before,
            after,
            response,
            p1,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint1<P1, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint2<P1 : Par, P2 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    val handler: (context(P1, P2, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, it)
    }

    fun <B : Par> with(parameter: B): Endpoint3<P1, P2, B, RETURN> =
        Endpoint3(
            params,
            before,
            after,
            response,
            p1,
            p2,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint4<P1, P2, BODY, HasBody, RETURN> =
        Endpoint4(
            params,
            before,
            after,
            response,
            p1,
            p2,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint2<P1, P2, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint3<P1 : Par, P2 : Par, P3 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    val handler: (context(P1, P2, P3, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, it)
    }

    fun <B : Par> with(parameter: B): Endpoint4<P1, P2, P3, B, RETURN> =
        Endpoint4(
            params,
            before,
            after,
            response,
            p1,
            p2,
            p3,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint5<P1, P2, P3, BODY, HasBody, RETURN> =
        Endpoint5(
            params,
            before,
            after,
            response,
            p1,
            p2,
            p3,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint3<P1, P2, P3, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint4<P1 : Par, P2 : Par, P3 : Par, P4 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    val handler: (context(P1, P2, P3, P4, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, it)
    }

    fun <B : Par> with(parameter: B): Endpoint5<P1, P2, P3, P4, B, RETURN> =
        Endpoint5(
            params,
            before,
            after,
            response,
            p1,
            p2,
            p3,
            p4,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint6<P1, P2, P3, P4, BODY, HasBody, RETURN> =
        Endpoint6(
            params,
            before,
            after,
            response,
            p1,
            p2,
            p3,
            p4,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint4<P1, P2, P3, P4, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint5<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    val handler: (context(P1, P2, P3, P4, P5, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, it)
    }

    fun <B : Par> with(parameter: B): Endpoint6<P1, P2, P3, P4, P5, B, RETURN> =
        Endpoint6(
            params,
            before,
            after,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint7<P1, P2, P3, P4, P5, BODY, HasBody, RETURN> =
        Endpoint7(
            params,
            before,
            after,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint5<P1, P2, P3, P4, P5, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint6<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    val handler: (context(P1, P2, P3, P4, P5, P6, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, it)
    }

    fun <B : Par> with(parameter: B): Endpoint7<P1, P2, P3, P4, P5, P6, B, RETURN> =
        Endpoint7(
            params,
            before,
            after,
            response,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint8<P1, P2, P3, P4, P5, P6, BODY, HasBody, RETURN> =
        Endpoint8(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint6<P1, P2, P3, P4, P5, P6, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint7<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, it)
    }

    fun <B : Par> with(parameter: B): Endpoint8<P1, P2, P3, P4, P5, P6, P7, B, RETURN> =
        Endpoint8(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint9<P1, P2, P3, P4, P5, P6, P7, BODY, HasBody, RETURN> =
        Endpoint9(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint7<P1, P2, P3, P4, P5, P6, P7, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint8<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val p1: P1,
    inline val p2: P2,
    inline val p3: P3,
    inline val p4: P4,
    inline val p5: P5,
    inline val p6: P6,
    inline val p7: P7,
    inline val p8: P8,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, it)
    }

    fun <B : Par> with(parameter: B): Endpoint9<P1, P2, P3, P4, P5, P6, P7, P8, B, RETURN> =
        Endpoint9(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint10<P1, P2, P3, P4, P5, P6, P7, P8, BODY, HasBody, RETURN> =
        Endpoint10(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint8<P1, P2, P3, P4, P5, P6, P7, P8, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint9<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, it)
    }

    fun <B : Par> with(parameter: B): Endpoint10<P1, P2, P3, P4, P5, P6, P7, P8, P9, B, RETURN> =
        Endpoint10(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint11<P1, P2, P3, P4, P5, P6, P7, P8, P9, BODY, HasBody, RETURN> =
        Endpoint11(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint9<P1, P2, P3, P4, P5, P6, P7, P8, P9, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint10<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, it)
    }

    fun <B : Par> with(parameter: B): Endpoint11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, B, RETURN> =
        Endpoint11(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, BODY, HasBody, RETURN> =
        Endpoint12(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint11<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, it)
    }

    fun <B : Par> with(parameter: B): Endpoint12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, B, RETURN> =
        Endpoint12(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, BODY, HasBody, RETURN> =
        Endpoint13(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint12<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, it)
    }

    fun <B : Par> with(parameter: B): Endpoint13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, B, RETURN> =
        Endpoint13(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, BODY, HasBody, RETURN> =
        Endpoint14(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint13<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, it)
    }

    fun <B : Par> with(parameter: B): Endpoint14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, B, RETURN> =
        Endpoint14(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, BODY, HasBody, RETURN> =
        Endpoint15(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint14<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, it)
    }

    fun <B : Par> with(parameter: B): Endpoint15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, B, RETURN> =
        Endpoint15(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, BODY, HasBody, RETURN> =
        Endpoint16(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint15<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, it)
    }

    fun <B : Par> with(parameter: B): Endpoint16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, B, RETURN> =
        Endpoint16(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, BODY, HasBody, RETURN> =
        Endpoint17(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint16<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, it)
    }

    fun <B : Par> with(parameter: B): Endpoint17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, B, RETURN> =
        Endpoint17(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, BODY, HasBody, RETURN> =
        Endpoint18(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint17<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, it)
    }

    fun <B : Par> with(parameter: B): Endpoint18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, B, RETURN> =
        Endpoint18(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, BODY, HasBody, RETURN> =
        Endpoint19(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint18<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, it)
    }

    fun <B : Par> with(parameter: B): Endpoint19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, B, RETURN> =
        Endpoint19(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, BODY, HasBody, RETURN> =
        Endpoint20(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint19<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, it)
    }

    fun <B : Par> with(parameter: B): Endpoint20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, B, RETURN> =
        Endpoint20(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, BODY, HasBody, RETURN> =
        Endpoint21(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint20<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, it)
    }

    fun <B : Par> with(parameter: B): Endpoint21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, B, RETURN> =
        Endpoint21(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, BODY, HasBody, RETURN> =
        Endpoint22(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint21<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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

    fun <B : Par> with(parameter: B): Endpoint22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, B, RETURN> =
        Endpoint22(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint23<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, BODY, HasBody, RETURN> =
        Endpoint23(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint22<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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

    fun <B : Par> with(parameter: B): Endpoint23<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, B, RETURN> =
        Endpoint23(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint24<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, BODY, HasBody, RETURN> =
        Endpoint24(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint23<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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

    fun <B : Par> with(parameter: B): Endpoint24<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, B, RETURN> =
        Endpoint24(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint25<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, BODY, HasBody, RETURN> =
        Endpoint25(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint23<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint24<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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

    fun <B : Par> with(parameter: B): Endpoint25<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, B, RETURN> =
        Endpoint25(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint26<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, BODY, HasBody, RETURN> =
        Endpoint26(
            params,
            before,
            after,
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

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint24<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint25<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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

    fun <B : Par> with(parameter: B): Endpoint26<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, B, RETURN> =
        Endpoint26(
            params,
            before,
            after,
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

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint27<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, BODY, HasBody, RETURN> =
        Endpoint27(
            params,
            before,
            after,
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
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint25<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint26<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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

    fun <B : Par> with(parameter: B): Endpoint27<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, B, RETURN> =
        Endpoint27(
            params,
            before,
            after,
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
            p26,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint28<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, BODY, HasBody, RETURN> =
        Endpoint28(
            params,
            before,
            after,
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
            p26,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint26<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint27<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    inline val p27: P27,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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
            p27,
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint28<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, B, RETURN> =
        Endpoint28(
            params,
            before,
            after,
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
            p26,
            p27,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint29<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, BODY, HasBody, RETURN> =
        Endpoint29(
            params,
            before,
            after,
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
            p26,
            p27,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint27<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint28<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    inline val p27: P27,
    inline val p28: P28,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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
            p27,
            p28,
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint29<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, B, RETURN> =
        Endpoint29(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint30<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, BODY, HasBody, RETURN> =
        Endpoint30(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint28<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint29<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    inline val p27: P27,
    inline val p28: P28,
    inline val p29: P29,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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
            p27,
            p28,
            p29,
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint30<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, B, RETURN> =
        Endpoint30(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint31<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, BODY, HasBody, RETURN> =
        Endpoint31(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint29<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint30<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, P30 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    inline val p27: P27,
    inline val p28: P28,
    inline val p29: P29,
    inline val p30: P30,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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
            p27,
            p28,
            p29,
            p30,
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint31<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, B, RETURN> =
        Endpoint31(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint32<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, BODY, HasBody, RETURN> =
        Endpoint32(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint30<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint31<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, P30 : Par, P31 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    inline val p27: P27,
    inline val p28: P28,
    inline val p29: P29,
    inline val p30: P30,
    inline val p31: P31,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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
            p27,
            p28,
            p29,
            p30,
            p31,
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint32<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, B, RETURN> =
        Endpoint32(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            p31,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint33<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, BODY, HasBody, RETURN> =
        Endpoint33(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            p31,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint31<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint32<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, P30 : Par, P31 : Par, P32 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    inline val p27: P27,
    inline val p28: P28,
    inline val p29: P29,
    inline val p30: P30,
    inline val p31: P31,
    inline val p32: P32,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint33<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, B, RETURN> =
        Endpoint33(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint34<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, BODY, HasBody, RETURN> =
        Endpoint34(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint32<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint33<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, P30 : Par, P31 : Par, P32 : Par, P33 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    inline val p27: P27,
    inline val p28: P28,
    inline val p29: P29,
    inline val p30: P30,
    inline val p31: P31,
    inline val p32: P32,
    inline val p33: P33,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            p33,
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint34<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, B, RETURN> =
        Endpoint34(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            p33,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint35<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, BODY, HasBody, RETURN> =
        Endpoint35(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            p33,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint33<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint34<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, P30 : Par, P31 : Par, P32 : Par, P33 : Par, P34 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    inline val p27: P27,
    inline val p28: P28,
    inline val p29: P29,
    inline val p30: P30,
    inline val p31: P31,
    inline val p32: P32,
    inline val p33: P33,
    inline val p34: P34,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            p33,
            p34,
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint35<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, B, RETURN> =
        Endpoint35(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            p33,
            p34,
            parameter
        )

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint36<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, BODY, HasBody, RETURN> =
        Endpoint36(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            p33,
            p34,
            body,
            HasBody
        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint34<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint35<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, P30 : Par, P31 : Par, P32 : Par, P33 : Par, P34 : Par, P35 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    inline val p27: P27,
    inline val p28: P28,
    inline val p29: P29,
    inline val p30: P30,
    inline val p31: P31,
    inline val p32: P32,
    inline val p33: P33,
    inline val p34: P34,
    inline val p35: P35,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, P35, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            p33,
            p34,
            p35,
            it
        )
    }

    fun <B : Par> with(parameter: B): Endpoint36<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, P35, B, RETURN> =
        Endpoint36(
            params,
            before,
            after,
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
            p26,
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            p33,
            p34,
            p35,
            parameter
        )

//    fun <B, BODY : Body<B>> with(body: BODY): Endpoint37<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, P35, BODY, HasBody, RETURN> =
//        Endpoint37(
//            params,
//            before,
//            after,
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
//            p27,
//            p28,
//            p29,
//            p30,
//            p31,
//            p32,
//            p33,
//            p34,
//            p35,
//            body,
//            HasBody
//        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint35<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, P35, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint36<P1 : Par, P2 : Par, P3 : Par, P4 : Par, P5 : Par, P6 : Par, P7 : Par, P8 : Par, P9 : Par, P10 : Par, P11 : Par, P12 : Par, P13 : Par, P14 : Par, P15 : Par, P16 : Par, P17 : Par, P18 : Par, P19 : Par, P20 : Par, P21 : Par, P22 : Par, P23 : Par, P24 : Par, P25 : Par, P26 : Par, P27 : Par, P28 : Par, P29 : Par, P30 : Par, P31 : Par, P32 : Par, P33 : Par, P34 : Par, P35 : Par, P36 : Par, RETURN : Any>(
    override var params: EndpointParameters,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
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
    inline val p27: P27,
    inline val p28: P28,
    inline val p29: P29,
    inline val p30: P30,
    inline val p31: P31,
    inline val p32: P32,
    inline val p33: P33,
    inline val p34: P34,
    inline val p35: P35,
    inline val p36: P36,
    val handler: (context(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, P35, P36, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
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
            p27,
            p28,
            p29,
            p30,
            p31,
            p32,
            p33,
            p34,
            p35,
            p36,
            it
        )
    }

//    fun <B : Par> with(parameter: B): Endpoint37<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, P35, P36, B, RETURN> =
//        Endpoint37(
//            params,
//            before,
//            after,
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
//            p27,
//            p28,
//            p29,
//            p30,
//            p31,
//            p32,
//            p33,
//            p34,
//            p35,
//            p36,
//            parameter
//        )

//    fun <B, BODY : Body<B>> with(body: BODY): Endpoint38<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, P35, P36, BODY, HasBody, RETURN> =
//        Endpoint38(
//            params,
//            before,
//            after,
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
//            p27,
//            p28,
//            p29,
//            p30,
//            p31,
//            p32,
//            p33,
//            p34,
//            p35,
//            p36,
//            body,
//            HasBody
//        )

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint36<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, P23, P24, P25, P26, P27, P28, P29, P30, P31, P32, P33, P34, P35, P36, RETURN>) () -> X): X =
        block(this)

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = TODO()// with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = TODO() //with(t)
}

