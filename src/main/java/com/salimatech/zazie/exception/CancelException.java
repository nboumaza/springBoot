package com.salimatech.zazie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The service was acting as a proxy and received
 * an invalid response from the upstream server
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CancelException extends RuntimeException {

    public CancelException(String message) {

        super(message );
    }
}