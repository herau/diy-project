package com.ds.ce.diy.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Error Response envelope
 */
public final class ErrorResponse {

    /**
     * HTTP status value
     */
    public final int status;

    /**
     * HTTP status name
     */
    public final String error;

    /**
     * Message associated with the error
     */
    public final String message;

    /**
     * API path
     */
    public final String path;

    /**
     * cause exception
     */
    public final String exception;

    public ErrorResponse(HttpStatus status, String message, Class<? extends Exception> clazz) {
        this.error = status.getReasonPhrase();
        this.status = status.value();
        this.message = message;
        this.path = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
                                                                                                .getServletPath();
        this.exception = clazz.getCanonicalName();
    }
}
