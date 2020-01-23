package com.finland.ws.client;

import com.finland.exceptions.UserNotFoundException;

public interface AuthClient {
    void authenticateToken(String token) throws UserNotFoundException;
}
