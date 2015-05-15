package com.sitexa.framework.util;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

public class XMLProperties {

    private File file;
    private Document doc;
    private Map propertyCache;

    public XMLProperties(String filePath) { //whole file path,i.e, c:\aaa\bbb\ccc.properties
        propertyCache = new HashMap();
        file = new File(filePath);
        try {
            SAXBuilder saxbuilder = new SAXBuilder();
            DataUnformatFilter dataunformatfilter = new DataUnformatFilter();
            saxbuilder.setXMLFilter(dataunformatfilter);
            doc = saxbuilder.build(new File(filePath));
        } catch (Exception exception) {
            System.err.println("Error creating XML parser in PropertyManager.java");
            exception.printStackTrace();
        }
    }

    public XMLProperties(InputStream in) {
        propertyCache = new HashMap();
        file = new File("file.tmp");
        try {
            SAXBuilder saxbuilder = new SAXBuilder();
            DataUnformatFilter dataunformatfilter = new DataUnformatFilter();
            saxbuilder.setXMLFilter(dataunformatfilter);
            doc = saxbuilder.build(in);
        } catch (Exception exception) {
            System.err.println("Error creating XML parser in PropertyManager.java");
            exception.printStackTrace();
        }
    }

    public void deleteProperty(String s) {
        String as[] = parsePropertyName(s);
        Element element = doc.getRootElement();
        for (int i = 0; i < as.length - 1; i++) {
            element = element.getChild(as[i]);
            if (element == null)
                return;
        }

        element.removeChild(as[as.length - 1]);
        saveProperties();
    }

    public String[] getChildrenProperties(String s) {
        String as[] = parsePropertyName(s);
        Element element = doc.getRootElement();
        for (String a : as) {
            element = element.getChild(a);
            if (element == null)
                return new String[0];
        }

        List list = element.getChildren();
        int j = list.size();
        String as1[] = new String[j];
        for (int k = 0; k < j; k++)
            as1[k] = ((Element) list.get(k)).getName();

        return as1;
    }

    public String getProperty(String s) {
        if (propertyCache.containsKey(s))
            return (String) propertyCache.get(s);
        String as[] = parsePropertyName(s);
        Element element = doc.getRootElement();
        for (String a : as) {
            element = element.getChild(a);
            if (element == null)
                return null;
        }

        String s1 = element.getText();
        if ("".equals(s1)) {
            return null;
        } else {
            s1 = s1.trim();
            propertyCache.put(s, s1);
            return s1;
        }
    }

    private String[] parsePropertyName(String s) {
        int i = 1;
        for (int j = 0; j < s.length(); j++)
            if (s.charAt(j) == '.')
                i++;

        String as[] = new String[i];
        StringTokenizer stringtokenizer = new StringTokenizer(s, ".");
        for (int k = 0; stringtokenizer.hasMoreTokens(); k++)
            as[k] = stringtokenizer.nextToken();

        return as;
    }

    private synchronized void saveProperties() {
        BufferedOutputStream bufferedoutputstream = null;
        boolean flag = false;
        File file1 = null;
        try {
            file1 = new File(file.getParentFile(), file.getName() + ".tmp");
            XMLOutputter xmloutputter = new XMLOutputter();
//            XMLOutputter xmloutputter = new XMLOutputter("    ", true);
            bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(file1));
            xmloutputter.output(doc, bufferedoutputstream);
        } catch (Exception exception1) {
            exception1.printStackTrace();
            flag = true;
        } finally {
            try {
                bufferedoutputstream.close();
            } catch (Exception exception2) {
                exception2.printStackTrace();
                flag = true;
            }
        }
        if (!flag) {
            file.delete();
            file1.renameTo(file);
        }
    }

    public void setProperty(String s, String s1) {
        propertyCache.put(s, s1);
        String as[] = parsePropertyName(s);
        Element element = doc.getRootElement();
        for (int i = 0; i < as.length; i++) {
            if (element.getChild(as[i]) == null)
                element.addContent(new Element(as[i]));
            element = element.getChild(as[i]);
        }

        element.setText(s1);
        saveProperties();
    }

    public String[] getPropertyNames() {
        List propNames = new LinkedList();
        List elements = doc.getRootElement().getChildren();
        if (elements.size() == 0)
            return new String[0];
        for (Object element1 : elements) {
            Element element = (Element) element1;
            getElementNames(propNames, element, element.getName());
        }

        return (String[]) propNames.toArray(new String[0]);
    }

    private void getElementNames(List list, Element e, String name) {
        if (e.getContent().size() < 1) {
            list.add(name);
        } else {
            List children = e.getChildren();
            for (Object aChildren : children) {
                Element child = (Element) aChildren;
                getElementNames(list, child, name + '.' + child.getName());
            }

        }
    }

    public Document getDocument() {
        return doc;
    }

}
