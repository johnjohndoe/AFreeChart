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
 * ----------------------
 * IntervalXYDataset.java
 * ----------------------
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
 * (C) Copyright 2001-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  Mark Watson (www.markwatson.com);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 18-Oct-2001 : Version 1, thanks to Mark Watson (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc (DG);
 * 06-May-2004 : Added methods that return double primitives (DG);
 *
 */

package org.afree.data.xy;

/**
 * An extension of the {@link XYDataset} interface that allows a range of data
 * to be defined for the X values, the Y values, or both the X and Y values.
 * This interface is used to support (among other things) bar plots against
 * numerical axes.
 */
public interface IntervalXYDataset extends XYDataset {

    /**
     * Returns the starting X value for the specified series and item.
     * 
     * @param series
     *            the series index (zero-based).
     * @param item
     *            the item index (zero-based).
     * 
     * @return The value.
     */
    public Number getStartX(int series, int item);

    /**
     * Returns the start x-value (as a double primitive) for an item within a
     * series.
     * 
     * @param series
     *            the series (zero-based index).
     * @param item
     *            the item (zero-based index).
     * 
     * @return The start x-value.
     */
    public double getStartXValue(int series, int item);

    /**
     * Returns the ending X value for the specified series and item.
     * 
     * @param series
     *            the series index (zero-based).
     * @param item
     *            the item index (zero-based).
     * 
     * @return The value.
     */
    public Number getEndX(int series, int item);

    /**
     * Returns the end x-value (as a double primitive) for an item within a
     * series.
     * 
     * @param series
     *            the series index (zero-based).
     * @param item
     *            the item index (zero-based).
     * 
     * @return The end x-value.
     */
    public double getEndXValue(int series, int item);

    /**
     * Returns the starting Y value for the specified series and item.
     * 
     * @param series
     *            the series index (zero-based).
     * @param item
     *            the item index (zero-based).
     * 
     * @return The value.
     */
    public Number getStartY(int series, int item);

    /**
     * Returns the start y-value (as a double primitive) for an item within a
     * series.
     * 
     * @param series
     *            the series index (zero-based).
     * @param item
     *            the item index (zero-based).
     * 
     * @return The start y-value.
     */
    public double getStartYValue(int series, int item);

    /**
     * Returns the ending Y value for the specified series and item.
     * 
     * @param series
     *            the series index (zero-based).
     * @param item
     *            the item index (zero-based).
     * 
     * @return The value.
     */
    public Number getEndY(int series, int item);

    /**
     * Returns the end y-value (as a double primitive) for an item within a
     * series.
     * 
     * @param series
     *            the series index (zero-based).
     * @param item
     *            the item index (zero-based).
     * 
     * @return The end y-value.
     */
    public double getEndYValue(int series, int item);

}
