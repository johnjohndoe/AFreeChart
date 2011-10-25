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
 * ---------------
 * ChartColor.java
 * ---------------
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
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  Cameron Riley;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 23-Jan-2003 : Version 1, contributed by Cameron Riley (DG);
 * 25-Nov-2004 : Changed first 7 colors to softer shades (DG);
 * 03-Nov-2005 : Removed orange color, too close to yellow - see bug
 *               report 1328408 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.afree.chart;

import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import android.graphics.Color;

/**
 * Class to extend the number of Colors available to the charts. This extends
 * the java.awt.Color object and extends the number of final Colors publically
 * accessible.
 */
public class ChartColor extends Color {

    /** A very dark red color. */
    public static final int VERY_DARK_RED = Color.argb(0xFF, 0x80, 0x00, 0x00);

    /** A dark red color. */
    public static final int DARK_RED = Color.argb(0xFF, 0xc0, 0x00, 0x00);

    /** A light red color. */
    public static final int LIGHT_RED = Color.argb(0xFF, 0xFF, 0x40, 0x40);

    /** A very light red color. */
    public static final int VERY_LIGHT_RED = Color.argb(0xFF, 0xFF, 0x80, 0x80);

    /** A very dark yellow color. */
    public static final int VERY_DARK_YELLOW = Color.argb(0xFF, 0x80, 0x80,
            0x00);

    /** A dark yellow color. */
    public static final int DARK_YELLOW = Color.argb(0xFF, 0xC0, 0xC0, 0x00);

    /** A light yellow color. */
    public static final int LIGHT_YELLOW = Color.argb(0xFF, 0xFF, 0xFF, 0x40);

    /** A very light yellow color. */
    public static final int VERY_LIGHT_YELLOW = Color.argb(0xFF, 0xFF, 0xFF,
            0x80);

    /** A very dark green color. */
    public static final int VERY_DARK_GREEN = Color
            .argb(0xFF, 0x00, 0x80, 0x00);

    /** A dark green color. */
    public static final int DARK_GREEN = Color.argb(0xFF, 0x00, 0xC0, 0x00);

    /** A light green color. */
    public static final int LIGHT_GREEN = Color.argb(0xFF, 0x40, 0xFF, 0x40);

    /** A very light green color. */
    public static final int VERY_LIGHT_GREEN = Color.argb(0xFF, 0x80, 0xFF,
            0x80);

    /** A very dark cyan color. */
    public static final int VERY_DARK_CYAN = Color.argb(0xFF, 0x00, 0x80, 0x80);

    /** A dark cyan color. */
    public static final int DARK_CYAN = Color.argb(0xFF, 0x00, 0xC0, 0xC0);

    /** A light cyan color. */
    public static final int LIGHT_CYAN = Color.argb(0xFF, 0x40, 0xFF, 0xFF);

    /** Aa very light cyan color. */
    public static final int VERY_LIGHT_CYAN = Color
            .argb(0xFF, 0x80, 0xFF, 0xFF);

    /** A very dark blue color. */
    public static final int VERY_DARK_BLUE = Color.argb(0xFF, 0x00, 0x00, 0x80);

    /** A dark blue color. */
    public static final int DARK_BLUE = Color.argb(0xFF, 0x00, 0x00, 0xC0);

    /** A light blue color. */
    public static final int LIGHT_BLUE = Color.argb(0xFF, 0x40, 0x40, 0xFF);

    /** A very light blue color. */
    public static final int VERY_LIGHT_BLUE = Color
            .argb(0xFF, 0x80, 0x80, 0xFF);

    /** A very dark magenta/purple color. */
    public static final int VERY_DARK_MAGENTA = Color.argb(0xFF, 0x80, 0x00,
            0x80);

    /** A dark magenta color. */
    public static final int DARK_MAGENTA = Color.argb(0xFF, 0xC0, 0x00, 0xC0);

    /** A light magenta color. */
    public static final int LIGHT_MAGENTA = Color.argb(0xFF, 0xFF, 0x40, 0xFF);

    /** A very light magenta color. */
    public static final int VERY_LIGHT_MAGENTA = Color.argb(0xFF, 0xFF, 0x80,
            0xFF);

    /** A pink color. */
    public static final int PINK = Color.argb(0xFF, 0xFF, 0xAF,
            0xAF);
    
    /**
     * Convenience method to return an array of <code>Paint</code> objects that
     * represent the pre-defined colors in the <code>Color<code> and
     * <code>ChartColor</code> objects.
     * 
     * @return An array of objects with the <code>Paint</code> interface.
     */
    public static PaintType[] createDefaultPaintArray() {

        return new PaintType[] {

                new SolidColor(Color.argb(0xFF, 0xFF, 0x55, 0x55)),
                new SolidColor(Color.argb(0xFF, 0x55, 0x55, 0xFF)),
                new SolidColor(Color.argb(0xFF, 0x55, 0xFF, 0x55)),
                new SolidColor(Color.argb(0xFF, 0xFF, 0xFF, 0x55)),
                new SolidColor(Color.argb(0xFF, 0xFF, 0x55, 0xFF)),
                new SolidColor(Color.argb(0xFF, 0x55, 0xFF, 0xFF)),
                new SolidColor(ChartColor.PINK),
                new SolidColor(Color.GRAY),
                new SolidColor(ChartColor.DARK_RED),
                new SolidColor(ChartColor.DARK_BLUE),
                new SolidColor(ChartColor.DARK_GREEN),
                new SolidColor(ChartColor.DARK_YELLOW),
                new SolidColor(ChartColor.DARK_MAGENTA),
                new SolidColor(ChartColor.DARK_CYAN),
                new SolidColor(Color.DKGRAY),
                new SolidColor(ChartColor.LIGHT_RED),
                new SolidColor(ChartColor.LIGHT_BLUE),
                new SolidColor(ChartColor.LIGHT_GREEN),
                new SolidColor(ChartColor.LIGHT_YELLOW),
                new SolidColor(ChartColor.LIGHT_MAGENTA),
                new SolidColor(ChartColor.LIGHT_CYAN),
                new SolidColor(ChartColor.LTGRAY),
                new SolidColor(ChartColor.VERY_DARK_RED),
                new SolidColor(ChartColor.VERY_DARK_BLUE),
                new SolidColor(ChartColor.VERY_DARK_GREEN),
                new SolidColor(ChartColor.VERY_DARK_YELLOW),
                new SolidColor(ChartColor.VERY_DARK_MAGENTA),
                new SolidColor(ChartColor.VERY_DARK_CYAN),
                new SolidColor(ChartColor.VERY_LIGHT_RED),
                new SolidColor(ChartColor.VERY_LIGHT_BLUE),
                new SolidColor(ChartColor.VERY_LIGHT_GREEN),
                new SolidColor(ChartColor.VERY_LIGHT_YELLOW),
                new SolidColor(ChartColor.VERY_LIGHT_MAGENTA),
                new SolidColor(ChartColor.VERY_LIGHT_CYAN) };
    }

}
