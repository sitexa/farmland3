package com.sitexa.action.admin;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.post.data.Filter;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-28
 * Time: 12:50:16
 */
public class FilterAction extends BaseAction {
    protected String id;
    protected Filter filter;
    protected List<Filter> filters = new ArrayList<Filter>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
