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
 * -------------------
 * ChartUtilities.java
 * -------------------
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
 * (C) Copyright 2001-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Wolfgang Irler;
 *                   Richard Atkinson;
 *                   Xavier Poinsard;
 *
 * Changes
 * -------
 * 11-Dec-2001 : Version 1.  The JPEG method comes from Wolfgang Irler's
 *               JFreeChartServletDemo class (DG);
 * 23-Jan-2002 : Changed saveChartAsXXX() methods to pass IOExceptions back to
 *               caller (DG);
 * 26-Jun-2002 : Added image map methods (DG);
 * 05-Aug-2002 : Added writeBufferedImage methods
 *               Modified writeImageMap method to support flexible image
 *               maps (RA);
 * 26-Aug-2002 : Added saveChartAsJPEG and writeChartAsJPEG methods with info
 *               objects (RA);
 * 05-Sep-2002 : Added writeImageMap() method to support OverLIB
 *               - http://www.bosrup.com/web/overlib (RA);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 17-Oct-2002 : Exposed JPEG quality setting and PNG compression level as
 *               parameters (DG);
 * 25-Oct-2002 : Fixed writeChartAsJPEG() empty method bug (DG);
 * 13-Mar-2003 : Updated writeImageMap method as suggested by Xavier Poinsard
 *               (see Feature Request 688079) (DG);
 * 12-Aug-2003 : Added support for custom image maps using
 *               ToolTipTagFragmentGenerator and URLTagFragmentGenerator (RA);
 * 02-Sep-2003 : Separated PNG encoding from writing chart to an
 *               OutputStream (RA);
 * 04-Dec-2003 : Chart draw() method modified to include anchor point (DG);
 * 20-Feb-2004 : Edited Javadocs and added argument checking (DG);
 * 05-Apr-2004 : Fixed problem with buffered image type (DG);
 * 01-Aug-2004 : Modified to use EncoderUtil for all image encoding (RA);
 * 02-Aug-2004 : Delegated image map related functionality to ImageMapUtil (RA);
 * 13-Jan-2005 : Renamed ImageMapUtil --> ImageMapUtilities, removed method
 *               writeImageMap(PrintWriter, String, ChartRenderingInfo) which
 *               exists in ImageMapUtilities (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Feb-2006 : API doc update (DG);
 * 19-Mar-2007 : Use try-finally to close output stream in saveChartAsXXX()
 *               methods (DG);
 * 10-Jan-2008 : Fix bug 1868251 - don't create image with transparency when
 *               saving to JPEG format (DG);
 *
 */

package org.afree.chart;

/**
 * A collection of utility methods for AFreeChart.  Includes methods for
 * converting charts to image formats (PNG and JPEG) plus creating simple HTML
 * image maps.
 *
 * @see ImageMapUtilities
 */
public abstract class ChartUtilities {

    /**
     * Applies the current theme to the specified chart.  This method is
     * provided for convenience, the theme itself is stored in the
     * {@link ChartFactory} class.
     *
     * @param chart  the chart (<code>null</code> not permitted).
     *
     * @since JFreeChart 1.0.11
     */
    public static void applyCurrentTheme(AFreeChart chart) {
        ChartFactory.getChartTheme().apply(chart);
    }
}
