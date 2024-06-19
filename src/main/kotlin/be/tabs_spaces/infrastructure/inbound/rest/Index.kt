import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.InputType
import kotlinx.html.body
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.script
import kotlinx.html.title

const val INDEX_PATH = "/"

fun Application.indexController() {
    routing {
        get(INDEX_PATH) {
            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title {
                        +"Ktor + HTMX"
                    }
                    script(type = "text/javascript", src = "https://unpkg.com/htmx.org@2.0.0") {}
                }
                body {
                    h1 {
                        +"Ktor + HTMX"
                    }
                    button {
                        attributes += mapOf(
                            "hx-get" to "/hello",
                            "hx-target" to "#hello",
                        )
                        +"Click me"
                    }
                    div {
                        id = "hello"
                    }
                    form {
                        attributes += mapOf(
                            "hx-post" to "/hello",
                            "hx-target" to "#hello-you",
                        )
                        input {
                            type = InputType.text
                            name = "name"
                            placeholder = "What is your name?"
                        }
                        button {
                            +"is my name"
                        }
                    }
                    div {
                        id = "hello-you"
                    }
                }
            }
        }
    }
}
