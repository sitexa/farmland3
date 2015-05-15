/**
* @作者 leim
* @创建日期 2010-6-23
* @版本 V 1.0
*/ 
package com.sitexa.farm.rest.admin.work;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Farming;
import com.sitexa.farm.service.ActionBase;
import com.sitexa.farm.service.FactionService;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.FarmingService;
import com.sitexa.farm.service.FertilizeBean;
import com.sitexa.farm.service.HarvestBean;
import com.sitexa.farm.service.KillBean;
import com.sitexa.farm.service.LandService;
import com.sitexa.farm.service.OtherBean;
import com.sitexa.farm.service.PickBean;
import com.sitexa.farm.service.PlantBean;
import com.sitexa.farm.service.PloughBean;
import com.sitexa.farm.service.SeedService;
import com.sitexa.farm.service.ServiceEnum;
import com.sitexa.farm.service.WateringBean;
import com.sitexa.farm.service.WeedBean;
import com.sitexa.user.data.LeaveMessage;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.LeaveMessageService;

public class FarmingController extends FarmingAction{

	public HttpHeaders index() {
		String farmId = getParameter("farmId");
		farm = FarmService.getById(farmId);
		farmings = FarmingService.getFarming(farm, page);
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	public HttpHeaders lands() {
		member = getProfile();
		List landList = FarmingService.getLandList(member);
		ServletActionContext.getRequest().setAttribute("landList", landList);
		return new DefaultHttpHeaders("lands").disableCaching();
	}
	
	public HttpHeaders farms() {
		String landId = getParameter("landId");
		List farmList = FarmingService.getFarmList(LandService.getById(landId), page);
		ServletActionContext.getRequest().setAttribute("farmList", farmList);
		ServletActionContext.getRequest().setAttribute("landId", landId);
		return new DefaultHttpHeaders("farms").disableCaching();
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
	
	public HttpHeaders delete() {
		member = getProfile();
		Farming farming = FarmingService.getById(id);
		String message = getParameter("message");
		if(farming!=null){
			FarmingService.destroy(farming);
			FarmingService.sendMessage(member, farming, message);
			farm = farming.getFarm();
			farmings = FarmingService.getFarming(farm, page);
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
    public HttpHeaders doFarming() {
        System.out.println("FarmingController.doFarming");
        Farming farming = FarmingService.getById(id);
        ActionBase biz = null;
        String returnMessage = "";
        try {
        	if("1".equals(farming.getState())){
                biz = getAction(farming);
                biz.doFarming();
                returnMessage = biz.getSuccessMessage();
        	}
        } catch (Exception e) {
        	returnMessage = biz.getErrorMessage();
        }
        
		farm = farming.getFarm();
		farmings = FarmingService.getFarming(farm, page);
		ServletActionContext.getRequest().setAttribute("returnMessage", returnMessage);
        return new DefaultHttpHeaders("index").disableCaching();
    }
    
    private ActionBase getAction(Farming farming){
    	ActionBase action = null;
    	String actionId = farming.getFaction().getActionId();
    	if (ServiceEnum.PLANTING.value().equals(actionId)) {
    		action = new PlantBean(farming);
    	}else if(ServiceEnum.WATERING.value().equals(actionId)){
    		action = new WateringBean(farming);
    	}else if (ServiceEnum.FERTILIZE.value().equals(actionId)){
    		action = new FertilizeBean(farming);
    	}else if (ServiceEnum.WEED.value().equals(actionId)){
    		action = new WeedBean(farming);
    	}else if (ServiceEnum.KILL_INSECT.value().equals(actionId)){
    		action = new KillBean(farming);
    	}else if(ServiceEnum.PICK.value().equals(actionId)){
    		action = new PickBean(farming);
    	}else if (ServiceEnum.HARVEST.value().equals(actionId)){
    		action = new HarvestBean(farming);
    	}else if(ServiceEnum.PLOUGH.value().equals(actionId)
                || ServiceEnum.PLOUGH_MACHINE.value().equals(actionId)){
    		action = new PloughBean(farming);
    	}else{
    		action = new OtherBean(farming);
    	}
    	return action;
    }
}
