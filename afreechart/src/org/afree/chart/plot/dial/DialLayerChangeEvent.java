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
 * -------------------------
 * DialLayerChangeEvent.java
 * -------------------------
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
 * 06-Nov-2006 : Version 1 (DG);
 *
 */

package org.afree.chart.plot.dial;

import org.afree.chart.event.ChartChangeEvent;

/**
 * An event that can be forwarded to any {@link DialLayerChangeListener} to
 * signal a change to a {@link DialLayer}.
 *
 * @since JFreeChart 1.0.7
 */
public class DialLayerChangeEvent extends ChartChangeEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 6963655309014003360L;
    /** The dial layer that generated the event. */
    private DialLayer layer;

    /**
     * Creates a new instance.
     *
     * @param layer  the dial layer that generated the event.
     */
    public DialLayerChangeEvent(DialLayer layer) {
        super(layer);
        this.layer = layer;
    }

    /**
     * Returns the layer that generated the event.
     *
     * @return The layer that generated the event.
     */
    public DialLayer getDialLayer() {
        return this.layer;
    }

}
