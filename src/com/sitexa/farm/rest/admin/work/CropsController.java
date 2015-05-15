package com.sitexa.farm.rest.admin.work;

import com.sitexa.farm.service.CropService;
import com.sitexa.farm.service.FarmService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-9-8
 * Time: 11:24:24
 */
public class CropsController extends CropsAction {

    public HttpHeaders index() {
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
            } catch (IOException ignored) {
            }
        }

        String farmId = ServletActionContext.getRequest().getParameter("farmId");
        farm = FarmService.getById(farmId);
        if (farm == null || !member.getMemberId().equals(farm.getLand().getLord().getMemberId())) {
            try {
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        land = farm.getLand();
        cropses = CropService.getByFarm(farm);
        return new DefaultHttpHeaders("");
    }
}
