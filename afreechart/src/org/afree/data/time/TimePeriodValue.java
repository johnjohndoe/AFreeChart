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
 * --------------------
 * TimePeriodValue.java
 * --------------------
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
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 22-Apr-2003 : Version 1 (DG);
 * 03-Oct-2006 : Added null argument check to constructor (DG);
 * 07-Apr-2008 : Added a toString() override for debugging (DG);
 *
 */

package org.afree.data.time;

import java.io.Serializable;

/**
 * Represents a time period and an associated value.
 */
public class TimePeriodValue implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 3390443360845711275L;

    /** The time period. */
    private TimePeriod period;

    /** The value associated with the time period. */
    private Number value;

    /**
     * Constructs a new data item.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value associated with the time period.
     *
     * @throws IllegalArgumentException if <code>period</code> is
     *     <code>null</code>.
     */
    public TimePeriodValue(TimePeriod period, Number value) {
        if (period == null) {
            throw new IllegalArgumentException("Null 'period' argument.");
        }
        this.period = period;
        this.value = value;
    }

    /**
     * Constructs a new data item.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value associated with the time period.
     *
     * @throws IllegalArgumentException if <code>period</code> is
     *     <code>null</code>.
     */
    public TimePeriodValue(TimePeriod period, double value) {
        this(period, new Double(value));
    }

    /**
     * Returns the time period.
     *
     * @return The time period (never <code>null</code>).
     */
    public TimePeriod getPeriod() {
        return this.period;
    }

    /**
     * Returns the value.
     *
     * @return The value (possibly <code>null</code>).
     *
     * @see #setValue(Number)
     */
    public Number getValue() {
        return this.value;
    }

    /**
     * Sets the value for this data item.
     *
     * @param value  the new value (<code>null</code> permitted).
     *
     * @see #getValue()
     */
    public void setValue(Number value) {
        this.value = value;
    }

    /**
     * Tests this object for equality with the target object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimePeriodValue)) {
            return false;
        }

        TimePeriodValue timePeriodValue = (TimePeriodValue) obj;

        if (this.period != null ? !this.period.equals(timePeriodValue.period)
                : timePeriodValue.period != null) {
            return false;
        }
        if (this.value != null ? !this.value.equals(timePeriodValue.value)
                : timePeriodValue.value != null) {
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
        int result;
        result = (this.period != null ? this.period.hashCode() : 0);
        result = 29 * result + (this.value != null ? this.value.hashCode() : 0);
        return result;
    }

    /**
     * Clones the object.
     * <P>
     * Note: no need to clone the period or value since they are immutable
     * classes.
     *
     * @return A clone.
     */
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        }
        catch (CloneNotSupportedException e) { // won't get here...
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * Returns a string representing this instance, primarily for use in
     * debugging.
     *
     * @return A string.
     */
    public String toString() {
        return "TimePeriodValue[" + getPeriod() + "," + getValue() + "]";
    }

}
