import kotlin.reflect.KProperty

abstract class PathParameter(
    inline val name: String,
    inline val description: String = "description"
)

class ParDelegate {
    operator fun getValue(id: PathParameter, property: KProperty<*>): PathParameter {
        return object: PathParameter(property.name, id.description) {}
    }
}
