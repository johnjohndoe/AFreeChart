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
 * ---------------------
 * EntityCollection.java
 * ---------------------
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
 * (C) Copyright 2002-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-May-2002 : Version 1 (DG);
 * 25-Jun-2002 : Removed unnecessary import (DG);
 * 26-Jun-2002 : Added iterator() method (DG);
 * 03-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 30-Jan-2004 : Added a method to add a collection of entities.
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0
 *               release (DG);
 * 18-Jan-2005 : Added getEntity() and getEntityCount() methods (DG);
 *
 */

package org.afree.chart.entity;

import java.util.Collection;
import java.util.Iterator;

/**
 * This interface defines the methods used to access an ordered list of
 * {@link ChartEntity} objects.
 */
public interface EntityCollection {

    /**
     * Clears all entities.
     */
    public void clear();

    /**
     * Adds an entity to the collection.
     * 
     * @param entity
     *            the entity (<code>null</code> not permitted).
     */
    public void add(ChartEntity entity);

    /**
     * Adds the entities from another collection to this collection.
     * 
     * @param collection
     *            the other collection.
     */
    public void addAll(EntityCollection collection);

    /**
     * Returns an entity whose area contains the specified point.
     * 
     * @param x
     *            the x coordinate.
     * @param y
     *            the y coordinate.
     * 
     * @return The entity.
     */
    public ChartEntity getEntity(double x, double y);

    /**
     * Returns an entity from the collection.
     * 
     * @param index
     *            the index (zero-based).
     * 
     * @return An entity.
     */
    public ChartEntity getEntity(int index);

    /**
     * Returns the entity count.
     * 
     * @return The entity count.
     */
    public int getEntityCount();

    /**
     * Returns the entities in an unmodifiable collection.
     * 
     * @return The entities.
     */
    public Collection getEntities();

    /**
     * Returns an iterator for the entities in the collection.
     * 
     * @return An iterator.
     */
    public Iterator iterator();

}
