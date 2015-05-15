package com.sitexa.post;

/**
 * User: xnpeng
 * Date: 2010-1-9
 * Time: 16:11:49
 */


public enum LandPostType {
    ANNOUNCE {public String value() {
        return "场主告示";
    }},
    COMMENT {public String value() {
        return "用户评论";
    }};

    public abstract String value();
}
