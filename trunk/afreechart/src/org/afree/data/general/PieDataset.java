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
 * ---------------
 * PieDataset.java
 * ---------------
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
 * Contributor(s):   Sam (oldman);
 *
 * Changes
 * -------
 * 17-Nov-2001 : Version 1 (DG);
 * 22-Jan-2002 : Removed the getCategoryCount() method, updated Javadoc
 *               comments (DG);
 * 18-Apr-2002 : getCategories() now returns List instead of Set (oldman);
 * 23-Oct-2002 : Reorganised the code: PieDataset now extends KeyedValues
 *               interface (DG);
 * 04-Mar-2003 : Now just replicates the KeyedValuesDataset interface (DG);
 *
 */

package org.afree.data.general;

import org.afree.data.KeyedValues;
import org.afree.data.general.Dataset;



/**
 * A general purpose dataset where values are associated with keys.  As the
 * name suggests, you can use this dataset to supply data for pie charts (refer
 * to the {@link org.afree.chart.plot.PiePlot} class).
 */
public interface PieDataset extends KeyedValues, Dataset {

    // no new methods added.

}
