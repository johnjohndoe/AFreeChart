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
 * ---------------------
 * ChartChangeEvent.java
 * ---------------------
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
 *               Change event type names (DG);
 * 09-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 18-Feb-2005 : Changed the type from int to ChartChangeEventType (DG);
 *
 */

package org.afree.chart.event;

import java.util.EventObject;

import org.afree.chart.AFreeChart;

/**
 * A change event that encapsulates information about a change to a chart.
 */
public class ChartChangeEvent extends EventObject {

    /**
     * 
     */
    private static final long serialVersionUID = 5700666833282609814L;

    /** The type of event. */
    private ChartChangeEventType type;

    /** The chart that generated the event. */
    private AFreeChart chart;

    /**
     * Creates a new chart change event.
     * 
     * @param source
     *            the source of the event (could be the chart, a title, an axis
     *            etc.)
     */
    public ChartChangeEvent(Object source) {
        this(source, null, ChartChangeEventType.GENERAL);
    }

    /**
     * Creates a new chart change event.
     * 
     * @param source
     *            the source of the event (could be the chart, a title, an axis
     *            etc.)
     * @param chart
     *            the chart that generated the event.
     */
    public ChartChangeEvent(Object source, AFreeChart chart) {
        this(source, chart, ChartChangeEventType.GENERAL);
    }

    /**
     * Creates a new chart change event.
     * 
     * @param source
     *            the source of the event (could be the chart, a title, an axis
     *            etc.)
     * @param chart
     *            the chart that generated the event.
     * @param type
     *            the type of event.
     */
    public ChartChangeEvent(Object source, AFreeChart chart,
            ChartChangeEventType type) {
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
     * @param chart
     *            the chart that generated the event.
     */
    public void setChart(AFreeChart chart) {
        this.chart = chart;
    }

    /**
     * Returns the event type.
     * 
     * @return The event type.
     */
    public ChartChangeEventType getType() {
        return this.type;
    }

    /**
     * Sets the event type.
     * 
     * @param type
     *            the event type.
     */
    public void setType(ChartChangeEventType type) {
        this.type = type;
    }

}
