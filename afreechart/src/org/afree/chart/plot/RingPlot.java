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
 * -------------
 * RingPlot.java
 * -------------
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
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limtied);
 * Contributor(s):   Christoph Beck (bug 2121818);
 *
 * Changes
 * -------
 * 08-Nov-2004 : Version 1 (DG);
 * 22-Feb-2005 : Renamed DonutPlot --> RingPlot (DG);
 * 06-Jun-2005 : Added default constructor and fixed equals() method to handle
 *               GradientPaint (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Dec-2005 : Fixed problem with entity shape (bug 1386328) (DG);
 * 27-Sep-2006 : Updated drawItem() method for new lookup methods (DG);
 * 12-Oct-2006 : Added configurable section depth (DG);
 * 14-Feb-2007 : Added notification in setSectionDepth() method (DG);
 * 23-Sep-2008 : Fix for bug 2121818 by Christoph Beck (DG);
 *
 */

package org.afree.chart.plot;

import java.io.Serializable;

import org.afree.data.general.PieDataset;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.entity.PieSectionEntity;
import org.afree.chart.event.PlotChangeEvent;
import org.afree.graphics.geom.ArcShape;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;
import org.afree.ui.RectangleInsets;
import org.afree.util.Rotation;
import org.afree.util.UnitType;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.graphics.Region.Op;

/**
 * A customised pie plot that leaves a hole in the middle.
 */
public class RingPlot extends PiePlot implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 1556064784129676620L;

    /**
     * A flag that controls whether or not separators are drawn between the
     * sections of the chart.
     */
    private boolean separatorsVisible;

    /** The stroke used to draw separators. */
    private transient float separatorStroke;
    
    /** The effect used to draw separators. */
    private transient PathEffect separatorEffect;

    /** The paint used to draw separators. */
    private transient PaintType separatorPaintType;

    /**
     * The length of the inner separator extension (as a percentage of the
     * depth of the sections).
     */
    private double innerSeparatorExtension;

    /**
     * The length of the outer separator extension (as a percentage of the
     * depth of the sections).
     */
    private double outerSeparatorExtension;

    /**
     * The depth of the section as a percentage of the diameter.
     */
    private double sectionDepth;

    /**
     * Creates a new plot with a <code>null</code> dataset.
     */
    public RingPlot() {
        this(null);
    }

    /**
     * Creates a new plot for the specified dataset.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public RingPlot(PieDataset dataset) {
        super(dataset);
        this.separatorsVisible = true;
//        this.separatorStroke = new BasicStroke(0.5f);
        this.separatorStroke = 0.5f;
        //this.separatorPaint = Color.gray;
        this.separatorPaintType = new SolidColor(Color.GRAY);
        this.separatorEffect = null;
        this.innerSeparatorExtension = 0.20;  // twenty percent
        this.outerSeparatorExtension = 0.20;  // twenty percent
        this.sectionDepth = 0.20; // 20%
    }

    /**
     * Returns a flag that indicates whether or not separators are drawn between
     * the sections in the chart.
     *
     * @return A boolean.
     *
     * @see #setSeparatorsVisible(boolean)
     */
    public boolean getSeparatorsVisible() {
        return this.separatorsVisible;
    }

    /**
     * Sets the flag that controls whether or not separators are drawn between
     * the sections in the chart, and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param visible  the flag.
     *
     * @see #getSeparatorsVisible()
     */
    public void setSeparatorsVisible(boolean visible) {
        this.separatorsVisible = visible;
        fireChangeEvent();
    }

    /**
     * Returns the separator stroke.
     *
     * @return The stroke (never <code>null</code>).
     *
     * @see #setSeparatorStroke(Float stroke)
     */
    public Float getSeparatorStroke() {
        return this.separatorStroke;
    }

    /**
     * Sets the stroke used to draw the separator between sections and sends
     * a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     *
     * @see #getSeparatorStroke()
     */
    public void setSeparatorStroke(Float stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.separatorStroke = stroke;
        fireChangeEvent();
    }

    /**
     * Returns the separator paint.
     *
     * @return The paint type (never <code>null</code>).
     *
     * @see #setSeparatorPaintType(PaintType paintType)
     */
    public PaintType getSeparatorPaintType() {
        return this.separatorPaintType;
    }

    /**
     * Sets the paint used to draw the separator between sections and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paintType  the paint (<code>null</code> not permitted).
     *
     * @see #getSeparatorPaintType()
     */
    public void setSeparatorPaintType(PaintType paintType) {
        if (paintType == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.separatorPaintType = paintType;
        fireChangeEvent();
    }

    /**
     * Returns the separator effect.
     *
     * @return The effect (never <code>null</code>).
     *
     * @see #setSeparatorEffect(PathEffect effect)
     */
    public PathEffect getSeparatorEffect() {
        return this.separatorEffect;
    }

    /**
     * Sets the effect used to draw the separator between sections and sends
     * a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param effect  the effect (<code>null</code> not permitted).
     *
     * @see #getSeparatorEffect()
     */
    public void setSeparatorEffect(PathEffect effect) {
        this.separatorEffect = effect;
        fireChangeEvent();
    }

    /**
     * Returns the length of the inner extension of the separator line that
     * is drawn between sections, expressed as a percentage of the depth of
     * the section.
     *
     * @return The inner separator extension (as a percentage).
     *
     * @see #setInnerSeparatorExtension(double)
     */
    public double getInnerSeparatorExtension() {
        return this.innerSeparatorExtension;
    }

    /**
     * Sets the length of the inner extension of the separator line that is
     * drawn between sections, as a percentage of the depth of the
     * sections, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param percent  the percentage.
     *
     * @see #getInnerSeparatorExtension()
     * @see #setOuterSeparatorExtension(double)
     */
    public void setInnerSeparatorExtension(double percent) {
        this.innerSeparatorExtension = percent;
        fireChangeEvent();
    }

    /**
     * Returns the length of the outer extension of the separator line that
     * is drawn between sections, expressed as a percentage of the depth of
     * the section.
     *
     * @return The outer separator extension (as a percentage).
     *
     * @see #setOuterSeparatorExtension(double)
     */
    public double getOuterSeparatorExtension() {
        return this.outerSeparatorExtension;
    }

    /**
     * Sets the length of the outer extension of the separator line that is
     * drawn between sections, as a percentage of the depth of the
     * sections, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param percent  the percentage.
     *
     * @see #getOuterSeparatorExtension()
     */
    public void setOuterSeparatorExtension(double percent) {
        this.outerSeparatorExtension = percent;
        fireChangeEvent();
    }

    /**
     * Returns the depth of each section, expressed as a percentage of the
     * plot radius.
     *
     * @return The depth of each section.
     *
     * @see #setSectionDepth(double)
     * @since JFreeChart 1.0.3
     */
    public double getSectionDepth() {
        return this.sectionDepth;
    }

    /**
     * The section depth is given as percentage of the plot radius.
     * Specifying 1.0 results in a straightforward pie chart.
     *
     * @param sectionDepth  the section depth.
     *
     * @see #getSectionDepth()
     * @since JFreeChart 1.0.3
     */
    public void setSectionDepth(double sectionDepth) {
        this.sectionDepth = sectionDepth;
        fireChangeEvent();
    }

    /**
     * Initialises the plot state (which will store the total of all dataset
     * values, among other things).  This method is called once at the
     * beginning of each drawing.
     *
     * @param canvas  the graphics device.
     * @param plotArea  the plot area (<code>null</code> not permitted).
     * @param plot  the plot.
     * @param index  the secondary index (<code>null</code> for primary
     *               renderer).
     * @param info  collects chart rendering information for return to caller.
     *
     * @return A state object (maintains state information relevant to one
     *         chart drawing).
     */
    public PiePlotState initialise(Canvas canvas, RectShape plotArea,
            PiePlot plot, Integer index, PlotRenderingInfo info) {

        PiePlotState state = super.initialise(canvas, plotArea, plot, index, info);
        state.setPassesRequired(3);
        return state;

    }

    /**
     * Draws a single data item.
     *
     * @param canvas  the graphics device (<code>null</code> not permitted).
     * @param section  the section index.
     * @param dataArea  the data plot area.
     * @param state  state information for one chart.
     * @param currentPass  the current pass index.
     */
    protected void drawItem(Canvas canvas,
                            int section,
                            RectShape dataArea,
                            PiePlotState state,
                            int currentPass, int alpha) {
        canvas.save();
        PieDataset dataset = getDataset();
        Number n = dataset.getValue(section);
        if (n == null) {
            return;
        }
        double value = n.doubleValue();
        double angle1 = 0.0;
        double angle2 = 0.0;

        Rotation direction = getDirection();
        if (direction == Rotation.CLOCKWISE) {
            angle1 = state.getLatestAngle();
            angle2 = angle1 - value / state.getTotal() * 360.0;
        }
        else if (direction == Rotation.ANTICLOCKWISE) {
            angle1 = state.getLatestAngle();
            angle2 = angle1 + value / state.getTotal() * 360.0;
        }
        else {
            throw new IllegalStateException("Rotation type not recognised.");
        }

        double angle = (angle2 - angle1);
        if (Math.abs(angle) > getMinimumArcAngleToDraw()) {
            Comparable key = getSectionKey(section);
            double ep = 0.0;
            double mep = getMaximumExplodePercent();
            if (mep > 0.0) {
                ep = getExplodePercent(key) / mep;
            }
            RectShape arcBounds = getArcBounds(state.getPieArea(),
                    state.getExplodedPieArea(), angle1, angle, ep);
            ArcShape arc = new ArcShape(arcBounds, angle1, angle,
                    true);

            // create the bounds for the inner arc
            double depth = this.sectionDepth / 2.0;
            RectangleInsets s = new RectangleInsets(UnitType.RELATIVE,
                depth, depth, depth, depth);
            RectShape innerArcBounds = new RectShape();
            innerArcBounds.setRect(arcBounds);
            s.trim(innerArcBounds);

            ArcShape arc2 = new ArcShape(innerArcBounds, angle1
                    + angle, -angle, true);

            LineShape separator = new LineShape(arc2.getEndPoint(),
                    arc.getStartPoint());

            if (currentPass == 0) {
                Paint shadowPaint = getShadowPaint();
                double shadowXOffset = getShadowXOffset();
                double shadowYOffset = getShadowYOffset();
                if (shadowPaint != null) {
                    Matrix mat = new Matrix();
                    mat.postTranslate((float)shadowXOffset, (float)shadowYOffset);
                    
                    Path sArc1 = new Path(arc.getPath());
                    Path sArc2 = new Path(arc2.getPath());
                    sArc1.transform(mat);
                    sArc2.transform(mat);
                    
                    canvas.clipPath(arc.getPath());
                    canvas.clipPath(arc2.getPath(), Op.XOR);

                    arc.fill(canvas, shadowPaint);
                }
            }
            else if (currentPass == 1) {
                canvas.save();
                Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, lookupSectionPaintType(key));
                canvas.clipPath(arc.getPath());
                canvas.clipPath(arc2.getPath(), Op.XOR);
                arc.fill(canvas, paint);
                canvas.restore();
                // TODO: draw outline
                PaintType outlinePaintType = lookupSectionOutlinePaintType(key);
                Float outlineStroke = lookupSectionOutlineStroke(key);
                PathEffect outlineEffect = lookupSectionOutlineEffect(key);
                Paint outlinePaint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, outlinePaintType, outlineStroke, outlineEffect);
                if (outlinePaint != null && outlineStroke != null) {
                    outlinePaint.setStyle(Style.STROKE);
                    outlinePaint.setStrokeWidth(outlineStroke);
                    Path path = new Path();
                    path.arcTo(new RectF(arc.getMinX(), arc.getMinY(), arc.getMaxX(), arc.getMaxY()), (float)angle1, (float)angle);
                    path.lineTo(arc2.getStartPoint().x, arc2.getStartPoint().y);
                    path.arcTo(new RectF(arc2.getMinX(), arc2.getMinY(), arc2.getMaxX(), arc2.getMaxY()), (float)(angle1 + angle), (float)-angle);
                    path.close();
                    canvas.drawPath(path, outlinePaint);
                }

                // add an entity for the pie section
                if (state.getInfo() != null) {
                    EntityCollection entities = state.getEntityCollection();
                    if (entities != null) {
//                        String tip = null;
//                        PieToolTipGenerator toolTipGenerator
//                                = getToolTipGenerator();
//                        if (toolTipGenerator != null) {
//                            tip = toolTipGenerator.generateToolTip(dataset,
//                                    key);
//                        }
//                        String url = null;
//                        PieURLGenerator urlGenerator = getURLGenerator();
//                        if (urlGenerator != null) {
//                            url = urlGenerator.generateURL(dataset, key,
//                                    getPieIndex());
//                        }
                        String tip = null;
                        String url = null;
                        
                        PieSectionEntity entity = new PieSectionEntity(arc,
                                dataset, getPieIndex(), section, key, tip,
                                url);
                        entities.add(entity);
                    }
                }
            }
            else if (currentPass == 2) {
                if (this.separatorsVisible) {
                    LineShape extendedSeparator = extendLine(separator,
                        this.innerSeparatorExtension,
                        this.outerSeparatorExtension);
                    Paint paint = PaintUtility.createPaint(Paint.ANTI_ALIAS_FLAG, this.separatorPaintType, this.separatorStroke, this.separatorEffect);
                    extendedSeparator.draw(canvas, paint);
                    
                }
            }
        }
        state.setLatestAngle(angle2);
        canvas.restore();
    }

    /**
     * This method overrides the default value for cases where the ring plot
     * is very thin.  This fixes bug 2121818.
     *
     * @return The label link depth, as a percentage of the plot's radius.
     */
    protected double getLabelLinkDepth() {
        return Math.min(super.getLabelLinkDepth(), getSectionDepth() / 2);
    }

    /**
     * Tests this plot for equality with an arbitrary object.
     *
     * @param obj  the object to test against (<code>null</code> permitted).
     *
     * @return A boolean.
     */
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (!(obj instanceof RingPlot)) {
//            return false;
//        }
//        RingPlot that = (RingPlot) obj;
//        if (this.separatorsVisible != that.separatorsVisible) {
//            return false;
//        }
//        if (!ObjectUtilities.equal(this.separatorStroke,
//                that.separatorStroke)) {
//            return false;
//        }
//        if (!PaintUtilities.equal(this.separatorPaint, that.separatorPaint)) {
//            return false;
//        }
//        if (this.innerSeparatorExtension != that.innerSeparatorExtension) {
//            return false;
//        }
//        if (this.outerSeparatorExtension != that.outerSeparatorExtension) {
//            return false;
//        }
//        if (this.sectionDepth != that.sectionDepth) {
//            return false;
//        }
//        return super.equals(obj);
//    }

    /**
     * Creates a new line by extending an existing line.
     *
     * @param line  the line (<code>null</code> not permitted).
     * @param startPercent  the amount to extend the line at the start point
     *                      end.
     * @param endPercent  the amount to extend the line at the end point end.
     *
     * @return A new line.
     */
    private LineShape extendLine(LineShape line, double startPercent,
                              double endPercent) {
        if (line == null) {
            throw new IllegalArgumentException("Null 'line' argument.");
        }
        double x1 = line.getX1();
        double x2 = line.getX2();
        double deltaX = x2 - x1;
        double y1 = line.getY1();
        double y2 = line.getY2();
        double deltaY = y2 - y1;
        x1 = x1 - (startPercent * deltaX);
        y1 = y1 - (startPercent * deltaY);
        x2 = x2 + (endPercent * deltaX);
        y2 = y2 + (endPercent * deltaY);
        return new LineShape(x1, y1, x2, y2);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
//    private void writeObject(ObjectOutputStream stream) throws IOException {
//        stream.defaultWriteObject();
//        SerialUtilities.writeStroke(this.separatorStroke, stream);
//        SerialUtilities.writePaint(this.separatorPaint, stream);
//    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
//    private void readObject(ObjectInputStream stream)
//        throws IOException, ClassNotFoundException {
//        stream.defaultReadObject();
//        this.separatorStroke = SerialUtilities.readStroke(stream);
//        this.separatorPaint = SerialUtilities.readPaint(stream);
//    }

}
