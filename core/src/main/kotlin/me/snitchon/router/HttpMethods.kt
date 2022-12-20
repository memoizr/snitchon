package me.snitchon.router

import me.snitchon.documentation.Visibility
import me.snitchon.endpoint.*
import me.snitchon.http.HTTPMethod
import me.snitchon.parameter.*
import me.snitchon.path.Path

internal typealias PP<T> = Path<T, *>
internal typealias Par = Parameter<*, *>

object HttpMethods {
    fun GET(path: String = "") = Endpoint0(
        EndpointParameters(
            HTTPMethod.GET,
            if (path.isEmpty()) "" else path.ensureLeadingSlash(),
            "",
            description = null,
            visibility = Visibility.PUBLIC,
        ),
        response = Nothing::class
    )

    fun PUT(path: String) = Endpoint0(
        EndpointParameters(
            HTTPMethod.PUT,
            path.ensureLeadingSlash(),
            "",
            description = null,
            visibility = Visibility.PUBLIC,
        ),
        response = Nothing::class
    )

    fun POST(path: String) = Endpoint0(
        EndpointParameters(
            HTTPMethod.POST,
            path.ensureLeadingSlash(),
            "",
            description = null,
            visibility = Visibility.PUBLIC,
        ),
        response = Nothing::class
    )

    fun DELETE(path: String) = Endpoint0(
        EndpointParameters(
            HTTPMethod.DELETE,
            path.ensureLeadingSlash(),
            "",
            description = null,
            visibility = Visibility.PUBLIC,
        ),
        response = Nothing::class
    )

    context(ParameterMarkupDecorator)
    fun <P1 : PP<P1>>
            GET(path: P1) =
        Endpoint1(
            EndpointParameters(
                HTTPMethod.GET,
                path.markupName,
                null,
                null,
                Visibility.PUBLIC,
            ),
            {},
            { _, res -> res },
            Nothing::class,
            path
        )

    fun <P1 : PP<P1>>
            GET(path: ParametrizedPath1<P1>) =
        Endpoint1(
            EndpointParameters(
                HTTPMethod.GET,
                path.path,
                null,
                null,
                Visibility.PUBLIC,
            ),
            {},
            { _, res -> res },
            Nothing::class,
            path.a
        )

    fun <P1 : PP<P1>>
            PUT(path: ParametrizedPath1<P1>) =
        Endpoint1(
            EndpointParameters(
                HTTPMethod.PUT,
                path.path,
                null,
                null,
                Visibility.PUBLIC,
            ),
            {},
            { _, res -> res },
            Nothing::class,
            path.a
        )

    fun <A : PP<A>, B : PP<B>>
            GET(path: ParametrizedPath2<A, B>) =
        Endpoint2(
            EndpointParameters(
                HTTPMethod.GET, path.path,
                null,
                null,
                Visibility.PUBLIC,
            ),
            {},
            { _, res -> res },
            Nothing::class,
            path.a, path.b
        )

    context(ParameterMarkupDecorator)
    operator fun <P1 : PP<P1>, P2 : PP<P2>> ParametrizedPath1<P1>.div(path: P2): ParametrizedPath2<P1, P2> {
        return ParametrizedPath2(this.path + path.markupName.ensureLeadingSlash(), this.a, path)
    }

}

fun String.ensureLeadingSlash() = if (!startsWith("/")) "/$this" else this


