package com.sitexa.market.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.market.data.Order;
import com.sitexa.market.data.OrderDAO;
import com.sitexa.market.data.OrderItem;
import com.sitexa.market.data.OrderItemDAO;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-10-22
 * Time: 9:26:50
 */
public class OrderService {

    public static void main(String[] args) {
        String referer = "http://www1.farmlander.com/market/order/1000460";
        String host = "www1.farmlander.com";
        String context = "market";
        if (referer != null && !"".equals(referer)) {
            referer = referer.substring(referer.indexOf(host));
            if (context != null && !"".equals(context)) {
                referer = referer.substring(referer.indexOf(context) + context.length());
            }
            referer = referer.substring(referer.indexOf("/") + 1);
        }
        System.out.println("referer = " + referer);
    }

    public static List<Order> getOrders(Member member) {
        System.out.println("OrderService.getOrders");
        List<Order> result = new ArrayList<Order>();
        if (member == null) return result;
        OrderDAO dao = new OrderDAO();
        result = dao.findByMember(member);
        return result;
    }

    public static Order getMemberOrder(Member member, String orderId) {
        if (member == null || orderId == null || "".equals(orderId)) return null;
        String hql = "from Order as model where model.member=? and id=?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, member).setParameter(1, orderId);
        return (Order) query.uniqueResult();
    }

    public static Order getByOrderId(String orderId) {
        if (orderId == null || "".equals(orderId)) return null;
        OrderDAO dao = new OrderDAO();
        Order order = dao.findById(orderId);
        doCalc(order);
        return order;
    }

    private static void doCalc(Order order) {
        float op = 0.0f;
        Iterator itr = order.getOrderItems().iterator();
        while (itr.hasNext()) {
            OrderItem item = (OrderItem) itr.next();
            if (item.getFinalPrice() != null) {
                float fp = item.getFinalPrice().floatValue();
                op += fp;
            }
        }
        order.setCurrencyValue((double) op);
    }

    public static void createOrder(Order order) {
        if (order == null) return;
        if (null == order.getOrderId() || "".equals(order.getOrderId()))
            order.setOrderId(Sequence.getId());

        OrderDAO dao = new OrderDAO();
        dao.save(order);
    }

    public static void updateOrder(Order order) {
        if (order == null) return;
        OrderDAO dao = new OrderDAO();
        Order order2 = dao.findById(order.getOrderId());
        //todo...未完待续

    }

    @SuppressWarnings("deprecation")
    public static void deleteOrder(String orderId) throws SQLException {
        System.out.println("OrderService.deleteOrder");
        if (orderId == null || "".equals(orderId)) return;
        String sql_order_item = "delete t_order_item where orderId=" + orderId;
        String sql_order = "delete t_order where orderId=" + orderId;
        Session session = HibernateSessionFactory.getSession();
        Connection conn = session.connection();
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.addBatch(sql_order_item);
            stmt.addBatch(sql_order);
            stmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
            }
            throw e;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void createOrderItem(OrderItem item) {
        if (item == null) return;
        if (null == item.getOrderItemId() || "".equals(item.getOrderItemId()))
            item.setOrderItemId(Sequence.getId());

        OrderItemDAO dao = new OrderItemDAO();
        dao.save(item);
    }

    public static void deleteOrderItem(String orderItemId) {
        if (orderItemId == null || "".equals(orderItemId)) return;

        OrderItemDAO dao = new OrderItemDAO();
        dao.delete(dao.findById(orderItemId));
    }

    public static void updateOrderItem(OrderItem orderItem) {
        if (orderItem == null) return;

        OrderItemDAO dao = new OrderItemDAO();
        OrderItem orderItem2 = dao.findById(orderItem.getOrderItemId());
        orderItem2.setItemPrice(orderItem.getItemPrice());
        orderItem2.setFinalPrice(orderItem.getFinalPrice());
        orderItem2.setItemQuantity(orderItem.getItemQuantity());

        dao.save(orderItem2);
    }

    public static OrderItem findOrderItem(String orderItemId) {
        if (orderItemId == null || "".equals(orderItemId)) return null;
        OrderItemDAO dao = new OrderItemDAO();
        return dao.findById(orderItemId);
    }
}
