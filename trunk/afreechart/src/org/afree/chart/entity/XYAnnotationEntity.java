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
 * -----------------------
 * XYAnnotationEntity.java
 * -----------------------
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
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 29-Sep-2004 : Version 1 (DG);
 *
 */

package org.afree.chart.entity;

import java.io.Serializable;

import org.afree.graphics.geom.Shape;



/**
 * A chart entity that represents an annotation on an
 * {@link org.afree.chart.plot.XYPlot}.
 */
public class XYAnnotationEntity extends ChartEntity
                                implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 2340334068383660799L;

    /** The renderer index. */
    private int rendererIndex;

    /**
     * Creates a new entity.
     *
     * @param hotspot  the area.
     * @param rendererIndex  the rendererIndex (zero-based index).
     * @param toolTipText  the tool tip text.
     * @param urlText  the URL text for HTML image maps.
     */
    public XYAnnotationEntity(Shape hotspot, int rendererIndex,
                              String toolTipText, String urlText) {
        super(hotspot, toolTipText, urlText);
        this.rendererIndex = rendererIndex;
    }

    /**
     * Returns the renderer index.
     *
     * @return The renderer index.
     */
    public int getRendererIndex() {
        return this.rendererIndex;
    }

    /**
     * Sets the renderer index.
     *
     * @param index  the item index (zero-based).
     */
    public void setRendererIndex(int index) {
        this.rendererIndex = index;
    }

    /**
     * Tests the entity for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof XYAnnotationEntity)) {
            return false;
        }
        XYAnnotationEntity that = (XYAnnotationEntity) obj;
        if (this.rendererIndex != that.rendererIndex) {
            return false;
        }
        return true;
    }

}
