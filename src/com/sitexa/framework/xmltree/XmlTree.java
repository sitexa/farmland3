package com.sitexa.framework.xmltree;

import java.util.TreeSet;

/**
 * Author: Oscar Peng
 * Date: 2006-7-29
 * Time: 7:15:04
 * Copyright @2005-2006,Motional Collaboration Co. Ltd.
 * xnpeng@hotmail.com
 * http://www.motional.net
 */
public abstract class XmlTree implements Comparable {

    static final String IM0 = "book.gif";
    static final String IM1 = "books_open.gif";
    static final String IM2 = "books_close.gif";

    private String text;
    private String id;
    private String tooltip;
    private String img0;
    private String img1;
    private String img2;
    private String aCol;
    private String sCol;
    private String select;
    private String open;
    private String call;
    private String checked;
    private String child;
    private String imheight;
    private String imwidth;
    private String style;
    private int topoffset;
    private String radio;//empty or ...
    private String userdataName;
    private Object userdata;
    //////////////////////
    private String parentId;
    private boolean isLeaf = false;
    private boolean dataNode = false;
    ////////////
    private static TreeSet<XmlTree> data;
    private static boolean loaded = false;

    public XmlTree() {
    }

    protected abstract TreeSet<XmlTree> getData();

/*
    private void init() {
        data = getData();
    }
*/


    public String getSubTree(XmlTree root) {
//        init();
        data = getData();
        StringBuffer treeStr = new StringBuffer("<?xml version='1.0' encoding='UTF-8'?>");
        if (data.size() > 0) {
            treeStr.append("<tree id='").append(root.getId()).append("'>");
            treeStr.append(root.getSubTree());
            treeStr.append("</tree>");
        } else {
            treeStr.append("<tree id='").append(root.getId()).append("'>");
            treeStr.append("</tree>");
        }
        return treeStr.toString();
    }

    private String getSubTree() {
        StringBuffer treeStr = new StringBuffer();
        for (XmlTree l : data) {
            if (l.isChildOf(this)) {
                treeStr.append("<item ");
                if (l.dataNode) {
                    treeStr.append(" child='0'");
                } else {
                    if (l.haveChildren()) {
                        treeStr.append(" child='1'");
                    } else {
                        treeStr.append(" child='0'");
                    }
                }

                treeStr.append(" text='").append(l.getText()).append("'");
                treeStr.append(" id='").append(l.getId()).append("'");
                if (l.getImg0() != null) treeStr.append(" img0='").append(l.getImg0()).append("'");
                if (l.getImg1() != null) treeStr.append(" img1='").append(l.getImg1()).append("'");
                if (l.getImg2() != null) treeStr.append(" img2='").append(l.getImg2()).append("'");
                if (l.getImheight() != null) treeStr.append(" imheight='").append(l.getImheight()).append("'");
                if (l.getImwidth() != null) treeStr.append(" imwidth='").append(l.getImwidth()).append("'");
                if (l.getACol() != null) treeStr.append(" aCol='").append(l.getACol()).append("'");
                if (l.getSCol() != null) treeStr.append(" sCol='").append(l.getSCol()).append("'");
                if (l.getSelect() != null) treeStr.append(" select='").append(l.getSelect()).append("'");
                if (l.getOpen() != null) treeStr.append(" open='").append(l.getOpen()).append("'");
                if (l.getCall() != null) treeStr.append(" call='").append(l.getCall()).append("'");
                if (l.getChecked() != null) treeStr.append(" checked='").append(l.getChecked()).append("'");
                treeStr.append(">");
                if (l.getUserdata() != null) {
                    treeStr.append("<userdata name='");
                    treeStr.append(l.getUserdataName()).append("'>");
                    treeStr.append(l.getUserdata()).append("</userdata>");
                }
                treeStr.append(l.getSubTree());
                treeStr.append("</item>");
            }
        }
        return treeStr.toString();
    }

    private boolean haveChildren() {
        if (this.isDataNode()) return false;
        for (XmlTree x : data) {
            if (this.getId() != null
                    && x.getParentId() != null
                    && this.getId().equalsIgnoreCase(x.getParentId()))
                return true;
        }
        return false;
    }

