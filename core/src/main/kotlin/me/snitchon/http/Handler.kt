package me.snitchon.http

import me.snitchon.router.BodyMarker
import me.snitchon.router.Param

interface Handler<out W : RequestWrapper> {
    val request: W
}

context(BodyMarker<T>, Handler<W>)
val <T: Any?, W: RequestWrapper> RequestWrapper.body get(): T = request.myBody(t)

data class NoBodyHandler<W : RequestWrapper>(
    override inline val request: W,
) : Handler<W>

data class BodyHandler<W : RequestWrapper, T: Any>(
    override inline val request: W,
    inline val body: T
) : Handler<W>


interface Group {
    fun <PP, P: Param<PP>> with(p: P): Group
}

object Group0 : Group {
    override fun <PP, P : Param<PP>> with(p: P) = Group1(p)
}

data class Group1<
        P1P,
        P1 : Param<P1P>>(val p1: P1) : Group {
    context(Handler<W>)
    operator fun <W : RequestWrapper> W.get(p1: P1) = parseParam(p1)

    override fun <PP, P : Param<PP>> with(p: P) = Group2(p1, p)
}

data class Group2<
        P1P,
        P1 : Param<P1P>,
        P2P,
        P2 : Param<P2P>>(val p1: P1, val p2: P2) : Group {

    override fun <PP, P : Param<PP>> with(p: P): Group = TODO()//


    context(Handler<W>)
    @JvmName("p1")
    operator fun <W : RequestWrapper> W.get(p1: P1) = parseParam(p1)

    context(Handler<W>)
    @JvmName("p2")
    operator fun <W : RequestWrapper> W.get(p2: P2) = parseParam(p2)
}