/**
* @作者 leim
* @创建日期 2010-6-12
* @版本 V 1.0
*/ 
package com.sitexa.farm.rest.myfarm;

import java.util.List;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmPlan;
import com.sitexa.farm.data.Seed;
import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;

public class StoreAction extends BaseAction{
	protected String id;
	
	protected Seed seed;
	protected FarmPlan plan;
	protected Farm farm;
	protected Member member;
	protected Object[] good;

	protected List<Object[]> goods;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Farm getFarm() {
		return farm;
	}
	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Object[] getGood() {
		return good;
	}
	public List<Object[]> getGoods() {
		return goods;
	}

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
