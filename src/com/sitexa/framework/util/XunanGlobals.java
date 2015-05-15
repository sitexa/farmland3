package com.sitexa.framework.util;

import javax.naming.InitialContext;
import java.io.File;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Apr 17, 2004
 * Time: 12:09:58 AM
 * To change this template use Options | File Templates.
 */
public class XunanGlobals {
    private static final String XUNAN_CONFIG_FILENAME = "config.xml";
    private static final String DEFAULT_CHAR_ENCODING = "UTF-8";
    public static String xunanHome = null;
    public static boolean failedLoading = false;
    private static XMLProperties properties = null;
    private static Locale locale = null;
    private static TimeZone timeZone = null;
    private static String characterEncoding = null;
    private static DateFormat dateFormat = null;
    private static DateFormat dateTimeFormat = null;

    public static void main(String[] args) {
        String s = getXunanProperty("constant");
        System.out.println("s:"+s);
    }

    public XunanGlobals() {
    }

    public static Locale getLocale() {
        if (locale == null)
            loadProperties();
        if (locale != null)
            return locale;
        else
            return Locale.getDefault();
    }

    public static void setLocale(Locale newLocale) {
        locale = newLocale;
        setXunanProperty("locale.country", locale.getCountry());
        setXunanProperty("locale.language", locale.getLanguage());
        dateFormat = DateFormat.getDateInstance(2, locale);
        dateTimeFormat = DateFormat.getDateTimeInstance(2, 2, locale);
        dateFormat.setTimeZone(timeZone);
        dateTimeFormat.setTimeZone(timeZone);
    }

    public static String getCharacterEncoding() {
        if (locale == null)
            loadProperties();
        if (characterEncoding != null)
            return characterEncoding;
        else
            return DEFAULT_CHAR_ENCODING;
    }

    public static void setCharacterEncoding(String characterEncoding) {
        setXunanProperty("locale.characterEncoding", characterEncoding);
    }

    public static TimeZone getTimeZone() {
        if (locale == null)
            loadProperties();
        return timeZone;
    }

    public static void setTimeZone(TimeZone newTimeZone) {
        timeZone = newTimeZone;
        dateFormat.setTimeZone(timeZone);
        dateTimeFormat.setTimeZone(timeZone);
        setXunanProperty("locale.timeZone", timeZone.getID());
    }

    public static String formatDate(Date date) {
        if (locale == null)
            loadProperties();
        return dateFormat.format(date);
    }

    public static String formatDateTime(Date date) {
        if (locale == null)
            loadProperties();
        return dateTimeFormat.format(date);
    }

    public static String getXunanHome() {
        if (xunanHome == null)
            loadProperties();
        return xunanHome;
    }

    public static String getXunanProperty(String name) {
        if (properties == null)
            loadProperties();
        if (properties == null)
            return null;
        else
            return properties.getProperty(name);
    }

    public static List getXunanProperties(String parent) {
        if (properties == null)
            loadProperties();
        if (properties == null)
            return Collections.EMPTY_LIST;
        String propNames[] = properties.getChildrenProperties(parent);
        List<String> values = new ArrayList<String>();
        for (String propName : propNames) {
            String value = getXunanProperty(parent + "." + propName);
            if (value != null)
                values.add(value);
        }
        return values;
    }

    public static List getXunanPropertyNames() {
        if (properties == null)
            loadProperties();
        if (properties == null)
            return Collections.EMPTY_LIST;
        String propNames[] = properties.getPropertyNames();
        return Arrays.asList(propNames);
    }

    public static List getXunanPropertyNames(String parent) {
        if (properties == null)
            loadProperties();
        if (properties == null)
            return Collections.EMPTY_LIST;
        String propNames[] = properties.getChildrenProperties(parent);
        return Arrays.asList(propNames);
    }

    public static void setXunanProperty(String name, String value) {
        loadProperties();
        properties.setProperty(name, value);
    }

    public static void deleteXunanProperty(String name) {
        loadProperties();
        properties.deleteProperty(name);
    }

    private static synchronized void loadProperties() {
        if (failedLoading)
            return;
        if (properties == null) {
            if (xunanHome == null)
                xunanHome = (new InitPropLoader()).getXunanHome();
            if (xunanHome == null)
                try {
                    InitialContext context = new InitialContext();
                    xunanHome = (String) context.lookup("java:comp/env/config");
                } catch (Exception ignored) {
                }
            if (xunanHome == null)
                xunanHome = System.getProperty("config");
            if (xunanHome == null) {
                failedLoading = true;
                StringBuffer msg = new StringBuffer();
                msg.append("Critical Error! The config directory could not be loaded, \n");
                msg.append("which will prevent the application from working correctly.\n\n");
                msg.append("You must set config in one of four ways:\n");
                msg.append("    1) Set a servlet init parameter named config.\n");
                msg.append("    2) Add a init.xml file to your classpath, which points \n ");
                msg.append("       to config. Normally, this file will be in WEB-INF/classes.\n");
                msg.append("    3) Set the JNDI value \"java:comp/env/config\" with a String \n");
                msg.append("       that points to your config directory. \n");
                msg.append("    4) Set the Java system property \"config\".\n\n");
                msg.append("Further instructions for setting config can be found in the \n");
                msg.append("installation documentation.");
                System.err.println(msg.toString());
                return;
            }
            try {
                System.out.println("InitPropLoader::xunanHome:" + xunanHome);
                properties = new XMLProperties(xunanHome + File.separator + XUNAN_CONFIG_FILENAME);
            } catch (Exception ioe) {
                ioe.printStackTrace();
                failedLoading = true;
                return;
            }
        }
        if (locale == null) {
            String language = properties.getProperty("locale.language");
            if (language == null)
                language = "";
            String country = properties.getProperty("locale.country");
            if (country == null)
                country = "";
            if (language.equals("") && country.equals(""))
                locale = Locale.getDefault();
            else
                locale = new Locale(language, country);
            String charEncoding = properties.getProperty("locale.characterEncoding");
            if (charEncoding != null)
                characterEncoding = charEncoding;
            else
                characterEncoding = DEFAULT_CHAR_ENCODING;
            String timeZoneID = properties.getProperty("locale.timeZone");
            if (timeZoneID == null)
                timeZone = TimeZone.getDefault();
            else
                timeZone = TimeZone.getTimeZone(timeZoneID);
            dateFormat = DateFormat.getDateInstance(2, locale);
            dateTimeFormat = DateFormat.getDateTimeInstance(2, 2, locale);
            dateFormat.setTimeZone(timeZone);
            dateTimeFormat.setTimeZone(timeZone);
        }
    }
}
