import me.snitchon.http.RequestWrapper
import kotlin.reflect.KProperty

interface Parameter {
    val name: String
    val description: String

}

abstract class PathParameter(
    inline val _name: String? = null,
    override inline val description: String = "description"
): Parameter {
    override val name: String by lazy { _name ?: this.javaClass.simpleName}
    context(RequestWrapper)
    fun yo() = params(name)
    context(RequestWrapper)
    operator fun invoke() = params(name)
}

//class ParDelegate<T> {
//    context(RequestWrapper)
//    operator fun getValue(parameter: Parameter, property: KProperty<*>): String? {
//        return
//    }
//}

abstract class QueryParameter(
    inline val _name: String? = null,
    override inline val description: String = "description"
): Parameter {
    override val name: String by lazy { _name ?: this.javaClass.simpleName}
}

class QueryDelegate {
    operator fun getValue(id: Parameter, property: KProperty<*>): QueryParameter {
        return object: QueryParameter(property.name, id.description) {}
    }
}
