package it.cm.webserver.prank

import java.io.File
import kotlin.system.exitProcess

/**
 * Shuts down the application by creating a file named ".shutdown" and then terminates the process.
 *
 * This method is intended to be called when the application needs to be gracefully shutdown.
 * By creating the ".shutdown" file, a powershell script can detect its existence and take
 * appropriate actions, such as terminating the server.
 *
 * @throws IOException if an I/O error occurs while creating the ".shutdown" file
 */
fun shutdownMe() {
    // If the powershell script find this file, don't instantiate the server end terminate itself
    File(".shutdown").createNewFile()
    exitProcess(1)
}