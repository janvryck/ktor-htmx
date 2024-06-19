package be.tabs_spaces

import be.tabs_spaces.infrastructure.inbound.rest.RestApplication
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer

fun main() {
    embeddedServer(
        factory = CIO,
        port = 8080,
        host = "0.0.0.0",
        module = RestApplication.setup()
    ).start(wait = true)
}


