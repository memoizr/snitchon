package me.snitchon.syntax

import com.snitch.HttpResponse
import com.snitch.HttpResponses.ok
import com.snitch.NonEmptyString
import me.snitchon.SparkMarkup
import me.snitchon.documentation.generateDocs
import me.snitchon.endpoint.headers
import me.snitchon.endpoint.plus
import me.snitchon.http.Handler
import me.snitchon.http.HTTPMethod
import me.snitchon.parameter.Header
import me.snitchon.path.Path
import me.snitchon.parameter.Query
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ServiceTest {
    data class MyBody(val myChoice: String)
    data class OtherBody(val myChoice: String)

    val service = TestSnitchService()

    //    context(RequestWrapper)


    object userId : Path<userId, String>("userId", pattern = NonEmptyString)
    object videoId : Path<videoId, String>("videoId", pattern = NonEmptyString)
    object TokenHeader : Header<TokenHeader, String>(pattern = NonEmptyString, "token")

    object TimeHeader : Header<TimeHeader, String>(pattern = NonEmptyString, "time")
//    val contentType by ParamDelegate()

    object `Content-Type` : Header<`Content-Type`, String>(pattern = NonEmptyString)
    object HeaderOne : Header<HeaderOne, String>(pattern = NonEmptyString, "one")
    object QueryOne : Query<QueryOne, String>(pattern = NonEmptyString, "one")
    object QueryTwo : Query<QueryTwo, String>(pattern = NonEmptyString, "two")
    object QueryThree : Query<QueryThree, String>(pattern = NonEmptyString, "two")

    object Routing

    data class MyResponse(val greeting: String, val counter: Int, val options: List<String>)

    @Test
    fun `supports 0 path parameters`() {

        val handler: context(Handler) () -> HttpResponse<String> = {
            "foo response".ok
        }

        with(GsonJsonParser) {
            service.withRoutes {
                GET("foo")
                    .isHandledBy(handler)
                GET("foo" / "bar")
                    .headers { HeaderOne + TimeHeader }
                    .isHandledBy {
                        HeaderOne()
                        MyResponse("hello world", 33, listOf("one", "two", "three")).ok }
            }.startListening()
                .generateDocs()
                .writeDocsToStaticFolder()
        }

        val fooResponse = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo"))
        val barResponse = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/bar"))

        assertEquals("foo response", fooResponse)
        assertEquals("bar response", barResponse)
    }


//    fun <X : Endpoint<R>, Y : Endpoint<R>, R> X.hooo(): Y = headers { (TimeHeader + TokenHeader + HeaderOne) } as Y

//    @Test
//    fun `supports 1 path parameter`() {
//        with(GsonJsonParser) {
//            val handler: context(userId, `Content-Type`, HeaderOne, TokenHeader, QueryOne, QueryTwo, QueryThree, Body<MyBody>, HasBody, EndpointCall)
//                () -> String =
//                {
//                    println("******************time*****************")
////                        println(contentType())
////                        TimeHeader.parse()
////                        println(TimeHeader())
//                    println(`Content-Type`())
//                    TokenHeader()
//                    "param value: ${userId()}"
//                }
//            service.setRoutes {
//                GET("foo" / userId)
//                    .headers { `Content-Type` + HeaderOne + TokenHeader }
//                    .queries { QueryOne + QueryTwo + QueryThree }
//                    .with(body<MyBody>())
//                    .isHandledBy(handler)
//                GET("foo" / userId / "bar")
//                    .isHandledBy { "param value is also: ${userId()}" }
//            }.startListening()
//                .generateDocs().also {
////                    println("docss")
//                }
//                .writeDocsToStaticFolder()
//
//        }
//
//
//        val response1 =
//            service.makeRequest(
//                TestRequest(
//                    HTTPMethod.GET, "/foo/good", headers = mapOf(
//                        "time" to "midnight",
//                        "Content-Type" to "the content",
//
//                        )
//                )
//            )
//        val response2 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good/bar"))
//
//        assertEquals("param value: good", response1)
//        assertEquals("param value is also: good", response2)
//    }

    @Test
    fun `supports 1 path parameter and body`() {
        with (SparkMarkup()) {
            service.withRoutes {
                GET("foo" / userId)
                    .with(body<MyBody>())
                    .isHandledBy {
                        "param value: ${userId()}, body: ${body.myChoice}".ok
                    }
            }.startListening()
        }

        val response1 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good", MyBody("it depends")))

        assertEquals("param value: good, body: it depends", response1)
    }
//
//    @Test
//    fun `supports 1 path parameter 1 header and body`() {
//        service.setRoutes {
//            GET("foo" / userId)
//                .with(TokenHeader)
//                .with(body<MyBody>())
//                .isHandledBy {
//                    "param value: ${userId.parse()}, body: ${body.myChoice}, header: ${TokenHeader.parse()}"
//                }
//
//        }.startListening()
//
//        val response1 = service.makeRequest(
//            TestRequest(
//                HTTPMethod.GET,
//                "/foo/good",
//                MyBody("it depends"),
//                headers = mapOf("token" to "head")
//            )
//        )
//
//        assertEquals("param value: good, body: it depends, header: head", response1)
//    }
//
//    @Test
//    fun `supports 1 path parameter 1 header 2 query and body`() {
//        service.setRoutes {
//            GET("foo" / userId)
//                .with(TokenHeader)
//                .queries { QueryOne + QueryTwo }
//                .with(body<MyBody>())
//                .isHandledBy {
//                    "param value: ${userId.parse()}, body: ${body.myChoice}, header: ${TokenHeader.parse()}, query: ${QueryOne()}, two: ${QueryTwo()}"
//                }
//
//        }.startListening()
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
//        assertEquals("param value: good, body: it depends, header: head, query: great, two: greater", response1)
//    }
}


@Suppress(
    "BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER",
    "SUBTYPING_BETWEEN_CONTEXT_RECEIVERS"
)
fun <A, B, C> with(a: A, b: B, x: context(A, B) () -> Unit) where C : A, C : B {
    x(a, b)
}
