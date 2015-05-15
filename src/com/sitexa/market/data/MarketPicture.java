package com.sitexa.market.data;

/**
 * MarketPicture entity. @author MyEclipse Persistence Tools
 */

public class MarketPicture implements java.io.Serializable {

	// Fields

	private String picId;
	private Market market;
	private byte[] abbrev;
	private String picUrl;
	private String title;
	private String description;

	// Constructors

	/** default constructor */
	public MarketPicture() {
	}


	// Property accessors

	public String getPicId() {
		return this.picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public Market getMarket() {
		return this.market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public byte[] getAbbrev() {
		return this.abbrev;
	}

	public void setAbbrev(byte[] abbrev) {
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