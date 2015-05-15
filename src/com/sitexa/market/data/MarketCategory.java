package com.sitexa.market.data;

import java.util.HashSet;
import java.util.Set;

/**
 * MarketCategory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketCategory implements java.io.Serializable {

	// Fields

	private String cateId;
	private String cateName;
	private Set markets = new HashSet(0);

	// Constructors

	/** default constructor */
	public MarketCategory() {
	}

	/** minimal constructor */
	public MarketCategory(String cateId, String cateName) {
		this.cateId = cateId;
		this.cateName = cateName;
	}

	/** full constructor */
	public MarketCategory(String cateId, String cateName, Set markets) {
		this.cateId = cateId;
		this.cateName = cateName;
		this.markets = markets;
	}

	// Property accessors

	public String getCateId() {
		return this.cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return this.cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Set getMarkets() {
		return this.markets;
	}

	public void setMarkets(Set markets) {
		this.markets = markets;
	}

}