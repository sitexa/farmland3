package com.sitexa.framework.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * User: xnpeng
 * Date: 2009-5-28
 * Time: 11:39:09
 */
public class KeywordsInterceptor extends AbstractInterceptor {

    public String intercept(ActionInvocation invocation) throws Exception {
        return invocation.invoke();
    }
}
