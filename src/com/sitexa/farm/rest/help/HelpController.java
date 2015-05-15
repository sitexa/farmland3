/**
* @作者 leim
* @创建日期 2010-4-23
* @版本 V 1.0
*/ 
package com.sitexa.farm.rest.help;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import com.sitexa.framework.struts.BaseAction;


public class HelpController extends HelpAction{
    public HttpHeaders index() {
        System.out.println("HelpController.index");
        return new DefaultHttpHeaders("index");
    }
}
