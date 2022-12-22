package me.snitchon.syntax

import com.dslplatform.json.DslJson
import com.dslplatform.json.runtime.Settings
import me.snitchon.parsing.Parser
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream


class SyntaxTest {
    @Test
    fun `supports 0 path parameters`() {

        with (DslJsonParser) {
            val subj = Foobar(
                "one",
                Baz("two"),
                3,
                4.1
            )
        }
    }
}

object DslJsonParser: Parser {
    private val dslJson = DslJson(Settings.withRuntime<Any>().includeServiceLoader())
    override val Any.jsonString
        get() = ByteArrayOutputStream().apply {
            dslJson.serialize(this@Any, this@apply)
        }.toString()

    override val Any.jsonByteArray: ByteArray
        get() = TODO("Not yet implemented")

    override fun <T : Any> String.parseJson(klass: Class<T>): T = byteInputStream()
        .run {
            dslJson.deserialize(klass, this)
        }!!

    override fun <T : Any> ByteArray.parseJson(klass: Class<T>): T = dslJson.deserialize(klass, this.inputStream())!!
}

data class Foobar(
    val a: String,
    val b: Baz,
    val int: Int,
    val double: Double
)

data class Baz(val baz: String)