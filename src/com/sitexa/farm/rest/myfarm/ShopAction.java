/**
* @作者 leim
* @创建日期 2010-6-8
* @版本 V 1.0
*/ 
package com.sitexa.farm.rest.myfarm;

import java.util.ArrayList;
import java.util.List;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmPlan;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.Seed;
import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;

public class ShopAction extends BaseAction {
	protected String id;	//merchandiseId
	
	protected Member member;
	protected Land land; 
	protected Farm farm;
	protected Seed seed;
	protected FarmPlan plan;

	protected List<Seed> seedList = new ArrayList<Seed>();
	protected List<FarmPlan> planList = new ArrayList<FarmPlan>();

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
	
	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	
	public Seed getSeed() {
		return seed;
	}

	public void setSeed(Seed seed) {
		this.seed = seed;
	}
	
	public FarmPlan getPlan() {
		return plan;
	}

	public void setPlan(FarmPlan plan) {
		this.plan = plan;
	}
	
	
	public List<Seed> getSeedList() {
		return seedList;
	}
	
	public List<FarmPlan> getPlanList() {
		return planList;
	}

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
