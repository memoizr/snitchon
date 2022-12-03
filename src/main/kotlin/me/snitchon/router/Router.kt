package me.snitchon.router

import me.snitchon.config.Config
import me.snitchon.endpoint.Endpoint
import me.snitchon.service.SnitchService

context (RouterContext)
class Router() {
    val endpointss = mutableListOf<Endpoint<*>>()

    inline fun <reified T : Any> body() = Body(T::class)
}