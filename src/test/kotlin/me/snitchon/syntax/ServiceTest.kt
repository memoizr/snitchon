package me.snitchon.syntax

import me.snitchon.http.HttpResponses.ok
import me.snitchon.NonEmptyString
import me.snitchon.endpoint.with
import me.snitchon.endpoint.withBody
import me.snitchon.http.*
import me.snitchon.parameter.Header
import me.snitchon.path.Path
import me.snitchon.parameter.Query
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.parsers.GsonJsonParser.jsonString
import me.snitchon.router.BodyMarker
import me.snitchon.router.marker
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ServiceTest {
    data class MyBody(val myChoice: String)
    data class OtherBody(val myChoice: String)

    val service = TestSnitchService()

    object path0 : Path<String>("userId", pattern = NonEmptyString)
    object path1 : Path<String>("userId", pattern = NonEmptyString)
    object path2 : Path<String>(pattern = NonEmptyString)
    object path3 : Path<String>(pattern = NonEmptyString)
    object path4 : Path<String>(pattern = NonEmptyString)
    object path5 : Path<String>(pattern = NonEmptyString)
    object path6 : Path<String>(pattern = NonEmptyString)
    object path7 : Path<String>(pattern = NonEmptyString)
    object path8 : Path<String>(pattern = NonEmptyString)
    object path9 : Path<String>(pattern = NonEmptyString)
    object path10 : Path<String>(pattern = NonEmptyString)
    object path11 : Path<String>(pattern = NonEmptyString)
    object path12 : Path<String>(pattern = NonEmptyString)
    object path13 : Path<String>(pattern = NonEmptyString)
    object path14 : Path<String>(pattern = NonEmptyString)
    object path15 : Path<String>(pattern = NonEmptyString)
    object path16 : Path<String>(pattern = NonEmptyString)
    object path17 : Path<String>(pattern = NonEmptyString)
    object path18 : Path<String>(pattern = NonEmptyString)
    object path19 : Path<String>(pattern = NonEmptyString)
    object path20 : Path<String>(pattern = NonEmptyString)
    object path21 : Path<String>(pattern = NonEmptyString)
    object path22 : Path<String>(pattern = NonEmptyString)
    object path23 : Path<String>(pattern = NonEmptyString)
    object path24 : Path<String>(pattern = NonEmptyString)

    object TokenHeader : Header<TokenHeader, String>(pattern = NonEmptyString, "token")
    object QueryOne : Query<QueryOne, String>(pattern = NonEmptyString, "one")
    object QueryTwo : Query<QueryTwo, String>(pattern = NonEmptyString, "two")

    data class MyResponse(val greeting: String, val counter: Int, val options: List<String>)
    data class SimpleResponse(val message: String)

    fun get(path: String, headers: Map<String, String> = mapOf()) =
        service.makeRequest(TestRequest(HTTPMethod.GET, path, headers = headers))

    @Test
    fun `supports 0 path parameters`() {
        service.withRoutes {
            GET("foo").isHandledBy { SimpleResponse("foo response").ok }
            GET("foo" / "bar").isHandledBy { MyResponse("hello world", 33, listOf("one", "two", "three")).ok }
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
                    SimpleResponse("foo${request[path1]}").ok
                }
            GET("foo" / path1 / "bar")
                .isHandledBy { "param value is also: ${request[path1]}".ok }
        }.startListening()


        val response1 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good"))
        val response2 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good/bar"))

        assertEquals(SimpleResponse("foogood").jsonString, response1)
        assertEquals("param value is also: good".jsonString, response2)
    }

    //
