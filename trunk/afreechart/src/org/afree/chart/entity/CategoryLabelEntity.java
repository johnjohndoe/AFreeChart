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
 * ------------------------
 * CategoryLabelEntity.java
 * ------------------------
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
 * (C) Copyright 2006-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 02-Oct-2006 : Version 1 (DG);
 * 13-Nov-2007 : Added equals() and hashCode() methods (DG);
 *
 */

package org.afree.chart.entity;

import org.afree.chart.axis.CategoryAxis;
import org.afree.graphics.geom.Shape;

/**
 * An entity to represent the labels on a {@link CategoryAxis}.
 * 
 * @since JFreeChart 1.0.3
 */
public class CategoryLabelEntity extends TickLabelEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 7717189344430753119L;
    /** The category key. */
    private Comparable key;

    /**
     * Creates a new entity.
     * 
     * @param key
     *            the category key.
     * @param area
     *            the hotspot.
     * @param toolTipText
     *            the tool tip text.
     * @param urlText
     *            the URL text.
     */
    public CategoryLabelEntity(Comparable key, Shape area, String toolTipText,
            String urlText) {
        super(area, toolTipText, urlText);
        this.key = key;
    }

    /**
     * Returns the category key.
     * 
     * @return The category key.
     */
    public Comparable getKey() {
        return this.key;
    }

    /**
     * Returns a string representation of this entity. This is primarily useful
     * for debugging.
     * 
     * @return A string representation of this entity.
     */
    public String toString() {
        StringBuffer buf = new StringBuffer("CategoryLabelEntity: ");
        buf.append("category=");
        buf.append(this.key);
        buf.append(", tooltip=" + getToolTipText());
        buf.append(", url=" + getURLText());
        return buf.toString();
    }
}
