package com.sitexa.site.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.KV;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.service.RightService;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteMember;
import com.sitexa.site.service.SiteService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-5
 * Time: 9:52:18
 */
public class SiteMemberAction extends BaseAction {
    protected String id;
    protected Site site;
    protected SiteMember siteMember;
    protected List<SiteMember> siteMembers = new ArrayList<SiteMember>();
    protected boolean haveRight;

    public List<KV> getStatusMap() {
        return SiteMember.STATUS;
    }

    public List<KV> getTypeMap() {
        return SiteMember.TYPES;
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

    public SiteMember getSiteMember() {
        return siteMember;
    }

    public void setSiteMember(SiteMember siteMember) {
        this.siteMember = siteMember;
    }

    public List<SiteMember> getSiteMembers() {
        return siteMembers;
    }

    public void setSiteMembers(List<SiteMember> siteMembers) {
        this.siteMembers = siteMembers;
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
