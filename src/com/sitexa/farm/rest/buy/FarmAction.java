package com.sitexa.farm.rest.buy;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberPicture;
import com.sitexa.user.data.MemberProperty;
import com.sitexa.user.data.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-14
 * Time: 22:08:40
 */
public class FarmAction extends FarmlanderAction {
    protected String id; //farmId
    protected Site site;
    protected User user;
    protected Member member;
    protected MemberPicture picture;
    protected MemberProperty property;
    protected List<MemberPicture> pictures = new ArrayList<MemberPicture>();
    protected List<MemberProperty> properties = new ArrayList<MemberProperty>();
    //Begin	leim	2009.12.8----------------------
    protected Farm farm;
    protected List<Farm> hotFarms = new ArrayList<Farm>();
    protected List<Farm> farms = new ArrayList<Farm>();
    protected Land land;
    protected String password;

    protected File upload;
    protected String uploadFileName;
    protected String uploadContentType;

    protected Page page = new Page();

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
    //End------------------------------------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public MemberPicture getPicture() {
        return picture;
    }

    public void setPicture(MemberPicture picture) {
        this.picture = picture;
    }

    public MemberProperty getProperty() {
        return property;
    }

    public void setProperty(MemberProperty property) {
        this.property = property;
    }

    public List<MemberPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<MemberPicture> pictures) {
        this.pictures = pictures;
    }

    public List<MemberProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<MemberProperty> properties) {
        this.properties = properties;
    }

    public List<Farm> getHotFarms() {
        return hotFarms;
    }

    public void setHotFarms(List<Farm> hotFarms) {
        this.hotFarms = hotFarms;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }
}
