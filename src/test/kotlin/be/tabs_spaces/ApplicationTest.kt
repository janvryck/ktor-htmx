package be.tabs_spaces

import be.tabs_spaces.infrastructure.inbound.rest.HELLO_WORLD_PATH
import be.tabs_spaces.infrastructure.inbound.rest.RestApplication
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.formUrlEncode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `should say hello by default`() = testApplication {
        application { RestApplication.setup()() }

        val response = client.get(HELLO_WORLD_PATH)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello World!", response.bodyAsText())
    }

    @Test
    fun `should say hallo to me by name`() = testApplication {
        application { RestApplication.setup()() }

        val response = client.post(HELLO_WORLD_PATH) {
            header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(listOf("name" to "Ktor").formUrlEncode())
        }

        response.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello Ktor!", bodyAsText())
        }
    }

    @Test
    fun `should fail to say hallo to me by name`() = testApplication {
        application { RestApplication.setup()() }

        val response = client.post(HELLO_WORLD_PATH) {
            header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(listOf("wrong" to "body").formUrlEncode())
        }

        response.apply {
            assertEquals(HttpStatusCode.BadRequest, status)
        }
    }

}
