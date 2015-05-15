package com.sitexa.farm.rest.join;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.LandPicture;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-7
 * Time: 8:48:40
 */
public class JoinlandAction extends FarmlanderAction {
    protected String id;//landId;
    protected Land land;
    protected List<Land> lands = new ArrayList<Land>();
    protected Site site;
    protected Member member;
    protected LandPicture picture;
    protected List<LandPicture> pictures = new ArrayList<LandPicture>();
    protected String step = "1";

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

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public List<Land> getLands() {
        return lands;
    }

    public void setLands(List<Land> lands) {
        this.lands = lands;
    }
}
