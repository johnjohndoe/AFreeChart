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
 * --------------
 * DialFrame.java
 * --------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.chart.plot.dial;

/*
import java.awt.Shape;
import java.awt.geom.RectShape;
*/
import java.io.Serializable;

import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;

/**
 * A dial frame is the face plate for a dial plot - it is always drawn last.
 * AFreeChart includes a couple of implementations of this interface
 * ({@link StandardDialFrame} and {@link ArcDialFrame}).
 * <br><br>
 * Classes that implement this interface should be {@link Serializable},
 * otherwise chart serialization may fail.
 *
 * @since JFreeChart 1.0.7
 */
public interface DialFrame extends DialLayer {

    /**
     * Returns the shape of the viewing window for the dial, or
     * <code>null</code> if the dial is completely open.  Other layers in the
     * plot will rely on their drawing to be clipped within this window.
     *
     * @param frame  the reference frame for the dial.
     *
     * @return The window.
     */
    public Shape getWindow(RectShape frame);

}
