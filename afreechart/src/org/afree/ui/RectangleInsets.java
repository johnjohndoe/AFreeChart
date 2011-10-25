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
 * --------------------
 * RectangleInsets.java
 * --------------------
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
 * 14-Jan-2011 : Updated API docs
 *  
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 *
 * Changes:
 * --------
 * 11-Feb-2004 (DG);
 * 
 */

package org.afree.ui;


import org.afree.graphics.geom.RectShape;
import org.afree.util.UnitType;


/**
 * Represents the insets for a RectShape, specified in absolute or relative
 * terms. This class is immutable.
 */
public class RectangleInsets {

    public static final RectangleInsets ZERO_INSETS = new RectangleInsets(
            UnitType.ABSOLUTE, 0.0, 0.0, 0.0, 0.0);

    /** Absolute or relative units. */
    private UnitType unitType;

    /** The top insets. */
    private double top;

    /** The bottom insets. */
    private double bottom;

    /** The left insets. */
    private double left;

    /** The right insets. */
    private double right;

    public RectangleInsets(final double top, final double left,
            final double bottom, final double right) {
        this(UnitType.ABSOLUTE, top, left, bottom, right);
    }

    /**
     * Creates a new instance.
     * 
     * @param unitType
     *            absolute or relative units (<code>null</code> not permitted).
     * @param top
     *            the top insets.
     * @param bottom
     *            the bottom insets.
     * @param left
     *            the left insets.
     * @param right
     *            the right insets.
     */
    public RectangleInsets(final UnitType unitType, final double top,
            final double left, final double bottom, final double right) {
        if (unitType == null) {
            throw new IllegalArgumentException("Null 'unitType' argument.");
        }
        this.unitType = unitType;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the unit type (absolute or relative). This specifies whether the
     * insets are measured as Java2D units or percentages.
     * 
     * @return The unit type (never <code>null</code>).
     */
    public UnitType getUnitType() {
        return this.unitType;
    }

    /*
     * Shrinks the given RectShape by the amount of these insets.
     * 
     * @param area the area (<code>null</code> not permitted).
     */
    public void trim(final RectShape area) {
        final double w = area.getWidth();
        final double h = area.getHeight();
        final double l = calculateLeftInset(w);
        final double r = calculateRightInset(w);
        final double t = calculateTopInset(h);
        final double b = calculateBottomInset(h);
        area.setRect(area.getX() + l, area.getY() + t, w - l - r, h - t - b);
    }

    public double calculateBottomInset(final double height) {
        double result = this.bottom;
        if (this.unitType == UnitType.RELATIVE) {
            result = (this.bottom * height);
        }
        return result;
    }

    public double calculateLeftInset(final double width) {
        double result = this.left;
        if (this.unitType == UnitType.RELATIVE) {
            result = (this.left * width);
        }
        return result;
    }

    public double calculateRightInset(final double width) {
        double result = this.right;
        if (this.unitType == UnitType.RELATIVE) {
            result = (this.right * width);
        }
        return result;
    }

    public double calculateTopInset(final double height) {
        double result = this.top;
        if (this.unitType == UnitType.RELATIVE) {
            result = (this.top * height);
        }
        return result;
    }

    /**
     * Returns the top insets.
     * 
     * @return The top insets.
     */
    public double getTop() {
        return this.top;
    }

    /**
     * Returns the bottom insets.
     * 
     * @return The bottom insets.
     */
    public double getBottom() {
        return this.bottom;
    }

    /**
     * Returns the left insets.
     * 
     * @return The left insets.
     */
    public double getLeft() {
        return this.left;
    }

    /**
     * Returns the right insets.
     * 
     * @return The right insets.
     */
    public double getRight() {
        return this.right;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;   
        }
        if (!(obj instanceof RectangleInsets)) {
                return false;
        }
        final RectangleInsets that = (RectangleInsets) obj;
        if (that.unitType != this.unitType) {
            return false;   
        }
        if (this.left != that.left) {
            return false;   
        }
        if (this.right != that.right) {
            return false;   
        }
        if (this.top != that.top) {
            return false;   
        }
        if (this.bottom != that.bottom) {
            return false;   
        }
        return true;   
    }

