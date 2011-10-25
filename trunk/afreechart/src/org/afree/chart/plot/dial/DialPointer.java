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
 * ----------------
 * DialPointer.java
 * ----------------
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
 * 17-Oct-2007 : Added equals() overrides (DG);
 * 24-Oct-2007 : Implemented PublicCloneable, changed default radius,
 *               and added argument checks (DG);
 * 23-Nov-2007 : Added fillPaint and outlinePaint attributes to
 *               DialPointer.Pointer (DG);
 *
 */

package org.afree.chart.plot.dial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.afree.io.SerialUtilities;
import org.afree.graphics.geom.ArcShape;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.PathShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * A base class for the pointer in a {@link DialPlot}.
 *
 * @since JFreeChart 1.0.7
 */
public abstract class DialPointer extends AbstractDialLayer
        implements DialLayer, Cloneable, /*PublicCloneable,*/ Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2483972458546357143L;

    /** The needle radius. */
    double radius;

    /**
     * The dataset index for the needle.
     */
    int datasetIndex;

    /**
     * Creates a new <code>DialPointer</code> instance.
     */
    protected DialPointer() {
        this(0);
    }

    /**
     * Creates a new pointer for the specified dataset.
     *
     * @param datasetIndex  the dataset index.
     */
    protected DialPointer(int datasetIndex) {
        this.radius = 0.9;
        this.datasetIndex = datasetIndex;
    }

    /**
     * Returns the dataset index that the pointer maps to.
     *
     * @return The dataset index.
     *
     * @see #getDatasetIndex()
     */
    public int getDatasetIndex() {
        return this.datasetIndex;
    }

    /**
     * Sets the dataset index for the pointer and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param index  the index.
     *
     * @see #getDatasetIndex()
     */
    public void setDatasetIndex(int index) {
        this.datasetIndex = index;
        notifyListeners(new DialLayerChangeEvent(this));
    }

    /**
     * Returns the radius of the pointer, as a percentage of the dial's
     * framing RectShape.
     *
     * @return The radius.
     *
     * @see #setRadius(double)
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Sets the radius of the pointer and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param radius  the radius.
     *
     * @see #getRadius()
     */
    public void setRadius(double radius) {
        this.radius = radius;
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
     * Checks this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> not permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DialPointer)) {
            return false;
        }
        DialPointer that = (DialPointer) obj;
        if (this.datasetIndex != that.datasetIndex) {
            return false;
        }
        if (this.radius != that.radius) {
            return false;
        }
        return super.equals(obj);
    }

//    /**
//     * Returns a hash code.
//     *
//     * @return A hash code.
//     */
//    public int hashCode() {
//        int result = 23;
//        result = HashUtilities.hashCode(result, this.radius);
//        return result;
//    }

    /**
     * Returns a clone of the pointer.
     *
     * @return a clone.
     *
     * @throws CloneNotSupportedException if one of the attributes cannot
     *     be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * A dial pointer that draws a thin line (like a pin).
     */
    public static class Pin extends DialPointer {

        /** For serialization. */
        static final long serialVersionUID = -8445860485367689750L;

        /** The paint. */
        private transient Paint paint;

        /** The stroke. */
        private transient Float stroke;

        /**
         * Creates a new instance.
         */
        public Pin() {
            this(0);
        }

        /**
         * Creates a new instance.
         *
         * @param datasetIndex  the dataset index.
         */
        public Pin(int datasetIndex) {
            super(datasetIndex);
            //this.paint = Color.red;
            this.paint = new Paint();
            paint.setColor(Color.RED);
//            this.stroke = new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
//                    BasicStroke.JOIN_BEVEL);
            this.stroke = 3.0f;
        }

        /**
         * Returns the paint.
         *
         * @return The paint (never <code>null</code>).
         *
         * @see #setPaint(Paint)
         */
        public Paint getPaint() {
            return this.paint;
        }

        /**
         * Sets the paint and sends a {@link DialLayerChangeEvent} to all
         * registered listeners.
         *
         * @param paint  the paint (<code>null</code> not permitted).
         *
         * @see #getPaint()
         */
        public void setPaint(Paint paint) {
            if (paint == null) {
                throw new IllegalArgumentException("Null 'paint' argument.");
            }
            this.paint = paint;
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
         * Sets the stroke and sends a {@link DialLayerChangeEvent} to all
         * registered listeners.
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
         * Draws the pointer.
         *
         * @param canvas  the graphics target.
         * @param plot  the plot.
         * @param frame  the dial's reference frame.
         * @param view  the dial's view.
         */
        public void draw(Canvas canvas, DialPlot plot, RectShape frame,
            Shape view) {
            
            paint.setStrokeWidth(stroke);
            RectShape arcRect = DialPlot.RectShapeByRadius(frame,
                    this.radius, this.radius);

            double value = plot.getValue(this.datasetIndex);
            DialScale scale = plot.getScaleForDataset(this.datasetIndex);
            double angle = scale.valueToAngle(value);

            ArcShape arc = new ArcShape(arcRect, angle, 0, true);
            PointF pt = arc.getEndPoint();

            LineShape line = new LineShape(frame.getCenterX(),
                    frame.getCenterY(), pt.x, pt.y);
            line.draw(canvas, paint);
        }

        /**
         * Tests this pointer for equality with an arbitrary object.
         *
         * @param obj  the object (<code>null</code> permitted).
         *
         * @return A boolean.
         */
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DialPointer.Pin)) {
                return false;
            }
            DialPointer.Pin that = (DialPointer.Pin) obj;
