package me.snitchon

import com.snitch.Format
import me.snitchon.config.Config
import me.snitchon.documentation.generateDocs
import me.snitchon.parameter.Query
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.path.Path
import me.snitchon.spark.SparkService
import me.snitchon.springboot.SpringService

object query : Query<query, String?>(NonEmptyString.optional())
object id : Path<id, String>(pattern = NonEmptyString)

fun main(args: Array<String>) {
    with (GsonJsonParser) {
        SparkService(Config())
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
            }
            .generateDocs()
            .writeDocsToStaticFolder()
            .routedService.startListening()

        SpringService(Config(port= 3001))
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
            }
            .generateDocs()
            .writeDocsToStaticFolder()
            .routedService.startListening()
    }
}

data class Result(val message: String)

