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
 * -------------
 * Pannable.java
 * -------------
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
 * Original Author:  Ulrich Voigt - patch 2686040;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 19-Mar-2009 : Version 1, with modifications from patch by UV (DG);
 *
 */

package org.afree.chart.plot;

import android.graphics.PointF;


/**
 * An interface that the {@link DemoView} class uses to communicate with plots
 * that support panning.
 * 
 * @since JFreeChart 1.0.13
 */
public interface Pannable {

    /**
     * Returns the orientation of the plot.
     * 
     * @return The orientation (never <code>null</code>).
     */
    public PlotOrientation getOrientation();

    /**
     * Evaluates if the domain axis can be panned.
     * 
     * @return <code>true</code> if the domain axis is pannable.
     */
    public boolean isDomainPannable();

    /**
     * Evaluates if the range axis can be panned.
     * 
     * @return <code>true</code> if the range axis is pannable.
     */
    public boolean isRangePannable();

    /**
     * Pans the domain axes by the specified percentage.
     * 
     * @param percent
     *            the distance to pan (as a percentage of the axis length).
     * @param info
     *            the plot info
     * @param source
     *            the source point where the pan action started.
     */
    public void panDomainAxes(double percent, PlotRenderingInfo info,
            PointF source);

    /**
     * Pans the range axes by the specified percentage.
     * 
     * @param percent
     *            the distance to pan (as a percentage of the axis length).
     * @param info
     *            the plot info
     * @param source
     *            the source point where the pan action started.
     */
    public void panRangeAxes(double percent, PlotRenderingInfo info,
            PointF source);

}
