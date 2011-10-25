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
 * ---------------------------------
 * AbstractCategoryItemRenderer.java
 * ---------------------------------
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
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Peter Kolb (patch 2497611);
 *
 * Changes:
 * --------
 * 29-May-2002 : Version 1 (DG);
 * 06-Jun-2002 : Added accessor methods for the tool tip generator (DG);
 * 11-Jun-2002 : Made constructors protected (DG);
 * 26-Jun-2002 : Added axis to initialise method (DG);
 * 05-Aug-2002 : Added urlGenerator member variable plus accessors (RA);
 * 22-Aug-2002 : Added categoriesPaint attribute, based on code submitted by
 *               Janet Banks.  This can be used when there is only one series,
 *               and you want each category item to have a different color (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 29-Oct-2002 : Fixed bug where background image for plot was not being
 *               drawn (DG);
 * 05-Nov-2002 : Replaced references to CategoryDataset with TableDataset (DG);
 * 26-Nov 2002 : Replaced the isStacked() method with getRangeType() (DG);
 * 09-Jan-2003 : Renamed grid-line methods (DG);
 * 17-Jan-2003 : Moved plot classes into separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 12-May-2003 : Modified to take into account the plot orientation (DG);
 * 12-Aug-2003 : Very minor javadoc corrections (DB)
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 05-Nov-2003 : Fixed marker rendering bug (833623) (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 11-Feb-2004 : Modified labelling for markers (DG);
 * 12-Feb-2004 : Updated clone() method (DG);
 * 15-Apr-2004 : Created a new CategoryToolTipGenerator interface (DG);
 * 05-May-2004 : Fixed bug (948310) where interval markers extend outside axis
 *               range (DG);
 * 14-Jun-2004 : Fixed bug in drawRangeMarker() method - now uses 'paint' and
 *               'stroke' rather than 'outlinePaint' and 'outlineStroke' (DG);
 * 15-Jun-2004 : Interval markers can now use GradientPaint (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities
 *               --> TextUtilities (DG);
 * 01-Oct-2004 : Fixed bug 1029697, problem with label alignment in
 *               drawRangeMarker() method (DG);
 * 07-Jan-2005 : Renamed getRangeExtent() --> findRangeBounds() (DG);
 * 21-Jan-2005 : Modified return type of calculateRangeMarkerTextAnchorPoint()
 *               method (DG);
 * 08-Mar-2005 : Fixed positioning of marker labels (DG);
 * 20-Apr-2005 : Added legend label, tooltip and URL generators (DG);
 * 01-Jun-2005 : Handle one dimension of the marker label adjustment
 *               automatically (DG);
 * 09-Jun-2005 : Added utility method for adding an item entity (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Mar-2006 : Updated getLegendItems() to check seriesVisibleInLegend
 *               flags (DG);
 * 20-Jul-2006 : Set dataset and series indices in LegendItem (DG);
 * 23-Oct-2006 : Draw outlines for interval markers (DG);
 * 24-Oct-2006 : Respect alpha setting in markers, as suggested by Sergei
 *               Ivanov in patch 1567843 (DG);
 * 30-Nov-2006 : Added a check for series visibility in the getLegendItem()
 *               method (DG);
 * 07-Dec-2006 : Fix for equals() method (DG);
 * 22-Feb-2007 : Added createState() method (DG);
 * 01-Mar-2007 : Fixed interval marker drawing (patch 1670686 thanks to
 *               Sergei Ivanov) (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change, and deprecated
 *               itemLabelGenerator, toolTipGenerator and itemURLGenerator
 *               override fields (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 17-Jun-2008 : Apply legend shape, font and paint attributes (DG);
 * 26-Jun-2008 : Added crosshair support (DG);
 * 25-Nov-2008 : Fixed bug in findRangeBounds() method (DG);
 * 14-Jan-2009 : Update initialise() to store visible series indices (PK);
 * 21-Jan-2009 : Added drawRangeLine() method (DG);
 * 27-Mar-2009 : Added new findRangeBounds() method to account for hidden
 *               series (DG);
 * 01-Apr-2009 : Added new addEntity() method (DG);
 * 
 */

package org.afree.chart.renderer.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.afree.ui.LengthAdjustmentType;
import org.afree.util.ObjectList;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.chart.LegendItem;
import org.afree.chart.LegendItemCollection;
import org.afree.chart.axis.CategoryAxis;
import org.afree.chart.axis.ValueAxis;
import org.afree.data.category.CategoryDataset;
import org.afree.data.Range;
import org.afree.data.general.DatasetUtilities;
import org.afree.chart.entity.CategoryItemEntity;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.event.RendererChangeEvent;
import org.afree.chart.labels.CategoryItemLabelGenerator;
import org.afree.chart.labels.CategorySeriesLabelGenerator;
import org.afree.chart.labels.ItemLabelPosition;
import org.afree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.afree.chart.plot.CategoryCrosshairState;
import org.afree.chart.plot.CategoryMarker;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.DrawingSupplier;
import org.afree.chart.plot.IntervalMarker;
import org.afree.chart.plot.Marker;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.ValueMarker;
import org.afree.chart.renderer.AbstractRenderer;
import org.afree.chart.renderer.category.CategoryItemRenderer;
import org.afree.chart.renderer.category.CategoryItemRendererState;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.OvalShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;

/**
 * An abstract base class that you can use to implement a new
 * {@link CategoryItemRenderer}. When you create a new
 * {@link CategoryItemRenderer} you are not required to extend this class, but
 * it makes the job easier.
 */
public abstract class AbstractCategoryItemRenderer extends AbstractRenderer
        implements CategoryItemRenderer, Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 1247553218442497391L;

    /** The plot that the renderer is assigned to. */
    private CategoryPlot plot;

    /** A list of item label generators (one per series). */
    private ObjectList itemLabelGeneratorList;

    /** The base item label generator. */
    private CategoryItemLabelGenerator baseItemLabelGenerator;

    /** The legend item label generator. */
    private CategorySeriesLabelGenerator legendItemLabelGenerator;

    /** The legend item tool tip generator. */
    private CategorySeriesLabelGenerator legendItemToolTipGenerator;

    /** The legend item URL generator. */
    private CategorySeriesLabelGenerator legendItemURLGenerator;

    /** The number of rows in the dataset (temporary record). */
    private transient int rowCount;

    /** The number of columns in the dataset (temporary record). */
    private transient int columnCount;

    /**
     * Creates a new renderer with no tool tip generator and no URL generator.
     * The defaults (no tool tip or URL generators) have been chosen to minimise
     * the processing required to generate a default chart. If you require tool
     * tips or URLs, then you can easily add the required generators.
     */
    protected AbstractCategoryItemRenderer() {
        this.itemLabelGenerator = null;
        this.itemLabelGeneratorList = new ObjectList();
        this.legendItemLabelGenerator = new StandardCategorySeriesLabelGenerator();
    }

    /**
     * Returns the number of passes through the dataset required by the
     * renderer. This method returns <code>1</code>, subclasses should override
     * if they need more passes.
     * 
     * @return The pass count.
     */
    public int getPassCount() {
        return 1;
    }

    /**
     * Returns the plot that the renderer has been assigned to (where
     * <code>null</code> indicates that the renderer is not currently assigned
     * to a plot).
     * 
     * @return The plot (possibly <code>null</code>).
     * 
     * @see #setPlot(CategoryPlot)
     */
    public CategoryPlot getPlot() {
        return this.plot;
    }

    /**
     * Sets the plot that the renderer has been assigned to. This method is
     * usually called by the {@link CategoryPlot}, in normal usage you shouldn't
     * need to call this method directly.
     * 
     * @param plot
     *            the plot (<code>null</code> not permitted).
     * 
     * @see #getPlot()
     */
    public void setPlot(CategoryPlot plot) {
        if (plot == null) {
            throw new IllegalArgumentException("Null 'plot' argument.");
        }
        this.plot = plot;
    }

    // ITEM LABEL GENERATOR

    /**
     * Returns the item label generator for a data item. This implementation
     * simply passes control to the {@link #getSeriesItemLabelGenerator(int)}
     * method. If, for some reason, you want a different generator for
     * individual items, you can override this method.
     * 
     * @param row
     *            the row index (zero based).
     * @param column
     *            the column index (zero based).
     * 
     * @return The generator (possibly <code>null</code>).
     */
    public CategoryItemLabelGenerator getItemLabelGenerator(int row, int column) {
        return getSeriesItemLabelGenerator(row);
    }

    /**
     * Returns the item label generator for a series.
     * 
     * @param series
     *            the series index (zero based).
     * 
     * @return The generator (possibly <code>null</code>).
     * 
     * @see #setSeriesItemLabelGenerator(int, CategoryItemLabelGenerator)
     */
    public CategoryItemLabelGenerator getSeriesItemLabelGenerator(int series) {

        // return the generator for ALL series, if there is one...
        if (this.itemLabelGenerator != null) {
            return this.itemLabelGenerator;
        }

        // otherwise look up the generator table
        CategoryItemLabelGenerator generator = (CategoryItemLabelGenerator) this.itemLabelGeneratorList
                .get(series);
        if (generator == null) {
            generator = this.baseItemLabelGenerator;
        }
        return generator;

    }

    /**
     * Sets the item label generator for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero based).
     * @param generator
     *            the generator (<code>null</code> permitted).
     * 
     * @see #getSeriesItemLabelGenerator(int)
     */
    public void setSeriesItemLabelGenerator(int series,
            CategoryItemLabelGenerator generator) {
        this.itemLabelGeneratorList.set(series, generator);
         fireChangeEvent();
    }

    /**
     * Returns the base item label generator.
     * 
     * @return The generator (possibly <code>null</code>).
     * 
     * @see #setBaseItemLabelGenerator(CategoryItemLabelGenerator)
     */
    public CategoryItemLabelGenerator getBaseItemLabelGenerator() {
        return this.baseItemLabelGenerator;
    }

    /**
     * Sets the base item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param generator
     *            the generator (<code>null</code> permitted).
     * 
     * @see #getBaseItemLabelGenerator()
     */
    public void setBaseItemLabelGenerator(CategoryItemLabelGenerator generator) {
        this.baseItemLabelGenerator = generator;
         fireChangeEvent();
    }

    /**
     * Returns the number of rows in the dataset. This value is updated in the
     * {@link AbstractCategoryItemRenderer#initialise} method.
     * 
     * @return The row count.
     */
    public int getRowCount() {
        return this.rowCount;
    }

    /**
     * Returns the number of columns in the dataset. This value is updated in
     * the {@link AbstractCategoryItemRenderer#initialise} method.
     * 
     * @return The column count.
     */
    public int getColumnCount() {
        return this.columnCount;
    }

    /**
     * Creates a new state instance---this method is called from the
     * {@link #initialise(Graphics2D, RectShape, CategoryPlot, int, PlotRenderingInfo)}
     * method. Subclasses can override this method if they need to use a
     * subclass of {@link CategoryItemRendererState}.
     * 
     * @param info
     *            collects plot rendering info (<code>null</code> permitted).
     * 
     * @return The new state instance (never <code>null</code>).
     * 
     * @since JFreeChart 1.0.5
     */
    protected CategoryItemRendererState createState(PlotRenderingInfo info) {
        return new CategoryItemRendererState(info);
    }

    /**
     * Initialises the renderer and returns a state object that will be used for
     * the remainder of the drawing process for a single chart. The state object
     * allows for the fact that the renderer may be used simultaneously by
     * multiple threads (each thread will work with a separate state object).
     * 
     * @param canvas
     *            the graphics device.
     * @param dataArea
     *            the data area.
     * @param plot
     *            the plot.
     * @param rendererIndex
     *            the renderer index.
     * @param info
     *            an object for returning information about the structure of the
     *            plot (<code>null</code> permitted).
     * 
     * @return The renderer state.
     */
    public CategoryItemRendererState initialise(Canvas canvas,
            RectShape dataArea, CategoryPlot plot, int rendererIndex,
            PlotRenderingInfo info) {

        setPlot(plot);
        CategoryDataset data = plot.getDataset(rendererIndex);
        if (data != null) {
            this.rowCount = data.getRowCount();
            this.columnCount = data.getColumnCount();
        } else {
            this.rowCount = 0;
            this.columnCount = 0;
        }
        CategoryItemRendererState state = createState(info);
        int[] visibleSeriesTemp = new int[this.rowCount];
        int visibleSeriesCount = 0;
        for (int row = 0; row < this.rowCount; row++) {
            if (isSeriesVisible(row)) {
                visibleSeriesTemp[visibleSeriesCount] = row;
                visibleSeriesCount++;
            }
        }
        int[] visibleSeries = new int[visibleSeriesCount];
        System.arraycopy(visibleSeriesTemp, 0, visibleSeries, 0,
                visibleSeriesCount);
        state.setVisibleSeriesArray(visibleSeries);
        return state;
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     * 
     * @param dataset
     *            the dataset (<code>null</code> permitted).
     * 
     * @return The range (or <code>null</code> if the dataset is
     *         <code>null</code> or empty).
     */
    public Range findRangeBounds(CategoryDataset dataset) {
        return findRangeBounds(dataset, false);
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     * 
     * @param dataset
     *            the dataset (<code>null</code> permitted).
     * @param includeInterval
     *            include the y-interval if the dataset has one.
     * 
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     * 
     * @since JFreeChart 1.0.13
     */
    protected Range findRangeBounds(CategoryDataset dataset,
            boolean includeInterval) {
        if (dataset == null) {
            return null;
        }
        if (getDataBoundsIncludesVisibleSeriesOnly()) {
            List visibleSeriesKeys = new ArrayList();
            int seriesCount = dataset.getRowCount();
            for (int s = 0; s < seriesCount; s++) {
                if (isSeriesVisible(s)) {
                    visibleSeriesKeys.add(dataset.getRowKey(s));
                }
            }
            return DatasetUtilities.findRangeBounds(dataset, visibleSeriesKeys,
                    includeInterval);
        } else {
            return DatasetUtilities.findRangeBounds(dataset, includeInterval);
        }
    }

    /**
     * Returns the Java2D coordinate for the middle of the specified data item.
     * 
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the column key.
     * @param dataset
     *            the dataset.
     * @param axis
     *            the axis.
     * @param area
     *            the data area.
     * @param edge
     *            the edge along which the axis lies.
     * 
     * @return The Java2D coordinate for the middle of the item.
     * 
     * @since JFreeChart 1.0.11
     */
    public double getItemMiddle(Comparable rowKey, Comparable columnKey,
            CategoryDataset dataset, CategoryAxis axis, RectShape area,
            RectangleEdge edge) {
        return axis.getCategoryMiddle(columnKey, dataset.getColumnKeys(), area,
                edge);
    }

    /**
     * Draws a background for the data area. The default implementation just
     * gets the plot to draw the background, but some renderers will override
     * this behaviour.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param dataArea
     *            the data area.
     */
    public void drawBackground(Canvas canvas, CategoryPlot plot,
            RectShape dataArea) {

        plot.drawBackground(canvas, dataArea);

    }

    /**
     * Draws an outline for the data area. The default implementation just gets
     * the plot to draw the outline, but some renderers will override this
     * behaviour.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param dataArea
     *            the data area.
     */
    public void drawOutline(Canvas canvas, CategoryPlot plot, RectShape dataArea) {

        plot.drawOutline(canvas, dataArea);

    }

    /**
     * Draws a grid line against the domain axis.
     * <P>
     * Note that this default implementation assumes that the horizontal axis is
     * the domain axis. If this is not the case, you will need to override this
     * method.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param dataArea
     *            the area for plotting data (not yet adjusted for any 3D
     *            effect).
     * @param value
     *            the Java2D value at which the grid line should be drawn.
     * 
     * @see #drawRangeGridline(Canvas, CategoryPlot, ValueAxis, RectShape,
     *      double)
     */
    public void drawDomainGridline(Canvas canvas, CategoryPlot plot,
            RectShape dataArea, double value) {

        LineShape line = null;
        PlotOrientation orientation = plot.getOrientation();

        if (orientation == PlotOrientation.HORIZONTAL) {
            line = new LineShape(dataArea.getMinX(), value, dataArea
                    .getMaxX(), value);
        } else if (orientation == PlotOrientation.VERTICAL) {
            line = new LineShape(value, dataArea.getMinY(), value, dataArea
                    .getMaxY());
        }

        PaintType paintType = plot.getDomainGridlinePaintType();
        if (paintType == null) {
            paintType = CategoryPlot.DEFAULT_GRIDLINE_PAINT_TYPE;
        }

        float stroke = plot.getDomainGridlineStroke();
        if (stroke == 0.0f) {
            stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
        }

        Paint paint = PaintUtility.createPaint(
                paintType,
                stroke,
                plot.getDomainGridlineEffect());
        paint.setStrokeCap(Paint.Cap.ROUND);
        line.draw(canvas, paint);

    }

    /**
     * Draws a grid line against the range axis.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param axis
     *            the value axis.
     * @param dataArea
     *            the area for plotting data (not yet adjusted for any 3D
     *            effect).
     * @param value
     *            the value at which the grid line should be drawn.
     * 
     * @see #drawDomainGridline(Canvas, CategoryPlot, RectShape, double)
     */
    public void drawRangeGridline(Canvas canvas, CategoryPlot plot, ValueAxis axis,
            RectShape dataArea, double value) {

        Range range = axis.getRange();
        if (!range.contains(value)) {
            return;
        }

        PlotOrientation orientation = plot.getOrientation();
        double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
        LineShape line = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            line = new LineShape(v, dataArea.getMinY(), v, dataArea
                    .getMaxY());
        } else if (orientation == PlotOrientation.VERTICAL) {
            line = new LineShape(dataArea.getMinX(), v, dataArea.getMaxX(),
                    v);
        }

        PaintType paintType = plot.getRangeGridlinePaintType();
        if (paintType == null) {
            paintType = CategoryPlot.DEFAULT_GRIDLINE_PAINT_TYPE;
        }

        float stroke = plot.getRangeGridlineStroke();
        if (stroke == 0.0f) {
            stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
        }

        Paint paint = PaintUtility.createPaint(
                paintType,
                stroke,
                plot.getRangeGridlineEffect());
        paint.setStrokeCap(Paint.Cap.ROUND);
        line.draw(canvas, paint);

    }

    /**
     * Draws a line perpendicular to the range axis.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param axis
     *            the value axis.
     * @param dataArea
     *            the area for plotting data (not yet adjusted for any 3D
     *            effect).
     * @param value
     *            the value at which the grid line should be drawn.
     * @param paintType
     *            the paintType (<code>null</code> not permitted).
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * @param effect
     *            the effect (<code>null</code> not permitted).
     * 
     * @see #drawRangeGridline
     * 
     * @since JFreeChart 1.0.13
     */
    public void drawRangeLine(Canvas canvas, CategoryPlot plot, ValueAxis axis,
            RectShape dataArea, double value, PaintType paintType, Float stroke, PathEffect effect) {

        Range range = axis.getRange();
        if (!range.contains(value)) {
            return;
        }

        PlotOrientation orientation = plot.getOrientation();
        LineShape line = null;
        double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
        if (orientation == PlotOrientation.HORIZONTAL) {
            line = new LineShape(v, dataArea.getMinY(), v, dataArea
                    .getMaxY());
        } else if (orientation == PlotOrientation.VERTICAL) {
            line = new LineShape(dataArea.getMinX(), v, dataArea.getMaxX(),
                    v);
        }
        
        Paint paint = PaintUtility.createPaint(paintType, stroke, effect);

        paint.setStrokeWidth(stroke);
        canvas.drawLine((float) line.getX1(), (float) line.getY1(), (float) line
                .getX2(), (float) line.getY2(), paint);

    }

    /**
     * Draws a marker for the domain axis.
     * 
     * @param canvas
     *            the graphics device (not <code>null</code>).
     * @param plot
     *            the plot (not <code>null</code>).
     * @param axis
     *            the range axis (not <code>null</code>).
     * @param marker
     *            the marker to be drawn (not <code>null</code>).
     * @param dataArea
     *            the area inside the axes (not <code>null</code>).
     * 
     * @see #drawRangeMarker(Canvas, CategoryPlot, ValueAxis, Marker,
     *      RectShape)
     */
    public void drawDomainMarker(Canvas canvas, CategoryPlot plot,
            CategoryAxis axis, CategoryMarker marker, RectShape dataArea) {

        Comparable category = marker.getKey();
        CategoryDataset dataset = plot.getDataset(plot.getIndexOf(this));
        int columnIndex = dataset.getColumnIndex(category);
        if (columnIndex < 0) {
            return;
        }

        PlotOrientation orientation = plot.getOrientation();
        RectShape bounds = new RectShape();
        if (marker.getDrawAsLine()) {
            double v = axis.getCategoryMiddle(columnIndex, dataset
                    .getColumnCount(), dataArea, plot.getDomainAxisEdge());
            LineShape line = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                line = new LineShape(dataArea.getMinX(), v, dataArea
                        .getMaxX(), v);
            } else if (orientation == PlotOrientation.VERTICAL) {
                line = new LineShape(v, dataArea.getMinY(), v, dataArea
                        .getMaxY());
            }

            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    marker.getPaintType(),
                    marker.getStroke(),
                    marker.getEffect());
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setAlpha(marker.getAlpha());
            line.draw(canvas, paint);
            line.getBounds(bounds);
        } else {
            double v0 = axis.getCategoryStart(columnIndex, dataset
                    .getColumnCount(), dataArea, plot.getDomainAxisEdge());
            double v1 = axis.getCategoryEnd(columnIndex, dataset
                    .getColumnCount(), dataArea, plot.getDomainAxisEdge());
            RectShape area = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                area = new RectShape(dataArea.getMinX(), v0, dataArea
                        .getWidth(), (v1 - v0));
            } else if (orientation == PlotOrientation.VERTICAL) {
                area = new RectShape(v0, dataArea.getMinY(),
                        (v1 - v0), dataArea.getHeight());
            }
            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    marker.getPaintType());
            paint.setAlpha(marker.getAlpha());
            paint.setStrokeCap(Paint.Cap.ROUND);
            area.fill(canvas, paint);
            bounds = area;
        }

        String label = marker.getLabel();
        RectangleAnchor anchor = marker.getLabelAnchor();
        if (label != null) {
            PointF coordinates = calculateDomainMarkerTextAnchorPoint(canvas,
                    orientation, dataArea, bounds, marker.getLabelOffset(),
                    marker.getLabelOffsetType(), anchor);

            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    marker.getLabelPaintType(),
                    marker.getLabelFont());
            paint.setAlpha(marker.getAlpha());
            TextUtilities.drawAlignedString(label, canvas, (float) coordinates
                    .x, (float) coordinates.y, marker
                    .getLabelTextAnchor(), paint);
        }

    }

    /**
     * Draws a marker for the range axis.
     * 
     * @param canvas
     *            the graphics device (not <code>null</code>).
     * @param plot
     *            the plot (not <code>null</code>).
     * @param axis
     *            the range axis (not <code>null</code>).
     * @param marker
     *            the marker to be drawn (not <code>null</code>).
     * @param dataArea
     *            the area inside the axes (not <code>null</code>).
     * 
     * @see #drawDomainMarker(Canvas, CategoryPlot, CategoryAxis,
     *      CategoryMarker, RectShape)
     */
    public void drawRangeMarker(Canvas canvas, CategoryPlot plot, ValueAxis axis,
            Marker marker, RectShape dataArea) {

        if (marker instanceof ValueMarker) {
            ValueMarker vm = (ValueMarker) marker;
            double value = vm.getValue();
            Range range = axis.getRange();

            if (!range.contains(value)) {
                return;
            }

            PlotOrientation orientation = plot.getOrientation();
            double v = axis.valueToJava2D(value, dataArea, plot
                    .getRangeAxisEdge());
            LineShape line = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                line = new LineShape(v, dataArea.getMinY(), v, dataArea
                        .getMaxY());
            } else if (orientation == PlotOrientation.VERTICAL) {
                line = new LineShape(dataArea.getMinX(), v, dataArea
                        .getMaxX(), v);
            }

            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    marker.getPaintType(),
                    marker.getStroke(),
                    marker.getEffect());
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setAlpha(marker.getAlpha());
            line.draw(canvas, paint);
            
            String label = marker.getLabel();
            RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                RectShape rectShape = new RectShape();
                line.getBounds(rectShape);
                PointF coordinates = calculateRangeMarkerTextAnchorPoint(canvas,
                        orientation, dataArea, rectShape, marker
                                .getLabelOffset(), LengthAdjustmentType.EXPAND,
                        anchor);
                
                Paint labelPaint = PaintUtility.createPaint(
                        Paint.ANTI_ALIAS_FLAG,
                        marker.getLabelPaintType(),
                        marker.getLabelFont());
                labelPaint.setAlpha(marker.getAlpha());
                TextUtilities.drawAlignedString(label, canvas, (float) coordinates
                        .x, (float) coordinates.y, marker
                        .getLabelTextAnchor(), labelPaint);
            }

        } else if (marker instanceof IntervalMarker) {
            IntervalMarker im = (IntervalMarker) marker;
            double start = im.getStartValue();
            double end = im.getEndValue();
            Range range = axis.getRange();
            if (!(range.intersects(start, end))) {
                return;
            }

            double start2d = axis.valueToJava2D(start, dataArea, plot
                    .getRangeAxisEdge());
            double end2d = axis.valueToJava2D(end, dataArea, plot
                    .getRangeAxisEdge());
            double low = Math.min(start2d, end2d);
            double high = Math.max(start2d, end2d);

            PlotOrientation orientation = plot.getOrientation();
            RectShape rect = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                // clip left and right bounds to data area
                low = Math.max(low, dataArea.getMinX());
                high = Math.min(high, dataArea.getMaxX());
                rect = new RectShape(low, dataArea.getMinY(), high
                        - low, dataArea.getHeight());
            } else if (orientation == PlotOrientation.VERTICAL) {
                // clip top and bottom bounds to data area
                low = Math.max(low, dataArea.getMinY());
                high = Math.min(high, dataArea.getMaxY());
                rect = new RectShape(dataArea.getMinX(), low, dataArea
                        .getWidth(), high - low);
            }

            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    marker.getPaintType());
            paint.setAlpha(marker.getAlpha());
            rect.fill(canvas, paint);

            // now draw the outlines, if visible...
            if (im.getOutlinePaintType() != null && im.getOutlineStroke() != null) {
                if (orientation == PlotOrientation.VERTICAL) {
                    LineShape line = new LineShape();
                    double x0 = dataArea.getMinX();
                    double x1 = dataArea.getMaxX();

                    Paint outlinePaint = PaintUtility.createPaint(
                            Paint.ANTI_ALIAS_FLAG,
                            im.getOutlinePaintType(),
                            im.getOutlineStroke(),
                            im.getEffect());
                    outlinePaint.setAlpha(marker.getAlpha());
                    outlinePaint.setStrokeCap(Paint.Cap.ROUND);
                    if (range.contains(start)) {
                        line.setLine(x0, start2d, x1, start2d);
                        canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                                (float) line.getX2(), (float) line.getY2(),
                                outlinePaint);
                    }
                    if (range.contains(end)) {
                        line.setLine(x0, end2d, x1, end2d);
                        canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                                (float) line.getX2(), (float) line.getY2(),
                                outlinePaint);
                    }
                } else { // PlotOrientation.HORIZONTAL
                    LineShape line = new LineShape();
                    double y0 = dataArea.getMinY();
                    double y1 = dataArea.getMaxY();

                    Paint outlinePaint = PaintUtility.createPaint(
                            Paint.ANTI_ALIAS_FLAG,
                            im.getOutlinePaintType(),
                            im.getOutlineStroke(),
                            im.getOutlineEffect());
                    outlinePaint.setAlpha(marker.getAlpha());
                    outlinePaint.setStrokeCap(Paint.Cap.ROUND);
                    if (range.contains(start)) {
                        line.setLine(start2d, y0, start2d, y1);
                        canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                                (float) line.getX2(), (float) line.getY2(),
                                outlinePaint);
                    }
                    if (range.contains(end)) {
                        line.setLine(end2d, y0, end2d, y1);
                        canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                                (float) line.getX2(), (float) line.getY2(),
                                outlinePaint);
                    }
                }
            }

            String label = marker.getLabel();
            RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                PointF coordinates = calculateRangeMarkerTextAnchorPoint(canvas,
                        orientation, dataArea, rect, marker.getLabelOffset(),
                        marker.getLabelOffsetType(), anchor);
                Paint labelPaint = PaintUtility.createPaint(
                        Paint.ANTI_ALIAS_FLAG,
                        marker.getLabelPaintType(),
                        marker.getLabelFont());
                labelPaint.setAlpha(marker.getAlpha());
                TextUtilities.drawAlignedString(label, canvas, (float) coordinates
                        .x, (float) coordinates.y, marker
                        .getLabelTextAnchor(), labelPaint);
            }

        }
    }

    /**
     * Calculates the (x, y) coordinates for drawing the label for a marker on
     * the range axis.
     * 
     * @param canvas
     *            the graphics device.
     * @param orientation
     *            the plot orientation.
     * @param dataArea
     *            the data area.
     * @param markerArea
     *            the RectShape surrounding the marker.
     * @param markerOffset
     *            the marker offset.
     * @param labelOffsetType
     *            the label offset type.
     * @param anchor
     *            the label anchor.
     * 
     * @return The coordinates for drawing the marker label.
     */
    protected PointF calculateDomainMarkerTextAnchorPoint(Canvas canvas,
            PlotOrientation orientation, RectShape dataArea,
            RectShape markerArea, RectangleInsets markerOffset,
            LengthAdjustmentType labelOffsetType, RectangleAnchor anchor) {

        RectShape anchorRect = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            anchorRect = markerOffset.createAdjustedRectShape(markerArea,
                    LengthAdjustmentType.CONTRACT, labelOffsetType);
        } else if (orientation == PlotOrientation.VERTICAL) {
            anchorRect = markerOffset.createAdjustedRectShape(markerArea,
                    labelOffsetType, LengthAdjustmentType.CONTRACT);
        }
        return RectangleAnchor.coordinates(anchorRect, anchor);

    }

    /**
     * Calculates the (x, y) coordinates for drawing a marker label.
     * 
     * @param canvas
     *            the graphics device.
     * @param orientation
     *            the plot orientation.
     * @param dataArea
     *            the data area.
     * @param markerArea
     *            the RectShape surrounding the marker.
     * @param markerOffset
     *            the marker offset.
     * @param labelOffsetType
     *            the label offset type.
     * @param anchor
     *            the label anchor.
     * 
     * @return The coordinates for drawing the marker label.
     */
    protected PointF calculateRangeMarkerTextAnchorPoint(Canvas canvas,
            PlotOrientation orientation, RectShape dataArea,
            RectShape markerArea, RectangleInsets markerOffset,
            LengthAdjustmentType labelOffsetType, RectangleAnchor anchor) {

        RectShape anchorRect = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            anchorRect = markerOffset.createAdjustedRectShape(markerArea,
                    labelOffsetType, LengthAdjustmentType.CONTRACT);
        } else if (orientation == PlotOrientation.VERTICAL) {
            anchorRect = markerOffset.createAdjustedRectShape(markerArea,
                    LengthAdjustmentType.CONTRACT, labelOffsetType);
        }
        return RectangleAnchor.coordinates(anchorRect, anchor);

    }

    /**
     * Returns a legend item for a series. This default implementation will
     * return <code>null</code> if {@link #isSeriesVisible(int)} or
     * {@link #isSeriesVisibleInLegend(int)} returns <code>false</code>.
     * 
     * @param datasetIndex
     *            the dataset index (zero-based).
     * @param series
     *            the series index (zero-based).
     * 
     * @return The legend item (possibly <code>null</code>).
     * 
     * @see #getLegendItems()
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {

        CategoryPlot p = getPlot();
        if (p == null) {
            return null;
        }

        // check that a legend item needs to be displayed...
        if (!isSeriesVisible(series) || !isSeriesVisibleInLegend(series)) {
            return null;
        }

        CategoryDataset dataset = p.getDataset(datasetIndex);
        String label = this.legendItemLabelGenerator.generateLabel(dataset,
                series);
        String description = label;
        String toolTipText = null;
        if (this.legendItemToolTipGenerator != null) {
            toolTipText = this.legendItemToolTipGenerator.generateLabel(
                    dataset, series);
        }
        String urlText = null;
        if (this.legendItemURLGenerator != null) {
            urlText = this.legendItemURLGenerator
                    .generateLabel(dataset, series);
        }
        Shape shape = lookupLegendShape(series);
        PaintType paintType = lookupSeriesPaintType(series);
        PaintType outlinePaintType = lookupSeriesOutlinePaintType(series);
        Float outlineStroke = lookupSeriesOutlineStroke(series);

        LegendItem item = new LegendItem(label, description, toolTipText,
                urlText, shape, paintType, outlineStroke, outlinePaintType);
        item.setLabelFont(lookupLegendTextFont(series));
        PaintType labelPaintType = lookupLegendTextPaintType(series);
        if (labelPaintType != null) {
            item.setLabelPaintType(labelPaintType);
        }
        item.setSeriesKey(dataset.getRowKey(series));
        item.setSeriesIndex(series);
        item.setDataset(dataset);
        item.setDatasetIndex(datasetIndex);
        return item;
    }

    /**
     * Returns the drawing supplier from the plot.
     * 
     * @return The drawing supplier (possibly <code>null</code>).
     */
    public DrawingSupplier getDrawingSupplier() {
        DrawingSupplier result = null;
        CategoryPlot cp = getPlot();
        if (cp != null) {
            result = cp.getDrawingSupplier();
        }
        return result;
    }

    /**
     * Considers the current (x, y) coordinate and updates the crosshair point
     * if it meets the criteria (usually means the (x, y) coordinate is the
     * closest to the anchor point so far).
     * 
     * @param crosshairState
     *            the crosshair state (<code>null</code> permitted, but the
     *            method does nothing in that case).
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the column key.
     * @param value
     *            the data value.
     * @param datasetIndex
     *            the dataset index.
     * @param transX
     *            the x-value translated to Java2D space.
     * @param transY
     *            the y-value translated to Java2D space.
     * @param orientation
     *            the plot orientation (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.11
     */
    protected void updateCrosshairValues(CategoryCrosshairState crosshairState,
            Comparable rowKey, Comparable columnKey, double value,
            int datasetIndex, double transX, double transY,
            PlotOrientation orientation) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }

        if (crosshairState != null) {
            if (this.plot.isRangeCrosshairLockedOnData()) {
                // both axes
                crosshairState.updateCrosshairPoint(rowKey, columnKey, value,
                        datasetIndex, transX, transY, orientation);
            } else {
                crosshairState.updateCrosshairX(rowKey, columnKey,
                        datasetIndex, transX, orientation);
            }
        }
    }

    /**
     * Draws an item label.
     * 
     * @param canvas
     *            the graphics device.
     * @param orientation
     *            the orientation.
     * @param dataset
     *            the dataset.
     * @param row
     *            the row.
     * @param column
     *            the column.
     * @param x
     *            the x coordinate (in Java2D space).
     * @param y
     *            the y coordinate (in Java2D space).
     * @param negative
     *            indicates a negative value (which affects the item label
     *            position).
     */
    protected void drawItemLabel(Canvas canvas, PlotOrientation orientation,
            CategoryDataset dataset, int row, int column, double x, double y,
            boolean negative) {

        CategoryItemLabelGenerator generator = getItemLabelGenerator(row,
                column);
        if (generator != null) {
            String label = generator.generateLabel(dataset, row, column);
            ItemLabelPosition position = null;
            if (!negative) {
                position = getPositiveItemLabelPosition(row, column);
            } else {
                position = getNegativeItemLabelPosition(row, column);
            }
            PointF anchorPoint = calculateLabelAnchorPoint(position
                    .getItemLabelAnchor(), x, y, orientation);
            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    getItemLabelPaintType(row, column),
                    getItemLabelFont(row, column));
            TextUtilities.drawRotatedString(label, canvas, (float) anchorPoint
                    .x, (float) anchorPoint.y, position
                    .getTextAnchor(), position.getAngle(), position
                    .getRotationAnchor(), paint);
        }

    }

    /**
     * Returns a domain axis for a plot.
     * 
     * @param plot
     *            the plot.
     * @param index
     *            the axis index.
     * 
     * @return A domain axis.
     */
    protected CategoryAxis getDomainAxis(CategoryPlot plot, int index) {
        CategoryAxis result = plot.getDomainAxis(index);
        if (result == null) {
            result = plot.getDomainAxis();
        }
        return result;
    }

    /**
     * Returns a range axis for a plot.
     * 
     * @param plot
     *            the plot.
     * @param index
     *            the axis index.
     * 
     * @return A range axis.
     */
    protected ValueAxis getRangeAxis(CategoryPlot plot, int index) {
        ValueAxis result = plot.getRangeAxis(index);
        if (result == null) {
            result = plot.getRangeAxis();
        }
        return result;
    }

    /**
     * Returns a (possibly empty) collection of legend items for the series that
     * this renderer is responsible for drawing.
     * 
     * @return The legend item collection (never <code>null</code>).
     * 
     * @see #getLegendItem(int, int)
     */
    public LegendItemCollection getLegendItems() {
        if (this.plot == null) {
            return new LegendItemCollection();
        }
        LegendItemCollection result = new LegendItemCollection();
        int index = this.plot.getIndexOf(this);
        CategoryDataset dataset = this.plot.getDataset(index);
        if (dataset != null) {
            int seriesCount = dataset.getRowCount();
            for (int i = 0; i < seriesCount; i++) {
                if (isSeriesVisibleInLegend(i)) {
                    LegendItem item = getLegendItem(index, i);
                    if (item != null) {
                        result.add(item);
                    }
                }
            }

        }
        return result;
    }

    /**
     * Returns the legend item label generator.
     * 
     * @return The label generator (never <code>null</code>).
     * 
     * @see #setLegendItemLabelGenerator(CategorySeriesLabelGenerator)
     */
    public CategorySeriesLabelGenerator getLegendItemLabelGenerator() {
        return this.legendItemLabelGenerator;
    }

    /**
     * Sets the legend item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param generator
     *            the generator (<code>null</code> not permitted).
     * 
     * @see #getLegendItemLabelGenerator()
     */
    public void setLegendItemLabelGenerator(
            CategorySeriesLabelGenerator generator) {
        if (generator == null) {
            throw new IllegalArgumentException("Null 'generator' argument.");
        }
        this.legendItemLabelGenerator = generator;
         fireChangeEvent();
    }

    /**
     * Returns the legend item tool tip generator.
     * 
     * @return The tool tip generator (possibly <code>null</code>).
     * 
     * @see #setLegendItemToolTipGenerator(CategorySeriesLabelGenerator)
     */
    public CategorySeriesLabelGenerator getLegendItemToolTipGenerator() {
        return this.legendItemToolTipGenerator;
    }

    /**
     * Sets the legend item tool tip generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param generator
     *            the generator (<code>null</code> permitted).
     * 
     * @see #setLegendItemToolTipGenerator(CategorySeriesLabelGenerator)
     */
    public void setLegendItemToolTipGenerator(
            CategorySeriesLabelGenerator generator) {
        this.legendItemToolTipGenerator = generator;
        fireChangeEvent();
    }

    /**
     * Returns the legend item URL generator.
     * 
     * @return The URL generator (possibly <code>null</code>).
     * 
     * @see #setLegendItemURLGenerator(CategorySeriesLabelGenerator)
     */
    public CategorySeriesLabelGenerator getLegendItemURLGenerator() {
        return this.legendItemURLGenerator;
    }

    /**
     * Sets the legend item URL generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param generator
     *            the generator (<code>null</code> permitted).
     * 
     * @see #getLegendItemURLGenerator()
     */
    public void setLegendItemURLGenerator(CategorySeriesLabelGenerator generator) {
        this.legendItemURLGenerator = generator;
        fireChangeEvent();
    }

    /**
     * Adds an entity with the specified hotspot.
     * 
     * @param entities
     *            the entity collection.
     * @param dataset
     *            the dataset.
     * @param row
     *            the row index.
     * @param column
     *            the column index.
     * @param hotspot
     *            the hotspot (<code>null</code> not permitted).
     */
    protected void addItemEntity(EntityCollection entities,
            CategoryDataset dataset, int row, int column, Shape hotspot) {
        if (hotspot == null) {
            throw new IllegalArgumentException("Null 'hotspot' argument.");
        }
        if (!getItemCreateEntity(row, column)) {
            return;
        }

        CategoryItemEntity entity = new CategoryItemEntity(hotspot, "", "",
                dataset, dataset.getRowKey(row), dataset.getColumnKey(column));
        entities.add(entity);
    }

    /**
     * Adds an entity to the collection.
     * 
     * @param entities
     *            the entity collection being populated.
     * @param hotspot
     *            the entity area (if <code>null</code> a default will be used).
     * @param dataset
     *            the dataset.
     * @param row
     *            the series.
     * @param column
     *            the item.
     * @param entityX
     *            the entity's center x-coordinate in user space (only used if
     *            <code>area</code> is <code>null</code>).
     * @param entityY
     *            the entity's center y-coordinate in user space (only used if
     *            <code>area</code> is <code>null</code>).
     * 
     * @since JFreeChart 1.0.13
     */
    protected void addEntity(EntityCollection entities, Shape hotspot,
            CategoryDataset dataset, int row, int column, double entityX,
            double entityY) {
        if (!getItemCreateEntity(row, column)) {
            return;
        }
        Shape s = hotspot;
        if (hotspot == null) {
            double r = getDefaultEntityRadius();
            double w = r * 2;
            if (getPlot().getOrientation() == PlotOrientation.VERTICAL) {
                s = new OvalShape(entityX - r, entityY - r, w, w);
            } else {
                s = new OvalShape(entityY - r, entityX - r, w, w);
            }
        }

        CategoryItemEntity entity = new CategoryItemEntity(s, "", "", dataset,
                dataset.getRowKey(row), dataset.getColumnKey(column));
        entities.add(entity);
    }

