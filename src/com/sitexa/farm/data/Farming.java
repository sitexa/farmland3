package com.sitexa.farm.data;

import com.sitexa.user.data.Member;

import java.util.Date;

/**
 * Farming entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Farming implements java.io.Serializable {

    // Fields

    private String farmingId;
    private Farm farm;
    private Member farmer;
    private Faction faction;
    private FarmPlan farmPlan;
    private Seed seed;
    private Date startTime;
    private Date endTime;
    private Integer quantity;
    private String amount;
    private String contents;
    private String remark;
    private String state;
    private String paymode;

    // Constructors

    /**
     * default constructor
     */
    public Farming() {
    }

    /**
     * minimal constructor
     */
    public Farming(String farmingId) {
        this.farmingId = farmingId;
    }
    // Property accessors

    public String getFarmingId() {
        return this.farmingId;
    }

    public void setFarmingId(String farmingId) {
        this.farmingId = farmingId;
    }

    public Farm getFarm() {
        return this.farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Member getFarmer() {
        return farmer;
    }

    public void setFarmer(Member farmer) {
        this.farmer = farmer;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public FarmPlan getFarmPlan() {
        return farmPlan;
    }

    public void setFarmPlan(FarmPlan farmPlan) {
        this.farmPlan = farmPlan;
    }

    @Override
    public String toString() {
        return "Farming{" +
                "farmingId='" + farmingId + '\'' +
                ", farm=" + farm == null ? "" : farm.getFarmName() +
                ", farmer=" + farmer == null ? "" : farmer.getRealname() +
                ", faction=" + faction == null ? "" : faction.getActionName() +
                ", seed=" + seed == null ? "" : seed.getSeedName() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", quantity=" + quantity +
                ", amount='" + amount + '\'' +
                ", contents='" + contents + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}