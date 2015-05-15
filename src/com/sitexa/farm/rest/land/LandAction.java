package com.sitexa.farm.rest.land;

import com.sitexa.farm.data.*;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.farm.service.RentModeService;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.site.data.Site;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-18
 * Time: 下午4:21
 */
public class LandAction extends FarmlanderAction {
    protected Site site;
    protected Land land;
    protected List<Land> lands = new ArrayList<Land>();
    protected List<LandPicture> pictures = new ArrayList<LandPicture>();
    protected List<Farm> farms = new ArrayList<Farm>();
    protected List<Faction> factions = new ArrayList<Faction>();
    protected List<Seed> seeds = new ArrayList<Seed>();
    protected Page page = new Page(10);
    protected Page page1 = new Page(5);
    protected Page page2 = new Page(6);
    protected Page page3 = new Page(7);
    protected int tabIndex = 1;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

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

    public List<LandPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<LandPicture> pictures) {
        this.pictures = pictures;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPage1() {
        return page1;
    }

    public void setPage1(Page page) {
        this.page1 = page;
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

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
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

    public String getLandPositions() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < lands.size(); i++) {
            Land land = lands.get(i);
            if (i != 0) sb.append(",");
            sb.append("new google.maps.LatLng(");
            sb.append(land.getLatitude());
            sb.append(",");
            sb.append(land.getLongitude());
            sb.append(")");
        }
        sb.append("]");
        return sb.toString();
    }

    //[{id:1,name:a},{id:2,name:b},{}]
    public String getLandNames() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < lands.size(); i++) {
            Land land = lands.get(i);
            if (i != 0) sb.append(",");
            sb.append("{'landId':'");
            sb.append(land.getLandId());
            sb.append("',");
            sb.append("'landName':'");
            sb.append(land.getLandName());
            sb.append("'}");
        }
        sb.append("]");
        return sb.toString();
    }

    public Float getLng() {
        return site.getLongitude();
    }

    public Float getLat() {
        return site.getLatitude();
    }

    public Integer getZoom() {
        return getZM(site);
    }

    private Integer getZM(Site site) {
        if (site == null) return 8;
        if (site.getZoom() == null) return 8;
        String typeId = site.getType().getTypeId();
        int intId = Integer.parseInt(typeId);
        if (intId == 1) return 3;
        else if (intId == 2) return 7;
        else if (intId == 3) return 8;
        else return 9;
    }

    public int getSell() {
        List<RentMode> modes = RentModeService.getByLand(land);
        return modes.size() > 0 ? 1 : 0;
    }
}
