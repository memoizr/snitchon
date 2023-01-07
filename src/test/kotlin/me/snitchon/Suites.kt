package me.snitchon

import com.snitch.undertow.UndertowRequestWrapper
import com.snitch.undertow.UndertowService
import me.snitchon.http.RequestWrapper
import me.snitchon.parsers.GsonJsonParser
import me.snitchon.spark.SparkRequestWrapper
import me.snitchon.spark.SparkService
import me.snitchon.springboot.SpringRequestWrapper
import me.snitchon.springboot.SpringService
import me.snitchon.tests.ServiceFactory
import me.snitchon.vertx.VertxRequestWrapper
import me.snitchon.vertx.VertxService
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

@Disabled
open class Suite<W: RequestWrapper>(val service: ServiceFactory<W>) {

    @Nested
    @DisplayName("Path")
    inner class PathBuilder : SimplePathBuilderTest<W>(service)

    @Nested
    @DisplayName("Parameters")
    inner class ParamsTest : ParametersTest<W>(service)
}

open class SuiteOfSuites {

    @Nested
    @DisplayName("Spring")
    inner class SpringSuite : Suite<SpringRequestWrapper>({ with(GsonJsonParser) { SpringService(config.copy(port = it)) } })

    @Nested
    @DisplayName("Spark")
    inner class SparkSuite : Suite<SparkRequestWrapper>({ with(GsonJsonParser) { SparkService(config.copy(port = it)) } })

    @Nested
    @DisplayName("Undertow")
    inner class UndertwoSuite : Suite<UndertowRequestWrapper>({ with(GsonJsonParser) { UndertowService(config.copy(port = it)) } })

    @Nested
    @DisplayName("Vertx")
    inner class VertxSuite : Suite<VertxRequestWrapper>({ with(GsonJsonParser) { VertxService(config.copy(port = it)) } })
}

