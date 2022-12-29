package me.snitchon.syntax

import com.snitch.undertow.UndertowRequestWrapper
import com.snitch.undertow.UndertowService
import me.snitchon.http.Handler
import me.snitchon.http.HttpResponse
import me.snitchon.http.HttpResponses.ok
import me.snitchon.http.HttpResponses.plainText
import me.snitchon.http.RequestWrapper
import me.snitchon.intparam
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.parsing.Parser
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import me.snitchon.stringParam
import me.snitchon.strparam

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
    with(GsonJsonParser) {
        UndertowService().withRoutes {
            GET("hello" / stringParam / intparam / strparam)
                .isHandledBy {
                    "".ok
                }
        }.startListening()
    }
}