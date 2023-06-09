package me.snitchon

import me.snitchon.http.HttpResponse.*
import me.snitchon.documentation.Description
import me.snitchon.documentation.Visibility
import me.snitchon.http.*
import me.snitchon.parameter.*
import me.snitchon.path.Path
import me.snitchon.parsers.GsonJsonParser.jsonString
import me.snitchon.tests.ServiceFactory
import me.snitchon.endpoint.with
import me.snitchon.endpoint.withBody
import me.snitchon.router.PostHttpMethods.POST
import me.snitchon.router.marker
import me.snitchon.tests.SnitchTest
import me.snitchon.types.Sealed
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.text.SimpleDateFormat
import java.util.*

object stringParam : Path<String>(pattern = NonEmptyString)

object intparam : Path<Int>(pattern = NonNegativeInt)

object otherIntParam : Path<Int>(pattern = NonNegativeInt)

object q : Query<q, String>(NonEmptyString)

object int :
    Query<int, Int>(pattern = NonNegativeInt, emptyAsMissing = true)

object offset : Query<offset, Int>(NonNegativeInt.optional(defaultIfMissing = { "30" }))

object limit : Query<limit, Int?>(NonNegativeInt.optional())

object qHead : Header<qHead, String>(pattern = NonEmptyString)
object intHead : Header<intHead, Int>(pattern = NonNegativeInt)
object offsetHead : Header<offsetHead, Int>(
    pattern = NonNegativeInt.optional {
        "666"
    },
    emptyAsMissing = true,
)

object limitHead : Header<limitHead, Int?>(
    pattern = NonNegativeInt.optional(), emptyAsMissing = true
)

object time : Query<time, Date>(pattern = DateValidator, "time", description = "the time")

object DateValidator : Validator<String, Date> {
    override val description: String = "An iso 8601 format date"
    override val regex: Regex =
        """^(?:[1-9]\d{3}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1\d|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[1-9]\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]|[13579][26])00)-02-29)T(?:[01]\d|2[0-3]):[0-5]\d:[0-5]\d(?:Z|[+-][01]\d:[0-5]\d)$""".toRegex()
    override val parse: (String, String) -> Date =
        { it, _ -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(it) }
    override val required: Boolean = true
}

@ExtendWith(CustomTypeParameterResolver::class)
open class ParametersTest<W : RequestWrapper>(service: ServiceFactory<W>) : SnitchTest<W>(service) {
    @BeforeEach
    fun before() {
        routes {
            GET("stringpath" / stringParam)
                .isHandledBy { TestResult(request.get(stringParam)).ok }
            GET("intpath" / intparam).isHandledBy { IntTestResult(request[intparam]).ok }
            GET("intpath2" / intparam / "end")
                .with(q)
                .isHandledBy {
                    request[intparam]
                    IntTestResult(request[intparam]).ok
                }

            GET("queriespath").with(q).isHandledBy { TestResult(request[q]).ok }

            GET("queriespath2").with(int).isHandledBy { IntTestResult(request[int]).ok }
            GET("queriespath3").with(offset).isHandledBy { IntTestResult(request[offset]).ok }
            GET("queriespath4").with(limit).isHandledBy { NullableIntTestResult(request[limit]).ok }

            GET("headerspath").with(qHead).isHandledBy { TestResult(request[qHead]).ok }
            GET("headerspath2").with(intHead).isHandledBy {
                IntTestResult(
                    request[intHead]
                ).ok
            }
            GET("headerspath3").with(offsetHead).isHandledBy {
                val result = request[offsetHead]
                NullableIntTestResult(result).ok
            }
            GET("headerspath4").with(limitHead).isHandledBy { NullableIntTestResult(request[limitHead]).ok }

            GET("customParsing").with(time).isHandledBy { DateResult(request[time]).ok }
            POST("bodyparam")
                .withBody(marker<BodyParam>())
                .isHandledBy {
                    val sealed = request.body.sealed
                    BodyTestResult(
                        request.body.int, when (sealed) {
                            is SealedClass.One -> sealed.oneInt
                            is SealedClass.Two -> 2
                        }
                    ).ok
                }
        }
    }

    @Test
    fun `supports typed path parameters`() {
        whenPerform Get "/stringpath/hellothere" expectBodyJson TestResult("hellothere")
        whenPerform Get "/intpath/300" expectBodyJson IntTestResult(300)
    }

