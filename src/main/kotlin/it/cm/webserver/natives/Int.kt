package it.cm.webserver.natives

import java.awt.Point

infix fun Int.point(y: Int) = Point(this, y)