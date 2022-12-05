package me.snitchon.service

import me.snitchon.config.Config
import me.snitchon.endpoint.*
import me.snitchon.router.Router
import me.snitchon.router.RouterContext

interface SnitchService {
    val config: Config get() = Config()
    fun registerMethod(bundle: Endpoint<*>, path: String)

    fun withRoutes(routerConfiguration: Router.() -> Unit) {
    }
}

data class RoutedService(val service: SnitchService, val router: Router) {
    fun startListening(): RoutedService {
        router.endpoints.forEach {
            with(RouterContext) {
                service.registerMethod(it, it.url.ensureLeadingSlash())
            }
        }
        return this
    }
}

