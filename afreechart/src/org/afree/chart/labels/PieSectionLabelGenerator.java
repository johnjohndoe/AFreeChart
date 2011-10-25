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
 * -----------------------------
 * PieSectionLabelGenerator.java
 * -----------------------------
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
 * (C) Copyright 2001-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 13-Dec-2001 : Version 1 (DG);
 * 16-Jan-2002 : Completed Javadocs (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 30-Oct-2002 : Category is now a Comparable instance (DG);
 * 07-Mar-2003 : Changed to KeyedValuesDataset and added pieIndex
 *               parameter (DG);
 * 21-Mar-2003 : Updated Javadocs (DG);
 * 24-Apr-2003 : Switched around PieDataset and KeyedValuesDataset (DG);
 * 13-Aug-2003 : Added clone() method (DG);
 * 19-Aug-2003 : Renamed PieToolTipGenerator --> PieItemLabelGenerator (DG);
 * 11-Nov-2003 : Removed clone() method (DG);
 * 30-Jan-2004 : Added generateSectionLabel() method (DG);
 * 15-Apr-2004 : Moved generateToolTip() method into separate interface and
 *               renamed this interface PieSectionLabelGenerator (DG);
 *
 */

package org.afree.chart.labels;


import java.awt.font.TextAttribute;
import java.text.AttributedString;

import org.afree.data.general.PieDataset;
import org.afree.graphics.geom.Font;


/**
 * Interface for a label generator for plots that use data from
 * a {@link PieDataset}.
 */
public interface PieSectionLabelGenerator {

    /**
     * Generates a label for a pie section.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param key  the section key (<code>null</code> not permitted).
     *
     * @return The label (possibly <code>null</code>).
     */
    public String generateSectionLabel(PieDataset dataset, Comparable key);

    /**
     * Generates an attributed label for the specified series, or
     * <code>null</code> if no attributed label is available (in which case,
     * the string returned by
     * {@link #generateSectionLabel(PieDataset, Comparable)} will
     * provide the fallback).  Only certain attributes are recognised by the
     * code that ultimately displays the labels:
     * <ul>
     * <li>{@link TextAttribute#FONT}: will set the font;</li>
     * <li>{@link TextAttribute#POSTURE}: a value of
     *     {@link TextAttribute#POSTURE_OBLIQUE} will add {@link Font#ITALIC} to
     *     the current font;</li>
     * <li>{@link TextAttribute#WEIGHT}: a value of
     *     {@link TextAttribute#WEIGHT_BOLD} will add {@link Font#BOLD} to the
     *     current font;</li>
     * <li>{@link TextAttribute#FOREGROUND}: this will set the {@link Paint}
     *     for the current</li>
     * <li>{@link TextAttribute#SUPERSCRIPT}: the values
     *     {@link TextAttribute#SUPERSCRIPT_SUB} and
     *     {@link TextAttribute#SUPERSCRIPT_SUPER} are recognised.</li>
     * </ul>
     *
     * @param dataset  the dataset.
     * @param key  the key.
     *
     * @return An attributed label (possibly <code>null</code>).
     */
    public AttributedString generateAttributedSectionLabel(PieDataset dataset,
                                                           Comparable key);

}
