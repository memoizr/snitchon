package me.snitchon.documentation

import com.snitch.Format
import com.snitch.HttpResponses.format
import com.snitch.HttpResponses.ok
import me.snitchon.endpoint.Endpoint
import me.snitchon.parameter.Header
import me.snitchon.parameter.Parameter
import me.snitchon.parameter.Path
import me.snitchon.parameter.Query
import me.snitchon.parsing.Parser
import me.snitchon.router.Body
import me.snitchon.router.Router
import me.snitchon.router.RouterContext
import me.snitchon.service.RoutedService
import java.io.File
import java.io.FileOutputStream
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.starProjectedType


val <T : Any> Endpoint<T>.headerParams
    get() = this::class
        .memberProperties
        .map { it.call(this) as? Header<*, *> }
        .filterNotNull()

val <T : Any> Endpoint<T>.pathParams
    get() = this::class
        .memberProperties
        .map { it.call(this) as? Path<*, *> }
        .filterNotNull()

val <T : Any> Endpoint<T>.queryParams
    get() = this::class
        .memberProperties
        .map { it.call(this) as? Query<*, *> }
        .filterNotNull()

val <T : Any> Endpoint<T>.bodyParam
    get() = this::class
        .memberProperties
        .map { it.call(this) as? Body<*> }
        .filterNotNull()
        .firstOrNull()
        .let { it ?: Body(Nothing::class) }

context(Parser)
fun RoutedService.generateDocs(): Spec {
    val openApi = OpenApi(info = Info(router.config.title, "1.0"), servers = listOf(Server(router.config.host)))
    return router.endpoints
        .groupBy { it.params.url }
        .map { entry ->
            entry.key to entry.value.foldRight(Path()) { bundle: Endpoint<*>, path ->
                path.withOperation(
                    bundle.params.httpMethod,
                    Operation(
                        tags = bundle.params.url.split("/").drop(1).firstOrNull()?.let { listOf(it) },
                        summary = bundle.params.summary,
                        description = bundle.params.description,
                        responses = emptyMap(),
                        visibility = bundle.params.visibility
                    )
                        .withResponse(ContentType.APPLICATION_JSON, bundle.response, "200")
                        .let {
                            if (bundle.bodyParam.klass != Nothing::class) {
                                it.withRequestBody(ContentType.APPLICATION_JSON, bundle.bodyParam.klass)
                            } else it
                        }
                        .let {
                            bundle.headerParams.fold(it) { acc, p ->
                                acc.withParameter(
                                    Parameters.HeaderParameter(
                                        name = p.name,
                                        required = p.required,
                                        description = getDescription(p),
                                        visibility = p.visibility,
                                        schema = toSchema(p.type.kotlin.starProjectedType)
                                            .withPattern(p.pattern.regex)
                                    )
                                )
                            }
                        }
                        .let {
                            bundle.pathParams.fold(it) { acc, param ->
                                acc.withParameter(
                                    Parameters.PathParameter(
                                        name = param.name,
                                        description = getDescription(param),
                                        schema = toSchema(param.type.kotlin.starProjectedType)
                                            .withPattern(param.pattern.regex)
                                    )
                                )
                            }
                        }
                        .let {
                            bundle.queryParams.fold(it) { acc, p ->
                                acc.withParameter(
                                    Parameters.QueryParameter(
                                        name = p.name,
                                        description = getDescription(p),
                                        allowEmptyValue = p.emptyAsMissing,
                                        required = p.required,
                                        visibility = p.visibility,
                                        schema = toSchema(p.type.kotlin.starProjectedType)
                                            .withPattern(p.pattern.regex)

                                    )
                                )
                            }
                        }
                )
            }
        }.fold(openApi) { a, b -> a.withPath(b.first, b.second) }
        .let { Spec(it.jsonString, router, this) }
}

data class Spec(val spec: String, val router: Router, val routedService: RoutedService) {

    fun writeDocsToStaticFolder() {
        with(RouterContext) {
            with(Router(router.config, "")) {
                routedService.service.registerMethod(GET("/docs").isHandledBy {
                    index.ok.format(Format.TextPlain)
                }, "/docs")
                routedService.service.registerMethod(GET("/spec.json").isHandledBy {
                    spec.ok.format(Format.TextPlain)
                }, "/spec.json")
            }
        }
//        val dest = "tmp/swagger-ui" + "/docs"
//        File(dest).mkdirs()
//        writeToFile(spec, "$dest/spec.json")
//        File("$dest/index.html").writeText(index)
    }
}

private fun getDescription(param: Parameter<*, *>) =
    "${param.description} - ${param.pattern.description}${if (param.invalidAsMissing) " - Invalid as Missing" else ""}${if (param.emptyAsMissing) " - Empty as Missing" else ""}"

internal fun writeToFile(content: String, destination: String) {
    File(destination.split("/").dropLast(1).joinToString("")).apply { if (!exists()) mkdirs() }
    content.byteInputStream().use { input ->
        FileOutputStream(destination).use { output ->
            input.copyTo(output)
        }
    }
}

val index = """
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Swagger UI</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swagger-ui-dist@3.44.0/swagger-ui.css">
    <link rel="icon" type="image/png" href="https://cdn.jsdelivr.net/npm/swagger-ui-dist@3.44.0/favicon-32x32.png" sizes="32x32" />
    <link rel="icon" type="image/png" href="https://cdn.jsdelivr.net/npm/swagger-ui-dist@3.44.0/favicon-32x32.png" sizes="16x16" />
    <style>
      html {
        box-sizing: border-box;
        overflow: -moz-scrollbars-vertical;
        overflow-y: scroll;
      }

      *, *:before, *:after {
        box-sizing: inherit;
      }

      body {
        margin:0;
        background: #fafafa;
      }
    </style>
  </head>

  <body>
    <div id="swagger-ui"></div>

    <script src="https://cdn.jsdelivr.net/npm/swagger-ui-dist@3.44.0/swagger-ui-standalone-preset.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/swagger-ui-dist@3.44.0/swagger-ui-bundle.js"></script>
    <script>
    window.onload = function() {
      // Begin Swagger UI call region
      const ui = SwaggerUIBundle({
        url: "./spec.json",
        dom_id: '#swagger-ui',
        deepLinking: true,
        presets: [
          SwaggerUIBundle.presets.apis,
          SwaggerUIStandalonePreset
        ],
        plugins: [
          SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout"
      });
      // End Swagger UI call region

      window.ui = ui;
    };
  </script>
  </body>
</html>
"""