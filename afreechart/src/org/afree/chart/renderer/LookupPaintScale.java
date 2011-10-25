/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
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
 * LookupPaintScale.java
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
 * (C) Copyright 2006-2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Jul-2006 : Version 1 (DG);
 * 31-Jan-2007 : Fixed serialization support (DG);
 * 09-Mar-2007 : Fixed cloning (DG);
 * 14-Jun-2007 : Use double primitive in PaintItem (DG);
 * 28-Mar-2009 : Made PaintItem inner class static (DG);
 *
 */

package org.afree.chart.renderer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import android.graphics.Color;

import org.afree.util.PaintTypeUtilities;
import org.afree.util.PublicCloneable;
import org.afree.io.SerialUtilities;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;

/**
 * A paint scale that uses a lookup table to associate paint instances
 * with data value ranges.
 *
 * @since JFreeChart 1.0.4
 */
public class LookupPaintScale
        implements PaintScale, PublicCloneable, Serializable {

    /**
     * Stores the paint for a value.
     */
    static class PaintItem implements Comparable, Serializable {

        /** For serialization. */
        static final long serialVersionUID = 698920578512361570L;

        /** The value. */
        double value;

        /** The paint. */
        transient PaintType paintType;

        /**
         * Creates a new instance.
         *
         * @param value  the value.
         * @param paintType  the paint.
         */
        public PaintItem(double value, PaintType paintType) {
            this.value = value;
            this.paintType = paintType;
        }

        /* (non-Javadoc)
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo(Object obj) {
            PaintItem that = (PaintItem) obj;
            double d1 = this.value;
            double d2 = that.value;
            if (d1 > d2) {
                return 1;
            }
            if (d1 < d2) {
                return -1;
            }
            return 0;
        }

        /**
         * Tests this item for equality with an arbitrary object.
         *
         * @param obj  the object (<code>null</code> permitted).
         *
         * @return A boolean.
         */
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PaintItem)) {
                return false;
            }
            PaintItem that = (PaintItem) obj;
            if (this.value != that.value) {
                return false;
            }
            if (!PaintTypeUtilities.equal(this.paintType, that.paintType)) {
                return false;
            }
            return true;
        }

        /**
         * Provides serialization support.
         *
         * @param stream  the output stream.
         *
         * @throws IOException  if there is an I/O error.
         */
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            SerialUtilities.writePaintType(this.paintType, stream);
        }

        /**
         * Provides serialization support.
         *
         * @param stream  the input stream.
         *
         * @throws IOException  if there is an I/O error.
         * @throws ClassNotFoundException  if there is a classpath problem.
         */
        private void readObject(ObjectInputStream stream)
                throws IOException, ClassNotFoundException {
            stream.defaultReadObject();
            this.paintType = SerialUtilities.readPaintType(stream);
        }

    }

    /** For serialization. */
    static final long serialVersionUID = -5239384246251042006L;

    /** The lower bound. */
    private double lowerBound;

    /** The upper bound. */
    private double upperBound;

    /** The default paint. */
    private transient PaintType defaultPaintType;

    /** The lookup table. */
    private List lookupTable;

    /**
     * Creates a new paint scale.
     */
    
    private static PaintType paintLightGray = new SolidColor(Color.LTGRAY);        
    
    public LookupPaintScale() {
       
        this(0.0, 1.0,paintLightGray);
    }

    /**
     * Creates a new paint scale with the specified default paint.
     *
     * @param lowerBound  the lower bound.
     * @param upperBound  the upper bound.
     * @param defaultPaintType  the default paint (<code>null</code> not
     *     permitted).
     */
    public LookupPaintScale(double lowerBound, double upperBound,
            PaintType defaultPaintType) {
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException(
                    "Requires lowerBound < upperBound.");
        }
        if (defaultPaintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.defaultPaintType = defaultPaintType;
        this.lookupTable = new java.util.ArrayList();
    }

    /**
     * Returns the default paint (never <code>null</code>).
     *
     * @return The default paint.
     */
    public PaintType getDefaultPaintType() {
        return this.defaultPaintType;
    }

    /**
     * Returns the lower bound.
     *
     * @return The lower bound.
     *
     * @see #getUpperBound()
     */
    public double getLowerBound() {
        return this.lowerBound;
    }

    /**
     * Returns the upper bound.
     *
     * @return The upper bound.
     *
     * @see #getLowerBound()
     */
    public double getUpperBound() {
        return this.upperBound;
    }

    /**
     * Adds an entry to the lookup table.  Any values from <code>n</code> up
     * to but not including the next value in the table take on the specified
     * <code>paint</code>.
     *
     * @param value  the data value.
     * @param paintType  the paint.
     *
     * @since JFreeChart 1.0.6
     */
    public void add(double value, PaintType paintType) {
        PaintItem item = new PaintItem(value, paintType);
        int index = Collections.binarySearch(this.lookupTable, item);
        if (index >= 0) {
            this.lookupTable.set(index, item);
        }
        else {
            this.lookupTable.add(-(index + 1), item);
        }
    }

    /**
     * Returns the paint associated with the specified value.
     *
     * @param value  the value.
     *
     * @return The paint type.
     *
     * @see #getDefaultPaintType()
     */
    public PaintType getPaintType(double value) {

        // handle value outside bounds...
        if (value < this.lowerBound) {
            return this.defaultPaintType;
        }
        if (value > this.upperBound) {
            return this.defaultPaintType;
        }

        int count = this.lookupTable.size();
        if (count == 0) {
            return this.defaultPaintType;
        }

        // handle special case where value is less that item zero
        PaintItem item = (PaintItem) this.lookupTable.get(0);
        if (value < item.value) {
            return this.defaultPaintType;
        }

        // for value in bounds, do the lookup...
        int low = 0;
        int high = this.lookupTable.size() - 1;
        while (high - low > 1) {
            int current = (low + high) / 2;
            item = (PaintItem) this.lookupTable.get(current);
            if (value >= item.value) {
                low = current;
            }
            else {
                high = current;
            }
        }
        if (high > low) {
            item = (PaintItem) this.lookupTable.get(high);
            if (value < item.value) {
                item = (PaintItem) this.lookupTable.get(low);
            }
        }
        return (item != null ? item.paintType : this.defaultPaintType);
    }


    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LookupPaintScale)) {
            return false;
        }
        LookupPaintScale that = (LookupPaintScale) obj;
        if (this.lowerBound != that.lowerBound) {
            return false;
        }
        if (this.upperBound != that.upperBound) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.defaultPaintType, that.defaultPaintType)) {
            return false;
        }
        if (!this.lookupTable.equals(that.lookupTable)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a clone of the instance.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if there is a problem cloning the
     *     instance.
     */
    public Object clone() throws CloneNotSupportedException {
        LookupPaintScale clone = (LookupPaintScale) super.clone();
        clone.lookupTable = new java.util.ArrayList(this.lookupTable);
        return clone;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaintType(this.defaultPaintType, stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.defaultPaintType = SerialUtilities.readPaintType(stream);
    }

}
