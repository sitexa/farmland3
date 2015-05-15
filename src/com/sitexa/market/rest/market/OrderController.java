package com.sitexa.market.rest.market;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.service.FarmService;
import com.sitexa.framework.Constants;
import com.sitexa.market.action.OrderAction;
import com.sitexa.market.data.Order;
import com.sitexa.market.data.OrderItem;
import com.sitexa.market.service.OrderService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.sql.SQLException;

/**
 * User: xnpeng
 * Date: 2010-1-18
 * Time: 10:29:16
 */
public class OrderController extends OrderAction {

    public HttpHeaders index() {
        System.out.println("OrderController.index");
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("logon");
            } catch (IOException e) {

            }
        }
        orders = OrderService.getOrders(member);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("OrderController.show");
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
            } catch (IOException e) {

            }
        }
        order = OrderService.getMemberOrder(member, id);
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        System.out.println("OrderController.edit");
        if (haveRight())
            order = OrderService.getByOrderId(id);
        else
            return show();
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        System.out.println("OrderController.editNew");
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("OrderController.create");
        if (haveRight()) {
            OrderService.createOrder(order);
        }
        return index();
    }

    public HttpHeaders update() {
        System.out.println("OrderController.update");
        if (haveRight())
            OrderService.updateOrder(order);
        return show();
    }

    public HttpHeaders destroy() {
        System.out.println("OrderController.destroy");
        if (!haveRight()) return index();
        try {
            OrderService.deleteOrder(id);
        } catch (SQLException e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return index();
    }

    public HttpHeaders deleteOrderItem() {
        System.out.println("OrderController.deleteOrderItem");
        if (!haveRight()) return index();
        String orderItemId = ServletActionContext.getRequest().getParameter("orderItemId");
        OrderService.deleteOrderItem(orderItemId);
        return index();
    }

    public HttpHeaders addOrderItem() {
        System.out.println("OrderController.addOrderItem");
        member = getProfile();
        if (member == null) return index();
        objectId = ServletActionContext.getRequest().getParameter("objectId");
        String orderItemId = ServletActionContext.getRequest().getParameter("orderItemId");
        if (OBJECT_FARM.equals(objectId)) {
            String orderId = (String) getSession().get(Constants.PO_KEY);
            order = OrderService.getByOrderId(orderId);
            if (order == null) {
                order = new Order();
                order.setMember(member);
                OrderService.createOrder(order);
            }
            getSession().put(Constants.PO_KEY, order.getOrderId());
            Farm farm = FarmService.getById(orderItemId);
            if (farm != null && farm.getMember() == null) {
                orderItem = new OrderItem();
                orderItem.setObjectId(OBJECT_FARM);
                orderItem.setItemId(farm.getFarmId());
                orderItem.setItemName(farm.getFarmName());
                orderItem.setOrder(order);
                Double prc = null;
                try {
                    prc = new Double(farm.getRentPrice());
                } catch (Exception ignored) {
                }
                orderItem.setItemPrice(prc);
                OrderService.createOrderItem(orderItem);
            }
        }
        return index();
    }

    private boolean haveRight() {
        //todo...
        return true;
    }
}
