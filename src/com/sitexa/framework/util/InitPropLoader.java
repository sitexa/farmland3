package com.sitexa.framework.util;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-26
 * Time: 19:04:16
 */
public class InitPropLoader {

    public InitPropLoader() {
    }

    public String getXunanHome() {
        String xunanHome = null;
        InputStream in = null;
        try {
            in = getClass().getResourceAsStream("/init.xml");
            if (in != null) {
                Document doc = (new SAXBuilder()).build(in);
                xunanHome = doc.getRootElement().getText();
            }
        } catch (Exception e) {
            System.out.println("Error loading init.xml to find config.");
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception ignored) {
            }
        }
        if (xunanHome != null)
            for (xunanHome = xunanHome.trim();
                 xunanHome.endsWith("/") || xunanHome.endsWith("\\");
                 xunanHome = xunanHome.substring(0, xunanHome.length() - 1))
                ;
        if ("".equals(xunanHome))
            xunanHome = null;
        return xunanHome;
    }
}