package me.snitchon.parameter

import me.snitchon.http.Group
import me.snitchon.http.Group0
import me.snitchon.http.Group1
import me.snitchon.http.Group2
import me.snitchon.http.Group3
import me.snitchon.path.Path
import me.snitchon.router.ensureLeadingSlash

interface ParametrizedPath<
        G : Group,
        out NextParametrizedPath
        > {
    val path: String
    val group: G

    context(ParameterMarkupDecorator)
    operator fun <T, P: Path<P,T>> div(p: P): NextParametrizedPath
}

class ParametrizedPath0
    (override val path: String) : ParametrizedPath<Group0, ParametrizedPath1<*,*>> {
    override val group: Group0 = Group0

    context(ParameterMarkupDecorator)
    override operator fun <T, P: Path<P,T>> div(p: P): ParametrizedPath1<T, P> =
        ParametrizedPath1(this.path + p.markupName.ensureLeadingSlash(), p)
}

class ParametrizedPath1<
        P1P,
        P1 : Path<P1, P1P>
        >(override val path: String, val p1: P1) : ParametrizedPath<Group1<P1P, P1>,
        ParametrizedPath2<*,*,*,*>> {
    override val group = Group1(p1)

    context(ParameterMarkupDecorator)
    override operator fun <T, P: Path<P,T>> div(p: P): ParametrizedPath2<P1P, P1, T, P> =
        ParametrizedPath2(this.path + p.markupName.ensureLeadingSlash(), p1, p)
}

class ParametrizedPath2<
        P1P,
        P1 : Path<P1, P1P>,
        P2P,
        P2 : Path<P2, P2P>
        >(
    override val path: String,
    val p1: P1,
    val p2: P2
) : ParametrizedPath<Group2<P1P, P1, P2P, P2>, ParametrizedPath3<*,*,*,*,*,*>> {
    override val group = Group2(p1, p2)

    context(ParameterMarkupDecorator)
    override operator fun <T, P: Path<P,T>> div(p: P) =
            ParametrizedPath3(this.path + p.markupName.ensureLeadingSlash(), p1, p2, p)
}

class ParametrizedPath3<
        P1P,
        P1 : Path<P1, P1P>,
        P2P,
        P2 : Path<P2, P2P>,
        P3P,
        P3 : Path<P3, P3P>,
        >(
    override val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3
) : ParametrizedPath<Group3<P1P, P1, P2P, P2, P3P, P3>, Nothing> {
    override val group = Group3(p1, p2, p3)

    context(ParameterMarkupDecorator)
    override operator fun <T, P: Path<P,T>> div(p: P) = TODO()
}

