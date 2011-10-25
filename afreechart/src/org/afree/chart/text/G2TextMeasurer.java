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
 * -------------------
 * G2TextMeasurer.java
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
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 * 17-Dec-2010 : performance tuning
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
 *
 */

package org.afree.chart.text;

import android.graphics.Paint;

/**
 * A {@link TextMeasurer} based on a {@link Graphics2D}.
 */
public class G2TextMeasurer implements TextMeasurer {

    /** The graphics device. */
    Paint p;

    /**
     * Creates a new text measurer.
     * 
     * @param p
     *            the graphics device.
     */
    public G2TextMeasurer(final Paint p) {
        this.p = p;
    }

    /**
     * Returns the string width.
     * 
     * @param text
     *            the text.
     * @param start
     *            the index of the first character to measure.
     * @param end
     *            the index of the last character to measure.
     * 
     * @return the string width.
     */
    public float getStringWidth(final String text, final int start,
            final int end) {

        //performance tuning
//        final RectShape bounds = TextUtilities.getTextBounds(text.substring(
//                start, end), p);
//        final float result = (float) bounds.getWidth();
//        return result;
        return TextUtilities.getTextWidth(text.substring(
              start, end), p);
    }

}
