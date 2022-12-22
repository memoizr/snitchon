package me.snitchon

import com.snitch.spark.SparkService
import me.snitchon.config.Config
import me.snitchon.documentation.generateDocs
import me.snitchon.parsers.GsonJsonParser

fun main() {
    with(GsonJsonParser) {
        SparkService(Config(port = 3000)).withRoutes {
            GET("hello").isHandledBy {
                Response("world").ok
            }
            GET("textplain").isHandledBy {
                "this is just plain text".ok.plainText
            }
        }
            .startListening()
            .generateDocs()
            .writeDocsToStaticFolder()
    }
}

data class Response(val message: String)
data class Failure(val reason: String)