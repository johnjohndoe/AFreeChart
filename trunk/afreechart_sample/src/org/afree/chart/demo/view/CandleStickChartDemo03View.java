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
 * CandleStickChartDemo03View.java
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

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import org.afree.chart.AFreeChart;
import org.afree.chart.annotations.XYTitleAnnotation;
import org.afree.chart.axis.AxisLocation;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.axis.NumberTickUnit;
import org.afree.chart.axis.TickUnitSource;
import org.afree.chart.axis.TickUnits;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.CombinedDomainXYPlot;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.CandlestickRenderer;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.chart.title.LegendTitle;
import org.afree.data.time.Day;
import org.afree.data.time.TimeSeries;
import org.afree.data.time.TimeSeriesCollection;
import org.afree.data.xy.DefaultHighLowDataset;
import org.afree.data.xy.OHLCDataset;
import org.afree.data.xy.XYDataset;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.LineShape;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;

import org.afree.chart.demo.DemoView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

public class CandleStickChartDemo03View extends DemoView {

    public CandleStickChartDemo03View(Context context) {
        super(context);

        final AFreeChart chart = createChart();
        setChart(chart);
    }

    private static AFreeChart createChart() {

        // declare colors
        PaintType black = new SolidColor(Color.BLACK);
        PaintType blue = new SolidColor(Color.BLUE);
        PaintType red = new SolidColor(Color.RED);
        PaintType yellow = new SolidColor(Color.YELLOW);
        PaintType darkGreen = new SolidColor(Color.argb(255, 0, 64, 0));
        PaintType white = new SolidColor(Color.WHITE);
        
        OHLCDataset dataset1 = createDataset1();

        NumberAxis rangeAxis1 = new NumberAxis();
        rangeAxis1.setStandardTickUnits(createTickUnits());
        rangeAxis1.setAutoTickUnitSelection(true);
        rangeAxis1.setAutoRange(true);
        rangeAxis1.setAutoRangeIncludesZero(false);
        rangeAxis1.setAxisLinePaintType(white);
        rangeAxis1.setAxisLineStroke(1);
        rangeAxis1.setTickMarkPaintType(white);
        rangeAxis1.setTickMarkStroke(1);
        rangeAxis1.setTickMarkOutsideLength(2);
        rangeAxis1.setLabelPaintType(white);
        rangeAxis1.setTickLabelPaintType(white);
        rangeAxis1.setTickLabelInsets(new RectangleInsets(10, 0, 10, 0));
        rangeAxis1.setLimitAble(/*true*/false);
        rangeAxis1.setLimitRange(20, 70);

        CandlestickRenderer renderer1 = new CandlestickRenderer();

        renderer1.setUseOutlinePaint(true);
        renderer1.setBaseOutlinePaintType(white);
        renderer1.setBaseOutlineStroke(1.0f);

        renderer1.setUpPaintType(red);
        renderer1.setDownPaintType(blue);

        XYPlot subplot1 = new XYPlot(dataset1, null, rangeAxis1, renderer1);
       
        //overlaid
        XYDataset dataset3 = createDataset2();
        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
        renderer3.setBaseShapesVisible(false);
        renderer3.setSeriesPaintType(0, yellow);
        
        subplot1.setDataset(1, dataset3);
        subplot1.setRenderer(1, renderer3);

        subplot1.setBackgroundPaintType(black);
        subplot1.setDomainGridlinePaintType(darkGreen);
        subplot1.setRangeGridlinePaintType(darkGreen);
        subplot1.setRangeAxisLocation(AxisLocation.TOP_OR_RIGHT);
        subplot1.setOutlineVisible(true);
        subplot1.setOutlinePaintType(white);
        subplot1.setOutlineStroke(2.0f);

        XYDataset dataset2 = createDataset2();
        NumberAxis rangeAxis2 = new NumberAxis();
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setAxisLinePaintType(white);
        rangeAxis2.setAxisLineStroke(1);
        rangeAxis2.setTickMarkPaintType(white);
        rangeAxis2.setTickMarkStroke(1);
        rangeAxis2.setTickMarkOutsideLength(2);
        rangeAxis2.setLabelPaintType(white);
        rangeAxis2.setTickLabelPaintType(white);
        rangeAxis2.setTickLabelInsets(new RectangleInsets(10, 0, 10, 0));
        rangeAxis2.setLimitAble(true);
        rangeAxis2.setLimitRange(0, 100);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setBaseShapesVisible(false);
        renderer2.setLegendLine(new LineShape());

        XYPlot subplot2 = new XYPlot(dataset2, null, rangeAxis2,renderer2);
        subplot2.setDomainGridlinesVisible(true);
        subplot2.setBackgroundPaintType(black);
        subplot2.setDomainGridlinePaintType(darkGreen);
        subplot2.setRangeGridlinePaintType(darkGreen);
        subplot2.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        subplot2.setOutlineVisible(true);
        subplot2.setOutlinePaintType(white);
        subplot2.setOutlineStroke(2.0f);

        // setting domain axis
        ValueAxis timeAxis = new DateAxis("Date");
        timeAxis.setAxisLinePaintType(white);
        timeAxis.setAxisLineStroke(1);

        timeAxis.setTickMarkPaintType(white);
        timeAxis.setTickMarkStroke(1);
        timeAxis.setTickMarkOutsideLength(2);

        timeAxis.setLabelPaintType(white);
        timeAxis.setTickLabelPaintType(white);

        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(timeAxis);
        plot.setBackgroundPaintType(black);
        plot.add(subplot1, 2);
        plot.add(subplot2, 1);

        // add a annotation
        LegendTitle lt = new LegendTitle(subplot2);

        lt.setItemFont(new Font("Dialog", Typeface.BOLD, 9));
        lt.setBackgroundPaintType(new SolidColor(Color.argb(0, 0, 0, 0)));
        lt.setItemPaintType(white);
        lt.setPosition(RectangleEdge.TOP);
        XYTitleAnnotation ta = new XYTitleAnnotation(0.02, 0.98, lt, RectangleAnchor.TOP_LEFT);

        ta.setMaxWidth(0.48);
        subplot2.addAnnotation(ta);

        AFreeChart chart = new AFreeChart(
                "Candle Stick Chart Demo 03",
                AFreeChart.DEFAULT_TITLE_FONT,
                plot,
                false);

        // setting chart
        chart.setBackgroundPaintType(black);

        return chart;
    }

