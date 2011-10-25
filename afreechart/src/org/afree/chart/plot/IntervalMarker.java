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
 * -------------------
 * IntervalMarker.java
 * -------------------
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
 * (C) Copyright 2002-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Aug-2002 : Added stroke to constructor in Marker class (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Sep-2006 : Added MarkerChangeEvent notification (DG);
 * 18-Dec-2007 : Added new constructor (DG);
 *
 */

package org.afree.chart.plot;

import java.io.Serializable;

import org.afree.chart.event.MarkerChangeEvent;
import org.afree.ui.GradientShaderFactory;
import org.afree.ui.LengthAdjustmentType;

import android.graphics.Color;

/**
 * Represents an interval to be highlighted in some way.
 */
public class IntervalMarker extends Marker implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -1762344775267627916L;

    /** The start value. */
    private double startValue;

    /** The end value. */
    private double endValue;

    /** The gradient shader factory (optional). */
    private GradientShaderFactory gradientShaderFactory;

    /**
     * Constructs an interval marker.
     * 
     * @param start
     *            the start of the interval.
     * @param end
     *            the end of the interval.
     */
    public IntervalMarker(double start, double end) {
        this(start, end, Color.GRAY, 0.5f, Color.GRAY, 0.5f, 200);
    }

    /**
     * Creates a new interval marker with the specified range and fill paint.
     * The outline paint and stroke default to <code>null</code>.
     * 
     * @param start
     *            the lower bound of the interval.
     * @param end
     *            the upper bound of the interval.
     * @param paint
     *            the fill paint (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.9
     */
    public IntervalMarker(double start, double end, int paint) {
        // this(start, end, paint, 0.5f, 0, 0.8f);
        this(start, end, paint, 0.5f, paint, 0.5f, 200);
    }

    /**
     * Constructs an interval marker.
     * 
     * @param start
     *            the start of the interval.
     * @param end
     *            the end of the interval.
     * @param paint
     *            the paint (<code>null</code> not permitted).
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * @param outlinePaint
     *            the outline paint.
     * @param outlineStroke
     *            the outline stroke.
     * @param alpha
     *            the alpha transparency.
     */
    public IntervalMarker(double start, double end, int paint, float stroke,
            int outlinePaint, float outlineStroke, int alpha) {

        super(paint, stroke, outlinePaint, outlineStroke, alpha);
        this.startValue = start;
        this.endValue = end;
        this.gradientShaderFactory = null;
        setLabelOffsetType(LengthAdjustmentType.CONTRACT);

    }

    /**
     * Returns the start value for the interval.
     * 
     * @return The start value.
     */
    public double getStartValue() {
        return this.startValue;
    }

    /**
     * Sets the start value for the marker and sends a {@link MarkerChangeEvent}
     * to all registered listeners.
     * 
     * @param value
     *            the value.
     * 
     * @since JFreeChart 1.0.3
     */
    public void setStartValue(double value) {
        this.startValue = value;
        notifyListeners(new MarkerChangeEvent(this));
    }

    /**
     * Returns the end value for the interval.
     * 
     * @return The end value.
     */
    public double getEndValue() {
        return this.endValue;
    }

    /**
     * Sets the end value for the marker and sends a {@link MarkerChangeEvent}
     * to all registered listeners.
     * 
     * @param value
     *            the value.
     * 
     * @since JFreeChart 1.0.3
     */
    public void setEndValue(double value) {
        this.endValue = value;
        notifyListeners(new MarkerChangeEvent(this));
    }

    /**
     * Returns the gradient paint transformer.
     * 
     * @return The gradient paint transformer (possibly <code>null</code>).
     */
    public GradientShaderFactory getGradientShaderFactory() {
        return this.gradientShaderFactory;
    }

    /**
     * Sets the gradient paint transformer and sends a {@link MarkerChangeEvent}
     * to all registered listeners.
     * 
     * @param gradientShaderFactory
     *            the transformer (<code>null</code> permitted).
     */
    public void setGradientPaintTransformer(GradientShaderFactory gradientShaderFactory) {
        this.gradientShaderFactory = gradientShaderFactory;
        notifyListeners(new MarkerChangeEvent(this));
    }

}
