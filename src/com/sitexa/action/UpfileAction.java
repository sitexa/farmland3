/**
* @作者 leim
* @创建日期 2010-5-12
* @版本 V 1.0
*/ 
package com.sitexa.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;

public class UpfileAction extends BaseAction{
    protected String id;
    
	protected List<File> upload = new ArrayList<File>();
	protected List<String> uploadFileName = new ArrayList<String>();
    protected List<String> uploadContentType = new ArrayList<String>();
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
