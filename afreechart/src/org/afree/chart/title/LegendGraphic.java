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
 * ------------------
 * LegendGraphic.java
 * ------------------
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
 * 14-Dec-2010 : performance tuning
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 26-Oct-2004 : Version 1 (DG);
 * 21-Jan-2005 : Modified return type of RectangleAnchor.coordinates()
 *               method (DG);
 * 20-Apr-2005 : Added new draw() method (DG);
 * 13-May-2005 : Fixed to respect margin, border and padding settings (DG);
 * 01-Sep-2005 : Implemented PublicCloneable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Dec-2006 : Added fillPaintTransformer attribute, so legend graphics can
 *               display gradient paint correctly, updated equals() and
 *               corrected clone() (DG);
 * 01-Aug-2007 : Updated API docs (DG);
 *
 */

package org.afree.chart.title;



import org.afree.ui.GradientShaderFactory;
import org.afree.util.ObjectUtilities;
import org.afree.util.PaintTypeUtilities;
import org.afree.util.PublicCloneable;
import org.afree.ui.RectangleAnchor;
import org.afree.util.ShapeUtilities;
import org.afree.ui.Size2D;
import org.afree.ui.StandardGradientShaderFactory;
import org.afree.chart.block.AbstractBlock;
import org.afree.chart.block.Block;
import org.afree.chart.block.LengthConstraintType;
import org.afree.chart.block.RectangleConstraint;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.GradientColor;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Shader;

/**
 * The graphical item within a legend item.
 */
