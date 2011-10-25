/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
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
 * ------------------
 * TextUtilities.java
 * ------------------
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
 * 15-Dec-2010 : performance tuning
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
 * 07-Jan-2004 : Version 1 (DG);
 * 24-Mar-2004 : Added 'paint' argument to createTextBlock() method (DG);
 * 07-Apr-2004 : Added getTextBounds() method and useFontMetricsGetStringBounds flag (DG);
 * 08-Apr-2004 : Changed word break iterator to line break iterator in the createTextBlock()
 *               method - see bug report 926074 (DG);
 *
 */

package org.afree.chart.text;


import java.text.BreakIterator;

import org.afree.ui.TextAnchor;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.PathShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;

/**
 * Some utility methods for working with text.
 */
public abstract class TextUtilities {

    /**
     * Creates a new text block from the given string.
     * 
     * @param text
     *            the text.
     * @param font
     *            the font.
     * @param paintType
     *            the paint.
     * @param maxWidth
     *            the maximum width for each line.
     * @param measurer
     *            the text measurer.
     * 
     * @return A text block.
     */
    public static TextBlock createTextBlock(final String text, final Font font,
            final PaintType paintType, final float maxWidth, final TextMeasurer measurer) {
        return createTextBlock(text, font, paintType, maxWidth, Integer.MAX_VALUE,
                measurer);
    }

