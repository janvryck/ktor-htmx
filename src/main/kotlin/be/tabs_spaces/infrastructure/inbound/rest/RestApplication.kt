package be.tabs_spaces.infrastructure.inbound.rest

import indexController
import io.ktor.server.application.Application

object RestApplication {
    fun setup(): Application.() -> Unit = {
        indexController()
        helloWorldController()
    }
}
