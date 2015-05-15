package com.sitexa.framework.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-16
 * Time: 21:57:35
 */
public class CharacterEncodingHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private String encoding = null;
    private String oldEncoding = null;

    public CharacterEncodingHttpServletRequestWrapper(HttpServletRequest request, String encoding) {
        super(request);
        this.encoding = encoding;
        this.oldEncoding = request.getCharacterEncoding();
        System.out.println(">>>>>>>>>>>old:" + this.oldEncoding);
    }

    public String getParameter(String value) {
        try {
            if ( !(encoding.equals(oldEncoding)) && super.getParameter(value) != null) {
                String s = super.getParameter(value);
                System.out.println(">>>>>>>>>>old value:" + s);
                System.out.println(">>>>>>>>>>do filter:" + this.encoding);
                String v = new String(super.getParameter(value).getBytes("iso-8859-1"), encoding);
                System.out.println(">>>>>>>>>>new value:" + v);
                return v;
            } else {
                return super.getParameter(value);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return super.getParameter(value);
        }
    }

    private boolean isISO88591(String endcoding) {
        endcoding = endcoding.toLowerCase();
        return endcoding.startsWith("iso") && (endcoding.indexOf("8859") != -1) && endcoding.endsWith("1");
    }
}

