package com.sitexa.farm.rest.land;

import com.sitexa.farm.service.FactionService;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.LandService;
import com.sitexa.farm.service.SeedService;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-18
 * Time: 下午4:20
 */
public class LandController extends LandAction {

    public HttpHeaders index() {
        System.out.println("LandController.index");
        String siteId = getParameter("siteId");
        if (siteId != null && !"".equals(siteId))
            site = SiteService.getSite(siteId);
        else site = SiteService.getRoot();
        System.out.println("site.getName = " + site.getName());
        lands = LandService.getLandsBySite(site, page);
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders show() {
        System.out.println("LandController.show");
        try {
            land = LandService.getById(id);
            pictures.addAll(land.getLandPictures());
            factions = FactionService.getFactions(land, page1);
            seeds = SeedService.getSeeds(land, page2);
            farms = FarmService.getFarmByLand3(land, page3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders farms() {
        System.out.println("LandController.farms");
        land = LandService.getById(id);
        farms = FarmService.getFarmByLand3(land, page);
        return new DefaultHttpHeaders("farms").disableCaching();
    }

    public HttpHeaders factions() {
        System.out.println("LandController.factions");
        land = LandService.getById(id);
        factions = FactionService.getFactions(land, page);
        return new DefaultHttpHeaders("factions").disableCaching();
    }

    public HttpHeaders seeds() {
        System.out.println("LandController.seeds");
        land = LandService.getById(id);
        seeds = SeedService.getSeeds(land, page);
        return new DefaultHttpHeaders("seeds").disableCaching();
    }
}
