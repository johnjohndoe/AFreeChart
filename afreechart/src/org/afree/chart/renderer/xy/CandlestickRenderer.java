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
 * ------------------------
 * CandlestickRenderer.java
 * ------------------------
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
 * 16-Dec-2010 : performance tuning
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2001-2009, by Object Refinery Limited.
 *
 * Original Authors:  David Gilbert (for Object Refinery Limited);
 *                    Sylvain Vieujot;
 * Contributor(s):    Richard Atkinson;
 *                    Christian W. Zuckschwerdt;
 *                    Jerome Fisher;
 *
 * Changes
 * -------
 * 13-Dec-2001 : Version 1.  Based on code in the (now redundant)
 *               CandlestickPlot class, written by Sylvain Vieujot (DG);
 * 23-Jan-2002 : Added DrawInfo parameter to drawItem() method (DG);
 * 28-Mar-2002 : Added a property change listener mechanism so that renderers
 *               no longer need to be immutable.  Added properties for up and
 *               down colors (DG);
 * 04-Apr-2002 : Updated with new automatic width calculation and optional
 *               volume display, contributed by Sylvain Vieujot (DG);
 * 09-Apr-2002 : Removed translatedRangeZero from the drawItem() method, and
 *               changed the return type of the drawItem method to void,
 *               reflecting a change in the XYItemRenderer interface.  Added
 *               tooltip code to drawItem() method (DG);
 * 25-Jun-2002 : Removed redundant code (DG);
 * 05-Aug-2002 : Small modification to drawItem method to support URLs for HTML
 *               image maps (RA);
 * 19-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 30-Jun-2003 : Added support for PlotOrientation (for completeness, this
 *               renderer is unlikely to be used with a HORIZONTAL
 *               orientation) (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 29-Aug-2003 : Moved maxVolume calculation to initialise method (see bug
 *               report 796619) (DG);
 * 02-Sep-2003 : Added maxCandleWidthInMilliseconds as workaround for bug
 *               796621 (DG);
 * 08-Sep-2003 : Changed ValueAxis API (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 13-Oct-2003 : Applied patch from Jerome Fisher to improve auto width
 *               calculations (DG);
 * 23-Dec-2003 : Fixed bug where up and down paint are used incorrectly (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with
 *               getYValue() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Jul-2006 : Swapped calls to getX() --> getXValue(), and the same for the
 *               other data values (DG);
 * 17-Aug-2006 : Corrections to the equals() method (DG);
 * 05-Mar-2007 : Added flag to allow optional use of outline paint (DG);
 * 08-Oct-2007 : Added new volumePaint field (DG);
 * 08-Apr-2008 : Added findRangeBounds() method override (DG);
 * 13-May-2008 : Fixed chart entity bugs (1962467 and 1962472) (DG);
 * 27-Mar-2009 : Updated findRangeBounds() to call new method in
 *               superclass (DG);
 *
 */

package org.afree.chart.renderer.xy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.afree.ui.RectangleEdge;
import org.afree.io.SerialUtilities;
import org.afree.chart.axis.ValueAxis;
import org.afree.data.xy.IntervalXYDataset;
import org.afree.data.xy.OHLCDataset;
import org.afree.data.Range;
import org.afree.data.xy.XYDataset;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.event.RendererChangeEvent;
import org.afree.chart.plot.CrosshairState;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.XYPlot;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * A renderer that draws candlesticks on an {@link XYPlot} (requires a
 * {@link OHLCDataset}).  The example shown here is generated
 * by the <code>CandlestickChartDemo1.java</code> program included in the
 * AFreeChart demo collection:
 * <br><br>
 * <img src="../../../../../images/CandlestickRendererSample.png"
 * alt="CandlestickRendererSample.png" />
 * <P>
 * This renderer does not include code to calculate the crosshair point for the
 * plot.
 */
public class CandlestickRenderer extends AbstractXYItemRenderer
        implements XYItemRenderer, Cloneable, /*PublicCloneable,*/ Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 50390395841817121L;

    /** The average width method. */
    public static final int WIDTHMETHOD_AVERAGE = 0;

    /** The smallest width method. */
    public static final int WIDTHMETHOD_SMALLEST = 1;

    /** The interval data method. */
    public static final int WIDTHMETHOD_INTERVALDATA = 2;

    /** The method of automatically calculating the candle width. */
    private int autoWidthMethod = WIDTHMETHOD_AVERAGE;

    /**
     * The number (generally between 0.0 and 1.0) by which the available space
     * automatically calculated for the candles will be multiplied to determine
     * the actual width to use.
     */
    private double autoWidthFactor = 4.5 / 7;

    /** The minimum gap between one candle and the next */
    private double autoWidthGap = 0.0;

    /** The candle width. */
    private double candleWidth;

    /** The maximum candlewidth in milliseconds. */
    private double maxCandleWidthInMilliseconds = 1000.0 * 60.0 * 60.0 * 20.0;

    /** Temporary storage for the maximum candle width. */
    private double maxCandleWidth;

    /**
     * The paint used to fill the candle when the price moved up from open to
     * close.
     */
    private transient PaintType upPaintType;

    /**
     * The paint used to fill the candle when the price moved down from open
     * to close.
     */
    private transient PaintType downPaintType;

    /** A flag controlling whether or not volume bars are drawn on the chart. */
    private boolean drawVolume;

    /**
     * The paint used to fill the volume bars (if they are visible).  Once
     * initialised, this field should never be set to <code>null</code>.
     *
     * @since JFreeChart 1.0.7
     */
    private transient PaintType volumePaintType;

    /** Temporary storage for the maximum volume. */
    private transient double maxVolume;

    /**
     * A flag that controls whether or not the renderer's outline paint is
     * used to draw the outline of the candlestick.  The default value is
     * <code>false</code> to avoid a change of behaviour for existing code.
     *
     * @since JFreeChart 1.0.5
     */
    private boolean useOutlinePaint;

    /** work Paint object. */
    private Paint mWorkPaintVolume = new Paint(0);
    private Paint mWorkPaint = new Paint(0);
    
    /** work RectShape object */
    private RectShape mWorkRectShape = new RectShape();
    
    /** work LineShape object */
    private LineShape mWorkLineShape = new LineShape();
    
    /**
     * Creates a new renderer for candlestick charts.
     */
    public CandlestickRenderer() {
        this(-1.0);
    }

    /**
     * Creates a new renderer for candlestick charts.
     * <P>
     * Use -1 for the candle width if you prefer the width to be calculated
     * automatically.
     *
     * @param candleWidth  The candle width.
     */
    public CandlestickRenderer(double candleWidth) {
//        this(candleWidth, true, new HighLowItemLabelGenerator());
        this(candleWidth, true);        
    }

    /**
     * Creates a new renderer for candlestick charts.
     * <P>
     * Use -1 for the candle width if you prefer the width to be calculated
     * automatically.
     *
     * @param candleWidth  the candle width.
     * @param drawVolume  a flag indicating whether or not volume bars should
     *                    be drawn.
     */
    public CandlestickRenderer(double candleWidth, boolean drawVolume) {
        super();
        //setBaseToolTipGenerator(toolTipGenerator);
        this.candleWidth = candleWidth;
        this.drawVolume = drawVolume;
//        this.volumePaint = Color.gray;
//        this.upPaint = Color.green;
//        this.downPaint = Color.red;
        this.volumePaintType = new SolidColor(Color.GRAY);
        this.upPaintType = new SolidColor(Color.GREEN);
        this.downPaintType = new SolidColor(Color.RED);
        
        
        this.useOutlinePaint = false;  // false preserves the old behaviour
                                       // prior to introducing this flag
    }

    /**
     * Returns the width of each candle.
     *
     * @return The candle width.
     *
     * @see #setCandleWidth(double)
     */
    public double getCandleWidth() {
        return this.candleWidth;
    }

    /**
     * Sets the candle width and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     * <P>
     * If you set the width to a negative value, the renderer will calculate
     * the candle width automatically based on the space available on the chart.
     *
     * @param width  The width.
     * @see #setAutoWidthMethod(int)
     * @see #setAutoWidthGap(double)
     * @see #setAutoWidthFactor(double)
     * @see #setMaxCandleWidthInMilliseconds(double)
     */
    public void setCandleWidth(double width) {
        if (width != this.candleWidth) {
            this.candleWidth = width;
            fireChangeEvent();
        }
    }

    /**
     * Returns the maximum width (in milliseconds) of each candle.
     *
     * @return The maximum candle width in milliseconds.
     *
     * @see #setMaxCandleWidthInMilliseconds(double)
     */
    public double getMaxCandleWidthInMilliseconds() {
        return this.maxCandleWidthInMilliseconds;
    }

    /**
     * Sets the maximum candle width (in milliseconds) and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param millis  The maximum width.
     *
     * @see #getMaxCandleWidthInMilliseconds()
     * @see #setCandleWidth(double)
     * @see #setAutoWidthMethod(int)
     * @see #setAutoWidthGap(double)
     * @see #setAutoWidthFactor(double)
     */
    public void setMaxCandleWidthInMilliseconds(double millis) {
        this.maxCandleWidthInMilliseconds = millis;
        fireChangeEvent();
    }

    /**
     * Returns the method of automatically calculating the candle width.
     *
     * @return The method of automatically calculating the candle width.
     *
     * @see #setAutoWidthMethod(int)
     */
    public int getAutoWidthMethod() {
        return this.autoWidthMethod;
    }

    /**
     * Sets the method of automatically calculating the candle width and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * <p>
     * <code>WIDTHMETHOD_AVERAGE</code>: Divides the entire display (ignoring
     * scale factor) by the number of items, and uses this as the available
     * width.<br>
     * <code>WIDTHMETHOD_SMALLEST</code>: Checks the interval between each
     * item, and uses the smallest as the available width.<br>
     * <code>WIDTHMETHOD_INTERVALDATA</code>: Assumes that the dataset supports
     * the IntervalXYDataset interface, and uses the startXValue - endXValue as
     * the available width.
     * <br>
     *
     * @param autoWidthMethod  The method of automatically calculating the
     * candle width.
     *
     * @see #WIDTHMETHOD_AVERAGE
     * @see #WIDTHMETHOD_SMALLEST
     * @see #WIDTHMETHOD_INTERVALDATA
     * @see #getAutoWidthMethod()
     * @see #setCandleWidth(double)
     * @see #setAutoWidthGap(double)
     * @see #setAutoWidthFactor(double)
     * @see #setMaxCandleWidthInMilliseconds(double)
     */
    public void setAutoWidthMethod(int autoWidthMethod) {
        if (this.autoWidthMethod != autoWidthMethod) {
            this.autoWidthMethod = autoWidthMethod;
            fireChangeEvent();
        }
    }

    /**
     * Returns the factor by which the available space automatically
     * calculated for the candles will be multiplied to determine the actual
     * width to use.
     *
     * @return The width factor (generally between 0.0 and 1.0).
     *
     * @see #setAutoWidthFactor(double)
     */
    public double getAutoWidthFactor() {
        return this.autoWidthFactor;
    }

    /**
     * Sets the factor by which the available space automatically calculated
     * for the candles will be multiplied to determine the actual width to use.
     *
     * @param autoWidthFactor The width factor (generally between 0.0 and 1.0).
     *
     * @see #getAutoWidthFactor()
     * @see #setCandleWidth(double)
     * @see #setAutoWidthMethod(int)
     * @see #setAutoWidthGap(double)
     * @see #setMaxCandleWidthInMilliseconds(double)
     */
    public void setAutoWidthFactor(double autoWidthFactor) {
        if (this.autoWidthFactor != autoWidthFactor) {
            this.autoWidthFactor = autoWidthFactor;
            fireChangeEvent();
        }
    }

    /**
     * Returns the amount of space to leave on the left and right of each
     * candle when automatically calculating widths.
     *
     * @return The gap.
     *
     * @see #setAutoWidthGap(double)
     */
    public double getAutoWidthGap() {
        return this.autoWidthGap;
    }

    /**
     * Sets the amount of space to leave on the left and right of each candle
     * when automatically calculating widths and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param autoWidthGap The gap.
     *
     * @see #getAutoWidthGap()
     * @see #setCandleWidth(double)
     * @see #setAutoWidthMethod(int)
     * @see #setAutoWidthFactor(double)
     * @see #setMaxCandleWidthInMilliseconds(double)
     */
    public void setAutoWidthGap(double autoWidthGap) {
        if (this.autoWidthGap != autoWidthGap) {
            this.autoWidthGap = autoWidthGap;
            fireChangeEvent();
        }
    }

    /**
     * Returns the paint used to fill candles when the price moves up from open
     * to close.
     *
     * @return The paint type (possibly <code>null</code>).
     *
     * @see #setUpPaintType(PaintType)
     */
    public PaintType getUpPaintType() {
        return this.upPaintType;
    }

    /**
     * Sets the paint used to fill candles when the price moves up from open
     * to close and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param paintType  the paint (<code>null</code> permitted).
     *
     * @see #getUpPaintType()
     */
    public void setUpPaintType(PaintType paintType) {
        this.upPaintType = paintType;
        fireChangeEvent();
    }

    /**
     * Returns the paint used to fill candles when the price moves down from
     * open to close.
     *
     * @return The paint type (possibly <code>null</code>).
     *
     * @see #setDownPaintType(PaintType)
     */
    public PaintType getDownPaintType() {
        return this.downPaintType;
    }

    /**
     * Sets the paint used to fill candles when the price moves down from open
     * to close and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param paintType  The paint (<code>null</code> permitted).
     */
    public void setDownPaintType(PaintType paintType) {
        this.downPaintType = paintType;
        fireChangeEvent();
    }

    /**
     * Returns a flag indicating whether or not volume bars are drawn on the
     * chart.
     *
     * @return A boolean.
     *
     * @since JFreeChart 1.0.5
     *
     * @see #setDrawVolume(boolean)
     */
    public boolean getDrawVolume() {
        return this.drawVolume;
    }

    /**
     * Sets a flag that controls whether or not volume bars are drawn in the
     * background and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param flag  the flag.
     *
     * @see #getDrawVolume()
     */
    public void setDrawVolume(boolean flag) {
        if (this.drawVolume != flag) {
            this.drawVolume = flag;
            fireChangeEvent();
        }
    }

    /**
     * Returns the paint that is used to fill the volume bars if they are
     * visible.
     *
     * @return The paint type (never <code>null</code>).
     *
     * @see #setVolumePaintType(PaintType)
     *
     * @since JFreeChart 1.0.7
     */
    public PaintType getVolumePaintType() {
        return this.volumePaintType;
    }

    /**
     * Sets the paint used to fill the volume bars, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getVolumePaintType()
     * @see #getDrawVolume()
     *
     * @since JFreeChart 1.0.7
     */
    public void setVolumePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.volumePaintType = paintType;
        fireChangeEvent();
    }

    /**
     * Returns the flag that controls whether or not the renderer's outline
     * paint is used to draw the candlestick outline.  The default value is
     * <code>false</code>.
     *
     * @return A boolean.
     *
     * @since JFreeChart 1.0.5
     *
     * @see #setUseOutlinePaint(boolean)
     */
    public boolean getUseOutlinePaint() {
        return this.useOutlinePaint;
    }

    /**
     * Sets the flag that controls whether or not the renderer's outline
     * paint is used to draw the candlestick outline, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param use  the new flag value.
     *
     * @since JFreeChart 1.0.5
     *
     * @see #getUseOutlinePaint()
     */
    public void setUseOutlinePaint(boolean use) {
        if (this.useOutlinePaint != use) {
            this.useOutlinePaint = use;
            fireChangeEvent();
        }
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     *
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     */
    public Range findRangeBounds(XYDataset dataset) {
        return findRangeBounds(dataset, true);
    }

    /**
     * Initialises the renderer then returns the number of 'passes' through the
     * data that the renderer will require (usually just one).  This method
     * will be called before the first item is rendered, giving the renderer
     * an opportunity to initialise any state information it wants to maintain.
     * The renderer can do nothing if it chooses.
     *
     * @param canvas  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param dataset  the data.
     * @param info  an optional info collection object to return data back to
     *              the caller.
     *
     * @return The number of passes the renderer requires.
     */
    public XYItemRendererState initialise(Canvas canvas,
                                          RectShape dataArea,
                                          XYPlot plot,
                                          XYDataset dataset,
                                          PlotRenderingInfo info) {

        // calculate the maximum allowed candle width from the axis...
        ValueAxis axis = plot.getDomainAxis();
        double x1 = axis.getLowerBound();
        double x2 = x1 + this.maxCandleWidthInMilliseconds;
        RectangleEdge edge = plot.getDomainAxisEdge();
        double xx1 = axis.valueToJava2D(x1, dataArea, edge);
        double xx2 = axis.valueToJava2D(x2, dataArea, edge);
        this.maxCandleWidth = Math.abs(xx2 - xx1);
            // Absolute value, since the relative x
            // positions are reversed for horizontal orientation

        // calculate the highest volume in the dataset...
        if (this.drawVolume) {
            OHLCDataset highLowDataset = (OHLCDataset) dataset;
            this.maxVolume = 0.0;
            for (int series = 0; series < highLowDataset.getSeriesCount();
                 series++) {
                for (int item = 0; item < highLowDataset.getItemCount(series);
                     item++) {
                    double volume = highLowDataset.getVolumeValue(series, item);
                    if (volume > this.maxVolume) {
                        this.maxVolume = volume;
                    }

                }
            }
        }

        return new XYItemRendererState(info);
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param canvas  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param info  collects info about the drawing.
     * @param plot  the plot (can be used to obtain standard color
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawItem(Canvas canvas,
                         XYItemRendererState state,
                         RectShape dataArea,
                         PlotRenderingInfo info,
                         XYPlot plot,
                         ValueAxis domainAxis,
                         ValueAxis rangeAxis,
                         XYDataset dataset,
                         int series,
                         int item,
                         CrosshairState crosshairState,
                         int pass) {
        
        boolean horiz;
        PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            horiz = true;
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            horiz = false;
        }
        else {
            return;
        }

        // setup for collecting optional entity info...
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getOwner().getEntityCollection();
        }

        OHLCDataset highLowData = (OHLCDataset) dataset;

        double x = highLowData.getXValue(series, item);
        double yHigh = highLowData.getHighValue(series, item);
        double yLow = highLowData.getLowValue(series, item);
        double yOpen = highLowData.getOpenValue(series, item);
        double yClose = highLowData.getCloseValue(series, item);

        RectangleEdge domainEdge = plot.getDomainAxisEdge();
        double xx = domainAxis.valueToJava2D(x, dataArea, domainEdge);

        RectangleEdge edge = plot.getRangeAxisEdge();
        double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, edge);
        double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, edge);
        double yyOpen = rangeAxis.valueToJava2D(yOpen, dataArea, edge);
        double yyClose = rangeAxis.valueToJava2D(yClose, dataArea, edge);

        double volumeWidth;
        double stickWidth;
        if (this.candleWidth > 0) {
            // These are deliberately not bounded to minimums/maxCandleWidth to
            //  retain old behaviour.
            volumeWidth = this.candleWidth;
            stickWidth = this.candleWidth;
        }
        else {
            double xxWidth = 0;
            int itemCount;
            switch (this.autoWidthMethod) {

                case WIDTHMETHOD_AVERAGE:
                    itemCount = highLowData.getItemCount(series);
                    if (horiz) {
                        xxWidth = dataArea.getHeight() / itemCount;
                    }
                    else {
                        xxWidth = dataArea.getWidth() / itemCount;
                    }
                    break;

                case WIDTHMETHOD_SMALLEST:
                    // Note: It would be nice to pre-calculate this per series
                    itemCount = highLowData.getItemCount(series);
                    double lastPos = -1;
                    xxWidth = dataArea.getWidth();
                    for (int i = 0; i < itemCount; i++) {
                        double pos = domainAxis.valueToJava2D(
                                highLowData.getXValue(series, i), dataArea,
                                domainEdge);
                        if (lastPos != -1) {
                            xxWidth = Math.min(xxWidth,
                                    Math.abs(pos - lastPos));
                        }
                        lastPos = pos;
                    }
                    break;

                case WIDTHMETHOD_INTERVALDATA:
                    IntervalXYDataset intervalXYData
                            = (IntervalXYDataset) dataset;
                    double startPos = domainAxis.valueToJava2D(
                            intervalXYData.getStartXValue(series, item),
                            dataArea, plot.getDomainAxisEdge());
                    double endPos = domainAxis.valueToJava2D(
                            intervalXYData.getEndXValue(series, item),
                            dataArea, plot.getDomainAxisEdge());
                    xxWidth = Math.abs(endPos - startPos);
                    break;

            }
            xxWidth -= 2 * this.autoWidthGap;
            xxWidth *= this.autoWidthFactor;
            xxWidth = Math.min(xxWidth, this.maxCandleWidth);
            volumeWidth = Math.max(Math.min(1, this.maxCandleWidth), xxWidth);
            stickWidth = Math.max(Math.min(3, this.maxCandleWidth), xxWidth);
        }

        PaintType itemPaintType = getItemPaintType(series, item);
        PaintType outlinePaintType = null;
        if (this.useOutlinePaint) {
            outlinePaintType = getItemOutlinePaintType(series, item);
        }

        if (this.drawVolume) {
            int volume = (int) highLowData.getVolumeValue(series, item);
            double volumeHeight = volume / this.maxVolume;

            double min, max;
            if (horiz) {
                min = dataArea.getMinX();
                max = dataArea.getMaxX();
            }
            else {
                min = dataArea.getMinY();
                max = dataArea.getMaxY();
            }

            double zzVolume = volumeHeight * (max - min);
            

            //performance tuning
//            Paint paint = PaintUtility.createPaint(
//                    getVolumePaintType(), 
//                    getItemStroke(series, item), 
//                    getItemEffect(series, item));
            Paint paint = this.mWorkPaintVolume;
            PaintUtility.updatePaint(paint, getVolumePaintType());
            paint.setAlpha((int)(paint.getAlpha() * 0.3f));
            paint.setStrokeWidth(getItemStroke(series, item));
            paint.setPathEffect(getItemEffect(series, item));
            
            //performance tuning
            if (horiz) {
//                new RectShape(min, xx - volumeWidth * 0.5,
//                        zzVolume, volumeWidth).fill(canvas, paint);
                this.mWorkRectShape.setRect(min, xx - volumeWidth * 0.5,
                        zzVolume, volumeWidth);
            }
            else {
//                new RectShape(xx - volumeWidth * 0.5,
//                        max - zzVolume, volumeWidth, zzVolume).fill(canvas, paint);
                this.mWorkRectShape.setRect(xx - volumeWidth * 0.5,
                        max - zzVolume, volumeWidth, zzVolume);
            }
            this.mWorkRectShape.fill(canvas, paint);

//            canvas.setComposite(originalComposite);
        }

        //performance tuning
        //Paint paint;
        Paint paint = this.mWorkPaint;
        if (this.useOutlinePaint) {
            //paint = PaintUtility.createPaint(outlinePaintType);
            PaintUtility.updatePaint(paint, outlinePaintType);
        }
        else {
            //paint = PaintUtility.createPaint(itemPaintType);
            PaintUtility.updatePaint(paint, itemPaintType);
        }

        double yyMaxOpenClose = Math.max(yyOpen, yyClose);
        double yyMinOpenClose = Math.min(yyOpen, yyClose);
        double maxOpenClose = Math.max(yOpen, yClose);
        double minOpenClose = Math.min(yOpen, yClose);

        // draw the upper shadow
        if (yHigh > maxOpenClose) {
            if (horiz) {
                //new LineShape(yyHigh, xx, yyMaxOpenClose, xx).draw(canvas, paint);
                this.mWorkLineShape.setLine(yyHigh, xx, yyMaxOpenClose, xx);
            }
            else {
                //new LineShape(xx, yyHigh, xx, yyMaxOpenClose).draw(canvas, paint);
                this.mWorkLineShape.setLine(xx, yyHigh, xx, yyMaxOpenClose);
            }
            this.mWorkLineShape.draw(canvas, paint);
        }
        

        // draw the lower shadow
        if (yLow < minOpenClose) {
            if (horiz) {
                //new LineShape(yyLow, xx, yyMinOpenClose, xx).draw(canvas, paint);
                this.mWorkLineShape.setLine(yyLow, xx, yyMinOpenClose, xx);
            }
            else {
                //new LineShape(xx, yyLow, xx, yyMinOpenClose).draw(canvas, paint);
                this.mWorkLineShape.setLine(xx, yyLow, xx, yyMinOpenClose);
            }
            this.mWorkLineShape.draw(canvas, paint);
        }

        //performance tuning
        // draw the body
//        RectShape body = null;
        RectShape body = this.mWorkRectShape;
        RectShape hotspot = null;
        double length = Math.abs(yyHigh - yyLow);
        double base = Math.min(yyHigh, yyLow);
        if (horiz) {
//            body = new RectShape(yyMinOpenClose, xx - stickWidth * 0.5,
//                    yyMaxOpenClose - yyMinOpenClose, stickWidth);
            body.setRect(yyMinOpenClose, xx - stickWidth * 0.5,
                    yyMaxOpenClose - yyMinOpenClose, stickWidth);
            hotspot = new RectShape(base, xx - stickWidth * 0.5,
                    length, stickWidth);
        }
        else {
//            body = new RectShape(xx - stickWidth * 0.5, yyMinOpenClose,
//                    stickWidth, yyMaxOpenClose - yyMinOpenClose);
            body.setRect(xx - stickWidth * 0.5, yyMinOpenClose,
                    stickWidth, yyMaxOpenClose - yyMinOpenClose);
            hotspot = new RectShape(xx - stickWidth * 0.5,
                    base, stickWidth, length);
        }
        //performance tuning
        if (yClose > yOpen) {
            if (this.upPaintType != null) {
                //paint = PaintUtility.createPaint(this.upPaintType);
                PaintUtility.updatePaint(paint, this.upPaintType);
            }
            else {
                //paint = PaintUtility.createPaint(itemPaintType);
                PaintUtility.updatePaint(paint, itemPaintType);
            }
            body.fill(canvas, paint);
        }
        else {
            if (this.downPaintType != null) {
                //paint = PaintUtility.createPaint(this.downPaintType);
                PaintUtility.updatePaint(paint, this.downPaintType);
            }
            else {
                //paint = PaintUtility.createPaint(itemPaintType);
                PaintUtility.updatePaint(paint, itemPaintType);
            }
            body.fill(canvas, paint);
        }
        if (this.useOutlinePaint) {
            //paint = PaintUtility.createPaint(outlinePaintType);
            PaintUtility.updatePaint(paint, outlinePaintType);
        }
        else {
            //paint = PaintUtility.createPaint(itemPaintType);
            PaintUtility.updatePaint(paint, itemPaintType);
        }
        body.draw(canvas, paint);

        // add an entity for the item...
        if (entities != null) {
            addEntity(entities, hotspot, dataset, series, item, 0.0, 0.0);
        }

    }

    /**
     * Tests this renderer for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CandlestickRenderer)) {
            return false;
        }
        CandlestickRenderer that = (CandlestickRenderer) obj;
        if (this.candleWidth != that.candleWidth) {
            return false;
        }
//        if (!PaintUtilities.equal(this.upPaint, that.upPaint)) {
//            return false;
//        }
//        if (!PaintUtilities.equal(this.downPaint, that.downPaint)) {
//            return false;
//        }
        if (this.drawVolume != that.drawVolume) {
            return false;
        }
        if (this.maxCandleWidthInMilliseconds
                != that.maxCandleWidthInMilliseconds) {
            return false;
        }
        if (this.autoWidthMethod != that.autoWidthMethod) {
            return false;
        }
        if (this.autoWidthFactor != that.autoWidthFactor) {
            return false;
        }
        if (this.autoWidthGap != that.autoWidthGap) {
            return false;
        }
        if (this.useOutlinePaint != that.useOutlinePaint) {
            return false;
        }
//        if (!PaintUtilities.equal(this.volumePaint, that.volumePaint)) {
//            return false;
//        }
        return super.equals(obj);
    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaintType(this.upPaintType, stream);
        SerialUtilities.writePaintType(this.downPaintType, stream);
        SerialUtilities.writePaintType(this.volumePaintType, stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.upPaintType = SerialUtilities.readPaintType(stream);
        this.downPaintType = SerialUtilities.readPaintType(stream);
        this.volumePaintType = SerialUtilities.readPaintType(stream);
    }

}