//            if (!PaintUtilities.equal(this.paint, that.paint)) {
//                return false;
//            }
            if (!this.stroke.equals(that.stroke)) {
                return false;
            }
            return super.equals(obj);
        }

//        /**
//         * Returns a hash code for this instance.
//         *
//         * @return A hash code.
//         */
//        public int hashCode() {
//            int result = super.hashCode();
//            result = HashUtilities.hashCode(result, this.paint);
//            result = HashUtilities.hashCode(result, this.stroke);
//            return result;
//        }
//
//        /**
//         * Provides serialization support.
//         *
//         * @param stream  the output stream.
//         *
//         * @throws IOException  if there is an I/O error.
//         */
//        private void writeObject(ObjectOutputStream stream) throws IOException {
//            stream.defaultWriteObject();
//            SerialUtilities.writePaint(this.paint, stream);
//            SerialUtilities.writeStroke(this.stroke, stream);
//        }

//        /**
//         * Provides serialization support.
//         *
//         * @param stream  the input stream.
//         *
//         * @throws IOException  if there is an I/O error.
//         * @throws ClassNotFoundException  if there is a classpath problem.
//         */
//        private void readObject(ObjectInputStream stream)
//                throws IOException, ClassNotFoundException {
//            stream.defaultReadObject();
//            this.paint = SerialUtilities.readPaint(stream);
//            this.stroke = SerialUtilities.readStroke(stream);
//        }

    }

    /**
     * A dial pointer.
     */
    public static class Pointer extends DialPointer {

        /** For serialization. */
        static final long serialVersionUID = -4180500011963176960L;

        /**
         * The radius that defines the width of the pointer at the base.
         */
        private double widthRadius;

        /**
         * The fill paint.
         *
         * @since JFreeChart 1.0.8
         */
        private transient PaintType fillPaintType;

        /**
         * The outline paint.
         *
         * @since JFreeChart 1.0.8
         */
        private transient PaintType outlinePaintType;

        /**
         * Creates a new instance.
         */
        public Pointer() {
            this(0);
        }

        /**
         * Creates a new instance.
         *
         * @param datasetIndex  the dataset index.
         */
        public Pointer(int datasetIndex) {
            super(datasetIndex);
            this.widthRadius = 0.05;
            this.fillPaintType = new SolidColor(Color.GRAY);
            this.outlinePaintType = new SolidColor(Color.BLACK);
        }

        /**
         * Returns the width radius.
         *
         * @return The width radius.
         *
         * @see #setWidthRadius(double)
         */
        public double getWidthRadius() {
            return this.widthRadius;
        }

        /**
         * Sets the width radius and sends a {@link DialLayerChangeEvent} to
         * all registered listeners.
         *
         * @param radius  the radius
         *
         * @see #getWidthRadius()
         */
        public void setWidthRadius(double radius) {
            this.widthRadius = radius;
            notifyListeners(new DialLayerChangeEvent(this));
        }

        /**
         * Returns the fill paint.
         *
         * @return The paint type (never <code>null</code>).
         *
         * @see #setFillPaintType(PaintType paintType)
         *
         * @since JFreeChart 1.0.8
         */
        public PaintType getFillPaintType() {
            return this.fillPaintType;
        }

        /**
         * Sets the fill paint and sends a {@link DialLayerChangeEvent} to all
         * registered listeners.
         *
         * @param paintType  the paint (<code>null</code> not permitted).
         *
         * @see #getFillPaintType()
         *
         * @since JFreeChart 1.0.8
         */
        public void setFillPaintType(PaintType paintType) {
            if (paintType == null) {
                throw new IllegalArgumentException("Null 'paint' argument.");
            }
            this.fillPaintType = paintType;
            notifyListeners(new DialLayerChangeEvent(this));
        }

        /**
         * Returns the outline paint.
         *
         * @return The paint type (never <code>null</code>).
         *
         * @see #setOutlinePaintType(PaintType paintType)
         *
         * @since JFreeChart 1.0.8
         */
        public PaintType getOutlinePaintType() {
            return this.outlinePaintType;
        }

        /**
         * Sets the outline paint and sends a {@link DialLayerChangeEvent} to
         * all registered listeners.
         *
         * @param paintType  the paint (<code>null</code> not permitted).
         *
         * @see #getOutlinePaintType()
         *
         * @since JFreeChart 1.0.8
         */
        public void setOutlinePaintType(PaintType paintType) {
            if (paintType == null) {
                throw new IllegalArgumentException("Null 'paint' argument.");
            }
            this.outlinePaintType = paintType;
            notifyListeners(new DialLayerChangeEvent(this));
        }

        /**
         * Draws the pointer.
         *
         * @param canvas  the graphics target.
         * @param plot  the plot.
         * @param frame  the dial's reference frame.
         * @param view  the dial's view.
         */
        public void draw(Canvas canvas, DialPlot plot, RectShape frame,
                Shape view) {
            
            //canvas.setPaint(Color.blue);
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            //canvas.setStroke(new BasicStroke(1.0f));
            paint.setStrokeWidth(1.0f);
            RectShape lengthRect = DialPlot.RectShapeByRadius(frame,
                    this.radius, this.radius);
            RectShape widthRect = DialPlot.RectShapeByRadius(frame,
                    this.widthRadius, this.widthRadius);
            double value = plot.getValue(this.datasetIndex);
            DialScale scale = plot.getScaleForDataset(this.datasetIndex);
            double angle = scale.valueToAngle(value);

            ArcShape arc1 = new ArcShape(lengthRect, angle, 0, true);
            PointF pt1 = arc1.getEndPoint();
            ArcShape arc2 = new ArcShape(widthRect, angle - 90.0, 180.0,
                    true);
            PointF pt2 = arc2.getStartPoint();
            PointF pt3 = arc2.getEndPoint();
            ArcShape arc3 = new ArcShape(widthRect, angle - 180.0, 0.0,
                    true);
            PointF pt4 = arc3.getStartPoint();

            PathShape gp = new PathShape();
            gp.moveTo((float) pt1.x, (float) pt1.y);
            gp.lineTo((float) pt2.x, (float) pt2.y);
            gp.lineTo((float) pt4.x, (float) pt4.y);
            gp.lineTo((float) pt3.x, (float) pt3.y);
            gp.closePath();
            
            Paint fillPaint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    fillPaintType);
            gp.draw(canvas, fillPaint);

            LineShape line = new LineShape(frame.getCenterX(),
                    frame.getCenterY(), pt1.x, pt1.y);
            
            Paint outlinePaint = PaintUtility.createPaint(
                    outlinePaintType);
            line.draw(canvas, outlinePaint);

            line.setLine(pt2, pt3);
            line.draw(canvas, outlinePaint);

            line.setLine(pt3, pt1);
            line.draw(canvas, outlinePaint);

            line.setLine(pt2, pt1);
            line.draw(canvas, outlinePaint);

            line.setLine(pt2, pt4);
            line.draw(canvas, outlinePaint);

            line.setLine(pt3, pt4);
            line.draw(canvas, outlinePaint);
        }

        /**
         * Tests this pointer for equality with an arbitrary object.
         *
         * @param obj  the object (<code>null</code> permitted).
         *
         * @return A boolean.
         */
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DialPointer.Pointer)) {
                return false;
            }
            DialPointer.Pointer that = (DialPointer.Pointer) obj;

            if (this.widthRadius != that.widthRadius) {
                return false;
            }
//            if (!PaintUtilities.equal(this.fillPaint, that.fillPaint)) {
//                return false;
//            }
//            if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
//                return false;
//            }
            return super.equals(obj);
        }

//        /**
//         * Returns a hash code for this instance.
//         *
//         * @return A hash code.
//         */
//        public int hashCode() {
//            int result = super.hashCode();
//            result = HashUtilities.hashCode(result, this.widthRadius);
//            result = HashUtilities.hashCode(result, this.fillPaint);
//            result = HashUtilities.hashCode(result, this.outlinePaint);
//            return result;
//        }

        /**
         * Provides serialization support.
         *
         * @param stream  the output stream.
         *
         * @throws IOException  if there is an I/O error.
         */
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            SerialUtilities.writePaintType(this.fillPaintType, stream);
            SerialUtilities.writePaintType(this.outlinePaintType, stream);
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
            this.fillPaintType = SerialUtilities.readPaintType(stream);
            this.outlinePaintType = SerialUtilities.readPaintType(stream);
        }

    }

}
