package com.sitexa.framework.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.util.Stack;

public class DataUnformatFilter extends XMLFilterBase {

    public DataUnformatFilter() {
        state = SEEN_NOTHING;
        stateStack = new Stack();
        whitespace = new StringBuffer();
    }

    public DataUnformatFilter(XMLReader xmlreader) {
        super(xmlreader);
        state = SEEN_NOTHING;
        stateStack = new Stack();
        whitespace = new StringBuffer();
    }

    public void characters(char ac[], int i, int j)
            throws SAXException {
        if (state != SEEN_DATA) {
            int k;
            for (k = i + j; k-- > i;)
                if (!isXMLWhitespace(ac[k]))
                    break;

            if (k < i) {
                saveWhitespace(ac, i, j);
            } else {
                state = SEEN_DATA;
                emitWhitespace();
            }
        }
        if (state == SEEN_DATA)
            super.characters(ac, i, j);
    }

    protected void clearWhitespace() {
        whitespace.setLength(0);
    }

    protected void emitWhitespace()
            throws SAXException {
        char ac[] = new char[whitespace.length()];
        if (whitespace.length() > 0) {
            whitespace.getChars(0, ac.length, ac, 0);
            whitespace.setLength(0);
            super.characters(ac, 0, ac.length);
        }
    }

    public void endElement(String s, String s1, String s2)
            throws SAXException {
        if (state == SEEN_ELEMENT)
            clearWhitespace();
        else
            emitWhitespace();
        state = stateStack.pop();
        super.endElement(s, s1, s2);
    }

    public void ignorableWhitespace(char ac[], int i, int j)
            throws SAXException {
        emitWhitespace();
    }

    private boolean isXMLWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\n';
    }

    public void processingInstruction(String s, String s1)
            throws SAXException {
        emitWhitespace();
        super.processingInstruction(s, s1);
    }

    public void reset() {
        state = SEEN_NOTHING;
        stateStack = new Stack();
        whitespace = new StringBuffer();
    }

    protected void saveWhitespace(char ac[], int i, int j) {
        whitespace.append(ac, i, j);
    }

    public void startDocument()
            throws SAXException {
        reset();
        super.startDocument();
    }

    public void startElement(String s, String s1, String s2, Attributes attributes)
            throws SAXException {
        clearWhitespace();
        stateStack.push(SEEN_ELEMENT);
        state = SEEN_NOTHING;
        super.startElement(s, s1, s2, attributes);
    }

    private static final Object SEEN_NOTHING = new Object();
    private static final Object SEEN_ELEMENT = new Object();
    private static final Object SEEN_DATA = new Object();
    private Object state;
    private Stack stateStack;
    private StringBuffer whitespace;

}