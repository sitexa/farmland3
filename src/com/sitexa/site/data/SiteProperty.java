package com.sitexa.site.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SiteProperty entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class SiteProperty implements java.io.Serializable {
    private Log logger = LogFactory.getLog(SiteProperty.class);
    // Fields
    public static final String[] SITE_PROPERTY_ITEMS = {
            "详细地址",
            "社区类型",
            "简要介绍",
            "社区标语"
    };

    public static final String DETAIL_ADDRESS = "详细地址";
    public static final String SITE_CATEGORY = "社区类型";
    public static final String SITE_PROFILE = "简要介绍";
    public static final String SITE_SLOGAN = "社区标语";

    private String propId;
    private Site site;
    private String propName;
    private String propValue;

    // Constructors

    /**
     * default constructor
     */
    public SiteProperty() {
    }

    /**
     * full constructor
     */
    public SiteProperty(String propId, Site site, String propName,
                        String propValue) {
        this.propId = propId;
        this.site = site;
        this.propName = propName;
        this.propValue = propValue;
    }

    // Property accessors

    public String getPropId() {
        return this.propId;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }

    public Site getSite() {
        return this.site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getPropName() {
        return this.propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropValue() {
        return this.propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteProperty that = (SiteProperty) o;

        if (!propId.equals(that.propId)) return false;
        if (!propName.equals(that.propName)) return false;
        if (propValue != null ? !propValue.equals(that.propValue) : that.propValue != null) return false;
        if (!site.equals(that.site)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = propId.hashCode();
        result = 31 * result + site.hashCode();
        result = 31 * result + propName.hashCode();
        result = 31 * result + (propValue != null ? propValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SiteProperty{" +
                "propId='" + propId + '\'' +
                ", siteId='" + ((site != null) ? site.getSiteId() : "") + '\'' +
                ", propName='" + propName + '\'' +
                ", propValue='" + propValue + '\'' +
                "}\n";
    }
}