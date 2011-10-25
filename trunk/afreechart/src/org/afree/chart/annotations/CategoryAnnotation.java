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
 * -----------------------
 * CategoryAnnotation.java
 * -----------------------
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
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 02-Apr-2003 : Version 1 (DG);
 * 02-Jul-2003 : Eliminated Annotation base interface (DG);
 *
 */

package org.afree.chart.annotations;

import org.afree.chart.axis.CategoryAxis;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.CategoryPlot;
import org.afree.graphics.geom.RectShape;
import android.graphics.Canvas;

/**
 * The interface that must be supported by annotations that are to be added to a
 * {@link org.afree.chart.plot.CategoryPlot}.
 */
public interface CategoryAnnotation {

    /**
     * Draws the annotation.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param dataArea
     *            the data area.
     * @param domainAxis
     *            the domain axis.
     * @param rangeAxis
     *            the range axis.
     */
    public void draw(Canvas canvas, CategoryPlot plot, RectShape dataArea,
            CategoryAxis domainAxis, ValueAxis rangeAxis);

}
