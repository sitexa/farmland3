package com.sitexa.sys.rest.sys;

import com.sitexa.framework.struts.BaseController;
import com.sitexa.sys.action.FuncAction;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-24
 * Time: 12:41:51
 */
public class FuncController extends FuncAction implements BaseController {
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
