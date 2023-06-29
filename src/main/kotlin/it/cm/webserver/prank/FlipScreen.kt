package it.cm.webserver.prank

import it.cm.webserver.util.frameWithImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import javax.swing.JFrame

/**
 * Suspends the current execution and flips the screen by capturing a screenshot, flipping the image horizontally,
 * and displaying it in a new JFrame window.
 */
suspend fun flipScreen(time: Long) = JFrame().apply {
    val screenshot = captureScreenshot()
    val flippedScreenshot = flipImage(screenshot)
    frameWithImage(ImageIcon(flippedScreenshot)).apply {
        delay(time)
        dispose()
    }
}

/**
 * Captures a screenshot of the current screen.
 *
 * @return A [BufferedImage] representing the captured screenshot.
 *
 * @throws AWTException if the platform configuration does not allow low-level input control.
 */
suspend fun captureScreenshot(): BufferedImage {
    return withContext(Dispatchers.IO) { // Lanciare su un thread IO perché potrebbe essere un'operazione pesante
        val screenRect = Rectangle(Toolkit.getDefaultToolkit().screenSize)
        val captureRobot = Robot()
        captureRobot.createScreenCapture(screenRect)
    }
}

/**
 * Flips the given image horizontally.
 *
 * @param image the image to be flipped
 * @return the flipped image
 */
suspend fun flipImage(image: BufferedImage): BufferedImage {
    return withContext(Dispatchers.IO) { // Lanciare su un thread IO perché potrebbe essere un'operazione pesante
        val tx = AffineTransform.getScaleInstance(-1.0, 1.0) // Questo trasformerà l'asse X
        tx.translate(-image.getWidth(null).toDouble(), 0.0)
        val op = AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR)
        op.filter(image, null)
    }
}