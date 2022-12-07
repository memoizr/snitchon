package me.snitchon.parameter

import com.snitch.Validator
import me.snitchon.documentation.Visibility
import me.snitchon.http.Handler

interface Parameter<RAW : Any?, out PARSED : Any?> {
    val type: Class<*>
    val name: String
    val pattern: Validator<RAW, PARSED>
    val description: String
    val required: Boolean
    val emptyAsMissing: Boolean
    val invalidAsMissing: Boolean
}

abstract class Path<T, PARSED>(
    override inline val name: String,
    override inline val description: String = "description",
    override val pattern: Validator<String, PARSED>,
    override val required: Boolean = false,
    override val emptyAsMissing: Boolean = false,
    override val invalidAsMissing: Boolean = false,
    open val visibility: Visibility = Visibility.PUBLIC,
) : Parameter<String, PARSED> {

    override val type: Class<*>
        get() = String::class.java

    context(Handler, T)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    operator fun invoke() = parse()

    context(Handler, T)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun parse(): PARSED = request.getParam(this)
}


abstract class Header<T, PARSED>(
    override val pattern: Validator<String, PARSED>,
    inline val _name: String? = null,
    override val description: String = "description",
    override val emptyAsMissing: Boolean = false,
    override val invalidAsMissing: Boolean = false,
    open val visibility: Visibility = Visibility.PUBLIC,
) : Parameter<String, PARSED> {

    override val type: Class<*>
        get() = String::class.java

    override val name: String get() = _name ?: javaClass.simpleName

    override val required: Boolean = true


    context(Handler, T)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    operator fun invoke(): PARSED = parse()

    context(Handler, T)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun parse(): PARSED = request.getParam(this)
}

//abstract class OptionalHeaderParameter<T, PARSED>(
//    inline val _name: String? = null,
//    override inline val description: String = "description",
//    override val pattern: Validator<String?, PARSED?>,
//    override val emptyAsMissing: Boolean = false,
//    override val invalidAsMissing: Boolean = false,
//    open val visibility: Visibility = Visibility.PUBLIC,
//    val default: (() -> String)? = null
//) : Parameter<String?, PARSED?> {
//
//    override val type: Class<*>
//        get() = String::class.java
//
//    override val name: String get() = _name ?: javaClass.simpleName
//
//    override val required: Boolean = false
//
//
//    context(Handler, T)
//    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//    operator fun invoke(): PARSED? = parse()
//
//    context(Handler, T)
//    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
//    fun parse(): PARSED? = request.getParam(this)
//}

abstract class Query<T, PARSED>(
    override val pattern: Validator<String, PARSED>,
    inline val _name: String? = null,
    override val description: String = "description",
    override val emptyAsMissing: Boolean = false,
    override val invalidAsMissing: Boolean = false,
    open val visibility: Visibility = Visibility.PUBLIC,

    ) : Parameter<String, PARSED> {

    override val name: String get() = _name ?: javaClass.simpleName

    override val type: Class<*>
        get() = String::class.java

    override val required: Boolean = true


    context(Handler, T)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    operator fun invoke() = parse()

    context(Handler, T)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun parse(): PARSED = request.getParam(this)
}

