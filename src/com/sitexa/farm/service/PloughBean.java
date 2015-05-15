package com.sitexa.farm.service;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmDAO;
import com.sitexa.farm.data.Farming;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: xnpeng
 * Date: 2010-6-2
 * Time: 9:57:19
 */
public class PloughBean extends ActionBase {
    private static Log log = LogFactory.getLog(PloughBean.class);

    public PloughBean(Farming operation) {
        super(operation);
    }

    /**
     * 检测服务条件
     *
     * @return
     */
    protected boolean checkConditions() {
        if (Farm.PLOUGH_STATUS.equals(farm.getPloughStatus())) {
            this.addErrorMessage("土地已经翻耕##");
            throw new FarmingException(PLOUGH_ERROR);
        }
        return true;
    }

    protected Integer calcPrice() {
        float price = 0, qty = 0, count = 0;
        try {
            price = Float.parseFloat(action.getExpense());
            qty = operation.getQuantity();
            if (qty < 10) qty = 10;
            count = price * qty;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return (int) count;
    }

    protected void createOrChangeCrops() {
        try {
            FarmDAO dao = new FarmDAO();
            Farm farm1 = dao.findById(farm.getFarmId());
            farm1.setPloughStatus(Farm.PLOUGH_STATUS);
            dao.save(farm1);
            this.addSuccessMessage("耕耘土地收获幸福##");
        } catch (Exception e) {
            log.error(e);
            throw new FarmingException(CREATE_OR_CHANGE_CROPS_ERROR);
        }
    }
}
