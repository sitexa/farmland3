package com.sitexa.farm.data;

import java.util.Date;

/**
 * FarmProduct entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FarmProduct implements java.io.Serializable {

    // Fields

    private String productId;
    private Farm farm;
    private String productName;
    private Date produceTime;
    private String specs;
    private String price;
    private String description;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public FarmProduct() {
    }

    /**
     * minimal constructor
     */
    public FarmProduct(String productId, Farm farm) {
        this.productId = productId;
        this.farm = farm;
    }

    // Property accessors

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Farm getFarm() {
        return this.farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getProduceTime() {
        return this.produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public String getSpecs() {
        return this.specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
	}

}