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
 * -------------------------
 * LegendRenderingOrder.java
 * -------------------------
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
 * (C) Copyright 2004-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  Angel;
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 26-Mar-2004 : Version 1 (DG);
 *
 */

package org.afree.chart;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Represents the order for rendering legend items.
 */
public final class LegendRenderingOrder implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -3832486612685808616L;

    /** In order. */
    public static final LegendRenderingOrder STANDARD = new LegendRenderingOrder(
            "LegendRenderingOrder.STANDARD");

    /** In reverse order. */
    public static final LegendRenderingOrder REVERSE = new LegendRenderingOrder(
            "LegendRenderingOrder.REVERSE");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     * 
     * @param name
     *            the name.
     */
    private LegendRenderingOrder(String name) {
        this.name = name;
    }

    /**
     * Returns a string representing the object.
     * 
     * @return The string.
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns <code>true</code> if this object is equal to the specified
     * object, and <code>false</code> otherwise.
     * 
     * @param obj
     *            the other object.
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LegendRenderingOrder)) {
            return false;
        }
        LegendRenderingOrder order = (LegendRenderingOrder) obj;
        if (!this.name.equals(order.toString())) {
            return false;
        }
        return true;
    }

    /**
     * Ensures that serialization returns the unique instances.
     * 
     * @return The object.
     * 
     * @throws ObjectStreamException
     *             if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(LegendRenderingOrder.STANDARD)) {
            return LegendRenderingOrder.STANDARD;
        } else if (this.equals(LegendRenderingOrder.REVERSE)) {
            return LegendRenderingOrder.REVERSE;
        }
        return null;
    }

}
