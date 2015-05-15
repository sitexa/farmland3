package com.sitexa.site.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-5
 * Time: 14:11:58
 */
public class SelectorAction extends BaseAction {

    private static Log log = LogFactory.getLog(SelectorAction.class);

    protected Site state;
    protected Site city;
    protected Site county;
    protected Site town;
    protected Site site;
    protected List<Site> stateList = new ArrayList<Site>();
    protected List<Site> cityList = new ArrayList<Site>();
    protected List<Site> countyList = new ArrayList<Site>();
    protected List<Site> townList = new ArrayList<Site>();
    protected List<Site> villageList = new ArrayList<Site>();
    protected List<Site> siteList = new ArrayList<Site>();

    public Site getState() {
        return state;
    }

    public void setState(Site state) {
        this.state = state;
    }

    public Site getCity() {
        return city;
    }

    public void setCity(Site city) {
        this.city = city;
    }

    public Site getCounty() {
        return county;
    }

    public void setCounty(Site county) {
        this.county = county;
    }

    public Site getTown() {
        return town;
    }

    public void setTown(Site town) {
        this.town = town;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<Site> getStateList() {
        return stateList;
    }

    public void setStateList(List<Site> stateList) {
        this.stateList = stateList;
    }

    public List<Site> getCityList() {
        return cityList;
    }

    public void setCityList(List<Site> cityList) {
        this.cityList = cityList;
    }

    public List<Site> getCountyList() {
        return countyList;
    }

    public void setCountyList(List<Site> countyList) {
        this.countyList = countyList;
    }

    public List<Site> getTownList() {
        return townList;
    }

    public void setTownList(List<Site> townList) {
        this.townList = townList;
    }

    public List<Site> getVillageList() {
        return villageList;
    }

    public void setVillageList(List<Site> villageList) {
        this.villageList = villageList;
    }

    public List<Site> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