    /**
     * Creates a sample high low dataset.
     * @return a sample high low dataset.
     */
    public static OHLCDataset createDataset1() {

        Date[] date = new Date[47];
        double[] high = new double[47];
        double[] low = new double[47];
        double[] open = new double[47];
        double[] close = new double[47];
        double[] volume = new double[47];

        int jan = 1;
        int feb = 2;

        for(int i = 0; i < 47; i++) {
        	if(i <= 27) {
        		date[i] = createDate(2001, jan, i+4, 12, 0);
        	} else {
        		date[i] = createDate(2001, feb, i-27, 12, 0);
        	}
        	high[i] = 45 + Math.random() * 20;
        	low[i] = high[i] - (Math.random() * 30 + 3);
        	do {
	        	open[i] = high[i] - Math.random() * (high[i] - low[i]);
	        	close[i] = low[i] + Math.random() * (high[i] - low[i]);
        	} while(Math.abs(open[i] - close[i]) < 1);
        }

        return new DefaultHighLowDataset("Series 1", date, high, low, open, close, volume);
    }

    private static final Calendar calendar = Calendar.getInstance();

    /**
     * Returns a date using the default locale and timezone.
     * @param y the year (YYYY).
     * @param m the month (1-12).
     * @param d the day of the month.
     * @param hour the hour of the day.
     * @param min the minute of the hour.
     * @return A date.
     */
    private static Date createDate(int y, int m, int d, int hour, int min) {
        calendar.clear();
        calendar.set(y, m - 1, d, hour, min);
        return calendar.getTime();
    }

    /**
     * Creates a dataset.
     * @return A dataset.
     */
    public static XYDataset createDataset2() {

        TimeSeries s1 = new TimeSeries("MACD");

        for(int i = 0; i < 47; i++) {
        	if(i <= 27) {
        		s1.add(new Day(i + 4, 1, 2001), Math.random() * 30 + 30);
        	} else {
        		s1.add(new Day(i - 27, 2, 2001), Math.random() * 30 + 30);
        	}
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;
    }

    public static TickUnitSource createTickUnits() {

        TickUnits units = new TickUnits();
        DecimalFormat df1 = new DecimalFormat("$0");
        DecimalFormat df2 = new DecimalFormat("$0.00");

        // we can add the units in any order, the TickUnits collection will
        // sort them...
        units.add(new NumberTickUnit(1000000, df1, 0));
        units.add(new NumberTickUnit(500000, df1, 0));
        units.add(new NumberTickUnit(200000, df1, 0));
        units.add(new NumberTickUnit(100000, df1, 0));
        units.add(new NumberTickUnit(50000, df1, 0));
        units.add(new NumberTickUnit(20000, df1, 0));
        units.add(new NumberTickUnit(10000, df1, 0));
        units.add(new NumberTickUnit(5000, df1, 0));
        units.add(new NumberTickUnit(2000, df1, 0));
        units.add(new NumberTickUnit(1000, df1, 0));
        units.add(new NumberTickUnit(500, df1, 0));
        units.add(new NumberTickUnit(200, df1, 0));
        units.add(new NumberTickUnit(100, df1, 0));
        units.add(new NumberTickUnit(50, df1, 0));
        units.add(new NumberTickUnit(20, df1, 0));
        units.add(new NumberTickUnit(10, df1, 0));
        units.add(new NumberTickUnit(5, df1, 0));
        units.add(new NumberTickUnit(2, df1, 0));
        units.add(new NumberTickUnit(1, df1, 0));

        units.add(new NumberTickUnit(0.000001, df2, 0));
        units.add(new NumberTickUnit(0.0000025, df2, 0));
        units.add(new NumberTickUnit(0.000005, df2, 0));
        units.add(new NumberTickUnit(0.00001, df2, 0));
        units.add(new NumberTickUnit(0.000025, df2, 0));
        units.add(new NumberTickUnit(0.00005, df2, 0));
        units.add(new NumberTickUnit(0.0001, df2, 0));
        units.add(new NumberTickUnit(0.00025, df2, 0));
        units.add(new NumberTickUnit(0.0005, df2, 0));
        units.add(new NumberTickUnit(0.001, df2, 0));
        units.add(new NumberTickUnit(0.0025, df2, 0));
        units.add(new NumberTickUnit(0.005, df2, 0));
        units.add(new NumberTickUnit(0.01, df2, 0));
        units.add(new NumberTickUnit(0.025, df2, 0));
        units.add(new NumberTickUnit(0.05, df2, 0));
        units.add(new NumberTickUnit(0.1, df2, 0));
        units.add(new NumberTickUnit(0.25, df2, 0));
        units.add(new NumberTickUnit(0.5, df2, 0));

        return units;
    }

}
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
 * CandleStickChartDemo03View.java
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

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import org.afree.chart.AFreeChart;
import org.afree.chart.annotations.XYTitleAnnotation;
import org.afree.chart.axis.AxisLocation;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.axis.NumberTickUnit;
import org.afree.chart.axis.TickUnitSource;
import org.afree.chart.axis.TickUnits;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.CombinedDomainXYPlot;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.CandlestickRenderer;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.chart.title.LegendTitle;
import org.afree.data.time.Day;
import org.afree.data.time.TimeSeries;
import org.afree.data.time.TimeSeriesCollection;
import org.afree.data.xy.DefaultHighLowDataset;
import org.afree.data.xy.OHLCDataset;
import org.afree.data.xy.XYDataset;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.LineShape;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;

import org.afree.chart.demo.DemoView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

public class CandleStickChartDemo03View extends DemoView {

