package it.cm.webserver.request

import java.awt.TrayIcon

data class NotificationModel(
    val title: String
    , val description: String
    , val tooltip: String
    , val type: TrayIcon.MessageType = TrayIcon.MessageType.ERROR
)