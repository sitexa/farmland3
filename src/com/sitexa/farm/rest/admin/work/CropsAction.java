package com.sitexa.farm.rest.admin.work;

import com.sitexa.farm.data.Crops;
import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-9-8
 * Time: 11:24:50
 */
public class CropsAction extends BaseAction {
    protected String id;
    protected Crops crops;
    protected Farm farm;
    protected Land land;
    protected List<Crops> cropses = new ArrayList<Crops>();

    protected Member member;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Crops getCrops() {
        return crops;
    }

    public void setCrops(Crops crops) {
        this.crops = crops;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public List<Crops> getCropses() {
        return cropses;
    }

    public void setCropses(List<Crops> cropses) {
        this.cropses = cropses;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