    public CandleStickChartDemo03View(Context context) {
        super(context);

        final AFreeChart chart = createChart();
        setChart(chart);
    }

    private static AFreeChart createChart() {

        // declare colors
        PaintType black = new SolidColor(Color.BLACK);
        PaintType blue = new SolidColor(Color.BLUE);
        PaintType red = new SolidColor(Color.RED);
        PaintType yellow = new SolidColor(Color.YELLOW);
        PaintType darkGreen = new SolidColor(Color.argb(255, 0, 64, 0));
        PaintType white = new SolidColor(Color.WHITE);
        
        OHLCDataset dataset1 = createDataset1();

        NumberAxis rangeAxis1 = new NumberAxis();
        rangeAxis1.setStandardTickUnits(createTickUnits());
        rangeAxis1.setAutoTickUnitSelection(true);
        rangeAxis1.setAutoRange(true);
        rangeAxis1.setAutoRangeIncludesZero(false);
        rangeAxis1.setAxisLinePaintType(white);
        rangeAxis1.setAxisLineStroke(1);
        rangeAxis1.setTickMarkPaintType(white);
        rangeAxis1.setTickMarkStroke(1);
        rangeAxis1.setTickMarkOutsideLength(2);
        rangeAxis1.setLabelPaintType(white);
        rangeAxis1.setTickLabelPaintType(white);
        rangeAxis1.setTickLabelInsets(new RectangleInsets(10, 0, 10, 0));
        rangeAxis1.setLimitAble(/*true*/false);
        rangeAxis1.setLimitRange(20, 70);

        CandlestickRenderer renderer1 = new CandlestickRenderer();

        renderer1.setUseOutlinePaint(true);
        renderer1.setBaseOutlinePaintType(white);
        renderer1.setBaseOutlineStroke(1.0f);

        renderer1.setUpPaintType(red);
        renderer1.setDownPaintType(blue);

        XYPlot subplot1 = new XYPlot(dataset1, null, rangeAxis1, renderer1);
       
        //overlaid
        XYDataset dataset3 = createDataset2();
        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
        renderer3.setBaseShapesVisible(false);
        renderer3.setSeriesPaintType(0, yellow);
        
        subplot1.setDataset(1, dataset3);
        subplot1.setRenderer(1, renderer3);

        subplot1.setBackgroundPaintType(black);
        subplot1.setDomainGridlinePaintType(darkGreen);
        subplot1.setRangeGridlinePaintType(darkGreen);
        subplot1.setRangeAxisLocation(AxisLocation.TOP_OR_RIGHT);
        subplot1.setOutlineVisible(true);
        subplot1.setOutlinePaintType(white);
        subplot1.setOutlineStroke(2.0f);

        XYDataset dataset2 = createDataset2();
        NumberAxis rangeAxis2 = new NumberAxis();
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setAxisLinePaintType(white);
        rangeAxis2.setAxisLineStroke(1);
        rangeAxis2.setTickMarkPaintType(white);
        rangeAxis2.setTickMarkStroke(1);
        rangeAxis2.setTickMarkOutsideLength(2);
        rangeAxis2.setLabelPaintType(white);
        rangeAxis2.setTickLabelPaintType(white);
        rangeAxis2.setTickLabelInsets(new RectangleInsets(10, 0, 10, 0));
        rangeAxis2.setLimitAble(true);
        rangeAxis2.setLimitRange(0, 100);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setBaseShapesVisible(false);
        renderer2.setLegendLine(new LineShape());

        XYPlot subplot2 = new XYPlot(dataset2, null, rangeAxis2,renderer2);
        subplot2.setDomainGridlinesVisible(true);
        subplot2.setBackgroundPaintType(black);
        subplot2.setDomainGridlinePaintType(darkGreen);
        subplot2.setRangeGridlinePaintType(darkGreen);
        subplot2.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        subplot2.setOutlineVisible(true);
        subplot2.setOutlinePaintType(white);
        subplot2.setOutlineStroke(2.0f);

        // setting domain axis
        ValueAxis timeAxis = new DateAxis("Date");
        timeAxis.setAxisLinePaintType(white);
        timeAxis.setAxisLineStroke(1);

        timeAxis.setTickMarkPaintType(white);
        timeAxis.setTickMarkStroke(1);
        timeAxis.setTickMarkOutsideLength(2);

        timeAxis.setLabelPaintType(white);
        timeAxis.setTickLabelPaintType(white);

        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(timeAxis);
        plot.setBackgroundPaintType(black);
        plot.add(subplot1, 2);
        plot.add(subplot2, 1);

        // add a annotation
        LegendTitle lt = new LegendTitle(subplot2);

        lt.setItemFont(new Font("Dialog", Typeface.BOLD, 9));
        lt.setBackgroundPaintType(new SolidColor(Color.argb(0, 0, 0, 0)));
        lt.setItemPaintType(white);
        lt.setPosition(RectangleEdge.TOP);
        XYTitleAnnotation ta = new XYTitleAnnotation(0.02, 0.98, lt, RectangleAnchor.TOP_LEFT);

        ta.setMaxWidth(0.48);
        subplot2.addAnnotation(ta);

        AFreeChart chart = new AFreeChart(
                "Candle Stick Chart Demo 03",
                AFreeChart.DEFAULT_TITLE_FONT,
                plot,
                false);

        // setting chart
        chart.setBackgroundPaintType(black);

        return chart;
    }

