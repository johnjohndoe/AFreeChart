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
 * PeriodAxisLabelInfo.java
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
 * 23-Feb-2005 : Replaced Spacer with RectangleInsets (DG);
 * 01-Mar-2005 : Modified constructors to accept DateFormat (DG);
 * 20-May-2005 : Added default constants and null argument checks in the
 *               constructor (DG);
 * 02-Mar-2009 : Updated createInstance to use locale (DG);
 *
 */

package org.afree.chart.axis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.graphics.Color;
import android.graphics.PathEffect;
import android.graphics.Typeface;

import org.afree.ui.RectangleInsets;
import org.afree.io.SerialUtilities;
import org.afree.data.time.RegularTimePeriod;
import org.afree.graphics.geom.Font;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;

/**
 * A record that contains information for one "band" of date labels in
 * a {@link PeriodAxis}.
 */
public class PeriodAxisLabelInfo implements Cloneable, Serializable {

    // TODO: this class is mostly immutable, so implementing Cloneable isn't
    // really necessary.  But there is still a hole in that you can get the
    // dateFormat and modify it.  We could return a copy, but that would slow
    // things down. Needs resolving.

    /** For serialization. */
    private static final long serialVersionUID = 5710451740920277357L;

    /** The default insets. */
    public static final RectangleInsets DEFAULT_INSETS
            = new RectangleInsets(2, 2, 2, 2);

    /** The default font. */
//    public static final Font DEFAULT_FONT
//            = new Font("SansSerif", Font.PLAIN, 10);
    public static final Font DEFAULT_FONT
    = new Font("SansSerif", Typeface.NORMAL, 10);
    
    
    /** The default label paint. */
//    public static final Paint DEFAULT_LABEL_PAINT = Color.black;
    public static final PaintType DEFAULT_LABEL_PAINT_TYPE = new SolidColor(Color.BLACK);

    /** The default divider stroke. */
    public static final float DEFAULT_DIVIDER_STROKE = 0.5f;

    /** The default divider paint. */
//    public static final Paint DEFAULT_DIVIDER_PAINT = Color.gray;
    public static final PaintType DEFAULT_DIVIDER_PAINT_TYPE = new SolidColor(Color.GRAY);
    
    /** The default divider effect. */
    public static final PathEffect DEFAULT_DIVIDER_EFFECT = null;
    
    /** The subclass of {@link RegularTimePeriod} to use for this band. */
    private Class periodClass;

    /** Controls the gaps around the band. */
    private RectangleInsets padding;

    /** The date formatter. */
    private DateFormat dateFormat;

    /** The label font. */
    private Font labelFont;

    /** The label paint. */
    private transient PaintType labelPaintType;

    /** A flag that controls whether or not dividers are visible. */
    private boolean drawDividers;

    /** The stroke used to draw the dividers. */
    private transient float dividerStroke;

    /** The paint used to draw the dividers. */
    private transient PaintType dividerPaintType;
    
    /** The effect used to draw the dividers. */
    private transient PathEffect dividerEffect;

    /**
     * Creates a new instance.
     *
     * @param periodClass  the subclass of {@link RegularTimePeriod} to use
     *                     (<code>null</code> not permitted).
     * @param dateFormat  the date format (<code>null</code> not permitted).
     */
    public PeriodAxisLabelInfo(Class periodClass, DateFormat dateFormat) {
        this(
            periodClass, dateFormat, DEFAULT_INSETS, DEFAULT_FONT,
            DEFAULT_LABEL_PAINT_TYPE, true, DEFAULT_DIVIDER_STROKE,
            DEFAULT_DIVIDER_PAINT_TYPE, DEFAULT_DIVIDER_EFFECT
        );
    }

    /**
     * Creates a new instance.
     *
     * @param periodClass  the subclass of {@link RegularTimePeriod} to use
     *                     (<code>null</code> not permitted).
     * @param dateFormat  the date format (<code>null</code> not permitted).
     * @param padding  controls the space around the band (<code>null</code>
     *                 not permitted).
     * @param labelFont  the label font (<code>null</code> not permitted).
     * @param labelPaintType  the label paint (<code>null</code> not permitted).
     * @param drawDividers  a flag that controls whether dividers are drawn.
     * @param dividerStroke  the stroke used to draw the dividers
     *                       (<code>null</code> not permitted).
     * @param dividerPaintType  the paint used to draw the dividers
     *                      (<code>null</code> not permitted).
     */
    public PeriodAxisLabelInfo(Class periodClass, DateFormat dateFormat,
                               RectangleInsets padding,
                               Font labelFont, PaintType labelPaintType,
                               boolean drawDividers, float dividerStroke,
                               PaintType dividerPaintType, PathEffect dividerEffect) {
        if (periodClass == null) {
            throw new IllegalArgumentException("Null 'periodClass' argument.");
        }
        if (dateFormat == null) {
            throw new IllegalArgumentException("Null 'dateFormat' argument.");
        }
        if (padding == null) {
            throw new IllegalArgumentException("Null 'padding' argument.");
        }
        if (labelFont == null) {
            throw new IllegalArgumentException("Null 'labelFont' argument.");
        }
        if (labelPaintType == null) {
            throw new IllegalArgumentException("Null 'labelPaint' argument.");
        }
        if (dividerStroke == 0) {
            throw new IllegalArgumentException(
                    "Null 'dividerStroke' argument.");
        }
        if (dividerPaintType == null) {
            throw new IllegalArgumentException("Null 'dividerPaint' argument.");
        }
        this.periodClass = periodClass;
        this.dateFormat = dateFormat;
        this.padding = padding;
        this.labelFont = labelFont;
        this.labelPaintType = labelPaintType;
        this.drawDividers = drawDividers;
        this.dividerStroke = dividerStroke;
        this.dividerPaintType = dividerPaintType;
        this.dividerEffect = dividerEffect;
    }
    
