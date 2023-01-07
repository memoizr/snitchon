package me.snitchon.syntax

import com.snitch.undertow.UndertowRequestWrapper
import com.snitch.undertow.UndertowService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.snitchon.http.Group2
import me.snitchon.http.HTTPMethod
import me.snitchon.http.HttpResponses.ok
import me.snitchon.http.body
import me.snitchon.intparam
import me.snitchon.otherIntParam
import me.snitchon.parameter.ParametrizedPath2
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.path.Path
import me.snitchon.router.*
import me.snitchon.spark.SparkService
import me.snitchon.springboot.SpringService
import me.snitchon.stringParam
import me.snitchon.tests.SnitchTest
import me.snitchon.vertx.VertxService
import java.util.LinkedList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicIntegerArray
import java.util.concurrent.atomic.AtomicLongArray
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


fun main() {
    context(GsonJsonParser, NestSyntax, NestSlashSyntax) {
        val timings = LinkedList<Long>()
        val count = 2000
        val latch = CountDownLatch(count)

        UndertowService().withRoutes {
            GET("warmup").isHandledBy { "ok".ok }
            GET("hey").isHandledBy {
//                val duration = measureTimeMillis {
//                    println("start")
//                    println(
//                        GlobalScope.launch {
//                            measureTimeMillis {
//                                println(
//                                    SnitchTest.HttpClient.get(
//                                        "https://duet-cdn.vox-cdn.com/thumbor/0x0:2040x1351/1200x800/filters:focal(1020x676:1021x677):format(webp)/cdn.vox-cdn.com/uploads/chorus_asset/file/24335842/226470_RTX_4070_Ti_TWarren_0001_edit.jpg",
//                                        emptyMap()
//                                    )
//                                )
//
//                                latch.countDown()
//                            }.also { timings.push(it) }
//                        }
//                    )
//                }
//                println(duration)

                "hey".ok
            }
        }.startListening()

        println(SnitchTest.HttpClient.get("http://localhost:3000/warmup", emptyMap()).body())
        measureTimeMillis {
            repeat(count) {
                GlobalScope.launch {
                    SnitchTest.HttpClient.get("http://localhost:3000/hey", emptyMap()).body()
                    latch.countDown()
                }
            }
            latch.await()
        }.also {
            println("total time: $it, maximum time: ${""}") }
    }

}

context(GetHttpMethods, SlashSyntax<UndertowRequestWrapper>)
private fun Router<UndertowRequestWrapper, ParametrizedPath2<Int, intparam, Int, otherIntParam>>.extracted() {

    GET("hello" / stringParam).isHandledBy {
        ServiceTest.SimpleResponse("${request[intparam]}, ${request[otherIntParam]}, ${request[stringParam]}").ok
        mapOf("hello" to 1, "lol" to "indeed").ok
    }
}

