/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
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
 * ------------
 * TextBox.java
 * ------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 *
 * Changes
 * -------
 * 09-Mar-2004 : Version 1 (DG);
 * 22-Mar-2004 : Added equals() method and implemented Serializable (DG);
 * 09-Nov-2004 : Renamed getAdjustedHeight() --> calculateExtendedHeight() in 
 *               Spacer class (DG);
 * 22-Feb-2005 : Replaced Spacer with RectangleInsets (DG);
 *
 */

package org.afree.chart.text;

import java.io.Serializable;

import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleInsets;
import org.afree.ui.Size2D;
import org.afree.util.ObjectUtilities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * A box containing a text block.
 * 
 * @author David Gilbert
 */
public class TextBox implements Serializable {

    private static final PaintType BLACK = new SolidColor(Color.BLACK);

    private static final PaintType GRAY = new SolidColor(Color.GRAY);

    private static final PaintType WHITE = new SolidColor(Color.WHITE);

    /** For serialization. */
    private static final long serialVersionUID = 3360220213180203706L;

    /** The outline paint. */
    private transient PaintType outlinePaintType;

    /** The outline stroke. */
    private transient Float outlineStroke;
    
    /** The interior space. */
    private RectangleInsets interiorGap;

    /** The background paint. */
    private transient PaintType backgroundPaint;

    /** The shadow paint. */
    private transient PaintType shadowPaintType;

    /** The shadow x-offset. */
    private double shadowXOffset = 2.0;

    /** The shadow y-offset. */
    private double shadowYOffset = 2.0;

    /** The text block. */
    private TextBlock textBlock;

    /**
     * Creates an empty text box.
     */
    public TextBox() {
        this((TextBlock) null);
    }

    /**
     * Creates a text box.
     * 
     * @param text
     *            the text.
     */
    public TextBox(final String text) {
        this((TextBlock) null);
        if (text != null) {
            this.textBlock = new TextBlock();
            this.textBlock.addLine(text, new Font("SansSerif", Typeface.NORMAL,
                    10), BLACK);
        }
    }

    /**
     * Creates a new text box.
     * 
     * @param block
     *            the text block.
     */
    public TextBox(final TextBlock block) {
        this.outlinePaintType = BLACK;
        this.outlineStroke = 1.0f;
        this.interiorGap = new RectangleInsets(1.0, 3.0, 1.0, 3.0);
        this.backgroundPaint = WHITE;
        this.shadowPaintType = GRAY;
        this.shadowXOffset = 2.0;
        this.shadowYOffset = 2.0;
        this.textBlock = block;
    }

    /**
     * Returns the outline paint.
     * 
     * @return The outline paint.
     */
    public PaintType getOutlinePaintType() {
        return this.outlinePaintType;
    }

    /**
     * Sets the outline paint.
     * 
     * @param paintType
     *            the paint.
     */
    public void setOutlinePaintType(final PaintType paintType) {
        this.outlinePaintType = paintType;
    }

    /**
     * Returns the outline stroke.
     * 
     * @return The outline stroke.
     */
    public Float getOutlineStroke() {
        return this.outlineStroke;
    }

    /**
     * Sets the outline stroke.
     * 
     * @param stroke
     *            the stroke.
     */
    public void setOutlineStroke(final Float stroke) {
        this.outlineStroke = stroke;
    }

    /**
     * Returns the interior gap.
     * 
     * @return The interior gap.
     */
    public RectangleInsets getInteriorGap() {
        return this.interiorGap;
    }

    /**
     * Sets the interior gap.
     * 
     * @param gap
     *            the gap.
     */
    public void setInteriorGap(final RectangleInsets gap) {
        this.interiorGap = gap;
    }

    /**
     * Returns the background paint.
     * 
     * @return The background paint.
     */
    public PaintType getBackgroundPaintType() {
        return this.backgroundPaint;
    }

    /**
     * Sets the background paint.
     * 
     * @param paintType
     *            the paint.
     */
    public void setBackgroundPaintType(final PaintType paintType) {
        this.backgroundPaint = paintType;
    }

