package me.snitchon

import com.memoizr.assertk.expect
import me.snitchon.endpoint.withBody
import me.snitchon.http.RequestWrapper
import me.snitchon.router.marker
import me.snitchon.tests.ServiceFactory
import me.snitchon.tests.SnitchTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
open class FormatTest<W: RequestWrapper>(service: ServiceFactory<W>) : SnitchTest<W>(service) {
    data class TheBody(val yo: String, val bar: Int)
    data class TheResponse(val value: String)

    @BeforeEach
    fun before() {
        routes {
            PUT("json")
                .withBody(marker<TheBody>())
                .isHandledBy {
                    TheResponse("ok, body: noo").ok
                }
//        GET("bytearray").isHandledBy { "ok".ok.format(Format.VideoMP4) }
//        GET("image").isHandledBy { val readBytes = File("./squat.jpg").readBytes()
//            readBytes.size.print()
//            readBytes.ok.format(Format.ImageJpeg) }
        }
    }

    @Test
    fun `returns correct format`() {
        whenPerform Put "/json" withBody TheBody("hello", 3) expect {
            expect that it.body() isEqualTo """{"value":"ok, body: noo"}"""
        }
    }

//    @Test
//    fun `preserves binary formats`() {
//        whenPerform GET "/$root/image" expect {
//            expect that it.headers().allValues("Content-Type").first() isEqualTo "image/jpeg"
//        }
//    }
}
