package com.sitexa.rest.admin;

import com.sitexa.action.admin.FilterAction;
import com.sitexa.post.service.FilterService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-5-28
 * Time: 12:49:47
 */
public class FilterController extends FilterAction {
    public HttpHeaders index() {
        System.out.println("FilterController.index");
        filters = FilterService.getAll();
        System.out.println("filters.size() = " + filters.size());
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders show() {
        return index();
    }

    public HttpHeaders edit() {
        return index();
    }

    public HttpHeaders editNew() {
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("FilterController.create");
        FilterService.create(filter);
        return index();
    }

    public HttpHeaders update() {
        System.out.println("FilterController.update");
        System.out.println("filters.size() = " + filters.size());
        FilterService.update(filters);
        filters.clear();
        return index();
    }

    public HttpHeaders destroy() {
        return index();
    }
}
