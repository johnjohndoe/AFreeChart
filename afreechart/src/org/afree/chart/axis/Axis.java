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
 * ---------
 * Axis.java
 * ---------
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
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Bill Kelemen;
 *                   Nicolas Brodu;
 *                   Peter Kolb (patches 1934255 and 2603321);
 *                   Andrew Mickish (patch 1870189);
 *
 * Changes
 * -------
 * 21-Aug-2001 : Added standard header, fixed DOS encoding problem (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 07-Nov-2001 : Allow null axis labels (DG);
 *             : Added default font values (DG);
 * 13-Nov-2001 : Modified the setPlot() method to check compatibility between
 *               the axis and the plot (DG);
 * 30-Nov-2001 : Changed default font from "Arial" --> "SansSerif" (DG);
 * 06-Dec-2001 : Allow null in setPlot() method (BK);
 * 06-Mar-2002 : Added AxisConstants interface (DG);
 * 23-Apr-2002 : Added a visible property.  Moved drawVerticalString to
 *               RefineryUtilities.  Added fixedDimension property for use in
 *               combined plots (DG);
 * 25-Jun-2002 : Removed unnecessary imports (DG);
 * 05-Sep-2002 : Added attribute for tick mark paint (DG);
 * 18-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 07-Nov-2002 : Added attributes to control the inside and outside length of
 *               the tick marks (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 18-Nov-2002 : Added axis location to refreshTicks() parameters (DG);
 * 15-Jan-2003 : Removed monolithic constructor (DG);
 * 17-Jan-2003 : Moved plot classes to separate package (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 03-Jul-2003 : Modified reserveSpace method (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 11-Sep-2003 : Took care of listeners while cloning (NB);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 06-Nov-2003 : Modified refreshTicks() signature (DG);
 * 06-Jan-2004 : Added axis line attributes (DG);
 * 16-Mar-2004 : Added plot state to draw() method (DG);
 * 07-Apr-2004 : Modified text bounds calculation (DG);
 * 18-May-2004 : Eliminated AxisConstants.java (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities -->
 *               TextUtilities (DG);
 * 04-Oct-2004 : Modified getLabelEnclosure() method to treat an empty String
 *               the same way as a null string - see bug 1026521 (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 01-Jun-2005 : Added hasListener() method for unit testing (DG);
 * 08-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 22-Aug-2006 : API doc updates (DG);
 * 06-Jun-2008 : Added setTickLabelInsets(RectangleInsets, boolean) (DG);
 * 25-Sep-2008 : Added minor tick support, see patch 1934255 by Peter Kolb (DG);
 * 26-Sep-2008 : Added fireChangeEvent() method (DG);
 * 19-Mar-2009 : Added entity support - see patch 2603321 by Peter Kolb (DG);
 *
 */

package org.afree.chart.axis;

import java.io.Serializable;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.ui.TextAnchor;
import org.afree.chart.entity.AxisEntity;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.event.AxisChangeEvent;
import org.afree.chart.event.AxisChangeListener;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Typeface;

/**
 * The base class for all axes in AFreeChart. Subclasses are divided into those
 * that display values ({@link ValueAxis}) and those that display categories (
 * {@link CategoryAxis}).
 */
public abstract class Axis implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 7719289504573298271L;

    /** The default axis visibility. */
    public static final boolean DEFAULT_AXIS_VISIBLE = true;

    /** The default axis label font. */
    public static final Font DEFAULT_AXIS_LABEL_FONT = new Font("SansSerif",
            Typeface.BOLD, 12);

    /** The default axis label paint. */
    public static final PaintType DEFAULT_AXIS_LABEL_PAINT_TYPE = new SolidColor(Color.BLACK);

    /** The default axis label insets. */
    public static final RectangleInsets DEFAULT_AXIS_LABEL_INSETS = new RectangleInsets(
            3.0, 3.0, 3.0, 3.0);

    /** The default axis line paint. */
    public static final PaintType DEFAULT_AXIS_LINE_PAINT_TYPE = new SolidColor(Color.DKGRAY);

    /** The default axis line stroke. */
    public static final float DEFAULT_AXIS_LINE_STROKE = 1;
    
    /** The default axis line effect. */
    public static final PathEffect DEFAULT_AXIS_LINE_EFFECT = null;

    /** The default tick labels visibility. */
    public static final boolean DEFAULT_TICK_LABELS_VISIBLE = true;

    /** The default tick label font. */
    public static final Font DEFAULT_TICK_LABEL_FONT = new Font("SansSerif",
            Typeface.BOLD, 10);

    /** The default tick label paint. */
    public static final PaintType DEFAULT_TICK_LABEL_PAINT_TYPE = new SolidColor(Color.DKGRAY);
    
    /** The default tick label insets. */
    public static final RectangleInsets DEFAULT_TICK_LABEL_INSETS = new RectangleInsets(
            2.0, 4.0, 2.0, 4.0);

    /** The default tick marks visible. */
    public static final boolean DEFAULT_TICK_MARKS_VISIBLE = true;

    /** The default tick stroke. */
    public static final int DEFAULT_TICK_MARK_STROKE = 1;

    /** The default tick paint. */
    public static final PaintType DEFAULT_TICK_MARK_PAINT = new SolidColor(Color.DKGRAY);
    
    /** The default tick effect. */
    public static final PathEffect DEFAULT_TICK_MARK_EFFECT = null;

    /** The default tick mark inside length. */
    public static final float DEFAULT_TICK_MARK_INSIDE_LENGTH = 0.0f;

    /** The default tick mark outside length. */
    public static final float DEFAULT_TICK_MARK_OUTSIDE_LENGTH = 2.0f;

    /** A flag indicating whether or not the axis is visible. */
    private boolean visible;

    /** The label for the axis. */
    private String label;

    /** The font for displaying the axis label. */
    private Font labelFont;

    /** The paint for drawing the axis label. */
    private transient PaintType labelPaintType;

    /** The insets for the axis label. */
    private RectangleInsets labelInsets;

    /** The label angle. */
    private double labelAngle;

    /** A flag that controls whether or not the axis line is visible. */
    private boolean axisLineVisible;

    /** The stroke used for the axis line. */
    private transient float axisLineStroke;
    
    /** The effect used for the axis line. */
    private transient PathEffect axisLineEffect;

    /** The paint used for the axis line. */
    private transient PaintType axisLinePaintType;

    /**
     * A flag that indicates whether or not tick labels are visible for the
     * axis.
     */
    private boolean tickLabelsVisible;

    /** The font used to display the tick labels. */
    private Font tickLabelFont;

    /** The color used to display the tick labels. */
    private transient PaintType tickLabelPaintType;

    /** The blank space around each tick label. */
    private RectangleInsets tickLabelInsets;

    /**
     * A flag that indicates whether or not major tick marks are visible for the
     * axis.
     */
    private boolean tickMarksVisible;

    /**
     * The length of the major tick mark inside the data area (zero permitted).
     */
    private float tickMarkInsideLength;

    /**
     * The length of the major tick mark outside the data area (zero permitted).
     */
    private float tickMarkOutsideLength;

    /**
     * A flag that indicates whether or not minor tick marks are visible for the
     * axis.
     * 
     * @since JFreeChart 1.0.12
     */
    private boolean minorTickMarksVisible;

    /**
     * The length of the minor tick mark inside the data area (zero permitted).
     * 
     * @since JFreeChart 1.0.12
     */
    private float minorTickMarkInsideLength;

    /**
     * The length of the minor tick mark outside the data area (zero permitted).
     * 
     * @since JFreeChart 1.0.12
     */
    private float minorTickMarkOutsideLength;

    /** The stroke used to draw tick marks. */
    private transient int tickMarkStroke;

    /** The paint used to draw tick marks. */
    private transient PaintType tickMarkPaintType;
    
    /** The effect used to draw tick marks. */
    private transient PathEffect tickMarkEffect;

    /** The fixed (horizontal or vertical) dimension for the axis. */
    private double fixedDimension;

    /**
     * A reference back to the plot that the axis is assigned to (can be
     * <code>null</code>).
     */
    private transient Plot plot;

    /** Storage for registered listeners. */
    private transient List<AxisChangeListener> listenerList;

    /**
     * Constructs an axis, using default values where necessary.
     * 
     * @param label
     *            the axis label (<code>null</code> permitted).
     */
    protected Axis(String label) {

        this.label = label;
        this.visible = DEFAULT_AXIS_VISIBLE;
        this.labelFont = DEFAULT_AXIS_LABEL_FONT;
        this.labelPaintType = DEFAULT_AXIS_LABEL_PAINT_TYPE;
        this.labelInsets = DEFAULT_AXIS_LABEL_INSETS;
        this.labelAngle = 0.0;

        this.axisLineVisible = true;
        this.axisLinePaintType = DEFAULT_AXIS_LINE_PAINT_TYPE;
        this.axisLineStroke = DEFAULT_AXIS_LINE_STROKE;
        this.axisLineEffect = DEFAULT_AXIS_LINE_EFFECT;

        this.tickLabelsVisible = DEFAULT_TICK_LABELS_VISIBLE;
        this.tickLabelFont = DEFAULT_TICK_LABEL_FONT;
        this.tickLabelPaintType = DEFAULT_TICK_LABEL_PAINT_TYPE;
        this.tickLabelInsets = DEFAULT_TICK_LABEL_INSETS;

        this.tickMarksVisible = DEFAULT_TICK_MARKS_VISIBLE;
        this.tickMarkStroke = DEFAULT_TICK_MARK_STROKE;
        this.tickMarkPaintType = DEFAULT_TICK_MARK_PAINT;
        this.tickMarkEffect = DEFAULT_TICK_MARK_EFFECT;
        this.tickMarkInsideLength = DEFAULT_TICK_MARK_INSIDE_LENGTH;
        this.tickMarkOutsideLength = DEFAULT_TICK_MARK_OUTSIDE_LENGTH;

        this.minorTickMarksVisible = false;
        this.minorTickMarkInsideLength = 0.0f;
        this.minorTickMarkOutsideLength = 2.0f;

        this.plot = null;

        this.listenerList = new CopyOnWriteArrayList<AxisChangeListener>();

    }

    /**
     * Returns <code>true</code> if the axis is visible, and <code>false</code>
     * otherwise.
     * 
     * @return A boolean.
     * 
     * @see #setVisible(boolean)
     */
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * Sets a flag that controls whether or not the axis is visible and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param flag
     *            the flag.
     * 
     * @see #isVisible()
     */
    public void setVisible(boolean flag) {
        if (flag != this.visible) {
            this.visible = flag;
            fireChangeEvent();
        }
    }

    /**
     * Returns the label for the axis.
     * 
     * @return The label for the axis (<code>null</code> possible).
     * 
     * @see #getLabelFont()
     * @see #getLabelPaintType()
     * @see #setLabel(String)
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the label for the axis and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * 
     * @param label
     *            the new label (<code>null</code> permitted).
     * 
     * @see #getLabel()
     * @see #setLabelFont(Font)
     * @see #setLabelPaintType(PaintType paintType)
     */
    public void setLabel(String label) {

        String existing = this.label;
        if (existing != null) {
            if (!existing.equals(label)) {
                this.label = label;
                fireChangeEvent();
            }
        } else {
            if (label != null) {
                this.label = label;
                fireChangeEvent();
            }
        }

    }

    /**
     * Returns the font for the axis label.
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setLabelFont(Font)
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Sets the font for the axis label and sends an {@link AxisChangeEvent} to
     * all registered listeners.
     * 
     * @param font
     *            the font (<code>null</code> not permitted).
     * 
     * @see #getLabelFont()
     */
    public void setLabelFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        if (!this.labelFont.equals(font)) {
            this.labelFont = font;
            fireChangeEvent();
        }
    }

    /**
     * Returns the color/shade used to draw the axis label.
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setLabelPaintType(PaintType paintType)
     */
    public PaintType getLabelPaintType() {
        return this.labelPaintType;
    }

    /**
     * Sets the paint used to draw the axis label and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getLabelPaintType()
     */
    public void setLabelPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.labelPaintType = paintType;
        fireChangeEvent();
    }

    /**
     * Returns the insets for the label (that is, the amount of blank space that
     * should be left around the label).
     * 
     * @return The label insets (never <code>null</code>).
     * 
     * @see #setLabelInsets(RectangleInsets)
     */
    public RectangleInsets getLabelInsets() {
        return this.labelInsets;
    }

    /**
     * Sets the insets for the axis label, and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     * 
     * @param insets
     *            the insets (<code>null</code> not permitted).
     * 
     * @see #getLabelInsets()
     */
    public void setLabelInsets(RectangleInsets insets) {
        setLabelInsets(insets, true);
    }

    /**
     * Sets the insets for the axis label, and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     * 
     * @param insets
     *            the insets (<code>null</code> not permitted).
     * @param notify
     *            notify listeners?
     * 
     * @since JFreeChart 1.0.10
     */
    public void setLabelInsets(RectangleInsets insets, boolean notify) {
        if (insets == null) {
            throw new IllegalArgumentException("Null 'insets' argument.");
        }
        if (!insets.equals(this.labelInsets)) {
            this.labelInsets = insets;
            if (notify) {
                fireChangeEvent();
            }
        }
    }

    /**
     * Returns the angle of the axis label.
     * 
     * @return The angle (in radians).
     * 
     * @see #setLabelAngle(double)
     */
    public double getLabelAngle() {
        return this.labelAngle;
    }

    /**
     * Sets the angle for the label and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * 
     * @param angle
     *            the angle (in radians).
     * 
     * @see #getLabelAngle()
     */
    public void setLabelAngle(double angle) {
        this.labelAngle = angle;
        fireChangeEvent();
    }

    /**
     * A flag that controls whether or not the axis line is drawn.
     * 
     * @return A boolean.
     * 
     * @see #getAxisLinePaintType()
     * @see #getAxisLineStroke()
     * @see #setAxisLineVisible(boolean)
     */
    public boolean isAxisLineVisible() {
        return this.axisLineVisible;
    }

    /**
     * Sets a flag that controls whether or not the axis line is visible and
     * sends an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #isAxisLineVisible()
     * @see #setAxisLinePaintType(PaintType paintType)
     * @see #setAxisLineStroke(float stroke)
     */
    public void setAxisLineVisible(boolean visible) {
        this.axisLineVisible = visible;
        fireChangeEvent();
    }

    /**
     * Returns the paint used to draw the axis line.
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setAxisLinePaintType(PaintType paintType)
     */
    public PaintType getAxisLinePaintType() {
        return this.axisLinePaintType;
    }

    /**
     * Sets the paint used to draw the axis line and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getAxisLinePaintType()
     */
    public void setAxisLinePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.axisLinePaintType = paintType;
        fireChangeEvent();
    }

    /**
     * Returns the stroke used to draw the axis line.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setAxisLineStroke(float stroke)
     */
    public float getAxisLineStroke() {
        return this.axisLineStroke;
    }

    /**
     * Sets the stroke used to draw the axis line and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * 
     * @see #getAxisLineStroke()
     */
    public void setAxisLineStroke(float stroke) {

        this.axisLineStroke = stroke;
        fireChangeEvent();
    }

    /**
     * Returns the effect used to draw the axis line.
     * 
     * @return The effect (never <code>null</code>).
     * 
     * @see #setAxisLineEffect(PathEffect pathEffect)
     */
    public PathEffect getAxisLineEffect() {
        return this.axisLineEffect;
    }

    /**
     * Sets the effect used to draw the axis line and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param pathEffect
     *            the effect (<code>null</code> not permitted).
     * 
     * @see #getAxisLineEffect()
     */
    public void setAxisLineEffect(PathEffect pathEffect) {

        this.axisLineEffect = pathEffect;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns a flag indicating whether or not the tick labels are visible.
     * 
     * @return The flag.
     * 
     * @see #getTickLabelFont()
     * @see #getTickLabelPaintType()
     * @see #setTickLabelsVisible(boolean)
     */
    public boolean isTickLabelsVisible() {
        return this.tickLabelsVisible;
    }

    /**
     * Sets the flag that determines whether or not the tick labels are visible
     * and sends an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param flag
     *            the flag.
     * 
     * @see #isTickLabelsVisible()
     * @see #setTickLabelFont(Font)
     * @see #setTickLabelPaintType(PaintType paintType)
     */
    public void setTickLabelsVisible(boolean flag) {

        if (flag != this.tickLabelsVisible) {
            this.tickLabelsVisible = flag;
            fireChangeEvent();
        }

    }

    /**
     * Returns the flag that indicates whether or not the minor tick marks are
     * showing.
     * 
     * @return The flag that indicates whether or not the minor tick marks are
     *         showing.
     * 
     * @see #setMinorTickMarksVisible(boolean)
     * 
     * @since JFreeChart 1.0.12
     */
    public boolean isMinorTickMarksVisible() {
        return this.minorTickMarksVisible;
    }

    /**
     * Sets the flag that indicates whether or not the minor tick marks are
     * showing and sends an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param flag
     *            the flag.
     * 
     * @see #isMinorTickMarksVisible()
     * 
     * @since JFreeChart 1.0.12
     */
    public void setMinorTickMarksVisible(boolean flag) {
        if (flag != this.minorTickMarksVisible) {
            this.minorTickMarksVisible = flag;
            fireChangeEvent();
        }
    }

    /**
     * Returns the font used for the tick labels (if showing).
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setTickLabelFont(Font)
     */
    public Font getTickLabelFont() {
        return this.tickLabelFont;
    }

    /**
     * Sets the font for the tick labels and sends an {@link AxisChangeEvent} to
     * all registered listeners.
     * 
     * @param font
     *            the font (<code>null</code> not allowed).
     * 
     * @see #getTickLabelFont()
     */
    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

        if (!this.tickLabelFont.equals(font)) {
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }

    /**
     * Returns the color/shade used for the tick labels.
     * 
     * @return The paint type used for the tick labels.
     * 
     * @see #setTickLabelPaintType(PaintType paintType)
     */
    public PaintType getTickLabelPaintType() {
        return this.tickLabelPaintType;
    }

    /**
     * Sets the paint used to draw tick labels (if they are showing) and sends
     * an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getTickLabelPaintType()
     */
    public void setTickLabelPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.tickLabelPaintType = paintType;
        fireChangeEvent();
    }

    /**
     * Returns the insets for the tick labels.
     * 
     * @return The insets (never <code>null</code>).
     * 
     * @see #setTickLabelInsets(RectangleInsets)
     */
    public RectangleInsets getTickLabelInsets() {
        return this.tickLabelInsets;
    }

    /**
     * Sets the insets for the tick labels and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     * 
     * @param insets
     *            the insets (<code>null</code> not permitted).
     * 
     * @see #getTickLabelInsets()
     */
    public void setTickLabelInsets(RectangleInsets insets) {
        if (insets == null) {
            throw new IllegalArgumentException("Null 'insets' argument.");
        }
        if (!this.tickLabelInsets.equals(insets)) {
            this.tickLabelInsets = insets;
            fireChangeEvent();
        }
    }

    /**
     * Returns the flag that indicates whether or not the tick marks are
     * showing.
     * 
     * @return The flag that indicates whether or not the tick marks are
     *         showing.
     * 
     * @see #setTickMarksVisible(boolean)
     */
    public boolean isTickMarksVisible() {
        return this.tickMarksVisible;
    }

    /**
     * Sets the flag that indicates whether or not the tick marks are showing
     * and sends an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param flag
     *            the flag.
     * 
     * @see #isTickMarksVisible()
     */
    public void setTickMarksVisible(boolean flag) {
        if (flag != this.tickMarksVisible) {
            this.tickMarksVisible = flag;
            fireChangeEvent();
        }
    }

    /**
     * Returns the inside length of the tick marks.
     * 
     * @return The length.
     * 
     * @see #getTickMarkOutsideLength()
     * @see #setTickMarkInsideLength(float)
     */
    public float getTickMarkInsideLength() {
        return this.tickMarkInsideLength;
    }

    /**
     * Sets the inside length of the tick marks and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param length
     *            the new length.
     * 
     * @see #getTickMarkInsideLength()
     */
    public void setTickMarkInsideLength(float length) {
        this.tickMarkInsideLength = length;
        fireChangeEvent();
    }

    /**
     * Returns the outside length of the tick marks.
     * 
     * @return The length.
     * 
     * @see #getTickMarkInsideLength()
     * @see #setTickMarkOutsideLength(float)
     */
    public float getTickMarkOutsideLength() {
        return this.tickMarkOutsideLength;
    }

    /**
     * Sets the outside length of the tick marks and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param length
     *            the new length.
     * 
     * @see #getTickMarkInsideLength()
     */
    public void setTickMarkOutsideLength(float length) {
        this.tickMarkOutsideLength = length;
        fireChangeEvent();
    }

    /**
     * Returns the stroke used to draw tick marks.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setTickMarkStroke(int stroke)
     */
    public int getTickMarkStroke() {
        return this.tickMarkStroke;
    }

    /**
     * Sets the stroke used to draw tick marks and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * 
     * @see #getTickMarkStroke()
     */
    public void setTickMarkStroke(int stroke) {

        this.tickMarkStroke = stroke;
        fireChangeEvent();
    }

    /**
     * Returns the effect used to draw tick marks.
     * 
     * @return The effect (never <code>null</code>).
     * 
     * @see #setTickMarkEffect(PathEffect pathEffect)
     */
    public PathEffect getTickMarkEffect() {
        return this.tickMarkEffect;
    }

    /**
     * Sets the effect used to draw tick marks and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param pathEffect
     *            the effect (<code>null</code> not permitted).
     * 
     * @see #getTickMarkEffect()
     */
    public void setTickMarkEffect(PathEffect pathEffect) {

        this.tickMarkEffect = pathEffect;
        notifyListeners(new AxisChangeEvent(this));

    }

    /**
     * Returns the paint used to draw tick marks (if they are showing).
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setTickMarkPaintType(PaintType paintType)
     */
    public PaintType getTickMarkPaintType() {
        return this.tickMarkPaintType;
    }

    /**
     * Sets the paint used to draw tick marks and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getTickMarkPaintType()
     */
    public void setTickMarkPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.tickMarkPaintType = paintType;
        fireChangeEvent();
    }

    /**
     * Returns the inside length of the minor tick marks.
     * 
     * @return The length.
     * 
     * @see #getMinorTickMarkOutsideLength()
     * @see #setMinorTickMarkInsideLength(float)
     * 
     * @since JFreeChart 1.0.12
     */
    public float getMinorTickMarkInsideLength() {
        return this.minorTickMarkInsideLength;
    }

    /**
     * Sets the inside length of the minor tick marks and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param length
     *            the new length.
     * 
     * @see #getMinorTickMarkInsideLength()
     * 
     * @since JFreeChart 1.0.12
     */
    public void setMinorTickMarkInsideLength(float length) {
        this.minorTickMarkInsideLength = length;
        fireChangeEvent();
    }

    /**
     * Returns the outside length of the minor tick marks.
     * 
     * @return The length.
     * 
     * @see #getMinorTickMarkInsideLength()
     * @see #setMinorTickMarkOutsideLength(float)
     * 
     * @since JFreeChart 1.0.12
     */
    public float getMinorTickMarkOutsideLength() {
        return this.minorTickMarkOutsideLength;
    }

    /**
     * Sets the outside length of the minor tick marks and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param length
     *            the new length.
     * 
     * @see #getMinorTickMarkInsideLength()
     * 
     * @since JFreeChart 1.0.12
     */
    public void setMinorTickMarkOutsideLength(float length) {
        this.minorTickMarkOutsideLength = length;
        fireChangeEvent();
    }

    /**
     * Returns the plot that the axis is assigned to. This method will return
     * <code>null</code> if the axis is not currently assigned to a plot.
     * 
     * @return The plot that the axis is assigned to (possibly <code>null</code>
     *         ).
     * 
     * @see #setPlot(Plot)
     */
    public Plot getPlot() {
        return this.plot;
    }

    /**
     * Sets a reference to the plot that the axis is assigned to.
     * <P>
     * This method is used internally, you shouldn't need to call it yourself.
     * 
     * @param plot
     *            the plot.
     * 
     * @see #getPlot()
     */
    public void setPlot(Plot plot) {
        this.plot = plot;
        configure();
    }

    /**
     * Returns the fixed dimension for the axis.
     * 
     * @return The fixed dimension.
     * 
     * @see #setFixedDimension(double)
     */
    public double getFixedDimension() {
        return this.fixedDimension;
    }

    /**
     * Sets the fixed dimension for the axis.
     * <P>
     * This is used when combining more than one plot on a chart. In this case,
     * there may be several axes that need to have the same height or width so
     * that they are aligned. This method is used to fix a dimension for the
     * axis (the context determines whether the dimension is horizontal or
     * vertical).
     * 
     * @param dimension
     *            the fixed dimension.
     * 
     * @see #getFixedDimension()
     */
    public void setFixedDimension(double dimension) {
        this.fixedDimension = dimension;
    }

    /**
     * Configures the axis to work with the current plot. Override this method
     * to perform any special processing (such as auto-rescaling).
     */
    public abstract void configure();

    /**
     * Estimates the space (height or width) required to draw the axis.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot that the axis belongs to.
     * @param plotArea
     *            the area within which the plot (including axes) should be
     *            drawn.
     * @param edge
     *            the axis location.
     * @param space
     *            space already reserved.
     * 
     * @return The space required to draw the axis (including pre-reserved
     *         space).
     */
    public abstract AxisSpace reserveSpace(Canvas canvas, Plot plot,
            RectShape plotArea, RectangleEdge edge, AxisSpace space);

    /**
     * Draws the axis on a graphics device (such as the screen or a
     * printer).
     * 
     * @param canvas
     *            the graphics device (<code>null</code> not permitted).
     * @param cursor
     *            the cursor location (determines where to draw the axis).
     * @param plotArea
     *            the area within which the axes and plot should be drawn.
     * @param dataArea
     *            the area within which the data should be drawn.
     * @param edge
     *            the axis location (<code>null</code> not permitted).
     * @param plotState
     *            collects information about the plot (<code>null</code>
     *            permitted).
     * 
     * @return The axis state (never <code>null</code>).
     */
    public abstract AxisState draw(Canvas canvas, double cursor,
            RectShape plotArea, RectShape dataArea, RectangleEdge edge,
            PlotRenderingInfo plotState);

    /**
     * Calculates the positions of the ticks for the axis, storing the results
     * in the tick list (ready for drawing).
     * 
     * @param canvas
     *            the graphics device.
     * @param state
     *            the axis state.
     * @param dataArea
     *            the area inside the axes.
     * @param edge
     *            the edge on which the axis is located.
     * 
     * @return The list of ticks.
     */
    public abstract List refreshTicks(Canvas canvas, AxisState state,
            RectShape dataArea, RectangleEdge edge);

    /**
     * Created an entity for the axis.
     * 
     * @param cursor
     *            the initial cursor value.
     * @param state
     *            the axis state after completion of the drawing with a possibly
     *            updated cursor position.
     * @param dataArea
     *            the data area.
     * @param edge
     *            the edge.
     * @param plotState
     *            the PlotRenderingInfo from which a reference to the entity
     *            collection can be obtained.
     * 
     * @since JFreeChart 1.0.13
     */
    protected void createAndAddEntity(double cursor, AxisState state,
            RectShape dataArea, RectangleEdge edge,
            PlotRenderingInfo plotState) {

        if (plotState == null || plotState.getOwner() == null) {
            return; // no need to create entity if we can't save it anyways...
        }
        RectShape hotspot = null;
        if (edge.equals(RectangleEdge.TOP)) {
            hotspot = new RectShape(dataArea.getX(),
                    state.getCursor(), dataArea.getWidth(), cursor
                            - state.getCursor());
        } else if (edge.equals(RectangleEdge.BOTTOM)) {
            hotspot = new RectShape(dataArea.getX(), cursor, dataArea
                    .getWidth(), state.getCursor() - cursor);
        } else if (edge.equals(RectangleEdge.LEFT)) {
            hotspot = new RectShape(state.getCursor(),
                    dataArea.getY(), cursor - state.getCursor(), dataArea
                            .getHeight());
        } else if (edge.equals(RectangleEdge.RIGHT)) {
            hotspot = new RectShape(cursor, dataArea.getY(), state
                    .getCursor()
                    - cursor, dataArea.getHeight());
        }
        EntityCollection e = plotState.getOwner().getEntityCollection();
        if (e != null) {
            e.add(new AxisEntity(hotspot, this));
        }
    }

    /**
     * Registers an object for notification of changes to the axis.
     *
     * @param listener  the object that is being registered.
     *
     * @see #removeChangeListener(AxisChangeListener)
     */
    public void addChangeListener(AxisChangeListener listener) {
        this.listenerList.add(listener);
    }

    /**
     * Deregisters an object for notification of changes to the axis.
     *
     * @param listener  the object to deregister.
     *
     * @see #addChangeListener(AxisChangeListener)
     */
    public void removeChangeListener(AxisChangeListener listener) {
        this.listenerList.remove(listener);
    }

    /**
     * Returns <code>true</code> if the specified object is registered with
     * the dataset as a listener.  Most applications won't need to call this
     * method, it exists mainly for use by unit testing code.
     *
     * @param listener  the listener.
     *
     * @return A boolean.
     */
    public boolean hasListener(EventListener listener) {
        return listenerList.contains(listener);
    }

    /**
     * Notifies all registered listeners that the axis has changed.
     * The AxisChangeEvent provides information about the change.
     *
     * @param event  information about the change to the axis.
     */
    protected void notifyListeners(AxisChangeEvent event) {
        if(listenerList.size() == 0) {
            return;
        }
        for (int i = listenerList.size() - 1; i >= 0; i--) {
            listenerList.get(i).axisChanged(event);
        }
    }


    /**
     * Returns a RectShape that encloses the axis label. This is typically used
     * for layout purposes (it gives the maximum dimensions of the label).
     * 
     * @param canvas
     *            the graphics device.
     * @param edge
     *            the edge of the plot area along which the axis is measuring.
     * 
     * @return The enclosing RectShape.
     */
    protected RectShape getLabelEnclosure(Canvas canvas, RectangleEdge edge) {

        RectShape result = new RectShape();
        String axisLabel = getLabel();
        if (axisLabel != null && !axisLabel.equals("")) {
            
            Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, this.labelPaintType, this.labelFont);

            RectShape bounds = TextUtilities.getTextBounds(axisLabel,
                    paint);

            RectangleInsets insets = getLabelInsets();
            bounds = insets.createOutsetRectangle(bounds);

            double angle = getLabelAngle();
            if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
                angle = angle - 90;
            }
            float x = bounds.getCenterX();
            float y = bounds.getCenterY();
            Matrix mat = new Matrix();
            mat.postRotate((float)angle, x, y);
            
            Path p = new Path(bounds.getPath());
            p.transform(mat);
            
            RectF rect = new RectF(); 
            p.computeBounds(rect, false);
            
            result = new RectShape(rect);
            // result = bounds;
        }

        return result;

    }
    
    /**
     * Draws the axis label.
     * 
     * @param label
     *            the label text.
     * @param canvas
     *            the graphics device.
     * @param plotArea
     *            the plot area.
     * @param dataArea
     *            the area inside the axes.
     * @param edge
     *            the location of the axis.
     * @param state
     *            the axis state (<code>null</code> not permitted).
     * 
     * @return Information about the axis.
     */
    protected AxisState drawLabel(String label, Canvas canvas,
            RectShape plotArea, RectShape dataArea, RectangleEdge edge,
            AxisState state) {

        // it is unlikely that 'state' will be null, but check anyway...
        if (state == null) {
            throw new IllegalArgumentException("Null 'state' argument.");
        }

        if ((label == null) || (label.equals(""))) {
            return state;
        }

        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, this.labelPaintType, this.labelFont);

        RectShape labelBounds = TextUtilities.getTextBounds(label,
                paint);

        RectangleInsets insets = getLabelInsets();
        
        if (edge == RectangleEdge.TOP) {
            Matrix mat = new Matrix();
            mat.postRotate((float)getLabelAngle(), labelBounds.getCenterX(), labelBounds.getCenterY());
//            labelBounds.getPath().transform(mat);
//            labelBounds = labelBounds.getBounds();
            Path tmpPath = new Path();
            tmpPath.addPath(labelBounds.getPath(), mat);
            RectF rect = new RectF();
            tmpPath.computeBounds(rect, false);
            labelBounds = new RectShape(rect);
            
            double labelx = dataArea.getCenterX();
            double labely = state.getCursor() - insets.getBottom()
                            - labelBounds.getHeight() / 2.0;
            TextUtilities.drawRotatedString(label, canvas, (float) labelx,
                    (float) labely, TextAnchor.CENTER, Math.toRadians(getLabelAngle()),
                    TextAnchor.CENTER, paint);
            state.cursorUp(insets.getTop() + labelBounds.getHeight()
                    + insets.getBottom());
        } else if (edge == RectangleEdge.BOTTOM) {
            Matrix mat = new Matrix();
            mat.postRotate((float)getLabelAngle(), labelBounds.getCenterX(), labelBounds.getCenterY());
//            labelBounds.getPath().transform(mat);
//            labelBounds = labelBounds.getBounds();
            Path tmpPath = new Path();
            tmpPath.addPath(labelBounds.getPath(), mat);
            RectF rect = new RectF();
            tmpPath.computeBounds(rect, false);
            labelBounds = new RectShape(rect);
            
            double labelx = dataArea.getCenterX();
            double labely = state.getCursor()
                            + insets.getTop() + labelBounds.getHeight() / 2.0;
            TextUtilities.drawRotatedString(label, canvas, (float) labelx,
                    (float) labely, TextAnchor.CENTER, Math.toRadians(getLabelAngle()),
                    TextAnchor.CENTER, paint);
            state.cursorDown(insets.getTop() + labelBounds.getHeight()
                    + insets.getBottom());
        } else if (edge == RectangleEdge.LEFT) {
            Matrix mat = new Matrix();
            mat.postRotate((float)(getLabelAngle()-90 ), labelBounds.getCenterX(), labelBounds.getCenterY());
//            labelBounds.getPath().transform(mat);
//            labelBounds = labelBounds.getBounds();
            Path tmpPath = new Path();
            tmpPath.addPath(labelBounds.getPath(), mat);
            RectF rect = new RectF();
            tmpPath.computeBounds(rect, false);
            labelBounds = new RectShape(rect);
            
            double labelx = state.getCursor()
                - insets.getRight() - labelBounds.getWidth() / 2.0;
            double labely = dataArea.getCenterY();
            TextUtilities
                    .drawRotatedString(label, canvas, (float) labelx,
                            (float) labely, TextAnchor.CENTER, Math.toRadians(getLabelAngle() - 90), TextAnchor.CENTER,
                            paint);
            state.cursorLeft(insets.getLeft() + labelBounds.getWidth()
                    + insets.getRight());
        } else if (edge == RectangleEdge.RIGHT) {
            Matrix mat = new Matrix();
            mat.postRotate((float)(getLabelAngle() + 90), labelBounds.getCenterX(), labelBounds.getCenterY());
//            labelBounds.getPath().transform(mat);
//            labelBounds = labelBounds.getBounds();
            Path tmpPath = new Path();
            tmpPath.addPath(labelBounds.getPath(), mat);
            RectF rect = new RectF();
            tmpPath.computeBounds(rect, false);
            labelBounds = new RectShape(rect);
            
            double labelx = state.getCursor()
                + insets.getLeft() + labelBounds.getWidth() / 2.0;
            double labely = dataArea.getY() + dataArea.getHeight() / 2.0;
            TextUtilities
                    .drawRotatedString(label, canvas, (float) labelx,
                            (float) labely, TextAnchor.CENTER, Math.toRadians(getLabelAngle() + 90), TextAnchor.CENTER,
                            paint);
            state.cursorRight(insets.getLeft() + labelBounds.getWidth()
                    + insets.getRight());
        }

        return state;

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

        LineShape axisLine = null;
        if (edge == RectangleEdge.TOP) {
            axisLine = new LineShape(dataArea.getX(), (float)cursor, dataArea
                    .getMaxX(), (float)cursor);
        } else if (edge == RectangleEdge.BOTTOM) {
            axisLine = new LineShape(dataArea.getX(), (float)cursor, dataArea
                    .getMaxX(), (float)cursor);
        } else if (edge == RectangleEdge.LEFT) {
            axisLine = new LineShape((float)cursor, dataArea.getY(), (float)cursor,
                    dataArea.getMaxY());
        } else if (edge == RectangleEdge.RIGHT) {
            axisLine = new LineShape((float)cursor, dataArea.getY(), (float)cursor,
                    dataArea.getMaxY());
        }
        
        Paint paint = PaintUtility.createPaint(this.axisLinePaintType, this.axisLineStroke, this.axisLineEffect);

        axisLine.draw(canvas, paint);

    }

    /**
     * Sends an {@link AxisChangeEvent} to all registered listeners.
     *
     * @since JFreeChart 1.0.12
     */
    protected void fireChangeEvent() {
        notifyListeners(new AxisChangeEvent(this));
    }
}
