/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
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
 * StandardChartTheme.java
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
 * (C) Copyright 2008, 2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 14-Aug-2008 : Version 1 (DG);
 * 10-Apr-2009 : Added getter/setter for smallFont (DG);
 *
 */

package org.afree.chart;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

import android.graphics.Color;
import android.graphics.PathEffect;
import android.graphics.Typeface;

import org.afree.util.PaintTypeUtilities;
import org.afree.util.PublicCloneable;
import org.afree.ui.RectangleInsets;
import org.afree.io.SerialUtilities;
import org.afree.chart.annotations.XYAnnotation;
import org.afree.chart.axis.CategoryAxis;
import org.afree.chart.axis.SymbolAxis;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.block.Block;
import org.afree.chart.block.BlockContainer;
import org.afree.chart.block.LabelBlock;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.CombinedDomainCategoryPlot;
import org.afree.chart.plot.CombinedDomainXYPlot;
import org.afree.chart.plot.CombinedRangeCategoryPlot;
import org.afree.chart.plot.CombinedRangeXYPlot;
import org.afree.chart.plot.DefaultDrawingSupplier;
import org.afree.chart.plot.DrawingSupplier;
import org.afree.chart.plot.PieLabelLinkStyle;
import org.afree.chart.plot.PiePlot;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.AbstractRenderer;
import org.afree.chart.renderer.category.BarPainter;
import org.afree.chart.renderer.category.BarRenderer;
import org.afree.chart.renderer.category.BarRenderer3D;
import org.afree.chart.renderer.category.CategoryItemRenderer;
import org.afree.chart.renderer.category.GradientBarPainter;
import org.afree.chart.renderer.xy.GradientXYBarPainter;
import org.afree.chart.renderer.xy.XYBarPainter;
import org.afree.chart.renderer.xy.XYBarRenderer;
import org.afree.chart.renderer.xy.XYItemRenderer;
import org.afree.chart.title.LegendTitle;
import org.afree.chart.title.TextTitle;
import org.afree.chart.title.Title;
import org.afree.graphics.geom.Font;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;



/**
 * A default implementation of the {@link ChartTheme} interface.  This
 * implementation just collects a whole bunch of chart attributes and mimics
 * the manual process of applying each attribute to the right sub-object
 * within the AFreeChart instance.  It's not elegant code, but it works.
 *
 * @since JFreeChart 1.0.11
 */
