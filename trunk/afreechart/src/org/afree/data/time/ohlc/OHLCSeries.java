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
 * ---------------
 * OHLCSeries.java
 * ---------------
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
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 04-Dec-2006 : Version 1 (DG);
 *
 */

package org.afree.data.time.ohlc;


import java.util.Collections;

import org.afree.data.ComparableObjectItem;
import org.afree.data.ComparableObjectSeries;
import org.afree.data.time.RegularTimePeriod;



/**
 * A list of (RegularTimePeriod, open, high, low, close) data items.
 *
 * @since JFreeChart 1.0.4
 *
 * @see OHLCSeriesCollection
 */
public class OHLCSeries extends ComparableObjectSeries {

    /**
     * 
     */
    private static final long serialVersionUID = 1019559560481915160L;

    /**
     * Creates a new empty series.  By default, items added to the series will
     * be sorted into ascending order by period, and duplicate periods will
     * not be allowed.
     *
     * @param key  the series key (<code>null</code> not permitted).
     */
    public OHLCSeries(Comparable key) {
        super(key, true, false);
    }

    /**
     * Returns the time period for the specified item.
     *
     * @param index  the item index.
     *
     * @return The time period.
     */
    public RegularTimePeriod getPeriod(int index) {
        final OHLCItem item = (OHLCItem) getDataItem(index);
        return item.getPeriod();
    }

    /**
     * Returns the data item at the specified index.
     *
     * @param index  the item index.
     *
     * @return The data item.
     */
    public ComparableObjectItem getDataItem(int index) {
        return super.getDataItem(index);
    }

    /**
     * Adds a data item to the series.
     *
     * @param period  the period.
     * @param open  the open-value.
     * @param high  the high-value.
     * @param low  the low-value.
     * @param close  the close-value.
     */
    public void add(RegularTimePeriod period, double open, double high,
            double low, double close) {
        if (getItemCount() > 0) {
            OHLCItem item0 = (OHLCItem) this.getDataItem(0);
            if (!period.getClass().equals(item0.getPeriod().getClass())) {
                throw new IllegalArgumentException(
                        "Can't mix RegularTimePeriod class types.");
            }
        }
        super.add(new OHLCItem(period, open, high, low, close), true);
    }

    /**
     * Returns the index for the item (if any) that corresponds to a time
     * period.
     *
     * @param period  the time period (<code>null</code> not permitted).
     *
     * @return The index.
     */
    public int getIndex(RegularTimePeriod period) {
        if (period == null) {
            throw new IllegalArgumentException("Null 'period' argument.");
        }
        OHLCItem dummy = new OHLCItem(
              period, Double.MIN_VALUE,Double.MIN_VALUE,Double.MIN_VALUE,Double.MIN_VALUE);
        return Collections.binarySearch(this.data, dummy);
    }
}
