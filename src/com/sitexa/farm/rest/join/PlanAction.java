/**
* @作者 leim
* @创建日期 2010-6-11
* @版本 V 1.0
*/ 
package com.sitexa.farm.rest.join;

import java.util.ArrayList;
import java.util.List;

import com.sitexa.farm.data.FarmPlan;
import com.sitexa.farm.data.Land;
import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;

public class PlanAction extends BaseAction {
	protected String id;

	protected Member member;
	protected Land land;
	protected FarmPlan plan;

	protected List farmPlans =  new ArrayList<FarmPlan>();
	
	protected Page page = new Page(10);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public FarmPlan getPlan() {
		return plan;
	}

	public void setPlan(FarmPlan plan) {
		this.plan = plan;
	}
	
	public List getFarmPlans() {
		return farmPlans;
	}

	public void setFarmPlans(List farmPlans) {
		this.farmPlans = farmPlans;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
