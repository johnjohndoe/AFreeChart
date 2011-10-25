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
 * -----------------
 * CategoryAxis.java
 * -----------------
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
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   Pady Srinivasan (patch 1217634);
 *                   Peter Kolb (patches 2497611 and 2603321);
 *
 * Changes
 * -------
 * 21-Aug-2001 : Added standard header. Fixed DOS encoding problem (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 04-Dec-2001 : Changed constructors to protected, and tidied up default
 *               values (DG);
 * 19-Apr-2002 : Updated import statements (DG);
 * 05-Sep-2002 : Updated constructor for changes in Axis class (DG);
 * 06-Nov-2002 : Moved margins from the CategoryPlot class (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 22-Jan-2002 : Removed monolithic constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 09-May-2003 : Merged HorizontalCategoryAxis and VerticalCategoryAxis into
 *               this class (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 05-Nov-2003 : Fixed serialization bug (DG);
 * 26-Nov-2003 : Added category label offset (DG);
 * 06-Jan-2004 : Moved axis line attributes to Axis class, rationalised
 *               category label position attributes (DG);
 * 07-Jan-2004 : Added new implementation for linewrapping of category
 *               labels (DG);
 * 17-Feb-2004 : Moved deprecated code to bottom of source file (DG);
 * 10-Mar-2004 : Changed Dimension --> Dimension2D in text classes (DG);
 * 16-Mar-2004 : Added support for tooltips on category labels (DG);
 * 01-Apr-2004 : Changed java.awt.geom.Dimension2D to org.jfree.ui.Size2D
 *               because of JDK bug 4976448 which persists on JDK 1.3.1 (DG);
 * 03-Sep-2004 : Added 'maxCategoryLabelLines' attribute (DG);
 * 04-Oct-2004 : Renamed ShapeUtils --> ShapeUtilities (DG);
 * 11-Jan-2005 : Removed deprecated methods in preparation for 1.0.0
 *               release (DG);
 * 21-Jan-2005 : Modified return type for RectangleAnchor.coordinates()
 *               method (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 08-Jun-2005 : Fixed bug in axis layout (DG);
 * 22-Nov-2005 : Added a method to access the tool tip text for a category
 *               label (DG);
 * 23-Nov-2005 : Added per-category font and paint options - see patch
 *               1217634 (DG);
 * ------------- JFreeChart 1.0.x ---------------------------------------------
 * 11-Jan-2006 : Fixed null pointer exception in drawCategoryLabels - see bug
 *               1403043 (DG);
 * 18-Aug-2006 : Fix for bug drawing category labels, thanks to Adriaan
 *               Joubert (1277726) (DG);
 * 02-Oct-2006 : Updated category label entity (DG);
 * 30-Oct-2006 : Updated refreshTicks() method to account for possibility of
 *               multiple domain axes (DG);
 * 07-Mar-2007 : Fixed bug in axis label positioning (DG);
 * 27-Sep-2007 : Added getCategorySeriesMiddle() method (DG);
 * 21-Nov-2007 : Fixed performance bug noted by FindBugs in the
 *               equalPaintMaps() method (DG);
 * 23-Apr-2008 : Fixed bug 1942059, bad use of insets in
 *               calculateTextBlockWidth() (DG);
 * 26-Jun-2008 : Added new getCategoryMiddle() method (DG);
 * 27-Oct-2008 : Set font on Graphics2D when creating category labels (DG);
 * 14-Jan-2009 : Added new variant of getCategorySeriesMiddle() to make it
 *               simpler for renderers with hidden series (PK);
 * 19-Mar-2009 : Added entity support - see patch 2603321 by Peter Kolb (DG);
 * 16-Apr-2009 : Added tick mark drawing (DG);
 *
 */

package org.afree.chart.axis;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.util.ShapeUtilities;
import org.afree.ui.Size2D;
import org.afree.data.category.CategoryDataset;
import org.afree.chart.entity.CategoryLabelEntity;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.event.AxisChangeEvent;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.text.G2TextMeasurer;
import org.afree.chart.text.TextBlock;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;


/**
 * An axis that displays categories.
 */
public class CategoryAxis extends Axis implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 5886554608114265863L;

    /**
     * The default margin for the axis (used for both lower and upper margins).
     */
    public static final double DEFAULT_AXIS_MARGIN = 0.05;

    /**
     * The default margin between categories (a percentage of the overall axis
     * length).
     */
    public static final double DEFAULT_CATEGORY_MARGIN = 0.20;

    /** The amount of space reserved at the start of the axis. */
    private double lowerMargin;

    /** The amount of space reserved at the end of the axis. */
    private double upperMargin;

    /** The amount of space reserved between categories. */
    private double categoryMargin;

    /** The maximum number of lines for category labels. */
    private int maximumCategoryLabelLines;

    /**
     * A ratio that is multiplied by the width of one category to determine the
     * maximum label width.
     */
    private float maximumCategoryLabelWidthRatio;

    /** The category label offset. */
    private int categoryLabelPositionOffset;

    /**
     * A structure defining the category label positions for each axis location.
     */
    private CategoryLabelPositions categoryLabelPositions;

    /** Storage for tick label font overrides (if any). */
    private Map tickLabelFontMap;

    /** Storage for tick label paint overrides (if any). */
    private transient Map tickLabelPaintTypeMap;

    /** Storage for the category label tooltips (if any). */
    private Map categoryLabelToolTips;

    /**
     * Creates a new category axis with no label.
     */
    public CategoryAxis() {
        this(null);
    }

    /**
     * Constructs a category axis, using default values where necessary.
     * 
     * @param label
     *            the axis label (<code>null</code> permitted).
     */
    public CategoryAxis(String label) {

        super(label);

        this.lowerMargin = DEFAULT_AXIS_MARGIN;
        this.upperMargin = DEFAULT_AXIS_MARGIN;
        this.categoryMargin = DEFAULT_CATEGORY_MARGIN;
        this.maximumCategoryLabelLines = 1;
        this.maximumCategoryLabelWidthRatio = 0.0f;

        this.categoryLabelPositionOffset = 4;
        this.categoryLabelPositions = CategoryLabelPositions.STANDARD;
        this.tickLabelFontMap = new HashMap();
        this.tickLabelPaintTypeMap = new HashMap();
        this.categoryLabelToolTips = new HashMap();

    }

    /**
     * Returns the lower margin for the axis.
     * 
     * @return The margin.
     * 
     * @see #getUpperMargin()
     * @see #setLowerMargin(double)
     */
    public double getLowerMargin() {
        return this.lowerMargin;
    }

    /**
     * Sets the lower margin for the axis and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     * 
     * @param margin
     *            the margin as a percentage of the axis length (for example,
     *            0.05 is five percent).
     * 
     * @see #getLowerMargin()
     */
    public void setLowerMargin(double margin) {
        this.lowerMargin = margin;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the upper margin for the axis.
     * 
     * @return The margin.
     * 
     * @see #getLowerMargin()
     * @see #setUpperMargin(double)
     */
    public double getUpperMargin() {
        return this.upperMargin;
    }

    /**
     * Sets the upper margin for the axis and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     * 
     * @param margin
     *            the margin as a percentage of the axis length (for example,
     *            0.05 is five percent).
     * 
     * @see #getUpperMargin()
     */
    public void setUpperMargin(double margin) {
        this.upperMargin = margin;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the category margin.
     * 
     * @return The margin.
     * 
     * @see #setCategoryMargin(double)
     */
    public double getCategoryMargin() {
        return this.categoryMargin;
    }

    /**
     * Sets the category margin and sends an {@link AxisChangeEvent} to all
     * registered listeners. The overall category margin is distributed over N-1
     * gaps, where N is the number of categories on the axis.
     * 
     * @param margin
     *            the margin as a percentage of the axis length (for example,
     *            0.05 is five percent).
     * 
     * @see #getCategoryMargin()
     */
    public void setCategoryMargin(double margin) {
        this.categoryMargin = margin;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the maximum number of lines to use for each category label.
     * 
     * @return The maximum number of lines.
     * 
     * @see #setMaximumCategoryLabelLines(int)
     */
    public int getMaximumCategoryLabelLines() {
        return this.maximumCategoryLabelLines;
    }

    /**
     * Sets the maximum number of lines to use for each category label and sends
     * an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param lines
     *            the maximum number of lines.
     * 
     * @see #getMaximumCategoryLabelLines()
     */
    public void setMaximumCategoryLabelLines(int lines) {
        this.maximumCategoryLabelLines = lines;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the category label width ratio.
     * 
     * @return The ratio.
     * 
     * @see #setMaximumCategoryLabelWidthRatio(float)
     */
    public float getMaximumCategoryLabelWidthRatio() {
        return this.maximumCategoryLabelWidthRatio;
    }

    /**
     * Sets the maximum category label width ratio and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param ratio
     *            the ratio.
     * 
     * @see #getMaximumCategoryLabelWidthRatio()
     */
    public void setMaximumCategoryLabelWidthRatio(float ratio) {
        this.maximumCategoryLabelWidthRatio = ratio;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the offset between the axis and the category labels (before label
     * positioning is taken into account).
     * 
     * @return The offset (in Canvas units).
     * 
     * @see #setCategoryLabelPositionOffset(int)
     */
    public int getCategoryLabelPositionOffset() {
        return this.categoryLabelPositionOffset;
    }

    /**
     * Sets the offset between the axis and the category labels (before label
     * positioning is taken into account).
     * 
     * @param offset
     *            the offset (in Canvas units).
     * 
     * @see #getCategoryLabelPositionOffset()
     */
    public void setCategoryLabelPositionOffset(int offset) {
        this.categoryLabelPositionOffset = offset;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the category label position specification (this contains label
     * positioning info for all four possible axis locations).
     * 
     * @return The positions (never <code>null</code>).
     * 
     * @see #setCategoryLabelPositions(CategoryLabelPositions)
     */
    public CategoryLabelPositions getCategoryLabelPositions() {
        return this.categoryLabelPositions;
    }

    /**
     * Sets the category label position specification for the axis and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param positions
     *            the positions (<code>null</code> not permitted).
     * 
     * @see #getCategoryLabelPositions()
     */
    public void setCategoryLabelPositions(CategoryLabelPositions positions) {
        if (positions == null) {
            throw new IllegalArgumentException("Null 'positions' argument.");
        }
        this.categoryLabelPositions = positions;
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the font for the tick label for the given category.
     * 
     * @param category
     *            the category (<code>null</code> not permitted).
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setTickLabelFont(Comparable, Font)
     */
    public Font getTickLabelFont(Comparable category) {
        if (category == null) {
            throw new IllegalArgumentException("Null 'category' argument.");
        }
        Font result = (Font) this.tickLabelFontMap.get(category);
        // if there is no specific font, use the general one...
        if (result == null) {
            result = getTickLabelFont();
        }
        return result;
    }

    /**
     * Sets the font for the tick label for the specified category and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param category
     *            the category (<code>null</code> not permitted).
     * @param font
     *            the font (<code>null</code> permitted).
     * 
     * @see #getTickLabelFont(Comparable)
     */
    public void setTickLabelFont(Comparable category, Font font) {
        if (category == null) {
            throw new IllegalArgumentException("Null 'category' argument.");
        }
        if (font == null) {
            this.tickLabelFontMap.remove(category);
        } else {
            this.tickLabelFontMap.put(category, font);
        }
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the paint for the tick label for the given category.
     * 
     * @param category
     *            the category (<code>null</code> not permitted).
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setTickLabelPaintType(PaintType paintType)
     */
    public PaintType getTickLabelPaintType(Comparable category) {
        if (category == null) {
            throw new IllegalArgumentException("Null 'category' argument.");
        }
        PaintType result = (PaintType) this.tickLabelPaintTypeMap.get(category);
        // if there is no specific paint, use the general one...
        if (result == null) {
            result = getTickLabelPaintType();
        }
        return result;
    }

    /**
     * Sets the paint for the tick label for the specified category and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param category
     *            the category (<code>null</code> not permitted).
     * @param paintType
     *            the paint (<code>null</code> permitted).
     * 
     * @see #getTickLabelPaintType(Comparable)
     */
    public void setTickLabelPaintType(Comparable category, PaintType paintType) {
        if (category == null) {
            throw new IllegalArgumentException("Null 'category' argument.");
        }
        if (paintType == null) {
            this.tickLabelPaintTypeMap.remove(category);
        } else {
            this.tickLabelPaintTypeMap.put(category, paintType);
        }
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Adds a tooltip to the specified category and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param category
     *            the category (<code>null<code> not permitted).
     * @param tooltip
     *            the tooltip text (<code>null</code> permitted).
     * 
     * @see #removeCategoryLabelToolTip(Comparable)
     */
    public void addCategoryLabelToolTip(Comparable category, String tooltip) {
        if (category == null) {
            throw new IllegalArgumentException("Null 'category' argument.");
        }
        this.categoryLabelToolTips.put(category, tooltip);
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the tool tip text for the label belonging to the specified
     * category.
     * 
     * @param category
     *            the category (<code>null</code> not permitted).
     * 
     * @return The tool tip text (possibly <code>null</code>).
     * 
     * @see #addCategoryLabelToolTip(Comparable, String)
     * @see #removeCategoryLabelToolTip(Comparable)
     */
    public String getCategoryLabelToolTip(Comparable category) {
        if (category == null) {
            throw new IllegalArgumentException("Null 'category' argument.");
        }
        return (String) this.categoryLabelToolTips.get(category);
    }

    /**
     * Removes the tooltip for the specified category and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param category
     *            the category (<code>null<code> not permitted).
     * 
     * @see #addCategoryLabelToolTip(Comparable, String)
     * @see #clearCategoryLabelToolTips()
     */
    public void removeCategoryLabelToolTip(Comparable category) {
        if (category == null) {
            throw new IllegalArgumentException("Null 'category' argument.");
        }
        this.categoryLabelToolTips.remove(category);
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Clears the category label tooltips and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     * 
     * @see #addCategoryLabelToolTip(Comparable, String)
     * @see #removeCategoryLabelToolTip(Comparable)
     */
    public void clearCategoryLabelToolTips() {
        this.categoryLabelToolTips.clear();
        notifyListeners(new AxisChangeEvent(this));
    }

    /**
     * Returns the Java 2D coordinate for a category.
     * 
     * @param anchor
     *            the anchor point.
     * @param category
     *            the category index.
     * @param categoryCount
     *            the category count.
     * @param area
     *            the data area.
     * @param edge
     *            the location of the axis.
     * 
     * @return The coordinate.
     */
    public double getCategoryJava2DCoordinate(CategoryAnchor anchor,
            int category, int categoryCount, RectShape area,
            RectangleEdge edge) {

        double result = 0.0;
        if (anchor == CategoryAnchor.START) {
            result = getCategoryStart(category, categoryCount, area, edge);
        } else if (anchor == CategoryAnchor.MIDDLE) {
            result = getCategoryMiddle(category, categoryCount, area, edge);
        } else if (anchor == CategoryAnchor.END) {
            result = getCategoryEnd(category, categoryCount, area, edge);
        }
        return result;

    }

    /**
     * Returns the starting coordinate for the specified category.
     * 
     * @param category
     *            the category.
     * @param categoryCount
     *            the number of categories.
     * @param area
     *            the data area.
     * @param edge
     *            the axis location.
     * 
     * @return The coordinate.
     * 
     * @see #getCategoryMiddle(int, int, RectShape, RectangleEdge)
     * @see #getCategoryEnd(int, int, RectShape, RectangleEdge)
     */
    public double getCategoryStart(int category, int categoryCount,
            RectShape area, RectangleEdge edge) {

        double result = 0.0;
        if ((edge == RectangleEdge.TOP) || (edge == RectangleEdge.BOTTOM)) {
            result = area.getX() + area.getWidth() * getLowerMargin();
        } else if ((edge == RectangleEdge.LEFT)
                || (edge == RectangleEdge.RIGHT)) {
            result = area.getMinY() + area.getHeight() * getLowerMargin();
        }

        double categorySize = calculateCategorySize(categoryCount, area, edge);
        double categoryGapWidth = calculateCategoryGapSize(categoryCount, area,
                edge);

        result = result + category * (categorySize + categoryGapWidth);
        return result;

    }

    /**
     * Returns the middle coordinate for the specified category.
     * 
     * @param category
     *            the category.
     * @param categoryCount
     *            the number of categories.
     * @param area
     *            the data area.
     * @param edge
     *            the axis location.
     * 
     * @return The coordinate.
     * 
     * @see #getCategoryStart(int, int, RectShape, RectangleEdge)
     * @see #getCategoryEnd(int, int, RectShape, RectangleEdge)
     */
    public double getCategoryMiddle(int category, int categoryCount,
            RectShape area, RectangleEdge edge) {

        if (category < 0 || category >= categoryCount) {
            throw new IllegalArgumentException("Invalid category index: "
                    + category);
        }
        return getCategoryStart(category, categoryCount, area, edge)
                + calculateCategorySize(categoryCount, area, edge) / 2;

    }

    /**
     * Returns the end coordinate for the specified category.
     * 
     * @param category
     *            the category.
     * @param categoryCount
     *            the number of categories.
     * @param area
     *            the data area.
     * @param edge
     *            the axis location.
     * 
     * @return The coordinate.
     * 
     * @see #getCategoryStart(int, int, RectShape, RectangleEdge)
     * @see #getCategoryMiddle(int, int, RectShape, RectangleEdge)
     */
    public double getCategoryEnd(int category, int categoryCount,
            RectShape area, RectangleEdge edge) {

        return getCategoryStart(category, categoryCount, area, edge)
                + calculateCategorySize(categoryCount, area, edge);

    }

    /**
     * A convenience method that returns the axis coordinate for the centre of a
     * category.
     * 
     * @param category
     *            the category key (<code>null</code> not permitted).
     * @param categories
     *            the categories (<code>null</code> not permitted).
     * @param area
     *            the data area (<code>null</code> not permitted).
     * @param edge
     *            the edge along which the axis lies (<code>null</code> not
     *            permitted).
     * 
     * @return The centre coordinate.
     * 
     * @since JFreeChart 1.0.11
     * 
     * @see #getCategorySeriesMiddle(Comparable, Comparable, CategoryDataset,
     *      double, RectShape, RectangleEdge)
     */
    public double getCategoryMiddle(Comparable category, List categories,
            RectShape area, RectangleEdge edge) {
        if (categories == null) {
            throw new IllegalArgumentException("Null 'categories' argument.");
        }
        int categoryIndex = categories.indexOf(category);
        int categoryCount = categories.size();
        return getCategoryMiddle(categoryIndex, categoryCount, area, edge);
    }

    /**
     * Returns the middle coordinate (in Java2D space) for a series within a
     * category.
     * 
     * @param category
     *            the category (<code>null</code> not permitted).
     * @param seriesKey
     *            the series key (<code>null</code> not permitted).
     * @param dataset
     *            the dataset (<code>null</code> not permitted).
     * @param itemMargin
     *            the item margin (0.0 <= itemMargin < 1.0);
     * @param area
     *            the area (<code>null</code> not permitted).
     * @param edge
     *            the edge (<code>null</code> not permitted).
     * 
     * @return The coordinate in Java2D space.
     * 
     * @since JFreeChart 1.0.7
     */
    public double getCategorySeriesMiddle(Comparable category,
            Comparable seriesKey, CategoryDataset dataset, double itemMargin,
            RectShape area, RectangleEdge edge) {

        int categoryIndex = dataset.getColumnIndex(category);
        int categoryCount = dataset.getColumnCount();
        int seriesIndex = dataset.getRowIndex(seriesKey);
        int seriesCount = dataset.getRowCount();
        double start = getCategoryStart(categoryIndex, categoryCount, area,
                edge);
        double end = getCategoryEnd(categoryIndex, categoryCount, area, edge);
        double width = end - start;
        if (seriesCount == 1) {
            return start + width / 2.0;
        } else {
            double gap = (width * itemMargin) / (seriesCount - 1);
            double ww = (width * (1 - itemMargin)) / seriesCount;
            return start + (seriesIndex * (ww + gap)) + ww / 2.0;
        }
    }

    /**
     * Returns the middle coordinate (in Java2D space) for a series within a
     * category.
     * 
     * @param categoryIndex
     *            the category index.
     * @param categoryCount
     *            the category count.
     * @param seriesIndex
     *            the series index.
     * @param seriesCount
     *            the series count.
     * @param itemMargin
     *            the item margin (0.0 <= itemMargin < 1.0);
     * @param area
     *            the area (<code>null</code> not permitted).
     * @param edge
     *            the edge (<code>null</code> not permitted).
     * 
     * @return The coordinate in Java2D space.
     * 
     * @since JFreeChart 1.0.13
     */
    public double getCategorySeriesMiddle(int categoryIndex, int categoryCount,
            int seriesIndex, int seriesCount, double itemMargin,
            RectShape area, RectangleEdge edge) {

        double start = getCategoryStart(categoryIndex, categoryCount, area,
                edge);
        double end = getCategoryEnd(categoryIndex, categoryCount, area, edge);
        double width = end - start;
        if (seriesCount == 1) {
            return start + width / 2.0;
        } else {
            double gap = (width * itemMargin) / (seriesCount - 1);
            double ww = (width * (1 - itemMargin)) / seriesCount;
            return start + (seriesIndex * (ww + gap)) + ww / 2.0;
        }
    }

    /**
     * Calculates the size (width or height, depending on the location of the
     * axis) of a category.
     * 
     * @param categoryCount
     *            the number of categories.
     * @param area
     *            the area within which the categories will be drawn.
     * @param edge
     *            the axis location.
     * 
     * @return The category size.
     */
    protected double calculateCategorySize(int categoryCount, RectShape area,
            RectangleEdge edge) {

        double result = 0.0;
        double available = 0.0;

        if ((edge == RectangleEdge.TOP) || (edge == RectangleEdge.BOTTOM)) {
            available = area.getWidth();
        } else if ((edge == RectangleEdge.LEFT)
                || (edge == RectangleEdge.RIGHT)) {
            available = area.getHeight();
        }
        if (categoryCount > 1) {
            result = available
                    * (1 - getLowerMargin() - getUpperMargin() - getCategoryMargin());
            result = result / categoryCount;
        } else {
            result = available * (1 - getLowerMargin() - getUpperMargin());
        }
        return result;

    }

    /**
     * Calculates the size (width or height, depending on the location of the
     * axis) of a category gap.
     * 
     * @param categoryCount
     *            the number of categories.
     * @param area
     *            the area within which the categories will be drawn.
     * @param edge
     *            the axis location.
     * 
     * @return The category gap width.
     */
    protected double calculateCategoryGapSize(int categoryCount,
            RectShape area, RectangleEdge edge) {

        double result = 0.0;
        double available = 0.0;

        if ((edge == RectangleEdge.TOP) || (edge == RectangleEdge.BOTTOM)) {
            available = area.getWidth();
        } else if ((edge == RectangleEdge.LEFT)
                || (edge == RectangleEdge.RIGHT)) {
            available = area.getHeight();
        }

        if (categoryCount > 1) {
            result = available * getCategoryMargin() / (categoryCount - 1);
        }

        return result;

    }

    /**
     * Estimates the space required for the axis, given a specific drawing area.
     * 
     * @param canvas
     *            the graphics device (used to obtain font information).
     * @param plot
     *            the plot that the axis belongs to.
     * @param plotArea
     *            the area within which the axis should be drawn.
     * @param edge
     *            the axis location (top or bottom).
     * @param space
     *            the space already reserved.
     * 
     * @return The space required to draw the axis.
     */
    public AxisSpace reserveSpace(Canvas canvas, Plot plot, RectShape plotArea,
            RectangleEdge edge, AxisSpace space) {

        // create a new space object if one wasn't supplied...
        if (space == null) {
            space = new AxisSpace();
        }

        // if the axis is not visible, no additional space is required...
        if (!isVisible()) {
            return space;
        }

        // calculate the max size of the tick labels (if visible)...
        double tickLabelHeight = 0.0;
        double tickLabelWidth = 0.0;
        if (isTickLabelsVisible()) {
            // canvas.setFont(getTickLabelFont());
            AxisState state = new AxisState();
            // we call refresh ticks just to get the maximum width or height
            refreshTicks(canvas, state, plotArea, edge);
            if (edge == RectangleEdge.TOP) {
                tickLabelHeight = state.getMax();
            } else if (edge == RectangleEdge.BOTTOM) {
                tickLabelHeight = state.getMax();
            } else if (edge == RectangleEdge.LEFT) {
                tickLabelWidth = state.getMax();
            } else if (edge == RectangleEdge.RIGHT) {
                tickLabelWidth = state.getMax();
            }
        }

        // get the axis label size and update the space object...
        RectShape labelEnclosure = getLabelEnclosure(canvas, edge);
        double labelHeight = 0.0;
        double labelWidth = 0.0;
        if (RectangleEdge.isTopOrBottom(edge)) {
            labelHeight = labelEnclosure.getHeight();
            space.add(labelHeight + tickLabelHeight
                    + this.categoryLabelPositionOffset, edge);
        } else if (RectangleEdge.isLeftOrRight(edge)) {
            labelWidth = labelEnclosure.getWidth();
            space.add(labelWidth + tickLabelWidth
                    + this.categoryLabelPositionOffset, edge);
        }
        return space;

    }

    /**
     * Configures the axis against the current plot.
     */
    public void configure() {
        // nothing required
    }

    /**
     * Draws the axis on a graphics device (such as the screen or a
     * printer).
     * 
     * @param canvas
     *            the graphics device (<code>null</code> not permitted).
     * @param cursor
     *            the cursor location.
     * @param plotArea
     *            the area within which the axis should be drawn (
     *            <code>null</code> not permitted).
     * @param dataArea
     *            the area within which the plot is being drawn (
     *            <code>null</code> not permitted).
     * @param edge
     *            the location of the axis (<code>null</code> not permitted).
     * @param plotState
     *            collects information about the plot (<code>null</code>
     *            permitted).
     * 
     * @return The axis state (never <code>null</code>).
     */
    public AxisState draw(Canvas canvas, double cursor, RectShape plotArea,
            RectShape dataArea, RectangleEdge edge,
            PlotRenderingInfo plotState) {

        // if the axis is not visible, don't draw it...
        if (!isVisible()) {
            return new AxisState(cursor);
        }

        if (isAxisLineVisible()) {
            drawAxisLine(canvas, cursor, dataArea, edge);
        }
        AxisState state = new AxisState(cursor);
        if (isTickMarksVisible()) {
            drawTickMarks(canvas, cursor, dataArea, edge, state);
        }

        // draw the category labels and axis label
        state = drawCategoryLabels(canvas, plotArea, dataArea, edge, state,
                plotState);
        state = drawLabel(getLabel(), canvas, plotArea, dataArea, edge, state);
        createAndAddEntity(cursor, state, dataArea, edge, plotState);
        return state;

    }
    
    /**
     * Draws the category labels and returns the updated axis state.
     * 
     * @param canvas
     *            the graphics device (<code>null</code> not permitted).
     * @param plotArea
     *            the plot area (<code>null</code> not permitted).
     * @param dataArea
     *            the area inside the axes (<code>null</code> not permitted).
     * @param edge
     *            the axis location (<code>null</code> not permitted).
     * @param state
     *            the axis state (<code>null</code> not permitted).
     * @param plotState
     *            collects information about the plot (<code>null</code>
     *            permitted).
     * 
     * @return The updated axis state (never <code>null</code>).
     */
    protected AxisState drawCategoryLabels(Canvas canvas, RectShape plotArea,
            RectShape dataArea, RectangleEdge edge, AxisState state,
            PlotRenderingInfo plotState) {

        if (state == null) {
            throw new IllegalArgumentException("Null 'state' argument.");
        }

        if (isTickLabelsVisible()) {
            List ticks = refreshTicks(canvas, state, plotArea, edge);
            state.setTicks(ticks);

            int categoryIndex = 0;
            Iterator iterator = ticks.iterator();
            while (iterator.hasNext()) {

                CategoryTick tick = (CategoryTick) iterator.next();

                CategoryLabelPosition position = this.categoryLabelPositions
                        .getLabelPosition(edge);
                double x0 = 0.0;
                double x1 = 0.0;
                double y0 = 0.0;
                double y1 = 0.0;
                if (edge == RectangleEdge.TOP) {
                    x0 = getCategoryStart(categoryIndex, ticks.size(),
                            dataArea, edge);
                    x1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea,
                            edge);
                    y1 = state.getCursor() - this.categoryLabelPositionOffset;
                    y0 = y1 - state.getMax();
                } else if (edge == RectangleEdge.BOTTOM) {
                    x0 = getCategoryStart(categoryIndex, ticks.size(),
                            dataArea, edge);
                    x1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea,
                            edge);
                    y0 = state.getCursor() + this.categoryLabelPositionOffset;
                    y1 = y0 + state.getMax();
                } else if (edge == RectangleEdge.LEFT) {
                    y0 = getCategoryStart(categoryIndex, ticks.size(),
                            dataArea, edge);
                    y1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea,
                            edge);
                    x1 = state.getCursor() - this.categoryLabelPositionOffset - 6;
                    x0 = x1 - state.getMax() - 6;
                } else if (edge == RectangleEdge.RIGHT) {
                    y0 = getCategoryStart(categoryIndex, ticks.size(),
                            dataArea, edge);
                    y1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea,
                            edge);
                    x0 = state.getCursor() + this.categoryLabelPositionOffset;
                    x1 = x0 - state.getMax();
                }
                RectShape area = new RectShape(x0, y0, (x1 - x0),
                        (y1 - y0));
                PointF anchorPoint = RectangleAnchor.coordinates(area,
                        position.getCategoryAnchor());
                TextBlock block = tick.getLabel();
                block.draw(canvas, (float) anchorPoint.x, (float) anchorPoint
                        .y, position.getLabelAnchor(), (float) anchorPoint
                        .x, (float) anchorPoint.y, position
                        .getAngle());
                Shape bounds = block.calculateBounds(canvas, (float) anchorPoint
                        .x, (float) anchorPoint.y, position
                        .getLabelAnchor(), (float) anchorPoint.x,
                        (float) anchorPoint.y, position.getAngle());
                if (plotState != null && plotState.getOwner() != null) {
                    EntityCollection entities = plotState.getOwner()
                            .getEntityCollection();
                    if (entities != null) {
                        String tooltip = getCategoryLabelToolTip(tick
                                .getCategory());
                        entities.add(new CategoryLabelEntity(
                                tick.getCategory(), bounds, tooltip, null));
                    }
                }
                categoryIndex++;
            }

            if (edge.equals(RectangleEdge.TOP)) {
                double h = state.getMax() + this.categoryLabelPositionOffset;
                state.cursorUp(h);
            } else if (edge.equals(RectangleEdge.BOTTOM)) {
                double h = state.getMax() + this.categoryLabelPositionOffset;
                state.cursorDown(h);
            } else if (edge == RectangleEdge.LEFT) {
                double w = state.getMax() + this.categoryLabelPositionOffset;
                state.cursorLeft(w);
            } else if (edge == RectangleEdge.RIGHT) {
                double w = state.getMax() + this.categoryLabelPositionOffset;
                state.cursorRight(w);
            }
        }
        return state;
    }

    /**
     * Creates a temporary list of ticks that can be used when drawing the axis.
     * 
     * @param canvas
     *            the graphics device (used to get font measurements).
     * @param state
     *            the axis state.
     * @param dataArea
     *            the area inside the axes.
     * @param edge
     *            the location of the axis.
     * 
     * @return A list of ticks.
     */
    public List refreshTicks(Canvas canvas, AxisState state, RectShape dataArea,
            RectangleEdge edge) {

        List ticks = new java.util.ArrayList();

        // sanity check for data area...
        if (dataArea.getHeight() <= 0.0 || dataArea.getWidth() < 0.0) {
            return ticks;
        }

        CategoryPlot plot = (CategoryPlot) getPlot();
        List categories = plot.getCategoriesForAxis(this);
        double max = 0.0;

        if (categories != null) {
            CategoryLabelPosition position = this.categoryLabelPositions
                    .getLabelPosition(edge);
            float r = this.maximumCategoryLabelWidthRatio;
            if (r <= 0.0) {
                r = position.getWidthRatio();
            }

            float l = 0.0f;
            if (position.getWidthType() == CategoryLabelWidthType.CATEGORY) {
                l = (float) calculateCategorySize(categories.size(), dataArea,
                        edge);
            } else {
                if (RectangleEdge.isLeftOrRight(edge)) {
                    l = (float) dataArea.getWidth();
                } else {
                    l = (float) dataArea.getHeight();
                }
            }
            int categoryIndex = 0;
            Iterator iterator = categories.iterator();
            while (iterator.hasNext()) {
                Comparable category = (Comparable) iterator.next();
                // canvas.setFont(getTickLabelFont(category));
                TextBlock label = createLabel(category, l * r, edge, canvas);
                if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
                    max = Math.max(max, calculateTextBlockHeight(label,
                            position, canvas));
                } else if (edge == RectangleEdge.LEFT
                        || edge == RectangleEdge.RIGHT) {
                    max = Math.max(max, calculateTextBlockWidth(label,
                            position, canvas));
                }
                Tick tick = new CategoryTick(category, label, position
                        .getLabelAnchor(), position.getRotationAnchor(),
                        position.getAngle());
                ticks.add(tick);
                categoryIndex = categoryIndex + 1;
            }
        }
        state.setMax(max);
        return ticks;

    }
    /**
     * Draws the tick marks.
     * 
     * @since JFreeChart 1.0.13
     */
    public void drawTickMarks(Canvas canvas, double cursor, RectShape dataArea,
            RectangleEdge edge, AxisState state) {

        Plot p = getPlot();
        if (p == null) {
            return;
        }
        CategoryPlot plot = (CategoryPlot) p;
        double il = getTickMarkInsideLength();
        double ol = getTickMarkOutsideLength();
        LineShape line = new LineShape();
        List categories = plot.getCategoriesForAxis(this);

        Paint tickMark = PaintUtility.createPaint(
                Paint.ANTI_ALIAS_FLAG,
                getTickMarkPaintType(), 
                getTickMarkStroke(), 
                getTickMarkEffect());

        if (edge.equals(RectangleEdge.TOP)) {
            Iterator iterator = categories.iterator();
            while (iterator.hasNext()) {
                Comparable key = (Comparable) iterator.next();
                double x = getCategoryMiddle(key, categories, dataArea, edge);
                line.setLine(x, cursor, x, cursor + il);
                canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                        (float) line.getX2(), (float) line.getY2(), tickMark);

                line.setLine(x, cursor, x, cursor - ol);
                canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                        (float) line.getX2(), (float) line.getY2(), tickMark);
            }
            state.cursorUp(ol);
        } else if (edge.equals(RectangleEdge.BOTTOM)) {
            Iterator iterator = categories.iterator();
            while (iterator.hasNext()) {
                Comparable key = (Comparable) iterator.next();
                double x = getCategoryMiddle(key, categories, dataArea, edge);
                line.setLine(x, cursor, x, cursor - il);
                canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                        (float) line.getX2(), (float) line.getY2(), tickMark);
                line.setLine(x, cursor, x, cursor + ol);
                canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                        (float) line.getX2(), (float) line.getY2(), tickMark);
            }
            state.cursorDown(ol);
        } else if (edge.equals(RectangleEdge.LEFT)) {
            Iterator iterator = categories.iterator();
            while (iterator.hasNext()) {
                Comparable key = (Comparable) iterator.next();
                double y = getCategoryMiddle(key, categories, dataArea, edge);
                line.setLine(cursor, y, cursor + il, y);
                canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                        (float) line.getX2(), (float) line.getY2(), tickMark);
                line.setLine(cursor, y, cursor - ol, y);
                canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                        (float) line.getX2(), (float) line.getY2(), tickMark);
            }
            state.cursorLeft(ol);
        } else if (edge.equals(RectangleEdge.RIGHT)) {
            Iterator iterator = categories.iterator();
            while (iterator.hasNext()) {
                Comparable key = (Comparable) iterator.next();
                double y = getCategoryMiddle(key, categories, dataArea, edge);
                line.setLine(cursor, y, cursor - il, y);
                canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                        (float) line.getX2(), (float) line.getY2(), tickMark);
                line.setLine(cursor, y, cursor + ol, y);
                canvas.drawLine((float) line.getX1(), (float) line.getY1(),
                        (float) line.getX2(), (float) line.getY2(), tickMark);
            }
            state.cursorRight(ol);
        }
    }

    /**
     * Creates a label.
     * 
     * @param category
     *            the category.
     * @param width
     *            the available width.
     * @param edge
     *            the edge on which the axis appears.
     * @param canvas
     *            the graphics device.
     * 
     * @return A label.
     */
    protected TextBlock createLabel(Comparable category, float width,
            RectangleEdge edge, Canvas canvas) {
        Paint paint = PaintUtility.createPaint(
                Paint.ANTI_ALIAS_FLAG, 
                getLabelPaintType(), 
                getLabelFont());
        
        TextBlock label = TextUtilities.createTextBlock(category.toString(),
                getTickLabelFont(category), getTickLabelPaintType(category), width,
                this.maximumCategoryLabelLines, new G2TextMeasurer(paint));
        return label;
    }

    /**
     * A utility method for determining the width of a text block.
     * 
     * @param block
     *            the text block.
     * @param position
     *            the position.
     * @param canvas
     *            the graphics device.
     * 
     * @return The width.
     */
    protected double calculateTextBlockWidth(TextBlock block,
            CategoryLabelPosition position, Canvas canvas) {

        RectangleInsets insets = getTickLabelInsets();
        Size2D size = block.calculateDimensions(canvas);
        RectShape box = new RectShape(0.0, 0.0, size.getWidth(),
                size.getHeight());
        Shape rotatedBox = ShapeUtilities.rotateShape(box, position.getAngle(),
                0.0f, 0.0f);
        RectShape rectShape = new RectShape();
        rotatedBox.getBounds(rectShape);
        
        double w = rectShape.getWidth() + insets.getLeft()
                + insets.getRight();
        return w;

    }

    /**
     * A utility method for determining the height of a text block.
     * 
     * @param block
     *            the text block.
     * @param position
     *            the label position.
     * @param canvas
     *            the graphics device.
     * 
     * @return The height.
     */
    protected double calculateTextBlockHeight(TextBlock block,
            CategoryLabelPosition position, Canvas canvas) {

        RectangleInsets insets = getTickLabelInsets();
        Size2D size = block.calculateDimensions(canvas);
        RectShape box = new RectShape(0.0, 0.0, size.getWidth(),
                size.getHeight());
        Shape rotatedBox = ShapeUtilities.rotateShape(box, position.getAngle(),
                0.0f, 0.0f);
        RectShape rectShape = new RectShape();
        rotatedBox.getBounds(rectShape);
        double h = rectShape.getHeight() + insets.getTop()
                + insets.getBottom();
        return h;

    }

    /**
     * Creates a clone of the axis.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if some component of the axis does
     *         not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
        CategoryAxis clone = (CategoryAxis) super.clone();
        clone.tickLabelFontMap = new HashMap(this.tickLabelFontMap);
        clone.tickLabelPaintTypeMap = new HashMap(this.tickLabelPaintTypeMap);
        clone.categoryLabelToolTips = new HashMap(this.categoryLabelToolTips);
        return clone;
    }    
    
}
