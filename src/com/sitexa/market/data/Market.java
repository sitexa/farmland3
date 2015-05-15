package com.sitexa.market.data;

import com.sitexa.farm.data.Farm;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Market entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Market implements java.io.Serializable {

    public static final String SELL = "1";
    public static final String BUY = "0";
    // Fields

    private String itemId;
    private Farm farm;
    private String itemTitle;
    private Site site;
    private String contents;
    private String contact;
    private Integer vwCnt;
    private Integer status;
    private String itemType;
    private Date createDate;
    private MarketCategory category;
    private Member member;
    private Market parent;
    private Set marketPictures = new HashSet(0);
    private Set children = new HashSet(0);
    
    // Constructors

    /**
     * default constructor
     */
    public Market() {
    }

    /**
     * minimal constructor
     */
    public Market(String itemId, String itemTitle) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
    }
    // Property accessors

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Farm getFarm() {
        return this.farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public String getItemTitle() {
        return this.itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getVwCnt() {
        return this.vwCnt;
    }

    public void setVwCnt(Integer vwCnt) {
        this.vwCnt = vwCnt;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public MarketCategory getCategory() {
        return category;
    }

    public void setCategory(MarketCategory category) {
        this.category = category;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set getMarketPictures() {
        return marketPictures;
    }

    public void setMarketPictures(Set marketPictures) {
        this.marketPictures = marketPictures;
    }
    
    public Set getChildren() {
		return children;
	}

	public void setChildren(Set children) {
		this.children = children;
	}

	public Market getParent() {
		return parent;
	}

	public void setParent(Market parent) {
		this.parent = parent;
	}
}