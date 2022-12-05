package me.snitchon.endpoint

import me.snitchon.documentation.Visibility
import me.snitchon.http.EndpointCall

import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.HeaderParameter
import me.snitchon.parameter.QueryParameter
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import me.snitchon.router.Par

private typealias HP = HeaderParameter<*, *>
private typealias QP = QueryParameter<*, *>

interface Endpoint<R> {
    val httpMethod: HTTPMethod
    val url: String
    val invoke: (EndpointCall) -> R
    context(OnlyHeader)
            infix operator fun <T : HP> plus(t: T): Endpoint<R>

    context(OnlyQuery)
            infix operator fun <T : QP> plus(t: T): Endpoint<R>

    val summary: String?
    val description: String?
    val visibility: Visibility
    val before: (RequestWrapper) -> Unit
    val after: (RequestWrapper, ResponseWrapper) -> Unit
}

object OnlyHeader
object OnlyQuery

context(Endpoint1<A, RETURN>)
        infix operator fun <
        A : Par,
        ONE : HP,
        TWO : HP,
        RETURN> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint2<A, B, RETURN>)
        infix operator fun <
        A : Par,
        B : Par,
        ONE : Par,
        TWO : Par,
        RETURN> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint3<A, B, C, RETURN>)
        infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        ONE : Par,
        TWO : Par,
        RETURN> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint4<A, B, C, D, RETURN>)
        infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        ONE : Par,
        TWO : Par,
        RETURN> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint5<A, B, C, D, E, RETURN>)
        infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        ONE : Par,
        TWO : Par,
        RETURN> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint6<A, B, C, D, E, F, RETURN>)
        infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        ONE : Par,
        TWO : Par,
        RETURN> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint7<A, B, C, D, E, F, G, RETURN>)
        infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        G : Par,
        ONE : Par,
        TWO : Par,
        RETURN> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint8<A, B, C, D, E, F, G, H, RETURN>)
        infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        G : Par,
        H : Par,
        ONE : Par,
        TWO : Par,
        RETURN> ONE.plus(parameter: TWO) = with(this).with(parameter)


fun <X : Endpoint<RETURN>, Y : Endpoint<RETURN>, RETURN> X.headers(block: context(X, OnlyHeader) () -> Y): Y =
    block(this@X, OnlyHeader)

fun <X : Endpoint<RETURN>, Y : Endpoint<RETURN>, RETURN> X.queries(block: context(X, OnlyQuery) () -> Y): Y =
    block(this@X, OnlyQuery)

typealias After = (RequestWrapper, ResponseWrapper) -> Unit

