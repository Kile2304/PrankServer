package it.cm.webserver.plugins

import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureSecurity() {
    authentication {
        basic(name = "myauth1") {
            realm = "Ktor Server"
            validate { credentials ->
                if (credentials.name == "CriStian0" && credentials.password == "IntellijF4N")
                    UserIdPrincipal(credentials.name)
                else
                    null
            }
        }
    }
}
