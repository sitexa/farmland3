package com.sitexa.market.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.market.data.Order;
import com.sitexa.market.data.OrderItem;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-1-18
 * Time: 10:30:22
 */
public class OrderAction extends BaseAction {
    protected final String OBJECT_FARM = "farm";
    protected final String OBJECT_SERVICE = "service";
    protected final String OBJECT_PRODUCT = "product";
    protected final String OBJECT_MATERIAL = "material";

    protected String id;
    protected String objectId;
    protected Order order;
    protected OrderItem orderItem;
    protected List<Order> orders = new ArrayList<Order>();

    protected Member member;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

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

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
