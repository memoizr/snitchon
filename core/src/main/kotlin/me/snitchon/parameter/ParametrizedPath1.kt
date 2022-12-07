package me.snitchon.parameter


data class ParametrizedPath1<A : Path<A, *>>(val path: String, val a: A) {
}

data class ParametrizedPath2<
        A : Path<A, *>,
        B : Path<B, *>
        >(val path: String, val a: A, val b: B)
