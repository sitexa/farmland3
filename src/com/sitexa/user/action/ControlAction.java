package com.sitexa.user.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberPicture;
import com.sitexa.user.data.MemberProperty;
import com.sitexa.user.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-11-16
 * Time: 20:36:25
 */
public class ControlAction extends BaseAction {
    protected String id;

    protected User user;
    protected Member member;

    protected MemberProperty property;
    protected List<MemberProperty> properties = new ArrayList<MemberProperty>();

    protected MemberPicture picture;
    protected List<MemberPicture> pictures = new ArrayList<MemberPicture>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return member.getUser();
    }

    public void setUser(User user) {
        this.user = user;
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

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
