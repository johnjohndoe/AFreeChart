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
 * -----------
 * Size2D.java
 * -----------
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
 * Contributor(s):   -;
 *
 *
 * Changes (from 26-Oct-2001)
 * --------------------------
 * 26-Oct-2001 : Changed package to com.jrefinery.ui.*;
 * 14-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 *
 */

package org.afree.ui;

/**
 * A simple class for representing the dimensions of an object. I would use
 * Dimension2D, but refer to Bug ID 4189446 on the Java Developer Connection for
 * why not (last checked 20 July 2000, maybe it's been fixed now).
 * 
 */
public class Size2D {

    /** The width. */
    public double width;

    /** The height. */
    public double height;

    /**
     * Standard constructor - builds a Size2D with the specified width and
     * height.
     * 
     * @param width
     *            the width.
     * @param height
     *            the height.
     */
    public Size2D(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    public Size2D() {
        this(0.0, 0.0);
    }

    /**
     * Returns the height.
     * 
     * @return the height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the width.
     * 
     * @return the width.
     */
    public double getWidth() {
        return this.width;
    }

}
