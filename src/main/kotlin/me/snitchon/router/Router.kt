package me.snitchon.router

import me.snitchon.config.Config
import me.snitchon.endpoint.Endpoint

context (RouterContext)
class Router(val config: Config = Config()) {
    val endpoints = mutableListOf<Endpoint<*>>()

    inline fun <reified T : Any> body() = Body(T::class)
}