package com.sitexa.farm.data;

import com.sitexa.user.data.Member;

/**
 * FarmStore entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FarmStore implements java.io.Serializable {

    // Fields

    private String storeId;
    private Farm farm;
    private Member buyer;
	private String objectId;
    private String itemId;
    private String itemName;
    private Integer quantity;
    private Integer acreage;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public FarmStore() {
    }


    // Property accessors

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Member getBuyer() {
		return buyer;
	}

	public void setBuyer(Member buyer) {
		this.buyer = buyer;
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

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAcreage() {
        return this.acreage;
    }

    public void setAcreage(Integer acreage) {
        this.acreage = acreage;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
	}

}