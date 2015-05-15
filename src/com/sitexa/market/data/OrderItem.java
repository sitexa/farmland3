package com.sitexa.market.data;

/**
 * OrderItem entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class OrderItem implements java.io.Serializable {

    // Fields

    private String orderItemId;
    private Order order;
    private String objectId;
    private String itemId;
    private String itemModel;
    private String itemName;
    private Double itemPrice;
    private Double finalPrice;
    private Integer itemQuantity;

    // Constructors

    /**
     * default constructor
     */
    public OrderItem() {
    }

    /**
     * minimal constructor
     */
    public OrderItem(String orderItemId, Order order, String objectId,
                     String itemId) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.objectId = objectId;
        this.itemId = itemId;
    }

    /**
     * full constructor
     */
    public OrderItem(String orderItemId, Order order, String objectId,
                     String itemId, String itemModel, String itemName, Double itemPrice,
                     Double finalPrice, Integer itemQuantity) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.objectId = objectId;
        this.itemId = itemId;
        this.itemModel = itemModel;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.finalPrice = finalPrice;
        this.itemQuantity = itemQuantity;
    }

    // Property accessors

    public String getOrderItemId() {
        return this.orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemModel() {
        return this.itemModel;
    }

    public void setItemModel(String itemModel) {
        this.itemModel = itemModel;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Double getFinalPrice() {
        return this.finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getItemQuantity() {
        return this.itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
	}

}