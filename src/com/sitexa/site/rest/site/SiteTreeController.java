package com.sitexa.site.rest.site;

import com.opensymphony.xwork2.ModelDriven;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.xmltree.XMLTreeNode;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-3-31
 * Time: 10:01:01
 */
public class SiteTreeController implements ModelDriven<Object> {
    private String id;
    private String word = "";
    private Page page = new Page(50);

    private XMLTreeNode node;
    private List<XMLTreeNode> tree = new ArrayList<XMLTreeNode>();

    private void search() {
        List<Site> list = new ArrayList<Site>();
        try {
            SiteService.search(page, word, null, false);
            for (Site s : list) {
                XMLTreeNode n = new XMLTreeNode(s.getName(), s.getSiteId());
                tree.add(n);
            }
        } catch (BaseException ignored) {
        }
    }

    public HttpHeaders index() {
        search();
        return new DefaultHttpHeaders("index");
    }

    public Object getModel() {
        if (node != null) return null;
        else if (tree.size() > 0) return tree;
        else return null;
    }

    //setter/getter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public XMLTreeNode getNode() {
        return node;
    }

    public void setNode(XMLTreeNode node) {
        this.node = node;
    }

    public List<XMLTreeNode> getTree() {
        return tree;
    }

    public void setTree(List<XMLTreeNode> tree) {
        this.tree = tree;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
