package com.sitexa.framework.struts;

import org.apache.struts2.rest.HttpHeaders;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-24
 * Time: 12:30:23
 */
public interface BaseController {

    public HttpHeaders index();

    public HttpHeaders show();

    public HttpHeaders edit();

    public HttpHeaders editNew();

    public HttpHeaders create();

    public HttpHeaders update();

    public HttpHeaders destroy();
}
