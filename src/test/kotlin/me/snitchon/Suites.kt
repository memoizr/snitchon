package me.snitchon

import me.snitchon.parsers.GsonJsonParser
import me.snitchon.spark.SparkService
import me.snitchon.springboot.SpringService
import me.snitchon.tests.ServiceFactory
import me.snitchon.vertx.VertxService
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

@Disabled
open class Suite(val service: ServiceFactory) {

    @Nested
    @DisplayName("Path")
    inner class PathBuilder : SimplePathBuilderTest(service)

    @Nested
    @DisplayName("Parameters")
    inner class ParamsTest : ParametersTest(service)
}

open class SuiteOfSuites {
    @Nested
    @DisplayName("Spring")
    inner class SpringSuite : Suite({ with(GsonJsonParser) { SpringService(config.copy(port = it)) } })

    @Nested
    @DisplayName("Spark")
    inner class SparkSuite : Suite({ with(GsonJsonParser) { SparkService(config.copy(port = it)) } })

    @Nested
    @DisplayName("Vertx")
    inner class VertxSuite : Suite({ with(GsonJsonParser) { VertxService(config.copy(port = it)) } })
}

