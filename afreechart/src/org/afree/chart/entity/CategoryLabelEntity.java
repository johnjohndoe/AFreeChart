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
 * ------------------------
 * CategoryLabelEntity.java
 * ------------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2006-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes:
 * --------
 * 02-Oct-2006 : Version 1 (DG);
 * 13-Nov-2007 : Added equals() and hashCode() methods (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
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
