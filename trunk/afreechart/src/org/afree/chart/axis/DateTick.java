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
 * -------------
 * DateTick.java
 * -------------
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
 * Contributor(s):   Peter Kolb (patch 1934255);
 *                   Andrew Mickish (patch 1870189);
 *
 * Changes
 * -------
 * 07-Nov-2003 : Version 1 (DG);
 * 13-May-2004 : Added equals() method (DG);
 * 25-Sep-2008 : Added minor tick support, see patch 1934255 by Peter Kolb (DG);
 *
 */

package org.afree.chart.axis;


import java.util.Date;

import org.afree.util.ObjectUtilities;
import org.afree.ui.TextAnchor;

/**
 * A tick used by the {@link DateAxis} class.
 */
public class DateTick extends ValueTick {

    /**
     * 
     */
    private static final long serialVersionUID = 8585849089296417839L;
    /** The date. */
    private Date date;

    /**
     * Creates a new date tick.
     *
     * @param date  the date.
     * @param label  the label.
     * @param textAnchor  the part of the label that is aligned to the anchor
     *                    point.
     * @param rotationAnchor  defines the rotation point relative to the text.
     * @param angle  the rotation angle (in radians).
     */
    public DateTick(Date date, String label,
                    TextAnchor textAnchor, TextAnchor rotationAnchor,
                    double angle) {

        this(TickType.MAJOR, date, label, textAnchor, rotationAnchor, angle);

    }

    /**
     * Creates a new date tick.
     *
     * @param tickType the tick type.
     * @param date  the date.
     * @param label  the label.
     * @param textAnchor  the part of the label that is aligned to the anchor
     *                    point.
     * @param rotationAnchor  defines the rotation point relative to the text.
     * @param angle  the rotation angle (in radians).
     *
     * @since JFreeChart 1.0.12
     */
    public DateTick(TickType tickType, Date date, String label,
                    TextAnchor textAnchor, TextAnchor rotationAnchor,
                    double angle) {

        super(tickType, date.getTime(), label, textAnchor, rotationAnchor, angle);
        this.date = date;

    }

    /**
     * Returns the date.
     *
     * @return The date.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Tests this tick for equality with an arbitrary object.
     *
     * @param obj  the object to test (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DateTick)) {
            return false;
        }
        DateTick that = (DateTick) obj;
        //TODO: port ObjectUtilities
        if (!ObjectUtilities.equal(this.date, that.date)) {
            return false;
        }
       
        return super.equals(obj);
    }

    /**
     * Returns a hash code for this object.
     *
     * @return A hash code.
     */
    public int hashCode() {
        return this.date.hashCode();
    }

}
