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
 * ---------------
 * LabelBlock.java
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
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2004-2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Pierre-Marie Le Biot;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 19-Apr-2005 : Added optional tooltip and URL text items,
 *               draw() method now returns entities if
 *               requested (DG);
 * 13-May-2005 : Added methods to set the font (DG);
 * 01-Sep-2005 : Added paint management (PMLB);
 *               Implemented equals() and clone() (PublicCloneable) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Fixed entity area in draw() method (DG);
 * 16-Mar-2007 : Fixed serialization when using GradientPaint (DG);
 * 10-Feb-2009 : Added alignment fields (DG);
 *
 */

package org.afree.chart.block;

import org.afree.ui.RectangleAnchor;
import org.afree.ui.Size2D;
import org.afree.chart.entity.ChartEntity;
import org.afree.chart.entity.StandardEntityCollection;
import org.afree.chart.text.TextBlock;
import org.afree.chart.text.TextBlockAnchor;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Typeface;

/**
 * A block containing a label.
 */
public class LabelBlock extends AbstractBlock implements Block {

    /** For serialization. */
    static final long serialVersionUID = 249626098864178017L;

    /**
     * The text for the label - retained in case the label needs regenerating
     * (for example, to change the font).
     */
    private String text;

    /** The label. */
    private TextBlock label;

    /** The font. */
    private Font font;

    /** The tool tip text (can be <code>null</code>). */
    private String toolTipText;

    /** The URL text (can be <code>null</code>). */
    private String urlText;

    /** The default color. */
    public static final PaintType DEFAULT_PAINT_TYPE = new SolidColor(Color.BLACK);

    /** The paint. */
    private transient PaintType paintType;

    /**
     * The content alignment point.
     * 
     * @since JFreeChart 1.0.13
     */
    private TextBlockAnchor contentAlignmentPoint;

    /**
     * The anchor point for the text.
     * 
     * @since JFreeChart 1.0.13
     */
    private RectangleAnchor textAnchor;

    /**
     * Creates a new label block.
     * 
     * @param label
     *            the label (<code>null</code> not permitted).
     */
    public LabelBlock(String label) {
        this(label, new Font("SansSerif", Typeface.NORMAL, 10), DEFAULT_PAINT_TYPE);
    }

    /**
     * Creates a new label block.
     * 
     * @param text
     *            the text for the label (<code>null</code> not permitted).
     * @param font
     *            the font (<code>null</code> not permitted).
     */
    public LabelBlock(String text, Font font) {
        this(text, font, DEFAULT_PAINT_TYPE);
    }

    /**
     * Creates a new label block.
     * 
     * @param text
     *            the text for the label (<code>null</code> not permitted).
     * @param font
     *            the font (<code>null</code> not permitted).
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     */
    public LabelBlock(String text, Font font, PaintType paintType) {
        this.text = text;
        this.paintType = paintType;
        this.label = TextUtilities.createTextBlock(text, font, this.paintType);
        this.font = font;
        this.toolTipText = null;
        this.urlText = null;
        this.contentAlignmentPoint = TextBlockAnchor.CENTER;
        this.textAnchor = RectangleAnchor.CENTER;
    }

    /**
     * Returns the font.
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setFont(Font)
     */
    public Font getFont() {
        return this.font;
    }

    /**
     * Sets the font and regenerates the label.
     * 
     * @param font
     *            the font (<code>null</code> not permitted).
     * 
     * @see #getFont()
     */
    public void setFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.font = font;
        this.label = TextUtilities.createTextBlock(this.text, font, this.paintType);
    }

    /**
     * Returns the paint.
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setPaintType(PaintType paintType)
     */
    public PaintType getPaintType() {
        return this.paintType;
    }

    /**
     * Sets the paint and regenerates the label.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getPaintType()
     */
    public void setPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.paintType = paintType;
        this.label = TextUtilities.createTextBlock(this.text, this.font,
                this.paintType);
    }

    /**
     * Returns the tool tip text.
     * 
     * @return The tool tip text (possibly <code>null</code>).
     * 
     * @see #setToolTipText(String)
     */
    public String getToolTipText() {
        return this.toolTipText;
    }

    /**
     * Sets the tool tip text.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     * 
     * @see #getToolTipText()
     */
    public void setToolTipText(String text) {
        this.toolTipText = text;
    }

    /**
     * Returns the URL text.
     * 
     * @return The URL text (possibly <code>null</code>).
     * 
     * @see #setURLText(String)
     */
    public String getURLText() {
        return this.urlText;
    }

    /**
     * Sets the URL text.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     * 
     * @see #getURLText()
     */
    public void setURLText(String text) {
        this.urlText = text;
    }

    /**
     * Returns the content alignment point.
     * 
     * @return The content alignment point (never <code>null</code>).
     * 
     * @since JFreeChart 1.0.13
     */
    public TextBlockAnchor getContentAlignmentPoint() {
        return this.contentAlignmentPoint;
    }

    /**
     * Sets the content alignment point.
     * 
     * @param anchor
     *            the anchor used to determine the alignment point (never
     *            <code>null</code>).
     * 
     * @since JFreeChart 1.0.13
     */
    public void setContentAlignmentPoint(TextBlockAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.contentAlignmentPoint = anchor;
    }

    /**
     * Returns the text anchor (never <code>null</code>).
     * 
     * @return The text anchor.
     * 
     * @since JFreeChart 1.0.13
     */
    public RectangleAnchor getTextAnchor() {
        return this.textAnchor;
    }

    /**
     * Sets the text anchor.
     * 
     * @param anchor
     *            the anchor (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.13
     */
    public void setTextAnchor(RectangleAnchor anchor) {
        this.textAnchor = anchor;
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
        // canvas.setFont(this.font);
        Size2D s = this.label.calculateDimensions(canvas);
        return new Size2D(calculateTotalWidth(s.getWidth()),
                calculateTotalHeight(s.getHeight()));
    }

    /**
     * Draws the block.
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area.
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
     * @return Always <code>null</code>.
     */
    public Object draw(Canvas canvas, RectShape area, Object params) {
        area = trimMargin(area);
        drawBorder(canvas, area);
        area = trimBorder(area);
        area = trimPadding(area);

        // check if we need to collect chart entities from the container
        EntityBlockParams ebp = null;
        StandardEntityCollection sec = null;
        Shape entityArea = null;
        if (params instanceof EntityBlockParams) {
            ebp = (EntityBlockParams) params;
            if (ebp.getGenerateEntities()) {
                sec = new StandardEntityCollection();
                entityArea = (Shape) area.clone();
            }
        }

        PointF pt = RectangleAnchor.coordinates(area, this.textAnchor);
        this.label.draw(canvas, (float) pt.x, (float) pt.y,
                this.contentAlignmentPoint);
        BlockResult result = null;
        if (ebp != null && sec != null) {
            if (this.toolTipText != null || this.urlText != null) {
                ChartEntity entity = new ChartEntity(entityArea,
                        this.toolTipText, this.urlText);
                sec.add(entity);
                result = new BlockResult();
                result.setEntityCollection(sec);
            }
        }
        return result;
    }

    /**
     * Returns a clone of this <code>LabelBlock</code> instance.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}
