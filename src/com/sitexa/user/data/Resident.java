package com.sitexa.user.data;

import com.sitexa.site.data.Site;
import com.sitexa.post.data.House;

import java.util.Date;

/**
 * Resident entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Resident implements java.io.Serializable {

    // Fields

    private String id;
    private House house;
    private Member member;
    private Site site;
    private String name;
    private String gender;
    private Date birthDate;
    private String ccid;
    private String career;
    private String homephone;
    private Date startDate;
    private Date endDate;

    // Constructors

    /**
     * default constructor
     */
    public Resident() {
    }

    /**
     * minimal constructor
     */
    public Resident(String id, House house, String name, String gender) {
        this.id = id;
        this.house = house;
        this.name = name;
        this.gender = gender;
    }

    /**
     * full constructor
     */
    public Resident(String id, House house, Member member, Site site,
                    String name, String gender, Date birthDate, String ccid,
                    String career, String homephone, Date startDate, Date endDate) {
        this.id = id;
        this.house = house;
        this.member = member;
        this.site = site;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.ccid = ccid;
        this.career = career;
        this.homephone = homephone;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public House getHouse() {
        return this.house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Site getSite() {
        return this.site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCcid() {
        return this.ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public String getCareer() {
        return this.career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getHomephone() {
        return this.homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}