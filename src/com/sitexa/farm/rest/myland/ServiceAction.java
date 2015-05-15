package com.sitexa.farm.rest.myland;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.Service;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.user.data.Member;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-22
 * Time: 10:14:45
 */
public class ServiceAction extends FarmlanderAction {
    protected Land land;
    protected Member member;
    protected Service service;
    protected List<Service> services = new ArrayList<Service>();

    protected File upload;
    protected String uploadFileName;
    protected String uploadContentType;

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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
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
}
