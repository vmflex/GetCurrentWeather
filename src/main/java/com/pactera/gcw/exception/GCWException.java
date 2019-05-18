package com.pactera.gcw.exception;

public class GCWException extends Exception {

    public GCWException() {
        super();
    }

    public GCWException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GCWException(String message, Throwable cause) {
        super(message, cause);
    }

    public GCWException(String message) {
        super(message);
    }

    public GCWException(Throwable cause) {
        super(cause);
    }

    
}
