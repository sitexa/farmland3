package com.sitexa.farm.rest;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2010-5-1
 * Time: 20:47:46
 */
public class FlController extends BaseAction {
    public void index() {
        try {
            ServletActionContext.getResponse().sendRedirect("/fml");
        } catch (IOException e) {
        }
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
