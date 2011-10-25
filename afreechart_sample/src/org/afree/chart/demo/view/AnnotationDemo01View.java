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
 * AnnotationDemo01View.java
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
import org.afree.chart.annotations.XYTextAnnotation;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.XYPlot;
import org.afree.data.xy.XYDataset;
import org.afree.data.xy.XYSeries;
import org.afree.data.xy.XYSeriesCollection;
import org.afree.graphics.geom.Font;
import org.afree.ui.TextAnchor;

import org.afree.chart.demo.DemoView;

import android.content.Context;
import android.graphics.Typeface;

/**
 * AnnotationDemo01View
 */
public class AnnotationDemo01View extends DemoView {

    /**
     * constructor
     * @param context
     */
    public AnnotationDemo01View(Context context) {
        super(context);
        
        final AFreeChart chart = createChart();

        setChart(chart);
    }

    /**
     * Creates a dataset.
     * @return a dataset.
     */
    private static XYSeriesCollection createDataset() {

    	XYSeries xyS1 = new XYSeries("xyS1", true, false);
    	XYSeries xyS2 = new XYSeries("xyS2", true, false);
    	XYSeries xyS3 = new XYSeries("xyS3", true, false);

    	double value1 = 5.0;

    	for(int i = 0; i < 100; i++) {
    		value1 = value1 + 10.0 / (i+1);
    		xyS1.add(i, value1);
    		xyS2.add(i, value1 - 5);
    		xyS3.add(i, value1 - 10);
    	}

    	XYSeriesCollection xySC = new XYSeriesCollection();
    	xySC.addSeries(xyS1);
    	xySC.addSeries(xyS2);
    	xySC.addSeries(xyS3);

    	return xySC;
    }

    /**
     * Creates a sample chart.
     * @param dataset the dataset.
     * @return A sample chart.
     */
    private static AFreeChart createChart() {
        XYDataset dataset = createDataset();
        AFreeChart chart = ChartFactory.createXYLineChart(
        		"Annotation Demo 01",
                "X Value",
                "Y Value",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setUpperMargin(0.2);

        // add some annotations...
        XYTextAnnotation annotation = null;
        Font font = new Font("SansSerif", Typeface.NORMAL, 12);
        annotation = new XYTextAnnotation("Annotation 1", 96, 57);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        annotation = new XYTextAnnotation("Annotation 2", 96, 52);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        annotation = new XYTextAnnotation("Annotation 3", 96, 47);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        return chart;
    }

}
