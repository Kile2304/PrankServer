package it.cm.webserver.request

import java.awt.TrayIcon

/**
 * Represents a notification model with various properties.
 *
 * @param title The title of the notification.
 * @param description The description of the notification.
 * @param tooltip The tooltip message to be displayed when the user hovers over the notification.
 * @param type The type of the notification icon, defaults to TrayIcon.MessageType.ERROR.
 */
data class NotificationModel(
    val title: String
    , val description: String
    , val tooltip: String
    , val type: TrayIcon.MessageType = TrayIcon.MessageType.ERROR
)