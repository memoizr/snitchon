package me.snitchon.parameter


data class ParametrizedPath1<A : PathParameter<A, String>>(val path: String, val a: A) {
}

data class ParametrizedPath2<
        A : PathParameter<A, String>,
        B : PathParameter<B, String>
        >(val path: String, val a: A, val b: B)
