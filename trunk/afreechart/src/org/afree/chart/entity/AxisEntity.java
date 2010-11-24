/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
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
 * ----------------
 * AxisEntity.java
 * ----------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  Peter Kolb;
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes:
 * --------
 * 15-Feb-2009 : Version 1 (PK);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.chart.entity;

import org.afree.chart.axis.Axis;
import org.afree.graphics.geom.Shape;

/**
 * A class that captures information about an Axis of a chart.
 * 
 * @since JFreeChart 1.0.13
 */
public class AxisEntity extends ChartEntity {

    /** For serialization. */
    private static final long serialVersionUID = -4445994133561919083L;
    // same as for ChartEntity!

    /** The axis for the entity. */
    private Axis axis;

    /**
     * Creates a new axis entity.
     * 
     * @param area
     *            the area (<code>null</code> not permitted).
     * @param axis
     *            the axis (<code>null</code> not permitted).
     */
    public AxisEntity(Shape area, Axis axis) {
        // defer argument checks...
        this(area, axis, null);
    }

    /**
     * Creates a new axis entity.
     * 
     * @param area
     *            the area (<code>null</code> not permitted).
     * @param axis
     *            the axis (<code>null</code> not permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     */
    public AxisEntity(Shape area, Axis axis, String toolTipText) {
        // defer argument checks...
        this(area, axis, toolTipText, null);
    }

    /**
     * Creates a new axis entity.
     * 
     * @param area
     *            the area (<code>null</code> not permitted).
     * @param axis
     *            the axis (<code>null</code> not permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     * @param urlText
     *            the URL text for HTML image maps (<code>null</code>
     *            permitted).
     */
    public AxisEntity(Shape area, Axis axis, String toolTipText, String urlText) {
        super(area, toolTipText, urlText);
        if (axis == null) {
            throw new IllegalArgumentException("Null 'axis' argument.");
        }

        this.axis = axis;
    }

    /**
     * Returns the axis that occupies the entity area.
     * 
     * @return The axis (never <code>null</code>).
     */
    public Axis getAxis() {
        return this.axis;
    }

    /**
     * Returns a string representation of the chart entity, useful for
     * debugging.
     * 
     * @return A string.
     */
    public String toString() {
        StringBuffer buf = new StringBuffer("AxisEntity: ");
        buf.append("tooltip = ");
        buf.append(getToolTipText());
        return buf.toString();
    }

}
