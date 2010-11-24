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
 * --------------------
 * VectorXYDataset.java
 * --------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2007, 2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 24-May-2007 : Renamed getDeltaXValue() as getVectorXValue(), and likewise
 *               for getDeltaYValue(), and replaced getDeltaX()/getDeltaY()
 *               with getVector() (DG);
 * 25-May-2007 : Moved from experimental to the main source tree (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.data.xy;

import org.afree.data.xy.XYDataset;

/**
 * An extension of the {@link XYDataset} interface that allows a vector to be
 * defined at a specific (x, y) location.
 *
 * @since JFreeChart 1.0.6
 */
public interface VectorXYDataset extends XYDataset {

    /**
     * Returns the x-component of the vector for an item in a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The x-component of the vector.
     */
    public double getVectorXValue(int series, int item);

    /**
     * Returns the y-component of the vector for an item in a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The y-component of the vector.
     */
    public double getVectorYValue(int series, int item);

    /**
     * Returns the vector for an item in a series.  Depending on the particular
     * dataset implementation, this may involve creating a new {@link Vector}
     * instance --- if you are just interested in the x and y components,
     * use the {@link #getVectorXValue(int, int)} and
     * {@link #getVectorYValue(int, int)} methods instead.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The vector (possibly <code>null</code>).
     */
    public Vector getVector(int series, int item);

}
