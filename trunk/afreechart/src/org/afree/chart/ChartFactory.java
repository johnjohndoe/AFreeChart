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
 * ChartFactory.java
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
 * 24-Apr-2012 : Added method to create slide category chart.
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Serge V. Grachov;
 *                   Joao Guilherme Del Valle;
 *                   Bill Kelemen;
 *                   Jon Iles;
 *                   Jelai Wang;
 *                   Richard Atkinson;
 *                   David Browning (for Australian Institute of Marine
 *                       Science);
 *                   Benoit Xhenseval;
 *
 * Changes
 * -------
 * 19-Oct-2001 : Version 1, most methods transferred from JFreeChart.java (DG);
 * 22-Oct-2001 : Added methods to create stacked bar charts (DG);
 *               Renamed DataSource.java --> Dataset.java etc. (DG);
 * 31-Oct-2001 : Added 3D-effect vertical bar and stacked-bar charts,
 *               contributed by Serge V. Grachov (DG);
 * 07-Nov-2001 : Added a flag to control whether or not a legend is added to
 *               the chart (DG);
 * 17-Nov-2001 : For pie chart, changed dataset from CategoryDataset to
 *               PieDataset (DG);
 * 30-Nov-2001 : Removed try/catch handlers from chart creation, as the
 *               exception are now RuntimeExceptions, as suggested by Joao
 *               Guilherme Del Valle (DG);
 * 06-Dec-2001 : Added createCombinableXXXXXCharts methods (BK);
 * 12-Dec-2001 : Added createCandlestickChart() method (DG);
 * 13-Dec-2001 : Updated methods for charts with new renderers (DG);
 * 08-Jan-2002 : Added import for
 *               com.jrefinery.chart.combination.CombinedChart (DG);
 * 31-Jan-2002 : Changed the createCombinableVerticalXYBarChart() method to use
 *               renderer (DG);
 * 06-Feb-2002 : Added new method createWindPlot() (DG);
 * 23-Apr-2002 : Updates to the chart and plot constructor API (DG);
 * 21-May-2002 : Added new method createAreaChart() (JI);
 * 06-Jun-2002 : Added new method createGanttChart() (DG);
 * 11-Jun-2002 : Renamed createHorizontalStackedBarChart()
 *               --> createStackedHorizontalBarChart() for consistency (DG);
 * 06-Aug-2002 : Updated Javadoc comments (DG);
 * 21-Aug-2002 : Added createPieChart(CategoryDataset) method (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 09-Oct-2002 : Added methods including tooltips and URL flags (DG);
 * 06-Nov-2002 : Moved renderers into a separate package (DG);
 * 18-Nov-2002 : Changed CategoryDataset to TableDataset (DG);
 * 21-Mar-2003 : Incorporated HorizontalCategoryAxis3D, see bug id 685501 (DG);
 * 13-May-2003 : Merged some horizontal and vertical methods (DG);
 * 24-May-2003 : Added support for timeline in createHighLowChart (BK);
 * 07-Jul-2003 : Added createHistogram() method contributed by Jelai Wang (DG);
 * 27-Jul-2003 : Added createStackedAreaXYChart() method (RA);
 * 05-Aug-2003 : added new method createBoxAndWhiskerChart (DB);
 * 08-Sep-2003 : Changed ValueAxis API (DG);
 * 07-Oct-2003 : Added stepped area XY chart contributed by Matthias Rose (DG);
 * 06-Nov-2003 : Added createWaterfallChart() method (DG);
 * 20-Nov-2003 : Set rendering order for 3D bar charts to fix overlapping
 *               problems (DG);
 * 25-Nov-2003 : Added createWaferMapChart() method (DG);
 * 23-Dec-2003 : Renamed createPie3DChart() --> createPieChart3D for
 *               consistency (DG);
 * 20-Jan-2004 : Added createPolarChart() method (DG);
 * 28-Jan-2004 : Fixed bug (882890) with axis range in
 *               createStackedXYAreaChart() method (DG);
 * 25-Feb-2004 : Renamed XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 11-Mar-2004 : Updated for pie chart changes (DG);
 * 27-Apr-2004 : Added new createPieChart() method contributed by Benoit
 *               Xhenseval (see RFE 942195) (DG);
 * 11-May-2004 : Split StandardCategoryItemLabelGenerator
 *               --> StandardCategoryToolTipGenerator and
 *               StandardCategoryLabelGenerator (DG);
 * 06-Jan-2005 : Removed deprecated methods (DG);
 * 27-Jan-2005 : Added new constructor to LineAndShapeRenderer (DG);
 * 28-Feb-2005 : Added docs to createBubbleChart() method (DG);
 * 17-Mar-2005 : Added createRingPlot() method (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets (DG);
 * 29-Nov-2005 : Removed signal chart (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 26-Jan-2006 : Corrected API docs for createScatterPlot() (DG);
 * 23-Aug-2006 : Modified createStackedXYAreaChart() to use
 *               StackedXYAreaRenderer2, because StackedXYAreaRenderer doesn't
 *               handle negative values (DG);
 * 27-Sep-2006 : Update createPieChart() method for deprecated code (DG);
 * 29-Nov-2006 : Update createXYBarChart() to use a time based tool tip
 *               generator is a DateAxis is requested (DG);
 * 17-Jan-2007 : Added createBoxAndWhiskerChart() method from patch 1603937
 *               submitted by Darren Jung (DG);
 * 10-Jul-2007 : Added new methods to create pie charts with locale for
 *               section label and tool tip formatting (DG);
 * 14-Aug-2008 : Added ChartTheme facility (DG);
 * 23-Oct-2008 : Check for legacy theme in setChartTheme() and reset default
 *               bar painters (DG);
 * 20-Dec-2008 : In createStackedAreaChart(), set category margin to 0.0 (DG);
 *
 */

package org.afree.chart;

import org.afree.chart.axis.CategoryAxis;
import org.afree.chart.axis.CategoryAxis3D;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.axis.NumberAxis3D;
import org.afree.chart.axis.ValueAxis;
import org.afree.data.category.CategoryDataset;
import org.afree.data.xy.IntervalXYDataset;
import org.afree.data.xy.OHLCDataset;
import org.afree.data.general.PieDataset;
import org.afree.data.xy.XYDataset;
import org.afree.ui.RectangleInsets;
import org.afree.ui.TextAnchor;
import org.afree.util.SortOrder;
import org.afree.chart.labels.ItemLabelAnchor;
import org.afree.chart.labels.ItemLabelPosition;
import org.afree.chart.labels.StandardPieSectionLabelGenerator;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.SlidingCategoryPlot;
import org.afree.chart.plot.PiePlot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.RingPlot;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.category.BarRenderer;
import org.afree.chart.renderer.category.BarRenderer3D;
import org.afree.chart.renderer.category.GradientBarPainter;
import org.afree.chart.renderer.category.AreaRenderer;
import org.afree.chart.renderer.category.LineAndShapeRenderer;
import org.afree.chart.renderer.category.StandardBarPainter;
import org.afree.chart.renderer.xy.CandlestickRenderer;
import org.afree.chart.renderer.xy.GradientXYBarPainter;
import org.afree.chart.renderer.xy.HighLowRenderer;
import org.afree.chart.renderer.xy.StandardXYBarPainter;
import org.afree.chart.renderer.xy.XYBarRenderer;
import org.afree.chart.renderer.xy.XYItemRenderer;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 * A collection of utility methods for creating some standard charts with
 * AFreeChart.
 */
public abstract class ChartFactory {
    
    /** The chart theme. */
    //private static ChartTheme currentTheme = new StandardChartTheme("JFree");
    private static ChartTheme currentTheme = StandardChartTheme.createLegacyTheme();
    
    /**
     * Returns the current chart theme used by the factory.
     *
     * @return The chart theme.
     *
     * @see #setChartTheme(ChartTheme)
     * @see ChartUtilities#applyCurrentTheme(AFreeChart)
     *
     * @since JFreeChart 1.0.11
     */
    public static ChartTheme getChartTheme() {
        return currentTheme;
    }

    /**
     * Sets the current chart theme.  This will be applied to all new charts
     * created via methods in this class.
     *
     * @param theme  the theme (<code>null</code> not permitted).
     *
     * @see #getChartTheme()
     * @see ChartUtilities#applyCurrentTheme(AFreeChart)
     *
     * @since JFreeChart 1.0.11
     */
    public static void setChartTheme(ChartTheme theme) {
        if (theme == null) {
            throw new IllegalArgumentException("Null 'theme' argument.");
        }
        currentTheme = theme;

        // here we do a check to see if the user is installing the "Legacy"
        // theme, and reset the bar painters in that case...
        if (theme instanceof StandardChartTheme) {
            StandardChartTheme sct = (StandardChartTheme) theme;
            if (sct.getName().equals("Legacy")) {
                BarRenderer.setDefaultBarPainter(new StandardBarPainter());
                XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());
            }
            else {
                BarRenderer.setDefaultBarPainter(new GradientBarPainter());
                XYBarRenderer.setDefaultBarPainter(new GradientXYBarPainter());
            }
        }
    }
    
    /**
     * Creates a bar chart. The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link NumberAxis} as the range axis, and a
     * {@link BarRenderer} as the renderer.
     * 
     * @param title
     *            the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel
     *            the label for the category axis (<code>null</code> permitted).
     * @param valueAxisLabel
     *            the label for the value axis (<code>null</code> permitted).
     * @param dataset
     *            the dataset for the chart (<code>null</code> permitted).
     * @param orientation
     *            the plot orientation (horizontal or vertical) (
     *            <code>null</code> not permitted).
     * @param legend
     *            a flag specifying whether or not a legend is required.
     * @param tooltips
     *            configure chart to generate tool tips?
     * @param urls
     *            configure chart to generate URLs?
     * 
     * @return A bar chart.
     */
    public static AFreeChart createBarChart(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        BarRenderer renderer = new BarRenderer();
        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer.setBasePositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer.setBaseNegativeItemLabelPosition(position2);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer.setBasePositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer.setBaseNegativeItemLabelPosition(position2);
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;

    }
    
    /**
     * Creates a sliding bar chart. The chart object returned by this method uses a
     * {@link SlidingCategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link NumberAxis} as the range axis, and a
     * {@link BarRenderer} as the renderer.
     * 
     * @param title
     *            the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel
     *            the label for the category axis (<code>null</code> permitted).
     * @param valueAxisLabel
     *            the label for the value axis (<code>null</code> permitted).
     * @param dataset
     *            the dataset for the chart (<code>null</code> permitted).
     * @param orientation
     *            the plot orientation (horizontal or vertical) (
     *            <code>null</code> not permitted).
     * @param legend
     *            a flag specifying whether or not a legend is required.
     * @param tooltips
     *            configure chart to generate tool tips?
     * @param urls
     *            configure chart to generate URLs?
     * 
     * @return A bar chart.
     */
    public static AFreeChart createSlidingBarChart(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls){
    	
    	if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        BarRenderer renderer = new BarRenderer();
        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer.setBasePositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer.setBaseNegativeItemLabelPosition(position2);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer.setBasePositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer.setBaseNegativeItemLabelPosition(position2);
        }

        SlidingCategoryPlot plot = new SlidingCategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    /**
     * Creates a bar chart with a 3D effect. The chart object returned by this
     * method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis3D} for the domain axis, a {@link NumberAxis3D} as
     * the range axis, and a {@link BarRenderer3D} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis (<code>null</code>
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical)
     *                     (<code>null</code> not permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A bar chart with a 3D effect.
     */
    public static AFreeChart createBarChart3D(String title,
                                              String categoryAxisLabel,
                                              String valueAxisLabel,
                                              CategoryDataset dataset,
                                              PlotOrientation orientation,
                                              boolean legend,
                                              boolean tooltips,
                                              boolean urls) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        CategoryAxis categoryAxis = new CategoryAxis3D(categoryAxisLabel);
        ValueAxis valueAxis = new NumberAxis3D(valueAxisLabel);

        BarRenderer3D renderer = new BarRenderer3D();
        /*
        if (tooltips) {
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
        }
        */

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        if (orientation == PlotOrientation.HORIZONTAL) {
            // change rendering order to ensure that bar overlapping is the
            // right way around
            plot.setRowRenderingOrder(SortOrder.DESCENDING);
            plot.setColumnRenderingOrder(SortOrder.DESCENDING);
        }
        plot.setForegroundAlpha(191);

        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        //currentTheme.apply(chart);
        return chart;

    }

     /**
     * Creates a pie chart with default settings.
     * <P>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A pie chart.
     */
    public static AFreeChart createPieChart(String title,
                                            PieDataset dataset,
                                            boolean legend,
                                            boolean tooltips,
                                            boolean urls) {

        PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
      
        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;
    }
    
    /**
     * Creates a line chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link LineAndShapeRenderer} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis (<code>null</code>
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the chart orientation (horizontal or vertical)
     *                     (<code>null</code> not permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A line chart.
     */
    public static AFreeChart createLineChart(String title,
                                             String categoryAxisLabel,
                                             String valueAxisLabel,
                                             CategoryDataset dataset,
                                             PlotOrientation orientation,
                                             boolean legend,
                                             boolean tooltips,
                                             boolean urls) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer(true, false);
      
        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;

    }
    
    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical)
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return The chart.
     */
    public static AFreeChart createXYLineChart(String title,
                                               String xAxisLabel,
                                               String yAxisLabel,
                                               XYDataset dataset,
                                               PlotOrientation orientation,
                                               boolean legend,
                                               boolean tooltips,
                                               boolean urls) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        
        // Generate Axis objects.
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        
        // Generate renderer.
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        
        // Generate plot object.
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
      
        // Generate AFreeChart object.
        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    
    /**
     * Creates and returns a default instance of a high-low-open-close chart.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param timeAxisLabel  a label for the time axis (<code>null</code>
     *                       permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code>
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     *
     * @return A high-low-open-close chart.
     */
    public static AFreeChart createHighLowChart(String title,
                                                String timeAxisLabel,
                                                String valueAxisLabel,
                                                OHLCDataset dataset,
                                                boolean legend) {

        // Generate Axis objects.
        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);

        // Generate renderer.
        HighLowRenderer renderer = new HighLowRenderer();
        //renderer.setBaseToolTipGenerator(new HighLowItemLabelGenerator());
        
        // Generate plot object.
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
        
        // Generate AFreeChart object.
        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        //currentTheme.apply(chart);
        return chart;

    }


    /**
     * Creates an area chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and an {@link AreaRenderer} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis (<code>null</code>
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (<code>null</code> not
     *                     permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return An area chart.
     */
    public static AFreeChart createAreaChart(String title,
                                             String categoryAxisLabel,
                                             String valueAxisLabel,
                                             CategoryDataset dataset,
                                             PlotOrientation orientation,
                                             boolean legend,
                                             boolean tooltips,
                                             boolean urls) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        categoryAxis.setCategoryMargin(0.0);

        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        AreaRenderer renderer = new AreaRenderer();
        
        /*
        if (tooltips) {
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
        }
         */
        
        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        //currentTheme.apply(chart);
        return chart;

    }


    /**
     * Creates a ring chart with default settings.
     * <P>
     * The chart object returned by this method uses a {@link RingPlot}
     * instance as the plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A ring chart.
     */
    public static AFreeChart createRingChart(String title,
                                             PieDataset dataset,
                                             boolean legend,
                                             boolean tooltips,
                                             boolean urls) {

        RingPlot plot = new RingPlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        /*
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator());
        }
        */
        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        //currentTheme.apply(chart);
        return chart;

    }

    
    /**
     * Creates and returns a default instance of an XY bar chart.
     * <P>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link DateAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link XYBarRenderer} as the
     * renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param dateAxis  make the domain axis display dates?
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the orientation (horizontal or vertical)
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return An XY bar chart.
     */
    public static AFreeChart createXYBarChart(String title,
                                              String xAxisLabel,
                                              boolean dateAxis,
                                              String yAxisLabel,
                                              IntervalXYDataset dataset,
                                              PlotOrientation orientation,
                                              boolean legend,
                                              boolean tooltips,
                                              boolean urls) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        ValueAxis domainAxis = null;
        if (dateAxis) {
            domainAxis = new DateAxis(xAxisLabel);
        }
        else {
            NumberAxis axis = new NumberAxis(xAxisLabel);
            axis.setAutoRangeIncludesZero(false);
            domainAxis = axis;
        }
        ValueAxis valueAxis = new NumberAxis(yAxisLabel);

        XYBarRenderer renderer = new XYBarRenderer();
        if (tooltips) {
//            XYToolTipGenerator tt;
//            if (dateAxis) {
//                tt = StandardXYToolTipGenerator.getTimeSeriesInstance();
//            }
//            else {
//                tt = new StandardXYToolTipGenerator();
//            }
//            renderer.setBaseToolTipGenerator(tt);
        }
