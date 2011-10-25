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
 * BlockBorder.java
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
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 04-Feb-2005 : Added equals() and implemented Serializable (DG);
 * 23-Feb-2005 : Added attribute for border color (DG);
 * 06-May-2005 : Added new convenience constructors (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 16-Mar-2007 : Implemented BlockFrame (DG);
 *
 */

package org.afree.chart.block;

import java.io.Serializable;

import org.afree.ui.RectangleInsets;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * A border for a block. This class is immutable.
 */
public class BlockBorder implements BlockFrame, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 4961579220410228283L;

    /** An empty border. */

    public static final PaintType white = new SolidColor(Color.argb(255, 256, 256, 256));

    public static final PaintType black = new SolidColor(Color.argb(255, 0, 0, 0));

    public static final BlockBorder NONE = new BlockBorder(
            RectangleInsets.ZERO_INSETS, white);

    /** The space reserved for the border. */
    private RectangleInsets insets;

    /** The border color. */
    private transient PaintType paintType;

    /**
     * Creates a default border.
     */
    public BlockBorder() {
        this(black);
    }

    /**
     * Creates a new border with the specified color.
     * 
     * @param paintType
     *            the color (<code>null</code> not permitted).
     */
    public BlockBorder(PaintType paintType) {
        this(new RectangleInsets(1, 1, 1, 1), paintType);
    }

    /**
     * Creates a new border with the specified line widths (in black).
     * 
     * @param top
     *            the width of the top border.
     * @param left
     *            the width of the left border.
     * @param bottom
     *            the width of the bottom border.
     * @param right
     *            the width of the right border.
     */
    public BlockBorder(double top, double left, double bottom, double right) {
        this(new RectangleInsets(top, left, bottom, right), black);
    }

    /**
     * Creates a new border with the specified line widths (in black).
     * 
     * @param top
     *            the width of the top border.
     * @param left
     *            the width of the left border.
     * @param bottom
     *            the width of the bottom border.
     * @param right
     *            the width of the right border.
     * @param paintType
     *            the border paint (<code>null</code> not permitted).
     */
    public BlockBorder(double top, double left, double bottom, double right,
            PaintType paintType) {
        this(new RectangleInsets(top, left, bottom, right), paintType);
    }

    /**
     * Creates a new border.
     * 
     * @param insets
     *            the border insets (<code>null</code> not permitted).
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     */
    public BlockBorder(RectangleInsets insets, PaintType paintType) {
        if (insets == null) {
            throw new IllegalArgumentException("Null 'insets' argument.");
        }
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.insets = insets;
        this.paintType = paintType;
    }

    /**
     * Returns the space reserved for the border.
     * 
     * @return The space (never <code>null</code>).
     */
    public RectangleInsets getInsets() {
        return this.insets;
    }

    /**
     * Returns the paint used to draw the border.
     * 
     * @return The paint type (never <code>null</code>).
     */
    public PaintType getPaintType() {
        return this.paintType;
    }

    /**
     * Draws the border by filling in the reserved space.
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area.
     */
    public void draw(Canvas canvas, RectShape area) {
        // this default implementation will just fill the available
        // border space with a single color
        double t = this.insets.calculateTopInset(area.getHeight());
        double b = this.insets.calculateBottomInset(area.getHeight());
        double l = this.insets.calculateLeftInset(area.getWidth());
        double r = this.insets.calculateRightInset(area.getWidth());
        double x = area.getX();
        double y = area.getY();
        double w = area.getWidth();
        double h = area.getHeight();

        RectShape rect = new RectShape();
        
        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, this.paintType);
        paint.setStyle(Style.FILL);
        if (t > 0.0) {
            rect.setRect(x, y, w, t);
            canvas.drawRect((float) rect.getMinX(), (float) rect.getMinY(),
                    (float) rect.getMaxX(), (float) rect.getMaxY(), paint);
        }
        if (b > 0.0) {
            rect.setRect(x, y + h - b, w, b);
            canvas.drawRect((float) rect.getMinX(), (float) rect.getMinY(),
                    (float) rect.getMaxX(), (float) rect.getMaxY(), paint);
        }
        if (l > 0.0) {
            rect.setRect(x, y, l, h);
            canvas.drawRect((float) rect.getMinX(), (float) rect.getMinY(),
                    (float) rect.getMaxX(), (float) rect.getMaxY(), paint);
        }
        if (r > 0.0) {
            rect.setRect(x + w - r, y, r, h);
            canvas.drawRect((float) rect.getMinX(), (float) rect.getMinY(),
                    (float) rect.getMaxX(), (float) rect.getMaxY(), paint);
        }
    }

}