    /**
     * Creates a sample high low dataset.
     * @return a sample high low dataset.
     */
    public static OHLCDataset createDataset1() {

        Date[] date = new Date[47];
        double[] high = new double[47];
        double[] low = new double[47];
        double[] open = new double[47];
        double[] close = new double[47];
        double[] volume = new double[47];

        int jan = 1;
        int feb = 2;

        for(int i = 0; i < 47; i++) {
        	if(i <= 27) {
        		date[i] = createDate(2001, jan, i+4, 12, 0);
        	} else {
        		date[i] = createDate(2001, feb, i-27, 12, 0);
        	}
        	high[i] = 45 + Math.random() * 20;
        	low[i] = high[i] - (Math.random() * 30 + 3);
        	do {
	        	open[i] = high[i] - Math.random() * (high[i] - low[i]);
	        	close[i] = low[i] + Math.random() * (high[i] - low[i]);
        	} while(Math.abs(open[i] - close[i]) < 1);
        }

        return new DefaultHighLowDataset("Series 1", date, high, low, open, close, volume);
    }

    private static final Calendar calendar = Calendar.getInstance();

    /**
     * Returns a date using the default locale and timezone.
     * @param y the year (YYYY).
     * @param m the month (1-12).
     * @param d the day of the month.
     * @param hour the hour of the day.
     * @param min the minute of the hour.
     * @return A date.
     */
    private static Date createDate(int y, int m, int d, int hour, int min) {
        calendar.clear();
        calendar.set(y, m - 1, d, hour, min);
        return calendar.getTime();
    }

    /**
     * Creates a dataset.
     * @return A dataset.
     */
    public static XYDataset createDataset2() {

        TimeSeries s1 = new TimeSeries("MACD");

        for(int i = 0; i < 47; i++) {
        	if(i <= 27) {
        		s1.add(new Day(i + 4, 1, 2001), Math.random() * 30 + 30);
        	} else {
        		s1.add(new Day(i - 27, 2, 2001), Math.random() * 30 + 30);
        	}
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;
    }

    public static TickUnitSource createTickUnits() {

        TickUnits units = new TickUnits();
        DecimalFormat df1 = new DecimalFormat("$0");
        DecimalFormat df2 = new DecimalFormat("$0.00");

        // we can add the units in any order, the TickUnits collection will
        // sort them...
        units.add(new NumberTickUnit(1000000, df1, 0));
        units.add(new NumberTickUnit(500000, df1, 0));
        units.add(new NumberTickUnit(200000, df1, 0));
        units.add(new NumberTickUnit(100000, df1, 0));
        units.add(new NumberTickUnit(50000, df1, 0));
        units.add(new NumberTickUnit(20000, df1, 0));
        units.add(new NumberTickUnit(10000, df1, 0));
        units.add(new NumberTickUnit(5000, df1, 0));
        units.add(new NumberTickUnit(2000, df1, 0));
        units.add(new NumberTickUnit(1000, df1, 0));
        units.add(new NumberTickUnit(500, df1, 0));
        units.add(new NumberTickUnit(200, df1, 0));
        units.add(new NumberTickUnit(100, df1, 0));
        units.add(new NumberTickUnit(50, df1, 0));
        units.add(new NumberTickUnit(20, df1, 0));
        units.add(new NumberTickUnit(10, df1, 0));
        units.add(new NumberTickUnit(5, df1, 0));
        units.add(new NumberTickUnit(2, df1, 0));
        units.add(new NumberTickUnit(1, df1, 0));

        units.add(new NumberTickUnit(0.000001, df2, 0));
        units.add(new NumberTickUnit(0.0000025, df2, 0));
        units.add(new NumberTickUnit(0.000005, df2, 0));
        units.add(new NumberTickUnit(0.00001, df2, 0));
        units.add(new NumberTickUnit(0.000025, df2, 0));
        units.add(new NumberTickUnit(0.00005, df2, 0));
        units.add(new NumberTickUnit(0.0001, df2, 0));
        units.add(new NumberTickUnit(0.00025, df2, 0));
        units.add(new NumberTickUnit(0.0005, df2, 0));
        units.add(new NumberTickUnit(0.001, df2, 0));
        units.add(new NumberTickUnit(0.0025, df2, 0));
        units.add(new NumberTickUnit(0.005, df2, 0));
        units.add(new NumberTickUnit(0.01, df2, 0));
        units.add(new NumberTickUnit(0.025, df2, 0));
        units.add(new NumberTickUnit(0.05, df2, 0));
        units.add(new NumberTickUnit(0.1, df2, 0));
        units.add(new NumberTickUnit(0.25, df2, 0));
        units.add(new NumberTickUnit(0.5, df2, 0));

        return units;
    }

}
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
 * CandleStickChartDemo03View.java
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

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import org.afree.chart.AFreeChart;
import org.afree.chart.annotations.XYTitleAnnotation;
import org.afree.chart.axis.AxisLocation;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.axis.NumberTickUnit;
import org.afree.chart.axis.TickUnitSource;
import org.afree.chart.axis.TickUnits;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.CombinedDomainXYPlot;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.CandlestickRenderer;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.chart.title.LegendTitle;
import org.afree.data.time.Day;
import org.afree.data.time.TimeSeries;
import org.afree.data.time.TimeSeriesCollection;
import org.afree.data.xy.DefaultHighLowDataset;
import org.afree.data.xy.OHLCDataset;
import org.afree.data.xy.XYDataset;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.LineShape;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;

import org.afree.chart.demo.DemoView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

public class CandleStickChartDemo03View extends DemoView {

