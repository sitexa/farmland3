package com.sitexa.framework.xmltree;

import java.io.Serializable;

/**
 * User: xnpeng
 * Date: 2009-3-31
 * Time: 10:07:53
 */
public class XMLTreeNode implements Serializable {

    //Mandatory fields
    private String text;
    private String id;
    //Optional fields;
    private String tooltip;
    private String img0;
    private String img1;
    private String img2;
    private String aCol;
    private String sCol;
    private String select;
    private String style;
    private String open;
    private String call;
    private String checked;
    private String child;//0/1
    private int imheight;
    private int imwidth;
    private int topoffset;
    private String radio;//empty or ...

    public XMLTreeNode(String text, String id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getImg0() {
        return img0;
    }

    public void setImg0(String img0) {
        this.img0 = img0;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getACol() {
        return aCol;
    }

    public void setACol(String aCol) {
        this.aCol = aCol;
    }

    public String getSCol() {
        return sCol;
    }

    public void setSCol(String sCol) {
        this.sCol = sCol;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public int getImheight() {
        return imheight;
    }

    public void setImheight(int imheight) {
        this.imheight = imheight;
    }

    public int getImwidth() {
        return imwidth;
    }

    public void setImwidth(int imwidth) {
        this.imwidth = imwidth;
    }

    public int getTopoffset() {
        return topoffset;
    }

    public void setTopoffset(int topoffset) {
        this.topoffset = topoffset;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XMLTreeNode xmlTree = (XMLTreeNode) o;

        if (!id.equals(xmlTree.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "XMLTreeNode{" +
                "text='" + text + '\'' +
                ", id='" + id + '\'' +
                ", tooltip='" + tooltip + '\'' +
                ", img0='" + img0 + '\'' +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", aCol='" + aCol + '\'' +
                ", sCol='" + sCol + '\'' +
                ", select='" + select + '\'' +
                ", style='" + style + '\'' +
                ", open='" + open + '\'' +
                ", call='" + call + '\'' +
                ", checked='" + checked + '\'' +
                ", child='" + child + '\'' +
                ", imheight=" + imheight +
                ", imwidth=" + imwidth +
                ", topoffset=" + topoffset +
                ", radio='" + radio + '\'' +
                '}';
    }
}
