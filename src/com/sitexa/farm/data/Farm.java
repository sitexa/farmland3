package com.sitexa.farm.data;

import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Farm entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Farm implements java.io.Serializable {

    public static final String PLOUGH_STATUS = "1";

    // Fields

    private String farmId;
    private String farmName;
    private Land land;
    private Site site;
    private Member member;
    private Long acreage;
    private String coordination;
    private Date rentDate;
    private Date endDate;
    private String rentMode;
    private String rentPrice;
    private String remark;
    private int score;
    private String farmNo;
    private String slogan;
    private String ploughStatus;
    private Set owners = new HashSet(0);
    private Set cropses = new HashSet(0);
    private Set trusteeships = new HashSet(0);
    private Set farmProducts = new HashSet(0);
    private Set farmPictures = new HashSet(0);
    private Set farmings = new HashSet(0);
    private Set farmVisits = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Farm() {
    }

    /**
     * minimal constructor
     */
    public Farm(String farmId, Land land) {
        this.farmId = farmId;
        this.land = land;
    }

    public Farm(String farmId, Land land, String farmNo, Long acreage) {
        this.farmId = farmId;
        this.land = land;
        this.acreage = acreage;
        this.farmNo = farmNo;
    }

    // Property accessors

    public String getFarmId() {
        return this.farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Long getAcreage() {
        return this.acreage;
    }

    public void setAcreage(Long acreage) {
        this.acreage = acreage;
    }

    public Date getRentDate() {
        return this.rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRentMode() {
        return rentMode;
    }

    public void setRentMode(String rentMode) {
        this.rentMode = rentMode;
    }

    public String getRentPrice() {
        return this.rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Set getTrusteeships() {
        return trusteeships;
    }

    public void setTrusteeships(Set trusteeships) {
        this.trusteeships = trusteeships;
    }

    public Set getFarmProducts() {
        return farmProducts;
    }

    public void setFarmProducts(Set farmProducts) {
        this.farmProducts = farmProducts;
    }

    public Set getFarmPictures() {
        return farmPictures;
    }

    public void setFarmPictures(Set farmPictures) {
        this.farmPictures = farmPictures;
    }

    public Set getFarmings() {
        return farmings;
    }

    public void setFarmings(Set farmings) {
        this.farmings = farmings;
    }

    public Set getFarmVisits() {
        return farmVisits;
    }

    public void setFarmVisits(Set farmVisits) {
        this.farmVisits = farmVisits;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCoordination() {
        return coordination;
    }

    public void setCoordination(String coordination) {
        this.coordination = coordination;
    }

    public Set getCropses() {
        return cropses;
    }

    public void setCropses(Set cropses) {
        this.cropses = cropses;
    }

    public String getFarmNo() {
        return farmNo;
    }

    public void setFarmNo(String farmNo) {
        this.farmNo = farmNo;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getPloughStatus() {
        return ploughStatus;
    }

    public void setPloughStatus(String ploughStatus) {
        this.ploughStatus = ploughStatus;
    }

    public Set getOwners() {
        return owners;
    }

    public void setOwners(Set owners) {
        this.owners = owners;
    }
}