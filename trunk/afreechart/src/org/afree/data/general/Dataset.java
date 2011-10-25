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
 * ------------
 * Dataset.java
 * ------------
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
 * (C) Copyright 2000-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes (from 18-Sep-2001)
 * --------------------------
 * 18-Sep-2001 : Added standard header and fixed DOS encoding problem (DG);
 * 15-Oct-2001 : Moved to a new package (com.jrefinery.data.*) (DG);
 * 22-Oct-2001 : Changed name to Dataset.java (DG);
 * 17-Nov-2001 : Added getLegendItemCount() and getLegendItemLabels() methods,
 *               created SeriesDataset interface and transferred series related
 *               methods out (DG);
 * 22-Jan-2002 : Reconsidered (and removed) the getLegendItemCount() and
 *               getLegendItemLabels() methods...leave this to client code (DG);
 * 27-Sep-2002 : Added get/setDatasetGroup() methods (DG);
 * 10-Jan-2003 : Updated Javadocs (DG);
 *
 */

package org.afree.data.general;

/**
 * The base interface for data sets.
 * <P>
 * All datasets are required to support the {@link DatasetChangeEvent} mechanism
 * by allowing listeners to register and receive notification of any changes to
 * the dataset.
 * <P>
 * In addition, all datasets must belong to one (and only one)
 * {@link DatasetGroup}. The group object maintains a reader-writer lock which
 * provides synchronised access to the datasets in multi-threaded code.
 */
public interface Dataset {

    /**
     * Returns the dataset group.
     * 
     * @return The dataset group.
     */
    public DatasetGroup getGroup();

    /**
     * Sets the dataset group.
     * 
     * @param group
     *            the dataset group.
     */
    public void setGroup(DatasetGroup group);
    
    /**
     * Registers an object for notification of changes to the dataset.
     *
     * @param listener  the object to register.
     */
    public void addChangeListener(DatasetChangeListener listener);

    /**
     * Deregisters an object for notification of changes to the dataset.
     *
     * @param listener  the object to deregister.
     */
    public void removeChangeListener(DatasetChangeListener listener);

}
