package me.snitchon.syntax

import com.snitch.HttpResponse
import com.snitch.HttpResponses.ok
import com.snitch.NonEmptyString
import me.snitchon.http.Handler
import me.snitchon.http.HTTPMethod
import me.snitchon.parameter.Header
import me.snitchon.path.Path
import me.snitchon.parameter.Query
import me.snitchon.parsers.GsonJsonParser.jsonString
import org.junit.jupiter.api.Test
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
    object path6 : Path<path6, String>(pattern = NonEmptyString)
    object path7 : Path<path7, String>(pattern = NonEmptyString)
    object path8 : Path<path8, String>(pattern = NonEmptyString)
    object path9 : Path<path9, String>(pattern = NonEmptyString)
    object path10 : Path<path10, String>(pattern = NonEmptyString)
    object path11 : Path<path11, String>(pattern = NonEmptyString)
    object path12 : Path<path12, String>(pattern = NonEmptyString)
    object path13 : Path<path13, String>(pattern = NonEmptyString)
    object path14 : Path<path14, String>(pattern = NonEmptyString)
    object path15 : Path<path15, String>(pattern = NonEmptyString)
    object path16 : Path<path16, String>(pattern = NonEmptyString)
    object path17 : Path<path17, String>(pattern = NonEmptyString)
    object path18 : Path<path18, String>(pattern = NonEmptyString)
    object path19 : Path<path19, String>(pattern = NonEmptyString)
    object path20 : Path<path20, String>(pattern = NonEmptyString)
    object path21 : Path<path21, String>(pattern = NonEmptyString)
    object path22 : Path<path22, String>(pattern = NonEmptyString)
    object path23 : Path<path23, String>(pattern = NonEmptyString)
    object path24 : Path<path24, String>(pattern = NonEmptyString)

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
    fun `supports 6 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / "hey" / path2 / path3 / path4 / "there" / path5 / path6 / "friend")
                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}".ok }
        }.startListening()

        assertEquals(""""get.foo.1.2.3.4.5.6"""", get("/foo/1/hey/2/3/4/there/5/6/friend"))
    }

    @Test
    fun `supports 7 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / path2 / path3 / path4 / path5 / path6 / path7)
                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}.${path7()}".ok }
        }.startListening()

        assertEquals(""""get.foo.1.2.3.4.5.6.7"""", get("/foo/1/2/3/4/5/6/7"))
    }

    @Test
    fun `supports 8 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / path2 / path3 / path4 / path5 / path6 / path7 / path8)
                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}.${path7()}.${path8()}".ok }
        }.startListening()

        assertEquals(""""get.foo.1.2.3.4.5.6.7.8"""", get("/foo/1/2/3/4/5/6/7/8"))
    }

    @Test
    fun `supports 9 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / path2 / path3 / path4 / path5 / path6 / path7 / path8 / path9)
                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}.${path7()}.${path8()}.${path9()}".ok }
        }.startListening()

        assertEquals(""""get.foo.1.2.3.4.5.6.7.8.9"""", get("/foo/1/2/3/4/5/6/7/8/9"))
    }

    @Test
    fun `supports 10 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / path2 / path3 / path4 / path5 / path6 / path7 / path8 / path9 / path10)
                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}.${path7()}.${path8()}.${path9()}.${path10()}".ok }
        }.startListening()

        assertEquals(""""get.foo.1.2.3.4.5.6.7.8.9.10"""", get("/foo/1/2/3/4/5/6/7/8/9/10"))
    }

    @Test
    fun `supports 11 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / path2 / path3 / path4 / path5 / path6 / path7 / path8 / path9 / path10 / path11)
                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}.${path7()}.${path8()}.${path9()}.${path10()}.${path11()}".ok }
        }.startListening()

        assertEquals(""""get.foo.1.2.3.4.5.6.7.8.9.10.11"""", get("/foo/1/2/3/4/5/6/7/8/9/10/11"))
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
