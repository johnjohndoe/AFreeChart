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
 * -----------------------------------
 * StandardXYSeriesLabelGenerator.java
 * -----------------------------------
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
 * 16-Nov-2004 : Version 1 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 24-Nov-2006 : Fixed equals() method and updated API docs (DG);
 * 31-Mar-2008 : Added hashCode() method to appease FindBugs (DG);
 *
 */

package org.afree.chart.labels;

import java.io.Serializable;
import java.text.MessageFormat;


import org.afree.util.PublicCloneable;
import org.afree.chart.HashUtilities;
import org.afree.data.xy.XYDataset;



/**
 * A standard series label generator for plots that use data from
 * an {@link org.afree.data.xy.XYDataset}.
 * <br><br>
 * This class implements <code>PublicCloneable</code> by mistake but we retain
 * this for the sake of backward compatibility.
 */
public class StandardXYSeriesLabelGenerator implements XYSeriesLabelGenerator,
        Cloneable,  PublicCloneable,  Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 1916017081848400024L;

    /** The default item label format. */
    public static final String DEFAULT_LABEL_FORMAT = "{0}";

    /** The format pattern. */
    private String formatPattern;

    /**
     * Creates a default series label generator (uses
     * {@link #DEFAULT_LABEL_FORMAT}).
     */
    public StandardXYSeriesLabelGenerator() {
        this(DEFAULT_LABEL_FORMAT);
    }

    /**
     * Creates a new series label generator.
     *
     * @param format  the format pattern (<code>null</code> not permitted).
     */
    public StandardXYSeriesLabelGenerator(String format) {
        if (format == null) {
            throw new IllegalArgumentException("Null 'format' argument.");
        }
        this.formatPattern = format;
    }

    /**
     * Generates a label for the specified series.  This label will be
     * used for the chart legend.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series.
     *
     * @return A series label.
     */
    public String generateLabel(XYDataset dataset, int series) {
        if (dataset == null) {
            throw new IllegalArgumentException("Null 'dataset' argument.");
        }
        String label = MessageFormat.format(
            this.formatPattern, createItemArray(dataset, series)
        );
        return label;
    }

    /**
     * Creates the array of items that can be passed to the
     * {@link MessageFormat} class for creating labels.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series (zero-based index).
     *
     * @return The items (never <code>null</code>).
     */
    protected Object[] createItemArray(XYDataset dataset, int series) {
        Object[] result = new Object[1];
        result[0] = dataset.getSeriesKey(series).toString();
        return result;
    }

    /**
     * Returns an independent copy of the generator.  This is unnecessary,
     * because instances are immutable anyway, but we retain this
     * behaviour for backwards compatibility.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }  
    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the other object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardXYSeriesLabelGenerator)) {
            return false;
        }
        StandardXYSeriesLabelGenerator that
                = (StandardXYSeriesLabelGenerator) obj;
        if (!this.formatPattern.equals(that.formatPattern)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = 127;
        result = HashUtilities.hashCode(result, this.formatPattern);
        return result;
    }

}
