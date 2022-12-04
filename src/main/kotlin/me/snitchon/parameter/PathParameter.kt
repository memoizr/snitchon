import me.snitchon.http.EndpointCall
import kotlin.reflect.KProperty

interface Parameter {
    val name: String
    val description: String
}

abstract class PathParameter<T>(
    override inline val name: String,
    override inline val description: String = "description"
) : Parameter {

    context(EndpointCall, T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
            operator fun invoke() = parse()

    context(EndpointCall, T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
            fun parse() = request.params(name)
}

abstract class HeaderParameter<T>(
    override inline val name: String,
    override inline val description: String = "description"
) : Parameter {
    context(EndpointCall, T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
            operator fun invoke() = parse()

    context(EndpointCall, T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
    fun parse() = request.headers(name)
}

abstract class QueryParameter<T>(
    override inline val name: String,
    override inline val description: String = "description"
) : Parameter {
    context(EndpointCall, T)
            @Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
            operator fun invoke() = parse()

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
