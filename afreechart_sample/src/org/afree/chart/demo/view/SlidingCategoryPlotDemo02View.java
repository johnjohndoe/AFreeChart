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
 * -----------------------------
 * SlidingCategoryPlotDemo02View
 * -----------------------------
 * 
 * (C) Copyright 2010, 2011, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  Niwano Masayoshi (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 24-Apr-2012 : Version 0.0.4 (NM);
 */

package org.afree.chart.demo.view;


import java.util.Random;

import org.afree.chart.ChartFactory;
import org.afree.chart.AFreeChart;
import org.afree.chart.axis.CategoryAxis;
import org.afree.chart.axis.CategoryLabelPositions;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.demo.DemoView;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.SlidingCategoryPlot;
import org.afree.chart.renderer.category.BarRenderer;
import org.afree.data.category.CategoryDataset;
import org.afree.data.category.DefaultCategoryDataset;
import org.afree.data.category.SlidingCategoryDataset;
import org.afree.graphics.GradientColor;
import org.afree.graphics.SolidColor;

import android.content.Context;
import android.graphics.Color;

/**
 * SlidingCategoryPlotDemo01View
 */
public class SlidingCategoryPlotDemo02View extends DemoView {

    /**
     * constructor
     * @param context
     */
    public SlidingCategoryPlotDemo02View(Context context) {
        super(context);

        CategoryDataset dataset = createDataset();
        AFreeChart chart = createChart(dataset);

        setChart(chart);
    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {

        // row keys...
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Random r = new Random(1);
        for (int i = 0; i < 100; i++) {
            dataset.addValue(r.nextDouble(), series1, "category" + i);
            dataset.addValue(r.nextDouble(), series2, "category" + i);
            dataset.addValue(r.nextDouble(), series3, "category" + i);
        }
        
        // set domain range 1 between 10.
        SlidingCategoryDataset slidingCategoryDataset =
            new SlidingCategoryDataset(dataset, 1, 5);
        return slidingCategoryDataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static AFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        AFreeChart chart = ChartFactory.createSlidingBarChart(
            "Sliding Category Plot Demo 02",      // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaintType(new SolidColor(Color.WHITE));

        // get a reference to the plot for further customisation...
        SlidingCategoryPlot plot = (SlidingCategoryPlot) chart.getPlot();
        plot.setSlideRatio(0.5f);
        plot.setZoomInterval(1000);

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientColor gp0 = new GradientColor(Color.BLUE, Color.rgb(0, 0, 64));
        GradientColor gp1 = new GradientColor(Color.GREEN, Color.rgb(0, 64, 0));
        GradientColor gp2 = new GradientColor(Color.RED, Color.rgb(64, 0, 0));
        renderer.setSeriesPaintType(0, gp0);
        renderer.setSeriesPaintType(1, gp1);
        renderer.setSeriesPaintType(2, gp2);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 6.0));
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }
}
