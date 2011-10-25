/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
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
 * ----------
 * Align.java
 * ----------
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
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  Christian W. Zuckschwerdt;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 *
 * Changes (from 30-May-2002)
 * --------------------------
 * 30-May-2002 : Added title (DG);
 * 13-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 *
 */

package org.afree.ui;

import org.afree.graphics.geom.RectShape;


/**
 * A utility class for aligning RectShapes.
 * 
 */
public final class Align {

    /** Center alignment. */
    public static final int CENTER = 0x00;

    /** Top alignment. */
    public static final int TOP = 0x01;

    /** Bottom alignment. */
    public static final int BOTTOM = 0x02;

    /** Left alignment. */
    public static final int LEFT = 0x04;

    /** Right alignment. */
    public static final int RIGHT = 0x08;

    /** Top/Left alignment. */
    public static final int TOP_LEFT = TOP | LEFT;

    /** Top/Right alignment. */
    public static final int TOP_RIGHT = TOP | RIGHT;

    /** Bottom/Left alignment. */
    public static final int BOTTOM_LEFT = BOTTOM | LEFT;

    /** Bottom/Right alignment. */
    public static final int BOTTOM_RIGHT = BOTTOM | RIGHT;

    /** Horizontal fit. */
    public static final int FIT_HORIZONTAL = LEFT | RIGHT;

    /** Vertical fit. */
    public static final int FIT_VERTICAL = TOP | BOTTOM;

    /** Complete fit. */
    public static final int FIT = FIT_HORIZONTAL | FIT_VERTICAL;

    /** North alignment (same as TOP). */
    public static final int NORTH = TOP;

    /** South alignment (same as BOTTOM). */
    public static final int SOUTH = BOTTOM;

    /** West alignment (same as LEFT). */
    public static final int WEST = LEFT;

    /** East alignment (same as RIGHT). */
    public static final int EAST = RIGHT;

    /** North/West alignment (same as TOP_LEFT). */
    public static final int NORTH_WEST = NORTH | WEST;

    /** North/East alignment (same as TOP_RIGHT). */
    public static final int NORTH_EAST = NORTH | EAST;

    /** South/West alignment (same as BOTTOM_LEFT). */
    public static final int SOUTH_WEST = SOUTH | WEST;

    /** South/East alignment (same as BOTTOM_RIGHT). */
    public static final int SOUTH_EAST = SOUTH | EAST;

    /**
     * Private constructor.
     */
    private Align() {
        super();
    }

    /**
     * Aligns one RectShape (<code>rect</code>) relative to another RectShape (
     * <code>frame</code>).
     * 
     * @param rect
     *            the RectShape to be aligned.
     * @param frame
     *            the reference frame.
     * @param align
     *            the alignment type.
     */
    public static void align(final RectShape rect, final RectShape frame,
            final int align) {

        double x = frame.getCenterX() - rect.getWidth() / 2.0;
        double y = frame.getCenterY() - rect.getHeight() / 2.0;
        double w = rect.getWidth();
        double h = rect.getHeight();

        if ((align & FIT_VERTICAL) == FIT_VERTICAL) {
            h = frame.getHeight();
        }

        if ((align & FIT_HORIZONTAL) == FIT_HORIZONTAL) {
            w = frame.getWidth();
        }

        if ((align & TOP) == TOP) {
            y = frame.getMinY();
        }

        if ((align & BOTTOM) == BOTTOM) {
            y = frame.getMaxY() - h;
        }

        if ((align & LEFT) == LEFT) {
            x = frame.getX();
        }

        if ((align & RIGHT) == RIGHT) {
            x = frame.getMaxX() - w;
        }

        rect.setRect(x, y, w, h);

    }

}
