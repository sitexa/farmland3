package com.sitexa.farm.data;

import com.sitexa.user.data.Member;

import java.util.Date;

/**
 * FarmOwner entity. @author MyEclipse Persistence Tools
 */

public class FarmOwner implements java.io.Serializable {

    // Fields

    private String ownerId;
    private Farm farm;
    private Member member;
    private Date joinDate;
    private Date quitDate;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public FarmOwner() {
    }

    // Property accessors

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Farm getFarm() {
        return this.farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getJoinDate() {
        return this.joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getQuitDate() {
        return this.quitDate;
    }

    public void setQuitDate(Date quitDate) {
        this.quitDate = quitDate;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
	}

}