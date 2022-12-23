package me.snitchon

import me.snitchon.parameter.Query
import me.snitchon.path.Path
import me.snitchon.springboot.SpringService

object query : Query<query, String?>(NonEmptyString.optional())
object id : Path<id, String>(pattern = NonEmptyString)

fun main(args: Array<String>) {
    SpringService()
        .withRoutes {  ->
            GET("hello" / id / "world")
                .with(query)
                .isHandledBy {
                    "foobar, id= ${id()} qq = ${query()}".ok
                }
        }.startListening()
}

