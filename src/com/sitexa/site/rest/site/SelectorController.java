package com.sitexa.site.rest.site;

import com.sitexa.site.action.SelectorAction;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-4-5
 * Time: 14:33:10
 */
public class SelectorController extends SelectorAction {

    public HttpHeaders index() {
        stateList = SiteService.getChildren(SiteService.getRoot());
        return new DefaultHttpHeaders("").disableCaching();
    }
}
