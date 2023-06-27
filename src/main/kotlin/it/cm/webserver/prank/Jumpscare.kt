package it.cm.webserver.prank

import it.cm.webserver.util.frameWithImage
import it.cm.webserver.util.playSong
import javax.swing.JFrame

fun jumpscareFrame() {
    playSong(
        "/audio/jumpscare.wav"
        , { frameWithImage("/image/jumpscare.jpg") }
        , { (it as JFrame).dispose() }
    )
}