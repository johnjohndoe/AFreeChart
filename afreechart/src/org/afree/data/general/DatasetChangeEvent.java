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
 * DatasetChangeEvent.java
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
 * (C) Copyright 2000-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes (from 24-Aug-2001)
 * --------------------------
 * 24-Aug-2001 : Added standard source header. Fixed DOS encoding problem (DG);
 * 15-Oct-2001 : Move to new package (com.jrefinery.data.*) (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 11-Jun-2002 : Separated the event source from the dataset to cover the case
 *               where the dataset is changed to null in the Plot class.
 *               Updated Javadocs (DG);
 * 04-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 05-Oct-2004 : Minor Javadoc updates (DG);
 *
 */

package org.afree.data.general;

/**
 * A change event that encapsulates information about a change to a dataset.
 */
public class DatasetChangeEvent extends java.util.EventObject {

    /**
     * 
     */
    private static final long serialVersionUID = 4638967344627938520L;
    /**
     * The dataset that generated the change event.
     */
    private Dataset dataset;

    /**
     * Constructs a new event.  The source is either the dataset or the
     * {@link org.afree.chart.plot.Plot} class.  The dataset can be
     * <code>null</code> (in this case the source will be the
     * {@link org.afree.chart.plot.Plot} class).
     *
     * @param source  the source of the event.
     * @param dataset  the dataset that generated the event (<code>null</code>
     *                 permitted).
     */
    public DatasetChangeEvent(Object source, Dataset dataset) {
        super(source);
        this.dataset = dataset;
    }

    /**
     * Returns the dataset that generated the event.  Note that the dataset
     * may be <code>null</code> since adding a <code>null</code> dataset to a
     * plot will generated a change event.
     *
     * @return The dataset (possibly <code>null</code>).
     */
    public Dataset getDataset() {
        return this.dataset;
    }

}
