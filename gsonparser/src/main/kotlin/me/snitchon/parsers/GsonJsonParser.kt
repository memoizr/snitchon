package me.snitchon.parsers

import com.google.gson.*
import com.google.gson.internal.bind.TypeAdapters
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import me.snitchon.parsers.GsonJsonParser.jsonString
import me.snitchon.parsers.GsonJsonParser.parseJson
import me.snitchon.parsing.Parser
import me.snitchon.types.Sealed
import java.lang.reflect.Type
import java.text.SimpleDateFormat

class SealedAdapter : JsonDeserializer<Sealed> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Sealed {
        val type = json?.asJsonObject?.get("type")?.jsonString
        val rawType = TypeToken.get(typeOfT).rawType

        return rawType.kotlin.sealedSubclasses
            .firstOrNull { it.simpleName == type?.replace("\"", "") }
            ?.let {
                Gson().fromJson(json, it.java) as Sealed
            } ?: Gson().fromJson(json, rawType) as Sealed
    }
}


object GsonJsonParser : Parser {
    val builder = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        .registerTypeHierarchyAdapter(Sealed::class.java, SealedAdapter())
    private val gson = builder.create()

    override val Any.jsonString get() = gson.toJson(this)

    override val Any.jsonByteArray: ByteArray
        get() = TODO("Not yet implemented")

    override fun <T : Any> String.parseJson(klass: Class<T>): T {
        return gson.fromJson(this, klass)
    }

    override fun <T : Any> ByteArray.parseJson(klass: Class<T>): T = TODO()
}