/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
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
 * ---------------------------
 * XYAnnotationBoundsInfo.java
 * ---------------------------
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
 * (C) Copyright 2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 10-Mar-2009 : Version 1 (DG);
 *
 */

package org.afree.chart.annotations;

import org.afree.data.Range;



/**
 * An interface that supplies information about the bounds of the annotation.
 *
 * @since JFreeChart 1.0.13
 */
public interface XYAnnotationBoundsInfo {

    /**
     * Returns a flag that determines whether or not the annotation's
     * bounds should be taken into account for auto-range calculations on
     * the axes that the annotation is plotted against.
     *
     * @return A boolean.
     */
    public boolean getIncludeInDataBounds();

    /**
     * Returns the range of x-values (in data space) that the annotation
     * uses.
     *
     * @return The x-range.
     */
    public Range getXRange();

    /**
     * Returns the range of y-values (in data space) that the annotation
     * uses.
     *
     * @return The y-range.
     */
    public Range getYRange();

}
