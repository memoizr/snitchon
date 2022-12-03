package me.snitchon.service

import me.snitchon.config.Config
import me.snitchon.endpoint.*
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
                    is EndpointBundle1<*, *> -> service.registerMethod(it, it.endpoint.url.ensureLeadingSlash())
                    is EndpointBundle2<*, *, *> -> service.registerMethod(it, it.endpoint.url.ensureLeadingSlash())
                    is EndpointBundle3<*, *, *, *> -> service.registerMethod(it, it.endpoint.url.ensureLeadingSlash())
                    is EndpointBundle4<*, *, *, *, *> -> service.registerMethod(
                        it,
                        it.endpoint.url.ensureLeadingSlash()
                    )

                    is EndpointBundle5<*, *, *, *, *, *> -> service.registerMethod(
                        it,
                        it.endpoint.url.ensureLeadingSlash()
                    )

                    is EndpointBundle6<*, *, *, *, *, *, *> -> service.registerMethod(
                        it,
                        it.endpoint.url.ensureLeadingSlash()
                    )

                    is EndpointBundle1Bodied<*, *, *> -> service.registerMethod(
                        it,
                        it.endpoint.url.ensureLeadingSlash()
                    )

                    is EndpointBundle2Bodied<*, *, *, *> -> service.registerMethod(
                        it,
                        it.endpoint.url.ensureLeadingSlash()
                    )
                }
            }
        }
        return this
    }
}

