package com.sitexa.post;

/**
 * User: xnpeng
 * Date: 2010-1-9
 * Time: 16:11:26
 */

public enum CsaType {
    NEWS {public String value() {
        return "农场新闻";
    }},
    STRATEGY {public String value() {
        return "农场攻略";
    }},
    ACTIVITY {public String value() {
        return "农场活动";
    }},
    NOTICE {public String value() {
        return "农场通知";
    }};

    public abstract String value();
}