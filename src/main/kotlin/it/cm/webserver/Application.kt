package it.cm.webserver

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import it.cm.webserver.plugins.configureRouting
import it.cm.webserver.plugins.configureSecurity
import it.cm.webserver.plugins.configureSerialization
import it.cm.webserver.util.isFirewallPortBlocked
import java.net.BindException
import javax.swing.UIManager


/**
 * The App class represents the main application.
 */
class App

/**
 * The current version of the PRANK software.
 *
 * This variable holds the version string of the PRANK software.
 * It follows the semantic versioning convention (MAJOR.MINOR.PATCH).
 * The version string may also include a suffix indicating additional information like "SNAPSHOT".
 *
 * Example usage:
 *     val version = PRANK_VERSION
 *
 * Note: This variable should not be modified directly.
 */
const val PRANK_VERSION = "1.3-SNAPSHOT"

/**
 * The main method is the entry point of the application. It sets the system look and feel
 * for the user interface manager and starts an embedded server on one of the specified ports.
 * The server is configured to listen on all available network interfaces.
 *
 * @throws BindException if the server fails to bind to any of the specified ports.
 */
fun main() {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

    for (port in listOf(6050, 6040, 6060, 5050, 5040, 5060))
        try {
            embeddedServer(
                Netty, port = port, host = "0.0.0.0", module = Application::module
            ).start(wait = true)
            break
        } catch (_: BindException) { }
}

/**
 * Configures the application module.
 * This method initializes the necessary settings for the application module, including security, serialization, and routing.
 *
 * Usage:
 * ```
 * Application.module()
 * ```
 *
 * @see configureSecurity
 * @see configureSerialization
 * @see configureRouting
 */
fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureRouting()
}
