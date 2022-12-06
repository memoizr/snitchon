package me.snitchon.syntax

import com.snitch.NonEmptyString
import com.snitch.Validator
import me.snitchon.documentation.generateDocs
import me.snitchon.endpoint.headers
import me.snitchon.endpoint.plus
import me.snitchon.endpoint.queries
import me.snitchon.http.EndpointCall
import me.snitchon.http.HTTPMethod
import me.snitchon.parameter.Header
import me.snitchon.parameter.HeaderParameter
import me.snitchon.parameter.PathParameter
import me.snitchon.parameter.QueryParameter
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import org.junit.jupiter.api.Test
import kotlin.reflect.KProperty
import kotlin.test.assertEquals

class ServiceTest {
    data class MyBody(val myChoice: String)
    data class OtherBody(val myChoice: String)

    val service = TestSnitchService()

    //    context(RequestWrapper)


    object userId : PathParameter<userId, String>("userId", pattern = NonEmptyString)
    object videoId : PathParameter<videoId, String>("videoId", pattern = NonEmptyString)
    object TokenHeader : HeaderParameter<TokenHeader, String>("token", pattern = NonEmptyString)

    object TimeHeader : HeaderParameter<TimeHeader, String>("time", pattern = NonEmptyString)
//    val contentType by ParamDelegate()

    object `Content-Type` : Header<`Content-Type`>()
    object HeaderOne : HeaderParameter<HeaderOne, String>("one", pattern = NonEmptyString)
    object QueryOne : QueryParameter<QueryOne, String>("one", pattern = NonEmptyString)
    object QueryTwo : QueryParameter<QueryTwo, String>("two", pattern = NonEmptyString)
    object QueryThree : QueryParameter<QueryThree, String>("two", pattern = NonEmptyString)

    class ParamDelegate {
//        operator fun getValue(any: Any, property: KProperty<*>) =
//            object : HeaderParameter<String, String>(property.name, pattern = NonEmptyString) {}

//        operator fun getValue(any: Any, property: KProperty<*>): String? =
//            object : HeaderParameter<String, String>(property.name, pattern = NonEmptyString) {}
    }

    object Routing

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

//    fun <X : Endpoint<R>, Y : Endpoint<R>, R> X.hooo(): Y = headers { (TimeHeader + TokenHeader + HeaderOne) } as Y

    @Test
    fun `supports 1 path parameter`() {
        with(GsonJsonParser) {
            val handler: context(userId, `Content-Type`, HeaderOne, TokenHeader, QueryOne, QueryTwo, QueryThree, Body<MyBody>, HasBody, EndpointCall)
                () -> String =
                {
                    println("******************time*****************")
//                        println(contentType())
//                        TimeHeader.parse()
//                        println(TimeHeader())
                    println(`Content-Type`())
                    TokenHeader()
                    "param value: ${userId()}"
                }
            service.setRoutes {
                GET("foo" / userId)
                    .headers { `Content-Type` + HeaderOne + TokenHeader }
                    .queries { QueryOne + QueryTwo + QueryThree }
                    .with(body<MyBody>())
                    .isHandledBy(handler)
                GET("foo" / userId / "bar")
                    .isHandledBy { "param value is also: ${userId()}" }
            }.startListening()
                .generateDocs().also {
//                    println("docss")
                }
                .writeDocsToStaticFolder()

        }


        val response1 =
            service.makeRequest(
                TestRequest(
                    HTTPMethod.GET, "/foo/good", headers = mapOf(
                        "time" to "midnight",
                        "Content-Type" to "the content",

                        )
                )
            )
        val response2 = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo/good/bar"))

        assertEquals("param value: good", response1)
        assertEquals("param value is also: good", response2)
    }

    @Test
    fun `supports 1 path parameter and body`() {
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
    fun `supports 1 path parameter 1 header and body`() {
        service.setRoutes {
            GET("foo" / userId)
                .with(TokenHeader)
                .with(body<MyBody>())
                .isHandledBy {
                    "param value: ${userId.parse()}, body: ${body.myChoice}, header: ${TokenHeader.parse()}"
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

        assertEquals("param value: good, body: it depends, header: head", response1)
    }

    @Test
    fun `supports 1 path parameter 1 header 2 query and body`() {
        service.setRoutes {
            GET("foo" / userId)
                .with(TokenHeader)
                .queries { QueryOne + QueryTwo }
                .with(body<MyBody>())
                .isHandledBy {
                    "param value: ${userId.parse()}, body: ${body.myChoice}, header: ${TokenHeader.parse()}, query: ${QueryOne()}, two: ${QueryTwo()}"
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

        assertEquals("param value: good, body: it depends, header: head, query: great, two: greater", response1)
    }
}


@Suppress(
    "BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER",
    "SUBTYPING_BETWEEN_CONTEXT_RECEIVERS"
)
fun <A, B, C> with(a: A, b: B, x: context(A, B) () -> Unit) where C : A, C : B {
    x(a, b)
}
