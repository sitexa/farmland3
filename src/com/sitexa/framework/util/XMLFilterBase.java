package com.sitexa.framework.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

class XMLFilterBase extends XMLFilterImpl {

    public XMLFilterBase() {
    }

    public XMLFilterBase(XMLReader xmlreader) {
        super(xmlreader);
    }

    public void characters(String s)
            throws SAXException {
        char ac[] = s.toCharArray();
        characters(ac, 0, ac.length);
    }

    public void dataElement(String s, String s1)
            throws SAXException {
        dataElement("", s, "", EMPTY_ATTS, s1);
    }

    public void dataElement(String s, String s1, String s2)
            throws SAXException {
        dataElement(s, s1, "", EMPTY_ATTS, s2);
    }

    public void dataElement(String s, String s1, String s2, Attributes attributes, String s3)
            throws SAXException {
        startElement(s, s1, s2, attributes);
        characters(s3);
        endElement(s, s1, s2);
    }

    public void emptyElement(String s)
            throws SAXException {
        emptyElement("", s, "", EMPTY_ATTS);
    }

    public void emptyElement(String s, String s1)
            throws SAXException {
        emptyElement(s, s1, "", EMPTY_ATTS);
    }

    public void emptyElement(String s, String s1, String s2, Attributes attributes)
            throws SAXException {
        startElement(s, s1, s2, attributes);
        endElement(s, s1, s2);
    }

    public void endElement(String s)
            throws SAXException {
        endElement("", s, "");
    }

    public void endElement(String s, String s1)
            throws SAXException {
        endElement(s, s1, "");
    }

    public void startElement(String s)
            throws SAXException {
        startElement("", s, "", EMPTY_ATTS);
    }

    public void startElement(String s, String s1)
            throws SAXException {
        startElement(s, s1, "", EMPTY_ATTS);
    }

    protected static final Attributes EMPTY_ATTS = new AttributesImpl();

}