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
 * ---------------
 * AFreeChart.java
 * ---------------
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
 * 14-Jan-2011 : renamed method name
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Andrzej Porebski;
 *                   David Li;
 *                   Wolfgang Irler;
 *                   Christian W. Zuckschwerdt;
 *                   Klaus Rheinwald;
 *                   Nicolas Brodu;
 *                   Peter Kolb (patch 2603321);
 *
 * NOTE: The above list of contributors lists only the people that have
 * contributed to this source file (JFreeChart.java) - for a list of ALL
 * contributors to the project, please see the README.txt file.
 *
 * Changes (from 20-Jun-2001)
 * --------------------------
 * 20-Jun-2001 : Modifications submitted by Andrzej Porebski for legend
 *               placement;
 * 21-Jun-2001 : Removed JFreeChart parameter from Plot constructors (DG);
 * 22-Jun-2001 : Multiple titles added (original code by David Berry, with
 *               reworkings by DG);
 * 18-Sep-2001 : Updated header (DG);
 * 15-Oct-2001 : Moved data source classes into new package
 *               com.jrefinery.data.* (DG);
 * 18-Oct-2001 : New factory method for creating VerticalXYBarChart (DG);
 * 19-Oct-2001 : Moved series paint and stroke methods to the Plot class (DG);
 *               Moved static chart creation methods to new ChartFactory
 *               class (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 *               Fixed bug where chart isn't registered with the dataset (DG);
 * 07-Nov-2001 : Fixed bug where null title in constructor causes
 *               exception (DG);
 *               Tidied up event notification code (DG);
 * 17-Nov-2001 : Added getLegendItemCount() method (DG);
 * 21-Nov-2001 : Set clipping in draw method to ensure that nothing gets drawn
 *               outside the chart area (DG);
 * 11-Dec-2001 : Added the createBufferedImage() method, taken from the
 *               JFreeChartServletDemo class (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 16-Jan-2002 : Added handleClick() method (DG);
 * 22-Jan-2002 : Fixed bug correlating legend labels with pie data (DG);
 * 05-Feb-2002 : Removed redundant tooltips code (DG);
 * 19-Feb-2002 : Added accessor methods for the backgroundImage and
 *               backgroundImageAlpha attributes (DG);
 * 21-Feb-2002 : Added static fields for INFO, COPYRIGHT, LICENCE, CONTRIBUTORS
 *               and LIBRARIES.  These can be used to display information about
 *               JFreeChart (DG);
 * 06-Mar-2002 : Moved constants to JFreeChartConstants interface (DG);
 * 18-Apr-2002 : PieDataset is no longer sorted (oldman);
 * 23-Apr-2002 : Moved dataset to the Plot class (DG);
 * 13-Jun-2002 : Added an extra draw() method (DG);
 * 25-Jun-2002 : Implemented the Drawable interface and removed redundant
 *               imports (DG);
 * 26-Jun-2002 : Added another createBufferedImage() method (DG);
 * 18-Sep-2002 : Fixed issues reported by Checkstyle (DG);
 * 23-Sep-2002 : Added new contributor (DG);
 * 28-Oct-2002 : Created main title and subtitle list to replace existing title
 *               list (DG);
 * 08-Jan-2003 : Added contributor (DG);
 * 17-Jan-2003 : Added new constructor (DG);
 * 22-Jan-2003 : Added ChartColor class by Cameron Riley, and background image
 *               alignment code by Christian W. Zuckschwerdt (DG);
 * 11-Feb-2003 : Added flag to allow suppression of chart change events, based
 *               on a suggestion by Klaus Rheinwald (DG);
 * 04-Mar-2003 : Added small fix for suppressed chart change events (see bug id
 *               690865) (DG);
 * 10-Mar-2003 : Added Benoit Xhenseval to contributors (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 15-Jul-2003 : Added an optional border for the chart (DG);
 * 11-Sep-2003 : Took care of listeners while cloning (NB);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 22-Sep-2003 : Added nullpointer checks.
 * 25-Sep-2003 : Added nullpointer checks too (NB).
 * 03-Dec-2003 : Legends are now registered by this class instead of using the
 *               old constructor way (TM);
 * 03-Dec-2003 : Added anchorPoint to draw() method (DG);
 * 08-Jan-2004 : Reworked title code, introducing line wrapping (DG);
 * 09-Feb-2004 : Created additional createBufferedImage() method (DG);
 * 05-Apr-2004 : Added new createBufferedImage() method (DG);
 * 27-May-2004 : Moved constants from JFreeChartConstants.java back to this
 *               class (DG);
 * 25-Nov-2004 : Updates for changes to Title class (DG);
 * 06-Jan-2005 : Change lookup for default background color (DG);
 * 31-Jan-2005 : Added Don Elliott to contributors (DG);
 * 02-Feb-2005 : Added clearSubtitles() method (DG);
 * 03-Feb-2005 : Added Mofeed Shahin to contributors (DG);
 * 08-Feb-2005 : Updated for RectangleConstraint changes (DG);
 * 28-Mar-2005 : Renamed Legend --> OldLegend (DG);
 * 12-Apr-2005 : Added methods to access legend(s) in subtitle list (DG);
 * 13-Apr-2005 : Added removeLegend() and removeSubtitle() methods (DG);
 * 20-Apr-2005 : Modified to collect chart entities from titles and
 *               subtitles (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 06-Jun-2005 : Added addLegend() method and padding attribute, fixed equals()
 *               method (DG);
 * 24-Nov-2005 : Removed OldLegend and related code - don't want to support
 *               this in 1.0.0 final (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 27-Jan-2006 : Updated version number (DG);
 * 07-Dec-2006 : Added some missing credits (DG);
 * 17-Jan-2007 : Added Darren Jung to contributor list (DG);
 * 05-Mar-2007 : Added Sergei Ivanov to the contributor list (DG);
 * 16-Mar-2007 : Modified initial legend border (DG);
 * 22-Mar-2007 : New methods for text anti-aliasing (DG);
 * 16-May-2007 : Fixed argument check in getSubtitle(), copy list in
 *               get/setSubtitles(), and added new addSubtitle(int, Title)
 *               method (DG);
 * 05-Jun-2007 : Add change listener to default legend (DG);
 * 04-Dec-2007 : In createBufferedImage() methods, make the default image type
 *               BufferedImage.TYPE_INT_ARGB (thanks to Klaus Rheinwald) (DG);
 * 05-Dec-2007 : Fixed bug 1749124 (not registering as listener with
 *               TextTitle) (DG);
 * 23-Apr-2008 : Added new contributor (Diego Pierangeli) (DG);
 * 16-May-2008 : Added new contributor (Michael Siemer) (DG);
 * 19-Sep-2008 : Check for title visibility (DG);
 * 18-Dec-2008 : Use ResourceBundleWrapper - see patch 1607918 by
 *               Jess Thrysoee (DG);
 * 19-Mar-2009 : Added entity support - see patch 2603321 by Peter Kolb (DG);
 *
 */

package org.afree.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.afree.ui.Align;
import org.afree.ui.Drawable;
import org.afree.ui.HorizontalAlignment;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.ui.Size2D;
import org.afree.ui.VerticalAlignment;
import org.afree.util.ObjectUtilities;
import org.afree.util.PaintTypeUtilities;
import org.afree.chart.block.BlockParams;
import org.afree.chart.block.EntityBlockResult;
import org.afree.chart.block.LengthConstraintType;
import org.afree.chart.block.LineBorder;
import org.afree.chart.block.RectangleConstraint;
import org.afree.data.Range;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.entity.AFreeChartEntity;
import org.afree.chart.event.ChartChangeEvent;
import org.afree.chart.event.ChartChangeListener;
import org.afree.chart.event.ChartProgressEvent;
import org.afree.chart.event.ChartProgressListener;
import org.afree.chart.event.PlotChangeEvent;
import org.afree.chart.event.PlotChangeListener;
import org.afree.chart.event.TitleChangeEvent;
import org.afree.chart.event.TitleChangeListener;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.title.LegendTitle;
import org.afree.chart.title.TextTitle;
import org.afree.chart.title.Title;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Region.Op;

/**
 * A chart class implemented using the Android APIs. The current version
 * supports bar charts, line charts, pie charts and xy plots (including time
 * series data).
 * <P>
 * AFreeChart coordinates several objects to achieve its aim of being able to
 * draw a chart on a graphics device: a list of {@link Title} objects
 * (which often includes the chart's legend), a {@link Plot} and a
 * {@link org.afree.data.general.Dataset} (the plot in turn manages a domain
 * axis and a range axis).
 * <P>
 * The {@link ChartFactory} class contains static methods for creating
 * 'ready-made' charts.
 * 
 * @see ChartFactory
 * @see Title
 * @see Plot
 */
public class AFreeChart implements Drawable, TitleChangeListener,
        PlotChangeListener, Serializable, Cloneable {

    /** For serialization. */
    private static final long serialVersionUID = -3470703747817429120L;

    /** The default font for titles. */
    public static final Font DEFAULT_TITLE_FONT = new Font("SansSerif",
            Typeface.BOLD, 18);

    /** The default background color. */
    public static final PaintType DEFAULT_BACKGROUND_PAINT = new SolidColor(Color.LTGRAY);

    /** The default background image alignment. */
    public static final int DEFAULT_BACKGROUND_IMAGE_ALIGNMENT = Align.FIT;

    /** The default background image alpha. */
    public static final float DEFAULT_BACKGROUND_IMAGE_ALPHA = 0.5f;

    /** A flag that controls whether or not the chart border is drawn. */
    private boolean borderVisible;

    /** The stroke used to draw the chart border (if visible). */
    private transient float borderStroke;

    /** The paint used to draw the chart border (if visible). */
    private transient PaintType borderPaintType;

    /** The padding between the chart border and the chart drawing area. */
    private RectangleInsets padding;

    /** The chart title (optional). */
    private TextTitle title;

    /** The default border effect. */
    public static final PathEffect DEFAULT_BORDER_EFFECT = null;

    /** The effect used to draw the chart border (if visible). */
    private transient PathEffect borderEffect;
    
    /**
     * The chart subtitles (zero, one or many). This field should never be
     * <code>null</code>.
     */
    private List subtitles;

    /** Draws the visual representation of the data. */
    private Plot plot;

    /** Paint used to draw the background of the chart. */
    private transient PaintType backgroundPaintType;

    /** Storage for registered change listeners. */
    private transient List<ChartChangeListener> changeListeners;

    /** Storage for registered progress listeners. */
    private transient List<ChartProgressListener> progressListeners;
    
    /**
     * A flag that can be used to enable/disable notification of chart change
     * events.
     */
    private boolean notify;

    /**
     * Creates a new chart with the given title and plot. The
     * <code>createLegend</code> argument specifies whether or not a legend
     * should be added to the chart. <br>
     * <br>
     * Note that the {@link ChartFactory} class contains a range of static
     * methods that will return ready-made charts, and often this is a more
     * convenient way to create charts than using this constructor.
     * 
     * @param title
     *            the chart title (<code>null</code> permitted).
     * @param titleFont
     *            the font for displaying the chart title (<code>null</code>
     *            permitted).
     * @param plot
     *            controller of the visual representation of the data (
     *            <code>null</code> not permitted).
     * @param createLegend
     *            a flag indicating whether or not a legend should be created
     *            for the chart.
     */
    public AFreeChart(String title, Font titleFont, Plot plot,
            boolean createLegend) {

        if (plot == null) {
            throw new NullPointerException("Null 'plot' argument.");
        }
        // create storage for listeners...
        this.progressListeners = new CopyOnWriteArrayList<ChartProgressListener>();
        this.changeListeners = new CopyOnWriteArrayList<ChartChangeListener>();
        this.notify = true; // default is to notify listeners when the

        this.borderVisible = true;
        this.borderStroke = 2;
        this.borderPaintType = new SolidColor(Color.WHITE);
        this.borderEffect = DEFAULT_BORDER_EFFECT;

        this.plot = plot;
        plot.addChangeListener(this);

        this.subtitles = new ArrayList();

        // create a legend, if requested...
        if (createLegend) {
            LegendTitle legend = new LegendTitle(this.plot);
            legend.setMargin(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
            legend.setFrame(new LineBorder());
            PaintType paintType = new SolidColor(Color.WHITE);
            legend.setBackgroundPaintType(paintType);
            legend.setPosition(RectangleEdge.BOTTOM);
            this.subtitles.add(legend);
            legend.addChangeListener(this);
        }

        // add the chart title, if one has been specified...
        if (title != null) {
            if (titleFont == null) {
                titleFont = DEFAULT_TITLE_FONT;
            }
            this.title = new TextTitle(title, titleFont);
            this.title.addChangeListener(this);
        }

        this.backgroundPaintType = DEFAULT_BACKGROUND_PAINT;

    }

    /**
     * Creates a new chart with the given title and plot.  A default font
     * ({@link #DEFAULT_TITLE_FONT}) is used for the title, and the chart will
     * have a legend added automatically.
     * <br><br>
     * Note that the {@link ChartFactory} class contains a range
     * of static methods that will return ready-made charts, and often this
     * is a more convenient way to create charts than using this constructor.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param plot  the plot (<code>null</code> not permitted).
     */
    public AFreeChart(String title, Plot plot) {
        this(title, AFreeChart.DEFAULT_TITLE_FONT, plot, true);
    }
    
    /**
     * Creates a new chart based on the supplied plot.  The chart will have
     * a legend added automatically, but no title (although you can easily add
     * one later).
     * <br><br>
     * Note that the  {@link ChartFactory} class contains a range
     * of static methods that will return ready-made charts, and often this
     * is a more convenient way to create charts than using this constructor.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     */
    public AFreeChart(Plot plot) {
        this(null, null, plot, true);
    }
    
//    /**
//     * Returns the collection of rendering hints for the chart.
//     *
//     * @return The rendering hints for the chart (never <code>null</code>).
//     *
//     * @see #setRenderingHints(RenderingHints)
//     */
//    public RenderingHints getRenderingHints() {
//        return this.renderingHints;
//    }
//
//    /**
//     * Sets the rendering hints for the chart.  These will be added (using the
//     * Graphics2D.addRenderingHints() method) near the start of the
//     * JFreeChart.draw() method.
//     *
//     * @param renderingHints  the rendering hints (<code>null</code> not
//     *                        permitted).
//     *
//     * @see #getRenderingHints()
//     */
//    public void setRenderingHints(RenderingHints renderingHints) {
//        if (renderingHints == null) {
//            throw new NullPointerException("RenderingHints given are null");
//        }
//        this.renderingHints = renderingHints;
//        fireChartChanged();
//    }
    
    /**
     * Returns a flag that controls whether or not a border is drawn around the
     * outside of the chart.
     * 
     * @return A boolean.
     * 
     * @see #setBorderVisible(boolean)
     */
    public boolean isBorderVisible() {
        return this.borderVisible;
    }

    /**
     * Sets a flag that controls whether or not a border is drawn around the
     * outside of the chart.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #isBorderVisible()
     */
    public void setBorderVisible(boolean visible) {
        this.borderVisible = visible;
        fireChartChanged();
    }

    /**
     * Returns the stroke used to draw the chart border (if visible).
     * 
     * @return The border stroke.
     * 
     * @see #setBorderStroke(float stroke)
     */
    public float getBorderStroke() {
        return this.borderStroke;
    }

    /**
     * Sets the stroke used to draw the chart border (if visible).
     * 
     * @param stroke
     *            the stroke.
     * 
     * @see #getBorderStroke()
     */
    public void setBorderStroke(float stroke) {
        this.borderStroke = stroke;
        fireChartChanged();
    }

    /**
     * Returns the paint used to draw the chart border (if visible).
     * 
     * @return The border paint.
     * 
     * @see #setBorderPaintType(PaintType paintType)
     */
    public PaintType getBorderPaintType() {
        return this.borderPaintType;
    }

    /**
     * Sets the paint used to draw the chart border (if visible).
     * 
     * @param paintType
     *            the paintType.
     * 
     * @see #getBorderPaintType()
     */
    public void setBorderPaintType(PaintType paintType) {
        this.borderPaintType = paintType;
        fireChartChanged();
    }

    /**
     * Returns the padding between the chart border and the chart drawing area.
     * 
     * @return The padding (never <code>null</code>).
     * 
     * @see #setPadding(RectangleInsets)
     */
    public RectangleInsets getPadding() {
        return this.padding;
    }

    /**
     * Sets the padding between the chart border and the chart drawing area, and
     * sends a {@link ChartChangeEvent} to all registered listeners.
     * 
     * @param padding
     *            the padding (<code>null</code> not permitted).
     * 
     * @see #getPadding()
     */
    public void setPadding(RectangleInsets padding) {
        if (padding == null) {
            throw new IllegalArgumentException("Null 'padding' argument.");
        }
        this.padding = padding;
        notifyListeners(new ChartChangeEvent(this));
    }

    /**
     * Returns the main chart title. Very often a chart will have just one
     * title, so we make this case simple by providing accessor methods for the
     * main title. However, multiple titles are supported - see the
     * {@link #addSubtitle(Title)} method.
     * 
     * @return The chart title (possibly <code>null</code>).
     * 
     * @see #setTitle(TextTitle)
     */
    public TextTitle getTitle() {
        return this.title;
    }

    /**
     * Sets the main title for the chart and sends a {@link ChartChangeEvent} to
     * all registered listeners. If you do not want a title for the chart, set
     * it to <code>null</code>. If you want more than one title on a chart, use
     * the {@link #addSubtitle(Title)} method.
     * 
     * @param title
     *            the title (<code>null</code> permitted).
     * 
     * @see #getTitle()
     */
    public void setTitle(TextTitle title) {
        if (this.title != null) {
            this.title.removeChangeListener(this);
        }
        this.title = title;
        if (title != null) {
            title.addChangeListener(this);
        }
        fireChartChanged();
    }

    /**
     * Sets the effect used to draw the chart border (if visible).
     * 
     * @param effect
     *            the effect.
     * 
     * @see #getBorderEffect()
     */
    public void setBorderEffect(PathEffect effect) {
        this.borderEffect = effect;
        fireChartChanged();
    }
    
    /**
     * Sets the chart title and sends a {@link ChartChangeEvent} to all
     * registered listeners. This is a convenience method that ends up calling
     * the {@link #setTitle(TextTitle)} method. If there is an existing title,
     * its text is updated, otherwise a new title using the default font is
     * added to the chart. If <code>text</code> is <code>null</code> the chart
     * title is set to <code>null</code>.
     * 
     * @param text
     *            the title text (<code>null</code> permitted).
     * 
     * @see #getTitle()
     */
    public void setTitle(String text) {
        if (text != null) {
            if (this.title == null) {
                setTitle(new TextTitle(text, AFreeChart.DEFAULT_TITLE_FONT));
            } else {
                this.title.setText(text);
            }
        } else {
            setTitle((TextTitle) null);
        }
    }

    /**
     * Adds a legend to the plot and sends a {@link ChartChangeEvent} to all
     * registered listeners.
     * 
     * @param legend
     *            the legend (<code>null</code> not permitted).
     * 
     * @see #removeLegend()
     */
    public void addLegend(LegendTitle legend) {
        addSubtitle(legend);
    }

    public void addSubtitle(Title subtitle) {
        if (subtitle == null) {
            throw new IllegalArgumentException("Null 'subtitle' argument.");
        }
        this.subtitles.add(subtitle);
        subtitle.addChangeListener(this);
        fireChartChanged();
    }

    /**
     * Adds a subtitle at a particular position in the subtitle list, and sends
     * a {@link ChartChangeEvent} to all registered listeners.
     *
     * @param index  the index (in the range 0 to {@link #getSubtitleCount()}).
     * @param subtitle  the subtitle to add (<code>null</code> not permitted).
     *
     * @since 1.0.6
     */
    public void addSubtitle(int index, Title subtitle) {
        if (index < 0 || index > getSubtitleCount()) {
            throw new IllegalArgumentException(
                    "The 'index' argument is out of range.");
        }
        if (subtitle == null) {
            throw new IllegalArgumentException("Null 'subtitle' argument.");
        }
        this.subtitles.add(index, subtitle);
        subtitle.addChangeListener(this);
        fireChartChanged();
    }

    /**
     * Returns the legend for the chart, if there is one. Note that a chart can
     * have more than one legend - this method returns the first.
     * 
     * @return The legend (possibly <code>null</code>).
     * 
     * @see #getLegend(int)
     */
    public LegendTitle getLegend() {
        return getLegend(0);
    }

    /**
     * Returns the nth legend for a chart, or <code>null</code>.
     * 
     * @param index
     *            the legend index (zero-based).
     * 
     * @return The legend (possibly <code>null</code>).
     * 
     * @see #addLegend(LegendTitle)
     */
    public LegendTitle getLegend(int index) {
        int seen = 0;
        Iterator iterator = this.subtitles.iterator();
        while (iterator.hasNext()) {
            Title subtitle = (Title) iterator.next();
            if (subtitle instanceof LegendTitle) {
                if (seen == index) {
                    return (LegendTitle) subtitle;
                } else {
                    seen++;
                }
            }
        }
        return null;
    }

    /**
     * Removes the first legend in the chart and sends a
     * {@link ChartChangeEvent} to all registered listeners.
     * 
     * @see #getLegend()
     */
    public void removeLegend() {
        removeSubtitle(getLegend());
    }

    public void removeSubtitle(Title title) {
        this.subtitles.remove(title);
        fireChartChanged();
    }

    /**
     * Returns the list of subtitles for the chart.
     * 
     * @return The subtitle list (possibly empty, but never <code>null</code>).
     * 
     * @see #setSubtitles(List)
     */
    public List getSubtitles() {
        return new ArrayList(this.subtitles);
    }

    /**
     * Sets the title list for the chart (completely replaces any existing
     * titles) and sends a {@link ChartChangeEvent} to all registered listeners.
     * 
     * @param subtitles
     *            the new list of subtitles (<code>null</code> not permitted).
     * 
     * @see #getSubtitles()
     */
    public void setSubtitles(List subtitles) {
        if (subtitles == null) {
            throw new NullPointerException("Null 'subtitles' argument.");
        }
        // setNotify(false);
        clearSubtitles();
        Iterator iterator = subtitles.iterator();
        while (iterator.hasNext()) {
            Title t = (Title) iterator.next();
            if (t != null) {
                addSubtitle(t);
            }
        }
    }

    public void clearSubtitles() {
        Iterator iterator = this.subtitles.iterator();
        while (iterator.hasNext()) {
            Title t = (Title) iterator.next();
            t.removeChangeListener(this);
        }
        this.subtitles.clear();
        fireChartChanged();
    }

    /**
     * Returns the number of titles for the chart.
     * 
     * @return The number of titles for the chart.
     * 
     * @see #getSubtitles()
     */
    public int getSubtitleCount() {
        return this.subtitles.size();
    }

    /**
     * Returns a chart subtitle.
     * 
     * @param index
     *            the index of the chart subtitle (zero based).
     * 
     * @return A chart subtitle.
     * 
     * @see #addSubtitle(Title)
     */
    public Title getSubtitle(int index) {
        if ((index < 0) || (index >= getSubtitleCount())) {
            throw new IllegalArgumentException("Index out of range.");
        }
        return (Title) this.subtitles.get(index);
    }

    /**
     * Returns the plot for the chart. The plot is a class responsible for
     * coordinating the visual representation of the data, including the axes
     * (if any).
     * 
     * @return The plot.
     */
    public Plot getPlot() {
        return this.plot;
    }

    /**
     * Returns the plot cast as a {@link CategoryPlot}.
     * <p>
     * NOTE: if the plot is not an instance of {@link CategoryPlot}, then a
     * <code>ClassCastException</code> is thrown.
     * 
     * @return The plot.
     * 
     * @see #getPlot()
     */
    public CategoryPlot getCategoryPlot() {
        return (CategoryPlot) this.plot;
    }

    /**
     * Returns the plot cast as an {@link XYPlot}.
     * <p>
     * NOTE: if the plot is not an instance of {@link XYPlot}, then a
     * <code>ClassCastException</code> is thrown.
     * 
     * @return The plot.
     * 
     * @see #getPlot()
     */
    public XYPlot getXYPlot() {
        return (XYPlot) this.plot;
    }

    /**
     * Returns the effect used to draw the chart border (if visible).
     * 
     * @return The border effect.
     * 
     * @see #setBorderEffect(PathEffect effect)
     */
    public PathEffect getBorderEffect() {
        return this.borderEffect;
    }
    
    /**
     * Returns a flag that indicates whether or not anti-aliasing is used when
     * the chart is drawn.
     * 
     * @return The flag.
     * 
     * @see #setAntiAlias(boolean)
     */
    // public boolean getAntiAlias() {
    // Object val = this.renderingHints.get(RenderingHints.KEY_ANTIALIASING);
    // return RenderingHints.VALUE_ANTIALIAS_ON.equals(val);
    // }
    /**
     * Sets a flag that indicates whether or not anti-aliasing is used when the
     * chart is drawn.
     * <P>
     * Anti-aliasing usually improves the appearance of charts, but is slower.
     * 
     * @param flag
     *            the new value of the flag.
     * 
     * @see #getAntiAlias()
     */
    // public void setAntiAlias(boolean flag) {
    // Object val = this.renderingHints.get(RenderingHints.KEY_ANTIALIASING);
    // if (val == null) {
    // val = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
    // }
    // if (!flag && RenderingHints.VALUE_ANTIALIAS_OFF.equals(val)
    /*
     * || flag && RenderingHints.VALUE_ANTIALIAS_ON.equals(val)) { // no change,
     * do nothing return; } if (flag) {
     * this.renderingHints.put(RenderingHints.KEY_ANTIALIASING,
     * RenderingHints.VALUE_ANTIALIAS_ON); } else {
     * this.renderingHints.put(RenderingHints.KEY_ANTIALIASING,
     * RenderingHints.VALUE_ANTIALIAS_OFF); fireChartChanged(); }
     * 
     * }
     */

    /**
     * Returns the current value stored in the rendering hints table for
     * {@link RenderingHints#KEY_TEXT_ANTIALIASING}.
     * 
     * @return The hint value (possibly <code>null</code>).
     * 
     * @since JFreeChart 1.0.5
     * 
     * @see #setTextAntiAlias(Object)
     */
    /*
     * public Object getTextAntiAlias() { return
     * this.renderingHints.get(RenderingHints.KEY_TEXT_ANTIALIASING);
     *//**
     * Sets the value in the rendering hints table for
     * {@link RenderingHints#KEY_TEXT_ANTIALIASING} to either
     * {@link RenderingHints#VALUE_TEXT_ANTIALIAS_ON} or
     * {@link RenderingHints#VALUE_TEXT_ANTIALIAS_OFF}, then sends a
     * {@link ChartChangeEvent} to all registered listeners.
     * 
     * @param flag
     *            the new value of the flag.
     * 
     * @since JFreeChart 1.0.5
     * 
     * @see #getTextAntiAlias()
     * @see #setTextAntiAlias(Object)
     */
    /*
     * public void setTextAntiAlias(boolean flag) { if (flag) {
     * setTextAntiAlias(RenderingHints.VALUE_TEXT_ANTIALIAS_ON); } else {
     * setTextAntiAlias(RenderingHints.VALUE_TEXT_ANTIALIAS_OFF); } }
     */

    /**
     * Sets the value in the rendering hints table for
     * {@link RenderingHints#KEY_TEXT_ANTIALIASING} and sends a
     * {@link ChartChangeEvent} to all registered listeners.
     * 
     * @param val
     *            the new value (<code>null</code> permitted).
     * 
     * @since JFreeChart 1.0.5
     * 
     * @see #getTextAntiAlias()
     * @see #setTextAntiAlias(boolean)
     */
    // public void setTextAntiAlias(Object val) {
    // this.renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, val);
    // notifyListeners(new ChartChangeEvent(this));
    // }
    /**
     * Returns the paint used for the chart background.
     * 
     * @return The paint type (possibly <code>null</code>).
     * 
     * @see #setBackgroundPaintType(PaintType paintType)
     */
    public PaintType getBackgroundPaintType() {
        return this.backgroundPaintType;
    }

    /**
     * Sets the paint used to fill the chart background and sends a
     * {@link ChartChangeEvent} to all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> permitted).
     * 
     * @see #getBackgroundPaintType()
     */
    public void setBackgroundPaintType(PaintType paintType) {

        if (this.backgroundPaintType != null) {
            if (!this.backgroundPaintType.equals(paintType)) {
                this.backgroundPaintType = paintType;
                fireChartChanged();
            }
        } else {
            if (paintType != null) {
                this.backgroundPaintType = paintType;
                fireChartChanged();
            }
        }

    }


//    /**
//     * Returns the background image for the chart, or <code>null</code> if
//     * there is no image.
//     *
//     * @return The image (possibly <code>null</code>).
//     *
//     * @see #setBackgroundImage(Image)
//     */
//    public Image getBackgroundImage() {
//        return this.backgroundImage;
//    }
//
//    /**
//     * Sets the background image for the chart and sends a
//     * {@link ChartChangeEvent} to all registered listeners.
//     *
//     * @param image  the image (<code>null</code> permitted).
//     *
//     * @see #getBackgroundImage()
//     */
//    public void setBackgroundImage(Image image) {
//
//        if (this.backgroundImage != null) {
//            if (!this.backgroundImage.equals(image)) {
//                this.backgroundImage = image;
//                fireChartChanged();
//            }
//        }
//        else {
//            if (image != null) {
//                this.backgroundImage = image;
//                fireChartChanged();
//            }
//        }
//
//    }
//
//    /**
//     * Returns the background image alignment. Alignment constants are defined
//     * in the <code>org.jfree.ui.Align</code> class in the JCommon class
//     * library.
//     *
//     * @return The alignment.
//     *
//     * @see #setBackgroundImageAlignment(int)
//     */
//    public int getBackgroundImageAlignment() {
//        return this.backgroundImageAlignment;
//    }
//
//    /**
//     * Sets the background alignment.  Alignment options are defined by the
//     * {@link org.jfree.ui.Align} class.
//     *
//     * @param alignment  the alignment.
//     *
//     * @see #getBackgroundImageAlignment()
//     */
//    public void setBackgroundImageAlignment(int alignment) {
//        if (this.backgroundImageAlignment != alignment) {
//            this.backgroundImageAlignment = alignment;
//            fireChartChanged();
//        }
//    }
//
//    /**
//     * Returns the alpha-transparency for the chart's background image.
//     *
//     * @return The alpha-transparency.
//     *
//     * @see #setBackgroundImageAlpha(float)
//     */
//    public float getBackgroundImageAlpha() {
//        return this.backgroundImageAlpha;
//    }
//
//    /**
//     * Sets the alpha-transparency for the chart's background image.
//     * Registered listeners are notified that the chart has been changed.
//     *
//     * @param alpha  the alpha value.
//     *
//     * @see #getBackgroundImageAlpha()
//     */
//    public void setBackgroundImageAlpha(float alpha) {
//
//        if (this.backgroundImageAlpha != alpha) {
//            this.backgroundImageAlpha = alpha;
//            fireChartChanged();
//        }
//
//    }
    
    /**
     * Draws the chart on a graphics device (such as the screen or a
     * printer).
     * <P>
     * This method is the focus of the entire AFreeChart library.
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area within which the chart should be drawn.
     */
    public void draw(Canvas canvas, RectShape area) {
        draw(canvas, area, null, null);
    }

    /**
     * Draws the chart on a graphics device (such as the screen or a
     * printer). This method is the focus of the entire AFreeChart library.
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area within which the chart should be drawn.
     * @param info
     *            records info about the drawing (null means collect no info).
     */
    public void draw(Canvas canvas, RectShape area, ChartRenderingInfo info) {
        draw(canvas, area, null, info);
    }

    /**
     * Draws the chart on a graphics device (such as the screen or a
     * printer).
     * <P>
     * This method is the focus of the entire AFreeChart library.
     * 
     * @param canvas
     *            the graphics device.
     * @param chartArea
     *            the area within which the chart should be drawn.
     * @param anchor
     *            the anchor point (in Java2D space) for the chart (
     *            <code>null</code> permitted).
     * @param info
     *            records info about the drawing (null means collect no info).
     */
    public void draw(Canvas canvas, RectShape chartArea, PointF anchor,
            ChartRenderingInfo info) {
        EntityCollection entities = null;
        
        notifyListeners(new ChartProgressEvent(this, this,
                ChartProgressEvent.DRAWING_STARTED, 0));
        
        // record the chart area, if info is requested...
        if (info != null) {
            info.clear();
            info.setChartArea(chartArea);
            entities = info.getEntityCollection();
        }
        if (entities != null) {
            entities.add(new AFreeChartEntity((RectShape) chartArea.clone(), this));
        }
        
        Rect savedClip = canvas.getClipBounds();
        // ensure no drawing occurs outside chart area...
        canvas.clipRect((float) chartArea.getMinX(), (float) chartArea.getMinY(),
                (float) chartArea.getMaxX() , (float) chartArea.getMaxY());

        // draw the chart background...
        if (this.backgroundPaintType != null) {
            Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, this.backgroundPaintType);
            chartArea.fill(canvas, paint);
        }

        if (isBorderVisible()) {
            PaintType paintType = getBorderPaintType();
            if (paintType != null) {
                RectShape borderArea = new RectShape(chartArea
                        .getX(), chartArea.getY(), chartArea.getWidth() - 1.0,
                        chartArea.getHeight() - 1.0);
                Paint paint = PaintUtility.createPaint(borderPaintType, borderStroke, borderEffect);
                borderArea.draw(canvas, paint);
            }
        }

        // draw the title and subtitles...
        RectShape nonTitleArea = new RectShape();
        nonTitleArea.setRect(chartArea);
        if (this.padding != null)
            this.padding.trim(nonTitleArea);

        if (this.title != null) {
            EntityCollection e = drawTitle(this.title, canvas, nonTitleArea,
                    (entities != null));
            if (e != null) {
                entities.addAll(e);
            }
        }

        Iterator iterator = this.subtitles.iterator();
        while (iterator.hasNext()) {
            Title currentTitle = (Title) iterator.next();
            if (currentTitle.isVisible()) {
                EntityCollection e = drawTitle(currentTitle, canvas, nonTitleArea,
                        (entities != null));
                if (e != null) {
                    entities.addAll(e);
                }
            }
        }
        
        RectShape plotArea = nonTitleArea;

        // draw the plot (axes and data visualisation)
        PlotRenderingInfo plotInfo = null;
        if (info != null) {
            plotInfo = info.getPlotInfo();
        }
        this.plot.draw(canvas, plotArea, anchor, null, plotInfo);

        canvas.clipRect(savedClip, Op.REPLACE);

        notifyListeners(new ChartProgressEvent(this, this,
                ChartProgressEvent.DRAWING_FINISHED, 100));
    }

    /**
     * Creates a RectShape that is aligned to the frame.
     * 
     * @param dimensions
     *            the dimensions for the RectShape.
     * @param frame
     *            the frame to align to.
     * @param hAlign
     *            the horizontal alignment.
     * @param vAlign
     *            the vertical alignment.
     * 
     * @return A RectShape.
     */
    private RectShape createAlignedRectShape(Size2D dimensions,
            RectShape frame, HorizontalAlignment hAlign,
            VerticalAlignment vAlign) {
        double x = Double.NaN;
        double y = Double.NaN;
        if (hAlign == HorizontalAlignment.LEFT) {
            x = frame.getX();
        } else if (hAlign == HorizontalAlignment.CENTER) {
            x = frame.getCenterX() - (dimensions.width / 2.0);
        } else if (hAlign == HorizontalAlignment.RIGHT) {
            x = frame.getMaxX() - dimensions.width;
        }
        if (vAlign == VerticalAlignment.TOP) {
            y = frame.getY();
        } else if (vAlign == VerticalAlignment.CENTER) {
            y = frame.getCenterY() - (dimensions.height / 2.0);
        } else if (vAlign == VerticalAlignment.BOTTOM) {
            y = frame.getMaxY() - dimensions.height;
        }

        return new RectShape(x, y, dimensions.width, dimensions.height);
    }

    /**
     * Draws a title. The title should be drawn at the top, bottom, left or
     * right of the specified area, and the area should be updated to reflect
     * the amount of space used by the title.
     * 
     * @param t
     *            the title (<code>null</code> not permitted).
     * @param canvas
     *            the graphics device (<code>null</code> not permitted).
     * @param area
     *            the chart area, excluding any existing titles (
     *            <code>null</code> not permitted).
     * @param entities
     *            a flag that controls whether or not an entity collection is
     *            returned for the title.
     * 
     * @return An entity collection for the title (possibly <code>null</code>).
     */
    protected EntityCollection drawTitle(Title t, Canvas canvas, RectShape area,
            boolean entities) {

        if (t == null) {
            throw new IllegalArgumentException("Null 't' argument.");
        }
        if (area == null) {
            throw new IllegalArgumentException("Null 'area' argument.");
        }
        RectShape titleArea = new RectShape();
        RectangleEdge position = t.getPosition();
        double ww = area.getWidth();
        if (ww <= 0.0) {
            return null;
        }
        double hh = area.getHeight();
        if (hh <= 0.0) {
            return null;
        }
        RectangleConstraint constraint = new RectangleConstraint(ww, new Range(
                0.0, ww), LengthConstraintType.RANGE, hh, new Range(0.0, hh),
                LengthConstraintType.RANGE);
        Object retValue = null;
        BlockParams p = new BlockParams();
        p.setGenerateEntities(entities);
        if (position == RectangleEdge.TOP) {
            Size2D size = t.arrange(canvas, constraint);
            titleArea = createAlignedRectShape(size, area, t
                    .getHorizontalAlignment(), VerticalAlignment.TOP);
            retValue = t.draw(canvas, titleArea, p);
            area.setRect(area.getX(), Math.min(area.getY() + size.height, area
                    .getMaxY()), area.getWidth(), Math.max(area.getHeight()
                    - size.height, 0));
        } else if (position == RectangleEdge.BOTTOM) {
            Size2D size = t.arrange(canvas, constraint);
            titleArea = createAlignedRectShape(size, area, t
                    .getHorizontalAlignment(), VerticalAlignment.BOTTOM);
            retValue = t.draw(canvas, titleArea, p);
            area.setRect(area.getX(), area.getY(), area.getWidth(), area
                    .getHeight()
                    - size.height);
        } else if (position == RectangleEdge.RIGHT) {
            Size2D size = t.arrange(canvas, constraint);
            titleArea = createAlignedRectShape(size, area,
                    HorizontalAlignment.RIGHT, t.getVerticalAlignment());
            retValue = t.draw(canvas, titleArea, p);
            area.setRect(area.getX(), area.getY(),
                    area.getWidth() - size.width, area.getHeight());
        }

        else if (position == RectangleEdge.LEFT) {
            Size2D size = t.arrange(canvas, constraint);
            titleArea = createAlignedRectShape(size, area,
                    HorizontalAlignment.LEFT, t.getVerticalAlignment());
            retValue = t.draw(canvas, titleArea, p);
            area.setRect(area.getX() + size.width, area.getY(), area.getWidth()
                    - size.width, area.getHeight());
        } else {
            throw new RuntimeException("Unrecognised title position.");
        }
        EntityCollection result = null;
        if (retValue instanceof EntityBlockResult) {
            EntityBlockResult ebr = (EntityBlockResult) retValue;
            result = ebr.getEntityCollection();
        }
        return result;
    }

    /**
     * Handles a 'click' on the chart. AFreeChart is not a UI component, so some
     * other object (for example, {@link DemoView}) needs to capture the click
     * event and pass it onto the AFreeChart object. If you are not using
     * AFreeChart in a client application, then this method is not required.
     * 
     * @param x
     *            x-coordinate of the click (in Java2D space).
     * @param y
     *            y-coordinate of the click (in Java2D space).
     * @param info
     *            contains chart dimension and entity information (
     *            <code>null</code> not permitted).
     */
    public void handleClick(int x, int y, ChartRenderingInfo info) {

        // pass the click on to the plot...
        // rely on the plot to post a plot change event and redraw the chart...
        this.plot.handleClick(x, y, info.getPlotInfo());

    }

    /**
     * Registers an object for notification of changes to the chart.
     *
     * @param listener  the listener (<code>null</code> not permitted).
     *
     * @see #removeChangeListener(ChartChangeListener)
     */
    public void addChangeListener(ChartChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Null 'listener' argument.");
        }
        this.changeListeners.add(listener);
    }

    /**
     * Deregisters an object for notification of changes to the chart.
     *
     * @param listener  the listener (<code>null</code> not permitted)
     *
     * @see #addChangeListener(ChartChangeListener)
     */
    public void removeChangeListener(ChartChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Null 'listener' argument.");
        }
        this.changeListeners.remove(listener);
    }

    /**
     * Sends a default {@link ChartChangeEvent} to all registered listeners.
     * <P>
     * This method is for convenience only.
     */
    public void fireChartChanged() {
        ChartChangeEvent event = new ChartChangeEvent(this);
        notifyListeners(event);
    }

    /**
     * Sends a {@link ChartChangeEvent} to all registered listeners.
     *
     * @param event  information about the event that triggered the
     *               notification.
     */
    protected void notifyListeners(ChartChangeEvent event) {
        if(changeListeners.size() == 0) {
            return;
        }
        if (this.notify) {
            for (int i = changeListeners.size() - 1; i >= 0; i--) {
                changeListeners.get(i).chartChanged(event);
            }

        }
    }

    /**
     * Registers an object for notification of progress events relating to the
     * chart.
     *
     * @param listener  the object being registered.
     *
     * @see #removeProgressListener(ChartProgressListener)
     */
    public void addProgressListener(ChartProgressListener listener) {
        this.progressListeners.add(listener);
    }

    /**
     * Deregisters an object for notification of changes to the chart.
     *
     * @param listener  the object being deregistered.
     *
     * @see #addProgressListener(ChartProgressListener)
     */
    public void removeProgressListener(ChartProgressListener listener) {
        this.progressListeners.remove(listener);
    }

    /**
     * Sends a {@link ChartProgressEvent} to all registered listeners.
     *
     * @param event  information about the event that triggered the
     *               notification.
     */
    protected void notifyListeners(ChartProgressEvent event) {
        if(progressListeners.size() == 0) {
            return;
        }
        for (int i = progressListeners.size() - 1; i >= 0; i--) {
            progressListeners.get(i).chartProgress(event);
        }
    }

    /**
     * Receives notification that a chart title has changed, and passes this
     * on to registered listeners.
     *
     * @param event  information about the chart title change.
     */
    public void titleChanged(TitleChangeEvent event) {
        event.setChart(this);
        notifyListeners(event);
    }

    /**
     * Receives notification that the plot has changed, and passes this on to
     * registered listeners.
     *
     * @param event  information about the plot change.
     */
    public void plotChanged(PlotChangeEvent event) {
        event.setChart(this);
        notifyListeners(event);
    }
    
    /**
     * Clones the object, and takes care of listeners. Note: caller shall
     * register its own listeners on cloned graph.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException
     *             if the chart is not cloneable.
     */
    public Object clone() throws CloneNotSupportedException {
        AFreeChart chart = (AFreeChart) super.clone();

//        chart.renderingHints = (RenderingHints) this.renderingHints.clone();
        // private boolean borderVisible;
        // private transient Stroke borderStroke;
        // private transient Paint borderPaint;

        if (this.title != null) {
            chart.title = (TextTitle) this.title.clone();
            chart.title.addChangeListener(chart);
        }

        chart.subtitles = new ArrayList();
        for (int i = 0; i < getSubtitleCount(); i++) {
            Title subtitle = (Title) getSubtitle(i).clone();
            chart.subtitles.add(subtitle);
            subtitle.addChangeListener(chart);
        }

        if (this.plot != null) {
            chart.plot = (Plot) this.plot.clone();
            chart.plot.addChangeListener(chart);
        }

        chart.progressListeners = new CopyOnWriteArrayList<ChartProgressListener>();
        chart.changeListeners = new CopyOnWriteArrayList<ChartChangeListener>();
        return chart;
    }

    /**
     * Sets a flag that controls whether or not listeners receive
     * {@link ChartChangeEvent} notifications.
     * 
     * @param notify
     *            a boolean.
     * 
     * @see #isNotify()
     */
    public void setNotify(boolean notify) {
        this.notify = notify;
        // if the flag is being set to true, there may be queued up changes...
        if (notify) {
            notifyListeners(new ChartChangeEvent(this));
        }
    }
    
    /**
     * Tests this chart for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AFreeChart)) {
            return false;
        }
        AFreeChart that = (AFreeChart) obj;
//        if (!this.renderingHints.equals(that.renderingHints)) {
//            return false;
//        }
        if (this.borderVisible != that.borderVisible) {
            return false;
        }
        if (!ObjectUtilities.equal(this.borderStroke, that.borderStroke)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(this.borderPaintType, that.borderPaintType)) {
            return false;
        }
//        if (!this.padding.equals(that.padding)) {
//            return false;
//        }
        if (!ObjectUtilities.equal(this.title, that.title)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.subtitles, that.subtitles)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.plot, that.plot)) {
            return false;
        }
        if (!PaintTypeUtilities.equal(
            this.backgroundPaintType, that.backgroundPaintType
        )) {
            return false;
        }
//        if (!ObjectUtilities.equal(this.backgroundImage,
//                that.backgroundImage)) {
//            return false;
//        }
//        if (this.backgroundImageAlignment != that.backgroundImageAlignment) {
//            return false;
//        }
//        if (this.backgroundImageAlpha != that.backgroundImageAlpha) {
//            return false;
//        }
        if (this.notify != that.notify) {
            return false;
        }
        return true;
    }
    
//    /**
//     * Provides serialization support.
//     *
//     * @param stream  the input stream.
//     *
//     * @throws IOException  if there is an I/O error.
//     * @throws ClassNotFoundException  if there is a classpath problem.
//     */
//    private void readObject(ObjectInputStream stream)
//        throws IOException, ClassNotFoundException {
//        stream.defaultReadObject();
//        this.borderStroke = SerialUtilities.readStroke(stream);
//        this.borderPaint = SerialUtilities.readPaint(stream);
//        this.backgroundPaint = SerialUtilities.readPaint(stream);
//        this.progressListeners = new EventListenerList();
//        this.changeListeners = new EventListenerList();
//        this.renderingHints = new RenderingHints(
//                RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//
//        // register as a listener with sub-components...
//        if (this.title != null) {
//            this.title.addChangeListener(this);
//        }
//
//        for (int i = 0; i < getSubtitleCount(); i++) {
//            getSubtitle(i).addChangeListener(this);
//        }
//        this.plot.addChangeListener(this);
//    }

    
}
