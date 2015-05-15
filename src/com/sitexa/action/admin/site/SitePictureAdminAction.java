package com.sitexa.action.admin.site;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SitePicture;
import com.sitexa.site.service.SiteService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-20
 * Time: 18:00:12
 */
public class SitePictureAdminAction extends BaseAction {
    protected String id;
    protected Site site;
    protected SitePicture picture;
    protected List<SitePicture> pictures = new ArrayList<SitePicture>();

    protected List<File> upload = new ArrayList<File>();
    protected List<String> uploadFileName = new ArrayList<String>();
    protected List<String> uploadContentType = new ArrayList<String>();

    protected List<String> picTitles = new ArrayList<String>();
    protected List<String> descriptions = new ArrayList<String>();

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

    public SitePicture getPicture() {
        return picture;
    }

    public void setPicture(SitePicture picture) {
        this.picture = picture;
    }

    public List<SitePicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<SitePicture> pictures) {
        this.pictures = pictures;
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

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
