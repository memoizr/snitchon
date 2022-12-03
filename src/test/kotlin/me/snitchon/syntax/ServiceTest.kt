package me.snitchon.syntax

import HeaderParameter
import PathParameter
import me.snitchon.http.EndpointCall
import me.snitchon.http.HTTPMethod
import me.snitchon.router.Body
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ServiceTest {
    data class MyBody(val myChoice: String)
    data class OtherBody(val myChoice: String)

    val service = TestSnitchService()

//    context(RequestWrapper)
    object userId : PathParameter<userId>("userId")

   object TokenHeader : HeaderParameter<TokenHeader>("token")
    object TimeHeader : HeaderParameter<TimeHeader>("time")

    @Test
    fun `supports 0 path parameters`() {

        service.setRoutes {
            GET("foo")
            .isHandledBy { "foo response" }
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
            GET("foo" / userId)
                .isHandledBy {
                    "param value: ${userId()}" }
            GET("foo" / userId / "bar").isHandledBy { "param value is also: ${userId()}" }
        }.startListening()

        val response1 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good"))
        val response2 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good/bar"))

        assertEquals("param value: good", response1)
        assertEquals("param value is also: good", response2)
    }

    @Test
    fun `supports 1 path parameter and body` () {
        service.setRoutes {
            GET("foo" / userId)
                .with(body<MyBody>())
                .isHandledBy {
                    "param value: ${userId()}, body: ${body.myChoice}"
                }
        }.startListening()

        val response1 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good", MyBody("it depends")))

        assertEquals("param value: good, body: it depends", response1)
    }

    @Test
    fun `supports 1 path parameter 1 header and body` () {
        service.setRoutes {
            GET("foo" / userId)
                .with(TokenHeader)
                .with(body<MyBody>())
                .isHandledBy {
                    "param value: ${userId.parse()}, body: ${body.myChoice}, header: ${TokenHeader.parse()}"
                }



        }.startListening()

        val response1 = service.makeRequest(TestRequest(
            HTTPMethod.GET,
            "/foo/good",
            MyBody("it depends"),
            headers = mapOf("token" to "head")
        ))

        assertEquals("param value: good, body: it depends, header: head", response1)
    }
}



