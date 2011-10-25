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
 * ----------
 * Block.java
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
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 08-Feb-2005 : Added ID (DG);
 * 20-Apr-2005 : Added a new draw() method that can accept params
 *               and return results (DG);
 *
 */

package org.afree.chart.block;

import org.afree.ui.Drawable;
import org.afree.ui.Size2D;
import org.afree.graphics.geom.RectShape;
import android.graphics.Canvas;

/**
 * A block is an arbitrary item that can be drawn (in Java2D space) within a
 * rectangular area, has a preferred size, and can be arranged by an
 * {@link Arrangement} manager.
 */
public interface Block extends Drawable {

    /**
     * Returns an ID for the block.
     * 
     * @return An ID.
     */
    public String getID();

    /**
     * Sets the ID for the block.
     * 
     * @param id
     *            the ID.
     */
    public void setID(String id);

    /**
     * Arranges the contents of the block, with no constraints, and returns the
     * block size.
     * 
     * @param canvas
     *            the graphics device.
     * 
     * @return The size of the block.
     */
    public Size2D arrange(Canvas canvas);

    /**
     * Arranges the contents of the block, within the given constraints, and
     * returns the block size.
     * 
     * @param canvas
     *            the graphics device.
     * @param constraint
     *            the constraint (<code>null</code> not permitted).
     * 
     * @return The block size (in Canvas units, never <code>null</code>).
     */
    public Size2D arrange(Canvas canvas, RectangleConstraint constraint);

    /**
     * Returns the current bounds of the block.
     * 
     * @return The bounds.
     */
    public RectShape getBounds();

    /**
     * Sets the bounds of the block.
     * 
     * @param bounds
     *            the bounds.
     */
    public void setBounds(RectShape bounds);

    /**
     * Draws the block within the specified area. Refer to the documentation for
     * the implementing class for information about the <code>params</code> and
     * return value supported.
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area.
     * @param params
     *            optional parameters (<code>null</code> permitted).
     * 
     * @return An optional return value (possibly <code>null</code>).
     */
    public Object draw(Canvas canvas, RectShape area, Object params);

}
