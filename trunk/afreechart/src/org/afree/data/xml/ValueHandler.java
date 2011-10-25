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
 * ValueHandler.java
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
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2003-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Luke Quinane;
 *
 * Changes
 * -------
 * 23-Jan-2003 : Version 1 (DG);
 * 25-Nov-2003 : Patch to handle 'NaN' values (DG);
 *
 */

package org.afree.data.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A handler for reading a 'Value' element.
 */
public class ValueHandler extends DefaultHandler implements DatasetTags {

    /** The root handler. */
    private RootHandler rootHandler;

    /** The item handler. */
    private ItemHandler itemHandler;

    /** Storage for the current CDATA */
    private StringBuffer currentText;

    /**
     * Creates a new value handler.
     *
     * @param rootHandler  the root handler.
     * @param itemHandler  the item handler.
     */
    public ValueHandler(RootHandler rootHandler, ItemHandler itemHandler) {
        this.rootHandler = rootHandler;
        this.itemHandler = itemHandler;
        this.currentText = new StringBuffer();
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

        if (localName.equals(VALUE_TAG)) {
            // no attributes to read
            clearCurrentText();
        }
        else {
            throw new SAXException("Expecting <Value> but found " + localName);
        }

    }

    /**
     * The end of an element.
     *
     * @param namespaceURI  the namespace.
     * @param localName  the element name.
     * @param qName  the element name.
     *
     * @throws SAXException for errors.
     */
    public void endElement(String namespaceURI,
                           String localName,
                           String qName) throws SAXException {

        if (localName.equals(VALUE_TAG)) {
            Number value;
            try {
                value = Double.valueOf(this.currentText.toString());
                if (((Double) value).isNaN()) {
                    value = null;
                }
            }
            catch (NumberFormatException e1) {
                value = null;
            }
            this.itemHandler.setValue(value);
            this.rootHandler.popSubHandler();
        }
        else {
            throw new SAXException("Expecting </Value> but found " + localName);
        }

    }

    /**
     * Receives some (or all) of the text in the current element.
     *
     * @param ch  character buffer.
     * @param start  the start index.
     * @param length  the length of the valid character data.
     */
    public void characters(char[] ch, int start, int length) {
        if (this.currentText != null) {
            this.currentText.append(String.copyValueOf(ch, start, length));
        }
    }

    /**
     * Returns the current text of the textbuffer.
     *
     * @return The current text.
     */
    protected String getCurrentText() {
        return this.currentText.toString();
    }

    /**
     * Removes all text from the textbuffer at the end of a CDATA section.
     */
    protected void clearCurrentText() {
        this.currentText.delete(0, this.currentText.length());
    }

}
