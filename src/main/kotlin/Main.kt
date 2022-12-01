import me.snitchon.router.RouterContext

context(Paths.id)
fun exampleHandler() {
    println("accessing the parameter called ${id.name}, described as ${id.description}")
}


object Paths {
    object id : PathParameter("foo") {
        val id by ParDelegate()
    }
}

fun main() {
    println("foo")
    with (RouterContext) {
        GET("foo" / Paths.id)
            .isHandledBy {
                exampleHandler()
            }
    }
}

