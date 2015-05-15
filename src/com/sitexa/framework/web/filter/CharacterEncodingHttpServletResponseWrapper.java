package com.sitexa.framework.web.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


public class CharacterEncodingHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private String encoding = null;

    public CharacterEncodingHttpServletResponseWrapper(HttpServletResponse response, String encoding) {
        super(response);
        this.encoding = encoding;
    }

    public void setContentType(String value) {
        System.out.println("<<<<<<<<<<<" + value);
        super.setContentType("text/Html;charset=" + encoding);
    }

}


