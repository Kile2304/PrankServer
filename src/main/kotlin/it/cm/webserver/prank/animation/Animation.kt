package it.cm.webserver.prank.animation

import it.cm.webserver.App
import it.cm.webserver.natives.mp
import it.cm.webserver.natives.point
import it.cm.webserver.util.BiPoint
import java.awt.Image
import javax.swing.ImageIcon

data class Animation(
    val filePath: Image
    , val animPath: List<BiPoint>
)

val MOVE_PLAYER_BOTTOM: Animation = Animation(
    ImageIcon(App::class.java.getResource("/image/player_alpha.png")).image
    , listOf(
        (8 point 37) mp (23 point 56)
        , (26 point 35) mp (39 point 54)
        , (41 point 39) mp (56 point 56)
    )
)