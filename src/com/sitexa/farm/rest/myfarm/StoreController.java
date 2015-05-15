/**
 * @作者 leim
 * @创建日期 2010-6-12
 * @版本 V 1.0
 */
package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.SeedService;
import com.sitexa.farm.service.StoreService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

public class StoreController extends StoreAction {

    private static final long serialVersionUID = -2919900205387238881L;

    public HttpHeaders index() {
        member = getProfile();
        String group = ShopController.SEED_ID;
        String farmId = getParameter("farmId");
        farm = FarmService.getById(farmId);
        goods = StoreService.getGoods(farm, member, group);
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders seeds() {
        member = getProfile();
        String group = ShopController.SEED_ID;
        String farmId = getParameter("farmId");
        farm = FarmService.getById(farmId);
        goods = StoreService.getGoods(farm, member, group);
        return new DefaultHttpHeaders("seeds").disableCaching();
    }

    public HttpHeaders plans() {
        member = getProfile();
        String group = ShopController.PLAN_ID;
        String farmId = getParameter("farmId");
        farm = FarmService.getById(farmId);
        goods = StoreService.getGoods(farm, member, group);
        return new DefaultHttpHeaders("plans").disableCaching();
    }

    public HttpHeaders seedPage() {
        member = getProfile();
        String group = ShopController.SEED_ID;
        String itemId = getParameter("itemId");
        String farmId = getParameter("farmId");
        seed = SeedService.getById(itemId);
        farm = FarmService.getById(farmId);
        good = StoreService.getGood(farm, member, group, itemId);
        return new DefaultHttpHeaders("seedPage").disableCaching();

    }

    public HttpHeaders planPage() {
        member = getProfile();
        String group = ShopController.PLAN_ID;
        String itemId = getParameter("itemId");
        String farmId = getParameter("farmId");
        plan = StoreService.getFarmPlanById(itemId);
        farm = FarmService.getById(farmId);
        good = StoreService.getGood(farm, member, group, itemId);
        return new DefaultHttpHeaders("planPage").disableCaching();

    }

}
