package com.sitexa.farm.rest;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.service.LandService;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-1
 * Time: 11:13:48
 */
public class FarmlanderAction extends BaseAction {
    protected List<Land> getDefaultLand() {
        Site site = getDefaultSite();
        return LandService.getLandsBySite(site);
    }

    protected Site getDefaultSite() {
        Site site = getHome();
        if (site == null) site = SiteService.getRoot();
        return site;
    }

    protected boolean isAdmin() {
        Member member = getProfile();
        return member != null && member.getMemberId().equals("1005");
    }

    protected boolean isLandlord() {
        Member member = getProfile();
        return member.getType().getTypeId().equals("6");
    }
}
