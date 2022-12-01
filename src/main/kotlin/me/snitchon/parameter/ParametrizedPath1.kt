package me.snitchon.parameter

import PathParameter

data class ParametrizedPath1<A: PathParameter>(val path: String, val a: A) {
}