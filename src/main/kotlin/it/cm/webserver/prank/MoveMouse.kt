package it.cm.webserver.prank

import kotlinx.coroutines.delay
import java.awt.Robot
import kotlin.random.Random

/**
 * Moves the mouse cursor randomly within a specified range for a given number of times.
 *
 * @param times the number of times the mouse cursor should be moved
 */
suspend fun randomMouseMovement(times: Int) {
    val random = Random.Default
    for (i in 0..times) {
        val point = java.awt.MouseInfo.getPointerInfo().location

        Robot().mouseMove(
            point.x + random.nextInt(-100, 100)
            , point.y + random.nextInt(-100, 100)
        )
        delay(random.nextLong(100, 500))
    }
}