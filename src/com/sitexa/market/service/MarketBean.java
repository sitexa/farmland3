package com.sitexa.market.service;

import com.sitexa.market.data.Market;
import com.sitexa.market.data.MarketDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: xnpeng
 * Date: 2010-1-25
 * Time: 19:20:52
 */
public class MarketBean {
    private static Log log = LogFactory.getLog(MarketBean.class);

    public static final String ERROR_OBJECT = "11";
    public static final String ERROR_DAO = "12";

    private String error_string = "";
    private String success_string = "";

    private Market market;

    public MarketBean(Market market) {
        this.market = market;
        init();
    }

    private void init() {
        if (market == null || market.getItemId() == null || "".equals(market.getItemId())) {
            log.error(ERROR_OBJECT);
            throw new MarketException(ERROR_OBJECT);
        }
        try {
            MarketDAO dao = new MarketDAO();
            market = dao.findById(market.getItemId());
        } catch (Exception e) {
            log.error(ERROR_DAO);
            throw new MarketException(ERROR_DAO);
        }
    }

    
}
