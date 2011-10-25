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
 * SymbolAxis.java
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
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2002-2008, by Anthony Boulestreau and Contributors.
 *
 * Original Author:  Anthony Boulestreau;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 *
 * Changes
 * -------
 * 29-Mar-2002 : First version (AB);
 * 19-Apr-2002 : Updated formatting and import statements (DG);
 * 21-Jun-2002 : Make change to use the class TickUnit - remove valueToString()
 *               method and add SymbolicTickUnit (AB);
 * 25-Jun-2002 : Removed redundant code (DG);
 * 25-Jul-2002 : Changed order of parameters in ValueAxis constructor (DG);
 * 05-Sep-2002 : Updated constructor to reflect changes in the Axis class (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 14-Feb-2003 : Added back missing constructor code (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 14-May-2003 : Renamed HorizontalSymbolicAxis --> SymbolicAxis and merged in
 *               VerticalSymbolicAxis (DG);
 * 12-Aug-2003 : Fixed bug where refreshTicks() method has different signature
 *               to super class (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 02-Nov-2003 : Added code to avoid overlapping labels (MR);
 * 07-Nov-2003 : Modified to use new tick classes (DG);
 * 18-Nov-2003 : Fixed bug where symbols are not being displayed on the
 *               axis (DG);
 * 24-Nov-2003 : Added fix for gridlines on zooming (bug id 834643) (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 11-Mar-2004 : Modified the way the background grid color is being drawn, see
 *               this thread:
 *               http://www.jfree.org/phpBB2/viewtopic.php?p=22973 (DG);
 * 16-Mar-2004 : Added plotState to draw() method (DG);
 * 07-Apr-2004 : Modified string bounds calculation (DG);
 * 28-Mar-2005 : Renamed autoRangeIncludesZero() --> getAutoRangeIncludesZero()
 *               and autoRangeStickyZero() --> getAutoRangeStickyZero() (DG);
 * 05-Jul-2005 : Fixed signature on refreshTicks() method - see bug report
 *               1232264 (DG);
 * 06-Jul-2005 : Renamed SymbolicAxis --> SymbolAxis, added equals() method,
 *               renamed getSymbolicValue() --> getSymbols(), renamed
 *               symbolicGridPaint --> gridBandPaint, fixed serialization of
 *               gridBandPaint, renamed symbolicGridLinesVisible -->
 *               gridBandsVisible, eliminated symbolicGridLineList (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 28-Feb-2007 : Fixed bug 1669302 (tick label overlap) (DG);
 * 25-Jul-2007 : Added new field for alternate grid band paint (DG);
 * 15-Aug-2008 : Use alternate grid band paint when drawing (DG);
 *
 */

package org.afree.chart.axis;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.afree.util.PaintTypeUtilities;
import org.afree.ui.RectangleEdge;
import org.afree.ui.TextAnchor;
import org.afree.data.Range;
import org.afree.chart.event.AxisChangeEvent;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.ValueAxisPlot;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * A standard linear value axis that replaces integer values with symbols.
 */
public class SymbolAxis extends NumberAxis implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 7216330468770619716L;

    /** The default grid band paint. */
    public static final PaintType DEFAULT_GRID_BAND_PAINT_TYPE = new SolidColor(Color.argb(128, 232, 234, 232));

    /**
     * The default paint for alternate grid bands.
     *
     * @since JFreeChart 1.0.7
     */
    public static final PaintType DEFAULT_GRID_BAND_ALTERNATE_PAINT_TYPE = new SolidColor(Color.argb(0, 0, 0, 0));

    /** The list of symbols to display instead of the numeric values. */
    private List symbols;

    /** Flag that indicates whether or not grid bands are visible. */
    private boolean gridBandsVisible;

    /** The paint used to color the grid bands (if the bands are visible). */
    private transient PaintType gridBandPaintType;

    /**
     * The paint used to fill the alternate grid bands.
     *
     * @since JFreeChart 1.0.7
     */
    private transient PaintType gridBandAlternatePaintType;

    /**
     * Constructs a symbol axis, using default attribute values where
     * necessary.
     *
     * @param label  the axis label (<code>null</code> permitted).
     * @param sv  the list of symbols to display instead of the numeric
     *            values.
     */
    public SymbolAxis(String label, String[] sv) {
        super(label);
        this.symbols = Arrays.asList(sv);
        this.gridBandsVisible = true;
        this.gridBandPaintType = DEFAULT_GRID_BAND_PAINT_TYPE;
        this.gridBandAlternatePaintType = DEFAULT_GRID_BAND_ALTERNATE_PAINT_TYPE;
        setAutoTickUnitSelection(false, false);
        setAutoRangeStickyZero(false);

    }

    /**
     * Returns an array of the symbols for the axis.
     *
     * @return The symbols.
     */
    public String[] getSymbols() {
        String[] result = new String[this.symbols.size()];
        result = (String[]) this.symbols.toArray(result);
        return result;
    }

    /**
     * Returns <code>true</code> if the grid bands are showing, and
     * <code>false</code> otherwise.
     *
     * @return <code>true</code> if the grid bands are showing, and
     *         <code>false</code> otherwise.
     *
     * @see #setGridBandsVisible(boolean)
     */
    public boolean isGridBandsVisible() {
        return this.gridBandsVisible;
    }

    /**
     * Sets the visibility of the grid bands and notifies registered
     * listeners that the axis has been modified.
     *
     * @param flag  the new setting.
     *
     * @see #isGridBandsVisible()
     */
    public void setGridBandsVisible(boolean flag) {
        if (this.gridBandsVisible != flag) {
            this.gridBandsVisible = flag;
            notifyListeners(new AxisChangeEvent(this));
        }
    }

    /**
     * Returns the paint used to color the grid bands.
     *
     * @return The grid band paint (never <code>null</code>).
     *
     * @see #setGridBandPaintType(PaintType paintType)
     * @see #isGridBandsVisible()
     */
    public PaintType getGridBandPaintType() {
        return this.gridBandPaintType;
    }

    /**
     * Sets the grid band paint and sends an {@link AxisChangeEvent} to
     * all registered listeners.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getGridBandPaintType()
     */
    public void setGridBandPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.gridBandPaintType = paintType;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the paint used for alternate grid bands.
     *
     * @return The paint type (never <code>null</code>).
     *
     * @see #setGridBandAlternatePaintType(PaintType paintType)
     * @see #getGridBandPaintType()
     *
     * @since JFreeChart 1.0.7
     */
    public PaintType getGridBandAlternatePaintType() {
        return this.gridBandAlternatePaintType;
    }

    /**
     * Sets the paint used for alternate grid bands and sends a
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getGridBandAlternatePaintType()
     * @see #setGridBandPaintType(PaintType paintType)
     *
     * @since JFreeChart 1.0.7
     */
    public void setGridBandAlternatePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.gridBandAlternatePaintType = paintType;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * This operation is not supported by this axis.
     *
     * @param canvas  the graphics device.
     * @param dataArea  the area in which the plot and axes should be drawn.
     * @param edge  the edge along which the axis is drawn.
     */
    protected void selectAutoTickUnit(Canvas canvas, RectShape dataArea,
                                      RectangleEdge edge) {
        throw new UnsupportedOperationException();
    }

    /**
     * Draws the axis on a graphics device (such as the screen or a
     * printer).
     *
     * @param canvas  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location.
     * @param plotArea  the area within which the plot and axes should be drawn
     *                  (<code>null</code> not permitted).
     * @param dataArea  the area within which the data should be drawn
     *                  (<code>null</code> not permitted).
     * @param edge  the axis location (<code>null</code> not permitted).
     * @param plotState  collects information about the plot
     *                   (<code>null</code> permitted).
     *
     * @return The axis state (never <code>null</code>).
     */
    public AxisState draw(Canvas canvas,
                          double cursor,
                          RectShape plotArea,
                          RectShape dataArea,
                          RectangleEdge edge,
                          PlotRenderingInfo plotState) {

        AxisState info = new AxisState(cursor);
        if (isVisible()) {
            info = super.draw(canvas, cursor, plotArea, dataArea, edge, plotState);
        }
        if (this.gridBandsVisible) {
            drawGridBands(canvas, plotArea, dataArea, edge, info.getTicks());
        }
        return info;

    }

    /**
     * Draws the grid bands.  Alternate bands are colored using
     * <CODE>gridBandPaint<CODE> (<CODE>DEFAULT_GRID_BAND_PAINT</CODE> by
     * default).
     *
     * @param canvas  the graphics device.
     * @param plotArea  the area within which the chart should be drawn.
     * @param dataArea  the area within which the plot should be drawn (a
     *                  subset of the drawArea).
     * @param edge  the axis location.
     * @param ticks  the ticks.
     */
    protected void drawGridBands(Canvas canvas,
                                 RectShape plotArea,
                                 RectShape dataArea,
                                 RectangleEdge edge,
                                 List ticks) {

        canvas.save();
        canvas.clipRect(dataArea.getRectF());
        if (RectangleEdge.isTopOrBottom(edge)) {
            drawGridBandsHorizontal(canvas, plotArea, dataArea, true, ticks);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            drawGridBandsVertical(canvas, plotArea, dataArea, true, ticks);
        }
        canvas.restore();

    }

    /**
     * Draws the grid bands for the axis when it is at the top or bottom of
     * the plot.
     *
     * @param canvas  the graphics device.
     * @param plotArea  the area within which the chart should be drawn.
     * @param dataArea  the area within which the plot should be drawn
     *                  (a subset of the drawArea).
     * @param firstGridBandIsDark  True: the first grid band takes the
     *                             color of <CODE>gridBandPaint<CODE>.
     *                             False: the second grid band takes the
     *                             color of <CODE>gridBandPaint<CODE>.
     * @param ticks  the ticks.
     */
    protected void drawGridBandsHorizontal(Canvas canvas,
                                           RectShape plotArea,
                                           RectShape dataArea,
                                           boolean firstGridBandIsDark,
                                           List ticks) {

        boolean currentGridBandIsDark = firstGridBandIsDark;
        double yy = dataArea.getY();
        double xx1, xx2;

        //gets the outline stroke width of the plot
        float outlineStrokeWidth;
        if (getPlot().getOutlineStroke() !=  0.0f) {
            outlineStrokeWidth
                = getPlot().getOutlineStroke();
        }
        else {
            outlineStrokeWidth = 1f;
        }

        Iterator iterator = ticks.iterator();
        ValueTick tick;
        RectShape band;
        while (iterator.hasNext()) {
            tick = (ValueTick) iterator.next();
            xx1 = valueToJava2D(tick.getValue() - 0.5d, dataArea,
                    RectangleEdge.BOTTOM);
            xx2 = valueToJava2D(tick.getValue() + 0.5d, dataArea,
                    RectangleEdge.BOTTOM);
            PaintType paintType;
            if (currentGridBandIsDark) {
                paintType = this.gridBandPaintType;
            }
            else {
                paintType = this.gridBandAlternatePaintType;
            }
            Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, paintType);
            band = new RectShape(xx1, yy + outlineStrokeWidth,
                xx2 - xx1, dataArea.getMaxY() - yy - outlineStrokeWidth);
            band.fill(canvas, paint);
            currentGridBandIsDark = !currentGridBandIsDark;
        }
    }

    /**
     * Draws the grid bands for the axis when it is at the top or bottom of
     * the plot.
     *
     * @param canvas  the graphics device.
     * @param drawArea  the area within which the chart should be drawn.
     * @param plotArea  the area within which the plot should be drawn (a
     *                  subset of the drawArea).
     * @param firstGridBandIsDark  True: the first grid band takes the
     *                             color of <CODE>gridBandPaint<CODE>.
     *                             False: the second grid band takes the
     *                             color of <CODE>gridBandPaint<CODE>.
     * @param ticks  a list of ticks.
     */
    protected void drawGridBandsVertical(Canvas canvas,
                                         RectShape drawArea,
                                         RectShape plotArea,
                                         boolean firstGridBandIsDark,
                                         List ticks) {

        boolean currentGridBandIsDark = firstGridBandIsDark;
        double xx = plotArea.getX();
        double yy1, yy2;

        //gets the outline stroke width of the plot
        double outlineStrokeWidth;
        float outlineStroke = getPlot().getOutlineStroke();
        if (outlineStroke != 0.0f) {
            outlineStrokeWidth = outlineStroke;
        }
        else {
            outlineStrokeWidth = 1f;
        }

        Iterator iterator = ticks.iterator();
        ValueTick tick;
        RectShape band;
        while (iterator.hasNext()) {
            tick = (ValueTick) iterator.next();
            yy1 = valueToJava2D(tick.getValue() + 0.5d, plotArea,
                    RectangleEdge.LEFT);
            yy2 = valueToJava2D(tick.getValue() - 0.5d, plotArea,
                    RectangleEdge.LEFT);
            PaintType paintType;
            if (currentGridBandIsDark) {
                paintType = this.gridBandPaintType;
            }
            else {
                paintType = this.gridBandAlternatePaintType;
            }
            Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, paintType);
           
            band = new RectShape(xx + outlineStrokeWidth, yy1,
                    plotArea.getMaxX() - xx - outlineStrokeWidth, yy2 - yy1);
            band.fill(canvas, paint);
            currentGridBandIsDark = !currentGridBandIsDark;
        }
    }

    /**
     * Rescales the axis to ensure that all data is visible.
     */
    protected void autoAdjustRange() {

        Plot plot = getPlot();
        if (plot == null) {
            return;  // no plot, no data
        }

        if (plot instanceof ValueAxisPlot) {

            // ensure that all the symbols are displayed
            double upper = this.symbols.size() - 1;
            double lower = 0;
            double range = upper - lower;

            // ensure the autorange is at least <minRange> in size...
            double minRange = getAutoRangeMinimumSize();
            if (range < minRange) {
                upper = (upper + lower + minRange) / 2;
                lower = (upper + lower - minRange) / 2;
            }

            // this ensure that the grid bands will be displayed correctly.
            double upperMargin = 0.5;
            double lowerMargin = 0.5;

            if (getAutoRangeIncludesZero()) {
                if (getAutoRangeStickyZero()) {
                    if (upper <= 0.0) {
                        upper = 0.0;
                    }
                    else {
                        upper = upper + upperMargin;
                    }
                    if (lower >= 0.0) {
                        lower = 0.0;
                    }
                    else {
                        lower = lower - lowerMargin;
                    }
                }
                else {
                    upper = Math.max(0.0, upper + upperMargin);
                    lower = Math.min(0.0, lower - lowerMargin);
                }
            }
            else {
                if (getAutoRangeStickyZero()) {
                    if (upper <= 0.0) {
                        upper = Math.min(0.0, upper + upperMargin);
                    }
                    else {
                        upper = upper + upperMargin * range;
                    }
                    if (lower >= 0.0) {
                        lower = Math.max(0.0, lower - lowerMargin);
                    }
                    else {
                        lower = lower - lowerMargin;
                    }
                }
                else {
                    upper = upper + upperMargin;
                    lower = lower - lowerMargin;
                }
            }

            setRange(new Range(lower, upper), false, false);

        }

    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the
     * results in the tick label list (ready for drawing).
     *
     * @param canvas  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the area in which the data should be drawn.
     * @param edge  the location of the axis.
     *
     * @return A list of ticks.
     */
    public List refreshTicks(Canvas canvas,
                             AxisState state,
                             RectShape dataArea,
                             RectangleEdge edge) {
        List ticks = null;
        if (RectangleEdge.isTopOrBottom(edge)) {
            ticks = refreshTicksHorizontal(canvas, dataArea, edge);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            ticks = refreshTicksVertical(canvas, dataArea, edge);
        }
        return ticks;
    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the
     * results in the tick label list (ready for drawing).
     *
     * @param canvas  the graphics device.
     * @param dataArea  the area in which the data should be drawn.
     * @param edge  the location of the axis.
     *
     * @return The ticks.
     */
    protected List refreshTicksHorizontal(Canvas canvas,
                                          RectShape dataArea,
                                          RectangleEdge edge) {

        List ticks = new java.util.ArrayList();

        Paint labelPaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getTickLabelPaintType(), getTickLabelFont());
        double size = getTickUnit().getSize();
        int count = calculateVisibleTickCount();
        double lowestTickValue = calculateLowestVisibleTickValue();

        double previousDrawnTickLabelPos = 0.0;
        double previousDrawnTickLabelLength = 0.0;

        if (count <= ValueAxis.MAXIMUM_TICK_COUNT) {
            for (int i = 0; i < count; i++) {
                double currentTickValue = lowestTickValue + (i * size);
                double xx = valueToJava2D(currentTickValue, dataArea, edge);
                String tickLabel;
                NumberFormat formatter = getNumberFormatOverride();
                if (formatter != null) {
                    tickLabel = formatter.format(currentTickValue);
                }
                else {
                    tickLabel = valueToString(currentTickValue);
                }

                // avoid to draw overlapping tick labels
                RectShape bounds = TextUtilities.getTextBounds(tickLabel, labelPaint);
                double tickLabelLength = isVerticalTickLabels()
                        ? bounds.getHeight() : bounds.getWidth();
                boolean tickLabelsOverlapping = false;
                if (i > 0) {
                    double avgTickLabelLength = (previousDrawnTickLabelLength
                            + tickLabelLength) / 2.0;
                    if (Math.abs(xx - previousDrawnTickLabelPos)
                            < avgTickLabelLength) {
                        tickLabelsOverlapping = true;
                    }
                }
                if (tickLabelsOverlapping) {
                    tickLabel = ""; // don't draw this tick label
                }
                else {
                    // remember these values for next comparison
                    previousDrawnTickLabelPos = xx;
                    previousDrawnTickLabelLength = tickLabelLength;
                }

                TextAnchor anchor = null;
                TextAnchor rotationAnchor = null;
                double angle = 0.0;
                if (isVerticalTickLabels()) {
                    anchor = TextAnchor.CENTER_RIGHT;
                    rotationAnchor = TextAnchor.CENTER_RIGHT;
                    if (edge == RectangleEdge.TOP) {
                        angle = Math.PI / 2.0;
                    }
                    else {
                        angle = -Math.PI / 2.0;
                    }
                }
                else {
                    if (edge == RectangleEdge.TOP) {
                        anchor = TextAnchor.BOTTOM_CENTER;
                        rotationAnchor = TextAnchor.BOTTOM_CENTER;
                    }
                    else {
                        anchor = TextAnchor.TOP_CENTER;
                        rotationAnchor = TextAnchor.TOP_CENTER;
                    }
                }
                Tick tick = new NumberTick(new Double(currentTickValue),
                        tickLabel, anchor, rotationAnchor, angle);
                ticks.add(tick);
            }
        }
        return ticks;

    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the
     * results in the tick label list (ready for drawing).
     *
     * @param canvas  the graphics device.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the location of the axis.
     *
     * @return The ticks.
     */
    protected List refreshTicksVertical(Canvas canvas,
                                        RectShape dataArea,
                                        RectangleEdge edge) {

        List ticks = new java.util.ArrayList();

        Paint labelPaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getTickLabelPaintType(), getTickLabelFont());

        double size = getTickUnit().getSize();
        int count = calculateVisibleTickCount();
        double lowestTickValue = calculateLowestVisibleTickValue();

        double previousDrawnTickLabelPos = 0.0;
        double previousDrawnTickLabelLength = 0.0;

        if (count <= ValueAxis.MAXIMUM_TICK_COUNT) {
            for (int i = 0; i < count; i++) {
                double currentTickValue = lowestTickValue + (i * size);
                double yy = valueToJava2D(currentTickValue, dataArea, edge);
                String tickLabel;
                NumberFormat formatter = getNumberFormatOverride();
                if (formatter != null) {
                    tickLabel = formatter.format(currentTickValue);
                }
                else {
                    tickLabel = valueToString(currentTickValue);
                }

                // avoid to draw overlapping tick labels
                RectShape bounds = TextUtilities.getTextBounds(tickLabel, labelPaint);
                double tickLabelLength = isVerticalTickLabels()
                    ? bounds.getWidth() : bounds.getHeight();
                boolean tickLabelsOverlapping = false;
                if (i > 0) {
                    double avgTickLabelLength = (previousDrawnTickLabelLength
                            + tickLabelLength) / 2.0;
                    if (Math.abs(yy - previousDrawnTickLabelPos)
                            < avgTickLabelLength) {
                        tickLabelsOverlapping = true;
                    }
                }
                if (tickLabelsOverlapping) {
                    tickLabel = ""; // don't draw this tick label
                }
                else {
                    // remember these values for next comparison
                    previousDrawnTickLabelPos = yy;
                    previousDrawnTickLabelLength = tickLabelLength;
                }

                TextAnchor anchor = null;
                TextAnchor rotationAnchor = null;
                double angle = 0.0;
                if (isVerticalTickLabels()) {
                    anchor = TextAnchor.BOTTOM_CENTER;
                    rotationAnchor = TextAnchor.BOTTOM_CENTER;
                    if (edge == RectangleEdge.LEFT) {
                        angle = -Math.PI / 2.0;
                    }
                    else {
                        angle = Math.PI / 2.0;
                    }
                }
                else {
                    if (edge == RectangleEdge.LEFT) {
                        anchor = TextAnchor.CENTER_RIGHT;
                        rotationAnchor = TextAnchor.CENTER_RIGHT;
                    }
                    else {
                        anchor = TextAnchor.CENTER_LEFT;
                        rotationAnchor = TextAnchor.CENTER_LEFT;
                    }
                }
                Tick tick = new NumberTick(new Double(currentTickValue),
                        tickLabel, anchor, rotationAnchor, angle);
                ticks.add(tick);
            }
        }
        return ticks;

    }

    /**
     * Converts a value to a string, using the list of symbols.
     *
     * @param value  value to convert.
     *
     * @return The symbol.
     */
    public String valueToString(double value) {
        String strToReturn;
        try {
            strToReturn = (String) this.symbols.get((int) value);
        }
        catch (IndexOutOfBoundsException  ex) {
            strToReturn = "";
        }
        return strToReturn;
    }

    /**
     * Tests this axis for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SymbolAxis)) {
            return false;
        }
        SymbolAxis that = (SymbolAxis) obj;
        if (!this.symbols.equals(that.symbols)) {
            return false;
        }
        if (this.gridBandsVisible != that.gridBandsVisible) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.gridBandPaintType, that.gridBandPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.gridBandAlternatePaintType,
                that.gridBandAlternatePaintType)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    // TODO: implement writeObject
//    private void writeObject(ObjectOutputStream stream) throws IOException {
//        stream.defaultWriteObject();
//        SerialUtilities.writePaint(this.gridBandPaint, stream);
//        SerialUtilities.writePaint(this.gridBandAlternatePaint, stream);
//    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    // TODO: implement readObject
//    private void readObject(ObjectInputStream stream)
//        throws IOException, ClassNotFoundException {
//        stream.defaultReadObject();
//        this.gridBandPaint = SerialUtilities.readPaint(stream);
//        this.gridBandAlternatePaint = SerialUtilities.readPaint(stream);
//    }

}
