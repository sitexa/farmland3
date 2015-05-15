package com.sitexa.farm.data;

import com.sitexa.user.data.Member;

import java.util.Date;

/**
 * Trusteeship entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Trusteeship implements java.io.Serializable {

    // Fields

    private String id;
    private Farm farm;
    private Member member;
    private Member trustee;
    private Date startTime;
    private Date endTime;
    private String contents;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public Trusteeship() {
    }

    /**
     * minimal constructor
     */
    public Trusteeship(String id, Farm farm) {
        this.id = id;
        this.farm = farm;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Member getTrustee() {
        return trustee;
    }

    public void setTrustee(Member trustee) {
        this.trustee = trustee;
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

}