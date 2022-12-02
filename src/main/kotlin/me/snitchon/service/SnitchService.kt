package me.snitchon.service

import me.snitchon.config.Config
import me.snitchon.endpoint.Bundle
import me.snitchon.endpoint.EndpointBundle
import me.snitchon.endpoint.EndpointBundle1
import me.snitchon.router.Router
import me.snitchon.router.RouterContext

interface SnitchService {
    val config: Config get() = Config()
    fun registerMethod(bundle: Bundle, path: String)

    fun withRoutes(routerConfiguration: Router.() -> Unit) {

    }
}

data class RoutedService(val service: SnitchService, val router: Router) {
    fun startListening(): RoutedService {
        router.endpoints.forEach {
            with(RouterContext) {
                when (it) {
                    is EndpointBundle<*> -> service.registerMethod(it, it.endpoint.url.ensureLeadingSlash())
                    is EndpointBundle1<*,*> -> service.registerMethod(it, it.endpoint.url.ensureLeadingSlash())
                }
            }
        }
        return this
    }
}

