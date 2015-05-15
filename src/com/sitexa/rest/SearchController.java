package com.sitexa.rest;

import com.sitexa.framework.Constants;
import com.sitexa.action.SearchAction;
import com.sitexa.post.data.Post;
import com.sitexa.post.service.PostService;
import com.sitexa.service.SearchService;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-12
 * Time: 18:14:56
 */
public class SearchController extends SearchAction {

    public void prepare() {
        super.prepare();
        setSite(getHome());
    }

    public List<Post> getHotPosts() {
        Site site1 = null;
        String siteId = (String) getSession().get(Constants.SITE_KEY);
        if (siteId == null) {
            site1 = getProfile().getSite();
        } else {
            site1 = SiteService.getSite(siteId);
        }
        return PostService.getHotPosts(site1);
    }

    public List<Post> getTopPosts() {
        Site site1 = null;
        String siteId = (String) getSession().get(Constants.SITE_KEY);
        if (siteId == null) {
            site1 = getProfile().getSite();
        } else {
            site1 = SiteService.getSite(siteId);
        }
        return PostService.getTopPosts(site1);
    }

    public HttpHeaders index() {
        System.out.println("SearchController.index");
        posex = SearchService.search(pagex, sex, null, null);
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders show() {
        return index();
    }

    public HttpHeaders edit() {
        return index();
    }

    public HttpHeaders editNew() {
        return index();
    }

    public HttpHeaders create() {
        return index();
    }

    public HttpHeaders update() {
        return index();
    }

    public HttpHeaders destroy() {
        return index();
    }
}
