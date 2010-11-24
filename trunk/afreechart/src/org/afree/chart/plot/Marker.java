/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
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
 * -----------
 * Marker.java
 * -----------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2002-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nicolas Brodu;
 *                   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes
 * -------
 * 02-Jul-2002 : Added extra constructor, standard header and Javadoc
 *               comments (DG);
 * 20-Aug-2002 : Added the outline stroke attribute (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 16-Oct-2002 : Added new constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 21-May-2003 : Added labels (DG);
 * 11-Sep-2003 : Implemented Cloneable (NB);
 * 05-Nov-2003 : Added checks to ensure some attributes are never null (DG);
 * 11-Feb-2003 : Moved to org.jfree.chart.plot package, plus significant API
 *               changes to support IntervalMarker in plots (DG);
 * 14-Jun-2004 : Updated equals() method (DG);
 * 21-Jan-2005 : Added settings to control direction of horizontal and
 *               vertical label offsets (DG);
 * 01-Jun-2005 : Modified to use only one label offset type - this will be
 *               applied to the domain or range axis as appropriate (DG);
 * 06-Jun-2005 : Fix equals() method to handle GradientPaint (DG);
 * 19-Aug-2005 : Changed constructor from public --> protected (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Sep-2006 : Added MarkerChangeListener support (DG);
 * 26-Sep-2007 : Fix for serialization bug 1802195 (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.chart.plot;

import java.io.Serializable;

import org.afree.ui.LengthAdjustmentType;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleInsets;
import org.afree.ui.TextAnchor;
import org.afree.graphics.geom.Font;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Typeface;

/**
 * The base class for markers that can be added to plots to highlight a value or
 * range of values. <br>
 * <br>
 * An event notification mechanism was added to this class in AFreeChart version
 * 1.0.3.
 */
public abstract class Marker implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -734389651405327166L;

    /** The paint (null is not allowed). */
    private transient PaintType paintType;

    /** The stroke (null is not allowed). */
    private transient float stroke;
    
    private transient PathEffect effect;

    /** The outline paint. */
    private transient PaintType outlinePaintType;

    /** The outline stroke. */
    private transient float outlineStroke;
    
    private transient PathEffect outlineEffect;

    /** The alpha transparency. */
    private int alpha;

    /** The label. */
    private String label = null;

    /** The label font. */
    private Font labelFont;

    /** The label paint. */
    private transient PaintType labelPaintType;

    /** The label position. */
    private RectangleAnchor labelAnchor;

    /** The text anchor for the label. */
    private TextAnchor labelTextAnchor;

    /** The label offset from the marker RectShape. */
    private RectangleInsets labelOffset;

    /**
     * The offset type for the domain or range axis (never <code>null</code>).
     */
    private LengthAdjustmentType labelOffsetType;

    /**
     * Creates a new marker with default attributes.
     */
    protected Marker() {
        this(Color.GRAY);
    }

    /**
     * Constructs a new marker.
     * 
     * @param paint
     *            the paint (<code>null</code> not permitted).
     */
    protected Marker(int paint) {
        this(paint, 0.5f, Color.GRAY, 0.5f, 200);
    }

    /**
     * Constructs a new marker.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * @param outlinePaintType
     *            the outline paint (<code>null</code> permitted).
     * @param outlineStroke
     *            the outline stroke (<code>null</code> permitted).
     * @param alpha
     *            the alpha transparency (must be in the range 0.0f to 1.0f).
     * 
     * @throws IllegalArgumentException
     *             if <code>paint</code> or <code>stroke</code> is
     *             <code>null</code>, or <code>alpha</code> is not in the
     *             specified range.
     */
    protected Marker(int paintType, float stroke, int outlinePaintType,
            float outlineStroke, int alpha) {

        this.paintType = new SolidColor(paintType);
        this.stroke = stroke;
        this.outlinePaintType = new SolidColor(outlinePaintType);
        this.outlineStroke = outlineStroke;
        this.alpha = alpha;

        this.labelFont = new Font("SansSerif", Typeface.NORMAL, 9);
        this.labelPaintType = new SolidColor(Color.BLACK);
        this.labelAnchor = RectangleAnchor.TOP_LEFT;
        this.labelOffset = new RectangleInsets(3.0, 3.0, 3.0, 3.0);
        this.labelOffsetType = LengthAdjustmentType.CONTRACT;
        this.labelTextAnchor = TextAnchor.CENTER;

    }

    /**
     * Returns the paint.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setPaintType(Paint)
     */
    public PaintType getPaintType() {
        return this.paintType;
    }

    /**
     * Sets the paint and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getPaintType()
     */
    public void setPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.paintType = paintType;
    }

    /**
     * Returns the stroke.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setStroke(Stroke)
     */
    public float getStroke() {
        return this.stroke;
    }

    /**
     * Sets the stroke and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     * 
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * 
     * @see #getStroke()
     */
    public void setEffect(PathEffect effect) {

        this.effect = effect;
    }
    

    public PathEffect getEffect() {
        return this.effect;
    }

    /**
     * Sets the stroke and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     * 
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * 
     * @see #getStroke()
     */
    public void setStroke(float stroke) {

        this.stroke = stroke;
    }

    /**
     * Returns the outline paint.
     * 
     * @return The outline paint (possibly <code>null</code>).
     * 
     * @see #setOutlinePaintType(Paint)
     */
    public PaintType getOutlinePaintType() {
        return this.outlinePaintType;
    }

    /**
     * Sets the outline paint and sends a {@link MarkerChangeEvent} to all
     * registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> permitted).
     * 
     * @see #getOutlinePaintType()
     */
    public void setOutlinePaintType(PaintType paintType) {
        this.outlinePaintType = paintType;
    }

    /**
     * Returns the outline stroke.
     * 
     * @return The outline stroke (possibly <code>null</code>).
     * 
     * @see #setOutlineStroke(Stroke)
     */
    public Float getOutlineStroke() {
        return this.outlineStroke;
    }

    /**
     * Sets the outline stroke and sends a {@link MarkerChangeEvent} to all
     * registered listeners.
     * 
     * @param stroke
     *            the stroke (<code>null</code> permitted).
     * 
     * @see #getOutlineStroke()
     */
    public void setOutlineStroke(float stroke) {
        this.outlineStroke = stroke;
    }

    public PathEffect getOutlineEffect() {
        return outlineEffect;
    }

    public void setOutlineEffect(PathEffect outlineEffect) {
        this.outlineEffect = outlineEffect;
    }

    /**
     * Returns the alpha transparency.
     * 
     * @return The alpha transparency.
     * 
     * @see #setAlpha(float)
     */
    public int getAlpha() {
        return this.alpha;
    }

    /**
     * Sets the alpha transparency that should be used when drawing the marker,
     * and sends a {@link MarkerChangeEvent} to all registered listeners. The
     * alpha transparency is a value in the range 0.0f (completely transparent)
     * to 1.0f (completely opaque).
     * 
     * @param alpha
     *            the alpha transparency (must be in the range 0 to 255).
     * 
     * @throws IllegalArgumentException
     *             if <code>alpha</code> is not in the specified range.
     * 
     * @see #getAlpha()
     */
    public void setAlpha(int alpha) {
        
        this.alpha = alpha;
    }

    /**
     * Returns the label (if <code>null</code> no label is displayed).
     * 
     * @return The label (possibly <code>null</code>).
     * 
     * @see #setLabel(String)
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the label (if <code>null</code> no label is displayed) and sends a
     * {@link MarkerChangeEvent} to all registered listeners.
     * 
     * @param label
     *            the label (<code>null</code> permitted).
     * 
     * @see #getLabel()
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns the label font.
     * 
     * @return The label font (never <code>null</code>).
     * 
     * @see #setLabelFont(Font)
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Sets the label font and sends a {@link MarkerChangeEvent} to all
     * registered listeners.
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
        this.labelFont = font;
    }

    /**
     * Returns the label paint.
     * 
     * @return The label paint (never </code>null</code>).
     * 
     * @see #setLabelPaintType(Paint)
     */
    public PaintType getLabelPaintType() {
        return this.labelPaintType;
    }

    /**
     * Sets the label paint and sends a {@link MarkerChangeEvent} to all
     * registered listeners.
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
    }

    /**
     * Returns the label anchor. This defines the position of the label anchor,
     * relative to the bounds of the marker.
     * 
     * @return The label anchor (never <code>null</code>).
     * 
     * @see #setLabelAnchor(RectangleAnchor)
     */
    public RectangleAnchor getLabelAnchor() {
        return this.labelAnchor;
    }

    /**
     * Sets the label anchor and sends a {@link MarkerChangeEvent} to all
     * registered listeners. The anchor defines the position of the label
     * anchor, relative to the bounds of the marker.
     * 
     * @param anchor
     *            the anchor (<code>null</code> not permitted).
     * 
     * @see #getLabelAnchor()
     */
    public void setLabelAnchor(RectangleAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.labelAnchor = anchor;
    }

    /**
     * Returns the label offset.
     * 
     * @return The label offset (never <code>null</code>).
     * 
     * @see #setLabelOffset(RectangleInsets)
     */
    public RectangleInsets getLabelOffset() {
        return this.labelOffset;
    }

    /**
     * Sets the label offset and sends a {@link MarkerChangeEvent} to all
     * registered listeners.
     * 
     * @param offset
     *            the label offset (<code>null</code> not permitted).
     * 
     * @see #getLabelOffset()
     */
    public void setLabelOffset(RectangleInsets offset) {
        if (offset == null) {
            throw new IllegalArgumentException("Null 'offset' argument.");
        }
        this.labelOffset = offset;
    }

    /**
     * Returns the label offset type.
     * 
     * @return The type (never <code>null</code>).
     * 
     * @see #setLabelOffsetType(LengthAdjustmentType)
     */
    public LengthAdjustmentType getLabelOffsetType() {
        return this.labelOffsetType;
    }

    /**
     * Sets the label offset type and sends a {@link MarkerChangeEvent} to all
     * registered listeners.
     * 
     * @param adj
     *            the type (<code>null</code> not permitted).
     * 
     * @see #getLabelOffsetType()
     */
    public void setLabelOffsetType(LengthAdjustmentType adj) {
        if (adj == null) {
            throw new IllegalArgumentException("Null 'adj' argument.");
        }
        this.labelOffsetType = adj;
    }

    /**
     * Returns the label text anchor.
     * 
     * @return The label text anchor (never <code>null</code>).
     * 
     * @see #setLabelTextAnchor(TextAnchor)
     */
    public TextAnchor getLabelTextAnchor() {
        return this.labelTextAnchor;
    }

    /**
     * Sets the label text anchor and sends a {@link MarkerChangeEvent} to all
     * registered listeners.
     * 
     * @param anchor
     *            the label text anchor (<code>null</code> not permitted).
     * 
     * @see #getLabelTextAnchor()
     */
    public void setLabelTextAnchor(TextAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.labelTextAnchor = anchor;
    }
    
    /**
     * Creates a clone of the marker.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException never.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
