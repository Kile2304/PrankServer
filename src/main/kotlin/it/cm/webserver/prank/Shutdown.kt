package it.cm.webserver.prank

import java.io.File
import kotlin.system.exitProcess

fun shutdownMe() {
    // If the powershell script find this file, don't instantiate the server end terminate itself
    File(".shutdown").createNewFile()
    exitProcess(1)
}