package it.cm.webserver.prank

import it.cm.webserver.request.DialogModel
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

/**
 * Promemoria: Integrare tipo di popup (errore, warning e info)
 *
 * Creates a popup dialog with the given model.
 *
 * @param model the dialog model containing the title and description of the dialog
 */
fun createPopup(model: DialogModel) {
    SwingUtilities.invokeLater {
        JFrame().apply {
            val thisFrame = this
            title = model.title
            layout = BorderLayout()
            defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
            contentPane.background = Color(51, 62, 76)
            setLocationRelativeTo(null)
            isUndecorated = true
            minimumSize = Dimension(300, 150)
            maximumSize = Dimension(600, 400)

            var mouseClickedPoint: Point? = null
            addMouseListener(
                object : MouseAdapter() {
                    override fun mousePressed(e: MouseEvent) {
                        mouseClickedPoint = e.point // remember the point where mouse is clicked
                    }
                }
            )
            addMouseMotionListener(
                object : MouseAdapter() {
                    override fun mouseDragged(e: MouseEvent) {
                        mouseClickedPoint?.let {
                            // New location of window is calculated by old location plus the dragged distance
                            location = Point(
                                locationOnScreen.x + e.x - it.x,
                                locationOnScreen.y + e.y - it.y
                            )
                        }
                    }
                }
            )

            contentPane.add(
                JPanel().apply {
                    background = Color(51, 62, 76)
                    add(
                        ErrorIconCanvas().apply {
                            background = Color(51, 62, 76)
                            preferredSize = Dimension(40, 40)
                        }
                    )
                }, BorderLayout.WEST
            )
            contentPane.add(
                JPanel().apply {
                    layout = GridBagLayout()
                    background = Color(51, 62, 76)
                    border = BorderFactory.createEmptyBorder(10, 10, 10, 10)

                    add(
                        JPanel().apply {
                            layout = BoxLayout(this, BoxLayout.Y_AXIS)
                            background = Color(51, 62, 76)
                            add(
                                JLabel(model.title).apply {
                                    font = Font("Arial", Font.BOLD, 16)
                                    foreground = Color.WHITE
                                    alignmentX = Component.LEFT_ALIGNMENT
                                }
                            )
                            add(
                                JScrollPane(
                                    JTextArea(model.description).apply {
                                        lineWrap = true
                                        wrapStyleWord = true
                                        isEditable = false
                                        font = Font("Arial", Font.PLAIN, 14)
                                        foreground = Color.WHITE
                                        background = Color(51, 62, 76)
                                        border = null
                                        val lineHeight = getFontMetrics(font).height
                                        preferredSize = Dimension(150, lineHeight * 5)
                                    }
                                ).apply {
                                    verticalScrollBarPolicy = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
                                    horizontalScrollBarPolicy = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
                                    border = null
                                }
                            )
                        }
                    )
                }, BorderLayout.CENTER
            )
            contentPane.add(
                JPanel().apply {
                    background = Color(51, 62, 76)
                    layout = FlowLayout(FlowLayout.RIGHT)
                    add(
                        JButton("Close").apply {
                            foreground = Color.WHITE
                            background = Color(108, 122, 141)
                            isOpaque = true
//                            border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
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

/**
 * A custom JPanel that displays an info icon.
 */
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
        g2d.stroke = BasicStroke(1F)
        g2d.color = Color.WHITE
        g2d.drawOval(x, y, iconSIze, iconSIze)
//        g2d.fillOval(x, y, iconSIze, iconSIze)

        g2d.color = Color.BLUE
        val gradient = GradientPaint(x.toFloat(), y.toFloat(), Color.BLUE,
            (x + iconSIze).toFloat(), (y + iconSIze).toFloat(), Color.CYAN) // Create gradient
        g2d.paint = gradient
        g2d.fillOval(x + 1, y + 1, iconSIze - 1, iconSIze - 1) // minus 1 to keep border visible

        g2d.color = Color.WHITE
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

/**
 * A custom JPanel that displays an error icon in the center.
 */
class ErrorIconCanvas: JPanel() {
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
        g2d.stroke = BasicStroke(1F)
        g2d.color = Color.WHITE
        g2d.drawOval(x, y, iconSIze, iconSIze)

        g2d.color = Color.RED
        val gradient = GradientPaint(x.toFloat(), y.toFloat(), Color.RED,
            (x + iconSIze).toFloat(), (y + iconSIze).toFloat(), Color(139, 0, 0)) // Create gradient
        g2d.paint = gradient
        g2d.fillOval(x + 1, y + 1, iconSIze - 1, iconSIze - 1) // minus 1 to keep border visible

        g2d.color = Color.WHITE
        g2d.font = Font("Arial", Font.ITALIC, 27)
        val text = "X"
        val fm = g2d.fontMetrics
        val textWidth = fm.stringWidth(text)
        val textHeight = fm.height
        val textX = x + (iconSIze - textWidth) / 2
        val textY = y + (iconSIze + textHeight) / 2 - fm.descent
        g2d.drawString(text, textX, textY)
    }
}

/**
 * A custom JPanel class that represents a warning icon.
 * The warning icon is displayed as a yellow circle with an exclamation mark in the center.
 */
class WarningIconCanvas: JPanel() {
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
        g2d.stroke = BasicStroke(1F)
        g2d.color = Color.WHITE
        g2d.drawOval(x, y, iconSIze, iconSIze)

        g2d.color = Color.YELLOW
        val gradient = GradientPaint(x.toFloat(), y.toFloat(), Color.YELLOW,
            (x + iconSIze).toFloat(), (y + iconSIze).toFloat(), Color.ORANGE) // Create gradient
        g2d.paint = gradient
        g2d.fillOval(x + 1, y + 1, iconSIze - 1, iconSIze - 1) // minus 1 to keep border visible

        g2d.color = Color.BLACK
        g2d.font = Font("Arial", Font.BOLD, 27)
        val text = "!"
        val fm = g2d.fontMetrics
        val textWidth = fm.stringWidth(text)
        val textHeight = fm.height
        val textX = x + (iconSIze - textWidth) / 2
        val textY = y + (iconSIze + textHeight) / 2 - fm.descent
        g2d.drawString(text, textX, textY)
    }
}