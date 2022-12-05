import kotlin.reflect.KProperty

//context(Paths.id)
fun exampleHandler() {
//    println("accessing the parameter called ${id.name}, described as ${id.description}")
}


//object Paths {
//    context(RequestWrapper)
//    object id : PathParameter() {
//        val id by yo()
//    }
//}

object Bar: Goo()

abstract class Goo {
    val name by lazy {
        println("memoized?")
        this.javaClass.simpleName}
}

class Noo {
    operator fun getValue(id: Any, property: KProperty<*>): String {
        println("memoized?")
        return id.javaClass.simpleName
    }
}


fun main() {

    val x = object : Any() {}

    println(x)


//    with (RouterContext) {
//        GET("foo" / Paths.id)
//            .isHandledBy {
//                exampleHandler()
//            }
//    }
}

