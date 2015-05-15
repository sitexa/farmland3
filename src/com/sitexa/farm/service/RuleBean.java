package com.sitexa.farm.service;

import com.sitexa.farm.data.Service;

/**
 * User: xnpeng
 * Date: 2010-2-28
 * Time: 12:32:58
 */
public class RuleBean {
    private Service action;

    public RuleBean(Service action) {
        this.action = action;
    }

    public boolean execute() {
        return false;
    }
}
