package com.finland.controller.messages;

public class AuthResponse {

    private Integer userId;
    private String userName;
    private String email;
    private String status;
    private String message;

    public AuthResponse() {
    }

    public AuthResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public AuthResponse(String status, String message, Integer userId, String userName, String email) {
        this.status = status;
        this.message = message;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
