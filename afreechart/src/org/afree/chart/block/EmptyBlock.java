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
 * EmptyBlock.java
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
 * 04-Feb-2005 : Now cloneable and serializable (DG);
 * 20-Apr-2005 : Added new draw() method (DG);
 * 08-Apr-2008 : Added support for margin and border (DG);
 * 08-May-2008 : Updated arrange() method to recognise
 *               incoming constraint (DG);
 *
 */

package org.afree.chart.block;

import java.io.Serializable;

import android.graphics.Canvas;

import org.afree.util.PublicCloneable;
import org.afree.ui.Size2D;
import org.afree.graphics.geom.RectShape;


/**
 * An empty block with a fixed size.
 */
public class EmptyBlock extends AbstractBlock
        implements Block, Cloneable, PublicCloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -4083197869412648579L;

    /**
     * Creates a new block with the specified width and height.
     *
     * @param width  the width.
     * @param height  the height.
     */
    public EmptyBlock(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    /**
     * Arranges the contents of the block, within the given constraints, and
     * returns the block size.
     *
     * @param canvas  the graphics device.
     * @param constraint  the constraint (<code>null</code> not permitted).
     *
     * @return The block size (in Canvas units, never <code>null</code>).
     */
    public Size2D arrange(Canvas canvas, RectangleConstraint constraint) {
        Size2D base = new Size2D(calculateTotalWidth(getWidth()),
                calculateTotalHeight(getHeight()));
        return constraint.calculateConstrainedSize(base);
    }

    /**
     * Draws the block.  Since the block is empty, there is nothing to draw
     * except the optional border.
     *
     * @param canvas  the graphics device.
     * @param area  the area.
     */
    public void draw(Canvas canvas, RectShape area) {
        draw(canvas, area, null);
    }

    /**
     * Draws the block within the specified area.  Since the block is empty,
     * there is nothing to draw except the optional border.
     *
     * @param canvas  the graphics device.
     * @param area  the area.
     * @param params  ignored (<code>null</code> permitted).
     *
     * @return Always <code>null</code>.
     */
    public Object draw(Canvas canvas, RectShape area, Object params) {
        area = trimMargin(area);
        drawBorder(canvas, area);
        return null;
    }

    /**
     * Returns a clone of the block.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
