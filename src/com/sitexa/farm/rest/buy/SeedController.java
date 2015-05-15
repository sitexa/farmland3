/**
 * @作者 leim
 * @创建日期 2010-4-21
 * @版本 V 1.0
 */
package com.sitexa.farm.rest.buy;

import com.sitexa.farm.service.LandService;
import com.sitexa.farm.service.SeedService;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

public class SeedController extends SeedAction {

    public HttpHeaders show() {
        System.out.println("SeedController.show");
        seed = SeedService.getById(id);
        if (seed != null) land = seed.getLand();
        return new DefaultHttpHeaders("show");
    }
}