    @Test
    fun `validates path parameters`() {
        whenPerform Get "/intpath2/4545/end" expectBodyJson IntTestResult(4545)
        whenPerform Get "/intpath2/hello/end" expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Path parameter `intparam` is invalid, expecting non negative integer, but got `hello`")
        )
    }

    @Test
    fun `supports query parameters`() {
        whenPerform Get "/queriespath?q=foo" expectBodyJson TestResult("foo")
        whenPerform Get "/queriespath?q=foo%0Abar" expectBodyJson TestResult("foo\nbar")
        whenPerform Get "/queriespath?q=" expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Query parameter `q` is invalid, expecting non empty string, but got ``")
        )
        whenPerform Get "/queriespath" expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Required Query parameter `q` is missing")
        )

        whenPerform Get "/queriespath2?int=3434" expectBodyJson IntTestResult(3434)
        whenPerform Get "/queriespath2?int=" expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Required Query parameter `int` is missing")
        )
        whenPerform Get "/queriespath2?int=hello" expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Query parameter `int` is invalid, expecting non negative integer, but got `hello`")
        )
        whenPerform Get "/queriespath2?int=-34" expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Query parameter `int` is invalid, expecting non negative integer, but got `-34`")
        )
    }

    @Test
    fun `supports default values for query parameters`() {
        whenPerform Get "/queriespath3?offset=42" expectBody IntTestResult(42).jsonString
        whenPerform Get "/queriespath3" expectBody IntTestResult(30).jsonString

        whenPerform Get "/queriespath4?limit=42" expectBody NullableIntTestResult(42).jsonString
        whenPerform Get "/queriespath4" expectBodyJson NullableIntTestResult(null)
    }

    @Test
    fun `supports header parameters`() {
        whenPerform Get "/headerspath" withHeaders mapOf(qHead.name to "foo") expectBodyJson TestResult("foo")
        whenPerform Get "/headerspath" withHeaders mapOf(qHead.name to " ") expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Header parameter `qHead` is invalid, expecting non empty string, but got ``")
        )
        whenPerform Get "/headerspath" withHeaders mapOf() expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Required Header parameter `qHead` is missing")
        )

        whenPerform Get "/headerspath2" withHeaders mapOf(intHead.name to 3434) expectBodyJson IntTestResult(3434)
        whenPerform Get "/headerspath2" expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Required Header parameter `intHead` is missing")
        )
        whenPerform Get "/headerspath2" withHeaders mapOf(intHead.name to "hello") expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Header parameter `intHead` is invalid, expecting non negative integer, but got `hello`")
        )
        whenPerform Get "/headerspath2" withHeaders mapOf(intHead.name to -34) expectBodyJson ErrorHttpResponse<TestResult, List<String>>(
            400,
            listOf("Header parameter `intHead` is invalid, expecting non negative integer, but got `-34`")
        )
    }

    @Test
    fun `supports default values for header parameters`() {
        whenPerform Get "/headerspath3" withHeaders mapOf(offsetHead.name to 42) expectBody IntTestResult(42).jsonString
        whenPerform Get "/headerspath3" expectBody IntTestResult(666).jsonString
        whenPerform Get "/headerspath3" expectBody IntTestResult(666).jsonString

        whenPerform Get "/headerspath4" withHeaders mapOf(limitHead.name to 42) expectBody NullableIntTestResult(42).jsonString
        whenPerform Get "/headerspath4" withHeaders mapOf(limitHead.name to "") expectBodyJson NullableIntTestResult(
            null
        )
        whenPerform Get "/headerspath4" expectBodyJson NullableIntTestResult(null)
    }

    val bodyParam = BodyParam(42, "hello", SealedClass.One(33))

    @Test
    fun `supports body parameter`() {
        whenPerform Post "/bodyparam" withBody bodyParam withHeaders mapOf("Content-Type" to Format.Json.type) expectBodyJson BodyTestResult(
            42,
            33
        )
    }

    @Test
    fun `supports custom parsing`() {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH)
        val date = "2018-06-30T02:59:51-00:00"
        whenPerform Get "/customParsing?time=$date" expectBody DateResult(df.parse(date)).jsonString
    }

    data class IntTestResult(val result: Int)
    data class NullableIntTestResult(val result: Int?)
    data class DateResult(val date: Date)

    data class BodyParam(val int: Int, val string: String, val sealed: SealedClass)
    data class BodyTestResult(val a: Int, val b: Int)

    sealed class SealedClass : Sealed() {
        data class One(val oneInt: Int) : SealedClass()
        object Two : SealedClass()
    }
}

data class TestResult(@Description(visibility = Visibility.INTERNAL) val value: String)
