package com.sitexa.farm.data;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-24
 * Time: 下午11:30
 */
public class RentMode implements java.io.Serializable {
    private String modeId;
    private String modeName;
    private Land land;
    private Float rentPrice;
    private String description;

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Float getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Float rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
