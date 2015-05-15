package com.sitexa.farm.rest.buy;

import com.sitexa.farm.service.LandService;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * User: xnpeng
 * Date: 2010-4-6
 * Time: 16:50:48
 */
public class BuyfarmController extends BuyfarmAction {

    public HttpHeaders index() {
        System.out.println("BuyfarmController.index");
        HttpServletRequest request = ServletActionContext.getRequest();
        String siteId = request.getParameter("siteId");
        if (siteId == null || "".equals(siteId)) {
            site = getDefaultSite();
        } else {
            site = SiteService.getSite(siteId);
        }
        //todo....430000  获取本市及本市的农场
        site = SiteService.getRoot();
        lands = LandService.getLandsBySite(site, page);
        land = lands.get(0);

        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        return null;
    }

    public HttpHeaders edit() {
        return null;
    }

    public HttpHeaders editNew() {
        return null;
    }

    public HttpHeaders create() {
        return null;
    }

    public HttpHeaders update() {
        return null;
    }

    public HttpHeaders destroy() {
        return null;
    }
}
