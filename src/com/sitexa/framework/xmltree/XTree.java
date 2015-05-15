package com.sitexa.framework.xmltree;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Author: Oscar Peng
 * Date: 2006-4-27
 * Time: 16:28:02
 * Copyright @2005-2006,Motional Collaboration Co. Ltd.
 * xnpeng@hotmail.com
 * http://www.motional.net
 */
public class XTree {

    private static final String IM0 = "book.gif";
    private static final String IM1 = "books_open.gif";
    private static final String IM2 = "books_close.gif";

    private String text;
    private String id;
    private String tooltip;
    private String im0;
    private String im1;
    private String im2;
    private String acolor;
    private String scolor;
    private String select;
    private String open;
    private String call;
    private String checked;
    private String child;
    private String imheight;
    private String imwidth;
    private String userdataName;
    private String userdata;
    ///////////////////////
    private String parentId;
    private boolean isLeaf = false;
    private boolean dataNode = false;
    ///
    private static Vector<XTree> data;
    private static boolean loaded = false;


    public String getTreeString(String id) {
        this.id = id;
        sortTree();
        StringBuffer treeStr = new StringBuffer("<?xml version='1.0' encoding='GBK'?>");
        if (data.size() > 0) {
            treeStr.append("<tree id='").append(id).append("'>");
            treeStr.append(getSubTree(data));
            treeStr.append("\n</tree>");
        } else {
            treeStr.append("<tree id='").append(id).append("'>");
            treeStr.append("\n</tree>");
        }
        return treeStr.toString();
    }

    public String getSubTree(Vector<XTree> data) {
        StringBuffer treeStr = new StringBuffer();
        for (int i = 0; i < data.size(); i++) {
            XTree l = data.get(i);
            if (l.childOf(this)) {
                treeStr.append("\n<item ");
                if (l.dataNode) {
                    treeStr.append(" child='0'");
                } else {
                    if (l.haveChild(data)) {
                        treeStr.append(" child='1'");
                    } else {
                        treeStr.append(" child='0'");
                    }
                }
                treeStr.append(" text='").append(l.getText()).append("'");
                treeStr.append(" id='").append(l.getId()).append("'");
                if (l.getIm0() != null) treeStr.append(" im0='").append(l.getIm0()).append("'");
                if (l.getIm1() != null) treeStr.append(" im1='").append(l.getIm1()).append("'");
                if (l.getIm2() != null) treeStr.append(" im2='").append(l.getIm2()).append("'");
                if (l.getImheight() != null) treeStr.append(" imheight='").append(l.getImheight()).append("'");
                if (l.getImwidth() != null) treeStr.append(" imwidth='").append(l.getImwidth()).append("'");
                if (l.getAcolor() != null) treeStr.append(" acolor='").append(l.getAcolor()).append("'");
                if (l.getScolor() != null) treeStr.append(" scolor='").append(l.getScolor()).append("'");
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
                treeStr.append("</item>");
            }
        }
        return treeStr.toString();
    }

    public String getFullTreeString(String id) {
        this.id = id;
        sortTree();
        StringBuffer treeStr = new StringBuffer("<?xml version='1.0' encoding='GBK'?>");
        if (data.size() > 0) {
            treeStr.append("\n<tree id='").append(id).append("'>");
            treeStr.append(getFullTree(this, data));
            treeStr.append("\n</tree>");
        } else {
            treeStr.append("\n<tree id='").append(id).append("'>");
            treeStr.append("\n</tree>");
        }
        return treeStr.toString();
    }

    public String getFullTree(XTree root, Vector<XTree> data) {
        StringBuffer treeStr = new StringBuffer();
        for (int i = 0; i < data.size(); i++) {
            XTree l = data.get(i);
            if (l.childOf(root)) {
                treeStr.append("\n<item ");
                if (l.dataNode) {
                    treeStr.append(" child='0'");
                } else {
                    if (l.haveChild(data)) {
                        treeStr.append(" child='1'");
                    } else {
                        treeStr.append(" child='0'");
                    }
                }
                treeStr.append(" text='").append(l.getText()).append("'");
                treeStr.append(" id='").append(l.getId()).append("'");
                if (l.getIm0() != null) treeStr.append(" im0='").append(l.getIm0()).append("'");
                if (l.getIm1() != null) treeStr.append(" im1='").append(l.getIm1()).append("'");
                if (l.getIm2() != null) treeStr.append(" im2='").append(l.getIm2()).append("'");
                if (l.getImheight() != null) treeStr.append(" imheight='").append(l.getImheight()).append("'");
                if (l.getImwidth() != null) treeStr.append(" imwidth='").append(l.getImwidth()).append("'");
                if (l.getAcolor() != null) treeStr.append(" acolor='").append(l.getAcolor()).append("'");
                if (l.getScolor() != null) treeStr.append(" scolor='").append(l.getScolor()).append("'");
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
                treeStr.append(getFullTree(l, data));
                treeStr.append("\n</item>");
            }
        }
        return treeStr.toString();
    }

