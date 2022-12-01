package me.snitchon.service

import me.snitchon.config.Config
import me.snitchon.endpoint.Endpoint
import me.snitchon.endpoint.EndpointBundle
import me.snitchon.router.Router
import java.io.File

interface SnitchService {
    val config: Config get() = Config()
    fun registerMethod(it: EndpointBundle<*,*>, path: String)

    fun withRoutes(routerConfiguration: Router.() -> Unit) {

    }
}

data class RoutedService(val service: SnitchService, val router: Router) {
    fun startListening(): RoutedService {
        router.endpoints.forEach {
            service.registerMethod(it, it.endpoint.url)
        }
        return this
    }
}

