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
 * MarkerDemo01View.java
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

import java.text.SimpleDateFormat;

import org.afree.chart.ChartFactory;
import org.afree.chart.AFreeChart;
import org.afree.chart.axis.PeriodAxis;
import org.afree.chart.axis.PeriodAxisLabelInfo;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.IntervalMarker;
import org.afree.chart.plot.Marker;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.ValueMarker;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.XYItemRenderer;
import org.afree.data.time.Day;
import org.afree.data.time.Hour;
import org.afree.data.time.TimeSeries;
import org.afree.data.time.TimeSeriesCollection;
import org.afree.data.xy.XYDataset;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import org.afree.ui.Layer;
import org.afree.ui.LengthAdjustmentType;
import org.afree.ui.RectangleInsets;
import org.afree.chart.demo.DemoView;

import android.content.Context;
import android.graphics.Color;

public class MarkerDemo01View extends DemoView {

    /**
     * constructor
     * @param context
     */
    public MarkerDemo01View(Context context) {
        super(context);

        final AFreeChart chart = createChart();

        setChart(chart);
    }

    /**
     * Creates a sample chart.
     * @param data the sample data.
     * @return A configured chart.
     */
    private static AFreeChart createChart() {
        XYDataset data = createDataset();
        AFreeChart chart = ChartFactory.createXYLineChart(
                "Marker Demo 01",
                "Time",
                "Value",
                data,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);

        XYPlot plot = (XYPlot) chart.getPlot();
        ValueAxis valueAxis = plot.getRangeAxis();
        PeriodAxisLabelInfo[] info = new PeriodAxisLabelInfo[2];

        info[0] = new PeriodAxisLabelInfo(Hour.class, new SimpleDateFormat("HH"),
        		new RectangleInsets(0, 0, 25, 0), valueAxis.getLabelFont(),
        		valueAxis.getAxisLinePaintType(), true, valueAxis.getAxisLineStroke(),
        		valueAxis.getAxisLinePaintType());
        info[1] = new PeriodAxisLabelInfo(Day.class, new SimpleDateFormat("dd-MMM"),
        		new RectangleInsets(0, 0, 0, 0), valueAxis.getLabelFont(),
        		valueAxis.getLabelPaintType(), false, valueAxis.getAxisLineStroke(),
        		valueAxis.getLabelPaintType());

        PeriodAxis domainAxis = new PeriodAxis(null, new Hour(0, 18, 10, 2011),
        		new Hour(23, 18, 10, 2011));

        domainAxis.setLabelInfo(info);
        plot.setDomainAxis(domainAxis);

        PaintType paintRed = new SolidColor(Color.RED);
        PaintType paintGreen = new SolidColor(Color.GREEN);

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setRange(0, 100);

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaintType(0, paintGreen);
        renderer.setSeriesStroke(0, 2.0f);

        // add a marker
        Marker marker_H = new ValueMarker(50.0);
        marker_H.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        marker_H.setPaintType(paintRed);
        marker_H.setStroke(2.0f);
        plot.addRangeMarker(marker_H);

        // add range marker
        Hour hour1 = new Hour(11, 18, 10, 2011);
        Hour hour2 = new Hour(13, 18, 10, 2011);
        double millis1 = hour1.getFirstMillisecond();
        double millis2 = hour2.getFirstMillisecond();
        Marker marker_V = new IntervalMarker(millis1, millis2);
        marker_V.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        marker_V.setPaintType(new SolidColor(Color.rgb(150, 150, 255)));
        plot.addDomainMarker(marker_V, Layer.BACKGROUND);

        Marker marker_V_Start = new ValueMarker(millis1, Color.BLUE, 2.0f);
        Marker marker_V_End = new ValueMarker(millis2, Color.BLUE, 2.0f);
        plot.addDomainMarker(marker_V_Start, Layer.BACKGROUND);
        plot.addDomainMarker(marker_V_End, Layer.BACKGROUND);
        return chart;
    }

    /**
     * Returns a sample dataset.
     * @return A sample dataset.
     */
    private static XYDataset createDataset() {
        TimeSeriesCollection result = new TimeSeriesCollection();
        TimeSeries series1 = new TimeSeries("Value");

        for(int i = 0; i <= 23; i++) {
        	series1.add(new Hour(i, 18, 10, 2011), Math.random() * 50 + 40);
        }

        result.addSeries(series1);
        return result;
    }

}
