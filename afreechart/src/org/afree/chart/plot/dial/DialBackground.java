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
 * -------------------
 * DialBackground.java
 * -------------------
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
 * 16-Oct-2007 : The equals() method needs to call super.equals() (DG);
 *
 */

package org.afree.chart.plot.dial;

import java.io.Serializable;

import org.afree.ui.GradientShaderFactory;
import org.afree.ui.StandardGradientShaderFactory;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * A regular dial layer that can be used to draw the background for a dial.
 *
 * @since JFreeChart 1.0.7
 */
public class DialBackground extends AbstractDialLayer implements DialLayer,
        Cloneable, /*PublicCloneable,*/ Serializable {

    /** For serialization. */
    static final long serialVersionUID = -9019069533317612375L;

    /**
     * The background paint.  This field is transient because serialization
     * requires special handling.
     */
    private transient Paint paint;

    /**
     * The transformer used when the background paint is an instance of
     * <code>GradientPaint</code>.
     */
    private GradientShaderFactory gradientShaderFactory;

    /**
     * Creates a new instance of <code>DialBackground</code>.  The
     * default background paint is <code>Color.white</code>.
     */
    public DialBackground() {
        //this(Color.white);
        this(new Paint());
    }

    /**
     * Creates a new instance of <code>DialBackground</code>.  The
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @throws IllegalArgumentException if <code>paint</code> is
     *     <code>null</code>.
     */
    public DialBackground(Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.paint = paint;
        this.gradientShaderFactory = new StandardGradientShaderFactory();
    }

    /**
     * Returns the paint used to fill the background.
     *
     * @return The paint (never <code>null</code>).
     *
     * @see #setPaint(Paint)
     */
    public Paint getPaint() {
        return this.paint;
    }

    /**
     * Sets the paint for the dial background and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
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
     * Returns the transformer used to adjust the coordinates of any
     * <code>GradientPaint</code> instance used for the background paint.
     *
     * @return The transformer (never <code>null</code>).
     *
     * @see #setGradientShaderFactory(GradientShaderFactory)
     */
    public GradientShaderFactory getGradientShaderFactory() {
        return this.gradientShaderFactory;
    }

    /**
     * Sets the transformer used to adjust the coordinates of any
     * <code>GradientPaint</code> instance used for the background paint, and
     * sends a {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param factory  the transformer (<code>null</code> not permitted).
     *
     * @see #getGradientShaderFactory()
     */
    public void setGradientShaderFactory(GradientShaderFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Null 't' argument.");
        }
        this.gradientShaderFactory = factory;
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
     * Draws the background to the specified graphics device.  If the dial
     * frame specifies a window, the clipping region will already have been
     * set to this window before this method is called.
     *
     * @param canvas  the graphics device (<code>null</code> not permitted).
     * @param plot  the plot (ignored here).
     * @param frame  the dial frame (ignored here).
     * @param view  the view RectShape (<code>null</code> not permitted).
     */
    public void draw(Canvas canvas, DialPlot plot, RectShape frame,
            Shape view) {
        canvas.save();
//        canvas.clipPath(view.getPath());
        Paint p = this.paint;
//        if (p instanceof GradientPaint) {
//            p = this.gradientPaintTransformer.transform((GradientPaint) p,
//                    view);
//        }
        
        frame.fill(canvas, p);
        canvas.restore();
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
        if (!(obj instanceof DialBackground)) {
            return false;
        }
        DialBackground that = (DialBackground) obj;
//        if (!PaintUtilities.equal(this.paint, that.paint)) {
//            return false;
//        }
        if (!this.gradientShaderFactory.equals(
                that.gradientShaderFactory)) {
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
//        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
//        result = 37 * result + this.gradientPaintTransformer.hashCode();
//        return result;
//    }

    /**
     * Returns a clone of this instance.
     *
     * @return The clone.
     *
     * @throws CloneNotSupportedException if some attribute of this instance
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
//        SerialUtilities.writePaint(this.paint, stream);
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
//        this.paint = SerialUtilities.readPaint(stream);
//    }

}
