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
 * --------------------------
 * CategorySeriesHandler.java
 * --------------------------
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
 * (C) Copyright 2003-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-Jan-2003 : Version 1 (DG);
 *
 */

package org.afree.data.xml;

import java.util.Iterator;

import org.afree.data.DefaultKeyedValues;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A handler for reading a series for a category dataset.
 */
public class CategorySeriesHandler extends DefaultHandler
        implements DatasetTags {

    /** The root handler. */
    private RootHandler root;

    /** The series key. */
    private Comparable seriesKey;

    /** The values. */
    private DefaultKeyedValues values;

    /**
     * Creates a new item handler.
     *
     * @param root  the root handler.
     */
    public CategorySeriesHandler(RootHandler root) {
        this.root = root;
        this.values = new DefaultKeyedValues();
    }

    /**
     * Sets the series key.
     *
     * @param key  the key.
     */
    public void setSeriesKey(Comparable key) {
        this.seriesKey = key;
    }

    /**
     * Adds an item to the temporary storage for the series.
     *
     * @param key  the key.
     * @param value  the value.
     */
    public void addItem(Comparable key, final Number value) {
        this.values.addValue(key, value);
    }

    /**
     * The start of an element.
     *
     * @param namespaceURI  the namespace.
     * @param localName  the element name.
     * @param qName  the element name.
     * @param atts  the attributes.
     *
     * @throws SAXException for errors.
     */
    public void startElement(String namespaceURI,
                             String localName,
                             String qName,
                             Attributes atts) throws SAXException {

        if (localName.equals(SERIES_TAG)) {
            setSeriesKey(atts.getValue("name"));
            ItemHandler subhandler = new ItemHandler(this.root, this);
            this.root.pushSubHandler(subhandler);
        }
        else if (localName.equals(ITEM_TAG)) {
            ItemHandler subhandler = new ItemHandler(this.root, this);
            this.root.pushSubHandler(subhandler);
            subhandler.startElement(namespaceURI, localName, qName, atts);
        }

        else {
            throw new SAXException(
                "Expecting <Series> or <Item> tag...found " + localName
            );
        }
    }

    /**
     * The end of an element.
     *
     * @param namespaceURI  the namespace.
     * @param localName  the element name.
     * @param qName  the element name.
     */
    public void endElement(String namespaceURI,
                           String localName,
                           String qName) {

        if (this.root instanceof CategoryDatasetHandler) {
            CategoryDatasetHandler handler = (CategoryDatasetHandler) this.root;

            Iterator iterator = this.values.getKeys().iterator();
            while (iterator.hasNext()) {
                Comparable key = (Comparable) iterator.next();
                Number value = this.values.getValue(key);
                handler.addItem(this.seriesKey, key, value);
            }

            this.root.popSubHandler();
        }

    }

}
