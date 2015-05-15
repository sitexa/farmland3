package com.sitexa.site.data;

public class SitePicture implements java.io.Serializable {

    private String picId;
    private Site site;
    private String picTitle;
    private byte[] abbrev;
    private String picUrl;
    private String description;


    public SitePicture() {
    }

    public SitePicture(String picId, Site site, String picTitle,String description) {
        this.picId = picId;
        this.site = site;
        this.picTitle = picTitle;
        this.description = description;
    }

    // Property accessors

    public String getPicId() {
        return this.picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public Site getSite() {
        return this.site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getPicTitle() {
        return this.picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SitePicture that = (SitePicture) o;

        if (!picId.equals(that.picId)) return false;
        if (!site.equals(that.site)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = picId.hashCode();
        result = 31 * result + site.hashCode();
        return result;
    }
}