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
 * ----------------------
 * RefineryUtilities.java
 * ----------------------
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
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2000-2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Jon Iles;
 *
 *
 * Changes (from 26-Oct-2001)
 * --------------------------
 * 26-Oct-2001 : Changed package to com.jrefinery.ui.*;
 * 26-Nov-2001 : Changed name to SwingRefinery.java to make it obvious that this is not part of
 *               the Java APIs (DG);
 * 10-Dec-2001 : Changed name (again) to JRefineryUtilities.java (DG);
 * 28-Feb-2002 : Moved system properties classes into com.jrefinery.ui.about (DG);
 * 19-Apr-2002 : Renamed JRefineryUtilities-->RefineryUtilities.  Added drawRotatedString(...)
 *               method (DG);
 * 21-May-2002 : Changed frame positioning methods to accept Window parameters, as suggested by
 *               Laurence Vanhelsuwe (DG);
 * 27-May-2002 : Added getPointInRectShape method (DG);
 * 26-Jun-2002 : Removed unnecessary imports (DG);
 * 12-Jul-2002 : Added workaround for rotated text (JI);
 * 14-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-May-2003 : Added a new drawRotatedString() method (DG);
 * 09-May-2003 : Added a drawRotatedShape() method (DG);
 * 10-Jun-2003 : Updated aligned and rotated string methods (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 07-Nov-2003 : Added rotateShape() method (DG);
 * 16-Mar-2004 : Moved rotateShape() method to ShapeUtils.java (DG);
 * 07-Apr-2004 : Modified text bounds calculation with TextUtilities.getTextBounds() (DG);
 * 21-May-2004 : Fixed bug 951870 - precision in drawAlignedString() method (DG);
 *
 */

package org.afree.ui;

import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.RectShape;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;

/**
 * A collection of utility methods relating to user interfaces.
 */
public abstract class RefineryUtilities {

    
    public static void drawRotatedString(final String text, final Canvas canvas,
            final float x, final float y, final TextAnchor textAnchor,
            final Paint paint, float angle) {
        drawRotatedString(text, canvas, x, y, textAnchor, x, y, paint, angle);
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
     *            the location of the text anchor.
     * @param y
     *            the location of the text anchor.
     * @param textAnchor
     *            the text anchor.
     * @param rotateX
     *            the x-coordinate for the rotation anchor point.
     * @param rotateY
     *            the y-coordinate for the rotation anchor point.
     * @param angle
     *            the rotation angle.
     */
    public static void drawRotatedString(final String text, final Canvas canvas,
            final float x, final float y, final TextAnchor textAnchor,
            final float rotateX, final float rotateY,
            final Paint paint, float angle) {
        final float[] textAdj = deriveTextBoundsAnchorOffsets(paint, text, textAnchor);
        
        canvas.save();
        if (angle == 0.0)
            canvas.drawText(text, x + textAdj[0], y + textAdj[1], paint);
        else {
            canvas.rotate((float) Math.toDegrees(angle), rotateX, rotateY);
            canvas.drawText(text, x + textAdj[0], y + textAdj[1], paint);
        }

        //canvas.drawCircle(x + textAdj[0], y + textAdj[1], 2, paint);
        canvas.restore();
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
}
