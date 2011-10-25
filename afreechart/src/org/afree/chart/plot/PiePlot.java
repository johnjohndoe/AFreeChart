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
 * ------------
 * PiePlot.java
 * ------------
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
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2000-2008, by Andrzej Porebski and Contributors.
 *
 * Original Author:  Andrzej Porebski;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Martin Cordova (percentages in labels);
 *                   Richard Atkinson (URL support for image maps);
 *                   Christian W. Zuckschwerdt;
 *                   Arnaud Lelievre;
 *                   Martin Hilpert (patch 1891849);
 *                   Andreas Schroeder (very minor);
 *                   Christoph Beck (bug 2121818);
 *
 * Changes
 * -------
 * 21-Jun-2001 : Removed redundant JFreeChart parameter from constructors (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 15-Oct-2001 : Data source classes moved to com.jrefinery.data.* (DG);
 * 19-Oct-2001 : Moved series paint and stroke methods from JFreeChart.java to
 *               Plot.java (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 13-Nov-2001 : Modified plot subclasses so that null axes are possible for
 *               pie plot (DG);
 * 17-Nov-2001 : Added PieDataset interface and amended this class accordingly,
 *               and completed removal of BlankAxis class as it is no longer
 *               required (DG);
 * 19-Nov-2001 : Changed 'drawCircle' property to 'circular' property (DG);
 * 21-Nov-2001 : Added options for exploding pie sections and filled out range
 *               of properties (DG);
 *               Added option for percentages in chart labels, based on code
 *               by Martin Cordova (DG);
 * 30-Nov-2001 : Changed default font from "Arial" --> "SansSerif" (DG);
 * 12-Dec-2001 : Removed unnecessary 'throws' clause in constructor (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 16-Jan-2002 : Renamed tooltips class (DG);
 * 22-Jan-2002 : Fixed bug correlating legend labels with pie data (DG);
 * 05-Feb-2002 : Added alpha-transparency to plot class, and updated
 *               constructors accordingly (DG);
 * 06-Feb-2002 : Added optional background image and alpha-transparency to Plot
 *               and subclasses.  Clipped drawing within plot area (DG);
 * 26-Mar-2002 : Added an empty zoom method (DG);
 * 18-Apr-2002 : PieDataset is no longer sorted (oldman);
 * 23-Apr-2002 : Moved dataset from JFreeChart to Plot.  Added
 *               getLegendItemLabels() method (DG);
 * 19-Jun-2002 : Added attributes to control starting angle and direction
 *               (default is now clockwise) (DG);
 * 25-Jun-2002 : Removed redundant imports (DG);
 * 02-Jul-2002 : Fixed sign of percentage bug introduced in 0.9.2 (DG);
 * 16-Jul-2002 : Added check for null dataset in getLegendItemLabels() (DG);
 * 30-Jul-2002 : Moved summation code to DatasetUtilities (DG);
 * 05-Aug-2002 : Added URL support for image maps - new member variable for
 *               urlGenerator, modified constructor and minor change to the
 *               draw method (RA);
 * 18-Sep-2002 : Modified the percent label creation and added setters for the
 *               formatters (AS);
 * 24-Sep-2002 : Added getLegendItems() method (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 09-Oct-2002 : Added check for null entity collection (DG);
 * 30-Oct-2002 : Changed PieDataset interface (DG);
 * 18-Nov-2002 : Changed CategoryDataset to TableDataset (DG);
 * 02-Jan-2003 : Fixed "no data" message (DG);
 * 23-Jan-2003 : Modified to extract data from rows OR columns in
 *               CategoryDataset (DG);
 * 14-Feb-2003 : Fixed label drawing so that foreground alpha does not apply
 *               (bug id 685536) (DG);
 * 07-Mar-2003 : Modified to pass pieIndex on to PieSectionEntity and tooltip
 *               and URL generators (DG);
 * 21-Mar-2003 : Added a minimum angle for drawing arcs
 *               (see bug id 620031) (DG);
 * 24-Apr-2003 : Switched around PieDataset and KeyedValuesDataset (DG);
 * 02-Jun-2003 : Fixed bug 721733 (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 19-Aug-2003 : Implemented Cloneable (DG);
 * 29-Aug-2003 : Fixed bug 796936 (null pointer on setOutlinePaint()) (DG);
 * 08-Sep-2003 : Added internationalization via use of properties
 *               resourceBundle (RFE 690236) (AL);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 05-Nov-2003 : Fixed missing legend bug (DG);
 * 10-Nov-2003 : Re-added the DatasetChangeListener to constructors (CZ);
 * 29-Jan-2004 : Fixed clipping bug in draw() method (DG);
 * 11-Mar-2004 : Major overhaul to improve labelling (DG);
 * 31-Mar-2004 : Made an adjustment for the plot area when the label generator
 *               is null.  Fixed null pointer exception when the label
 *               generator returns null for a label (DG);
 * 06-Apr-2004 : Added getter, setter, serialization and draw support for
 *               labelBackgroundPaint (AS);
 * 08-Apr-2004 : Added flag to control whether null values are ignored or
 *               not (DG);
 * 15-Apr-2004 : Fixed some minor warnings from Eclipse (DG);
 * 26-Apr-2004 : Added attributes for label outline and shadow (DG);
 * 04-Oct-2004 : Renamed ShapeUtils --> ShapeUtilities (DG);
 * 04-Nov-2004 : Fixed null pointer exception with new LegendTitle class (DG);
 * 09-Nov-2004 : Added user definable legend item shape (DG);
 * 25-Nov-2004 : Added new legend label generator (DG);
 * 20-Apr-2005 : Added a tool tip generator for legend labels (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 10-May-2005 : Added flag to control visibility of label linking lines, plus
 *               another flag to control the handling of zero values (DG);
 * 08-Jun-2005 : Fixed bug in getLegendItems() method (not respecting flags
 *               for ignoring null and zero values), and fixed equals() method
 *               to handle GradientPaint (DG);
 * 15-Jul-2005 : Added sectionOutlinesVisible attribute (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 09-Jan-2006 : Fixed bug 1400442, inconsistent treatment of null and zero
 *               values in dataset (DG);
 * 28-Feb-2006 : Fixed bug 1440415, bad distribution of pie section
 *               labels (DG);
 * 27-Sep-2006 : Initialised baseSectionPaint correctly, added lookup methods
 *               for section paint, outline paint and outline stroke (DG);
 * 27-Sep-2006 : Refactored paint and stroke methods to use keys rather than
 *               section indices (DG);
 * 03-Oct-2006 : Replaced call to JRE 1.5 method (DG);
 * 23-Nov-2006 : Added support for URLs for the legend items (DG);
 * 24-Nov-2006 : Cloning fixes (DG);
 * 17-Apr-2007 : Check for null label in legend items (DG);
 * 19-Apr-2007 : Deprecated override settings (DG);
 * 18-May-2007 : Set dataset for LegendItem (DG);
 * 14-Jun-2007 : Added label distributor attribute (DG);
 * 18-Jul-2007 : Added simple label option (DG);
 * 21-Nov-2007 : Fixed labelling bugs, added debug code, restored default
 *               white background (DG);
 * 19-Mar-2008 : Fixed IllegalArgumentException when drawing with null
 *               dataset (DG);
 * 31-Mar-2008 : Adjust the label area for the interiorGap (DG);
 * 31-Mar-2008 : Added quad and cubic curve label link lines - see patch
 *               1891849 by Martin Hilpert (DG);
 * 02-Jul-2008 : Added autoPopulate flags (DG);
 * 15-Aug-2008 : Added methods to clear section attributes (DG);
 * 15-Aug-2008 : Fixed bug 2051168 - problem with LegendItemEntity
 *               generation (DG);
 * 23-Sep-2008 : Added getLabelLinkDepth() method - see bug 2121818 reported
 *               by Christoph Beck (DG);
 * 18-Dec-2008 : Use ResourceBundleWrapper - see patch 1607918 by
 *               Jess Thrysoee (DG);
 *
 */

package org.afree.chart.plot;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.afree.chart.EffectMap;
import org.afree.chart.LegendItem;
import org.afree.chart.LegendItemCollection;
import org.afree.chart.PaintTypeMap;
import org.afree.chart.StrokeMap;
import org.afree.data.DefaultKeyedValues;
import org.afree.data.KeyedValues;
import org.afree.data.general.PieDataset;
import org.afree.data.general.DatasetChangeEvent;
import org.afree.data.general.DatasetUtilities;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.entity.PieSectionEntity;
import org.afree.chart.event.PlotChangeEvent;
import org.afree.chart.labels.PieSectionLabelGenerator;
import org.afree.chart.labels.StandardPieSectionLabelGenerator;
import org.afree.chart.text.G2TextMeasurer;
import org.afree.chart.text.TextBlock;
import org.afree.chart.text.TextBox;
import org.afree.chart.text.TextUtilities;
import org.afree.graphics.geom.ArcShape;
import org.afree.graphics.geom.CubicCurveShape;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.QuadCurveShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleInsets;
import org.afree.ui.TextAnchor;
import org.afree.util.Rotation;
import org.afree.util.ShapeUtilities;
import org.afree.util.UnitType;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Typeface;


/**
 * A plot that displays data in the form of a pie chart, using data from any
 * class that implements the {@link PieDataset} interface. The example shown
 * here is generated by the <code>PieChartDemo2.java</code> program included in
 * the AFreeChart Demo Collection: <br>
 * <br>
 * <img src="../../../../images/PiePlotSample.png" alt="PiePlotSample.png" />
 * <P>
 * Special notes:
 * <ol>
 * <li>the default starting point is 12 o'clock and the pie sections proceed in
 * a clockwise direction, but these settings can be changed;</li>
 * <li>negative values in the dataset are ignored;</li>
 * <li>there are utility methods for creating a {@link PieDataset} from a
 * {@link org.afree.data.category.CategoryDataset};</li>
 * </ol>
 * 
 * @see Plot
 * @see PieDataset
 */
public class PiePlot extends Plot implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -795612466005590431L;

    /** The default interior gap. */
    public static final double DEFAULT_INTERIOR_GAP = 0.08;

    /** The maximum interior gap (currently 40%). */
    public static final double MAX_INTERIOR_GAP = 0.40;

    /** The default starting angle for the pie chart. */
    public static final double DEFAULT_START_ANGLE = -90.0;

    /** The default section label font. */
    public static final Font DEFAULT_LABEL_FONT_TYPE = new Font("SansSerif", Typeface.NORMAL, 10);

    /** The default section label paint. */
    public static final PaintType DEFAULT_LABEL_PAINT_TYPE = new SolidColor(Color.BLACK);

    /** The default section label background paint. */
    public static final PaintType DEFAULT_LABEL_BACKGROUND_PAINT_TYPE = new SolidColor(Color.rgb(
            255, 255, 192));

    /** The default section label outline paint. */
    public static final PaintType DEFAULT_LABEL_OUTLINE_PAINT_TYPE = new SolidColor(Color.BLACK);

    /** The default section label outline stroke. */
    public static final float DEFAULT_LABEL_OUTLINE_STROKE = 0.5f;

    /** The default section label outline effect. */
    public static final PathEffect DEFAULT_LABEL_OUTLINE_EFFECT = null;

    /** The default section label shadow paint. */
    public static final PaintType DEFAULT_LABEL_SHADOW_PAINT_TYPE = new SolidColor(Color.argb(128,
            151, 151, 151));

    public static final PaintType DEFAULT_SHADOW_PAINT = new SolidColor(Color.LTGRAY);

    /** The default minimum arc angle to draw. */
    public static final double DEFAULT_MINIMUM_ARC_ANGLE_TO_DRAW = 0.00001;

    /** The dataset for the pie chart. */
    private PieDataset dataset;

    /** The pie index (used by the {@link MultiplePiePlot} class). */
    private int pieIndex;

    /**
     * The amount of space left around the outside of the pie plot, expressed as
     * a percentage of the plot area width and height.
     */
    private double interiorGap;

    /** Flag determining whether to draw an ellipse or a perfect circle. */
    private boolean circular;

    /** The starting angle. */
    private double startAngle;

    /** The direction for the pie segments. */
    private Rotation direction;

    /** The section paint map. */
    private PaintTypeMap sectionPaintTypeMap;

    /** The base section paint (fallback). */
    private transient PaintType baseSectionPaintType;

    /**
     * A flag that controls whether or not the section paint is auto-populated
     * from the drawing supplier.
     * 
     * @since JFreeChart 1.0.11
     */
    private boolean autoPopulateSectionPaint;

    /**
     * A flag that controls whether or not an outline is drawn for each section
     * in the plot.
     */
    private boolean sectionOutlinesVisible;

    /** The section outline paint map. */
    private PaintTypeMap sectionOutlinePaintTypeMap;

    /** The base section outline paint (fallback). */
    private transient PaintType baseSectionOutlinePaintType;

    /**
     * A flag that controls whether or not the section outline paint is
     * auto-populated from the drawing supplier.
     * 
     * @since JFreeChart 1.0.11
     */
    private boolean autoPopulateSectionOutlinePaint;

    /** The section outline stroke map. */
    private StrokeMap sectionOutlineStrokeMap;

    /** The section outline effect map. */
    private EffectMap sectionOutlineEffectMap;

    /** The base section outline stroke (fallback). */
    private transient float baseSectionOutlineStroke;

    /** The base section outline effect (fallback). */
    private transient PathEffect baseSectionOutlineEffect;

    /**
     * A flag that controls whether or not the section outline stroke is
     * auto-populated from the drawing supplier.
     * 
     * @since JFreeChart 1.0.11
     */
    private boolean autoPopulateSectionOutlineStroke;

    private boolean autoPopulateSectionOutlineEffect;

    /** The shadow paint. */
    private transient Paint shadowPaint = null;

    /** The x-offset for the shadow effect. */
    private double shadowXOffset = 4.0f;

    /** The y-offset for the shadow effect. */
    private double shadowYOffset = 4.0f;

    /** The percentage amount to explode each pie section. */
    private Map explodePercentages;

    /** The section label generator. */
    private PieSectionLabelGenerator labelGenerator;

    /** The font used to display the section labels. */
    private Font labelFont;

    /** The color used to draw the section labels. */
    private transient PaintType labelPaintType;

    /**
     * The color used to draw the background of the section labels. If this is
     * <code>null</code>, the background is not filled.
     */
    private transient PaintType labelBackgroundPaintType;

    /**
     * The paint used to draw the outline of the section labels (
     * <code>null</code> permitted).
     */
    private transient PaintType labelOutlinePaintType;

    /**
     * The stroke used to draw the outline of the section labels (
     * <code>null</code> permitted).
     */
    private transient Float labelOutlineStroke;

    /**
     * The effect used to draw the outline of the section labels (
     * <code>null</code> permitted).
     */
    private transient PathEffect labelOutlineEffect;

    /**
     * The paint used to draw the shadow for the section labels (
     * <code>null</code> permitted).
     */
    private transient PaintType labelShadowPaintType;

    /**
     * A flag that controls whether simple or extended labels are used.
     * 
     * @since JFreeChart 1.0.7
     */
    private boolean simpleLabels = true;

    /**
     * The padding between the labels and the label outlines. This is not
     * allowed to be <code>null</code>.
     * 
     * @since JFreeChart 1.0.7
     */
    private RectangleInsets labelPadding;

    /**
     * The simple label offset.
     * 
     * @since JFreeChart 1.0.7
     */
    private RectangleInsets simpleLabelOffset;

    /** The maximum label width as a percentage of the plot width. */
    private double maximumLabelWidth = 0.14;

    /**
     * The gap between the labels and the link corner, as a percentage of the
     * plot width.
     */
    private double labelGap = 0.025;

    /** A flag that controls whether or not the label links are drawn. */
    private boolean labelLinksVisible;

    /**
     * The label link style.
     * 
     * @since JFreeChart 1.0.10
     */
    private PieLabelLinkStyle labelLinkStyle = PieLabelLinkStyle.STANDARD;

    /** The link margin. */
    private double labelLinkMargin = 0.025;

    /** The paint used for the label linking lines. */
    private transient PaintType labelLinkPaintType = DEFAULT_LABEL_PAINT_TYPE;

    /** The stroke used for the label linking lines. */
    private transient float labelLinkStroke = 2f;

    /** The effect used for the label linking lines. */
    private transient PathEffect labelLinkEffect = null;

    /**
     * The pie section label distributor.
     * 
     * @since JFreeChart 1.0.6
     */
    private AbstractPieLabelDistributor labelDistributor;

    /** The legend label generator. */
    private PieSectionLabelGenerator legendLabelGenerator;

    /** A tool tip generator for the legend. */
    private PieSectionLabelGenerator legendLabelToolTipGenerator;

    /**
     * A flag that controls whether <code>null</code> values are ignored.
     */
    private boolean ignoreNullValues;

    /**
     * A flag that controls whether zero values are ignored.
     */
    private boolean ignoreZeroValues;

    /** The legend item shape. */
    private transient Shape legendItemShape;

    /**
     * The smallest arc angle that will get drawn (this is to avoid a bug in
     * various Java implementations that causes the JVM to crash). See this link
     * for details:
     * 
     * http://www.jfree.org/phpBB2/viewtopic.php?t=2707
     * 
     * ...and this bug report in the Java Bug Parade:
     * 
     * http://developer.java.sun.com/developer/bugParade/bugs/4836495.html
     */
    private double minimumArcAngleToDraw;

    /**
     * This debug flag controls whether or not an outline is drawn showing the
     * interior of the plot region. This is drawn as a lightGray RectShape
     * showing the padding provided by the 'interiorGap' setting.
     */
    static final boolean DEBUG_DRAW_INTERIOR = false;

    /**
     * This debug flag controls whether or not an outline is drawn showing the
     * link area (in blue) and link ellipse (in yellow). This controls where the
     * label links have 'elbow' points.
     */
    static final boolean DEBUG_DRAW_LINK_AREA = false;

    /**
     * This debug flag controls whether or not an outline is drawn showing the
     * pie area (in green).
     */
    static final boolean DEBUG_DRAW_PIE_AREA = false;

    /**
     * Creates a new plot. The dataset is initially set to <code>null</code>.
     */
    public PiePlot() {
        this(null);
    }

    /**
     * Creates a plot that will draw a pie chart for the specified dataset.
     * 
     * @param dataset
     *            the dataset (<code>null</code> permitted).
     */
    public PiePlot(PieDataset dataset) {
        super();
        this.dataset = dataset;
        if (dataset != null) {
            dataset.addChangeListener(this);
        }
        this.pieIndex = 0;

        this.interiorGap = DEFAULT_INTERIOR_GAP;
        this.circular = true;
        this.startAngle = DEFAULT_START_ANGLE;
        this.direction = Rotation.CLOCKWISE;
        this.minimumArcAngleToDraw = DEFAULT_MINIMUM_ARC_ANGLE_TO_DRAW;

        this.sectionPaint = null;
        this.sectionPaintTypeMap = new PaintTypeMap();
        this.baseSectionPaintType = new SolidColor(Color.GRAY);
        this.autoPopulateSectionPaint = true;

        this.sectionOutlinesVisible = true;
        this.sectionOutlinePaintType = null;
        this.sectionOutlinePaintTypeMap = new PaintTypeMap();
        this.baseSectionOutlinePaintType = DEFAULT_OUTLINE_PAINT_TYPE;
        this.autoPopulateSectionOutlinePaint = false;

        // this.sectionOutlineStroke = null;
        this.sectionOutlineStrokeMap = new StrokeMap();
        this.sectionOutlineEffectMap = new EffectMap();
        this.baseSectionOutlineStroke = DEFAULT_OUTLINE_STROKE;
        this.autoPopulateSectionOutlineStroke = false;

        this.explodePercentages = new TreeMap();

        this.labelGenerator = new StandardPieSectionLabelGenerator();
        this.labelFont = DEFAULT_LABEL_FONT_TYPE;
        this.labelPaintType = DEFAULT_LABEL_PAINT_TYPE;
        this.labelBackgroundPaintType = DEFAULT_LABEL_BACKGROUND_PAINT_TYPE;
        this.labelOutlinePaintType = DEFAULT_LABEL_OUTLINE_PAINT_TYPE;
        this.labelOutlineStroke = DEFAULT_LABEL_OUTLINE_STROKE;
        this.labelOutlineEffect = DEFAULT_LABEL_OUTLINE_EFFECT;
        this.labelShadowPaintType = DEFAULT_LABEL_SHADOW_PAINT_TYPE;
        this.labelLinksVisible = true;
        this.labelDistributor = new PieLabelDistributor(0);

        this.simpleLabels = false;
        this.simpleLabelOffset = new RectangleInsets(UnitType.RELATIVE, 0.18, 0.18, 0.18, 0.18);
        this.labelPadding = new RectangleInsets(2, 2, 2, 2);

        this.legendLabelGenerator = new StandardPieSectionLabelGenerator();
        this.legendLabelToolTipGenerator = null;

        this.legendItemShape = Plot.DEFAULT_LEGEND_ITEM_CIRCLE;

        this.ignoreNullValues = false;
        this.ignoreZeroValues = false;
    }

    /**
     * Returns the dataset.
     * 
     * @return The dataset (possibly <code>null</code>).
     * 
     * @see #setDataset(PieDataset)
     */
    public PieDataset getDataset() {
        return this.dataset;
    }

    /**
     * Sets the dataset and sends a {@link DatasetChangeEvent} to 'this'.
     * 
     * @param dataset
     *            the dataset (<code>null</code> permitted).
     * 
     * @see #getDataset()
     */
    public void setDataset(PieDataset dataset) {
        // if there is an existing dataset, remove the plot from the list of
        // change listeners...
        PieDataset existing = this.dataset;
        if (existing != null) {
            existing.removeChangeListener(this);
        }

        // set the new dataset, and register the chart as a change listener...
        this.dataset = dataset;
        if (dataset != null) {
            setDatasetGroup(dataset.getGroup());
            dataset.addChangeListener(this);
        }

        // send a dataset change event to self...
        DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        datasetChanged(event);
    }

    /**
     * Returns the pie index (this is used by the {@link MultiplePiePlot} class
     * to track subplots).
     * 
     * @return The pie index.
     * 
     * @see #setPieIndex(int)
     */
    public int getPieIndex() {
        return this.pieIndex;
    }

    /**
     * Sets the pie index (this is used by the {@link MultiplePiePlot} class to
     * track subplots).
     * 
     * @param index
     *            the index.
     * 
     * @see #getPieIndex()
     */
    public void setPieIndex(int index) {
        this.pieIndex = index;
    }

    /**
     * Returns the start angle for the first pie section. This is measured in
     * degrees starting from 3 o'clock and measuring anti-clockwise.
     * 
     * @return The start angle.
     * 
     * @see #setStartAngle(double)
     */
    public double getStartAngle() {
        return this.startAngle;
    }

    /**
     * Sets the starting angle and sends a {@link PlotChangeEvent} to all
     * registered listeners. The initial default value is 90 degrees, which
     * corresponds to 12 o'clock. A value of zero corresponds to 3 o'clock...
     * this is the encoding used by Java's ArcShape class.
     * 
     * @param angle
     *            the angle (in degrees).
     * 
     * @see #getStartAngle()
     */
    public void setStartAngle(double angle) {
        this.startAngle = angle;
         fireChangeEvent();
    }

    /**
     * Returns the direction in which the pie sections are drawn (clockwise or
     * anti-clockwise).
     * 
     * @return The direction (never <code>null</code>).
     * 
     * @see #setDirection(Rotation)
     */
    public Rotation getDirection() {
        return this.direction;
    }

    /**
     * Sets the direction in which the pie sections are drawn and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param direction
     *            the direction (<code>null</code> not permitted).
     * 
     * @see #getDirection()
     */
    public void setDirection(Rotation direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Null 'direction' argument.");
        }
        this.direction = direction;
         fireChangeEvent();

    }

    /**
     * Returns the interior gap, measured as a percentage of the available
     * drawing space.
     * 
     * @return The gap (as a percentage of the available drawing space).
     * 
     * @see #setInteriorGap(double)
     */
    public double getInteriorGap() {
        return this.interiorGap;
    }

    /**
     * Sets the interior gap and sends a {@link PlotChangeEvent} to all
     * registered listeners. This controls the space between the edges of the
     * pie plot and the plot area itself (the region where the section labels
     * appear).
     * 
     * @param percent
     *            the gap (as a percentage of the available drawing space).
     * 
     * @see #getInteriorGap()
     */
    public void setInteriorGap(double percent) {

        if ((percent < 0.0) || (percent > MAX_INTERIOR_GAP)) {
            throw new IllegalArgumentException("Invalid 'percent' (" + percent + ") argument.");
        }

        if (this.interiorGap != percent) {
            this.interiorGap = percent;
             fireChangeEvent();
        }

    }

    /**
     * Returns a flag indicating whether the pie chart is circular, or stretched
     * into an elliptical shape.
     * 
     * @return A flag indicating whether the pie chart is circular.
     * 
     * @see #setCircular(boolean)
     */
    public boolean isCircular() {
        return this.circular;
    }

    /**
     * A flag indicating whether the pie chart is circular, or stretched into an
     * elliptical shape.
     * 
     * @param flag
     *            the new value.
     * 
     * @see #isCircular()
     */
    public void setCircular(boolean flag) {
        setCircular(flag, true);
    }

    /**
     * Sets the circular attribute and, if requested, sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param circular
     *            the new value of the flag.
     * @param notify
     *            notify listeners?
     * 
     * @see #isCircular()
     */
    public void setCircular(boolean circular, boolean notify) {
        this.circular = circular;
        if (notify) {
             fireChangeEvent();
        }
    }

    /**
     * Returns the flag that controls whether <code>null</code> values in the
     * dataset are ignored.
     * 
     * @return A boolean.
     * 
     * @see #setIgnoreNullValues(boolean)
     */
    public boolean getIgnoreNullValues() {
        return this.ignoreNullValues;
    }

    /**
     * Sets a flag that controls whether <code>null</code> values are ignored,
     * and sends a {@link PlotChangeEvent} to all registered listeners. At
     * present, this only affects whether or not the key is presented in the
     * legend.
     * 
     * @param flag
     *            the flag.
     * 
     * @see #getIgnoreNullValues()
     * @see #setIgnoreZeroValues(boolean)
     */
    public void setIgnoreNullValues(boolean flag) {
        this.ignoreNullValues = flag;
         fireChangeEvent();
    }

    /**
     * Returns the flag that controls whether zero values in the dataset are
     * ignored.
     * 
     * @return A boolean.
     * 
     * @see #setIgnoreZeroValues(boolean)
     */
    public boolean getIgnoreZeroValues() {
        return this.ignoreZeroValues;
    }

    /**
     * Sets a flag that controls whether zero values are ignored, and sends a
     * {@link PlotChangeEvent} to all registered listeners. This only affects
     * whether or not a label appears for the non-visible pie section.
     * 
     * @param flag
     *            the flag.
     * 
     * @see #getIgnoreZeroValues()
     * @see #setIgnoreNullValues(boolean)
     */
    public void setIgnoreZeroValues(boolean flag) {
        this.ignoreZeroValues = flag;
         fireChangeEvent();
    }

    // // SECTION PAINT ////////////////////////////////////////////////////////

    /**
     * Returns the paint for the specified section. This is equivalent to
     * <code>lookupSectionPaint(section, getAutoPopulateSectionPaint())</code>.
     * 
     * @param key
     *            the section key.
     * 
     * @return The paint type for the specified section.
     * 
     * @since JFreeChart 1.0.3
     * 
     * @see #lookupSectionPaintType(Comparable, boolean)
     */
    protected PaintType lookupSectionPaintType(Comparable key) {
        return lookupSectionPaintType(key, getAutoPopulateSectionPaint());
    }

    /**
     * Returns the paint for the specified section. The lookup involves these
     * steps:
     * <ul>
     * <li>if {@link #getSectionPaintType()} is non-<code>null</code>, return
     * it;</li>
     * <li>if {@link #getSectionPaintType(int)} is non-<code>null</code> return
     * it;</li>
     * <li>if {@link #getSectionPaintType(int)} is <code>null</code> but
     * <code>autoPopulate</code> is <code>true</code>, attempt to fetch a new
     * paint from the drawing supplier ({@link #getDrawingSupplier()});
     * <li>if all else fails, return {@link #getBaseSectionPaintType()}.
     * </ul>
     * 
     * @param key
     *            the section key.
     * @param autoPopulate
     *            a flag that controls whether the drawing supplier is used to
     *            auto-populate the section paint settings.
     * 
     * @return The paint type.
     * 
     * @since JFreeChart 1.0.3
     */
    protected PaintType lookupSectionPaintType(Comparable key, boolean autoPopulate) {

        // is there an override?
        PaintType result = getSectionPaintType();
        if (result != null) {
            return result;
        }

        // if not, check if there is a paint defined for the specified key
        result = this.sectionPaintTypeMap.getPaintType(key);
        if (result != null) {
            return result;
        }

        // nothing defined - do we autoPopulate?
        if (autoPopulate) {
            DrawingSupplier ds = getDrawingSupplier();
            if (ds != null) {
                result = ds.getNextPaintType();
                this.sectionPaintTypeMap.put(key, result);
            } else {
                result = this.baseSectionPaintType;
            }
        } else {
            result = this.baseSectionPaintType;
        }
        return result;
    }

    /**
     * Returns the paint for ALL sections in the plot.
     * 
     * @return The paint type (possibly <code>null</code>).
     * 
     * @see #setSectionPaintType(Comparable, PaintType)
     * 
     * @deprecated Use {@link #getSectionPaint(Comparable)} and
     *             {@link #getBaseSectionPaintType()}. Deprecated as of JFreeChart
     *             version 1.0.6.
     */
    public PaintType getSectionPaintType() {
        return this.sectionPaint;
    }

    /**
     * Returns a key for the specified section. If there is no such section in
     * the dataset, we generate a key. This is to provide some backward
     * compatibility for the (now deprecated) methods that get/set attributes
     * based on section indices. The preferred way of doing this now is to link
     * the attributes directly to the section key (there are new methods for
     * this, starting from JFreeChart version 1.0.3).
     * 
     * @param section
     *            the section index.
     * 
     * @return The key.
     * 
     * @since JFreeChart 1.0.3
     */
    protected Comparable getSectionKey(int section) {
        Comparable key = null;
        if (this.dataset != null) {
            if (section >= 0 && section < this.dataset.getItemCount()) {
                key = this.dataset.getKey(section);
            }
        }
        if (key == null) {
            key = new Integer(section);
        }
        return key;
    }

    /**
     * Returns the paint associated with the specified key, or <code>null</code>
     * if there is no paint associated with the key.
     * 
     * @param key
     *            the key (<code>null</code> not permitted).
     * 
     * @return The paint type associated with the specified key, or <code>null</code>
     *         .
     * 
     * @throws IllegalArgumentException
     *             if <code>key</code> is <code>null</code>.
     * 
     * @see #setSectionPaintType(Comparable, PaintType)
     * 
     * @since JFreeChart 1.0.3
     */
    public PaintType getSectionPaint(Comparable key) {
        // null argument check delegated...
        return this.sectionPaintTypeMap.getPaintType(key);
    }

    /**
     * Sets the paint associated with the specified key, and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param key
     *            the key (<code>null</code> not permitted).
     * @param paintType
     *            the paint.
     * 
     * @throws IllegalArgumentException
     *             if <code>key</code> is <code>null</code>.
     * 
     * @see #getSectionPaint(Comparable)
     * 
     * @since JFreeChart 1.0.3
     */
    public void setSectionPaintType(Comparable key, PaintType paintType) {
        // null argument check delegated...
        this.sectionPaintTypeMap.put(key, paintType);
         fireChangeEvent();
    }

    /**
     * Clears the section paint settings for this plot and, if requested, sends
     * a {@link PlotChangeEvent} to all registered listeners. Be aware that if
     * the <code>autoPopulateSectionPaint</code> flag is set, the section paints
     * may be repopulated using the same colours as before.
     * 
     * @param notify
     *            notify listeners?
     * 
     * @since JFreeChart 1.0.11
     * 
     * @see #autoPopulateSectionPaint
     */
    public void clearSectionPaints(boolean notify) {
        this.sectionPaintTypeMap.clear();
        if (notify) {
             fireChangeEvent();
        }
    }

    /**
     * Returns the base section paint. This is used when no other paint is
     * defined, which is rare. The default value is <code>Color.gray</code>.
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setBaseSectionPaintType(PaintType paintType)
     */
    public PaintType getBaseSectionPaintType() {
        return this.baseSectionPaintType;
    }

    /**
     * Sets the base section paint and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getBaseSectionPaintType()
     */
    public void setBaseSectionPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.baseSectionPaintType = paintType;
         fireChangeEvent();
    }

    /**
     * Returns the flag that controls whether or not the section paint is
     * auto-populated by the {@link #lookupSectionPaintType(Comparable)} method.
     * 
     * @return A boolean.
     * 
     * @since JFreeChart 1.0.11
     */
    public boolean getAutoPopulateSectionPaint() {
        return this.autoPopulateSectionPaint;
    }

    /**
     * Sets the flag that controls whether or not the section paint is
     * auto-populated by the {@link #lookupSectionPaintType(Comparable)} method,
     * and sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param auto
     *            auto-populate?
     * 
     * @since JFreeChart 1.0.11
     */
    public void setAutoPopulateSectionPaint(boolean auto) {
        this.autoPopulateSectionPaint = auto;
         fireChangeEvent();
    }

    // // SECTION OUTLINE PAINT ////////////////////////////////////////////////

    /**
     * Returns the flag that controls whether or not the outline is drawn for
     * each pie section.
     * 
     * @return The flag that controls whether or not the outline is drawn for
     *         each pie section.
     * 
     * @see #setSectionOutlinesVisible(boolean)
     */
    public boolean getSectionOutlinesVisible() {
        return this.sectionOutlinesVisible;
    }

    /**
     * Sets the flag that controls whether or not the outline is drawn for each
     * pie section, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #getSectionOutlinesVisible()
     */
    public void setSectionOutlinesVisible(boolean visible) {
        this.sectionOutlinesVisible = visible;
         fireChangeEvent();
    }

    /**
     * Returns the outline paint for the specified section. This is equivalent
     * to <code>lookupSectionPaint(section,
     * getAutoPopulateSectionOutlinePaint())</code>.
     * 
     * @param key
     *            the section key.
     * 
     * @return The paint type for the specified section.
     * 
     * @since JFreeChart 1.0.3
     * 
     * @see #lookupSectionOutlinePaintType(Comparable, boolean)
     */
    protected PaintType lookupSectionOutlinePaintType(Comparable key) {
        return lookupSectionOutlinePaintType(key, getAutoPopulateSectionOutlinePaint());
    }

    /**
     * Returns the outline paint for the specified section. The lookup involves
     * these steps:
     * <ul>
     * <li>if {@link #getSectionOutlinePaintType()} is non-<code>null</code>,
     * return it;</li>
     * <li>otherwise, if {@link #getSectionOutlinePaintType(int)} is non-
     * <code>null</code> return it;</li>
     * <li>if {@link #getSectionOutlinePaintType(int)} is <code>null</code> but
     * <code>autoPopulate</code> is <code>true</code>, attempt to fetch a new
     * outline paint from the drawing supplier ({@link #getDrawingSupplier()});
     * <li>if all else fails, return {@link #getBaseSectionOutlinePaintType()}.
     * </ul>
     * 
     * @param key
     *            the section key.
     * @param autoPopulate
     *            a flag that controls whether the drawing supplier is used to
     *            auto-populate the section outline paint settings.
     * 
     * @return The paint type.
     * 
     * @since JFreeChart 1.0.3
     */
    protected PaintType lookupSectionOutlinePaintType(Comparable key, boolean autoPopulate) {

        // is there an override?
        PaintType result = getSectionOutlinePaintType();
        if (result != null) {
            return result;
        }

        // if not, check if there is a paint defined for the specified key
        result = this.sectionOutlinePaintTypeMap.getPaintType(key);
        if (result != null) {
            return result;
        }

        // nothing defined - do we autoPopulate?
        if (autoPopulate) {
            DrawingSupplier ds = getDrawingSupplier();
            if (ds != null) {
                result = ds.getNextOutlinePaintType();
                this.sectionOutlinePaintTypeMap.put(key, result);
            } else {
                result = this.baseSectionOutlinePaintType;
            }
        } else {
            result = this.baseSectionOutlinePaintType;
        }
        return result;
    }

    /**
     * Returns the outline paint associated with the specified key, or
     * <code>null</code> if there is no paint associated with the key.
     * 
     * @param key
     *            the key (<code>null</code> not permitted).
     * 
     * @return The paint type associated with the specified key, or <code>null</code>
     *         .
     * 
     * @throws IllegalArgumentException
     *             if <code>key</code> is <code>null</code>.
     * 
     * @see #setSectionOutlinePaintType(Comparable, PaintType)
     * 
     * @since JFreeChart 1.0.3
     */
    public PaintType getSectionOutlinePaintType(Comparable key) {
        // null argument check delegated...
        return this.sectionOutlinePaintTypeMap.getPaintType(key);
    }

    /**
     * Sets the outline paint associated with the specified key, and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param key
     *            the key (<code>null</code> not permitted).
     * @param paintType
     *            the paint.
     * 
     * @throws IllegalArgumentException
     *             if <code>key</code> is <code>null</code>.
     * 
     * @see #getSectionOutlinePaintType(Comparable)
     * 
     * @since JFreeChart 1.0.3
     */
    public void setSectionOutlinePaintType(Comparable key, PaintType paintType) {
        // null argument check delegated...
        this.sectionOutlinePaintTypeMap.put(key, paintType);
         fireChangeEvent();
    }

    /**
     * Clears the section outline paint settings for this plot and, if
     * requested, sends a {@link PlotChangeEvent} to all registered listeners.
     * Be aware that if the <code>autoPopulateSectionPaint</code> flag is set,
     * the section paints may be repopulated using the same colours as before.
     * 
     * @param notify
     *            notify listeners?
     * 
     * @since JFreeChart 1.0.11
     * 
     * @see #autoPopulateSectionOutlinePaint
     */
    public void clearSectionOutlinePaints(boolean notify) {
        this.sectionOutlinePaintTypeMap.clear();
        if (notify) {
             fireChangeEvent();
        }
    }

    /**
     * Returns the base section paint. This is used when no other paint is
     * available.
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setBaseSectionOutlinePaintType(PaintType paintType)
     */
    public PaintType getBaseSectionOutlinePaintType() {
        return this.baseSectionOutlinePaintType;
    }

    /**
     * Sets the base section paint.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getBaseSectionOutlinePaintType()
     */
    public void setBaseSectionOutlinePaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.baseSectionOutlinePaintType = paintType;
         fireChangeEvent();
    }

    /**
     * Returns the flag that controls whether or not the section outline paint
     * is auto-populated by the
     * {@link #lookupSectionOutlinePaintType(Comparable)} method.
     * 
     * @return A boolean.
     * 
     * @since JFreeChart 1.0.11
     */
    public boolean getAutoPopulateSectionOutlinePaint() {
        return this.autoPopulateSectionOutlinePaint;
    }

    /**
     * Sets the flag that controls whether or not the section outline paint is
     * auto-populated by the {@link #lookupSectionOutlinePaintType(Comparable)}
     * method, and sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param auto
     *            auto-populate?
     * 
     * @since JFreeChart 1.0.11
     */
    public void setAutoPopulateSectionOutlinePaint(boolean auto) {
        this.autoPopulateSectionOutlinePaint = auto;
         fireChangeEvent();
    }

    // // SECTION OUTLINE STROKE ///////////////////////////////////////////////

    /**
     * Returns the outline stroke for the specified section. This is equivalent
     * to <code>lookupSectionOutlineStroke(section,
     * getAutoPopulateSectionOutlineStroke())</code>.
     * 
     * @param key
     *            the section key.
     * 
     * @return The stroke for the specified section.
     * 
     * @since JFreeChart 1.0.3
     * 
     * @see #lookupSectionOutlineStroke(Comparable, boolean)
     */
    protected Float lookupSectionOutlineStroke(Comparable key) {
        return lookupSectionOutlineStroke(key, getAutoPopulateSectionOutlineStroke());
    }

    /**
     * Returns the outline stroke for the specified section. The lookup involves
     * these steps:
     * <ul>
     * <li>if {@link #getSectionOutlineStroke()} is non-<code>null</code>,
     * return it;</li>
     * <li>otherwise, if {@link #getSectionOutlineStroke(int)} is non-
     * <code>null</code> return it;</li>
     * <li>if {@link #getSectionOutlineStroke(int)} is <code>null</code> but
     * <code>autoPopulate</code> is <code>true</code>, attempt to fetch a new
     * outline stroke from the drawing supplier ({@link #getDrawingSupplier()});
     * <li>if all else fails, return {@link #getBaseSectionOutlineStroke()}.
     * </ul>
     * 
     * @param key
     *            the section key.
     * @param autoPopulate
     *            a flag that controls whether the drawing supplier is used to
     *            auto-populate the section outline stroke settings.
     * 
     * @return The stroke.
     * 
     * @since JFreeChart 1.0.3
     */
    protected Float lookupSectionOutlineStroke(Comparable key, boolean autoPopulate) {

        // is there an override?
        Float result = getSectionOutlineStroke();
        if (result != null) {
            return result;
        }

        // if not, check if there is a stroke defined for the specified key
        result = this.sectionOutlineStrokeMap.getStroke(key);
        if (result != null) {
            return result;
        }

        // nothing defined - do we autoPopulate?
        if (autoPopulate) {
            DrawingSupplier ds = getDrawingSupplier();
            if (ds != null) {
                result = ds.getNextOutlineStroke();
                this.sectionOutlineStrokeMap.put(key, result);
            } else {
                result = this.baseSectionOutlineStroke;
            }
        } else {
            result = this.baseSectionOutlineStroke;
        }
        return result;
    }

    /**
     * Returns the outline effect for the specified section. This is equivalent
     * to <code>lookupSectionOutlineEffect(section,
     * getAutoPopulateSectionOutlineEffect())</code>.
     * 
     * @param key
     *            the section key.
     * 
     * @return The effect for the specified section.
     * 
     * @see #lookupSectionOutlineEffect(Comparable, boolean)
     */
    protected PathEffect lookupSectionOutlineEffect(Comparable key) {
        return lookupSectionOutlineEffect(key, getAutoPopulateSectionOutlineEffect());
    }

    /**
     * Returns the outline effect for the specified section. The lookup involves
     * these steps:
     * <ul>
     * <li>if {@link #getSectionOutlineEffect()} is non-<code>null</code>,
     * return it;</li>
     * <li>otherwise, if {@link #getSectionOutlineEffect(int)} is non-
     * <code>null</code> return it;</li>
     * <li>if {@link #getSectionOutlineEffect(int)} is <code>null</code> but
     * <code>autoPopulate</code> is <code>true</code>, attempt to fetch a new
     * outline effect from the drawing supplier ({@link #getDrawingSupplier()});
     * <li>if all else fails, return {@link #getBaseSectionOutlineEffect()}.
     * </ul>
     * 
     * @param key
     *            the section key.
     * @param autoPopulate
     *            a flag that controls whether the drawing supplier is used to
     *            auto-populate the section outline effect settings.
     * 
     * @return The effect.
     */
    protected PathEffect lookupSectionOutlineEffect(Comparable key, boolean autoPopulate) {

        // is there an override?
        PathEffect result = getSectionOutlineEffect();
        if (result != null) {
            return result;
        }

        // if not, check if there is a stroke defined for the specified key
        result = this.sectionOutlineEffectMap.getEffect(key);
        if (result != null) {
            return result;
        }

        // nothing defined - do we autoPopulate?
        if (autoPopulate) {
            DrawingSupplier ds = getDrawingSupplier();
            if (ds != null) {
                result = ds.getNextOutlineEffect();
                this.sectionOutlineEffectMap.put(key, result);
            } else {
                result = this.baseSectionOutlineEffect;
            }
        } else {
            result = this.baseSectionOutlineEffect;
        }
        return result;
    }

    /**
     * Returns the outline stroke associated with the specified key, or
     * <code>null</code> if there is no stroke associated with the key.
     * 
     * @param key
     *            the key (<code>null</code> not permitted).
     * 
     * @return The stroke associated with the specified key, or
     *         <code>null</code>.
     * 
     * @throws IllegalArgumentException
     *             if <code>key</code> is <code>null</code>.
     * 
     * @see #setSectionOutlineStroke(Comparable, Float)
     * 
     * @since JFreeChart 1.0.3
     */
    public Float getSectionOutlineStroke(Comparable key) {
        // null argument check delegated...
        return this.sectionOutlineStrokeMap.getStroke(key);
    }

    /**
     * Sets the outline stroke associated with the specified key, and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param key
     *            the key (<code>null</code> not permitted).
     * @param stroke
     *            the stroke.
     * 
     * @throws IllegalArgumentException
     *             if <code>key</code> is <code>null</code>.
     * 
     * @see #getSectionOutlineStroke(Comparable)
     * 
     * @since JFreeChart 1.0.3
     */
    public void setSectionOutlineStroke(Comparable key, Float stroke) {
        // null argument check delegated...
        this.sectionOutlineStrokeMap.put(key, stroke);
         fireChangeEvent();
    }

    /**
     * Returns the outline effect associated with the specified key, or
     * <code>null</code> if there is no effect associated with the key.
     * 
     * @param key
     *            the key (<code>null</code> not permitted).
     * 
     * @return The effect associated with the specified key, or
     *         <code>null</code>.
     * 
     * @throws IllegalArgumentException
     *             if <code>key</code> is <code>null</code>.
     * 
     * @see #setSectionOutlineEffect(Comparable key, PathEffect effect)
     */
    public PathEffect getSectionOutlineEffect(Comparable key) {
        // null argument check delegated...
        return this.sectionOutlineEffectMap.getEffect(key);
    }

    /**
     * Sets the outline effect associated with the specified key, and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param key
     *            the key (<code>null</code> not permitted).
     * @param effect
     *            the effect.
     * 
     * @throws IllegalArgumentException
     *             if <code>key</code> is <code>null</code>.
     * 
     * @see #getSectionOutlineEffect(Comparable)
     */
    public void setSectionOutlineEffect(Comparable key, PathEffect effect) {
        // null argument check delegated...
        this.sectionOutlineEffectMap.put(key, effect);
         fireChangeEvent();
    }
    
    /**
     * Clears the section outline stroke settings for this plot and, if
     * requested, sends a {@link PlotChangeEvent} to all registered listeners.
     * Be aware that if the <code>autoPopulateSectionPaint</code> flag is set,
     * the section paints may be repopulated using the same colours as before.
     * 
     * @param notify
     *            notify listeners?
     * 
     * @since JFreeChart 1.0.11
     * 
     * @see #autoPopulateSectionOutlineStroke
     */
    public void clearSectionOutlineStrokes(boolean notify) {
        this.sectionOutlineStrokeMap.clear();
        if (notify) {
             fireChangeEvent();
        }
    }

    /**
     * Returns the base section stroke. This is used when no other stroke is
     * available.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setBaseSectionOutlineStroke(Float stroke)
     */
    public Float getBaseSectionOutlineStroke() {
        return this.baseSectionOutlineStroke;
    }

    /**
     * Sets the base section stroke.
     * 
     * @param stroke
     *            the stroke (<code>null</code> not permitted).
     * 
     * @see #getBaseSectionOutlineStroke()
     */
    public void setBaseSectionOutlineStroke(Float stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.baseSectionOutlineStroke = stroke;
         fireChangeEvent();
    }

    /**
     * Returns the base section effect.  This is used when no other effect is
     * available.
     *
     * @return The effect (never <code>null</code>).
     *
     * @see #setBaseSectionOutlineEffect(effect)
     */
    public PathEffect getBaseSectionOutlineEffect() {
        return this.baseSectionOutlineEffect;
    }

    /**
     * Sets the base section effect.
     *
     * @param effect  the effect (<code>null</code> not permitted).
     *
     * @see #getBaseSectionOutlineEffect()
     */
    public void setBaseSectionOutlineEffect(PathEffect effect) {
        this.baseSectionOutlineEffect = effect;
        fireChangeEvent();
    }
    
    /**
     * Returns the flag that controls whether or not the section outline stroke
     * is auto-populated by the
     * {@link #lookupSectionOutlinePaintType(Comparable)} method.
     * 
     * @return A boolean.
     * 
     * @since JFreeChart 1.0.11
     */
    public boolean getAutoPopulateSectionOutlineStroke() {
        return this.autoPopulateSectionOutlineStroke;
    }

    public boolean getAutoPopulateSectionOutlineEffect() {
        return this.autoPopulateSectionOutlineEffect;
    }

    /**
     * Sets the flag that controls whether or not the section outline stroke is
     * auto-populated by the {@link #lookupSectionOutlineStroke(Comparable)}
     * method, and sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param auto
     *            auto-populate?
     * 
     * @since JFreeChart 1.0.11
     */
    public void setAutoPopulateSectionOutlineStroke(boolean auto) {
        this.autoPopulateSectionOutlineStroke = auto;
         fireChangeEvent();
    }

    /**
     * Returns the shadow paint.
     * 
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setShadowPaint(Paint)
     */
    public Paint getShadowPaint() {
        return this.shadowPaint;
    }

    /**
     * Sets the shadow paint and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param paint
     *            the paint (<code>null</code> permitted).
     * 
     * @see #getShadowPaint()
     */
    public void setShadowPaint(Paint paint) {
        this.shadowPaint = paint;
         fireChangeEvent();
    }

    /**
     * Returns the x-offset for the shadow effect.
     * 
     * @return The offset (in Canvas units).
     * 
     * @see #setShadowXOffset(double)
     */
    public double getShadowXOffset() {
        return this.shadowXOffset;
    }

    /**
     * Sets the x-offset for the shadow effect and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param offset
     *            the offset (in Canvas units).
     * 
     * @see #getShadowXOffset()
     */
    public void setShadowXOffset(double offset) {
        this.shadowXOffset = offset;
         fireChangeEvent();
    }

    /**
     * Returns the y-offset for the shadow effect.
     * 
     * @return The offset (in Canvas units).
     * 
     * @see #setShadowYOffset(double)
     */
    public double getShadowYOffset() {
        return this.shadowYOffset;
    }

    /**
     * Sets the y-offset for the shadow effect and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param offset
     *            the offset (in Canvas units).
     * 
     * @see #getShadowYOffset()
     */
    public void setShadowYOffset(double offset) {
        this.shadowYOffset = offset;
         fireChangeEvent();
    }

    /**
     * Returns the amount that the section with the specified key should be
     * exploded.
     * 
     * @param key
     *            the key (<code>null</code> not permitted).
     * 
     * @return The amount that the section with the specified key should be
     *         exploded.
     * 
     * @throws IllegalArgumentException
     *             if <code>key</code> is <code>null</code>.
     * 
     * @since JFreeChart 1.0.3
     * 
     * @see #setExplodePercent(Comparable, double)
     */
    public double getExplodePercent(Comparable key) {
        double result = 0.0;
        if (this.explodePercentages != null) {
            Number percent = (Number) this.explodePercentages.get(key);
            if (percent != null) {
                result = percent.doubleValue();
            }
        }
        return result;
    }

    /**
     * Sets the amount that a pie section should be exploded and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param key
     *            the section key (<code>null</code> not permitted).
     * @param percent
     *            the explode percentage (0.30 = 30 percent).
     * 
     * @since JFreeChart 1.0.3
     * 
     * @see #getExplodePercent(Comparable)
     */
    public void setExplodePercent(Comparable key, double percent) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        if (this.explodePercentages == null) {
            this.explodePercentages = new TreeMap();
        }
        this.explodePercentages.put(key, new Double(percent));
         fireChangeEvent();
    }

    /**
     * Returns the maximum explode percent.
     * 
     * @return The percent.
     */
    public double getMaximumExplodePercent() {
        if (this.dataset == null) {
            return 0.0;
        }
        double result = 0.0;
        Iterator iterator = this.dataset.getKeys().iterator();
        while (iterator.hasNext()) {
            Comparable key = (Comparable) iterator.next();
            Number explode = (Number) this.explodePercentages.get(key);
            if (explode != null) {
                result = Math.max(result, explode.doubleValue());
            }
        }
        return result;
    }

    /**
     * Returns the section label generator.
     * 
     * @return The generator (possibly <code>null</code>).
     * 
     * @see #setLabelGenerator(PieSectionLabelGenerator)
     */
    public PieSectionLabelGenerator getLabelGenerator() {
        return this.labelGenerator;
    }

    /**
     * Sets the section label generator and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     * 
     * @param generator
     *            the generator (<code>null</code> permitted).
     * 
     * @see #getLabelGenerator()
     */
    public void setLabelGenerator(PieSectionLabelGenerator generator) {
        this.labelGenerator = generator;
         fireChangeEvent();
    }

    /**
     * Returns the gap between the edge of the pie and the labels, expressed as
     * a percentage of the plot width.
     * 
     * @return The gap (a percentage, where 0.05 = five percent).
     * 
     * @see #setLabelGap(double)
     */
    public double getLabelGap() {
        return this.labelGap;
    }

    /**
     * Sets the gap between the edge of the pie and the labels (expressed as a
     * percentage of the plot width) and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param gap
     *            the gap (a percentage, where 0.05 = five percent).
     * 
     * @see #getLabelGap()
     */
    public void setLabelGap(double gap) {
        this.labelGap = gap;
         fireChangeEvent();
    }

    /**
     * Returns the maximum label width as a percentage of the plot width.
     * 
     * @return The width (a percentage, where 0.20 = 20 percent).
     * 
     * @see #setMaximumLabelWidth(double)
     */
    public double getMaximumLabelWidth() {
        return this.maximumLabelWidth;
    }

    /**
     * Sets the maximum label width as a percentage of the plot width and sends
     * a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param width
     *            the width (a percentage, where 0.20 = 20 percent).
     * 
     * @see #getMaximumLabelWidth()
     */
    public void setMaximumLabelWidth(double width) {
        this.maximumLabelWidth = width;
         fireChangeEvent();
    }

    /**
     * Returns the flag that controls whether or not label linking lines are
     * visible.
     * 
     * @return A boolean.
     * 
     * @see #setLabelLinksVisible(boolean)
     */
    public boolean getLabelLinksVisible() {
        return this.labelLinksVisible;
    }

    /**
     * Sets the flag that controls whether or not label linking lines are
     * visible and sends a {@link PlotChangeEvent} to all registered listeners.
     * Please take care when hiding the linking lines - depending on the data
     * values, the labels can be displayed some distance away from the
     * corresponding pie section.
     * 
     * @param visible
     *            the flag.
     * 
     * @see #getLabelLinksVisible()
     */
    public void setLabelLinksVisible(boolean visible) {
        this.labelLinksVisible = visible;
         fireChangeEvent();
    }

    /**
     * Returns the label link style.
     * 
     * @return The label link style (never <code>null</code>).
     * 
     * @see #setLabelLinkStyle(PieLabelLinkStyle)
     * 
     * @since JFreeChart 1.0.10
     */
    public PieLabelLinkStyle getLabelLinkStyle() {
        return this.labelLinkStyle;
    }

    /**
     * Sets the label link style and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param style
     *            the new style (<code>null</code> not permitted).
     * 
     * @see #getLabelLinkStyle()
     * 
     * @since JFreeChart 1.0.10
     */
    public void setLabelLinkStyle(PieLabelLinkStyle style) {
        if (style == null) {
            throw new IllegalArgumentException("Null 'style' argument.");
        }
        this.labelLinkStyle = style;
         fireChangeEvent();
    }

    /**
     * Returns the margin (expressed as a percentage of the width or height)
     * between the edge of the pie and the link point.
     * 
     * @return The link margin (as a percentage, where 0.05 is five percent).
     * 
     * @see #setLabelLinkMargin(double)
     */
    public double getLabelLinkMargin() {
        return this.labelLinkMargin;
    }

    /**
     * Sets the link margin and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param margin
     *            the margin.
     * 
     * @see #getLabelLinkMargin()
     */
    public void setLabelLinkMargin(double margin) {
        this.labelLinkMargin = margin;
         fireChangeEvent();
    }

    /**
     * Returns the paint used for the lines that connect pie sections to their
     * corresponding labels.
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setLabelLinkPaintType(PaintType paintType)
     */
    public PaintType getLabelLinkPaintType() {
        return this.labelLinkPaintType;
    }

    /**
     * Sets the paint used for the lines that connect pie sections to their
     * corresponding labels, and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getLabelLinkPaintType()
     */
    public void setLabelLinkPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.labelLinkPaintType = paintType;
         fireChangeEvent();
    }

    /**
     * Returns the stroke used for the label linking lines.
     * 
     * @return The stroke.
     * 
     * @see #setLabelLinkStroke(Float stroke)
     */
    public Float getLabelLinkStroke() {
        return this.labelLinkStroke;
    }

    /**
     * Sets the link stroke and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param stroke
     *            the stroke.
     * 
     * @see #getLabelLinkStroke()
     */
    public void setLabelLinkStroke(Float stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.labelLinkStroke = stroke;
         fireChangeEvent();
    }

    /**
     * Returns the distance that the end of the label link is embedded into the
     * plot, expressed as a percentage of the plot's radius. <br>
     * <br>
     * This method is overridden in the {@link RingPlot} class to resolve bug
     * 2121818.
     * 
     * @return <code>0.10</code>.
     * 
     * @since JFreeChart 1.0.12
     */
    protected double getLabelLinkDepth() {
        return 0.1;
    }

    /**
     * Returns the section label font.
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setLabelFont(Font)
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Sets the section label font and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param font
     *            the font (<code>null</code> not permitted).
     * 
     * @see #getLabelFont()
     */
    public void setLabelFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.labelFont = font;
         fireChangeEvent();
    }

    /**
     * Returns the section label paint.
     * 
     * @return The paint type (never <code>null</code>).
     * 
     * @see #setLabelPaint(PaintType paintType)
     */
    public PaintType getLabelPaintType() {
        return this.labelPaintType;
    }

    /**
     * Sets the section label paint and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> not permitted).
     * 
     * @see #getLabelPaintType()
     */
    public void setLabelPaint(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.labelPaintType = paintType;
         fireChangeEvent();
    }

    /**
     * Returns the section label background paint.
     * 
     * @return The paint type (possibly <code>null</code>).
     * 
     * @see #setLabelBackgroundPaintType(PaintType paintType)
     */
    public PaintType getLabelBackgroundPaintType() {
        return this.labelBackgroundPaintType;
    }

    /**
     * Sets the section label background paint and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> permitted).
     * 
     * @see #getLabelBackgroundPaintType()
     */
    public void setLabelBackgroundPaintType(PaintType paintType) {
        this.labelBackgroundPaintType = paintType;
         fireChangeEvent();
    }

    /**
     * Returns the section label outline paint.
     * 
     * @return The paint type (possibly <code>null</code>).
     * 
     * @see #setLabelOutlinePaint(PaintType paintType)
     */
    public PaintType getLabelOutlinePaintType() {
        return this.labelOutlinePaintType;
    }

    /**
     * Sets the section label outline paint and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> permitted).
     * 
     * @see #getLabelOutlinePaintType()
     */
    public void setLabelOutlinePaint(PaintType paintType) {
        this.labelOutlinePaintType = paintType;
         fireChangeEvent();
    }

    /**
     * Returns the section label outline stroke.
     * 
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setLabelOutlineStroke(Float stroke)
     */
    public Float getLabelOutlineStroke() {
        return this.labelOutlineStroke;
    }

    /**
     * Sets the section label outline stroke and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param stroke
     *            the stroke (<code>null</code> permitted).
     * 
     * @see #getLabelOutlineStroke()
     */
    public void setLabelOutlineStroke(Float stroke) {
        this.labelOutlineStroke = stroke;
         fireChangeEvent();
    }

    /**
     * Returns the section label shadow paint.
     * 
     * @return The paint type (possibly <code>null</code>).
     * 
     * @see #setLabelShadowPaint(PaintType paintType)
     */
    public PaintType getLabelShadowPaintType() {
        return this.labelShadowPaintType;
    }

    /**
     * Sets the section label shadow paint and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param paintType
     *            the paint (<code>null</code> permitted).
     * 
     * @see #getLabelShadowPaintType()
     */
    public void setLabelShadowPaint(PaintType paintType) {
        this.labelShadowPaintType = paintType;
         fireChangeEvent();
    }

    /**
     * Returns the label padding.
     * 
     * @return The label padding (never <code>null</code>).
     * 
     * @since JFreeChart 1.0.7
     * 
     * @see #setLabelPadding(RectangleInsets)
     */
    public RectangleInsets getLabelPadding() {
        return this.labelPadding;
    }

    /**
     * Sets the padding between each label and its outline and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param padding
     *            the padding (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.7
     * 
     * @see #getLabelPadding()
     */
    public void setLabelPadding(RectangleInsets padding) {
        if (padding == null) {
            throw new IllegalArgumentException("Null 'padding' argument.");
        }
        this.labelPadding = padding;
         fireChangeEvent();
    }

    /**
     * Returns the flag that controls whether simple or extended labels are
     * displayed on the plot.
     * 
     * @return A boolean.
     * 
     * @since JFreeChart 1.0.7
     */
    public boolean getSimpleLabels() {
        return this.simpleLabels;
    }

    /**
     * Sets the flag that controls whether simple or extended labels are
     * displayed on the plot, and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param simple
     *            the new flag value.
     * 
     * @since JFreeChart 1.0.7
     */
    public void setSimpleLabels(boolean simple) {
        this.simpleLabels = simple;
         fireChangeEvent();
    }

    /**
     * Returns the offset used for the simple labels, if they are displayed.
     * 
     * @return The offset (never <code>null</code>).
     * 
     * @since JFreeChart 1.0.7
     * 
     * @see #setSimpleLabelOffset(RectangleInsets)
     */
    public RectangleInsets getSimpleLabelOffset() {
        return this.simpleLabelOffset;
    }

    /**
     * Sets the offset for the simple labels and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param offset
     *            the offset (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.7
     * 
     * @see #getSimpleLabelOffset()
     */
    public void setSimpleLabelOffset(RectangleInsets offset) {
        if (offset == null) {
            throw new IllegalArgumentException("Null 'offset' argument.");
        }
        this.simpleLabelOffset = offset;
         fireChangeEvent();
    }

    /**
     * Returns the object responsible for the vertical layout of the pie section
     * labels.
     * 
     * @return The label distributor (never <code>null</code>).
     * 
     * @since JFreeChart 1.0.6
     */
    public AbstractPieLabelDistributor getLabelDistributor() {
        return this.labelDistributor;
    }

    /**
     * Sets the label distributor and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param distributor
     *            the distributor (<code>null</code> not permitted).
     * 
     * @since JFreeChart 1.0.6
     */
    public void setLabelDistributor(AbstractPieLabelDistributor distributor) {
        if (distributor == null) {
            throw new IllegalArgumentException("Null 'distributor' argument.");
        }
        this.labelDistributor = distributor;
         fireChangeEvent();
    }

    /**
     * Returns the minimum arc angle that will be drawn. Pie sections for an
     * angle smaller than this are not drawn, to avoid a JDK bug.
     * 
     * @return The minimum angle.
     * 
     * @see #setMinimumArcAngleToDraw(double)
     */
    public double getMinimumArcAngleToDraw() {
        return this.minimumArcAngleToDraw;
    }

    /**
     * Sets the minimum arc angle that will be drawn. Pie sections for an angle
     * smaller than this are not drawn, to avoid a JDK bug. See this link for
     * details: <br>
     * <br>
     * <a href="http://www.jfree.org/phpBB2/viewtopic.php?t=2707">
     * http://www.jfree.org/phpBB2/viewtopic.php?t=2707</a> <br>
     * <br>
     * ...and this bug report in the Java Bug Parade: <br>
     * <br>
     * <a href=
     * "http://developer.java.sun.com/developer/bugParade/bugs/4836495.html">
     * http://developer.java.sun.com/developer/bugParade/bugs/4836495.html</a>
     * 
     * @param angle
     *            the minimum angle.
     * 
     * @see #getMinimumArcAngleToDraw()
     */
    public void setMinimumArcAngleToDraw(double angle) {
        this.minimumArcAngleToDraw = angle;
    }

    /**
     * Returns the shape used for legend items.
     * 
     * @return The shape (never <code>null</code>).
     * 
     * @see #setLegendItemShape(Shape)
     */
    public Shape getLegendItemShape() {
        return this.legendItemShape;
    }

    /**
     * Sets the shape used for legend items and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param shape
     *            the shape (<code>null</code> not permitted).
     * 
     * @see #getLegendItemShape()
     */
    public void setLegendItemShape(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }
        this.legendItemShape = shape;
         fireChangeEvent();
    }

    /**
     * Returns the legend label generator.
     * 
     * @return The legend label generator (never <code>null</code>).
     * 
     * @see #setLegendLabelGenerator(PieSectionLabelGenerator)
     */
    public PieSectionLabelGenerator getLegendLabelGenerator() {
        return this.legendLabelGenerator;
    }

    /**
     * Sets the legend label generator and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     * 
     * @param generator
     *            the generator (<code>null</code> not permitted).
     * 
     * @see #getLegendLabelGenerator()
     */
    public void setLegendLabelGenerator(PieSectionLabelGenerator generator) {
        if (generator == null) {
            throw new IllegalArgumentException("Null 'generator' argument.");
        }
        this.legendLabelGenerator = generator;
         fireChangeEvent();
    }

    /**
     * Returns the legend label tool tip generator.
     * 
     * @return The legend label tool tip generator (possibly <code>null</code>).
     * 
     * @see #setLegendLabelToolTipGenerator(PieSectionLabelGenerator)
     */
    public PieSectionLabelGenerator getLegendLabelToolTipGenerator() {
        return this.legendLabelToolTipGenerator;
    }

    /**
     * Sets the legend label tool tip generator and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param generator
     *            the generator (<code>null</code> permitted).
     * 
     * @see #getLegendLabelToolTipGenerator()
     */
    public void setLegendLabelToolTipGenerator(PieSectionLabelGenerator generator) {
        this.legendLabelToolTipGenerator = generator;
         fireChangeEvent();
    }

    /**
     * Initialises the drawing procedure. This method will be called before the
     * first item is rendered, giving the plot an opportunity to initialise any
     * state information it wants to maintain.
     * 
     * @param canvas
     *            the graphics device.
     * @param plotArea
     *            the plot area (<code>null</code> not permitted).
     * @param plot
     *            the plot.
     * @param index
     *            the secondary index (<code>null</code> for primary renderer).
     * @param info
     *            collects chart rendering information for return to caller.
     * 
     * @return A state object (maintains state information relevant to one chart
     *         drawing).
     */
    public PiePlotState initialise(Canvas canvas, RectShape plotArea, PiePlot plot, Integer index,
            PlotRenderingInfo info) {

        PiePlotState state = new PiePlotState(info);
        state.setPassesRequired(2);
        if (this.dataset != null) {
            state.setTotal(DatasetUtilities.calculatePieDatasetTotal(plot.getDataset()));
        }
        state.setLatestAngle(plot.getStartAngle());
        return state;

    }

    /**
     * Draws the plot on a graphics device (such as the screen or a
     * printer).
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area within which the plot should be drawn.
     * @param anchor
     *            the anchor point (<code>null</code> permitted).
     * @param parentState
     *            the state from the parent plot, if there is one.
     * @param info
     *            collects info about the drawing (<code>null</code> permitted).
     */
    public void draw(Canvas canvas, RectShape area, PointF anchor, PlotState parentState,
            PlotRenderingInfo info) {

        // adjust for insets...
        RectangleInsets insets = getInsets();
        insets.trim(area);

        if (info != null) {
            info.setPlotArea(area);
            info.setDataArea(area);
        }

        drawBackground(canvas, area);
        drawOutline(canvas, area);

        canvas.save();
        canvas.clipRect((float) area.getMinX(), (float) area.getMinY(), (float) area.getMaxX(),
                (float) area.getMaxY());

        if (!DatasetUtilities.isEmptyOrNull(this.dataset)) {
            drawPie(canvas, area, info, getForegroundAlpha());
        } else {
            drawNoDataMessage(canvas, area);
        }

        canvas.restore();

        drawOutline(canvas, area);

    }

    /**
     * Draws the pie.
     * 
     * @param canvas
     *            the graphics device.
     * @param plotArea
     *            the plot area.
     * @param info
     *            chart rendering info.
     */
    protected void drawPie(Canvas canvas, RectShape plotArea, PlotRenderingInfo info, int alpha) {

        PiePlotState state = initialise(canvas, plotArea, this, null, info);

        // adjust the plot area for interior spacing and labels...
        double labelReserve = 0.0;
        if (this.labelGenerator != null && !this.simpleLabels) {
            labelReserve = this.labelGap + this.maximumLabelWidth;
        }
        double gapHorizontal = plotArea.getWidth() * (this.interiorGap + labelReserve) * 2.0;
        double gapVertical = plotArea.getHeight() * this.interiorGap * 2.0;

        /*
         * if (DEBUG_DRAW_INTERIOR) { double hGap = plotArea.getWidth() *
         * this.interiorGap; double vGap = plotArea.getHeight() *
         * this.interiorGap;
         * 
         * double igx1 = plotArea.getX() + hGap; double igx2 =
         * plotArea.getMaxX() - hGap; double igy1 = plotArea.getY() + vGap;
         * double igy2 = plotArea.getMaxY() - vGap; Paint gray = new
         * Paint(Paint.ANTI_ALIAS_FLAG); gray.setColor(Color.GRAY);
         * canvas.setPaint(gray); canvas.draw(new RectShape(igx1, igy1, igx2 - igx1,
         * igy2 - igy1)); }
         */

        double linkX = plotArea.getX() + gapHorizontal / 2;
        double linkY = plotArea.getY() + gapVertical / 2;
        double linkW = plotArea.getWidth() - gapHorizontal;
        double linkH = plotArea.getHeight() - gapVertical;

        // make the link area a square if the pie chart is to be circular...
        if (this.circular) {
            double min = Math.min(linkW, linkH) / 2;
            linkX = (linkX + linkX + linkW) / 2 - min;
            linkY = (linkY + linkY + linkH) / 2 - min;
            linkW = 2 * min;
            linkH = 2 * min;
        }

        // the link area defines the dog leg points for the linking lines to
        // the labels
        RectShape linkArea = new RectShape(linkX, linkY, linkW, linkH);
        state.setLinkArea(linkArea);

        /*
         * if (DEBUG_DRAW_LINK_AREA) { canvas.setPaint(Color.blue);
         * canvas.draw(linkArea); canvas.setPaint(Color.yellow); canvas.draw(new
         * OvalShape(linkArea.getX(), linkArea.getY(), linkArea.getWidth(),
         * linkArea.getHeight())); }
         */

        // the explode area defines the max circle/ellipse for the exploded
        // pie sections. it is defined by shrinking the linkArea by the
        // linkMargin factor.
        double lm = 0.0;
        if (!this.simpleLabels) {
            lm = this.labelLinkMargin;
        }
        double hh = linkArea.getWidth() * lm * 2.0;
        double vv = linkArea.getHeight() * lm * 2.0;
        RectShape explodeArea = new RectShape(linkX + hh / 2.0, linkY + vv / 2.0, linkW - hh, linkH
                - vv);

        state.setExplodedPieArea(explodeArea);

        // the pie area defines the circle/ellipse for regular pie sections.
        // it is defined by shrinking the explodeArea by the explodeMargin
        // factor.
        double maximumExplodePercent = getMaximumExplodePercent();
        double percent = maximumExplodePercent / (1.0 + maximumExplodePercent);

        double h1 = explodeArea.getWidth() * percent;
        double v1 = explodeArea.getHeight() * percent;
        RectShape pieArea = new RectShape(explodeArea.getX() + h1 / 2.0, explodeArea.getY() + v1
                / 2.0, explodeArea.getWidth() - h1, explodeArea.getHeight() - v1);

        /*
         * if (DEBUG_DRAW_PIE_AREA) { canvas.setPaint(Color.green);
         * canvas.draw(pieArea); }
         */
        state.setPieArea(pieArea);
        state.setPieCenterX(pieArea.getCenterX());
        state.setPieCenterY(pieArea.getCenterY());
        state.setPieWRadius(pieArea.getWidth() / 2.0);
        state.setPieHRadius(pieArea.getHeight() / 2.0);

        // plot the data (unless the dataset is null)...
        if ((this.dataset != null) && (this.dataset.getKeys().size() > 0)) {

            List keys = this.dataset.getKeys();
            double totalValue = DatasetUtilities.calculatePieDatasetTotal(this.dataset);

            int passesRequired = state.getPassesRequired();
            for (int pass = 0; pass < passesRequired; pass++) {
                double runningTotal = 0.0;
                for (int section = 0; section < keys.size(); section++) {
                    Number n = this.dataset.getValue(section);
                    if (n != null) {
                        double value = n.doubleValue();
                        if (value > 0.0) {
                            runningTotal += value;
                            drawItem(canvas, section, explodeArea, state, pass, alpha);
                        }
                    }
                }
            }
            if (this.simpleLabels) {
                drawSimpleLabels(canvas, keys, totalValue, plotArea, linkArea, state, alpha);
            } else {
                drawLabels(canvas, keys, totalValue, plotArea, linkArea, state, alpha);
            }

        } else {
            drawNoDataMessage(canvas, plotArea);
        }
    }

    /**
     * Draws a single data item.
     * 
     * @param canvas
     *            the graphics device (<code>null</code> not permitted).
     * @param section
     *            the section index.
     * @param dataArea
     *            the data plot area.
     * @param state
     *            state information for one chart.
     * @param currentPass
     *            the current pass index.
     */
    protected void drawItem(Canvas canvas, int section, RectShape dataArea, PiePlotState state,
            int currentPass, int alpha) {

        Number n = this.dataset.getValue(section);
        if (n == null) {
            return;
        }

        double value = n.doubleValue();
        double angle1 = 0.0;
        double angle2 = 0.0;

        if (this.direction == Rotation.CLOCKWISE) {
            angle1 = state.getLatestAngle();
            angle2 = angle1 + value / state.getTotal() * 360.0;
        } else if (this.direction == Rotation.ANTICLOCKWISE) {
            angle1 = state.getLatestAngle();
            angle2 = angle1 - value / state.getTotal() * 360.0;
        } else {
            throw new IllegalStateException("Rotation type not recognised.");
        }

        double angle = (angle2 - angle1);
        if (Math.abs(angle) > getMinimumArcAngleToDraw()) {
            double ep = 0.0;
            double mep = getMaximumExplodePercent();
            if (mep > 0.0) {
                ep = getExplodePercent(section) / mep;
            }
            RectShape arcBounds = getArcBounds(state.getPieArea(), state.getExplodedPieArea(),
                    angle1, angle, ep);
            ArcShape arc = new ArcShape(arcBounds, angle1, angle, true);

            if (currentPass == 0) {
                if (this.shadowPaint != null) {
                    shadowPaint.setAlpha(alpha);
                    shadowPaint.setStyle(Paint.Style.FILL);
                    arc.translate((float) this.shadowXOffset, (float) this.shadowYOffset);
                    arc.fill(canvas, shadowPaint);
                }
            } else if (currentPass == 1) {
                Comparable key = getSectionKey(section);
                Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG,
                        lookupSectionPaintType(key));
                paint.setAlpha(alpha);
                arc.fill(canvas, paint);

                Paint outlinePaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG,
                        lookupSectionOutlinePaintType(key), lookupSectionOutlineStroke(key),
                        lookupSectionOutlineEffect(key));
                outlinePaint.setAlpha(alpha);
                if (this.sectionOutlinesVisible) {
                    outlinePaint.setStyle(Paint.Style.STROKE);
                    outlinePaint.setStrokeMiter(90);
                    outlinePaint.setStrokeJoin(Paint.Join.MITER);
                    arc.draw(canvas, outlinePaint);
                }

                // update the linking line target for later
                // add an entity for the pie section
                if (state.getInfo() != null) {
                    EntityCollection entities = state.getEntityCollection();
                    if (entities != null) {
                        String tip = null;

                        String url = null;

                        PieSectionEntity entity = new PieSectionEntity(arc, this.dataset,
                                this.pieIndex, section, key, tip, url);
                        entities.add(entity);
                    }
                }
            }
        }
        state.setLatestAngle(angle2);
    }

    public void drawSimpleLabels(Canvas canvas, List keys, double totalValue,
            RectShape adjustedPlotArea, RectShape linkArea, PiePlotState state) {
        int alpha = getForegroundAlpha();
        drawSimpleLabels(canvas, keys, totalValue, adjustedPlotArea, linkArea, state, alpha);

    }

    /**
     * Draws the pie section labels in the simple form.
     * 
     * @param canvas
     *            the graphics device.
     * @param keys
     *            the section keys.
     * @param totalValue
     *            the total value for all sections in the pie.
     * @param plotArea
     *            the plot area.
     * @param pieArea
     *            the area containing the pie.
     * @param state
     *            the plot state.
     * 
     * @since JFreeChart 1.0.7
     */
    protected void drawSimpleLabels(Canvas canvas, List keys, double totalValue, RectShape plotArea,
            RectShape pieArea, PiePlotState state, int alpha) {
        int oldAlpha = labelPaintType.getAlpha();
        RectangleInsets labelInsets = new RectangleInsets(UnitType.RELATIVE, 0.18, 0.18, 0.18, 0.18);
        RectShape labelsArea = labelInsets.createInsetRectShape(pieArea);
        double runningTotal = 0.0;
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            Comparable key = (Comparable) iterator.next();
            boolean include = true;
            double v = 0.0;
            Number n = getDataset().getValue(key);
            if (n == null) {
                include = !getIgnoreNullValues();
            } else {
                v = n.doubleValue();
                include = getIgnoreZeroValues() ? v > 0.0 : v >= 0.0;
            }

            if (include) {
                runningTotal = runningTotal + v;
                // work out the mid angle (0 - 90 and 270 - 360) = right,
                // otherwise left
                double mid = getStartAngle()
                        + (getDirection().getFactor() * ((runningTotal - v / 2.0) * 360) / totalValue);

                ArcShape arc = new ArcShape(labelsArea, getStartAngle(), -(mid - getStartAngle()),
                        true);
                int x = (int) arc.getEndPoint().x;
                int y = (int) arc.getEndPoint().y;

                PieSectionLabelGenerator labelGenerator = getLabelGenerator();
                if (labelGenerator == null) {
                    continue;
                }
                String label = labelGenerator.generateSectionLabel(this.dataset, key);
                if (label == null) {
                    continue;
                }

                Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, labelPaintType,
                        labelFont);

                labelPaintType.setAlpha(255);
                // FontMetrics fm = canvas.getFontMetrics();
                RectShape bounds = TextUtilities.getTextBounds(label, paint);
                RectShape out = this.labelPadding.createOutsetRectangle(bounds);
                Shape bg = ShapeUtilities.createTranslatedShape(out, x - bounds.getCenterX(), y
                        - bounds.getCenterY());
                if (this.labelShadowPaintType != null) {
                    Shape shadow = ShapeUtilities.createTranslatedShape(bg, this.shadowXOffset,
                            this.shadowYOffset);
                    PaintUtility.updatePaint(paint, labelShadowPaintType);
                    shadow.fill(canvas, paint);
                }
                if (this.labelBackgroundPaintType != null) {
                    PaintUtility.updatePaint(paint, labelBackgroundPaintType);
                    bg.fill(canvas, paint);
                }
                if (this.labelOutlinePaintType != null && this.labelOutlineStroke != null) {
                    paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG,
                            labelOutlinePaintType, labelOutlineStroke, labelOutlineEffect);
                    bg.draw(canvas, paint);

                }

                paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, labelPaintType,
                        labelFont);

                TextUtilities.drawAlignedString(getLabelGenerator().generateSectionLabel(
                        getDataset(), key), canvas, x, y, TextAnchor.CENTER, paint);

            }
        }

        labelPaintType.setAlpha(oldAlpha);
        // canvas.setComposite(originalComposite);

    }

    public void drawLabels(Canvas canvas, List keys, double totalValue, RectShape adjustedPlotArea,
            RectShape linkArea, PiePlotState state) {
        int alpha = getForegroundAlpha();
        drawLabels(canvas, keys, totalValue, adjustedPlotArea, linkArea, state, alpha);
    }

    /**
     * Draws the labels for the pie sections.
     * 
     * @param canvas
     *            the graphics device.
     * @param keys
     *            the keys.
     * @param totalValue
     *            the total value.
     * @param plotArea
     *            the plot area.
     * @param linkArea
     *            the link area.
     * @param state
     *            the state.
     */
    protected void drawLabels(Canvas canvas, List keys, double totalValue, RectShape plotArea,
            RectShape linkArea, PiePlotState state, int alphaa) {

        // classify the keys according to which side the label will appear...
        DefaultKeyedValues leftKeys = new DefaultKeyedValues();
        DefaultKeyedValues rightKeys = new DefaultKeyedValues();

        double runningTotal = 0.0;
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            Comparable key = (Comparable) iterator.next();
            boolean include = true;
            double v = 0.0;
            Number n = this.dataset.getValue(key);
            if (n == null) {
                include = !this.ignoreNullValues;
            } else {
                v = n.doubleValue();
                include = this.ignoreZeroValues ? v > 0.0 : v >= 0.0;
            }

            if (include) {
                runningTotal = runningTotal + v;
                // work out the mid angle (0 - 90 and 270 - 360) = right,
                // otherwise left
                double mid = this.startAngle
                        - (this.direction.getFactor() * ((runningTotal - v / 2.0) * 360) / totalValue);
                if (Math.cos(Math.toRadians(mid)) < 0.0) {
                    leftKeys.addValue(key, new Double(mid));
                } else {
                    rightKeys.addValue(key, new Double(mid));
                }
            }
        }

        // canvas.setFont(getLabelFont());
        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, getLabelPaintType(),
                getLabelFont());
        paint.setAlpha(255);

        // calculate the max label width from the plot dimensions, because
        // a circular pie can leave a lot more room for labels...
        double marginX = plotArea.getX() + this.interiorGap * plotArea.getWidth();
        double gap = plotArea.getWidth() * this.labelGap;
        double ww = linkArea.getX() - gap - marginX;
        float labelWidth = (float) this.labelPadding.trimWidth(ww);

        // draw the labels...
        if (this.labelGenerator != null) {
            drawLeftLabels(leftKeys, canvas, plotArea, linkArea, labelWidth, state);
            drawRightLabels(rightKeys, canvas, plotArea, linkArea, labelWidth, state);
        }
    }

    /**
     * Draws the left labels.
     * 
     * @param keys
     *            a collection of keys and angles (to the middle of the section,
     *            in degrees) for the sections on the left side of the plot.
     * @param canvas
     *            the graphics device.
     * @param plotArea
     *            the plot area.
     * @param linkArea
     *            the link area.
     * @param maxLabelWidth
     *            the maximum label width.
     * @param state
     *            the state.
     */
    protected void drawLeftLabels(KeyedValues keys, Canvas canvas, RectShape plotArea,
            RectShape linkArea, float maxLabelWidth, PiePlotState state) {

        this.labelDistributor.clear();
        double lGap = plotArea.getWidth() * this.labelGap;
        double verticalLinkRadius = state.getLinkArea().getHeight() / 2.0;
        Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, this.labelPaintType);
        for (int i = 0; i < keys.getItemCount(); i++) {
            String label = this.labelGenerator.generateSectionLabel(this.dataset, keys.getKey(i));
            if (label != null) {
                TextBlock block = TextUtilities.createTextBlock(label, this.labelFont,
                        this.labelPaintType, maxLabelWidth, new G2TextMeasurer(paint));
                TextBox labelBox = new TextBox(block);
                labelBox.setBackgroundPaintType(this.labelBackgroundPaintType);
                labelBox.setOutlinePaintType(this.labelOutlinePaintType);
                labelBox.setOutlineStroke(this.labelOutlineStroke);
                labelBox.setShadowPaintType(this.labelShadowPaintType);
                labelBox.setInteriorGap(this.labelPadding);
                double theta = Math.toRadians(keys.getValue(i).doubleValue());
                double baseY = state.getPieCenterY() + Math.sin(theta) * verticalLinkRadius;
                double hh = labelBox.getHeight(canvas);

                this.labelDistributor.addPieLabelRecord(new PieLabelRecord(keys.getKey(i), theta,
                        baseY, labelBox, hh, lGap / 2.0 + lGap / 2.0 * -Math.cos(theta), 1.0
                                - getLabelLinkDepth() + getExplodePercent(keys.getKey(i))));
            }
        }
        double hh = plotArea.getHeight();
        double gap = hh * getInteriorGap();
        this.labelDistributor.distributeLabels(plotArea.getMinY() + gap, hh - 2 * gap);
        for (int i = 0; i < this.labelDistributor.getItemCount(); i++) {
            drawLeftLabel(canvas, state, this.labelDistributor.getPieLabelRecord(i));
        }
    }

    /**
     * Draws the right labels.
     * 
     * @param keys
     *            the keys.
     * @param canvas
     *            the graphics device.
     * @param plotArea
     *            the plot area.
     * @param linkArea
     *            the link area.
     * @param maxLabelWidth
     *            the maximum label width.
     * @param state
     *            the state.
     */
    protected void drawRightLabels(KeyedValues keys, Canvas canvas, RectShape plotArea,
            RectShape linkArea, float maxLabelWidth, PiePlotState state) {

        // draw the right labels...
        this.labelDistributor.clear();
        double lGap = plotArea.getWidth() * this.labelGap;
        double verticalLinkRadius = state.getLinkArea().getHeight() / 2.0;

        for (int i = 0; i < keys.getItemCount(); i++) {
            String label = this.labelGenerator.generateSectionLabel(this.dataset, keys.getKey(i));

            if (label != null) {
                Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG,
                        this.labelPaintType, this.labelFont);

                TextBlock block = TextUtilities.createTextBlock(label, this.labelFont,
                        this.labelPaintType, maxLabelWidth, new G2TextMeasurer(paint));
                TextBox labelBox = new TextBox(block);
                labelBox.setBackgroundPaintType(this.labelBackgroundPaintType);
                labelBox.setOutlinePaintType(this.labelOutlinePaintType);
                labelBox.setOutlineStroke(this.labelOutlineStroke);
                labelBox.setShadowPaintType(this.labelShadowPaintType);
                labelBox.setInteriorGap(this.labelPadding);
                double theta = Math.toRadians(keys.getValue(i).doubleValue());
                double baseY = state.getPieCenterY() + Math.sin(theta) * verticalLinkRadius;
                double hh = labelBox.getHeight(canvas);
                this.labelDistributor.addPieLabelRecord(new PieLabelRecord(keys.getKey(i), theta,
                        baseY, labelBox, hh, lGap / 2.0 + lGap / 2.0 * Math.cos(theta), 1.0
                                - getLabelLinkDepth() + getExplodePercent(keys.getKey(i))));
            }
        }
        double hh = plotArea.getHeight();
        double gap = hh * getInteriorGap();
        this.labelDistributor.distributeLabels(plotArea.getMinY() + gap, hh - 2 * gap);
        for (int i = 0; i < this.labelDistributor.getItemCount(); i++) {
            drawRightLabel(canvas, state, this.labelDistributor.getPieLabelRecord(i));
        }

    }

    /**
     * Returns a collection of legend items for the pie chart.
     * 
     * @return The legend items (never <code>null</code>).
     */
    public LegendItemCollection getLegendItems() {

        LegendItemCollection result = new LegendItemCollection();
        if (this.dataset == null) {
            return result;
        }
        List keys = this.dataset.getKeys();
        int section = 0;
        Shape shape = getLegendItemShape();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            Comparable key = (Comparable) iterator.next();
            Number n = this.dataset.getValue(key);
            boolean include = true;
            if (n == null) {
                include = !this.ignoreNullValues;
            } else {
                double v = n.doubleValue();
                if (v == 0.0) {
                    include = !this.ignoreZeroValues;
                } else {
                    include = v > 0.0;
                }
            }
            if (include) {
                String label = this.legendLabelGenerator.generateSectionLabel(this.dataset, key);
                if (label != null) {
                    String description = label;
                    String toolTipText = null;
                    if (this.legendLabelToolTipGenerator != null) {
                        toolTipText = this.legendLabelToolTipGenerator.generateSectionLabel(
                                this.dataset, key);
                    }
                    String urlText = null;

                    Paint black = new Paint(Paint.ANTI_ALIAS_FLAG);
                    black.setColor(Color.BLACK);

                    Float outlineStroke = lookupSectionOutlineStroke(key);

                    LegendItem item = new LegendItem(label, description, toolTipText, urlText,
                            true, shape, true, lookupSectionPaintType(key), true,
                            lookupSectionOutlinePaintType(key), outlineStroke, false, // line
                            new LineShape(), 0.0f, new SolidColor(Color.BLACK));
                    item.setDataset(getDataset());
                    item.setSeriesIndex(this.dataset.getIndex(key));
                    item.setSeriesKey(key);
                    result.add(item);
                }
                section++;
            } else {
                section++;
            }
        }
        return result;
    }

    /**
     * Returns a short string describing the type of plot.
     * 
     * @return The plot type.
     */
    public String getPlotType() {
        return "Pie_Plot";
    }

    /**
     * Returns a RectShape that can be used to create a pie section (taking into
     * account the amount by which the pie section is 'exploded').
     * 
     * @param unexploded
     *            the area inside which the unexploded pie sections are drawn.
     * @param exploded
     *            the area inside which the exploded pie sections are drawn.
     * @param angle
     *            the start angle.
     * @param extent
     *            the extent of the arc.
     * @param explodePercent
     *            the amount by which the pie section is exploded.
     * 
     * @return A RectShape that can be used to create a pie section.
     */
    protected RectShape getArcBounds(RectShape unexploded, RectShape exploded, double angle,
            double extent, double explodePercent) {

        if (explodePercent == 0.0) {
            return unexploded;
        } else {
            ArcShape arc1 = new ArcShape(unexploded, angle, extent / 2, true);
            PointF point1 = arc1.getEndPoint();
            ArcShape arc2 = new ArcShape(exploded, angle, extent / 2, true);
            PointF point2 = arc2.getEndPoint();
            double deltaX = (point1.x - point2.x) * explodePercent;
            double deltaY = (point1.y - point2.y) * explodePercent;
            return new RectShape(unexploded.getX() - deltaX, unexploded.getY() - deltaY, unexploded
                    .getWidth(), unexploded.getHeight());
        }
    }

    /**
     * Draws a section label on the left side of the pie chart.
     * 
     * @param canvas
     *            the graphics device.
     * @param state
     *            the state.
     * @param record
     *            the label record.
     */
    protected void drawLeftLabel(Canvas canvas, PiePlotState state, PieLabelRecord record) {

        double anchorX = state.getLinkArea().getMinX();
        double targetX = anchorX - record.getGap();
        double targetY = record.getAllocatedY();

        if (this.labelLinksVisible) {
            double theta = record.getAngle();
            double linkX = state.getPieCenterX() + Math.cos(theta) * state.getPieWRadius()
                    * record.getLinkPercent();
            double linkY = state.getPieCenterY() + Math.sin(theta) * state.getPieHRadius()
                    * record.getLinkPercent();
            double elbowX = state.getPieCenterX() + Math.cos(theta)
                    * state.getLinkArea().getWidth() / 2.0;
            double elbowY = state.getPieCenterY() + Math.sin(theta)
                    * state.getLinkArea().getHeight() / 2.0;
            double anchorY = elbowY;

            Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, labelLinkPaintType,
                    labelLinkStroke, labelLinkEffect);
            PieLabelLinkStyle style = getLabelLinkStyle();
            if (style.equals(PieLabelLinkStyle.STANDARD)) {
                canvas.drawLine((float) linkX, (float) linkY, (float) elbowX, (float) elbowY, paint);
                canvas
                        .drawLine((float) anchorX, (float) anchorY, (float) elbowX, (float) elbowY,
                                paint);
                canvas.drawLine((float) anchorX, (float) anchorY, (float) targetX, (float) targetY,
                        paint);

            } else if (style.equals(PieLabelLinkStyle.QUAD_CURVE)) {
                QuadCurveShape q = new QuadCurveShape();
                q.setCurve(targetX, targetY, anchorX, anchorY, elbowX, elbowY);
                q.draw(canvas, paint);
            } else if (style.equals(PieLabelLinkStyle.CUBIC_CURVE)) {
                CubicCurveShape c = new CubicCurveShape();
                c.setCurve(targetX, targetY, anchorX, anchorY, elbowX, elbowY, linkX, linkY);
                c.draw(canvas, paint);
            }
        }
        TextBox tb = record.getLabel();
        tb.draw(canvas, (float) targetX, (float) targetY, RectangleAnchor.RIGHT);

    }

    /**
     * Draws a section label on the right side of the pie chart.
     * 
     * @param canvas
     *            the graphics device.
     * @param state
     *            the state.
     * @param record
     *            the label record.
     */
    protected void drawRightLabel(Canvas canvas, PiePlotState state, PieLabelRecord record) {

        double anchorX = state.getLinkArea().getMaxX();
        double targetX = anchorX + record.getGap();
        double targetY = record.getAllocatedY();

        if (this.labelLinksVisible) {
            double theta = record.getAngle();
            double linkX = state.getPieCenterX() + Math.cos(theta) * state.getPieWRadius()
                    * record.getLinkPercent();
            double linkY = state.getPieCenterY() + Math.sin(theta) * state.getPieHRadius()
                    * record.getLinkPercent();
            double elbowX = state.getPieCenterX() + Math.cos(theta)
                    * state.getLinkArea().getWidth() / 2.0;
            double elbowY = state.getPieCenterY() + Math.sin(theta)
                    * state.getLinkArea().getHeight() / 2.0;
            double anchorY = elbowY;

            PieLabelLinkStyle style = getLabelLinkStyle();
            Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, labelLinkPaintType,
                    labelLinkStroke, labelLinkEffect);
            if (style.equals(PieLabelLinkStyle.STANDARD)) {
                canvas.drawLine((float) linkX, (float) linkY, (float) elbowX, (float) elbowY, paint);
                canvas
                        .drawLine((float) anchorX, (float) anchorY, (float) elbowX, (float) elbowY,
                                paint);
                canvas.drawLine((float) anchorX, (float) anchorY, (float) targetX, (float) targetY,
                        paint);
            } else if (style.equals(PieLabelLinkStyle.QUAD_CURVE)) {
                QuadCurveShape q = new QuadCurveShape();
                q.setCurve(targetX, targetY, anchorX, anchorY, elbowX, elbowY);

                q.draw(canvas, paint);
            } else if (style.equals(PieLabelLinkStyle.CUBIC_CURVE)) {
                CubicCurveShape c = new CubicCurveShape();
                c.setCurve(targetX, targetY, anchorX, anchorY, elbowX, elbowY, linkX, linkY);
                c.draw(canvas, paint);
            }
        }

        TextBox tb = record.getLabel();
        tb.draw(canvas, (float) targetX, (float) targetY, RectangleAnchor.LEFT);

    }

    // DEPRECATED FIELDS AND METHODS...

    /**
     * The paint for ALL sections (overrides list).
     * 
     * @deprecated This field is redundant, it is sufficient to use
     *             sectionPaintMap and baseSectionPaint. Deprecated as of
     *             JFreeChart version 1.0.6.
     */
    private transient PaintType sectionPaint;

    /**
     * The outline paint for ALL sections (overrides list).
     * 
     * @deprecated This field is redundant, it is sufficient to use
     *             sectionOutlinePaintMap and baseSectionOutlinePaint.
     *             Deprecated as of JFreeChart version 1.0.6.
     */
    private transient PaintType sectionOutlinePaintType;

    /**
     * The outline stroke for ALL sections (overrides list).
     * 
     * @deprecated This field is redundant, it is sufficient to use
     *             sectionOutlineStrokeMap and baseSectionOutlineStroke.
     *             Deprecated as of JFreeChart version 1.0.6.
     */
    private transient Float sectionOutlineStroke;

    /** The outline effect for ALL sections (overrides list).
     * 
    * @deprecated This field is redundant, it is sufficient to use
    *             sectionOutlineEffectMap and baseSectionOutlineEffect.
    */
    private transient PathEffect sectionOutlineEffect;
