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
 * PaintScale.java
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
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Jul-2006 : Version 1 (DG);
 * 31-Jan-2007 : Added getLowerBound() and getUpperBound() methods (DG);
 *
 */

package org.afree.chart.renderer;

import org.afree.graphics.PaintType;

/**
 * A source for <code>Paint</code> instances, used by the
 * {@link XYBlockRenderer}.
 * <br><br>
 * NOTE: Classes that implement this interface should also implement
 * <code>PublicCloneable</code> and <code>Serializable</code>, so
 * that any renderer (or other object instance) that references an instance of
 * this interface can still be cloned or serialized.
 *
 * @since JFreeChart 1.0.4
 */
public interface PaintScale {

    /**
     * Returns the lower bound for the scale.
     *
     * @return The lower bound.
     *
     * @see #getUpperBound()
     */
    public double getLowerBound();

    /**
     * Returns the upper bound for the scale.
     *
     * @return The upper bound.
     *
     * @see #getLowerBound()
     */
    public double getUpperBound();

    /**
     * Returns a <code>Paint</code> instance for the specified value.
     *
     * @param value  the value.
     *
     * @return A <code>Paint</code> instance (never <code>null</code>).
     */
    public PaintType getPaintType(double value);

}
