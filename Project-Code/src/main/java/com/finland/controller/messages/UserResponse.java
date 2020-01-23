package com.finland.controller.messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserResponse {

    private String status;
    private String message;
    @JsonInclude(Include.NON_NULL)
    private String token;

    public UserResponse() {
    }

    public UserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public UserResponse(String status, String message, String token) {
        this.status = status;
        this.message = message;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
