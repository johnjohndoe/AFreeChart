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
 * ------------------------
 * XYItemRendererState.java
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
 * (C) Copyright 2003-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Ulrich Voigt;
 *                   Greg Darke;
 *
 * Changes:
 * --------
 * 07-Oct-2003 : Version 1 (DG);
 * 27-Jan-2004 : Added workingLine attribute (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 04-May-2007 : Added processVisibleItemsOnly flag (DG);
 * 09-Jul-2008 : Added start/endSeriesPass() methods - see patch 1997549 by
 *               Ulrich Voigt (DG);
 * 19-Sep-2008 : Added first and last item indices, based on patch by Greg
 *               Darke (DG);
 *
 */

package org.afree.chart.renderer.xy;



import org.afree.data.xy.XYDataset;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.RendererState;
import org.afree.graphics.geom.LineShape;



/**
 * The state for an {@link XYItemRenderer}.
 */
public class XYItemRendererState extends RendererState {

    /**
     * The first item in the series that will be displayed.
     *
     * @since JFreeChart 1.0.11
     */
    private int firstItemIndex;

    /**
     * The last item in the current series that will be displayed.
     *
     * @since JFreeChart 1.0.11
     */
    private int lastItemIndex;

    /**
     * A line object that the renderer can reuse to save instantiating a lot
     * of objects.
     */
    public LineShape workingLine;

    /**
     * A flag that controls whether the plot should pass ALL data items to the
     * renderer, or just the items that will be visible.
     *
     * @since JFreeChart 1.0.6
     */
    private boolean processVisibleItemsOnly;

    /**
     * Creates a new state.
     *
     * @param info  the plot rendering info.
     */
    public XYItemRendererState(PlotRenderingInfo info) {
        super(info);
        this.workingLine = new LineShape();
        this.processVisibleItemsOnly = true;
    }

    /**
     * Returns the flag that controls whether the plot passes all data
     * items in each series to the renderer, or just the visible items.  The
     * default value is <code>true</code>.
     *
     * @return A boolean.
     *
     * @since JFreeChart 1.0.6
     *
     * @see #setProcessVisibleItemsOnly(boolean)
     */
    public boolean getProcessVisibleItemsOnly() {
        return this.processVisibleItemsOnly;
    }

    /**
     * Sets the flag that controls whether the plot passes all data
     * items in each series to the renderer, or just the visible items.
     *
     * @param flag  the new flag value.
     *
     * @since JFreeChart 1.0.6
     */
    public void setProcessVisibleItemsOnly(boolean flag) {
        this.processVisibleItemsOnly = flag;
    }

    /**
     * Returns the first item index (this is updated with each call to
     * {@link #startSeriesPass(XYDataset, int, int, int, int, int)}.
     *
     * @return The first item index.
     *
     * @since JFreeChart 1.0.11
     */
    public int getFirstItemIndex() {
        return this.firstItemIndex;
    }

    /**
     * Returns the last item index (this is updated with each call to
     * {@link #startSeriesPass(XYDataset, int, int, int, int, int)}.
     *
     * @return The last item index.
     *
     * @since JFreeChart 1.0.11
     */
    public int getLastItemIndex() {
        return this.lastItemIndex;
    }

    /**
     * This method is called by the {@link XYPlot} when it starts a pass
     * through the (visible) items in a series.  The default implementation
     * records the first and last item indices - override this method to
     * implement additional specialised behaviour.
     *
     * @param dataset  the dataset.
     * @param series  the series index.
     * @param firstItem  the index of the first item in the series.
     * @param lastItem  the index of the last item in the series.
     * @param pass  the pass index.
     * @param passCount  the number of passes.
     *
     * @see #endSeriesPass(XYDataset, int, int, int, int, int)
     *
     * @since JFreeChart 1.0.11
     */
    public void startSeriesPass(XYDataset dataset, int series, int firstItem,
            int lastItem, int pass, int passCount) {
        this.firstItemIndex = firstItem;
        this.lastItemIndex = lastItem;
    }

    /**
     * This method is called by the {@link XYPlot} when it ends a pass
     * through the (visible) items in a series.  The default implementation
     * does nothing, but you can override this method to implement specialised
     * behaviour.
     *
     * @param dataset  the dataset.
     * @param series  the series index.
     * @param firstItem  the index of the first item in the series.
     * @param lastItem  the index of the last item in the series.
     * @param pass  the pass index.
     * @param passCount  the number of passes.
     *
     * @see #startSeriesPass(XYDataset, int, int, int, int, int)
     *
     * @since JFreeChart 1.0.11
     */
    public void endSeriesPass(XYDataset dataset, int series, int firstItem,
            int lastItem, int pass, int passCount) {
        // do nothing...this is just a hook for subclasses
    }

}