public class LegendGraphic extends AbstractBlock 
                           implements Block, PublicCloneable {

    /** For serialization. */
    static final long serialVersionUID = -1338791523854985009L;

    /**
     * A flag that controls whether or not the shape is visible - see also
     * lineVisible.
     */
    private boolean shapeVisible;

    /**
     * The shape to display. To allow for accurate positioning, the center of
     * the shape should be at (0, 0).
     */
    private transient Shape shape;

    /**
     * Defines the location within the block to which the shape will be aligned.
     */
    private RectangleAnchor shapeLocation;

    /**
     * Defines the point on the shape's bounding RectShape that will be aligned
     * to the drawing location when the shape is rendered.
     */
    private RectangleAnchor shapeAnchor;

    /** A flag that controls whether or not the shape is filled. */
    private boolean shapeFilled;

    /** The fill paint for the shape. */
    private transient PaintType fillPaintType;

    /**
     * The fill paint transformer (used if the fillPaint is an instance of
     * GradientPaint).
     * 
     * @since JFreeChart 1.0.4
     */
    private GradientShaderFactory shaderFactory;

    /** A flag that controls whether or not the shape outline is visible. */
    private boolean shapeOutlineVisible;

    /** The outline paint for the shape. */
    private transient PaintType outlinePaintType;

    /** The outline stroke for the shape. */
    private transient float outlineStroke;

    /** The outline effect for the shape. */
    private transient PathEffect outlineEffect;

    /**
     * A flag that controls whether or not the line is visible - see also
     * shapeVisible.
     */
    private boolean lineVisible;

    /** The line. */
    private transient Shape line;

    /** The line stroke. */
    private transient float lineStroke;
    
    /** The line effect. */
    private transient PathEffect lineEffect;

    /** The line paint. */
    private transient PaintType linePaintType;

    /** RectShape buffer */
    private RectShape mWorkRectShape = new RectShape();
    
    /**
     * Creates a new legend graphic.
     * 
     * @param shape
     *            the shape (<code>null</code> not permitted).
     * @param fillPaintType
     *            the fill paint (<code>null</code> not permitted).
     */
    public LegendGraphic(Shape shape, PaintType fillPaintType) {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }
        if (fillPaintType == null) {
            throw new IllegalArgumentException("Null 'fillPaint' argument.");
        }
        this.shapeVisible = true;
        this.shape = shape;
        this.shapeAnchor = RectangleAnchor.CENTER;
        this.shapeLocation = RectangleAnchor.CENTER;
        this.shapeFilled = true;
        this.fillPaintType = fillPaintType;
        this.shaderFactory = new StandardGradientShaderFactory();
        setPadding(2.0, 2.0, 2.0, 2.0);
    }

    /**
     * Returns a flag that controls whether or not the shape is visible.
     * 
     * @return A boolean.
     * 
     * @see #setShapeVisible(boolean)
     */
    public boolean isShapeVisible() {
        return this.shapeVisible;
    }

    /**
     * Sets a flag that controls whether or not the shape is visible.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #isShapeVisible()
     */
    public void setShapeVisible(boolean visible) {
        this.shapeVisible = visible;
    }

    /**
     * Returns the shape.
     * 
     * @return The shape.
     * 
     * @see #setShape(Shape)
     */
    public Shape getShape() {
        return this.shape;
    }

    /**
     * Sets the shape.
     * 
     * @param shape
     *            the shape.
     * 
     * @see #getShape()
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Returns a flag that controls whether or not the shapes are filled.
     * 
     * @return A boolean.
     * 
     * @see #setShapeFilled(boolean)
     */
    public boolean isShapeFilled() {
        return this.shapeFilled;
    }

    /**
     * Sets a flag that controls whether or not the shape is filled.
     * 
     * @param filled
     *            the flag.
     * 
     * @see #isShapeFilled()
     */
    public void setShapeFilled(boolean filled) {
        this.shapeFilled = filled;
    }

    /**
     * Returns the paint used to fill the shape.
     * 
     * @return The fill paint.
     * 
     * @see #setFillPaintType(PaintType)
     */
    public PaintType getFillPaintType() {
        return this.fillPaintType;
    }

    /**
     * Sets the paint used to fill the shape.
     * 
     * @param paintType
     *            the paint.
     * 
     * @see #getFillPaintType()
     */
    public void setFillPaintType(PaintType paintType) {
        this.fillPaintType = paintType;
    }

    /**
     * Returns the transformer used when the fill paint is an instance of
     * <code>GradientPaint</code>.
     * 
     * @return The transformer (never <code>null</code>).
     * 
     * @since JFreeChart 1.0.4.
     * 
     * @see #setShaderFactory(GradientShaderFactory)
     */
    public GradientShaderFactory getShaderFactory() {
        return this.shaderFactory;
    }

    /**
     * Sets the transformer used when the fill paint is an instance of
     * <code>GradientPaint</code>.
     * 
     * @param factory
     *            the transformer (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.4
     * 
     * @see #getShaderFactory()
     */
    public void setShaderFactory(GradientShaderFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Null 'transformer' argument.");
        }
        this.shaderFactory = factory;
    }

    /**
     * Returns a flag that controls whether the shape outline is visible.
     * 
     * @return A boolean.
     * 
     * @see #setShapeOutlineVisible(boolean)
     */
    public boolean isShapeOutlineVisible() {
        return this.shapeOutlineVisible;
    }

    /**
     * Sets a flag that controls whether or not the shape outline is visible.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #isShapeOutlineVisible()
     */
    public void setShapeOutlineVisible(boolean visible) {
        this.shapeOutlineVisible = visible;
    }

    /**
     * Returns the outline paint.
     * 
     * @return The paint type.
     * 
     * @see #setOutlinePaintType(PaintType)
     */
    public PaintType getOutlinePaintTypw() {
        return this.outlinePaintType;
    }

    /**
     * Sets the outline paint.
     * 
     * @param paintType
     *            the paint.
     * 
     * @see #getOutlinePaintTypw()
     */
    public void setOutlinePaintType(PaintType paintType) {
        this.outlinePaintType = paintType;
    }

    /**
     * Returns the outline stroke.
     * 
     * @return The stroke.
     * 
     * @see #setOutlineStroke(float stroke)
     */
    public float getOutlineStroke() {
        return this.outlineStroke;
    }

    /**
     * Sets the outline stroke.
     * 
     * @param stroke
     *            the stroke.
     * 
     * @see #getOutlineStroke()
     */
    public void setOutlineStroke(float stroke) {
        this.outlineStroke = stroke;
    }

    /**
     * Returns the outline effect.
     * 
     * @return The effect.
     * 
     * @see #setOutlineEffect(PathEffect effect)
     */
    public PathEffect getOutlineEffect() {
        return this.outlineEffect;
    }

    /**
     * Sets the outline effect.
     * 
     * @param effect
     *            the effect.
     * 
     * @see #getOutlineEffect()
     */
    public void setOutlineEffect(PathEffect effect) {
        this.outlineEffect = effect;
    }

    /**
     * Returns the shape anchor.
     * 
     * @return The shape anchor.
     * 
     * @see #getShapeAnchor()
     */
    public RectangleAnchor getShapeAnchor() {
        return this.shapeAnchor;
    }

    /**
     * Sets the shape anchor. This defines a point on the shapes bounding
     * RectShape that will be used to align the shape to a location.
     * 
     * @param anchor
     *            the anchor (<code>null</code> not permitted).
     * 
     * @see #setShapeAnchor(RectangleAnchor)
     */
    public void setShapeAnchor(RectangleAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.shapeAnchor = anchor;
    }

    /**
     * Returns the shape location.
     * 
     * @return The shape location.
     * 
     * @see #setShapeLocation(RectangleAnchor)
     */
    public RectangleAnchor getShapeLocation() {
        return this.shapeLocation;
    }

    /**
     * Sets the shape location. This defines a point within the drawing area
     * that will be used to align the shape to.
     * 
     * @param location
     *            the location (<code>null</code> not permitted).
     * 
     * @see #getShapeLocation()
     */
    public void setShapeLocation(RectangleAnchor location) {
        if (location == null) {
            throw new IllegalArgumentException("Null 'location' argument.");
        }
        this.shapeLocation = location;
    }

    /**
     * Returns the flag that controls whether or not the line is visible.
     * 
     * @return A boolean.
     * 
     * @see #setLineVisible(boolean)
     */
    public boolean isLineVisible() {
        return this.lineVisible;
    }

    /**
     * Sets the flag that controls whether or not the line is visible.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #isLineVisible()
     */
    public void setLineVisible(boolean visible) {
        this.lineVisible = visible;
    }

    /**
     * Returns the line centered about (0, 0).
     * 
     * @return The line.
     * 
     * @see #setLine(Shape)
     */
    public Shape getLine() {
        return this.line;
    }

    /**
     * Sets the line. A Shape is used here, because then you can use LineShape,
     * PathShape or any other Shape to represent the line.
     * 
     * @param line
     *            the line.
     * 
     * @see #getLine()
     */
    public void setLine(Shape line) {
        this.line = line;
    }

    /**
     * Returns the line paint.
     * 
     * @return The paint type.
     * 
     * @see #setLinePaintType(PaintType)
     */
    public PaintType getLinePaintType() {
        return this.linePaintType;
    }

    /**
     * Sets the line paint.
     * 
     * @param paintType
     *            the paint.
     * 
     * @see #getLinePaintType()
     */
    public void setLinePaintType(PaintType paintType) {
        this.linePaintType = paintType;
    }

    /**
     * Returns the line stroke.
     * 
     * @return The stroke.
     * 
     * @see #setLineStroke(float stroke)
     */
    public float getLineStroke() {
        return this.lineStroke;
    }

    /**
     * Sets the line stroke.
     * 
     * @param stroke
     *            the stroke.
     * 
     * @see #getLineStroke()
     */
    public void setLineStroke(float stroke) {
        this.lineStroke = stroke;
    }

    /**
     * Returns the line effect.
     * 
     * @return The effect.
     * 
     * @see #setLineEffect(PathEffect effect)
     */
    public PathEffect getLineEffect() {
        return this.lineEffect;
    }

    /**
     * Sets the line effect.
     * 
     * @param effect
     *            the effect.
     * 
     * @see #getLineEffect()
     */
    public void setLineEffect(PathEffect effect) {
        this.lineEffect = effect;
    }

    /**
     * Arranges the contents of the block, within the given constraints, and
     * returns the block size.
     * 
     * @param canvas
     *            the graphics device.
     * @param constraint
     *            the constraint (<code>null</code> not permitted).
     * 
     * @return The block size (in Canvas units, never <code>null</code>).
     */
    public Size2D arrange(Canvas canvas, RectangleConstraint constraint) {
        RectangleConstraint contentConstraint = toContentConstraint(constraint);
        LengthConstraintType w = contentConstraint.getWidthConstraintType();
        LengthConstraintType h = contentConstraint.getHeightConstraintType();
        Size2D contentSize = null;
        if (w == LengthConstraintType.NONE) {
            if (h == LengthConstraintType.NONE) {
                contentSize = arrangeNN(canvas);
            } else if (h == LengthConstraintType.RANGE) {
                throw new RuntimeException("Not yet implemented.");
            } else if (h == LengthConstraintType.FIXED) {
                throw new RuntimeException("Not yet implemented.");
            }
        } else if (w == LengthConstraintType.RANGE) {
            if (h == LengthConstraintType.NONE) {
                throw new RuntimeException("Not yet implemented.");
            } else if (h == LengthConstraintType.RANGE) {
                throw new RuntimeException("Not yet implemented.");
            } else if (h == LengthConstraintType.FIXED) {
                throw new RuntimeException("Not yet implemented.");
            }
        } else if (w == LengthConstraintType.FIXED) {
            if (h == LengthConstraintType.NONE) {
                throw new RuntimeException("Not yet implemented.");
            } else if (h == LengthConstraintType.RANGE) {
                throw new RuntimeException("Not yet implemented.");
            } else if (h == LengthConstraintType.FIXED) {
                contentSize = new Size2D(contentConstraint.getWidth(),
                        contentConstraint.getHeight());
            }
        }
        return new Size2D(calculateTotalWidth(contentSize.getWidth()),
                calculateTotalHeight(contentSize.getHeight()));
    }

    /**
     * Performs the layout with no constraint, so the content size is determined
     * by the bounds of the shape and/or line drawn to represent the series.
     * 
     * @param canvas
     *            the graphics device.
     * 
     * @return The content size.
     */
    protected Size2D arrangeNN(Canvas canvas) {
        //performance tuning
        RectShape contentSize = new RectShape();
        if (this.line != null) {
            //contentSize.setRect(this.line.getBounds());
            this.line.getBounds(this.mWorkRectShape);
            contentSize.setRect(this.mWorkRectShape);
        }
        if (this.shape != null) {
            //contentSize = contentSize.createUnion(this.shape.getBounds());
            this.shape.getBounds(this.mWorkRectShape);
            contentSize = contentSize.createUnion(this.mWorkRectShape);
        }
        return new Size2D(contentSize.getWidth(), contentSize.getHeight());
    }

    /**
     * Draws the graphic item within the specified area.
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area.
     */
    public void draw(Canvas canvas, RectShape area) {

        area = trimMargin(area);
        drawBorder(canvas, area);
        area = trimBorder(area);
        area = trimPadding(area);

        if (this.lineVisible) {
            PointF location = RectangleAnchor.coordinates(area,
                    this.shapeLocation);
            Shape aLine = ShapeUtilities.createTranslatedShape(getLine(),
                    this.shapeAnchor, location.x, location.y);

            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    linePaintType, 
                    lineStroke, 
                    lineEffect);
            aLine.draw(canvas, paint);
        }

        if (this.shapeVisible) {
            PointF location = RectangleAnchor.coordinates(area,
                    this.shapeLocation);

            Shape s = ShapeUtilities.createTranslatedShape(this.shape,
                    this.shapeAnchor, location.x, location.y);

            Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, fillPaintType);
            if (this.shapeFilled) {
                if(this.fillPaintType instanceof GradientColor) {
                    Shader shader = this.shaderFactory.create((GradientColor)this.fillPaintType, s);
                    paint.setShader(shader);
                }
                s.fill(canvas, paint);
            }
            if (this.shapeOutlineVisible) {
                paint = PaintUtility.createPaint(
                        Paint.ANTI_ALIAS_FLAG,
                        outlinePaintType, 
                        outlineStroke, 
                        outlineEffect);
                s.draw(canvas, paint);
            }
        }

    }

    /**
     * Draws the block within the specified area.
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area.
     * @param params
     *            ignored (<code>null</code> permitted).
     * 
     * @return Always <code>null</code>.
     */
    public Object draw(Canvas canvas, RectShape area, Object params) {
        draw(canvas, area);
        return null;
    }

    /**
     * Tests this <code>LegendGraphic</code> instance for equality with an
     * arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof LegendGraphic)) {
            return false;
        }
        LegendGraphic that = (LegendGraphic) obj;
        if (this.shapeVisible != that.shapeVisible) {
            return false;
        }
        
        if (!ShapeUtilities.equal(this.shape, that.shape)) {
            return false;
        }
        if (this.shapeFilled != that.shapeFilled) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.fillPaintType, that.fillPaintType)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.shaderFactory,
                that.shaderFactory)) {
            return false;
        }
        if (this.shapeOutlineVisible != that.shapeOutlineVisible) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.outlinePaintType, that.outlinePaintType)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) {
            return false;
        }
        if (this.shapeAnchor != that.shapeAnchor) {
            return false;
        }
        if (this.shapeLocation != that.shapeLocation) {
            return false;
        }
        if (this.lineVisible != that.lineVisible) {
            return false;
        }
        if (!ShapeUtilities.equal(this.line, that.line)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.linePaintType, that.linePaintType)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.lineStroke, that.lineStroke)) {
            return false;
        }
                
        return super.equals(obj);

    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = 193;
        result = 37 * result + ObjectUtilities.hashCode(this.fillPaintType);
        // FIXME: use other fields too
        return result;
    }
    
    
    /**
     * Returns a clone of this <code>LegendGraphic</code> instance.
     *
     * @return A clone of this <code>LegendGraphic</code> instance.
     *
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    public Object clone() throws CloneNotSupportedException {
        LegendGraphic clone = (LegendGraphic) super.clone();
        clone.shape = ShapeUtilities.clone(this.shape);
        clone.line = ShapeUtilities.clone(this.line);
        return clone;
    }    
    
}
