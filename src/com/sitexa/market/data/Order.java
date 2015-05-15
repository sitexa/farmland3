package com.sitexa.market.data;

import com.sitexa.user.data.Member;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Order entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

    // Fields

    private String orderId;
    private OrderStatus orderStatus;
    private Member member;
    private String customerName;
    private String customerCompany;
    private String customerAddress;
    private String customerCity;
    private String customerState;
    private String customerCountry;
    private String customerZipCode;
    private String customerPhone;
    private String customerEmail;
    private String deliveryName;
    private String deliveryCompany;
    private String deliveryAddress;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryCountry;
    private String deliveryZipCode;
    private String billingName;
    private String billingCompany;
    private String billingAddress;
    private String billingCity;
    private String billingState;
    private String billingCountry;
    private String billingZipCode;
    private String paymentMethod;
    private String ccType;
    private String ccOwner;
    private String ccNumber;
    private String ccExpires;
    private String courier;
    private Double totalPrice;
    private Date lastModified;
    private Date purchaseDate;
    private Date orderDateFnished;
    private String currency;
    private Double currencyValue;
    private Set orderItems = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Order() {
    }

    /**
     * minimal constructor
     */
    public Order(String orderId, Member member) {
        this.orderId = orderId;
        this.member = member;
    }

    // Property accessors

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCompany() {
        return this.customerCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    public String getCustomerAddress() {
        return this.customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return this.customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerState() {
        return this.customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerCountry() {
        return this.customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerZipCode() {
        return this.customerZipCode;
    }

    public void setCustomerZipCode(String customerZipCode) {
        this.customerZipCode = customerZipCode;
    }

    public String getCustomerPhone() {
        return this.customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getDeliveryName() {
        return this.deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryCompany() {
        return this.deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryCity() {
        return this.deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return this.deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryCountry() {
        return this.deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public String getDeliveryZipCode() {
        return this.deliveryZipCode;
    }

    public void setDeliveryZipCode(String deliveryZipCode) {
        this.deliveryZipCode = deliveryZipCode;
    }

    public String getBillingName() {
        return this.billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingCompany() {
        return this.billingCompany;
    }

    public void setBillingCompany(String billingCompany) {
        this.billingCompany = billingCompany;
    }

    public String getBillingAddress() {
        return this.billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCity() {
        return this.billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return this.billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingCountry() {
        return this.billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingZipCode() {
        return this.billingZipCode;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCcType() {
        return this.ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCcOwner() {
        return this.ccOwner;
    }

    public void setCcOwner(String ccOwner) {
        this.ccOwner = ccOwner;
    }

    public String getCcNumber() {
        return this.ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpires() {
        return this.ccExpires;
    }

    public void setCcExpires(String ccExpires) {
        this.ccExpires = ccExpires;
    }

    public String getCourier() {
        return this.courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getOrderDateFnished() {
        return this.orderDateFnished;
    }

    public void setOrderDateFnished(Date orderDateFnished) {
        this.orderDateFnished = orderDateFnished;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getCurrencyValue() {
        return this.currencyValue;
    }

    public void setCurrencyValue(Double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public Set getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(Set orderItems) {
        this.orderItems = orderItems;
    }

}