fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

fun GET(path: String) = Endpoint(HTTPMethod.GET, path)

data class Endpoint(
    val httpMethod: HTTPMethod,
    val url: String
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
