package webserver.cm.it.prank

import webserver.cm.it.request.DialogModel
import java.awt.*
import javax.swing.*

fun createPopup(model: DialogModel) {
    SwingUtilities.invokeLater {
        JFrame().apply {
            val thisFrame = this
            title = model.title
            layout = BorderLayout()
            defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
            contentPane.background = Color(40, 40, 40)
            setLocationRelativeTo(null)
            isUndecorated = true
            minimumSize = Dimension(300, 100)

            contentPane.add(
                JPanel().apply {
                    background = Color(40, 40, 40)
                    add(
                        InfoIconCanvas().apply {
                            background = Color(40, 40, 40)
                            preferredSize = Dimension(40, 40)
                        }
                    )
                }, BorderLayout.WEST
            )
            contentPane.add(
                JPanel().apply {
                    layout = GridBagLayout()
                    background = Color(40, 40, 40)
                    border = BorderFactory.createEmptyBorder(10, 10, 10, 10)

                    add(
                        JPanel().apply {
                            layout = BoxLayout(this, BoxLayout.Y_AXIS)
                            background = Color(40, 40, 40)
                            add(
                                JLabel(model.title).apply {
                                    font = Font("Arial", Font.BOLD, 16)
                                    foreground = Color.WHITE
                                    alignmentX = Component.LEFT_ALIGNMENT
                                }
                            )
                            add(
                                JLabel(model.description).apply {
                                    font = Font("Arial", Font.PLAIN, 14)
                                    foreground = Color.LIGHT_GRAY
                                    alignmentX = Component.LEFT_ALIGNMENT
                                }
                            )
                        }
                    )
                }
            )
            contentPane.add(
                JPanel().apply {
                    background = Color(40, 40, 40)
                    layout = FlowLayout(FlowLayout.RIGHT)
                    add(
                        JButton("Close").apply {
//                            background = Color(40, 40, 40)
                            foreground = Color(40, 40, 40)
//                            isContentAreaFilled = false
                            isOpaque = true
                            border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
                            addActionListener {
                                thisFrame.dispose()
                            }
                        }
                    )
                }, BorderLayout.SOUTH
            )

            requestFocus()
            isAlwaysOnTop = true
            pack()
            isVisible = true
        }
    }
}

class InfoIconCanvas: JPanel() {
    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        val iconSIze = 35
        val x = (width - iconSIze) / 2
        val y = (height - iconSIze) / 2

        val g2d = g as Graphics2D
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING
            , RenderingHints.VALUE_ANTIALIAS_ON
        )
        g2d.color = Color.WHITE
        g2d.fillOval(x, y, iconSIze, iconSIze)

        g2d.color = Color.BLUE
        g2d.font = Font("Arial", Font.ITALIC, 27)
        val text = "i"
        val fm = g2d.fontMetrics
        val textWidth = fm.stringWidth(text)
        val textHeight = fm.height
        val textX = x + (iconSIze - textWidth) / 2
        val textY = y + (iconSIze + textHeight) / 2 - fm.descent
        g2d.drawString(text, textX, textY)
    }
}