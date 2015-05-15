/**
* @作者 leim
* @创建日期 2010-6-2
* @版本 V 1.0
*/ 
package com.sitexa.farm.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sitexa.farm.data.Farming;

public class FertilizeBean extends ActionBase{

    private static Log log = LogFactory.getLog(FertilizeBean.class);
    
	public FertilizeBean(Farming operation) {
		super(operation);
	}

}
