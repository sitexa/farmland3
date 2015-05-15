/**
 * @作者 leim
 * @创建日期 2010-6-2
 * @版本 V 1.0
 */
package com.sitexa.farm.service;

import com.sitexa.farm.data.Crops;
import com.sitexa.farm.data.CropsDAO;
import com.sitexa.farm.data.Farming;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HarvestBean extends ActionBase {

    private static Log log = LogFactory.getLog(HarvestBean.class);

    public HarvestBean(Farming operation) {
        super(operation);
    }

    /**
     * 新建或修改农作物信息
     */
    protected void createOrChangeCrops() {
        try {
            if (crops != null) {
                CropsDAO dao2 = new CropsDAO();
                Crops crops1 = dao2.findById(crops.getCropsId());
                dao2.delete(crops1);
            }
        } catch (Exception e) {
            log.error(e);
            this.addErrorMessage(e.getMessage());
            throw new FarmingException(CREATE_OR_CHANGE_CROPS_ERROR);
        }
    }

}
