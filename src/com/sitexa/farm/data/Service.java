package com.sitexa.farm.data;

/**
 * Service entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Service implements java.io.Serializable {

    // Fields

    private String svcId;
    private String svcName;
    private String expense;
    private String materials;
    private String contents;
    private String remark;
    private byte[] img;
    // Constructors

    /**
     * default constructor
     */
    public Service() {
    }

    public String getSvcId() {
        return svcId;
    }

    public void setSvcId(String svcId) {
        this.svcId = svcId;
    }

    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getRemark() {
        return remark;
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
}