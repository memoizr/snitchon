package me.snitchon.parameter

import me.snitchon.Validator
import me.snitchon.documentation.Visibility
import me.snitchon.http.Handler

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
    fun parse(): PARSED = request.parseParam(this)
}