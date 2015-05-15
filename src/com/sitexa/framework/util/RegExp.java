package com.sitexa.framework.util;

/**
 * User: xnpeng
 * Date: 2009-6-25
 * Time: 22:08:46
 */
public class RegExp {
    protected static final String regEx_wh = "(height|HEIGHT)\\b[^>]*|(WIDTH|width)\\b[^>]*";
    protected static final String regEx_div = "<(DIV|div)\\b[^>]*>|</(DIV|div)>";
    protected static final String regEx_tbody = "<(tbody|TBODY)\\b[^>]*>|</(tbody|TBODY)>";
    protected static final String regEx_table = "<(TABLE|table)\\b[^>]*>|</(TABLE|table)>";
    protected static final String regEx_trtd = "<(TR|tr)\\b[^>]*>|</(TR|tr)>|<(TD|td)\\b[^>]*>|</(TD|td)>|<(TH|th)\\b[^>]*>|</(TH|th)>";
    protected static final String regEx_trn = "[\\t\\r\\n]";
    protected static final String regEx_space = "(&nbsp;)*";
    protected static final String regEx_v32 = "";
    protected static final String regEx_email = "\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\b";
    protected static final String regEx_link = "(<([A|a][ ]*href)[^>]*>|</([A|a])[^>]*>)";
    protected static final String regEx_html = "<(([A-Z][A-Z0-9]*)|([a-z][a-z0-9]*))\\b[^>]*>|</(([A-Z][A-Z0-9]*)|([a-z][a-z0-9]*))>";

}
