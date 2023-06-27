package it.cm.webserver.prank.animation

import it.cm.webserver.App
import it.cm.webserver.prank.animList
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.util.*
import javax.swing.ImageIcon
import javax.swing.JPanel


class PokemonCanvas(
    private val animList: Deque<PokemonAnim>
) : JPanel() {

    private val player = ImageIcon(App::class.java.getResource("/image/player.png")).image
    private var animLoop = false

    init {
        isOpaque = false
    }

    fun moveBottom() {
//        animList.addLast()
    }
    fun moveTop() {

    }
    fun moveLeft() {

    }
    fun moveRight() {

    }

    override fun paintComponent(g: Graphics) {
        super.paintComponents(g)

        val g2d = g.create() as Graphics2D
        g.color = Color(0, 0, 0, 0)
        g.drawRect(0, 0, width, height)

        g.color = Color.GREEN
        g.drawOval(50, 50, 300, 300)

        g.drawImage(player, 0, 0, null, null)

        g2d.dispose()
    }
    private fun render() {
        repaint()
    }
    private fun tick() {

    }

    private fun animationLoop() {
        animLoop = true

        var FPS = 0
        var lastTime = System.nanoTime()
        val amountOfTicks = 60.0
        val ns = 1000000000 / amountOfTicks
        var delta = 0.0
        var ticks = 0
        var frames = 0
        var timer = System.currentTimeMillis()

        while(animLoop) {
            val now = System.nanoTime()
            delta += (now - lastTime) / ns
            lastTime = now
            if (delta >= 1 && ticks < amountOfTicks) {
                tick()
                ticks++
                delta--
            }
            render()
            frames++
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000
                //System.out.println("FPS : " + frames + " Ticks : " + ticks);
                FPS = ticks
                frames = 0
                ticks = 0
            }
        }

    }

}