package me.snitchon

import me.snitchon.documentation.Visibility.INTERNAL
import me.snitchon.documentation.Visibility.PUBLIC
import me.snitchon.documentation.generateDocs
import me.snitchon.endpoint.description
import me.snitchon.endpoint.summary
import me.snitchon.endpoint.visibility
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.router.GetHttpMethods
import me.snitchon.router.Router
import me.snitchon.router.SlashSyntax
import me.snitchon.syntax.TestSnitchService
import me.snitchon.http.HttpResponses
import me.snitchon.parameter.ParametrizedPath0
import me.snitchon.syntax.TestRequestWrapper
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class DocumentationTest {
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun routes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        GetHttpMethods,
        SlashSyntax<TestRequestWrapper>,
        HttpResponses
        ) Router<TestRequestWrapper, ParametrizedPath0>.() -> Unit
    ) =
        with(GsonJsonParser) {
            TestSnitchService().withRoutes(routerConfiguration)
                .generateDocs()
        }

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun publicRoutes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        GetHttpMethods,
        SlashSyntax<TestRequestWrapper>,
        HttpResponses
        ) Router<TestRequestWrapper, ParametrizedPath0>.() -> Unit
    ) =
        with(GsonJsonParser) {
            TestSnitchService().withRoutes(routerConfiguration)
                .generateDocs().publicApi
        }

    @Test
    fun `generates documentation automatically`() {
        val spec = publicRoutes { GET("hello").isHandledBy { "hey".ok } }

        assertNotNull(spec.paths["/hello"]?.get?.responses?.getValue("200"))
    }

    @Test
    fun `documentation includes description for endpoint`() {
        val spec = publicRoutes { GET("hey").description("get endpoint").isHandledBy { "hey".ok } }

        assertEquals("get endpoint", spec.paths["/hey"]?.get?.description)
    }

    @Test
    fun `documentation includes summary for endpoint`() {
        val spec = publicRoutes { GET("hey").summary("get endpoint").isHandledBy { "hey".ok } }

        assertEquals("get endpoint", spec.paths["/hey"]?.get?.summary)
    }

    @Test
    fun `includes only ednpoints for a set visibility`() {
        val spec = routes {
            GET("internal") .visibility(INTERNAL).isHandledBy { "internal".ok }
            GET("public") .visibility(PUBLIC).isHandledBy { "public".ok }
        }

        assertNotNull(spec.publicApi.paths["/public"]?.get?.responses?.getValue("200"))
        assertNull(spec.publicApi.paths["/internal"]?.get?.responses?.getValue("200"))

        assertNotNull(spec.internalApi.paths["/internal"]?.get?.responses?.getValue("200"))
    }
}