package com.sitexa.post;

/**
 * User: xnpeng
 * Date: 2010-1-9
 * Time: 16:10:40
 */
public enum PostCategory {
    ACTIVITY {public String value() {
        return "ACTIVITY";
    }}, AFFAIR {public String value() {
        return "AFFAIR";
    }}, ANNOUNCE {public String value() {
        return "ANNOUNCE";
    }}, BUSINESS {public String value() {
        return "BUSINESS";
    }}, CASE {public String value() {
        return "CASE";
    }}, COMPANY {public String value() {
        return "COMPANY";
    }}, CSA {public String value() {
        return "CSA";
    }}, EMPLOY {public String value() {
        return "EMPLOY";
    }}, EXCHANGE {public String value() {
        return "EXCHANGE";
    }}, FARMPOST {public String value() {
        return "FARMPOST";
    }}, FRIEND {public String value() {
        return "FRIEND";
    }}, HOUSE {public String value() {
        return "HOUSE";
    }}, LANDPOST {public String value() {
        return "LANDPOST";
    }}, MESSAGE {public String value() {
        return "MESSAGE";
    }}, POLL {public String value() {
        return "POLL";
    }}, SCENERY {public String value() {
        return "SCENERY";
    }}, WELFARE {public String value() {
        return "WELFARE";
    }};

    public abstract String value();
}
