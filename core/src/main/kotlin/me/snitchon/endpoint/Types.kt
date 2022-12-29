package me.snitchon.endpoint

import me.snitchon.http.HttpResponse
import me.snitchon.http.RequestWrapper
import me.snitchon.http.ResponseWrapper
import me.snitchon.parameter.Header
import me.snitchon.parameter.Query

internal typealias HP = Header<*, *>
internal typealias QP = Query<*, *>

object OnlyHeader
object OnlyQuery

typealias After<W, T> = (W) -> HttpResponse<T>
