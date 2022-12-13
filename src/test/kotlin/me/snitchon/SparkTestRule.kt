package me.snitchon

import com.snitch.spark.SparkService
import me.snitchon.config.Config
import me.snitchon.config.DocExpansion
import me.snitchon.router.Router
import me.snitchon.router.RouterContext
import me.snitchon.service.SnitchService
import me.snitchon.syntax.GsonJsonParser
import org.junit.rules.ExternalResource
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.net.BindException
import java.net.ConnectException

val config = Config(description = "A test",
        basePath = "/",
        title = "Tunemoji API Documentation",
        port = 3000,
        host = "http://localhost:3000/",
        docPath = "spec",
        docExpansion = DocExpansion.LIST
)


open class SparkTestRule(port: Int, val router: context(RouterContext, SnitchService) Router.() -> Unit) : ExternalResource() {
        val server = SparkService(config.copy(port = port))

        override fun apply(base: Statement, description: Description): Statement {
                return object : Statement() {
                        override fun evaluate() {
                                before()
                                fun go() {
                                        try {
                                                base.evaluate()
                                        } catch (b: BindException) {
                                                go()
                                        } catch (e: ConnectException) {
                                                go()
                                        } finally {
                                                after()
                                        }
                                }
                                go()
                        }
                }
        }

        override fun before() {
                with(GsonJsonParser) {
                        server.setRoutes(router).startListening().handleInvalidParams()
                }

        }
}