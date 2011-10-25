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
 * ----------------------
 * MarkerChangeEvent.java
 * ----------------------
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
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Sep-2006 : Version 1 (DG);
 *
 */

package org.afree.chart.event;

import org.afree.chart.plot.Marker;


/**
 * An event that can be forwarded to any {@link MarkerChangeListener} to
 * signal a change to a {@link Marker}.
 *
 * @since JFreeChart 1.0.3
 */
public class MarkerChangeEvent extends ChartChangeEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 7195771223383835803L;
    /** The plot that generated the event. */
    private Marker marker;

    /**
     * Creates a new <code>MarkerChangeEvent</code> instance.
     *
     * @param marker  the marker that triggered the event (<code>null</code>
     *     not permitted).
     *
     * @since JFreeChart 1.0.3
     */
    public MarkerChangeEvent(Marker marker) {
        super(marker);
        this.marker = marker;
    }

    /**
     * Returns the marker that triggered the event.
     *
     * @return The marker that triggered the event (never <code>null</code>).
     *
     * @since JFreeChart 1.0.3
     */
    public Marker getMarker() {
        return this.marker;
    }

}
