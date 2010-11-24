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
 * AbstractDataset.java
 * --------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nicolas Brodu (for Astrium and EADS Corporate Research
 *                   Center);
 *                   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes (from 21-Aug-2001)
 * --------------------------
 * 21-Aug-2001 : Added standard header. Fixed DOS encoding problem (DG);
 * 18-Sep-2001 : Updated e-mail address in header (DG);
 * 15-Oct-2001 : Moved to new package (com.jrefinery.data.*) (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 17-Nov-2001 : Changed constructor from public to protected, created new
 *               AbstractSeriesDataset class and transferred series-related
 *               methods, updated Javadoc comments (DG);
 * 04-Mar-2002 : Updated import statements (DG);
 * 11-Jun-2002 : Updated for change in the event constructor (DG);
 * 07-Aug-2002 : Changed listener list to use
 *               javax.swing.event.EventListenerList (DG);
 * 04-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 27-Mar-2003 : Implemented Serializable (DG);
 * 18-Aug-2003 : Implemented Cloneable (DG);
 * 08-Sep-2003 : Serialization fixes (NB);
 * 11-Sep-2003 : Cloning Fixes (NB);
 * 01-Jun-2005 : Added hasListener() method for unit testing (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.data.general;

import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;
import java.io.Serializable;


//import org.afree.chart.event.EventListenerList;


/**
 * An abstract implementation of the {@link Dataset} interface, containing a
 * mechanism for registering change listeners.
 */
public abstract class AbstractDataset implements Dataset, Cloneable,
        Serializable, ObjectInputValidation {

    /** For serialization. */
    private static final long serialVersionUID = 1918768939869230744L;

    /** The group that the dataset belongs to. */
    private DatasetGroup group;

    /** Storage for registered change listeners. */
//    private transient EventListenerList listenerList;
    
    /**
     * Constructs a dataset. By default, the dataset is assigned to its own
     * group.
     */
    protected AbstractDataset() {
        this.group = new DatasetGroup();
//        this.listenerList = new EventListenerList();
    }

    /**
     * Returns the dataset group for the dataset.
     * 
     * @return The group (never <code>null</code>).
     * 
     * @see #setGroup(DatasetGroup)
     */
    public DatasetGroup getGroup() {
        return this.group;
    }

    /**
     * Sets the dataset group for the dataset.
     * 
     * @param group
     *            the group (<code>null</code> not permitted).
     * 
     * @see #getGroup()
     */
    public void setGroup(DatasetGroup group) {
        if (group == null) {
            throw new IllegalArgumentException("Null 'group' argument.");
        }
        this.group = group;
    }

    public void validateObject() throws InvalidObjectException {
        // TODO Auto-generated method stub
        
    }

    /**
     * Registers an object to receive notification of changes to the dataset.
     *
     * @param listener  the object to register.
     *
     * @see #removeChangeListener(DatasetChangeListener)
     */
    public void addChangeListener(DatasetChangeListener listener) {
//        this.listenerList.add(DatasetChangeListener.class, listener);
    }
    
    /**
     * Notifies all registered listeners that the dataset has changed.
     *
     * @param event  contains information about the event that triggered the
     *               notification.
     *
     * @see #addChangeListener(DatasetChangeListener)
     * @see #removeChangeListener(DatasetChangeListener)
     */
    protected void notifyListeners(DatasetChangeEvent event) {

//        Object[] listeners = this.listenerList.getListenerList();
//        for (int i = listeners.length - 2; i >= 0; i -= 2) {
//            if (listeners[i] == DatasetChangeListener.class) {
//                ((DatasetChangeListener) listeners[i + 1]).datasetChanged(
//                        event);
//            }
//        }

    }
    
    /**
     * Deregisters an object so that it no longer receives notification of
     * changes to the dataset.
     *
     * @param listener  the object to deregister.
     *
     * @see #addChangeListener(DatasetChangeListener)
     */
    public void removeChangeListener(DatasetChangeListener listener) {
//        this.listenerList.remove(DatasetChangeListener.class, listener);
    }

    /**
     * Notifies all registered listeners that the dataset has changed.
     *
     * @see #addChangeListener(DatasetChangeListener)
     */
    protected void fireDatasetChanged() {
        notifyListeners(new DatasetChangeEvent(this, this));
    }

    /**
     * Returns a clone of the dataset. The cloned dataset will NOT include the
     * {@link DatasetChangeListener} references that have been registered with
     * this dataset.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  if the dataset does not support
     *                                     cloning.
     */
    public Object clone() throws CloneNotSupportedException {
        AbstractDataset clone = (AbstractDataset) super.clone();
//        clone.listenerList = new EventListenerList();
        return clone;
    }
    
}
