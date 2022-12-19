package me.snitchon

import com.snitch.spark.SparkService
import me.snitchon.config.Config
import me.snitchon.documentation.generateDocs
import me.snitchon.endpoint.Endpoint0
import me.snitchon.syntax.GsonJsonParser

fun main() {
    with(GsonJsonParser) {
        SparkService(Config(port = 3000)).setRoutes {
            val handledBy: Endpoint0<Any> = GET("hello").isHandledBy {
                if (true) {
                    Failure("world").ok
                } else {
                    Response("world").ok
                }
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