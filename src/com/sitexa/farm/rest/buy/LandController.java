package com.sitexa.farm.rest.buy;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.LandService;
import com.sitexa.farm.service.SeedService;
import com.sitexa.site.service.SiteService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * User: xnpeng
 * Date: 2010-4-19
 * Time: 17:12:54
 */
public class LandController extends LandAction {
    public HttpHeaders index() {
        //member = getProfile();
        //todo...获取本地区农场列表
        site = SiteService.getSite("430000");
        List<Land> lands = LandService.getLandsBySite(site, page);
        if (lands.size() > 0) {
            land = lands.get(0);
            id = land.getLandId();

            HttpServletRequest request = ServletActionContext.getRequest();
            String url = request.getContextPath() + "/buy/land/" + id;
            try {
                ServletActionContext.getResponse().sendRedirect(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public HttpHeaders show() {
        System.out.println("LandController.show");
        if (land == null || "".equals(land.getLandId())) {
            land = LandService.getById(id);
        }
        //member = getProfile();
        if (land != null) {
            farms.clear();
            factions.clear();
            seeds.clear();
            pictures.clear();

            //farms.addAll(land.getFarms());
            //factions.addAll(land.getFarmingActions());
            //seeds.addAll(land.getSeeds());
            site = land.getSite();
            pictures.addAll(land.getLandPictures());

        }
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders farm() {
        System.out.println("LandController.farm");
        System.out.println("id = " + id);
        System.out.println("page.getCurrent() = " + page.getCurrent());
        land = LandService.getById(id);
//    	farms.clear();
//    	farms.addAll(starLand.getFarms());
        farms = FarmService.getFarmByLand3(land, page);
        for (int i = 0; i < farms.size(); i++) {
            Farm farm1 = farms.get(i);
            System.out.println("farm1.getFarmId() = " + farm1.getFarmId());
        }

        return new DefaultHttpHeaders("farm");
    }

    public HttpHeaders faction() {
        land = LandService.getById(id);
        factions.clear();
        factions.addAll(land.getFarmingActions());

        ServletActionContext.getRequest().setAttribute("menu", 2);
        return new DefaultHttpHeaders("faction");
    }

    public HttpHeaders seed() {
        land = LandService.getById(id);
        seeds = SeedService.getSeeds(land);
        ServletActionContext.getRequest().setAttribute("menu", 3);
        return new DefaultHttpHeaders("seed");
    }

}
