package me.snitchon

import java.lang.NumberFormatException
import kotlin.reflect.KClass
import kotlin.text.RegexOption.*

data class ValidationException(
    val value: Any,
    val summary: String,
    val description: String
) : Exception() {
    val invalidValue =
        "$summary is invalid, expecting $description, but got `$value`"
}

data class MissingRequiredParameterException(
    val name: String

) : Exception("Required $name is missing")

object NonNegativeInt : Validator<String, Int> {
    override val description = "non negative integer"
    override val regex = """^\d+$""".toRegex()
    override val parse: (String, String) -> Int = { it, context ->
        try {
            it.toInt().also { if (it < 0) throw NumberFormatException() }
        } catch (e: NumberFormatException) {
            throw ValidationException(
                it,
                context,
                description
            )
        }
    }
    override val required: Boolean = true
}

object AnyString : Validator<String, String> {
    override val description = "non empty string"
    override val regex = """^.*$""".toRegex(DOT_MATCHES_ALL)
    override val parse: (String, String) -> String = { it, _ -> it }
    override val required: Boolean = true
}

object NonEmptyString : Validator<String, String> {
    override val description = "non empty string"
    override val regex = """^.+$""".toRegex(DOT_MATCHES_ALL)
    override val parse: (String, String) -> String = { it, expectation ->
        if (it.isEmpty()) throw ValidationException(
            it,
            expectation,
            description
        ) else it
    }
    override val required: Boolean = true
}

interface Validator<RAW, out PARSED> {
    val regex: Regex
    val description: String
    val parse: (RAW, String) -> PARSED
    val required: Boolean
}

fun <RAW, PARSED> Validator<RAW, PARSED>.optional(): Validator<RAW, PARSED?> {
    return object : Validator<RAW, PARSED?> {
        override val regex: Regex = this@Validator.regex
        override val description: String = this@Validator.description
        override val parse: (RAW?, String) -> PARSED? = { it, context ->
            it?.let { this@Validator.parse(it, context) }
        }
        override val required: Boolean = false
    }
}

fun <RAW, PARSED> Validator<RAW, PARSED>.optional(defaultIfMissing: () -> RAW): Validator<RAW, PARSED> {
    return object : Validator<RAW, PARSED> {
        override val regex: Regex = this@Validator.regex
        override val description: String = this@Validator.description
        override val parse: (RAW?, String) -> PARSED = { it, context ->
            (it ?: defaultIfMissing()).let { this@Validator.parse(it, context) }
        }
        override val required: Boolean = false
    }
}

class Enum<E : kotlin.Enum<*>>(e: KClass<E>) : Validator<String, E> {
    private val values = e.java.enumConstants.asList().joinToString("|")
    override val description: String = "A string of value: $values"
    override val parse: (String, String) -> E = { it, _ ->
//        it.parseJson(e.java)
        null as E
    }
    override val regex: Regex = "^($values)$".toRegex()
    override val required: Boolean = true
}

inline fun <reified E : kotlin.Enum<*>> enum() = Enum(E::class)
