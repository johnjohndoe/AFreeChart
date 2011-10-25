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
 * --------------
 * ValueAxis.java
 * --------------
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
 * 15-Dec-2010 : performance tuning
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Jonathan Nash;
 *                   Nicolas Brodu (for Astrium and EADS Corporate Research
 *                   Center);
 *                   Peter Kolb (patch 1934255);
 *                   Andrew Mickish (patch 1870189);
 *
 * Changes
 * -------
 * 18-Sep-2001 : Added standard header and fixed DOS encoding problem (DG);
 * 23-Nov-2001 : Overhauled standard tick unit code (DG);
 * 04-Dec-2001 : Changed constructors to protected, and tidied up default
 *               values (DG);
 * 12-Dec-2001 : Fixed vertical gridlines bug (DG);
 * 16-Jan-2002 : Added an optional crosshair, based on the implementation by
 *               Jonathan Nash (DG);
 * 23-Jan-2002 : Moved the minimum and maximum values to here from NumberAxis,
 *               and changed the type from Number to double (DG);
 * 25-Feb-2002 : Added default value for autoRange. Changed autoAdjustRange
 *               from public to protected. Updated import statements (DG);
 * 23-Apr-2002 : Added setRange() method (DG);
 * 29-Apr-2002 : Added range adjustment methods (DG);
 * 13-Jun-2002 : Modified setCrosshairValue() to notify listeners only when the
 *               crosshairs are visible, to avoid unnecessary repaints, as
 *               suggested by Kees Kuip (DG);
 * 25-Jul-2002 : Moved lower and upper margin attributes from the NumberAxis
 *               class (DG);
 * 05-Sep-2002 : Updated constructor for changes in Axis class (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 04-Oct-2002 : Moved standardTickUnits from NumberAxis --> ValueAxis (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 19-Nov-2002 : Removed grid settings (now controlled by the plot) (DG);
 * 27-Nov-2002 : Moved the 'inverted' attributed from NumberAxis to
 *               ValueAxis (DG);
 * 03-Jan-2003 : Small fix to ensure auto-range minimum is observed
 *               immediately (DG);
 * 14-Jan-2003 : Changed autoRangeMinimumSize from Number --> double (DG);
 * 20-Jan-2003 : Replaced monolithic constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 09-May-2003 : Added AxisLocation parameter to translation methods (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 01-Sep-2003 : Fixed bug 793167 (setMaximumAxisValue exception) (DG);
 * 02-Sep-2003 : Fixed bug 795366 (zooming on inverted axes) (DG);
 * 08-Sep-2003 : Completed Serialization support (NB);
 * 08-Sep-2003 : Renamed get/setMinimumValue --> get/setLowerBound,
 *               and get/setMaximumValue --> get/setUpperBound (DG);
 * 27-Oct-2003 : Changed DEFAULT_AUTO_RANGE_MINIMUM_SIZE value - see bug ID
 *               829606 (DG);
 * 07-Nov-2003 : Changes to tick mechanism (DG);
 * 06-Jan-2004 : Moved axis line attributes to Axis class (DG);
 * 21-Jan-2004 : Removed redundant axisLineVisible attribute.  Renamed
 *               translateJava2DToValue --> java2DToValue, and
 *               translateValueToJava2D --> valueToJava2D (DG);
 * 23-Jan-2004 : Fixed setAxisLinePaint() and setAxisLineStroke() which had no
 *               effect (andreas.gawecki@coremedia.com);
 * 07-Apr-2004 : Changed text bounds calculation (DG);
 * 26-Apr-2004 : Added getter/setter methods for arrow shapes (DG);
 * 18-May-2004 : Added methods to set axis range *including* current
 *               margins (DG);
 * 02-Jun-2004 : Fixed bug in setRangeWithMargins() method (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities
 *               --> TextUtilities (DG);
 * 11-Jan-2005 : Removed deprecated methods in preparation for 1.0.0
 *               release (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 10-Oct-2006 : Source reformatting (DG);
 * 22-Mar-2007 : Added new defaultAutoRange attribute (DG);
 * 02-Aug-2007 : Check for major tick when drawing label (DG);
 * 25-Sep-2008 : Added minor tick support, see patch 1934255 by Peter Kolb (DG);
 * 21-Jan-2009 : Updated default behaviour of minor ticks (DG);
 * 18-Mar-2008 : Added resizeRange2() method which provides more natural
 *               anchored zooming for mouse wheel support (DG);
 * 26-Mar-2009 : In equals(), only check current range if autoRange is
 *               false (DG);
 * 30-Mar-2009 : Added pan(double) method (DG);
 *
 */

package org.afree.chart.axis;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.data.Range;
import org.afree.chart.event.AxisChangeEvent;
import org.afree.chart.plot.Plot;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.Polygon;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintUtility;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * The base class for axes that display value data, where values are measured
 * using the <code>double</code> primitive. The two key subclasses are
 * {@link DateAxis} and {@link NumberAxis}.
 */
public abstract class ValueAxis extends Axis implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 3698345477322391456L;

    /** The default axis range. */
    public static final Range DEFAULT_RANGE = new Range(0.0, 1.0);

    /** The default auto-range value. */
    public static final boolean DEFAULT_AUTO_RANGE = true;

    /** The default inverted flag setting. */
    public static final boolean DEFAULT_INVERTED = false;

    /** The default minimum auto range. */
    public static final double DEFAULT_AUTO_RANGE_MINIMUM_SIZE = 0.00000001;

    /** The default value for the lower margin (0.05 = 5%). */
    public static final double DEFAULT_LOWER_MARGIN = 0.05;

    /** The default value for the upper margin (0.05 = 5%). */
    public static final double DEFAULT_UPPER_MARGIN = 0.05;

    /** The default auto-tick-unit-selection value. */
    public static final boolean DEFAULT_AUTO_TICK_UNIT_SELECTION = true;

    /** The maximum tick count. */
    public static final int MAXIMUM_TICK_COUNT = 500;

    /**
     * A flag that controls whether an arrow is drawn at the positive end of the
     * axis line.
     */
    private boolean positiveArrowVisible;

    /**
     * A flag that controls whether an arrow is drawn at the negative end of the
     * axis line.
     */
    private boolean negativeArrowVisible;

    /** The shape used for an up arrow. */
    private transient Shape upArrow;

    /** The shape used for a down arrow. */
    private transient Shape downArrow;

    /** The shape used for a left arrow. */
    private transient Shape leftArrow;

    /** The shape used for a right arrow. */
    private transient Shape rightArrow;

    //performance tuning
    /** A flag that affects the orientation of the values on the axis. */
    //private boolean inverted;
    protected boolean mInverted;

    //performance tuning
    /** The axis range. */
    //private Range range;
    protected Range mRange;

    /**
     * Flag that indicates whether the axis automatically scales to fit the
     * chart data.
     */
    private boolean autoRange;

    /** The minimum size for the 'auto' axis range (excluding margins). */
    private double autoRangeMinimumSize;

    /**
     * The default range is used when the dataset is empty and the axis needs to
     * determine the auto range.
     * 
     * @since JFreeChart 1.0.5
     */
    private Range defaultAutoRange;

    /**
     * The upper margin percentage. This indicates the amount by which the
     * maximum axis value exceeds the maximum data value (as a percentage of the
     * range on the axis) when the axis range is determined automatically.
     */
    private double upperMargin;

    /**
     * The lower margin. This is a percentage that indicates the amount by which
     * the minimum axis value is "less than" the minimum data value when the
     * axis range is determined automatically.
     */
    private double lowerMargin;

    /**
     * If this value is positive, the amount is subtracted from the maximum data
     * value to determine the lower axis range. This can be used to provide a
     * fixed "window" on dynamic data.
     */
    private double fixedAutoRange;

    /**
     * Flag that indicates whether or not the tick unit is selected
     * automatically.
     */
    private boolean autoTickUnitSelection;

    /** The standard tick units for the axis. */
    private TickUnitSource standardTickUnits;

    /** An index into an array of standard tick values. */
    private int autoTickIndex;

    /**
     * The number of minor ticks per major tick unit. This is an override field,
     * if the value is > 0 it is used, otherwise the axis refers to the
     * minorTickCount in the current tickUnit.
     */
    private int minorTickCount;

    /** A flag indicating whether or not tick labels are rotated to vertical. */
    private boolean verticalTickLabels;
    
    private boolean limitAble = false;
    private Range limitRange = null;
    
    /** work LineShape object */
    private LineShape mWorkLineShape = new LineShape();
    
    /**
     * Constructs a value axis.
     * 
     * @param label
     *            the axis label (<code>null</code> permitted).
     * @param standardTickUnits
     *            the source for standard tick units (<code>null</code>
     *            permitted).
     */
    protected ValueAxis(String label, TickUnitSource standardTickUnits) {

        super(label);

        this.positiveArrowVisible = false;
        this.negativeArrowVisible = false;

        this.mRange = DEFAULT_RANGE;
        this.autoRange = DEFAULT_AUTO_RANGE;
        this.defaultAutoRange = DEFAULT_RANGE;

        this.mInverted = DEFAULT_INVERTED;
        this.autoRangeMinimumSize = DEFAULT_AUTO_RANGE_MINIMUM_SIZE;

        this.lowerMargin = DEFAULT_LOWER_MARGIN;
        this.upperMargin = DEFAULT_UPPER_MARGIN;

        this.fixedAutoRange = 0.0;

        this.autoTickUnitSelection = DEFAULT_AUTO_TICK_UNIT_SELECTION;
        this.standardTickUnits = standardTickUnits;

        Polygon p1 = new Polygon();
        p1.addPoint(0, 0);
        p1.addPoint(-2, 2);
        p1.addPoint(2, 2);

        this.upArrow = p1;

        Polygon p2 = new Polygon();
        p2.addPoint(0, 0);
        p2.addPoint(-2, -2);
        p2.addPoint(2, -2);

        this.downArrow = p2;

        Polygon p3 = new Polygon();
        p3.addPoint(0, 0);
        p3.addPoint(-2, -2);
        p3.addPoint(-2, 2);

        this.rightArrow = p3;

        Polygon p4 = new Polygon();
        p4.addPoint(0, 0);
        p4.addPoint(2, -2);
        p4.addPoint(2, 2);

        this.leftArrow = p4;

        this.verticalTickLabels = false;
        this.minorTickCount = 0;

    }

    /**
     * Returns <code>true</code> if the tick labels should be rotated (to
     * vertical), and <code>false</code> otherwise.
     * 
     * @return <code>true</code> or <code>false</code>.
     * 
     * @see #setVerticalTickLabels(boolean)
     */
    public boolean isVerticalTickLabels() {
        return this.verticalTickLabels;
    }

    /**
     * Sets the flag that controls whether the tick labels are displayed
     * vertically (that is, rotated 90 degrees from horizontal). If the flag is
     * changed, an {@link AxisChangeEvent} is sent to all registered listeners.
     * 
     * @param flag
     *            the flag.
     * 
     * @see #isVerticalTickLabels()
     */
    public void setVerticalTickLabels(boolean flag) {
        if (this.verticalTickLabels != flag) {
            this.verticalTickLabels = flag;
            notifyListeners(new AxisChangeEvent(this));
        }
    }

    /**
     * Returns a flag that controls whether or not the axis line has an arrow
     * drawn that points in the positive direction for the axis.
     * 
     * @return A boolean.
     * 
     * @see #setPositiveArrowVisible(boolean)
     */
    public boolean isPositiveArrowVisible() {
        return this.positiveArrowVisible;
    }

    /**
     * Sets a flag that controls whether or not the axis lines has an arrow
     * drawn that points in the positive direction for the axis, and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #isPositiveArrowVisible()
     */
    public void setPositiveArrowVisible(boolean visible) {
        this.positiveArrowVisible = visible;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns a flag that controls whether or not the axis line has an arrow
     * drawn that points in the negative direction for the axis.
     * 
     * @return A boolean.
     * 
     * @see #setNegativeArrowVisible(boolean)
     */
    public boolean isNegativeArrowVisible() {
        return this.negativeArrowVisible;
    }

    /**
     * Sets a flag that controls whether or not the axis lines has an arrow
     * drawn that points in the negative direction for the axis, and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #setNegativeArrowVisible(boolean)
     */
    public void setNegativeArrowVisible(boolean visible) {
        this.negativeArrowVisible = visible;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns a shape that can be displayed as an arrow pointing upwards at the
     * end of an axis line.
     * 
     * @return A shape (never <code>null</code>).
     * 
     * @see #setUpArrow(Shape)
     */
    public Shape getUpArrow() {
        return this.upArrow;
    }

    /**
     * Sets the shape that can be displayed as an arrow pointing upwards at the
     * end of an axis line and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * 
     * @param arrow
     *            the arrow shape (<code>null</code> not permitted).
     * 
     * @see #getUpArrow()
     */
    public void setUpArrow(Shape arrow) {
        if (arrow == null) {
            throw new IllegalArgumentException("Null 'arrow' argument.");
        }
        this.upArrow = arrow;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns a shape that can be displayed as an arrow pointing downwards at
     * the end of an axis line.
     * 
     * @return A shape (never <code>null</code>).
     * 
     * @see #setDownArrow(Shape)
     */
    public Shape getDownArrow() {
        return this.downArrow;
    }

    /**
     * Sets the shape that can be displayed as an arrow pointing downwards at
     * the end of an axis line and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * 
     * @param arrow
     *            the arrow shape (<code>null</code> not permitted).
     * 
     * @see #getDownArrow()
     */
    public void setDownArrow(Shape arrow) {
        if (arrow == null) {
            throw new IllegalArgumentException("Null 'arrow' argument.");
        }
        this.downArrow = arrow;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns a shape that can be displayed as an arrow pointing left at the
     * end of an axis line.
     * 
     * @return A shape (never <code>null</code>).
     * 
     * @see #setLeftArrow(Shape)
     */
    public Shape getLeftArrow() {
        return this.leftArrow;
    }

    /**
     * Sets the shape that can be displayed as an arrow pointing left at the end
     * of an axis line and sends an {@link AxisChangeEvent} to all registered
     * listeners.
     * 
     * @param arrow
     *            the arrow shape (<code>null</code> not permitted).
     * 
     * @see #getLeftArrow()
     */
    public void setLeftArrow(Shape arrow) {
        if (arrow == null) {
            throw new IllegalArgumentException("Null 'arrow' argument.");
        }
        this.leftArrow = arrow;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns a shape that can be displayed as an arrow pointing right at the
     * end of an axis line.
     * 
     * @return A shape (never <code>null</code>).
     * 
     * @see #setRightArrow(Shape)
     */
    public Shape getRightArrow() {
        return this.rightArrow;
    }

    /**
     * Sets the shape that can be displayed as an arrow pointing rightwards at
     * the end of an axis line and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * 
     * @param arrow
     *            the arrow shape (<code>null</code> not permitted).
     * 
     * @see #getRightArrow()
     */
    public void setRightArrow(Shape arrow) {
        if (arrow == null) {
            throw new IllegalArgumentException("Null 'arrow' argument.");
        }
        this.rightArrow = arrow;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Draws an axis line at the current cursor position and edge.
     * 
     * @param canvas
     *            the graphics device.
     * @param cursor
     *            the cursor position.
     * @param dataArea
     *            the data area.
     * @param edge
     *            the edge.
     */
    protected void drawAxisLine(Canvas canvas, double cursor, RectShape dataArea,
            RectangleEdge edge) {
        //performance tuning
//        LineShape axisLine = null;
        LineShape axisLine = this.mWorkLineShape;
        if (edge == RectangleEdge.TOP) {
//            axisLine = new LineShape(dataArea.getX(), cursor, dataArea
//                    .getMaxX(), cursor);
            axisLine.setLine(dataArea.getX(), cursor, dataArea
                  .getMaxX(), cursor);
        } else if (edge == RectangleEdge.BOTTOM) {
//            axisLine = new LineShape(dataArea.getX(), cursor, dataArea
//                    .getMaxX(), cursor);
            axisLine.setLine(dataArea.getX(), cursor, dataArea
                  .getMaxX(), cursor);
        } else if (edge == RectangleEdge.LEFT) {
//            axisLine = new LineShape(cursor, dataArea.getY(), cursor,
//                    dataArea.getMaxY());
            axisLine.setLine(cursor, dataArea.getY(), cursor,
                  dataArea.getMaxY());
        } else if (edge == RectangleEdge.RIGHT) {
//            axisLine = new LineShape(cursor, dataArea.getY(), cursor,
//                    dataArea.getMaxY());
            axisLine.setLine(cursor, dataArea.getY(), cursor,
                  dataArea.getMaxY());
        }

        Paint p = PaintUtility.createPaint(getAxisLinePaintType(), getAxisLineStroke(), getAxisLineEffect());
        axisLine.draw(canvas, p);

        boolean drawUpOrRight = false;
        boolean drawDownOrLeft = false;
        if (this.positiveArrowVisible) {
            if (this.mInverted) {
                drawDownOrLeft = true;
            } else {
                drawUpOrRight = true;
            }
        }
        if (this.negativeArrowVisible) {
            if (this.mInverted) {
                drawUpOrRight = true;
            } else {
                drawDownOrLeft = true;
            }
        }
        if (drawUpOrRight) {
            double x = 0.0;
            double y = 0.0;
            Shape arrow = null;
            if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
                x = dataArea.getMaxX();
                y = cursor;
                arrow = this.rightArrow;
            } else if (edge == RectangleEdge.LEFT
                    || edge == RectangleEdge.RIGHT) {
                x = cursor;
                y = dataArea.getMinY();
                arrow = this.upArrow;
            }

            // draw the arrow...
            Matrix mat = new Matrix();
            mat.postTranslate((float)x, (float)y);
            
            Shape shape = arrow.clone();
            shape.getPath().transform(mat);

            shape.draw(canvas, p);
        }

        if (drawDownOrLeft) {
            double x = 0.0;
            double y = 0.0;
            Shape arrow = null;
            if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
                x = dataArea.getMinX();
                y = cursor;
                arrow = this.leftArrow;
            } else if (edge == RectangleEdge.LEFT
                    || edge == RectangleEdge.RIGHT) {
                x = cursor;
                y = dataArea.getMaxY();
                arrow = this.downArrow;
            }

            // draw the arrow...
            Matrix mat = new Matrix();
            mat.postTranslate((float)x, (float)y);
            
            Shape shape = arrow.clone();
            shape.getPath().transform(mat);

            shape.draw(canvas, p);
        }

    }

    /**
     * Calculates the anchor point for a tick label.
     * 
     * @param tick
     *            the tick.
     * @param cursor
     *            the cursor.
     * @param dataArea
     *            the data area.
     * @param edge
     *            the edge on which the axis is drawn.
     * 
     * @return The x and y coordinates of the anchor point.
     */
    protected float[] calculateAnchorPoint(ValueTick tick, double cursor,
            RectShape dataArea, RectangleEdge edge, Paint paint) {

        //performance tuning
//        RectShape labelBounds = TextUtilities.getTextBounds(tick.getText(), paint);
        
        RectangleInsets insets = getTickLabelInsets();
        float[] result = new float[2];
        if (edge == RectangleEdge.TOP) {
            result[0] = (float) valueToJava2D(tick.getValue(), dataArea, edge);
            result[1] = (float) (cursor - insets.getBottom() - 2.0);
        } else if (edge == RectangleEdge.BOTTOM) {
            result[0] = (float) valueToJava2D(tick.getValue(), dataArea, edge);
            result[1] = (float) (cursor + insets.getTop() + 2.0);
        } else if (edge == RectangleEdge.LEFT) {
            result[0] = (float) (cursor - insets.getLeft() - 2.0);
            result[1] = (float) valueToJava2D(tick.getValue(), dataArea, edge);
        } else if (edge == RectangleEdge.RIGHT) {
            result[0] = (float) (cursor + insets.getRight() + 2.0);
            result[1] = (float) valueToJava2D(tick.getValue(), dataArea, edge);
        }
        return result;
    }
    
    /**
     * Draws the axis line, tick marks and tick mark labels.
     * 
     * @param canvas
     *            the graphics device.
     * @param cursor
     *            the cursor.
     * @param plotArea
     *            the plot area.
     * @param dataArea
     *            the data area.
     * @param edge
     *            the edge that the axis is aligned with.
     * 
     * @return The width or height used to draw the axis.
     */
    protected AxisState drawTickMarksAndLabels(Canvas canvas, double cursor,
            RectShape plotArea, RectShape dataArea, RectangleEdge edge) {

        AxisState state = new AxisState(cursor);

        if (isAxisLineVisible()) {
            drawAxisLine(canvas, cursor, dataArea, edge);
        }

        List ticks = refreshTicks(canvas, state, dataArea, edge);
        state.setTicks(ticks);
        // canvas.setFont(getTickLabelFont());
        
        //performance tuning
        Paint tickPaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getTickLabelPaintType(), getTickLabelFont());
        Paint tickMark = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getTickMarkPaintType(),getTickMarkStroke(), getTickMarkEffect());
        
        Iterator iterator = ticks.iterator();
        while (iterator.hasNext()) {
            ValueTick tick = (ValueTick) iterator.next();
            if (isTickLabelsVisible()) {

                //Paint tickPaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getTickLabelPaintType(), getTickLabelFont());
                float[] anchorPoint = calculateAnchorPoint(tick, cursor,
                        dataArea, edge, tickPaint);
                TextUtilities.drawRotatedString(tick.getText(), canvas,
                        anchorPoint[0], anchorPoint[1], tick.getTextAnchor(),
                        tick.getAngle(), tick.getRotationAnchor(), tickPaint);
            }

            if ((isTickMarksVisible() && tick.getTickType().equals(
                    TickType.MAJOR))
                    || (isMinorTickMarksVisible() && tick.getTickType().equals(
                            TickType.MINOR))) {

                double ol = (tick.getTickType().equals(TickType.MINOR)) ? getMinorTickMarkOutsideLength()
                        : getTickMarkOutsideLength();

                double il = (tick.getTickType().equals(TickType.MINOR)) ? getMinorTickMarkInsideLength()
                        : getTickMarkInsideLength();

                float xx = (float) valueToJava2D(tick.getValue(), dataArea,
                        edge);
                //performance tuning
//                LineShape mark = null;
                LineShape mark = this.mWorkLineShape;
                
                //Paint tickMark = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getTickMarkPaintType(),getTickMarkStroke(), getTickMarkEffect());
                if (edge == RectangleEdge.LEFT) {
                    //mark = new LineShape(cursor - ol, xx, cursor + il, xx);
                    mark.setLine(cursor - ol, xx, cursor + il, xx);
                } else if (edge == RectangleEdge.RIGHT) {
                    //mark = new LineShape(cursor + ol, xx, cursor - il, xx);
                    mark.setLine(cursor + ol, xx, cursor - il, xx);
                } else if (edge == RectangleEdge.TOP) {
                    //mark = new LineShape(xx, cursor - ol, xx, cursor + il);
                    mark.setLine(xx, cursor - ol, xx, cursor + il);
                } else if (edge == RectangleEdge.BOTTOM) {
                    //mark = new LineShape(xx, cursor + ol, xx, cursor - il);
                    mark.setLine(xx, cursor + ol, xx, cursor - il);
                }
//                canvas.drawLine((float) mark.getX1(), (float) mark.getY1(),
//                        (float) mark.getX2(), (float) mark.getY2(), tickMark);
                mark.draw(canvas,tickMark);
            }
        }

        // need to work out the space used by the tick labels...
        // so we can update the cursor...
        double used = 0.0;
        if (isTickLabelsVisible()) {
            if (edge == RectangleEdge.LEFT) {
                used += findMaximumTickLabelWidth(ticks, canvas, plotArea,
                        isVerticalTickLabels());
                state.cursorLeft(used);
            } else if (edge == RectangleEdge.RIGHT) {
                used = findMaximumTickLabelWidth(ticks, canvas, plotArea,
                        isVerticalTickLabels());
                state.cursorRight(used);
            } else if (edge == RectangleEdge.TOP) {
                used = findMaximumTickLabelHeight(ticks, canvas, plotArea,
                        isVerticalTickLabels());
                state.cursorUp(used);
            } else if (edge == RectangleEdge.BOTTOM) {
                used = findMaximumTickLabelHeight(ticks, canvas, plotArea,
                        isVerticalTickLabels());
                state.cursorDown(used);
            }
        }

        return state;
    }

    /**
     * Returns the space required to draw the axis.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot that the axis belongs to.
     * @param plotArea
     *            the area within which the plot should be drawn.
     * @param edge
     *            the axis location.
     * @param space
     *            the space already reserved (for other axes).
     * 
     * @return The space required to draw the axis (including pre-reserved
     *         space).
     */
    public AxisSpace reserveSpace(Canvas canvas, Plot plot, RectShape plotArea,
            RectangleEdge edge, AxisSpace space) {

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

        // calculate the max size of the tick labels (if visible)...
        double tickLabelHeight = 0.0;
        double tickLabelWidth = 0.0;
        if (isTickLabelsVisible()) {
            // canvas.setFont(getTickLabelFont());
            List ticks = refreshTicks(canvas, new AxisState(), plotArea, edge);
            if (RectangleEdge.isTopOrBottom(edge)) {
                tickLabelHeight = findMaximumTickLabelHeight(ticks, canvas,
                        plotArea, isVerticalTickLabels());
            } else if (RectangleEdge.isLeftOrRight(edge)) {
                tickLabelWidth = findMaximumTickLabelWidth(ticks, canvas, plotArea,
                        isVerticalTickLabels());
            }
        }

        // get the axis label size and update the space object...
        RectShape labelEnclosure = getLabelEnclosure(canvas, edge);
        double labelHeight = 0.0;
        double labelWidth = 0.0;
        if (RectangleEdge.isTopOrBottom(edge)) {
            labelHeight = labelEnclosure.getHeight();
            space.add(labelHeight + tickLabelHeight, edge);
        } else if (RectangleEdge.isLeftOrRight(edge)) {
            labelWidth = labelEnclosure.getWidth();
            space.add(labelWidth + tickLabelWidth, edge);
        }

        return space;

    }

    /**
     * A utility method for determining the height of the tallest tick label.
     * 
     * @param ticks
     *            the ticks.
     * @param canvas
     *            the graphics device.
     * @param drawArea
     *            the area within which the plot and axes should be drawn.
     * @param vertical
     *            a flag that indicates whether or not the tick labels are
     *            'vertical'.
     * 
     * @return The height of the tallest tick label.
     */
    protected double findMaximumTickLabelHeight(List ticks, Canvas canvas,
            RectShape drawArea, boolean vertical) {

        RectangleInsets insets = getTickLabelInsets();
        // Font font = getTickLabelFont();
        double maxHeight = 0.0;
        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getTickLabelPaintType(), getTickLabelFont());
        if (vertical) {

            Iterator iterator = ticks.iterator();
            while (iterator.hasNext()) {
                Tick tick = (Tick) iterator.next();
                RectShape labelBounds = TextUtilities.getTextBounds(tick
                        .getText(), paint);
                if (labelBounds.getWidth() + insets.getTop()
                        + insets.getBottom() > maxHeight) {
                    maxHeight = labelBounds.getWidth() + insets.getTop()
                            + insets.getBottom();
                }
            }
        } else {

            RectShape labelBounds = TextUtilities.getTextBounds("ABCxyz",
                    paint);

            maxHeight = labelBounds.getHeight() + insets.getTop()
                    + insets.getBottom();
        }
        return maxHeight;

    }

    /**
     * A utility method for determining the width of the widest tick label.
     * 
     * @param ticks
     *            the ticks.
     * @param canvas
     *            the graphics device.
     * @param drawArea
     *            the area within which the plot and axes should be drawn.
     * @param vertical
     *            a flag that indicates whether or not the tick labels are
     *            'vertical'.
     * 
     * @return The width of the tallest tick label.
     */
    protected double findMaximumTickLabelWidth(List ticks, Canvas canvas,
            RectShape drawArea, boolean vertical) {

        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getTickLabelPaintType(), getTickLabelFont());
        RectangleInsets insets = getTickLabelInsets();
        // Font font = getTickLabelFont();
        double maxWidth = 0.0;
        if (!vertical) {

            Iterator iterator = ticks.iterator();
            while (iterator.hasNext()) {
                Tick tick = (Tick) iterator.next();
                //performance tuning
//                RectShape labelBounds = TextUtilities.getTextBounds(tick
//                        .getText(), paint);
//                if (labelBounds.getWidth() + insets.getLeft()
//                        + insets.getRight() > maxWidth) {
//                    maxWidth = labelBounds.getWidth() + insets.getLeft()
//                            + insets.getRight();
//                }
                float labelWidth = TextUtilities.getTextWidth(tick
                        .getText(), paint);
                if (labelWidth + insets.getLeft()
                        + insets.getRight() > maxWidth) {
                    maxWidth = labelWidth + insets.getLeft()
                            + insets.getRight();
                }
            }
        } else {
            RectShape labelBounds = TextUtilities.getTextBounds("ABCxyz",
                    paint);
            maxWidth = labelBounds.getHeight() + insets.getTop()
                    + insets.getBottom();
        }
        return maxWidth;

    }

    /**
     * Returns a flag that controls the direction of values on the axis.
     * <P>
     * For a regular axis, values increase from left to right (for a horizontal
     * axis) and bottom to top (for a vertical axis). When the axis is
     * 'inverted', the values increase in the opposite direction.
     * 
     * @return The flag.
     * 
     * @see #setInverted(boolean)
     */
    public boolean isInverted() {
        return this.mInverted;
    }

    /**
     * Sets a flag that controls the direction of values on the axis, and
     * notifies registered listeners that the axis has changed.
     * 
     * @param flag
     *            the flag.
     * 
     * @see #isInverted()
     */
    public void setInverted(boolean flag) {

        if (this.mInverted != flag) {
            this.mInverted = flag;
            notifyListeners(new AxisChangeEvent(this));
        }

    }

    /**
     * Returns the flag that controls whether or not the axis range is
     * automatically adjusted to fit the data values.
     * 
     * @return The flag.
     * 
     * @see #setAutoRange(boolean)
     */
    public boolean isAutoRange() {
        return this.autoRange;
    }

    /**
     * Sets a flag that determines whether or not the axis range is
     * automatically adjusted to fit the data, and notifies registered listeners
     * that the axis has been modified.
     * 
     * @param auto
     *            the new value of the flag.
     * 
     * @see #isAutoRange()
     */
    public void setAutoRange(boolean auto) {
        setAutoRange(auto, true);
    }

    /**
     * Sets the auto range attribute. If the <code>notify</code> flag is set, an
     * {@link AxisChangeEvent} is sent to registered listeners.
     * 
     * @param auto
     *            the flag.
     * @param notify
     *            notify listeners?
     * 
     * @see #isAutoRange()
     */
    protected void setAutoRange(boolean auto, boolean notify) {
        if (this.autoRange != auto) {
            this.autoRange = auto;
            if (this.autoRange) {
                autoAdjustRange();
            }
            if (notify) {
                notifyListeners(new AxisChangeEvent(this));
            }
        }
    }

    /**
     * Returns the minimum size allowed for the axis range when it is
     * automatically calculated.
     * 
     * @return The minimum range.
     * 
     * @see #setAutoRangeMinimumSize(double)
     */
    public double getAutoRangeMinimumSize() {
        return this.autoRangeMinimumSize;
    }

    /**
     * Sets the auto range minimum size and sends an {@link AxisChangeEvent} to
     * all registered listeners.
     * 
     * @param size
     *            the size.
     * 
     * @see #getAutoRangeMinimumSize()
     */
    public void setAutoRangeMinimumSize(double size) {
        setAutoRangeMinimumSize(size, true);
    }

    /**
     * Sets the minimum size allowed for the axis range when it is automatically
     * calculated.
     * <p>
     * If requested, an {@link AxisChangeEvent} is forwarded to all registered
     * listeners.
     * 
     * @param size
     *            the new minimum.
     * @param notify
     *            notify listeners?
     */
    public void setAutoRangeMinimumSize(double size, boolean notify) {
        if (size <= 0.0) {
            throw new IllegalArgumentException(
                    "NumberAxis.setAutoRangeMinimumSize(double): must be > 0.0.");
        }
        if (this.autoRangeMinimumSize != size) {
            this.autoRangeMinimumSize = size;
            if (this.autoRange) {
                autoAdjustRange();
            }
            if (notify) {
                notifyListeners(new AxisChangeEvent(this));
            }
        }

    }

    /**
     * Returns the default auto range.
     * 
     * @return The default auto range (never <code>null</code>).
     * 
     * @see #setDefaultAutoRange(Range)
     * 
     * @since JFreeChart 1.0.5
     */
    public Range getDefaultAutoRange() {
        return this.defaultAutoRange;
    }

    /**
     * Sets the default auto range and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * 
     * @param range
     *            the range (<code>null</code> not permitted).
     * 
     * @see #getDefaultAutoRange()
     * 
     * @since JFreeChart 1.0.5
     */
    public void setDefaultAutoRange(Range range) {
        if (range == null) {
            throw new IllegalArgumentException("Null 'range' argument.");
        }
        this.defaultAutoRange = range;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the lower margin for the axis, expressed as a percentage of the
     * axis range. This controls the space added to the lower end of the axis
     * when the axis range is automatically calculated (it is ignored when the
     * axis range is set explicitly). The default value is 0.05 (five percent).
     * 
     * @return The lower margin.
     * 
     * @see #setLowerMargin(double)
     */
    public double getLowerMargin() {
        return this.lowerMargin;
    }

    /**
     * Sets the lower margin for the axis (as a percentage of the axis range)
     * and sends an {@link AxisChangeEvent} to all registered listeners. This
     * margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     * 
     * @param margin
     *            the margin percentage (for example, 0.05 is five percent).
     * 
     * @see #getLowerMargin()
     * @see #setUpperMargin(double)
     */
    public void setLowerMargin(double margin) {
        this.lowerMargin = margin;
        if (isAutoRange()) {
            autoAdjustRange();
        }
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the upper margin for the axis, expressed as a percentage of the
     * axis range. This controls the space added to the lower end of the axis
     * when the axis range is automatically calculated (it is ignored when the
     * axis range is set explicitly). The default value is 0.05 (five percent).
     * 
     * @return The upper margin.
     * 
     * @see #setUpperMargin(double)
     */
    public double getUpperMargin() {
        return this.upperMargin;
    }

    /**
     * Sets the upper margin for the axis (as a percentage of the axis range)
     * and sends an {@link AxisChangeEvent} to all registered listeners. This
     * margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     * 
     * @param margin
     *            the margin percentage (for example, 0.05 is five percent).
     * 
     * @see #getLowerMargin()
     * @see #setLowerMargin(double)
     */
    public void setUpperMargin(double margin) {
        this.upperMargin = margin;
        if (isAutoRange()) {
            autoAdjustRange();
        }
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the fixed auto range.
     * 
     * @return The length.
     * 
     * @see #setFixedAutoRange(double)
     */
    public double getFixedAutoRange() {
        return this.fixedAutoRange;
    }

    /**
     * Sets the fixed auto range for the axis.
     * 
     * @param length
     *            the range length.
     * 
     * @see #getFixedAutoRange()
     */
    public void setFixedAutoRange(double length) {
        this.fixedAutoRange = length;
        if (isAutoRange()) {
            autoAdjustRange();
        }
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the lower bound of the axis range.
     * 
     * @return The lower bound.
     * 
     * @see #setLowerBound(double)
     */
    public double getLowerBound() {
        return this.mRange.getLowerBound();
    }

    /**
     * Sets the lower bound for the axis range. An {@link AxisChangeEvent} is
     * sent to all registered listeners.
     * 
     * @param min
     *            the new minimum.
     * 
     * @see #getLowerBound()
     */
    public void setLowerBound(double min) {
        if (this.mRange.getUpperBound() > min) {
            setRange(new Range(min, this.mRange.getUpperBound()));
        } else {
            setRange(new Range(min, min + 1.0));
        }
    }

    /**
     * Returns the upper bound for the axis range.
     * 
     * @return The upper bound.
     * 
     * @see #setUpperBound(double)
     */
    public double getUpperBound() {
        return this.mRange.getUpperBound();
    }

    /**
     * Sets the upper bound for the axis range, and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param max
     *            the new maximum.
     * 
     * @see #getUpperBound()
     */
    public void setUpperBound(double max) {
        if (this.mRange.getLowerBound() < max) {
            setRange(new Range(this.mRange.getLowerBound(), max));
        } else {
            setRange(max - 1.0, max);
        }
    }

    /**
     * Returns the range for the axis.
     * 
     * @return The axis range (never <code>null</code>).
     * 
     * @see #setRange(Range)
     */
    public Range getRange() {
        return this.mRange;
    }

    /**
     * Sets the range attribute and sends an {@link AxisChangeEvent} to all
     * registered listeners. As a side-effect, the auto-range flag is set to
     * <code>false</code>.
     * 
     * @param range
     *            the range (<code>null</code> not permitted).
     * 
     * @see #getRange()
     */
    public void setRange(Range range) {
        // defer argument checking
        setRange(range, true, true);
    }

    /**
     * Sets the range for the axis, if requested, sends an
     * {@link AxisChangeEvent} to all registered listeners. As a side-effect,
     * the auto-range flag is set to <code>false</code> (optional).
     * 
     * @param range
     *            the range (<code>null</code> not permitted).
     * @param turnOffAutoRange
     *            a flag that controls whether or not the auto range is turned
     *            off.
     * @param notify
     *            a flag that controls whether or not listeners are notified.
     * 
     * @see #getRange()
     */
    public void setRange(Range range, boolean turnOffAutoRange, boolean notify) {
        if (range == null) {
            throw new IllegalArgumentException("Null 'range' argument.");
        }
        if (turnOffAutoRange) {
            this.autoRange = false;
        }
        this.mRange = range;
        if (notify) {
            notifyListeners(new AxisChangeEvent(this));
        }
    }

    /**
     * Sets the axis range and sends an {@link AxisChangeEvent} to all
     * registered listeners. As a side-effect, the auto-range flag is set to
     * <code>false</code>.
     * 
     * @param lower
     *            the lower axis limit.
     * @param upper
     *            the upper axis limit.
     * 
     * @see #getRange()
     * @see #setRange(Range)
     */
    public void setRange(double lower, double upper) {
        setRange(new Range(lower, upper));
    }

    /**
     * Sets the range for the axis (after first adding the current margins to
     * the specified range) and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * 
     * @param range
     *            the range (<code>null</code> not permitted).
     */
    public void setRangeWithMargins(Range range) {
        setRangeWithMargins(range, true, true);
    }

    /**
     * Sets the range for the axis after first adding the current margins to the
     * range and, if requested, sends an {@link AxisChangeEvent} to all
     * registered listeners. As a side-effect, the auto-range flag is set to
     * <code>false</code> (optional).
     * 
     * @param range
     *            the range (excluding margins, <code>null</code> not
     *            permitted).
     * @param turnOffAutoRange
     *            a flag that controls whether or not the auto range is turned
     *            off.
     * @param notify
     *            a flag that controls whether or not listeners are notified.
     */
    public void setRangeWithMargins(Range range, boolean turnOffAutoRange,
            boolean notify) {
        if (range == null) {
            throw new IllegalArgumentException("Null 'range' argument.");
        }
        setRange(Range.expand(range, getLowerMargin(), getUpperMargin()),
                turnOffAutoRange, notify);
    }

    /**
     * Sets the axis range (after first adding the current margins to the range)
     * and sends an {@link AxisChangeEvent} to all registered listeners. As a
     * side-effect, the auto-range flag is set to <code>false</code>.
     * 
     * @param lower
     *            the lower axis limit.
     * @param upper
     *            the upper axis limit.
     */
    public void setRangeWithMargins(double lower, double upper) {
        setRangeWithMargins(new Range(lower, upper));
    }

    /**
     * Sets the axis range, where the new range is 'size' in length, and
     * centered on 'value'.
     * 
     * @param value
     *            the central value.
     * @param length
     *            the range length.
     */
    public void setRangeAboutValue(double value, double length) {
        setRange(new Range(value - length / 2, value + length / 2));
    }

    /**
     * Returns a flag indicating whether or not the tick unit is automatically
     * selected from a range of standard tick units.
     * 
     * @return A flag indicating whether or not the tick unit is automatically
     *         selected.
     * 
     * @see #setAutoTickUnitSelection(boolean)
     */
    public boolean isAutoTickUnitSelection() {
        return this.autoTickUnitSelection;
    }

    /**
     * Sets a flag indicating whether or not the tick unit is automatically
     * selected from a range of standard tick units. If the flag is changed,
     * registered listeners are notified that the chart has changed.
     * 
     * @param flag
     *            the new value of the flag.
     * 
     * @see #isAutoTickUnitSelection()
     */
    public void setAutoTickUnitSelection(boolean flag) {
        setAutoTickUnitSelection(flag, true);
    }

    /**
     * Sets a flag indicating whether or not the tick unit is automatically
     * selected from a range of standard tick units.
     * 
     * @param flag
     *            the new value of the flag.
     * @param notify
     *            notify listeners?
     * 
     * @see #isAutoTickUnitSelection()
     */
    public void setAutoTickUnitSelection(boolean flag, boolean notify) {

        if (this.autoTickUnitSelection != flag) {
            this.autoTickUnitSelection = flag;
            if (notify) {
                notifyListeners(new AxisChangeEvent(this));
            }
        }
    }

    /**
     * Returns the source for obtaining standard tick units for the axis.
     * 
     * @return The source (possibly <code>null</code>).
     * 
     * @see #setStandardTickUnits(TickUnitSource)
     */
    public TickUnitSource getStandardTickUnits() {
        return this.standardTickUnits;
    }

    /**
     * Sets the source for obtaining standard tick units for the axis and sends
     * an {@link AxisChangeEvent} to all registered listeners. The axis will try
     * to select the smallest tick unit from the source that does not cause the
     * tick labels to overlap (see also the
     * {@link #setAutoTickUnitSelection(boolean)} method.
     * 
     * @param source
     *            the source for standard tick units (<code>null</code>
     *            permitted).
     * 
     * @see #getStandardTickUnits()
     */
    public void setStandardTickUnits(TickUnitSource source) {
        this.standardTickUnits = source;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the number of minor tick marks to display.
     * 
     * @return The number of minor tick marks to display.
     * 
     * @see #setMinorTickCount(int)
     * 
     * @since JFreeChart 1.0.12
     */
    public int getMinorTickCount() {
        return this.minorTickCount;
    }

    /**
     * Sets the number of minor tick marks to display, and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param count
     *            the count.
     * 
     * @see #getMinorTickCount()
     * 
     * @since JFreeChart 1.0.12
     */
    public void setMinorTickCount(int count) {
        this.minorTickCount = count;
        notifyListeners(new AxisChangeEvent(this));
    }

    public boolean isLimitAble() {
        return limitAble;
    }

    public void setLimitAble(boolean limitAble) {
        this.limitAble = limitAble;
    }

    public void setLimitRange(Range range) {
        this.limitRange = range;
    }

    public void setLimitRange(double lower, double upper) {
        this.limitRange = new Range(lower, upper);
    }
    
    public Range getLimitRange() {
        return limitRange;
    }
    
    /**
     * Converts a data value to a coordinate in Java2D space, assuming that the
     * axis runs along one edge of the specified dataArea.
     * <p>
     * Note that it is possible for the coordinate to fall outside the area.
     * 
     * @param value
     *            the data value.
     * @param area
     *            the area for plotting the data.
     * @param edge
     *            the edge along which the axis lies.
     * 
     * @return The Java2D coordinate.
     * 
     * @see #java2DToValue(double, RectShape, RectangleEdge)
     */
    public abstract double valueToJava2D(double value, RectShape area,
            RectangleEdge edge);

    /**
     * Converts a length in data coordinates into the corresponding length in
     * Java2D coordinates.
     * 
     * @param length
     *            the length.
     * @param area
     *            the plot area.
     * @param edge
     *            the edge along which the axis lies.
     * 
     * @return The length in Java2D coordinates.
     */
    public double lengthToJava2D(double length, RectShape area,
            RectangleEdge edge) {
        double zero = valueToJava2D(0.0, area, edge);
        double l = valueToJava2D(length, area, edge);
        return Math.abs(l - zero);
    }

    /**
     * Converts a coordinate in Java2D space to the corresponding data value,
     * assuming that the axis runs along one edge of the specified dataArea.
     * 
     * @param java2DValue
     *            the coordinate in Java2D space.
     * @param area
     *            the area in which the data is plotted.
     * @param edge
     *            the edge along which the axis lies.
     * 
     * @return The data value.
     * 
     * @see #valueToJava2D(double, RectShape, RectangleEdge)
     */
    public abstract double java2DToValue(double java2DValue, RectShape area,
            RectangleEdge edge);

    /**
     * Automatically sets the axis range to fit the range of values in the
     * dataset. Sometimes this can depend on the renderer used as well (for
     * example, the renderer may "stack" values, requiring an axis range greater
     * than otherwise necessary).
     */
    protected abstract void autoAdjustRange();

    /**
     * Centers the axis range about the specified value and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param value
     *            the center value.
     */
    public void centerRange(double value) {

        double central = this.mRange.getCentralValue();
        Range adjusted = new Range(
                this.mRange.getLowerBound() + value - central, this.mRange
                        .getUpperBound()
                        + value - central);
        setRange(adjusted);

    }

    /**
     * Increases or decreases the axis range by the specified percentage about
     * the central value and sends an {@link AxisChangeEvent} to all registered
     * listeners.
     * <P>
     * To double the length of the axis range, use 200% (2.0). To halve the
     * length of the axis range, use 50% (0.5).
     * 
     * @param percent
     *            the resize factor.
     * 
     * @see #resizeRange(double, double)
     */
    public void resizeRange(double percent) {
        resizeRange(percent, this.mRange.getCentralValue());
    }

    /**
     * Increases or decreases the axis range by the specified percentage about
     * the specified anchor value and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * <P>
     * To double the length of the axis range, use 200% (2.0). To halve the
     * length of the axis range, use 50% (0.5).
     * 
     * @param percent
     *            the resize factor.
     * @param anchorValue
     *            the new central value after the resize.
     * 
     * @see #resizeRange(double)
     */
    public void resizeRange(double percent, double anchorValue) {
        if (percent > 0.0) {
            double halfLength = this.mRange.getLength() * percent / 2;
            double lower, upper;
            lower = anchorValue - halfLength;
            upper = anchorValue + halfLength;
            
            if (isLimitAble()
                    && limitRange != null) {
                double tmp = limitRange.getUpperBound();
                if (upper > tmp) {
                    upper = tmp;
                }
                
                tmp = limitRange.getLowerBound();
                if (lower < tmp) {
                    lower = tmp;
                }
            }
            
            Range adjusted = new Range(lower, upper);
            setRange(adjusted);
        } else {
            setAutoRange(true);
        }
    }

    /**
     * Increases or decreases the axis range by the specified percentage about
     * the specified anchor value and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * <P>
     * To double the length of the axis range, use 200% (2.0). To halve the
     * length of the axis range, use 50% (0.5).
     * 
     * @param percent
     *            the resize factor.
     * @param anchorValue
     *            the new central value after the resize.
     * 
     * @see #resizeRange(double)
     * 
     * @since JFreeChart 1.0.13
     */
    public void resizeRange2(double percent, double anchorValue) {
        if (percent > 0.0) {
            double left = anchorValue - getLowerBound();
            double right = getUpperBound() - anchorValue;
            Range adjusted = new Range(anchorValue - left * percent,
                    anchorValue + right * percent);
            setRange(adjusted);
        } else {
            setAutoRange(true);
        }
    }

    /**
     * Zooms in on the current range.
     * 
     * @param lowerPercent
     *            the new lower bound.
     * @param upperPercent
     *            the new upper bound.
     */
    public void zoomRange(double lowerPercent, double upperPercent) {
        double start = this.mRange.getLowerBound();
        double length = this.mRange.getLength();
        Range adjusted = null;
        if (isInverted()) {
            adjusted = new Range(start + (length * (1 - upperPercent)), start
                    + (length * (1 - lowerPercent)));
        } else {
            adjusted = new Range(start + length * lowerPercent, start + length
                    * upperPercent);
        }
        setRange(adjusted);
    }

    /**
     * Slides the axis range by the specified percentage.
     * 
     * @param percent
     *            the percentage.
     * 
     * @since JFreeChart 1.0.13
     */
    public void pan(double percent) {
        Range range = getRange();
        double length = range.getLength();
        double adj = length * percent;
        double lower = range.getLowerBound() + adj;
        double upper = range.getUpperBound() + adj;
        setRange(lower, upper);
    }

    /**
     * Returns the auto tick index.
     * 
     * @return The auto tick index.
     * 
     * @see #setAutoTickIndex(int)
     */
    protected int getAutoTickIndex() {
        return this.autoTickIndex;
    }

    /**
     * Sets the auto tick index.
     * 
     * @param index
     *            the new value.
     * 
     * @see #getAutoTickIndex()
     */
    protected void setAutoTickIndex(int index) {
        this.autoTickIndex = index;
    }

    /**
     * moveRange
     * @param movePercent
     */
    public void moveRange(double movePercent) {

        double start = this.mRange.getLowerBound();
        double end = this.mRange.getUpperBound();
        double length = this.mRange.getLength();
                
        Range adjusted = null;
        double moveBound, lower, upper;
        if (isInverted()) {
            moveBound = movePercent * length;
            lower = start - moveBound;
            upper = end - moveBound;
        } else {
            moveBound = movePercent * length;
            lower = start + moveBound;
            upper = end + moveBound;
        }

        if(isLimitAble()
                && limitRange != null) {
            double tmp = limitRange.getLowerBound();
            if(lower < tmp) {
                lower = tmp;
                upper = tmp + (end - start);
            }
            
            tmp = limitRange.getUpperBound();
            if(upper > tmp) {
                upper = tmp;
                lower = tmp - (end - start);
            }
        }

        adjusted = new Range(lower, upper);
        setRange(adjusted);
    }
    
    /**
     * Returns a clone of the object.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if some component of the axis does
     *         not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
        ValueAxis clone = (ValueAxis) super.clone();
        return clone;
    }
}
