/**
 * @作者 leim
 * @创建日期 2010-6-2
 * @版本 V 1.0
 */
package com.sitexa.farm.service;

import com.sitexa.farm.data.Farming;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OtherBean extends ActionBase {

    private static Log log = LogFactory.getLog(OtherBean.class);

    public OtherBean(Farming operation) {
        super(operation);
    }

    /**
     * 新建或修改农作物信息
     */
    protected void createOrChangeCrops() {
        try {
            updateCropsPower(crops, 1, 0);
        } catch (Exception e) {
            log.error(e);
            throw new FarmingException(CREATE_OR_CHANGE_CROPS_ERROR);
        }
    }
}
