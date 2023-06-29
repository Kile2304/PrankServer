package it.cm.webserver.prank

import it.cm.webserver.prank.animation.PokemonAnim
import it.cm.webserver.prank.animation.PokemonCanvas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import java.awt.Color
import java.awt.GridLayout
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque
import javax.swing.JFrame

val animList: Deque<PokemonAnim> = ConcurrentLinkedDeque()

/**
 * Creates a JFrame and displays a Pokemon animation.
 *
 * @param animations The animation sequence to be performed on the Pokemon.
 */
inline fun CoroutineScope.pokemonAnimation(
    animations: Deque<PokemonAnim>.()->Unit
) =
    JFrame().apply {
        isUndecorated = true

        extendedState = JFrame.MAXIMIZED_BOTH
        isAlwaysOnTop = true
        layout = GridLayout()

        background = Color(0, 0, 0, 0)
        contentPane.background = Color(0, 0, 0, 0)

        val pokemonCanvas = PokemonCanvas(animList)
        contentPane.add(pokemonCanvas)

        launch(Dispatchers.Swing) {
//            pokemonCanvas.animationLoop()
        }
        animList.animations()

        isVisible = true
    }

