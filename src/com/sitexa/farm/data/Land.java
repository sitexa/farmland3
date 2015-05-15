package com.sitexa.farm.data;

import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Land entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Land implements java.io.Serializable {

    // Fields

    public static final String LAND_TYPE_MOUNTAIN = "山地";
    public static final String LAND_TYPE_FARMLAND = "农田";
    public static final String LAND_TYPE_POND = "池塘";
    public static final String LAND_TYPE_GRASS = "草地";
    public static final String LAND_TYPE_FOREST = "森林";

    public static final String[] LAND_TYPES = {"农田", "池塘", "山地", "草地", "森林"};

    private String landId;
    private String landName;
    private Member lord;
    private String landLord;
    private String address;
    private Site site;
    private String landType;
    private String rentPrice;
    private Float longitude;
    private Float latitude;
    private String description;
    private String remark;
    private Date startDate;
    private Date endDate;
    private String rentTerm;
    private String status;
    private String tag;
    private Set seeds = new HashSet(0);
    private Set farmingActions = new HashSet(0);
    private Set farms = new HashSet(0);
    private Set landPictures = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Land() {
    }

    /**
     * minimal constructor
     */
    public Land(String landId) {
        this.landId = landId;
    }

    // Property accessors

    public String getLandId() {
        return this.landId;
    }

    public void setLandId(String landId) {
        this.landId = landId;
    }

    public String getLandName() {
        return this.landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public Member getLord() {
        return lord;
    }

    public void setLord(Member lord) {
        this.lord = lord;
    }

    public String getLandLord() {
        return this.landLord;
    }

    public void setLandLord(String landLord) {
        this.landLord = landLord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getLandType() {
        return this.landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getRentPrice() {
        return this.rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Float getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRentTerm() {
        return rentTerm;
    }

    public void setRentTerm(String rentTerm) {
        this.rentTerm = rentTerm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set getSeeds() {
        return seeds;
    }

    public void setSeeds(Set seeds) {
        this.seeds = seeds;
    }

    public Set getFarmingActions() {
        return farmingActions;
    }

    public void setFarmingActions(Set farmingActions) {
        this.farmingActions = farmingActions;
    }

    public Set getFarms() {
        return this.farms;
    }

    public void setFarms(Set farms) {
        this.farms = farms;
    }

    public Set getLandPictures() {
        return landPictures;
    }

    public void setLandPictures(Set landPictures) {
        this.landPictures = landPictures;
    }

    @Override
    public String toString() {
        return "Land{" +
                "landId='" + landId + '\'' +
                ", landName='" + landName + '\'' +
                ", landLord='" + landLord + '\'' +
                ", address='" + address + '\'' +
                ", landType='" + landType + '\'' +
                ", rentPrice='" + rentPrice + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", description='" + description + '\'' +
                ", remark='" + remark + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", rentTerm='" + rentTerm + '\'' +
                '}';
    }
}