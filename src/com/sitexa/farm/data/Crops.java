package com.sitexa.farm.data;

import java.util.Date;

/**
 * Crops entity. @author MyEclipse Persistence Tools
 */

public class Crops implements java.io.Serializable {

    // Fields

    private String cropsId;
    private Farm farm;
    private Seed seed;
    private Integer seedNumber;
    private Integer vitalPower;
    private Integer maturity;
    private Date startDate;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public Crops() {
    }

    /**
     * minimal constructor
     */
    public Crops(String cropsId, Farm farm, Seed seed) {
        this.cropsId = cropsId;
        this.farm = farm;
        this.seed = seed;
    }
    // Property accessors

    public String getCropsId() {
        return this.cropsId;
    }

    public void setCropsId(String cropsId) {
        this.cropsId = cropsId;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public Integer getSeedNumber() {
        return seedNumber;
    }

    public void setSeedNumber(Integer seedNumber) {
        this.seedNumber = seedNumber;
    }

    public Integer getVitalPower() {
        return vitalPower;
    }

    public void setVitalPower(Integer vitalPower) {
        this.vitalPower = vitalPower;
    }

    public Integer getMaturity() {
        return maturity;
    }

    public void setMaturity(Integer maturity) {
        this.maturity = maturity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}