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
 * -------------------
 * ShapeUtilities.java
 * -------------------
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
 * (C) Copyright 2003-2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 *
 * Changes
 * -------
 * 13-Aug-2003 : Version 1 (DG);
 * 16-Mar-2004 : Moved rotateShape() from RefineryUtilities.java to here (DG);
 * 13-May-2004 : Added new shape creation methods (DG);
 * 30-Sep-2004 : Added createLineRegion() method (DG);
 *               Moved drawRotatedShape() method from RefineryUtilities class 
 *               to this class (DG);
 * 04-Oct-2004 : Renamed ShapeUtils --> ShapeUtilities (DG);
 * 26-Oct-2004 : Added a method to test the equality of two LineShape 
 *               instances (DG);
 * 10-Nov-2004 : Added new translateShape() and equal(OvalShape, OvalShape)
 *               methods (DG);
 * 11-Nov-2004 : Renamed translateShape() --> createTranslatedShape() (DG);
 * 07-Jan-2005 : Minor Javadoc fix (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 21-Jan-2005 : Modified return type of RectangleAnchor.coordinates() 
 *               method (DG);
 * 22-Feb-2005 : Added equality tests for ArcShape and PathShape (DG);
 * 16-Mar-2005 : Fixed bug where equal(Shape, Shape) fails for two Polygon
 *               instances (DG);
 *
 */

package org.afree.util;



import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import org.afree.graphics.geom.ArcShape;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.OvalShape;
import org.afree.graphics.geom.PathShape;
import org.afree.graphics.geom.Polygon;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.ui.RectangleAnchor;

/**
 * Utility methods for {@link Shape} objects.
 * 
 * @author David Gilbert
 */
public class ShapeUtilities {

    /**
     * Prevents instantiation.
     */
    private ShapeUtilities() {
    }

    /**
     * Returns a clone of the specified shape, or <code>null</code>.  At the
     * current time, this method supports cloning for instances of
     * <code>Line2D</code>, <code>RectangularShape</code>, <code>Area</code>
     * and <code>GeneralPath</code>.
     * <p>
     * <code>RectangularShape</code> includes <code>Arc2D</code>,
     * <code>Ellipse2D</code>, <code>Rectangle2D</code>,
     * <code>RoundRectangle2D</code>.
     *
     * @param shape  the shape to clone (<code>null</code> permitted,
     *               returns <code>null</code>).
     *
     * @return A clone or <code>null</code>.
     */
    public static Shape clone(final Shape shape) {
        if (shape instanceof Cloneable) {
            try {
                return (Shape) ObjectUtilities.clone(shape);
            }
            catch (CloneNotSupportedException cnse) {
            }
        }
        final Shape result = null;
        return result;
    }    
      
    /**
     * Tests two shapes for equality.  If both shapes are <code>null</code>,
     * this method will return <code>true</code>.
     * <p>
     * In the current implementation, the following shapes are supported:
     * <code>Ellipse2D</code>, <code>Line2D</code> and <code>Rectangle2D</code>
     * (implicit).
     *
     * @param s1  the first shape (<code>null</code> permitted).
     * @param s2  the second shape (<code>null</code> permitted).
     *
     * @return A boolean.
     */

    public static boolean equal(final Shape s1, final Shape s2) {
        if (s1 instanceof LineShape && s2 instanceof LineShape) {
            return equal((LineShape) s1, (LineShape) s2);
        }
        else if (s1 instanceof OvalShape && s2 instanceof OvalShape) {
            return equal((OvalShape) s1, (OvalShape) s2);
        }
        else if (s1 instanceof ArcShape && s2 instanceof ArcShape) {
            return equal((ArcShape) s1, (ArcShape) s2);
        }
        else if (s1 instanceof Polygon && s2 instanceof Polygon) {
            return equal((Polygon) s1, (Polygon) s2);
        }
        else if (s1 instanceof PathShape && s2 instanceof PathShape) {
            return equal((PathShape) s1, (PathShape) s2);
        }
        else {
            // this will handle Rectangle2D...
            return ObjectUtilities.equal(s1, s2);
        }
    }    

    /**
     * Compares two lines are returns <code>true</code> if they are equal or
     * both <code>null</code>.
     *
     * @param l1  the first line (<code>null</code> permitted).
     * @param l2  the second line (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public static boolean equal(final LineShape l1, final LineShape l2) {
        if (l1 == null) {
            return (l2 == null);
        }
        if (l2 == null) {
            return false;
        }
        if (!l1.getP1().equals(l2.getP1())) {
            return false;
        }
        if (!l1.getP2().equals(l2.getP2())) {
            return false;
        }
        return true;
    }

    /**
     * Compares two ellipses and returns <code>true</code> if they are equal or
     * both <code>null</code>.
     *
     * @param e1  the first ellipse (<code>null</code> permitted).
     * @param e2  the second ellipse (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public static boolean equal(final OvalShape e1, final OvalShape e2) {
        if (e1 == null) {
            return (e2 == null);
        }
        if (e2 == null) {
            return false;
        }
//        if (!e1.getFrame().equals(e2.getFrame())) {
//            return false;
//        }
        return true;
    }

    /**
     * Compares two arcs and returns <code>true</code> if they are equal or
     * both <code>null</code>.
     *
     * @param a1  the first arc (<code>null</code> permitted).
     * @param a2  the second arc (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public static boolean equal(final ArcShape a1, final ArcShape a2) {
        if (a1 == null) {
            return (a2 == null);
        }
        if (a2 == null) {
            return false;
        }
//        if (!a1.getFrame().equals(a2.getFrame())) {
//            return false;
//        }
        if (a1.getAngleStart() != a2.getAngleStart()) {
            return false;
        }
        if (a1.getAngleExtent() != a2.getAngleExtent()) {
            return false;
        }
        if (!a1.equals(a2)) {
            return false;
        }
        return true;
    }

    /**
     * Tests two polygons for equality.  If both are <code>null</code> this
     * method returns <code>true</code>.
     *
     * @param p1  polygon 1 (<code>null</code> permitted).
     * @param p2  polygon 2 (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public static boolean equal(final Polygon p1, final Polygon p2) {
        if (p1 == null) {
            return (p2 == null);
        }
        if (p2 == null) {
            return false;
        }
        if (p1.equals(p2)) {
            return false;
        }
        return true;
    }

    /**
     * Tests two polygons for equality.  If both are <code>null</code> this
     * method returns <code>true</code>.
     *
     * @param p1  path 1 (<code>null</code> permitted).
     * @param p2  path 2 (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public static boolean equal(final PathShape p1, final PathShape p2) {
        if (p1 == null) {
            return (p2 == null);
        }
        if (p2 == null) {
            return false;
        }
//        if (p1.getWindingRule() != p2.getWindingRule()) {
//            return false;
//        }
//        PathIterator iterator1 = p1.getPathIterator(null);
//        PathIterator iterator2 = p2.getPathIterator(null);
//        double[] d1 = new double[6];
//        double[] d2 = new double[6];
//        boolean done = iterator1.isDone() && iterator2.isDone();
//        while (!done) {
//            if (iterator1.isDone() != iterator2.isDone()) {
//                return false;
//            }
//            int seg1 = iterator1.currentSegment(d1);
//            int seg2 = iterator2.currentSegment(d2);
//            if (seg1 != seg2) {
//                return false;
//            }
//            if (!Arrays.equals(d1, d2)) {
//                return false;
//            }
//            iterator1.next();
//            iterator2.next();
//            done = iterator1.isDone() && iterator2.isDone();
//        }
        return true;
    }
    
    /**
     * Creates and returns a translated shape.
     * 
     * @param shape
     *            the shape (<code>null</code> not permitted).
     * @param transX
     *            the x translation (in Java2D space).
     * @param transY
     *            the y translation (in Java2D space).
     * 
     * @return The translated shape.
     */
    public static Shape createTranslatedShape(final Shape shape,
            final double transX, final double transY) {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }
        Matrix mat = new Matrix();
        mat.postTranslate((float)transX, (float)transY);
        
        Path path = new Path(shape.getPath());
        path.transform(mat);
        
        RectF rect = new RectF();
        path.computeBounds(rect, false);
        
        return new PathShape(path);
    }

    /**
     * Translates a shape to a new location such that the anchor point (relative
     * to the rectangular bounds of the shape) aligns with the specified (x, y)
     * coordinate in Java2D space.
     * 
     * @param shape
     *            the shape (<code>null</code> not permitted).
     * @param anchor
     *            the anchor (<code>null</code> not permitted).
     * @param locationX
     *            the x-coordinate (in Java2D space).
     * @param locationY
     *            the y-coordinate (in Java2D space).
     * 
     * @return A new and translated shape.
     */
    public static Shape createTranslatedShape(final Shape shape,
            final RectangleAnchor anchor, final double locationX,
            final double locationY) {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        Matrix mat = new Matrix();
        mat.postTranslate((float)locationX, (float)locationY);
        
        Path path = new Path(shape.getPath());
        path.transform(mat);
        
        RectF rect = new RectF();
        path.computeBounds(rect, false);
        
        return new PathShape(path);

    }

    /**
     * Rotates a shape about the specified coordinates.
     * 
     * @param base
     *            the shape (<code>null</code> permitted, returns
     *            <code>null</code>).
     * @param angle
     *            the angle (in radians).
     * @param x
     *            the x coordinate for the rotation point (in Java2D space).
     * @param y
     *            the y coordinate for the rotation point (in Java2D space).
     * 
     * @return the rotated shape.
     */
    public static Shape rotateShape(final Shape base, final double angle,
            final float x, final float y) {
        if (base == null) {
            return null;
        }
        Matrix mat = new Matrix();
        mat.postRotate((float)Math.toDegrees(angle), x, y);
        
        
        Path path = new Path(base.getPath());
        path.transform(mat);
        
        RectF rect = new RectF();
        path.computeBounds(rect, false);
        
        return new PathShape(path);
    }

    /**
     * Returns a point based on (x, y) but constrained to be within the bounds
     * of a given RectShape.
     * 
     * @param x
     *            the x-coordinate.
     * @param y
     *            the y-coordinate.
     * @param area
     *            the constraining RectShape (<code>null</code> not permitted).
     * 
     * @return A point within the RectShape.
     * 
     * @throws NullPointerException
     *             if <code>area</code> is <code>null</code>.
     */
    public static PointF getPointInRectShape(double x, double y,
            final RectShape area) {

        x = Math.max(area.getMinX(), Math.min(x, area.getMaxX()));
        y = Math.max(area.getMinY(), Math.min(y, area.getMaxY()));
        return new PointF((float)x, (float)y);

    }

}
