package com.sitexa.framework.config;

import com.sitexa.framework.util.XMLProperties;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-26
 * Time: 20:58:15
 */
public class AppConfig {
    private static boolean failedLoading = false;
    private static XMLProperties properties = null;
    private static String filename = "AppConfig.xml";

    public AppConfig() {
    }

    public static String getProperty(String name) {

        if (properties == null)
            loadProperties();
        if (properties == null)
            return null;
        else
            return properties.getProperty(name);
    }

    public static List getProperties(String parent) {
        if (properties == null)
            loadProperties();
        if (properties == null)
            return Collections.EMPTY_LIST;
        String propNames[] = properties.getChildrenProperties(parent);
        List<String> values = new ArrayList<String>();
        for (String propName : propNames) {
            String value = getProperty(parent + "." + propName);
            if (value != null)
                values.add(value);
        }
        return values;
    }

    public static List getPropertyNames() {
        if (properties == null)
            loadProperties();
        if (properties == null)
            return Collections.EMPTY_LIST;
        String propNames[] = properties.getPropertyNames();
        return Arrays.asList(propNames);
    }

    public static List getPropertyNames(String parent) {
        if (properties == null)
            loadProperties();
        if (properties == null)
            return Collections.EMPTY_LIST;
        String propNames[] = properties.getChildrenProperties(parent);
        return Arrays.asList(propNames);
    }

    protected static void setProperty(String name, String value) {
        loadProperties();
        properties.setProperty(name, value);
    }

    protected static void deleteProperty(String name) {
        loadProperties();
        properties.deleteProperty(name);
    }

    private static synchronized void loadProperties() {
        if (failedLoading)
            return;
        try {
            if (filename.indexOf("/") < 0) filename = "/" + filename;
            InputStream in = AppConfig.class.getResourceAsStream(filename);
            properties = new XMLProperties(in);
        } catch (Exception ioe) {
            ioe.printStackTrace();
            failedLoading = true;
        }
    }

    public static String getConstant(String name) {
        loadProperties();
        Document doc = properties.getDocument();
        List list = doc.getRootElement().getChildren("constant");
        for (Object aList : list) {
            Element e = (Element) aList;
            Attribute a = e.getAttribute("name");
            if (a.getValue().equals(name)) {
                return e.getAttribute("value").getValue();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        AppConfig ac = new AppConfig();
        String db = ac.getProperty("hibernate.configuration.path");
        System.out.println("db = " + db);

        String ss = ac.getProperty("struts.multipart.saveDir");
        System.out.println("ss = " + ss);
    }

}
