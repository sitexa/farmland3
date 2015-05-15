package com.sitexa.farm.data;

/**
 * Seed entity. @author MyEclipse Persistence Tools
 */

public class Seed implements java.io.Serializable {

    // Fields

    private String seedId;
    private String seedName;
    private byte[] img;
    private String description;
    private String price;
    private String uom;
    private String plantTime;
    private String harvestTime;
    private Land land;
    private String status;

    // Constructors

    /**
     * default constructor
     */
    public Seed() {
    }

    /**
     * minimal constructor
     */
    public Seed(String seedId, String seedName) {
        this.seedId = seedId;
        this.seedName = seedName;
    }

    // Property accessors

    public String getSeedId() {
        return this.seedId;
    }

    public void setSeedId(String seedId) {
        this.seedId = seedId;
    }

    public String getSeedName() {
        return this.seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUom() {
        return this.uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(String plantTime) {
        this.plantTime = plantTime;
    }

    public String getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(String harvestTime) {
        this.harvestTime = harvestTime;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}