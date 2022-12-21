package me.snitchon.syntax

import com.snitch.HttpResponse
import com.snitch.HttpResponses.ok
import com.snitch.NonEmptyString
import me.snitchon.endpoint.plus
import me.snitchon.http.Handler
import me.snitchon.http.HTTPMethod
import me.snitchon.parameter.Header
import me.snitchon.path.Path
import me.snitchon.parameter.Query
import me.snitchon.syntax.GsonJsonParser.jsonString
import org.junit.Test
import kotlin.test.assertEquals

class ServiceTest {
    data class MyBody(val myChoice: String)
    data class OtherBody(val myChoice: String)

    val service = TestSnitchService()

    object path1 : Path<path1, String>("userId", pattern = NonEmptyString)
    object path2 : Path<path2, String>(pattern = NonEmptyString)
    object path3 : Path<path3, String>(pattern = NonEmptyString)
    object path4 : Path<path4, String>(pattern = NonEmptyString)
    object path5 : Path<path5, String>(pattern = NonEmptyString)
    object TokenHeader : Header<TokenHeader, String>(pattern = NonEmptyString, "token")
    object QueryOne : Query<QueryOne, String>(pattern = NonEmptyString, "one")
    object QueryTwo : Query<QueryTwo, String>(pattern = NonEmptyString, "two")

    data class MyResponse(val greeting: String, val counter: Int, val options: List<String>)
    data class SimpleResponse(val message: String)

    @Test
    fun `supports 0 path parameters`() {

        val handler: context(Handler) () -> HttpResponse<SimpleResponse> = {
            SimpleResponse("foo response").ok
        }

        service.withRoutes {
            GET("foo")
                .isHandledBy(handler)
            GET("foo" / "bar")
                .isHandledBy {
                    MyResponse("hello world", 33, listOf("one", "two", "three")).ok
                }
        }.startListening()

        val fooResponse = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo"))
        val barResponse = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/bar"))

        assertEquals(SimpleResponse("foo response").jsonString, fooResponse)
        assertEquals(MyResponse("hello world", 33, listOf("one", "two", "three")).jsonString, barResponse)
    }

    @Test
    fun `supports 1 path parameter`() {
        service.withRoutes {
            GET("foo" / path1)
                .isHandledBy {
                    SimpleResponse("foo${path1()}").ok
                }
            GET("foo" / path1 / "bar")
                .isHandledBy { "param value is also: ${path1()}".ok }
        }.startListening()


        val response1 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good"))
        val response2 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good/bar"))

        assertEquals(SimpleResponse("foogood").jsonString, response1)
        assertEquals("param value is also: good".jsonString, response2)
    }

    @Test
    fun `supports 2 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / path2).isHandledBy { "foo.${path1()}.${path2()}".ok }
        }.startListening()

        assertEquals(""""foo.one.two"""", get("/foo/one/two"))
    }

    fun get(path: String) = service.makeRequest(TestRequest(HTTPMethod.GET, path))

    @Test
    fun `supports 3 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / path2 / path3)
                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}".ok }
        }.startListening()

        assertEquals(""""get.foo.one.two.three"""", get("/foo/one/two/three"))
    }

    @Test
    fun `supports 4 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / path2 / path3 / path4)
                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}".ok }
        }.startListening()

        assertEquals(""""get.foo.one.two.three.four"""", get("/foo/one/two/three/four"))
    }

    @Test
    fun `supports 5 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / path2 / path3 / path4 / path5)
                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}".ok }
        }.startListening()

        assertEquals(""""get.foo.one.two.three.four.five"""", get("/foo/one/two/three/four/five"))
    }

    @Test
    fun `supports 1 path parameter and body`() {
        service.withRoutes {
            GET("foo" / path1)
                .with(body<MyBody>())
                .isHandledBy {
                    "param value: ${path1()}, body: ${body.myChoice}".ok
                }
        }.startListening()

        val response1 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good", MyBody("it depends")))

        assertEquals(""""param value: good, body: it depends"""", response1)
    }

    @Test
    fun `supports 1 path parameter 1 header and body`() {
        service.withRoutes {
            GET("foo" / path1)
                .with(TokenHeader)
                .with(body<MyBody>())
                .isHandledBy {
                    "param value: ${path1.parse()}, body: ${body.myChoice}, header: ${TokenHeader.parse()}".ok
                }

        }.startListening()

        val response1 = service.makeRequest(
            TestRequest(
                HTTPMethod.GET,
                "/foo/good",
                MyBody("it depends"),
                headers = mapOf("token" to "head")
            )
        )

        assertEquals(""""param value: good, body: it depends, header: head"""", response1)
    }

    @Test
    fun `supports 1 path parameter 1 header 2 query and body`() {
        service.withRoutes {
            GET("foo" / path1)
                .with(TokenHeader)
                .queries { QueryOne + QueryTwo }
                .with(body<MyBody>())
                .isHandledBy {
                    "param value: ${path1()}, body: ${body.myChoice}, header: ${TokenHeader()}, query: ${QueryOne()}, two: ${QueryTwo()}".ok
                }

        }.startListening()

        val response1 = service.makeRequest(
            TestRequest(
                HTTPMethod.GET,
                "/foo/good?one=great&two=greater",
                MyBody("it depends"),
                headers = mapOf("token" to "head")
            )
        )

        assertEquals(""""param value: good, body: it depends, header: head, query: great, two: greater"""", response1)
    }
}


@Suppress(
    "BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER",
    "SUBTYPING_BETWEEN_CONTEXT_RECEIVERS"
)
fun <A, B> with(a: A, b: B, x: context(A, B) () -> Unit) {
    x(a, b)
}
