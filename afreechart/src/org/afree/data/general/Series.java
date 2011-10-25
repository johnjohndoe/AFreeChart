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
 * -----------
 * Series.java
 * -----------
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
 * 15-Nov-2001 : Version 1 (DG);
 * 29-Nov-2001 : Added cloning and property change support (DG);
 * 30-Jan-2002 : Added a description attribute and changed the constructors to
 *               protected (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 13-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Added equals() method (DG);
 * 26-Jun-2003 : Changed listener list to use EventListenerList - see bug
 *               757027 (DG);
 * 15-Oct-2003 : Added a flag to control whether or not change events are sent
 *               to registered listeners (DG);
 * 19-May-2005 : Made abstract (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 04-May-2006 : Updated API docs (DG);
 * 26-Sep-2007 : Added isEmpty() and getItemCount() methods (DG);
 *
 */

package org.afree.data.general;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Base class representing a data series.  Subclasses are left to implement the
 * actual data structures.
 * <P>
 * The series has two properties ("Key" and "Description") for which you can
 * register a <code>PropertyChangeListener</code>.
 * <P>
 * You can also register a {@link SeriesChangeListener} to receive notification
 * of changes to the series data.
 */
public abstract class Series implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -6906561437538683581L;

    /** The key for the series. */
    private Comparable key;

    /** A description of the series. */
    private String description;

    /** Storage for registered change listeners. */
    private List<SeriesChangeListener> listeners;

    /** Object to support property change notification. */
    private PropertyChangeSupport propertyChangeSupport;

    /** A flag that controls whether or not changes are notified. */
    private boolean notify;

    /**
     * Creates a new series with the specified key.
     *
     * @param key  the series key (<code>null</code> not permitted).
     */
    protected Series(Comparable key) {
        this(key, null);
    }

    /**
     * Creates a new series with the specified key and description.
     *
     * @param key  the series key (<code>null</code> NOT permitted).
     * @param description  the series description (<code>null</code> permitted).
     */
    protected Series(Comparable key, String description) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        this.key = key;
        this.description = description;
        this.listeners = new CopyOnWriteArrayList<SeriesChangeListener>();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.notify = true;
    }

    /**
     * Returns the key for the series.
     *
     * @return The series key (never <code>null</code>).
     *
     * @see #setKey(Comparable)
     */
    public Comparable getKey() {
        return this.key;
    }

    /**
     * Sets the key for the series and sends a <code>PropertyChangeEvent</code>
     * (with the property name "Key") to all registered listeners.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @see #getKey()
     */
    public void setKey(Comparable key) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        Comparable old = this.key;
        this.key = key;
        this.listeners = new CopyOnWriteArrayList<SeriesChangeListener>();
        this.propertyChangeSupport.firePropertyChange("Key", old, key);
        this.notify = true;
    }

    /**
     * Returns a description of the series.
     *
     * @return The series description (possibly <code>null</code>).
     *
     * @see #setDescription(String)
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the series and sends a
     * <code>PropertyChangeEvent</code> to all registered listeners.
     *
     * @param description  the description (<code>null</code> permitted).
     *
     * @see #getDescription()
     */
    public void setDescription(String description) {
        String old = this.description;
        this.description = description;
        this.propertyChangeSupport.firePropertyChange("Description", old,
                description);
    }

    /**
     * Returns the flag that controls whether or not change events are sent to
     * registered listeners.
     *
     * @return A boolean.
     *
     * @see #setNotify(boolean)
     */
    public boolean getNotify() {
        return this.notify;
    }

    /**
     * Sets the flag that controls whether or not change events are sent to
     * registered listeners.
     *
     * @param notify  the new value of the flag.
     *
     * @see #getNotify()
     */
    public void setNotify(boolean notify) {
        if (this.notify != notify) {
            this.notify = notify;
            fireSeriesChanged();
        }
    }

    /**
     * Returns <code>true</code> if the series contains no data items, and
     * <code>false</code> otherwise.
     *
     * @return A boolean.
     *
     * @since JFreeChart 1.0.7
     */
    public boolean isEmpty() {
        return (getItemCount() == 0);
    }

    /**
     * Returns the number of data items in the series.
     *
     * @return The number of data items in the series.
     */
    public abstract int getItemCount();

    /**
     * Returns a clone of the series.
     * <P>
     * Notes:
     * <ul>
     * <li>No need to clone the name or description, since String object is
     * immutable.</li>
     * <li>We set the listener list to empty, since the listeners did not
     * register with the clone.</li>
     * <li>Same applies to the PropertyChangeSupport instance.</li>
     * </ul>
     *
     * @return A clone of the series.
     *
     * @throws CloneNotSupportedException  not thrown by this class, but
     *         subclasses may differ.
     */
    public Object clone() throws CloneNotSupportedException {

        Series clone = (Series) super.clone();
       
        clone.propertyChangeSupport = new PropertyChangeSupport(clone);
        return clone;

    }


    /**
     * General method for signalling to registered listeners that the series
     * has been changed.
     */
    public void fireSeriesChanged() {
        if (this.notify) {
            notifyListeners(new SeriesChangeEvent(this));
        }
    }

    /**
     * Sends a change event to all registered listeners.
     *
     * @param event  contains information about the event that triggered the
     *               notification.
     */
    protected void notifyListeners(SeriesChangeEvent event) {
        if(listeners.size() == 0) {
            return;
        }
        for (int i = listeners.size() - 1; i >= 0; i--) {
            listeners.get(i).seriesChanged(event);
        }
    }

    /**
     * Adds a property change listener to the series.
     *
     * @param listener  the listener.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from the series.
     *
     * @param listener The listener.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Fires a property change event.
     *
     * @param property  the property key.
     * @param oldValue  the old value.
     * @param newValue  the new value.
     */
    protected void firePropertyChange(String property, Object oldValue,
            Object newValue) {
        this.propertyChangeSupport.firePropertyChange(property, oldValue,
                newValue);
    }


    /**
     * Registers an object with this series, to receive notification whenever
     * the series changes.
     * <P>
     * Objects being registered must implement the {@link SeriesChangeListener}
     * interface.
     *
     * @param listener  the listener to register.
     */
    public void addChangeListener(SeriesChangeListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Deregisters an object, so that it not longer receives notification
     * whenever the series changes.
     *
     * @param listener  the listener to deregister.
     */
    public void removeChangeListener(SeriesChangeListener listener) {
        this.listeners.remove(listener);
    }
}
