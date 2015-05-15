package com.sitexa.framework.exception;

public class BaseException extends Exception {

    public static final String TAB = ":\t";
    public static final String RTN = "\n";

    private static final long serialVersionUID = 8225761118292605726L;

    BaseException(String message) {
        super(message);
    }

    BaseException(Throwable e) {
        super(e);
    }

    BaseException(String message, Throwable e) {
        super(message, e);
    }

    public static void threw(String message) throws BaseException {
        throw new BaseException(message);
    }

    public static void threw(Exception e) throws BaseException {
        throw new BaseException(e);
    }

    public static void threw(String message, Exception e) throws BaseException {
        throw new BaseException(message, e);
    }

    protected String encode(String levelMessage, String message) {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName());
        sb.append(TAB);
        sb.append(levelMessage);
        sb.append(RTN);
        sb.append(message);
        return sb.toString();
    }
}
