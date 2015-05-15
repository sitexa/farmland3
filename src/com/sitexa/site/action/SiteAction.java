package com.sitexa.site.action;

import com.opensymphony.xwork2.ModelDriven;
import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.service.RightService;
import com.sitexa.site.data.*;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

public class SiteAction extends BaseAction implements ModelDriven<Object> {

    protected String searchWord = "";
    protected boolean withChildren = false;
    protected Page page = new Page();

    protected String id;
    protected Site site;
    protected SiteType siteType;
    protected List<Site> siteList = new ArrayList<Site>();
    protected List<SiteType> siteTypeList = new ArrayList<SiteType>();

    protected SiteProperty property;
    protected List<SiteProperty> properties = new ArrayList<SiteProperty>();

    protected SitePicture picture;
    protected List<SitePicture> pictures = new ArrayList<SitePicture>();

    protected List<SiteMember> members = new ArrayList<SiteMember>();

    protected Member governor;
    protected String siteAddress;
    protected boolean haveRight;

    protected List<Site> hotSiteList = new ArrayList<Site>();

    public List<SiteProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<SiteProperty> properties) {
        this.properties = properties;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public boolean isWithChildren() {
        return withChildren;
    }

    public void setWithChildren(boolean withChildren) {
        this.withChildren = withChildren;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List getSiteList() {
        return siteList;
    }

    public SiteType getSiteType() {
        return siteType;
    }

    public void setSiteType(SiteType siteType) {
        this.siteType = siteType;
    }

    public List getSiteTypeList() {
        siteTypeList = SiteService.getAllSiteTypes();
        return siteTypeList;
    }

    public List<SitePicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<SitePicture> pictures) {
        this.pictures = pictures;
    }

    public SiteProperty getProperty() {
        return property;
    }

    public void setProperty(SiteProperty property) {
        this.property = property;
    }

    public SitePicture getPicture() {
        return picture;
    }

    public void setPicture(SitePicture picture) {
        this.picture = picture;
    }

    public List<SiteMember> getMembers() {
        if (site != null)
            members.addAll(site.getMembers());
        return members;
    }

    public void setMembers(List<SiteMember> members) {
        this.members = members;
    }

    public Member getGovernor() {
        return governor;
    }

    public void setGovernor(Member governor) {
        this.governor = governor;
    }

    public Object getModel() {
        if (site != null) return site;
        else if (siteList.size() > 0) return siteList;
        else return null;
    }

    public Float getLng() {
        return site.getLongitude();
    }

    public Float getLat() {
        return site.getLatitude();
    }

    public Integer getZoom() {
        return getZM(site);
    }

    private Integer getZM(Site site) {
        return site.getZoom();
    }

    public String getSiteAddress() {
        Site root = SiteService.getRoot();
        if (site != null && site.getType() != null && site.getType().getTypeId() != null) {
            if ("1".equals(site.getType().getTypeId())) {  //country
                siteAddress = site.getName();
            } else if ("2".equals(site.getType().getTypeId())) { //province
                siteAddress = site.getName() + "," + root.getName();
            } else if ("3".equals(site.getType().getTypeId())) { //city
                siteAddress = site.getName() + "," + site.getParent().getName() + "," + root.getName();
            } else if ("4".equals(site.getType().getTypeId())) { //county and others
                siteAddress = site.getName() + "," + site.getParent().getParent().getName() + "," + root.getName();
            } else if ("5".equals(site.getType().getTypeId())) { //county and others
                siteAddress = site.getName() + "," + site.getParent().getParent().getParent().getName() + "," + root.getName();
            } else {
                siteAddress = site.getName() + "," + site.getParent().getParent().getName() + "," + root.getName();
            }
        } else {
            siteAddress = root.getName();
        }
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public boolean getHaveRight() {
        if (id != null)
            haveRight = RightService.checkRight(getProfile(), SiteService.getSite(id));
        return haveRight;
    }

    public void setHaveRight(boolean haveRight) {
        this.haveRight = haveRight;
    }

    public List<Site> getHotSiteList() {
        return hotSiteList;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
