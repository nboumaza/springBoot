package com.salimatech.zazie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException {

    public UserExistsException(String userId) {
        super("A user account with userId " + userId + "already exists!");
    }
}
