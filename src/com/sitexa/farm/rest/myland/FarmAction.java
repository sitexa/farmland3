package com.sitexa.farm.rest.myland;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-21
 * Time: 9:10:51
 */
public class FarmAction extends FarmlanderAction {
    protected Site site;
    protected Land land;
    protected Farm farm;
    protected List<Farm> farms = new ArrayList<Farm>();
    protected Member member;
    protected Page page = new Page();

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
