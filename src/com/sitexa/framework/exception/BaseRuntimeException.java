package com.sitexa.framework.exception;

public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -775454847858229307L;

    BaseRuntimeException(String message, Throwable e) {
        super(message, e);
    }

    BaseRuntimeException(String message) {
        super(message);
    }

    BaseRuntimeException(Throwable e) {
        super(e);
    }


}
