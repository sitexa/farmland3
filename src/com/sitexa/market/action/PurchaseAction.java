package com.sitexa.market.action;

import com.sitexa.farm.data.Farm;
import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.market.data.Order;
import com.sitexa.market.data.OrderItem;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;

/**
 * User: xnpeng
 * Date: 2009-10-22
 * Time: 9:43:30
 */
public class PurchaseAction extends BaseAction {

    protected final String OBJECT_FARM = "farm";
    protected final String OBJECT_SERVICE = "service";
    protected final String OBJECT_PRODUCT = "product";
    protected final String OBJECT_MATERIAL = "material";

    protected String id;
    protected String objectId;
    protected Order order;
    protected OrderItem orderItem;

    protected Farm farm;

    public String getOBJECT_FARM() {
        return OBJECT_FARM;
    }

    public String getOBJECT_SERVICE() {
        return OBJECT_SERVICE;
    }

    public String getOBJECT_PRODUCT() {
        return OBJECT_PRODUCT;
    }

    public String getOBJECT_MATERIAL() {
        return OBJECT_MATERIAL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
