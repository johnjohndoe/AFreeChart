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
 * TextMeasurer.java
 * -----------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 *
 * Changes
 * -------
 * 07-Jan-2004 : Version 1 (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 */

package org.afree.chart.text;

/**
 * An object that can measure text.
 */
public interface TextMeasurer {

    /**
     * Calculates the width of a <code>String</code> in the current
     * <code>Graphics</code> context.
     * 
     * @param text
     *            the text.
     * @param start
     *            the start position of the substring to be measured.
     * @param end
     *            the position of the last character to be measured.
     * 
     * @return the width of the string in Java2D units.
     */
    public float getStringWidth(String text, int start, int end);

}
