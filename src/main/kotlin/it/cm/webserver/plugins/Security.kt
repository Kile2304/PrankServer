package it.cm.webserver.plugins

import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

/**
 * Configures the security settings for the Ktor application.
 *
 * This method sets up basic authentication with a single realm and credentials validation.
 *
 * <p>The basic authentication is configured with the name "myauth1" and the realm "Ktor Server".
 * The validation function checks if the given credentials match the expected values. If the
 * credentials match, a UserIdPrincipal with the provided username is returned. Otherwise, null
 * is returned, indicating that the authentication failed.
 */
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
