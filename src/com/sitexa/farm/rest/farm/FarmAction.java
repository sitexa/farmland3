package com.sitexa.farm.rest.farm;

import com.sitexa.farm.data.*;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.post.data.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-20
 * Time: 下午4:35
 */
public class FarmAction extends FarmlanderAction {
    protected Farm farm;
    protected List<Farm> farms = new ArrayList<Farm>();
    protected List<FarmPicture> pictures = new ArrayList<FarmPicture>();
    protected List<Crops> cropses = new ArrayList<Crops>();
    protected List<Farming> farmings = new ArrayList<Farming>();
    protected Page page = new Page(10);
    protected Land land;
    protected Page page1 = new Page(5);
    protected Page page2 = new Page(6);
    protected Page page3 = new Page(7);
    protected int tabIndex = 1;
    protected List<Post> posts = new ArrayList<Post>();

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public Farm getFarm() {
        return farm;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public List<FarmPicture> getPictures() {
        return pictures;
    }

    public List<Crops> getCropses() {
        return cropses;
    }

    public List<Farming> getFarmings() {
        return farmings;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Page getPage1() {
        return page1;
    }

    public void setPage1(Page page1) {
        this.page1 = page1;
    }

    public Page getPage2() {
        return page2;
    }

    public void setPage2(Page page2) {
        this.page2 = page2;
    }

    public Page getPage3() {
        return page3;
    }

    public void setPage3(Page page3) {
        this.page3 = page3;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
