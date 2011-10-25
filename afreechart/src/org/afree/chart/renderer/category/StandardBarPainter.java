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
 * -----------------------
 * StandardBarPainter.java
 * -----------------------
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
 * (C) Copyright 2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 19-Jun-2008 : Version 1 (DG);
 * 15-Aug-2008 : Use renderer's shadow paint (DG);
 *
 */

package org.afree.chart.renderer.category;

import java.io.Serializable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import org.afree.ui.RectangleEdge;
import org.afree.chart.renderer.category.BarPainter;
import org.afree.chart.renderer.category.BarRenderer;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;


/**
 * An implementation of the {@link BarPainter} interface that preserves the
 * behaviour of bar painting that existed prior to the introduction of the
 * {@link BarPainter} interface.
 *
 * @see GradientBarPainter
 *
 * @since JFreeChart 1.0.11
 */
public class StandardBarPainter implements BarPainter, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1706184054280631384L;

    /**
     * Creates a new instance.
     */
    public StandardBarPainter() {
    }

    /**
     * Paints a single bar instance.
     *
     * @param canvas  the graphics target.
     * @param renderer  the renderer.
     * @param row  the row index.
     * @param column  the column index.
     * @param bar  the bar
     * @param base  indicates which side of the rectangle is the base of the
     *              bar.
     */
    public void paintBar(Canvas canvas, BarRenderer renderer, int row,
            int column, RectShape bar, RectangleEdge base) {

        PaintType itemPaintType = renderer.getItemPaintType(row, column);
//        GradientPaintTransformer t = renderer.getGradientPaintTransformer();
//        if (t != null && itemPaint instanceof GradientPaint) {
//            itemPaint = t.transform((GradientPaint) itemPaint, bar);
//        }
//        canvas.setPaint(itemPaint);
//        canvas.fill(bar);
        
        Paint paint = PaintUtility.createPaint(
                Paint.ANTI_ALIAS_FLAG, 
                itemPaintType);
        paint.setStyle(Style.FILL);
        canvas.drawRect((float) bar.getMinX(), (float) bar.getMinY(),
                (float) bar.getMaxX(), (float) bar.getMaxY(), paint);

        // draw the outline...
        if (renderer.isDrawBarOutline()) {
               // && state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) {
            float stroke = renderer.getItemOutlineStroke(row, column);
            PaintType paintType = renderer.getItemOutlinePaintType(row, column);
            if (stroke != 0.0f && paintType != null) {
//                canvas.setStroke(stroke);
//                canvas.setPaint(paint);
//                canvas.draw(bar);
                paint = PaintUtility.createPaint(
                        paintType, 
                        stroke, 
                        renderer.getItemOutlineEffect(row, column));
                paint.setStyle(Paint.Style.STROKE);
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
     * @param base  indicates which side of the rectangle is the base of the
     *              bar.
     * @param pegShadow  peg the shadow to the base of the bar?
     */
    public void paintBarShadow(Canvas canvas, BarRenderer renderer, int row,
            int column, RectShape bar, RectangleEdge base,
            boolean pegShadow) {

        // handle a special case - if the bar colour has alpha == 0, it is
        // invisible so we shouldn't draw any shadow
        PaintType itemPaintType = renderer.getItemPaintType(row, column);
//        if (itemPaint instanceof Color) {
//            Color c = (Color) itemPaint;
//            if (c.getAlpha() == 0) {
//                return;
//            }
//        }
        if (itemPaintType.getAlpha() == 0) {
            return;
        }

//        RectangularShape shadow = createShadow(bar, renderer.getShadowXOffset(),
//                renderer.getShadowYOffset(), base, pegShadow);
//        canvas.setPaint(renderer.getShadowPaint());
//        canvas.fill(shadow);
        
        RectShape shadow = createShadow(bar, renderer.getShadowXOffset(),
                renderer.getShadowYOffset(), base, pegShadow);
        PaintType p = renderer.getShadowPaintType();
        Paint paint = PaintUtility.createPaint(
                Paint.ANTI_ALIAS_FLAG, 
                p);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect((float) shadow.getMinX(), (float) shadow.getMinY(),
                (float) shadow.getMaxX(), (float) shadow.getMaxY(), paint);

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
     * @return A rectangle for the shadow.
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
        if (!(obj instanceof StandardBarPainter)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int hash = 37;
        // no fields to compute...
        return hash;
    }

}
