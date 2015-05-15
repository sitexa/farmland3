package com.sitexa.farm.service;

/**
 * User: xnpeng
 * Date: 2010-1-22
 * Time: 21:58:47
 */
public enum ServiceEnum {
    PLOUGH {public String value() {
        return "1001555";
    }}, PLOUGH_MACHINE {public String value() {
        return "1001540";
    }}, PLANTING {public String value() {
        return "1001572";
    }}, WATERING {public String value() {
        return "1001550";
    }}, WEED {public String value() {
        return "1001556";
    }}, KILL_INSECT {public String value() {
        return "1001557";
    }}, FERTILIZE {public String value() {
        return "1001551";
    }}, PICK {public String value() {
        return "1001554";
    }}, HARVEST {public String value() {
        return "1001566";
    }}, MANAGE {public String value() {
        return "manage";
    }}, PLANT {public String value() {
        return "plant";
    }}, DELETE {public String value() {
        return "delete";
    }};

    public abstract String value();
}
