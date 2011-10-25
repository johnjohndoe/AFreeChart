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
 * -------------------
 * VectorDataItem.java
 * -------------------
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
 * (C) Copyright 2007, 2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 24-May-2007 : Added getVector(), renamed getDeltaX() --> getVectorX(),
 *               and likewise for getDeltaY() (DG);
 * 25-May-2007 : Moved from experimental to the main source tree (DG);
 *
 */

package org.afree.data.xy;

import org.afree.data.ComparableObjectItem;

/**
 * A data item representing data in the form (x, y, deltaX, deltaY), intended
 * for use by the {@link VectorSeries} class.
 *
 * @since JFreeChart 1.0.6
 */
public class VectorDataItem extends ComparableObjectItem {

    /**
     * 
     */
    private static final long serialVersionUID = 5076292324868472454L;

    /**
     * Creates a new instance of <code>YIntervalItem</code>.
     *
     * @param x  the x-value.
     * @param y  the y-value.
     * @param deltaX  the vector x.
     * @param deltaY  the vector y.
     */
    public VectorDataItem(double x, double y, double deltaX, double deltaY) {
        super(new XYCoordinate(x, y), new Vector(deltaX, deltaY));
    }

    /**
     * Returns the x-value.
     *
     * @return The x-value (never <code>null</code>).
     */
    public double getXValue() {
        XYCoordinate xy = (XYCoordinate) getComparable();
        return xy.getX();
    }

    /**
     * Returns the y-value.
     *
     * @return The y-value.
     */
    public double getYValue() {
        XYCoordinate xy = (XYCoordinate) getComparable();
        return xy.getY();
    }

    /**
     * Returns the vector.
     *
     * @return The vector (possibly <code>null</code>).
     */
    public Vector getVector() {
        return (Vector) getObject();
    }

    /**
     * Returns the x-component for the vector.
     *
     * @return The x-component.
     */
    public double getVectorX() {
        Vector vi = (Vector) getObject();
        if (vi != null) {
            return vi.getX();
        }
        else {
            return Double.NaN;
        }
    }

    /**
     * Returns the y-component for the vector.
     *
     * @return The y-component.
     */
    public double getVectorY() {
        Vector vi = (Vector) getObject();
        if (vi != null) {
            return vi.getY();
        }
        else {
            return Double.NaN;
        }
    }

}
