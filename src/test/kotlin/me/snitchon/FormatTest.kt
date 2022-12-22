package me.snitchon

import com.memoizr.assertk.expect
import me.snitchon.tests.SnitchTest
import org.junit.Rule
import org.junit.Test

class FormatTest : SnitchTest() {
    data class TheBody(val yo: String, val bar: Int)
    data class TheResponse(val value: String)

    @Rule
    @JvmField val rule = TestService {
        PUT("json")
            .with(body<TheBody>())
            .isHandledBy {
                TheResponse("ok, body: noo").ok
            }
//        GET("bytearray").isHandledBy { "ok".ok.format(Format.VideoMP4) }
//        GET("image").isHandledBy { val readBytes = File("./squat.jpg").readBytes()
//            readBytes.size.print()
//            readBytes.ok.format(Format.ImageJpeg) }
    }

    @Test
    fun `returns correct format`() {
        whenPerform Put "/json" withBody TheBody("hello", 3) expect {
            expect that it.body() isEqualTo "ok"
        }
    }

//    @Test
//    fun `preserves binary formats`() {
//        whenPerform GET "/$root/image" expect {
//            expect that it.headers().allValues("Content-Type").first() isEqualTo "image/jpeg"
//        }
//    }
}