public class StandardChartTheme implements ChartTheme, Cloneable,
        PublicCloneable, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5657231725996574858L;

    /** The name of this theme. */
    private String name;

    /**
     * The largest font size.  Use for the main chart title.
     */
    private Font extraLargeFont;

    /**
     * A large font.  Used for subtitles.
     */
    private Font largeFont;

    /**
     * The regular font size.  Used for axis tick labels, legend items etc.
     */
    private Font regularFont;

    /**
     * The small font size.
     */
    private Font smallFont;

    /** The paint used to display the main chart title. */
    private transient PaintType titlePaintType;

    /** The paint used to display subtitles. */
    private transient PaintType subtitlePaintType;

    /** The background paint for the chart. */
    private transient PaintType chartBackgroundPaintType;

    /** The legend background paint. */
    private transient PaintType legendBackgroundPaintType;

    /** The legend item paint. */
    private transient PaintType legendItemPaintType;

    /** The drawing supplier. */
    private DrawingSupplier drawingSupplierType;

    /** The background paint for the plot. */
    private transient PaintType plotBackgroundPaintType;

    /** The plot outline paint. */
    private transient PaintType plotOutlinePaintType;

    /** The label link style for pie charts. */
    private PieLabelLinkStyle labelLinkStyle;

    /** The label link paint for pie charts. */
    private transient PaintType labelLinkPaintType;

    /** The domain grid line paint. */
    private transient PaintType domainGridlinePaintType;

    /** The range grid line paint. */
    private transient PaintType rangeGridlinePaintType;

    /** 
     * The baseline paint (used for domain and range zero baselines)
     * 
     * @since JFreeChart 1.0.13
     */
    private transient PaintType baselinePaintType;

    /** The crosshair paint. */
    private transient PaintType crosshairPaintType;

    /** The axis offsets. */
    private RectangleInsets axisOffset;

    /** The axis label paint. */
    private transient PaintType axisLabelPaintType;

    /** The tick label paint. */
    private transient PaintType tickLabelPaintType;

    /** The item label paint. */
    private transient PaintType itemLabelPaintType;

    /**
     * A flag that controls whether or not shadows are visible (for example,
     * in a bar renderer).
     */
    private boolean shadowVisible;

    /** The shadow paint. */
    private transient PaintType shadowPaintType;

    /** The bar painter. */
    private BarPainter barPainter;

    /** The XY bar painter. */
    private XYBarPainter xyBarPainter;

    /** The thermometer paint. */
    private transient PaintType thermometerPaint;

    /**
     * The paint used to fill the interior of the 'walls' in the background
     * of a plot with a 3D effect.  Applied to BarRenderer3D.
     */
    private transient PaintType wallPaintType;

    /** The error indicator paint for the {@link StatisticalBarRenderer}. */
    private transient PaintType errorIndicatorPaintType;

    /** The grid band paint for a {@link SymbolAxis}. */
    private transient PaintType gridBandPaintType = SymbolAxis.DEFAULT_GRID_BAND_PAINT_TYPE;

    /** The grid band alternate paint for a {@link SymbolAxis}. */
    private transient PaintType gridBandAlternatePaintType
            = SymbolAxis.DEFAULT_GRID_BAND_ALTERNATE_PAINT_TYPE;

    /**
     * Creates and returns the default 'JFree' chart theme.
     *
     * @return A chart theme.
     */
    public static ChartTheme createJFreeTheme() {
        return new StandardChartTheme("JFree");
    }

    /**
     * Creates and returns a theme called "Darkness".  In this theme, the
     * charts have a black background.
     *
     * @return The "Darkness" theme.
     */
    public static ChartTheme createDarknessTheme() {
        StandardChartTheme theme = new StandardChartTheme("Darkness");
        theme.titlePaintType = createPaintType(Color.WHITE);
        theme.subtitlePaintType = createPaintType(Color.WHITE);
        theme.legendBackgroundPaintType = createPaintType(Color.BLACK);
        theme.legendItemPaintType = createPaintType(Color.WHITE);
        theme.chartBackgroundPaintType = createPaintType(Color.BLACK);
        theme.plotBackgroundPaintType = createPaintType(Color.BLACK);
        theme.plotOutlinePaintType = createPaintType(Color.YELLOW);
        theme.baselinePaintType = createPaintType(Color.WHITE);
        theme.crosshairPaintType = createPaintType(Color.RED);
        theme.labelLinkPaintType = createPaintType(Color.LTGRAY);
        theme.tickLabelPaintType = createPaintType(Color.WHITE);
        theme.axisLabelPaintType = createPaintType(Color.WHITE);
        theme.shadowPaintType = createPaintType(Color.DKGRAY);
        theme.itemLabelPaintType = createPaintType(Color.WHITE);
        theme.drawingSupplierType = new DefaultDrawingSupplier(
                new PaintType[] {createPaintType(Integer.decode("0xFFFF00")),
                        createPaintType(Integer.decode("0x0036CC")), createPaintType(Integer.decode("0xFF0000")),
                        createPaintType(Integer.decode("0xFFFF7F")), createPaintType(Integer.decode("0x6681CC")),
                        createPaintType(Integer.decode("0xFF7F7F")), createPaintType(Integer.decode("0xFFFFBF")),
                        createPaintType(Integer.decode("0x99A6CC")), createPaintType(Integer.decode("0xFFBFBF")),
                        createPaintType(Integer.decode("0xA9A938")), createPaintType(Integer.decode("0x2D4587"))},
                new PaintType[] {createPaintType(Integer.decode("0xFFFF00")),
                        createPaintType(Integer.decode("0x0036CC"))},
                new float[] {2.0f},
                new PathEffect[] {null},
                new float[] {0.5f},
                new PathEffect[] {null},
                DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
        theme.wallPaintType = createPaintType(Color.DKGRAY);
        theme.errorIndicatorPaintType = createPaintType(Color.LTGRAY);
        theme.gridBandPaintType = createPaintType(Color.argb(20, 255, 255, 255));
        theme.gridBandAlternatePaintType = createPaintType(Color.argb(40, 255, 255, 255));
        return theme;
    }
    /**
     * Creates and returns a {@link ChartTheme} that doesn't apply any changes
     * to the AFreeChart defaults.  This produces the "legacy" look for
     * AFreeChart.
     *
     * @return A legacy theme.
     */
    public static ChartTheme createLegacyTheme() {
        StandardChartTheme theme = new StandardChartTheme("Legacy") {
            /**
             * 
             */
            private static final long serialVersionUID = -741206071198207569L;

            public void apply(AFreeChart chart) {
                // do nothing at all
            }
        };
        return theme;
    }

    /**
     * Creates a new default instance.
     *
     * @param name  the name of the theme (<code>null</code> not permitted).
     */
    public StandardChartTheme(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Null 'name' argument.");
        }
        this.name = name;
        this.extraLargeFont = new Font("Tahoma", Typeface.BOLD, 20);
        this.largeFont = new Font("Tahoma", Typeface.BOLD, 14);
        this.regularFont = new Font("Tahoma", Typeface.NORMAL, 12);
        this.smallFont = new Font("Tahoma", Typeface.NORMAL, 10);
        this.titlePaintType = createPaintType(Color.BLACK);
        this.subtitlePaintType = createPaintType(Color.BLACK);
        this.legendBackgroundPaintType = createPaintType(Color.WHITE);
        this.legendItemPaintType = createPaintType(Color.DKGRAY);
        this.chartBackgroundPaintType = createPaintType(Color.WHITE);
        this.drawingSupplierType = new DefaultDrawingSupplier();
        this.plotBackgroundPaintType = createPaintType(Color.LTGRAY);
        this.plotOutlinePaintType = createPaintType(Color.BLACK);
        this.labelLinkPaintType = createPaintType(Color.BLACK);
        this.labelLinkStyle = PieLabelLinkStyle.CUBIC_CURVE;
        this.axisOffset = new RectangleInsets(4, 4, 4, 4);
        this.domainGridlinePaintType = createPaintType(Color.WHITE);
        this.rangeGridlinePaintType = createPaintType(Color.WHITE);
        this.baselinePaintType = createPaintType(Color.BLACK);
        this.crosshairPaintType = createPaintType(Color.BLUE);
        this.axisLabelPaintType = createPaintType(Color.DKGRAY);
        this.tickLabelPaintType = createPaintType(Color.DKGRAY);
        this.barPainter = new GradientBarPainter();
        this.xyBarPainter = new GradientXYBarPainter();
        this.shadowVisible = true;
        this.shadowPaintType = createPaintType(Color.GRAY);
        this.itemLabelPaintType = createPaintType(Color.BLACK);
        this.thermometerPaint = createPaintType(Color.WHITE);
        this.wallPaintType = BarRenderer3D.DEFAULT_WALL_PAINT;
        this.errorIndicatorPaintType = createPaintType(Color.BLACK);
    }
    
    /**
     * Returns the largest font for this theme.
     *
     * @return The largest font for this theme.
     *
     * @see #setExtraLargeFont(Font)
     */
    public Font getExtraLargeFont() {
        return this.extraLargeFont;
    }

    /**
     * Sets the largest font for this theme.
     *
     * @param font  the font (<code>null</code> not permitted).
     *
     * @see #getExtraLargeFont()
     */
    public void setExtraLargeFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.extraLargeFont = font;
    }

    /**
     * Returns the large font for this theme.
     *
     * @return The large font (never <code>null</code>).
     *
     * @see #setLargeFont(Font)
     */
    public Font getLargeFont() {
        return this.largeFont;
    }

    /**
     * Sets the large font for this theme.
     *
     * @param font  the font (<code>null</code> not permitted).
     *
     * @see #getLargeFont()
     */
    public void setLargeFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.largeFont = font;
    }

    /**
     * Returns the regular font.
     *
     * @return The regular font (never <code>null</code>).
     *
     * @see #setRegularFont(Font)
     */
    public Font getRegularFont() {
        return this.regularFont;
    }

    /**
     * Sets the regular font for this theme.
     *
     * @param font  the font (<code>null</code> not permitted).
     *
     * @see #getRegularFont()
     */
    public void setRegularFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.regularFont = font;
    }

    /**
     * Returns the small font.
     *
     * @return The small font (never <code>null</code>).
     *
     * @see #setSmallFont(Font)
     *
     * @since JFreeChart 1.0.13
     */
    public Font getSmallFont() {
        return this.smallFont;
    }

    /**
     * Sets the small font for this theme.
     *
     * @param font  the font (<code>null</code> not permitted).
     *
     * @see #getSmallFont()
     *
     * @since JFreeChart 1.0.13
     */
    public void setSmallFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.smallFont = font;
    }

    /**
     * Returns the title paint.
     *
     * @return The title paint (never <code>null</code>).
     *
     * @see #setTitlePaintType(PaintType paintType)
     */
    public PaintType getTitlePaintType() {
        return this.titlePaintType;
    }

    /**
     * Sets the title paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getTitlePaintType()
     */
    public void setTitlePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.titlePaintType = paintType;
    }

    /**
     * Returns the subtitle paint.
     *
     * @return The subtitle paint (never <code>null</code>).
     *
     * @see #setSubtitlePaintType(PaintType paintType)
     */
    public PaintType getSubtitlePaintType() {
        return this.subtitlePaintType;
    }

    /**
     * Sets the subtitle paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getSubtitlePaintType()
     */
    public void setSubtitlePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.subtitlePaintType = paintType;
    }

    /**
     * Returns the chart background paint.
     *
     * @return The chart background paint (never <code>null</code>).
     *
     * @see #setChartBackgroundPaintType(PaintType paintType)
     */
    public PaintType getChartBackgroundPaintType() {
        return this.chartBackgroundPaintType;
    }

    /**
     * Sets the chart background paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getChartBackgroundPaintType()
     */
    public void setChartBackgroundPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.chartBackgroundPaintType = paintType;
    }

    /**
     * Returns the legend background paint.
     *
     * @return The legend background paint (never <code>null</code>).
     *
     * @see #setLegendBackgroundPaintType(PaintType paintType)
     */
    public PaintType getLegendBackgroundPaintType() {
        return this.legendBackgroundPaintType;
    }

    /**
     * Sets the legend background paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getLegendBackgroundPaintType()
     */
    public void setLegendBackgroundPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.legendBackgroundPaintType = paintType;
    }

    /**
     * Returns the legend item paint.
     *
     * @return The legend item paint (never <code>null</code>).
     *
     * @see #setLegendItemPaintType(PaintType paintType)
     */
    public PaintType getLegendItemPaintType() {
        return this.legendItemPaintType;
    }

    /**
     * Sets the legend item paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getLegendItemPaintType()
     */
    public void setLegendItemPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.legendItemPaintType = paintType;
    }

    /**
     * Returns the plot background paint.
     *
     * @return The plot background paint (never <code>null</code>).
     *
     * @see #setPlotBackgroundPaintType(PaintType paintType)
     */
    public PaintType getPlotBackgroundPaintType() {
        return this.plotBackgroundPaintType;
    }

    /**
     * Sets the plot background paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getPlotBackgroundPaintType()
     */
    public void setPlotBackgroundPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.plotBackgroundPaintType = paintType;
    }

    /**
     * Returns the plot outline paint.
     *
     * @return The plot outline paint (never <code>null</code>).
     *
     * @see #setPlotOutlinePaintType(PaintType paintType)
     */
    public PaintType getPlotOutlinePaintType() {
        return this.plotOutlinePaintType;
    }

    /**
     * Sets the plot outline paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getPlotOutlinePaintType()
     */
    public void setPlotOutlinePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.plotOutlinePaintType = paintType;
    }

    /**
     * Returns the label link style for pie charts.
     *
     * @return The label link style (never <code>null</code>).
     *
     * @see #setLabelLinkStyle(PieLabelLinkStyle)
     */
    public PieLabelLinkStyle getLabelLinkStyle() {
        return this.labelLinkStyle;
    }

    /**
     * Sets the label link style for pie charts.
     *
     * @param style  the style (<code>null</code> not permitted).
     *
     * @see #getLabelLinkStyle()
     */
    public void setLabelLinkStyle(PieLabelLinkStyle style) {
        if (style == null) {
            throw new IllegalArgumentException("Null 'style' argument.");
        }
        this.labelLinkStyle = style;
    }

    /**
     * Returns the label link paint for pie charts.
     *
     * @return The label link paint (never <code>null</code>).
     *
     * @see #setLabelLinkPaintType(PaintType paintType)
     */
    public PaintType getLabelLinkPaintType() {
        return this.labelLinkPaintType;
    }

    /**
     * Sets the label link paint for pie charts.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getLabelLinkPaintType()
     */
    public void setLabelLinkPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.labelLinkPaintType = paintType;
    }

    /**
     * Returns the domain grid line paint.
     *
     * @return The domain grid line paint (never <code>null<code>).
     *
     * @see #setDomainGridlinePaintType(PaintType paintType)
     */
    public PaintType getDomainGridlinePaintType() {
        return this.domainGridlinePaintType;
    }

    /**
     * Sets the domain grid line paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getDomainGridlinePaintType()
     */
    public void setDomainGridlinePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.domainGridlinePaintType = paintType;
    }

    /**
     * Returns the range grid line paint.
     *
     * @return The range grid line paint (never <code>null</code>).
     *
     * @see #setRangeGridlinePaintType(PaintType paintType)
     */
    public PaintType getRangeGridlinePaintType() {
        return this.rangeGridlinePaintType;
    }

    /**
     * Sets the range grid line paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getRangeGridlinePaintType()
     */
    public void setRangeGridlinePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.rangeGridlinePaintType = paintType;
    }

    /**
     * Returns the baseline paint.
     * 
     * @return The baseline paint.
     * 
     * @since JFreeChart 1.0.13
     */
    public PaintType getBaselinePaintType() {
        return this.baselinePaintType;
    }

    /**
     * Sets the baseline paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @since JFreeChart 1.0.13
     */
    public void setBaselinePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.baselinePaintType = paintType;
    }

    /**
     * Returns the crosshair paint.
     *
     * @return The crosshair paint.
     */
    public PaintType getCrosshairPaintType() {
        return this.crosshairPaintType;
    }

    /**
     * Sets the crosshair paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     */
    public void setCrosshairPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.crosshairPaintType = paintType;
    }

    /**
     * Returns the axis offsets.
     *
     * @return The axis offsets (never <code>null</code>).
     *
     * @see #setAxisOffset(RectangleInsets)
     */
    public RectangleInsets getAxisOffset() {
        return this.axisOffset;
    }

    /**
     * Sets the axis offset.
     *
     * @param offset  the offset (<code>null</code> not permitted).
     *
     * @see #getAxisOffset()
     */
    public void setAxisOffset(RectangleInsets offset) {
        if (offset == null) {
            throw new IllegalArgumentException("Null 'offset' argument.");
        }
        this.axisOffset = offset;
    }

    /**
     * Returns the axis label paint.
     *
     * @return The axis label paint (never <code>null</code>).
     *
     * @see #setAxisLabelPaintType(PaintType paintType)
     */
    public PaintType getAxisLabelPaintType() {
        return this.axisLabelPaintType;
    }

    /**
     * Sets the axis label paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getAxisLabelPaintType()
     */
    public void setAxisLabelPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.axisLabelPaintType = paintType;
    }

    /**
     * Returns the tick label paint.
     *
     * @return The tick label paint (never <code>null</code>).
     *
     * @see #setTickLabelPaintType(PaintType paintType)
     */
    public PaintType getTickLabelPaintType() {
        return this.tickLabelPaintType;
    }

    /**
     * Sets the tick label paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getTickLabelPaintType()
     */
    public void setTickLabelPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.tickLabelPaintType = paintType;
    }

    /**
     * Returns the item label paint.
     *
     * @return The item label paint (never <code>null</code>).
     *
     * @see #setItemLabelPaintType(PaintType paintType)
     */
    public PaintType getItemLabelPaintType() {
        return this.itemLabelPaintType;
    }

    /**
     * Sets the item label paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getItemLabelPaintType()
     */
    public void setItemLabelPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.itemLabelPaintType = paintType;
    }

    /**
     * Returns the shadow visibility flag.
     *
     * @return The shadow visibility flag.
     *
     * @see #setShadowVisible(boolean)
     */
    public boolean isShadowVisible() {
        return this.shadowVisible;
    }

    /**
     * Sets the shadow visibility flag.
     *
     * @param visible  the flag.
     *
     * @see #isShadowVisible()
     */
    public void setShadowVisible(boolean visible) {
        this.shadowVisible = visible;
    }

    /**
     * Returns the shadow paint.
     *
     * @return The shadow paint (never <code>null</code>).
     *
     * @see #setShadowPaintType(PaintType paintType)
     */
    public PaintType getShadowPaintType() {
        return this.shadowPaintType;
    }

    /**
     * Sets the shadow paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getShadowPaintType()
     */
    public void setShadowPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.shadowPaintType = paintType;
    }

    /**
     * Returns the bar painter.
     *
     * @return The bar painter (never <code>null</code>).
     *
     * @see #setBarPainter(BarPainter)
     */
    public BarPainter getBarPainter() {
        return this.barPainter;
    }

    /**
     * Sets the bar painter.
     *
     * @param painter  the painter (<code>null</code> not permitted).
     *
     * @see #getBarPainter()
     */
    public void setBarPainter(BarPainter painter) {
        if (painter == null) {
            throw new IllegalArgumentException("Null 'painter' argument.");
        }
        this.barPainter = painter;
    }

    /**
     * Returns the XY bar painter.
     *
     * @return The XY bar painter (never <code>null</code>).
     *
     * @see #setXYBarPainter(XYBarPainter)
     */
    public XYBarPainter getXYBarPainter() {
        return this.xyBarPainter;
    }

    /**
     * Sets the XY bar painter.
     *
     * @param painter  the painter (<code>null</code> not permitted).
     *
     * @see #getXYBarPainter()
     */
    public void setXYBarPainter(XYBarPainter painter) {
        if (painter == null) {
            throw new IllegalArgumentException("Null 'painter' argument.");
        }
        this.xyBarPainter = painter;
    }

    /**
     * Returns the thermometer paint.
     *
     * @return The thermometer paint (never <code>null</code>).
     *
     * @see #setThermometerPaintType(PaintType paintType)
     */
    public PaintType getThermometerPaintType() {
        return this.thermometerPaint;
    }

    /**
     * Sets the thermometer paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getThermometerPaintType()
     */
    public void setThermometerPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.thermometerPaint = paintType;
    }

    /**
     * Returns the wall paint for charts with a 3D effect.
     *
     * @return The wall paint (never <code>null</code>).
     *
     * @see #setWallPaintType(PaintType paintType)
     */
    public PaintType getWallPaintType() {
        return this.wallPaintType;
    }

    /**
     * Sets the wall paint for charts with a 3D effect.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getWallPaintType()
     */
    public void setWallPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.wallPaintType = paintType;
    }

    /**
     * Returns the error indicator paint.
     *
     * @return The error indicator paint (never <code>null</code>).
     *
     * @see #setErrorIndicatorPaintType(PaintType paintType)
     */
    public PaintType getErrorIndicatorPaintType() {
        return this.errorIndicatorPaintType;
    }

    /**
     * Sets the error indicator paint.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getErrorIndicatorPaintType()
     */
    public void setErrorIndicatorPaintType(PaintType paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.errorIndicatorPaintType = paint;
    }

    /**
     * Returns the grid band paint.
     *
     * @return The grid band paint (never <code>null</code>).
     *
     * @see #setGridBandPaintType(PaintType paintType)
     */
    public PaintType getGridBandPaintType() {
        return this.gridBandPaintType;
    }

    /**
     * Sets the grid band paint.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getGridBandPaintType()
     */
    public void setGridBandPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.gridBandPaintType = paintType;
    }

    /**
     * Returns the grid band alternate paint (used for a {@link SymbolAxis}).
     *
     * @return The paint type (never <code>null</code>).
     *
     * @see #setGridBandAlternatePaintType(PaintType paintType)
     */
    public PaintType getGridBandAlternatePaintType() {
        return this.gridBandAlternatePaintType;
    }

    /**
     * Sets the grid band alternate paint (used for a {@link SymbolAxis}).
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getGridBandAlternatePaintType()
     */
    public void setGridBandAlternatePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.gridBandAlternatePaintType = paintType;
    }

    /**
     * Returns the name of this theme.
     *
     * @return The name of this theme.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a clone of the drawing supplier for this theme.
     *
     * @return A clone of the drawing supplier.
     */
    public DrawingSupplier getDrawingSupplier() {
        DrawingSupplier result = null;
        if (this.drawingSupplierType instanceof PublicCloneable) {
            PublicCloneable pc = (PublicCloneable) this.drawingSupplierType;
              try {
                result = (DrawingSupplier) pc.clone();
            }
            catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Sets the drawing supplier for this theme.
     *
     * @param supplier  the supplier (<code>null</code> not permitted).
     *
     * @see #getDrawingSupplier()
     */
    public void setDrawingSupplier(DrawingSupplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Null 'supplier' argument.");
        }
        this.drawingSupplierType = supplier;
    }

    /**
     * Applies this theme to the supplied chart.
     *
     * @param chart  the chart (<code>null</code> not permitted).
     */
    public void apply(AFreeChart chart) {
        if (chart == null) {
            throw new IllegalArgumentException("Null 'chart' argument.");
        }
        TextTitle title = chart.getTitle();
        if (title != null) {
            title.setFont(this.extraLargeFont);
            title.setPaintType(this.titlePaintType);
        }

        int subtitleCount = chart.getSubtitleCount();
        for (int i = 0; i < subtitleCount; i++) {
            applyToTitle(chart.getSubtitle(i));
        }

        chart.setBackgroundPaintType(this.chartBackgroundPaintType);

        // now process the plot if there is one
        Plot plot = chart.getPlot();
        if (plot != null) {
            applyToPlot(plot);
        }
    }

    /**
     * Applies the attributes of this theme to the specified title.
     *
     * @param title  the title.
     */
    protected void applyToTitle(Title title) {
        if (title instanceof TextTitle) {
            TextTitle tt = (TextTitle) title;
            tt.setFont(this.largeFont);
            tt.setPaintType(this.subtitlePaintType);
        }
        else if (title instanceof LegendTitle) {
            LegendTitle lt = (LegendTitle) title;
            if (lt.getBackgroundPaintType() != null) {
                lt.setBackgroundPaintType(this.legendBackgroundPaintType);
            }
            lt.setItemFont(this.regularFont);
            lt.setItemPaintType(this.legendItemPaintType);
            if (lt.getWrapper() != null) {
                applyToBlockContainer(lt.getWrapper());
            }
        }
//        else if (title instanceof PaintScaleLegend) {
//            PaintScaleLegend psl = (PaintScaleLegend) title;
//            psl.setBackgroundPaint(this.legendBackgroundPaint);
//            ValueAxis axis = psl.getAxis();
//            if (axis != null) {
//                applyToValueAxis(axis);
//            }
//        }
//        else if (title instanceof CompositeTitle) {
//            CompositeTitle ct = (CompositeTitle) title;
//            BlockContainer bc = ct.getContainer();
//            List blocks = bc.getBlocks();
//            Iterator iterator = blocks.iterator();
//            while (iterator.hasNext()) {
//                Block b = (Block) iterator.next();
//                if (b instanceof Title) {
//                    applyToTitle((Title) b);
//                }
//            }
//        }
    }

    /**
     * Applies the attributes of this theme to the specified container.
     *
     * @param bc  a block container (<code>null</code> not permitted).
     */
    protected void applyToBlockContainer(BlockContainer bc) {
        Iterator iterator = bc.getBlocks().iterator();
        while (iterator.hasNext()) {
            Block b = (Block) iterator.next();
            applyToBlock(b);
        }
    }

    /**
     * Applies the attributes of this theme to the specified block.
     *
     * @param b  the block.
     */
    protected void applyToBlock(Block b) {
        if (b instanceof Title) {
            applyToTitle((Title) b);
        }
        else if (b instanceof LabelBlock) {
            LabelBlock lb = (LabelBlock) b;
            lb.setFont(this.regularFont);
            lb.setPaintType(this.legendItemPaintType);
        }
    }

    /**
     * Applies the attributes of this theme to a plot.
     *
     * @param plot  the plot (<code>null</code>).
     */
    protected void applyToPlot(Plot plot) {
        if (plot == null) {
            throw new IllegalArgumentException("Null 'plot' argument.");
        }
        if (plot.getDrawingSupplier() != null) {
            plot.setDrawingSupplier(getDrawingSupplier());
        }
        if (plot.getBackgroundPaintType() != null) {
            plot.setBackgroundPaintType(this.plotBackgroundPaintType);
        }
        plot.setOutlinePaintType(this.plotOutlinePaintType);

        // now handle specific plot types (and yes, I know this is some
        // really ugly code that has to be manually updated any time a new
        // plot type is added - I should have written something much cooler,
        // but I didn't and neither did anyone else).
        if (plot instanceof PiePlot) {
            applyToPiePlot((PiePlot) plot);
        }
//        else if (plot instanceof MultiplePiePlot) {
//            applyToMultiplePiePlot((MultiplePiePlot) plot);
//        }
        else if (plot instanceof CategoryPlot) {
            applyToCategoryPlot((CategoryPlot) plot);
        }
        else if (plot instanceof XYPlot) {
            applyToXYPlot((XYPlot) plot);
        }
//        else if (plot instanceof FastScatterPlot) {
//            applyToFastScatterPlot((FastScatterPlot) plot);
//        }
//        else if (plot instanceof MeterPlot) {
//            applyToMeterPlot((MeterPlot) plot);
//        }
//        else if (plot instanceof ThermometerPlot) {
//            applyToThermometerPlot((ThermometerPlot) plot);
//        }
//        else if (plot instanceof SpiderWebPlot) {
//            applyToSpiderWebPlot((SpiderWebPlot) plot);
//        }
//        else if (plot instanceof PolarPlot) {
//            applyToPolarPlot((PolarPlot) plot);
//        }
    }

    /**
     * Applies the attributes of this theme to a {@link PiePlot} instance.
     * This method also clears any set values for the section paint, outline
     * etc, so that the theme's {@link DrawingSupplier} will be used.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     */
    protected void applyToPiePlot(PiePlot plot) {
        plot.setLabelLinkPaintType(this.labelLinkPaintType);
        plot.setLabelLinkStyle(this.labelLinkStyle);
        plot.setLabelFont(this.regularFont);

        // clear the section attributes so that the theme's DrawingSupplier
        // will be used
        if (plot.getAutoPopulateSectionPaint()) {
            plot.clearSectionPaints(false);
        }
        if (plot.getAutoPopulateSectionOutlinePaint()) {
            plot.clearSectionOutlinePaints(false);
        }
        if (plot.getAutoPopulateSectionOutlineStroke()) {
            plot.clearSectionOutlineStrokes(false);
        }
    }

    /**
     * Applies the attributes of this theme to a {@link MultiplePiePlot}.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     */
//    protected void applyToMultiplePiePlot(MultiplePiePlot plot) {
//        apply(plot.getPieChart());
//    }

    /**
     * Applies the attributes of this theme to a {@link CategoryPlot}.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     */
    protected void applyToCategoryPlot(CategoryPlot plot) {
        plot.setAxisOffset(this.axisOffset);
        plot.setDomainGridlinePaintType(this.domainGridlinePaintType);
        plot.setRangeGridlinePaintType(this.rangeGridlinePaintType);
        plot.setRangeZeroBaselinePaintType(this.baselinePaintType);

        // process all domain axes
        int domainAxisCount = plot.getDomainAxisCount();
        for (int i = 0; i < domainAxisCount; i++) {
            CategoryAxis axis = plot.getDomainAxis(i);
            if (axis != null) {
                applyToCategoryAxis(axis);
            }
        }

        // process all range axes
        int rangeAxisCount = plot.getRangeAxisCount();
        for (int i = 0; i < rangeAxisCount; i++) {
            ValueAxis axis = (ValueAxis) plot.getRangeAxis(i);
            if (axis != null) {
                applyToValueAxis(axis);
            }
        }

        // process all renderers
        int rendererCount = plot.getRendererCount();
        for (int i = 0; i < rendererCount; i++) {
            CategoryItemRenderer r = plot.getRenderer(i);
            if (r != null) {
                applyToCategoryItemRenderer(r);
            }
        }

        if (plot instanceof CombinedDomainCategoryPlot) {
            CombinedDomainCategoryPlot cp = (CombinedDomainCategoryPlot) plot;
            Iterator iterator = cp.getSubplots().iterator();
            while (iterator.hasNext()) {
                CategoryPlot subplot = (CategoryPlot) iterator.next();
                if (subplot != null) {
                    applyToPlot(subplot);
                }
            }
        }
        if (plot instanceof CombinedRangeCategoryPlot) {
            CombinedRangeCategoryPlot cp = (CombinedRangeCategoryPlot) plot;
            Iterator iterator = cp.getSubplots().iterator();
            while (iterator.hasNext()) {
                CategoryPlot subplot = (CategoryPlot) iterator.next();
                if (subplot != null) {
                    applyToPlot(subplot);
                }
            }
        }
    }

    /**
     * Applies the attributes of this theme to a {@link XYPlot}.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     */
    protected void applyToXYPlot(XYPlot plot) {
        plot.setAxisOffset(this.axisOffset);
        plot.setDomainZeroBaselinePaintType(this.baselinePaintType);
        plot.setRangeZeroBaselinePaintType(this.baselinePaintType);
        plot.setDomainGridlinePaintType(this.domainGridlinePaintType);
        plot.setRangeGridlinePaintType(this.rangeGridlinePaintType);
        plot.setDomainCrosshairPaintType(this.crosshairPaintType);
        plot.setRangeCrosshairPaintType(this.crosshairPaintType);
        // process all domain axes
        int domainAxisCount = plot.getDomainAxisCount();
        for (int i = 0; i < domainAxisCount; i++) {
            ValueAxis axis = plot.getDomainAxis(i);
            if (axis != null) {
                applyToValueAxis(axis);
            }
        }

        // process all range axes
        int rangeAxisCount = plot.getRangeAxisCount();
        for (int i = 0; i < rangeAxisCount; i++) {
            ValueAxis axis = (ValueAxis) plot.getRangeAxis(i);
            if (axis != null) {
                applyToValueAxis(axis);
            }
        }

        // process all renderers
        int rendererCount = plot.getRendererCount();
        for (int i = 0; i < rendererCount; i++) {
            XYItemRenderer r = plot.getRenderer(i);
            if (r != null) {
                applyToXYItemRenderer(r);
            }
        }

        // process all annotations
        Iterator iter = plot.getAnnotations().iterator();
        while (iter.hasNext()) {
            XYAnnotation a = (XYAnnotation) iter.next();
            applyToXYAnnotation(a);
        }

        if (plot instanceof CombinedDomainXYPlot) {
            CombinedDomainXYPlot cp = (CombinedDomainXYPlot) plot;
            Iterator iterator = cp.getSubplots().iterator();
            while (iterator.hasNext()) {
                XYPlot subplot = (XYPlot) iterator.next();
                if (subplot != null) {
                    applyToPlot(subplot);
                }
            }
        }
        if (plot instanceof CombinedRangeXYPlot) {
            CombinedRangeXYPlot cp = (CombinedRangeXYPlot) plot;
            Iterator iterator = cp.getSubplots().iterator();
            while (iterator.hasNext()) {
                XYPlot subplot = (XYPlot) iterator.next();
                if (subplot != null) {
                    applyToPlot(subplot);
                }
            }
        }
    }

    /**
     * Applies the attributes of this theme to a {@link FastScatterPlot}.
     * @param plot
     */
//    protected void applyToFastScatterPlot(FastScatterPlot plot) {
//        plot.setDomainGridlinePaint(this.domainGridlinePaint);
//        plot.setRangeGridlinePaint(this.rangeGridlinePaint);
//        ValueAxis xAxis = plot.getDomainAxis();
//        if (xAxis != null) {
//            applyToValueAxis(xAxis);
//        }
//        ValueAxis yAxis = plot.getRangeAxis();
//        if (yAxis != null) {
//            applyToValueAxis(yAxis);
//        }
//
//    }

    /**
     * Applies the attributes of this theme to a {@link PolarPlot}.  This
     * method is called from the {@link #applyToPlot(Plot)} method.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     */
//    protected void applyToPolarPlot(PolarPlot plot) {
//        plot.setAngleLabelFont(this.regularFont);
//        plot.setAngleLabelPaint(this.tickLabelPaint);
//        plot.setAngleGridlinePaint(this.domainGridlinePaint);
//        plot.setRadiusGridlinePaint(this.rangeGridlinePaint);
//        ValueAxis axis = plot.getAxis();
//        if (axis != null) {
//            applyToValueAxis(axis);
//        }
//    }

    /**
     * Applies the attributes of this theme to a {@link SpiderWebPlot}.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     */
//    protected void applyToSpiderWebPlot(SpiderWebPlot plot) {
//        plot.setLabelFont(this.regularFont);
//        plot.setLabelPaint(this.axisLabelPaint);
//        plot.setAxisLinePaint(this.axisLabelPaint);
//    }

    /**
     * Applies the attributes of this theme to a {@link MeterPlot}.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     */
//    protected void applyToMeterPlot(MeterPlot plot) {
//        plot.setDialBackgroundPaint(this.plotBackgroundPaint);
//        plot.setValueFont(this.largeFont);
//        plot.setValuePaint(this.axisLabelPaint);
//        plot.setDialOutlinePaint(this.plotOutlinePaint);
//        plot.setNeedlePaint(this.thermometerPaint);
//        plot.setTickLabelFont(this.regularFont);
//        plot.setTickLabelPaint(this.tickLabelPaint);
//    }

    /**
     * Applies the attributes for this theme to a {@link ThermometerPlot}.
     * This method is called from the {@link #applyToPlot(Plot)} method.
     *
     * @param plot  the plot.
     */
//    protected void applyToThermometerPlot(ThermometerPlot plot) {
//        plot.setValueFont(this.largeFont);
//        plot.setThermometerPaint(this.thermometerPaint);
//        ValueAxis axis = plot.getRangeAxis();
//        if (axis != null) {
//            applyToValueAxis(axis);
//        }
//    }

    /**
     * Applies the attributes for this theme to a {@link CategoryAxis}.
     *
     * @param axis  the axis (<code>null</code> not permitted).
     */
    protected void applyToCategoryAxis(CategoryAxis axis) {
        axis.setLabelFont(this.largeFont);
        axis.setLabelPaintType(this.axisLabelPaintType);
        axis.setTickLabelFont(this.regularFont);
        axis.setTickLabelPaintType(this.tickLabelPaintType);
//        if (axis instanceof SubCategoryAxis) {
//            SubCategoryAxis sca = (SubCategoryAxis) axis;
//            sca.setSubLabelFont(this.regularFont);
//            sca.setSubLabelPaint(this.tickLabelPaint);
//        }
    }

    /**
     * Applies the attributes for this theme to a {@link ValueAxis}.
     *
     * @param axis  the axis (<code>null</code> not permitted).
     */
    protected void applyToValueAxis(ValueAxis axis) {
        axis.setLabelFont(this.largeFont);
        axis.setLabelPaintType(this.axisLabelPaintType);
        axis.setTickLabelFont(this.regularFont);
        axis.setTickLabelPaintType(this.tickLabelPaintType);
        if (axis instanceof SymbolAxis) {
            applyToSymbolAxis((SymbolAxis) axis);
        }
//        if (axis instanceof PeriodAxis) {
//            applyToPeriodAxis((PeriodAxis) axis);
//        }
    }

    /**
     * Applies the attributes for this theme to a {@link SymbolAxis}.
     *
     * @param axis  the axis (<code>null</code> not permitted).
     */
    protected void applyToSymbolAxis(SymbolAxis axis) {
        axis.setGridBandPaintType(this.gridBandPaintType);
        axis.setGridBandAlternatePaintType(this.gridBandAlternatePaintType);
    }

    /**
     * Applies the attributes for this theme to a {@link PeriodAxis}.
     *
     * @param axis  the axis (<code>null</code> not permitted).
     */
//    protected void applyToPeriodAxis(PeriodAxis axis) {
//        PeriodAxisLabelInfo[] info = axis.getLabelInfo();
//        for (int i = 0; i < info.length; i++) {
//            PeriodAxisLabelInfo e = info[i];
//            PeriodAxisLabelInfo n = new PeriodAxisLabelInfo(e.getPeriodClass(),
//                    e.getDateFormat(), e.getPadding(), this.regularFont,
//                    this.tickLabelPaint, e.getDrawDividers(),
//                    e.getDividerStroke(), e.getDividerPaint());
//            info[i] = n;
//        }
//        axis.setLabelInfo(info);
//    }

    /**
     * Applies the attributes for this theme to an {@link AbstractRenderer}.
     *
     * @param renderer  the renderer (<code>null</code> not permitted).
     */
    protected void applyToAbstractRenderer(AbstractRenderer renderer) {
        if (renderer.getAutoPopulateSeriesPaint()) {
            renderer.clearSeriesPaints(false);
        }
        if (renderer.getAutoPopulateSeriesStroke()) {
            renderer.clearSeriesStrokes(false);
        }
    }

    /**
     * Applies the settings of this theme to the specified renderer.
     *
     * @param renderer  the renderer (<code>null</code> not permitted).
     */
    protected void applyToCategoryItemRenderer(CategoryItemRenderer renderer) {
        if (renderer == null) {
            throw new IllegalArgumentException("Null 'renderer' argument.");
        }

        if (renderer instanceof AbstractRenderer) {
            applyToAbstractRenderer((AbstractRenderer) renderer);
        }

        renderer.setBaseItemLabelFont(this.regularFont);
        renderer.setBaseItemLabelPaintType(this.itemLabelPaintType);

        // now we handle some special cases - yes, UGLY code alert!

        // BarRenderer
        if (renderer instanceof BarRenderer) {
            BarRenderer br = (BarRenderer) renderer;
            br.setBarPainter(this.barPainter);
            br.setShadowVisible(this.shadowVisible);
            br.setShadowPaintType(this.shadowPaintType);
        }

        // BarRenderer3D
        if (renderer instanceof BarRenderer3D) {
            BarRenderer3D br3d = (BarRenderer3D) renderer;
            br3d.setWallPaintType(this.wallPaintType);
        }

//        // LineRenderer3D
//        if (renderer instanceof LineRenderer3D) {
//            LineRenderer3D lr3d = (LineRenderer3D) renderer;
//            lr3d.setWallPaint(this.wallPaint);
//        }
//
//        //  StatisticalBarRenderer
//        if (renderer instanceof StatisticalBarRenderer) {
//            StatisticalBarRenderer sbr = (StatisticalBarRenderer) renderer;
//            sbr.setErrorIndicatorPaint(this.errorIndicatorPaint);
//        }
//
//        // MinMaxCategoryRenderer
//        if (renderer instanceof MinMaxCategoryRenderer) {
//            MinMaxCategoryRenderer mmcr = (MinMaxCategoryRenderer) renderer;
//            mmcr.setGroupPaint(this.errorIndicatorPaint);
//        }
    }

    /**
     * Applies the settings of this theme to the specified renderer.
     *
     * @param renderer  the renderer (<code>null</code> not permitted).
     */
    protected void applyToXYItemRenderer(XYItemRenderer renderer) {
        if (renderer == null) {
            throw new IllegalArgumentException("Null 'renderer' argument.");
        }
        if (renderer instanceof AbstractRenderer) {
            applyToAbstractRenderer((AbstractRenderer) renderer);
        }
        renderer.setBaseItemLabelFont(this.regularFont);
        renderer.setBaseItemLabelPaintType(this.itemLabelPaintType);
        if (renderer instanceof XYBarRenderer) {
            XYBarRenderer br = (XYBarRenderer) renderer;
            br.setBarPainter(this.xyBarPainter);
            br.setShadowVisible(this.shadowVisible);
        }
    }

    /**
     * Applies the settings of this theme to the specified annotation.
     *
     * @param annotation  the annotation.
     */
    protected void applyToXYAnnotation(XYAnnotation annotation) {
        if (annotation == null) {
            throw new IllegalArgumentException("Null 'annotation' argument.");
        }
//        if (annotation instanceof XYTextAnnotation) {
//            XYTextAnnotation xyta = (XYTextAnnotation) annotation;
//            xyta.setFont(this.smallFont);
//            xyta.setPaint(this.itemLabelPaint);
//        }
    }

    /**
     * Tests this theme for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardChartTheme)) {
            return false;
        }
        StandardChartTheme that = (StandardChartTheme) obj;
        if (!this.name.equals(that.name)) {
            return false;
        }
        if (!this.extraLargeFont.equals(that.extraLargeFont)) {
            return false;
        }
        if (!this.largeFont.equals(that.largeFont)) {
            return false;
        }
        if (!this.regularFont.equals(that.regularFont)) {
            return false;
        }
        if (!this.smallFont.equals(that.smallFont)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.titlePaintType, that.titlePaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.subtitlePaintType, that.subtitlePaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.chartBackgroundPaintType,
                that.chartBackgroundPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.legendBackgroundPaintType,
                that.legendBackgroundPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.legendItemPaintType, that.legendItemPaintType)) {
            return false;
        }
        if (!this.drawingSupplierType.equals(that.drawingSupplierType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.plotBackgroundPaintType,
                that.plotBackgroundPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.plotOutlinePaintType,
                that.plotOutlinePaintType)) {
            return false;
        }
        if (!this.labelLinkStyle.equals(that.labelLinkStyle)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.labelLinkPaintType, that.labelLinkPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.domainGridlinePaintType,
                that.domainGridlinePaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.rangeGridlinePaintType,
                that.rangeGridlinePaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.crosshairPaintType, that.crosshairPaintType)) {
            return false;
        }
        if (!this.axisOffset.equals(that.axisOffset)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.axisLabelPaintType, that.axisLabelPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.tickLabelPaintType, that.tickLabelPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.itemLabelPaintType, that.itemLabelPaintType)) {
            return false;
        }
        if (this.shadowVisible != that.shadowVisible) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.shadowPaintType, that.shadowPaintType)) {
            return false;
        }
        if (!this.barPainter.equals(that.barPainter)) {
            return false;
        }
        if (!this.xyBarPainter.equals(that.xyBarPainter)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.thermometerPaint,
                that.thermometerPaint)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.wallPaintType, that.wallPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.errorIndicatorPaintType,
                that.errorIndicatorPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.gridBandPaintType, that.gridBandPaintType)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.gridBandAlternatePaintType,
                that.gridBandAlternatePaintType)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a clone of this theme.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if the theme cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream (<code>null</code> not permitted).
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaintType(this.titlePaintType, stream);
        SerialUtilities.writePaintType(this.subtitlePaintType, stream);
        SerialUtilities.writePaintType(this.chartBackgroundPaintType, stream);
        SerialUtilities.writePaintType(this.legendBackgroundPaintType, stream);
        SerialUtilities.writePaintType(this.legendItemPaintType, stream);
        SerialUtilities.writePaintType(this.plotBackgroundPaintType, stream);
        SerialUtilities.writePaintType(this.plotOutlinePaintType, stream);
        SerialUtilities.writePaintType(this.labelLinkPaintType, stream);
        SerialUtilities.writePaintType(this.baselinePaintType, stream);
        SerialUtilities.writePaintType(this.domainGridlinePaintType, stream);
        SerialUtilities.writePaintType(this.rangeGridlinePaintType, stream);
        SerialUtilities.writePaintType(this.crosshairPaintType, stream);
        SerialUtilities.writePaintType(this.axisLabelPaintType, stream);
        SerialUtilities.writePaintType(this.tickLabelPaintType, stream);
        SerialUtilities.writePaintType(this.itemLabelPaintType, stream);
        SerialUtilities.writePaintType(this.shadowPaintType, stream);
        SerialUtilities.writePaintType(this.thermometerPaint, stream);
        SerialUtilities.writePaintType(this.wallPaintType, stream);
        SerialUtilities.writePaintType(this.errorIndicatorPaintType, stream);
        SerialUtilities.writePaintType(this.gridBandPaintType, stream);
        SerialUtilities.writePaintType(this.gridBandAlternatePaintType, stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream (<code>null</code> not permitted).
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream)
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.titlePaintType = SerialUtilities.readPaintType(stream);
        this.subtitlePaintType = SerialUtilities.readPaintType(stream);
        this.chartBackgroundPaintType = SerialUtilities.readPaintType(stream);
        this.legendBackgroundPaintType = SerialUtilities.readPaintType(stream);
        this.legendItemPaintType = SerialUtilities.readPaintType(stream);
        this.plotBackgroundPaintType = SerialUtilities.readPaintType(stream);
        this.plotOutlinePaintType = SerialUtilities.readPaintType(stream);
        this.labelLinkPaintType = SerialUtilities.readPaintType(stream);
        this.baselinePaintType = SerialUtilities.readPaintType(stream);
        this.domainGridlinePaintType = SerialUtilities.readPaintType(stream);
        this.rangeGridlinePaintType = SerialUtilities.readPaintType(stream);
        this.crosshairPaintType = SerialUtilities.readPaintType(stream);
        this.axisLabelPaintType = SerialUtilities.readPaintType(stream);
        this.tickLabelPaintType = SerialUtilities.readPaintType(stream);
        this.itemLabelPaintType = SerialUtilities.readPaintType(stream);
        this.shadowPaintType = SerialUtilities.readPaintType(stream);
        this.thermometerPaint = SerialUtilities.readPaintType(stream);
        this.wallPaintType = SerialUtilities.readPaintType(stream);
        this.errorIndicatorPaintType = SerialUtilities.readPaintType(stream);
        this.gridBandPaintType = SerialUtilities.readPaintType(stream);
        this.gridBandAlternatePaintType = SerialUtilities.readPaintType(stream);
    }

    private static PaintType createPaintType(int color) {
        return new SolidColor(color);
    }
}
