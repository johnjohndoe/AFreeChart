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
 * ----------------------------
 * IntervalCategoryDataset.java
 * ----------------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2002-2008, by Eduard Martinescu and Contributors.
 *
 * Original Author:  Eduard Martinescu;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes
 * -------
 * 19-Mar-2002 : Version 1 contributed by Eduard Martinescu.  The interface
 *               name and method names have been renamed to be consistent with
 *               existing interfaces (DG);
 * 06-Jun-2002 : Updated Javadoc comments (DG);
 * 24-Oct-2002 : Categories and series are now indexed by int or Comparable,
 *               following changes made to the CategoryDataset interface (DG);
 * 12-May-2008 : Updated API docs (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.data.category;

/**
 * A category dataset that defines a value range for each series/category
 * combination.
 */
public interface IntervalCategoryDataset extends CategoryDataset {

    /**
     * Returns the start value for the interval for a given series and category.
     * 
     * @param series
     *            the series (zero-based index).
     * @param category
     *            the category (zero-based index).
     * 
     * @return The start value (possibly <code>null</code>).
     * 
     * @see #getEndValue(int, int)
     */
    public Number getStartValue(int series, int category);

    /**
     * Returns the start value for the interval for a given series and category.
     * 
     * @param series
     *            the series key.
     * @param category
     *            the category key.
     * 
     * @return The start value (possibly <code>null</code>).
     * 
     * @see #getEndValue(Comparable, Comparable)
     */
    public Number getStartValue(Comparable series, Comparable category);

    /**
     * Returns the end value for the interval for a given series and category.
     * 
     * @param series
     *            the series (zero-based index).
     * @param category
     *            the category (zero-based index).
     * 
     * @return The end value (possibly <code>null</code>).
     * 
     * @see #getStartValue(int, int)
     */
    public Number getEndValue(int series, int category);

    /**
     * Returns the end value for the interval for a given series and category.
     * 
     * @param series
     *            the series key.
     * @param category
     *            the category key.
     * 
     * @return The end value (possibly <code>null</code>).
     * 
     * @see #getStartValue(Comparable, Comparable)
     */
    public Number getEndValue(Comparable series, Comparable category);

}