    /**
     * Creates an 'inset' RectShape.
     * 
     * @param base
     *            the base RectShape (<code>null</code> not permitted).
     * 
     * @return The inset RectShape.
     */
    public RectShape createInsetRectShape(final RectShape base) {
        return createInsetRectShape(base, true, true);
    }

    /**
     * Creates an 'inset' RectShape.
     * 
     * @param base
     *            the base RectShape (<code>null</code> not permitted).
     * @param horizontal
     *            apply horizontal insets?
     * @param vertical
     *            apply vertical insets?
     * 
     * @return The inset RectShape.
     */
    public RectShape createInsetRectShape(final RectShape base,
            final boolean horizontal, final boolean vertical) {
        if (base == null) {
            throw new IllegalArgumentException("Null 'base' argument.");
        }
        double topMargin = 0.0;
        double bottomMargin = 0.0;
        if (vertical) {
            topMargin = calculateTopMargin(base.getHeight());
            bottomMargin = calculateBottomMargin(base.getHeight());
        }
        double leftMargin = 0.0;
        double rightMargin = 0.0;
        if (horizontal) {
            leftMargin = calculateLeftMargin(base.getWidth());
            rightMargin = calculateRightMargin(base.getWidth());
        }
        return new RectShape(base.getX() + leftMargin, base.getY()
                + topMargin, base.getWidth() - leftMargin - rightMargin, base
                .getHeight()
                - topMargin - bottomMargin);
    }

    /**
     * Returns the top margin.
     * 
     * @param height
     *            the height of the base RectShape.
     * 
     * @return The top margin (in Canvas units).
     */
    public double calculateTopMargin(final double height) {
        double result = this.top;
        if (this.unitType == UnitType.RELATIVE) {
            result = (this.top * height);
        }
        return result;
    }

    /**
     * Returns the bottom margin.
     * 
     * @param height
     *            the height of the base RectShape.
     * 
     * @return The bottom margin (in Canvas units).
     */
    public double calculateBottomMargin(final double height) {
        double result = this.bottom;
        if (this.unitType == UnitType.RELATIVE) {
            result = (this.bottom * height);
        }
        return result;
    }

    /**
     * Returns the left margin.
     * 
     * @param width
     *            the width of the base RectShape.
     * 
     * @return The left margin (in Canvas units).
     */
    public double calculateLeftMargin(final double width) {
        double result = this.left;
        if (this.unitType == UnitType.RELATIVE) {
            result = (this.left * width);
        }
        return result;
    }

    /**
     * Returns the right margin.
     * 
     * @param width
     *            the width of the base RectShape.
     * 
     * @return The right margin (in Canvas units).
     */
    public double calculateRightMargin(final double width) {
        double result = this.right;
        if (this.unitType == UnitType.RELATIVE) {
            result = (this.right * width);
        }
        return result;
    }

    public double trimWidth(final double width) {
        return width - calculateLeftInset(width) - calculateRightInset(width);
    }

    /**
     * Extends the given width to allow for the insets.
     * 
     * @param width
     *            the width.
     * 
     * @return The extended width.
     */
    public double extendWidth(final double width) {
        return width + calculateLeftOutset(width) + calculateRightOutset(width);
    }

    /**
     * Trims the given height to allow for the insets.
     * 
     * @param height
     *            the height.
     * 
     * @return The trimmed height.
     */
    public double trimHeight(final double height) {
        return height - calculateTopInset(height)
                - calculateBottomInset(height);
    }

    /**
     * Extends the given height to allow for the insets.
     * 
     * @param height
     *            the height.
     * 
     * @return The extended height.
     */
    public double extendHeight(final double height) {
        return height + calculateTopOutset(height)
                + calculateBottomOutset(height);
    }

