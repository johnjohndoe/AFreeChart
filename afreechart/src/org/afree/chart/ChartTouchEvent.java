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
 * ChartTouchEvent.java
 * --------------------
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 14-Jan-2011 : renamed class and method name
 * 14-Jan-2011 : Updated API docs
 *  
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Alex Weber;
 *
 * Changes
 * -------
 * 27-May-2002 : Version 1, incorporating code and ideas by Alex Weber (DG);
 * 13-Jun-2002 : Added Javadoc comments (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 05-Nov-2002 : Added a reference to the source chart (DG);
 * 13-Jul-2004 : Now extends EventObject and implements Serializable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 24-May-2007 : Updated API docs (DG);
 *
 */

package org.afree.chart;

import java.io.Serializable;
import java.util.EventObject;

import android.view.MotionEvent;

import org.afree.chart.entity.ChartEntity;


/**
 * A touch event for a chart that is displayed in a {@link DemoView}.
 *
 * @see ChartTouchListener
 */
public class ChartTouchEvent extends EventObject implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -682393837314562149L;

    /** The chart that the touch event relates to. */
    private AFreeChart chart;

    /** The Java touch event that triggered this event. */
    private MotionEvent trigger;

    /** The chart entity (if any). */
    private ChartEntity entity;

    /**
     * Constructs a new event.
     *
     * @param chart  the source chart (<code>null</code> not permitted).
     * @param trigger  the touch event that triggered this event
     *                 (<code>null</code> not permitted).
     * @param entity  the chart entity (if any) under the touch point
     *                (<code>null</code> permitted).
     */
    public ChartTouchEvent(AFreeChart chart, MotionEvent trigger,
                           ChartEntity entity) {
        super(chart);
        this.chart = chart;
        this.trigger = trigger;
        this.entity = entity;
    }

    /**
     * Returns the chart that the touch event relates to.
     *
     * @return The chart (never <code>null</code>).
     */
    public AFreeChart getChart() {
        return this.chart;
    }

    /**
     * Returns the touch event that triggered this event.
     *
     * @return The event (never <code>null</code>).
     */
    public MotionEvent getTrigger() {
        return this.trigger;
    }

    /**
     * Returns the chart entity (if any) under the touch point.
     *
     * @return The chart entity (possibly <code>null</code>).
     */
    public ChartEntity getEntity() {
        return this.entity;
    }

}
