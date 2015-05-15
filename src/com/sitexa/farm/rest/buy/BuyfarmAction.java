package com.sitexa.farm.rest.buy;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.site.data.Site;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-7
 * Time: 8:41:52
 */
public class BuyfarmAction extends FarmlanderAction {
    protected Site site;
    protected Land land;
    protected List<Land> lands = new ArrayList<Land>();
    protected Page page = new Page();

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public List<Land> getLands() {
        return lands;
    }

    public void setLands(List<Land> lands) {
        this.lands = lands;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
