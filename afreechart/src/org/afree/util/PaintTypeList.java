/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
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
 * --------------
 * PaintList.java
 * --------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 *
 * Changes
 * -------
 * 13-Aug-2003 : Version 1 (DG);
 * 27-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * 
 */

package org.afree.util;

import org.afree.graphics.PaintType;
import android.graphics.Paint;

/**
 * A table of {@link Paint} objects.
 * 
 * @author David Gilbert
 */
public class PaintTypeList extends AbstractObjectList {

    /**
     * 
     */
    private static final long serialVersionUID = 5272510000359967138L;

    /**
     * Creates a new list.
     */
    public PaintTypeList() {
        super();
    }

    /**
     * Returns a {@link Paint} object from the list.
     * 
     * @param index
     *            the index (zero-based).
     * 
     * @return The object.
     */
    public PaintType getPaintType(final int index) {
        return (PaintType) get(index);
    }

    /**
     * Sets the {@link Paint} for an item in the list. The list is expanded if
     * necessary.
     * 
     * @param index
     *            the index (zero-based).
     * @param paint
     *            the {@link Paint}.
     */
    public void setPaintType(final int index, final PaintType paint) {
        set(index, paint);
    }

}
