/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
 * 
 * Project Info:
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *    AFreeChart: http://code.google.com/p/afreechart/
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Android is a trademark of Google Inc.]
 * 
 * ---------------
 * StrokeList.java
 * ---------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2003, 2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 *
 * Changes
 * -------
 * 19-Aug-2003 : Version 1 (DG);
 * 
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
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
     * Creates a new list.
     */
    public EffectList() {
        super();
    }

    /**
     * Returns a {@link Stroke} object from the list.
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
     * Sets the {@link Stroke} for an item in the list. The list is expanded if
     * necessary.
     * 
     * @param index
     *            the index (zero-based).
     * @param effect
     *            the {@link Stroke}.
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
        return super.hashCode();
    }

}
