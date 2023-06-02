package me.snitchon.parsers

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import me.snitchon.parsing.Parser
import kotlin.jvm.kotlin
import kotlin.reflect.KClass

import me.snitchon.types.Sealed
import kotlin.reflect.full.createType

//class SealedAdapter : JsonDeserializer<Sealed> {
//    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Sealed {
//        val type = json?.asJsonObject?.get("type")?.jsonString
//        val rawType = TypeToken.get(typeOfT).rawType
//
//        return rawType.kotlin.sealedSubclasses
//            .firstOrNull { it.simpleName == type?.replace("\"", "") }
//            ?.let {
//                Gson().fromJson(json, it.java) as Sealed
//            } ?: Gson().fromJson(json, rawType) as Sealed
//    }
//}
//object SealedDeserializer: JsonDeserializer<Sealed>() {
//    val objectMapper = jacksonObjectMapper()
//
//    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Sealed {
//        val node: JsonNode = p.codec.readTree(p)
//        val type = node.get("type").asText()
//
//        val rawType = ctxt.contextualType.rawClass
//
//
//        return rawType.kotlin.sealedSubclasses
//            .firstOrNull { it.simpleName == type?.replace("\"", "") }
//            ?.let {
//                objectMapper.readValue(p, it.java)
//            } ?: objectMapper.readValue(p, rawt)
//
//    }
//}


class Deadlock {
    class Friend(val name: String) {
        @Synchronized
        fun bow(bower: Friend) {
            Thread.sleep(100)
            println("${Thread.currentThread().name} -- $name: ${bower.name} has bowed to me!")
            bower.bowBack(this)
        }

        @Synchronized
        fun bowBack(bower: Friend) {
            println("bowing back!!")
            println("$name: ${bower.name} has bowed back to me!")
        }

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val alphonse = Friend("Alphonse")
            val gaston = Friend("Gaston")
            Thread { alphonse.bow(gaston) }.start()
            Thread { gaston.bow(alphonse) }.start()
        }
    }
}

class OtherDeadlock {
    fun set(v: () -> Unit) {
        println("hey")
        Thread.sleep(100)
        v()
    }
}

fun main() {

    val a = OtherDeadlock()
    val b = OtherDeadlock()

    Thread { a.set { b.set { println("one")} } }.start()
    Thread { b.set { a.set { println("two")} } }.start()
}
