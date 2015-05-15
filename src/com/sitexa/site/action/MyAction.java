package com.sitexa.site.action;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-30
 * Time: 8:44:30
 */
public class MyAction {


    public String execute() {
        return "success";
    }

    public String sleep() {
        System.out.println("sleep");
        String s = "sleep";
        for (int i = 0; i < 10000; i++) {
            s += i;
        }
        System.out.println("s:" + s);
        return null;
    }

    public String script() {
        return "script";
    }

    public Map<String, String> getOptions() {
        Map<String, String> options = new HashMap<String, String>();
        options.put("Florida", "FL");
        options.put("Alabama", "AL");
        options.put("New York", "NY");
        options.put("New Jersey", "NJ");
        options.put("California", "CA");
        return options;
    }

    public Map<String,String> getSites(){
        Map<String, String> sites = new HashMap<String, String>();
        sites.put("110000","北京市");
        sites.put("120000","天津市");
        sites.put("130000","河北省");
        sites.put("140000","山西省");
        sites.put("150000","内蒙古自治区");
        return sites;
    }

    public String index() {
        return "success";
    }

}
