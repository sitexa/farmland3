package com.sitexa.farm.data;

/**
 * VisitPicture entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VisitPicture implements java.io.Serializable {

	// Fields

	private String picId;
	private FarmVisit farmVisit;
	private String abbrev;
	private String picUrl;
	private String title;
	private String description;

	// Constructors

	/** default constructor */
	public VisitPicture() {
	}

	/** minimal constructor */
	public VisitPicture(String picId) {
		this.picId = picId;
	}

	/** full constructor */
	public VisitPicture(String picId, FarmVisit farmVisit, String abbrev,
			String picUrl, String title, String description) {
		this.picId = picId;
		this.farmVisit = farmVisit;
		this.abbrev = abbrev;
		this.picUrl = picUrl;
		this.title = title;
		this.description = description;
	}

	// Property accessors

	public String getPicId() {
		return this.picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public FarmVisit getFarmVisit() {
		return this.farmVisit;
	}

	public void setFarmVisit(FarmVisit farmVisit) {
		this.farmVisit = farmVisit;
	}

	public String getAbbrev() {
		return this.abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}