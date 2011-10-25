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
 * CategoryTick.java
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
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 07-Nov-2003 : Version 1 (DG);
 * 13-May-2004 : Added equals() method (DG);
 *
 */

package org.afree.chart.axis;

import org.afree.ui.TextAnchor;
import org.afree.chart.text.TextBlock;
import org.afree.chart.text.TextBlockAnchor;

/**
 * A tick for a {@link CategoryAxis}.
 */
public class CategoryTick extends Tick {

    /**
     * 
     */
    private static final long serialVersionUID = -3097542254784207090L;

    /** The category. */
    private Comparable category;

    /** The label. */
    private TextBlock label;

    /** The label anchor. */
    private TextBlockAnchor labelAnchor;

    /**
     * Creates a new tick.
     * 
     * @param category
     *            the category.
     * @param label
     *            the label.
     * @param labelAnchor
     *            the label anchor.
     * @param rotationAnchor
     *            the rotation anchor.
     * @param angle
     *            the rotation angle (in radians).
     */
    public CategoryTick(Comparable category, TextBlock label,
            TextBlockAnchor labelAnchor, TextAnchor rotationAnchor, double angle) {

        super("", TextAnchor.CENTER, rotationAnchor, angle);
        this.category = category;
        this.label = label;
        this.labelAnchor = labelAnchor;

    }

    /**
     * Returns the category.
     * 
     * @return The category.
     */
    public Comparable getCategory() {
        return this.category;
    }

    /**
     * Returns the label.
     * 
     * @return The label.
     */
    public TextBlock getLabel() {
        return this.label;
    }

    /**
     * Returns the label anchor.
     * 
     * @return The label anchor.
     */
    public TextBlockAnchor getLabelAnchor() {
        return this.labelAnchor;
    }

}
