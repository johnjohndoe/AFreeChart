/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
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
 * -------------------
 * PaintUtilities.java
 * -------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2003-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 *
 * Changes
 * -------
 * 13-Nov-2003 : Version 1 (DG);
 * 04-Oct-2004 : Renamed PaintUtils --> PaintUtilities (DG);
 * 23-Feb-2005 : Rewrote equal() method with less indenting required (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 */

package org.afree.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.afree.graphics.PaintType;

import android.graphics.Paint;

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
     
        boolean result = false;
        /*
        // handle GradientPaint as a special case...
        if (p1 instanceof GradientPaint && p2 instanceof GradientPaint) {
            final GradientPaint gp1 = (GradientPaint) p1;
            final GradientPaint gp2 = (GradientPaint) p2;
            result = gp1.getColor1().equals(gp2.getColor1()) 
                && gp1.getColor2().equals(gp2.getColor2())
                && gp1.getPoint1().equals(gp2.getPoint1())    
                && gp1.getPoint2().equals(gp2.getPoint2())
                && gp1.isCyclic() == gp2.isCyclic()
                && gp1.getTransparency() == gp1.getTransparency(); 
        }
        else {
            result = p1.equals(p2);
        }
        */
        return result;

    }
}
