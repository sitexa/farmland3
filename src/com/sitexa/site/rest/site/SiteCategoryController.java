package com.sitexa.site.rest.site;

import com.sitexa.post.service.CategoryService;
import com.sitexa.service.RightService;
import com.sitexa.site.action.SiteCategoryAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-5-5
 * Time: 8:09:54
 */
public class SiteCategoryController extends SiteCategoryAction {
    private static Log log = LogFactory.getLog(SiteController.class);

    public HttpHeaders index() {
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        return edit();
    }

    public HttpHeaders edit() {
        if (!haveRight()) return new DefaultHttpHeaders("edit");
        site = SiteService.getSite(id);
        if (site != null) {
            siteCategories.addAll(CategoryService.getCategoryBySite(site));
        }
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        return null;
    }

    public HttpHeaders create() {
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            category.setType("2");
            category.setStatus("1");
            category.setSite(site);
            try {
                CategoryService.create(category);
            } catch (Exception e) {
                log.error(e);
                addActionError(e.getMessage());
            }
            siteCategories.clear();
        }
        return show();
    }

    public HttpHeaders update() {
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            try {
                CategoryService.update(siteCategories);
            } catch (Exception e) {
                log.error(e);
                addActionError(e.getMessage());
            }
            return new DefaultHttpHeaders("edit");
        } else
            return show();
    }

    public HttpHeaders destroy() {
        System.out.println("SiteCategoryController.destroy");
        if (!haveRight()) return show();
        System.out.println("not implemented.");
        return null;
    }

    private boolean haveRight() {
        Member profile = getProfile();
        Site site = SiteService.getSite(id);
        haveRight = RightService.checkRight(profile, site);
        return haveRight;
    }

}
