package it.cm.webserver.natives

import it.cm.webserver.util.BiPoint
import java.awt.Point

/**
 * Creates a BiPoint representing the connection between two Points.
 *
 * @param point The second Point to connect with.
 * @return A BiPoint object representing the connection between the two Points.
 */
infix fun Point.mp(point: Point) = BiPoint(this, point)