package com.sitexa.farm.data;

/**
 * FarmPlan entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FarmPlan implements java.io.Serializable {

    // Fields

    private String planId;
    private Land land;
    private String planName;
    private String description;
    private Integer price;
    private String remark;
    private String status;

    // Constructors

    /**
     * default constructor
     */
    public FarmPlan() {
    }


    // Property accessors

    public String getPlanId() {
        return this.planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public String getPlanName() {
        return this.planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
	}

}