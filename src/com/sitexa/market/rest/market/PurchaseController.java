package com.sitexa.market.rest.market;

import com.sitexa.farm.service.FarmService;
import com.sitexa.framework.Constants;
import com.sitexa.market.action.PurchaseAction;
import com.sitexa.market.data.Order;
import com.sitexa.market.data.OrderItem;
import com.sitexa.market.service.OrderService;
import com.sitexa.user.data.Member;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-10-22
 * Time: 9:43:11
 */
public class PurchaseController extends PurchaseAction {

    public HttpHeaders index() {
        System.out.println("PurchaseController.index");
        String order_id = (String) getSession().get(Constants.PO_KEY);
        order = OrderService.getByOrderId(order_id);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("PurchaseController.show");
        return null;
    }

    public HttpHeaders edit() {
        System.out.println("PurchaseController.edit");
        return null;
    }

    public HttpHeaders editNew() {
        System.out.println("PurchaseController.editNew");
        if (OBJECT_FARM.equals(objectId)) {
            if (id != null) {
                farm = FarmService.getById(id);
                orderItem = new OrderItem();
                orderItem.setObjectId(OBJECT_FARM);
                orderItem.setItemId(farm.getFarmId());
                orderItem.setItemName(farm.getFarmName());
                Double prc = null;
                try {
                    prc = new Double(farm.getRentPrice());
                } catch (Exception ignored) {
                }
                orderItem.setItemPrice(prc);
            }
        }

        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("PurchaseController.create");

        orderItem.setObjectId(objectId);
        //todo... get order from session
        String order_id = (String) getSession().get(Constants.PO_KEY);
        if (order_id != null && !"".equals(order_id))
            order = OrderService.getByOrderId(order_id);

        //todo....  get member from session
        Member me = getProfile();
        if (order == null) {
            order = new Order();
            order.setMember(me);
            OrderService.createOrder(order);
        }

        orderItem.setOrder(order);

        OrderService.createOrderItem(orderItem);

        getSession().put(Constants.PO_KEY, order.getOrderId());

        return index();
    }

    public HttpHeaders update() {
        return null;
    }

    public HttpHeaders destroy() {
        return null;
    }

    public HttpHeaders buyFarm() {
        System.out.println("PurchaseController.buyFarm");
        objectId = OBJECT_FARM;
        return editNew();
    }

    public void bill() {
        System.out.println("PurchaseController.bill");
        getSession().remove(Constants.PO_KEY);
        //begin    2009.11.18 临时使用(只更新farm.Member字段)
        if (orderItem.getItemId() != null) {
            Member me = getProfile();
            farm = FarmService.getById(orderItem.getItemId());
            try {
                if (farm != null && farm.getMember() == null) {
                    farm.setMember(me);
                    FarmService.update(farm);
                    ServletActionContext.getResponse().sendRedirect("/work/work/" + farm.getFarmId());
                } else {
                    ServletActionContext.getResponse().sendRedirect("/");
                }
            } catch (Exception e) {

            }
        }
    }


}
