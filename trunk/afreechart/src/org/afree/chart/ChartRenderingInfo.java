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
 * -----------------------
 * ChartRenderingInfo.java
 * -----------------------
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
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 22-Jan-2002 : Version 1 (DG);
 * 05-Feb-2002 : Added a new constructor, completed Javadoc comments (DG);
 * 05-Mar-2002 : Added a clear() method (DG);
 * 23-May-2002 : Renamed DrawInfo --> ChartRenderingInfo (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 17-Sep-2003 : Added PlotRenderingInfo (DG);
 * 01-Nov-2005 : Updated equals() method (DG);
 * 30-Nov-2005 : Removed get/setPlotArea() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Dec-2006 : Fixed equals() and clone() (DG);
 *
 */

package org.afree.chart;

import java.io.Serializable;

import org.afree.util.ObjectUtilities;
import org.afree.util.PublicCloneable;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.entity.StandardEntityCollection;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.graphics.geom.RectShape;

/**
 * A structure for storing rendering information from one call to the
 * AFreeChart.draw() method.
 * <P>
 * An instance of the {@link AFreeChart} class can draw itself within an
 * arbitrary RectShape on any <code>Graphics2D</code>. It is assumed that client
 * code will sometimes render the same chart in more than one view, so the
 * {@link AFreeChart} instance does not retain any information about its
 * rendered dimensions. This information can be useful sometimes, so you have
 * the option to collect the information at each call to
 * <code>AFreeChart.draw()</code>, by passing an instance of this
 * <code>ChartRenderingInfo</code> class.
 */
public class ChartRenderingInfo implements Cloneable,PublicCloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 2751952018173406822L;

    /** The area in which the chart is drawn. */
    private transient RectShape chartArea;

    /** Rendering info for the chart's plot (and subplots, if any). */
    private PlotRenderingInfo plotInfo;

    /**
     * Storage for the chart entities. Since retaining entity information for
     * charts with a large number of data points consumes a lot of memory, it is
     * intended that you can set this to <code>null</code> to prevent the
     * information being collected.
     */
    private EntityCollection entities;

    /**
     * Constructs a new ChartRenderingInfo structure that can be used to collect
     * information about the dimensions of a rendered chart.
     */
    public ChartRenderingInfo() {
        this(new StandardEntityCollection());
    }

    /**
     * Constructs a new instance. If an entity collection is supplied, it will
     * be populated with information about the entities in a chart. If it is
     * <code>null</code>, no entity information (including tool tips) will be
     * collected.
     * 
     * @param entities
     *            an entity collection (<code>null</code> permitted).
     */
    public ChartRenderingInfo(EntityCollection entities) {
        this.chartArea = new RectShape();
        this.plotInfo = new PlotRenderingInfo(this);
        this.entities = entities;
    }

    /**
     * Returns the area in which the chart was drawn.
     * 
     * @return The area in which the chart was drawn.
     * 
     * @see #setChartArea(RectShape)
     */
    public RectShape getChartArea() {
        return this.chartArea;
    }

    /**
     * Sets the area in which the chart was drawn.
     * 
     * @param area
     *            the chart area.
     * 
     * @see #getChartArea()
     */
    public void setChartArea(RectShape area) {
        this.chartArea.setRect(area);
    }

    /**
     * Returns the collection of entities maintained by this instance.
     * 
     * @return The entity collection (possibly <code>null</code>).
     * 
     * @see #setEntityCollection(EntityCollection)
     */
    public EntityCollection getEntityCollection() {
        return this.entities;
    }

    /**
     * Sets the entity collection.
     * 
     * @param entities
     *            the entity collection (<code>null</code> permitted).
     * 
     * @see #getEntityCollection()
     */
    public void setEntityCollection(EntityCollection entities) {
        this.entities = entities;
    }

    /**
     * Clears the information recorded by this object.
     */
    public void clear() {
        this.chartArea.setRect(0.0, 0.0, 0.0, 0.0);
        this.plotInfo = new PlotRenderingInfo(this);
        if (this.entities != null) {
            this.entities.clear();
        }
    }

    /**
     * Returns the rendering info for the chart's plot.
     * 
     * @return The rendering info for the plot.
     */
    public PlotRenderingInfo getPlotInfo() {
        return this.plotInfo;
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the object to test against (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChartRenderingInfo)) {
            return false;
        }
        ChartRenderingInfo that = (ChartRenderingInfo) obj;
        if (!ObjectUtilities.equal(this.chartArea, that.chartArea)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.plotInfo, that.plotInfo)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.entities, that.entities)) {
            return false;
        }
        return true;
    }    
    
    /**
     * Returns a clone of this object.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if the object cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        ChartRenderingInfo clone = (ChartRenderingInfo) super.clone();
        if (this.chartArea != null) {
            clone.chartArea = (RectShape) this.chartArea.clone();
        }
        if (this.entities instanceof PublicCloneable) {
            PublicCloneable pc = (PublicCloneable) this.entities;
            clone.entities = (EntityCollection) pc.clone();
        }
        return clone;
    }    
    
}
