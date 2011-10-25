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
 * -------------------
 * CompositeTitle.java
 * -------------------
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
 * (C) Copyright 2005-2008, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Eric Penfold (patch 2006826);
 *
 * Changes
 * -------
 * 19-Nov-2004 : Version 1 (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 04-Feb-2005 : Implemented MAXIMUM_WIDTH in calculateSize (DG);
 * 20-Apr-2005 : Added new draw() method (DG);
 * 03-May-2005 : Implemented equals() method (DG);
 * 02-Jul-2008 : Applied patch 2006826 by Eric Penfold, to enable chart
 *               entities to be generated (DG);
 * 09-Jul-2008 : Added backgroundPaint field (DG);
 *
 */

package org.afree.chart.title;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.afree.io.SerialUtilities;
import org.afree.ui.Size2D;
import org.afree.chart.block.BlockContainer;
import org.afree.chart.block.BorderArrangement;
import org.afree.chart.block.RectangleConstraint;
import org.afree.chart.event.TitleChangeEvent;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;

import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * A title that contains multiple titles within a {@link BlockContainer}.
 */
public class CompositeTitle extends Title implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -6770854036232562290L;

    /**
     * The background paint.
     *
     * @since JFreeChart 1.0.11
     */
    private transient PaintType backgroundPaintType;

    /** A container for the individual titles. */
    private BlockContainer container;

    /**
     * Creates a new composite title with a default border arrangement.
     */
    public CompositeTitle() {
        this(new BlockContainer(new BorderArrangement()));
    }

    /**
     * Creates a new title using the specified container.
     *
     * @param container  the container (<code>null</code> not permitted).
     */
    public CompositeTitle(BlockContainer container) {
        if (container == null) {
            throw new IllegalArgumentException("Null 'container' argument.");
        }
        this.container = container;
        this.backgroundPaintType = null;
    }

    /**
     * Returns the background paint.
     *
     * @return The paint type (possibly <code>null</code>).
     *
     * @since JFreeChart 1.0.11
     */
    public PaintType getBackgroundPaintType() {
        return this.backgroundPaintType;
    }

    /**
     * Sets the background paint and sends a {@link TitleChangeEvent} to all
     * registered listeners.  If you set this attribute to <code>null</code>,
     * no background is painted (which makes the title background transparent).
     *
     * @param paintType  the background paint (<code>null</code> permitted).
     *
     * @since JFreeChart 1.0.11
     */
    public void setBackgroundPaintType(PaintType paintType) {
        this.backgroundPaintType = paintType;
        notifyListeners(new TitleChangeEvent(this));
    }

    /**
     * Returns the container holding the titles.
     *
     * @return The title container (never <code>null</code>).
     */
    public BlockContainer getContainer() {
        return this.container;
    }

    /**
     * Sets the title container.
     *
     * @param container  the container (<code>null</code> not permitted).
     */
    public void setTitleContainer(BlockContainer container) {
        if (container == null) {
            throw new IllegalArgumentException("Null 'container' argument.");
        }
        this.container = container;
    }

    /**
     * Arranges the contents of the block, within the given constraints, and
     * returns the block size.
     *
     * @param canvas  the graphics device.
     * @param constraint  the constraint (<code>null</code> not permitted).
     *
     * @return The block size (in Canvas units, never <code>null</code>).
     */
    public Size2D arrange(Canvas canvas, RectangleConstraint constraint) {
        RectangleConstraint contentConstraint = toContentConstraint(constraint);
        Size2D contentSize = this.container.arrange(canvas, contentConstraint);
        return new Size2D(calculateTotalWidth(contentSize.getWidth()),
                calculateTotalHeight(contentSize.getHeight()));
    }

    /**
     * Draws the title on a graphics device (such as the screen or a
     * printer).
     *
     * @param canvas  the graphics device.
     * @param area  the area allocated for the title.
     */
    public void draw(Canvas canvas, RectShape area) {
        draw(canvas, area, null);
    }

    /**
     * Draws the block within the specified area.
     *
     * @param canvas  the graphics device.
     * @param area  the area.
     * @param params  ignored (<code>null</code> permitted).
     *
     * @return Always <code>null</code>.
     */
    public Object draw(Canvas canvas, RectShape area, Object params) {
        area = trimMargin(area);
        drawBorder(canvas, area);
        area = trimBorder(area);
        if (this.backgroundPaintType != null) {
            Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, backgroundPaintType);
            area.fill(canvas, paint);
        }
        area = trimPadding(area);
        return this.container.draw(canvas, area, params);
    }

    /**
     * Tests this title for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CompositeTitle)) {
            return false;
        }
        CompositeTitle that = (CompositeTitle) obj;
        if (!this.container.equals(that.container)) {
            return false;
        }
        if (!this.backgroundPaintType.equals(that.backgroundPaintType)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaintType(this.backgroundPaintType, stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.backgroundPaintType = SerialUtilities.readPaintType(stream);
    }

}
