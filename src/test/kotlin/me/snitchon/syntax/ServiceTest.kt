package me.snitchon.syntax

import me.snitchon.http.HTTPMethod
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ServiceTest {

    @Test
    fun `calls the function`() {
        val service = TestSnitchService()

    service.setRoutes {
            GET("/foo").isHandledBy { "hey" }
        }.startListening()

        val response = service.makeRequest(TestRequest(HTTPMethod.GET, "/foo"))

        assertEquals("hey", response)
    }
}



