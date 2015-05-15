package com.sitexa.user.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberPicture;
import com.sitexa.user.data.User;
import com.sitexa.site.data.Site;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-7
 * Time: 16:35:47
 */
public class MemberPictureAction extends BaseAction {
    protected Site site;
    protected String id;
    protected Member member;
    protected User user;
    protected MemberPicture picture;
    protected List<MemberPicture> pictures = new ArrayList<MemberPicture>();

    protected List<String> picTitles = new ArrayList<String>();
    protected List<String> descriptions = new ArrayList<String>();

    protected List<File> upload = new ArrayList<File>();
    protected List<String> uploadFileName = new ArrayList<String>();
    protected List<String> uploadContentType = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<MemberPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<MemberPicture> pictures) {
        this.pictures = pictures;
    }

    public List<String> getPicTitles() {
        return picTitles;
    }

    public void setPicTitles(List<String> picTitles) {
        this.picTitles = picTitles;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public List<File> getUpload() {
        return upload;
    }

    public void setUpload(List<File> upload) {
        this.upload = upload;
    }

    public List<String> getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(List<String> uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public List<String> getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(List<String> uploadContentType) {
        this.uploadContentType = uploadContentType;
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

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
