package webserver.cm.it.prank

import webserver.cm.it.App
import webserver.cm.it.request.NotificationModel
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon

fun notification(model: NotificationModel) {
    //Obtain only one instance of the SystemTray object
    val tray = SystemTray.getSystemTray()

    //If the icon is a file
    val image = Toolkit.getDefaultToolkit().createImage(App::class.java.getResource("/image/eclipse.ico"))
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