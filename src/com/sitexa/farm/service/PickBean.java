package com.sitexa.farm.service;

import com.sitexa.farm.data.Farming;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: xnpeng
 * Date: 2010-1-27
 * Time: 15:09:32
 */
public class PickBean extends ActionBase {

    private static Log log = LogFactory.getLog(PickBean.class);

    public PickBean(Farming operation) {
        super(operation);
    }

    protected boolean checkConditions() {
        if (crops == null || crops.getSeedNumber() <= 0) {
            this.addErrorMessage("没有作物不能采摘##");
            throw new FarmingException(NOCROPS_ERROR);
        }
        return true;
    }

    protected Integer calcPrice() {
        float price = 0, qty = 0, count = 0;
        try {
            price = Integer.parseInt(action.getExpense());
            qty = operation.getQuantity();
            int q = crops.getSeedNumber();
            if (qty > q) qty = q;
            count = price * qty;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return (int) count;
    }

    protected void createOrChangeCrops() {
        try {
            updateCropsPower(crops, -1, -1);
            this.addSuccessMessage("采摘成功##");
        } catch (Exception e) {
            log.error(e);
            throw new FarmingException(CREATE_OR_CHANGE_CROPS_ERROR);
        }
    }

}
