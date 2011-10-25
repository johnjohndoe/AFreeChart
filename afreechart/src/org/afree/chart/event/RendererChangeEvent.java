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
 * ------------------------
 * RendererChangeEvent.java
 * ------------------------
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
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -; *
 * Changes
 * -------
 * 23-Oct-2003 : Version 1 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 04-Apr-2007 : Fixed typo in API docs (DG);
 * 26-Mar-2009 : Added flag to signal visible series change (DG);
 *
 */

package org.afree.chart.event;

/**
 * An event that can be forwarded to any {@link RendererChangeListener} to
 * signal a change to a renderer.
 */
public class RendererChangeEvent extends ChartChangeEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 3863107569056415393L;

    /** The renderer that generated the event. */
    private Object renderer;

    /**
     * A flag that indicates whether this event relates to a change in the
     * series visibility. If so, the receiver (if it is a plot) may want to
     * update the axis bounds.
     * 
     * @since JFreeChart 1.0.13
     */
    private boolean seriesVisibilityChanged;

    /**
     * Creates a new event.
     * 
     * @param renderer
     *            the renderer that generated the event.
     */
    public RendererChangeEvent(Object renderer) {
        this(renderer, false);
    }

    /**
     * Creates a new event.
     * 
     * @param renderer
     *            the renderer that generated the event.
     * @param seriesVisibilityChanged
     *            a flag that indicates whether or not the event relates to a
     *            change in the series visibility flags.
     */
    public RendererChangeEvent(Object renderer, boolean seriesVisibilityChanged) {
        super(renderer);
        this.renderer = renderer;
        this.seriesVisibilityChanged = seriesVisibilityChanged;
    }

    /**
     * Returns the renderer that generated the event.
     * 
     * @return The renderer that generated the event.
     */
    public Object getRenderer() {
        return this.renderer;
    }

    /**
     * Returns the flag that indicates whether or not the event relates to a
     * change in series visibility.
     * 
     * @return A boolean.
     * 
     * @since JFreeChart 1.0.13
     */
    public boolean getSeriesVisibilityChanged() {
        return this.seriesVisibilityChanged;
    }

}
