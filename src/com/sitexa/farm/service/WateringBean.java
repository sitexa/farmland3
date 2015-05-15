/**
 * @作者 leim
 * @创建日期 2010-6-2
 * @版本 V 1.0
 */
package com.sitexa.farm.service;

import com.sitexa.farm.data.Farming;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WateringBean extends ActionBase {

    private static Log log = LogFactory.getLog(WateringBean.class);

    public WateringBean(Farming operation) {
        super(operation);
    }

}
