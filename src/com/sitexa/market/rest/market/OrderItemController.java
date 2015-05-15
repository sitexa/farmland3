package com.sitexa.market.rest.market;

import com.sitexa.market.action.OrderItemAction;
import com.sitexa.market.service.OrderService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2010-1-18
 * Time: 16:31:09
 */
public class OrderItemController extends OrderItemAction {

    public void prepare() {
        super.prepare();
        member = getProfile();
    }

    public HttpHeaders index() {
        System.out.println("OrderItemController.index");
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("OrderItemController.show");
        orderItem = OrderService.findOrderItem(id);
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders update() {
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders destroy() {
        System.out.println("OrderItemController.destroy");
        if (haveRight()) {
            OrderService.deleteOrderItem(id);
        }
        String referer = ServletActionContext.getRequest().getHeader("referer");
        if (referer != null && !"".equals(referer)) {
            try {
                ServletActionContext.getResponse().sendRedirect(referer);
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new DefaultHttpHeaders("index");
    }

    private boolean haveRight() {
        return true;
    }
}
