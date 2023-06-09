package me.snitchon.path

import me.snitchon.Validator
import me.snitchon.documentation.Visibility
import me.snitchon.http.Handler
import me.snitchon.http.RequestWrapper
import me.snitchon.parameter.ParameterMarkupDecorator
import me.snitchon.parameter.Parameter


abstract class Path<PARSED>(
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
}


