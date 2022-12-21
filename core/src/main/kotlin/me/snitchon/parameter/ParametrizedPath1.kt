package me.snitchon.parameter

import me.snitchon.path.Path


data class ParametrizedPath0
        (val path: String)

data class ParametrizedPath1<
        A : Path<A, *>
        >(val path: String, val a: A)

data class ParametrizedPath2<
        A : Path<A, *>,
        B : Path<B, *>
        >(val path: String, val a: A, val b: B)

data class ParametrizedPath3<
        A : Path<A, *>,
        B : Path<B, *>,
        C : Path<C, *>,
        >(val path: String, val a: A, val b: B, val c: C)
