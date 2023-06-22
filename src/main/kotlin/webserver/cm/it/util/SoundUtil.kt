package webserver.cm.it.util

import webserver.cm.it.App
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.FloatControl
import javax.sound.sampled.SourceDataLine

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