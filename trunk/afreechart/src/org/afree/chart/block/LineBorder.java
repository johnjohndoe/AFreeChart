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
 * ---------------
 * LineBorder.java
 * ---------------
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
 * (C) Copyright 2007, 2008, by Christo Zietsman and Contributors.
 *
 * Original Author:  Christo Zietsman;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 16-Mar-2007 : Version 1, contributed by Christo Zietsman with
 *               modifications by DG (DG);
 * 13-Jun-2007 : Don't draw if area doesn't have positive dimensions (DG);
 *
 */

package org.afree.chart.block;

import java.io.Serializable;

import org.afree.ui.RectangleInsets;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;

/**
 * A line border for any {@link AbstractBlock}.
 * 
 * @since JFreeChart 1.0.5
 */
public class LineBorder implements BlockFrame, Serializable {

    /** For serialization. */
    static final long serialVersionUID = 4630356736707233924L;

    /** The line color. */
    private transient PaintType paintType;

    /** The line stroke. */
    private transient float stroke;
    
    /** The line effect. */
    private transient PathEffect effect;

    /** The insets. */
    private RectangleInsets insets;

    /**
     * Creates a default border.
     */
    public LineBorder() {
        this(Color.BLACK, 1.0f, null, new RectangleInsets(1.0, 1.0, 1.0, 1.0));
    }

    /**
     * Creates a new border with the specified color.
     * 
     * @param paint
     *            the color (<code>null</code> not permitted).
     * @param stroke
     *            the border stroke (<code>null</code> not permitted).
     * @param effect
     *            the border effect (<code>null</code> not permitted).
     * @param insets
     *            the insets (<code>null</code> not permitted).
     */
    public LineBorder(int paint, float stroke, PathEffect effect, RectangleInsets insets) {

        if (insets == null) {
            throw new IllegalArgumentException("Null 'insets' argument.");
        }

        this.paintType = new SolidColor(paint);
        this.stroke = stroke;
        this.effect = effect;
        this.insets = insets;
    }

    /**
     * Returns the paint.
     * 
     * @return The paint type (never <code>null</code>).
     */
    public PaintType getPaintType() {
        return this.paintType;
    }

    /**
     * Returns the insets.
     * 
     * @return The insets (never <code>null</code>).
     */
    public RectangleInsets getInsets() {
        return this.insets;
    }

    /**
     * Returns the stroke.
     * 
     * @return The stroke (never <code>null</code>).
     */
    public float getStroke() {
        return this.stroke;
    }
    
    /**
     * Returns the effect.
     * 
     * @return The effect (never <code>null</code>).
     */
    public PathEffect getEffect() {
        return this.effect;
    }

    /**
     * Draws the border by filling in the reserved space (in black).
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area.
     */
    public void draw(Canvas canvas, RectShape area) {
        double w = area.getWidth();
        double h = area.getHeight();
        // if the area has zero height or width, we shouldn't draw anything
        if (w <= 0.0 || h <= 0.0) {
            return;
        }
        double t = this.insets.calculateTopInset(h);
        double b = this.insets.calculateBottomInset(h);
        double l = this.insets.calculateLeftInset(w);
        double r = this.insets.calculateRightInset(w);
        double x = area.getX();
        double y = area.getY();
        double x0 = x + l / 2.0;
        double x1 = x + w - r / 2.0;
        double y0 = y + h - b / 2.0;
        double y1 = y + t / 2.0;
        
        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getPaintType(), getStroke(), getEffect());

        LineShape line = new LineShape();
        if (t > 0.0) {
            line.setLine(x0, y1, x1, y1);
            canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                    (float) line.getX2(), (float) line.getY2(), paint);
            // canvas.draw(line);
        }
        if (b > 0.0) {
            line.setLine(x0, y0, x1, y0);
            canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                    (float) line.getX2(), (float) line.getY2(), paint);
        }
        if (l > 0.0) {
            line.setLine(x0, y0, x0, y1);
            canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                    (float) line.getX2(), (float) line.getY2(), paint);
        }
        if (r > 0.0) {
            line.setLine(x1, y0, x1, y1);
            canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                    (float) line.getX2(), (float) line.getY2(), paint);
        }
    }

}
