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
 * -------------------------
 * GradientXYBarPainter.java
 * -------------------------
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
 * (C) Copyright 2008, 2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 19-Jun-2008 : Version 1 (DG);
 * 22-Feb-2009 : Fixed bug drawing outlines (DG);
 *
 */

package org.afree.chart.renderer.xy;

import java.io.Serializable;

import org.afree.ui.RectangleEdge;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.GradientColor;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;

/**
 * An implementation of the {@link XYBarPainter} interface that uses several
 * gradient fills to enrich the appearance of the bars.
 *
 * @since JFreeChart 1.0.11
 */
public class GradientXYBarPainter implements XYBarPainter, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8663121191441373564L;

    /** The division point between the first and second gradient regions. */
    protected double g1;

    /** The division point between the second and third gradient regions. */
    protected double canvas;

    /** The division point between the third and fourth gradient regions. */
    protected double g3;

    /**
     * Creates a new instance.
     */
    public GradientXYBarPainter() {
        this(0.10, 0.20, 0.80);
    }

    /**
     * Creates a new instance.
     *
     * @param g1
     * @param canvas
     * @param g3
     */
    public GradientXYBarPainter(double g1, double canvas, double g3) {
        this.g1 = g1;
        this.canvas = canvas;
        this.g3 = g3;
    }

    /**
     * Paints a single bar instance.
     *
     * @param canvas  the graphics target.
     * @param renderer  the renderer.
     * @param row  the row index.
     * @param column  the column index.
     * @param bar  the bar
     * @param base  indicates which side of the RectShape is the base of the
     *              bar.
     */
    public void paintBar(Canvas canvas, XYBarRenderer renderer, int row, int column,
            RectShape bar, RectangleEdge base) {

        PaintType itemPaintType = renderer.getItemPaintType(row, column);

        int c0 = 0;
        int c1 = 0;

        if (itemPaintType instanceof SolidColor) {
            c0 = ((SolidColor)itemPaintType).getColor();
            c1 = Color.rgb(Math.min(255, Color.red(c0) + 64), Math.min(255, Color.green(c0) + 64), Math.min(255, Color.blue(c0) + 64));
        } else if (itemPaintType instanceof GradientColor) {
            GradientColor gc = (GradientColor)itemPaintType;
            c0 = gc.getColor1();
            c1 = gc.getColor2();
        } else {
            throw new IllegalArgumentException("Not support PaintType");
        }

        // as a special case, if the bar colour has alpha == 0, we draw
        // nothing.
        if (itemPaintType.getAlpha() == 0) {
            return;
        }

        
        if (base == RectangleEdge.TOP || base == RectangleEdge.BOTTOM) {
            RectShape[] regions = splitVerticalBar(bar, this.g1, this.canvas,
                    this.g3);
            int color[] = new int[2];
            color[0] = c0;
            color[1] = Color.WHITE;
            
            GradientDrawable grad = new GradientDrawable(
                    Orientation.LEFT_RIGHT, color);
            grad.setSize((int) regions[0].getWidth(), (int) regions[0]
                    .getHeight());
            grad.setBounds((int) regions[0].getMinX(), (int) regions[0]
                    .getMinY(), (int) regions[0].getMaxX(), (int) regions[0]
                    .getMaxY());
            grad.draw(canvas);

            color[0] = Color.WHITE;
            color[1] = c0;

            grad = new GradientDrawable(Orientation.LEFT_RIGHT, color);
            grad.setSize((int) regions[1].getWidth(), (int) regions[1]
                    .getHeight());
            grad.setBounds((int) regions[1].getMinX(), (int) regions[1]
                    .getMinY(), (int) regions[1].getMaxX(), (int) regions[1]
                    .getMaxY());
            grad.draw(canvas);

            color[0] = c0;
            color[1] = c1;

            grad = new GradientDrawable(Orientation.LEFT_RIGHT, color);
            grad.setSize((int) regions[2].getWidth(), (int) regions[2]
                    .getHeight());
            grad.setBounds((int) regions[2].getMinX(), (int) regions[2]
                    .getMinY(), (int) regions[2].getMaxX(), (int) regions[2]
                    .getMaxY());
            grad.draw(canvas);

            color[0] = c1;
            color[1] = c0;

            grad = new GradientDrawable(Orientation.LEFT_RIGHT, color);
            grad.setSize((int) regions[3].getWidth(), (int) regions[3]
                    .getHeight());
            grad.setBounds((int) regions[3].getMinX(), (int) regions[3]
                    .getMinY(), (int) regions[3].getMaxX(), (int) regions[3]
                    .getMaxY());
            grad.draw(canvas);

        }
        else if (base == RectangleEdge.LEFT || base == RectangleEdge.RIGHT) {
            RectShape[] regions = splitHorizontalBar(bar, this.g1, this.canvas,
                    this.g3);
            int color[] = new int[2];
            color[0] = c0;
            color[1] = Color.WHITE;
            
           GradientDrawable grad = new GradientDrawable(
                    Orientation.TOP_BOTTOM, color);
            grad.setSize((int) regions[0].getWidth(), (int) regions[0]
                    .getHeight());
            grad.setBounds((int) regions[0].getMinX(), (int) regions[0]
                    .getMinY(), (int) regions[0].getMaxX(), (int) regions[0]
                    .getMaxY());
            grad.draw(canvas);

            color[0] = Color.WHITE;
            color[1] = c0;

            grad = new GradientDrawable(Orientation.TOP_BOTTOM, color);
            grad.setSize((int) regions[1].getWidth(), (int) regions[1]
                    .getHeight());
            grad.setBounds((int) regions[1].getMinX(), (int) regions[1]
                    .getMinY(), (int) regions[1].getMaxX(), (int) regions[1]
                    .getMaxY());
            grad.draw(canvas);

            color[0] = c0;
            color[1] = c1;

            grad = new GradientDrawable(Orientation.TOP_BOTTOM, color);
            grad.setSize((int) regions[2].getWidth(), (int) regions[2]
                    .getHeight());
            grad.setBounds((int) regions[2].getMinX(), (int) regions[2]
                    .getMinY(), (int) regions[2].getMaxX(), (int) regions[2]
                    .getMaxY());
            grad.draw(canvas);

            color[0] = c1;
            color[1] = c0;

            grad = new GradientDrawable(Orientation.TOP_BOTTOM, color);
            grad.setSize((int) regions[3].getWidth(), (int) regions[3]
                    .getHeight());
            grad.setBounds((int) regions[3].getMinX(), (int) regions[3]
                    .getMinY(), (int) regions[3].getMaxX(), (int) regions[3]
                    .getMaxY());
            grad.draw(canvas);


        }

        // draw the outline...
        if (renderer.isDrawBarOutline()) {
            //Stroke stroke = renderer.getItemOutlineStroke(row, column);
            float stroke = renderer.getItemOutlineStroke(row, column);

            PaintType paintType = renderer.getItemOutlinePaintType(row, column);

            if (stroke != 0.0f && paintType != null) {
                Paint paint = PaintUtility.createPaint(paintType, 
                        stroke, 
                        renderer.getItemOutlineEffect(row, column));
                canvas.drawRect((float) bar.getMinX(), (float) bar.getMinY(),
                        (float) bar.getMaxX(), (float) bar.getMaxY(), paint);
            }
        }

    }


    /**
     * Paints a single bar instance.
     *
     * @param canvas  the graphics target.
     * @param renderer  the renderer.
     * @param row  the row index.
     * @param column  the column index.
     * @param bar  the bar
     * @param base  indicates which side of the RectShape is the base of the
     *              bar.
     * @param pegShadow  peg the shadow to the base of the bar?
     */
    public void paintBarShadow(Canvas canvas, XYBarRenderer renderer, int row,
            int column, RectShape bar, RectangleEdge base,
            boolean pegShadow) {

        // handle a special case - if the bar colour has alpha == 0, it is
        // invisible so we shouldn't draw any shadow
        PaintType itemPaint = renderer.getItemPaintType(row, column);
        /*
        if (itemPaint instanceof Color) {
            Color c = (Color) itemPaint;
            if (c.getAlpha() == 0) {
                return;
            }
        }
        */
        if (itemPaint.getAlpha() == 0) {

            return;
        }

        /*
        RectShape shadow = createShadow(bar, renderer.getShadowXOffset(),
                renderer.getShadowYOffset(), base, pegShadow);
        canvas.setPaint(Color.gray);
        canvas.fill(shadow);
        */
        RectShape shadow = createShadow(bar,
                renderer.getShadowXOffset(), renderer.getShadowYOffset(), base,
                pegShadow);

        Paint paint = PaintUtility.createPaint(
                Paint.ANTI_ALIAS_FLAG,
                renderer.getShadowPaintType());
        shadow.fill(canvas, paint);

    }

    /**
     * Creates a shadow for the bar.
     *
     * @param bar  the bar shape.
     * @param xOffset  the x-offset for the shadow.
     * @param yOffset  the y-offset for the shadow.
     * @param base  the edge that is the base of the bar.
     * @param pegShadow  peg the shadow to the base?
     *
     * @return A RectShape for the shadow.
     */
    private RectShape createShadow(RectShape bar, double xOffset,
            double yOffset, RectangleEdge base, boolean pegShadow) {
        double x0 = bar.getMinX();
        double x1 = bar.getMaxX();
        double y0 = bar.getMinY();
        double y1 = bar.getMaxY();
        if (base == RectangleEdge.TOP) {
            x0 += xOffset;
            x1 += xOffset;
            if (!pegShadow) {
                y0 += yOffset;
            }
            y1 += yOffset;
        }
        else if (base == RectangleEdge.BOTTOM) {
            x0 += xOffset;
            x1 += xOffset;
            y0 += yOffset;
            if (!pegShadow) {
                y1 += yOffset;
            }
        }
        else if (base == RectangleEdge.LEFT) {
            if (!pegShadow) {
                x0 += xOffset;
            }
            x1 += xOffset;
            y0 += yOffset;
            y1 += yOffset;
        }
        else if (base == RectangleEdge.RIGHT) {
            x0 += xOffset;
            if (!pegShadow) {
                x1 += xOffset;
            }
            y0 += yOffset;
            y1 += yOffset;
        }
        return new RectShape(x0, y0, (x1 - x0), (y1 - y0));
    }

    /**
     * Splits a bar into subregions (elsewhere, these subregions will have
     * different gradients applied to them).
     *
     * @param bar  the bar shape.
     * @param a  the first division.
     * @param b  the second division.
     * @param c  the third division.
     *
     * @return An array containing four subregions.
     */
    protected RectShape[] splitVerticalBar(RectShape bar, double a,
            double b, double c) {
        RectShape[] result = new RectShape[4];
        double x0 = bar.getMinX();
        double x1 = Math.rint(x0 + (bar.getWidth() * a));
        double x2 = Math.rint(x0 + (bar.getWidth() * b));
        double x3 = Math.rint(x0 + (bar.getWidth() * c));
        result[0] = new RectShape(bar.getMinX(), bar.getMinY(),
                x1 - x0, bar.getHeight());
        result[1] = new RectShape(x1, bar.getMinY(), x2 - x1,
                bar.getHeight());
        result[2] = new RectShape(x2, bar.getMinY(), x3 - x2,
                bar.getHeight());
        result[3] = new RectShape(x3, bar.getMinY(),
                bar.getMaxX() - x3, bar.getHeight());
        return result;
    }

    /**
     * Splits a bar into subregions (elsewhere, these subregions will have
     * different gradients applied to them).
     *
     * @param bar  the bar shape.
     * @param a  the first division.
     * @param b  the second division.
     * @param c  the third division.
     *
     * @return An array containing four subregions.
     */
    protected RectShape[] splitHorizontalBar(RectShape bar, double a,
            double b, double c) {
        RectShape[] result = new RectShape[4];
        double y0 = bar.getMinY();
        double y1 = Math.rint(y0 + (bar.getHeight() * a));
        double y2 = Math.rint(y0 + (bar.getHeight() * b));
        double y3 = Math.rint(y0 + (bar.getHeight() * c));
        result[0] = new RectShape(bar.getMinX(), bar.getMinY(),
                bar.getWidth(), y1 - y0);
        result[1] = new RectShape(bar.getMinX(), y1, bar.getWidth(),
                y2 - y1);
        result[2] = new RectShape(bar.getMinX(), y2, bar.getWidth(),
                y3 - y2);
        result[3] = new RectShape(bar.getMinX(), y3, bar.getWidth(),
                bar.getMaxY() - y3);
        return result;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the obj (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GradientXYBarPainter)) {
            return false;
        }
        GradientXYBarPainter that = (GradientXYBarPainter) obj;
        if (this.g1 != that.g1) {
            return false;
        }
        if (this.canvas != that.canvas) {
            return false;
        }
        if (this.g3 != that.g3) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
//    public int hashCode() {
//        int hash = 37;
//        hash = HashUtilities.hashCode(hash, this.g1);
//        hash = HashUtilities.hashCode(hash, this.canvas);
//        hash = HashUtilities.hashCode(hash, this.g3);
//        return hash;
//    }

}
