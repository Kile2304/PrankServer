package it.cm.webserver.natives

import it.cm.webserver.util.BiPoint
import java.awt.Point

infix fun Point.mp(point: Point) = BiPoint(this, point)