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
 * ---------------------------
 * DefaultDrawingSupplier.java
 * ---------------------------
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
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Jeremy Bowman;
 *
 * Changes
 * -------
 * 16-Jan-2003 : Version 1 (DG);
 * 17-Jan-2003 : Added stroke method, renamed DefaultPaintSupplier
 *               --> DefaultDrawingSupplier (DG)
 * 27-Jan-2003 : Incorporated code from SeriesShapeFactory, originally
 *               contributed by Jeremy Bowman (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Jun-2007 : Added fillPaintSequence (DG);
 *
 */

package org.afree.chart.plot;

import java.io.Serializable;
import java.util.Arrays;


import org.afree.util.PublicCloneable;
import org.afree.util.ShapeUtilities;
import org.afree.chart.ChartColor;
import org.afree.graphics.geom.OvalShape;
import org.afree.graphics.geom.Polygon;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;

import android.graphics.Color;
import android.graphics.PathEffect;

/**
 * A default implementation of the {@link DrawingSupplier} interface. All
 * {@link Plot} instances have a new instance of this class installed by
 * default.
 */
public class DefaultDrawingSupplier implements DrawingSupplier, Cloneable,
        PublicCloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -7339847061039422538L;

    /** The default fill paint sequence. */
    public static final PaintType[] DEFAULT_PAINT_TYPE_SEQUENCE = ChartColor
            .createDefaultPaintArray();
    
    /** The default effect sequence. */
    public static final PathEffect[] DEFAULT_PATH_EFFECT_SEQUENCE = new PathEffect[] { null };

    public static final PaintType white = new SolidColor(Color.WHITE);

    public static final PaintType ltGray = new SolidColor(Color.LTGRAY);

    /** The default outline paint sequence. */
    public static final PaintType[] DEFAULT_OUTLINE_PAINT_TYPE_SEQUENCE = new PaintType[] { ltGray };
    
    /** The default outline effect sequence. */
    public static final PathEffect[] DEFAULT_OUTLINE_EFFECT_SEQUENCE = new PathEffect[] { null };

    /** The default fill paint sequence. */
    public static final PaintType[] DEFAULT_FILL_PAINT_TYPE_SEQUENCE = new PaintType[] { white };

    /** The default stroke sequence. */
//    public static final float[] DEFAULT_STROKE_SEQUENCE = new float[] { 5.f };
    public static final float[] DEFAULT_STROKE_SEQUENCE = new float[] { 1.0F };


    /** The default outline stroke sequence. */
    public static final float[] DEFAULT_OUTLINE_STROKE_SEQUENCE = new float[] { 1.0f };

    /** The default shape sequence. */
    public static final Shape[] DEFAULT_SHAPE_SEQUENCE = createStandardSeriesShapes();

    /** The paint sequence. */
    private transient PaintType[] paintTypeSequence;

    /** The current paint index. */
    private int paintTypeIndex;
    
    /** The effect sequence. */
    private transient PathEffect[] pathEffectSequence;

    /** The current effect index. */
    private int pathEffectIndex;

    /** The outline paint sequence. */
    private transient PaintType[] outlinePaintTypeSequence;

    /** The current outline paint index. */
    private int outlinePaintTypeIndex;
    
    /** The outline effect sequence. */
    private transient PathEffect[] outlineEffectSequence;

    /** The current outline effect index. */
    private int outlineEffectIndex;

    /** The fill paint sequence. */
    private transient PaintType[] fillPaintTypeSequence;

    /** The current fill paint index. */
    private int fillPaintTypeIndex;

    /** The stroke sequence. */
    private transient float[] strokeSequence;

    /** The current stroke index. */
    private int strokeIndex;

    /** The outline stroke sequence. */
    private transient float[] outlineStrokeSequence;

    /** The current outline stroke index. */
    private int outlineStrokeIndex;

    /** The shape sequence. */
    private transient Shape[] shapeSequence;

    /** The current shape index. */
    private int shapeIndex;

    /**
     * Creates a new supplier, with default sequences for fill paint, outline
     * paint, stroke and shapes.
     */
    public DefaultDrawingSupplier() {

        this(DEFAULT_PAINT_TYPE_SEQUENCE, DEFAULT_FILL_PAINT_TYPE_SEQUENCE,
                DEFAULT_OUTLINE_PAINT_TYPE_SEQUENCE, DEFAULT_STROKE_SEQUENCE, DEFAULT_PATH_EFFECT_SEQUENCE,
                DEFAULT_OUTLINE_STROKE_SEQUENCE, DEFAULT_OUTLINE_EFFECT_SEQUENCE, 
                DEFAULT_SHAPE_SEQUENCE);

    }

    /**
     * Creates a new supplier.
     * 
     * @param paintSequence
     *            the fill paint sequence.
     * @param outlinePaintSequence
     *            the outline paint sequence.
     * @param strokeSequence
     *            the stroke sequence.
     * @param pathEffectSequence
     *            the effect sequence.
     * @param outlineStrokeSequence
     *            the outline stroke sequence.
     * @param outlineEffectSequence
     *            the outline effect sequence.
     * @param shapeSequence
     *            the shape sequence.
     */
    public DefaultDrawingSupplier(PaintType[] paintSequence,
            PaintType[] outlinePaintSequence, float[] strokeSequence, PathEffect[] pathEffectSequence,
            float[] outlineStrokeSequence, PathEffect[] outlineEffectSequence, Shape[] shapeSequence) {

        this.paintTypeSequence = paintSequence;
        this.fillPaintTypeSequence = DEFAULT_FILL_PAINT_TYPE_SEQUENCE;
        this.outlinePaintTypeSequence = outlinePaintSequence;
        this.strokeSequence = strokeSequence;
        this.pathEffectSequence = pathEffectSequence;
        this.outlineStrokeSequence = outlineStrokeSequence;
        this.outlineEffectSequence = outlineEffectSequence;
        this.shapeSequence = shapeSequence;

    }

    /**
     * Creates a new supplier.
     * 
     * @param paintSequence
     *            the paint sequence.
     * @param fillPaintSequence
     *            the fill paint sequence.
     * @param outlinePaintSequence
     *            the outline paint sequence.
     * @param strokeSequence
     *            the stroke sequence.
     * @param pathEffectSequence
     *            the effect sequence.
     * @param outlineStrokeSequence
     *            the outline stroke sequence.
     * @param outlineEffectSequence
     *            the outline effect sequence.
     * @param shapeSequence
     *            the shape sequence.
     * 
     * @since JFreeChart 1.0.6
     */
    public DefaultDrawingSupplier(PaintType[] paintSequence,
            PaintType[] fillPaintSequence, PaintType[] outlinePaintSequence,
            float[] strokeSequence, PathEffect[] pathEffectSequence, float[] outlineStrokeSequence,
            PathEffect[] outlineEffectSequence, Shape[] shapeSequence) {

        this.paintTypeSequence = paintSequence;
        this.fillPaintTypeSequence = fillPaintSequence;
        this.outlinePaintTypeSequence = outlinePaintSequence;
        this.strokeSequence = strokeSequence;
        this.pathEffectSequence = pathEffectSequence;
        this.outlineStrokeSequence = outlineStrokeSequence;
        this.outlineEffectSequence = outlineEffectSequence;
        this.shapeSequence = shapeSequence;
    }

    /**
     * Returns the next paint in the sequence.
     * 
     * @return The paint type.
     */
    public PaintType getNextPaintType() {
        PaintType result = this.paintTypeSequence[this.paintTypeIndex
                % this.paintTypeSequence.length];
        this.paintTypeIndex++;
        return result;
    }

    /**
     * Returns the next effect in the sequence.
     * 
     * @return The effect.
     */
    public PathEffect getNextEffect() {
        PathEffect result = this.pathEffectSequence[this.pathEffectIndex
                % this.pathEffectSequence.length];
                  this.pathEffectIndex++;
        return result;
    }

    /**
     * Returns the next outline paint in the sequence.
     * 
     * @return The paint type.
     */
    public PaintType getNextOutlinePaintType() {
        PaintType result = this.outlinePaintTypeSequence[this.outlinePaintTypeIndex
                % this.outlinePaintTypeSequence.length];
        this.outlinePaintTypeIndex++;
        return result;
    }
    

    /**
     * Returns the next fill paint in the sequence.
     * 
     * @return The paint type.
     * 
     * @since JFreeChart 1.0.6
     */
    public PaintType getNextFillPaintType() {
        PaintType result = this.fillPaintTypeSequence[this.fillPaintTypeIndex
                % this.fillPaintTypeSequence.length];
        this.fillPaintTypeIndex++;
        return result;
    }

    /**
     * Returns the next stroke in the sequence.
     * 
     * @return The stroke.
     */
    public float getNextStroke() {
        float result = this.strokeSequence[this.strokeIndex
                % this.strokeSequence.length];
        this.strokeIndex++;
        return result;
    }

    /**
     * Returns the next outline stroke in the sequence.
     * 
     * @return The stroke.
     */
    public float getNextOutlineStroke() {
        float result = this.outlineStrokeSequence[this.outlineStrokeIndex
                % this.outlineStrokeSequence.length];
        this.outlineStrokeIndex++;
        return result;
    }

    /**
     * Returns the next outline effect in the sequence.
     * 
     * @return The effect.
     */
    public PathEffect getNextOutlineEffect() {
        PathEffect result = this.outlineEffectSequence[this.outlineEffectIndex
                % this.outlineEffectSequence.length];
                  this.outlineEffectIndex++;
        return result;
    }   

    /**
     * Returns the next shape in the sequence.
     * 
     * @return The shape.
     */
    public Shape getNextShape() {
        Shape result = this.shapeSequence[this.shapeIndex
                % this.shapeSequence.length];
        this.shapeIndex++;
        return result;
    }

    /**
     * Creates an array of standard shapes to display for the items in series on
     * charts.
     * 
     * @return The array of shapes.
     */
    public static Shape[] createStandardSeriesShapes() {

        Shape[] result = new Shape[10];

        double size = 6.0;
        double delta = size / 2.0;
        int[] xpoints = null;
        int[] ypoints = null;

        // square
        result[0] = new RectShape(-delta, -delta, size, size);
        // circle
        result[1] = new OvalShape(-delta, -delta, size, size);

        // up-pointing triangle
        xpoints = intArray(0.0, delta, -delta);
        ypoints = intArray(-delta, delta, delta);
        result[2] = new Polygon(xpoints, ypoints, 3);

        // diamond
        xpoints = intArray(0.0, delta, 0.0, -delta);
        ypoints = intArray(-delta, 0.0, delta, 0.0);
        result[3] = new Polygon(xpoints, ypoints, 4);

        // horizontal RectShape
        result[4] = new RectShape(-delta, -delta / 2, size, size / 2);

        // down-pointing triangle
        xpoints = intArray(-delta, +delta, 0.0);
        ypoints = intArray(-delta, -delta, delta);
        result[5] = new Polygon(xpoints, ypoints, 3);

        // horizontal ellipse
        result[6] = new OvalShape(-delta, -delta / 2, size, size / 2);

        // right-pointing triangle
        xpoints = intArray(-delta, delta, -delta);
        ypoints = intArray(-delta, 0.0, delta);
        result[7] = new Polygon(xpoints, ypoints, 3);

        // vertical RectShape
        result[8] = new RectShape(-delta / 2, -delta, size / 2, size);

        // left-pointing triangle
        xpoints = intArray(-delta, delta, delta);
        ypoints = intArray(0.0, -delta, +delta);
        result[9] = new Polygon(xpoints, ypoints, 3);

        return result;

    }
    
    /**
     * Tests this object for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof DefaultDrawingSupplier)) {
            return false;
        }

        DefaultDrawingSupplier that = (DefaultDrawingSupplier) obj;

        if (!Arrays.equals(this.paintTypeSequence, that.paintTypeSequence)) {
            return false;
        }
        if (this.paintTypeIndex != that.paintTypeIndex) {
            return false;
        }
        if (!Arrays.equals(this.outlinePaintTypeSequence,
                that.outlinePaintTypeSequence)) {
            return false;
        }
        if (this.outlinePaintTypeIndex != that.outlinePaintTypeIndex) {
            return false;
        }
        if (!Arrays.equals(this.strokeSequence, that.strokeSequence)) {
            return false;
        }
        if (this.strokeIndex != that.strokeIndex) {
            return false;
        }
        if (!Arrays.equals(this.outlineStrokeSequence,
                that.outlineStrokeSequence)) {
            return false;
        }
        if (this.outlineStrokeIndex != that.outlineStrokeIndex) {
            return false;
        }
        if (!equalShapes(this.shapeSequence, that.shapeSequence)) {
            return false;
        }
        if (this.shapeIndex != that.shapeIndex) {
            return false;
        }
        return true;

    }

    /**

        /**
     * A utility method for testing the equality of two arrays of shapes.
     *
     * @param s1  the first array (<code>null</code> permitted).
     * @param s2  the second array (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    private boolean equalShapes(Shape[] s1, Shape[] s2) {
        if (s1 == null) {
            return s2 == null;
        }
        if (s2 == null) {
            return false;
        }
        if (s1.length != s2.length) {
            return false;
        }
        for (int i = 0; i < s1.length; i++) {
            if (!ShapeUtilities.equal(s1[i], s2[i])) {
                return false;
            }
        }
        return true;
    }

    
    /**
     * Helper method to avoid lots of explicit casts in getShape(). Returns an
     * array containing the provided doubles cast to ints.
     * 
     * @param a
     *            x
     * @param b
     *            y
     * @param c
     *            z
     * 
     * @return int[3] with converted params.
     */
    private static int[] intArray(double a, double b, double c) {
        return new int[] { (int) a, (int) b, (int) c };
    }

    /**
     * Helper method to avoid lots of explicit casts in getShape(). Returns an
     * array containing the provided doubles cast to ints.
     * 
     * @param a
     *            x
     * @param b
     *            y
     * @param c
     *            z
     * @param d
     *            t
     * 
     * @return int[4] with converted params.
     */
    private static int[] intArray(double a, double b, double c, double d) {
        return new int[] { (int) a, (int) b, (int) c, (int) d };
    }

    /**
     * Returns a clone.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if a component of the supplier does
     *                                    not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
        DefaultDrawingSupplier clone = (DefaultDrawingSupplier) super.clone();
        return clone;
    }
    
}
