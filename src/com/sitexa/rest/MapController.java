package com.sitexa.rest;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-5-2
 * Time: 22:11:55
 */
public class MapController extends BaseAction {
    private Float lat;
    private Float lng;
    private String siteAddress;

    public String getSiteAddress() {
        siteAddress = SiteService.getRoot().getName();
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public HttpHeaders index() {
        String slat = ServletActionContext.getRequest().getParameter("lat");
        String slng = ServletActionContext.getRequest().getParameter("lng");

        if ((slat == null || slat.equals("")) && (slng == null || slng.equals(""))) {
            Site home = getHome();
            if (home != null) {
                lat = home.getLatitude();
                lng = home.getLongitude();
            }
        } else {
            lat = Float.parseFloat(slat);
            lng = Float.parseFloat(slng);
        }

        return new DefaultHttpHeaders("index");
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

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
