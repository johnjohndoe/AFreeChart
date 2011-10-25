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
 * MarkerAxisBand.java
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
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2000-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Sep-2002 : Updated Javadoc comments (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 13-May-2003 : Renamed HorizontalMarkerAxisBand --> MarkerAxisBand (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 07-Apr-2004 : Changed text bounds calculation (DG);
 *
 */

package org.afree.chart.axis;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.afree.util.ObjectUtilities;
import org.afree.ui.RectangleEdge;
import org.afree.chart.plot.IntervalMarker;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * A band that can be added to a number axis to display regions.
 */
public class MarkerAxisBand implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -1729482413886398919L;

    /** The axis that the band belongs to. */
    private NumberAxis axis;

    /** The top outer gap. */
    private double topOuterGap;

    /** The top inner gap. */
    private double topInnerGap;

    /** The bottom outer gap. */
    private double bottomOuterGap;

    /** The bottom inner gap. */
    private double bottomInnerGap;

    /** The font. */
    private Font font;

    /** Storage for the markers. */
    private List markers;

    /**
     * Constructs a new axis band.
     * 
     * @param axis
     *            the owner.
     * @param topOuterGap
     *            the top outer gap.
     * @param topInnerGap
     *            the top inner gap.
     * @param bottomOuterGap
     *            the bottom outer gap.
     * @param bottomInnerGap
     *            the bottom inner gap.
     * @param font
     *            the font.
     */
    public MarkerAxisBand(NumberAxis axis, double topOuterGap,
            double topInnerGap, double bottomOuterGap, double bottomInnerGap,
            Font font) {
        this.axis = axis;
        this.topOuterGap = topOuterGap;
        this.topInnerGap = topInnerGap;
        this.bottomOuterGap = bottomOuterGap;
        this.bottomInnerGap = bottomInnerGap;
        this.font = font;
        this.markers = new java.util.ArrayList();
    }

    /**
     * Adds a marker to the band.
     * 
     * @param marker
     *            the marker.
     */
    public void addMarker(IntervalMarker marker) {
        this.markers.add(marker);
    }

    /**
     * Returns the height of the band.
     * 
     * @param canvas
     *            the graphics device.
     * 
     * @return The height of the band.
     */
    public double getHeight(Canvas canvas) {

        double result = 0.0;
        if (this.markers.size() > 0) {
            
            Paint p = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, new SolidColor(Color.BLACK), this.font);

            RectShape rec = TextUtilities.getTextBounds("123g", p);
            result = this.topOuterGap + this.topInnerGap + rec.getHeight()
                    + this.bottomInnerGap + this.bottomOuterGap;
        }
        return result;

    }

    /**
     * A utility method that draws a string inside a RectShape.
     * 
     * @param canvas
     *            the graphics device.
     * @param bounds
     *            the RectShape.
     * @param font
     *            the font.
     * @param text
     *            the text.
     */
    private void drawStringInRect(Canvas canvas, RectShape bounds, PaintType paintType, Font font,
            String text) {

        Paint p = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, paintType, this.font);

        RectShape r = TextUtilities.getTextBounds(text, p);
        double x = bounds.getX();
        if (r.getWidth() < bounds.getWidth()) {
            x = x + (bounds.getWidth() - r.getWidth()) / 2;
        }

        canvas.drawText(text, (float) x,
                (float) (bounds.getMaxY() - this.bottomInnerGap), p);
    }

    /**
     * Draws the band.
     * 
     * @param canvas
     *            the graphics device.
     * @param plotArea
     *            the plot area.
     * @param dataArea
     *            the data area.
     * @param x
     *            the x-coordinate.
     * @param y
     *            the y-coordinate.
     */
    public void draw(Canvas canvas, RectShape plotArea, RectShape dataArea,
            double x, double y) {

        double h = getHeight(canvas);
        Iterator iterator = this.markers.iterator();
        while (iterator.hasNext()) {
            IntervalMarker marker = (IntervalMarker) iterator.next();
            double start = Math.max(marker.getStartValue(), this.axis
                    .getRange().getLowerBound());
            double end = Math.min(marker.getEndValue(), this.axis.getRange()
                    .getUpperBound());
            double s = this.axis.valueToJava2D(start, dataArea,
                    RectangleEdge.BOTTOM);
            double e = this.axis.valueToJava2D(end, dataArea,
                    RectangleEdge.BOTTOM);
            RectShape r = new RectShape(s, y + this.topOuterGap, e
                    - s, h - this.topOuterGap - this.bottomOuterGap);

            Paint p = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, marker.getPaintType());
            
            p.setStyle(Paint.Style.FILL);
            canvas.drawRect((float) r.getMinX(), (float) r.getMinY(), (float) r
                    .getMaxX(), (float) r.getMaxY(), p);

            PaintUtility.updatePaint(p, marker.getOutlinePaintType());
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect((float) r.getMinX(), (float) r.getMinY(), (float) r
                    .getMaxX(), (float) r.getMaxY(), p);
            p.setAlpha(255);

            drawStringInRect(canvas, r, marker.getLabelPaintType(), this.font, marker.getLabel());
        }

    }
    
    /**
     * Tests this axis for equality with another object.  Note that the axis
     * that the band belongs to is ignored in the test.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MarkerAxisBand)) {
            return false;
        }
        MarkerAxisBand that = (MarkerAxisBand) obj;
        if (this.topOuterGap != that.topOuterGap) {
            return false;
        }
        if (this.topInnerGap != that.topInnerGap) {
            return false;
        }
        if (this.bottomInnerGap != that.bottomInnerGap) {
            return false;
        }
        if (this.bottomOuterGap != that.bottomOuterGap) {
            return false;
        }
        if (!ObjectUtilities.equal(this.font, that.font)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.markers, that.markers)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for the object.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = 37;
        result = 19 * result + this.font.hashCode();
        result = 19 * result + this.markers.hashCode();
        return result;
    }
    

}
