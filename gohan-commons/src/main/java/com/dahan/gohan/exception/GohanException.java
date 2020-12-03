package com.dahan.gohan.exception;

/*
 * Creates on 2020/12/2.
 */

/**
 * @author kevin
 */
public class GohanException extends RuntimeException {

    public GohanException() {
    }

    public GohanException(String message) {
        super(message);
    }

    public GohanException(String message, Throwable cause) {
        super(message, cause);
    }

    public GohanException(Throwable cause) {
        super(cause);
    }

    public GohanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
