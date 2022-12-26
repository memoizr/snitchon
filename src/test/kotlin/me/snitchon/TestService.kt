package me.snitchon

import me.snitchon.config.Config
import me.snitchon.config.DocExpansion

val config = Config(
    description = "A test",
    basePath = "/",
    title = "Tunemoji API Documentation",
    port = 3000,
    host = "http://localhost:9999/",
    docPath = "spec",
    docExpansion = DocExpansion.LIST
)
