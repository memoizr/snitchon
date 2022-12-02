package me.snitchon.http

import Parameter

interface RequestWrapper {
    val body: String
    fun params(name: String): String?
    fun headers(name: String): String?

    fun method() : HTTPMethod

    fun getParam(param: Parameter): String?
}

interface ResponseWrapper {
}

data class EndpointCall(
    val request: RequestWrapper,
    val response: ResponseWrapper
)