    public CandleStickChartDemo03View(Context context) {
        super(context);

        final AFreeChart chart = createChart();
        setChart(chart);
    }

    private static AFreeChart createChart() {

        // declare colors
        PaintType black = new SolidColor(Color.BLACK);
        PaintType blue = new SolidColor(Color.BLUE);
        PaintType red = new SolidColor(Color.RED);
        PaintType yellow = new SolidColor(Color.YELLOW);
        PaintType darkGreen = new SolidColor(Color.argb(255, 0, 64, 0));
        PaintType white = new SolidColor(Color.WHITE);
        
        OHLCDataset dataset1 = createDataset1();

        NumberAxis rangeAxis1 = new NumberAxis();
        rangeAxis1.setStandardTickUnits(createTickUnits());
        rangeAxis1.setAutoTickUnitSelection(true);
        rangeAxis1.setAutoRange(true);
        rangeAxis1.setAutoRangeIncludesZero(false);
        rangeAxis1.setAxisLinePaintType(white);
        rangeAxis1.setAxisLineStroke(1);
        rangeAxis1.setTickMarkPaintType(white);
        rangeAxis1.setTickMarkStroke(1);
        rangeAxis1.setTickMarkOutsideLength(2);
        rangeAxis1.setLabelPaintType(white);
        rangeAxis1.setTickLabelPaintType(white);
        rangeAxis1.setTickLabelInsets(new RectangleInsets(10, 0, 10, 0));
        rangeAxis1.setLimitAble(/*true*/false);
        rangeAxis1.setLimitRange(20, 70);

        CandlestickRenderer renderer1 = new CandlestickRenderer();

        renderer1.setUseOutlinePaint(true);
        renderer1.setBaseOutlinePaintType(white);
        renderer1.setBaseOutlineStroke(1.0f);

        renderer1.setUpPaintType(red);
        renderer1.setDownPaintType(blue);

        XYPlot subplot1 = new XYPlot(dataset1, null, rangeAxis1, renderer1);
       
        //overlaid
        XYDataset dataset3 = createDataset2();
        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
        renderer3.setBaseShapesVisible(false);
        renderer3.setSeriesPaintType(0, yellow);
        
        subplot1.setDataset(1, dataset3);
        subplot1.setRenderer(1, renderer3);

        subplot1.setBackgroundPaintType(black);
        subplot1.setDomainGridlinePaintType(darkGreen);
        subplot1.setRangeGridlinePaintType(darkGreen);
        subplot1.setRangeAxisLocation(AxisLocation.TOP_OR_RIGHT);
        subplot1.setOutlineVisible(true);
        subplot1.setOutlinePaintType(white);
        subplot1.setOutlineStroke(2.0f);

        XYDataset dataset2 = createDataset2();
        NumberAxis rangeAxis2 = new NumberAxis();
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setAxisLinePaintType(white);
        rangeAxis2.setAxisLineStroke(1);
        rangeAxis2.setTickMarkPaintType(white);
        rangeAxis2.setTickMarkStroke(1);
        rangeAxis2.setTickMarkOutsideLength(2);
        rangeAxis2.setLabelPaintType(white);
        rangeAxis2.setTickLabelPaintType(white);
        rangeAxis2.setTickLabelInsets(new RectangleInsets(10, 0, 10, 0));
        rangeAxis2.setLimitAble(true);
        rangeAxis2.setLimitRange(0, 100);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setBaseShapesVisible(false);
        renderer2.setLegendLine(new LineShape());

        XYPlot subplot2 = new XYPlot(dataset2, null, rangeAxis2,renderer2);
        subplot2.setDomainGridlinesVisible(true);
        subplot2.setBackgroundPaintType(black);
        subplot2.setDomainGridlinePaintType(darkGreen);
        subplot2.setRangeGridlinePaintType(darkGreen);
        subplot2.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        subplot2.setOutlineVisible(true);
        subplot2.setOutlinePaintType(white);
        subplot2.setOutlineStroke(2.0f);

        // setting domain axis
        ValueAxis timeAxis = new DateAxis("Date");
        timeAxis.setAxisLinePaintType(white);
        timeAxis.setAxisLineStroke(1);

        timeAxis.setTickMarkPaintType(white);
        timeAxis.setTickMarkStroke(1);
        timeAxis.setTickMarkOutsideLength(2);

        timeAxis.setLabelPaintType(white);
        timeAxis.setTickLabelPaintType(white);

        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(timeAxis);
        plot.setBackgroundPaintType(black);
        plot.add(subplot1, 2);
        plot.add(subplot2, 1);

        // add a annotation
        LegendTitle lt = new LegendTitle(subplot2);

        lt.setItemFont(new Font("Dialog", Typeface.BOLD, 9));
        lt.setBackgroundPaintType(new SolidColor(Color.argb(0, 0, 0, 0)));
        lt.setItemPaintType(white);
        lt.setPosition(RectangleEdge.TOP);
        XYTitleAnnotation ta = new XYTitleAnnotation(0.02, 0.98, lt, RectangleAnchor.TOP_LEFT);

        ta.setMaxWidth(0.48);
        subplot2.addAnnotation(ta);

        AFreeChart chart = new AFreeChart(
                "Candle Stick Chart Demo 03",
                AFreeChart.DEFAULT_TITLE_FONT,
                plot,
                false);

        // setting chart
        chart.setBackgroundPaintType(black);

        return chart;
    }

