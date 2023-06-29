package it.cm.webserver.prank

import kotlinx.coroutines.delay

/**
 * Types random characters for a given duration of time using the specified keys.
 *
 * @param time The duration of time, in milliseconds, for which the method will type random characters.
 * @param keys The set of keys from which the method will select random characters to type.
 */
suspend fun randomTyper(time: Long, keys: String) {
    val robot = java.awt.Robot()
    val random = kotlin.random.Random.Default

    val endTime = System.currentTimeMillis() + time

    while (System.currentTimeMillis() < endTime) {
        val randomKey = keys.random()
        val keyCode = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(randomKey.toInt())

        robot.keyPress(keyCode)
        delay(random.nextLong(50, 200))  // Simulate variable typing speed
        robot.keyRelease(keyCode)
        delay(random.nextLong(1000, 3000))  // Wait a bit before typing the next character
    }
}