package com.sitexa.sys.rest.sys;

import com.sitexa.framework.struts.BaseController;
import com.sitexa.sys.action.RoleAction;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-24
 * Time: 12:42:43
 */
public class RoleController extends RoleAction implements BaseController {
    public HttpHeaders index() {
        return new DefaultHttpHeaders("index");
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
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders update() {
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders destroy() {
        return new DefaultHttpHeaders("index");
    }
}
