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
 * ---------------------------
 * AbstractXYItemRenderer.java
 * ---------------------------
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
 * 17-Dec-2010 : performance tuning
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Focus Computer Services Limited;
 *                   Tim Bardzil;
 *                   Sergei Ivanov;
 *
 * Changes:
 * --------
 * 15-Mar-2002 : Version 1 (DG);
 * 09-Apr-2002 : Added a getToolTipGenerator() method reflecting the change in
 *               the XYItemRenderer interface (DG);
 * 05-Aug-2002 : Added a urlGenerator member variable to support HTML image
 *               maps (RA);
 * 20-Aug-2002 : Added property change events for the tooltip and URL
 *               generators (DG);
 * 22-Aug-2002 : Moved property change support into AbstractRenderer class (DG);
 * 23-Sep-2002 : Fixed errors reported by Checkstyle tool (DG);
 * 18-Nov-2002 : Added methods for drawing grid lines (DG);
 * 17-Jan-2003 : Moved plot classes into a separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified initialise() return type and drawItem() method
 *               signature (DG);
 * 15-May-2003 : Modified to take into account the plot orientation (DG);
 * 21-May-2003 : Added labels to markers (DG);
 * 05-Jun-2003 : Added domain and range grid bands (sponsored by Focus Computer
 *               Services Ltd) (DG);
 * 27-Jul-2003 : Added getRangeType() to support stacked XY area charts (RA);
 * 31-Jul-2003 : Deprecated all but the default constructor (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 05-Nov-2003 : Fixed marker rendering bug (833623) (DG);
 * 11-Feb-2004 : Updated labelling for markers (DG);
 * 25-Feb-2004 : Added updateCrosshairValues() method.  Moved deprecated code
 *               to bottom of source file (DG);
 * 16-Apr-2004 : Added support for IntervalMarker in drawRangeMarker() method
 *               - thanks to Tim Bardzil (DG);
 * 05-May-2004 : Fixed bug (948310) where interval markers extend beyond axis
 *               range (DG);
 * 03-Jun-2004 : Fixed more bugs in drawing interval markers (DG);
 * 26-Aug-2004 : Added the addEntity() method (DG);
 * 29-Sep-2004 : Added annotation support (with layers) (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities -->
 *               TextUtilities (DG);
 * 06-Oct-2004 : Added findDomainBounds() method and renamed
 *               getRangeExtent() --> findRangeBounds() (DG);
 * 07-Jan-2005 : Removed deprecated code (DG);
 * 27-Jan-2005 : Modified getLegendItem() to omit hidden series (DG);
 * 24-Feb-2005 : Added getLegendItems() method (DG);
 * 08-Mar-2005 : Fixed positioning of marker labels (DG);
 * 20-Apr-2005 : Renamed XYLabelGenerator --> XYItemLabelGenerator and
 *               added generators for legend labels, tooltips and URLs (DG);
 * 01-Jun-2005 : Handle one dimension of the marker label adjustment
 *               automatically (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Set dataset and series indices in LegendItem (DG);
 * 24-Oct-2006 : Respect alpha setting in markers (see patch 1567843 by Sergei
 *               Ivanov) (DG);
 * 24-Oct-2006 : Added code to draw outlines for interval markers (DG);
 * 24-Nov-2006 : Fixed cloning for legend item generators (DG);
 * 06-Feb-2007 : Added new updateCrosshairValues() method that takes into
 *               account multiple axis plots (see bug 1086307) (DG);
 * 20-Feb-2007 : Fixed equals() method implementation (DG);
 * 01-Mar-2007 : Fixed interval marker drawing (patch 1670686 thanks to
 *               Sergei Ivanov) (DG);
 * 22-Mar-2007 : Modified the tool tip generator look up (DG);
 * 23-Mar-2007 : Added drawDomainLine() method (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change, and deprecated
 *               itemLabelGenerator and toolTipGenerator override fields (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 12-Nov-2007 : Fixed domain and range band drawing methods (DG);
 * 07-Apr-2008 : Minor API doc update (DG);
 * 14-May-2008 : Updated addEntity() method to take plot orientation into
 *               account when the incoming area is null (DG);
 * 02-Jun-2008 : Added isPointInRect() method (DG);
 * 17-Jun-2008 : Apply legend shape, font and paint attributes (DG);
 * 09-Mar-2009 : Added getAnnotations() method (DG);
 * 27-Mar-2009 : Added new findDomainBounds() and findRangeBounds() methods to
 *               take account of hidden series (DG);
 * 01-Apr-2009 : Moved defaultEntityRadius up to superclass (DG);
 * 
 */

package org.afree.chart.renderer.xy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.afree.ui.Layer;
import org.afree.ui.LengthAdjustmentType;
import org.afree.util.ObjectList;
import org.afree.util.ObjectUtilities;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleInsets;
import org.afree.chart.LegendItem;
import org.afree.chart.LegendItemCollection;
import org.afree.chart.annotations.XYAnnotation;
import org.afree.chart.axis.ValueAxis;
import org.afree.data.Range;
import org.afree.data.xy.XYDataset;
import org.afree.data.general.DatasetUtilities;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.entity.XYItemEntity;
import org.afree.chart.event.RendererChangeEvent;
import org.afree.chart.labels.CategorySeriesLabelGenerator;
import org.afree.chart.labels.ItemLabelPosition;
import org.afree.chart.labels.StandardXYSeriesLabelGenerator;
import org.afree.chart.labels.XYItemLabelGenerator;
import org.afree.chart.labels.XYSeriesLabelGenerator;
import org.afree.chart.labels.XYToolTipGenerator;
import org.afree.chart.plot.CrosshairState;
import org.afree.chart.plot.DrawingSupplier;
import org.afree.chart.plot.IntervalMarker;
import org.afree.chart.plot.Marker;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.ValueMarker;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.AbstractRenderer;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.Font;
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
 * A base class that can be used to create new {@link XYItemRenderer}
 * implementations.
 */
public abstract class AbstractXYItemRenderer extends AbstractRenderer implements XYItemRenderer {

    /** For serialization. */
    private static final long serialVersionUID = 8019124836026607990L;

    /** The plot. */
    private XYPlot plot;

    /** A list of item label generators (one per series). */
    private ObjectList itemLabelGeneratorList;

    /** The base item label generator. */
    private XYItemLabelGenerator baseItemLabelGenerator;

    /** A list of tool tip generators (one per series). */
    private ObjectList toolTipGeneratorList;

    /** The base tool tip generator. */
    private XYToolTipGenerator baseToolTipGenerator;

    /**
     * Annotations to be drawn in the background layer ('underneath' the data
     * items).
     */
    private List backgroundAnnotations;

    /**
     * Annotations to be drawn in the foreground layer ('on top' of the data
     * items).
     */
    private List foregroundAnnotations;

    /** The legend item label generator. */
    private XYSeriesLabelGenerator legendItemLabelGenerator;

    /** The legend item tool tip generator. */
    private XYSeriesLabelGenerator legendItemToolTipGenerator;

    /** The legend item URL generator. */
    private XYSeriesLabelGenerator legendItemURLGenerator;

    /** work LineShape object */
    private LineShape mWorkLineShape = new LineShape();
    
    /**
     * Creates a renderer where the tooltip generator and the URL generator are
     * both <code>null</code>.
     */
    protected AbstractXYItemRenderer() {
        super();
        this.itemLabelGeneratorList = new ObjectList();
        this.toolTipGeneratorList = new ObjectList();
        this.backgroundAnnotations = new java.util.ArrayList();
        this.foregroundAnnotations = new java.util.ArrayList();
        this.legendItemLabelGenerator = new StandardXYSeriesLabelGenerator("{0}");
    }

    /**
     * Returns the number of passes through the data that the renderer requires
     * in order to draw the chart. Most charts will require a single pass, but
     * some require two passes.
     * 
     * @return The pass count.
     */
    public int getPassCount() {
        return 1;
    }

    /**
     * Returns the plot that the renderer is assigned to.
     * 
     * @return The plot (possibly <code>null</code>).
     */
    public XYPlot getPlot() {
        return this.plot;
    }

    /**
     * Sets the plot that the renderer is assigned to.
     * 
     * @param plot
     *            the plot (<code>null</code> permitted).
     */
    public void setPlot(XYPlot plot) {
        this.plot = plot;
    }

    /**
     * Initialises the renderer and returns a state object that should be passed
     * to all subsequent calls to the drawItem() method.
     * <P>
     * This method will be called before the first item is rendered, giving the
     * renderer an opportunity to initialise any state information it wants to
     * maintain. The renderer can do nothing if it chooses.
     * 
     * @param canvas
     *            the graphics device.
     * @param dataArea
     *            the area inside the axes.
     * @param plot
     *            the plot.
     * @param data
     *            the data.
     * @param info
     *            an optional info collection object to return data back to the
     *            caller.
     * 
     * @return The renderer state (never <code>null</code>).
     */
    public XYItemRendererState initialise(Canvas canvas, RectShape dataArea, XYPlot plot,
            XYDataset data, PlotRenderingInfo info) {

        XYItemRendererState state = new XYItemRendererState(info);
        return state;

    }

    // ITEM LABEL GENERATOR

    /**
     * Returns the label generator for a data item. This implementation simply
     * passes control to the {@link #getSeriesItemLabelGenerator(int)} method.
     * If, for some reason, you want a different generator for individual items,
     * you can override this method.
     * 
     * @param series
     *            the series index (zero based).
     * @param item
     *            the item index (zero based).
     * 
     * @return The generator (possibly <code>null</code>).
     */
    public XYItemLabelGenerator getItemLabelGenerator(int series, int item) {

        // otherwise look up the generator table
        XYItemLabelGenerator generator = (XYItemLabelGenerator) this.itemLabelGeneratorList
                .get(series);
        if (generator == null) {
            generator = this.baseItemLabelGenerator;
        }
        return generator;
    }

    /**
     * Returns the item label generator for a series.
     * 
     * @param series
     *            the series index (zero based).
     * 
     * @return The generator (possibly <code>null</code>).
     */
    public XYItemLabelGenerator getSeriesItemLabelGenerator(int series) {
        return (XYItemLabelGenerator) this.itemLabelGeneratorList.get(series);
    }

    /**
     * Sets the item label generator for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero based).
     * @param generator
     *            the generator (<code>null</code> permitted).
     */
    public void setSeriesItemLabelGenerator(int series, XYItemLabelGenerator generator) {
        this.itemLabelGeneratorList.set(series, generator);
         fireChangeEvent();
    }

    /**
     * Returns the base item label generator.
     * 
     * @return The generator (possibly <code>null</code>).
     */
    public XYItemLabelGenerator getBaseItemLabelGenerator() {
        return this.baseItemLabelGenerator;
    }

    /**
     * Sets the base item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param generator
     *            the generator (<code>null</code> permitted).
     */
    public void setBaseItemLabelGenerator(XYItemLabelGenerator generator) {
        this.baseItemLabelGenerator = generator;
         fireChangeEvent();
    }

    /**
     * Returns the tool tip generator for a data item.  If, for some reason,
     * you want a different generator for individual items, you can override
     * this method.
     *
     * @param series  the series index (zero based).
     * @param item  the item index (zero based).
     *
     * @return The generator (possibly <code>null</code>).
     */
    public XYToolTipGenerator getToolTipGenerator(int series, int item) {

        // otherwise look up the generator table
        XYToolTipGenerator generator = (XYToolTipGenerator) this.toolTipGeneratorList.get(series);
        if (generator == null) {
            generator = this.baseToolTipGenerator;
        }
        return generator;
    }

    /**
     * Returns the tool tip generator for a series.
     * 
     * @param series
     *            the series index (zero based).
     * 
     * @return The generator (possibly <code>null</code>).
     */
    public XYToolTipGenerator getSeriesToolTipGenerator(int series) {
        return (XYToolTipGenerator) this.toolTipGeneratorList.get(series);
    }

    /**
     * Adds an annotation and sends a {@link RendererChangeEvent} to all
     * registered listeners. The annotation is added to the foreground layer.
     * 
     * @param annotation
     *            the annotation (<code>null</code> not permitted).
     */
    public void addAnnotation(XYAnnotation annotation) {
        // defer argument checking
        addAnnotation(annotation, Layer.FOREGROUND);
    }

    /**
     * Adds an annotation to the specified layer and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param annotation
     *            the annotation (<code>null</code> not permitted).
     * @param layer
     *            the layer (<code>null</code> not permitted).
     */
    public void addAnnotation(XYAnnotation annotation, Layer layer) {
        if (annotation == null) {
            throw new IllegalArgumentException("Null 'annotation' argument.");
        }
        if (layer.equals(Layer.FOREGROUND)) {
            this.foregroundAnnotations.add(annotation);
             fireChangeEvent();
        } else if (layer.equals(Layer.BACKGROUND)) {
            this.backgroundAnnotations.add(annotation);
             fireChangeEvent();
        } else {
            // should never get here
            throw new RuntimeException("Unknown layer.");
        }
    }

    /**
     * Removes the specified annotation and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     * 
     * @param annotation
     *            the annotation to remove (<code>null</code> not permitted).
     * 
     * @return A boolean to indicate whether or not the annotation was
     *         successfully removed.
     */
    public boolean removeAnnotation(XYAnnotation annotation) {
        boolean removed = this.foregroundAnnotations.remove(annotation);
        removed = removed & this.backgroundAnnotations.remove(annotation);
         fireChangeEvent();
        return removed;
    }

    /**
     * Removes all annotations and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     */
    public void removeAnnotations() {
        this.foregroundAnnotations.clear();
        this.backgroundAnnotations.clear();
         fireChangeEvent();
    }

    /**
     * Returns a collection of the annotations that are assigned to the
     * renderer.
     * 
     * @return A collection of annotations (possibly empty but never
     *         <code>null</code>).
     * 
     * @since JFreeChart 1.0.13
     */
    public Collection getAnnotations() {
        List result = new java.util.ArrayList(this.foregroundAnnotations);
        result.addAll(this.backgroundAnnotations);
        return result;
    }

    /**
     * Returns the legend item label generator.
     * 
     * @return The label generator (never <code>null</code>).
     * 
     * @see #setLegendItemLabelGenerator(XYSeriesLabelGenerator)
     */
    public XYSeriesLabelGenerator getLegendItemLabelGenerator() {
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
    public void setLegendItemLabelGenerator(XYSeriesLabelGenerator generator) {
        if (generator == null) {
            throw new IllegalArgumentException("Null 'generator' argument.");
        }
        this.legendItemLabelGenerator = generator;
         fireChangeEvent();
    }

    /**
     * Returns the lower and upper bounds (range) of the x-values in the
     * specified dataset.
     * 
     * @param dataset
     *            the dataset (<code>null</code> permitted).
     * 
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     * 
     * @see #findRangeBounds(XYDataset)
     */
    public Range findDomainBounds(XYDataset dataset) {
        return findDomainBounds(dataset, false);
    }

    /**
     * Returns the lower and upper bounds (range) of the x-values in the
     * specified dataset.
     * 
     * @param dataset
     *            the dataset (<code>null</code> permitted).
     * 
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     * 
     * @since JFreeChart 1.0.13
     */
    protected Range findDomainBounds(XYDataset dataset, boolean includeInterval) {
        if (dataset == null) {
            return null;
        }
        if (getDataBoundsIncludesVisibleSeriesOnly()) {
            List visibleSeriesKeys = new ArrayList();
            int seriesCount = dataset.getSeriesCount();
            for (int s = 0; s < seriesCount; s++) {
                if (isSeriesVisible(s)) {
                    visibleSeriesKeys.add(dataset.getSeriesKey(s));
                }
            }
            return DatasetUtilities.findDomainBounds(dataset, visibleSeriesKeys, includeInterval);
        } else {
            return DatasetUtilities.findDomainBounds(dataset, includeInterval);
        }
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     * 
     * @param dataset
     *            the dataset (<code>null</code> permitted).
     * 
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     * 
     * @see #findDomainBounds(XYDataset)
     */
    public Range findRangeBounds(XYDataset dataset) {
        return findRangeBounds(dataset, false);
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     * 
     * @param dataset
     *            the dataset (<code>null</code> permitted).
     * 
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     * 
     * @since JFreeChart 1.0.13
     */
    protected Range findRangeBounds(XYDataset dataset, boolean includeInterval) {
        if (dataset == null) {
            return null;
        }
        if (getDataBoundsIncludesVisibleSeriesOnly()) {
            List visibleSeriesKeys = new ArrayList();
            int seriesCount = dataset.getSeriesCount();
            for (int s = 0; s < seriesCount; s++) {
                if (isSeriesVisible(s)) {
                    visibleSeriesKeys.add(dataset.getSeriesKey(s));
                }
            }
            // the bounds should be calculated using just the items within
            // the current range of the x-axis...if there is one
            Range xRange = null;
            XYPlot p = getPlot();
            if (p != null) {
                ValueAxis xAxis = null;
                int index = p.getIndexOf(this);
                if (index >= 0) {
                    xAxis = plot.getDomainAxisForDataset(index);
                }
                if (xAxis != null) {
                    xRange = xAxis.getRange();
                }
            }
            if (xRange == null) {
                xRange = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            }
            return DatasetUtilities.findRangeBounds(dataset, visibleSeriesKeys, xRange,
                    includeInterval);
        } else {
            return DatasetUtilities.findRangeBounds(dataset, includeInterval);
        }
    }

    /**
     * Returns a (possibly empty) collection of legend items for the series that
     * this renderer is responsible for drawing.
     * 
     * @return The legend item collection (never <code>null</code>).
     */
    public LegendItemCollection getLegendItems() {
        if (this.plot == null) {
            return new LegendItemCollection();
        }
        LegendItemCollection result = new LegendItemCollection();
        int index = this.plot.getIndexOf(this);
        XYDataset dataset = this.plot.getDataset(index);
        if (dataset != null) {
            int seriesCount = dataset.getSeriesCount();
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
     * Returns a default legend item for the specified series. Subclasses should
     * override this method to generate customised items.
     * 
     * @param datasetIndex
     *            the dataset index (zero-based).
     * @param series
     *            the series index (zero-based).
     * 
     * @return A legend item for the series.
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
        LegendItem result = null;
        XYPlot xyplot = getPlot();
        if (xyplot != null) {
            XYDataset dataset = xyplot.getDataset(datasetIndex);
            if (dataset != null) {
                String label = this.legendItemLabelGenerator.generateLabel(dataset, series);
                String description = label;

                Shape shape = lookupLegendShape(series);
                PaintType paintType = lookupSeriesPaintType(series);
                PaintType outlinePaintType = lookupSeriesOutlinePaintType(series);
                Float outlineStroke = lookupSeriesOutlineStroke(series);
                result = new LegendItem(label, description, "", "", shape, paintType,
                        outlineStroke, outlinePaintType);
                PaintType labelPaintType = lookupLegendTextPaintType(series);
                result.setLabelFont(lookupLegendTextFont(series));
                if (labelPaintType != null) {
                    result.setLabelPaintType(labelPaintType);
                }
                result.setSeriesKey(dataset.getSeriesKey(series));
                result.setSeriesIndex(series);
                result.setDataset(dataset);
                result.setDatasetIndex(datasetIndex);
            }
        }
        return result;
    }

    /**
     * Fills a band between two values on the axis. This can be used to color
     * bands between the grid lines.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param axis
     *            the domain axis.
     * @param dataArea
     *            the data area.
     * @param start
     *            the start value.
     * @param end
     *            the end value.
     */
    public void fillDomainGridBand(Canvas canvas, XYPlot plot, ValueAxis axis, RectShape dataArea,
            double start, double end) {

        double x1 = axis.valueToJava2D(start, dataArea, plot.getDomainAxisEdge());
        double x2 = axis.valueToJava2D(end, dataArea, plot.getDomainAxisEdge());
        RectShape band;
        if (plot.getOrientation() == PlotOrientation.VERTICAL) {
            band = new RectShape(Math.min(x1, x2), dataArea.getMinY(), Math.abs(x2 - x1), dataArea
                    .getHeight());
        } else {
            band = new RectShape(dataArea.getMinX(), Math.min(x1, x2), dataArea.getWidth(), Math
                    .abs(x2 - x1));
        }
        PaintType paintType = plot.getDomainTickBandPaintType();

        if (paintType != null) {
            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG, 
                    paintType);
            band.fill(canvas, paint);
        }

    }

    /**
     * Fills a band between two values on the range axis. This can be used to
     * color bands between the grid lines.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param axis
     *            the range axis.
     * @param dataArea
     *            the data area.
     * @param start
     *            the start value.
     * @param end
     *            the end value.
     */
    public void fillRangeGridBand(Canvas canvas, XYPlot plot, ValueAxis axis, RectShape dataArea,
            double start, double end) {

        double y1 = axis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
        double y2 = axis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
        RectShape band;
        if (plot.getOrientation() == PlotOrientation.VERTICAL) {
            band = new RectShape(dataArea.getMinX(), Math.min(y1, y2), dataArea.getWidth(), Math
                    .abs(y2 - y1));
        } else {
            band = new RectShape(Math.min(y1, y2), dataArea.getMinY(), Math.abs(y2 - y1), dataArea
                    .getHeight());
        }

        Paint paint = PaintUtility.createPaint(plot.getRangeTickBandPaintType());

        if (paint != null) {
            band.fill(canvas, paint);
        }

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
     */
    public void drawDomainGridLine(Canvas canvas, XYPlot plot, ValueAxis axis, RectShape dataArea,
            double value) {

        Range range = axis.getRange();
        if (!range.contains(value)) {
            return;
        }

        PlotOrientation orientation = plot.getOrientation();
        double v = axis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());
        LineShape line = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            line = new LineShape(dataArea.getMinX(), v, dataArea.getMaxX(), v);
        } else if (orientation == PlotOrientation.VERTICAL) {
            line = new LineShape(v, dataArea.getMinY(), v, dataArea.getMaxY());
        }

        PaintType paintType = plot.getDomainGridlinePaintType();
        Float stroke = plot.getDomainGridlineStroke();
        PaintType p = paintType != null ? paintType : Plot.DEFAULT_OUTLINE_PAINT_TYPE;
        Float s = stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE;

        PathEffect pathEffect = null;
        if (stroke != 0.0f) {
            pathEffect = plot.getDomainGridlineEffect();
        }

        Paint paint = PaintUtility.createPaint(p, s, pathEffect);
        line.draw(canvas, paint);
    }

    /**
     * Draws a line perpendicular to the domain axis.
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
     *            the paint (<code>null</code> not permitted).
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * @param effect
     *            the effect (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.5
     */
    public void drawDomainLine(Canvas canvas, XYPlot plot, ValueAxis axis, RectShape dataArea,
            double value, PaintType paintType, Float stroke, PathEffect effect) {

        Range range = axis.getRange();
        if (!range.contains(value)) {
            return;
        }

        PlotOrientation orientation = plot.getOrientation();
        //performance tuning
//        LineShape line = null;
        LineShape line = this.mWorkLineShape;
        double v = axis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());
        if (orientation == PlotOrientation.HORIZONTAL) {
            //line = new LineShape(dataArea.getMinX(), v, dataArea.getMaxX(), v);
            line.setLine(dataArea.getMinX(), v, dataArea.getMaxX(), v);
        } else if (orientation == PlotOrientation.VERTICAL) {
            //line = new LineShape(v, dataArea.getMinY(), v, dataArea.getMaxY());
            line.setLine(v, dataArea.getMinY(), v, dataArea.getMaxY());
        }

        Paint paint = PaintUtility
                .createPaint(paintType, stroke, effect);

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
     *            the paintType.
     * @param stroke
     *            the stroke.
     * @param pathEffect
     *            the pathEffect.
     */
    public void drawRangeLine(Canvas canvas, XYPlot plot, ValueAxis axis, RectShape dataArea,
            double value, PaintType paintType, Float stroke, PathEffect pathEffect) {

        Range range = axis.getRange();
        if (!range.contains(value)) {
            return;
        }

        PlotOrientation orientation = plot.getOrientation();
        //performance tuning
        //LineShape line = null;
        LineShape line = this.mWorkLineShape;
        double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
        if (orientation == PlotOrientation.HORIZONTAL) {
            //line = new LineShape(v, dataArea.getMinY(), v, dataArea.getMaxY());
            line.setLine(v, dataArea.getMinY(), v, dataArea.getMaxY());
        } else if (orientation == PlotOrientation.VERTICAL) {
            //line = new LineShape(dataArea.getMinX(), v, dataArea.getMaxX(), v);
            line.setLine(dataArea.getMinX(), v, dataArea.getMaxX(), v);
        }

        Paint paint = PaintUtility.createPaint(paintType, stroke, pathEffect);
        line.draw(canvas, paint);
    }

    /**
     * Draws a vertical line on the chart to represent a 'range marker'.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param domainAxis
     *            the domain axis.
     * @param marker
     *            the marker line.
     * @param dataArea
     *            the axis data area.
     */
    public void drawDomainMarker(Canvas canvas, XYPlot plot, ValueAxis domainAxis, Marker marker,
            RectShape dataArea) {

        if (marker instanceof ValueMarker) {
            ValueMarker vm = (ValueMarker) marker;
            double value = vm.getValue();
            Range range = domainAxis.getRange();
            if (!range.contains(value)) {
                return;
            }

            double v = domainAxis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());

            PlotOrientation orientation = plot.getOrientation();
            LineShape line = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                line = new LineShape(dataArea.getMinX(), v, dataArea.getMaxX(), v);
            } else if (orientation == PlotOrientation.VERTICAL) {
                line = new LineShape(v, dataArea.getMinY(), v, dataArea.getMaxY());
            }

            PaintType paintType = marker.getPaintType();
            Float stroke = marker.getStroke();

            Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, paintType, stroke,
                    marker.getEffect());
            paint.setAlpha(marker.getAlpha());

            line.draw(canvas, paint);

            canvas.drawLine((float) line.getX1(), (float) line.getY1(), (float) line.getX2(),
                    (float) line.getY2(), paint);

            String label = marker.getLabel();
            RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                Font labelFont = marker.getLabelFont();
                PaintType labelPaintType = marker.getLabelPaintType();
                Paint labelPaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG,
                        labelPaintType, labelFont);
                labelPaint.setAlpha(marker.getAlpha());
                RectShape rectShape = new RectShape();
                line.getBounds(rectShape);
                PointF coordinates = calculateDomainMarkerTextAnchorPoint(canvas, orientation,
                        dataArea, rectShape, marker.getLabelOffset(),
                        LengthAdjustmentType.EXPAND, anchor);
                TextUtilities.drawAlignedString(label, canvas, (float) coordinates.x,
                        (float) coordinates.y, marker.getLabelTextAnchor(), labelPaint);
            }
        } else if (marker instanceof IntervalMarker) {
            IntervalMarker im = (IntervalMarker) marker;
            double start = im.getStartValue();
            double end = im.getEndValue();
            Range range = domainAxis.getRange();
            if (!(range.intersects(start, end))) {
                return;
            }

            double start2d = domainAxis.valueToJava2D(start, dataArea, plot.getDomainAxisEdge());
            double end2d = domainAxis.valueToJava2D(end, dataArea, plot.getDomainAxisEdge());
            double low = Math.min(start2d, end2d);
            double high = Math.max(start2d, end2d);

            PlotOrientation orientation = plot.getOrientation();
            RectShape rect = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                // clip top and bottom bounds to data area
                low = Math.max(low, dataArea.getMinY());
                high = Math.min(high, dataArea.getMaxY());
                rect = new RectShape(dataArea.getMinX(), low, dataArea.getWidth(), high - low);
            } else if (orientation == PlotOrientation.VERTICAL) {
                // clip left and right bounds to data area
                low = Math.max(low, dataArea.getMinX());
                high = Math.min(high, dataArea.getMaxX());
                rect = new RectShape(low, dataArea.getMinY(), high - low, dataArea.getHeight());
            }

            Paint paint = PaintUtility
                    .createPaint(Paint.ANTI_ALIAS_FLAG, marker.getPaintType());
            paint.setAlpha(marker.getAlpha());
            rect.fill(canvas, paint);
            // now draw the outlines, if visible...
            if (im.getOutlinePaintType() != null && im.getOutlineStroke() != null) {
                if (orientation == PlotOrientation.VERTICAL) {
                    LineShape line = new LineShape();
                    double y0 = dataArea.getMinY();
                    double y1 = dataArea.getMaxY();
                    Paint outlinePaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, im
                            .getOutlinePaintType(), im.getOutlineStroke(), im.getOutlineEffect());
                    outlinePaint.setAlpha(marker.getAlpha());

                    if (range.contains(start)) {
                        line.setLine(start2d, y0, start2d, y1);
                        line.draw(canvas, outlinePaint);
                    }
                    if (range.contains(end)) {
                        line.setLine(end2d, y0, end2d, y1);
                        line.draw(canvas, outlinePaint);
                    }
                } else { // PlotOrientation.HORIZONTAL
                    LineShape line = new LineShape();
                    double x0 = dataArea.getMinX();
                    double x1 = dataArea.getMaxX();
                    Paint outlinePaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, im
                            .getOutlinePaintType(), im.getOutlineStroke(), im.getOutlineEffect());
                    outlinePaint.setAlpha(marker.getAlpha());
                    if (range.contains(start)) {
                        line.setLine(x0, start2d, x1, start2d);
                        line.draw(canvas, outlinePaint);
                    }
                    if (range.contains(end)) {
                        line.setLine(x0, end2d, x1, end2d);
                        line.draw(canvas, outlinePaint);
                    }
                }
            }

            String label = marker.getLabel();
            RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                Paint labelPaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, marker
                        .getLabelPaintType(), marker.getLabelFont());
                PointF coordinates = calculateDomainMarkerTextAnchorPoint(canvas, orientation,
                        dataArea, rect, marker.getLabelOffset(), marker.getLabelOffsetType(),
                        anchor);
                TextUtilities.drawAlignedString(label, canvas, (float) coordinates.x,
                        (float) coordinates.y, marker.getLabelTextAnchor(), labelPaint);
            }

        }

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
     *            the RectShape surrounding the marker area.
     * @param markerOffset
     *            the marker label offset.
     * @param labelOffsetType
     *            the label offset type.
     * @param anchor
     *            the label anchor.
     * 
     * @return The coordinates for drawing the marker label.
     */
    protected PointF calculateDomainMarkerTextAnchorPoint(Canvas canvas, PlotOrientation orientation,
            RectShape dataArea, RectShape markerArea, RectangleInsets markerOffset,
            LengthAdjustmentType labelOffsetType, RectangleAnchor anchor) {

        RectShape anchorRect = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            anchorRect = markerOffset.createAdjustedRectShape(markerArea,
                    LengthAdjustmentType.CONTRACT, labelOffsetType);
        } else if (orientation == PlotOrientation.VERTICAL) {
            anchorRect = markerOffset.createAdjustedRectShape(markerArea, labelOffsetType,
                    LengthAdjustmentType.CONTRACT);
        }
        return RectangleAnchor.coordinates(anchorRect, anchor);

    }

    /**
     * Draws a horizontal line across the chart to represent a 'range marker'.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param rangeAxis
     *            the range axis.
     * @param marker
     *            the marker line.
     * @param dataArea
     *            the axis data area.
     */
    public void drawRangeMarker(Canvas canvas, XYPlot plot, ValueAxis rangeAxis, Marker marker,
            RectShape dataArea) {

        if (marker instanceof ValueMarker) {
            ValueMarker vm = (ValueMarker) marker;
            double value = vm.getValue();
            Range range = rangeAxis.getRange();
            if (!range.contains(value)) {
                return;
            }

            double v = rangeAxis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
            PlotOrientation orientation = plot.getOrientation();
            LineShape line = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                line = new LineShape(v, dataArea.getMinY(), v, dataArea.getMaxY());
            } else if (orientation == PlotOrientation.VERTICAL) {
                line = new LineShape(dataArea.getMinX(), v, dataArea.getMaxX(), v);
            }

            Paint markerPaint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    marker.getPaintType(),
                    marker.getStroke(),
                    marker.getEffect());
            markerPaint.setAlpha(marker.getAlpha());
            line.draw(canvas, markerPaint);
            String label = marker.getLabel();
            RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                Paint markerLabelPaint = PaintUtility.createPaint(
                        Paint.ANTI_ALIAS_FLAG,
                        marker.getLabelPaintType(),
                        marker.getLabelFont());
                RectShape rectShape = new RectShape();
                line.getBounds(rectShape);
                PointF coordinates = calculateRangeMarkerTextAnchorPoint(canvas, orientation, dataArea,
                        rectShape, marker.getLabelOffset(), LengthAdjustmentType.EXPAND,
                        anchor);
                TextUtilities.drawAlignedString(label, canvas, (float) coordinates.x,
                        (float) coordinates.y, marker.getLabelTextAnchor(), markerLabelPaint);
            }

        } else if (marker instanceof IntervalMarker) {
            IntervalMarker im = (IntervalMarker) marker;
            double start = im.getStartValue();
            double end = im.getEndValue();
            Range range = rangeAxis.getRange();
            if (!(range.intersects(start, end))) {
                return;
            }

            double start2d = rangeAxis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
            double end2d = rangeAxis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
            double low = Math.min(start2d, end2d);
            double high = Math.max(start2d, end2d);

            PlotOrientation orientation = plot.getOrientation();
            RectShape rect = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                // clip left and right bounds to data area
                low = Math.max(low, dataArea.getMinX());
                high = Math.min(high, dataArea.getMaxX());
                rect = new RectShape(low, dataArea.getMinY(), high - low, dataArea.getHeight());
            } else if (orientation == PlotOrientation.VERTICAL) {
                // clip top and bottom bounds to data area
                low = Math.max(low, dataArea.getMinY());
                high = Math.min(high, dataArea.getMaxY());
                rect = new RectShape(dataArea.getMinX(), low, dataArea.getWidth(), high - low);
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
                            im.getOutlineEffect());
                    outlinePaint.setAlpha(marker.getAlpha());
                    if (range.contains(start)) {
                        line.setLine(x0, start2d, x1, start2d);
                        line.draw(canvas, outlinePaint);
                    }
                    if (range.contains(end)) {
                        line.setLine(x0, end2d, x1, end2d);
                        line.draw(canvas, outlinePaint);
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
                    if (range.contains(start)) {
                        line.setLine(start2d, y0, start2d, y1);
                        line.draw(canvas, outlinePaint);
                    }
                    if (range.contains(end)) {
                        line.setLine(end2d, y0, end2d, y1);
                        line.draw(canvas, outlinePaint);
                    }
                }
            }

            String label = marker.getLabel();
            RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                Paint labelPaint = PaintUtility.createPaint(
                        Paint.ANTI_ALIAS_FLAG,
                        marker.getLabelPaintType(),
                        marker.getLabelFont());
                PointF coordinates = calculateRangeMarkerTextAnchorPoint(canvas, orientation, dataArea,
                        rect, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor);
                TextUtilities.drawAlignedString(label, canvas, (float) coordinates.x,
                        (float) coordinates.y, marker.getLabelTextAnchor(), labelPaint);
            }
        }
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
     *            the marker area.
     * @param markerOffset
     *            the marker offset.
     * @param labelOffsetForRange
     *            ??
     * @param anchor
     *            the label anchor.
     * 
     * @return The coordinates for drawing the marker label.
     */
    private PointF calculateRangeMarkerTextAnchorPoint(Canvas canvas, PlotOrientation orientation,
            RectShape dataArea, RectShape markerArea, RectangleInsets markerOffset,
            LengthAdjustmentType labelOffsetForRange, RectangleAnchor anchor) {

        RectShape anchorRect = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            anchorRect = markerOffset.createAdjustedRectShape(markerArea, labelOffsetForRange,
                    LengthAdjustmentType.CONTRACT);
        } else if (orientation == PlotOrientation.VERTICAL) {
            anchorRect = markerOffset.createAdjustedRectShape(markerArea,
                    LengthAdjustmentType.CONTRACT, labelOffsetForRange);
        }
        return RectangleAnchor.coordinates(anchorRect, anchor);

    }

    /**
     * Returns the drawing supplier from the plot.
     * 
     * @return The drawing supplier (possibly <code>null</code>).
     */
    public DrawingSupplier getDrawingSupplier() {
        DrawingSupplier result = null;
        XYPlot p = getPlot();
        if (p != null) {
            result = p.getDrawingSupplier();
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
     * @param x
     *            the x-value (in data space).
     * @param y
     *            the y-value (in data space).
     * @param domainAxisIndex
     *            the index of the domain axis for the point.
     * @param rangeAxisIndex
     *            the index of the range axis for the point.
     * @param transX
     *            the x-value translated to Java2D space.
     * @param transY
     *            the y-value translated to Java2D space.
     * @param orientation
     *            the plot orientation (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.4
     */
    protected void updateCrosshairValues(CrosshairState crosshairState, double x, double y,
            int domainAxisIndex, int rangeAxisIndex, double transX, double transY,
            PlotOrientation orientation) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }

        if (crosshairState != null) {
            // do we need to update the crosshair values?
            if (this.plot.isDomainCrosshairLockedOnData()) {
                if (this.plot.isRangeCrosshairLockedOnData()) {
                    // both axes
                    crosshairState.updateCrosshairPoint(x, y, domainAxisIndex, rangeAxisIndex,
                            transX, transY, orientation);
                } else {
                    // just the domain axis...
                    crosshairState.updateCrosshairX(x, domainAxisIndex);
                }
            } else {
                if (this.plot.isRangeCrosshairLockedOnData()) {
                    // just the range axis...
                    crosshairState.updateCrosshairY(y, rangeAxisIndex);
                }
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
     * @param series
     *            the series index (zero-based).
     * @param item
     *            the item index (zero-based).
     * @param x
     *            the x coordinate (in Java2D space).
     * @param y
     *            the y coordinate (in Java2D space).
     * @param negative
     *            indicates a negative value (which affects the item label
     *            position).
     */
    protected void drawItemLabel(Canvas canvas, PlotOrientation orientation, XYDataset dataset,
            int series, int item, double x, double y, boolean negative) {

        XYItemLabelGenerator generator = getItemLabelGenerator(series, item);
        if (generator != null) {
            String label = generator.generateLabel(dataset, series, item);

            // get the label position..
            ItemLabelPosition position = null;
            if (!negative) {
                position = getPositiveItemLabelPosition(series, item);
            } else {
                position = getNegativeItemLabelPosition(series, item);
            }

            Paint paint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    getItemLabelPaintType(series, item),
                    getItemLabelFont(series, item));
            
            // work out the label anchor point...
            PointF anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), x, y,
                    orientation);
            TextUtilities.drawRotatedString(label, canvas, (float) anchorPoint.x,
                    (float) anchorPoint.y, position.getTextAnchor(), position.getAngle(), position
                            .getRotationAnchor(), paint);
        }

    }

    /**
     * Draws all the annotations for the specified layer.
     * 
     * @param canvas
     *            the graphics device.
     * @param dataArea
     *            the data area.
     * @param domainAxis
     *            the domain axis.
     * @param rangeAxis
     *            the range axis.
     * @param layer
     *            the layer.
     * @param info
     *            the plot rendering info.
     */
    public void drawAnnotations(Canvas canvas, RectShape dataArea, ValueAxis domainAxis,
            ValueAxis rangeAxis, Layer layer, PlotRenderingInfo info) {

        Iterator iterator = null;
        if (layer.equals(Layer.FOREGROUND)) {
            iterator = this.foregroundAnnotations.iterator();
        } else if (layer.equals(Layer.BACKGROUND)) {
            iterator = this.backgroundAnnotations.iterator();
        } else {
            // should not get here
            throw new RuntimeException("Unknown layer.");
        }
        while (iterator.hasNext()) {
            XYAnnotation annotation = (XYAnnotation) iterator.next();
            annotation.draw(canvas, this.plot, dataArea, domainAxis, rangeAxis, 0, info);
        }

    }

    /**
     * Adds an entity to the collection.
     * 
     * @param entities
     *            the entity collection being populated.
     * @param area
     *            the entity area (if <code>null</code> a default will be used).
     * @param dataset
     *            the dataset.
     * @param series
     *            the series.
     * @param item
     *            the item.
     * @param entityX
     *            the entity's center x-coordinate in user space (only used if
     *            <code>area</code> is <code>null</code>).
     * @param entityY
     *            the entity's center y-coordinate in user space (only used if
     *            <code>area</code> is <code>null</code>).
     */
    protected void addEntity(EntityCollection entities, Shape area, XYDataset dataset, int series,
            int item, double entityX, double entityY) {
        if (!getItemCreateEntity(series, item)) {
            return;
        }
        Shape hotspot = area;
        if (hotspot == null) {
            double r = getDefaultEntityRadius();
            double w = r * 2;
            if (getPlot().getOrientation() == PlotOrientation.VERTICAL) {
                hotspot = new OvalShape(entityX - r, entityY - r, w, w);
            } else {
                hotspot = new OvalShape(entityY - r, entityX - r, w, w);
            }
        }

        XYItemEntity entity = new XYItemEntity(hotspot, dataset, series, item, "", "");
        entities.add(entity);
    }

    /**
     * Returns <code>true</code> if the specified point (x, y) falls within or
     * on the boundary of the specified RectShape.
     * 
     * @param rect
     *            the RectShape (<code>null</code> not permitted).
     * @param x
     *            the x-coordinate.
     * @param y
     *            the y-coordinate.
     * 
     * @return A boolean.
     * 
     * @since JFreeChart 1.0.10
     */
    public static boolean isPointInRect(RectShape rect, double x, double y) {

        return (x >= rect.getMinX() && x <= rect.getMaxX() && y >= rect.getMinY() && y <= rect
                .getMaxY());
    }

    /**
     * Returns the legend item URL generator.
     * 
     * @return The URL generator (possibly <code>null</code>).
     * 
     * @see #setLegendItemURLGenerator(CategorySeriesLabelGenerator)
     */
    public XYSeriesLabelGenerator getLegendItemURLGenerator() {
        return this.legendItemURLGenerator;
    }

    /**
     * Tests this renderer for equality with another object.
     * 
     * @param obj
     *            the object (<code>null</code> permitted).
     * 
     * @return <code>true</code> or <code>false</code>.
     */
    // TODO:It comments out when transplanting to the android.
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractXYItemRenderer)) {
            return false;
        }
        AbstractXYItemRenderer that = (AbstractXYItemRenderer) obj;
        /*
         * if (!ObjectUtilities.equal(this.itemLabelGenerator,
         * that.itemLabelGenerator)) { return false; }
         */
        if (!this.itemLabelGeneratorList.equals(that.itemLabelGeneratorList)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.baseItemLabelGenerator, that.baseItemLabelGenerator)) {
            return false;
        }
        /*
         * if (!ObjectUtilities.equal(this.toolTipGenerator,
         * that.toolTipGenerator)) { return false; }
         */
        if (!this.toolTipGeneratorList.equals(that.toolTipGeneratorList)) {
            return false;
        }
        /*
         * if (!ObjectUtilities.equal(this.baseToolTipGenerator,
         * that.baseToolTipGenerator)) { return false; } if
         * (!ObjectUtilities.equal(this.urlGenerator, that.urlGenerator)) {
         * return false; }
         */
        if (!this.foregroundAnnotations.equals(that.foregroundAnnotations)) {
            return false;
        }
        if (!this.backgroundAnnotations.equals(that.backgroundAnnotations)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.legendItemLabelGenerator, that.legendItemLabelGenerator)) {
            return false;
        }
        if (!ObjectUtilities
                .equal(this.legendItemToolTipGenerator, that.legendItemToolTipGenerator)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.legendItemURLGenerator, that.legendItemURLGenerator)) {
            return false;
        }
        return super.equals(obj);
    }

