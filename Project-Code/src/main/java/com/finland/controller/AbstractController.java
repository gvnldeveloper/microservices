package com.finland.controller;

import com.finland.exceptions.UserNotFoundException;
import com.finland.ws.client.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractController {

    public static final String TOKEN_HEADER = "X-Token";

    @Autowired
    protected AuthClient authClient;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Invalid request")
    @ExceptionHandler({RuntimeException.class})
    public void handleRuntimeException() {
        //Log exception
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED,
            reason = "Unauthorized user")
    @ExceptionHandler({UserNotFoundException.class})
    public void handleAuthException() {
        //Log exception
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,
            reason = "Error in processing request, please try again later")
    @ExceptionHandler({Exception.class})
    public void handleException() {
        //Log exception
    }
}
