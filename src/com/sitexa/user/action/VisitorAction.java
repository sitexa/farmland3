package com.sitexa.user.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.User;
import com.sitexa.user.data.Visitor;
import com.sitexa.site.data.Site;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-6
 * Time: 21:13:04
 */
public class VisitorAction extends BaseAction {
    protected String id;
    protected User user;
    protected Member member;
    protected Visitor visitor;
    protected Site site;
    protected List<Visitor> visitors = new ArrayList<Visitor>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
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

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