    private boolean isChildOf(XmlTree node) {
        return this.getParentId() != null
                && node.getId() != null
                && this.getParentId().equalsIgnoreCase(node.getId());
    }


/*
    public String getSubNode(XmlTree root) {
        init();
        StringBuffer treeStr = new StringBuffer("\n<?xml version='1.0' encoding='UTF8'?>");
        if (data.size() > 0) {
            treeStr.append("\n<tree id='").append(root.getId()).append("'>");
            treeStr.append(root.getSubNode());
            treeStr.append("\n</tree>");
        } else {
            treeStr.append("\n<tree id='").append(root.getId()).append("'>");
            treeStr.append("\n</tree>");
        }
        return treeStr.toString();
    }

    private String getSubNode() {
        StringBuffer treeStr = new StringBuffer();
        for (Iterator<XmlTree> iterator = data.iterator(); iterator.hasNext();) {
            XmlTree l = iterator.next();
            if (l.childOf(this)) {
                treeStr.append("\n<item ");
                if (l.dataNode) {
                    treeStr.append(" child='0'");
                } else {
                    if (l.haveChildren()) {
                        treeStr.append(" child='1'");
                    } else {
                        treeStr.append(" child='0'");
                    }
                }
                treeStr.append(" text='").append(l.getText()).append("'");
                treeStr.append(" id='").append(l.getId()).append("'");
                if (l.getImg0() != null) treeStr.append(" img0='").append(l.getImg0()).append("'");
                if (l.getImg1() != null) treeStr.append(" img1='").append(l.getImg1()).append("'");
                if (l.getImg2() != null) treeStr.append(" img2='").append(l.getImg2()).append("'");
                if (l.getImheight() != null) treeStr.append(" imheight='").append(l.getImheight()).append("'");
                if (l.getImwidth() != null) treeStr.append(" imwidth='").append(l.getImwidth()).append("'");
                if (l.getACol() != null) treeStr.append(" aCol='").append(l.getACol()).append("'");
                if (l.getSCol() != null) treeStr.append(" sCol='").append(l.getSCol()).append("'");
                if (l.getSelect() != null) treeStr.append(" select='").append(l.getSelect()).append("'");
                if (l.getOpen() != null) treeStr.append(" open='").append(l.getOpen()).append("'");
                if (l.getCall() != null) treeStr.append(" call='").append(l.getCall()).append("'");
                if (l.getChecked() != null) treeStr.append(" checked='").append(l.getChecked()).append("'");
                treeStr.append(">");
                if (l.getUserdata() != null) {
                    treeStr.append("\n<userdata name='");
                    treeStr.append(l.getUserdataName()).append("'>");
                    treeStr.append(l.getUserdata()).append("</userdata>");
                }
                treeStr.append("\n</item>");
            }
        }
        return treeStr.toString();
    }
*/

    ////////////////////////////


    ////////////////////////////

    public int compareTo(Object o) {
        XmlTree t = (XmlTree) o;
        return this.getId().compareTo(t.getId());
    }
    /////////////////////////////

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

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
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

    public String getImheight() {
        return imheight;
    }

    public void setImheight(String imheight) {
        this.imheight = imheight;
    }

    public String getImwidth() {
        return imwidth;
    }

    public void setImwidth(String imwidth) {
        this.imwidth = imwidth;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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

    public String getUserdataName() {
        return userdataName;
    }

    public void setUserdataName(String userdataName) {
        this.userdataName = userdataName;
    }

    public Object getUserdata() {
        return userdata;
    }

    public void setUserdata(Object userdata) {
        this.userdata = userdata;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean isDataNode() {
        return dataNode;
    }

    public void setDataNode(boolean dataNode) {
        this.dataNode = dataNode;
    }

    public String toString() {
        return "XHtmlTree{" +
                "text='" + text + '\'' +
                ", id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", child='" + child + '\'' +
                ", userdataName='" + userdataName + '\'' +
                ", userdata='" + userdata + '\'' +
                '}';
    }

}
