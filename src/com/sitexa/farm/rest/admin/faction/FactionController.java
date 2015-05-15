package com.sitexa.farm.rest.admin.faction;

import com.sitexa.framework.struts.BaseController;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2010-5-13
 * Time: 15:27:06
 */
public class FactionController extends FactionAction implements BaseController {
    public HttpHeaders index() {
        String landId = getParameter("landId");

        return null;
    }

    public HttpHeaders show() {
        return null;
    }

    public HttpHeaders edit() {
        return null;
    }

    public HttpHeaders editNew() {
        return null;
    }

    public HttpHeaders create() {
        return null;
    }

    public HttpHeaders update() {
        return null;
    }

    public HttpHeaders destroy() {
        return null;
    }
}