//    /**
//     * Sets the tool tip generator for a series and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param series  the series index (zero based).
//     * @param generator  the generator (<code>null</code> permitted).
//     */
//    public void setSeriesToolTipGenerator(int series,
//                                          XYToolTipGenerator generator) {
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
//    public void setBaseToolTipGenerator(XYToolTipGenerator generator) {
//        this.baseToolTipGenerator = generator;
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the URL generator for HTML image maps and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param urlGenerator  the URL generator (<code>null</code> permitted).
//     */
//    public void setURLGenerator(XYURLGenerator urlGenerator) {
//        this.urlGenerator = urlGenerator;
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the legend item tool tip generator and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @see #getLegendItemToolTipGenerator()
//     */
//    public void setLegendItemToolTipGenerator(
//            XYSeriesLabelGenerator generator) {
//        this.legendItemToolTipGenerator = generator;
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the legend item URL generator and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @see #getLegendItemURLGenerator()
//     */
//    public void setLegendItemURLGenerator(XYSeriesLabelGenerator generator) {
//        this.legendItemURLGenerator = generator;
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the item label generator for ALL series and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @see #getItemLabelGenerator()
//     *
//     * @deprecated As of version 1.0.6, this override setting should not be
//     *     used.  You can use the base setting instead
//     *     ({@link #setBaseItemLabelGenerator(XYItemLabelGenerator)}).
//     */
//    public void setItemLabelGenerator(XYItemLabelGenerator generator) {
//        this.itemLabelGenerator = generator;
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the tool tip generator for ALL series and sends a
//     * {@link RendererChangeEvent} to all registered listeners.
//     *
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @see #getToolTipGenerator()
//     *
//     * @deprecated As of version 1.0.6, this override setting should not be
//     *     used.  You can use the base setting instead
//     *     ({@link #setBaseToolTipGenerator(XYToolTipGenerator)}).
//     */
//    public void setToolTipGenerator(XYToolTipGenerator generator) {
//        this.toolTipGenerator = generator;
//        fireChangeEvent();
//    }
}
