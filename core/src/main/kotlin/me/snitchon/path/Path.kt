package me.snitchon.path

import me.snitchon.Validator
import me.snitchon.documentation.Visibility
import me.snitchon.http.Handler
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parameter.Parameter


abstract class Path<T, PARSED>(
    inline val _name: String? = null,
    override inline val description: String = "description",
    override val pattern: Validator<String, PARSED>,
    override val required: Boolean = false,
    override val emptyAsMissing: Boolean = false,
    override val invalidAsMissing: Boolean = false,
    open val visibility: Visibility = Visibility.PUBLIC,
) : Parameter<String, PARSED> {
    override val name: String by lazy { _name ?: this.javaClass.simpleName }

    context(ParameterMarkupDecorator)
    val markupName
        get() = decorate(name)

    override val type: Class<*>
        get() = String::class.java

    context(Handler, T)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    operator fun invoke() = parse()

    context(Handler, T)
    @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun parse(): PARSED = request.parseParam(this)
}


