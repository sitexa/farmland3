package com.sitexa.site.data;

import com.sitexa.farm.data.Land;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Site entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Site implements java.io.Serializable {

    private static Log log = LogFactory.getLog(Site.class);
    // Fields

    private String siteId;
    private SiteType type;
    private Site parent;
    private String name;
    private Date createDate;
    private String address;
    private boolean status;
    private Member governor;
    private String introduction;
    private Float longitude;
    private Float latitude;
    private Integer zoom;
    private Integer vwCnt;
    private String fullname;
    private Set<SiteProperty> properties = new HashSet<SiteProperty>();
    private Set<SitePicture> pictures = new HashSet<SitePicture>();
    private Set<Site> children = new HashSet<Site>();
    private Set<SiteMember> members = new HashSet<SiteMember>();
    private Set<Land> lands = new HashSet<Land>();
    private Site country;
    private Site state;
    private Site city;
    private Site county;
    private Site town;
    private Site village;

    // Constructors

    public Site() {
    }

    public Site(String siteId) {
        this.siteId = siteId;
    }

    public Site(String siteId, SiteType type, boolean status) {
        this.siteId = siteId;
        this.type = type;
        this.status = status;
    }

    public Site(String siteId, SiteType type, Site parent, String name, Date createDate, String address, boolean status) {
        this.siteId = siteId;
        this.type = type;
        this.parent = parent;
        this.name = name;
        this.createDate = createDate;
        this.address = address;
        this.status = status;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public SiteType getType() {
        return type;
    }

    public void setType(SiteType type) {
        this.type = type;
    }

    public Site getParent() {
//        if (parent.getSiteId() != null) parent = (new SiteDAO()).findById(parent.getSiteId());
        return parent;
    }

    public void setParent(Site parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Member getGovernor() {
        return governor;
    }

    public void setGovernor(Member governor) {
        this.governor = governor;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public Integer getVwCnt() {
        return vwCnt;
    }

    public void setVwCnt(Integer vwCnt) {
        this.vwCnt = vwCnt;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Set<SiteProperty> getProperties() {
        return properties;
    }

    public void setProperties(Set<SiteProperty> properties) {
        this.properties = properties;
    }

    public Set<SitePicture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<SitePicture> pictures) {
        this.pictures = pictures;
    }

    public Set<Site> getChildren() {
        return children;
    }

    public void setChildren(Set<Site> children) {
        this.children = children;
    }

    public Set<SiteMember> getMembers() {
        return members;
    }

    public void setMembers(Set<SiteMember> members) {
        this.members = members;
    }

    public Set<Land> getLands() {
        return lands;
    }

    public void setLands(Set<Land> lands) {
        this.lands = lands;
    }

    public Site getCountry() {
        return country;
    }

    public void setCountry(Site country) {
        this.country = country;
    }

    public Site getState() {
        return state;
    }

    public void setState(Site state) {
        this.state = state;
    }

    public Site getCity() {
        return city;
    }

    public void setCity(Site city) {
        this.city = city;
    }

    public Site getCounty() {
        return county;
    }

    public void setCounty(Site county) {
        this.county = county;
    }

    public Site getTown() {
        return town;
    }

    public void setTown(Site town) {
        this.town = town;
    }

    public Site getVillage() {
        return village;
    }

    public void setVillage(Site village) {
        this.village = village;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site site = (Site) o;

        if (!name.equals(site.name)) return false;
        if (!siteId.equals(site.siteId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = siteId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteId='" + siteId + '\'' +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", type=" + type +
                ", createDate=" + createDate +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", governor=" + governor +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", zoom=" + zoom +
                ", vwCnt=" + vwCnt +
                '}';
    }
}