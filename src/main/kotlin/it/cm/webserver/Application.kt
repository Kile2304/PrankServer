package it.cm.webserver

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import it.cm.webserver.plugins.configureRouting
import it.cm.webserver.plugins.configureSecurity
import it.cm.webserver.plugins.configureSerialization
import java.net.BindException
import javax.swing.UIManager


class App

val PRANK_VERSION = "1.2-SNAPSHOT"

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

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureRouting()
}