    /**
     * Creates a sample high low dataset.
     * @return a sample high low dataset.
     */
    public static OHLCDataset createDataset1() {

        Date[] date = new Date[47];
        double[] high = new double[47];
        double[] low = new double[47];
        double[] open = new double[47];
        double[] close = new double[47];
        double[] volume = new double[47];

        int jan = 1;
        int feb = 2;

        for(int i = 0; i < 47; i++) {
        	if(i <= 27) {
        		date[i] = createDate(2001, jan, i+4, 12, 0);
        	} else {
        		date[i] = createDate(2001, feb, i-27, 12, 0);
        	}
        	high[i] = 45 + Math.random() * 20;
        	low[i] = high[i] - (Math.random() * 30 + 3);
        	do {
	        	open[i] = high[i] - Math.random() * (high[i] - low[i]);
	        	close[i] = low[i] + Math.random() * (high[i] - low[i]);
        	} while(Math.abs(open[i] - close[i]) < 1);
        }

        return new DefaultHighLowDataset("Series 1", date, high, low, open, close, volume);
    }

    private static final Calendar calendar = Calendar.getInstance();

    /**
     * Returns a date using the default locale and timezone.
     * @param y the year (YYYY).
     * @param m the month (1-12).
     * @param d the day of the month.
     * @param hour the hour of the day.
     * @param min the minute of the hour.
     * @return A date.
     */
    private static Date createDate(int y, int m, int d, int hour, int min) {
        calendar.clear();
        calendar.set(y, m - 1, d, hour, min);
        return calendar.getTime();
    }

    /**
     * Creates a dataset.
     * @return A dataset.
     */
    public static XYDataset createDataset2() {

        TimeSeries s1 = new TimeSeries("MACD");

        for(int i = 0; i < 47; i++) {
        	if(i <= 27) {
        		s1.add(new Day(i + 4, 1, 2001), Math.random() * 30 + 30);
        	} else {
        		s1.add(new Day(i - 27, 2, 2001), Math.random() * 30 + 30);
        	}
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;
    }

    public static TickUnitSource createTickUnits() {

        TickUnits units = new TickUnits();
        DecimalFormat df1 = new DecimalFormat("$0");
        DecimalFormat df2 = new DecimalFormat("$0.00");

        // we can add the units in any order, the TickUnits collection will
        // sort them...
        units.add(new NumberTickUnit(1000000, df1, 0));
        units.add(new NumberTickUnit(500000, df1, 0));
        units.add(new NumberTickUnit(200000, df1, 0));
        units.add(new NumberTickUnit(100000, df1, 0));
        units.add(new NumberTickUnit(50000, df1, 0));
        units.add(new NumberTickUnit(20000, df1, 0));
        units.add(new NumberTickUnit(10000, df1, 0));
        units.add(new NumberTickUnit(5000, df1, 0));
        units.add(new NumberTickUnit(2000, df1, 0));
        units.add(new NumberTickUnit(1000, df1, 0));
        units.add(new NumberTickUnit(500, df1, 0));
        units.add(new NumberTickUnit(200, df1, 0));
        units.add(new NumberTickUnit(100, df1, 0));
        units.add(new NumberTickUnit(50, df1, 0));
        units.add(new NumberTickUnit(20, df1, 0));
        units.add(new NumberTickUnit(10, df1, 0));
        units.add(new NumberTickUnit(5, df1, 0));
        units.add(new NumberTickUnit(2, df1, 0));
        units.add(new NumberTickUnit(1, df1, 0));

        units.add(new NumberTickUnit(0.000001, df2, 0));
        units.add(new NumberTickUnit(0.0000025, df2, 0));
        units.add(new NumberTickUnit(0.000005, df2, 0));
        units.add(new NumberTickUnit(0.00001, df2, 0));
        units.add(new NumberTickUnit(0.000025, df2, 0));
        units.add(new NumberTickUnit(0.00005, df2, 0));
        units.add(new NumberTickUnit(0.0001, df2, 0));
        units.add(new NumberTickUnit(0.00025, df2, 0));
        units.add(new NumberTickUnit(0.0005, df2, 0));
        units.add(new NumberTickUnit(0.001, df2, 0));
        units.add(new NumberTickUnit(0.0025, df2, 0));
        units.add(new NumberTickUnit(0.005, df2, 0));
        units.add(new NumberTickUnit(0.01, df2, 0));
        units.add(new NumberTickUnit(0.025, df2, 0));
        units.add(new NumberTickUnit(0.05, df2, 0));
        units.add(new NumberTickUnit(0.1, df2, 0));
        units.add(new NumberTickUnit(0.25, df2, 0));
        units.add(new NumberTickUnit(0.5, df2, 0));

        return units;
    }

}
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
 * CandleStickChartDemo03View.java
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

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import org.afree.chart.AFreeChart;
import org.afree.chart.annotations.XYTitleAnnotation;
import org.afree.chart.axis.AxisLocation;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.axis.NumberTickUnit;
import org.afree.chart.axis.TickUnitSource;
import org.afree.chart.axis.TickUnits;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.CombinedDomainXYPlot;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.CandlestickRenderer;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.chart.title.LegendTitle;
import org.afree.data.time.Day;
import org.afree.data.time.TimeSeries;
import org.afree.data.time.TimeSeriesCollection;
import org.afree.data.xy.DefaultHighLowDataset;
import org.afree.data.xy.OHLCDataset;
import org.afree.data.xy.XYDataset;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.LineShape;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;

import org.afree.chart.demo.DemoView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

public class CandleStickChartDemo03View extends DemoView {

