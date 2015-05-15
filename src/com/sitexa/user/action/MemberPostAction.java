package com.sitexa.user.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.post.data.Post;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.User;
import com.sitexa.site.data.Site;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-6
 * Time: 22:04:34
 */
public class MemberPostAction extends BaseAction {
    protected String id;
    protected User user;
    protected Member member;
    protected Site site;
    protected Post post;
    protected List<Post> posts = new ArrayList<Post>();
    
    protected Page page = new Page(15);

    public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
