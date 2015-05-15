package com.sitexa.post;

/**
 * User: xnpeng
 * Date: 2010-1-9
 * Time: 16:12:46
 */

public enum MsgType {
    TYPE1 {public String value() {
        return "type1";
    }},
    TYPE2 {public String value() {
        return "type2";
    }};

    public abstract String value();
}