    public CandleStickChartDemo03View(Context context) {
        super(context);

        final AFreeChart chart = createChart();
        setChart(chart);
    }

    private static AFreeChart createChart() {

        // declare colors
        PaintType black = new SolidColor(Color.BLACK);
        PaintType blue = new SolidColor(Color.BLUE);
        PaintType red = new SolidColor(Color.RED);
        PaintType yellow = new SolidColor(Color.YELLOW);
        PaintType darkGreen = new SolidColor(Color.argb(255, 0, 64, 0));
        PaintType white = new SolidColor(Color.WHITE);
        
        OHLCDataset dataset1 = createDataset1();

        NumberAxis rangeAxis1 = new NumberAxis();
        rangeAxis1.setStandardTickUnits(createTickUnits());
        rangeAxis1.setAutoTickUnitSelection(true);
        rangeAxis1.setAutoRange(true);
        rangeAxis1.setAutoRangeIncludesZero(false);
        rangeAxis1.setAxisLinePaintType(white);
        rangeAxis1.setAxisLineStroke(1);
        rangeAxis1.setTickMarkPaintType(white);
        rangeAxis1.setTickMarkStroke(1);
        rangeAxis1.setTickMarkOutsideLength(2);
        rangeAxis1.setLabelPaintType(white);
        rangeAxis1.setTickLabelPaintType(white);
        rangeAxis1.setTickLabelInsets(new RectangleInsets(10, 0, 10, 0));
        rangeAxis1.setLimitAble(/*true*/false);
        rangeAxis1.setLimitRange(20, 70);

        CandlestickRenderer renderer1 = new CandlestickRenderer();

        renderer1.setUseOutlinePaint(true);
        renderer1.setBaseOutlinePaintType(white);
        renderer1.setBaseOutlineStroke(1.0f);

        renderer1.setUpPaintType(red);
        renderer1.setDownPaintType(blue);

        XYPlot subplot1 = new XYPlot(dataset1, null, rangeAxis1, renderer1);
       
        //overlaid
        XYDataset dataset3 = createDataset2();
        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
        renderer3.setBaseShapesVisible(false);
        renderer3.setSeriesPaintType(0, yellow);
        
        subplot1.setDataset(1, dataset3);
        subplot1.setRenderer(1, renderer3);

        subplot1.setBackgroundPaintType(black);
        subplot1.setDomainGridlinePaintType(darkGreen);
        subplot1.setRangeGridlinePaintType(darkGreen);
        subplot1.setRangeAxisLocation(AxisLocation.TOP_OR_RIGHT);
        subplot1.setOutlineVisible(true);
        subplot1.setOutlinePaintType(white);
        subplot1.setOutlineStroke(2.0f);

        XYDataset dataset2 = createDataset2();
        NumberAxis rangeAxis2 = new NumberAxis();
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setAxisLinePaintType(white);
        rangeAxis2.setAxisLineStroke(1);
        rangeAxis2.setTickMarkPaintType(white);
        rangeAxis2.setTickMarkStroke(1);
        rangeAxis2.setTickMarkOutsideLength(2);
        rangeAxis2.setLabelPaintType(white);
        rangeAxis2.setTickLabelPaintType(white);
        rangeAxis2.setTickLabelInsets(new RectangleInsets(10, 0, 10, 0));
        rangeAxis2.setLimitAble(true);
        rangeAxis2.setLimitRange(0, 100);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setBaseShapesVisible(false);
        renderer2.setLegendLine(new LineShape());

        XYPlot subplot2 = new XYPlot(dataset2, null, rangeAxis2,renderer2);
        subplot2.setDomainGridlinesVisible(true);
        subplot2.setBackgroundPaintType(black);
        subplot2.setDomainGridlinePaintType(darkGreen);
        subplot2.setRangeGridlinePaintType(darkGreen);
        subplot2.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        subplot2.setOutlineVisible(true);
        subplot2.setOutlinePaintType(white);
        subplot2.setOutlineStroke(2.0f);

        // setting domain axis
        ValueAxis timeAxis = new DateAxis("Date");
        timeAxis.setAxisLinePaintType(white);
        timeAxis.setAxisLineStroke(1);

        timeAxis.setTickMarkPaintType(white);
        timeAxis.setTickMarkStroke(1);
        timeAxis.setTickMarkOutsideLength(2);

        timeAxis.setLabelPaintType(white);
        timeAxis.setTickLabelPaintType(white);

        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(timeAxis);
        plot.setBackgroundPaintType(black);
        plot.add(subplot1, 2);
        plot.add(subplot2, 1);

        // add a annotation
        LegendTitle lt = new LegendTitle(subplot2);

        lt.setItemFont(new Font("Dialog", Typeface.BOLD, 9));
        lt.setBackgroundPaintType(new SolidColor(Color.argb(0, 0, 0, 0)));
        lt.setItemPaintType(white);
        lt.setPosition(RectangleEdge.TOP);
        XYTitleAnnotation ta = new XYTitleAnnotation(0.02, 0.98, lt, RectangleAnchor.TOP_LEFT);

        ta.setMaxWidth(0.48);
        subplot2.addAnnotation(ta);

        AFreeChart chart = new AFreeChart(
                "Candle Stick Chart Demo 03",
                AFreeChart.DEFAULT_TITLE_FONT,
                plot,
                false);

        // setting chart
        chart.setBackgroundPaintType(black);

        return chart;
    }

