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
 * ---------------------
 * XYTextAnnotation.java
 * ---------------------
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
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 28-Aug-2002 : Version 1 (DG);
 * 07-Nov-2002 : Fixed errors reported by Checkstyle (DG);
 * 13-Jan-2003 : Reviewed Javadocs (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 02-Jul-2003 : Added new text alignment and rotation options (DG);
 * 19-Aug-2003 : Implemented Cloneable (DG);
 * 17-Jan-2003 : Added fix for bug 878706, where the annotation is placed
 *               incorrectly for a plot with horizontal orientation (thanks to
 *               Ed Yu for the fix) (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 26-Jan-2006 : Fixed equals() method (bug 1415480) (DG);
 * 06-Mar-2007 : Added argument checks, re-implemented hashCode() method (DG);
 * 12-Feb-2009 : Added background paint and outline paint/stroke (DG);
 * 01-Apr-2009 : Fixed bug in hotspot calculation (DG);
 * 
 */

package org.afree.chart.annotations;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import org.afree.util.PaintTypeUtilities;
import org.afree.util.PublicCloneable;
import org.afree.ui.RectangleEdge;
import org.afree.io.SerialUtilities;
import org.afree.ui.TextAnchor;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;

/**
 * A text annotation that can be placed at a particular (x, y) location on an
 * {@link XYPlot}.
 */
public class XYTextAnnotation extends AbstractXYAnnotation
        implements Cloneable, PublicCloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -2946063342782506328L;

    /** The default font. */
    public static final Font DEFAULT_FONT = new Font("SansSerif", Typeface.NORMAL,
            10);

    /** The default paint. */
//    public static final Paint DEFAULT_PAINT = Color.black;
    public static final PaintType DEFAULT_PAINT_TYPE = new SolidColor(Color.BLACK);
    

    /** The default text anchor. */
    public static final TextAnchor DEFAULT_TEXT_ANCHOR = TextAnchor.CENTER;

    /** The default rotation anchor. */
    public static final TextAnchor DEFAULT_ROTATION_ANCHOR = TextAnchor.CENTER;

    /** The default rotation angle. */
    public static final double DEFAULT_ROTATION_ANGLE = 0.0;

    /** The text. */
    private String text;

    /** The font. */
    private Font font;

    /** The paint. */
    private transient PaintType paintType;

    /** The x-coordinate. */
    private double x;

    /** The y-coordinate. */
    private double y;

    /** The text anchor (to be aligned with (x, y)). */
    private TextAnchor textAnchor;

    /** The rotation anchor. */
    private TextAnchor rotationAnchor;

    /** The rotation angle. */
    private double rotationAngle;

    /**
     * The background paint (possibly null).
     *
     * @since JFreeChart 1.0.13
     */
    private transient PaintType backgroundPaintType;

    /**
     * The flag that controls the visibility of the outline.
     *
     * @since JFreeChart 1.0.13
     */
    private boolean outlineVisible;

    /**
     * The outline paint (never null).
     *
     * @since JFreeChart 1.0.13
     */
    private transient PaintType outlinePaintType;

    /**
     * The outline stroke (never null).
     *
     * @since JFreeChart 1.0.13
     */
    private transient float outlineStroke;

    /**
     * Creates a new annotation to be displayed at the given coordinates.  The
     * coordinates are specified in data space (they will be converted to
     * Java2D space for display).
     *
     * @param text  the text (<code>null</code> not permitted).
     * @param x  the x-coordinate (in data space).
     * @param y  the y-coordinate (in data space).
     */
    public XYTextAnnotation(String text, double x, double y) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");
        }
        this.text = text;
        this.font = DEFAULT_FONT;
        this.paintType = DEFAULT_PAINT_TYPE;
        this.x = x;
        this.y = y;
        this.textAnchor = DEFAULT_TEXT_ANCHOR;
        this.rotationAnchor = DEFAULT_ROTATION_ANCHOR;
        this.rotationAngle = DEFAULT_ROTATION_ANGLE;

        // by default the outline and background won't be visible
        this.backgroundPaintType = null;
        this.outlineVisible = false;
        this.outlinePaintType = new SolidColor(Color.BLACK);
        this.outlineStroke = 0.5f;
    }

    /**
     * Returns the text for the annotation.
     *
     * @return The text (never <code>null</code>).
     *
     * @see #setText(String)
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets the text for the annotation.
     *
     * @param text  the text (<code>null</code> not permitted).
     *
     * @see #getText()
     */
    public void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");
        }
        this.text = text;
    }

    /**
     * Returns the font for the annotation.
     *
     * @return The font (never <code>null</code>).
     *
     * @see #setFont(Font)
     */
    public Font getFont() {
        return this.font;
    }

    /**
     * Sets the font for the annotation.
     *
     * @param font  the font (<code>null</code> not permitted).
     *
     * @see #getFont()
     */
    public void setFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.font = font;
    }

    /**
     * Returns the paint for the annotation.
     *
     * @return The paint type (never <code>null</code>).
     *
     * @see #setPaintType(PaintType paintType)
     */
    public PaintType getPaintType() {
        return this.paintType;
    }

    /**
     * Sets the paint for the annotation.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
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
     * Returns the text anchor.
     *
     * @return The text anchor (never <code>null</code>).
     *
     * @see #setTextAnchor(TextAnchor)
     */
    public TextAnchor getTextAnchor() {
        return this.textAnchor;
    }

    /**
     * Sets the text anchor (the point on the text bounding rectangle that is
     * aligned to the (x, y) coordinate of the annotation).
     *
     * @param anchor  the anchor point (<code>null</code> not permitted).
     *
     * @see #getTextAnchor()
     */
    public void setTextAnchor(TextAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.textAnchor = anchor;
    }

    /**
     * Returns the rotation anchor.
     *
     * @return The rotation anchor point (never <code>null</code>).
     *
     * @see #setRotationAnchor(TextAnchor)
     */
    public TextAnchor getRotationAnchor() {
        return this.rotationAnchor;
    }

    /**
     * Sets the rotation anchor point.
     *
     * @param anchor  the anchor (<code>null</code> not permitted).
     *
     * @see #getRotationAnchor()
     */
    public void setRotationAnchor(TextAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.rotationAnchor = anchor;
    }

    /**
     * Returns the rotation angle.
     *
     * @return The rotation angle.
     *
     * @see #setRotationAngle(double)
     */
    public double getRotationAngle() {
        return this.rotationAngle;
    }

    /**
     * Sets the rotation angle.  The angle is measured clockwise in radians.
     *
     * @param angle  the angle (in radians).
     *
     * @see #getRotationAngle()
     */
    public void setRotationAngle(double angle) {
        this.rotationAngle = angle;
    }

    /**
     * Returns the x coordinate for the text anchor point (measured against the
     * domain axis).
     *
     * @return The x coordinate (in data space).
     *
     * @see #setX(double)
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets the x coordinate for the text anchor point (measured against the
     * domain axis).
     *
     * @param x  the x coordinate (in data space).
     *
     * @see #getX()
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the y coordinate for the text anchor point (measured against the
     * range axis).
     *
     * @return The y coordinate (in data space).
     *
     * @see #setY(double)
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the y coordinate for the text anchor point (measured against the
     * range axis).
     *
     * @param y  the y coordinate.
     *
     * @see #getY()
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns the background paint for the annotation.
     *
     * @return The background paint (possibly <code>null</code>).
     *
     * @see #setBackgroundPaintType(PaintType paintType)
     *
     * @since JFreeChart 1.0.13
     */
    public PaintType getBackgroundPaintType() {
        return this.backgroundPaintType;
    }

    /**
     * Sets the background paint for the annotation.
     *
     * @param paintType  the paint (<code>null</code> permitted).
     *
     * @see #getBackgroundPaintType()
     *
     * @since JFreeChart 1.0.13
     */
    public void setBackgroundPaintType(PaintType paintType) {
        this.backgroundPaintType = paintType;
    }

    /**
     * Returns the outline paint for the annotation.
     *
     * @return The outline paint (never <code>null</code>).
     *
     * @see #setOutlinePaintType(PaintType paintType)
     *
     * @since JFreeChart 1.0.13
     */
    public PaintType getOutlinePaintType() {
        return this.outlinePaintType;
    }

    /**
     * Sets the outline paint for the annotation.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getOutlinePaintType()
     *
     * @since JFreeChart 1.0.13
     */
    public void setOutlinePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.outlinePaintType = paintType;
    }

    /**
     * Returns the outline stroke for the annotation.
     *
     * @return The outline stroke (never <code>null</code>).
     *
     * @see #setOutlineStroke(float stroke)
     *
     * @since JFreeChart 1.0.13
     */
    public float getOutlineStroke() {
        return this.outlineStroke;
    }

    /**
     * Sets the outline stroke for the annotation.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     *
     * @see #getOutlineStroke()
     *
     * @since JFreeChart 1.0.13
     */
    public void setOutlineStroke(float stroke) {
        if (stroke == 0) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.outlineStroke = stroke;
    }

    /**
     * Returns the flag that controls whether or not the outline is drawn.
     *
     * @return A boolean.
     *
     * @since JFreeChart 1.0.13
     */
    public boolean isOutlineVisible() {
        return this.outlineVisible;
    }

    /**
     * Sets the flag that controls whether or not the outline is drawn.
     *
     * @param visible  the new flag value.
     *
     * @since JFreeChart 1.0.13
     */
    public void setOutlineVisible(boolean visible) {
        this.outlineVisible = visible;
    }

    /**
     * Draws the annotation.
     *
     * @param canvas  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param rendererIndex  the renderer index.
     * @param info  an optional info object that will be populated with
     *              entity information.
     */
    public void draw(Canvas canvas, XYPlot plot, RectShape dataArea,
                     ValueAxis domainAxis, ValueAxis rangeAxis,
                     int rendererIndex,
                     PlotRenderingInfo info) {

        PlotOrientation orientation = plot.getOrientation();
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);

        float anchorX = (float) domainAxis.valueToJava2D(
                this.x, dataArea, domainEdge);
        float anchorY = (float) rangeAxis.valueToJava2D(
                this.y, dataArea, rangeEdge);

        if (orientation == PlotOrientation.HORIZONTAL) {
            float tempAnchor = anchorX;
            anchorX = anchorY;
            anchorY = tempAnchor;
        }

        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getPaintType(), getFont());
        RectShape hotspot = TextUtilities.calculateRotatedStringBounds(
                getText(), paint, anchorX, anchorY, getTextAnchor(),
                getRotationAngle(), getRotationAnchor());
        if (this.backgroundPaintType != null) {
            PaintUtility.updatePaint(paint, this.backgroundPaintType);
            
            hotspot.fill(canvas, paint);
        }

        PaintUtility.updatePaint(paint, this.backgroundPaintType);
        TextUtilities.drawRotatedString(getText(), canvas, anchorX, anchorY,
                getTextAnchor(), getRotationAngle(), getRotationAnchor(), paint);
        if (this.outlineVisible) {
            PaintUtility.updatePaint(paint, this.backgroundPaintType);
            paint.setStrokeWidth(this.outlineStroke);

            hotspot.draw(canvas, paint);
        }

        String toolTip = getToolTipText();
        String url = getURL();
        if (toolTip != null || url != null) {
            addEntity(info, hotspot, rendererIndex, toolTip, url);
        }

    }

    /**
     * Tests this annotation for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYTextAnnotation)) {
            return false;
        }
        XYTextAnnotation that = (XYTextAnnotation) obj;
        if (!this.text.equals(that.text)) {
            return false;
        }
        if (this.x != that.x) {
            return false;
        }
        if (this.y != that.y) {
            return false;
        }
        if (!this.font.equals(that.font)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.paintType, that.paintType)) {
            return false;
        }
        if (!this.rotationAnchor.equals(that.rotationAnchor)) {
            return false;
        }
        if (this.rotationAngle != that.rotationAngle) {
            return false;
        }
        if (!this.textAnchor.equals(that.textAnchor)) {
            return false;
        }
        if (this.outlineVisible != that.outlineVisible) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.backgroundPaintType, that.backgroundPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.outlinePaintType, that.outlinePaintType)) {
            return false;
        }
//        if (!(this.outlineStroke.equals(that.outlineStroke))) {
        if (this.outlineStroke != that.outlineStroke) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns a hash code for the object.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = 193;
        result = 37 * this.text.hashCode();
        result = 37 * this.font.hashCode();
//        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
        long temp = Double.doubleToLongBits(this.x);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.y);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        result = 37 * result + this.textAnchor.hashCode();
        result = 37 * result + this.rotationAnchor.hashCode();
        temp = Double.doubleToLongBits(this.rotationAngle);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Returns a clone of the annotation.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  if the annotation can't be cloned.
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
        SerialUtilities.writePaintType(this.paintType, stream);
        SerialUtilities.writePaintType(this.backgroundPaintType, stream);
        SerialUtilities.writePaintType(this.outlinePaintType, stream);
//        SerialUtilities.writeStroke(this.outlineStroke, stream);
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
        this.paintType = SerialUtilities.readPaintType(stream);
        this.backgroundPaintType = SerialUtilities.readPaintType(stream);
        this.outlinePaintType = SerialUtilities.readPaintType(stream);
//        this.outlineStroke = SerialUtilities.readStroke(stream);
    }

}