    /**
     * Returns the shadow paint.
     * 
     * @return The shadow paint.
     */
    public PaintType getShadowPaintType() {
        return this.shadowPaintType;
    }

    /**
     * Sets the shadow paint.
     * 
     * @param paintType
     *            the paint.
     */
    public void setShadowPaintType(final PaintType paintType) {
        this.shadowPaintType = paintType;
    }

    /**
     * Returns the x-offset for the shadow effect.
     * 
     * @return The offset.
     */
    public double getShadowXOffset() {
        return this.shadowXOffset;
    }

    /**
     * Sets the x-offset for the shadow effect.
     * 
     * @param offset
     *            the offset (in Canvas units).
     */
    public void setShadowXOffset(final double offset) {
        this.shadowXOffset = offset;
    }

    /**
     * Returns the y-offset for the shadow effect.
     * 
     * @return The offset.
     */
    public double getShadowYOffset() {
        return this.shadowYOffset;
    }

    /**
     * Sets the y-offset for the shadow effect.
     * 
     * @param offset
     *            the offset (in Canvas units).
     */
    public void setShadowYOffset(final double offset) {
        this.shadowYOffset = offset;
    }

    /**
     * Returns the text block.
     * 
     * @return The text block.
     */
    public TextBlock getTextBlock() {
        return this.textBlock;
    }

    /**
     * Sets the text block.
     * 
     * @param block
     *            the block.
     */
    public void setTextBlock(final TextBlock block) {
        this.textBlock = block;
    }

    /**
     * Draws the text box.
     * 
     * @param canvas
     *            the graphics device.
     * @param x
     *            the x-coordinate.
     * @param y
     *            the y-coordinate.
     * @param anchor
     *            the anchor point.
     */
    public void draw(final Canvas canvas, final float x, final float y,
            final RectangleAnchor anchor) {
        final Size2D d1 = this.textBlock.calculateDimensions(canvas);
        final double w = this.interiorGap.extendWidth(d1.getWidth());
        final double h = this.interiorGap.extendHeight(d1.getHeight());
        
        final Size2D d2 = new Size2D(w, h);
        final RectShape bounds = RectangleAnchor.createRectShape(d2, x, y,
                anchor);

        double xx = bounds.getX();
        double yy = bounds.getY();
        
        Paint paint;
        if (this.shadowPaintType != null) {
            paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, shadowPaintType);
            final RectShape shadow = new RectShape(xx + this.shadowXOffset, yy + this.shadowYOffset,
                    bounds.getWidth(), bounds.getHeight());
            shadow.fill(canvas, paint);

        }
        if (this.backgroundPaint != null) {
            paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, backgroundPaint);
            bounds.fill(canvas, paint);
        }

        if (this.outlinePaintType != null && this.outlineStroke != null) {
            paint = PaintUtility.createPaint(outlinePaintType);
            bounds.draw(canvas, paint);
        }

        this.textBlock.draw(canvas,
                (float) (xx + this.interiorGap.calculateLeftInset(w)),
                (float) (yy + this.interiorGap.calculateTopInset(h)),
                TextBlockAnchor.TOP_LEFT);

    }

    /**
     * Returns the height of the text box.
     * 
     * @param canvas
     *            the graphics device.
     * 
     * @return The height (in Canvas units).
     */
    public double getHeight(final Canvas canvas) {
        final Size2D d = this.textBlock.calculateDimensions(canvas);
        return this.interiorGap.extendHeight(d.getHeight());
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the object to test against (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextBox)) {
            return false;
        }
        final TextBox that = (TextBox) obj;
        if (!ObjectUtilities.equal(this.outlinePaintType, that.outlinePaintType)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.interiorGap, that.interiorGap)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.backgroundPaint,
                that.backgroundPaint)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.shadowPaintType, that.shadowPaintType)) {
            return false;
        }
        if (this.shadowXOffset != that.shadowXOffset) {
            return false;
        }
        if (this.shadowYOffset != that.shadowYOffset) {
            return false;
        }
        if (!ObjectUtilities.equal(this.textBlock, that.textBlock)) {
            return false;
        }

        return true;
    }    

}
