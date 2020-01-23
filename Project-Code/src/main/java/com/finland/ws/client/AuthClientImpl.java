package com.finland.ws.client;

import com.finland.controller.messages.AuthRequest;
import com.finland.controller.messages.AuthResponse;
import com.finland.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.finland.controller.messages.CommonMessages.AUTH_FAILED;
import static com.finland.controller.messages.CommonMessages.SUCCESS;

@Component
public class AuthClientImpl implements AuthClient {

    @Value("${authController.base.path}")
    private String baseUrl;

    private static final String AUTH_ENDPOINT = "/auth";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void authenticateToken(String token) throws UserNotFoundException {
        //Log token
        final String uri = baseUrl + AUTH_ENDPOINT;

        AuthRequest authRequest = new AuthRequest(token);

        AuthResponse authResponse = restTemplate.postForObject(uri, authRequest, AuthResponse.class);
        if (!authResponse.getStatus().equals(SUCCESS)) {
            //Log error
            throw new UserNotFoundException(AUTH_FAILED);
        }
    }

}
