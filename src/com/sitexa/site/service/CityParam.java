package com.sitexa.site.service;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-2
 * Time: 12:48:34
 */
public class CityParam {
    public String code;
    public String name;

    public CityParam(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String id) {
        this.code = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
