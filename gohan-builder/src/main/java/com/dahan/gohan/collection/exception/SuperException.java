package com.dahan.gohan.collection.exception;

/*
 * Creates on 2020/12/2.
 */

/**
 * @author kevin
 */
public class SuperException extends RuntimeException {

    public SuperException() {
    }

    public SuperException(String message) {
        super(message);
    }

    public SuperException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuperException(Throwable cause) {
        super(cause);
    }

    public SuperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
