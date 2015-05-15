package com.sitexa.site.rest.site;

import com.sitexa.post.data.Post;
import com.sitexa.post.service.PostService;
import com.sitexa.service.RightService;
import com.sitexa.site.action.SitePostAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-5-9
 * Time: 11:01:13
 */
public class SitePostController extends SitePostAction {
    private static Log log = LogFactory.getLog(SitePostController.class);

    public HttpHeaders index() {
        site = getHome();
        if (site != null) {
            posts = PostService.searchBySite(site, page);
        }
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        site = SiteService.getSite(id);
        if (site != null) {
            posts = PostService.searchBySite(site, page);
        }
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        System.out.println("SitePostController.edit");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            posts = PostService.searchBySite(site, page);
        }
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        return index();
    }

    public HttpHeaders create() {
        return index();
    }

    public HttpHeaders update() {
        System.out.println("SitePostController.update");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        String idList = ServletActionContext.getRequest().getParameter("idList");
        String type = ServletActionContext.getRequest().getParameter("type");
        String siteId = ServletActionContext.getRequest().getParameter("toSite");
        String cateId = ServletActionContext.getRequest().getParameter("toCate");

        System.out.println("cateId = " + cateId);
        System.out.println("siteId = " + siteId);
        
        if (site != null && idList != null && type != null) {
            try {
                if ("top".equalsIgnoreCase(type)) {
                    PostService.toggleTop(idList);
                } else if ("elite".equalsIgnoreCase(type)) {
                    PostService.toggleElite(idList);
                } else if ("ban".equalsIgnoreCase(type)) {
                    PostService.updateStatus(idList, Post.STATUS_BAN);
                } else if ("publish".equalsIgnoreCase(type)) {
                    PostService.updateStatus(idList, Post.STATUS_PUB);
                } else if ("moveSite".equalsIgnoreCase(type)) {
                    if (RightService.checkRight(getProfile(), SiteService.getSite(siteId)))
                        PostService.moveToSite(idList, siteId);
                } else if ("moveCate".equalsIgnoreCase(type)) {
                    PostService.moveToCategory(idList, cateId);
                }
            } catch (Exception e) {
                log.error(e);
                addActionError(e.getMessage());
            }
        }
        return edit();
    }

    public HttpHeaders destroy() {
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        String idList = ServletActionContext.getRequest().getParameter("idList");
        if (site != null && idList != null) {
            try {
                PostService.delete(idList);
            } catch (Exception e) {
                log.error(e);
                addActionError(e.getMessage());
            }
        }
        return edit();
    }

    private boolean haveRight() {
        Member profile = getProfile();
        Site site = SiteService.getSite(id);
        haveRight = RightService.checkRight(profile, site);
        return haveRight;
    }
}
