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
 * ---------------
 * PeriodAxis.java
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
 * (C) Copyright 2004-2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 01-Jun-2004 : Version 1 (DG);
 * 16-Sep-2004 : Fixed bug in equals() method, added clone() method and
 *               PublicCloneable interface (DG);
 * 25-Nov-2004 : Updates to support major and minor tick marks (DG);
 * 25-Feb-2005 : Fixed some tick mark bugs (DG);
 * 15-Apr-2005 : Fixed some more tick mark bugs (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 16-Jun-2005 : Fixed zooming (DG);
 * 15-Sep-2005 : Changed configure() method to check autoRange flag,
 *               and added ticks to state (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Oct-2006 : Updated for deprecations in RegularTimePeriod and
 *               subclasses (DG);
 * 22-Mar-2007 : Use new defaultAutoRange attribute (DG);
 * 31-Jul-2007 : Fix for inverted axis labelling (see bug 1763413) (DG);
 * 08-Apr-2008 : Notify listeners in setRange(Range, boolean, boolean) - fixes
 *               bug 1932146 (DG);
 * 16-Jan-2009 : Fixed bug 2490803, a problem in the setRange() method (DG);
 * 02-Mar-2009 : Added locale - see patch 2569670 by Benjamin Bignell (DG);
 * 02-Mar-2009 : Fixed draw() method to check tickMarksVisible and
 *               tickLabelsVisible (DG);
 *
 */

package org.afree.chart.axis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Paint.FontMetrics;

import org.afree.util.PublicCloneable;
import org.afree.ui.RectangleEdge;
import org.afree.io.SerialUtilities;
import org.afree.ui.TextAnchor;
import org.afree.data.Range;
import org.afree.data.time.Day;
import org.afree.data.time.Month;
import org.afree.data.time.RegularTimePeriod;
import org.afree.data.time.Year;
import org.afree.chart.event.AxisChangeEvent;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.ValueAxisPlot;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;

/**
 * An axis that displays a date scale based on a
 * {@link org.afree.data.time.RegularTimePeriod}.  This axis works when
 * displayed across the bottom or top of a plot, but is broken for display at
 * the left or right of charts.
 */
public class PeriodAxis extends ValueAxis
        implements Cloneable, PublicCloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 8353295532075872069L;

    /** The first time period in the overall range. */
    private RegularTimePeriod first;

    /** The last time period in the overall range. */
    private RegularTimePeriod last;

    /**
     * The time zone used to convert 'first' and 'last' to absolute
     * milliseconds.
     */
    private TimeZone timeZone;

    /**
     * The locale (never <code>null</code>).
     * 
     * @since JFreeChart 1.0.13
     */
    private Locale locale;

    /**
     * A calendar used for date manipulations in the current time zone and
     * locale.
     */
    private Calendar calendar;

    /**
     * The {@link RegularTimePeriod} subclass used to automatically determine
     * the axis range.
     */
    private Class autoRangeTimePeriodClass;

    /**
     * Indicates the {@link RegularTimePeriod} subclass that is used to
     * determine the spacing of the major tick marks.
     */
    private Class majorTickTimePeriodClass;

    /**
     * A flag that indicates whether or not tick marks are visible for the
     * axis.
     */
    private boolean minorTickMarksVisible;

    /**
     * Indicates the {@link RegularTimePeriod} subclass that is used to
     * determine the spacing of the minor tick marks.
     */
    private Class minorTickTimePeriodClass;

    /** The length of the tick mark inside the data area (zero permitted). */
    private float minorTickMarkInsideLength = 0.0f;

    /** The length of the tick mark outside the data area (zero permitted). */
    private float minorTickMarkOutsideLength = 2.0f;

    /** The stroke used to draw tick marks. */
    private transient float minorTickMarkStroke = 0.5f;

    /** The paint used to draw tick marks. */
//    private transient Paint minorTickMarkPaint = Color.black;
    private transient PaintType minorTickMarkPaintType = new SolidColor(Color.BLACK);
    
    /** The effect used to draw tick marks. */
    private transient PathEffect minorTickMarkEffect = null;

    /** Info for each labelling band. */
    private PeriodAxisLabelInfo[] labelInfo;

    /**
     * Creates a new axis.
     *
     * @param label  the axis label.
     */
    public PeriodAxis(String label) {
        this(label, new Day(), new Day());
    }

    /**
     * Creates a new axis.
     *
     * @param label  the axis label (<code>null</code> permitted).
     * @param first  the first time period in the axis range
     *               (<code>null</code> not permitted).
     * @param last  the last time period in the axis range
     *              (<code>null</code> not permitted).
     */
    public PeriodAxis(String label,
                      RegularTimePeriod first, RegularTimePeriod last) {
        this(label, first, last, TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     * Creates a new axis.
     *
     * @param label  the axis label (<code>null</code> permitted).
     * @param first  the first time period in the axis range
     *               (<code>null</code> not permitted).
     * @param last  the last time period in the axis range
     *              (<code>null</code> not permitted).
     * @param timeZone  the time zone (<code>null</code> not permitted).
     * @param locale  the locale (<code>null</code> not permitted).
     *
     * @since JFreeChart 1.0.13
     */
    public PeriodAxis(String label, RegularTimePeriod first,
            RegularTimePeriod last, TimeZone timeZone, Locale locale) {
        super(label, null);
        if (timeZone == null) {
            throw new IllegalArgumentException("Null 'timeZone' argument.");
        }
        if (locale == null) {
            throw new IllegalArgumentException("Null 'locale' argument.");
        }
        this.first = first;
        this.last = last;
        this.timeZone = timeZone;
        this.locale = locale;
        this.calendar = Calendar.getInstance(timeZone, locale);
        this.first.peg(this.calendar);
        this.last.peg(this.calendar);
        this.autoRangeTimePeriodClass = first.getClass();
        this.majorTickTimePeriodClass = first.getClass();
        this.minorTickMarksVisible = false;
        this.minorTickTimePeriodClass = RegularTimePeriod.downsize(
                this.majorTickTimePeriodClass);
        setAutoRange(true);
        this.labelInfo = new PeriodAxisLabelInfo[2];
        this.labelInfo[0] = new PeriodAxisLabelInfo(Month.class,
                new SimpleDateFormat("MMM", locale));
        this.labelInfo[1] = new PeriodAxisLabelInfo(Year.class,
                new SimpleDateFormat("yyyy", locale));
    }

    /**
     * Returns the first time period in the axis range.
     *
     * @return The first time period (never <code>null</code>).
     */
    public RegularTimePeriod getFirst() {
        return this.first;
    }

    /**
     * Sets the first time period in the axis range and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param first  the time period (<code>null</code> not permitted).
     */
    public void setFirst(RegularTimePeriod first) {
        if (first == null) {
            throw new IllegalArgumentException("Null 'first' argument.");
        }
        this.first = first;
        this.first.peg(this.calendar);
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the last time period in the axis range.
     *
     * @return The last time period (never <code>null</code>).
     */
    public RegularTimePeriod getLast() {
        return this.last;
    }

    /**
     * Sets the last time period in the axis range and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param last  the time period (<code>null</code> not permitted).
     */
    public void setLast(RegularTimePeriod last) {
        if (last == null) {
            throw new IllegalArgumentException("Null 'last' argument.");
        }
        this.last = last;
        this.last.peg(this.calendar);
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the time zone used to convert the periods defining the axis
     * range into absolute milliseconds.
     *
     * @return The time zone (never <code>null</code>).
     */
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    /**
     * Sets the time zone that is used to convert the time periods into
     * absolute milliseconds.
     *
     * @param zone  the time zone (<code>null</code> not permitted).
     */
    public void setTimeZone(TimeZone zone) {
        if (zone == null) {
            throw new IllegalArgumentException("Null 'zone' argument.");
        }
        this.timeZone = zone;
        this.calendar = Calendar.getInstance(zone, this.locale);
        this.first.peg(this.calendar);
        this.last.peg(this.calendar);
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the locale for this axis.
     *
     * @return The locale (never (<code>null</code>).
     *
     * @since JFreeChart 1.0.13
     */
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * Returns the class used to create the first and last time periods for
     * the axis range when the auto-range flag is set to <code>true</code>.
     *
     * @return The class (never <code>null</code>).
     */
    public Class getAutoRangeTimePeriodClass() {
        return this.autoRangeTimePeriodClass;
    }

    /**
     * Sets the class used to create the first and last time periods for the
     * axis range when the auto-range flag is set to <code>true</code> and
     * sends an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param c  the class (<code>null</code> not permitted).
     */
    public void setAutoRangeTimePeriodClass(Class c) {
        if (c == null) {
            throw new IllegalArgumentException("Null 'c' argument.");
        }
        this.autoRangeTimePeriodClass = c;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the class that controls the spacing of the major tick marks.
     *
     * @return The class (never <code>null</code>).
     */
    public Class getMajorTickTimePeriodClass() {
        return this.majorTickTimePeriodClass;
    }

    /**
     * Sets the class that controls the spacing of the major tick marks, and
     * sends an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param c  the class (a subclass of {@link RegularTimePeriod} is
     *           expected).
     */
    public void setMajorTickTimePeriodClass(Class c) {
        if (c == null) {
            throw new IllegalArgumentException("Null 'c' argument.");
        }
        this.majorTickTimePeriodClass = c;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the flag that controls whether or not minor tick marks
     * are displayed for the axis.
     *
     * @return A boolean.
     */
    public boolean isMinorTickMarksVisible() {
        return this.minorTickMarksVisible;
    }

    /**
     * Sets the flag that controls whether or not minor tick marks
     * are displayed for the axis, and sends a {@link AxisChangeEvent}
     * to all registered listeners.
     *
     * @param visible  the flag.
     */
    public void setMinorTickMarksVisible(boolean visible) {
        this.minorTickMarksVisible = visible;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the class that controls the spacing of the minor tick marks.
     *
     * @return The class (never <code>null</code>).
     */
    public Class getMinorTickTimePeriodClass() {
        return this.minorTickTimePeriodClass;
    }

    /**
     * Sets the class that controls the spacing of the minor tick marks, and
     * sends an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param c  the class (a subclass of {@link RegularTimePeriod} is
     *           expected).
     */
    public void setMinorTickTimePeriodClass(Class c) {
        if (c == null) {
            throw new IllegalArgumentException("Null 'c' argument.");
        }
        this.minorTickTimePeriodClass = c;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the stroke used to display minor tick marks, if they are
     * visible.
     *
     * @return A stroke (never <code>null</code>).
     */
    public float getMinorTickMarkStroke() {
        return this.minorTickMarkStroke;
    }

    /**
     * Sets the stroke used to display minor tick marks, if they are
     * visible, and sends a {@link AxisChangeEvent} to all registered
     * listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     */
    public void setMinorTickMarkStroke(float stroke) {
        if (stroke == 0) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.minorTickMarkStroke = stroke;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the paint used to display minor tick marks, if they are
     * visible.
     *
     * @return A paint (never <code>null</code>).
     */
    public PaintType getMinorTickMarkPaintType() {
        return this.minorTickMarkPaintType;
    }

    /**
     * Sets the paint used to display minor tick marks, if they are
     * visible, and sends a {@link AxisChangeEvent} to all registered
     * listeners.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     */
    public void setMinorTickMarkPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.minorTickMarkPaintType = paintType;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the effect used to display minor tick marks, if they are
     * visible.
     *
     * @return A effect (never <code>null</code>).
     */
    public PathEffect getMinorTickMarkEffect() {
        return this.minorTickMarkEffect;
    }

    /**
     * Sets the effect used to display minor tick marks, if they are
     * visible
     *
     * @param pathEffect  the effect (<code>null</code> not permitted).
     */
    public void setMinorTickMarkEffect(PathEffect pathEffect) {
        this.minorTickMarkEffect = pathEffect;
        notifyListeners(new AxisChangeEvent(this));

    }

    /**
     * Returns the inside length for the minor tick marks.
     *
     * @return The length.
     */
    public float getMinorTickMarkInsideLength() {
        return this.minorTickMarkInsideLength;
    }

    /**
     * Sets the inside length of the minor tick marks and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param length  the length.
     */
    public void setMinorTickMarkInsideLength(float length) {
        this.minorTickMarkInsideLength = length;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the outside length for the minor tick marks.
     *
     * @return The length.
     */
    public float getMinorTickMarkOutsideLength() {
        return this.minorTickMarkOutsideLength;
    }

    /**
     * Sets the outside length of the minor tick marks and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param length  the length.
     */
    public void setMinorTickMarkOutsideLength(float length) {
        this.minorTickMarkOutsideLength = length;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns an array of label info records.
     *
     * @return An array.
     */
    public PeriodAxisLabelInfo[] getLabelInfo() {
        return this.labelInfo;
    }

    /**
     * Sets the array of label info records and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param info  the info.
     */
    public void setLabelInfo(PeriodAxisLabelInfo[] info) {
        this.labelInfo = info;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Sets the range for the axis, if requested, sends an
     * {@link AxisChangeEvent} to all registered listeners.  As a side-effect,
     * the auto-range flag is set to <code>false</code> (optional).
     *
     * @param range  the range (<code>null</code> not permitted).
     * @param turnOffAutoRange  a flag that controls whether or not the auto
     *                          range is turned off.
     * @param notify  a flag that controls whether or not listeners are
     *                notified.
     */
    public void setRange(Range range, boolean turnOffAutoRange,
                         boolean notify) {
        long upper = Math.round(range.getUpperBound());
        long lower = Math.round(range.getLowerBound());
        this.first = createInstance(this.autoRangeTimePeriodClass,
                new Date(lower), this.timeZone, this.locale);
        this.last = createInstance(this.autoRangeTimePeriodClass,
                new Date(upper), this.timeZone, this.locale);
        super.setRange(new Range(this.first.getFirstMillisecond(),
                this.last.getLastMillisecond() + 1.0), turnOffAutoRange,
                notify);
    }

    /**
     * Configures the axis to work with the current plot.  Override this method
     * to perform any special processing (such as auto-rescaling).
     */
    public void configure() {
        if (this.isAutoRange()) {
            autoAdjustRange();
        }
    }

    /**
     * Estimates the space (height or width) required to draw the axis.
     *
     * @param canvas  the graphics device.
     * @param plot  the plot that the axis belongs to.
     * @param plotArea  the area within which the plot (including axes) should
     *                  be drawn.
     * @param edge  the axis location.
     * @param space  space already reserved.
     *
     * @return The space required to draw the axis (including pre-reserved
     *         space).
     */
    public AxisSpace reserveSpace(Canvas canvas, Plot plot,
                                  RectShape plotArea, RectangleEdge edge,
                                  AxisSpace space) {
        // create a new space object if one wasn't supplied...
        if (space == null) {
            space = new AxisSpace();
        }

        // if the axis is not visible, no additional space is required...
        if (!isVisible()) {
            return space;
        }

        // if the axis has a fixed dimension, return it...
        double dimension = getFixedDimension();
        if (dimension > 0.0) {
            space.ensureAtLeast(dimension, edge);
        }

        // get the axis label size and update the space object...
        RectShape labelEnclosure = getLabelEnclosure(canvas, edge);
        double labelHeight = 0.0;
        double labelWidth = 0.0;
        double tickLabelBandsDimension = 0.0;
               
        for (int i = 0; i < this.labelInfo.length; i++) {
            PeriodAxisLabelInfo info = this.labelInfo[i];
            Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, info.getLabelPaintType(), info.getLabelFont());
            FontMetrics fm = paint.getFontMetrics();
            tickLabelBandsDimension
//                += info.getPadding().extendHeight(fm.getHeight());
            += info.getPadding().extendHeight(fm.bottom-fm.top);
        }

        if (RectangleEdge.isTopOrBottom(edge)) {
            labelHeight = labelEnclosure.getHeight();
            space.add(labelHeight + tickLabelBandsDimension, edge);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            labelWidth = labelEnclosure.getWidth();
            space.add(labelWidth + tickLabelBandsDimension, edge);
        }

        // add space for the outer tick labels, if any...
        double tickMarkSpace = 0.0;
        if (isTickMarksVisible()) {
            tickMarkSpace = getTickMarkOutsideLength();
        }
        if (this.minorTickMarksVisible) {
            tickMarkSpace = Math.max(tickMarkSpace,
                    this.minorTickMarkOutsideLength);
        }
        space.add(tickMarkSpace, edge);
        return space;
    }

    /**
     * Draws the axis on a graphics device (such as the screen or a
     * printer).
     *
     * @param canvas  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location (determines where to draw the axis).
     * @param plotArea  the area within which the axes and plot should be drawn.
     * @param dataArea  the area within which the data should be drawn.
     * @param edge  the axis location (<code>null</code> not permitted).
     * @param plotState  collects information about the plot
     *                   (<code>null</code> permitted).
     *
     * @return The axis state (never <code>null</code>).
     */
    public AxisState draw(Canvas canvas, double cursor, RectShape plotArea,
            RectShape dataArea, RectangleEdge edge,
            PlotRenderingInfo plotState) {

        AxisState axisState = new AxisState(cursor);
        if (isAxisLineVisible()) {
            drawAxisLine(canvas, cursor, dataArea, edge);
        }
        if (isTickMarksVisible()) {
            drawTickMarks(canvas, axisState, dataArea, edge);
        }
        if (isTickLabelsVisible()) {
            for (int band = 0; band < this.labelInfo.length; band++) {
                axisState = drawTickLabels(band, canvas, axisState, dataArea, edge);
            }
        }

        // draw the axis label (note that 'state' is passed in *and*
        // returned)...
        axisState = drawLabel(getLabel(), canvas, plotArea, dataArea, edge,
                axisState);
        return axisState;

    }

    /**
     * Draws the tick marks for the axis.
     *
     * @param canvas  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the data area.
     * @param edge  the edge.
     */
    protected void drawTickMarks(Canvas canvas, AxisState state,
                                 RectShape dataArea,
                                 RectangleEdge edge) {
        if (RectangleEdge.isTopOrBottom(edge)) {
            drawTickMarksHorizontal(canvas, state, dataArea, edge);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            drawTickMarksVertical(canvas, state, dataArea, edge);
        }
    }

    /**
     * Draws the major and minor tick marks for an axis that lies at the top or
     * bottom of the plot.
     *
     * @param canvas  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the data area.
     * @param edge  the edge.
     */
    protected void drawTickMarksHorizontal(Canvas canvas, AxisState state,
                                           RectShape dataArea,
                                           RectangleEdge edge) {
        List ticks = new ArrayList();
        double x0 = dataArea.getX();
        double y0 = state.getCursor();
        double insideLength = getTickMarkInsideLength();
        double outsideLength = getTickMarkOutsideLength();
        RegularTimePeriod t = createInstance(this.majorTickTimePeriodClass, 
                this.first.getStart(), getTimeZone(), this.locale);
        long t0 = t.getFirstMillisecond();
        LineShape inside = null;
        LineShape outside = null;
        long firstOnAxis = getFirst().getFirstMillisecond();
        long lastOnAxis = getLast().getLastMillisecond() + 1;
        while (t0 <= lastOnAxis) {
            ticks.add(new NumberTick(new Double(t0), "", TextAnchor.CENTER,
                    TextAnchor.CENTER, 0.0));
            x0 = valueToJava2D(t0, dataArea, edge);
            if (edge == RectangleEdge.TOP) {
                inside = new LineShape(x0, y0, x0, y0 + insideLength);
                outside = new LineShape(x0, y0, x0, y0 - outsideLength);
            }
            else if (edge == RectangleEdge.BOTTOM) {
                inside = new LineShape(x0, y0, x0, y0 - insideLength);
                outside = new LineShape(x0, y0, x0, y0 + outsideLength);
            }
            if (t0 >= firstOnAxis) {
//                canvas.setPaint(getTickMarkPaint());
//                canvas.setStroke(getTickMarkStroke());
//                canvas.draw(inside);
//                canvas.draw(outside);
                Paint paint = PaintUtility.createPaint(
                        Paint.ANTI_ALIAS_FLAG,
                        getTickMarkPaintType(), 
                        getTickMarkStroke(), 
                        getTickMarkEffect());
                canvas.drawLine((float) inside.getX1(), (float) inside.getY1(),
                        (float) inside.getX2(), (float) inside.getY2(),
                        paint);
                canvas.drawLine((float) outside.getX1(), (float) outside.getY1(),
                        (float) outside.getX2(), (float) outside.getY2(),
                        paint);                
            }
            // draw minor tick marks
            if (this.minorTickMarksVisible) {
                RegularTimePeriod tminor = createInstance(
                        this.minorTickTimePeriodClass, new Date(t0),
                        getTimeZone(), this.locale);
                long tt0 = tminor.getFirstMillisecond();
                while (tt0 < t.getLastMillisecond()
                        && tt0 < lastOnAxis) {
                    double xx0 = valueToJava2D(tt0, dataArea, edge);
                    if (edge == RectangleEdge.TOP) {
                        inside = new LineShape(xx0, y0, xx0,
                                y0 + this.minorTickMarkInsideLength);
                        outside = new LineShape(xx0, y0, xx0,
                                y0 - this.minorTickMarkOutsideLength);
                    }
                    else if (edge == RectangleEdge.BOTTOM) {
                        inside = new LineShape(xx0, y0, xx0,
                                y0 - this.minorTickMarkInsideLength);
                        outside = new LineShape(xx0, y0, xx0,
                                y0 + this.minorTickMarkOutsideLength);
                    }
                    if (tt0 >= firstOnAxis) {
//                        canvas.setPaint(this.minorTickMarkPaint);
//                        canvas.setStroke(this.minorTickMarkStroke);
//                        canvas.draw(inside);
//                        canvas.draw(outside);
                        Paint paint = PaintUtility.createPaint(
                                Paint.ANTI_ALIAS_FLAG,
                                getMinorTickMarkPaintType(), 
                                getMinorTickMarkStroke(), 
                                getMinorTickMarkEffect());

                        canvas.drawLine((float) inside.getX1(), (float) inside.getY1(),
                                (float) inside.getX2(), (float) inside.getY2(),
                                paint);
                        canvas.drawLine((float) outside.getX1(), (float) outside.getY1(),
                                (float) outside.getX2(), (float) outside.getY2(),
                                paint);                             
                    }
                    tminor = tminor.next();
                    tminor.peg(this.calendar);
                    tt0 = tminor.getFirstMillisecond();
                }
            }
            t = t.next();
            t.peg(this.calendar);
            t0 = t.getFirstMillisecond();
        }
        if (edge == RectangleEdge.TOP) {
            state.cursorUp(Math.max(outsideLength,
                    this.minorTickMarkOutsideLength));
        }
        else if (edge == RectangleEdge.BOTTOM) {
            state.cursorDown(Math.max(outsideLength,
                    this.minorTickMarkOutsideLength));
        }
        state.setTicks(ticks);
    }

    /**
     * Draws the tick marks for a vertical axis.
     *
     * @param canvas  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the data area.
     * @param edge  the edge.
     */
    protected void drawTickMarksVertical(Canvas canvas, AxisState state,
                                         RectShape dataArea,
                                         RectangleEdge edge) {
        // FIXME:  implement this...
    }

    /**
     * Draws the tick labels for one "band" of time periods.
     *
     * @param band  the band index (zero-based).
     * @param canvas  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the data area.
     * @param edge  the edge where the axis is located.
     *
     * @return The updated axis state.
     */
    protected AxisState drawTickLabels(int band, Canvas canvas, AxisState state,
                                       RectShape dataArea,
                                       RectangleEdge edge) {

        // work out the initial gap
        double delta1 = 0.0;
        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, 
                this.labelInfo[band].getLabelPaintType(), 
                this.labelInfo[band].getLabelFont());
//        FontMetrics fmFontMetrics fm = canvas.getFontMetrics(info.getLabelFont());
        FontMetrics fm = paint.getFontMetrics();
        if (edge == RectangleEdge.BOTTOM) {
//            delta1 = this.labelInfo[band].getPadding().calculateTopOutset(
//                    fm.getHeight);
            delta1 = this.labelInfo[band].getPadding().calculateTopOutset(
                    fm.bottom-fm.top);
        }
        else if (edge == RectangleEdge.TOP) {
//            delta1 = this.labelInfo[band].getPadding().calculateBottomOutset(
//                    fm.getHeight);
            delta1 = this.labelInfo[band].getPadding().calculateBottomOutset(
                    fm.bottom-fm.top);
        }
        state.moveCursor(delta1, edge);
        long axisMin = this.first.getFirstMillisecond();
        long axisMax = this.last.getLastMillisecond();
//        canvas.setFont(this.labelInfo[band].getLabelFont());
//        canvas.setPaint(this.labelInfo[band].getLabelPaint());

        // work out the number of periods to skip for labelling
        RegularTimePeriod p1 = this.labelInfo[band].createInstance(
                new Date(axisMin), this.timeZone, this.locale);
        RegularTimePeriod p2 = this.labelInfo[band].createInstance(
                new Date(axisMax), this.timeZone, this.locale);
        String label1 = this.labelInfo[band].getDateFormat().format(
                new Date(p1.getMiddleMillisecond()));
        String label2 = this.labelInfo[band].getDateFormat().format(
                new Date(p2.getMiddleMillisecond()));
        RectShape b1 = TextUtilities.getTextBounds(label1, 
//                canvas.getFontMetrics());
                paint);
        RectShape b2 = TextUtilities.getTextBounds(label2,
//                canvas.getFontMetrics());
                paint);
        double w = Math.max(b1.getWidth(), b2.getWidth());
        long ww = Math.round(java2DToValue(dataArea.getX() + w + 5.0,
                dataArea, edge));
        if (isInverted()) {
            ww = axisMax - ww;
        }
        else {
            ww = ww - axisMin;
        }
        long length = p1.getLastMillisecond()
                      - p1.getFirstMillisecond();
        int periods = (int) (ww / length) + 1;

        RegularTimePeriod p = this.labelInfo[band].createInstance(
                new Date(axisMin), this.timeZone, this.locale);
        RectShape b = null;
        long lastXX = 0L;
        float y = (float) (state.getCursor());
        TextAnchor anchor = TextAnchor.TOP_CENTER;
        float yDelta = (float) b1.getHeight();
        if (edge == RectangleEdge.TOP) {
            anchor = TextAnchor.BOTTOM_CENTER;
            yDelta = -yDelta;
        }
        while (p.getFirstMillisecond() <= axisMax) {
            float x = (float) valueToJava2D(p.getMiddleMillisecond(), dataArea,
                    edge);
            DateFormat df = this.labelInfo[band].getDateFormat();
            String label = df.format(new Date(p.getMiddleMillisecond()));
            long first = p.getFirstMillisecond();
            long last = p.getLastMillisecond();
            if (last > axisMax) {
                // this is the last period, but it is only partially visible
                // so check that the label will fit before displaying it...
                RectShape bb = TextUtilities.getTextBounds(label, paint);
//                        canvas.getFontMetrics());
                if ((x + bb.getWidth() / 2) > dataArea.getMaxX()) {
                    float xstart = (float) valueToJava2D(Math.max(first,
                            axisMin), dataArea, edge);
                    if (bb.getWidth() < (dataArea.getMaxX() - xstart)) {
                        x = ((float) dataArea.getMaxX() + xstart) / 2.0f;
                    }
                    else {
                        label = null;
                    }
                }
            }
            if (first < axisMin) {
                // this is the first period, but it is only partially visible
                // so check that the label will fit before displaying it...
                RectShape bb = TextUtilities.getTextBounds(label, paint);
//                        canvas.getFontMetrics());
                if ((x - bb.getWidth() / 2) < dataArea.getX()) {
                    float xlast = (float) valueToJava2D(Math.min(last,
                            axisMax), dataArea, edge);
                    if (bb.getWidth() < (xlast - dataArea.getX())) {
                        x = (xlast + (float) dataArea.getX()) / 2.0f;
                    }
                    else {
                        label = null;
                    }
                }

            }
            if (label != null) {
//                canvas.setPaint(this.labelInfo[band].getLabelPaint());
//                b = TextUtilities.drawAlignedString(label, canvas, x, y, anchor);
                PaintUtility.updatePaint(paint, this.labelInfo[band].getLabelPaintType());
                b = TextUtilities.drawAlignedString(label, canvas, x, y, anchor, paint);
            }
            if (lastXX > 0L) {
                if (this.labelInfo[band].getDrawDividers()) {
                    long nextXX = p.getFirstMillisecond();
                    long mid = (lastXX + nextXX) / 2;
                    float mid2d = (float) valueToJava2D(mid, dataArea, edge);
//                    canvas.setStroke(this.labelInfo[band].getDividerStroke());
//                    canvas.setPaint(this.labelInfo[band].getDividerPaint());
//                    canvas.draw(new LineShape.Float(mid2d, y, mid2d, y + yDelta));
                    paint.setStrokeWidth(this.labelInfo[band].getDividerStroke());
                    PaintUtility.updatePaint(paint, this.labelInfo[band].getDividerPaintType());
                    canvas.drawLine(mid2d, y, mid2d, y + yDelta, paint);                    
                }
            }
            lastXX = last;
            for (int i = 0; i < periods; i++) {
                p = p.next();
            }
            p.peg(this.calendar);
        }
        double used = 0.0;
        if (b != null) {
            used = b.getHeight();
            // work out the trailing gap
            if (edge == RectangleEdge.BOTTOM) {
                used += this.labelInfo[band].getPadding().calculateBottomOutset(
//                        fm.getHeight());
                        fm.bottom-fm.top);
            }
            else if (edge == RectangleEdge.TOP) {
                used += this.labelInfo[band].getPadding().calculateTopOutset(
//                        fm.getHeight());
                        fm.bottom-fm.top);
            }
        }
        state.moveCursor(used, edge);
        return state;
    }

    /**
     * Calculates the positions of the ticks for the axis, storing the results
     * in the tick list (ready for drawing).
     *
     * @param canvas  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the area inside the axes.
     * @param edge  the edge on which the axis is located.
     *
     * @return The list of ticks.
     */
    public List refreshTicks(Canvas canvas, AxisState state,
            RectShape dataArea, RectangleEdge edge) {
        return Collections.EMPTY_LIST;
    }

    /**
     * Converts a data value to a coordinate in Java2D space, assuming that the
     * axis runs along one edge of the specified dataArea.
     * <p>
     * Note that it is possible for the coordinate to fall outside the area.
     *
     * @param value  the data value.
     * @param area  the area for plotting the data.
     * @param edge  the edge along which the axis lies.
     *
     * @return The Java2D coordinate.
     */
    public double valueToJava2D(double value, RectShape area,
            RectangleEdge edge) {

        double result = Double.NaN;
        double axisMin = this.first.getFirstMillisecond();
        double axisMax = this.last.getLastMillisecond();
        if (RectangleEdge.isTopOrBottom(edge)) {
            double minX = area.getX();
            double maxX = area.getMaxX();
            if (isInverted()) {
                result = maxX + ((value - axisMin) / (axisMax - axisMin))
                         * (minX - maxX);
            }
            else {
                result = minX + ((value - axisMin) / (axisMax - axisMin))
                         * (maxX - minX);
            }
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            double minY = area.getMinY();
            double maxY = area.getMaxY();
            if (isInverted()) {
                result = minY + (((value - axisMin) / (axisMax - axisMin))
                         * (maxY - minY));
            }
            else {
                result = maxY - (((value - axisMin) / (axisMax - axisMin))
                         * (maxY - minY));
            }
        }
        return result;

    }

    /**
     * Converts a coordinate in Java2D space to the corresponding data value,
     * assuming that the axis runs along one edge of the specified dataArea.
     *
     * @param java2DValue  the coordinate in Java2D space.
     * @param area  the area in which the data is plotted.
     * @param edge  the edge along which the axis lies.
     *
     * @return The data value.
     */
    public double java2DToValue(double java2DValue, RectShape area,
            RectangleEdge edge) {

        double result = Double.NaN;
        double min = 0.0;
        double max = 0.0;
        double axisMin = this.first.getFirstMillisecond();
        double axisMax = this.last.getLastMillisecond();
        if (RectangleEdge.isTopOrBottom(edge)) {
            min = area.getX();
            max = area.getMaxX();
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            min = area.getMaxY();
            max = area.getY();
        }
        if (isInverted()) {
             result = axisMax - ((java2DValue - min) / (max - min)
                      * (axisMax - axisMin));
        }
        else {
             result = axisMin + ((java2DValue - min) / (max - min)
                      * (axisMax - axisMin));
        }
        return result;
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
            ValueAxisPlot vap = (ValueAxisPlot) plot;

            Range r = vap.getDataRange(this);
            if (r == null) {
                r = getDefaultAutoRange();
            }

            long upper = Math.round(r.getUpperBound());
            long lower = Math.round(r.getLowerBound());
            this.first = createInstance(this.autoRangeTimePeriodClass,
                    new Date(lower), this.timeZone, this.locale);
            this.last = createInstance(this.autoRangeTimePeriodClass,
                    new Date(upper), this.timeZone, this.locale);
            setRange(r, false, false);
        }

    }

    /**
     * Tests the axis for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PeriodAxis)) {
            return false;
        }
        PeriodAxis that = (PeriodAxis) obj;
        if (!this.first.equals(that.first)) {
            return false;
        }
        if (!this.last.equals(that.last)) {
            return false;
        }
        if (!this.timeZone.equals(that.timeZone)) {
            return false;
        }
        if (!this.locale.equals(that.locale)) {
            return false;
        }
        if (!this.autoRangeTimePeriodClass.equals(
                that.autoRangeTimePeriodClass)) {
            return false;
        }
        if (!(isMinorTickMarksVisible() == that.isMinorTickMarksVisible())) {
            return false;
        }
        if (!this.majorTickTimePeriodClass.equals(
                that.majorTickTimePeriodClass)) {
            return false;
        }
        if (!this.minorTickTimePeriodClass.equals(
                that.minorTickTimePeriodClass)) {
            return false;
        }
        if (!this.minorTickMarkPaintType.equals(that.minorTickMarkPaintType)) {
            return false;
        }
        if (this.minorTickMarkStroke != (that.minorTickMarkStroke)) {
            return false;
        }
        if (!Arrays.equals(this.labelInfo, that.labelInfo)) {
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
        if (getLabel() != null) {
            return getLabel().hashCode();
        }
        else {
            return 0;
        }
    }

    /**
     * Returns a clone of the axis.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  this class is cloneable, but
     *         subclasses may not be.
     */
    public Object clone() throws CloneNotSupportedException {
        PeriodAxis clone = (PeriodAxis) super.clone();
        clone.timeZone = (TimeZone) this.timeZone.clone();
        clone.labelInfo = new PeriodAxisLabelInfo[this.labelInfo.length];
        for (int i = 0; i < this.labelInfo.length; i++) {
            clone.labelInfo[i] = this.labelInfo[i];  // copy across references
                                                     // to immutable objs
        }
        return clone;
    }

    /**
     * A utility method used to create a particular subclass of the
     * {@link RegularTimePeriod} class that includes the specified millisecond,
     * assuming the specified time zone.
     *
     * @param periodClass  the class.
     * @param millisecond  the time.
     * @param zone  the time zone.
     * @param locale  the locale.
     *
     * @return The time period.
     */
    private RegularTimePeriod createInstance(Class periodClass, 
            Date millisecond, TimeZone zone, Locale locale) {
        RegularTimePeriod result = null;
        try {
            Constructor c = periodClass.getDeclaredConstructor(new Class[] {
                    Date.class, TimeZone.class, Locale.class});
            result = (RegularTimePeriod) c.newInstance(new Object[] {
                    millisecond, zone, locale});
        }
        catch (Exception e) {
            try {
                Constructor c = periodClass.getDeclaredConstructor(new Class[] {
                        Date.class});
                result = (RegularTimePeriod) c.newInstance(new Object[] {
                        millisecond});
            }
            catch (Exception e2) {
                // do nothing
            }
        }
        return result;
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
//        SerialUtilities.writeStroke(this.minorTickMarkStroke, stream);
        SerialUtilities.writePaintType(this.minorTickMarkPaintType, stream);
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
//        this.minorTickMarkStroke = SerialUtilities.readStroke(stream);
        this.minorTickMarkPaintType = SerialUtilities.readPaintType(stream);
    }

}
