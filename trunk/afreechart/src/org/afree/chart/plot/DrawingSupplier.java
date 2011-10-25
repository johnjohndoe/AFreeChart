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
 * --------------------
 * DrawingSupplier.java
 * --------------------
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
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 16-Jan-2003 : Version 1 (DG);
 * 17-Jan-2003 : Renamed PaintSupplier --> DrawingSupplier (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Jun-2007 : Added getNextOutlinePaint() method.
 *
 */

package org.afree.chart.plot;

import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
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
     * @return The paint type.
     */
    public PaintType getNextPaintType();

    /**
     * Returns the next outline paint in a sequence maintained by the supplier.
     * 
     * @return The paint type.
     */
    public PaintType getNextOutlinePaintType();

    /**
     * Returns the next fill paint in a sequence maintained by the supplier.
     * 
     * @return The paint type.
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

    /**
     * Returns the next effect in a sequence maintained by the supplier.
     * 
     * @return The effect.
     */
    public PathEffect getNextEffect();

    /**
     * Returns the next outline effect in a sequence maintained by the supplier.
     * 
     * @return The effect.
     */
    public PathEffect getNextOutlineEffect();
    
    /**
     * Returns the next <code>Shape</code> object in a sequence maintained by
     * the supplier.
     * 
     * @return The shape.
     */
    public Shape getNextShape();

}
