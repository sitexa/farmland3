package com.sitexa.sys.rest.sys;

import com.sitexa.sys.action.ViewStatsAction;
import com.sitexa.sys.service.ViewStatsService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-6-24
 * Time: 17:50:04
 */
public class ViewStatsController extends ViewStatsAction {

    private boolean haveRight() {
        return (getProfile() != null);
    }

    public HttpHeaders index() {
        if (!haveRight()) return null;
        viewStatses = ViewStatsService.views(page);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders addr() {
        if (!haveRight()) return null;
        viewStatsAddrs = ViewStatsService.viewAddr(page2);
        return new DefaultHttpHeaders("addr");
    }

    public HttpHeaders uri() {
        if (!haveRight()) return null;
        viewStatsUris = ViewStatsService.viewUri(page3);
        return new DefaultHttpHeaders("uri");
    }

}
