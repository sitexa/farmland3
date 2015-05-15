package com.sitexa.rest;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-5-17
 * Time: 20:14:04
 */
public class ErrorController extends BaseAction {

    Site site;

    public void prepare() {
        super.prepare();
        site = getHome();
    }

    public HttpHeaders index() {
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

    public Site getSite() {
        return site;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
