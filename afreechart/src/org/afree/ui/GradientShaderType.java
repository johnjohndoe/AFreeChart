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
 * -------------------------------
 * GradientShaderType.java
 * -------------------------------
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
 * 29-Nov-2010 : initialization value of the instance variable was changed.
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2003-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 *
 * Changes:
 * --------
 * 21-Oct-2003 (DG);
 * 
 */

package org.afree.ui;

import java.io.ObjectStreamException;
import java.io.Serializable;


/**
 * Represents a type of transform for a <code>GradientPaint</code>.
 * 
 * @author David Gilbert
 */
public final class GradientShaderType implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 8331561784933982450L;

    /** Vertical. */
    public static final GradientShaderType VERTICAL = new GradientShaderType(
            "GradientShaderType.VERTICAL");

    /** Horizontal. */
    public static final GradientShaderType HORIZONTAL = new GradientShaderType(
            "GradientShaderType.HORIZONTAL");

    /** Center/vertical. */
    public static final GradientShaderType CENTER_VERTICAL = new GradientShaderType(
            "GradientShaderType.CENTER_VERTICAL");

    /** Center/horizontal. */
    public static final GradientShaderType CENTER_HORIZONTAL = new GradientShaderType(
            "GradientShaderType.CENTER_HORIZONTAL");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     * 
     * @param name
     *            the name.
     */
    private GradientShaderType(final String name) {
        this.name = name;
    }

    /**
     * Returns a string representing the object.
     * 
     * @return The string.
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns <code>true</code> if this object is equal to the specified
     * object, and <code>false</code> otherwise.
     * 
     * @param o
     *            the other object.
     * 
     * @return A boolean.
     */
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof GradientShaderType)) {
            return false;
        }

        final GradientShaderType t = (GradientShaderType) o;
        if (!this.name.equals(t.name)) {
            return false;
        }

        return true;
    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return the hashcode
     */
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Ensures that serialization returns the unique instances.
     * 
     * @return The object.
     * 
     * @throws ObjectStreamException
     *             if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
        GradientShaderType result = null;
        if (this.equals(GradientShaderType.HORIZONTAL)) {
            result = GradientShaderType.HORIZONTAL;
        } else if (this.equals(GradientShaderType.VERTICAL)) {
            result = GradientShaderType.VERTICAL;
        } else if (this.equals(GradientShaderType.CENTER_HORIZONTAL)) {
            result = GradientShaderType.CENTER_HORIZONTAL;
        } else if (this.equals(GradientShaderType.CENTER_VERTICAL)) {
            result = GradientShaderType.CENTER_VERTICAL;
        }
        return result;
    }

}
