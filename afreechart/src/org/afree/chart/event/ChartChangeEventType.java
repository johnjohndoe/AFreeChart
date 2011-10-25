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
 * -------------------------
 * ChartChangeEventType.java
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
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2005-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 18-Feb-2005 : Version 1 (DG);
 *
 */

package org.afree.chart.event;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Defines tokens used to indicate an event type.
 */
public final class ChartChangeEventType implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 5481917022435735602L;

    /** GENERAL. */
    public static final ChartChangeEventType GENERAL = new ChartChangeEventType(
            "ChartChangeEventType.GENERAL");

    /** NEW_DATASET. */
    public static final ChartChangeEventType NEW_DATASET = new ChartChangeEventType(
            "ChartChangeEventType.NEW_DATASET");

    /** DATASET_UPDATED. */
    public static final ChartChangeEventType DATASET_UPDATED = new ChartChangeEventType(
            "ChartChangeEventType.DATASET_UPDATED");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     * 
     * @param name
     *            the name.
     */
    private ChartChangeEventType(String name) {
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
     *            the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChartChangeEventType)) {
            return false;
        }
        ChartChangeEventType that = (ChartChangeEventType) obj;
        if (!this.name.equals(that.toString())) {
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
        if (this.equals(ChartChangeEventType.GENERAL)) {
            return ChartChangeEventType.GENERAL;
        } else if (this.equals(ChartChangeEventType.NEW_DATASET)) {
            return ChartChangeEventType.NEW_DATASET;
        } else if (this.equals(ChartChangeEventType.DATASET_UPDATED)) {
            return ChartChangeEventType.DATASET_UPDATED;
        }
        return null;
    }

}