//TODO
    /**
     * Returns the outline paint for ALL sections in the plot.
     * 
     * @return The paint type (possibly <code>null</code>).
     * 
     * @see #setSectionOutlinePaintType(Comparable key, PaintType paintType)
     * 
     * @deprecated Use {@link #getSectionOutlinePaintType(Comparable)} and
     *             {@link #getBaseSectionOutlinePaintType()}. Deprecated as of
     *             JFreeChart version 1.0.6.
     */
    public PaintType getSectionOutlinePaintType() {
        return this.sectionOutlinePaintType;
    }

    /**
     * Returns the outline stroke for ALL sections in the plot.
     * 
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setSectionOutlineStroke(int section, Float stroke)
     * 
     * @deprecated Use {@link #getSectionOutlineStroke(Comparable)} and
     *             {@link #getBaseSectionOutlineStroke()}. Deprecated as of
     *             JFreeChart version 1.0.6.
     */
    public Float getSectionOutlineStroke() {
        return this.sectionOutlineStroke;
    }

    /**
     * Returns the outline effect for ALL sections in the plot.
     * 
     * @return The effect.
     * 
     * @see #setSectionOutlineEffect(PathEffect effect)
     * 
     * @deprecated Use {@link #getSectionOutlineEffect(Comparable)} and
     *             {@link #getBaseSectionOutlineEffect()}
     */
    public PathEffect getSectionOutlineEffect() {
        return this.sectionOutlineEffect;
    }

    /**
     * Sets the effect used to fill a section of the pie and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param effect
     *            the effect (<code>null</code> permitted).
     * 
     * @deprecated Use {@link #setSectionOutlineEffect(Comparable, PathEffect)}
     *             instead.
     */
    public void setSectionOutlineEffect(PathEffect effect) {
        this.sectionOutlineEffect = effect;
         fireChangeEvent();
    }

    /**
     * Sets the stroke used to fill a section of the pie and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param section
     *            the section index (zero-based).
     * @param stroke
     *            the stroke (<code>null</code> permitted).
     * 
     * @deprecated Use {@link #setSectionOutlineStroke(Comparable, Float)}
     *             instead.
     */
    public void setSectionOutlineStroke(int section, Float stroke) {
        Comparable key = getSectionKey(section);
        setSectionOutlineStroke(key, stroke);
    }

    /**
     * Returns the amount that a section should be 'exploded'.
     * 
     * @param section
     *            the section number.
     * 
     * @return The amount that a section should be 'exploded'.
     * 
     * @deprecated Use {@link #getExplodePercent(Comparable)} instead.
     */
    public double getExplodePercent(int section) {
        Comparable key = getSectionKey(section);
        return getExplodePercent(key);
    }

    /**
     * Sets the paint for ALL sections in the plot.  If this is set to
     * </code>null</code>, then a list of paints is used instead (to allow
     * different colors to be used for each section).
     *
     * @param paint  the paint (<code>null</code> permitted).
     *
     * @see #getSectionPaintType()
     *
     * @deprecated Use {@link #setSectionPaintType(Comparable, Paint)} and
     *     {@link #setBaseSectionPaintType(PaintType)}.  
     *     Deprecated as of JFreeChart version 1.0.6.
     */
    public void setSectionPaintType(PaintType paintType) {
        this.sectionPaint = paintType;
        fireChangeEvent();
    }
    
    /**
     * Sets the outline paint for ALL sections in the plot.  If this is set to
     * </code>null</code>, then a list of paints is used instead (to allow
     * different colors to be used for each section).
     *
     * @param paint  the paint type (<code>null</code> permitted).
     *
     * @see #getSectionOutlinePaintType()
     *
     * @deprecated Use {@link #setSectionOutlinePaintType(Comparable, PaintType)} and
     *     {@link #setBaseSectionOutlinePaintType(PaintType)}.  Deprecated as of
     *     version JFreeChart 1.0.6.
     */
    public void setSectionOutlinePaintType(PaintType paintType) {
        this.sectionOutlinePaintType = paintType;
        fireChangeEvent();
    }
    
    /**
     * Sets the outline stroke for ALL sections in the plot.  If this is set to
     * </code>null</code>, then a list of paints is used instead (to allow
     * different colors to be used for each section).
     *
     * @param stroke  the stroke (<code>null</code> permitted).
     *
     * @see #getSectionOutlineStroke()
     *
     * @deprecated Use {@link #setSectionOutlineStroke(Comparable, Float)} and
     *     {@link #setBaseSectionOutlineStroke(Stroke)}.  Deprecated as of
     *     version JFreeChart 1.0.6.
     */
    public void setSectionOutlineStroke(Float stroke) {
        this.sectionOutlineStroke = stroke;
        fireChangeEvent();
    }
    
//    /**
//     * Sets the tool tip generator and sends a {@link PlotChangeEvent} to all
//     * registered listeners.  Set the generator to <code>null</code> if you
//     * don't want any tool tips.
//     *
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @see #getToolTipGenerator()
//     */
//    public void setToolTipGenerator(PieToolTipGenerator generator) {
//        this.toolTipGenerator = generator;
//        fireChangeEvent();
//    }
    
//    /**
//     * Sets the URL generator and sends a {@link PlotChangeEvent} to all
//     * registered listeners.
//     *
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @see #getURLGenerator()
//     */
//    public void setURLGenerator(PieURLGenerator generator) {
//        this.urlGenerator = generator;
//        fireChangeEvent();
//    }

//    /**
//     * Sets the legend label URL generator and sends a
//     * {@link PlotChangeEvent} to all registered listeners.
//     *
//     * @param generator  the generator (<code>null</code> permitted).
//     *
//     * @see #getLegendLabelURLGenerator()
//     *
//     * @since 1.0.4
//     */
//    public void setLegendLabelURLGenerator(PieURLGenerator generator) {
//        this.legendLabelURLGenerator = generator;
//        fireChangeEvent();
//    }


}
