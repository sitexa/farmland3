package com.sitexa.sys.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.sys.data.ViewStats;
import com.sitexa.sys.data.ViewStatsAddr;
import com.sitexa.sys.data.ViewStatsUri;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-6-24
 * Time: 21:18:00
 */
public class ViewStatsAction extends BaseAction {
    protected int count;
    protected String id;
    protected ViewStats viewStats;
    protected List<ViewStats> viewStatses = new ArrayList<ViewStats>();
    protected List<ViewStatsAddr> viewStatsAddrs = new ArrayList<ViewStatsAddr>();
    protected List<ViewStatsUri> viewStatsUris = new ArrayList<ViewStatsUri>();
    protected Page page = new Page();
    protected Page page2 = new Page();
    protected Page page3 = new Page();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ViewStats getViewStats() {
        return viewStats;
    }

    public List<ViewStats> getViewStatses() {
        return viewStatses;
    }

    public List<ViewStatsAddr> getViewStatsAddrs() {
        return viewStatsAddrs;
    }

    public List<ViewStatsUri> getViewStatsUris() {
        return viewStatsUris;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPage2() {
        return page2;
    }

    public void setPage2(Page page2) {
        this.page2 = page2;
    }

    public Page getPage3() {
        return page3;
    }

    public void setPage3(Page page3) {
        this.page3 = page3;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
