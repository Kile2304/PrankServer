package webserver.cm.it.prank

import java.awt.Desktop
import java.net.URI

fun browse(url: String) {
    Desktop.getDesktop().browse(URI(url))
}