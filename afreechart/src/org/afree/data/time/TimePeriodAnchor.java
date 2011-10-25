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
 * ---------------------
 * TimePeriodAnchor.java
 * ---------------------
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
 * Changes:
 * --------
 * 30-Jul-2003 : Version 1 (DG);
 * 01-Mar-2004 : Added readResolve() method (DG);
 *
 */

package org.afree.data.time;


import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Used to indicate one of three positions in a time period:
 * <code>START</code>, <code>MIDDLE</code> and <code>END</code>.
 */
public final class TimePeriodAnchor implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 2011955697457548862L;

    /** Start of period. */
    public static final TimePeriodAnchor START
        = new TimePeriodAnchor("TimePeriodAnchor.START");

    /** Middle of period. */
    public static final TimePeriodAnchor MIDDLE
        = new TimePeriodAnchor("TimePeriodAnchor.MIDDLE");

    /** End of period. */
    public static final TimePeriodAnchor END
        = new TimePeriodAnchor("TimePeriodAnchor.END");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private TimePeriodAnchor(String name) {
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
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimePeriodAnchor)) {
            return false;
        }

        TimePeriodAnchor position = (TimePeriodAnchor) obj;
        if (!this.name.equals(position.name)) {
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
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(TimePeriodAnchor.START)) {
            return TimePeriodAnchor.START;
        }
        else if (this.equals(TimePeriodAnchor.MIDDLE)) {
            return TimePeriodAnchor.MIDDLE;
        }
        else if (this.equals(TimePeriodAnchor.END)) {
            return TimePeriodAnchor.END;
        }
        return null;
    }

}
