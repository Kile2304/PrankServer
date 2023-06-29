package it.cm.webserver.prank

import kotlinx.coroutines.delay
import javax.swing.JFrame

/**
 * Creates and displays an invisible frame.
 *
 * The method creates a new instance of `JFrame`, sets its properties to make it invisible, maximized and undecorated,
 * displays the frame, waits for 10 seconds, and then disposes the frame.
 *
 * @return A suspended function that creates and displays an invisible frame.
 */
suspend fun invisibleFrame(time: Long) = JFrame().apply {
    isUndecorated = true
    opacity = 0f
    extendedState = JFrame.MAXIMIZED_BOTH
    isVisible = true
    delay(time)
    dispose()
}