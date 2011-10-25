/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    AFreeChart: http://code.google.com/p/afreechart/
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * [Android is a trademark of Google Inc.]
 *
 * -----------------
 * XYCoordinate.java
 * -----------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2007, 2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 25-May-2007 : Moved from experimental to the main source tree (DG);
 *
 */

package org.afree.data.xy;

import java.io.Serializable;

/**
 * Represents an (x, y) coordinate.
 *
 * @since JFreeChart 1.0.6
 */
public class XYCoordinate implements Comparable, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5815662119944275125L;

    /** The x-coordinate. */
    private double x;

    /** The y-coordinate. */
    private double y;

    /**
     * Creates a new coordinate for the point (0.0, 0.0).
     */
    public XYCoordinate() {
        this(0.0, 0.0);
    }

    /**
     * Creates a new coordinate for the point (x, y).
     *
     * @param x  the x-coordinate.
     * @param y  the y-coordinate.
     */
    public XYCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Tests this coordinate for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYCoordinate)) {
            return false;
        }
        XYCoordinate that = (XYCoordinate) obj;
        if (this.x != that.x) {
            return false;
        }
        if (this.y != that.y) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = 193;
        long temp = Double.doubleToLongBits(this.x);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.y);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Returns a string representation of this instance, primarily for
     * debugging purposes.
     *
     * @return A string.
     */
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    /**
     * Compares this instance against an arbitrary object.
     *
     * @param obj  the object (<code>null</code> not permitted).
     *
     * @return An integer indicating the relative order of the items.
     */
    public int compareTo(Object obj) {
        if (!(obj instanceof XYCoordinate)) {
            throw new IllegalArgumentException("Incomparable object.");
        }
        XYCoordinate that = (XYCoordinate) obj;
        if (this.x > that.x) {
            return 1;
        }
        else if (this.x < that.x) {
            return -1;
        }
        else {
            if (this.y > that.y) {
                return 1;
            }
            else if (this.y < that.y) {
                return -1;
            }
        }
        return 0;
    }

}
