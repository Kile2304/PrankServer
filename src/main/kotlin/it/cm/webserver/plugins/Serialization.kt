package it.cm.webserver.plugins

import io.ktor.serialization.gson.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*

/**
 * Configures serialization for the application.
 *
 * This method installs the `ContentNegotiation` feature with the `gson` serializer.
 * It enables the application to automatically convert objects to JSON and vice versa.
 */
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson()
    }
}
