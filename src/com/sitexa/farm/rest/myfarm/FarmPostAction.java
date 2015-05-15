package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.post.data.FarmPost;
import com.sitexa.post.data.Post;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-16
 * Time: 17:03:14
 */
public class FarmPostAction extends FarmlanderAction {
    protected Site site;
    protected FarmPost post;
    protected List<Post> posts = new ArrayList<Post>();
    protected Member member;
    protected Farm farm;
    protected Page page = new Page(20);

    public FarmPost getPost() {
        return post;
    }

    public void setPost(FarmPost post) {
        this.post = post;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
