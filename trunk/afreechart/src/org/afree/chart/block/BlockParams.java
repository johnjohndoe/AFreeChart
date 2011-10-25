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
 * ----------------
 * BlockParams.java
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
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2005-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 19-Apr-2005 : Version 1 (DG);
 *
 */

package org.afree.chart.block;

/**
 * A standard parameter object that can be passed to the draw() method defined
 * by the {@link Block} class.
 */
public class BlockParams implements EntityBlockParams {

    /**
     * A flag that controls whether or not the block should generate and return
     * chart entities for the items it draws.
     */
    private boolean generateEntities;

    /**
     * The x-translation (used to enable chart entities to use global
     * coordinates rather than coordinates that are local to the container they
     * are within).
     */
    private double translateX;

    /**
     * The y-translation (used to enable chart entities to use global
     * coordinates rather than coordinates that are local to the container they
     * are within).
     */
    private double translateY;

    /**
     * Creates a new instance.
     */
    public BlockParams() {
        this.translateX = 0.0;
        this.translateY = 0.0;
        this.generateEntities = false;
    }

    /**
     * Returns the flag that controls whether or not chart entities are
     * generated.
     * 
     * @return A boolean.
     */
    public boolean getGenerateEntities() {
        return this.generateEntities;
    }

    /**
     * Sets the flag that controls whether or not chart entities are generated.
     * 
     * @param generate
     *            the flag.
     */
    public void setGenerateEntities(boolean generate) {
        this.generateEntities = generate;
    }

    /**
     * Returns the translation required to convert local x-coordinates back to
     * the coordinate space of the container.
     * 
     * @return The x-translation amount.
     */
    public double getTranslateX() {
        return this.translateX;
    }

    /**
     * Sets the translation required to convert local x-coordinates into the
     * coordinate space of the container.
     * 
     * @param x
     *            the x-translation amount.
     */
    public void setTranslateX(double x) {
        this.translateX = x;
    }

    /**
     * Returns the translation required to convert local y-coordinates back to
     * the coordinate space of the container.
     * 
     * @return The y-translation amount.
     */
    public double getTranslateY() {
        return this.translateY;
    }

    /**
     * Sets the translation required to convert local y-coordinates into the
     * coordinate space of the container.
     * 
     * @param y
     *            the y-translation amount.
     */
    public void setTranslateY(double y) {
        this.translateY = y;
    }

}
