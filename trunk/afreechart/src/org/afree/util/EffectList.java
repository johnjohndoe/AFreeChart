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
 * ---------------
 * EffectList.java
 * ---------------
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
 * 14-Jan-2011 : Updated API docs
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
 * 19-Aug-2003 : Version 1 (DG);
 * 
 */

package org.afree.util;

import android.graphics.PathEffect;

/**
 * A table of {@link Stroke} objects.
 * 
 * @author David Gilbert
 */
public class EffectList extends AbstractObjectList {

    /**
     * 
     */
    private static final long serialVersionUID = -5216122258429168187L;

    /**
     * Creates a new list.
     */
    public EffectList() {
        super();
    }

    /**
     * Returns a {@link PathEffect} object from the list.
     * 
     * @param index
     *            the index (zero-based).
     * 
     * @return The object.
     */
    public PathEffect getEffect(final int index) {
        return (PathEffect) get(index);
    }

    /**
     * Sets the {@link PathEffect} for an item in the list. The list is expanded if
     * necessary.
     * 
     * @param index
     *            the index (zero-based).
     * @param effect
     *            the {@link PathEffect}.
     */
    public void setEffect(final int index, final PathEffect effect) {
        set(index, effect);
    }

    /**
     * Returns an independent copy of the list.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException
     *             if an item in the list cannot be cloned.
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

        if (o instanceof EffectList) {
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
        return super.size();
    }

}
