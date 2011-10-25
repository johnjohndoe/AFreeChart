/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
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
 * ObjectUtilitiess.java
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
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2003-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 *
 * Changes
 * -------
 * 25-Mar-2003 : Version 1 (DG);
 * 15-Sep-2003 : Fixed bug in clone(List) method (DG);
 * 25-Nov-2004 : Modified clone(Object) method to fail with objects that
 *               cannot be cloned, added new deepClone(Collection) method.
 *               Renamed ObjectUtils --> ObjectUtilities (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 18-Aug-2005 : Added casts to suppress compiler warnings, as suggested in
 *               patch 1260622 (DG);
 *
 */

package org.afree.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;

import android.util.Log;



/**
 * A collection of useful static utility methods for handling classes and object
 * instantiation.
 *
 * @author Thomas Morgner
 */
public final class ObjectUtilities {



    /**
     * Returns <code>true</code> if the two objects are equal OR both
     * <code>null</code>.
     *
     * @param o1 object 1 (<code>null</code> permitted).
     * @param o2 object 2 (<code>null</code> permitted).
     * @return <code>true</code> or <code>false</code>.
     */
    public static boolean equal(final Object o1, final Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 != null) {
            return o1.equals(o2);
        }
        else {
            return false;
        }
    }

    /**
     * Returns a hash code for an object, or zero if the object is
     * <code>null</code>.
     *
     * @param object the object (<code>null</code> permitted).
     * @return The object's hash code (or zero if the object is
     *         <code>null</code>).
     */
    public static int hashCode(final Object object) {
        int result = 0;
        if (object != null) {
            result = object.hashCode();
        }
        return result;
    }

    /**
     * Returns a clone of the specified object, if it can be cloned, otherwise
     * throws a CloneNotSupportedException.
     *
     * @param object the object to clone (<code>null</code> not permitted).
     * @return A clone of the specified object.
     * @throws CloneNotSupportedException if the object cannot be cloned.
     */
    public static Object clone(final Object object)
        throws CloneNotSupportedException {
        if (object == null) {
            throw new IllegalArgumentException("Null 'object' argument.");
        }
        if (object instanceof PublicCloneable) {
            final PublicCloneable pc = (PublicCloneable) object;
            return pc.clone();
        }
        else {
            try {
                final Method method = object.getClass().getMethod("clone",
                        (Class[]) null);
                if (Modifier.isPublic(method.getModifiers())) {
                    return method.invoke(object, (Object[]) null);
                }
            }
            catch (NoSuchMethodException e) {
                Log.w("ObjectUtilities","Object without clone() method is impossible.");
            }
            catch (IllegalAccessException e) {
                Log.w("ObjectUtilities","Object.clone(): unable to call method.");
            }
            catch (InvocationTargetException e) {
                Log.w("ObjectUtilities","Object without clone() method is impossible.");
            }
        }
        throw new CloneNotSupportedException("Failed to clone.");
    }

    
    
    /**
     * Returns a new collection containing clones of all the items in the
     * specified collection.
     *
     * @param collection the collection (<code>null</code> not permitted).
     * @return A new collection containing clones of all the items in the
     *         specified collection.
     * @throws CloneNotSupportedException if any of the items in the collection
     *                                    cannot be cloned.
     */
    public static Collection deepClone(final Collection collection)
        throws CloneNotSupportedException {

        if (collection == null) {
            throw new IllegalArgumentException("Null 'collection' argument.");
        }
        // all JDK-Collections are cloneable ...
        // and if the collection is not clonable, then we should throw
        // a CloneNotSupportedException anyway ...
        final Collection result
            = (Collection) ObjectUtilities.clone(collection);
        result.clear();
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            final Object item = iterator.next();
            if (item != null) {
                result.add(clone(item));
            }
            else {
                result.add(null);
            }
        }
        return result;
    }
    
    
  }
