package com.sitexa.farm.data;

import com.sitexa.user.data.Member;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FarmVisit entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FarmVisit implements java.io.Serializable {

    // Fields

    private String visitId;
    private Farm farm;
    private Member visitor;
    private String name;
    private String contents;
    private Date createTime;
    private Integer vwCnt;
    private String parentId;
    private Set visitPictures = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public FarmVisit() {
    }

    /**
     * minimal constructor
     */
    public FarmVisit(String visitId) {
        this.visitId = visitId;
    }
    // Property accessors

    public String getVisitId() {
        return this.visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public Farm getFarm() {
        return this.farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Member getVisitor() {
        return visitor;
    }

    public void setVisitor(Member visitor) {
        this.visitor = visitor;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVwCnt() {
        return this.vwCnt;
    }

    public void setVwCnt(Integer vwCnt) {
        this.vwCnt = vwCnt;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Set getVisitPictures() {
        return this.visitPictures;
    }

    public void setVisitPictures(Set visitPictures) {
        this.visitPictures = visitPictures;
	}

}