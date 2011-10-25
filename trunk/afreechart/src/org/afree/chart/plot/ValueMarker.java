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
 * ----------------
 * ValueMarker.java
 * ----------------
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
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 09-Feb-2004 : Version 1 (DG);
 * 16-Feb-2005 : Added new constructor (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Sep-2006 : Added setValue() method (DG);
 * 08-Oct-2007 : Fixed bug 1808376, constructor calling super with incorrect
 *               values (DG);
 *
 */

package org.afree.chart.plot;

import org.afree.chart.event.MarkerChangeEvent;

/**
 * A marker that represents a single value. Markers can be added to plots to
 * highlight specific values.
 */
public class ValueMarker extends Marker {

    /**
     * 
     */
    private static final long serialVersionUID = 1197468552328194791L;
    /** The value. */
    private double value;

    /**
     * Creates a new marker.
     * 
     * @param value
     *            the value.
     */
    public ValueMarker(double value) {
        super();
        this.value = value;
    }

    /**
     * Creates a new marker.
     * 
     * @param value
     *            the value.
     * @param paint
     *            the paint (<code>null</code> not permitted).
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     */
    public ValueMarker(double value, int paint, float stroke) {
        this(value, paint, stroke, paint, stroke, 255);
    }

    /**
     * Creates a new value marker.
     * 
     * @param value
     *            the value.
     * @param paint
     *            the paint (<code>null</code> not permitted).
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * @param outlinePaint
     *            the outline paint (<code>null</code> permitted).
     * @param outlineStroke
     *            the outline stroke (<code>null</code> permitted).
     * @param alpha
     *            the alpha transparency (in the range 0.0f to 1.0f).
     */
    public ValueMarker(double value, int paint, float stroke, int outlinePaint,
            float outlineStroke, int alpha) {
        super(paint, stroke, outlinePaint, outlineStroke, alpha);
        this.value = value;
    }

    /**
     * Returns the value.
     * 
     * @return The value.
     * 
     * @see #setValue(double)
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Sets the value for the marker and sends a {@link MarkerChangeEvent} to
     * all registered listeners.
     * 
     * @param value
     *            the value.
     * 
     * @see #getValue()
     * 
     * @since JFreeChart 1.0.3
     */
    public void setValue(double value) {
        this.value = value;
        notifyListeners(new MarkerChangeEvent(this));
    }

    /**
     * Tests this marker for equality with an arbitrary object. This method
     * returns <code>true</code> if:
     * 
     * <ul>
     * <li><code>obj</code> is not <code>null</code>;</li>
     * <li><code>obj</code> is an instance of <code>ValueMarker</code>;</li>
     * <li><code>obj</code> has the same value as this marker;</li>
     * <li><code>super.equals(obj)</code> returns <code>true</code>.</li>
     * </ul>
     * 
     * @param obj
     *            the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof ValueMarker)) {
            return false;
        }
        ValueMarker that = (ValueMarker) obj;
        if (this.value != that.value) {
            return false;
        }
        return true;
    }
}
