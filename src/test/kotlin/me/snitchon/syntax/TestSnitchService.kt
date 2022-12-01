package me.snitchon.syntax

import Parameter
import me.snitchon.endpoint.EndpointBundle
import me.snitchon.http.HTTPMethod
import me.snitchon.http.RequestWrapper
import me.snitchon.router.Router
import me.snitchon.router.RouterContext
import me.snitchon.service.RoutedService
import me.snitchon.service.SnitchService

class TestSnitchService : SnitchService {
    val service = mutableMapOf<TestRequest, (RequestWrapper) -> Any?>()

    fun setRoutes(routerConfiguration: context(RouterContext, SnitchService) Router.() -> Unit): RoutedService {
        val router = with(RouterContext) {
            Router()
        }
        routerConfiguration(RouterContext, this, router)
        val routedService = RoutedService(this, router)

        return routedService
    }

    override fun registerMethod(endpoint: EndpointBundle<*, *>, path: String) {
        service.put(TestRequest(endpoint.endpoint.httpMethod, endpoint.endpoint.url), endpoint.func)
    }

    fun makeRequest(request: TestRequest): Any? {
        val func = service.get(request)
        println(service)
        return func?.invoke(TestRequestWrapper(request))
    }
}
class TestRequestWrapper(testRequest: TestRequest) : RequestWrapper {
    override val body: String by lazy { "" }

    override fun method(): HTTPMethod = HTTPMethod.fromString("PUT")


    override fun params(name: String): String? = ""//context.pathParam(name)

    override fun headers(name: String): String? = ""//context.request().getHeader(name)

    override fun getParam(param: Parameter): String? {
        return ""
    }

}

data class TestRequest(val method: HTTPMethod, val path: String)
