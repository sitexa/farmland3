package com.sitexa.sys.data;

import java.util.Date;

/**
 * ViewStats entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class ViewStats implements java.io.Serializable {

    // Fields

    private Long id;
    private String remoteAddr;
    private String requestUri;
    private Date visitTime;

    // Constructors

    /**
     * default constructor
     */
    public ViewStats() {
    }

    /**
     * full constructor
     */
    public ViewStats(String remoteAddr, String requestUri, Date visitTime) {
        this.remoteAddr = remoteAddr;
        this.requestUri = requestUri;
        this.visitTime = visitTime;
    }

    // Property accessors

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemoteAddr() {
        return this.remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRequestUri() {
        return this.requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public Date getVisitTime() {
        return this.visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
	}

}