    public boolean childOf(XTree node) {
        if (this.getParentId() == null) return false;
        return this.getParentId().equalsIgnoreCase(node.getId());
    }

    public XTree getRoot(List<XTree> list) {
        for (int i = 0; i < list.size(); i++) {
            XTree x = list.get(i);
            if ((!x.isDataNode()) && x.getId().equalsIgnoreCase(this.getParentId())) {
                if (x.getParentId() == null) {
                    return x;
                } else {
                    return x.getRoot(list);
                }
            }
        }
        return this;
    }

    public XTree getChild(List<XTree> list) {
        if (this.dataNode) return null;
        for (int i = 0; i < list.size(); i++) {
            XTree x = list.get(i);
            if (x.getParentId().equalsIgnoreCase(this.getId()))
                return x;
        }
        return null;
    }

    public XTree getNode(List<XTree> list) {
        for (int i = 0; i < list.size(); i++) {
            XTree x = list.get(i);
            if (x.getId().equalsIgnoreCase(this.getId())) return x;
        }
        return this;
    }

    public boolean haveParent(List<XTree> list) {
        for (int i = 0; i < list.size(); i++) {
            XTree x = list.get(i);
            if (x.getId().equalsIgnoreCase(this.getParentId())) return true;
        }
        return false;
    }

    public boolean haveChild(List<XTree> list) {
        if (this.dataNode) return false;
        for (int i = 0; i < list.size(); i++) {
            XTree x = list.get(i);
            if (x.getParentId() != null && x.getParentId().equalsIgnoreCase(this.getId())) return true;
        }
        return false;
    }

    protected List<XTree> getData() {
        return new ArrayList<XTree>();
    }

    private void sortTree() {
        if (!loaded) {
            List list = getData();
            data = sortTree(list);
            loaded = true;
        }
    }

    protected Vector<XTree> sortTree(List<XTree> list) {
        Vector<XTree> v = new Vector<XTree>();
        if (list.size() > 0) {
            XTree tmp = makeTree(this.id, "", "");
            XTree root = tmp.getRoot(list);
            v.add(root);

            for (int i = 0; i < v.size(); i++) {
                XTree r = v.get(i);
                for (int j = 0; j < list.size(); j++) {
                    XTree l = list.get(j);
                    if (l.getParentId() != null && l.getParentId().equalsIgnoreCase(r.getId())) {
                        v.add(i + 1, l);
                        list.remove(j--);
                    }
                }
            }
        }
        return v;
    }

    public static XTree makeTree(String id, String parentId, String text) {
        XTree tmp = new XTree();
        tmp.id = id;
        tmp.parentId = parentId;
        tmp.text = text;
        return tmp;
    }

    ////////////////////////

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

    public String getIm0() {
        return im0;
    }

    public void setIm0(String im0) {
        this.im0 = im0;
    }

    public String getIm1() {
        return im1;
    }

    public void setIm1(String im1) {
        this.im1 = im1;
    }

    public String getIm2() {
        return im2;
    }

    public void setIm2(String im2) {
        this.im2 = im2;
    }

    public String getAcolor() {
        return acolor;
    }

    public void setAcolor(String acolor) {
        this.acolor = acolor;
    }

    public String getScolor() {
        return scolor;
    }

    public void setScolor(String scolor) {
        this.scolor = scolor;
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

    public String getUserdata() {
        return userdata;
    }

    public void setUserdata(String userdata) {
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

    public String getUserdataName() {
        return userdataName;
    }

    public void setUserdataName(String userdataName) {
        this.userdataName = userdataName;
    }


    public String toString() {
        return "XTree{" +
                "text='" + text + '\'' +
                ", id='" + id + '\'' +
                ", child='" + child + '\'' +
                ", tooltip='" + tooltip + '\'' +
                ", im0='" + im0 + '\'' +
                ", im1='" + im1 + '\'' +
                ", im2='" + im2 + '\'' +
                ", acolor='" + acolor + '\'' +
                ", scolor='" + scolor + '\'' +
                ", select='" + select + '\'' +
                ", open='" + open + '\'' +
                ", call='" + call + '\'' +
                ", checked='" + checked + '\'' +
                ", imheight='" + imheight + '\'' +
                ", imwidth='" + imwidth + '\'' +
                ", userdataName='" + userdataName + '\'' +
                ", userdata='" + userdata + '\'' +
                ", parentId='" + parentId + '\'' +
                ", isLeaf=" + isLeaf +
                ", dataNode=" + dataNode +
                '}';
    }
}
