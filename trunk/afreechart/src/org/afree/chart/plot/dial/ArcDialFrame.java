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
 * -----------------
 * ArcDialFrame.java
 * -----------------
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
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 08-Mar-2007 : Fix in hashCode() (DG);
 * 17-Oct-2007 : Updated equals() (DG);
 * 24-Oct-2007 : Added argument checks and API docs, and renamed
 *               StandardDialFrame --> ArcDialFrame (DG);
 *
 */

package org.afree.chart.plot.dial;

import java.io.Serializable;

import org.afree.graphics.geom.ArcShape;
import org.afree.graphics.geom.PathShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.Paint.Style;
import android.graphics.Region.Op;

/**
 * A standard frame for the {@link DialPlot} class.
 *
 * @since JFreeChart 1.0.7
 */
public class ArcDialFrame extends AbstractDialLayer implements DialFrame,
        Cloneable, /*PublicCloneable,*/ Serializable {

    /** For serialization. */
    static final long serialVersionUID = -4089176959553523499L;

    /**
     * The color used for the front of the panel.  This field is transient
     * because it requires special handling for serialization.
     */
    private transient Paint backgroundPaint;

    /**
     * The color used for the border around the window. This field is transient
     * because it requires special handling for serialization.
     */
    private transient Paint foregroundPaint;

    /**
     * The stroke for drawing the frame outline.  This field is transient
     * because it requires special handling for serialization.
     */
    private transient Float stroke;

    /**
     * The start angle.
     */
    private double startAngle;

    /**
     * The end angle.
     */
    private double extent;

    /** The inner radius, relative to the framing RectShape. */
    private double innerRadius;

    /** The outer radius, relative to the framing RectShape. */
    private double outerRadius;

    /**
     * Creates a new instance of <code>ArcDialFrame</code> that spans
     * 180 degrees.
     */
    public ArcDialFrame() {
        this(0, 180);
    }

    /**
     * Creates a new instance of <code>ArcDialFrame</code> that spans
     * the arc specified.
     *
     * @param startAngle  the startAngle (in degrees).
     * @param extent  the extent of the arc (in degrees, counter-clockwise).
     */
    public ArcDialFrame(double startAngle, double extent) {
        //this.backgroundPaint = Color.gray;
        this.backgroundPaint = new Paint();
        this.backgroundPaint.setColor(Color.GRAY);
        //this.foregroundPaint = new Color(100, 100, 150);
        this.foregroundPaint = new Paint();
        this.foregroundPaint.setColor(Color.argb(100,100, 100, 150));
        //this.stroke = new BasicStroke(2.0f);
        this.stroke = 2.0f;
        this.innerRadius = 0.25;
        this.outerRadius = 0.75;
        this.startAngle = startAngle;
        this.extent = extent;
    }

    /**
     * Returns the background paint (never <code>null</code>).
     *
     * @return The background paint.
     *
     * @see #setBackgroundPaint(Paint)
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }

    /**
     * Sets the background paint and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getBackgroundPaint()
     */
    public void setBackgroundPaint(Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.backgroundPaint = paint;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the foreground paint.
     *
     * @return The foreground paint (never <code>null</code>).
     *
     * @see #setForegroundPaint(Paint)
     */
    public Paint getForegroundPaint() {
        return this.foregroundPaint;
    }

    /**
     * Sets the foreground paint and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getForegroundPaint()
     */
    public void setForegroundPaint(Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.foregroundPaint = paint;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the stroke.
     *
     * @return The stroke (never <code>null</code>).
     *
     * @see #setStroke(Float stroke)
     */
    public Float getStroke() {
        return this.stroke;
    }

    /**
     * Sets the stroke and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     *
     * @see #getStroke()
     */
    public void setStroke(Float stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.stroke = stroke;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the inner radius, relative to the framing RectShape.
     *
     * @return The inner radius.
     *
     * @see #setInnerRadius(double)
     */
    public double getInnerRadius() {
        return this.innerRadius;
    }

    /**
     * Sets the inner radius and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     *
     * @param radius  the inner radius.
     *
     * @see #getInnerRadius()
     */
    public void setInnerRadius(double radius) {
        if (radius < 0.0) {
            throw new IllegalArgumentException("Negative 'radius' argument.");
        }
        this.innerRadius = radius;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the outer radius, relative to the framing RectShape.
     *
     * @return The outer radius.
     *
     * @see #setOuterRadius(double)
     */
    public double getOuterRadius() {
        return this.outerRadius;
    }

    /**
     * Sets the outer radius and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     *
     * @param radius  the outer radius.
     *
     * @see #getOuterRadius()
     */
    public void setOuterRadius(double radius) {
        if (radius < 0.0) {
            throw new IllegalArgumentException("Negative 'radius' argument.");
        }
        this.outerRadius = radius;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the start angle.
     *
     * @return The start angle.
     *
     * @see #setStartAngle(double)
     */
    public double getStartAngle() {
        return this.startAngle;
    }

    /**
     * Sets the start angle and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     *
     * @param angle  the angle.
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
     * Sets the extent and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
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
     * Returns the shape for the window for this dial.  Some dial layers will
     * request that their drawing be clipped within this window.
     *
     * @param frame  the reference frame (<code>null</code> not permitted).
     *
     * @return The shape of the dial's window.
     */
    public Shape getWindow(RectShape frame) {

        RectShape innerFrame = DialPlot.RectShapeByRadius(frame,
                this.innerRadius, this.innerRadius);
        RectShape outerFrame = DialPlot.RectShapeByRadius(frame,
                this.outerRadius, this.outerRadius);
//        ArcShape inner = new ArcShape(innerFrame, this.startAngle,
//                this.extent, true);
        ArcShape outer = new ArcShape(outerFrame, this.startAngle,
                this.extent, true);

        Path path = new Path();
        path.arcTo(innerFrame.getRectF(), (float)this.startAngle, (float)this.extent);
        path.lineTo(outer.getEndPoint().x, outer.getEndPoint().y);
        path.arcTo(outerFrame.getRectF(), (float)(this.startAngle + this.extent), (float)-this.extent);
        path.close();
        
        RectF rect2 = new RectF();
        path.computeBounds(rect2, false);
        
        PathShape p = new PathShape(path);
        return p;

    }

    /**
     * Returns the outer window.
     *
     * @param frame  the frame.
     *
     * @return The outer window.
     */
    protected Shape getOuterWindow(RectShape frame) {
        double radiusMargin = 0.02;
        double angleMargin = 1.5;
        RectShape innerFrame = DialPlot.RectShapeByRadius(frame,
                this.innerRadius - radiusMargin, this.innerRadius
                - radiusMargin);
        RectShape outerFrame = DialPlot.RectShapeByRadius(frame,
                this.outerRadius + radiusMargin, this.outerRadius
                + radiusMargin);
//        ArcShape inner = new ArcShape(innerFrame, this.startAngle
//                - angleMargin, this.extent + 2 * angleMargin, true);
        ArcShape outer = new ArcShape(outerFrame, this.startAngle
                - angleMargin + this.extent, (float)(-this.extent + 2 * angleMargin),
                true);

        Path path = new Path();
        path.arcTo(innerFrame.getRectF(), (float)(this.startAngle
                + angleMargin), (float)(this.extent - 2 * angleMargin));
        path.lineTo(outer.getStartPoint().x, outer.getStartPoint().y);
        path.arcTo(outerFrame.getRectF(), (float)(this.startAngle
                - angleMargin + this.extent), (float)(-this.extent + 2 * angleMargin));
        path.close();
        
        RectF rect2 = new RectF();
        path.computeBounds(rect2, false);
        
        PathShape p = new PathShape(path);
        return p;
    }

    /**
     * Draws the frame.
     *
     * @param canvas  the graphics target.
     * @param plot  the plot.
     * @param frame  the dial's reference frame.
     * @param view  the dial's view RectShape.
     */
    public void draw(Canvas canvas, DialPlot plot, RectShape frame,
            Shape view) {
        
        Shape window = getWindow(frame);
        Shape outerWindow = getOuterWindow(frame);
        
        Region region1 = new Region((int)frame.getMinX(), (int)frame.getMinY(), (int)frame.getMaxX(), (int)frame.getMaxY());
        region1.setPath(outerWindow.getPath(), region1);

        Region region2 = new Region((int)frame.getMinX(), (int)frame.getMinY(), (int)frame.getMaxX(), (int)frame.getMaxY());
        region2.setPath(window.getPath(), region2);

        region1.op(region2, Op.XOR);

        Rect rect = new Rect();
        RegionIterator iterator = new RegionIterator(region1);

        foregroundPaint.setStyle(Style.FILL);
        foregroundPaint.setColor(Color.GRAY);
        while(iterator.next(rect)) {
            canvas.drawRect(rect, foregroundPaint);
        }
        
        foregroundPaint.setStyle(Style.STROKE);
        foregroundPaint.setColor(Color.DKGRAY);
        foregroundPaint.setStrokeWidth(this.stroke);
        
        window.draw(canvas, foregroundPaint);
        outerWindow.draw(canvas, foregroundPaint);

    }

    /**
     * Returns <code>false</code> to indicate that this dial layer is not
     * clipped to the dial window.
     *
     * @return <code>false</code>.
     */
    public boolean isClippedToWindow() {
        return false;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArcDialFrame)) {
            return false;
        }
        ArcDialFrame that = (ArcDialFrame) obj;
//        if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
//            return false;
//        }
//        if (!PaintUtilities.equal(this.foregroundPaint, that.foregroundPaint)) {
//            return false;
//        }
        if (this.startAngle != that.startAngle) {
            return false;
        }
        if (this.extent != that.extent) {
            return false;
        }
        if (this.innerRadius != that.innerRadius) {
            return false;
        }
        if (this.outerRadius != that.outerRadius) {
            return false;
        }
        if (!this.stroke.equals(that.stroke)) {
            return false;
        }
        return super.equals(obj);
    }

//    /**
//     * Returns a hash code for this instance.
//     *
//     * @return The hash code.
//     */
//    public int hashCode() {
//        int result = 193;
//        long temp = Double.doubleToLongBits(this.startAngle);
//        result = 37 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(this.extent);
//        result = 37 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(this.innerRadius);
//        result = 37 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(this.outerRadius);
//        result = 37 * result + (int) (temp ^ (temp >>> 32));
//        result = 37 * result + HashUtilities.hashCodeForPaint(
//                this.backgroundPaint);
//        result = 37 * result + HashUtilities.hashCodeForPaint(
//                this.foregroundPaint);
//        result = 37 * result + this.stroke.hashCode();
//        return result;
//    }

    /**
     * Returns a clone of this instance.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if any attribute of this instance
     *     cannot be cloned.
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
//        SerialUtilities.writePaint(this.backgroundPaint, stream);
//        SerialUtilities.writePaint(this.foregroundPaint, stream);
//        SerialUtilities.writeStroke(this.stroke, stream);
//    }

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
//        this.backgroundPaint = SerialUtilities.readPaint(stream);
//        this.foregroundPaint = SerialUtilities.readPaint(stream);
//        this.stroke = SerialUtilities.readStroke(stream);
//    }

}
