package com.sitexa.farm.service;

import com.sitexa.farm.data.*;

/**
 * User: xnpeng
 * Date: 2010-3-5
 * Time: 23:34:39
 */
public class UpdateBean {

    protected Farm farm;
    protected Farming operation;
    protected Service service;
    protected Faction action;
    protected Seed seed;
    protected Crops crops;

    protected int price = 0;

    protected String error_string = "";
    protected String success_string = "";

    public String getErrorInfo() {
        return error_string;
    }

    public String getSuccessInfo() {
        return success_string;
    }

    public UpdateBean() {
    }

    public void deleteCrops(Crops crops) {
        if (crops == null || crops.getCropsId() == null) return;
        this.crops = CropService.getById(crops.getCropsId());
    }

}
