package com.sitexa.framework.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-16
 * Time: 22:05:33
 */
public class CharsetEncodingFilter implements Filter {
    private FilterConfig config = null;
    private String RequestEncoding = null;
    private String ResponseEncoding = null;

    public void init(FilterConfig arg0) throws ServletException {
        this.config = arg0;
        this.RequestEncoding = config.getInitParameter("RequestEncoding");
        this.ResponseEncoding = config.getInitParameter("ResponseEncoding");
        System.out.println("=========req:"+this.RequestEncoding);
        System.out.println("=========res:"+this.ResponseEncoding);
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
            throws IOException, ServletException {
        if (this.config == null) {
            return;
        }
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        CharacterEncodingHttpServletRequestWrapper req_w = new CharacterEncodingHttpServletRequestWrapper(request, RequestEncoding);
        CharacterEncodingHttpServletResponseWrapper res_w = new CharacterEncodingHttpServletResponseWrapper(response, ResponseEncoding);
        fc.doFilter(req_w, res_w);
    }

    public void destroy() {
    }

}

