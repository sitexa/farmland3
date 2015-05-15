package com.sitexa.user.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteMember;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-14
 * Time: 9:16:17
 */
public class MemberSiteAction extends BaseAction {
    protected String id;
    protected Site site;
    protected Member member;
    protected List<SiteMember> memberSites = new ArrayList<SiteMember>();

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<SiteMember> getMemberSites() {
        return memberSites;
    }

    public void setMemberSites(List<SiteMember> memberSites) {
        this.memberSites = memberSites;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
