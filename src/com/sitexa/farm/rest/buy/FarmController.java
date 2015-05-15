package com.sitexa.farm.rest.buy;


import com.sitexa.farm.service.FarmBean;
import com.sitexa.farm.service.FarmService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import sun.print.resources.serviceui;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2010-4-19
 * Time: 17:12:54
 */
public class FarmController extends FarmAction {
    private static Log log = LogFactory.getLog(FarmController.class);

    public HttpHeaders index() {
        System.out.println("LandController.show");
        return show();
    }

    public HttpHeaders show() {
        System.out.println("LandController.show");
        farm = FarmService.getById(id);
        land = farm.getLand();
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders buyFarm() {
        System.out.println("FarmController.buyFarm");
        member = getProfile();
        if (member == null) {
            try {
            	String url = ServletActionContext.getRequest().getContextPath()+"/logon";
                ServletActionContext.getResponse().sendRedirect(url);
                return null;
            } catch (IOException e) {

            }
        }
        
        farm = FarmService.getById(id);
        land = farm.getLand();
        return new DefaultHttpHeaders("buyFarm");
    }

    public HttpHeaders pay() {
        System.out.println("FarmController.pay");
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
                return null;
            } catch (IOException e) {

            }
        }

        FarmBean biz = null;
        try {
        	farm = FarmService.getById(farm.getFarmId());
            biz = new FarmBean(farm);
            biz.buyFarm(member, password);
            String url = ServletActionContext.getRequest().getContextPath() + "/work/work/" + farm.getFarmId();
            ServletActionContext.getResponse().sendRedirect(url);
        } catch (Exception e) {
            if (biz == null) {
                log.error(e.getMessage());
                addActionError("购买出错：" + e.getMessage());
            } else {
                log.error(biz.getErrorInfo());
                addActionError("购买出错：" + biz.getErrorInfo());
            }
            return new DefaultHttpHeaders("buyFarm");
            /*
            try {
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getHeader("referer"));
            } catch (IOException e1) {
            }
            */
        }
        return null;
    }

    public HttpHeaders farms() {
        String tag = getParameter("tag");
        if (tag != null && "1".equals(tag)) {
            farms = FarmService.searchNewFarms(site, land, page);
        } else {
            farms = FarmService.searchFarms(site, land, page);
        }
        if (farms.size() > 0) {
            land = farms.get(0).getLand();
        }
        return new DefaultHttpHeaders("farms").disableCaching();
    }
    
    public HttpHeaders enterFarms() {
    	System.out.println("FarmController.enterFarms");
    	String word = ServletActionContext.getRequest().getParameter("word");
    	farms = FarmService.searchFarms(word, page, true);
        if (farms.size() > 0) {
            land = farms.get(0).getLand();
        }    	
        ServletActionContext.getRequest().setAttribute("word", word);
        return new DefaultHttpHeaders("enterFarms");
    }
    
    public HttpHeaders search() {
    	System.out.println("FarmController.search");
    	String word = ServletActionContext.getRequest().getParameter("word");
    	farms = FarmService.searchFarms(word, page, true);
        if (farms.size() > 0) {
            land = farms.get(0).getLand();
        }    	
        ServletActionContext.getRequest().setAttribute("word", word);
        return new DefaultHttpHeaders("farms").disableCaching();
    }
}
