package com.sitexa.post.rest.post;

import com.sitexa.post.action.CategoryAction;
import com.sitexa.post.service.CategoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-4-8
 * Time: 20:17:19
 */
public class CategoryController extends CategoryAction {
    private static Log log = LogFactory.getLog(CategoryController.class);

    private boolean haveRight() {
        return true;
    }

    public HttpHeaders index() {
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("CategoryController.create");
        if (!haveRight()) return show();
        try {
            CategoryService.create(category);
            addActionMessage("保存成功!");
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
        }
        return show();
    }

    public HttpHeaders update() {
        System.out.println("CategoryController.update");
        try {
            CategoryService.update(category);
            addActionMessage("修改成功!");
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
        }
        return index();
    }

    public HttpHeaders destroy() {
        System.out.println("CategoryController.destroy");
        return index();
    }
}
