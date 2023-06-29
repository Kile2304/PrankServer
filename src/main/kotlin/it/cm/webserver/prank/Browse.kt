package it.cm.webserver.prank

import java.awt.Desktop
import java.net.URI

/**
 * Launches the default browser to open the specified URL.
 *
 * @param url The URL to be opened in the browser.
 * @throws IOException if a browser launch error occurs.
 * @throws URISyntaxException if the URL is malformed.
 */
fun browse(url: String) {
    Desktop.getDesktop().browse(URI(url))
}