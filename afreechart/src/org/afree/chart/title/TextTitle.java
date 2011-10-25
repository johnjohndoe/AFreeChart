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
 * --------------
 * TextTitle.java
 * --------------
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
 * (C) Copyright 2000-2009, by David Berry and Contributors.
 *
 * Original Author:  David Berry;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Nicolas Brodu;
 *                   Peter Kolb - patch 2603321;
 *
 * Changes (from 18-Sep-2001)
 * --------------------------
 * 18-Sep-2001 : Added standard header (DG);
 * 07-Nov-2001 : Separated the JCommon Class Library classes, JFreeChart now
 *               requires jcommon.jar (DG);
 * 09-Jan-2002 : Updated Javadoc comments (DG);
 * 07-Feb-2002 : Changed Insets --> Spacer in AbstractTitle.java (DG);
 * 06-Mar-2002 : Updated import statements (DG);
 * 25-Jun-2002 : Removed redundant imports (DG);
 * 18-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 28-Oct-2002 : Small modifications while changing JFreeChart class (DG);
 * 13-Mar-2003 : Changed width used for relative spacing to fix bug 703050 (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 15-Jul-2003 : Fixed null pointer exception (DG);
 * 11-Sep-2003 : Implemented Cloneable (NB)
 * 22-Sep-2003 : Added checks for null values and throw nullpointer
 *               exceptions (TM);
 *               Background paint was not serialized.
 * 07-Oct-2003 : Added fix for exception caused by empty string in title (DG);
 * 29-Oct-2003 : Added workaround for text alignment in PDF output (DG);
 * 03-Feb-2004 : Fixed bug in getPreferredWidth() method (DG);
 * 17-Feb-2004 : Added clone() method and fixed bug in equals() method (DG);
 * 01-Apr-2004 : Changed java.awt.geom.Dimension2D to org.jfree.ui.Size2D
 *               because of JDK bug 4976448 which persists on JDK 1.3.1.  Also
 *               fixed bug in getPreferredHeight() method (DG);
 * 29-Apr-2004 : Fixed bug in getPreferredWidth() method - see bug id
 *               944173 (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0
 *               release (DG);
 * 08-Feb-2005 : Updated for changes in RectangleConstraint class (DG);
 * 11-Feb-2005 : Implemented PublicCloneable (DG);
 * 20-Apr-2005 : Added support for tooltips (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 06-Jun-2005 : Modified equals() to handle GradientPaint (DG);
 * 06-Jul-2005 : Added flag to control whether or not the title expands to
 *               fit the available space (DG);
 * 07-Oct-2005 : Added textAlignment attribute (DG);
 * ------------- JFREECHART 1.0.x RELEASED ------------------------------------
 * 13-Dec-2005 : Fixed bug 1379331 - incorrect drawing with LEFT or RIGHT
 *               title placement (DG);
 * 19-Dec-2007 : Implemented some of the missing arrangement options (DG);
 * 28-Apr-2008 : Added option for maximum lines, and fixed minor bugs in
 *               equals() method (DG);
 * 19-Mar-2009 : Changed ChartEntity to TitleEntity - see patch 2603321 by
 *               Peter Kolb (DG);
 *
 */

package org.afree.chart.title;

import java.io.Serializable;

import org.afree.ui.HorizontalAlignment;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.ui.Size2D;
import org.afree.ui.VerticalAlignment;
import org.afree.chart.block.BlockResult;
import org.afree.chart.block.EntityBlockParams;
import org.afree.chart.block.LengthConstraintType;
import org.afree.chart.block.RectangleConstraint;
import org.afree.data.Range;
import org.afree.chart.entity.ChartEntity;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.entity.StandardEntityCollection;
import org.afree.chart.entity.TitleEntity;
import org.afree.chart.event.TitleChangeEvent;
import org.afree.chart.text.G2TextMeasurer;
import org.afree.chart.text.TextBlock;
import org.afree.chart.text.TextBlockAnchor;
import org.afree.chart.text.TextUtilities;
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
 * A chart title that displays a text string with automatic wrapping as
 * required.
 */
public class TextTitle extends Title implements Serializable, Cloneable {

    public static final PaintType paintBlack = new SolidColor(Color.argb(255, 0, 0, 0));

    /** For serialization. */
    private static final long serialVersionUID = 8372008692127477443L;

    /** The default font. */
    public static final Font DEFAULT_FONT = new Font("SansSerif",
            Typeface.BOLD, 12);

    /** The default text color. */
    public static final PaintType DEFAULT_TEXT_PAINT_TYPE = paintBlack;

    /** The title text. */
    private String text;

    /** The font used to display the title. */
    private Font font;

    /** The text alignment. */
    private HorizontalAlignment textAlignment;

    /** The paint used to display the title text. */
    private transient PaintType paintType;

    /** The background paint. */
    private transient Paint backgroundPaint;

    /** The tool tip text (can be <code>null</code>). */
    private String toolTipText;

    /** The URL text (can be <code>null</code>). */
    private String urlText;

    /** The content. */
    private TextBlock content;

    /**
     * A flag that controls whether the title expands to fit the available
     * space..
     */
    private boolean expandToFitSpace = false;

    /**
     * The maximum number of lines to display.
     * 
     * @since JFreeChart 1.0.10
     */
    private int maximumLinesToDisplay = Integer.MAX_VALUE;

    /**
     * Creates a new title, using default attributes where necessary.
     */
    public TextTitle() {
        this("");
    }

    /**
     * Creates a new title, using default attributes where necessary.
     * 
     * @param text
     *            the title text (<code>null</code> not permitted).
     */
    public TextTitle(String text) {
        this(text, TextTitle.DEFAULT_FONT, TextTitle.DEFAULT_TEXT_PAINT_TYPE,
                Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
    }

    /**
     * Creates a new title, using default attributes where necessary.
     * 
     * @param text
     *            the title text (<code>null</code> not permitted).
     * @param font
     *            the title font (<code>null</code> not permitted).
     */
    public TextTitle(String text, Font font) {
        this(text, font, TextTitle.DEFAULT_TEXT_PAINT_TYPE, Title.DEFAULT_POSITION,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
    }

    /**
     * Creates a new title.
     * 
     * @param text
     *            the text for the title (<code>null</code> not permitted).
     * @param font
     *            the title font (<code>null</code> not permitted).
     * @param paintType
     *            the title paint (<code>null</code> not permitted).
     * @param position
     *            the title position (<code>null</code> not permitted).
     * @param horizontalAlignment
     *            the horizontal alignment (<code>null</code> not permitted).
     * @param verticalAlignment
     *            the vertical alignment (<code>null</code> not permitted).
     * @param padding
     *            the space to leave around the outside of the title.
     */
    public TextTitle(String text, Font font, PaintType paintType,
            RectangleEdge position, HorizontalAlignment horizontalAlignment,
            VerticalAlignment verticalAlignment, RectangleInsets padding) {

        super(position, horizontalAlignment, verticalAlignment, padding);

        if (text == null) {
            throw new NullPointerException("Null 'text' argument.");
        }
        if (font == null) {
            throw new NullPointerException("Null 'font' argument.");
        }
        if (paintType == null) {
            throw new NullPointerException("Null 'paint' argument.");
        }
        this.text = text;
        this.font = font;
        this.paintType = paintType;
        // the textAlignment and the horizontalAlignment are separate things,
        // but it makes sense for the default textAlignment to match the
        // title's horizontal alignment...
        this.textAlignment = horizontalAlignment;
        this.backgroundPaint = null;
        this.content = null;
        this.toolTipText = null;
        this.urlText = null;
        
    }

    /**
     * Returns the title text.
     * 
     * @return The text (never <code>null</code>).
     * 
     * @see #setText(String)
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets the title to the specified text and sends a {@link TitleChangeEvent}
     * to all registered listeners.
     * 
     * @param text
     *            the text (<code>null</code> not permitted).
     */
    public void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");
        }
        if (!this.text.equals(text)) {
            this.text = text;
            notifyListeners(new TitleChangeEvent(this));
        }
    }

    /**
     * Returns the text alignment. This controls how the text is aligned within
     * the title's bounds, whereas the title's horizontal alignment controls how
     * the title's bounding RectShape is aligned within the drawing space.
     * 
     * @return The text alignment.
     */
    public HorizontalAlignment getTextAlignment() {
        return this.textAlignment;
    }

    /**
     * Sets the text alignment and sends a {@link TitleChangeEvent} to all
     * registered listeners.
     * 
     * @param alignment
     *            the alignment (<code>null</code> not permitted).
     */
    public void setTextAlignment(HorizontalAlignment alignment) {
        if (alignment == null) {
            throw new IllegalArgumentException("Null 'alignment' argument.");
        }
        this.textAlignment = alignment;
        notifyListeners(new TitleChangeEvent(this));
    }

    /**
     * Returns the font used to display the title string.
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setFont(Font)
     */
    public Font getFont() {
        return this.font;
    }

    /**
     * Sets the font used to display the title string. Registered listeners are
     * notified that the title has been modified.
     * 
     * @param font
     *            the new font (<code>null</code> not permitted).
     * 
     * @see #getFont()
     */
    public void setFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        if (!this.font.equals(font)) {
            this.font = font;
            notifyListeners(new TitleChangeEvent(this));
        }
    }

    /**
     * Returns the paint used to display the title string.
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setPaintType(PaintType)
     */
    public PaintType getPaintType() {
        return this.paintType;
    }

    /**
     * Sets the paint used to display the title string. Registered listeners are
     * notified that the title has been modified.
     * 
     * @param paintType
     *            the new paint (<code>null</code> not permitted).
     * 
     * @see #getPaintType()
     */
    public void setPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        if (!this.paintType.equals(paintType)) {
            this.paintType = paintType;
            notifyListeners(new TitleChangeEvent(this));
        }
    }

    /**
     * Returns the background paint.
     * 
     * @return The paint (possibly <code>null</code>).
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }

    /**
     * Sets the background paint and sends a {@link TitleChangeEvent} to all
     * registered listeners. If you set this attribute to <code>null</code>, no
     * background is painted (which makes the title background transparent).
     * 
     * @param paint
     *            the background paint (<code>null</code> permitted).
     */
    public void setBackgroundPaint(Paint paint) {
        this.backgroundPaint = paint;
        notifyListeners(new TitleChangeEvent(this));
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
     * Sets the tool tip text to the specified text and sends a
     * {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     */
    public void setToolTipText(String text) {
        this.toolTipText = text;
        notifyListeners(new TitleChangeEvent(this));
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
     * Sets the URL text to the specified text and sends a
     * {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     */
    public void setURLText(String text) {
        this.urlText = text;
        notifyListeners(new TitleChangeEvent(this));
    }

    /**
     * Returns the flag that controls whether or not the title expands to fit
     * the available space.
     * 
     * @return The flag.
     */
    public boolean getExpandToFitSpace() {
        return this.expandToFitSpace;
    }

    /**
     * Sets the flag that controls whether the title expands to fit the
     * available space, and sends a {@link TitleChangeEvent} to all registered
     * listeners.
     * 
     * @param expand
     *            the flag.
     */
    public void setExpandToFitSpace(boolean expand) {
        this.expandToFitSpace = expand;
        notifyListeners(new TitleChangeEvent(this));
    }

    /**
     * Returns the maximum number of lines to display.
     * 
     * @return The maximum.
     * 
     * @since JFreeChart 1.0.10
     * 
     * @see #setMaximumLinesToDisplay(int)
     */
    public int getMaximumLinesToDisplay() {
        return this.maximumLinesToDisplay;
    }

    /**
     * Sets the maximum number of lines to display and sends a
     * {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param max
     *            the maximum.
     * 
     * @since JFreeChart 1.0.10.
     * 
     * @see #getMaximumLinesToDisplay()
     */
    public void setMaximumLinesToDisplay(int max) {
        this.maximumLinesToDisplay = max;
        notifyListeners(new TitleChangeEvent(this));
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
        RectangleConstraint cc = toContentConstraint(constraint);
        LengthConstraintType w = cc.getWidthConstraintType();
        LengthConstraintType h = cc.getHeightConstraintType();
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
                contentSize = arrangeRN(canvas, cc.getWidthRange());
            } else if (h == LengthConstraintType.RANGE) {
                contentSize = arrangeRR(canvas, cc.getWidthRange(), cc
                        .getHeightRange());
            } else if (h == LengthConstraintType.FIXED) {
                throw new RuntimeException("Not yet implemented.");
            }
        } else if (w == LengthConstraintType.FIXED) {
            if (h == LengthConstraintType.NONE) {
                contentSize = arrangeFN(canvas, cc.getWidth());
            } else if (h == LengthConstraintType.RANGE) {
                throw new RuntimeException("Not yet implemented.");
            } else if (h == LengthConstraintType.FIXED) {
                throw new RuntimeException("Not yet implemented.");
            }
        }
        return new Size2D(calculateTotalWidth(contentSize.getWidth()),
                calculateTotalHeight(contentSize.getHeight()));
    }

    /**
     * Arranges the content for this title assuming no bounds on the width or
     * the height, and returns the required size. This will reflect the fact
     * that a text title positioned on the left or right of a chart will be
     * rotated by 90 degrees.
     * 
     * @param canvas
     *            the graphics target.
     * 
     * @return The content size.
     * 
     * @since JFreeChart 1.0.9
     */
    protected Size2D arrangeNN(Canvas canvas) {
        Range max = new Range(0.0, Float.MAX_VALUE);
        return arrangeRR(canvas, max, max);
    }

    /**
     * Arranges the content for this title assuming a fixed width and no bounds
     * on the height, and returns the required size. This will reflect the fact
     * that a text title positioned on the left or right of a chart will be
     * rotated by 90 degrees.
     * 
     * @param canvas
     *            the graphics target.
     * @param w
     *            the width.
     * 
     * @return The content size.
     * 
     * @since JFreeChart 1.0.9
     */
    protected Size2D arrangeFN(Canvas canvas, double w) {
        RectangleEdge position = getPosition();
        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, this.paintType, this.font);

        if (position == RectangleEdge.TOP || position == RectangleEdge.BOTTOM) {
            float maxWidth = (float) w;
            
            this.content = TextUtilities.createTextBlock(this.text, this.font,
                    this.paintType, maxWidth, this.maximumLinesToDisplay,
                    new G2TextMeasurer(paint));
            this.content.setLineAlignment(this.textAlignment);
            Size2D contentSize = this.content.calculateDimensions(canvas);
            if (this.expandToFitSpace) {
                return new Size2D(maxWidth, contentSize.getHeight());
            } else {
                return contentSize;
            }
        } else if (position == RectangleEdge.LEFT
                || position == RectangleEdge.RIGHT) {
            float maxWidth = Float.MAX_VALUE;

            this.content = TextUtilities.createTextBlock(this.text, this.font,
                    this.paintType, maxWidth, this.maximumLinesToDisplay,
                    new G2TextMeasurer(paint));
            this.content.setLineAlignment(this.textAlignment);
            Size2D contentSize = this.content.calculateDimensions(canvas);

            // transpose the dimensions, because the title is rotated
            if (this.expandToFitSpace) {
                return new Size2D(contentSize.getHeight(), maxWidth);
            } else {
                return new Size2D(contentSize.height, contentSize.width);
            }
        } else {
            throw new RuntimeException("Unrecognised exception.");
        }
    }

    /**
     * Arranges the content for this title assuming a range constraint for the
     * width and no bounds on the height, and returns the required size. This
     * will reflect the fact that a text title positioned on the left or right
     * of a chart will be rotated by 90 degrees.
     * 
     * @param canvas
     *            the graphics target.
     * @param widthRange
     *            the range for the width.
     * 
     * @return The content size.
     * 
     * @since JFreeChart 1.0.9
     */
    protected Size2D arrangeRN(Canvas canvas, Range widthRange) {
        Size2D s = arrangeNN(canvas);
        if (widthRange.contains(s.getWidth())) {
            return s;
        }
        double ww = widthRange.constrain(s.getWidth());
        return arrangeFN(canvas, ww);
    }

    /**
     * Returns the content size for the title. This will reflect the fact that a
     * text title positioned on the left or right of a chart will be rotated 90
     * degrees.
     * 
     * @param canvas
     *            the graphics device.
     * @param widthRange
     *            the width range.
     * @param heightRange
     *            the height range.
     * 
     * @return The content size.
     */
    protected Size2D arrangeRR(Canvas canvas, Range widthRange, Range heightRange) {
        RectangleEdge position = getPosition();
        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, this.paintType, this.font);

        if (position == RectangleEdge.TOP || position == RectangleEdge.BOTTOM) {
            float maxWidth = (float) widthRange.getUpperBound();
            // canvas.setFont(this.font);

            this.content = TextUtilities.createTextBlock(this.text, this.font,
                    this.paintType, maxWidth, this.maximumLinesToDisplay,
                    new G2TextMeasurer(paint));
            this.content.setLineAlignment(this.textAlignment);
            Size2D contentSize = this.content.calculateDimensions(canvas);
            if (this.expandToFitSpace) {
                return new Size2D(maxWidth, contentSize.getHeight());
            } else {
                return contentSize;
            }
        } else if (position == RectangleEdge.LEFT
                || position == RectangleEdge.RIGHT) {
            float maxWidth = (float) heightRange.getUpperBound();
            // canvas.setFont(this.font);
            this.content = TextUtilities.createTextBlock(this.text, this.font,
                    this.paintType, maxWidth, this.maximumLinesToDisplay,
                    new G2TextMeasurer(paint));
            this.content.setLineAlignment(this.textAlignment);
            Size2D contentSize = this.content.calculateDimensions(canvas);

            // transpose the dimensions, because the title is rotated
            if (this.expandToFitSpace) {
                return new Size2D(contentSize.getHeight(), maxWidth);
            } else {
                return new Size2D(contentSize.height, contentSize.width);
            }
        } else {
            throw new RuntimeException("Unrecognised exception.");
        }
    }

    /**
     * Draws the title on a graphics device (such as the screen or a
     * printer).
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area allocated for the title.
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
     *            if this is an instance of {@link EntityBlockParams} it is used
     *            to determine whether or not an {@link EntityCollection} is
     *            returned by this method.
     * 
     * @return An {@link EntityCollection} containing a chart entity for the
     *         title, or <code>null</code>.
     */
    public Object draw(Canvas canvas, RectShape area, Object params) {
        if (this.content == null) {
            return null;
        }
        area = trimMargin(area);
        drawBorder(canvas, area);
        if (this.text.equals("")) {
            return null;
        }
        ChartEntity entity = null;
        if (params instanceof EntityBlockParams) {
            EntityBlockParams p = (EntityBlockParams) params;
            if (p.getGenerateEntities()) {
                entity = new TitleEntity(area, this, this.toolTipText,
                        this.urlText);
            }
        }
        area = trimBorder(area);
        if (this.backgroundPaint != null) {
            
            // canvas.setPaint(this.backgroundPaint);
            // canvas.fill(area);
            area.fill(canvas, this.backgroundPaint);
        }
        area = trimPadding(area);
        RectangleEdge position = getPosition();
        if (position == RectangleEdge.TOP || position == RectangleEdge.BOTTOM) {
            drawHorizontal(canvas, area);
        } else if (position == RectangleEdge.LEFT
                || position == RectangleEdge.RIGHT) {
            drawVertical(canvas, area);
        }
        BlockResult result = new BlockResult();
        if (entity != null) {
            StandardEntityCollection sec = new StandardEntityCollection();
            sec.add(entity);
            result.setEntityCollection(sec);
        }
        return result;
    }

    /**
     * Draws a the title horizontally within the specified area. This method
     * will be called from the {@link #draw(Graphics2D, RectShape) draw}
     * method.
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area for the title.
     */
    protected void drawHorizontal(Canvas canvas, RectShape area) {
        RectShape titleArea = (RectShape) area.clone();
        // canvas.setFont(this.font);
        TextBlockAnchor anchor = null;
        float x = 0.0f;
        HorizontalAlignment horizontalAlignment = getHorizontalAlignment();
        if (horizontalAlignment == HorizontalAlignment.LEFT) {
            x = (float) titleArea.getX();
            anchor = TextBlockAnchor.TOP_LEFT;
        } else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
            x = (float) titleArea.getMaxX();
            anchor = TextBlockAnchor.TOP_RIGHT;
        } else if (horizontalAlignment == HorizontalAlignment.CENTER) {
            x = (float) titleArea.getCenterX();
            anchor = TextBlockAnchor.TOP_CENTER;
        }
        float y = 0.0f;
        RectangleEdge position = getPosition();
        if (position == RectangleEdge.TOP) {
            y = (float) titleArea.getY();
        } else if (position == RectangleEdge.BOTTOM) {
            y = (float) titleArea.getMaxY();
            if (horizontalAlignment == HorizontalAlignment.LEFT) {
                anchor = TextBlockAnchor.BOTTOM_LEFT;
            } else if (horizontalAlignment == HorizontalAlignment.CENTER) {
                anchor = TextBlockAnchor.BOTTOM_CENTER;
            } else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
                anchor = TextBlockAnchor.BOTTOM_RIGHT;
            }
        }
        this.content.draw(canvas, x, y, anchor);
    }

    /**
     * Draws a the title vertically within the specified area. This method will
     * be called from the {@link #draw(Graphics2D, RectShape) draw} method.
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area for the title.
     */
    protected void drawVertical(Canvas canvas, RectShape area) {
        RectShape titleArea = (RectShape) area.clone();
        // canvas.setFont(this.font);
        // canvas.setPaint(this.paint);
        TextBlockAnchor anchor = null;
        float y = 0.0f;
        VerticalAlignment verticalAlignment = getVerticalAlignment();
        if (verticalAlignment == VerticalAlignment.TOP) {
            y = (float) titleArea.getY();
            anchor = TextBlockAnchor.TOP_RIGHT;
        } else if (verticalAlignment == VerticalAlignment.BOTTOM) {
            y = (float) titleArea.getMaxY();
            anchor = TextBlockAnchor.TOP_LEFT;
        } else if (verticalAlignment == VerticalAlignment.CENTER) {
            y = (float) titleArea.getCenterY();
            anchor = TextBlockAnchor.TOP_CENTER;
        }
        float x = 0.0f;
        RectangleEdge position = getPosition();
        if (position == RectangleEdge.LEFT) {
            x = (float) titleArea.getX();
        } else if (position == RectangleEdge.RIGHT) {
            x = (float) titleArea.getMaxX();
            if (verticalAlignment == VerticalAlignment.TOP) {
                anchor = TextBlockAnchor.BOTTOM_RIGHT;
            } else if (verticalAlignment == VerticalAlignment.CENTER) {
                anchor = TextBlockAnchor.BOTTOM_CENTER;
            } else if (verticalAlignment == VerticalAlignment.BOTTOM) {
                anchor = TextBlockAnchor.BOTTOM_LEFT;
            }
        }
        this.content.draw(canvas, x, y, anchor, x, y, -Math.PI / 2.0);
    }

    /**
     * Returns a hash code.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result + (this.text != null ? this.text.hashCode() : 0);
        result = 29 * result + (this.font != null ? this.font.hashCode() : 0);
        result = 29 * result + (this.paintType != null ? this.paintType.hashCode() : 0);
        result = 29 * result + (this.backgroundPaint != null
                ? this.backgroundPaint.hashCode() : 0);
        return result;
    }

    /**
     * Returns a clone of this object.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException never.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    
}
