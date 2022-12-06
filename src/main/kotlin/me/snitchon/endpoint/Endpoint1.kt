package me.snitchon.endpoint

import com.snitch.HttpResponse
import me.snitchon.documentation.Visibility
import me.snitchon.http.Handler

import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.HeaderParameter
import me.snitchon.parameter.QueryParameter
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import me.snitchon.router.Par
import kotlin.reflect.KClass

private typealias HP = HeaderParameter<*, *>
private typealias QP = QueryParameter<*, *>

interface Endpoint<R : Any> {
    val httpMethod: HTTPMethod
    val url: String
    val invoke: (Handler) -> HttpResponse<R>
    context(OnlyHeader)
    infix operator fun <T : HP> plus(t: T): Endpoint<R>

    context(OnlyQuery)
    infix operator fun <T : QP> plus(t: T): Endpoint<R>

    val summary: String?
    val description: String?
    val visibility: Visibility
    val before: (RequestWrapper) -> Unit
    val after: (RequestWrapper, ResponseWrapper) -> Unit
    val response: KClass<R>
}

object OnlyHeader
object OnlyQuery

context(Endpoint0<RETURN>)
infix operator fun <
        ONE : HP,
        TWO : HP,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint1<A, RETURN>)
infix operator fun <
        A : Par,
        ONE : HP,
        TWO : HP,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint2<A, B, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint3<A, B, C, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint4<A, B, C, D, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

context(Endpoint5<A, B, C, D, E, RETURN>)
infix operator fun <
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        ONE : Par,
        TWO : Par,
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

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
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

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
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

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
        RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)


fun <X : Endpoint<RETURN>, Y : Endpoint<RETURN>, RETURN> X.headers(block: context(X, OnlyHeader) () -> Y): Y =
    block(this@X, OnlyHeader)

fun <X : Endpoint<RETURN>, Y : Endpoint<RETURN>, RETURN> X.queries(block: context(X, OnlyQuery) () -> Y): Y =
    block(this@X, OnlyQuery)

typealias After = (RequestWrapper, ResponseWrapper) -> Unit

data class Endpoint0<RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    val func: (context(Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        func!!.invoke(it)
    }

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)

    fun <A : Par> with(parameter: A) =
        Endpoint1(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            parameter
        )

    fun <A, BODY : Body<A>> with(body: BODY) =
        Endpoint2(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            body,
            HasBody
        )
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint1<
        A : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    val handler: (context(A, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, it)
    }

    fun <B : Par> with(parameter: B): Endpoint2<A, B, RETURN> =
        Endpoint2(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            parameter
        )

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)

    fun <X : Endpoint<RETURN>> with(block: context(Endpoint1<A, RETURN>) () -> X): X =
        block(this)

    fun <B, BODY : Body<B>> with(body: BODY): Endpoint3<A, BODY, HasBody, RETURN> =
        Endpoint3(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            body,
            HasBody
        )
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint2<
        A : Par,
        B : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    inline val b: B,
    val handler: (context(A, B, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, b, it)
    }

    infix fun <C : Par> with(parameter: C): Endpoint3<A, B, C, RETURN> =
        Endpoint3(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            parameter
        )

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)

    fun <C, BODY : Body<C>> with(body: BODY): Endpoint4<A, B, BODY, HasBody, RETURN> =
        Endpoint4(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            body,
            HasBody
        )
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint3<
        A : Par,
        B : Par,
        C : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    val func: (context(A, B, C, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        func!!.invoke(a, b, c, it)
    }

    infix fun <D : Par> with(parameter: D): Endpoint4<A, B, C, D, RETURN> =
        Endpoint4(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            parameter
        )

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)


    fun <D, BODY : Body<D>> with(body: BODY): Endpoint5<A, B, C, BODY, HasBody, RETURN> =
        Endpoint5(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            body,
            HasBody
        )
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint4<
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    val handler: (context(A, B, C, D, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, b, c, d, it)
    }

    infix fun <E : Par> with(parameter: E): Endpoint5<A, B, C, D, E, RETURN> =
        Endpoint5(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            d,
            parameter
        )

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)


    fun <E, BODY : Body<E>> with(body: BODY): Endpoint6<A, B, C, D, BODY, HasBody, RETURN> =
        Endpoint6(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            d,
            body,
            HasBody
        )
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint5<
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    val handler: (context(A, B, C, D, E, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, b, c, d, e, it)
    }

    infix fun <F : Par> with(parameter: F): Endpoint6<A, B, C, D, E, F, RETURN> =
        Endpoint6(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            d,
            e,
            parameter
        )

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)


    fun <F, BODY : Body<F>> with(body: BODY): Endpoint7<A, B, C, D, E, BODY, HasBody, RETURN> =
        Endpoint7(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            d,
            e,
            body,
            HasBody
        )
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint6<
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    val handler: (context(A, B, C, D, E, F,
    Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {

    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, b, c, d, e, f, it)
    }

    fun <G : Par> with(parameter: G) =
        Endpoint7(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
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
        Endpoint8(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            d,
            e,
            f,
            body,
            HasBody
        )
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint7<
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        G : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    inline val g: G,
    val handler: (context(A, B, C, D, E, F, G, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {
    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, b, c, d, e, f, g, it)
    }

    fun <H : Par> with(parameter: H) =
        Endpoint8(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            d,
            e,
            f,
            g,
            parameter
        )

    fun <G, BODY : Body<G>> with(body: BODY) =
        Endpoint9(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
            a,
            b,
            c,
            d,
            e,
            f,
            g,
            body,
            HasBody
        )

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = with(t)

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = with(t)
}

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
data class Endpoint8<
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        G : Par,
        H : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    inline val g: G,
    inline val h: H,
    val handler: (context(A, B, C, D, E, F, G, H, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {
    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, b, c, d, e, f, g, h, it)
    }

    fun <I : Par> with(parameter: I) =
        Endpoint9(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
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
data class Endpoint9<
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        G : Par,
        H : Par,
        I : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
    inline val a: A,
    inline val b: B,
    inline val c: C,
    inline val d: D,
    inline val e: E,
    inline val f: F,
    inline val g: G,
    inline val h: H,
    inline val i: I,
    val handler: (context(A, B, C, D, E, F, G, H, I, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {
    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, b, c, d, e, f, g, h, i, it)
    }

    fun <I : Par> with(parameter: I) =
        Endpoint10(
            httpMethod,
            url,
            summary,
            description,
            visibility,
            before,
            after,
            response,
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
data class Endpoint10<
        A : Par,
        B : Par,
        C : Par,
        D : Par,
        E : Par,
        F : Par,
        G : Par,
        H : Par,
        I : Par,
        J : Par,
        RETURN : Any>(
    override val httpMethod: HTTPMethod,
    override val url: String,
    override val summary: String?,
    override val description: String?,
    override val visibility: Visibility,
    override val before: (RequestWrapper) -> Unit = {},
    override val after: After = { _, res -> res },
    override val response: KClass<RETURN>,
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
    val handler: (context(A, B, C, D, E, F, G, H, I, J, Handler) () -> HttpResponse<RETURN>)? = null,
) : Endpoint<RETURN> {
    override val invoke: (Handler) -> HttpResponse<RETURN> = {
        handler!!.invoke(a, b, c, d, e, f, g, h, i, j, it)
    }

    context(OnlyHeader)
    override infix operator fun <T : HP> plus(t: T) = TODO()

    context(OnlyQuery)
    override infix operator fun <T : QP> plus(t: T) = TODO()

}
