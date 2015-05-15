/**
* @作者 leim
* @创建日期 2010-6-23
* @版本 V 1.0
*/ 
package com.sitexa.farm.rest.myfarm;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Farming;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.FarmingService;

public class FarmingController extends FarmingAction{

	public HttpHeaders index() {
		String farmId = getParameter("farmId");
		farm = FarmService.getById(farmId);
		farmings = FarmingService.getFarming(farm, page);
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	public HttpHeaders window() {
		String farmId = getParameter("farmId");
		farm = FarmService.getById(farmId);
		farmings = FarmingService.getFarming(farm, page);
		return new DefaultHttpHeaders("window").disableCaching();
	}
	
	public HttpHeaders destroy() {
		Farming farming = FarmingService.getById(id);
		if(farming!=null){
			FarmingService.destroy(farming);
			farm = farming.getFarm();
			farmings = FarmingService.getFarming(farm, page);
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}
}
