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
 * SlidingCategoryPlot.java
 * ------------------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  Kevin Moray;
 * Contributor(s):   Niwano Masayoshi(NM);
 *
 * Changes
 * -------
 * 23-Apr-2010 : Version 0.0.4 (NM);
 */

package org.afree.chart.plot;

import java.io.Serializable;

import org.afree.chart.axis.CategoryAxis;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.renderer.category.CategoryItemRenderer;
import org.afree.data.category.CategoryDataset;
import org.afree.data.category.SlidingCategoryDataset;

import android.graphics.PointF;

/**
 * A plotting class that uses data from a {@link SlidingCategoryDataset} and
 * renders each data item using a {@link CategoryItemRenderer}.
 */
public class SlidingCategoryPlot extends CategoryPlot implements Serializable {

    private static final long serialVersionUID = -9098874673223808398L;

    /** Default of category slide percentage. */
    private static final float SLIDE_RATIO_DEFAULT = 0.025f;

    /** Default of pinch in/out sensing interval. */
    private static final long ZOOM_INTERVAL_DEFAULT = 200;

    /** The column Number */
    private int mColumnNumber;

    /** The previous frame time */
    private long mPreviousTime;

    /** The category slide percentage. */
    private float mSlideRatio;

    /** The pinch in/out sensing interval. */
    private long mZoomInterval;

    /**
     * Default constructor.
     */
    public SlidingCategoryPlot() {
        this(null, null, null, null);
    }

    /**
     * Creates a new plot.
     * 
     * @param dataset
     *            the dataset (<code>null</code> permitted).
     * @param domainAxis
     *            the domain axis (<code>null</code> permitted).
     * @param rangeAxis
     *            the range axis (<code>null</code> permitted).
     * @param renderer
     *            the item renderer (<code>null</code> permitted).
     * 
     */
    public SlidingCategoryPlot(CategoryDataset dataset, CategoryAxis domainAxis,
            ValueAxis rangeAxis, CategoryItemRenderer renderer) {
        super(dataset, domainAxis, rangeAxis, renderer);
        this.mPreviousTime = System.currentTimeMillis();
        
        if (dataset != null) {
            setDataset(dataset);
        }
        
        mSlideRatio = SLIDE_RATIO_DEFAULT;
        mZoomInterval = ZOOM_INTERVAL_DEFAULT;
    }

    /**
     * Sets slide ratio.<br />
     * 
     * <p>
     * Specify the percentage movement to slide.<br />
     * Percentage in the data area for the movement of the finger.<br />
     * (0.0f <= slideRatio <= 1.0)
     * </p>
     * 
     * @param slideRatio The slide ratio.
     *              if 'slideRatio < 0.0f' then sets 0.0f,
     *              if 'slideRatio > 1.0f' then sets 1.0f.
     * 
     * @see #getSlideRatio()
     */
    public void setSlideRatio(float slideRatio) {
        if (slideRatio < 0.0f) {
            slideRatio = 0.0f;
        } else if (slideRatio > 1.0f){
            slideRatio = 1.0f;
        }
        
        mSlideRatio = slideRatio;
    }

    /**
     * Return the slide ratio.
     * 
     * @return The slide ratio.
     * 
     * @see #setSlideRatio()
     */
    public float getSlideRatio() {
        return mSlideRatio;
    }

    /**
     * Sets zoom interval.<br >
     * 
     * <p>
     * Checks pinch-in and pinch-out at this interval.<br />
     * (zoomInterval > 0)<br />
     * if 'zoomInterval <= 0' then sets 1.
     * </p>
     * 
     * @param zoomInterval The zoom interval.
     * 
     * @see #getZoomInterval()
     */
    public void setZoomInterval(long zoomInterval) {
        if (zoomInterval <= 0) {
            zoomInterval = 1;
        }
        mZoomInterval = zoomInterval;
    }

    /**
     * Return the zoom interval.
     * 
     * @return The zoom interval.
     * 
     * @see #setZoomInterval(long)
     */
    public long getZoomInterval() {
        return mZoomInterval;
    }
    
    @Override
    public void setDataset(int index, CategoryDataset dataset) {
        mColumnNumber = dataset.getColumnCount();
        super.setDataset(index, dataset);
    }

    @Override
    public boolean isDomainMovable() {
        return true;
    }

    @Override
    public boolean isRangeMovable() {
        return super.isRangeMovable();
    }

    @Override
    public SlidingCategoryDataset getDataset() {
        return (SlidingCategoryDataset) super.getDataset();
    }

    @Override
    public void moveDomainAxes(double movePercent, PlotRenderingInfo info, PointF source) {
        if (movePercent > mSlideRatio) {
            // next
            SlidingCategoryDataset dataset = this.getDataset();
            int index = dataset.getFirstCategoryIndex();
            int window = dataset.getMaximumCategoryCount();
            if (index + window < dataset.getAllColumnCount()) {
                dataset.setFirstCategoryIndex(index + 1);
            }
        } else if (movePercent < -mSlideRatio) {
            // previous
            SlidingCategoryDataset dataset = this.getDataset();
            int index = dataset.getFirstCategoryIndex();
            if (index > 0) {
                dataset.setFirstCategoryIndex(index - 1);
            }
        }
    }

    @Override
    public void moveRangeAxes(double movePercent, PlotRenderingInfo info, PointF source) {
        super.moveRangeAxes(movePercent, info, source);
    }

    @Override
    public boolean isDomainZoomable() {
        return true;
    }

    @Override
    public boolean isRangeZoomable() {
        return super.isRangeZoomable();
    }

    @Override
    public void zoomDomainAxes(double factor, PlotRenderingInfo state, PointF source) {
        this.zoomDomainAxes(factor, state, source, false);
    }

    @Override
    public void zoomDomainAxes(double factor, PlotRenderingInfo info, PointF source,
            boolean useAnchor) {
        long now = System.currentTimeMillis();
        if (now - mPreviousTime < mZoomInterval) {
            return;
        }
        mPreviousTime = now;
        SlidingCategoryDataset dataset = this.getDataset();
        if (factor == 0.0) {
            // if 'factor == 0.0', autoAdjust.
            // This plot is not support domain auto adjust.
            return;
        } else if (factor > 1.0) {
            int columnNumber = dataset.getColumnCount();

            if (mColumnNumber > columnNumber) {
                int index = dataset.getFirstCategoryIndex();
                if (index > 0) {
                    dataset.setFirstCategoryIndex(index - 1);
                }
            }

            dataset.setMaximumCategoryCount(columnNumber + 1);
            mColumnNumber = columnNumber + 1;
        } else if (factor < 1.0) {
            // zoom
            int nbrColum = dataset.getColumnCount();

            if (nbrColum > 0) {
                dataset.setMaximumCategoryCount(nbrColum - 1);
            }
        }
    }
}
