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
 * ScatterPlotDemo03View.java
 * -----------------
 * (C) Copyright 2011, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  Yamakami Souichirou (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 18-Oct-2011 : Added new sample code (SY);
 */

package org.afree.chart.demo.view;

import org.afree.chart.ChartFactory;
import org.afree.chart.AFreeChart;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.XYPlot;
import org.afree.data.xy.XYDataset;
import org.afree.data.xy.XYSeries;
import org.afree.data.xy.XYSeriesCollection;
import org.afree.chart.demo.DemoView;

import android.content.Context;

/**
 * ScatterPlotDemo03View
 */
public class ScatterPlotDemo03View extends DemoView {

    /**
     * constructor
     * @param context
     */
    public ScatterPlotDemo03View(Context context) {
        super(context);

        final AFreeChart chart = createChart();
        setChart(chart);
    }

    private static AFreeChart createChart() {
        XYDataset dataset = createDataset();
        AFreeChart chart = ChartFactory.createScatterPlot(
                "Scatter Plot Demo 03",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setDomainCrosshairLockedOnData(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRangeCrosshairLockedOnData(true);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        return chart;
    }

    /**
     * Creates a dataset.
     * @return a dataset.
     */
    private static XYSeriesCollection createDataset() {

    	XYSeries xyS1 = new XYSeries("Sample 1", true, false);
    	XYSeries xyS2 = new XYSeries("Sample 2", true, false);
    	XYSeries xyS3 = new XYSeries("Sample 3", true, false);

    	for(int i = -99; i < 100; i++) {
    		xyS1.add(i, i * Math.random() * 10);
    		xyS2.add(i, i * Math.random() * 15);
    		xyS3.add(i, i * Math.random() * 5);
    	}

    	XYSeriesCollection xySC = new XYSeriesCollection();
    	xySC.addSeries(xyS1);
    	xySC.addSeries(xyS2);
    	xySC.addSeries(xyS3);

    	return xySC;
    }

}












