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
 * ---------------
 * NumberTick.java
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
 * 07-Nov-2003 : Version 1 (DG);
 * 02-Aug-2007 : Added new constructor with tick type (DG);
 *
 */

package org.afree.chart.axis;

import org.afree.ui.TextAnchor;

/**
 * A numerical tick.
 */
public class NumberTick extends ValueTick {

    /**
     * 
     */
    private static final long serialVersionUID = -3631870427166231943L;
    /** The number. */
    private Number number;

    /**
     * Creates a new tick.
     * 
     * @param number
     *            the number (<code>null</code> not permitted).
     * @param label
     *            the label.
     * @param textAnchor
     *            the part of the label that is aligned with the anchor point.
     * @param rotationAnchor
     *            defines the rotation point relative to the text.
     * @param angle
     *            the rotation angle (in radians).
     */
    public NumberTick(Number number, String label, TextAnchor textAnchor,
            TextAnchor rotationAnchor, double angle) {

        super(number.doubleValue(), label, textAnchor, rotationAnchor, angle);
        this.number = number;

    }

    /**
     * Creates a new tick.
     * 
     * @param tickType
     *            the tick type.
     * @param value
     *            the value.
     * @param label
     *            the label.
     * @param textAnchor
     *            the part of the label that is aligned with the anchor point.
     * @param rotationAnchor
     *            defines the rotation point relative to the text.
     * @param angle
     *            the rotation angle (in radians).
     * 
     * @since JFreeChart 1.0.7
     */
    public NumberTick(TickType tickType, double value, String label,
            TextAnchor textAnchor, TextAnchor rotationAnchor, double angle) {

        super(tickType, value, label, textAnchor, rotationAnchor, angle);
        this.number = new Double(value);

    }

    /**
     * Returns the number.
     * 
     * @return The number.
     */
    public Number getNumber() {
        return this.number;
    }

}