    /**
     * Creates a sample high low dataset.
     * @return a sample high low dataset.
     */
    public static OHLCDataset createDataset1() {

        Date[] date = new Date[47];
        double[] high = new double[47];
        double[] low = new double[47];
        double[] open = new double[47];
        double[] close = new double[47];
        double[] volume = new double[47];

        int jan = 1;
        int feb = 2;

        for(int i = 0; i < 47; i++) {
        	if(i <= 27) {
        		date[i] = createDate(2001, jan, i+4, 12, 0);
        	} else {
        		date[i] = createDate(2001, feb, i-27, 12, 0);
        	}
        	high[i] = 45 + Math.random() * 20;
        	low[i] = high[i] - (Math.random() * 30 + 3);
        	do {
	        	open[i] = high[i] - Math.random() * (high[i] - low[i]);
	        	close[i] = low[i] + Math.random() * (high[i] - low[i]);
        	} while(Math.abs(open[i] - close[i]) < 1);
        }

        return new DefaultHighLowDataset("Series 1", date, high, low, open, close, volume);
    }

    private static final Calendar calendar = Calendar.getInstance();

    /**
     * Returns a date using the default locale and timezone.
     * @param y the year (YYYY).
     * @param m the month (1-12).
     * @param d the day of the month.
     * @param hour the hour of the day.
     * @param min the minute of the hour.
     * @return A date.
     */
    private static Date createDate(int y, int m, int d, int hour, int min) {
        calendar.clear();
        calendar.set(y, m - 1, d, hour, min);
        return calendar.getTime();
    }

    /**
     * Creates a dataset.
     * @return A dataset.
     */
    public static XYDataset createDataset2() {

        TimeSeries s1 = new TimeSeries("MACD");

        for(int i = 0; i < 47; i++) {
        	if(i <= 27) {
        		s1.add(new Day(i + 4, 1, 2001), Math.random() * 30 + 30);
        	} else {
        		s1.add(new Day(i - 27, 2, 2001), Math.random() * 30 + 30);
        	}
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;
    }

    public static TickUnitSource createTickUnits() {

        TickUnits units = new TickUnits();
        DecimalFormat df1 = new DecimalFormat("$0");
        DecimalFormat df2 = new DecimalFormat("$0.00");

        // we can add the units in any order, the TickUnits collection will
        // sort them...
        units.add(new NumberTickUnit(1000000, df1, 0));
        units.add(new NumberTickUnit(500000, df1, 0));
        units.add(new NumberTickUnit(200000, df1, 0));
        units.add(new NumberTickUnit(100000, df1, 0));
        units.add(new NumberTickUnit(50000, df1, 0));
        units.add(new NumberTickUnit(20000, df1, 0));
        units.add(new NumberTickUnit(10000, df1, 0));
        units.add(new NumberTickUnit(5000, df1, 0));
        units.add(new NumberTickUnit(2000, df1, 0));
        units.add(new NumberTickUnit(1000, df1, 0));
        units.add(new NumberTickUnit(500, df1, 0));
        units.add(new NumberTickUnit(200, df1, 0));
        units.add(new NumberTickUnit(100, df1, 0));
        units.add(new NumberTickUnit(50, df1, 0));
        units.add(new NumberTickUnit(20, df1, 0));
        units.add(new NumberTickUnit(10, df1, 0));
        units.add(new NumberTickUnit(5, df1, 0));
        units.add(new NumberTickUnit(2, df1, 0));
        units.add(new NumberTickUnit(1, df1, 0));

        units.add(new NumberTickUnit(0.000001, df2, 0));
        units.add(new NumberTickUnit(0.0000025, df2, 0));
        units.add(new NumberTickUnit(0.000005, df2, 0));
        units.add(new NumberTickUnit(0.00001, df2, 0));
        units.add(new NumberTickUnit(0.000025, df2, 0));
        units.add(new NumberTickUnit(0.00005, df2, 0));
        units.add(new NumberTickUnit(0.0001, df2, 0));
        units.add(new NumberTickUnit(0.00025, df2, 0));
        units.add(new NumberTickUnit(0.0005, df2, 0));
        units.add(new NumberTickUnit(0.001, df2, 0));
        units.add(new NumberTickUnit(0.0025, df2, 0));
        units.add(new NumberTickUnit(0.005, df2, 0));
        units.add(new NumberTickUnit(0.01, df2, 0));
        units.add(new NumberTickUnit(0.025, df2, 0));
        units.add(new NumberTickUnit(0.05, df2, 0));
        units.add(new NumberTickUnit(0.1, df2, 0));
        units.add(new NumberTickUnit(0.25, df2, 0));
        units.add(new NumberTickUnit(0.5, df2, 0));

        return units;
    }

}
