package com.sitexa.site.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sitexa.site.data.SiteType;
import com.sitexa.site.data.SiteTypeDAO;

/**
 * User: xnpeng
 * Date: 2009-4-6
 * Time: 11:54:17
 */
public class SiteTypeService {
    private static Log log = LogFactory.getLog(SiteTypeService.class);

    public static SiteType getSiteType(String id){
        SiteTypeDAO dao = new SiteTypeDAO();
        return dao.findById(id);
    }

}
