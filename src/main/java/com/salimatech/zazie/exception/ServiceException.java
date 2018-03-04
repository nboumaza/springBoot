package com.salimatech.zazie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The service was acting as a proxy and received
 * an invalid response from the upstream server
 */
@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {

        super(message);
    }
}