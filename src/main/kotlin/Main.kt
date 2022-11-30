import kotlin.reflect.KProperty

fun main() {
    println("foo")
    with (RouterContext) {
        GET("foo" / id)
            .isHandledBy {
                exampleHandler()
            }
    }
}

data class Endpoint1<A: Par, BODY>(
    val httpMethod: HTTPMethod,
    val url: String,
    inline val a: A,
) {
    fun isHandledBy(block: A.() -> Unit) {
        block(a)
    }
}

context(id)
fun exampleHandler() {
    println("accessing the parameter called ${example.name}, described as ${example.description}")
}

abstract class Par(
    inline val name: String,
    inline val description: String = "description"
)

object id: Par("foo") {
    val example by ParDelegate()
}

class ParDelegate {
    operator fun getValue(id: id, property: KProperty<*>): Par {
        return object: Par(property.name, id.description) {}
    }
}


object NonEmptyString : Validator<String, String> {
    override val description = "non empty string"
    override val regex = """^.+$""".toRegex(RegexOption.DOT_MATCHES_ALL)
    override val parse: (String) -> String = { it }
}

inline fun <reified T, R> path(name: String, description: String = "", condition: Validator<T,R>) = PathParam(
    T::class.java,
    name,
    condition,
    description)

object RouterContext {
    fun GET(path: String) = Endpoint<Nothing>(HTTPMethod.GET, path, emptySet())
    fun <A: Par> GET(path: ParametrizedPath1<A>) = Endpoint1<A, Nothing>(HTTPMethod.GET, path.path, path.a)

    operator fun String.div(path: String) = this.leadingSlash + "/" + path
    operator fun <P: Par>String.div(path: P) = ParametrizedPath1(this, path)

    private val String.leadingSlash get() = if (!startsWith("/")) "/$this" else this

    inline fun <B : Any, reified T : Any> Endpoint<B>.isHandledBy(
//        noinline block: RequestHandler<B>.() -> HttpResponse<T>
    ) {
    }
}
data class ParametrizedPath1<A: Par>(val path: String, val a: A) {
}

data class ParametrizedPath(val path: String, val pathParameters: Set<PathParam<out Any, out Any>>) {
    operator fun div(path: String) = copy(path = this.path + "/" + path)
    operator fun div(path: PathParam<out Any, out Any>) = copy(path = this.path + "/" + "{${path.name}}", pathParameters = pathParameters + path)
}


data class PathParam<T, R>(
    override val type: Class<*>,
    override val name: String,
    override val pattern: Validator<T, R>,
    override val description: String
) : Parameter<T, R>(type, name, pattern, description, true, false) {
//    operator fun div(path: String) = this.path + "/" + path
}

sealed class Parameter<T, R>(
    open val type: Class<*>,
    open val name: String,
    open val pattern: Validator<T, R>,
    open val description: String,
    open val required: Boolean = false,
    open val emptyAsMissing: Boolean = false,
    open val invalidAsMissing: Boolean = false
)

interface Validator<T, R> {
    val regex: Regex
    val description: String

    val parse: (String) -> R

    fun optional(): Validator<T?, R?> = this as Validator<T?, R?>
}

data class Endpoint<BODY>(
    val httpMethod: HTTPMethod,
    val url: String,
    val pathParams: Set<PathParam<out Any, *>>,
)

enum class HTTPMethod {
    GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD;

    companion object {
        fun fromString(method: String) = when (method) {
            DELETE.name -> DELETE
            GET.name -> GET
            PUT.name -> PUT
            POST.name -> POST
            OPTIONS.name -> OPTIONS
            HEAD.name -> HEAD
            PATCH.name -> PATCH
            else -> throw IllegalArgumentException(method)
        }
    }
}