package com.sitexa.framework.hibernate;

import com.sitexa.framework.exception.BaseRuntime;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public class HQL {

    private static final String PROPERTY_FILE_NAME_LOCAL = "/hql.properties";
    private static Properties properties = null;

    static {
        load();
    }

    public static String getString(String hqlName, Object... arguments) {
        String hql = getString(hqlName);
        return MessageFormat.format(hql, arguments);
    }

    public static String getString(String hqlName) {
        return properties.getProperty(hqlName);
    }

    private static void load() {
        File file = new File(PROPERTY_FILE_NAME_LOCAL);
        properties = new Properties();
        FileInputStream fis = null;
        try {
            if (file.exists()) {
                fis = new FileInputStream(file);
                System.out.println(file.getAbsolutePath());
                System.out.println(file.getCanonicalPath());
                properties.load(fis);
            } else
                throw new IOException("hql.properites does'nt existed!");
        } catch (IOException e) {
            BaseRuntime.threw(HQL.class, e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
