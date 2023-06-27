package it.cm.webserver.prank.animation;

import it.cm.webserver.util.BiPoint

data class PokemonAnim(
    val anim: List<BiPoint>,
    val doStep: Int = -1,
    val doForTime: Long = -1,
    val loop: Boolean = false,
    val waitAfterAnimation: Long = 0
)
