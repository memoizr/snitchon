package me.snitchon.syntax

import ParDelegate
import PathParameter
import me.snitchon.router.RouterContext
import me.snitchon.router.RouterContext.div
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertEquals

context(Paths.id)
fun exampleHandler() {
    println("accessing the parameter called ${id.name}, described as ${id.description}")
}


object Paths {
    object id : PathParameter("id") {
        val id by ParDelegate()
    }
}

class SyntaxTest {
    @Test
    fun `supports 0 path parameters`() {
        val atomicInteger = AtomicInteger()

        with (RouterContext) {
            GET("foo").isHandledBy {
                atomicInteger.incrementAndGet()
            }
        }

        assertEquals(1, atomicInteger.get())
    }

    @Test
    fun `supports 1 path parameter`() {
        val atomicInteger = AtomicInteger()

        with (RouterContext) {
            GET("foo" / Paths.id).isHandledBy {
                atomicInteger.incrementAndGet()
                assertEquals("id", id.name)
                exampleHandler()
            }
        }

        assertEquals(1, atomicInteger.get())
    }
}