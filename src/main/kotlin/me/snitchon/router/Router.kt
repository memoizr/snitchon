package me.snitchon.router

import me.snitchon.config.Config
import me.snitchon.endpoint.Bundle
import me.snitchon.endpoint.Endpoint
import me.snitchon.endpoint.EndpointBundle
import me.snitchon.service.SnitchService

context (RouterContext)
class Router() {
    val endpoints = mutableListOf<Bundle>()

    inline fun <reified T : Any> body() = Body(T::class)
}