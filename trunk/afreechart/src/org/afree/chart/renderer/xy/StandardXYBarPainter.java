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
 * -------------------------
 * StandardXYBarPainter.java
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
 * (C) Copyright 2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 19-Jun-2008 : Version 1 (DG);
 *
 */

package org.afree.chart.renderer.xy;

import java.io.Serializable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import org.afree.ui.GradientShaderFactory;
import org.afree.ui.RectangleEdge;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.GradientColor;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;


/**
 * An implementation of the {@link XYBarPainter} interface that preserves the
 * behaviour of bar painting that existed prior to the introduction of the
 * {@link XYBarPainter} interface.
 * 
 * @see GradientXYBarPainter
 * 
 * @since JFreeChart 1.0.11
 */
public class StandardXYBarPainter implements XYBarPainter, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4208691485399813341L;

    /**
     * Creates a new instance.
     */
    public StandardXYBarPainter() {
    }

    /**
     * Paints a single bar instance.
     * 
     * @param canvas
     *            the graphics target.
     * @param renderer
     *            the renderer.
     * @param row
     *            the row index.
     * @param column
     *            the column index.
     * @param bar
     *            the bar
     * @param base
     *            indicates which side of the rectangle is the base of the bar.
     */
    public void paintBar(Canvas canvas, XYBarRenderer renderer, int row, int column, RectShape bar,
            RectangleEdge base) {

        PaintType itemPaintType = renderer.getItemPaintType(row, column);
        Paint itemPaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, itemPaintType);

        if (itemPaintType instanceof GradientColor) {
            GradientShaderFactory t = renderer.getGradientShaderFactory();
            Shader shader = t.create((GradientColor) itemPaintType, bar);
            itemPaint.setShader(shader);
        }


        canvas.drawRect((float) bar.getMinX(), (float) bar.getMinY(), (float) bar.getMaxX(),
                (float) bar.getMaxY(), itemPaint);

        // draw the outline...
        if (renderer.isDrawBarOutline()) {
            // && state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) {
            float stroke = renderer.getItemOutlineStroke(row, column);
            PaintType paintType = renderer.getItemOutlinePaintType(row, column);

            if (stroke != 0.0f && paintType != null) {
                Paint outlinePaint = PaintUtility.createPaint(
                        Paint.ANTI_ALIAS_FLAG,
                        paintType,
                        stroke,
                        renderer.getItemOutlineEffect(row, column));
                canvas.drawRect((float) bar.getMinX(), (float) bar.getMinY(), (float) bar.getMaxX(),
                        (float) bar.getMaxY(), outlinePaint);
            }
        }

    }

    /**
     * Paints a single bar instance.
     * 
     * @param canvas
     *            the graphics target.
     * @param renderer
     *            the renderer.
     * @param row
     *            the row index.
     * @param column
     *            the column index.
     * @param bar
     *            the bar
     * @param base
     *            indicates which side of the rectangle is the base of the bar.
     * @param pegShadow
     *            peg the shadow to the base of the bar?
     */
    public void paintBarShadow(Canvas canvas, XYBarRenderer renderer, int row, int column,
            RectShape bar, RectangleEdge base, boolean pegShadow) {

        // handle a special case - if the bar colour has alpha == 0, it is
        // invisible so we shouldn't draw any shadow
        PaintType itemPaintType = renderer.getItemPaintType(row, column);

        if (itemPaintType.getAlpha() == 0) {
            return;
        }

        RectShape shadow = createShadow(bar, renderer.getShadowXOffset(), renderer
                .getShadowYOffset(), base, pegShadow);
        
        Paint paint = PaintUtility.createPaint(
                Paint.ANTI_ALIAS_FLAG,
                renderer.getShadowPaintType());
        
        shadow.fill(canvas, paint);
        
    }

    /**
     * Creates a shadow for the bar.
     * 
     * @param bar
     *            the bar shape.
     * @param xOffset
     *            the x-offset for the shadow.
     * @param yOffset
     *            the y-offset for the shadow.
     * @param base
     *            the edge that is the base of the bar.
     * @param pegShadow
     *            peg the shadow to the base?
     * 
     * @return A rectangle for the shadow.
     */
    private RectShape createShadow(RectShape bar, double xOffset, double yOffset,
            RectangleEdge base, boolean pegShadow) {
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
        } else if (base == RectangleEdge.BOTTOM) {
            x0 += xOffset;
            x1 += xOffset;
            y0 += yOffset;
            if (!pegShadow) {
                y1 += yOffset;
            }
        } else if (base == RectangleEdge.LEFT) {
            if (!pegShadow) {
                x0 += xOffset;
            }
            x1 += xOffset;
            y0 += yOffset;
            y1 += yOffset;
        } else if (base == RectangleEdge.RIGHT) {
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
     * @param obj
     *            the obj (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardXYBarPainter)) {
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
