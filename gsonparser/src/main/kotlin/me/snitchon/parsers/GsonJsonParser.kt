package me.snitchon.parsers

import com.google.gson.Gson
import me.snitchon.parsing.Parser

object GsonJsonParser: Parser {
    private val gson = Gson()
    override val Any.jsonString get() = gson.toJson(this)

    override val Any.jsonByteArray: ByteArray
        get() = TODO("Not yet implemented")

    override fun <T : Any> String.parseJson(klass: Class<T>): T = gson.fromJson(this, klass)

    override fun <T : Any> ByteArray.parseJson(klass: Class<T>): T = TODO()
}