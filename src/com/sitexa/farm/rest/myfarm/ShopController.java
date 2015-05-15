/**
* @作者 leim
* @创建日期 2010-6-8
* @版本 V 1.0
*/ 
package com.sitexa.farm.rest.myfarm;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.sitexa.farm.data.FarmStore;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.LandService;
import com.sitexa.farm.service.PlanService;
import com.sitexa.farm.service.SeedService;

public class ShopController extends ShopAction{
	
	public static final String SEED_ID = "1";
	public static final String PLAN_ID = "2";
	
	public HttpHeaders index() {
		String farmId = getParameter("farmId");
		farm = FarmService.getById(farmId);
		land = farm.getLand();
		seedList = SeedService.getSeeds(land);
		return new DefaultHttpHeaders("index").disableCaching();
	}

    public HttpHeaders seeds() {
		String landId= getParameter("landId");
		land = LandService.getById(landId);
		seedList = SeedService.getSeeds(land);
		return new DefaultHttpHeaders("seeds").disableCaching();
	}
	
	public HttpHeaders plans() {
		String landId= getParameter("landId");
		land = LandService.getById(landId);
		planList = PlanService.getPlans(land);
		return new DefaultHttpHeaders("plans").disableCaching();
	}
	
	public HttpHeaders buySeedPage() {
		seed = SeedService.getById(id);
		return new DefaultHttpHeaders("buySeedPage").disableCaching();
		
	}

	public HttpHeaders buyPlanPage() {
		plan = PlanService.getById(id);
		return new DefaultHttpHeaders("buyPlanPage").disableCaching();
		
	}
	
	public void buy() {
		member = getProfile();
		String group = getParameter("group");
		String farmId = getParameter("farmId");
		String number = getParameter("number");
		String pwd = getParameter("pwd");
		
		FarmStore farmStore = new FarmStore();
		farmStore.setBuyer(member);
		farmStore.setFarm(FarmService.getById(farmId));
		farmStore.setObjectId(getObjectIdByGroup(group));
		farmStore.setItemId(id);
		
		ShopBean shop = new ShopBean(farmStore, number, pwd);
		shop.pay();
		String returnMessage = shop.getMessage();
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().println(returnMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	private String getObjectIdByGroup(String group){
		if(group.equals("seed")){
			return SEED_ID;
		}
		if(group.equals("plan")){
			return PLAN_ID;
		}
		return "";
	}
}
