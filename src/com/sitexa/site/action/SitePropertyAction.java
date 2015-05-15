package com.sitexa.site.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteProperty;
import com.sitexa.site.service.SiteService;
import com.sitexa.service.RightService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-26
 * Time: 10:09:12
 */
public class SitePropertyAction extends BaseAction {
    protected String id;
    protected Site site;
    protected SiteProperty property;
    protected List<SiteProperty> properties = new ArrayList<SiteProperty>();

    protected List<String> propNames = new ArrayList<String>();
    protected List<String> propValues = new ArrayList<String>();

    protected boolean haveRight;

    public String[] getPropertyItems() {
        return SiteProperty.SITE_PROPERTY_ITEMS;
    }

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

    public SiteProperty getProperty() {
        return property;
    }

    public void setProperty(SiteProperty property) {
        this.property = property;
    }

    public List<SiteProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<SiteProperty> properties) {
        this.properties = properties;
    }

    public List<String> getPropNames() {
        return propNames;
    }

    public void setPropNames(List<String> propNames) {
        this.propNames = propNames;
    }

    public List<String> getPropValues() {
        return propValues;
    }

    public void setPropValues(List<String> propValues) {
        this.propValues = propValues;
    }

    public boolean getHaveRight() {
        if (id != null)
            haveRight = RightService.checkRight(getProfile(), SiteService.getSite(id));
        return haveRight;
    }

    public void setHaveRight(boolean haveRight) {
        this.haveRight = haveRight;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
