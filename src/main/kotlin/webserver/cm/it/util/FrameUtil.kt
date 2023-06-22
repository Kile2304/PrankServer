package webserver.cm.it.util

import webserver.cm.it.App
import java.awt.GridLayout
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

fun frameWithImage(imagePath: String): JFrame =
    JFrame().apply {
        val current = this
        isUndecorated = true
        extendedState = JFrame.MAXIMIZED_BOTH
//        size = Dimension(200, 200)
//                        contentPane.background = Color.BLUE
        isAlwaysOnTop = true
        layout = GridLayout()

        contentPane.add(
            JLabel(ImageIcon(App::class.java.getResource(imagePath))).apply {
                setSize(current.width, current.height)
            }
        )

        isVisible = true
    }