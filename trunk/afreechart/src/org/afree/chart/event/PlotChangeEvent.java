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
 * --------------------
 * PlotChangeEvent.java
 * --------------------
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
 * (C) Copyright 2000-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes (from 24-Aug-2001)
 * --------------------------
 * 24-Aug-2001 : Added standard source header. Fixed DOS encoding problem (DG);
 * 07-Nov-2001 : Updated header (DG);
 * 09-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 17-Jan-2003 : Moved plot classes to a separate package (DG);
 *
 */

package org.afree.chart.event;

import org.afree.chart.plot.Plot;

/**
 * An event that can be forwarded to any
 * {@link org.afree.chart.event.PlotChangeListener} to signal a change to a
 * plot.
 */
public class PlotChangeEvent extends ChartChangeEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 7537796117744608765L;
    /** The plot that generated the event. */
    private Plot plot;

    /**
     * Creates a new PlotChangeEvent.
     * 
     * @param plot
     *            the plot that generated the event.
     */
    public PlotChangeEvent(Plot plot) {
        super(plot);
        this.plot = plot;
    }

    /**
     * Returns the plot that generated the event.
     * 
     * @return The plot that generated the event.
     */
    public Plot getPlot() {
        return this.plot;
    }

}
