/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
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
 * --------------
 * ShapeList.java
 * --------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 *
 * Changes
 * -------
 * 13-Aug-2003 : Version 1 (DG);
 * 
 */

package org.afree.util;

import org.afree.graphics.geom.Shape;


/**
 * A table of {@link Shape} objects.
 * 
 * @author David Gilbert
 */
public class ShapeList extends AbstractObjectList {

    /**
     * 
     */
    private static final long serialVersionUID = -8991795446308679274L;

    /**
     * Creates a new list.
     */
    public ShapeList() {
        super();
    }

    /**
     * Returns a {@link Shape} object from the list.
     * 
     * @param index
     *            the index (zero-based).
     * 
     * @return The object.
     */
    public Shape getShape(final int index) {
        return (Shape) get(index);
    }

    /**
     * Sets the {@link Shape} for an item in the list. The list is expanded if
     * necessary.
     * 
     * @param index
     *            the index (zero-based).
     * @param shape
     *            the {@link Shape}.
     */
    public void setShape(final int index, final Shape shape) {
        set(index, shape);
    }

    /**
     * Returns an independent copy of the list.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException
     *             if an item in the list does not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Tests the list for equality with another object (typically also a list).
     * 
     * @param o
     *            the other object.
     * 
     * @return A boolean.
     */
    public boolean equals(final Object o) {

        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (o instanceof ShapeList) {
            return super.equals(o);
        }

        return false;

    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return the hashcode
     */
    public int hashCode() {
        return super.hashCode();
    }

}
