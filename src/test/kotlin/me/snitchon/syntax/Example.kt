package me.snitchon.syntax

import com.snitch.undertow.UndertowRequestWrapper
import com.snitch.undertow.UndertowService
import me.snitchon.endpoint.Endpoint
import me.snitchon.http.Group1
import me.snitchon.http.Handler
import me.snitchon.http.HttpResponse
import me.snitchon.http.HttpResponses.ok
import me.snitchon.http.HttpResponses.plainText
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.Parameter
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.router.Body
import me.snitchon.router.HasBody
import me.snitchon.stringParam

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
            val x = GET("hello" / stringParam)
                x.isHandledBy {
                    println("printing")
                    println(request[stringParam])
                    println("done")
                    "".ok
                }
        }.startListening()
    }
}

//fun <P1P,
//        P1 : Param<P1P>,
//        P2P,
//        P2 : Param<P2P>> withs(p1: P1, p2: P2, block: context(Group2<P1P, P1, P2P, P2>) () -> Unit) = block(Group2(p1,p2))

typealias Param<T> = Parameter<*, T>

//interface Group {
//}
//
//data class Group1<
//        P1P,
//        P1 : Param<P1P>>(val p1: P1) : Group {
//    context(Handler<W>)
//    operator fun <P1P, P1 : Param<P1P>, W : RequestWrapper> W.get(p1: P1) = getParam(p1)
//}
//
//data class Group2<
//        P1P,
//        P1 : Param<P1P>,
//        P2P,
//        P2 : Param<P2P>>(val p1: P1, val p2: P2) : Group {
//
//
//    context(Handler<W>)
//    @JvmName("p1")
//    operator fun <W : RequestWrapper> W.get(p1: P1) = getParam(p1)
//
//    context(Handler<W>)
//    @JvmName("p2")
//    operator fun <W : RequestWrapper> W.get(p2: P2) = getParam(p2)
//}
