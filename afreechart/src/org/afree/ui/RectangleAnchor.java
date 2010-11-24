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
 * --------------------
 * RectangleAnchor.java
 * --------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2003-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 *
 * Changes:
 * --------
 * 31-Oct-2003 (DG);
 * 01-Apr-2004 : Changed java.awt.geom.Dimension2D to org.jfree.ui.Size2D 
 *               because of JDK bug 4976448 which persists on JDK 1.3.1 (DG);
 * 21-Jan-2005 : Changed return type of coordinates() method (DG);
 * 
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 */

package org.afree.ui;

import java.io.Serializable;

import org.afree.graphics.geom.RectShape;

import android.graphics.PointF;

/**
 * Used to indicate an anchor point for a RectShape.
 * 
 * @author David Gilbert
 */
public final class RectangleAnchor implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -2457494205644416327L;

    /** Center. */
    public static final RectangleAnchor CENTER = new RectangleAnchor(
            "RectangleAnchor.CENTER");

    /** Top. */
    public static final RectangleAnchor TOP = new RectangleAnchor(
            "RectangleAnchor.TOP");

    /** Top-Left. */
    public static final RectangleAnchor TOP_LEFT = new RectangleAnchor(
            "RectangleAnchor.TOP_LEFT");

    /** Top-Right. */
    public static final RectangleAnchor TOP_RIGHT = new RectangleAnchor(
            "RectangleAnchor.TOP_RIGHT");

    /** Bottom. */
    public static final RectangleAnchor BOTTOM = new RectangleAnchor(
            "RectangleAnchor.BOTTOM");

    /** Bottom-Left. */
    public static final RectangleAnchor BOTTOM_LEFT = new RectangleAnchor(
            "RectangleAnchor.BOTTOM_LEFT");

    /** Bottom-Right. */
    public static final RectangleAnchor BOTTOM_RIGHT = new RectangleAnchor(
            "RectangleAnchor.BOTTOM_RIGHT");

    /** Left. */
    public static final RectangleAnchor LEFT = new RectangleAnchor(
            "RectangleAnchor.LEFT");

    /** Right. */
    public static final RectangleAnchor RIGHT = new RectangleAnchor(
            "RectangleAnchor.RIGHT");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     * 
     * @param name
     *            the name.
     */
    private RectangleAnchor(final String name) {
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
     *            the other object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RectangleAnchor)) {
            return false;
        }

        final RectangleAnchor order = (RectangleAnchor) obj;
        if (!this.name.equals(order.name)) {
            return false;
        }

        return true;
    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return The hashcode
     */
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Returns the (x, y) coordinates of the specified anchor.
     * 
     * @param RectShape
     *            the RectShape.
     * @param anchor
     *            the anchor.
     * 
     * @return The (x, y) coordinates.
     */
    public static PointF coordinates(final org.afree.graphics.geom.RectShape RectShape,
            final RectangleAnchor anchor) {
        PointF result = new PointF();
        if (anchor == RectangleAnchor.CENTER) {
            result.set(RectShape.getCenterX(), RectShape.getCenterY());
        } else if (anchor == RectangleAnchor.TOP) {
            result.set(RectShape.getCenterX(), RectShape.getMinY());
        } else if (anchor == RectangleAnchor.BOTTOM) {
            result.set(RectShape.getCenterX(), RectShape.getMaxY());
        } else if (anchor == RectangleAnchor.LEFT) {
            result.set(RectShape.getMinX(), RectShape.getCenterY());
        } else if (anchor == RectangleAnchor.RIGHT) {
            result.set(RectShape.getMaxX(), RectShape.getCenterY());
        } else if (anchor == RectangleAnchor.TOP_LEFT) {
            result.set(RectShape.getMinX(), RectShape.getMinY());
        } else if (anchor == RectangleAnchor.TOP_RIGHT) {
            result.set(RectShape.getMaxX(), RectShape.getMinY());
        } else if (anchor == RectangleAnchor.BOTTOM_LEFT) {
            result.set(RectShape.getMinX(), RectShape.getMaxY());
        } else if (anchor == RectangleAnchor.BOTTOM_RIGHT) {
            result.set(RectShape.getMaxX(), RectShape.getMaxY());
        }
        return result;
    }

    /**
     * Creates a new RectShape with the specified dimensions that is aligned to
     * the given anchor point <code>(anchorX, anchorY)</code>.
     * 
     * @param dimensions
     *            the dimensions (<code>null</code> not permitted).
     * @param anchorX
     *            the x-anchor.
     * @param anchorY
     *            the y-anchor.
     * @param anchor
     *            the anchor (<code>null</code> not permitted).
     * 
     * @return A RectShape.
     */
    public static RectShape createRectShape(final Size2D dimensions,
            final double anchorX, final double anchorY,
            final RectangleAnchor anchor) {
        RectShape result = null;
        final double w = dimensions.getWidth();
        final double h = dimensions.getHeight();
        if (anchor == RectangleAnchor.CENTER) {
            result = new RectShape(anchorX - w / 2.0, anchorY - h
                    / 2.0, w, h);
        } else if (anchor == RectangleAnchor.TOP) {
            result = new RectShape(anchorX - w / 2.0, anchorY - h
                    / 2.0, w, h);
        } else if (anchor == RectangleAnchor.BOTTOM) {
            result = new RectShape(anchorX - w / 2.0, anchorY - h
                    / 2.0, w, h);
        } else if (anchor == RectangleAnchor.LEFT) {
            result = new RectShape(anchorX, anchorY - h / 2.0, w, h);
        } else if (anchor == RectangleAnchor.RIGHT) {
            result = new RectShape(anchorX - w, anchorY - h / 2.0, w,
                    h);
        } else if (anchor == RectangleAnchor.TOP_LEFT) {
            result = new RectShape(anchorX - w / 2.0, anchorY - h
                    / 2.0, w, h);
        } else if (anchor == RectangleAnchor.TOP_RIGHT) {
            result = new RectShape(anchorX - w / 2.0, anchorY - h
                    / 2.0, w, h);
        } else if (anchor == RectangleAnchor.BOTTOM_LEFT) {
            result = new RectShape(anchorX - w / 2.0, anchorY - h
                    / 2.0, w, h);
        } else if (anchor == RectangleAnchor.BOTTOM_RIGHT) {
            result = new RectShape(anchorX - w / 2.0, anchorY - h
                    / 2.0, w, h);
        }
        return result;
    }

}