//    fun <
//            P1P, P1 : Param<P1P>, P2P, P2 : Param<P2P>, P3P, P3 : Param<P3P>, B : Any?
//            > handler(
//        p1: P1,
//        p2: P2,
//        p3: P3,
//        block: context(
//        Group3<P1P, P1, P2P, P2, P3P, P3>,
//        BodyMarker<B>,
//        Handler<TestRequestWrapper>
//        ) Container<B>.() -> HttpResponse<String>
//    ) = { g: Group3<P1P, P1, P2P, P2, P3P, P3>, b: BodyMarker<B>, h: Handler<TestRequestWrapper> ->
//        block(g,b,h, Container<B>())}
//
//
//    class Container<B : Any?> {
//        var body: BodyMarker<B>? = null
//    }
//
    @Test
    fun `supports 2 path parameters and header`() {
        service.withRoutes {
            GET("foo" / path1 / path2)
                .with(TokenHeader)
                .isHandledBy {
                    "foo.${request[path1]}.${request[path2]}:token:${request[TokenHeader]}".ok
                }

        }.startListening()

        assertEquals(""""foo.one.two:token:valid"""", get("/foo/one/two", mapOf("token" to "valid")))
    }


    @Test
    fun `supports 3 path parameters`() {
        service.withRoutes {
            GET("foo" / path1 / path2 / path3)
                .isHandledBy { "get.foo.${request[path1]}.${request[path2]}.${request[path3]}".ok }
        }.startListening()

        assertEquals(""""get.foo.one.two.three"""", get("/foo/one/two/three"))
    }
//
//    @Test
//    fun `supports 4 path parameters`() {
//        service.withRoutes {
//            GET("foo" / path1 / path2 / path3 / path4)
//                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}".ok }
//        }.startListening()
//
//        assertEquals(""""get.foo.one.two.three.four"""", get("/foo/one/two/three/four"))
//    }
//
//    @Test
//    fun `supports 5 path parameters`() {
//        service.withRoutes {
//            GET("foo" / path1 / path2 / path3 / path4 / path5)
//                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}".ok }
//        }.startListening()
//
//        assertEquals(""""get.foo.one.two.three.four.five"""", get("/foo/one/two/three/four/five"))
//    }
//
//    @Test
//    fun `supports 6 path parameters`() {
//        service.withRoutes {
//            GET("foo" / path1 / "hey" / path2 / path3 / path4 / "there" / path5 / path6 / "friend")
//                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}".ok }
//        }.startListening()
//
//        assertEquals(""""get.foo.1.2.3.4.5.6"""", get("/foo/1/hey/2/3/4/there/5/6/friend"))
//    }
//
//    @Test
//    fun `supports 7 path parameters`() {
//        service.withRoutes {
//            GET("foo" / path1 / path2 / path3 / path4 / path5 / path6 / path7)
//                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}.${path7()}".ok }
//        }.startListening()
//
//        assertEquals(""""get.foo.1.2.3.4.5.6.7"""", get("/foo/1/2/3/4/5/6/7"))
//    }
//
//    @Test
//    fun `supports 8 path parameters`() {
//        service.withRoutes {
//            GET("foo" / path1 / path2 / path3 / path4 / path5 / path6 / path7 / path8)
//                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}.${path7()}.${path8()}".ok }
//        }.startListening()
//
//        assertEquals(""""get.foo.1.2.3.4.5.6.7.8"""", get("/foo/1/2/3/4/5/6/7/8"))
//    }
//
//    @Test
//    fun `supports 9 path parameters`() {
//        service.withRoutes {
//            GET("foo" / path1 / path2 / path3 / path4 / path5 / path6 / path7 / path8 / path9)
//                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}.${path7()}.${path8()}.${path9()}".ok }
//        }.startListening()
//
//        assertEquals(""""get.foo.1.2.3.4.5.6.7.8.9"""", get("/foo/1/2/3/4/5/6/7/8/9"))
//    }
//
//    @Test
//    fun `supports 10 path parameters`() {
//        service.withRoutes {
//            GET("foo" / path1 / path2 / path3 / path4 / path5 / path6 / path7 / path8 / path9 / path10)
//                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}.${path7()}.${path8()}.${path9()}.${path10()}".ok }
//        }.startListening()
//
//        assertEquals(""""get.foo.1.2.3.4.5.6.7.8.9.10"""", get("/foo/1/2/3/4/5/6/7/8/9/10"))
//    }
//
//    @Test
//    fun `supports 11 path parameters`() {
//        service.withRoutes {
//            GET("foo" / path1 / path2 / path3 / path4 / path5 / path6 / path7 / path8 / path9 / path10 / path11)
//                .isHandledBy { "get.foo.${path1()}.${path2()}.${path3()}.${path4()}.${path5()}.${path6()}.${path7()}.${path8()}.${path9()}.${path10()}.${path11()}".ok }
//        }.startListening()
//
//        assertEquals(""""get.foo.1.2.3.4.5.6.7.8.9.10.11"""", get("/foo/1/2/3/4/5/6/7/8/9/10/11"))
//    }


    @Test
    fun `supports 1 path parameter and body`() {
        with(GsonJsonParser) {
            service.withRoutes {
                GET("foo" / path1)
                    .withBody(marker<MyBody>())
                    .isHandledBy {
                        "param value: ${request[path1]}, body: ${request.body.myChoice}".ok
                    }

            }.startListening()
        }

        val response1 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good", MyBody("it depends")))

        assertEquals(""""param value: good, body: it depends"""", response1)
    }

    @Test
    fun `supports 1 path parameter 1 header and body`() {
        service.withRoutes {
            GET("foo" / path1)
                .with(TokenHeader)
                .withBody(marker<MyBody>())
                .isHandledBy {
                    "param value: ${request[path1]}, body: ${request.body.myChoice}, header: ${request[TokenHeader]}".ok
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

//    @Test
//    fun `supports 1 path parameter 1 header 2 query and body`() {
//        with (GsonJsonParser) {
//            service.withRoutes {
//                GET("foo" / path1)
//                    .with(TokenHeader)
//                    .queries { QueryOne + QueryTwo }
//                    .with(body<MyBody>())
//                    .isHandledBy {
//                        "param value: ${path1()}, body: ${body.myChoice}, header: ${TokenHeader()}, query: ${QueryOne()}, two: ${QueryTwo()}".ok
//                    }
//
//            }.startListening()
//        }
//
//        val response1 = service.makeRequest(
//            TestRequest(
//                HTTPMethod.GET,
//                "/foo/good?one=great&two=greater",
//                MyBody("it depends"),
//                headers = mapOf("token" to "head")
//            )
//        )
//
//        assertEquals(""""param value: good, body: it depends, header: head, query: great, two: greater"""", response1)
//    }
}


@Suppress(
    "BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER",
    "SUBTYPING_BETWEEN_CONTEXT_RECEIVERS"
)
fun <A, B> context(a: A, b: B, x: context(A, B) () -> Unit) {
    x(a, b)
}

@Suppress(
    "BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER",
    "SUBTYPING_BETWEEN_CONTEXT_RECEIVERS"
)
fun <A, B, C> context(a: A, b: B, c: C, x: context(A, B, C) () -> Unit) {
    x(a, b, c)
}

@Suppress(
    "BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER",
    "SUBTYPING_BETWEEN_CONTEXT_RECEIVERS"
)
fun <A, B, C, D> context(a: A, b: B, c: C, d: D, x: context(A, B, C, D) () -> Unit) {
    x(a, b, c, d)
}
