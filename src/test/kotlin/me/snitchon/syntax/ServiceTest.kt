package me.snitchon.syntax

import PathParameter
import me.snitchon.http.HTTPMethod
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ServiceTest {
    val service = TestSnitchService()

//    context(RequestWrapper)
    object userId : PathParameter()

    @Test
    fun `supports 0 path parameters`() {

        service.setRoutes {
            GET("foo").isHandledBy { "foo response" }
            GET("foo" / "bar").isHandledBy { "bar response" }
        }.startListening()

        val fooResponse = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo"))
        val barResponse = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/bar"))

        assertEquals("foo response", fooResponse)
        assertEquals("bar response", barResponse)
    }

    @Test
    fun `supports 1 path parameter` () {
        service.setRoutes {
            GET("foo" / userId).isHandledBy { "param value: ${userId()}" }
            GET("foo" / userId / "bar").isHandledBy { "param value is also: ${userId()}" }
        }.startListening()

        val response1 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good"))
        val response2 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good/bar"))

        assertEquals("param value: good", response1)
        assertEquals("param value is also: good", response2)
    }
}



