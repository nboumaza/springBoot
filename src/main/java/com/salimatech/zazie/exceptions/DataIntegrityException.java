package com.salimatech.zazie.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 *  DataIntegrityException exception
 *  Thrown if a data integrity violation during a persistence create or update persistence operation:
 *  The message will include the specific violation - The following are example scenarios
 *  1. Unique constraint violation
 *  2. Missing entity required values
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DataIntegrityException extends DataIntegrityViolationException {

    public DataIntegrityException(String message) {
        super(message);
    }
}
