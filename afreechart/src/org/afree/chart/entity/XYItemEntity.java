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
 * -----------------
 * XYItemEntity.java
 * -----------------
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
 * (C) Copyright 2002-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes:
 * --------
 * 23-May-2002 : Version 1 (DG);
 * 12-Jun-2002 : Added accessor methods and Javadoc comments (DG);
 * 26-Jun-2002 : Added getImageMapAreaTag() method (DG);
 * 05-Aug-2002 : Added new constructor to populate URLText
 *               Moved getImageMapAreaTag() to ChartEntity (superclass) (RA);
 * 03-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 30-Jun-2003 : Added XYDataset reference (CZ);
 * 20-May-2004 : Added equals() and clone() methods and implemented
 *               Serializable (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 *
 */

package org.afree.chart.entity;

import org.afree.data.xy.XYDataset;
import org.afree.graphics.geom.Shape;





/**
 * A chart entity that represents one item within an
 * {@link org.afree.chart.plot.XYPlot}.
 */
public class XYItemEntity extends ChartEntity {

    /** For serialization. */
    private static final long serialVersionUID = -3870862224880283771L;

    /** The dataset. */
    private transient XYDataset dataset;

    /** The series. */
    private int series;

    /** The item. */
    private int item;

    /**
     * Creates a new entity.
     *
     * @param area  the area.
     * @param dataset  the dataset.
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * @param toolTipText  the tool tip text.
     * @param urlText  the URL text for HTML image maps.
     */
    public XYItemEntity(Shape area,
                        XYDataset dataset, int series, int item,
                        String toolTipText, String urlText) {
        super(area, toolTipText, urlText);
        this.dataset = dataset;
        this.series = series;
        this.item = item;
    }

    /**
     * Returns the dataset this entity refers to.
     *
     * @return The dataset.
     */
    public XYDataset getDataset() {
        return this.dataset;
    }

    /**
     * Sets the dataset this entity refers to.
     *
     * @param dataset  the dataset.
     */
    public void setDataset(XYDataset dataset) {
        this.dataset = dataset;
    }

    /**
     * Returns the series index.
     *
     * @return The series index.
     */
    public int getSeriesIndex() {
        return this.series;
    }

    /**
     * Sets the series index.
     *
     * @param series the series index (zero-based).
     */
    public void setSeriesIndex(int series) {
        this.series = series;
    }

    /**
     * Returns the item index.
     *
     * @return The item index.
     */
    public int getItem() {
        return this.item;
    }

    /**
     * Sets the item index.
     *
     * @param item the item index (zero-based).
     */
    public void setItem(int item) {
        this.item = item;
    }

    /**
     * Tests the entity for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof XYItemEntity && super.equals(obj)) {
            XYItemEntity ie = (XYItemEntity) obj;
            if (this.series != ie.series) {
                return false;
            }
            if (this.item != ie.item) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of this instance, useful for debugging
     * purposes.
     *
     * @return A string.
     */
    public String toString() {
        return "XYItemEntity: series = " + getSeriesIndex() + ", item = "
            + getItem() + ", dataset = " + getDataset();
    }

}