data class Endpoint0<RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    val func: (context(EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {

    override val invoke: (EndpointCall) -> RETURN = {
        func!!.invoke(it)
    }

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = with(t)

    fun <A : Par> with(parameter: A) =
        Endpoint1<_, RETURN>(httpMethod, url, summary, description, visibility, before, after, parameter)

    fun <A, BODY : Body<A>> with(body: BODY) =
        Endpoint2<BODY, HasBody, RETURN>(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            body,
            HasBody
        )
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint1<A : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    inline val a: A,
    val handler: (context(A, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {

    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, it)
    }

    fun <B : Par> with(parameter: B): Endpoint2<A, B, RETURN> =
        Endpoint2(httpMethod, url, summary, description, visibility, before, after, a, parameter)

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = with(t)

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint1<A, RETURN>) () -> X): X =
        block(this)

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint3<A, BODY, HasBody, RETURN> =
        Endpoint3(httpMethod, url, summary, description, visibility, before, after, a, body, HasBody)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint2<A : Par, B : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    inline val a: A,
    inline val b: B,
    val handler: (context(A, B, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {

    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, it)
    }

    infix fun <C : Par> with(parameter: C): Endpoint3<A, B, C, RETURN> =
        Endpoint3(httpMethod, url, summary, description, visibility, before, after, a, b, parameter)

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = with(t)

    fun <C, BODY : Body<C>> with(body: BODY): Endpoint4<A, B, BODY, HasBody, RETURN> =
        Endpoint4(httpMethod, url, summary, description, visibility, before, after, a, b, body, HasBody)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint3<A : Par, B : Par, C : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    inline val a: A,
    inline val b: B,
    inline val c: C,
    val func: (context(A, B, C, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {

    override val invoke: (EndpointCall) -> RETURN = {
        func!!.invoke(a, b, c, it)
    }

    infix fun <D : Par> with(parameter: D): Endpoint4<A, B, C, D, RETURN> =
        Endpoint4(httpMethod, url, summary, description, visibility, before, after, a, b, c, parameter)

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = with(t)


    fun <D, BODY : Body<D>> with(body: BODY): Endpoint5<A, B, C, BODY, HasBody, RETURN> =
        Endpoint5(httpMethod, url, summary, description, visibility, before, after, a, b, c, body, HasBody)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint4<A : Par, B : Par, C : Par, D : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    val handler: (context(A, B, C, D, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {

    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, c, d, it)
    }

    infix fun <E : Par> with(parameter: E): Endpoint5<A, B, C, D, E, RETURN> =
        Endpoint5(httpMethod, url, summary, description, visibility, before, after, a, b, c, d, parameter)

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = with(t)


    fun <E, BODY : Body<E>> with(body: BODY): Endpoint6<A, B, C, D, BODY, HasBody, RETURN> =
        Endpoint6(httpMethod, url, summary, description, visibility, before, after, a, b, c, d, body, HasBody)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint5<A : Par, B : Par, C : Par, D : Par, E : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    val handler: (context(A, B, C, D, E, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {

    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, c, d, e, it)
    }

    infix fun <F : Par> with(parameter: F): Endpoint6<A, B, C, D, E, F, RETURN> =
        Endpoint6(httpMethod, url, summary, description, visibility, before, after, a, b, c, d, e, parameter)

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = with(t)


    fun <F, BODY : Body<F>> with(body: BODY): Endpoint7<A, B, C, D, E, BODY, HasBody, RETURN> =
        Endpoint7(httpMethod, url, summary, description, visibility, before, after, a, b, c, d, e, body, HasBody)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint6<A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    val handler: (context(A, B, C, D, E, F,
    EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {

    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, c, d, e, f, it)
    }

    fun <G : Par> with(parameter: G) =
        Endpoint7<_, _, _, _, _, _, _, RETURN>(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            a,
            b,
            c,
            d,
            e,
            f,
            parameter
        )

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = with(t)

    fun <G, BODY : Body<G>> with(body: BODY) =
        Endpoint8<_, _, _, _, _, _, BODY, HasBody, RETURN>(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            a,
            b,
            c,
            d,
            e,
            f,
            body,
            HasBody
        )

//
//    context(Router)
//    fun <R> isHandledBy(handler: context(A, B, C, D, E, F, EndpointCall) () -> R) =
//        Endpoint6(httpMethod, url, a, b, c, d, e, f, handler)
//            .also { endpointss.add(it) }
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint7<A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    inline val g: G,
    val handler: (context(A, B, C, D, E, F, G, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, c, d, e, f, g, it)
    }

    fun <H : Par> with(parameter: H) =
        Endpoint8<_, _, _, _, _, _, _, _, RETURN>(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            a,
            b,
            c,
            d,
            e,
            f,
            g,
            parameter
        )

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint8<A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    inline val g: G,
    inline val h: H,
    val handler: (context(A, B, C, D, E, F, G, H, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, c, d, e, f, g, h, it)
    }

    fun <I : Par> with(parameter: I) =
        Endpoint9<_, _, _, _, _, _, _, _, _, RETURN>(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            a,
            b,
            c,
            d,
            e,
            f,
            g,
            h,
            parameter
        )

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = with(t)

}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint9<A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, I : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    inline val g: G,
    inline val h: H,
    inline val i: I,
    val handler: (context(A, B, C, D, E, F, G, H, I, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, c, d, e, f, g, h, i, it)
    }

    fun <I : Par> with(parameter: I) =
        Endpoint10<_, _, _, _, _, _, _, _, _, _, RETURN>(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            a,
            b,
            c,
            d,
            e,
            f,
            g,
            h,
            i,
            parameter
        )

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = TODO()

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = TODO()

}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint10<A : Par, B : Par, C : Par, D : Par, E : Par, F : Par, G : Par, H : Par, I : Par, J : Par, RETURN>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    inline val g: G,
    inline val h: H,
    inline val i: I,
    inline val j: J,
    val handler: (context(A, B, C, D, E, F, G, H, I, J, EndpointCall) () -> RETURN)? = null,
) : Endpoint<RETURN> {
    override val invoke: (EndpointCall) -> RETURN = {
        handler!!.invoke(a, b, c, d, e, f, g, h, i, j, it)
    }

    context(OnlyHeader)
            override infix operator fun <T : HP> plus(t: T) = TODO()

    context(OnlyQuery)
            override infix operator fun <T : QP> plus(t: T) = TODO()

}
