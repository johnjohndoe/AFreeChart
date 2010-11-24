/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *    AFreeChart: http://code.google.com/p/afreechart/
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Android is a trademark of Google Inc.]
 *
 * -----------------
 * Movable.java
 * -----------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 *
 * Original Author:  Niwano Masayoshi (for Icom Systech Co., Ltd);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Nov-2010 : Version 0.0.1 (NM);
 */

package org.afree.chart.plot;

import android.graphics.PointF;


/**
 * Movable
 * @author ikeda
 *
 */
public interface Movable {

    public boolean isDomainMovable();
    
    public boolean isRangeMovable();
    
    /**
     * Returns the orientation of the plot.
     * 
     * @return The orientation.
     */
    public PlotOrientation getOrientation();
    
    public void moveDomainAxes(double movePercent, PlotRenderingInfo info, PointF source);
    
    public void moveRangeAxes(double movePercent, PlotRenderingInfo info, PointF source);
    
}
