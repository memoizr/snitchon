package me.snitchon.parameter

import com.snitch.Validator
import me.snitchon.documentation.Visibility
import me.snitchon.http.Handler
import me.snitchon.router.Body


abstract class Path<T, PARSED>(
    override inline val name: String,
    override inline val description: String = "description",
    override val pattern: Validator<String, PARSED>,
    override val required: Boolean = false,
    override val emptyAsMissing: Boolean = false,
    override val invalidAsMissing: Boolean = false,
    open val visibility: Visibility = Visibility.PUBLIC,
) : Parameter<String, PARSED> {

    context(Markup)
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


