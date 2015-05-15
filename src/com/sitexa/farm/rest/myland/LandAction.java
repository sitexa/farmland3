package com.sitexa.farm.rest.myland;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.LandPicture;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-28
 * Time: 下午10:43
 */
public class LandAction extends FarmlanderAction {
    protected Land land;
    protected List<Land> lands = new ArrayList<Land>();
    protected LandPicture picture;
    protected List<LandPicture> pictures = new ArrayList<LandPicture>();
    protected Farm farm;
    protected List<Farm> farms = new ArrayList<Farm>();
    protected Member member;

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

    public Map getLandTypes() {
        Map map = new Hashtable();
        map.put("菜地", "菜地");
        map.put("水田", "水田");
        map.put("山地", "山地");
        map.put("鱼塘", "鱼塘");
        return map;
    }
}
