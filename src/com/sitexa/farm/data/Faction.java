package com.sitexa.farm.data;

/**
 * User: xnpeng
 * Date: 2010-5-19
 * Time: 10:53:51
 */
public class Faction implements java.io.Serializable {
    // Fields

    private String factionId;
    private Land land;
    private String actionId;
    private String actionName;
    private String contents;
    private String expense;
    private String materials;
    private String remark;
    private byte[] img;
    private String status;

    // Constructors

    /**
     * default constructor
     */
    public Faction() {
    }

    // Property accessors

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {

        this.land = land;
    }

    public String getActionId() {
        return this.actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getExpense() {
        return this.expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getMaterials() {
        return this.materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
