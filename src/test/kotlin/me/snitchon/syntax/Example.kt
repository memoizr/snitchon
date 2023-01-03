package me.snitchon.syntax

import com.snitch.undertow.UndertowRequestWrapper
import com.snitch.undertow.UndertowService
import me.snitchon.http.*
import me.snitchon.http.HttpResponses.ok
import me.snitchon.http.HttpResponses.plainText
import me.snitchon.intparam
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parameter.ParametrizedPath2
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.router.*
import me.snitchon.spark.SparkService
import me.snitchon.stringParam
import me.snitchon.otherIntParam
import me.snitchon.springboot.SpringService
import me.snitchon.vertx.VertxService

class Example {

}

val handler: context(
Body<ServiceTest.MyBody>,
Body<ServiceTest.OtherBody>,
HasBody,
Handler<RequestWrapper>
) () -> HttpResponse<String> =
    {
        body.ok
        "hello".ok.plainText
    }

fun main() {
    context(GsonJsonParser, NestSyntax, NestSlashSyntax) {
        UndertowService().withRoutes {
            nest(intparam) / {
                nest("hello") / {
                    nest(otherIntParam) / {
                        "hello" / otherIntParam
                        GET("hello" / stringParam).isHandledBy {
                            println("printing")
                            println(request[stringParam])
                            println(request[stringParam])
                            println("done")
                            ServiceTest.SimpleResponse("${request[intparam]}, ${request[otherIntParam]}, ${request[stringParam]}").ok
                            mapOf("hello" to 1, "lol" to "indeed").ok
                        }
                    }
                }
            }
        }.startListening()
    }
}

