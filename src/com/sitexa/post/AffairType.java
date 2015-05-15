package com.sitexa.post;

/**
 * User: xnpeng
 * Date: 2010-1-9
 * Time: 16:14:25
 */
public enum AffairType {
    TYPE1 {public String value() {
        return "TYPE1";
    }},
    TYPE2 {public String value() {
        return "TYPE2";
    }};

    public abstract String value();
}