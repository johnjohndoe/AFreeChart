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
 * -------------------------
 * LengthAdjustmentType.java
 * -------------------------
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
 * (C) Copyright 2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 *
 * Changes:
 * --------
 * 21-Jan-2005 : Version 1 (DG);
 * 
 */

package org.afree.ui;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Represents the three options for adjusting a length: expand, contract, and no
 * change.
 * 
 * @author David Gilbert
 */
public final class LengthAdjustmentType implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -6097408511380545010L;

    /** NO_CHANGE. */
    public static final LengthAdjustmentType NO_CHANGE = new LengthAdjustmentType(
            "NO_CHANGE");

    /** EXPAND. */
    public static final LengthAdjustmentType EXPAND = new LengthAdjustmentType(
            "EXPAND");

    /** CONTRACT. */
    public static final LengthAdjustmentType CONTRACT = new LengthAdjustmentType(
            "CONTRACT");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     * 
     * @param name
     *            the name.
     */
    private LengthAdjustmentType(final String name) {
        this.name = name;
    }

    /**
     * Returns a string representing the object.
     * 
     * @return The string.
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns <code>true</code> if this object is equal to the specified
     * object, and <code>false</code> otherwise.
     * 
     * @param obj
     *            the other object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LengthAdjustmentType)) {
            return false;
        }
        final LengthAdjustmentType that = (LengthAdjustmentType) obj;
        if (!this.name.equals(that.name)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return The hashcode
     */
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Ensures that serialization returns the unique instances.
     * 
     * @return The object.
     * 
     * @throws ObjectStreamException
     *             if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(LengthAdjustmentType.NO_CHANGE)) {
            return LengthAdjustmentType.NO_CHANGE;
        } else if (this.equals(LengthAdjustmentType.EXPAND)) {
            return LengthAdjustmentType.EXPAND;
        } else if (this.equals(LengthAdjustmentType.CONTRACT)) {
            return LengthAdjustmentType.CONTRACT;
        }
        return null;
    }

}
