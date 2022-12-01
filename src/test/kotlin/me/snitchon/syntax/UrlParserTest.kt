package me.snitchon.syntax

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UrlParserTest {
    @Test
    fun `parses path parameters`() {
        val result = "/foo/:bar/baz".parse("/foo/123/baz")["bar"]

        assertEquals("123", result)
    }

    @Test
    fun `matches path parameters`() {
        assertTrue("/foo/:bar/baz".match("/foo/hey/baz"))
        assertTrue("/foo/:bar/baz".match("/foo/123/baz"))
        assertTrue("/foo/:bar/baz".match("/foo/123/baz/"))
        assertTrue("/foo/:bar/baz/".match("/foo/123/baz/"))
        assertTrue("/foo/:bar/baz/".match("/foo/123/baz"))

        assertFalse("/foo/:bar/baz".match("/foo/hey/baz/bar"))
        assertFalse("/foo/:bar/baz".match("/foo/hey"))
        assertFalse("/foo/:bar/baz".match("/foo"))
    }
}