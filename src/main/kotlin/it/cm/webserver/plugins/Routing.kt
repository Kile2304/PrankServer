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

fun Application.configureRouting() {
    routing {
        authenticate("myauth1") {
            get("/version") {
                call.respond(PRANK_VERSION)
            }
            get("/ping") {
                call.respond(HttpStatusCode.OK)
            }
            post("/shutdown") {
                shutdownMe()
            }
            post("/browse") {
                browse(call.receiveText())
                call.respond(HttpStatusCode.OK)
            }
            post("/popup") {
                val model = call.receive<DialogModel>()
                launch { createPopup(model) }
                call.respondText("OK")
            }
            get("/move_mouse") {
                launch { randomMouseMovement(5) }
                call.respond(HttpStatusCode.OK)
            }
            get("/jumpscare") {
                launch {
                    jumpscareFrame()
                }
                call.respond(HttpStatusCode.OK)
            }
            get("/fart") {
                launch { playSong("/audio/fart.wav") }
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
            post("/notification") {
                val model = call.receive<NotificationModel>()
                launch { notification(model) }
            }
        }
    }
}