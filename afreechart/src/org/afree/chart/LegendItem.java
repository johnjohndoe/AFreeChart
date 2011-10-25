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
 * ---------------
 * LegendItem.java
 * ---------------
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
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Andrzej Porebski;
 *                   David Li;
 *                   Wolfgang Irler;
 *                   Luke Quinane;
 *
 * Changes (from 2-Oct-2002)
 * -------------------------
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 17-Jan-2003 : Dropped outlineStroke attribute (DG);
 * 08-Oct-2003 : Applied patch for displaying series line style, contributed by
 *               Luke Quinane (DG);
 * 21-Jan-2004 : Added the shapeFilled flag (DG);
 * 04-Jun-2004 : Added equals() method, implemented Serializable (DG);
 * 25-Nov-2004 : Changes required by new LegendTitle implementation (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0
 *               release (DG);
 * 20-Apr-2005 : Added tooltip and URL text (DG);
 * 28-Nov-2005 : Separated constructors for AttributedString labels (DG);
 * 10-Dec-2005 : Fixed serialization bug (1377239) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Added dataset and series index fields (DG);
 * 13-Dec-2006 : Added fillPaintTransformer attribute (DG);
 * 18-May-2007 : Added dataset and seriesKey fields (DG);
 * 03-Aug-2007 : Fixed null pointer exception (DG);
 * 23-Apr-2008 : Added new constructor and implemented Cloneable (DG);
 * 17-Jun-2008 : Added optional labelFont and labelPaint attributes (DG);
 * 15-Oct-2008 : Added new constructor (DG);
 *
 */

package org.afree.chart;

import java.io.Serializable;
import java.text.AttributedString;
import java.text.CharacterIterator;

import org.afree.ui.GradientShaderFactory;
import org.afree.ui.StandardGradientShaderFactory;
import org.afree.data.general.Dataset;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import android.graphics.Color;

/**
 * A temporary storage object for recording the properties of a legend item,
 * without any consideration for layout issues.
 */
public class LegendItem implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -797214582948827144L;

    /**
     * The dataset.
     * 
     * @since JFreeChart 1.0.6
     */
    private Dataset dataset;

    /**
     * The series key.
     * 
     * @since JFreeChart 1.0.6
     */
    private Comparable seriesKey;

    /** The dataset index. */
    private int datasetIndex;

    /** The series index. */
    private int series;

    /** The label. */
    private String label;

    /**
     * The label font (<code>null</code> is permitted).
     * 
     * @since JFreeChart 1.0.11
     */
    private Font labelFont;

    /**
     * The label paint (<code>null</code> is permitted).
     * 
     * @since JFreeChart 1.0.11
     */
    private transient PaintType labelPaintType;

    /** The attributed label (if null, fall back to the regular label). */
    private transient AttributedString attributedLabel;

    /**
     * The description (not currently used - could be displayed as a tool tip).
     */
    private String description;

    /** The tool tip text. */
    private String toolTipText;

    /** The url text. */
    private String urlText;

    /** A flag that controls whether or not the shape is visible. */
    private boolean shapeVisible;

    /** The shape. */
    private transient Shape shape;

    /** A flag that controls whether or not the shape is filled. */
    private boolean shapeFilled;

    /** The paint. */
    private transient PaintType fillPaintType;

    /**
     * A gradient paint transformer.
     * 
     * @since JFreeChart 1.0.4
     */
     private GradientShaderFactory fillShaderFactory;

    /** A flag that controls whether or not the shape outline is visible. */
    private boolean shapeOutlineVisible;

    /** The outline paint. */
    private transient PaintType outlinePaintType;

    /** The outline stroke. */
    private transient float outlineStroke;

    /** A flag that controls whether or not the line is visible. */
    private boolean lineVisible;

    /** The line. */
    private transient Shape line;

    /** The stroke. */
    private transient float lineStroke;

    /** The line paint. */
    private transient PaintType linePaintType;

    /**
     * The shape must be non-null for a LegendItem - if no shape is required,
     * use this.
     */
    private static final Shape UNUSED_SHAPE = new LineShape();

    /**
     * The stroke must be non-null for a LegendItem - if no stroke is required,
     * use this.
     */
    private static final float UNUSED_STROKE = 0.0f;

    /**
     * Creates a legend item with the specified label. The remaining attributes
     * take default values.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.10
     */
    public LegendItem(String label) {
        this(label, new SolidColor(Color.BLACK));
    }

    /**
     * Creates a legend item with the specified label and fill paint. The
     * remaining attributes take default values.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     * @param color
     *            the color (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.12
     */
    public LegendItem(String label, PaintType color) {
        this(label, null, null, null, new RectShape(-4.0, -4.0, 8.0,
                8.0), color);
    }

    /**
     * Creates a legend item with a filled shape. The shape is not outlined, and
     * no line is visible.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     * @param description
     *            the description (<code>null</code> permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     * @param urlText
     *            the URL text (<code>null</code> permitted).
     * @param shape
     *            the shape (<code>null</code> not permitted).
     * @param fillColor
     *            the painttype used to fill the shape (<code>null</code> not
     *            permitted).
     */
    public LegendItem(String label, String description, String toolTipText,
            String urlText, Shape shape, PaintType fillColor) {

        this(label, description, toolTipText, urlText,
        /* shape visible = */true, shape,
        /* shape filled = */true, fillColor,
        /* shape outlined */false, new SolidColor(Color.BLACK), UNUSED_STROKE,
        /* line visible */false, UNUSED_SHAPE, UNUSED_STROKE, new SolidColor(Color.BLACK));

    }

    /**
     * Creates a legend item with a filled and outlined shape.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     * @param description
     *            the description (<code>null</code> permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     * @param urlText
     *            the URL text (<code>null</code> permitted).
     * @param shape
     *            the shape (<code>null</code> not permitted).
     * @param fillPaint
     *            the paint used to fill the shape (<code>null</code> not
     *            permitted).
     * @param outlineStroke
     *            the outline stroke (<code>null</code> not permitted).
     * @param outlinePaint
     *            the outline paint (<code>null</code> not permitted).
     */
    public LegendItem(String label, String description, String toolTipText,
            String urlText, Shape shape, PaintType fillPaint, float outlineStroke,
            PaintType outlinePaint) {

        this(label, description, toolTipText, urlText,
        /* shape visible = */true, shape,
        /* shape filled = */true, fillPaint,
        /* shape outlined = */true, outlinePaint, outlineStroke,
        /* line visible */false, UNUSED_SHAPE, UNUSED_STROKE, new SolidColor(Color.BLACK));

    }

    /**
     * Creates a legend item using a line.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     * @param description
     *            the description (<code>null</code> permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     * @param urlText
     *            the URL text (<code>null</code> permitted).
     * @param line
     *            the line (<code>null</code> not permitted).
     * @param lineStroke
     *            the line stroke (<code>null</code> not permitted).
     * @param linePaint
     *            the line paint (<code>null</code> not permitted).
     */
    public LegendItem(String label, String description, String toolTipText,
            String urlText, Shape line, float lineStroke, PaintType linePaint) {

        this(label, description, toolTipText, urlText,
        /* shape visible = */false, UNUSED_SHAPE,
        /* shape filled = */false, new SolidColor(Color.BLACK),
        /* shape outlined = */false, new SolidColor(Color.BLACK), UNUSED_STROKE,
        /* line visible = */true, line, lineStroke, linePaint);
    }

    /**
     * Creates a new legend item.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     * @param description
     *            the description (not currently used, <code>null</code>
     *            permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     * @param urlText
     *            the URL text (<code>null</code> permitted).
     * @param shapeVisible
     *            a flag that controls whether or not the shape is displayed.
     * @param shape
     *            the shape (<code>null</code> permitted).
     * @param shapeFilled
     *            a flag that controls whether or not the shape is filled.
     * @param fillPaint
     *            the fill paint (<code>null</code> not permitted).
     * @param shapeOutlineVisible
     *            a flag that controls whether or not the shape is outlined.
     * @param outlinePaint
     *            the outline paint (<code>null</code> not permitted).
     * @param outlineStroke
     *            the outline stroke (<code>null</code> not permitted).
     * @param lineVisible
     *            a flag that controls whether or not the line is visible.
     * @param line
     *            the line.
     * @param lineStroke
     *            the stroke (<code>null</code> not permitted).
     * @param linePaint
     *            the line paint (<code>null</code> not permitted).
     */
    public LegendItem(String label, String description, String toolTipText,
            String urlText, boolean shapeVisible, Shape shape,
            boolean shapeFilled, PaintType fillPaint, boolean shapeOutlineVisible,
            PaintType outlinePaint, float outlineStroke, boolean lineVisible,
            Shape line, float lineStroke, PaintType linePaint) {

        if (label == null) {
            throw new IllegalArgumentException("Null 'label' argument.");
        }

        this.label = label;
        this.labelPaintType = null;
        this.attributedLabel = null;
        this.description = description;
        this.shapeVisible = shapeVisible;
        this.shape = shape;
        this.shapeFilled = shapeFilled;
        this.fillPaintType = fillPaint;
        this.fillShaderFactory = new StandardGradientShaderFactory();
        this.shapeOutlineVisible = shapeOutlineVisible;
        this.outlinePaintType = outlinePaint;
        this.outlineStroke = outlineStroke;
        this.lineVisible = lineVisible;
        this.line = line;
        this.lineStroke = lineStroke;
        this.linePaintType = linePaint;
        this.toolTipText = toolTipText;
        this.urlText = urlText;
    }

    /**
     * Creates a legend item with a filled shape. The shape is not outlined, and
     * no line is visible.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     * @param description
     *            the description (<code>null</code> permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     * @param urlText
     *            the URL text (<code>null</code> permitted).
     * @param shape
     *            the shape (<code>null</code> not permitted).
     * @param fillPaint
     *            the paint used to fill the shape (<code>null</code> not
     *            permitted).
     */
    public LegendItem(AttributedString label, String description,
            String toolTipText, String urlText, Shape shape, PaintType fillPaint) {

        this(label, description, toolTipText, urlText,
        /* shape visible = */true, shape,
        /* shape filled = */true, fillPaint,
        /* shape outlined = */false, new SolidColor(Color.BLACK), UNUSED_STROKE,
        /* line visible = */false, UNUSED_SHAPE, UNUSED_STROKE, new SolidColor(Color.BLACK));

    }

    /**
     * Creates a legend item with a filled and outlined shape.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     * @param description
     *            the description (<code>null</code> permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     * @param urlText
     *            the URL text (<code>null</code> permitted).
     * @param shape
     *            the shape (<code>null</code> not permitted).
     * @param fillPaint
     *            the paint used to fill the shape (<code>null</code> not
     *            permitted).
     * @param outlineStroke
     *            the outline stroke (<code>null</code> not permitted).
     * @param outlinePaint
     *            the outline paint (<code>null</code> not permitted).
     */
    public LegendItem(AttributedString label, String description,
            String toolTipText, String urlText, Shape shape, PaintType fillPaint,
            float outlineStroke, PaintType outlinePaint) {

        this(label, description, toolTipText, urlText,
        /* shape visible = */true, shape,
        /* shape filled = */true, fillPaint,
        /* shape outlined = */true, outlinePaint, outlineStroke,
        /* line visible = */false, UNUSED_SHAPE, UNUSED_STROKE, new SolidColor(Color.BLACK));
    }

    /**
     * Creates a legend item using a line.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     * @param description
     *            the description (<code>null</code> permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     * @param urlText
     *            the URL text (<code>null</code> permitted).
     * @param line
     *            the line (<code>null</code> not permitted).
     * @param lineStroke
     *            the line stroke (<code>null</code> not permitted).
     * @param linePaint
     *            the line paint (<code>null</code> not permitted).
     */
    public LegendItem(AttributedString label, String description,
            String toolTipText, String urlText, Shape line, float lineStroke,
            PaintType linePaint) {

        this(label, description, toolTipText, urlText,
        /* shape visible = */false, UNUSED_SHAPE,
        /* shape filled = */false, new SolidColor(Color.BLACK),
        /* shape outlined = */false, new SolidColor(Color.BLACK), UNUSED_STROKE,
        /* line visible = */true, line, lineStroke, linePaint);
    }

    /**
     * Creates a new legend item.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     * @param description
     *            the description (not currently used, <code>null</code>
     *            permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     * @param urlText
     *            the URL text (<code>null</code> permitted).
     * @param shapeVisible
     *            a flag that controls whether or not the shape is displayed.
     * @param shape
     *            the shape (<code>null</code> permitted).
     * @param shapeFilled
     *            a flag that controls whether or not the shape is filled.
     * @param fillPaint
     *            the fill paint (<code>null</code> not permitted).
     * @param shapeOutlineVisible
     *            a flag that controls whether or not the shape is outlined.
     * @param outlinePaint
     *            the outline paint (<code>null</code> not permitted).
     * @param outlineStroke
     *            the outline stroke (<code>null</code> not permitted).
     * @param lineVisible
     *            a flag that controls whether or not the line is visible.
     * @param line
     *            the line (<code>null</code> not permitted).
     * @param lineStroke
     *            the stroke (<code>null</code> not permitted).
     * @param linePaint
     *            the line paint (<code>null</code> not permitted).
     */
    public LegendItem(AttributedString label, String description,
            String toolTipText, String urlText, boolean shapeVisible,
            Shape shape, boolean shapeFilled, PaintType fillPaint,
            boolean shapeOutlineVisible, PaintType outlinePaint, float outlineStroke,
            boolean lineVisible, Shape line, float lineStroke, PaintType linePaint) {

        if (label == null) {
            throw new IllegalArgumentException("Null 'label' argument.");
        }

        if (line == null) {
            throw new IllegalArgumentException("Null 'line' argument.");
        }

        this.label = characterIteratorToString(label.getIterator());
        this.attributedLabel = label;
        this.description = description;
        this.shapeVisible = shapeVisible;
        this.shape = shape;
        this.shapeFilled = shapeFilled;
        this.fillPaintType = fillPaint;
        this.fillShaderFactory = new StandardGradientShaderFactory();
        this.shapeOutlineVisible = shapeOutlineVisible;
        this.outlinePaintType = outlinePaint;
        this.outlineStroke = outlineStroke;
        this.lineVisible = lineVisible;
        this.line = line;
        this.lineStroke = lineStroke;
        this.linePaintType = linePaint;
        this.toolTipText = toolTipText;
        this.urlText = urlText;
    }

    /**
     * Returns a string containing the characters from the given iterator.
     * 
     * @param iterator
     *            the iterator (<code>null</code> not permitted).
     * 
     * @return A string.
     */
    private String characterIteratorToString(CharacterIterator iterator) {
        int endIndex = iterator.getEndIndex();
        int beginIndex = iterator.getBeginIndex();
        int count = endIndex - beginIndex;
        if (count <= 0) {
            return "";
        }
        char[] chars = new char[count];
        int i = 0;
        char c = iterator.first();
        while (c != CharacterIterator.DONE) {
            chars[i] = c;
            i++;
            c = iterator.next();
        }
        return new String(chars);
    }

    /**
     * Returns the dataset.
     * 
     * @return The dataset.
     * 
     * @since JFreeChart 1.0.6
     * 
     * @see #setDatasetIndex(int)
     */
    public Dataset getDataset() {
        return this.dataset;
    }

    /**
     * Sets the dataset.
     * 
     * @param dataset
     *            the dataset.
     * 
     * @since JFreeChart 1.0.6
     */
    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    /**
     * Returns the dataset index for this legend item.
     * 
     * @return The dataset index.
     * 
     * @since JFreeChart 1.0.2
     * 
     * @see #setDatasetIndex(int)
     * @see #getDataset()
     */
    public int getDatasetIndex() {
        return this.datasetIndex;
    }

    /**
     * Sets the dataset index for this legend item.
     * 
     * @param index
     *            the index.
     * 
     * @since JFreeChart 1.0.2
     * 
     * @see #getDatasetIndex()
     */
    public void setDatasetIndex(int index) {
        this.datasetIndex = index;
    }

    /**
     * Returns the series key.
     * 
     * @return The series key.
     * 
     * @since JFreeChart 1.0.6
     * 
     * @see #setSeriesKey(Comparable)
     */
    public Comparable getSeriesKey() {
        return this.seriesKey;
    }

    /**
     * Sets the series key.
     * 
     * @param key
     *            the series key.
     * 
     * @since JFreeChart 1.0.6
     */
    public void setSeriesKey(Comparable key) {
        this.seriesKey = key;
    }

    /**
     * Returns the series index for this legend item.
     * 
     * @return The series index.
     * 
     * @since JFreeChart 1.0.2
     */
    public int getSeriesIndex() {
        return this.series;
    }

    /**
     * Sets the series index for this legend item.
     * 
     * @param index
     *            the index.
     * 
     * @since JFreeChart 1.0.2
     */
    public void setSeriesIndex(int index) {
        this.series = index;
    }

    /**
     * Returns the label.
     * 
     * @return The label (never <code>null</code>).
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the label font.
     * 
     * @return The label font (possibly <code>null</code>).
     * 
     * @since JFreeChart 1.0.11
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Sets the label font.
     * 
     * @param font
     *            the font (<code>null</code> permitted).
     * 
     * @since JFreeChart 1.0.11
     */
    public void setLabelFont(Font font) {
        this.labelFont = font;
    }

    /**
     * Returns the paint used to draw the label.
     * 
     * @return The paint type (possibly <code>null</code>).
     * 
     * @since JFreeChart 1.0.11
     */
    public PaintType getLabelPaintType() {
        return this.labelPaintType;
    }

    /**
     * Sets the paint used to draw the label.
     * 
     * @param paintType
     *            the paint (<code>null</code> permitted).
     * 
     * @since JFreeChart 1.0.11
     */
    public void setLabelPaintType(PaintType paintType) {
        this.labelPaintType = paintType;
    }

    /**
     * Returns the attributed label.
     * 
     * @return The attributed label (possibly <code>null</code>).
     */
    public AttributedString getAttributedLabel() {
        return this.attributedLabel;
    }

    /**
     * Returns the description for the legend item.
     * 
     * @return The description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the tool tip text.
     * 
     * @return The tool tip text (possibly <code>null</code>).
     */
    public String getToolTipText() {
        return this.toolTipText;
    }

    /**
     * Returns the URL text.
     * 
     * @return The URL text (possibly <code>null</code>).
     */
    public String getURLText() {
        return this.urlText;
    }

    /**
     * Returns a flag that indicates whether or not the shape is visible.
     * 
     * @return A boolean.
     */
    public boolean isShapeVisible() {
        return this.shapeVisible;
    }

    /**
     * Returns the shape used to label the series represented by this legend
     * item.
     * 
     * @return The shape (never <code>null</code>).
     */
    public Shape getShape() {
        return this.shape;
    }

    /**
     * Returns a flag that controls whether or not the shape is filled.
     * 
     * @return A boolean.
     */
    public boolean isShapeFilled() {
        return this.shapeFilled;
    }

    /**
     * Returns the fill paint.
     * 
     * @return The fill paint (never <code>null</code>).
     */
    public PaintType getFillPaintType() {
        return this.fillPaintType;
    }

    /**
     * Sets the fill paint.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.11
     */
    public void setFillPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.fillPaintType = paintType;
    }

    /**
     * Returns the flag that controls whether or not the shape outline is
     * visible.
     * 
     * @return A boolean.
     */
    public boolean isShapeOutlineVisible() {
        return this.shapeOutlineVisible;
    }

    /**
     * Returns the line stroke for the series.
     * 
     * @return The stroke (never <code>null</code>).
     */
    public float getLineStroke() {
        return this.lineStroke;
    }

    /**
     * Returns the paint used for lines.
     * 
     * @return The paint type (never <code>null</code>).
     */
    public PaintType getLinePaintType() {
        return this.linePaintType;
    }

    /**
     * Sets the line paint.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.11
     */
    public void setLinePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.linePaintType = paintType;
    }

    /**
     * Returns the outline paint.
     * 
     * @return The outline paint (never <code>null</code>).
     */
    public PaintType getOutlinePaintType() {
        return this.outlinePaintType;
    }

    /**
     * Sets the outline paint.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.11
     */
    public void setOutlinePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.outlinePaintType = paintType;
    }

    /**
     * Returns the outline stroke.
     * 
     * @return The outline stroke (never <code>null</code>).
     */
    public float getOutlineStroke() {
        return this.outlineStroke;
    }

    /**
     * Returns a flag that indicates whether or not the line is visible.
     * 
     * @return A boolean.
     */
    public boolean isLineVisible() {
        return this.lineVisible;
    }

    /**
     * Returns the line.
     * 
     * @return The line (never <code>null</code>).
     */
    public Shape getLine() {
        return this.line;
    }

    /* *//**
     * Returns the transformer used when the fill paint is an instance of
     * <code>GradientPaint</code>.
     * 
     * @return The transformer (never <code>null</code>).
     * 
     * @since JFreeChart 1.0.4
     * 
     * @see #setFillShaderFactory(GradientShaderFactory)
     */
    
      public GradientShaderFactory getFillShaderFactory() { return
      this.fillShaderFactory; }
     /**
     * Sets the transformer used when the fill paint is an instance of
     * <code>GradientPaint</code>.
     * 
     * @param factory
     *            the transformer (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.4
     * 
     * @see #getFillPaintTransformer()
     */
    
     public void setFillShaderFactory(GradientShaderFactory factory)
     { if (factory == null) { throw new
     IllegalArgumentException("Null 'transformer' attribute."); }
     this.fillShaderFactory = factory; }
    

}
