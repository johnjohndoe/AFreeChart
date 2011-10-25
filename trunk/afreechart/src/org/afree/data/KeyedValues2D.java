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
 * ------------------
 * KeyedValues2D.java
 * ------------------
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
 * (C) Copyright 2002-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 28-Oct-2002 : Version 1 (DG);
 * 12-Jan-2005 : Updated Javadocs (DG);
 *
 */

package org.afree.data;

import java.util.List;

/**
 * An extension of the {@link Values2D} interface where a unique key is
 * associated with the row and column indices.
 */
public interface KeyedValues2D extends Values2D {

    /**
     * Returns the row key for a given index.
     * 
     * @param row
     *            the row index (zero-based).
     * 
     * @return The row key.
     * 
     * @throws IndexOutOfBoundsException
     *             if <code>row</code> is out of bounds.
     */
    public Comparable getRowKey(int row);

    /**
     * Returns the row index for a given key.
     * 
     * @param key
     *            the row key.
     * 
     * @return The row index, or <code>-1</code> if the key is unrecognised.
     */
    public int getRowIndex(Comparable key);

    /**
     * Returns the row keys.
     * 
     * @return The keys.
     */
    public List getRowKeys();

    /**
     * Returns the column key for a given index.
     * 
     * @param column
     *            the column index (zero-based).
     * 
     * @return The column key.
     * 
     * @throws IndexOutOfBoundsException
     *             if <code>row</code> is out of bounds.
     */
    public Comparable getColumnKey(int column);

    /**
     * Returns the column index for a given key.
     * 
     * @param key
     *            the column key.
     * 
     * @return The column index, or <code>-1</code> if the key is unrecognised.
     */
    public int getColumnIndex(Comparable key);

    /**
     * Returns the column keys.
     * 
     * @return The keys.
     */
    public List getColumnKeys();

    /**
     * Returns the value associated with the specified keys.
     * 
     * @param rowKey
     *            the row key (<code>null</code> not permitted).
     * @param columnKey
     *            the column key (<code>null</code> not permitted).
     * 
     * @return The value.
     * 
     * @throws UnknownKeyException
     *             if either key is not recognised.
     */
    public Number getValue(Comparable rowKey, Comparable columnKey);

}
