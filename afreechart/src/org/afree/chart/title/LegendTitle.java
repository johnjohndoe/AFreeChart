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
 * ----------------
 * LegendTitle.java
 * ----------------
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
 * Contributor(s):   Pierre-Marie Le Biot;
 *
 * Changes
 * -------
 * 25-Nov-2004 : First working version (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 08-Feb-2005 : Updated for changes in RectangleConstraint class (DG);
 * 11-Feb-2005 : Implemented PublicCloneable (DG);
 * 23-Feb-2005 : Replaced chart reference with LegendItemSource (DG);
 * 16-Mar-2005 : Added itemFont attribute (DG);
 * 17-Mar-2005 : Fixed missing fillShape setting (DG);
 * 20-Apr-2005 : Added new draw() method (DG);
 * 03-May-2005 : Modified equals() method to ignore sources (DG);
 * 13-May-2005 : Added settings for legend item label and graphic padding (DG);
 * 09-Jun-2005 : Fixed serialization bug (DG);
 * 01-Sep-2005 : Added itemPaint attribute (PMLB);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Use new LegendItemBlockContainer to restore support for
 *               LegendItemEntities (DG);
 * 06-Oct-2006 : Add tooltip and URL text to legend item (DG);
 * 13-Dec-2006 : Added support for GradientPaint in legend items (DG);
 * 16-Mar-2007 : Updated border drawing for changes in AbstractBlock (DG);
 * 18-May-2007 : Pass seriesKey and dataset to legend item block (DG);
 * 15-Aug-2008 : Added getWrapper() method (DG);
 * 19-Mar-2009 : Added entity support - see patch 2603321 by Peter Kolb (DG);
 *
 */

package org.afree.chart.title;

import java.io.Serializable;

import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.ui.Size2D;
import org.afree.chart.LegendItem;
import org.afree.chart.LegendItemCollection;
import org.afree.chart.LegendItemSource;
import org.afree.chart.block.Arrangement;
import org.afree.chart.block.Block;
import org.afree.chart.block.BlockContainer;
import org.afree.chart.block.BlockFrame;
import org.afree.chart.block.BlockResult;
import org.afree.chart.block.BorderArrangement;
import org.afree.chart.block.CenterArrangement;
import org.afree.chart.block.ColumnArrangement;
import org.afree.chart.block.EntityBlockParams;
import org.afree.chart.block.FlowArrangement;
import org.afree.chart.block.LabelBlock;
import org.afree.chart.block.RectangleConstraint;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.entity.StandardEntityCollection;
import org.afree.chart.entity.TitleEntity;
import org.afree.chart.event.TitleChangeEvent;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;



/**
 * A chart title that displays a legend for the data in the chart.
 * <P>
 * The title can be populated with legend items manually, or you can assign a
 * reference to the plot, in which case the legend items will be automatically
 * created to match the dataset(s).
 */
public class LegendTitle extends Title implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 2644010518533854633L;

    /** The default item font. */
    public static final Font DEFAULT_ITEM_FONT = new Font("SansSerif",
            Typeface.NORMAL, 12);

    /** The default item paint. */
    public static final PaintType DEFAULT_ITEM_PAINT = new SolidColor(Color.BLACK);

    /** The sources for legend items. */
    private LegendItemSource[] sources;

    /** The background paint (possibly <code>null</code>). */
    private transient PaintType backgroundPaintType;

    /** The edge for the legend item graphic relative to the text. */
    private RectangleEdge legendItemGraphicEdge;

    /** The anchor point for the legend item graphic. */
    private RectangleAnchor legendItemGraphicAnchor;

    /** The legend item graphic location. */
    private RectangleAnchor legendItemGraphicLocation;

    /** The padding for the legend item graphic. */
    private RectangleInsets legendItemGraphicPadding;

    /** The item font. */
    private Font itemFont;

    /** The item paint. */
    private transient PaintType itemPaintType;

    /** The padding for the item labels. */
    private RectangleInsets itemLabelPadding;

    /**
     * A container that holds and displays the legend items.
     */
    private BlockContainer items;

    /**
     * The layout for the legend when it is positioned at the top or bottom of
     * the chart.
     */
    private Arrangement hLayout;

    /**
     * The layout for the legend when it is positioned at the left or right of
     * the chart.
     */
    private Arrangement vLayout;

    /**
     * An optional container for wrapping the legend items (allows for adding a
     * title or other text to the legend).
     */
    private BlockContainer wrapper;

    /**
     * Constructs a new (empty) legend for the specified source.
     * 
     * @param source
     *            the source.
     */
    public LegendTitle(LegendItemSource source) {
        this(source, new FlowArrangement(), new ColumnArrangement());
    }

    /**
     * Creates a new legend title with the specified arrangement.
     * 
     * @param source
     *            the source.
     * @param hLayout
     *            the horizontal item arrangement (<code>null</code> not
     *            permitted).
     * @param vLayout
     *            the vertical item arrangement (<code>null</code> not
     *            permitted).
     */
    public LegendTitle(LegendItemSource source, Arrangement hLayout,
            Arrangement vLayout) {
        this.sources = new LegendItemSource[] { source };
        this.items = new BlockContainer(hLayout);
        this.hLayout = hLayout;
        this.vLayout = vLayout;
        this.backgroundPaintType = null;
        this.legendItemGraphicEdge = RectangleEdge.LEFT;
        this.legendItemGraphicAnchor = RectangleAnchor.CENTER;
        this.legendItemGraphicLocation = RectangleAnchor.CENTER;
        this.legendItemGraphicPadding = new RectangleInsets(2.0, 2.0, 2.0, 2.0);
        this.itemFont = DEFAULT_ITEM_FONT;
        this.itemPaintType = DEFAULT_ITEM_PAINT;
        this.itemLabelPadding = new RectangleInsets(2.0, 2.0, 2.0, 2.0);
    }

    /**
     * Returns the legend item sources.
     * 
     * @return The sources.
     */
    public LegendItemSource[] getSources() {
        return this.sources;
    }

    /**
     * Sets the legend item sources and sends a {@link TitleChangeEvent} to all
     * registered listeners.
     * 
     * @param sources
     *            the sources (<code>null</code> not permitted).
     */
    public void setSources(LegendItemSource[] sources) {
        if (sources == null) {
            throw new IllegalArgumentException("Null 'sources' argument.");
        }
        this.sources = sources;
        notifyListeners(new TitleChangeEvent(this));
    }

    /**
     * Returns the background paint.
     * 
     * @return The background paint (possibly <code>null</code>).
     */
    public PaintType getBackgroundPaintType() {
        return this.backgroundPaintType;
    }

    /**
     * Sets the background paint for the legend and sends a
     * {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> permitted).
     */
    public void setBackgroundPaintType(PaintType paintType) {
        this.backgroundPaintType = paintType;
        notifyListeners(new TitleChangeEvent(this));
    }

    /**
     * Returns the location of the shape within each legend item.
     * 
     * @return The location (never <code>null</code>).
     */
    public RectangleEdge getLegendItemGraphicEdge() {
        return this.legendItemGraphicEdge;
    }

    /**
     * Sets the location of the shape within each legend item.
     * 
     * @param edge
     *            the edge (<code>null</code> not permitted).
     */
    public void setLegendItemGraphicEdge(RectangleEdge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("Null 'edge' argument.");
        }
        this.legendItemGraphicEdge = edge;
        notifyListeners(new TitleChangeEvent(this));
    }

    /**
     * Returns the legend item graphic anchor.
     * 
     * @return The graphic anchor (never <code>null</code>).
     */
    public RectangleAnchor getLegendItemGraphicAnchor() {
        return this.legendItemGraphicAnchor;
    }

    /**
     * Sets the anchor point used for the graphic in each legend item.
     * 
     * @param anchor
     *            the anchor point (<code>null</code> not permitted).
     */
    public void setLegendItemGraphicAnchor(RectangleAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' point.");
        }
        this.legendItemGraphicAnchor = anchor;
    }

    /**
     * Returns the legend item graphic location.
     * 
     * @return The location (never <code>null</code>).
     */
    public RectangleAnchor getLegendItemGraphicLocation() {
        return this.legendItemGraphicLocation;
    }

    /**
     * Sets the legend item graphic location.
     * 
     * @param anchor
     *            the anchor (<code>null</code> not permitted).
     */
    public void setLegendItemGraphicLocation(RectangleAnchor anchor) {
        this.legendItemGraphicLocation = anchor;
    }

    /**
     * Returns the padding that will be applied to each item graphic.
     * 
     * @return The padding (never <code>null</code>).
     */
    public RectangleInsets getLegendItemGraphicPadding() {
        return this.legendItemGraphicPadding;
    }

    /**
     * Sets the padding that will be applied to each item graphic in the legend
     * and sends a {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param padding
     *            the padding (<code>null</code> not permitted).
     */
    public void setLegendItemGraphicPadding(RectangleInsets padding) {
        if (padding == null) {
            throw new IllegalArgumentException("Null 'padding' argument.");
        }
        this.legendItemGraphicPadding = padding;
        notifyListeners(new TitleChangeEvent(this));
}

    /**
     * Returns the item font.
     * 
     * @return The font (never <code>null</code>).
     */
    public Font getItemFont() {
        return this.itemFont;
    }

    /**
     * Sets the item font and sends a {@link TitleChangeEvent} to all registered
     * listeners.
     * 
     * @param font
     *            the font (<code>null</code> not permitted).
     */
    public void setItemFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.itemFont = font;
        notifyListeners(new TitleChangeEvent(this));
    }

    /**
     * Returns the item paint.
     * 
     * @return The paint type (never <code>null</code>).
     */
    public PaintType getItemPaintType() {
        return this.itemPaintType;
    }

    /**
     * Sets the item paint.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     */
    public void setItemPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.itemPaintType = paintType;
        notifyListeners(new TitleChangeEvent(this));
    }

    /**
     * Returns the padding used for the items labels.
     * 
     * @return The padding (never <code>null</code>).
     */
    public RectangleInsets getItemLabelPadding() {
        return this.itemLabelPadding;
    }

    /**
     * Sets the padding used for the item labels in the legend.
     * 
     * @param padding
     *            the padding (<code>null</code> not permitted).
     */
    public void setItemLabelPadding(RectangleInsets padding) {
        if (padding == null) {
            throw new IllegalArgumentException("Null 'padding' argument.");
        }
        this.itemLabelPadding = padding;
        notifyListeners(new TitleChangeEvent(this));
    }

    /**
     * Fetches the latest legend items.
     */
    protected void fetchLegendItems() {
        this.items.clear();
        RectangleEdge p = getPosition();
        if (RectangleEdge.isTopOrBottom(p)) {
            this.items.setArrangement(this.hLayout);
        } else {
            this.items.setArrangement(this.vLayout);
        }
        for (int s = 0; s < this.sources.length; s++) {
            LegendItemCollection legendItems = this.sources[s].getLegendItems();
            if (legendItems != null) {
                for (int i = 0; i < legendItems.getItemCount(); i++) {
                    LegendItem item = legendItems.get(i);
                    Block block = createLegendItemBlock(item);
                    this.items.add(block);
                }
            }
        }
    }

    /**
     * Creates a legend item block.
     * 
     * @param item
     *            the legend item.
     * 
     * @return The block.
     */
    protected Block createLegendItemBlock(LegendItem item) {
        BlockContainer result = null;
        LegendGraphic lg = new LegendGraphic(item.getShape(), item
                .getFillPaintType());
        // lg.setFillPaintTransformer(item.getFillPaintTransformer());
        lg.setShapeFilled(item.isShapeFilled());
        lg.setLine(item.getLine());
        lg.setLineStroke(item.getLineStroke());
        lg.setLinePaintType(item.getLinePaintType());
        lg.setLineVisible(item.isLineVisible());
        lg.setShapeVisible(item.isShapeVisible());
        lg.setShapeOutlineVisible(item.isShapeOutlineVisible());
        lg.setOutlinePaintType(item.getOutlinePaintType());
        lg.setOutlineStroke(item.getOutlineStroke());
        lg.setPadding(this.legendItemGraphicPadding);

        LegendItemBlockContainer legendItem = new LegendItemBlockContainer(
                new BorderArrangement(), item.getDataset(), item.getSeriesKey());
        lg.setShapeAnchor(getLegendItemGraphicAnchor());
        lg.setShapeLocation(getLegendItemGraphicLocation());
        legendItem.add(lg, this.legendItemGraphicEdge);
        Font textFont = item.getLabelFont();
        if (textFont == null) {
            textFont = this.itemFont;
        }
        PaintType textPaintType = item.getLabelPaintType();
        if (textPaintType == null) {
            textPaintType = this.itemPaintType;
        }
        LabelBlock labelBlock = new LabelBlock(item.getLabel(), textFont,
                textPaintType);
        labelBlock.setPadding(this.itemLabelPadding);
        legendItem.add(labelBlock);
        legendItem.setToolTipText(item.getToolTipText());
        legendItem.setURLText(item.getURLText());

        result = new BlockContainer(new CenterArrangement());
        result.add(legendItem);

        return result;
    }

    /**
     * Returns the container that holds the legend items.
     * 
     * @return The container for the legend items.
     */
    public BlockContainer getItemContainer() {
        return this.items;
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
        Size2D result = new Size2D();
        fetchLegendItems();
        if (this.items.isEmpty()) {
            return result;
        }
        BlockContainer container = this.wrapper;
        if (container == null) {
            container = this.items;
        }
        RectangleConstraint c = toContentConstraint(constraint);
        Size2D size = container.arrange(canvas, c);
        result.height = calculateTotalHeight(size.height);
        result.width = calculateTotalWidth(size.width);
        return result;
    }

    /**
     * Draws the title on a graphics device (such as the screen or a
     * printer).
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the available area for the title.
     */
    public void draw(Canvas canvas, RectShape area) {
        draw(canvas, area, null);
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
     * @return An {@link org.afree.chart.block.EntityBlockResult} or
     *         <code>null</code>.
     */
    public Object draw(Canvas canvas, RectShape area, Object params) {
        RectShape target = (RectShape) area.clone();
        RectShape hotspot = (RectShape) area.clone();
        StandardEntityCollection sec = null;
        if (params instanceof EntityBlockParams
                && ((EntityBlockParams) params).getGenerateEntities()) {
            sec = new StandardEntityCollection();
            sec.add(new TitleEntity(hotspot, this));
        }
        target = trimMargin(target);
        if (this.backgroundPaintType != null) {
            Paint backgroundPaint = PaintUtility.createPaint(
                    Paint.ANTI_ALIAS_FLAG,
                    backgroundPaintType);
           target.fill(canvas, backgroundPaint);
        }
        BlockFrame border = getFrame();
        border.draw(canvas, target);
        border.getInsets().trim(target);
        BlockContainer container = this.wrapper;
        if (container == null) {
            container = this.items;
        }
        target = trimPadding(target);
        Object val = container.draw(canvas, target, params);
        if (val instanceof BlockResult) {
            EntityCollection ec = ((BlockResult) val).getEntityCollection();
            if (ec != null && sec != null) {
                sec.addAll(ec);
                ((BlockResult) val).setEntityCollection(sec);
            }
        }
        return val;
    }

    /**
     * Returns the wrapper container, if any.
     * 
     * @return The wrapper container (possibly <code>null</code>).
     * 
     * @since JFreeChart 1.0.11
     */
    public BlockContainer getWrapper() {
        return this.wrapper;
    }

    /**
     * Sets the wrapper container for the legend.
     * 
     * @param wrapper
     *            the wrapper container.
     */
    public void setWrapper(BlockContainer wrapper) {
        this.wrapper = wrapper;
    }

}
