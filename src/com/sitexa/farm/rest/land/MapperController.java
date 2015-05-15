package com.sitexa.farm.rest.land;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.farm.service.LandService;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-19
 * Time: 下午3:37
 */
public class MapperController extends FarmlanderAction {
    private float lat;
    private float lng;
    private int zoom;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public HttpHeaders index() {
        String siteId = ServletActionContext.getRequest().getParameter("siteId");
        String landId = ServletActionContext.getRequest().getParameter("landId");
        try {
            if (landId != null && !"".equals(landId)) {
                Land land = LandService.getById(landId);
                lat = land.getLatitude() == null ? 0 : land.getLatitude();
                lng = land.getLongitude() == null ? 0 : land.getLongitude();
            } else if (siteId != null && !"".equals(siteId)) {
                Site site = SiteService.getSite(siteId);
                lat = site.getLatitude() == null ? 0 : site.getLatitude();
                lng = site.getLongitude() == null ? 0 : site.getLongitude();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
