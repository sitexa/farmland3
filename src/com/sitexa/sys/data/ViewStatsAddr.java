package com.sitexa.sys.data;

import java.util.Date;

/**
 * ViewStatsAddr entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ViewStatsAddr implements java.io.Serializable {

	// Fields

	private Long id;
	private Date visitTime;
	private String remoteAddr;
	private Integer times;
	private String region;

	// Constructors

	/** default constructor */
	public ViewStatsAddr() {
	}
	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getVisitTime() {
		return this.visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public String getRemoteAddr() {
		return this.remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public Integer getTimes() {
		return this.times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}