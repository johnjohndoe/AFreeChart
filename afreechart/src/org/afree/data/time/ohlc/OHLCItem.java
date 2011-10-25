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
 * -------------
 * OHLCItem.java
 * -------------
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


import org.afree.data.ComparableObjectItem;
import org.afree.data.time.RegularTimePeriod;

/**
 * An item representing data in the form (period, open, high, low, close).
 *
 * @since JFreeChart 1.0.4
 */
public class OHLCItem extends ComparableObjectItem {

    /**
     * 
     */
    private static final long serialVersionUID = -7885901766618036760L;

    /**
     * Creates a new instance of <code>OHLCItem</code>.
     *
     * @param period  the time period.
     * @param open  the open-value.
     * @param high  the high-value.
     * @param low  the low-value.
     * @param close  the close-value.
     */
    public OHLCItem(RegularTimePeriod period, double open, double high,
            double low, double close) {
        super(period, new OHLC(open, high, low, close));
    }

    /**
     * Returns the period.
     *
     * @return The period (never <code>null</code>).
     */
    public RegularTimePeriod getPeriod() {
        return (RegularTimePeriod) getComparable();
    }

    /**
     * Returns the y-value.
     *
     * @return The y-value.
     */
    public double getYValue() {
        return getCloseValue();
    }

    /**
     * Returns the open value.
     *
     * @return The open value.
     */
    public double getOpenValue() {
        OHLC ohlc = (OHLC) getObject();
        if (ohlc != null) {
            return ohlc.getOpen();
        }
        else {
            return Double.NaN;
        }
    }

    /**
     * Returns the high value.
     *
     * @return The high value.
     */
    public double getHighValue() {
        OHLC ohlc = (OHLC) getObject();
        if (ohlc != null) {
            return ohlc.getHigh();
        }
        else {
            return Double.NaN;
        }
    }

    /**
     * Returns the low value.
     *
     * @return The low value.
     */
    public double getLowValue() {
        OHLC ohlc = (OHLC) getObject();
        if (ohlc != null) {
            return ohlc.getLow();
        }
        else {
            return Double.NaN;
        }
    }

    /**
     * Returns the close value.
     *
     * @return The close value.
     */
    public double getCloseValue() {
        OHLC ohlc = (OHLC) getObject();
        if (ohlc != null) {
            return ohlc.getClose();
        }
        else {
            return Double.NaN;
        }
    }

}
