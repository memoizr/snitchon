package com.snitch

import me.snitchon.parsing.Parser
import kotlin.reflect.KClass
import kotlin.text.RegexOption.*

object NonNegativeInt : Validator<String, Int> {
    override val description = "non negative integer"
    override val regex = """^\d+$""".toRegex()
    override val parse: context(Parser) (String) -> Int = {
        println("=======================")
        println(it)
        it.toInt()
    }
}

object AnyString : Validator<String, String> {
    override val description = "non empty string"
    override val regex = """^.*$""".toRegex(DOT_MATCHES_ALL)
    override val parse: context(Parser) (String) -> String = { it }
}

object NonEmptyString : Validator<String, String> {
    override val description = "non empty string"
    override val regex = """^.+$""".toRegex(DOT_MATCHES_ALL)
    override val parse: context(Parser) (String) -> String = { it }
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

interface Validator<RAW, PARSED> {
    val regex: Regex
    val description: String

    val parse: context(Parser) (RAW) -> PARSED
}

context(Parser)
fun <RAW, PARSED> Validator<RAW, PARSED>.optional(): Validator<RAW?, PARSED?> {
    val prs: context(Parser) (RAW) -> PARSED = parse
    return object : Validator<RAW?, PARSED?> {
        override val regex: Regex = this@Validator.regex
        override val description: String = this@Validator.description
        override val parse: context(Parser) (RAW?) -> PARSED? = {
            it?.let {
                prs(this@Parser, it)
            }
        }
    }
}

class Enum<E : kotlin.Enum<*>>(e: KClass<E>) : Validator<String, E> {
    private val values = e.java.enumConstants.asList().joinToString("|")
    override val description: String = "A string of value: $values"
    override val parse: context(Parser)(String) -> E = {
        it.parseJson(e.java)
    }
    override val regex: Regex = "^($values)$".toRegex()
}

inline fun <reified E : kotlin.Enum<*>> enum() = Enum(E::class)
