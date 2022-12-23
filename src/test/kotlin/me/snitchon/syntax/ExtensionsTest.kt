package me.snitchon.syntax

import com.memoizr.assertk.isEqualTo
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.types.Sealed
import org.junit.jupiter.api.Test

class ExtensionsTest {
    @Test
    fun `parses sealed classes`() {
        with(GsonJsonParser) {
            """{"type":"ONE","one":"hello"}""".parseJson(TheSeal::class.java) isEqualTo TheSeal.ONE("hello")
            """{"type":"TWO","two":"hello"}""".parseJson(TheSeal::class.java) isEqualTo TheSeal.TWO("hello")

            """{"type":"ONE","one":"hello"}""".parseJson(TheSeal.ONE::class.java) isEqualTo TheSeal.ONE("hello")
            """{"type":"TWO","two":"hello"}""".parseJson(TheSeal.TWO::class.java) isEqualTo TheSeal.TWO("hello")
        }
    }
}

sealed class TheSeal : Sealed() {
    data class ONE(val one: String) : TheSeal()
    data class TWO(val two: String) : TheSeal()
}