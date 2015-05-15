package com.sitexa.sys.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.sys.data.Func;

import java.util.ArrayList;
import java.util.List;

public class FuncAction extends BaseAction {
    String id;
    Func func;
    List<Func> funcList = new ArrayList<Func>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Func getFunc() {
        return func;
    }

    public void setFunc(Func func) {
        this.func = func;
    }

    public List<Func> getFuncList() {
        return funcList;
    }

    public void setFuncList(List<Func> funcList) {
        this.funcList = funcList;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
