package com.sitexa.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.post.data.Category;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;

/**
 * User: xnpeng
 * Date: 2009-4-21
 * Time: 20:02:30
 */
public class PublishAction extends BaseAction {

    protected Site site;
    protected Category category;

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

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