//        if (urls) {
//            renderer.setURLGenerator(new StandardXYURLGenerator());
//        }

        XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(orientation);

        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
//        currentTheme.apply(chart);
        return chart;

    }    
    
    /**
     * Creates and returns a time series chart.  A time series chart is an
     * {@link XYPlot} with a {@link DateAxis} for the x-axis and a
     * {@link NumberAxis} for the y-axis.  The default renderer is an
     * {@link XYLineAndShapeRenderer}.
     * <P>
     * A convenient dataset to use with this chart is a
     * {@link org.afree.data.time.TimeSeriesCollection}.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param timeAxisLabel  a label for the time axis (<code>null</code>
     *                       permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code>
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A time series chart.
     */
    public static AFreeChart createTimeSeriesChart(String title,
                                                   String timeAxisLabel,
                                                   String valueAxisLabel,
                                                   XYDataset dataset,
                                                   boolean legend,
                                                   boolean tooltips,
                                                   boolean urls) {

        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        timeAxis.setLowerMargin(0.02);  // reduce the default margins
        timeAxis.setUpperMargin(0.02);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);  // override default
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);

//        XYToolTipGenerator toolTipGenerator = null;
//        if (tooltips) {
//            toolTipGenerator
//                = StandardXYToolTipGenerator.getTimeSeriesInstance();
//        }
//
//        XYURLGenerator urlGenerator = null;
//        if (urls) {
//            urlGenerator = new StandardXYURLGenerator();
//        }

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true,
                false);
