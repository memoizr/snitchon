package me.snitchon.syntax

import com.snitch.undertow.UndertowRequestWrapper
import com.snitch.undertow.UndertowService
import me.snitchon.http.HttpResponses.ok
import me.snitchon.intparam
import me.snitchon.otherIntParam
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parameter.ParametrizedPath2
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.router.*
import me.snitchon.stringParam

fun main() {
    context(GsonJsonParser, NestSyntax, NestSlashSyntax) {
        UndertowService().withRoutes {
            nest(intparam) / {
                nest("hello") / {
                    nest(otherIntParam) / {
                        "hello" / otherIntParam
                        extracted()
                    }
                }
            }
        }.startListening()
    }
}

context(GetHttpMethods, SlashSyntax<UndertowRequestWrapper>, ParameterMarkupDecorator)
private fun Router<UndertowRequestWrapper, ParametrizedPath2<Int, intparam, Int, otherIntParam>>.extracted() {

    GET("hello" / stringParam).isHandledBy {
        println("printing")
        println(request[stringParam])
        println(request[stringParam])
        println("done")
        ServiceTest.SimpleResponse("${request[intparam]}, ${request[otherIntParam]}, ${request[stringParam]}").ok
        mapOf("hello" to 1, "lol" to "indeed").ok
    }
}

