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
 * --------------------------
 * AbstractSeriesDataset.java
 * --------------------------
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
 * (C) Copyright 2001-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 17-Nov-2001 : Version 1 (DG);
 * 28-Mar-2002 : Implemented SeriesChangeListener interface (DG);
 * 04-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 04-Feb-2003 : Removed redundant methods (DG);
 * 27-Mar-2003 : Implemented Serializable (DG);
 *
 */

package org.afree.data.general;

import java.io.Serializable;


/**
 * An abstract implementation of the {@link SeriesDataset} interface, containing
 * a mechanism for registering change listeners.
 */
public abstract class AbstractSeriesDataset extends AbstractDataset implements
        SeriesDataset, Serializable, SeriesChangeListener {

    /** For serialization. */
    private static final long serialVersionUID = -6074996219705033171L;

    /**
     * Creates a new dataset.
     */
    protected AbstractSeriesDataset() {
        super();
    }

    /**
     * Returns the number of series in the dataset.
     * 
     * @return The series count.
     */
    public abstract int getSeriesCount();

    /**
     * Returns the key for a series.
     * <p>
     * If <code>series</code> is not within the specified range, the
     * implementing method should throw an {@link IndexOutOfBoundsException}
     * (preferred) or an {@link IllegalArgumentException}.
     * 
     * @param series
     *            the series index (in the range <code>0</code> to
     *            <code>getSeriesCount() - 1</code>).
     * 
     * @return The series key.
     */
    public abstract Comparable getSeriesKey(int series);

    /**
     * Returns the index of the named series, or -1.
     * 
     * @param seriesKey
     *            the series key (<code>null</code> permitted).
     * 
     * @return The index.
     */
    public int indexOf(Comparable seriesKey) {
        int seriesCount = getSeriesCount();
        for (int s = 0; s < seriesCount; s++) {
            if (getSeriesKey(s).equals(seriesKey)) {
                return s;
            }
        }
        return -1;
    }

    /**
     * Called when a series belonging to the dataset changes.
     *
     * @param event  information about the change.
     */
    public void seriesChanged(SeriesChangeEvent event) {
        fireDatasetChanged();
    }

}
