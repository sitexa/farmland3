package com.sitexa.framework.config;

import com.opensymphony.xwork2.inject.Container;
import com.sitexa.framework.exception.BaseException;
import org.apache.struts2.dispatcher.Dispatcher;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-26
 * Time: 22:03:30
 */
public class StrutsConfig {

    static Container con = Dispatcher.getInstance().getConfigurationManager().getConfiguration().getContainer();

    public static String getSaveDir(){
        return con.getInstance(String.class,"struts.multipart.saveDir");
    }

    public static long getMaxSize(){
        return con.getInstance(Long.class,"struts.multipart.maxSize");
    }

    public static String getConfig(String configParameter) {
        return con.getInstance(String.class, configParameter);
    }

    public static String getConstant(String name) {
        return getConfig(name);
    }

    public static String getPackage(String name) throws BaseException {
        BaseException.threw("no implementation");
        return null;
    }

    public static String getAction(String name) throws BaseException {
        BaseException.threw("no implementation");
        return null;
    }

    public static void main(String[] args) {
        StrutsConfig c = new StrutsConfig();
        String s = c.getConstant("struts.multipart.maxSize");
        System.out.println("s:" + s);
        String rootPath = c.getConstant("struts.multipart.saveDir");
        System.out.println("rootPath = " + rootPath);
    }
}
