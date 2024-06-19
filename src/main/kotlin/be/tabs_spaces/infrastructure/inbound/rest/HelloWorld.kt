package be.tabs_spaces.infrastructure.inbound.rest

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.server.util.getOrFail

const val HELLO_WORLD_PATH = "/hello"

fun Application.helloWorldController() {
    routing {
        get(HELLO_WORLD_PATH) {
            call.respondText("Hello World!")
        }
        post(HELLO_WORLD_PATH) {
            call.receiveParameters()
                .getOrFail("name")
                .let { name ->
                    call.respondText("Hello $name!")
                }
        }
    }
}
