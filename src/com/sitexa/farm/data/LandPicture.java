package com.sitexa.farm.data;

/**
 * LandPicture entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class LandPicture implements java.io.Serializable {

    // Fields

    private String picId;
    private Land land;
    private byte[] abbrev;
    private String picUrl;
    private String title;
    private String description;
    private Integer picTag;

    // Constructors

    /**
     * default constructor
     */
    public LandPicture() {
    }

    /**
     * minimal constructor
     */
    public LandPicture(String picId, Land land, String t, String d) {
        this.picId = picId;
        this.land = land;
        this.title = t;
        this.description = d;
    }

    // Property accessors

    public String getPicId() {
        return this.picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public Land getLand() {
        return this.land;
    }

    public void setLand(Land land) {
        this.land = land;
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

    public Integer getPicTag() {
        return picTag;
    }

    public void setPicTag(Integer picTag) {
        this.picTag = picTag;
    }
}