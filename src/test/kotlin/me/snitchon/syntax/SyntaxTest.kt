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

