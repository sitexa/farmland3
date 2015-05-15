package com.sitexa.framework.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-24
 * Time: 15:17:50
 */
public class SimpleInterceptor extends AbstractInterceptor {

    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("!!!!!SimpleInterceptor==date:" + (new Date()));
        return invocation.invoke();
    }
}
