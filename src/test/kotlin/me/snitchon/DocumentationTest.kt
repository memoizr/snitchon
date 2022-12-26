package me.snitchon

import me.snitchon.documentation.generateDocs
import me.snitchon.endpoint.description
import me.snitchon.endpoint.summary
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.router.HttpMethods
import me.snitchon.router.Router
import me.snitchon.router.SlashSyntax
import me.snitchon.syntax.TestSnitchService
import me.snitchon.http.HttpResponses
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DocumentationTest {

    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun routes(
        routerConfiguration: context(
        ParameterMarkupDecorator,
        HttpMethods,
        SlashSyntax,
        HttpResponses
        ) Router.() -> Unit
    ) =
        with(GsonJsonParser) {
            TestSnitchService().withRoutes(routerConfiguration)
                .generateDocs().openApi
        }

    @Test
    fun `generates documentation automatically`() {
        val spec = routes { GET("hello").isHandledBy { "hey".ok } }

        assertNotNull(spec.paths["/hello"]?.get?.responses?.getValue("200"))
    }

    @Test
    fun `documentation includes description for endpoint`() {
        val spec = routes { GET("hey").description("get endpoint").isHandledBy { "hey".ok } }

        assertEquals("get endpoint", spec.paths["/hey"]?.get?.description)
    }

    @Test
    fun `documentation includes summary for endpoint`() {
        val spec = routes { GET("hey").summary("get endpoint").isHandledBy { "hey".ok } }

        assertEquals("get endpoint", spec.paths["/hey"]?.get?.summary)
    }
}