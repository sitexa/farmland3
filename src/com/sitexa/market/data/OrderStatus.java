package com.sitexa.market.data;

/**
 * OrderStatus entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class OrderStatus implements java.io.Serializable {

    // Fields

    private String orderStatusId;
    private String orderStatusName;

    // Constructors

    /**
     * default constructor
     */
    public OrderStatus() {
    }

    /**
     * minimal constructor
     */
    public OrderStatus(String orderStatusId, String orderStatusName) {
        this.orderStatusId = orderStatusId;
        this.orderStatusName = orderStatusName;
    }

    // Property accessors

    public String getOrderStatusId() {
        return this.orderStatusId;
    }

    public void setOrderStatusId(String orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getOrderStatusName() {
        return this.orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

}