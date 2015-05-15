/**
* @作者 leim
* @创建日期 2010-4-21
* @版本 V 1.0
*/ 
package com.sitexa.farm.rest.buy;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.sitexa.farm.service.FactionService;
import com.sitexa.farm.service.LandService;

public class FactionController extends FactionAction {
    
    public HttpHeaders show() {
        System.out.println("FactionController.show");
        faction = FactionService.getById(id);
        land = faction.getLand();
        return new DefaultHttpHeaders("show");
    }
}
