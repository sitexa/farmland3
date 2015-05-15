package com.sitexa.post;

/**
 * User: xnpeng
 * Date: 2010-1-9
 * Time: 16:12:23
 */
public enum FarmPostType {
    POST {public String value() {
        return "农庄灌水";
    }},
    ACTIVITY {public String value() {
        return "农庄活动";
    }},
    NOTICE {public String value() {
        return "农庄通知";
    }},
    SCHEDULE {public String value() {
        return "农事计划";
    }};

    public abstract String value();
}