/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
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
 * -------------
 * TextLine.java
 * -------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 *
 * Changes
 * -------
 * 07-Nov-2003 : Version 1 (DG);
 * 22-Dec-2003 : Added workaround for Java bug 4245442 (DG);
 * 29-Jan-2004 : Added new constructor (DG);
 * 22-Mar-2004 : Added equals() method and implemented Serializable (DG);
 * 01-Apr-2004 : Changed java.awt.geom.Dimension2D to org.jfree.ui.Size2D because of 
 *               JDK bug 4976448 which persists on JDK 1.3.1 (DG);
 *
 */

package org.afree.chart.text;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.afree.ui.Size2D;
import org.afree.ui.TextAnchor;
import org.afree.graphics.geom.Font;
import org.afree.graphics.PaintType;
import android.graphics.Canvas;

/**
 * A sequence of {@link TextFragment} objects that together form a line of text.
 * A sequence of text lines is managed by the {@link TextBlock} class.
 */
public class TextLine implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3649650025605980613L;
    /** Storage for the text fragments that make up the line. */
    private List fragments;

    /**
     * Creates a new empty line.
     */
    public TextLine() {
        this.fragments = new java.util.ArrayList();
    }

    /**
     * Creates a new text line using the default font.
     * 
     * @param text
     *            the text (<code>null</code> not permitted).
     */
    public TextLine(final String text) {
        this(text, TextFragment.DEFAULT_FONT);
    }

    /**
     * Creates a new text line.
     * 
     * @param text
     *            the text (<code>null</code> not permitted).
     * @param font
     *            the text font (<code>null</code> not permitted).
     */
    public TextLine(final String text, final Font font) {
        this.fragments = new java.util.ArrayList();
        final TextFragment fragment = new TextFragment(text, font);
        this.fragments.add(fragment);
    }

    /**
     * Creates a new text line.
     * 
     * @param text
     *            the text (<code>null</code> not permitted).
     * @param font
     *            the text font (<code>null</code> not permitted).
     * @param paintType
     *            the text color (<code>null</code> not permitted).
     */
    public TextLine(final String text, final Font font, final PaintType paintType) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");
        }
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.fragments = new java.util.ArrayList();
        final TextFragment fragment = new TextFragment(text, font, paintType);
        this.fragments.add(fragment);
    }

    /**
     * Adds a text fragment to the text line.
     * 
     * @param fragment
     *            the text fragment (<code>null</code> not permitted).
     */
    public void addFragment(final TextFragment fragment) {
        this.fragments.add(fragment);
    }

    /**
     * Draws the text line.
     * 
     * @param canvas
     *            the graphics device.
     * @param anchorX
     *            the x-coordinate for the anchor point.
     * @param anchorY
     *            the y-coordinate for the anchor point.
     * @param anchor
     *            the point on the text line that is aligned to the anchor
     *            point.
     * @param rotateX
     *            the x-coordinate for the rotation point.
     * @param rotateY
     *            the y-coordinate for the rotation point.
     * @param angle
     *            the rotation angle (in radians).
     */
    public void draw(final Canvas canvas, final float anchorX, final float anchorY,
            final TextAnchor anchor, final float rotateX, final float rotateY,
            final double angle) {

        float x = anchorX;
        final float yOffset = calculateBaselineOffset(anchor);
        final Iterator iterator = this.fragments.iterator();
        while (iterator.hasNext()) {
            final TextFragment fragment = (TextFragment) iterator.next();
            final Size2D d = fragment.calculateDimensions(canvas);
            fragment.draw(canvas, x, anchorY + yOffset, TextAnchor.BASELINE_LEFT,
                    rotateX, rotateY, angle);
            x = x + (float) d.getWidth();
        }

    }

    /**
     * Calculates the width and height of the text line.
     * 
     * @param canvas
     *            the graphics device.
     * 
     * @return The width and height.
     */
    public Size2D calculateDimensions(final Canvas canvas) {
        double width = 0.0;
        double height = 0.0;
        final Iterator iterator = this.fragments.iterator();
        while (iterator.hasNext()) {
            final TextFragment fragment = (TextFragment) iterator.next();
            final Size2D dimension = fragment.calculateDimensions(canvas);
            width = width + dimension.getWidth();
            height = Math.max(height, dimension.getHeight());

        }
        return new Size2D(width, height);
    }

    /**
     * Returns the first text fragment in the line.
     * 
     * @return the first text fragment in the line.
     */
    public TextFragment getFirstTextFragment() {
        TextFragment result = null;
        if (this.fragments.size() > 0) {
            result = (TextFragment) this.fragments.get(0);
        }
        return result;
    }

    /**
     * Calculate the offsets required to translate from the specified anchor
     * position to the left baseline position.
     * 
     * @param canvas
     *            the graphics device.
     * @param anchor
     *            the anchor position.
     * 
     * @return the offsets.
     */
    private float calculateBaselineOffset(final TextAnchor anchor) {
        float result = 0.0f;
        final TextFragment fragment = getFirstTextFragment();
        if (fragment != null) {
            result = fragment.calculateBaselineOffset(anchor);
        }
        return result;
    }

    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj
     *            the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextLine) {
            final TextLine line = (TextLine) obj;
            return this.fragments.equals(line.fragments);
        }
        return false;
    }

}