//        renderer.setBaseToolTipGenerator(toolTipGenerator);
//        renderer.setURLGenerator(urlGenerator);
        plot.setRenderer(renderer);

        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
//        currentTheme.apply(chart);
        return chart;

    }
    
    /**
     * Creates and returns a default instance of a candlesticks chart.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param timeAxisLabel  a label for the time axis (<code>null</code>
     *                       permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code>
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     *
     * @return A candlestick chart.
     */
    public static AFreeChart createCandlestickChart(String title,
                                                    String timeAxisLabel,
                                                    String valueAxisLabel,
                                                    OHLCDataset dataset,
                                                    boolean legend) {
    
        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
        plot.setRenderer(new CandlestickRenderer());
        AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        // currentTheme.apply(chart);
        return chart;
    
    }

    /**
         * Creates a scatter plot with default settings.  The chart object
         * returned by this method uses an {@link XYPlot} instance as the plot,
         * with a {@link NumberAxis} for the domain axis, a  {@link NumberAxis}
         * as the range axis, and an {@link XYLineAndShapeRenderer} as the
         * renderer.
         *
         * @param title  the chart title (<code>null</code> permitted).
         * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
         * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
         * @param dataset  the dataset for the chart (<code>null</code> permitted).
         * @param orientation  the plot orientation (horizontal or vertical)
         *                     (<code>null</code> NOT permitted).
         * @param legend  a flag specifying whether or not a legend is required.
         * @param tooltips  configure chart to generate tool tips?
         * @param urls  configure chart to generate URLs?
         *
         * @return A scatter plot.
         */
        public static AFreeChart createScatterPlot(String title, String xAxisLabel,
                String yAxisLabel, XYDataset dataset, PlotOrientation orientation,
                boolean legend, boolean tooltips, boolean urls) {
    
            if (orientation == null) {
                throw new IllegalArgumentException("Null 'orientation' argument.");
            }
            NumberAxis xAxis = new NumberAxis(xAxisLabel);
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis(yAxisLabel);
            yAxis.setAutoRangeIncludesZero(false);
    
            XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
    
    //        XYToolTipGenerator toolTipGenerator = null;
    //        if (tooltips) {
    //            toolTipGenerator = new StandardXYToolTipGenerator();
    //        }
    //
    //        XYURLGenerator urlGenerator = null;
    //        if (urls) {
    //            urlGenerator = new StandardXYURLGenerator();
    //        }
            XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);
    //        renderer.setBaseToolTipGenerator(toolTipGenerator);
    //        renderer.setURLGenerator(urlGenerator);
            plot.setRenderer(renderer);
            plot.setOrientation(orientation);
    
            AFreeChart chart = new AFreeChart(title, AFreeChart.DEFAULT_TITLE_FONT,
                    plot, legend);
    //        currentTheme.apply(chart);
            return chart;
    
        }
}
