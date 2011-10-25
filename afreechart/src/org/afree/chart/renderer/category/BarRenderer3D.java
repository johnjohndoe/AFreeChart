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
 * ------------------
 * BarRenderer3D.java
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
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2001-2009, by Serge V. Grachov and Contributors.
 *
 * Original Author:  Serge V. Grachov;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Tin Luu;
 *                   Milo Simpson;
 *                   Richard Atkinson;
 *                   Rich Unger;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 31-Oct-2001 : First version, contributed by Serge V. Grachov (DG);
 * 15-Nov-2001 : Modified to allow for null data values (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 16-Jan-2002 : Added fix for single category or single series datasets,
 *               pointed out by Taoufik Romdhane (DG);
 * 24-May-2002 : Incorporated tooltips into chart entities (DG);
 * 11-Jun-2002 : Added check for (permitted) null info object, bug and fix
 *               reported by David Basten.  Also updated Javadocs. (DG);
 * 19-Jun-2002 : Added code to draw labels on bars (TL);
 * 26-Jun-2002 : Added bar clipping to avoid PRExceptions (DG);
 * 05-Aug-2002 : Small modification to drawCategoryItem method to support URLs
 *               for HTML image maps (RA);
 * 06-Aug-2002 : Value labels now use number formatter, thanks to Milo
 *               Simpson (DG);
 * 08-Aug-2002 : Applied fixed in bug id 592218 (DG);
 * 20-Sep-2002 : Added fix for categoryPaint by Rich Unger, and fixed errors
 *               reported by Checkstyle (DG);
 * 24-Oct-2002 : Amendments for changes in CategoryDataset interface and
 *               CategoryToolTipGenerator interface (DG);
 * 05-Nov-2002 : Replaced references to CategoryDataset with TableDataset (DG);
 * 06-Nov-2002 : Moved to the com.jrefinery.chart.renderer package (DG);
 * 28-Jan-2003 : Added an attribute to control the shading of the left and
 *               bottom walls in the plot background (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 10-Apr-2003 : Removed category paint usage (DG);
 * 13-May-2003 : Renamed VerticalBarRenderer3D --> BarRenderer3D and merged with
 *               HorizontalBarRenderer3D (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 19-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 07-Oct-2003 : Added renderer state (DG);
 * 08-Oct-2003 : Removed clipping (replaced with flag in CategoryPlot to
 *               control order in which the data items are processed) (DG);
 * 20-Oct-2003 : Fixed bug (outline stroke not being used for bar
 *               outlines) (DG);
 * 21-Oct-2003 : Bar width moved into CategoryItemRendererState (DG);
 * 24-Nov-2003 : Fixed bug 846324 (item labels not showing) (DG);
 * 27-Nov-2003 : Added code to respect maxBarWidth setting (DG);
 * 02-Feb-2004 : Fixed bug where 'drawBarOutline' flag is not respected (DG);
 * 10-Feb-2004 : Small change to drawItem() method to make cut-and-paste
 *               overriding easier (DG);
 * 04-Oct-2004 : Fixed bug with item label positioning when plot alignment is
 *               horizontal (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator
 *               --> CategoryItemLabelGenerator (DG);
 * 25-Apr-2005 : Override initialise() method to fix bug 1189642 (DG);
 * 09-Jun-2005 : Use addEntityItem from super class (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 07-Dec-2006 : Implemented equals() override (DG);
 * 17-Jan-2007 : Fixed bug in drawDomainGridline() method (DG);
 * 03-Apr-2007 : Fixed bugs in drawBackground() method (DG);
 * 16-Oct-2007 : Fixed bug in range marker drawing (DG);
 * 19-Mar-2009 : Override for drawRangeLine() method (DG);
 *
 */

package org.afree.chart.renderer.category;

import java.io.Serializable;

import org.afree.ui.LengthAdjustmentType;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleEdge;
import org.afree.ui.TextAnchor;
import org.afree.chart.axis.CategoryAxis;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.Effect3D;
import org.afree.data.category.CategoryDataset;
import org.afree.data.Range;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.event.RendererChangeEvent;
import org.afree.chart.labels.CategoryItemLabelGenerator;
import org.afree.chart.labels.ItemLabelAnchor;
import org.afree.chart.labels.ItemLabelPosition;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.Marker;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.ValueMarker;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.PathShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;


/**
 * A renderer for bars with a 3D effect, for use with the
 * {@link CategoryPlot} class.  The example shown here is generated
 * by the <code>BarChart3DDemo1.java</code> program included in the AFreeChart
 * Demo Collection:
 * <br><br>
 * <img src="../../../../../images/BarRenderer3DSample.png"
 * alt="BarRenderer3DSample.png" />
 */
public class BarRenderer3D extends BarRenderer
        implements Effect3D, Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 7686976503536003636L;

    /** The default x-offset for the 3D effect. */
    public static final double DEFAULT_X_OFFSET = 12.0;

    /** The default y-offset for the 3D effect. */
    public static final double DEFAULT_Y_OFFSET = 8.0;

    /** The default wall paint. */
    public static final PaintType DEFAULT_WALL_PAINT = new SolidColor(0xFF888888);

    /** The size of x-offset for the 3D effect. */
    private double xOffset;

    /** The size of y-offset for the 3D effect. */
    private double yOffset;

    /** The paint used to shade the left and lower 3D wall. */
    private transient PaintType wallPaintType;

    /**
     * Default constructor, creates a renderer with a default '3D effect'.
     */
    public BarRenderer3D() {
        this(DEFAULT_X_OFFSET, DEFAULT_Y_OFFSET);
    }

    /**
     * Constructs a new renderer with the specified '3D effect'.
     *
     * @param xOffset  the x-offset for the 3D effect.
     * @param yOffset  the y-offset for the 3D effect.
     */
    public BarRenderer3D(double xOffset, double yOffset) {

        super();
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.wallPaintType = DEFAULT_WALL_PAINT;
        // set the default item label positions
        ItemLabelPosition p1 = new ItemLabelPosition(ItemLabelAnchor.INSIDE12,
                TextAnchor.TOP_CENTER);
        setBasePositiveItemLabelPosition(p1);
        ItemLabelPosition p2 = new ItemLabelPosition(ItemLabelAnchor.INSIDE12,
                TextAnchor.TOP_CENTER);
        setBaseNegativeItemLabelPosition(p2);

    }

    /**
     * Returns the x-offset for the 3D effect.
     *
     * @return The 3D effect.
     *
     * @see #getYOffset()
     */
    public double getXOffset() {
        return this.xOffset;
    }

    /**
     * Returns the y-offset for the 3D effect.
     *
     * @return The 3D effect.
     */
    public double getYOffset() {
        return this.yOffset;
    }

    /**
     * Returns the paint used to highlight the left and bottom wall in the plot
     * background.
     *
     * @return The paint type.
     *
     * @see #setWallPaintType(PaintType)
     */
    public PaintType getWallPaintType() {
        return this.wallPaintType;
    }

    /**
     * Sets the paint used to hightlight the left and bottom walls in the plot
     * background, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getWallPaintType()
     */
    public void setWallPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.wallPaintType = paintType;
        //fireChangeEvent();
    }


    /**
     * Initialises the renderer and returns a state object that will be passed
     * to subsequent calls to the drawItem method.  This method gets called
     * once at the start of the process of drawing a chart.
     *
     * @param canvas  the graphics device.
     * @param dataArea  the area in which the data is to be plotted.
     * @param plot  the plot.
     * @param rendererIndex  the renderer index.
     * @param info  collects chart rendering information for return to caller.
     *
     * @return The renderer state.
     */
    public CategoryItemRendererState initialise(Canvas canvas,
                                                RectShape dataArea,
                                                CategoryPlot plot,
                                                int rendererIndex,
                                                PlotRenderingInfo info) {

        RectShape adjusted = new RectShape(dataArea.getX(),
                dataArea.getY() + getYOffset(), dataArea.getWidth()
                - getXOffset(), dataArea.getHeight() - getYOffset());
        CategoryItemRendererState state = super.initialise(canvas, adjusted, plot,
                rendererIndex, info);
        return state;

    }
    
    /**
     * Draws the background for the plot.
     *
     * @param canvas  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the area inside the axes.
     */
    public void drawBackground(Canvas canvas, CategoryPlot plot,
                               RectShape dataArea) {

        
        float x0 = (float) dataArea.getX();
        float x1 = x0 + (float) Math.abs(this.xOffset);
        float x3 = (float) dataArea.getMaxX();
        float x2 = x3 - (float) Math.abs(this.xOffset);

        float y0 = (float) dataArea.getMaxY();
        float y1 = y0 - (float) Math.abs(this.yOffset);
        float y3 = (float) dataArea.getMinY();
        float y2 = y3 + (float) Math.abs(this.yOffset);

        PathShape clip = new PathShape();
        clip.moveTo(x0, y0);
        clip.lineTo(x0, y2);
        clip.lineTo(x1, y3);
        clip.lineTo(x3, y3);
        clip.lineTo(x3, y1);
        clip.lineTo(x2, y0);
        clip.closePath();

        // fill background...
        PaintType backgroundPaintType = plot.getBackgroundPaintType();
        
        if (backgroundPaintType != null) {
            Paint backgroundPaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, backgroundPaintType);
            clip.fill(canvas, backgroundPaint);
        }

        Paint wallPaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getWallPaintType());
        PathShape leftWall = new PathShape();
        leftWall.moveTo(x0, y0);
        leftWall.lineTo(x0, y2);
        leftWall.lineTo(x1, y3);
        leftWall.lineTo(x1, y1);
        leftWall.closePath();
        leftWall.fill(canvas, wallPaint);

        PathShape bottomWall = new PathShape();
        bottomWall.moveTo(x0, y0);
        bottomWall.lineTo(x1, y1);
        bottomWall.lineTo(x3, y1);
        bottomWall.lineTo(x2, y0);
        bottomWall.closePath();
        bottomWall.fill(canvas, wallPaint);

        // highlight the background corners...
        wallPaint.setColor(Color.LTGRAY);
        LineShape corner = new LineShape(x0, y0, x1, y1);
        corner.draw(canvas, wallPaint);
        corner.setLine(x1, y1, x1, y3);
        corner.draw(canvas, wallPaint);
        corner.setLine(x1, y1, x3, y1);
        corner.draw(canvas, wallPaint);

        // draw background image, if there is one...
        // TODO: implement draw background image
        /*Image backgroundImage = plot.getBackgroundImage();
        if (backgroundImage != null) {
            RectShape adjusted = new RectShape(dataArea.getX()
                    + getXOffset(), dataArea.getY(),
                    dataArea.getWidth() - getXOffset(),
                    dataArea.getHeight() - getYOffset());
            plot.drawBackgroundImage(canvas, adjusted);
        }*/

        //canvas.setComposite(originalComposite);
        //canvas.setAlpha(oldAlpha);

    }
    
    /**
     * Draws the outline for the plot.
     *
     * @param canvas  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the area inside the axes.
     */
    public void drawOutline(Canvas canvas, CategoryPlot plot,
                            RectShape dataArea) {

        float x0 = (float) dataArea.getX();
        float x1 = x0 + (float) Math.abs(this.xOffset);
        float x3 = (float) dataArea.getMaxX();
        float x2 = x3 - (float) Math.abs(this.xOffset);

        float y0 = (float) dataArea.getMaxY();
        float y1 = y0 - (float) Math.abs(this.yOffset);
        float y3 = (float) dataArea.getMinY();
        float y2 = y3 + (float) Math.abs(this.yOffset);

        PathShape clip = new PathShape();
        clip.moveTo(x0, y0);
        clip.lineTo(x0, y2);
        clip.lineTo(x1, y3);
        clip.lineTo(x3, y3);
        clip.lineTo(x3, y1);
        clip.lineTo(x2, y0);
        clip.closePath();

        // put an outline around the data area...
        PaintType outlinePaintType = plot.getOutlinePaintType();
        if ((plot.getOutlineStroke() != 0.0f) && (outlinePaintType != null)) {
            Paint outlinePaint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG, 
                    outlinePaintType,
                    plot.getOutlineStroke(),
                    plot.getOutlineEffect());
            outlinePaint.setStrokeWidth(plot.getOutlineStroke());
            clip.draw(canvas, outlinePaint);
        }

    }
    
    /**
     * Draws a grid line against the domain axis.
     *
     * @param canvas  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the area for plotting data (not yet adjusted for any
     *                  3D effect).
     * @param value  the Java2D value at which the grid line should be drawn.
     *
     */
    public void drawDomainGridline(Canvas canvas,
                                   CategoryPlot plot,
                                   RectShape dataArea,
                                   double value) {

        LineShape line1 = null;
        LineShape line2 = null;
        PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            double y0 = value;
            double y1 = value - getYOffset();
            double x0 = dataArea.getMinX();
            double x1 = x0 + getXOffset();
            double x2 = dataArea.getMaxX();
            line1 = new LineShape(x0, y0, x1, y1);
            line2 = new LineShape(x1, y1, x2, y1);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            double x0 = value;
            double x1 = value + getXOffset();
            double y0 = dataArea.getMaxY();
            double y1 = y0 - getYOffset();
            double y2 = dataArea.getMinY();
            line1 = new LineShape(x0, y0, x1, y1);
            line2 = new LineShape(x1, y1, x1, y2);
        }
        
        PaintType paintType = plot.getDomainGridlinePaintType();
        if ( paintType == null ) {
            paintType = Plot.DEFAULT_OUTLINE_PAINT_TYPE;
        }
        
        float stroke = plot.getDomainGridlineStroke();
        if ( stroke != 0.0f ) {
            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG, 
                    paintType, 
                    stroke, 
                    plot.getOutlineEffect());
            line1.draw(canvas, paint);
            line2.draw(canvas, paint);
        }

    }
    
    /**
     * Draws a grid line against the range axis.
     *
     * @param canvas  the graphics device.
     * @param plot  the plot.
     * @param axis  the value axis.
     * @param dataArea  the area for plotting data (not yet adjusted for any
     *                  3D effect).
     * @param value  the value at which the grid line should be drawn.
     *
     */
    public void drawRangeGridline(Canvas canvas, CategoryPlot plot,
            ValueAxis axis, RectShape dataArea, double value) {

        Range range = axis.getRange();

        if (!range.contains(value)) {
            return;
        }

        RectShape adjusted = new RectShape(dataArea.getX(),
                dataArea.getY() + getYOffset(), dataArea.getWidth()
                - getXOffset(), dataArea.getHeight() - getYOffset());

        LineShape line1 = null;
        LineShape line2 = null;
        PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            double x0 = axis.valueToJava2D(value, adjusted,
                    plot.getRangeAxisEdge());
            double x1 = x0 + getXOffset();
            double y0 = dataArea.getMaxY();
            double y1 = y0 - getYOffset();
            double y2 = dataArea.getMinY();
            line1 = new LineShape(x0, y0, x1, y1);
            line2 = new LineShape(x1, y1, x1, y2);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            double y0 = axis.valueToJava2D(value, adjusted,
                    plot.getRangeAxisEdge());
            double y1 = y0 - getYOffset();
            double x0 = dataArea.getMinX();
            double x1 = x0 + getXOffset();
            double x2 = dataArea.getMaxX();
            line1 = new LineShape(x0, y0, x1, y1);
            line2 = new LineShape(x1, y1, x2, y1);
        }
        PaintType paintType = plot.getRangeGridlinePaintType();

        if ( paintType == null ) {
            paintType = Plot.DEFAULT_OUTLINE_PAINT_TYPE;
        }
        
        float stroke = plot.getRangeGridlineStroke();
        if ( plot.getRangeGridlineStroke() != 0.0f ) {
            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG, 
                    paintType, 
                    stroke, 
                    plot.getOutlineEffect());

            line1.draw(canvas, paint);
            line2.draw(canvas, paint);
        }
    }
    
    /**
     * Draws a line perpendicular to the range axis.
     *
     * @param canvas  the graphics device.
     * @param plot  the plot.
     * @param axis  the value axis.
     * @param dataArea  the area for plotting data (not yet adjusted for any 3D
     *                  effect).
     * @param value  the value at which the grid line should be drawn.
     * @param paint  the paint.
     * @param stroke  the stroke.
     *
     * @see #drawRangeGridline
     *
     * @since JFreeChart 1.0.13
     */
       public void drawRangeLine(Canvas canvas, CategoryPlot plot, ValueAxis axis,
               RectShape dataArea, double value, Paint paint, float stroke) {

        Range range = axis.getRange();
        if (!range.contains(value)) {
            return;
        }

        RectShape adjusted = new RectShape(dataArea.getX(),
                dataArea.getY() + getYOffset(), dataArea.getWidth()
                - getXOffset(), dataArea.getHeight() - getYOffset());

        LineShape line1 = null;
        LineShape line2 = null;
        PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            double x0 = axis.valueToJava2D(value, adjusted,
                    plot.getRangeAxisEdge());
            double x1 = x0 + getXOffset();
            double y0 = dataArea.getMaxY();
            double y1 = y0 - getYOffset();
            double y2 = dataArea.getMinY();
            line1 = new LineShape(x0, y0, x1, y1);
            line2 = new LineShape(x1, y1, x1, y2);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            double y0 = axis.valueToJava2D(value, adjusted,
                    plot.getRangeAxisEdge());
            double y1 = y0 - getYOffset();
            double x0 = dataArea.getMinX();
            double x1 = x0 + getXOffset();
            double x2 = dataArea.getMaxX();
            line1 = new LineShape(x0, y0, x1, y1);
            line2 = new LineShape(x1, y1, x2, y1);
        }
        paint.setStrokeWidth(stroke);
        line1.draw(canvas, paint);
        line2.draw(canvas, paint);
    }
       
    /**
     * Draws a range marker.
     *
     * @param canvas  the graphics device.
     * @param plot  the plot.
     * @param axis  the value axis.
     * @param marker  the marker.
     * @param dataArea  the area for plotting data (not including 3D effect).
     */
       public void drawRangeMarker(Canvas canvas,
                                CategoryPlot plot,
                                ValueAxis axis,
                                Marker marker,
                                RectShape dataArea) {


        RectShape adjusted = new RectShape(dataArea.getX(),
                dataArea.getY() + getYOffset(), dataArea.getWidth()
                - getXOffset(), dataArea.getHeight() - getYOffset());
        if (marker instanceof ValueMarker) {
            ValueMarker vm = (ValueMarker) marker;
            double value = vm.getValue();
            Range range = axis.getRange();
            if (!range.contains(value)) {
                return;
            }

            PathShape path = null;
            PlotOrientation orientation = plot.getOrientation();
            if (orientation == PlotOrientation.HORIZONTAL) {
                float x = (float) axis.valueToJava2D(value, adjusted,
                        plot.getRangeAxisEdge());
                float y = (float) adjusted.getMaxY();
                path = new PathShape();
                path.moveTo(x, y);
                path.lineTo((float) (x + getXOffset()),
                        y - (float) getYOffset());
                path.lineTo((float) (x + getXOffset()),
                        (float) (adjusted.getMinY() - getYOffset()));
                path.lineTo(x, (float) adjusted.getMinY());
                path.closePath();
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                float y = (float) axis.valueToJava2D(value, adjusted,
                        plot.getRangeAxisEdge());
                float x = (float) dataArea.getX();
                path = new PathShape();
                path.moveTo(x, y);
                path.lineTo(x + (float) this.xOffset, y - (float) this.yOffset);
                path.lineTo((float) (adjusted.getMaxX() + this.xOffset),
                        y - (float) this.yOffset);
                path.lineTo((float) (adjusted.getMaxX()), y);
                path.closePath();
            }
            
            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG, 
                    marker.getPaintType());
            path.fill(canvas, paint);
            
            paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG, 
                    marker.getOutlinePaintType(), 
                    marker.getOutlineStroke(), 
                    marker.getOutlineEffect());
            path.draw(canvas, paint);

            String label = marker.getLabel();
            RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {

                paint = PaintUtility.createPaint(
                        Paint.ANTI_ALIAS_FLAG, 
                        marker.getLabelPaintType(), 
                        marker.getLabelFont());

                RectShape rectShape = new RectShape();
                path.getBounds(rectShape);
                
                PointF coordinates = calculateRangeMarkerTextAnchorPoint(
                        canvas, orientation, dataArea, rectShape,
                        marker.getLabelOffset(), LengthAdjustmentType.EXPAND,
                        anchor);
                TextUtilities.drawAlignedString(label, canvas,
                        (float) coordinates.x, (float) coordinates.y,
                        marker.getLabelTextAnchor(), paint);
            }

        }
        else {
            super.drawRangeMarker(canvas, plot, axis, marker, adjusted);
            // TODO: draw the interval marker with a 3D effect
        }
    }

    /**
     * Draws a 3D bar to represent one data item.
     *
     * @param canvas  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area for plotting the data.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass index.
     */
       public void drawItem(Canvas canvas,
                         CategoryItemRendererState state,
                         RectShape dataArea,
                         CategoryPlot plot,
                         CategoryAxis domainAxis,
                         ValueAxis rangeAxis,
                         CategoryDataset dataset,
                         int row,
                         int column,
                         int pass) {

        // check the value we are plotting...
        Number dataValue = dataset.getValue(row, column);
        if (dataValue == null) {
            return;
        }

        double value = dataValue.doubleValue();

        RectShape adjusted = new RectShape(dataArea.getX(),
                dataArea.getY() + getYOffset(),
                dataArea.getWidth() - getXOffset(),
                dataArea.getHeight() - getYOffset());

        PlotOrientation orientation = plot.getOrientation();

        double barW0 = calculateBarW0(plot, orientation, adjusted, domainAxis,
                state, row, column);
        double[] barL0L1 = calculateBarL0L1(value);
        if (barL0L1 == null) {
            return;  // the bar is not visible
        }

        RectangleEdge edge = plot.getRangeAxisEdge();
        double transL0 = rangeAxis.valueToJava2D(barL0L1[0], adjusted, edge);
        double transL1 = rangeAxis.valueToJava2D(barL0L1[1], adjusted, edge);
        double barL0 = Math.min(transL0, transL1);
        double barLength = Math.abs(transL1 - transL0);

        // draw the bar...
        RectShape bar = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            bar = new RectShape(barL0, barW0, barLength,
                    state.getBarWidth());
        }
        else {
            bar = new RectShape(barW0, barL0, state.getBarWidth(),
                    barLength);
        }
        PaintType itemPaintType = getItemFillPaintType(row, column); 
        Paint itemPaint = PaintUtility.createPaint(
                Paint.ANTI_ALIAS_FLAG, 
                itemPaintType);
        
        bar.fill(canvas, itemPaint);

        double x0 = bar.getMinX();
        double x1 = x0 + getXOffset();
        double x2 = bar.getMaxX();
        double x3 = x2 + getXOffset();

        double y0 = bar.getMinY() - getYOffset();
        double y1 = bar.getMinY();
        double y2 = bar.getMaxY() - getYOffset();
        double y3 = bar.getMaxY();

        PathShape bar3dRight = null;
        PathShape bar3dTop = null;
        if (barLength > 0.0) {
            bar3dRight = new PathShape();
            bar3dRight.moveTo((float) x2, (float) y3);
            bar3dRight.lineTo((float) x2, (float) y1);
            bar3dRight.lineTo((float) x3, (float) y0);
            bar3dRight.lineTo((float) x3, (float) y2);
            bar3dRight.closePath();

            itemPaintType = itemPaintType.getDarkerSides();

            Paint rightPaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, itemPaintType);
            
            bar3dRight.fill(canvas, rightPaint);
        }

        bar3dTop = new PathShape();
        bar3dTop.moveTo((float) x0, (float) y1);
        bar3dTop.lineTo((float) x1, (float) y0);
        bar3dTop.lineTo((float) x3, (float) y0);
        bar3dTop.lineTo((float) x2, (float) y1);
        bar3dTop.closePath();
        bar3dTop.fill(canvas, itemPaint);

        if (isDrawBarOutline()
                && state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) {
            
            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG, 
                    getItemOutlinePaintType(row, column), 
                    getItemOutlineStroke(row, column),
                    getItemOutlineEffect(row, column));
            bar.draw(canvas, paint);
            if (bar3dRight != null) {
                bar3dRight.draw(canvas, paint);
            }
            if (bar3dTop != null) {
                bar3dTop.draw(canvas, paint);
            }
        }

        CategoryItemLabelGenerator generator
            = getItemLabelGenerator(row, column);
        if (generator != null && isItemLabelVisible(row, column)) {
            drawItemLabel(canvas, dataset, row, column, plot, generator, bar,
                    (value < 0.0));
        }

        // add an item entity, if this information is being collected
        EntityCollection entities = state.getEntityCollection();
        if (entities != null) {
            PathShape barOutline = new PathShape();
            barOutline.moveTo((float) x0, (float) y3);
            barOutline.lineTo((float) x0, (float) y1);
            barOutline.lineTo((float) x1, (float) y0);
            barOutline.lineTo((float) x3, (float) y0);
            barOutline.lineTo((float) x3, (float) y2);
            barOutline.lineTo((float) x2, (float) y3);
            barOutline.closePath();
            addItemEntity(entities, dataset, row, column, barOutline);
        }

    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BarRenderer3D)) {
            return false;
        }
        BarRenderer3D that = (BarRenderer3D) obj;
        if (this.xOffset != that.xOffset) {
            return false;
        }
        if (this.yOffset != that.yOffset) {
            return false;
        }
        /*
        if (!PaintUtilities.equal(this.wallPaint, that.wallPaint)) {
            return false;
        }
        */
        return super.equals(obj);
    }
    
//    /**
//     * Sets the paint used to hightlight the left and bottom walls in the plot
//     * background, and sends a {@link RendererChangeEvent} to all registered
//     * listeners.
//     *
//     * @param paint  the paint (<code>null</code> not permitted).
//     *
//     * @see #getWallPaint()
//     */
//    public void setWallPaint(Paint paint) {
//        if (paint == null) {
//            throw new IllegalArgumentException("Null 'paint' argument.");
//        }
//        this.wallPaint = paint;
//        fireChangeEvent();
//    }
}