    public PeriodAxisLabelInfo(Class periodClass, DateFormat dateFormat,
            RectangleInsets padding,
            Font labelFont, PaintType labelPaintType,
            boolean drawDividers, float dividerStroke,
            PaintType dividerPaintType) {
        this(periodClass, dateFormat, 
                padding, 
                labelFont, labelPaintType,
                drawDividers, dividerStroke,
                dividerPaintType, null);
    }

    /**
     * Returns the subclass of {@link RegularTimePeriod} that should be used
     * to generate the date labels.
     *
     * @return The class.
     */
    public Class getPeriodClass() {
        return this.periodClass;
    }

    /**
     * Returns the date formatter.
     *
     * @return The date formatter (never <code>null</code>).
     */
    public DateFormat getDateFormat() {
        return this.dateFormat;
    }

    /**
     * Returns the padding for the band.
     *
     * @return The padding.
     */
    public RectangleInsets getPadding() {
        return this.padding;
    }

    /**
     * Returns the label font.
     *
     * @return The label font (never <code>null</code>).
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Returns the label paint.
     *
     * @return The label paint.
     */
    public PaintType getLabelPaintType() {
        return this.labelPaintType;
    }

    /**
     * Returns a flag that controls whether or not dividers are drawn.
     *
     * @return A flag.
     */
    public boolean getDrawDividers() {
        return this.drawDividers;
    }

    /**
     * Returns the stroke used to draw the dividers.
     *
     * @return The stroke.
     */
    public float getDividerStroke() {
        return this.dividerStroke;
    }

    /**
     * Returns the paint used to draw the dividers.
     *
     * @return The paint type.
     */
    public PaintType getDividerPaintType() {
        return this.dividerPaintType;
    }
    
    /**
     * Returns the effect used to draw the dividers.
     *
     * @return The effect.
     */
    public PathEffect getDividerEffect() {
        return this.dividerEffect;
    }

    /**
     * Creates a time period that includes the specified millisecond, assuming
     * the given time zone.
     *
     * @param millisecond  the time.
     * @param zone  the time zone.
     * @param locale  the locale.
     *
     * @return The time period.
     *
     * @since JFreeChart 1.0.13.
     */
    public RegularTimePeriod createInstance(Date millisecond, TimeZone zone,
            Locale locale) {
        RegularTimePeriod result = null;
        try {
            Constructor c = this.periodClass.getDeclaredConstructor(
                    new Class[] {Date.class, TimeZone.class, Locale.class});
            result = (RegularTimePeriod) c.newInstance(new Object[] {
                    millisecond, zone, locale});
        }
        catch (Exception e) {
            // do nothing
        }
        return result;
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the object to test against (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PeriodAxisLabelInfo) {
            PeriodAxisLabelInfo info = (PeriodAxisLabelInfo) obj;
            if (!info.periodClass.equals(this.periodClass)) {
                return false;
            }
            if (!info.dateFormat.equals(this.dateFormat)) {
                return false;
            }
            if (!info.padding.equals(this.padding)) {
                return false;
            }
            if (!info.labelFont.equals(this.labelFont)) {
                return false;
            }
            if (!info.labelPaintType.equals(this.labelPaintType)) {
                return false;
            }
            if (info.drawDividers != this.drawDividers) {
                return false;
            }
            if (info.dividerStroke != (this.dividerStroke)) {
                return false;
            }
            if (!info.dividerPaintType.equals(this.dividerPaintType)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = 41;
        result = 37 * this.periodClass.hashCode();
        result = 37 * this.dateFormat.hashCode();
        return result;
    }

    /**
     * Returns a clone of the object.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
        PeriodAxisLabelInfo clone = (PeriodAxisLabelInfo) super.clone();
        return clone;
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
        SerialUtilities.writePaintType(this.labelPaintType, stream);
//        SerialUtilities.writeStroke(this.dividerStroke, stream);
        SerialUtilities.writePaintType(this.dividerPaintType, stream);
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
        this.labelPaintType = SerialUtilities.readPaintType(stream);
//        this.dividerStroke = SerialUtilities.readStroke(stream);
        this.dividerPaintType = SerialUtilities.readPaintType(stream);
    }

}
