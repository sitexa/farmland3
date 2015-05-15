package com.sitexa.site.service;

import com.sitexa.site.data.City;
import com.sitexa.site.data.CityDAO;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-2
 * Time: 12:44:57
 */
public class CityService {
    private static final Log log = LogFactory.getLog(CityService.class);

    public static void main(String[] args) throws SQLException {
    }


    public static List getStateList() {
        CityDAO dao = new CityDAO();
        return dao.getStateList();
    }

    public static String getCityList(String stateId) {
        StringBuffer sb = new StringBuffer();
        CityDAO dao = new CityDAO();
        List list = dao.getCityList(stateId);
        for (int i = 0; i < list.size(); i++) {
            City t = (City) list.get(i);
            if (i == 0)
                sb.append(t.getCode()).append(",").append(t.getCity());
            else
                sb.append(";").append(t.getCode()).append(",").append(t.getCity());
        }
        return sb.toString();
    }

    public static String getCountyList(String cityId) {
        StringBuffer sb = new StringBuffer();
        CityDAO dao = new CityDAO();
        List list = dao.getCountyList(cityId);
        for (int i = 0; i < list.size(); i++) {
            City t = (City) list.get(i);
            if (i == 0)
                sb.append(t.getCode()).append(",").append(t.getCity());
            else
                sb.append(";").append(t.getCode()).append(",").append(t.getCity());
        }
        return sb.toString();
    }

    public static String getParentSite(String siteId) {
        StringBuffer sb = new StringBuffer();

        return sb.toString();
    }

    public static String getChildrenSite(String siteId) {
        StringBuffer sb = new StringBuffer();

        return sb.toString();
    }

    public static String getSiteList(String stateCode, String cityCode, String countyCode) {
        StringBuffer sb = new StringBuffer();
        String code;
        if (countyCode != null && !countyCode.equals(""))
            code = countyCode;
        else if (cityCode != null && !cityCode.equals(""))
            code = cityCode;
        else
            code = stateCode;

        SiteDAO dao = new SiteDAO();
        List list = dao.findByProperty("parentId", code);
        for (int i = 0; i < list.size(); i++) {
            Site s = (Site) list.get(i);
            if (i == 0) {
                sb.append(s.getSiteId()).append(",").append(s.getName());
            } else {
                sb.append(";").append(s.getSiteId()).append(",").append(s.getName());
            }
        }

        return sb.toString();
    }
}


