package me.snitchon.parameter

import PathParameter

data class ParametrizedPath1<A: PathParameter>(val path: String, val a: A) {
}

data class ParametrizedPath2<
        A: PathParameter,
        B: PathParameter
        >(val path: String, val a: A, val b: B)
