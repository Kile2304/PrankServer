package webserver.cm.it.prank

import webserver.cm.it.util.frameWithImage
import webserver.cm.it.util.playSong
import javax.swing.JFrame

fun jumpscareFrame() {
    playSong(
        "/audio/jumpscare.wav"
        , { frameWithImage("/image/jumpscare.jpg") }
        , { (it as JFrame).dispose() }
    )
}