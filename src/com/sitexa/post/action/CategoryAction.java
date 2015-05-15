package com.sitexa.post.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.CategoryProperty;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-8
 * Time: 19:53:59
 */
public class CategoryAction extends BaseAction {
    protected String id;
    protected Category category;

    protected CategoryProperty property;
    protected List<CategoryProperty> properties = new ArrayList<CategoryProperty>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryProperty getProperty() {
        return property;
    }

    public void setProperty(CategoryProperty property) {
        this.property = property;
    }

    public List<CategoryProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<CategoryProperty> properties) {
        this.properties = properties;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
