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
 * -----------------------------------------
 * StandardCategorySeriesLabelGenerator.java
 * -----------------------------------------
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
 * 20-Apr-2005 : Version 1 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 03-May-2006 : Fixed equals() method (bug 1481102) (DG);
 * 31-Mar-2008 : Added hashCode() method to appease FindBugs (DG);
 *
 */

package org.afree.chart.labels;

import java.io.Serializable;
import java.text.MessageFormat;

import org.afree.data.category.CategoryDataset;

/**
 * A standard series label generator for plots that use data from a
 * {@link org.afree.data.category.CategoryDataset}.
 */
public class StandardCategorySeriesLabelGenerator implements
        CategorySeriesLabelGenerator, Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 4630760091523940820L;

    /** The default item label format. */
    public static final String DEFAULT_LABEL_FORMAT = "{0}";

    /** The format pattern. */
    private String formatPattern;

    /**
     * Creates a default series label generator (uses
     * {@link #DEFAULT_LABEL_FORMAT}).
     */
    public StandardCategorySeriesLabelGenerator() {
        this(DEFAULT_LABEL_FORMAT);
    }

    /**
     * Creates a new series label generator.
     * 
     * @param format
     *            the format pattern (<code>null</code> not permitted).
     */
    public StandardCategorySeriesLabelGenerator(String format) {
        if (format == null) {
            throw new IllegalArgumentException("Null 'format' argument.");
        }
        this.formatPattern = format;
    }

    /**
     * Generates a label for the specified series.
     * 
     * @param dataset
     *            the dataset (<code>null</code> not permitted).
     * @param series
     *            the series.
     * 
     * @return A series label.
     */
    public String generateLabel(CategoryDataset dataset, int series) {
        if (dataset == null) {
            throw new IllegalArgumentException("Null 'dataset' argument.");
        }
        String label = MessageFormat.format(this.formatPattern,
                createItemArray(dataset, series));
        return label;
    }

    /**
     * Creates the array of items that can be passed to the
     * {@link MessageFormat} class for creating labels.
     * 
     * @param dataset
     *            the dataset (<code>null</code> not permitted).
     * @param series
     *            the series (zero-based index).
     * 
     * @return The items (never <code>null</code>).
     */
    protected Object[] createItemArray(CategoryDataset dataset, int series) {
        Object[] result = new Object[1];
        result[0] = dataset.getRowKey(series).toString();
        return result;
    }

}
