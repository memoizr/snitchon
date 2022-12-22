package me.snitchon

import me.snitchon.spark.SparkService
import me.snitchon.tests.ServiceFactory
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

open class Suite(val service: ServiceFactory) {

    @Nested
    @DisplayName("Path")
    inner class PathBuilder : SimplePathBuilderTest(service)

    @Nested
    @DisplayName("Parameters")
    inner class ParamsTest : ParametersTest(service)
}

class ChildSuite: Suite({ SparkService(config.copy(port = it))  })

