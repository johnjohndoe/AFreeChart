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
 * -----------------
 * PiePlotState.java
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
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Mar-2004 : Version 1 (DG);
 *
 */

package org.afree.chart.plot;


import org.afree.chart.renderer.RendererState;
import org.afree.graphics.geom.RectShape;



/**
 * A renderer state.
 */
public class PiePlotState extends RendererState {

    /** The number of passes required by the renderer. */
    private int passesRequired;

    /** The total of the values in the dataset. */
    private double total;

    /** The latest angle. */
    private double latestAngle;

    /** The exploded pie area. */
    private RectShape explodedPieArea;

    /** The pie area. */
    private RectShape pieArea;

    /** The center of the pie in Java 2D coordinates. */
    private double pieCenterX;

    /** The center of the pie in Java 2D coordinates. */
    private double pieCenterY;

    /** The vertical pie radius. */
    private double pieHRadius;

    /** The horizontal pie radius. */
    private double pieWRadius;

    /** The link area. */
    private RectShape linkArea;

    /**
     * Creates a new object for recording temporary state information for a
     * renderer.
     *
     * @param info  the plot rendering info.
     */
    public PiePlotState(PlotRenderingInfo info) {
        super(info);
        this.passesRequired = 1;
        this.total = 0.0;
    }

    /**
     * Returns the number of passes required by the renderer.
     *
     * @return The number of passes.
     */
    public int getPassesRequired() {
        return this.passesRequired;
    }

    /**
     * Sets the number of passes required by the renderer.
     *
     * @param passes  the passes.
     */
    public void setPassesRequired(int passes) {
        this.passesRequired = passes;
    }

    /**
     * Returns the total of the values in the dataset.
     *
     * @return The total.
     */
    public double getTotal() {
        return this.total;
    }

    /**
     * Sets the total.
     *
     * @param total  the total.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Returns the latest angle.
     *
     * @return The latest angle.
     */
    public double getLatestAngle() {
        return this.latestAngle;
    }

    /**
     * Sets the latest angle.
     *
     * @param angle  the angle.
     */
    public void setLatestAngle(double angle) {
        this.latestAngle = angle;
    }

    /**
     * Returns the pie area.
     *
     * @return The pie area.
     */
    public RectShape getPieArea() {
        return this.pieArea;
    }

    /**
     * Sets the pie area.
     *
     * @param area  the area.
     */
    public void setPieArea(RectShape area) {
       this.pieArea = area;
    }

    /**
     * Returns the exploded pie area.
     *
     * @return The exploded pie area.
     */
    public RectShape getExplodedPieArea() {
        return this.explodedPieArea;
    }

    /**
     * Sets the exploded pie area.
     *
     * @param area  the area.
     */
    public void setExplodedPieArea(RectShape area) {
        this.explodedPieArea = area;
    }

    /**
     * Returns the x-coordinate of the center of the pie chart.
     *
     * @return The x-coordinate (in Java2D space).
     */
    public double getPieCenterX() {
        return this.pieCenterX;
    }

    /**
     * Sets the x-coordinate of the center of the pie chart.
     *
     * @param x  the x-coordinate (in Java2D space).
     */
    public void setPieCenterX(double x) {
        this.pieCenterX = x;
    }

    /**
     * Returns the y-coordinate (in Java2D space) of the center of the pie
     * chart.  For the {@link PiePlot3D} class, we derive this from the top of
     * the pie.
     *
     * @return The y-coordinate (in Java2D space).
     */
    public double getPieCenterY() {
        return this.pieCenterY;
    }

    /**
     * Sets the y-coordinate of the center of the pie chart.  This method is
     * used by the plot and typically is not called directly by applications.
     *
     * @param y  the y-coordinate (in Java2D space).
     */
    public void setPieCenterY(double y) {
        this.pieCenterY = y;
    }

    /**
     * Returns the link area.  This defines the "dog-leg" point for the label
     * linking lines.
     *
     * @return The link area.
     */
    public RectShape getLinkArea() {
        return this.linkArea;
    }

    /**
     * Sets the label link area.  This defines the "dog-leg" point for the
     * label linking lines.
     *
     * @param area  the area.
     */
    public void setLinkArea(RectShape area) {
        this.linkArea = area;
    }

    /**
     * Returns the vertical pie radius.
     *
     * @return The radius.
     */
    public double getPieHRadius() {
        return this.pieHRadius;
    }

    /**
     * Sets the vertical pie radius.
     *
     * @param radius  the radius.
     */
    public void setPieHRadius(double radius) {
        this.pieHRadius = radius;
    }

    /**
     * Returns the horizontal pie radius.
     *
     * @return The radius.
     */
    public double getPieWRadius() {
        return this.pieWRadius;
    }

    /**
     * Sets the horizontal pie radius.
     *
     * @param radius  the radius.
     */
    public void setPieWRadius(double radius) {
        this.pieWRadius = radius;
    }

}
