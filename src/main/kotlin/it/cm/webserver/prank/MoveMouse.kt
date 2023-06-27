package it.cm.webserver.prank

import kotlinx.coroutines.delay
import java.awt.Robot
import kotlin.random.Random

suspend fun randomMouseMovement(times: Int) {
    val random = Random.Default
    for (i in 0..times) {
        Robot().mouseMove(
            random.nextInt(1920), random.nextInt(1080)
        )
        delay(random.nextLong(3000) + 3000)
    }
}