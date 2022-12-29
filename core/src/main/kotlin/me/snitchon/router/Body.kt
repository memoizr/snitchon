package me.snitchon.router

import me.snitchon.Validator
import kotlin.reflect.KClass
import me.snitchon.endpoint.*
import me.snitchon.parameter.*
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.parsing.Parser

data class Body<T : Any>(val klass: KClass<T>) : Bodied<T, Body<T>> {
    override val type: Class<*>
        get() = klass.java

    override val name: String
        get() = "Body"
    override val pattern: Validator<Any, T>
        get() = TODO()
    override val description: String
        get() = "body of the request"
    override val required: Boolean
        get() = TODO()
    override val emptyAsMissing: Boolean
        get() = TODO()
    override val invalidAsMissing: Boolean
        get() = TODO()
}

object HasBody : Parameter<Nothing, Nothing> {
    override val type: Class<*>
        get() = TODO("Not yet implemented")
    override val name: String
        get() = TODO("Not yet implemented")
    override val description: String
        get() = TODO("Not yet implemented")
    override val pattern: Validator<Nothing, Nothing>
        get() = TODO()
    override val required: Boolean
        get() = TODO()
    override val emptyAsMissing: Boolean
        get() = TODO()
    override val invalidAsMissing: Boolean
        get() = TODO()
}

interface Bodied<T : Any, out A : Body<T>> : Parameter<Any, T> {
    context (Handler<RequestWrapper>, A)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    val body: T
        get() = request.body(type) as T
}
