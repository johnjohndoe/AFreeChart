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
 * ----------------------
 * StandardDialScale.java
 * ----------------------
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
 * (C) Copyright 2006-2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 17-Nov-2006 : Added flags for tick label visibility (DG);
 * 24-Oct-2007 : Added tick label formatter (DG);
 * 19-Nov-2007 : Added some missing accessor methods (DG);
 * 27-Feb-2009 : Fixed bug 2617557: tickLabelPaint ignored (DG);
 *
 */

package org.afree.chart.plot.dial;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.afree.graphics.geom.ArcShape;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Paint.Align;

/**
 * A scale for a {@link DialPlot}.
 *
 * @since JFreeChart 1.0.7
 */
public class StandardDialScale extends AbstractDialLayer implements DialScale,
        Cloneable, /*PublicCloneable,*/ Serializable {

    /** For serialization. */
    static final long serialVersionUID = 3715644629665918516L;

    /** The minimum data value for the scale. */
    private double lowerBound;

    /** The maximum data value for the scale. */
    private double upperBound;

    /**
     * The start angle for the scale display, in degrees (using the same
     * encoding as ArcShape).
     */
    private double startAngle;

    /** The extent of the scale display. */
    private double extent;

    /**
     * The factor (in the range 0.0 to 1.0) that determines the outside limit
     * of the tick marks.
     */
    private double tickRadius;

    /**
     * The increment (in data units) between major tick marks.
     */
    private double majorTickIncrement;

    /**
     * The factor that is subtracted from the tickRadius to determine the
     * inner point of the major ticks.
     */
    private double majorTickLength;

    /**
     * The paint to use for major tick marks.  This field is transient because
     * it requires special handling for serialization.
     */
    private transient Paint majorTickPaint;

    /**
     * The stroke to use for major tick marks.  This field is transient because
     * it requires special handling for serialization.
     */
    private transient Float majorTickStroke;

    /**
     * The number of minor ticks between each major tick.
     */
    private int minorTickCount;

    /**
     * The factor that is subtracted from the tickRadius to determine the
     * inner point of the minor ticks.
     */
    private double minorTickLength;

    /**
     * The paint to use for minor tick marks.  This field is transient because
     * it requires special handling for serialization.
     */
    private transient Paint minorTickPaint;

    /**
     * The stroke to use for minor tick marks.  This field is transient because
     * it requires special handling for serialization.
     */
    private transient Float minorTickStroke;

    /**
     * The tick label offset.
     */
    private double tickLabelOffset;

    /**
     * The tick label font.
     */
    private Font tickLabelFont;

    /**
     * A flag that controls whether or not the tick labels are
     * displayed.
     */
    private boolean tickLabelsVisible;

    /**
     * The number formatter for the tick labels.
     */
    private NumberFormat tickLabelFormatter;

    /**
     * A flag that controls whether or not the first tick label is
     * displayed.
     */
    private boolean firstTickLabelVisible;

    /**
     * The tick label paint.  This field is transient because it requires
     * special handling for serialization.
     */
    private transient Paint tickLabelPaint;

    /**
     * Creates a new instance of DialScale.
     */
    public StandardDialScale() {
        this(0.0, 100.0, 175, -170, 10.0, 4);
    }

    /**
     * Creates a new instance.
     *
     * @param lowerBound  the lower bound of the scale.
     * @param upperBound  the upper bound of the scale.
     * @param startAngle  the start angle (in degrees, using the same
     *     orientation as Java's <code>ArcShape</code> class).
     * @param extent  the extent (in degrees, counter-clockwise).
     * @param majorTickIncrement  the interval between major tick marks
     * @param minorTickCount  the number of minor ticks between major tick
     *          marks.
     */
    public StandardDialScale(double lowerBound, double upperBound,
            double startAngle, double extent, double majorTickIncrement,
            int minorTickCount) {
        this.startAngle = startAngle;
        this.extent = extent;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.tickRadius = 0.70;
        this.tickLabelsVisible = true;
        this.tickLabelFormatter = new DecimalFormat("0.0");
        this.firstTickLabelVisible = true;
        //this.tickLabelFont = new Font("Dialog", Font.BOLD, 16);
        this.tickLabelFont = new Font();
        //this.tickLabelPaint = Color.blue;
        this.tickLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.tickLabelPaint.setColor(Color.BLUE);
        this.tickLabelOffset = 0.10;
        this.majorTickIncrement = majorTickIncrement;
        this.majorTickLength = 0.04;
        //this.majorTickPaint = Color.black;
        this.majorTickPaint =  new Paint(Paint.ANTI_ALIAS_FLAG);
        this.majorTickPaint.setColor(Color.BLACK);
        this.majorTickStroke = 3.0f;
        this.minorTickCount = minorTickCount;
        this.minorTickLength = 0.02;
        //this.minorTickPaint = Color.black;
        this.minorTickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.minorTickPaint.setColor(Color.BLACK);
        this.minorTickStroke = 1.0f;
    }

    /**
     * Returns the lower bound for the scale.
     *
     * @return The lower bound for the scale.
     *
     * @see #setLowerBound(double)
     *
     * @since JFreeChart 1.0.8
     */
    public double getLowerBound() {
        return this.lowerBound;
    }

    /**
     * Sets the lower bound for the scale and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param lower  the lower bound.
     *
     * @see #getLowerBound()
     *
     * @since JFreeChart 1.0.8
     */
    public void setLowerBound(double lower) {
        this.lowerBound = lower;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the upper bound for the scale.
     *
     * @return The upper bound for the scale.
     *
     * @see #setUpperBound(double)
     *
     * @since JFreeChart 1.0.8
     */
    public double getUpperBound() {
        return this.upperBound;
    }

    /**
     * Sets the upper bound for the scale and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param upper  the upper bound.
     *
     * @see #getUpperBound()
     *
     * @since JFreeChart 1.0.8
     */
    public void setUpperBound(double upper) {
        this.upperBound = upper;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the start angle for the scale (in degrees using the same
     * orientation as Java's <code>ArcShape</code> class).
     *
     * @return The start angle.
     *
     * @see #setStartAngle(double)
     */
    public double getStartAngle() {
        return this.startAngle;
    }

    /**
     * Sets the start angle for the scale and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param angle  the angle (in degrees).
     *
     * @see #getStartAngle()
     */
    public void setStartAngle(double angle) {
        this.startAngle = angle;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the extent.
     *
     * @return The extent.
     *
     * @see #setExtent(double)
     */
    public double getExtent() {
        return this.extent;
    }

    /**
     * Sets the extent and sends a {@link DialLayerChangeEvent} to all
     * registered listeners.
     *
     * @param extent  the extent.
     *
     * @see #getExtent()
     */
    public void setExtent(double extent) {
        this.extent = extent;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the radius (as a percentage of the maximum space available) of
     * the outer limit of the tick marks.
     *
     * @return The tick radius.
     *
     * @see #setTickRadius(double)
     */
    public double getTickRadius() {
        return this.tickRadius;
    }

    /**
     * Sets the tick radius and sends a {@link DialLayerChangeEvent} to all
     * registered listeners.
     *
     * @param radius  the radius.
     *
     * @see #getTickRadius()
     */
    public void setTickRadius(double radius) {
        if (radius <= 0.0) {
            throw new IllegalArgumentException(
                    "The 'radius' must be positive.");
        }
        this.tickRadius = radius;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the increment (in data units) between major tick labels.
     *
     * @return The increment between major tick labels.
     *
     * @see #setMajorTickIncrement(double)
     */
    public double getMajorTickIncrement() {
        return this.majorTickIncrement;
    }

    /**
     * Sets the increment (in data units) between major tick labels and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param increment  the increment.
     *
     * @see #getMajorTickIncrement()
     */
    public void setMajorTickIncrement(double increment) {
        if (increment <= 0.0) {
            throw new IllegalArgumentException(
                    "The 'increment' must be positive.");
        }
        this.majorTickIncrement = increment;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the length factor for the major tick marks.  The value is
     * subtracted from the tick radius to determine the inner starting point
     * for the tick marks.
     *
     * @return The length factor.
     *
     * @see #setMajorTickLength(double)
     */
    public double getMajorTickLength() {
        return this.majorTickLength;
    }

    /**
     * Sets the length factor for the major tick marks and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param length  the length.
     *
     * @see #getMajorTickLength()
     */
    public void setMajorTickLength(double length) {
        if (length < 0.0) {
            throw new IllegalArgumentException("Negative 'length' argument.");
        }
        this.majorTickLength = length;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the major tick paint.
     *
     * @return The major tick paint (never <code>null</code>).
     *
     * @see #setMajorTickPaint(Paint)
     */
    public Paint getMajorTickPaint() {
        return this.majorTickPaint;
    }

    /**
     * Sets the major tick paint and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getMajorTickPaint()
     */
    public void setMajorTickPaint(Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.majorTickPaint = paint;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the stroke used to draw the major tick marks.
     *
     * @return The stroke (never <code>null</code>).
     *
     * @see #setMajorTickStroke(Float stroke)
     */
    public Float getMajorTickStroke() {
        return this.majorTickStroke;
    }

    /**
     * Sets the stroke used to draw the major tick marks and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     *
     * @see #getMajorTickStroke()
     */
    public void setMajorTickStroke(Float stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.majorTickStroke = stroke;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the number of minor tick marks between major tick marks.
     *
     * @return The number of minor tick marks between major tick marks.
     *
     * @see #setMinorTickCount(int)
     */
    public int getMinorTickCount() {
        return this.minorTickCount;
    }

    /**
     * Sets the number of minor tick marks between major tick marks and sends
     * a {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param count  the count.
     *
     * @see #getMinorTickCount()
     */
    public void setMinorTickCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(
                    "The 'count' cannot be negative.");
        }
        this.minorTickCount = count;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the length factor for the minor tick marks.  The value is
     * subtracted from the tick radius to determine the inner starting point
     * for the tick marks.
     *
     * @return The length factor.
     *
     * @see #setMinorTickLength(double)
     */
    public double getMinorTickLength() {
        return this.minorTickLength;
    }

    /**
     * Sets the length factor for the minor tick marks and sends
     * a {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param length  the length.
     *
     * @see #getMinorTickLength()
     */
    public void setMinorTickLength(double length) {
        if (length < 0.0) {
            throw new IllegalArgumentException("Negative 'length' argument.");
        }
        this.minorTickLength = length;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the paint used to draw the minor tick marks.
     *
     * @return The paint (never <code>null</code>).
     *
     * @see #setMinorTickPaint(Paint)
     */
    public Paint getMinorTickPaint() {
        return this.minorTickPaint;
    }

    /**
     * Sets the paint used to draw the minor tick marks and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getMinorTickPaint()
     */
    public void setMinorTickPaint(Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.minorTickPaint = paint;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the stroke used to draw the minor tick marks.
     *
     * @return The paint (never <code>null</code>).
     *
     * @see #setMinorTickStroke(Float stroke)
     *
     * @since JFreeChart 1.0.8
     */
    public Float getMinorTickStroke() {
        return this.minorTickStroke;
    }

    /**
     * Sets the stroke used to draw the minor tick marks and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     *
     * @see #getMinorTickStroke()
     *
     * @since JFreeChart 1.0.8
     */
    public void setMinorTickStroke(Float stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.minorTickStroke = stroke;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the tick label offset.
     *
     * @return The tick label offset.
     *
     * @see #setTickLabelOffset(double)
     */
    public double getTickLabelOffset() {
        return this.tickLabelOffset;
    }

    /**
     * Sets the tick label offset and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     *
     * @param offset  the offset.
     *
     * @see #getTickLabelOffset()
     */
    public void setTickLabelOffset(double offset) {
        this.tickLabelOffset = offset;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the font used to draw the tick labels.
     *
     * @return The font (never <code>null</code>).
     *
     * @see #setTickLabelFont(Font)
     */
    public Font getTickLabelFont() {
        return this.tickLabelFont;
    }

    /**
     * Sets the font used to display the tick labels and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param font  the font (<code>null</code> not permitted).
     *
     * @see #getTickLabelFont()
     */
    public void setTickLabelFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.tickLabelFont = font;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the paint used to draw the tick labels.
     *
     * @return The paint (<code>null</code> not permitted).
     *
     * @see #setTickLabelPaint(Paint)
     */
    public Paint getTickLabelPaint() {
        return this.tickLabelPaint;
    }

    /**
     * Sets the paint used to draw the tick labels and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setTickLabelPaint(Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.tickLabelPaint = paint;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns <code>true</code> if the tick labels should be displayed,
     * and <code>false</code> otherwise.
     *
     * @return A boolean.
     *
     * @see #setTickLabelsVisible(boolean)
     */
    public boolean getTickLabelsVisible() {
        return this.tickLabelsVisible;
    }

    /**
     * Sets the flag that controls whether or not the tick labels are
     * displayed, and sends a {@link DialLayerChangeEvent} to all registered
     * listeners.
     *
     * @param visible  the new flag value.
     *
     * @see #getTickLabelsVisible()
     */
    public void setTickLabelsVisible(boolean visible) {
        this.tickLabelsVisible = visible;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the number formatter used to convert the tick label values to
     * strings.
     *
     * @return The formatter (never <code>null</code>).
     *
     * @see #setTickLabelFormatter(NumberFormat)
     */
    public NumberFormat getTickLabelFormatter() {
        return this.tickLabelFormatter;
    }

    /**
     * Sets the number formatter used to convert the tick label values to
     * strings, and sends a {@link DialLayerChangeEvent} to all registered
     * listeners.
     *
     * @param formatter  the formatter (<code>null</code> not permitted).
     *
     * @see #getTickLabelFormatter()
     */
    public void setTickLabelFormatter(NumberFormat formatter) {
        if (formatter == null) {
            throw new IllegalArgumentException("Null 'formatter' argument.");
        }
        this.tickLabelFormatter = formatter;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns a flag that controls whether or not the first tick label is
     * visible.
     *
     * @return A boolean.
     *
     * @see #setFirstTickLabelVisible(boolean)
     */
    public boolean getFirstTickLabelVisible() {
        return this.firstTickLabelVisible;
    }

    /**
     * Sets a flag that controls whether or not the first tick label is
     * visible, and sends a {@link DialLayerChangeEvent} to all registered
     * listeners.
     *
     * @param visible  the new flag value.
     *
     * @see #getFirstTickLabelVisible()
     */
    public void setFirstTickLabelVisible(boolean visible) {
        this.firstTickLabelVisible = visible;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns <code>true</code> to indicate that this layer should be
     * clipped within the dial window.
     *
     * @return <code>true</code>.
     */
    public boolean isClippedToWindow() {
        return true;
    }

    /**
     * Draws the scale on the dial plot.
     *
     * @param canvas  the graphics target (<code>null</code> not permitted).
     * @param plot  the dial plot (<code>null</code> not permitted).
     * @param frame  the reference frame that is used to construct the
     *     geometry of the plot (<code>null</code> not permitted).
     * @param view  the visible part of the plot (<code>null</code> not
     *     permitted).
     */
    public void draw(Canvas canvas, DialPlot plot, RectShape frame,
            Shape view) {
        
        RectShape arcRect = DialPlot.RectShapeByRadius(frame,
                this.tickRadius, this.tickRadius);
        RectShape arcRectMajor = DialPlot.RectShapeByRadius(frame,
                this.tickRadius - this.majorTickLength,
                this.tickRadius - this.majorTickLength);
        RectShape arcRectMinor = arcRect;
        if (this.minorTickCount > 0 && this.minorTickLength > 0.0) {
            arcRectMinor = DialPlot.RectShapeByRadius(frame,
                    this.tickRadius - this.minorTickLength,
                    this.tickRadius - this.minorTickLength);
        }
        RectShape arcRectForLabels = DialPlot.RectShapeByRadius(frame,
                this.tickRadius - this.tickLabelOffset,
                this.tickRadius - this.tickLabelOffset);

        boolean firstLabel = true;

        ArcShape arc = new ArcShape();
        LineShape workingLine = new LineShape();
        for (double v = this.lowerBound; v <= this.upperBound;
                v += this.majorTickIncrement) {
            arc.setArc(arcRect, this.startAngle, -(this.startAngle - valueToAngle(v)), true);
            PointF pt0 = arc.getEndPoint();
            arc.setArc(arcRectMajor, this.startAngle, -(this.startAngle - valueToAngle(v)), true);
            PointF pt1 = arc.getEndPoint();
            majorTickPaint.setStrokeWidth(majorTickStroke);
            workingLine.setLine(pt0, pt1);
            workingLine.draw(canvas, majorTickPaint);
            arc.setArc(arcRectForLabels, this.startAngle, -(this.startAngle - valueToAngle(v)), true);
            PointF pt2 = arc.getEndPoint();

            if (this.tickLabelsVisible) {
                if (!firstLabel || this.firstTickLabelVisible) {
                    tickLabelPaint.setTypeface(tickLabelFont.getTypeFace());
                    tickLabelPaint.setTextSize(tickLabelFont.getSize());
                    tickLabelPaint.setTextAlign(Align.CENTER);
                    canvas.drawText(this.tickLabelFormatter.format(v), pt2.x, pt2.y, tickLabelPaint);
//                    TextUtilities.drawAlignedString(
//                            this.tickLabelFormatter.format(v), canvas,
//                            (float) pt2.x, (float) pt2.y,
//                            TextAnchor.CENTER, tickLabelPaint);
                }
            }
            firstLabel = false;

            // now do the minor tick marks
            if (this.minorTickCount > 0 && this.minorTickLength > 0.0) {
                double minorTickIncrement = this.majorTickIncrement
                        / (this.minorTickCount + 1);
                for (int i = 0; i < this.minorTickCount; i++) {
                    double vv = v + ((i + 1) * minorTickIncrement);
                    if (vv >= this.upperBound) {
                        break;
                    }
                    double angle = valueToAngle(vv);

                    arc.setArc(arcRect, this.startAngle, -(this.startAngle - angle), true);
                    pt0 = arc.getEndPoint();
                    arc.setArc(arcRectMinor, this.startAngle, -(this.startAngle - angle), true);
                    PointF pt3 = arc.getEndPoint();
                    minorTickPaint.setStrokeWidth(minorTickStroke);
                    workingLine.setLine(pt0, pt3);
                    workingLine.draw(canvas, minorTickPaint);
                }
            }
        }
    }

    /**
     * Converts a data value to an angle against this scale.
     *
     * @param value  the data value.
     *
     * @return The angle (in degrees, using the same specification as Java's
     *     ArcShape class).
     *
     * @see #angleToValue(double)
     */
    public double valueToAngle(double value) {
        double range = this.upperBound - this.lowerBound;
        double unit = this.extent / range;
        return this.startAngle + unit * (value - this.lowerBound);
    }

    /**
     * Converts the given angle to a data value, based on this scale.
     *
     * @param angle  the angle.
     *
     * @return The data value.
     *
     * @see #valueToAngle(double)
     */
    public double angleToValue(double angle) {
        return Double.NaN;  // FIXME
    }

    /**
     * Tests this <code>StandardDialScale</code> for equality with an arbitrary
     * object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardDialScale)) {
            return false;
        }
        StandardDialScale that = (StandardDialScale) obj;
        if (this.lowerBound != that.lowerBound) {
            return false;
        }
        if (this.upperBound != that.upperBound) {
            return false;
        }
        if (this.startAngle != that.startAngle) {
            return false;
        }
        if (this.extent != that.extent) {
            return false;
        }
        if (this.tickRadius != that.tickRadius) {
            return false;
        }
        if (this.majorTickIncrement != that.majorTickIncrement) {
            return false;
        }
        if (this.majorTickLength != that.majorTickLength) {
            return false;
        }
//        if (!PaintUtilities.equal(this.majorTickPaint, that.majorTickPaint)) {
//            return false;
//        }
        if (!this.majorTickStroke.equals(that.majorTickStroke)) {
            return false;
        }
        if (this.minorTickCount != that.minorTickCount) {
            return false;
        }
        if (this.minorTickLength != that.minorTickLength) {
            return false;
        }
//        if (!PaintUtilities.equal(this.minorTickPaint, that.minorTickPaint)) {
//            return false;
//        }
        if (!this.minorTickStroke.equals(that.minorTickStroke)) {
            return false;
        }
        if (this.tickLabelsVisible != that.tickLabelsVisible) {
            return false;
        }
        if (this.tickLabelOffset != that.tickLabelOffset) {
            return false;
        }
        if (!this.tickLabelFont.equals(that.tickLabelFont)) {
            return false;
        }
//        if (!PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint)) {
//            return false;
//        }
        return super.equals(obj);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = 193;
        // lowerBound
        long temp = Double.doubleToLongBits(this.lowerBound);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        // upperBound
        temp = Double.doubleToLongBits(this.upperBound);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        // startAngle
        temp = Double.doubleToLongBits(this.startAngle);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        // extent
        temp = Double.doubleToLongBits(this.extent);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        // tickRadius
        temp = Double.doubleToLongBits(this.tickRadius);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        // majorTickIncrement
        // majorTickLength
        // majorTickPaint
        // majorTickStroke
        // minorTickCount
        // minorTickLength
        // minorTickPaint
        // minorTickStroke
        // tickLabelOffset
        // tickLabelFont
        // tickLabelsVisible
        // tickLabelFormatter
        // firstTickLabelsVisible
        return result;
    }

    /**
     * Returns a clone of this instance.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if this instance is not cloneable.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

//    /**
//     * Provides serialization support.
//     *
//     * @param stream  the output stream.
//     *
//     * @throws IOException  if there is an I/O error.
//     */
//    private void writeObject(ObjectOutputStream stream) throws IOException {
//        stream.defaultWriteObject();
//        SerialUtilities.writePaint(this.majorTickPaint, stream);
//        SerialUtilities.writeStroke(this.majorTickStroke, stream);
//        SerialUtilities.writePaint(this.minorTickPaint, stream);
//        SerialUtilities.writeStroke(this.minorTickStroke, stream);
//        SerialUtilities.writePaint(this.tickLabelPaint, stream);
//    }
//
//    /**
//     * Provides serialization support.
//     *
//     * @param stream  the input stream.
//     *
//     * @throws IOException  if there is an I/O error.
//     * @throws ClassNotFoundException  if there is a classpath problem.
//     */
//    private void readObject(ObjectInputStream stream)
//            throws IOException, ClassNotFoundException {
//        stream.defaultReadObject();
//        this.majorTickPaint = SerialUtilities.readPaint(stream);
//        this.majorTickStroke = SerialUtilities.readStroke(stream);
//        this.minorTickPaint = SerialUtilities.readPaint(stream);
//        this.minorTickStroke = SerialUtilities.readStroke(stream);
//        this.tickLabelPaint = SerialUtilities.readPaint(stream);
//    }

}
