package com.sitexa.farm.rest.join;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.LandPicture;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.user.data.Member;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-21
 * Time: 11:35:44
 */
public class LandPictureAction extends FarmlanderAction {
    protected String id;//landId;
    protected LandPicture picture;
    protected List<LandPicture> pictures = new ArrayList<LandPicture>();
    protected Land land;
    protected Member member;

    protected List<File> upload = new ArrayList<File>();
    protected List<String> uploadFileName = new ArrayList<String>();
    protected List<String> uploadContentType = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LandPicture getPicture() {
        return picture;
    }

    public void setPicture(LandPicture picture) {
        this.picture = picture;
    }

    public List<LandPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<LandPicture> pictures) {
        this.pictures = pictures;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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
}
