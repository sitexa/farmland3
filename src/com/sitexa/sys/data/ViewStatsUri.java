package com.sitexa.sys.data;

import java.util.Date;

/**
 * ViewStatsUri entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ViewStatsUri implements java.io.Serializable {

	// Fields

	private Long id;
	private Date visitTime;
	private String requestUri;
	private Integer times;
	private String category;

	// Constructors

	/** default constructor */
	public ViewStatsUri() {
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

	public String getRequestUri() {
		return this.requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public Integer getTimes() {
		return this.times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}