/**
* @作者 leim
* @创建日期 2010-6-23
* @版本 V 1.0
*/ 
package com.sitexa.farm.rest.myfarm;

import java.util.ArrayList;
import java.util.List;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Farming;
import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;

public class FarmingAction extends BaseAction{
	protected String id;
	protected Farm farm;
	protected List<Farming> farmings = new ArrayList<Farming>();
	
	protected Page page = new Page(10);
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Farm getFarm() {
		return farm;
	}
	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	public List<Farming> getFarmings() {
		return farmings;
	}
	public void setFarmings(List<Farming> farmings) {
		this.farmings = farmings;
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
