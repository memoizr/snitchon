package me.snitchon.router

import com.snitch.HttpResponse
import com.snitch.Validator
import me.snitchon.documentation.Visibility
import me.snitchon.endpoint.*
import me.snitchon.http.Handler
import me.snitchon.http.HTTPMethod
import me.snitchon.parameter.*
import kotlin.reflect.KClass

internal typealias PP<T> = Path<T, *>
internal typealias Par = Parameter<*, *>

data class Body<T : Any>(val klass: KClass<T>) : Bodied<T, Body<T>> {
    override val type: Class<*>
        get() = klass.java

    override val name: String
        get() = "Body"
    override val pattern: Validator<Any, T>
        get() = TODO()
    override val description: String
        get() = "body of the request"
    override val required: Boolean
        get() = TODO()
    override val emptyAsMissing: Boolean
        get() = TODO()
    override val invalidAsMissing: Boolean
        get() = TODO()
}

object HasBody : Parameter<Nothing, Nothing> {
    override val type: Class<*>
        get() = TODO("Not yet implemented")
    override val name: String
        get() = TODO("Not yet implemented")
    override val description: String
        get() = TODO("Not yet implemented")
    override val pattern: Validator<Nothing, Nothing>
        get() = TODO()
    override val required: Boolean
        get() = TODO()
    override val emptyAsMissing: Boolean
        get() = TODO()
    override val invalidAsMissing: Boolean
        get() = TODO()
}

interface Bodied<T : Any, A : Body<T>> : Parameter<Any, T> {
    context (Handler, A, HasBody)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val body: T
        get() = request.body(type) as T
}

object RouterContext {

    fun GET(path: String = "") = Endpoint0(
        HTTPMethod.GET,
        if (path.isEmpty()) "" else path.ensureLeadingSlash(),
        "",
        description = null,
        visibility = Visibility.PUBLIC,
        response = Nothing::class
    )

    fun PUT(path: String) = Endpoint0(
        HTTPMethod.PUT,
        path.ensureLeadingSlash(),
        "",
        description = null,
        visibility = Visibility.PUBLIC,
        response = Nothing::class
    )

    fun POST(path: String) = Endpoint0(
        HTTPMethod.POST,
        path.ensureLeadingSlash(),
        "",
        description = null,
        visibility = Visibility.PUBLIC,
        response = Nothing::class
    )

    fun DELETE(path: String) = Endpoint0(
        HTTPMethod.DELETE,
        path.ensureLeadingSlash(),
        "",
        description = null,
        visibility = Visibility.PUBLIC,
        response = Nothing::class
    )

    context(Markup)
    fun <P1 : PP<P1>>
            GET(path: P1) =
        Endpoint1(
            HTTPMethod.GET,
            path.markupName,
            null,
            null,
            Visibility.PUBLIC,
            {},
            { _, res -> res },
            Nothing::class,
            path
        )

    fun <P1 : PP<P1>>
            GET(path: ParametrizedPath1<P1>) =
        Endpoint1(
            HTTPMethod.GET,
            path.path,
            null,
            null,
            Visibility.PUBLIC,
            {},
            { _, res -> res },
            Nothing::class,
            path.a
        )

    fun <P1 : PP<P1>>
            PUT(path: ParametrizedPath1<P1>) =
        Endpoint1(
            HTTPMethod.PUT,
            path.path,
            null,
            null,
            Visibility.PUBLIC,
            {},
            { _, res -> res },
            Nothing::class,
            path.a
        )

    fun <A : PP<A>, B : PP<B>>
            GET(path: ParametrizedPath2<A, B>) =
        Endpoint2(
            HTTPMethod.GET, path.path,
            null,
            null,
            Visibility.PUBLIC,
            {},
            { _, res -> res },
            Nothing::class,
            path.a, path.b
        )

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

    context(Markup)
    operator fun <P1 : PP<P1>, P2 : PP<P2>> ParametrizedPath1<P1>.div(path: P2): ParametrizedPath2<P1, P2> {
        return ParametrizedPath2(this.path + path.markupName.ensureLeadingSlash(), this.a, path)
    }

    fun String.ensureLeadingSlash() = if (!startsWith("/")) "/$this" else this
}