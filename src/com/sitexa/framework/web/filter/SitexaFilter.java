package com.sitexa.framework.web.filter;

import com.sitexa.framework.web.GZIPResponseWrapper;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-17
 * Time: 8:14:14
 */
public class SitexaFilter extends StrutsPrepareAndExecuteFilter {
    private static String encoding = "UTF-8";

    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null && encodingParam.trim().length() != 0) {
            encoding = encodingParam;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        try {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res = (HttpServletResponse) response;
                String ae = req.getHeader("accept-encoding");
                if (ae != null && ae.indexOf("gzip") != -1) {
                    GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(res);
                    super.doFilter(req, wrappedResponse, chain);
                    wrappedResponse.finishResponse();
                } else {
                    super.doFilter(request, response, chain);
                }
            }
        } catch (Exception e) {
            System.out.println("SitexaFilter.doFilter:"+e.getMessage());
        }
    }
}
