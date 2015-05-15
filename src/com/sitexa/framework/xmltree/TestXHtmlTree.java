package com.sitexa.framework.xmltree;

import java.util.TreeSet;

/**
 * Author: Oscar Peng
 * Date: 2006-7-29
 * Time: 11:20:38
 * Copyright @2005-2006,Motional Collaboration Co. Ltd.
 * xnpeng@hotmail.com
 * http://www.motional.net
 * Description:
 */
public class TestXHtmlTree extends XmlTree {

    public static void main(String[] args) {
        TestXHtmlTree tx = new TestXHtmlTree();
        XmlTree r = new TestXHtmlTree("0", null, "root");
        String s = tx.getSubTree(r);
        System.out.println("s = " + s);
    }

    public TestXHtmlTree() {
    }

    public TestXHtmlTree(String id, String parentid, String text) {
        this.setId(id);
        this.setParentId(parentid);
        this.setText(text);
    }

    protected TreeSet<XmlTree> getData() {
        TreeSet<XmlTree> set = new TreeSet<XmlTree>();
        XmlTree tree = new TestXHtmlTree("1", "0", "node1");
        set.add(tree);
        tree = new TestXHtmlTree("4", "1", "node4");
        set.add(tree);
        tree = new TestXHtmlTree("5", "1", "node5");
        set.add(tree);
        tree = new TestXHtmlTree("6", "5", "node6");
        set.add(tree);
        tree = new TestXHtmlTree("2", "0", "node2");
        set.add(tree);
        tree = new TestXHtmlTree("0", null, "root");
        set.add(tree);
        tree = new TestXHtmlTree("3", "0", "node3");
        set.add(tree);
        return set;
    }


}
