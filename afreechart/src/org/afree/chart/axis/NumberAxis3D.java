/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
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
 * -----------------
 * NumberAxis3D.java
 * -----------------
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
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2001-2009, by Serge V. Grachov and Contributors.
 *
 * Original Author:  Serge V. Grachov;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Jonathan Nash;
 *                   Richard Atkinson;
 *                   Tin Luu;
 *
 * Changes
 * -------
 * 31-Oct-2001 : Version 1 contributed by Serge V. Grachov (DG);
 * 23-Nov-2001 : Overhauled auto tick unit code for all axes (DG);
 * 12-Dec-2001 : Minor change due to grid lines bug fix (DG);
 * 08-Jan-2002 : Added flag allowing the axis to be 'inverted'.  That is, run
 *               from positive to negative.  Added default values to
 *               constructors (DG);
 * 16-Jan-2002 : Added an optional crosshair, based on the implementation by
 *               Jonathan Nash (DG);
 * 25-Feb-2002 : Updated constructors for new autoRangeStickyZero flag (DG);
 * 19-Apr-2002 : drawVerticalString() is now drawRotatedString() in
 *               RefineryUtilities (DG);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 25-Jul-2002 : Changed order of parameters in ValueAxis constructor (DG);
 * 06-Aug-2002 : Modified draw method to not draw axis label if label is empty
 *               String (RA);
 * 05-Sep-2002 : Updated constructor for changes in the Axis class, and changed
 *               draw method to observe tickMarkPaint (DG);
 * 22-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 20-Jan-2003 : Removed unnecessary constructors (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 13-May-2003 : Merged HorizontalNumberAxis3D and VerticalNumberAxis3D (DG);
 * 21-Aug-2003 : Updated draw() method signature (DG);
 * 07-Nov-2003 : Modified refreshTicks method signature (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-Jan-2006 : Fixed bug 1408904 (axis assumes CategoryPlot) (DG):
 * 16-Apr-2009 : Changed axis line visibility default (DG);
 *
 */

package org.afree.chart.axis;

import java.io.Serializable;
import java.util.List;

import android.graphics.Canvas;

import org.afree.ui.RectangleEdge;
import org.afree.chart.Effect3D;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.renderer.category.CategoryItemRenderer;
import org.afree.graphics.geom.RectShape;

/**
 * A standard linear value axis with a 3D effect corresponding to the
 * offset specified by some renderers.
 */
public class NumberAxis3D extends NumberAxis implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -1790205852569123512L;

    /**
     * Default constructor.
     */
    public NumberAxis3D() {
        this(null);
    }

    /**
     * Constructs a new axis.
     *
     * @param label  the axis label (<code>null</code> permitted).
     */
    public NumberAxis3D(String label) {
        super(label);
    }

    /**
     * Draws the axis on a graphics device (such as the screen or a
     * printer).
     *
     * @param canvas  the graphics device.
     * @param cursor  the cursor.
     * @param plotArea  the area for drawing the axes and data.
     * @param dataArea  the area for drawing the data (a subset of the
     *                  plotArea).
     * @param edge  the axis location.
     * @param plotState  collects information about the plot (<code>null</code>
     *                   permitted).
     *
     * @return The updated cursor value.
     */
    public AxisState draw(Canvas canvas, double cursor, RectShape plotArea,
            RectShape dataArea, RectangleEdge edge,
            PlotRenderingInfo plotState) {

        // if the axis is not visible, don't draw it...
        if (!isVisible()) {
            AxisState state = new AxisState(cursor);
            // even though the axis is not visible, we need ticks for the
            // gridlines...
            List ticks = refreshTicks(canvas, state, dataArea, edge);
            state.setTicks(ticks);
            return state;
        }

        // calculate the adjusted data area taking into account the 3D effect...
        double xOffset = 0.0;
        double yOffset = 0.0;
        Plot plot = getPlot();
        if (plot instanceof CategoryPlot) {
            CategoryPlot cp = (CategoryPlot) plot;
            CategoryItemRenderer r = cp.getRenderer();
            if (r instanceof Effect3D) {
                Effect3D e3D = (Effect3D) r;
                xOffset = e3D.getXOffset();
                yOffset = e3D.getYOffset();
            }
        }

        double adjustedX = dataArea.getMinX();
        double adjustedY = dataArea.getMinY();
        double adjustedW = dataArea.getWidth() - xOffset;
        double adjustedH = dataArea.getHeight() - yOffset;

        if (edge == RectangleEdge.LEFT || edge == RectangleEdge.BOTTOM) {
            adjustedY += yOffset;
        }
        else if (edge == RectangleEdge.RIGHT || edge == RectangleEdge.TOP) {
            adjustedX += xOffset;
        }
        RectShape adjustedDataArea = new RectShape(adjustedX,
                adjustedY, adjustedW, adjustedH);

        // draw the tick marks and labels...
        AxisState info = drawTickMarksAndLabels(canvas, cursor, plotArea,
                adjustedDataArea, edge);

        // draw the axis label...
        info = drawLabel(getLabel(), canvas, plotArea, dataArea, edge, info);

        return info;

    }

}
