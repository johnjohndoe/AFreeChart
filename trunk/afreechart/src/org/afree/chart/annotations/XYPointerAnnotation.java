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
 * XYPointerAnnotation.java
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
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 21-May-2003 : Version 1 (DG);
 * 10-Jun-2003 : Changed BoundsAnchor to TextAnchor (DG);
 * 02-Jul-2003 : Added accessor methods and simplified constructor (DG);
 * 19-Aug-2003 : Implemented Cloneable (DG);
 * 13-Oct-2003 : Fixed bug where arrow paint is not set correctly (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 29-Sep-2004 : Changes to draw() method signature (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Feb-2006 : Correction for equals() method (fixes bug 1435160) (DG);
 * 12-Jul-2006 : Fix drawing for PlotOrientation.HORIZONTAL, thanks to
 *               Skunk (DG);
 * 12-Feb-2009 : Added support for rotated label, plus background and
 *               outline (DG);
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
import android.graphics.PathEffect;

import org.afree.util.ObjectUtilities;
import org.afree.util.PublicCloneable;
import org.afree.ui.RectangleEdge;
import org.afree.io.SerialUtilities;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.PathShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;

/**
 * An arrow and label that can be placed on an {@link XYPlot}.  The arrow is
 * drawn at a user-definable angle so that it points towards the (x, y)
 * location for the annotation.
 * <p>
 * The arrow length (and its offset from the (x, y) location) is controlled by
 * the tip radius and the base radius attributes.  Imagine two circles around
 * the (x, y) coordinate: the inner circle defined by the tip radius, and the
 * outer circle defined by the base radius.  Now, draw the arrow starting at
 * some point on the outer circle (the point is determined by the angle), with
 * the arrow tip being drawn at a corresponding point on the inner circle.
 *
 */
public class XYPointerAnnotation extends XYTextAnnotation
        implements Cloneable, PublicCloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -4031161445009858551L;

    /** The default tip radius (in Canvas units). */
    public static final double DEFAULT_TIP_RADIUS = 10.0;

    /** The default base radius (in Canvas units). */
    public static final double DEFAULT_BASE_RADIUS = 30.0;

    /** The default label offset (in Canvas units). */
    public static final double DEFAULT_LABEL_OFFSET = 3.0;

    /** The default arrow length (in Canvas units). */
    public static final double DEFAULT_ARROW_LENGTH = 5.0;

    /** The default arrow width (in Canvas units). */
    public static final double DEFAULT_ARROW_WIDTH = 3.0;

    /** The angle of the arrow's line (in radians). */
    private double angle;

    /**
     * The radius from the (x, y) point to the tip of the arrow (in Java2D
     * units).
     */
    private double tipRadius;

    /**
     * The radius from the (x, y) point to the start of the arrow line (in
     * Java2D units).
     */
    private double baseRadius;

    /** The length of the arrow head (in Canvas units). */
    private double arrowLength;

    /** The arrow width (in Canvas units, per side). */
    private double arrowWidth;

    /** The arrow stroke. */
    private transient float arrowStroke;
    
    /** The arrow effect. */
    private transient PathEffect arrowEffect;

    /** The arrow paint. */
    private transient PaintType arrowPaintType;

    /** The radius from the base point to the anchor point for the label. */
    private double labelOffset;

    /**
     * Creates a new label and arrow annotation.
     *
     * @param label  the label (<code>null</code> permitted).
     * @param x  the x-coordinate (measured against the chart's domain axis).
     * @param y  the y-coordinate (measured against the chart's range axis).
     * @param angle  the angle of the arrow's line (in radians).
     */
    public XYPointerAnnotation(String label, double x, double y, double angle) {

        super(label, x, y);
        this.angle = angle;
        this.tipRadius = DEFAULT_TIP_RADIUS;
        this.baseRadius = DEFAULT_BASE_RADIUS;
        this.arrowLength = DEFAULT_ARROW_LENGTH;
        this.arrowWidth = DEFAULT_ARROW_WIDTH;
        this.labelOffset = DEFAULT_LABEL_OFFSET;
        this.arrowStroke = 1.0f;
        this.arrowEffect = null;
        PaintType black = new SolidColor(Color.BLACK);
        this.arrowPaintType = black;

    }

    /**
     * Returns the angle of the arrow.
     *
     * @return The angle (in radians).
     *
     * @see #setAngle(double)
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * Sets the angle of the arrow.
     *
     * @param angle  the angle (in radians).
     *
     * @see #getAngle()
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Returns the tip radius.
     *
     * @return The tip radius (in Canvas units).
     *
     * @see #setTipRadius(double)
     */
    public double getTipRadius() {
        return this.tipRadius;
    }

    /**
     * Sets the tip radius.
     *
     * @param radius  the radius (in Canvas units).
     *
     * @see #getTipRadius()
     */
    public void setTipRadius(double radius) {
        this.tipRadius = radius;
    }

    /**
     * Returns the base radius.
     *
     * @return The base radius (in Canvas units).
     *
     * @see #setBaseRadius(double)
     */
    public double getBaseRadius() {
        return this.baseRadius;
    }

    /**
     * Sets the base radius.
     *
     * @param radius  the radius (in Canvas units).
     *
     * @see #getBaseRadius()
     */
    public void setBaseRadius(double radius) {
        this.baseRadius = radius;
    }

    /**
     * Returns the label offset.
     *
     * @return The label offset (in Canvas units).
     *
     * @see #setLabelOffset(double)
     */
    public double getLabelOffset() {
        return this.labelOffset;
    }

    /**
     * Sets the label offset (from the arrow base, continuing in a straight
     * line, in Canvas units).
     *
     * @param offset  the offset (in Canvas units).
     *
     * @see #getLabelOffset()
     */
    public void setLabelOffset(double offset) {
        this.labelOffset = offset;
    }

    /**
     * Returns the arrow length.
     *
     * @return The arrow length.
     *
     * @see #setArrowLength(double)
     */
    public double getArrowLength() {
        return this.arrowLength;
    }

    /**
     * Sets the arrow length.
     *
     * @param length  the length.
     *
     * @see #getArrowLength()
     */
    public void setArrowLength(double length) {
        this.arrowLength = length;
    }

    /**
     * Returns the arrow width.
     *
     * @return The arrow width (in Canvas units).
     *
     * @see #setArrowWidth(double)
     */
    public double getArrowWidth() {
        return this.arrowWidth;
    }

    /**
     * Sets the arrow width.
     *
     * @param width  the width (in Canvas units).
     *
     * @see #getArrowWidth()
     */
    public void setArrowWidth(double width) {
        this.arrowWidth = width;
    }

    /**
     * Returns the stroke used to draw the arrow line.
     *
     * @return The arrow stroke (never <code>null</code>).
     *
     * @see #setArrowStroke(float stroke)
     */
    public float getArrowStroke() {
        return this.arrowStroke;
    }

    /**
     * Sets the stroke used to draw the arrow line.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     *
     * @see #getArrowStroke()
     */
    public void setArrowStroke(float stroke) {
        if (stroke == 0) {
            throw new IllegalArgumentException("Null 'stroke' not permitted.");
        }
        this.arrowStroke = stroke;
    }

    /**
     * Returns the paint used for the arrow.
     *
     * @return The arrow paint (never <code>null</code>).
     *
     * @see #setArrowPaintType(PaintType paintType)
     */
    public PaintType getArrowPaintType() {
        return this.arrowPaintType;
    }

    /**
     * Sets the paint used for the arrow.
     *
     * @param paintType  the arrow paint (<code>null</code> not permitted).
     *
     * @see #getArrowPaintType()
     */
    public void setArrowPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.arrowPaintType = paintType;
    }

    /**
     * Returns the effect used for the arrow.
     *
     * @return The arrow effect (never <code>null</code>).
     *
     * @see #setArrowPaintEffect(PathEffect pathEffect)
     */
    public PathEffect getArrowPaintEffect() {
        return this.arrowEffect;
    }

    /**
     * Sets the effect used for the arrow.
     *
     * @param pathEffect  the arrow effect (<code>null</code> not permitted).
     *
     * @see #getArrowPaintEffect()
     */
    public void setArrowPaintEffect(PathEffect pathEffect) {
        if (pathEffect == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.arrowEffect = pathEffect;
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
     * @param info  the plot rendering info.
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
        double j2DX = domainAxis.valueToJava2D(getX(), dataArea, domainEdge);
        double j2DY = rangeAxis.valueToJava2D(getY(), dataArea, rangeEdge);
        if (orientation == PlotOrientation.HORIZONTAL) {
            double temp = j2DX;
            j2DX = j2DY;
            j2DY = temp;
        }
        double startX = j2DX + Math.cos(this.angle) * this.baseRadius;
        double startY = j2DY + Math.sin(this.angle) * this.baseRadius;

        double endX = j2DX + Math.cos(this.angle) * this.tipRadius;
        double endY = j2DY + Math.sin(this.angle) * this.tipRadius;

        double arrowBaseX = endX + Math.cos(this.angle) * this.arrowLength;
        double arrowBaseY = endY + Math.sin(this.angle) * this.arrowLength;

        double arrowLeftX = arrowBaseX
                + Math.cos(this.angle + Math.PI / 2.0) * this.arrowWidth;
        double arrowLeftY = arrowBaseY
                + Math.sin(this.angle + Math.PI / 2.0) * this.arrowWidth;

        double arrowRightX = arrowBaseX
                - Math.cos(this.angle + Math.PI / 2.0) * this.arrowWidth;
        double arrowRightY = arrowBaseY
                - Math.sin(this.angle + Math.PI / 2.0) * this.arrowWidth;

        PathShape arrow = new PathShape();
        arrow.moveTo((float) endX, (float) endY);
        arrow.lineTo((float) arrowLeftX, (float) arrowLeftY);
        arrow.lineTo((float) arrowRightX, (float) arrowRightY);
        arrow.closePath();

//        canvas.setStroke(this.arrowStroke);
//        canvas.setPaint(this.arrowPaint);
        
        Paint paint = PaintUtility.createPaint(
                Paint.ANTI_ALIAS_FLAG,
                this.arrowPaintType, 
                this.arrowStroke, 
                this.arrowEffect);
        LineShape line = new LineShape(startX, startY, endX, endY);
//        canvas.draw(line);
//        canvas.fill(arrow);
        canvas.drawLine(line.getX1(), line.getY1(), 
                line.getX2(), line.getY2(), paint);
        canvas.drawPath(arrow.getPath(), paint);
        
        // draw the label
        double labelX = j2DX + Math.cos(this.angle) * (this.baseRadius
                + this.labelOffset);
        double labelY = j2DY + Math.sin(this.angle) * (this.baseRadius
                + this.labelOffset);
//        canvas.setFont(getFont());
        
//        Shape hotspot = TextUtilities.calculateRotatedStringBounds(
//                getText(), canvas, (float) labelX, (float) labelY, getTextAnchor(),
//                getRotationAngle(), getRotationAnchor());
        
//        hotspot = TextUtilities.drawRotatedString(
//                getText(), canvas, (float) labelX, (float) labelY, getTextAnchor(),
//                getRotationAngle(), getRotationAnchor(),paint);        
        
        if (getBackgroundPaintType() != null) {
//            canvas.setPaint(getBackgroundPaint());
//            canvas.fill(hotspot);
            PaintUtility.updatePaint(paint, getBackgroundPaintType());
            canvas.drawPaint(paint);
        }
        
//        canvas.setPaint(getPaint());
        
        PaintUtility.updatePaint(paint, getPaintType());
//        TextUtilities.drawRotatedString(getText(), canvas, (float) labelX,
//                (float) labelY, getTextAnchor(), getRotationAngle(),
//                getRotationAnchor());
        TextUtilities.drawRotatedString(
                getText(), canvas, (float) labelX, (float) labelY, getTextAnchor(),
                getRotationAngle(), getRotationAnchor(),paint);
        if (isOutlineVisible()) {
//            canvas.setStroke(getOutlineStroke());
//            canvas.setPaint(getOutlinePaint());
//            canvas.draw(hotspot);
            PaintUtility.updatePaint(paint, getOutlinePaintType());
            paint.setStrokeWidth(getOutlineStroke());
            canvas.drawLine((float)startX, (float)startY, (float)endX, (float)endY, paint);
        }

        String toolTip = getToolTipText();
        String url = getURL();
        Shape hotspot = TextUtilities.getTextBounds(getText(),paint);
        if (toolTip != null || url != null) {
            addEntity(info, hotspot, rendererIndex, toolTip, url);
        }

    }

    /**
     * Tests this annotation for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYPointerAnnotation)) {
            return false;
        }
        XYPointerAnnotation that = (XYPointerAnnotation) obj;
        if (this.angle != that.angle) {
            return false;
        }
        if (this.tipRadius != that.tipRadius) {
            return false;
        }
        if (this.baseRadius != that.baseRadius) {
            return false;
        }
        if (this.arrowLength != that.arrowLength) {
            return false;
        }
        if (this.arrowWidth != that.arrowWidth) {
            return false;
        }
        if (!this.arrowPaintType.equals(that.arrowPaintType)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.arrowStroke, that.arrowStroke)) {
            return false;
        }
        if (this.labelOffset != that.labelOffset) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = super.hashCode();
        long temp = Double.doubleToLongBits(this.angle);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.tipRadius);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.baseRadius);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.arrowLength);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.arrowWidth);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
//        result = result * 37 + HashUtilities.hashCodeForPaint(this.arrowPaint);
//        result = result * 37 + this.arrowStroke.hashCode();
        temp = Double.doubleToLongBits(this.labelOffset);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        return super.hashCode();
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
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaintType(this.arrowPaintType, stream);
//        SerialUtilities.writeStroke(this.arrowStroke, stream);
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
        this.arrowPaintType = SerialUtilities.readPaintType(stream);
//        this.arrowStroke = SerialUtilities.readStroke(stream);
    }

}
