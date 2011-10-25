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
 * --------------
 * DialLayer.java
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
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 *
 */

package org.afree.chart.plot.dial;

/*
import java.awt.Graphics2D;
import java.awt.geom.RectShape;
*/
import java.io.Serializable;
import java.util.EventListener;

import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;

import android.graphics.Canvas;

/**
 * A dial layer draws itself within a reference frame.  The view frame is a
 * subset of the reference frame, and defines the area that is actually
 * visible.
 * <br><br>
 * Classes that implement this interface should be {@link Serializable},
 * otherwise chart serialization may fail.
 *
 * @since JFreeChart 1.0.7
 */
public interface DialLayer {

    /**
     * Returns a flag that indicates whether or not the layer is visible.
     *
     * @return A boolean.
     */
    public boolean isVisible();

    /**
     * Registers a listener with this layer, so that it receives notification
     * of changes to this layer.
     *
     * @param listener  the listener.
     */
    public void addChangeListener(DialLayerChangeListener listener);

    /**
     * Deregisters a listener, so that it no longer receives notification of
     * changes to this layer.
     *
     * @param listener  the listener.
     */
    public void removeChangeListener(DialLayerChangeListener listener);

    /**
     * Returns <code>true</code> if the specified listener is currently
     * registered with the this layer.
     *
     * @param listener  the listener.
     *
     * @return A boolean.
     */
    public boolean hasListener(EventListener listener);

    /**
     * Returns <code>true</code> if the drawing should be clipped to the
     * dial window (which is defined by the {@link DialFrame}), and
     * <code>false</code> otherwise.
     *
     * @return A boolean.
     */
    public boolean isClippedToWindow();

    /**
     * Draws the content of this layer.
     *
     * @param canvas  the graphics target (<code>null</code> not permitted).
     * @param plot  the plot (typically this should not be <code>null</code>,
     *     but for a layer that doesn't need to reference the plot, it may
     *     be permitted).
     * @param frame  the reference frame for the dial's geometry
     *     (<code>null</code> not permitted).  This is typically larger than
     *     the visible area of the dial (see the next parameter).
     * @param view  the visible area for the dial (<code>null</code> not
     *     permitted).
     */
    public void draw(Canvas canvas, DialPlot plot, RectShape frame,
            Shape view);

}
