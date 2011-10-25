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
 * ---------------------------------------
 * AbstractCategoryItemLabelGenerator.java
 * ---------------------------------------
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
 * (C) Copyright 2005-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-May-2004 : Version 1, distilled from StandardCategoryLabelGenerator (DG);
 * 31-Jan-2005 : Added methods to return row and column labels (DG);
 * 17-May-2005 : Added percentage to item array (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 03-May-2006 : Added new constructor (DG);
 * 23-Nov-2007 : Implemented hashCode() (DG);
 *
 */

package org.afree.chart.labels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;

import org.afree.data.category.CategoryDataset;
import org.afree.data.DataUtilities;



/**
 * A base class that can be used to create a label or tooltip generator that
 * can be assigned to a
 * {@link org.afree.chart.renderer.category.CategoryItemRenderer}.
 */
public abstract class AbstractCategoryItemLabelGenerator
        implements  Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -7108591260223293197L;

    /**
     * The label format string used by a <code>MessageFormat</code> object to
     * combine the standard items:  {0} = series name, {1} = category,
     * {2} = value, {3} = value as a percentage of the column total.
     */
    private String labelFormat;

    /** The string used to represent a null value. */
    private String nullValueString;

    /**
     * A number formatter used to preformat the value before it is passed to
     * the MessageFormat object.
     */
    private NumberFormat numberFormat;

    /**
     * A date formatter used to preformat the value before it is passed to the
     * MessageFormat object.
     */
    private DateFormat dateFormat;

    /**
     * A number formatter used to preformat the percentage value before it is
     * passed to the MessageFormat object.
     */
    private NumberFormat percentFormat;

    /**
     * Creates a label generator with the specified number formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not
     *                     permitted).
     * @param formatter  the number formatter (<code>null</code> not permitted).
     */
    protected AbstractCategoryItemLabelGenerator(String labelFormat,
                                                 NumberFormat formatter) {
        this(labelFormat, formatter, NumberFormat.getPercentInstance());
    }

    /**
     * Creates a label generator with the specified number formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not
     *                     permitted).
     * @param formatter  the number formatter (<code>null</code> not permitted).
     * @param percentFormatter  the percent formatter (<code>null</code> not
     *     permitted).
     *
     * @since JFreeChart 1.0.2
     */
    protected AbstractCategoryItemLabelGenerator(String labelFormat,
            NumberFormat formatter, NumberFormat percentFormatter) {
        if (labelFormat == null) {
            throw new IllegalArgumentException("Null 'labelFormat' argument.");
        }
        if (formatter == null) {
            throw new IllegalArgumentException("Null 'formatter' argument.");
        }
        if (percentFormatter == null) {
            throw new IllegalArgumentException(
                    "Null 'percentFormatter' argument.");
        }
        this.labelFormat = labelFormat;
        this.numberFormat = formatter;
        this.percentFormat = percentFormatter;
        this.dateFormat = null;
        this.nullValueString = "-";
    }

    /**
     * Creates a label generator with the specified date formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not
     *                     permitted).
     * @param formatter  the date formatter (<code>null</code> not permitted).
     */
    protected AbstractCategoryItemLabelGenerator(String labelFormat,
                                                 DateFormat formatter) {
        if (labelFormat == null) {
            throw new IllegalArgumentException("Null 'labelFormat' argument.");
        }
        if (formatter == null) {
            throw new IllegalArgumentException("Null 'formatter' argument.");
        }
        this.labelFormat = labelFormat;
        this.numberFormat = null;
        this.percentFormat = NumberFormat.getPercentInstance();
        this.dateFormat = formatter;
        this.nullValueString = "-";
    }

    /**
     * Generates a label for the specified row.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param row  the row index (zero-based).
     *
     * @return The label.
     */
    public String generateRowLabel(CategoryDataset dataset, int row) {
        return dataset.getRowKey(row).toString();
    }

    /**
     * Generates a label for the specified row.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param column  the column index (zero-based).
     *
     * @return The label.
     */
    public String generateColumnLabel(CategoryDataset dataset, int column) {
        return dataset.getColumnKey(column).toString();
    }

    /**
     * Returns the label format string.
     *
     * @return The label format string (never <code>null</code>).
     */
    public String getLabelFormat() {
        return this.labelFormat;
    }

    /**
     * Returns the number formatter.
     *
     * @return The number formatter (possibly <code>null</code>).
     */
    public NumberFormat getNumberFormat() {
        return this.numberFormat;
    }

    /**
     * Returns the date formatter.
     *
     * @return The date formatter (possibly <code>null</code>).
     */
    public DateFormat getDateFormat() {
        return this.dateFormat;
    }

    /**
     * Generates a for the specified item.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The label (possibly <code>null</code>).
     */
    protected String generateLabelString(CategoryDataset dataset,
                                         int row, int column) {
        if (dataset == null) {
            throw new IllegalArgumentException("Null 'dataset' argument.");
        }
        String result = null;
        Object[] items = createItemArray(dataset, row, column);
        result = MessageFormat.format(this.labelFormat, items);
        return result;

    }

    /**
     * Creates the array of items that can be passed to the
     * {@link MessageFormat} class for creating labels.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The items (never <code>null</code>).
     */
    protected Object[] createItemArray(CategoryDataset dataset,
                                       int row, int column) {
        Object[] result = new Object[4];
        result[0] = dataset.getRowKey(row).toString();
        result[1] = dataset.getColumnKey(column).toString();
        Number value = dataset.getValue(row, column);
        if (value != null) {
            if (this.numberFormat != null) {
                result[2] = this.numberFormat.format(value);
            }
            else if (this.dateFormat != null) {
                result[2] = this.dateFormat.format(value);
            }
        }
        else {
            result[2] = this.nullValueString;
        }
        if (value != null) {
            double total = DataUtilities.calculateColumnTotal(dataset, column);
            double percent = value.doubleValue() / total;
            result[3] = this.percentFormat.format(percent);
        }

        return result;
    }

   
}
