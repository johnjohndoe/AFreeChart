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
 * CategoryMarker.java
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
 * (C) Copyright 2005-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nicolas Brodu;
 *
 * Changes
 * -------
 * 20-May-2005 : Version 1 (DG);
 * 19-Aug-2005 : Implemented equals(), fixed bug in constructor (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Sep-2006 : Added MarkerChangeListener support (DG);
 *
 */

package org.afree.chart.plot;

import java.io.Serializable;

import org.afree.chart.event.MarkerChangeEvent;
import org.afree.ui.LengthAdjustmentType;

/**
 * A marker for a category. <br>
 * <br>
 * Note that for serialization to work correctly, the category key must be an
 * instance of a serializable class.
 * 
 * @see CategoryPlot#addDomainMarker(CategoryMarker)
 */
public class CategoryMarker extends Marker implements Cloneable, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1224692572088716268L;

    /** The category key. */
    private Comparable key;

    /**
     * A hint that the marker should be drawn as a line rather than a region.
     */
    private boolean drawAsLine = false;

    /**
     * Creates a new category marker for the specified category.
     * 
     * @param key
     *            the category key.
     */
    // public CategoryMarker(Comparable key) {
    // this(key, Color.GRAY, 1.0f);
    // }

    /**
     * Creates a new category marker.
     * 
     * @param key
     *            the key.
     * @param paint
     *            the paint (<code>null</code> not permitted).
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     */
    public CategoryMarker(Comparable key, int paint, int stroke) {
        this(key, paint, stroke, paint, stroke, 255);
    }

    /**
     * Creates a new category marker.
     * 
     * @param key
     *            the key.
     * @param paint
     *            the paint (<code>null</code> not permitted).
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * @param outlinePaint
     *            the outline paint (<code>null</code> permitted).
     * @param outlineStroke
     *            the outline stroke (<code>null</code> permitted).
     * @param alpha
     *            the alpha transparency.
     */
    public CategoryMarker(Comparable key, int paint, float stroke,
            int outlinePaint, float outlineStroke, int alpha) {
        super(paint, stroke, outlinePaint, outlineStroke, alpha);
        this.key = key;
        setLabelOffsetType(LengthAdjustmentType.EXPAND);
    }

    /**
     * Returns the key.
     * 
     * @return The key.
     */
    public Comparable getKey() {
        return this.key;
    }

    /**
     * Sets the key and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     * 
     * @param key
     *            the key (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.3
     */
    public void setKey(Comparable key) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        this.key = key;
        notifyListeners(new MarkerChangeEvent(this));
    }

    /**
     * Returns the flag that controls whether the marker is drawn as a region or
     * a line.
     * 
     * @return A line.
     */
    public boolean getDrawAsLine() {
        return this.drawAsLine;
    }

    /**
     * Sets the flag that controls whether the marker is drawn as a region or as
     * a line, and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     * 
     * @param drawAsLine
     *            the flag.
     */
    public void setDrawAsLine(boolean drawAsLine) {
        this.drawAsLine = drawAsLine;
        notifyListeners(new MarkerChangeEvent(this));
    }

    /**
     * Tests the marker for equality with an arbitrary object.
     * 
     * @param obj
     *            the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CategoryMarker)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        CategoryMarker that = (CategoryMarker) obj;
        if (!this.key.equals(that.key)) {
            return false;
        }
        if (this.drawAsLine != that.drawAsLine) {
            return false;
        }
        return true;
    }

}