    /**
     * Creates a {@link TextBlock} from a <code>String</code>. Line breaks are
     * added where the <code>String</code> contains '\n' characters.
     * 
     * @param text
     *            the text.
     * @param font
     *            the font.
     * @param paintType
     *            the paint.
     * 
     * @return A text block.
     */
    public static TextBlock createTextBlock(final String text, final Font font,
            final PaintType paintType) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");
        }
        final TextBlock result = new TextBlock();
        String input = text;
        boolean moreInputToProcess = (text.length() > 0);
        final int start = 0;
        while (moreInputToProcess) {
            final int index = input.indexOf("\n");
            if (index > start) {
                final String line = input.substring(start, index);
                if (index < input.length() - 1) {
                    result.addLine(line, font, paintType);
                    input = input.substring(index + 1);
                } else {
                    moreInputToProcess = false;
                }
            } else if (index == start) {
                if (index < input.length() - 1) {
                    input = input.substring(index + 1);
                } else {
                    moreInputToProcess = false;
                }
            } else {
                result.addLine(input, font, paintType);
                moreInputToProcess = false;
            }
        }
        return result;
    }

    /**
     * Creates a new text block from the given string.
     * 
     * @param text
     *            the text.
     * @param font
     *            the font.
     * @param paintType
     *            the paint.
     * @param maxWidth
     *            the maximum width for each line.
     * @param maxLines
     *            the maximum number of lines.
     * @param measurer
     *            the text measurer.
     * 
     * @return A text block.
     */
    public static TextBlock createTextBlock(final String text, final Font font,
            final PaintType paintType, final float maxWidth, final int maxLines,
            final TextMeasurer measurer) {
        final TextBlock result = new TextBlock();
        final BreakIterator iterator = BreakIterator.getLineInstance();
        iterator.setText(text);
        int current = 0;
        int lines = 0;
        final int length = text.length();
        while (current < length && lines < maxLines) {
            final int next = nextLineBreak(text, current, maxWidth, iterator,
                    measurer);
            if (next == BreakIterator.DONE) {
                result.addLine(text.substring(current), font, paintType);
                return result;
            }
            result.addLine(text.substring(current, next), font, paintType);
            lines++;
            current = next;
        }
        return result;
    }

    /**
     * Returns the character index of the next line break.
     * 
     * @param text
     *            the text.
     * @param start
     *            the start index.
     * @param width
     *            the end index.
     * @param iterator
     *            the word break iterator.
     * @param measurer
     *            the text measurer.
     * 
     * @return The index of the next line break.
     */
    private static int nextLineBreak(final String text, final int start,
            final float width, final BreakIterator iterator,
            final TextMeasurer measurer) {
        // this method is (loosely) based on code in JFreeReport's TextParagraph
        // class
        int current = start;
        int end;
        float x = 0.0f;
        boolean firstWord = true;
        while (((end = iterator.next()) != BreakIterator.DONE)) {
            x += measurer.getStringWidth(text, current, end);
            if (x > width) {
                if (firstWord) {
                    while (measurer.getStringWidth(text, start, end) > width) {
                        end--;
                    }
                    // iterator.setPosition(end);
                    return end;
                } else {
                    end = iterator.previous();
                    return end;
                }
            }
            // we found at least one word that fits ...
            firstWord = false;
            current = end;
        }
        return BreakIterator.DONE;
    }

    /**
     * Returns the bounds for the specified text.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     * @param paint
     *            the font metrics (not <code>null</code>).
     * 
     * @return The text bounds (possibly <code>null</code>).
     */
    public static RectShape getTextBounds(final String text, final Paint paint) {
        Rect bounds = new Rect();
        
        if (!text.equals("")) {
            paint.getTextBounds(text, 0, text.length(), bounds);
        }
        
        FontMetrics fontMetrix = paint.getFontMetrics();
        
        bounds.bottom = (int)fontMetrix.bottom;
        bounds.top = (int)fontMetrix.top;

        return new RectShape(bounds);
    }

    //performance tuning
    /**
     * Returns the width for the specified text.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     * @param paint
     *            the font metrics (not <code>null</code>).
     * 
     * @return The text width
     */
    public static float getTextWidth(final String text, final Paint paint) {
//        Rect bounds = new Rect();
//        
//        if (!text.equals("")) {
//            paint.getTextBounds(text, 0, text.length(), bounds);
//        }
//        
//        return bounds.right - bounds.left;
        return paint.measureText(text);
    }
    
    /**
     * Returns the height for the specified text.
     * 
     * @param paint
     *            the font metrics (not <code>null</code>).
     * 
     * @return The text height
     */
    public static float getTextHeight(final Paint paint) {
        FontMetrics fontMetrix = paint.getFontMetrics();
        
        int bottom = (int)fontMetrix.bottom;
        int top = (int)fontMetrix.top;
        
        return bottom - top;
    }
    
    /**
     * A flag that controls whether the FontMetrics.getStringBounds() method is
     * used or a workaround is applied.
     */
    private static boolean useFontMetricsGetStringBounds = false;

    /**
     * Returns the flag that controls whether the FontMetrics.getStringBounds()
     * method is used or not. If you are having trouble with label alignment or
     * positioning, try changing the value of this flag.
     * 
     * @return A boolean.
     */
    public static boolean getUseFontMetricsGetStringBounds() {
        return useFontMetricsGetStringBounds;
    }

    /**
     * Sets the flag that controls whether the FontMetrics.getStringBounds()
     * method is used or not. If you are having trouble with label alignment or
     * positioning, try changing the value of this flag.
     * 
     * @param use
     *            the flag.
     */
    public static void setUseFontMetricsGetStringBounds(boolean use) {
        useFontMetricsGetStringBounds = use;
    }

    /**
     * Draws a string that is aligned by one anchor point and rotated about
     * another anchor point.
     * 
     * @param text
     *            the text.
     * @param canvas
     *            the graphics device.
     * @param x
     *            the x-coordinate for positioning the text.
     * @param y
     *            the y-coordinate for positioning the text.
     * @param textAnchor
     *            the text anchor.
     * @param angle
     *            the rotation angle (in radians).
     * @param rotationAnchor
     *            the rotation anchor.
     */
    public static void drawRotatedString(final String text, final Canvas canvas,
            final float x, final float y, final TextAnchor textAnchor,
            final double angle, final TextAnchor rotationAnchor, Paint paint) {
        
        if (text == null || text.equals("")) {
            return;
        }

        //performance tuning
//        final RectShape textBounds = new RectShape();
//        final float[] textAdjust = deriveTextBoundsAnchorOffsets(canvas, text, textAnchor,
//                textBounds, paint);
//        final float[] rotateAdjust = deriveTextBoundsAnchorOffsets(canvas, text, rotationAnchor,
//                        textBounds, paint);
        final float[] textAdjust = deriveTextBoundsAnchorOffsets(canvas, text, textAnchor,
                paint);
        final float[] rotateAdjust = deriveTextBoundsAnchorOffsets(canvas, text, rotationAnchor,
                paint);
        
        //canvas.drawCircle(x, y, 2, paint);
        canvas.save();
        canvas.translate(x + textAdjust[0], y + textAdjust[1]);
        canvas.rotate((float) Math.toDegrees(angle), -rotateAdjust[0], -rotateAdjust[1]);
        canvas.drawText(text, 0, 0, paint);
        canvas.restore();
    

    }

    
    /**
     * Draws a string such that the specified anchor point is aligned to the
     * given (x, y) location.
     * 
     * @param text
     *            the text.
     * @param canvas
     *            the graphics device.
     * @param x
     *            the x coordinate (Java 2D).
     * @param y
     *            the y coordinate (Java 2D).
     * @param anchor
     *            the anchor location.
     * 
     * @return The text bounds (adjusted for the text position).
     */
    public static RectShape drawAlignedString(final String text,
            final Canvas canvas, final float x, final float y,
            final TextAnchor anchor, Paint paint) {

        final RectShape textBounds = new RectShape();
        //performance tuning
//        final float[] adjust = deriveTextBoundsAnchorOffsets(canvas, text, anchor,
//                textBounds, paint);
        final float[] adjust = deriveTextBoundsAnchorOffsets(canvas, text, anchor,
                paint);
        paint.setTextAlign(Align.LEFT);
        // adjust text bounds to match string position
        /*
         * textBounds.setRect(x + adjust[0], y + adjust[1] + adjust[2],
         * textBounds.getWidth(), textBounds.getHeight());
         */
        // TODO ACHUNG
        textBounds.setRect(x + adjust[0], y + adjust[1] + adjust[2], textBounds
                .getWidth(), textBounds.getHeight());
        canvas.drawText(text, x + adjust[0], y + adjust[1], paint);
        // canvas.drawString(text, x + 0, y + 0);
        return textBounds;
    }

    private static float[] deriveTextBoundsAnchorOffsets(final Canvas canvas,
            final String text, final TextAnchor anchor,Paint paint) {

        final float[] result = new float[3];

        final FontMetrics fm = paint.getFontMetrics();
        
        //performance tuning
        //final RectShape bounds = TextUtilities.getTextBounds(text, paint);
        float width = TextUtilities.getTextWidth(text, paint);
        float height = TextUtilities.getTextHeight(paint);
        
        final float ascent = fm.ascent;
        result[2] = -ascent;
        final float halfAscent = ascent / 2.0f;
        final float descent = fm.descent;
        final float leading = fm.leading;
        float xAdj = 0.0f;
        float yAdj = 0.0f;

        if (anchor == TextAnchor.TOP_CENTER
                || anchor == TextAnchor.CENTER
                || anchor == TextAnchor.BOTTOM_CENTER
                || anchor == TextAnchor.BASELINE_CENTER
                || anchor == TextAnchor.HALF_ASCENT_CENTER) {

            //xAdj = (float) -bounds.getWidth() / 2.0f;
            xAdj = -width * 0.5f;

        }
        else if (anchor == TextAnchor.TOP_RIGHT
                || anchor == TextAnchor.CENTER_RIGHT
                || anchor == TextAnchor.BOTTOM_RIGHT
                || anchor == TextAnchor.BASELINE_RIGHT
                || anchor == TextAnchor.HALF_ASCENT_RIGHT) {

            //xAdj = (float) -bounds.getWidth();
            xAdj = -width;

        }

        if (anchor == TextAnchor.TOP_LEFT
                || anchor == TextAnchor.TOP_CENTER
                || anchor == TextAnchor.TOP_RIGHT) {

            //yAdj = -descent - leading + (float) bounds.getHeight();
            yAdj = -descent - leading + height;
            
        }
        else if (anchor == TextAnchor.HALF_ASCENT_LEFT
                || anchor == TextAnchor.HALF_ASCENT_CENTER
                || anchor == TextAnchor.HALF_ASCENT_RIGHT) {

            yAdj = halfAscent;

        }
        else if (anchor == TextAnchor.CENTER_LEFT
                || anchor == TextAnchor.CENTER
                || anchor == TextAnchor.CENTER_RIGHT) {

            //yAdj = -descent - leading + (float) (bounds.getHeight() / 2.0);
            yAdj = -descent - leading + height * 0.5f;

        }
        else if (anchor == TextAnchor.BASELINE_LEFT
                || anchor == TextAnchor.BASELINE_CENTER
                || anchor == TextAnchor.BASELINE_RIGHT) {

            yAdj = 0.0f;

        }
        else if (anchor == TextAnchor.BOTTOM_LEFT
                || anchor == TextAnchor.BOTTOM_CENTER
                || anchor == TextAnchor.BOTTOM_RIGHT) {

            yAdj = -fm.descent - fm.leading;

        }
//        if (textBounds != null) {
//            textBounds.setRect(bounds);
//        }
        result[0] = xAdj;
        result[1] = yAdj;
        return result;


    }

    /**
     * Returns a shape that represents the bounds of the string after the
     * specified rotation has been applied.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     * @param anchorX
     *            the anchorX coordinate for the anchor point.
     * @param anchorY
     *            the anchorY coordinate for the anchor point.
     * @param textAnchor
     *            the text anchor.
     * @param rotationAngle
     *            the rotationAngle.
     * @param rotationAnchor
     *            the rotation anchor.
     * 
     * @return The bounds (possibly <code>null</code>).
     */
    /*
     * public static Shape calculateRotatedStringBounds(final String text, final
     * Canvas canvas, final float x, final float y, final TextAnchor textAnchor,
     * final double angle, final TextAnchor rotationAnchor) {
     * 
     * if (text == null || text.equals("")) { return null; } final float[]
     * textAdj = deriveTextBoundsAnchorOffsets(canvas, text, textAnchor);
     * 
     * final float[] rotateAdj = deriveRotationAnchorOffsets(canvas, text,
     * rotationAnchor);
     * 
     * final Shape result = calculateRotatedStringBounds(text, canvas, x +
     * textAdj[0], y + textAdj[1], angle, x + textAdj[0] + rotateAdj[0], y +
     * textAdj[1] + rotateAdj[1]); return result;
     * 
     * }
     */

    public static RectShape calculateRotatedStringBounds(String text, Paint paint, float anchorX,
            float anchorY, TextAnchor textAnchor, double rotationAngle, TextAnchor rotationAnchor) {
        if (text == null || text.equals("")) { return null; }

        final float[]textAdj = deriveTextBoundsAnchorOffsets(paint, text, textAnchor);
        
        final float[] rotateAdj = deriveRotationAnchorOffsets(paint, text, rotationAnchor);
        
        final RectShape result = calculateRotatedStringBounds(text, paint, anchorX +
            textAdj[0], anchorY + textAdj[1], rotationAngle, anchorX + textAdj[0] + rotateAdj[0], anchorY +
            textAdj[1] + rotateAdj[1]);
        
        return result;
    }
    

    /**
     * A utility method that calculates the anchor offsets for a string.
     * Normally, the (x, y) coordinate for drawing text is a point on the
     * baseline at the left of the text string.  If you add these offsets to
     * (x, y) and draw the string, then the anchor point should coincide with
     * the (x, y) point.
     *
     * @param canvas  the graphics device (not <code>null</code>).
     * @param text  the text.
     * @param anchor  the anchor point.
     *
     * @return  The offsets.
     */
    private static float[] deriveTextBoundsAnchorOffsets(final Paint paint,
            final String text, final TextAnchor anchor) {

        final float[] result = new float[2];
        final FontMetrics fm = paint.getFontMetrics();
        final RectShape bounds = TextUtilities.getTextBounds(text, paint);
        final float ascent = Math.abs(fm.ascent);
        final float halfAscent = ascent / 2.0f;
        final float descent = Math.abs(fm.descent);
        final float leading = Math.abs(fm.leading);
        float xAdj = 0.0f;
        float yAdj = 0.0f;

        if (anchor == TextAnchor.TOP_CENTER
                || anchor == TextAnchor.CENTER
                || anchor == TextAnchor.BOTTOM_CENTER
                || anchor == TextAnchor.BASELINE_CENTER
                || anchor == TextAnchor.HALF_ASCENT_CENTER) {

            xAdj = (float) -bounds.getWidth() / 2.0f;

        }
        else if (anchor == TextAnchor.TOP_RIGHT
                || anchor == TextAnchor.CENTER_RIGHT
                || anchor == TextAnchor.BOTTOM_RIGHT
                || anchor == TextAnchor.BASELINE_RIGHT
                || anchor == TextAnchor.HALF_ASCENT_RIGHT) {

            xAdj = (float) -bounds.getWidth();

        }

        if (anchor == TextAnchor.TOP_LEFT
                || anchor == TextAnchor.TOP_CENTER
                || anchor == TextAnchor.TOP_RIGHT) {

            yAdj = -descent - leading + (float) bounds.getHeight();

        }
        else if (anchor == TextAnchor.HALF_ASCENT_LEFT
                || anchor == TextAnchor.HALF_ASCENT_CENTER
                || anchor == TextAnchor.HALF_ASCENT_RIGHT) {

            yAdj = halfAscent;

        }
        else if (anchor == TextAnchor.CENTER_LEFT
                || anchor == TextAnchor.CENTER
                || anchor == TextAnchor.CENTER_RIGHT) {

            yAdj = -descent - leading + (float) (bounds.getHeight() / 2.0);

        }
        else if (anchor == TextAnchor.BASELINE_LEFT
                || anchor == TextAnchor.BASELINE_CENTER
                || anchor == TextAnchor.BASELINE_RIGHT) {

            yAdj = 0.0f;

        }
        else if (anchor == TextAnchor.BOTTOM_LEFT
                || anchor == TextAnchor.BOTTOM_CENTER
                || anchor == TextAnchor.BOTTOM_RIGHT) {

            yAdj = -Math.abs(fm.descent) - Math.abs(fm.leading);

        }
        result[0] = xAdj;
        result[1] = yAdj;
        return result;

    }
    
    /**
     * A utility method that calculates the rotation anchor offsets for a
     * string.  These offsets are relative to the text starting coordinate
     * (BASELINE_LEFT).
     *
     * @param canvas  the graphics device.
     * @param text  the text.
     * @param anchor  the anchor point.
     *
     * @return  The offsets.
     */
    private static float[] deriveRotationAnchorOffsets(final Paint paint,
            final String text, final TextAnchor anchor) {

        final float[] result = new float[2];
        final FontMetrics fm = paint.getFontMetrics();
        final RectShape bounds = TextUtilities.getTextBounds(text, paint);
        final float ascent = fm.ascent;
        final float halfAscent = ascent / 2.0f;
        final float descent = fm.descent;
        final float leading = fm.leading;
        float xAdj = 0.0f;
        float yAdj = 0.0f;

        if (anchor == TextAnchor.TOP_LEFT
                || anchor == TextAnchor.CENTER_LEFT
                || anchor == TextAnchor.BOTTOM_LEFT
                || anchor == TextAnchor.BASELINE_LEFT
                || anchor == TextAnchor.HALF_ASCENT_LEFT) {

            xAdj = 0.0f;

        }
        else if (anchor == TextAnchor.TOP_CENTER
                || anchor == TextAnchor.CENTER
                || anchor == TextAnchor.BOTTOM_CENTER
                || anchor == TextAnchor.BASELINE_CENTER
                || anchor == TextAnchor.HALF_ASCENT_CENTER) {

            xAdj = (float) bounds.getWidth() / 2.0f;

        }
        else if (anchor == TextAnchor.TOP_RIGHT
                || anchor == TextAnchor.CENTER_RIGHT
                || anchor == TextAnchor.BOTTOM_RIGHT
                || anchor == TextAnchor.BASELINE_RIGHT
                || anchor == TextAnchor.HALF_ASCENT_RIGHT) {

            xAdj = (float) bounds.getWidth();

        }

        if (anchor == TextAnchor.TOP_LEFT
                || anchor == TextAnchor.TOP_CENTER
                || anchor == TextAnchor.TOP_RIGHT) {

            yAdj = descent + leading - (float) bounds.getHeight();

        }
        else if (anchor == TextAnchor.CENTER_LEFT
                || anchor == TextAnchor.CENTER
                || anchor == TextAnchor.CENTER_RIGHT) {

            yAdj = descent + leading - (float) (bounds.getHeight() / 2.0);

        }
        else if (anchor == TextAnchor.HALF_ASCENT_LEFT
                || anchor == TextAnchor.HALF_ASCENT_CENTER
                || anchor == TextAnchor.HALF_ASCENT_RIGHT) {

            yAdj = -halfAscent;

        }
        else if (anchor == TextAnchor.BASELINE_LEFT
                || anchor == TextAnchor.BASELINE_CENTER
                || anchor == TextAnchor.BASELINE_RIGHT) {

            yAdj = 0.0f;

        }
        else if (anchor == TextAnchor.BOTTOM_LEFT
                || anchor == TextAnchor.BOTTOM_CENTER
                || anchor == TextAnchor.BOTTOM_RIGHT) {

            yAdj = fm.descent + fm.leading;

        }
        result[0] = xAdj;
        result[1] = yAdj;
        return result;

    }
    


    /**
     * Returns a shape that represents the bounds of the string after the
     * specified rotation has been applied.
     *
     * @param text  the text (<code>null</code> permitted).
     * @param textX  the x coordinate for the text.
     * @param textY  the y coordinate for the text.
     * @param angle  the angle.
     * @param rotateX  the x coordinate for the rotation point.
     * @param rotateY  the y coordinate for the rotation point.
     *
     * @return The bounds (<code>null</code> if <code>text</code> is
     *         </code>null</code> or has zero length).
     */
    public static RectShape calculateRotatedStringBounds(final String text,
            final Paint paint, final float textX, final float textY,
            final double angle, final float rotateX, final float rotateY) {

        if ((text == null) || (text.equals(""))) {
            return null;
        }

        final RectShape bounds = TextUtilities.getTextBounds(text, paint);
        bounds.translate(textX, textY);

        Matrix rotate = new Matrix();
        rotate.postRotate((float)angle, rotateX, rotateY);
        
        Path path = new Path(bounds.getPath());
        path.transform(rotate);
        
        RectF rect = new RectF();
        path.computeBounds(rect, false);
        
        PathShape pathShape = new PathShape(path);
        
        RectShape rectShape = new RectShape();
        pathShape.getBounds(rectShape);
        return rectShape;
    }
}
