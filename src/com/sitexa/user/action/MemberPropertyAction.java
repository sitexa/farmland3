package com.sitexa.user.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberProperty;
import com.sitexa.user.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-7
 * Time: 16:35:37
 */
public class MemberPropertyAction extends BaseAction {
    protected String id;
    protected Site site;
    protected Member member;
    protected User user;
    protected MemberProperty property;
    protected List<MemberProperty> properties = new ArrayList<MemberProperty>();
    protected List<String> propNames = new ArrayList<String>();
    protected List<String> propValues = new ArrayList<String>();

    public String[] getPropertyItems() {
        return MemberProperty.MEMBER_PROPERTY_ITEMS;
    }

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

    public MemberProperty getProperty() {
        return property;
    }

    public void setProperty(MemberProperty property) {
        this.property = property;
    }

    public List<MemberProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<MemberProperty> properties) {
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
