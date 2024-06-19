package be.tabs_spaces.infrastructure.inbound.rest

import io.ktor.server.application.Application

object RestApplication {
    fun setup(): Application.() -> Unit = {
        helloWorldController()
    }
}
