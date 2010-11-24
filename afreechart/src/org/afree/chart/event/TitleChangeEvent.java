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
 * ---------------------
 * TitleChangeEvent.java
 * ---------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes (from 22-Jun-2001)
 * --------------------------
 * 22-Jun-2001 : Changed Title to AbstractTitle while incorporating
 *               David Berry's changes (DG);
 * 24-Aug-2001 : Fixed DOS encoding problem (DG);
 * 07-Nov-2001 : Updated header (DG);
 * 09-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Jan-2004 : Renamed AbstractTitle --> Title and moved to new package (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.chart.event;

import org.afree.chart.title.Title;

/**
 * A change event that encapsulates information about a change to a chart title.
 */
public class TitleChangeEvent extends ChartChangeEvent {

    /** The chart title that generated the event. */
    private Title title;

    /**
     * Default constructor.
     * 
     * @param title
     *            the chart title that generated the event.
     */
    public TitleChangeEvent(Title title) {
        super(title);
        this.title = title;
    }

    /**
     * Returns the title that generated the event.
     * 
     * @return The title that generated the event.
     */
    public Title getTitle() {
        return this.title;
    }

}