//    /**
//     * Sets the tool tip generator for a series and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param series  the series index (zero-based).
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @see #getSeriesToolTipGenerator(int)
//     */
//    public void setSeriesToolTipGenerator(int series,
//                                          CategoryToolTipGenerator generator) {
//        this.toolTipGeneratorList.set(series, generator);
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the base tool tip generator and sends a {@link RendererChangeEvent}
//     * to all registered listeners.
//     *
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @see #getBaseToolTipGenerator()
//     */
//    public void setBaseToolTipGenerator(CategoryToolTipGenerator generator) {
//        this.baseToolTipGenerator = generator;
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the URL generator for a series and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param series  the series index (zero based).
//     * @param generator  the generator.
//     *
//     * @see #getSeriesItemURLGenerator(int)
//     */
//    public void setSeriesItemURLGenerator(int series,
//                                          CategoryURLGenerator generator) {
//        this.itemURLGeneratorList.set(series, generator);
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the base item URL generator and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param generator  the item URL generator (<code>null</code> permitted).
//     *
//     * @see #getBaseItemURLGenerator()
//     */
//    public void setBaseItemURLGenerator(CategoryURLGenerator generator) {
//        this.baseItemURLGenerator = generator;
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the item label generator for ALL series and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @deprecated This method should no longer be used (as of version 1.0.6).
//     *     It is sufficient to rely on {@link #setSeriesItemLabelGenerator(int,
//     *     CategoryItemLabelGenerator)} and
//     *     {@link #setBaseItemLabelGenerator(CategoryItemLabelGenerator)}.
//     */
//    public void setItemLabelGenerator(CategoryItemLabelGenerator generator) {
//        this.itemLabelGenerator = generator;
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the tool tip generator for ALL series and sends a
//     * {@link org.jfree.chart.event.RendererChangeEvent} to all registered
//     * listeners.
//     *
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @see #getToolTipGenerator()
//     *
//     * @deprecated This method should no longer be used (as of version 1.0.6).
//     *     It is sufficient to rely on {@link #setSeriesToolTipGenerator(int,
//     *     CategoryToolTipGenerator)} and
//     *     {@link #setBaseToolTipGenerator(CategoryToolTipGenerator)}.
//     */
//    public void setToolTipGenerator(CategoryToolTipGenerator generator) {
//        this.toolTipGenerator = generator;
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the item URL generator for ALL series and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param generator  the generator.
//     *
//     * @deprecated This method should no longer be used (as of version 1.0.6).
//     *     It is sufficient to rely on {@link #setSeriesItemURLGenerator(int,
//     *     CategoryURLGenerator)} and
//     *     {@link #setBaseItemURLGenerator(CategoryURLGenerator)}.
//     */
//    public void setItemURLGenerator(CategoryURLGenerator generator) {
//        this.itemURLGenerator = generator;
//        fireChangeEvent();
//    }
    
    // === DEPRECATED CODE ===

    /**
     * The item label generator for ALL series.
     * 
     * @deprecated This field is redundant and deprecated as of version 1.0.6.
     */
    private CategoryItemLabelGenerator itemLabelGenerator;

}
