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
 * ----------------
 * Arrangement.java
 * ----------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 11-Feb-2005 : Modified arrange() method to return Size2D (DG);
 * 22-Apr-2005 : Reordered arguments in arrange() method (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.chart.block;

import org.afree.ui.Size2D;
import android.graphics.Canvas;

/**
 * An object that is responsible for arranging a collection of {@link Block}s
 * within a {@link BlockContainer}.
 */
public interface Arrangement {

    /**
     * Adds a block and a key which can be used to determine the position of the
     * block in the arrangement. This method is called by the container (you
     * don't need to call this method directly) and gives the arrangement an
     * opportunity to record the details if they are required.
     * 
     * @param block
     *            the block.
     * @param key
     *            the key (<code>null</code> permitted).
     */
    public void add(Block block, Object key);

    /**
     * Arranges the blocks within the specified container, subject to the given
     * constraint.
     * 
     * @param container
     *            the container (<code>null</code> not permitted).
     * @param canvas
     *            the graphics device.
     * @param constraint
     *            the constraint.
     * 
     * @return The container size after the arrangement.
     */
    public Size2D arrange(BlockContainer container, Canvas canvas,
            RectangleConstraint constraint);

    /**
     * Clears any cached layout information retained by the arrangement.
     */
    public void clear();

}
