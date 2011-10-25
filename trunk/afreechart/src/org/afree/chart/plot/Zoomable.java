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
 * -------------
 * Zoomable.java
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
 * (C) Copyright 2004-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Rune Fauske;
 *
 * Changes
 * -------
 * 12-Nov-2004 : Version 1 (DG);
 * 26-Jan-2004 : Added getOrientation() method (DG);
 * 04-Sep-2006 : Added credit for Rune Fauske, see patch 1050659 (DG);
 * 21-Sep-2007 : Added new zooming methods with 'useAnchor' flag.  This breaks
 *               the API, but is the cleanest way I can think of to fix a
 *               long-standing bug (DG);
 *
 */

package org.afree.chart.plot;

import android.graphics.PointF;


/**
 * A plot that is zoomable must implement this interface to provide a mechanism
 * for the {@link DemoView} to control the zooming.
 */
public interface Zoomable {

    /**
     * Returns <code>true</code> if the plot's domain is zoomable, and
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     * 
     * @see #isRangeZoomable()
     */
    public boolean isDomainZoomable();

    /**
     * Returns <code>true</code> if the plot's range is zoomable, and
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     * 
     * @see #isDomainZoomable()
     */
    public boolean isRangeZoomable();

    /**
     * Returns the orientation of the plot.
     * 
     * @return The orientation.
     */
    public PlotOrientation getOrientation();

    /**
     * Multiplies the range on the domain axis/axes by the specified factor. The
     * <code>source</code> point can be used in some cases to identify a
     * subplot, or to determine the center of zooming (refer to the
     * documentation of the implementing class for details).
     * 
     * @param factor
     *            the zoom factor.
     * @param state
     *            the plot state.
     * @param source
     *            the source point (in Java2D coordinates).
     * 
     * @see #zoomRangeAxes(double, PlotRenderingInfo, PointF)
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo state,
            PointF source);

    /**
     * Multiplies the range on the domain axis/axes by the specified factor. The
     * <code>source</code> point can be used in some cases to identify a
     * subplot, or to determine the center of zooming (refer to the
     * documentation of the implementing class for details).
     * 
     * @param factor
     *            the zoom factor.
     * @param state
     *            the plot state.
     * @param source
     *            the source point (in Java2D coordinates).
     * @param useAnchor
     *            use source point as zoom anchor?
     * 
     * @see #zoomRangeAxes(double, PlotRenderingInfo, PointF, boolean)
     * 
     * @since JFreeChart 1.0.7
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo state,
            PointF source, boolean useAnchor);

    /**
     * Zooms in on the domain axes. The <code>source</code> point can be used in
     * some cases to identify a subplot for zooming.
     * 
     * @param lowerPercent
     *            the new lower bound.
     * @param upperPercent
     *            the new upper bound.
     * @param state
     *            the plot state.
     * @param source
     *            the source point (in Java2D coordinates).
     * 
     * @see #zoomRangeAxes(double, double, PlotRenderingInfo, PointF)
     */
    public void zoomDomainAxes(double lowerPercent, double upperPercent,
            PlotRenderingInfo state, PointF source);

    /**
     * Multiplies the range on the range axis/axes by the specified factor. The
     * <code>source</code> point can be used in some cases to identify a
     * subplot, or to determine the center of zooming (refer to the
     * documentation of the implementing class for details).
     * 
     * @param factor
     *            the zoom factor.
     * @param state
     *            the plot state.
     * @param source
     *            the source point (in Java2D coordinates).
     * 
     * @see #zoomDomainAxes(double, PlotRenderingInfo, PointF)
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo state,
            PointF source);

    /**
     * Multiplies the range on the range axis/axes by the specified factor. The
     * <code>source</code> point can be used in some cases to identify a
     * subplot, or to determine the center of zooming (refer to the
     * documentation of the implementing class for details).
     * 
     * @param factor
     *            the zoom factor.
     * @param state
     *            the plot state.
     * @param source
     *            the source point (in Java2D coordinates).
     * @param useAnchor
     *            use source point as zoom anchor?
     * 
     * @see #zoomDomainAxes(double, PlotRenderingInfo, PointF)
     * 
     * @since JFreeChart 1.0.7
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo state,
            PointF source, boolean useAnchor);

    /**
     * Zooms in on the range axes. The <code>source</code> point can be used in
     * some cases to identify a subplot for zooming.
     * 
     * @param lowerPercent
     *            the new lower bound.
     * @param upperPercent
     *            the new upper bound.
     * @param state
     *            the plot state.
     * @param source
     *            the source point (in Java2D coordinates).
     * 
     * @see #zoomDomainAxes(double, double, PlotRenderingInfo, PointF)
     */
    public void zoomRangeAxes(double lowerPercent, double upperPercent,
            PlotRenderingInfo state, PointF source);

}
