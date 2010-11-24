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
 * -----------------------
 * ChartProgressEvent.java
 * -----------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes
 * -------
 * 14-Jan-2003 : Version 1 (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.chart.event;

import org.afree.chart.AFreeChart;


/**
 * An event that contains information about the drawing progress of a chart.
 */
public class ChartProgressEvent extends java.util.EventObject {

    /** Indicates drawing has started. */
    public static final int DRAWING_STARTED = 1;

    /** Indicates drawing has finished. */
    public static final int DRAWING_FINISHED = 2;

    /** The type of event. */
    private int type;

    /** The percentage of completion. */
    private int percent;

    /** The chart that generated the event. */
    private AFreeChart chart;

    /**
     * Creates a new chart change event.
     *
     * @param source  the source of the event (could be the chart, a title, an
     *                axis etc.)
     * @param chart  the chart that generated the event.
     * @param type  the type of event.
     * @param percent  the percentage of completion.
     */
    public ChartProgressEvent(Object source, AFreeChart chart, int type,
                              int percent) {
        super(source);
        this.chart = chart;
        this.type = type;
    }

    /**
     * Returns the chart that generated the change event.
     *
     * @return The chart that generated the change event.
     */
    public AFreeChart getChart() {
        return this.chart;
    }

    /**
     * Sets the chart that generated the change event.
     *
     * @param chart  the chart that generated the event.
     */
    public void setChart(AFreeChart chart) {
        this.chart = chart;
    }

    /**
     * Returns the event type.
     *
     * @return The event type.
     */
    public int getType() {
        return this.type;
    }

    /**
     * Sets the event type.
     *
     * @param type  the event type.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Returns the percentage complete.
     *
     * @return The percentage complete.
     */
    public int getPercent() {
        return this.percent;
    }

    /**
     * Sets the percentage complete.
     *
     * @param percent  the percentage.
     */
    public void setPercent(int percent) {
        this.percent = percent;
    }

}
