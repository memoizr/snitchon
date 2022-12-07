package me.snitchon.parameter


data class ParametrizedPath1<A : PathParameter<A, *>>(val path: String, val a: A) {
}

data class ParametrizedPath2<
        A : PathParameter<A, *>,
        B : PathParameter<B, *>
        >(val path: String, val a: A, val b: B)