    public double calculateRightOutset(final double width) {
        double result = this.right;
        if (this.unitType == UnitType.RELATIVE) {
            result = (width / (1 - this.left - this.right)) * this.right;
        }
        return result;
    }

    public double calculateLeftOutset(final double width) {
        double result = this.left;
        if (this.unitType == UnitType.RELATIVE) {
            result = (width / (1 - this.left - this.right)) * this.left;
        }
        return result;
    }

    public double calculateBottomOutset(final double height) {
        double result = this.bottom;
        if (this.unitType == UnitType.RELATIVE) {
            result = (height / (1 - this.top - this.bottom)) * this.bottom;
        }
        return result;
    }

    public double calculateTopOutset(final double height) {
        double result = this.top;
        if (this.unitType == UnitType.RELATIVE) {
            result = (height / (1 - this.top - this.bottom)) * this.top;
        }
        return result;
    }

    /**
     * Creates an outset RectShape.
     * 
     * @param base
     *            the base RectShape (<code>null</code> not permitted).
     * @param horizontal
     *            apply horizontal insets?
     * @param vertical
     *            apply vertical insets?
     * 
     * @return An outset RectShape.
     */
    public RectShape createOutsetRectangle(final RectShape base,
            final boolean horizontal, final boolean vertical) {
        if (base == null) {
            throw new IllegalArgumentException("Null 'base' argument.");
        }
        double topMargin = 0.0;
        double bottomMargin = 0.0;
        if (vertical) {
            topMargin = calculateTopOutset(base.getHeight());
            bottomMargin = calculateBottomOutset(base.getHeight());
        }
        double leftMargin = 0.0;
        double rightMargin = 0.0;
        if (horizontal) {
            leftMargin = calculateLeftOutset(base.getWidth());
            rightMargin = calculateRightOutset(base.getWidth());
        }
        return new RectShape(base.getX() - leftMargin, base.getY()
                - topMargin, base.getWidth() + leftMargin + rightMargin, base
                .getHeight()
                + topMargin + bottomMargin);
    }
    
    /**
     * Creates an outset rectangle.
     * 
     * @param base
     *            the base rectangle (<code>null</code> not permitted).
     * 
     * @return An outset rectangle.
     */
    public RectShape createOutsetRectangle(final RectShape base) {
        return createOutsetRectangle(base, true, true);
    }

    /**
     * Creates an adjusted RectShape using the supplied RectShape, the insets
     * specified by this instance, and the horizontal and vertical adjustment
     * types.
     * 
     * @param base
     *            the base RectShape (<code>null</code> not permitted).
     * @param horizontal
     *            the horizontal adjustment type (<code>null</code> not
     *            permitted).
     * @param vertical
     *            the vertical adjustment type (<code>null</code> not
     *            permitted).
     * 
     * @return The inset RectShape.
     */
    public RectShape createAdjustedRectShape(final RectShape base,
            final LengthAdjustmentType horizontal,
            final LengthAdjustmentType vertical) {
        if (base == null) {
            throw new IllegalArgumentException("Null 'base' argument.");
        }
        double x = base.getX();
        double y = base.getY();
        double w = base.getWidth();
        double h = base.getHeight();
        if (horizontal == LengthAdjustmentType.EXPAND) {
            final double leftOutset = calculateLeftOutset(w);
            x = x - leftOutset;
            w = w + leftOutset + calculateRightOutset(w);
        } else if (horizontal == LengthAdjustmentType.CONTRACT) {
            final double leftMargin = calculateLeftInset(w);
            x = x + leftMargin;
            w = w - leftMargin - calculateRightInset(w);
        }
        if (vertical == LengthAdjustmentType.EXPAND) {
            final double topMargin = calculateTopOutset(h);
            y = y - topMargin;
            h = h + topMargin + calculateBottomOutset(h);
        } else if (vertical == LengthAdjustmentType.CONTRACT) {
            final double topMargin = calculateTopInset(h);
            y = y + topMargin;
            h = h - topMargin - calculateBottomInset(h);
        }
        return new RectShape(x, y, w, h);
    }

}
