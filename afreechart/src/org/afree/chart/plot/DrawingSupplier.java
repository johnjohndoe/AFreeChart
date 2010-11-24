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
 * --------------------
 * DrawingSupplier.java
 * --------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes
 * -------
 * 16-Jan-2003 : Version 1 (DG);
 * 17-Jan-2003 : Renamed PaintSupplier --> DrawingSupplier (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Jun-2007 : Added getNextOutlinePaint() method.
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.chart.plot;

import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import android.graphics.Paint;
import android.graphics.PathEffect;

/**
 * A supplier of <code>Paint</code>, <code>Stroke</code> and <code>Shape</code>
 * objects for use by plots and renderers. By providing a central place for
 * obtaining these items, we can ensure that duplication is avoided.
 * <p>
 * To support the cloning of charts, classes that implement this interface
 * should also implement <code>PublicCloneable</code>.
 */
public interface DrawingSupplier {

    /**
     * Returns the next paint in a sequence maintained by the supplier.
     * 
     * @return The paint.
     */
    public PaintType getNextPaintType();

    /**
     * Returns the next outline paint in a sequence maintained by the supplier.
     * 
     * @return The paint.
     */
    public PaintType getNextOutlinePaintType();

    /**
     * Returns the next fill paint in a sequence maintained by the supplier.
     * 
     * @return The paint.
     * 
     * @since JFreeChart 1.0.6
     */
    public PaintType getNextFillPaintType();

    /**
     * Returns the next <code>Stroke</code> object in a sequence maintained by
     * the supplier.
     * 
     * @return The stroke.
     */
    public float getNextStroke();

    /**
     * Returns the next <code>Stroke</code> object in a sequence maintained by
     * the supplier.
     * 
     * @return The stroke.
     */
    public float getNextOutlineStroke();

    public PathEffect getNextEffect();

    public PathEffect getNextOutlineEffect();
    
    /**
     * Returns the next <code>Shape</code> object in a sequence maintained by
     * the supplier.
     * 
     * @return The shape.
     */
    public Shape getNextShape();

}
