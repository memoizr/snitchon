package me.snitchon

import com.snitch.undertow.UndertowService
import me.snitchon.config.Config
import me.snitchon.documentation.Description
import me.snitchon.documentation.Visibility.INTERNAL
import me.snitchon.documentation.generateDocs
import me.snitchon.endpoint.description
import me.snitchon.endpoint.summary
import me.snitchon.endpoint.visibility
import me.snitchon.parameter.Query
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.path.Path
import me.snitchon.spark.SparkService
import me.snitchon.springboot.SpringService
import me.snitchon.vertx.VertxService

object query : Query<query, String?>(NonEmptyString.optional())
object id : Path<id, String>(pattern = NonEmptyString)

fun main(args: Array<String>) {
    with (GsonJsonParser) {
        SparkService()
            .withRoutes { ->
                GET("hello" / id / "world")
                    .with(query)
                    .isHandledBy {
                        Result("foobar, id:${id()} qq:${query()}").ok
                    }

                GET("ola"/"is"/"lovely")
                    .isHandledBy {
                        Result("She is ok").ok
                    }

                GET("ola"/"is"/"secret")
                    .visibility(INTERNAL)
                    .isHandledBy {
                        Result("She is ok").ok
                    }
            }
            .generateDocs()
            .servePublicDocumenation()
            .serveInternalDocumenation()
            .routedService.startListening()

        VertxService(Config(port=3003))
            .withRoutes { ->
                GET("hello" / id / "world")
                    .with(query)
                    .isHandledBy {
                        Result("foobar, id:${id()} qq:${query()}").ok
                    }

                GET("ola"/"is"/"lovely")
                    .isHandledBy {
                        Result("She is ok").ok
                    }

                GET("ola"/"is"/"secret")
                    .visibility(INTERNAL)
                    .isHandledBy {
                        Result("She is ok").ok
                    }
            }
            .generateDocs()
            .servePublicDocumenation()
            .serveInternalDocumenation()
            .routedService.startListening()

        SpringService(Config(port= 3001))
            .withRoutes { ->
                "welcome" / {
                    GET("hello" / id / "me")
                        .description("A tragedy")
                        .summary("sample")
                        .with(query)
                        .isHandledBy {
                            Result("foobar, id:${id()} qq:${query()}").ok
                        }

                    GET("ola" / "is" / "lovely")
                        .isHandledBy {
                            Result("She is ok").ok
                        }
                }
            }
            .generateDocs()
            .servePublicDocumenation()
            .routedService.startListening()

        UndertowService(Config(port= 3002))
            .withRoutes { ->
                "welcome" / {
                    GET("hello" / id / "me")
                        .description("A tragedy")
                        .summary("sample")
                        .with(query)
                        .isHandledBy {
                            Result("foobar, id:${id()} qq:${query()}").ok
                        }

                    GET("ola" / "is" / "lovely")
                        .isHandledBy {
                            Result("She is ok").ok
                        }

                    GET("hello" / "is" / "lovely")
                        .isHandledBy {
                            Result("She is ok").ok
                        }
                }
            }
            .generateDocs()
            .servePublicDocumenation()
            .routedService.startListening()
    }
}

data class Result(@Description("the message") val message: String)

