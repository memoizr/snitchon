package me.snitchon.syntax

import me.snitchon.http.HTTPMethod

data class TestRequest(val method: HTTPMethod,
                       val path: String,
                       val body: Any? = null,
                       val headers: Map<String, String> = emptyMap(),
)