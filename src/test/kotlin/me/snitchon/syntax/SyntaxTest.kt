package me.snitchon.syntax

import com.dslplatform.json.CompiledJson
import com.dslplatform.json.DslJson
import com.dslplatform.json.runtime.Settings
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.yield
import me.snitchon.parsing.Parser
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import kotlin.coroutines.resume
import kotlin.test.assertEquals


@CompiledJson
data class Foobar(
    val a: String,
    val b: Baz,
    val int: Int,
    val double: Double
)

@CompiledJson
data class Baz(
    val baz: String)

class SyntaxTest {
    @Test
    fun `supports 0 path parameters`() {
        runBlocking {
            println(hey())
        }
    }
}

suspend fun hey(): Int {
    return suspendCancellableCoroutine {
        it.resume(3)
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

