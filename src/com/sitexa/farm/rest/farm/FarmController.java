package com.sitexa.farm.rest.farm;

import com.sitexa.farm.service.CropService;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.FarmingService;
import com.sitexa.farm.service.LandService;
import com.sitexa.post.service.FarmPostService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-20
 * Time: 下午4:34
 */
public class FarmController extends FarmAction {

    public HttpHeaders index() {
        System.out.println("FarmController.index");
        try {
            if (land != null)
                land = LandService.getById(land.getLandId());
            farms = FarmService.searchFarms(land, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders show() {
        System.out.println("FarmController.show");
        try {
            farm = FarmService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (farm != null) {
            try {
                land = farm.getLand();
                cropses = CropService.getByFarm(farm, page1);
                farmings = FarmingService.getFarming(farm, page2);
                posts = FarmPostService.getFarmPosts(farm, page3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new DefaultHttpHeaders("show").disableCaching();
    }
}
