package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmPicture;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.user.data.Member;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-16
 * Time: 14:49:01
 */
public class FarmPictureAction extends FarmlanderAction {
    protected String id;
    protected FarmPicture picture;
    protected List<FarmPicture> pictures = new ArrayList<FarmPicture>();
    protected List<String> picTitles = new ArrayList<String>();
    protected List<String> descriptions = new ArrayList<String>();

    protected List<File> upload = new ArrayList<File>();
    protected List<String> uploadFileName = new ArrayList<String>();
    protected List<String> uploadContentType = new ArrayList<String>();

    protected Member member;
    protected Farm farm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FarmPicture getPicture() {
        return picture;
    }

    public void setPicture(FarmPicture picture) {
        this.picture = picture;
    }

    public List<FarmPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<FarmPicture> pictures) {
        this.pictures = pictures;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
