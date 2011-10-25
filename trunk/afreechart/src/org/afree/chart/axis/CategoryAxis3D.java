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
 * -------------------
 * CategoryAxis3D.java
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
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2003-2009, by Klaus Rheinwald and Contributors.
 *
 * Original Author:  Klaus Rheinwald;
 * Contributor(s):   Tin Luu,
 *                   David Gilbert (for Object Refinery Limited);
 *                   Adriaan Joubert;
 *
 * Changes
 * -------
 * 19-Feb-2003 : File creation;
 * 21-Mar-2003 : Added to JFreeChart CVS, see bug id 685501 for code
 *               contribution from KR (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 13-May-2003 : Renamed HorizontalCategoryAxis3D --> CategoryAxis3D, and
 *               modified to take into account the plot orientation (DG);
 * 14-Aug-2003 : Implemented Cloneable (DG);
 * 21-Aug-2003 : Fixed draw() method bugs (DG);
 * 22-Mar-2004 : Added workaround for bug 920959 (null pointer exception with
 *               no renderer) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-Aug-2006 : Fix for bug drawing category labels, thanks to Adriaan
 *               Joubert (1277726) (DG);
 * 16-Apr-2009 : Draw axis line and tick marks (DG);
 *
 */

package org.afree.chart.axis;

import java.io.Serializable;

import android.graphics.Canvas;

import org.afree.ui.RectangleEdge;
import org.afree.chart.Effect3D;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.renderer.category.CategoryItemRenderer;
import org.afree.graphics.geom.RectShape;

/**
 * An axis that displays categories and has a 3D effect.
 * Used for bar charts and line charts.
 */
public class CategoryAxis3D extends CategoryAxis
        implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 4114732251353700972L;

    /**
     * Creates a new axis.
     */
    public CategoryAxis3D() {
        this(null);
    }

    /**
     * Creates a new axis using default attribute values.
     *
     * @param label  the axis label (<code>null</code> permitted).
     */
    public CategoryAxis3D(String label) {
        super(label);
    }

    /**
     * Draws the axis on a graphics device (such as the screen or a
     * printer).
     *
     * @param canvas  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location.
     * @param plotArea  the area within which the axis should be drawn
     *                  (<code>null</code> not permitted).
     * @param dataArea  the area within which the plot is being drawn
     *                  (<code>null</code> not permitted).
     * @param edge  the location of the axis (<code>null</code> not permitted).
     * @param plotState  collects information about the plot (<code>null</code>
     *                   permitted).
     *
     * @return The axis state (never <code>null</code>).
     */
    public AxisState draw(Canvas canvas,
                          double cursor,
                          RectShape plotArea,
                          RectShape dataArea,
                          RectangleEdge edge,
                          PlotRenderingInfo plotState) {

        // if the axis is not visible, don't draw it...
        if (!isVisible()) {
            return new AxisState(cursor);
        }

        // calculate the adjusted data area taking into account the 3D effect...
        // this assumes that there is a 3D renderer, all this 3D effect is a
        // bit of an ugly hack...
        CategoryPlot plot = (CategoryPlot) getPlot();

        RectShape adjustedDataArea = new RectShape();
        if (plot.getRenderer() instanceof Effect3D) {
            Effect3D e3D = (Effect3D) plot.getRenderer();
            double adjustedX = dataArea.getMinX();
            double adjustedY = dataArea.getMinY();
            double adjustedW = dataArea.getWidth() - e3D.getXOffset();
            double adjustedH = dataArea.getHeight() - e3D.getYOffset();

            if (edge == RectangleEdge.LEFT || edge == RectangleEdge.BOTTOM) {
                adjustedY += e3D.getYOffset();
            }
            else if (edge == RectangleEdge.RIGHT || edge == RectangleEdge.TOP) {
                adjustedX += e3D.getXOffset();
            }
            adjustedDataArea.setRect(adjustedX, adjustedY, adjustedW,
                    adjustedH);
        }
        else {
            adjustedDataArea.setRect(dataArea);
        }

        if (isAxisLineVisible()) {
            drawAxisLine(canvas, cursor, adjustedDataArea, edge);
        }
        // draw the category labels and axis label
        AxisState state = new AxisState(cursor);
        if (isTickMarksVisible()) {
            drawTickMarks(canvas, cursor, adjustedDataArea, edge, state);
        }
        state = drawCategoryLabels(canvas, plotArea, adjustedDataArea, edge,
                state, plotState);
        state = drawLabel(getLabel(), canvas, plotArea, dataArea, edge, state);

        return state;

    }

    /**
     * Returns the Java 2D coordinate for a category.
     *
     * @param anchor  the anchor point.
     * @param category  the category index.
     * @param categoryCount  the category count.
     * @param area  the data area.
     * @param edge  the location of the axis.
     *
     * @return The coordinate.
     */
    public double getCategoryJava2DCoordinate(CategoryAnchor anchor,
                                              int category,
                                              int categoryCount,
                                              RectShape area,
                                              RectangleEdge edge) {

        double result = 0.0;
        RectShape adjustedArea = area;
        CategoryPlot plot = (CategoryPlot) getPlot();
        CategoryItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof Effect3D) {
            Effect3D e3D = (Effect3D) renderer;
            double adjustedX = area.getMinX();
            double adjustedY = area.getMinY();
            double adjustedW = area.getWidth() - e3D.getXOffset();
            double adjustedH = area.getHeight() - e3D.getYOffset();

            if (edge == RectangleEdge.LEFT || edge == RectangleEdge.BOTTOM) {
                adjustedY += e3D.getYOffset();
            }
            else if (edge == RectangleEdge.RIGHT || edge == RectangleEdge.TOP) {
                adjustedX += e3D.getXOffset();
            }
            adjustedArea = new RectShape(adjustedX, adjustedY,
                    adjustedW, adjustedH);
        }

        if (anchor == CategoryAnchor.START) {
            result = getCategoryStart(category, categoryCount, adjustedArea,
                    edge);
        }
        else if (anchor == CategoryAnchor.MIDDLE) {
            result = getCategoryMiddle(category, categoryCount, adjustedArea,
                    edge);
        }
        else if (anchor == CategoryAnchor.END) {
            result = getCategoryEnd(category, categoryCount, adjustedArea,
                    edge);
        }
        return result;

    }

    /**
     * Returns a clone of the axis.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException If the axis is not cloneable for
     *         some reason.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
