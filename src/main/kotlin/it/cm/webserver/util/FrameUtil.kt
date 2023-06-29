package it.cm.webserver.util

import it.cm.webserver.App
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

/**
 * Creates and returns a `JFrame` object with an image set as its content.
 *
 * @param imagePath the relative path of the image file
 * @return the created `JFrame` object
 */
fun frameWithImage(imagePath: String): JFrame = frameWithImage(ImageIcon(App::class.java.getResource(imagePath)))

/**
 * Creates a new JFrame with the given image as its content, and applies some settings.
 *
 * @param image The ImageIcon to be set as the content of the frame.
 * @return The newly created JFrame with the specified image as its content.
 */
fun frameWithImage(image: ImageIcon): JFrame =
    JFrame().apply {
        val current = this
        isUndecorated = true
        extendedState = JFrame.MAXIMIZED_BOTH
//        size = Dimension(200, 200)
//                        contentPane.background = Color.BLUE
        isAlwaysOnTop = true
        layout = GridLayout()

        contentPane.add(
            JLabel(image).apply {
                setSize(current.width, current.height)
            }
        )

        isVisible = true
    }