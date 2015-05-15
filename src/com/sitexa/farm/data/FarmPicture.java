package com.sitexa.farm.data;

/**
 * FarmPicture entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FarmPicture implements java.io.Serializable {

    // Fields

    private String picId;
    private Farm farm;
    private byte[] abbrev;
    private String picUrl;
    private String title;
    private String description;

    // Constructors

    /**
     * default constructor
     */
    public FarmPicture() {
    }

    /**
     * minimal constructor
     */
    public FarmPicture(String picId, Farm farm, String title, String desc) {
        this.picId = picId;
        this.farm = farm;
        this.title = title;
        this.description = desc;
    }

    // Property accessors

    public String getPicId() {
        return this.picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public Farm getFarm() {
        return this.farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public byte[] getAbbrev() {
        return abbrev;
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