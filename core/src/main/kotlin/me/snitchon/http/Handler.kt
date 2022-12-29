package me.snitchon.http

interface Handler<out W: RequestWrapper> {
    val request: W
}

data class NoBodyHandler<W: RequestWrapper>(
    override inline val request: W,
): Handler<W>

data class BodyHandler<T, W: RequestWrapper>(
    override inline val request: W,
    inline val body: T
): Handler<W>
