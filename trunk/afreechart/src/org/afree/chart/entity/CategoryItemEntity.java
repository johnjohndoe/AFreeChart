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
 * -----------------------
 * CategoryItemEntity.java
 * -----------------------
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
 * (C) Copyright 2002-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes:
 * --------
 * 23-May-2002 : Version 1 (DG);
 * 12-Jun-2002 : Added Javadoc comments (DG);
 * 26-Jun-2002 : Added getImageMapAreaTag() method (DG);
 * 05-Aug-2002 : Added new constructor to populate URLText
 *               Moved getImageMapAreaTag() to ChartEntity (superclass) (RA);
 * 03-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 30-Jul-2003 : Added CategoryDataset reference (CZ);
 * 20-May-2004 : Added equals() and clone() methods, and implemented
 *               Serializable (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-May-2007 : Updated to use row and column keys to identify item (DG);
 *
 */

package org.afree.chart.entity;

import java.io.Serializable;

import org.afree.data.category.CategoryDataset;
import org.afree.graphics.geom.Shape;

/**
 * A chart entity that represents one item within a category plot.
 */
public class CategoryItemEntity extends ChartEntity implements Cloneable,
        Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -8657249457902337349L;

    /** The dataset. */
    private CategoryDataset dataset;

    /**
     * The row key.
     * 
     * @since JFreeChart 1.0.6
     */
    private Comparable rowKey;

    /**
     * The column key.
     * 
     * @since JFreeChart 1.0.6
     */
    private Comparable columnKey;

    /**
     * Creates a new entity instance for an item in the specified dataset.
     * 
     * @param area
     *            the 'hotspot' area (<code>null</code> not permitted).
     * @param toolTipText
     *            the tool tip text.
     * @param urlText
     *            the URL text.
     * @param dataset
     *            the dataset (<code>null</code> not permitted).
     * @param rowKey
     *            the row key (<code>null</code> not permitted).
     * @param columnKey
     *            the column key (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.6
     */
    public CategoryItemEntity(Shape area, String toolTipText, String urlText,
            CategoryDataset dataset, Comparable rowKey, Comparable columnKey) {
        super(area, toolTipText, urlText);
        if (dataset == null) {
            throw new IllegalArgumentException("Null 'dataset' argument.");
        }
        this.dataset = dataset;
        this.rowKey = rowKey;
        this.columnKey = columnKey;

        dataset.getRowIndex(rowKey);
    }

    /**
     * Returns the dataset this entity refers to. This can be used to
     * differentiate between items in a chart that displays more than one
     * dataset.
     * 
     * @return The dataset (never <code>null</code>).
     * 
     * @see #setDataset(CategoryDataset)
     */
    public CategoryDataset getDataset() {
        return this.dataset;
    }

    /**
     * Sets the dataset this entity refers to.
     * 
     * @param dataset
     *            the dataset (<code>null</code> not permitted).
     * 
     * @see #getDataset()
     */
    public void setDataset(CategoryDataset dataset) {
        if (dataset == null) {
            throw new IllegalArgumentException("Null 'dataset' argument.");
        }
        this.dataset = dataset;
    }

    /**
     * Returns the row key.
     * 
     * @return The row key (never <code>null</code>).
     * 
     * @since JFreeChart 1.0.6
     * 
     * @see #setRowKey(Comparable)
     */
    public Comparable getRowKey() {
        return this.rowKey;
    }

    /**
     * Sets the row key.
     * 
     * @param rowKey
     *            the row key (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.6
     * 
     * @see #getRowKey()
     */
    public void setRowKey(Comparable rowKey) {
        this.rowKey = rowKey;
        this.dataset.getRowIndex(rowKey);
    }

    /**
     * Returns the column key.
     * 
     * @return The column key (never <code>null</code>).
     * 
     * @since JFreeChart 1.0.6
     * 
     * @see #setColumnKey(Comparable)
     */
    public Comparable getColumnKey() {
        return this.columnKey;
    }

    /**
     * Sets the column key.
     * 
     * @param columnKey
     *            the column key (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.6
     * 
     * @see #getColumnKey()
     */
    public void setColumnKey(Comparable columnKey) {
        this.columnKey = columnKey;
    }

    /**
     * Returns a string representing this object (useful for debugging
     * purposes).
     * 
     * @return A string (never <code>null</code>).
     */
    public String toString() {
        return "CategoryItemEntity: rowKey=" + this.rowKey + ", columnKey="
                + this.columnKey + ", dataset=" + this.dataset;
    }

}
