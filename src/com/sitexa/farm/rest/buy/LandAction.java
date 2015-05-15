package com.sitexa.farm.rest.buy;

import com.sitexa.farm.data.*;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-19
 * Time: 17:14:00
 */
public class LandAction extends FarmlanderAction {
    protected Site site;
    protected Land land;
    protected Farm farm;
    protected List<Farm> farms = new ArrayList<Farm>();
    protected List<Faction> factions = new ArrayList<Faction>();
    protected List<Seed> seeds = new ArrayList<Seed>();
    protected List<LandPicture> pictures = new ArrayList<LandPicture>();
    protected Member member;

    protected Page page = new Page(20);

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

    public List<Faction> getFactions() {
        return factions;
    }

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }

    public List<Seed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
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

    public List<LandPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<LandPicture> pictures) {
        this.pictures = pictures;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
