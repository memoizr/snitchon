package me.snitchon.syntax

import ParDelegate
import PathParameter
import QueryDelegate
import QueryParameter
import me.snitchon.router.RouterContext
import org.junit.jupiter.api.Test

context(Paths.id)
fun exampleHandler() {
    println("accessing the parameter called ${id.name}, described as ${id.description}")
}


object Paths {
    object id : PathParameter("id") {
        val id by ParDelegate()
    }

    object user: PathParameter("user") {
        val user by ParDelegate()
    }
}

object Queries {
    class Page : QueryParameter("user") {
        val page by QueryDelegate()
    }
}

class SyntaxTest {
    @Test
    fun `supports 0 path parameters`() {
//        with(RouterContext) {
//            GET("foo").isHandledBy { }
//        }
    }

    @Test
    fun `supports 1 path parameter`() {
        with(RouterContext) {
            GET("foo" / Paths.id).isHandledBy { id }
            GET("foo" / Paths.id / "bar" / "baz").isHandledBy { id }
        }
    }

    @Test
    fun `supports 2 path parameters`() {
        with(RouterContext) {
            GET("foo" / Paths.id / "meh" / Paths.user).isHandledBy { id; user }
        }
    }

    @Test
    fun `supports path parameters and query parameters`() {
        with(RouterContext) {
            GET("foo" / Paths.id / "meh" / Paths.user)
                .query(Queries.Page())
                .isHandledBy { id; user; page }
        }
    }
}