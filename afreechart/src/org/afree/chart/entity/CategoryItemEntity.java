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
 * CategoryItemEntity.java
 * -----------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2002-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *                   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
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
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
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
     * The series (zero-based index).
     * 
     * @deprecated As of 1.0.6, this field is redundant as you can derive the
     *             index from the <code>rowKey</code> field.
     */
    private int series;

    /**
     * The category.
     * 
     * @deprecated As of 1.0.6, this field is deprecated in favour of the
     *             <code>columnKey</code> field.
     */
    private Object category;

    /**
     * The category index.
     * 
     * @deprecated As of 1.0.6, this field is redundant as you can derive the
     *             index from the <code>columnKey</code> field.
     */
    private int categoryIndex;

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

        // populate the deprecated fields
        this.series = dataset.getRowIndex(rowKey);
        this.category = columnKey;
        this.categoryIndex = dataset.getColumnIndex(columnKey);
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
        // update the deprecated field
        this.series = this.dataset.getRowIndex(rowKey);
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
        // update the deprecated fields
        this.category = columnKey;
        this.categoryIndex = this.dataset.getColumnIndex(columnKey);
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
