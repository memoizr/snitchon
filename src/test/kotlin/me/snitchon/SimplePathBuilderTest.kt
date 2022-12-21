package me.snitchon

import com.snitch.*
import com.snitch.HttpResponses.badRequest
import me.snitchon.path.Path
import me.snitchon.syntax.GsonJsonParser.jsonString
import org.junit.Rule
import org.junit.Test

class SimplePathBuilderTest : SparkTest() {
    object clipId: Path<clipId, Int>(
        _name = "clipId",
        pattern = NonNegativeInt,
        description = "The clip id"
    )

    object otherPathParam: Path<otherPathParam, Int>(
        _name = "otherPathParam",
        pattern = NonNegativeInt,
        description = "The clip id"
    )

    object thirdPathParam: Path<thirdPathParam, Int>(
        _name = "thirdPathParam",
        pattern = NonNegativeInt,
        description = "The clip id"
    )

    @Rule
    @JvmField
    val rule = SparkTestRule(port) {
        GET("foo")
//            .inSummary("returns a foo")
//            .isDescribedAs("")
            .isHandledBy { TestResult("get value").ok }

        PUT("/foo").isHandledBy { TestResult("put value").created }
        POST("/foo").isHandledBy { TestResult("post value").created }
        DELETE("/foo").isHandledBy { TestResult("delete value").ok }

        GET("/error").isHandledBy { if (false) TestResult("never happens").ok else badRequest("Something went wrong") }
        GET("/forbidden").isHandledBy { if (false) TestResult("never happens").ok else forbidden("Forbidden") }

        GET("noslash/bar").isHandledBy { TestResult("success").ok }
        PUT("noslash/bar").isHandledBy { TestResult("success").ok }
        POST("noslash/bar").isHandledBy { TestResult("success").ok }
        DELETE("noslash/bar").isHandledBy { TestResult("success").ok }

        GET("infixslash" / "bar").isHandledBy { TestResult("success").ok }
        PUT("infixslash" / "bar").isHandledBy { TestResult("success").ok }
        POST("infixslash" / "bar").isHandledBy { TestResult("success").ok }
        DELETE("infixslash" / "bar").isHandledBy { TestResult("success").ok }

        "one" / {
            GET("/a").isHandledBy { TestResult("get value").ok }
            GET("/b").isHandledBy { TestResult("get value").ok }
            "two" / {
                GET("/c").isHandledBy { TestResult("get value").ok }
            }
        }

        "hey" / "there" / {
            GET("/a").isHandledBy { TestResult("get value there a").ok }
        }

        "v1" / {
            GET().isHandledBy { TestResult("get value").ok }
            GET(clipId).isHandledBy { TestResult("get value").ok }
            GET("one" / clipId).isHandledBy { TestResult("get value").ok }
        }

        GET("params1" / clipId / "params2" / otherPathParam).isHandledBy { TestResult("${clipId()}${otherPathParam()}").ok }

        GET("params3" / clipId / "params4" / otherPathParam).isHandledBy { TestResult("${clipId()}${otherPathParam()}").ok }

        GET().isHandledBy { TestResult("get value").ok }

        "hey" / {
            clipId / {
                GET("/a").isHandledBy {
                    TestResult("get value").ok
                }
                "level2" / {
                    otherPathParam / {
                        "nope" / {
                            GET().isHandledBy {
                                TestResult("get ${clipId()} ${otherPathParam()}").ok
                            }
                        }
                    }
                }
            }
        }
    }


    @Test
    fun `supports nested routes`() {
        whenPerform Get "/hey/there/a" expectBodyJson TestResult("get value there a") expectCode 200

        whenPerform Get "/hey/123/level2/3459/nope" expectBodyJson TestResult("get 123 3459") expectCode 200

        whenPerform Get "/one/a" expectBodyJson TestResult("get value") expectCode 200
        whenPerform Get "/one/b" expectBodyJson TestResult("get value") expectCode 200
        whenPerform Get "/one/two/c" expectBodyJson TestResult("get value") expectCode 200

        whenPerform Get "/hey/a" expectCode 404
        whenPerform Get "/hey/123/a" expectBodyJson TestResult("get value") expectCode 200
        whenPerform Get "/v1/123" expectBodyJson TestResult("get value") expectCode 200
        whenPerform Get "/v1/one/123" expectBodyJson TestResult("get value") expectCode 200
        whenPerform Get "/v1" expectBody TestResult("get value").jsonString expectCode 200
        whenPerform Get "/" expectBody TestResult("get value").jsonString expectCode 200
    }

    @Test
    fun `returns successful status codes`() {
        whenPerform Get "/foo" expectBodyJson TestResult("get value") expectCode 200
        whenPerform Put "/foo" expectBodyJson TestResult("put value") expectCode 201
        whenPerform Post "/foo" expectBodyJson TestResult("post value") expectCode 201
        whenPerform Delete "/foo" expectBodyJson TestResult("delete value") expectCode 200
    }

    @Test
    fun `returns error responses`() {
        whenPerform Get "/error" withHeaders mapOf("Accept" to "Text/Plain") expectBodyJson badRequest<TestResult, String>("Something went wrong") expectCode 400
        whenPerform Get "/forbidden" expectBodyJson badRequest<TestResult, String>("Forbidden", 403) expectCode 403
    }

    @Test
    fun `when there's no leading slash, it adds it`() {
        whenPerform Get "/noslash/bar" expectBodyJson TestResult("success") expectCode 200
        whenPerform Put "/noslash/bar" expectBodyJson TestResult("success") expectCode 200
        whenPerform Post "/noslash/bar" expectBodyJson TestResult("success") expectCode 200
        whenPerform Delete "/noslash/bar" expectBodyJson TestResult("success") expectCode 200
    }

    @Test
    fun `supports infix slash`() {
        whenPerform Get "/infixslash/bar" expectBodyJson TestResult("success") expectCode 200
        whenPerform Put "/infixslash/bar" expectBodyJson TestResult("success") expectCode 200
        whenPerform Post "/infixslash/bar" expectBodyJson TestResult("success") expectCode 200
        whenPerform Delete "/infixslash/bar" expectBodyJson TestResult("success") expectCode 200
    }

    @Test
    fun `supports several path parameters`() {
        whenPerform Get "/params1/90/params2/32" expectBodyJson TestResult("9032") expectCode 200
        whenPerform Get "/params3/42/params4/24" expectBodyJson TestResult("4224") expectCode 200
    }
}

//data class TestResult(val value: String)
