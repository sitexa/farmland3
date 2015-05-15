package com.sitexa.farm.service;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.LandDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: xnpeng
 * Date: 2010-1-25
 * Time: 18:53:50
 */
public class LandBean {
    private static Log log = LogFactory.getLog(LandBean.class);

    public static final String ERROR_OBJECT = "11";
    public static final String ERROR_DAO = "12";


    private String error_string = "";
    private String success_string = "";

    private Land land;

    public LandBean(Land land) {
        this.land = land;
        init();
    }

    private void init() {
        if (land == null || land.getLandId() == null || "".equals(land.getLandId())) {
            log.error("ERROR_OBJECT");
            throw new LandException(ERROR_OBJECT);
        }
        try {
            LandDAO dao = new LandDAO();
            land = dao.findById(land.getLandId());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LandException(ERROR_DAO);
        }
    }

    
}
