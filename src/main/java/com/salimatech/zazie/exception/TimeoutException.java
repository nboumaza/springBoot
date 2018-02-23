package com.salimatech.zazie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The service timed out
 */
@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class TimeoutException extends RuntimeException {

    public TimeoutException(String message) {

        super(message);
    }
}