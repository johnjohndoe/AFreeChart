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
 * LegendItemEntity.java
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
 * (C) Copyright 2003-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 05-Jun-2003 : Version 1 (DG);
 * 20-May-2004 : Added equals() method and implemented Cloneable and
 *               Serializable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-May-2007 : Added dataset and seriesKey fields (DG);
 *
 */

package org.afree.chart.entity;

import java.io.Serializable;


import org.afree.util.ObjectUtilities;
import org.afree.data.general.Dataset;
import org.afree.graphics.geom.Shape;

/**
 * An entity that represents an item within a legend.
 */
public class LegendItemEntity extends ChartEntity implements Cloneable,
        Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -7435683933545666702L;

    /**
     * The dataset.
     * 
     * @since JFreeChart 1.0.6
     */
    private Dataset dataset;

    /**
     * The series key.
     * 
     * @since JFreeChart 1.0.6
     */
    private Comparable seriesKey;

    /** The series index. */
    private int seriesIndex;

    /**
     * Creates a legend item entity.
     * 
     * @param area
     *            the area.
     */
    public LegendItemEntity(Shape area) {
        super(area);
    }

    /**
     * Returns a reference to the dataset that this legend item is derived from.
     * 
     * @return The dataset.
     * 
     * @since JFreeChart 1.0.6
     * 
     * @see #setDataset(Dataset)
     */
    public Dataset getDataset() {
        return this.dataset;
    }

    /**
     * Sets a reference to the dataset that this legend item is derived from.
     * 
     * @param dataset
     *            the dataset.
     * 
     * @since JFreeChart 1.0.6
     */
    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    /**
     * Returns the series key that identifies the legend item.
     * 
     * @return The series key.
     * 
     * @since JFreeChart 1.0.6
     * 
     * @see #setSeriesKey(Comparable)
     */
    public Comparable getSeriesKey() {
        return this.seriesKey;
    }

    /**
     * Sets the key for the series.
     * 
     * @param key
     *            the key.
     * 
     * @since JFreeChart 1.0.6
     * 
     * @see #getSeriesKey()
     */
    public void setSeriesKey(Comparable key) {
        this.seriesKey = key;
    }

    /**
     * Sets the series index.
     * 
     * @param index
     *            the series index.
     * 
     * @see #getSeriesIndex()
     * 
     * @deprecated As of JFreeChart 1.0.6, use the {@link #setSeriesKey(Comparable)}
     *             method.
     */
    public void setSeriesIndex(int index) {
        this.seriesIndex = index;
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LegendItemEntity)) {
            return false;
        }
        LegendItemEntity that = (LegendItemEntity) obj;
        if (!ObjectUtilities.equal(this.seriesKey, that.seriesKey)) {
            return false;
        }
        if (this.seriesIndex != that.seriesIndex) {
            return false;
        }
        if (!ObjectUtilities.equal(this.dataset, that.dataset)) {
            return false;
        }
        return super.equals(obj);
    }
    
    
    /**
     * Returns a clone of the entity.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if there is a problem cloning the
     *         object.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }    
    
}
