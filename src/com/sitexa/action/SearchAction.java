package com.sitexa.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.Post;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-12
 * Time: 18:15:34
 */
public class SearchAction extends BaseAction {
    protected String sex;
    protected Page pagex = new Page(7);
    protected Site sitex;
    protected Category catex;
    protected User usex;
    protected Member membex;
    protected List<Post> posex = new ArrayList<Post>();
    private Site site;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Page getPagex() {
        return pagex;
    }

    public void setPagex(Page pagex) {
        this.pagex = pagex;
    }

    public Site getSitex() {
        return sitex;
    }

    public void setSitex(Site sitex) {
        this.sitex = sitex;
    }

    public Category getCatex() {
        return catex;
    }

    public void setCatex(Category catex) {
        this.catex = catex;
    }

    public User getUsex() {
        return usex;
    }

    public void setUsex(User usex) {
        this.usex = usex;
    }

    public Member getMembex() {
        return membex;
    }

    public void setMembex(Member membex) {
        this.membex = membex;
    }

    public List<Post> getPosex() {
        return posex;
    }

    public void setPosex(List<Post> posex) {
        this.posex = posex;
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
