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
 * DeviationRendererDemo02View.java
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
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.DeviationRenderer;
import org.afree.data.time.Hour;
import org.afree.data.time.RegularTimePeriod;
import org.afree.data.xy.XYDataset;
import org.afree.data.xy.YIntervalSeries;
import org.afree.data.xy.YIntervalSeriesCollection;
import org.afree.graphics.SolidColor;
import org.afree.chart.demo.DemoView;

import android.content.Context;
import android.graphics.Color;

/**
 * DeviationRendererDemo02View
 */
public class DeviationRendererDemo02View extends DemoView {

    /**
     * constructor
     * @param context
     */
    public DeviationRendererDemo02View(Context context) {
        super(context);

        final AFreeChart chart = createChart();

        setChart(chart);
    }

    /**
     * Creates a sample dataset.
     * @return a sample dataset.
     */
    private static XYDataset createDataset() {

        YIntervalSeries series1 = new YIntervalSeries("Series 1");
        YIntervalSeries series2 = new YIntervalSeries("Series 2");
        RegularTimePeriod t = new Hour();
        double y1 = 50.0;
        double y2 = 50.0;
        for (int i = 0; i <= 52; i++) {
            double dev1 = (0.05 * i);
            series1.add(t.getFirstMillisecond(), y1, y1 - dev1, y1 + dev1);
            y1 = y1 + Math.random() - 0.45;

            double dev2 = (0.07 * i);
            series2.add(t.getFirstMillisecond(), y2, y2 - dev2, y2 + dev2);
            y2 = y2 + Math.random() - 0.55;
            t = t.next();
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    /**
     * Creates a chart.
     * @param dataset the data for the chart.
     * @return a chart.
     */
    private static AFreeChart createChart() {
        XYDataset dataset = createDataset();
        // create the chart...
        AFreeChart chart = ChartFactory.createTimeSeriesChart(
        		"Deviation Renderer Demo 02", // chart title
                "Time", // x axis label
                "Value", // y axis label
                dataset, // data
                true, // include legend
                true, // tooltips
                false // urls
                );

        XYPlot plot = (XYPlot) chart.getPlot();

        DeviationRenderer renderer = new DeviationRenderer(true, false);
        renderer.setSeriesStroke(0, 3.0f);
        renderer.setSeriesStroke(1, 3.0f);

        renderer.setSeriesFillPaintType(0, new SolidColor(Color.rgb(255, 200, 200)));
        renderer.setSeriesFillPaintType(1, new SolidColor(Color.rgb(200, 200, 255)));
        plot.setRenderer(renderer);

        return chart;
    }

}
