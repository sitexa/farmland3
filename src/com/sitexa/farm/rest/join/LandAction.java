package com.sitexa.farm.rest.join;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.LandPicture;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * User: xnpeng
 * Date: 2010-4-20
 * Time: 16:30:47
 */
public class LandAction extends FarmlanderAction {
    protected String id;
    protected Land land;
    protected List<Land> lands = new ArrayList<Land>();
    protected LandPicture picture;
    protected List<LandPicture> pictures = new ArrayList<LandPicture>();
    protected Farm farm;
    protected List<Farm> farms = new ArrayList<Farm>();

    protected Member member;
    protected Site site;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LandPicture getPicture() {
        return picture;
    }

    public void setPicture(LandPicture picture) {
        this.picture = picture;
    }

    public List<LandPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<LandPicture> pictures) {
        this.pictures = pictures;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
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

    public Map getLandTypes() {
        Map map = new Hashtable();
        map.put("菜地", "菜地");
        map.put("水田", "水田");
        map.put("山地", "山地");
        map.put("鱼塘", "鱼塘");
        return map;
    }
}
