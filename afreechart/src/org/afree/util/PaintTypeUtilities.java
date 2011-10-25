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
 * -------------------
 * PaintUtilities.java
 * -------------------
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
 * (C) Copyright 2003-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 *
 * Changes
 * -------
 * 13-Nov-2003 : Version 1 (DG);
 * 04-Oct-2004 : Renamed PaintUtils --> PaintUtilities (DG);
 * 23-Feb-2005 : Rewrote equal() method with less indenting required (DG);
 *
 */

package org.afree.util;

import org.afree.graphics.PaintType;

/**
 * Utility code that relates to <code>Paint</code> objects.
 *
 * @author David Gilbert
 */
public class PaintTypeUtilities {

    /**
     * Private constructor prevents object creation.
     */
    private PaintTypeUtilities() {
    }

    /**
     * Returns <code>true</code> if the two <code>Paint</code> objects are equal 
     * OR both <code>null</code>.  This method handles
     * <code>GradientPaint</code> as a special case.
     *
     * @param p1  paint 1 (<code>null</code> permitted).
     * @param p2  paint 2 (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public static boolean equal(final PaintType p1, final PaintType p2) {

        // handle cases where either or both arguments are null
        if (p1 == null) {
            return (p2 == null);   
        }
        if (p2 == null) {
            return false;   
        }
        return p1.equals(p2);
    }
}
