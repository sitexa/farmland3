package com.sitexa.framework.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseRuntime {
    private static final Log log = LogFactory.getLog(BaseRuntime.class);

    public static void threw(Object o, Throwable e) {
        BaseRuntimeException runtimeThrowable = new BaseRuntimeException(e);
        log.error(o, runtimeThrowable);
        throw runtimeThrowable;
    }

    public static void threw(Object o, String message, Throwable e) {
        BaseRuntimeException runtimeThrowable = new BaseRuntimeException(message, e);
        log.error(o, runtimeThrowable);
        throw runtimeThrowable;
    }

    public static void threw(Object o, String message) {
        BaseRuntimeException runtimeThrowable = new BaseRuntimeException(message);
        log.error(o, runtimeThrowable);
        throw runtimeThrowable;
    }
}
