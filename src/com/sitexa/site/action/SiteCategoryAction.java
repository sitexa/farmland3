package com.sitexa.site.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.post.data.Category;
import com.sitexa.service.RightService;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-5
 * Time: 8:11:36
 */
public class SiteCategoryAction extends BaseAction {
    protected String id;
    protected Site site;
    protected Category category;
    protected List<Category> siteCategories = new ArrayList<Category>();
    protected boolean haveRight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getSiteCategories() {
        return siteCategories;
    }

    public void setSiteCategories(List<Category> siteCategories) {
        this.siteCategories = siteCategories;
    }

    public boolean getHaveRight() {
        if (id != null)
            haveRight = RightService.checkRight(getProfile(), SiteService.getSite(id));
        return haveRight;
    }

    public void setHaveRight(boolean haveRight) {
        this.haveRight = haveRight;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
