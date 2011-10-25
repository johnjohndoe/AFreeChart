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
 * -------------------------------------
 * StandardGradientPaintTransformer.java
 * -------------------------------------
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
 * 28-Oct-2003 : Version 1 (DG);
 * 19-Mar-2004 : Added equals() method (DG);
 * 
 */

package org.afree.ui;

import java.io.Serializable;

import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;

import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.GradientColor;

/**
 * Transforms a <code>GradientPaint</code> to range over the width of a target
 * shape.
 * 
 * @author David Gilbert
 */
public class StandardGradientShaderFactory implements
        GradientShaderFactory, Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -8155025776964678320L;

    /** The transform type. */
      private GradientShaderType type;
      
    /**
     * Creates a new transformer.
     */
    
      public StandardGradientShaderFactory() {
      this(GradientShaderType.VERTICAL); }
      
     /**
     * Creates a new transformer.
     * 
     * @param type
     *            the transform type.
     */
    
      public StandardGradientShaderFactory( final GradientShaderType type) {
          this.type = type; 
      }
      
    /**
     * Create a <code>LinearGradient</code> instance.
     * 
     * @param gradientPaint
     *            the original GradientColor.
     * @param target
     *            the target shape.
     * 
     * @return The LinearGradient.
     */
    
    public LinearGradient create(final GradientColor gradientPaint, final Shape target) {
        RectShape bounds = new RectShape();
        target.getBounds(bounds);
        LinearGradient result = null;
        
        if (this.type.equals(GradientShaderType.VERTICAL)) {
            result = new LinearGradient((float) bounds.getCenterX(), (float) bounds.getMinY(), (float) bounds.getCenterX(), (float) bounds.getMaxY(), gradientPaint.getColor1(), gradientPaint.getColor2(), TileMode.CLAMP);
        } else if (this.type.equals(GradientShaderType.HORIZONTAL)) {
            result = new LinearGradient((float) bounds.getMinX(), (float) bounds.getCenterY(), (float) bounds.getMaxX(), (float) bounds.getCenterY(), gradientPaint.getColor1(), gradientPaint.getColor2(), TileMode.CLAMP);
        } else if (this.type.equals(GradientShaderType.CENTER_HORIZONTAL)) {
            result = new LinearGradient((float) bounds.getMinX(), (float) bounds.getCenterY(), (float) bounds.getMaxX(), (float) bounds.getCenterY(),  gradientPaint.getColor2(),gradientPaint.getColor1(), TileMode.MIRROR);
        } else if (this.type.equals(GradientShaderType.CENTER_VERTICAL)) {
            result = new LinearGradient((float) bounds.getCenterX(), (float) bounds.getMinY(), (float) bounds.getCenterX(), (float) bounds.getCenterY(), gradientPaint.getColor2(), gradientPaint.getColor1(), TileMode.MIRROR);
        }

        return result;
    }
     
     /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj
     *            the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    
     public boolean equals(final Object obj) { 
         if (obj == this) {
             return true;
         }
         if (!(obj instanceof StandardGradientShaderFactory)) {
             return false;
         }
         final StandardGradientShaderFactory that =
             (StandardGradientShaderFactory) obj;
         if (this.type != that.type) {
             return false;
         }
         return true;
     }
     /**
     * Returns a clone of the transformer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException
     *             not thrown by this class, but subclasses (if any) might.
     */
    
     public Object clone() throws CloneNotSupportedException { 
         return super.clone(); 
     }
     /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
     public int hashCode() {
         return (this.type != null ? this.type.hashCode() : 0); 
     }
     
}
