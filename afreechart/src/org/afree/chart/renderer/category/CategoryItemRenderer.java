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
 * -------------------------
 * CategoryItemRenderer.java
 * -------------------------
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
 * (C) Copyright 2001-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Mark Watson (www.markwatson.com);
 *
 * Changes
 * -------
 * 23-Oct-2001 : Version 1 (DG);
 * 16-Jan-2002 : Renamed HorizontalCategoryItemRenderer.java
 *               --> CategoryItemRenderer.java (DG);
 * 05-Feb-2002 : Changed return type of the drawCategoryItem method from void
 *               to Shape, as part of the tooltips implementation (DG)
 *
 *               NOTE (30-May-2002) : this has subsequently been changed back
 *               to void, tooltips are now collected along with entities in
 *               ChartRenderingInfo (DG);
 *
 * 14-Mar-2002 : Added the initialise method, and changed all bar plots to use
 *               this renderer (DG);
 * 23-May-2002 : Added ChartRenderingInfo to the initialise method (DG);
 * 29-May-2002 : Added the getAxisArea(RectShape) method (DG);
 * 06-Jun-2002 : Updated Javadoc comments (DG);
 * 26-Jun-2002 : Added range axis to the initialise method (DG);
 * 24-Sep-2002 : Added getLegendItem() method (DG);
 * 23-Oct-2002 : Added methods to get/setToolTipGenerator (DG);
 * 05-Nov-2002 : Replaced references to CategoryDataset with TableDataset (DG);
 * 06-Nov-2002 : Added the domain axis to the drawCategoryItem method.  Renamed
 *               drawCategoryItem() --> drawItem() (DG);
 * 20-Nov-2002 : Changed signature of drawItem() method to reflect use of
 *               TableDataset (DG);
 * 26-Nov-2002 : Replaced the isStacked() method with the getRangeType()
 *               method (DG);
 * 08-Jan-2003 : Changed getSeriesCount() --> getRowCount() and
 *               getCategoryCount() --> getColumnCount() (DG);
 * 09-Jan-2003 : Changed name of grid-line methods (DG);
 * 21-Jan-2003 : Merged TableDataset with CategoryDataset (DG);
 * 10-Apr-2003 : Changed CategoryDataset to KeyedValues2DDataset in
 *               drawItem() method (DG);
 * 29-Apr-2003 : Eliminated Renderer interface (DG);
 * 02-Sep-2003 : Fix for bug 790407 (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 20-Oct-2003 : Added setOutlinePaint() method (DG);
 * 06-Feb-2004 : Added missing methods, and moved deprecated methods (DG);
 * 19-Feb-2004 : Added extra setXXXLabelsVisible() methods (DG);
 * 29-Apr-2004 : Changed Integer --> int in initialise() method (DG);
 * 18-May-2004 : Added methods for item label paint (DG);
 * 05-Nov-2004 : Added getPassCount() method and 'pass' parameter to drawItem()
 *               method (DG);
 * 07-Jan-2005 : Renamed getRangeExtent() --> findRangeBounds (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 23-Feb-2005 : Now extends LegendItemSource (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator
 *               --> CategoryItemLabelGenerator (DG);
 * 20-May-2005 : Added drawDomainMarker() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Feb-2007 : Updated API docs (DG);
 * 19-Apr-2007 : Deprecated seriesVisible and seriesVisibleInLegend flags (DG);
 * 20-Apr-2007 : Deprecated paint, fillPaint, outlinePaint, stroke,
 *               outlineStroke, shape, itemLabelsVisible, itemLabelFont,
 *               itemLabelPaint, positiveItemLabelPosition,
 *               negativeItemLabelPosition and createEntities override
 *               fields (DG);
 * 26-Jun-2008 : Added new method required for crosshair support - THIS CHANGES
 *               THE API as of version 1.0.11 (DG);
 *
 */

package org.afree.chart.renderer.category;

import org.afree.ui.RectangleEdge;
import org.afree.chart.LegendItem;
import org.afree.chart.LegendItemSource;
import org.afree.chart.axis.CategoryAxis;
import org.afree.chart.axis.ValueAxis;
import org.afree.data.category.CategoryDataset;
import org.afree.data.Range;
import org.afree.chart.event.RendererChangeEvent;
import org.afree.chart.event.RendererChangeListener;
import org.afree.chart.labels.CategoryItemLabelGenerator;
import org.afree.chart.labels.ItemLabelPosition;
import org.afree.chart.plot.CategoryMarker;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.Marker;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import android.graphics.Canvas;

/**
 * A plug-in object that is used by the {@link CategoryPlot} class to display
 * individual data items from a {@link CategoryDataset}.
 * <p>
 * This interface defines the methods that must be provided by all renderers. If
 * you are implementing a custom renderer, you should consider extending the
 * {@link AbstractCategoryItemRenderer} class.
 * <p>
 * Most renderer attributes are defined using a "three layer" approach. When
 * looking up an attribute (for example, the outline paint) the renderer first
 * checks to see if there is a setting (in layer 0) that applies to ALL items
 * that the renderer draws. If there is, that setting is used, but if it is
 * <code>null</code> the renderer looks up the next layer, which contains
 * "per series" settings for the attribute (many attributes are defined on a per
 * series basis, so this is the layer that is most commonly used). If the layer
 * 1 setting is <code>null</code>, the renderer will look up the final layer,
 * which provides a default or "base" setting. Some attributes allow the base
 * setting to be <code>null</code>, while other attributes enforce non-
 * <code>null</code> values.
 */
public interface CategoryItemRenderer extends LegendItemSource {

    /**
     * Returns the number of passes through the dataset required by the
     * renderer. Usually this will be one, but some renderers may use a second
     * or third pass to overlay items on top of things that were drawn in an
     * earlier pass.
     * 
     * @return The pass count.
     */
    public int getPassCount();

    /**
     * Returns the plot that the renderer has been assigned to (where
     * <code>null</code> indicates that the renderer is not currently assigned
     * to a plot).
     * 
     * @return The plot (possibly <code>null</code>).
     * 
     * @see #setPlot(CategoryPlot)
     */
    public CategoryPlot getPlot();

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
    public void setPlot(CategoryPlot plot);

    /**
     * Adds a change listener.
     * 
     * @param listener
     *            the listener.
     * 
     * @see #removeChangeListener(RendererChangeListener)
     */
    public void addChangeListener(RendererChangeListener listener);

    /**
     * Removes a change listener.
     * 
     * @param listener
     *            the listener.
     * 
     * @see #addChangeListener(RendererChangeListener)
     */
    public void removeChangeListener(RendererChangeListener listener);

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
    public Range findRangeBounds(CategoryDataset dataset);

    /**
     * Initialises the renderer. This method will be called before the first
     * item is rendered, giving the renderer an opportunity to initialise any
     * state information it wants to maintain. The renderer can do nothing if it
     * chooses.
     * 
     * @param canvas
     *            the graphics device.
     * @param dataArea
     *            the area inside the axes.
     * @param plot
     *            the plot.
     * @param rendererIndex
     *            the renderer index.
     * @param info
     *            collects chart rendering information for return to caller.
     * 
     * @return A state object (maintains state information relevant to one chart
     *         drawing).
     */
    public CategoryItemRendererState initialise(Canvas canvas,
            RectShape dataArea, CategoryPlot plot, int rendererIndex,
            PlotRenderingInfo info);

    /**
     * Returns a boolean that indicates whether or not the specified item should
     * be drawn (this is typically used to hide an entire series).
     * 
     * @param series
     *            the series index.
     * @param item
     *            the item index.
     * 
     * @return A boolean.
     */
    public boolean getItemVisible(int series, int item);

    /**
     * Returns a boolean that indicates whether or not the specified series
     * should be drawn (this is typically used to hide an entire series).
     * 
     * @param series
     *            the series index.
     * 
     * @return A boolean.
     */
    public boolean isSeriesVisible(int series);

    /**
     * Returns the flag that controls whether a series is visible.
     * 
     * @param series
     *            the series index (zero-based).
     * 
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setSeriesVisible(int, Boolean)
     */
    public Boolean getSeriesVisible(int series);

    /**
     * Sets the flag that controls whether a series is visible and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param visible
     *            the flag (<code>null</code> permitted).
     * 
     * @see #getSeriesVisible(int)
     */
    public void setSeriesVisible(int series, Boolean visible);

    /**
     * Sets the flag that controls whether a series is visible and, if
     * requested, sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param series
     *            the series index.
     * @param visible
     *            the flag (<code>null</code> permitted).
     * @param notify
     *            notify listeners?
     * 
     * @see #getSeriesVisible(int)
     */
    public void setSeriesVisible(int series, Boolean visible, boolean notify);

    /**
     * Returns the base visibility for all series.
     * 
     * @return The base visibility.
     * 
     * @see #setBaseSeriesVisible(boolean)
     */
    public boolean getBaseSeriesVisible();

    /**
     * Sets the base visibility and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #getBaseSeriesVisible()
     */
    public void setBaseSeriesVisible(boolean visible);

    /**
     * Sets the base visibility and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible
     *            the visibility.
     * @param notify
     *            notify listeners?
     * 
     * @see #getBaseSeriesVisible()
     */
    public void setBaseSeriesVisible(boolean visible, boolean notify);

    // SERIES VISIBLE IN LEGEND (not yet respected by all renderers)

    /**
     * Returns <code>true</code> if the series should be shown in the legend,
     * and <code>false</code> otherwise.
     * 
     * @param series
     *            the series index.
     * 
     * @return A boolean.
     */
    public boolean isSeriesVisibleInLegend(int series);

    /**
     * Returns the flag that controls whether a series is visible in the legend.
     * This method returns only the "per series" settings - to incorporate the
     * override and base settings as well, you need to use the
     * {@link #isSeriesVisibleInLegend(int)} method.
     * 
     * @param series
     *            the series index (zero-based).
     * 
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setSeriesVisibleInLegend(int, Boolean)
     */
    public Boolean getSeriesVisibleInLegend(int series);

    /**
     * Sets the flag that controls whether a series is visible in the legend and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param visible
     *            the flag (<code>null</code> permitted).
     * 
     * @see #getSeriesVisibleInLegend(int)
     */
    public void setSeriesVisibleInLegend(int series, Boolean visible);

    /**
     * Sets the flag that controls whether a series is visible in the legend
     * and, if requested, sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param series
     *            the series index.
     * @param visible
     *            the flag (<code>null</code> permitted).
     * @param notify
     *            notify listeners?
     * 
     * @see #getSeriesVisibleInLegend(int)
     */
    public void setSeriesVisibleInLegend(int series, Boolean visible,
            boolean notify);

    /**
     * Returns the base visibility in the legend for all series.
     * 
     * @return The base visibility.
     * 
     * @see #setBaseSeriesVisibleInLegend(boolean)
     */
    public boolean getBaseSeriesVisibleInLegend();

    /**
     * Sets the base visibility in the legend and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #getBaseSeriesVisibleInLegend()
     */
    public void setBaseSeriesVisibleInLegend(boolean visible);

    /**
     * Sets the base visibility in the legend and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible
     *            the visibility.
     * @param notify
     *            notify listeners?
     * 
     * @see #getBaseSeriesVisibleInLegend()
     */
    public void setBaseSeriesVisibleInLegend(boolean visible, boolean notify);

    // // PAINT
    // /////////////////////////////////////////////////////////////////

    /**
     * Returns the paint used to fill data items as they are drawn.
     * 
     * @param row
     *            the row (or series) index (zero-based).
     * @param column
     *            the column (or category) index (zero-based).
     * 
     * @return The paint type (never <code>null</code>).
     */
    public PaintType getItemPaintType(int row, int column);

    /**
     * Returns the paint used to fill an item drawn by the renderer.
     * 
     * @param series
     *            the series index (zero-based).
     * 
     * @return The paint type (possibly <code>null</code>).
     * 
     * @see #setSeriesPaintType(int, PaintType)
     */
    public PaintType getSeriesPaintType(int series);

    /**
     * Sets the paint used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param paintType
     *            the paint (<code>null</code> permitted).
     * 
     * @see #getSeriesPaintType(int)
     */
    public void setSeriesPaintType(int series, PaintType paintType);

    // FIXME: add setSeriesPaint(int, Paint, boolean)?

    /**
     * Returns the base paint.
     * 
     * @return The base paint (never <code>null</code>).
     * 
     * @see #setBasePaintType(PaintType)
     */
    public PaintType getBasePaintType();

    /**
     * Sets the base paint and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getBasePaintType()
     */
    public void setBasePaintType(PaintType paintType);

    // FIXME: add setBasePaint(int, Paint, boolean)?

    // // FILL PAINT /////////////////////////////////////////////////////////

    // /**
    // * Returns the paint used to fill data items as they are drawn.
    // *
    // * @param row the row (or series) index (zero-based).
    // * @param column the column (or category) index (zero-based).
    // *
    // * @return The paint (never <code>null</code>).
    // */
    // public Paint getItemFillPaint(int row, int column);
    //
    // /**
    // * Returns the paint used to fill an item drawn by the renderer.
    // *
    // * @param series the series (zero-based index).
    // *
    // * @return The paint (possibly <code>null</code>).
    // *
    // * @see #setSeriesFillPaint(int, Paint)
    // */
    // public Paint getSeriesFillPaint(int series);
    //
    // /**
    // * Sets the paint used for a series outline and sends a
    // * {@link RendererChangeEvent} to all registered listeners.
    // *
    // * @param series the series index (zero-based).
    // * @param paint the paint (<code>null</code> permitted).
    // *
    // * @see #getSeriesFillPaint(int)
    // */
    // public void setSeriesFillPaint(int series, Paint paint);
    //
    // /**
    // * Returns the base outline paint.
    // *
    // * @return The paint (never <code>null</code>).
    // *
    // * @see #setBaseFillPaint(Paint)
    // */
    // public Paint getBaseFillPaint();
    //
    // /**
    // * Sets the base outline paint and sends a {@link RendererChangeEvent} to
    // * all registered listeners.
    // *
    // * @param paint the paint (<code>null</code> not permitted).
    // *
    // * @see #getBaseFillPaint()
    // */
    // public void setBaseFillPaint(Paint paint);

    // // OUTLINE PAINT
    // /////////////////////////////////////////////////////////

    /**
     * Returns the paint used to outline data items as they are drawn.
     * 
     * @param row
     *            the row (or series) index (zero-based).
     * @param column
     *            the column (or category) index (zero-based).
     * 
     * @return The paint type (never <code>null</code>).
     */
    public PaintType getItemOutlinePaintType(int row, int column);

    /**
     * Returns the paint used to outline an item drawn by the renderer.
     * 
     * @param series
     *            the series (zero-based index).
     * 
     * @return The paint type (possibly <code>null</code>).
     * 
     * @see #setSeriesOutlinePaintType(int, PaintType)
     */
    public PaintType getSeriesOutlinePaintType(int series);

    /**
     * Sets the paint used for a series outline and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param paintType
     *            the paint (<code>null</code> permitted).
     * 
     * @see #getSeriesOutlinePaintType(int)
     */
    public void setSeriesOutlinePaintType(int series, PaintType paintType);

    // FIXME: add setSeriesOutlinePaint(int, Paint, boolean)?

    /**
     * Returns the base outline paint.
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setBaseOutlinePaintType(PaintType)
     */
    public PaintType getBaseOutlinePaintType();

    /**
     * Sets the base outline paint and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getBaseOutlinePaintType()
     */
    public void setBaseOutlinePaintType(PaintType paintType);

    // FIXME: add setBaseOutlinePaint(Paint, boolean)?

    // // STROKE
    // ////////////////////////////////////////////////////////////////

    /**
     * Returns the stroke used to draw data items.
     * 
     * @param row
     *            the row (or series) index (zero-based).
     * @param column
     *            the column (or category) index (zero-based).
     * 
     * @return The stroke (never <code>null</code>).
     */
    public Float getItemStroke(int row, int column);

    /**
     * Returns the stroke used to draw the items in a series.
     * 
     * @param series
     *            the series (zero-based index).
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setSeriesStroke(int, Float)
     */
    public Float getSeriesStroke(int series);

    /**
     * Sets the stroke used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param stroke
     *            the stroke (<code>null</code> permitted).
     * 
     * @see #getSeriesStroke(int)
     */
    //public void setSeriesStroke(int series, float stroke);
    public void setSeriesStroke(int series, Float stroke);

    // FIXME: add setSeriesStroke(int, Stroke, boolean) ?

    /**
     * Returns the base stroke.
     * 
     * @return The base stroke (never <code>null</code>).
     * 
     * @see #setBaseStroke(float stroke)
     */
    public Float getBaseStroke();

    /**
     * Sets the base stroke and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     * 
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * 
     * @see #getBaseStroke()
     */
    public void setBaseStroke(float stroke);

    // FIXME: add setBaseStroke(Stroke, boolean) ?

    // // OUTLINE STROKE
    // ////////////////////////////////////////////////////////

    /**
     * Returns the stroke used to outline data items.
     * <p>
     * The default implementation passes control to the
     * lookupSeriesOutlineStroke method. You can override this method if you
     * require different behaviour.
     * 
     * @param row
     *            the row (or series) index (zero-based).
     * @param column
     *            the column (or category) index (zero-based).
     * 
     * @return The stroke (never <code>null</code>).
     */
    public Float getItemOutlineStroke(int row, int column);

    /**
     * Returns the stroke used to outline the items in a series.
     * 
     * @param series
     *            the series (zero-based index).
     * 
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setSeriesOutlineStroke(int, Float)
     */
    public Float getSeriesOutlineStroke(int series);

    /**
     * Sets the outline stroke used for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param stroke
     *            the stroke (<code>null</code> permitted).
     * 
     * @see #getSeriesOutlineStroke(int)
     */
    public void setSeriesOutlineStroke(int series, Float stroke);

    // FIXME: add setSeriesOutlineStroke(int, Stroke, boolean) ?

    /**
     * Returns the base outline stroke.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setBaseOutlineStroke(Float stroke)
     */
    public Float getBaseOutlineStroke();

    /**
     * Sets the base outline stroke and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     * 
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * 
     * @see #getBaseOutlineStroke()
     */
    public void setBaseOutlineStroke(Float stroke);

    // FIXME: add setBaseOutlineStroke(Stroke, boolean) ?

    // // SHAPE
    // /////////////////////////////////////////////////////////////////

    /**
     * Returns a shape used to represent a data item.
     * 
     * @param row
     *            the row (or series) index (zero-based).
     * @param column
     *            the column (or category) index (zero-based).
     * 
     * @return The shape (never <code>null</code>).
     */
    public Shape getItemShape(int row, int column);

    /**
     * Returns a shape used to represent the items in a series.
     * 
     * @param series
     *            the series (zero-based index).
     * 
     * @return The shape (possibly <code>null</code>).
     * 
     * @see #setSeriesShape(int, Shape)
     */
    public Shape getSeriesShape(int series);

    /**
     * Sets the shape used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param shape
     *            the shape (<code>null</code> permitted).
     * 
     * @see #getSeriesShape(int)
     */
    public void setSeriesShape(int series, Shape shape);

    // FIXME: add setSeriesShape(int, Shape, boolean) ?

    /**
     * Returns the base shape.
     * 
     * @return The shape (never <code>null</code>).
     * 
     * @see #setBaseShape(Shape)
     */
    public Shape getBaseShape();

    /**
     * Sets the base shape and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     * 
     * @param shape
     *            the shape (<code>null</code> not permitted).
     * 
     * @see #getBaseShape()
     */
    public void setBaseShape(Shape shape);

    // FIXME: add setBaseShape(Shape, boolean) ?

    // ITEM LABELS VISIBLE

    /**
     * Returns <code>true</code> if an item label is visible, and
     * <code>false</code> otherwise.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return A boolean.
     */
    public boolean isItemLabelVisible(int row, int column);

    /**
     * Returns <code>true</code> if the item labels for a series are visible,
     * and <code>false</code> otherwise.
     * 
     * @param series
     *            the series index (zero-based).
     * 
     * @return A boolean.
     * 
     * @see #setSeriesItemLabelsVisible(int, Boolean)
     */
    public boolean isSeriesItemLabelsVisible(int series);

    /**
     * Sets a flag that controls the visibility of the item labels for a series.
     * 
     * @param series
     *            the series index (zero-based).
     * @param visible
     *            the flag.
     * 
     * @see #isSeriesItemLabelsVisible(int)
     */
    public void setSeriesItemLabelsVisible(int series, boolean visible);

    /**
     * Sets a flag that controls the visibility of the item labels for a series.
     * 
     * @param series
     *            the series index (zero-based).
     * @param visible
     *            the flag (<code>null</code> permitted).
     * 
     * @see #isSeriesItemLabelsVisible(int)
     */
    public void setSeriesItemLabelsVisible(int series, Boolean visible);

    /**
     * Sets the visibility of item labels for a series and, if requested, sends
     * a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param visible
     *            the visible flag.
     * @param notify
     *            a flag that controls whether or not listeners are notified.
     * 
     * @see #isSeriesItemLabelsVisible(int)
     */
    public void setSeriesItemLabelsVisible(int series, Boolean visible,
            boolean notify);

    /**
     * Returns the base setting for item label visibility. A <code>null</code>
     * result should be interpreted as equivalent to <code>Boolean.FALSE</code>
     * (this is an error in the API design, the return value should have been a
     * boolean primitive).
     * 
     * @return A flag (possibly <code>null</code>).
     * 
     * @see #setBaseItemLabelsVisible(Boolean)
     */
    public Boolean getBaseItemLabelsVisible();

    /**
     * Sets the base flag that controls whether or not item labels are visible
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #getBaseItemLabelsVisible()
     */
    public void setBaseItemLabelsVisible(boolean visible);

    /**
     * Sets the base setting for item label visibility and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible
     *            the flag (<code>null</code> permitted).
     * 
     * @see #getBaseItemLabelsVisible()
     */
    public void setBaseItemLabelsVisible(Boolean visible);

    /**
     * Sets the base visibility for item labels and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible
     *            the visibility flag.
     * @param notify
     *            a flag that controls whether or not listeners are notified.
     * 
     * @see #getBaseItemLabelsVisible()
     */
    public void setBaseItemLabelsVisible(Boolean visible, boolean notify);

    // ITEM LABEL GENERATOR

    /**
     * Returns the item label generator for the specified data item.
     * 
     * @param series
     *            the series index (zero-based).
     * @param item
     *            the item index (zero-based).
     * 
     * @return The generator (possibly <code>null</code>).
     */
    public CategoryItemLabelGenerator getItemLabelGenerator(int series, int item);

    /**
     * Returns the item label generator for a series.
     * 
     * @param series
     *            the series index (zero-based).
     * 
     * @return The label generator (possibly <code>null</code>).
     * 
     * @see #setSeriesItemLabelGenerator(int, CategoryItemLabelGenerator)
     */
    public CategoryItemLabelGenerator getSeriesItemLabelGenerator(int series);

    /**
     * Sets the item label generator for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param generator
     *            the generator.
     * 
     * @see #getSeriesItemLabelGenerator(int)
     */
    public void setSeriesItemLabelGenerator(int series,
            CategoryItemLabelGenerator generator);

    // FIXME: add setSeriesItemLabelGenerator(int, CategoryItemLabelGenerator,
    // boolean)

    /**
     * Returns the base item label generator.
     * 
     * @return The generator (possibly <code>null</code>).
     * 
     * @see #setBaseItemLabelGenerator(CategoryItemLabelGenerator)
     */
    public CategoryItemLabelGenerator getBaseItemLabelGenerator();

    /**
     * Sets the base item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param generator
     *            the generator (<code>null</code> permitted).
     * 
     * @see #getBaseItemLabelGenerator()
     */
    public void setBaseItemLabelGenerator(CategoryItemLabelGenerator generator);

    // FIXME: add setBaseItemLabelGenerator(CategoryItemLabelGenerator,
    // boolean) ?

    // TOOL TIP GENERATOR

    // // ITEM LABEL FONT //////////////////////////////////////////////////////

    /**
     * Returns the font for an item label.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return The font (never <code>null</code>).
     */
    public Font getItemLabelFont(int row, int column);

    /**
     * Returns the font for all the item labels in a series.
     * 
     * @param series
     *            the series index (zero-based).
     * 
     * @return The font (possibly <code>null</code>).
     * 
     * @see #setSeriesItemLabelFont(int, Font)
     */
    public Font getSeriesItemLabelFont(int series);

    /**
     * Sets the item label font for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param font
     *            the font (<code>null</code> permitted).
     * 
     * @see #getSeriesItemLabelFont(int)
     */
    public void setSeriesItemLabelFont(int series, Font font);

    // FIXME: add setSeriesItemLabelFont(int, Font, boolean) ?

    /**
     * Returns the base item label font (this is used when no other font setting
     * is available).
     * 
     * @return The font (<code>never</code> null).
     * 
     * @see #setBaseItemLabelFont(Font)
     */
    public Font getBaseItemLabelFont();

    /**
     * Sets the base item label font and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     * 
     * @param font
     *            the font (<code>null</code> not permitted).
     * 
     * @see #getBaseItemLabelFont()
     */
    public void setBaseItemLabelFont(Font font);

    // FIXME: add setBaseItemLabelFont(Font, boolean) ?

    // // ITEM LABEL PAINT /////////////////////////////////////////////////////

    /**
     * Returns the paint used to draw an item label.
     * 
     * @param row
     *            the row index (zero based).
     * @param column
     *            the column index (zero based).
     * 
     * @return The paint type (never <code>null</code>).
     */
    public PaintType getItemLabelPaintType(int row, int column);

    /**
     * Returns the paint used to draw the item labels for a series.
     * 
     * @param series
     *            the series index (zero based).
     * 
     * @return The paint type (possibly <code>null<code>).
     * 
     * @see #setSeriesItemLabelPaintType(int, PaintType)
     */
    public PaintType getSeriesItemLabelPaintType(int series);

    /**
     * Sets the item label paint for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series (zero based index).
     * @param paintType
     *            the paint (<code>null</code> permitted).
     * 
     * @see #getSeriesItemLabelPaintType(int)
     */
    public void setSeriesItemLabelPaintType(int series, PaintType paintType);

    // FIXME: add setSeriesItemLabelPaint(int, Paint, boolean) ?

    /**
     * Returns the base item label paint.
     * 
     * @return The paint type (never <code>null<code>).
     * 
     * @see #setBaseItemLabelPaintType(PaintType)
     */
    public PaintType getBaseItemLabelPaintType();

    /**
     * Sets the base item label paint and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getBaseItemLabelPaintType()
     */
    public void setBaseItemLabelPaintType(PaintType paintType);

    // FIXME: add setBaseItemLabelPaint(Paint, boolean) ?

    // POSITIVE ITEM LABEL POSITION...

    /**
     * Returns the item label position for positive values.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return The item label position (never <code>null</code>).
     */
    public ItemLabelPosition getPositiveItemLabelPosition(int row, int column);

    /**
     * Returns the item label position for all positive values in a series.
     * 
     * @param series
     *            the series index (zero-based).
     * 
     * @return The item label position.
     * 
     * @see #setSeriesPositiveItemLabelPosition(int, ItemLabelPosition)
     */
    public ItemLabelPosition getSeriesPositiveItemLabelPosition(int series);

    /**
     * Sets the item label position for all positive values in a series and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param position
     *            the position (<code>null</code> permitted).
     * 
     * @see #getSeriesPositiveItemLabelPosition(int)
     */
    public void setSeriesPositiveItemLabelPosition(int series,
            ItemLabelPosition position);

    /**
     * Sets the item label position for all positive values in a series and (if
     * requested) sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param position
     *            the position (<code>null</code> permitted).
     * @param notify
     *            notify registered listeners?
     * 
     * @see #getSeriesPositiveItemLabelPosition(int)
     */
    public void setSeriesPositiveItemLabelPosition(int series,
            ItemLabelPosition position, boolean notify);

    /**
     * Returns the base positive item label position.
     * 
     * @return The position.
     * 
     * @see #setBasePositiveItemLabelPosition(ItemLabelPosition)
     */
    public ItemLabelPosition getBasePositiveItemLabelPosition();

    /**
     * Sets the base positive item label position.
     * 
     * @param position
     *            the position.
     * 
     * @see #getBasePositiveItemLabelPosition()
     */
    public void setBasePositiveItemLabelPosition(ItemLabelPosition position);

    /**
     * Sets the base positive item label position and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param position
     *            the position.
     * @param notify
     *            notify registered listeners?
     * 
     * @see #getBasePositiveItemLabelPosition()
     */
    public void setBasePositiveItemLabelPosition(ItemLabelPosition position,
            boolean notify);

    // NEGATIVE ITEM LABEL POSITION...

    /**
     * Returns the item label position for negative values. This method can be
     * overridden to provide customisation of the item label position for
     * individual data items.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column (zero-based).
     * 
     * @return The item label position.
     */
    public ItemLabelPosition getNegativeItemLabelPosition(int row, int column);

    /**
     * Returns the item label position for all negative values in a series.
     * 
     * @param series
     *            the series index (zero-based).
     * 
     * @return The item label position.
     * 
     * @see #setSeriesNegativeItemLabelPosition(int, ItemLabelPosition)
     */
    public ItemLabelPosition getSeriesNegativeItemLabelPosition(int series);

    /**
     * Sets the item label position for negative values in a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param position
     *            the position (<code>null</code> permitted).
     * 
     * @see #getSeriesNegativeItemLabelPosition(int)
     */
    public void setSeriesNegativeItemLabelPosition(int series,
            ItemLabelPosition position);

    /**
     * Sets the item label position for negative values in a series and (if
     * requested) sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param series
     *            the series index (zero-based).
     * @param position
     *            the position (<code>null</code> permitted).
     * @param notify
     *            notify registered listeners?
     * 
     * @see #getSeriesNegativeItemLabelPosition(int)
     */
    public void setSeriesNegativeItemLabelPosition(int series,
            ItemLabelPosition position, boolean notify);

    /**
     * Returns the base item label position for negative values.
     * 
     * @return The position.
     * 
     * @see #setBaseNegativeItemLabelPosition(ItemLabelPosition)
     */
    public ItemLabelPosition getBaseNegativeItemLabelPosition();

    /**
     * Sets the base item label position for negative values and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param position
     *            the position.
     * 
     * @see #getBaseNegativeItemLabelPosition()
     */
    public void setBaseNegativeItemLabelPosition(ItemLabelPosition position);

    /**
     * Sets the base negative item label position and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param position
     *            the position.
     * @param notify
     *            notify registered listeners?
     * 
     * @see #getBaseNegativeItemLabelPosition()
     */
    public void setBaseNegativeItemLabelPosition(ItemLabelPosition position,
            boolean notify);

    // CREATE ENTITIES
    // FIXME: these methods should be defined

    // public boolean getItemCreateEntity(int series, int item);
    //
    // public Boolean getSeriesCreateEntities(int series);
    //
    // public void setSeriesCreateEntities(int series, Boolean create);
    //
    // public void setSeriesCreateEntities(int series, Boolean create,
    // boolean notify);
    //
    // public boolean getBaseCreateEntities();
    //
    // public void setBaseCreateEntities(boolean create);
    //
    // public void setBaseCreateEntities(boolean create, boolean notify);

    // ITEM URL GENERATOR

    /**
     * Returns a legend item for a series. This method can return
     * <code>null</code>, in which case the series will have no entry in the
     * legend.
     * 
     * @param datasetIndex
     *            the dataset index (zero-based).
     * @param series
     *            the series (zero-based index).
     * 
     * @return The legend item (possibly <code>null</code>).
     */
    public LegendItem getLegendItem(int datasetIndex, int series);

    /**
     * Draws a background for the data area.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param dataArea
     *            the data area.
     */
    public void drawBackground(Canvas canvas, CategoryPlot plot,
            RectShape dataArea);

    /**
     * Draws an outline for the data area.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param dataArea
     *            the data area.
     */
    public void drawOutline(Canvas canvas, CategoryPlot plot, RectShape dataArea);

    /**
     * Draws a single data item.
     * 
     * @param canvas
     *            the graphics device.
     * @param state
     *            state information for one chart.
     * @param dataArea
     *            the data plot area.
     * @param plot
     *            the plot.
     * @param domainAxis
     *            the domain axis.
     * @param rangeAxis
     *            the range axis.
     * @param dataset
     *            the data.
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * @param pass
     *            the pass index.
     */
    public void drawItem(Canvas canvas, CategoryItemRendererState state,
            RectShape dataArea, CategoryPlot plot, CategoryAxis domainAxis,
            ValueAxis rangeAxis, CategoryDataset dataset, int row, int column,
            int pass);

    /**
     * Draws a grid line against the domain axis.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param dataArea
     *            the area for plotting data (not yet adjusted for any 3D
     *            effect).
     * @param value
     *            the value.
     * 
     * @see #drawRangeGridline(Canvas, CategoryPlot, ValueAxis, RectShape,
     *      double)
     */
    public void drawDomainGridline(Canvas canvas, CategoryPlot plot,
            RectShape dataArea, double value);

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
     *            the value.
     * 
     * @see #drawDomainGridline(Canvas, CategoryPlot, RectShape, double)
     */
    public void drawRangeGridline(Canvas canvas, CategoryPlot plot, ValueAxis axis,
            RectShape dataArea, double value);

    /**
     * Draws a line (or some other marker) to indicate a particular category on
     * the domain axis.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param axis
     *            the category axis.
     * @param marker
     *            the marker.
     * @param dataArea
     *            the area for plotting data (not including 3D effect).
     * 
     * @see #drawRangeMarker(Canvas, CategoryPlot, ValueAxis, Marker,
     *      RectShape)
     */
    public void drawDomainMarker(Canvas canvas, CategoryPlot plot,
            CategoryAxis axis, CategoryMarker marker, RectShape dataArea);

    /**
     * Draws a line (or some other marker) to indicate a particular value on the
     * range axis.
     * 
     * @param canvas
     *            the graphics device.
     * @param plot
     *            the plot.
     * @param axis
     *            the value axis.
     * @param marker
     *            the marker.
     * @param dataArea
     *            the area for plotting data (not including 3D effect).
     * 
     * @see #drawDomainMarker(Canvas, CategoryPlot, CategoryAxis,
     *      CategoryMarker, RectShape)
     */
    public void drawRangeMarker(Canvas canvas, CategoryPlot plot, ValueAxis axis,
            Marker marker, RectShape dataArea);

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
            RectangleEdge edge);

}
