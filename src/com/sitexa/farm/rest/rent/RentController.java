package com.sitexa.farm.rest.rent;

import com.sitexa.farm.service.FarmBean;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.LandService;
import com.sitexa.farm.service.RentModeService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-27
 * Time: 上午11:20
 */
public class RentController extends RentAction {

    public HttpHeaders index() {
        String landId = ServletActionContext.getRequest().getParameter("landId");
        land = LandService.getById(landId);
        member = getProfile();

        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (land == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/land/land");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            rentModes = RentModeService.getByLand(land);
        }

        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders show() {
        return null;
    }

    public HttpHeaders edit() {
        return null;
    }

    public HttpHeaders editNew() {
        String landId = ServletActionContext.getRequest().getParameter("landId");
        land = LandService.getById(landId);
        member = getProfile();

        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (land == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/land/land");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            rentModes = RentModeService.getByLand(land);
        }
        return new DefaultHttpHeaders("index").disableCaching();
    }


    public HttpHeaders create() {
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
                return null;
            } catch (IOException e) {

            }
        }

        try {
            land = LandService.getById(farm.getLand().getLandId());
            farm.setLand(land);
            farm.setSite(land.getSite());
            FarmService.create(farm);

            FarmBean biz = null;
            try {
                farm = FarmService.getById(farm.getFarmId());
                biz = new FarmBean(farm);
                biz.buyFarm(member, password);
                ServletActionContext.getResponse().sendRedirect("/work/work/" + farm.getFarmId());
            } catch (Exception e) {
                e.printStackTrace();
                if (biz == null) {
                    addActionError("购买出错：" + e.getMessage());
                } else {
                    addActionError("购买出错：" + biz.getErrorInfo());
                }
                rentModes = RentModeService.getByLand(land);
                return new DefaultHttpHeaders("").disableCaching();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpHeaders update() {
        return null;
    }

    public HttpHeaders destroy() {
        return null;
    }
}
