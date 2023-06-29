package it.cm.webserver.natives

import java.awt.Point

/**
 * Creates a new Point object with the given x and y coordinates.
 *
 * @param y the y-coordinate of the point.
 * @return the resulting Point object.
 */
infix fun Int.point(y: Int) = Point(this, y)