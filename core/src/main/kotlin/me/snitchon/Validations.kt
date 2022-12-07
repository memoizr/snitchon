package com.snitch

import kotlin.reflect.KClass
import kotlin.text.RegexOption.*

object NonNegativeInt : Validator<String, Int> {
    override val description = "non negative integer"
    override val regex = """^\d+$""".toRegex()
    override val parse: (String) -> Int = {
        println("=======================")
        println(it)
        it.toInt()
    }
}

object AnyString : Validator<String, String> {
    override val description = "non empty string"
    override val regex = """^.*$""".toRegex(DOT_MATCHES_ALL)
    override val parse: (String) -> String = { it }
}

object NonEmptyString : Validator<String, String> {
    override val description = "non empty string"
    override val regex = """^.+$""".toRegex(DOT_MATCHES_ALL)
    override val parse: (String) -> String = { it }
}

//object NonEmptySingleLineString : Validator<String, String> {
//    override val description = "non empty single-line string"
//    override val regex = """^.+$""".toRegex()
//    override val parse: (String) -> String = { it }
//}
//
//object NonEmptyStringSet : Validator<String, Set<String>> {
//    override val description = "non empty string set"
//    override val regex = """^(.+,?)*.+$""".toRegex()
//    override val parse: (String) -> Set<String> = { it.split(",").toSet() }
//}
//
//object StringSet : Validator<String, Set<String>> {
//    override val description = "string set"
//    override val regex = """.*""".toRegex()
//    override val parse: context(Parser, String)() -> Set<String> = { it.split(",").toSet() }
//}

interface Validator<RAW, out PARSED> {
    val regex: Regex
    val description: String

    val parse: (RAW) -> PARSED
}

fun <RAW, PARSED> Validator<RAW, PARSED>.optional(): Validator<RAW, PARSED?> {
    return object : Validator<RAW, PARSED?> {
        override val regex: Regex = this@Validator.regex
        override val description: String = this@Validator.description
        override val parse: (RAW?) -> PARSED? = {
            it?.let { this@Validator.parse(it) }
        }
    }
}

fun <RAW, PARSED> Validator<RAW, PARSED>.optional(defaultIfMissing: () -> RAW): Validator<RAW, PARSED> {
    return object : Validator<RAW, PARSED> {
        override val regex: Regex = this@Validator.regex
        override val description: String = this@Validator.description
        override val parse: (RAW?) -> PARSED = {
            (it ?: defaultIfMissing()).let { this@Validator.parse(it) }
        }
    }
}

class Enum<E : kotlin.Enum<*>>(e: KClass<E>) : Validator<String, E> {
    private val values = e.java.enumConstants.asList().joinToString("|")
    override val description: String = "A string of value: $values"
    override val parse: (String) -> E = {
//        it.parseJson(e.java)
        null as E
    }
    override val regex: Regex = "^($values)$".toRegex()
}

inline fun <reified E : kotlin.Enum<*>> enum() = Enum(E::class)
