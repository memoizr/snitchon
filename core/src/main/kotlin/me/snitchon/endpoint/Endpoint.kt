package me.snitchon.endpoint

import me.snitchon.documentation.Visibility
import me.snitchon.http.*
import me.snitchon.router.Body
import me.snitchon.router.BodyMarker
import me.snitchon.router.Param
import kotlin.reflect.KClass

data class Endpoint<
        W : RequestWrapper,
        G : Group,
        B : Any?,
        R : Any>(
    val meta: EndpointMeta,
    val invoke: (context(G, BodyMarker<B>, Handler<W>) () -> HttpResponse<R>)? = null,
    val group: G,
    val body: BodyMarker<B>,
    val response: KClass<R>,
) {
//    context(OnlyHeader)
//    infix operator fun <T : HP> plus(t: T): Endpoint<W, R>
//
//    context(OnlyQuery)
//    infix operator fun <T : QP> plus(t: T): Endpoint<W, R>

}

fun <W : RequestWrapper,
        G : Group,
        R : Any,
        T : Any?
        > Endpoint<W, G, Nothing, R>.withBody(body: BodyMarker<T>) =
    Endpoint<W, G, T, R>(
        meta,
        null,
        group,
        body,
        response
    )

@JvmName("zero")
fun < PP, P : Param<PP>,
        W : RequestWrapper> Endpoint<W, Group0, Nothing, Nothing>.with(p: P) =
    Endpoint<W, _, Nothing, Nothing>(
        meta,
        null,
        group.with(p),
        body,
        response
    )

@JvmName("one")
fun <P1P, P1 : Param<P1P>,
        PP, P : Param<PP>,
        W : RequestWrapper> Endpoint<W, Group1<P1P, P1>, Nothing, Nothing>.with(p: P) =
    Endpoint<W, _, Nothing, Nothing>(
        meta,
        null,
        group.with(p),
        body,
        response
    )

@JvmName("two")
fun <P1P, P1 : Param<P1P>,
        P2P, P2 : Param<P2P>,
        PP, P : Param<PP>,
        W : RequestWrapper> Endpoint<W, Group2<P1P, P1, P2P, P2>, Nothing, Nothing>.with(p: P) =
    Endpoint<W, _, _, _>(
        meta,
        null,
        group.with(p),
        body,
        response
    )


fun <G : Group, B : Any?, R : Any, W : RequestWrapper, E : Endpoint<W, G, B, R>> E.description(description: String) =
    copy(meta.copy(description = description))

fun <G : Group, B : Any?, R : Any, W : RequestWrapper, E : Endpoint<W, G, B, R>> E.summary(summary: String) =
    copy(meta.copy(summary = summary))

fun <G : Group, B : Any?, R : Any, W : RequestWrapper, E : Endpoint<W, G, B, R>> E.visibility(visibility: Visibility) =
    copy(meta.copy(visibility = visibility))


data class EndpointMeta(
    val httpMethod: HTTPMethod,
    val url: String,
    val summary: String?,
    val description: String?,
    val visibility: Visibility,
)


