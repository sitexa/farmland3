/**
 * User: xnpeng
 * Date: 2009-4-16
 * Time: 21:24:58
 */
package com.sitexa.framework;

public class AppContext {

    private String userId;
    private String siteId;

    public AppContext() {
    }

    public AppContext(String userId, String siteId) {
        this.userId = userId;
        this.siteId = siteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        return "AppContext{" +
                "userId='" + userId + '\'' +
                ", siteId='" + siteId + '\'' +
                '}';
    }
}
