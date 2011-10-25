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
 * ------------------
 * DatasetReader.java
 * ------------------
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
 * (C) Copyright 2002-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Nov-2002 : Version 1 (DG);
 *
 */

package org.afree.data.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.afree.data.category.CategoryDataset;
import org.afree.data.general.PieDataset;

import org.xml.sax.SAXException;

/**
 * A utility class for reading datasets from XML.
 */
public class DatasetReader {

    /**
     * Reads a {@link PieDataset} from an XML file.
     *
     * @param file  the file.
     *
     * @return A dataset.
     *
     * @throws IOException if there is a problem reading the file.
     */
    public static PieDataset readPieDatasetFromXML(File file)
        throws IOException {
        InputStream in = new FileInputStream(file);
        return readPieDatasetFromXML(in);
    }

    /**
     * Reads a {@link PieDataset} from a stream.
     *
     * @param in  the input stream.
     *
     * @return A dataset.
     *
     * @throws IOException if there is an I/O error.
     */
    public static PieDataset readPieDatasetFromXML(InputStream in)
        throws IOException {

        PieDataset result = null;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            PieDatasetHandler handler = new PieDatasetHandler();
            parser.parse(in, handler);
            result = handler.getDataset();
        }
        catch (SAXException e) {
            System.out.println(e.getMessage());
        }
        catch (ParserConfigurationException e2) {
            System.out.println(e2.getMessage());
        }
        return result;

    }

    /**
     * Reads a {@link CategoryDataset} from a file.
     *
     * @param file  the file.
     *
     * @return A dataset.
     *
     * @throws IOException if there is a problem reading the file.
     */
    public static CategoryDataset readCategoryDatasetFromXML(File file)
        throws IOException {
        InputStream in = new FileInputStream(file);
        return readCategoryDatasetFromXML(in);
    }

    /**
     * Reads a {@link CategoryDataset} from a stream.
     *
     * @param in  the stream.
     *
     * @return A dataset.
     *
     * @throws IOException if there is a problem reading the file.
     */
    public static CategoryDataset readCategoryDatasetFromXML(InputStream in)
        throws IOException {

        CategoryDataset result = null;

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            CategoryDatasetHandler handler = new CategoryDatasetHandler();
            parser.parse(in, handler);
            result = handler.getDataset();
        }
        catch (SAXException e) {
            System.out.println(e.getMessage());
        }
        catch (ParserConfigurationException e2) {
            System.out.println(e2.getMessage());
        }
        return result;

    }

}
