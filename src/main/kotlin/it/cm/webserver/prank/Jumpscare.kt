package it.cm.webserver.prank

import it.cm.webserver.util.frameWithImage
import it.cm.webserver.util.playSong
import javax.swing.JFrame

/**
 * Plays a jumpscare audio and displays a frame with a jumpscare image.
 * Once the audio finishes playing, the frame is automatically closed.
 */
fun jumpscareFrame() {
    playSong(
        "/audio/jumpscare.wav"
        , { frameWithImage("/image/jumpscare.jpg") }
        , { (it as JFrame).dispose() }
    )
}