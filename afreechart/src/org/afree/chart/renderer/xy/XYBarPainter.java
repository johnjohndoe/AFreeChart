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
 * -----------------
 * XYBarPainter.java
 * -----------------
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
 * (C) Copyright 2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 19-Jun-2008 : Version 1 (DG);
 *
 */

package org.afree.chart.renderer.xy;

import org.afree.ui.RectangleEdge;
import org.afree.graphics.geom.RectShape;
import android.graphics.Canvas;

/**
 * The interface for plugin painter for the {@link XYBarRenderer} class.  When
 * developing a class that implements this interface, bear in mind the
 * following:
 * <ul>
 * <li>the <code>equals(Object)</code> method should be overridden;</li>
 * <li>instances of the class should be immutable OR implement the
 *     <code>PublicCloneable</code> interface, so that a renderer using the
 *     painter can be cloned reliably;
 * <li>the class should be <code>Serializable</code>, otherwise chart
 *     serialization will not be supported.</li>
 * </ul>
 *
 * @since JFreeChart 1.0.11
 */
public interface XYBarPainter {

    /**
     * Paints a single bar on behalf of a renderer.
     *
     * @param canvas  the graphics target.
     * @param renderer  the renderer.
     * @param row  the row index for the item.
     * @param column  the column index for the item.
     * @param bar  the bounds for the bar.
     * @param base  the base of the bar.
     */
    public void paintBar(Canvas canvas, XYBarRenderer renderer,
            int row, int column, RectShape bar, RectangleEdge base);

    /**
     * Paints the shadow for a single bar on behalf of a renderer.
     *
     * @param canvas  the graphics target.
     * @param renderer  the renderer.
     * @param row  the row index for the item.
     * @param column  the column index for the item.
     * @param bar  the bounds for the bar.
     * @param base  the base of the bar.
     * @param pegShadow  peg the shadow to the base of the bar?
     */
    public void paintBarShadow(Canvas canvas, XYBarRenderer renderer,
            int row, int column, RectShape bar, RectangleEdge base,
            boolean pegShadow);

}
