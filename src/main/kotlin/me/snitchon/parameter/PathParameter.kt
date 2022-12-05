package me.snitchon.parameter

import com.snitch.AnyString
import com.snitch.Validator
import me.snitchon.documentation.Visibility
import me.snitchon.http.EndpointCall

interface Parameter<RAW, PARSED> {
    val type: Class<*>
    val name: String
    val pattern: Validator<RAW, PARSED>
    val description: String
    val required: Boolean
    val emptyAsMissing: Boolean
    val invalidAsMissing: Boolean
}

abstract class PathParameter<T, PARSED>(
    override inline val name: String,
    override inline val description: String = "description",
    override val pattern: Validator<String, PARSED>,
    override val required: Boolean = false,
    override val emptyAsMissing: Boolean = false,
    override val invalidAsMissing: Boolean = false,
    open val visibility: Visibility = Visibility.PUBLIC
) : Parameter<String, PARSED> {

    override val type: Class<*>
        get() = String::class.java

    context(EndpointCall, T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
            operator fun invoke() = parse()

    context(EndpointCall, T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun parse() = request.params(name)
}
abstract class Header<T>(
    _name: String? = null,
    override inline val description: String = "description",
    override val pattern: Validator<String, String> = AnyString,
    override val required: Boolean = false,
    override val emptyAsMissing: Boolean = false,
    override val invalidAsMissing: Boolean = false,
    visibility: Visibility = Visibility.PUBLIC
): HeaderParameter<T, String>(
    _name,
    description,
    pattern,
    required,
    emptyAsMissing,
    invalidAsMissing,
    visibility
)

abstract class HeaderParameter<T, PARSED>(
    inline val _name: String? = null,
    override inline val description: String = "description",
    override val pattern: Validator<String, PARSED>,
    override val required: Boolean = false,
    override val emptyAsMissing: Boolean = false,
    override val invalidAsMissing: Boolean = false,
    open val visibility: Visibility = Visibility.PUBLIC
) : Parameter<String, PARSED> {
    override val name: String get() = _name ?: javaClass.simpleName

    context(EndpointCall,T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
            operator fun invoke() = parse()

    override val type: Class<*>
        get() = String::class.java

    context(EndpointCall, T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun parse() = request.headers(name)
}

abstract class QueryParameter<T, PARSED>(
    override inline val name: String,
    override inline val description: String = "description",
    override val pattern: Validator<String, PARSED>,
    override val required: Boolean = false,
    override val emptyAsMissing: Boolean = false,
    override val invalidAsMissing: Boolean = false,
    open val visibility: Visibility = Visibility.PUBLIC
) : Parameter<String, PARSED> {
    context(EndpointCall, T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
            operator fun invoke() = parse()

    override val type: Class<*>
        get() = String::class.java

    context(EndpointCall, T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun parse() = request.getParam(this)
}

//abstract class QueryParameter(
//    override inline val name: String,
//    override inline val description: String = "description"
//) : Parameter
//
//class QueryDelegate {
//    operator fun getValue(id: Parameter, property: KProperty<*>): QueryParameter {
//        return object : QueryParameter(property.name, id.description) {}
//    }
//}
