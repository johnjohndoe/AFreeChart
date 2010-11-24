/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 * 
 * Project Info:
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *    AFreeChart: http://code.google.com/p/afreechart/
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Android is a trademark of Google Inc.]
 * 
 * -----------------
 * TextFragment.java
 * -----------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2003, 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 *
 * Changes
 * -------
 * 07-Nov-2003 : Version 1 (DG);
 * 25-Nov-2003 : Fixed bug in the dimension calculation (DG);
 * 22-Dec-2003 : Added workaround for Java bug 4245442 (DG);
 * 29-Jan-2004 : Added paint attribute (DG);
 * 22-Mar-2004 : Added equals() method and implemented Serializable (DG);
 * 01-Apr-2004 : Changed java.awt.geom.Dimension2D to org.jfree.ui.Size2D because of 
 *               JDK bug 4976448 which persists on JDK 1.3.1 (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 */

package org.afree.chart.text;

import java.io.Serializable;

import org.afree.ui.RefineryUtilities;
import org.afree.ui.Size2D;
import org.afree.ui.TextAnchor;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.FontMetrics;

/**
 * A text item, with an associated font, that fits on a single line (see
 * {@link TextLine}). Instances of the class are immutable.
 */
public class TextFragment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** The default font. */
    public static final Font DEFAULT_FONT = new Font("Serif", Typeface.BOLD, 12);

    /** The default text color. */
    public static final PaintType DEFAULT_PAINT = new SolidColor(Color.argb(0, 0, 0, 0));

    /** The text. */
    private String text;

    /** The font. */
    private Font font;

    /** The text color. */
    private PaintType paintType;

    /**
     * Creates a new text fragment.
     * 
     * @param text
     *            the text (<code>null</code> not permitted).
     */
    public TextFragment(final String text) {
        this(text, DEFAULT_FONT, DEFAULT_PAINT);
    }

    /**
     * Creates a new text fragment.
     * 
     * @param text
     *            the text (<code>null</code> not permitted).
     * @param font
     *            the font (<code>null</code> not permitted).
     */
    public TextFragment(final String text, final Font font) {
        this(text, font, DEFAULT_PAINT);
    }

    /**
     * Creates a new text fragment.
     * 
     * @param text
     *            the text (<code>null</code> not permitted).
     * @param font
     *            the font (<code>null</code> not permitted).
     * @param paintType
     *            the text color (<code>null</code> not permitted).
     */
    public TextFragment(final String text, final Font font, final PaintType paintType) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");
        }
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.text = text;
        this.font = font;
        this.paintType = paintType;
    }

    /**
     * Returns the text.
     * 
     * @return The text (possibly <code>null</code>).
     */
    public String getText() {
        return this.text;
    }

    /**
     * Returns the font.
     * 
     * @return The font (never <code>null</code>).
     */
    public Font getFont() {
        return this.font;
    }

    /**
     * Returns the text paint.
     * 
     * @return The text paint (never <code>null</code>).
     */
    public PaintType getPaintType() {
        return this.paintType;
    }

    /**
     * Draws the text fragment.
     * 
     * @param canvas
     *            the graphics device.
     * @param anchorX
     *            the x-coordinate of the anchor point.
     * @param anchorY
     *            the y-coordinate of the anchor point.
     * @param anchor
     *            the location of the text that is aligned to the anchor point.
     * @param rotateX
     *            the x-coordinate of the rotation point.
     * @param rotateY
     *            the y-coordinate of the rotation point.
     * @param angle
     *            the angle.
     */
    public void draw(final Canvas canvas, final float anchorX, final float anchorY,
            final TextAnchor anchor, final float rotateX, final float rotateY,
            final double angle) {

        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, paintType, font);
        RefineryUtilities.drawRotatedString(this.text, canvas,
                anchorX, anchorY, anchor,
                rotateX, rotateY,
                paint, (float)angle);

    }

    /**
     * Calculates the dimensions of the text fragment.
     * 
     * @param canvas
     *            the graphics device.
     * 
     * @return The width and height of the text.
     */
    public Size2D calculateDimensions(final Canvas canvas) {
        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, paintType, font);
        RectShape rec = TextUtilities.getTextBounds(text, paint);
        return new Size2D(rec.getWidth(), rec.getHeight());
    }

    /**
     * Calculates the vertical offset between the baseline and the specified
     * text anchor.
     * 
     * @param canvas
     *            the graphics device.
     * @param anchor
     *            the anchor.
     * 
     * @return the offset.
     */
    public float calculateBaselineOffset(final TextAnchor anchor) {
        float result = 0.0f;
        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, paintType, font);
        final FontMetrics fm = paint.getFontMetrics();
        if (anchor == TextAnchor.TOP_LEFT || anchor == TextAnchor.TOP_CENTER
                                          || anchor == TextAnchor.TOP_RIGHT) {
            result = Math.abs(fm.ascent);
        }
        else if (anchor == TextAnchor.BOTTOM_LEFT 
                || anchor == TextAnchor.BOTTOM_CENTER
                || anchor == TextAnchor.BOTTOM_RIGHT) {
            result = -Math.abs(fm.descent) - Math.abs(fm.leading);
        }
        return result;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj
     *            the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextFragment) {
            final TextFragment tf = (TextFragment) obj;
            if (!this.text.equals(tf.text)) {
                return false;
            }
            if (!this.font.equals(tf.font)) {
                return false;
            }
            if (!this.paintType.equals(tf.paintType)) {
                return false;
            }
            return true;
        }
        return false;
    }

}
