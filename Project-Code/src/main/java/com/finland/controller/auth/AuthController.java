package com.finland.controller.auth;

import com.finland.controller.AbstractController;
import com.finland.controller.messages.AuthRequest;
import com.finland.controller.messages.AuthResponse;
import com.finland.controller.messages.UserRequest;
import com.finland.controller.messages.UserResponse;
import com.finland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.finland.controller.messages.CommonMessages.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AuthController extends AbstractController {

    private static final String LOGIN_ENDPOINT = "/login";
    private static final String AUTH_ENDPOINT = "/auth";

    @Autowired
    private UserService userService;

    @RequestMapping(method = POST,
            value = LOGIN_ENDPOINT,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserResponse login(@RequestBody UserRequest userRequest) {

        if (userRequest.getEmail() != null && userRequest.getPassword() != null)
            return userService.loginUser(userRequest);
        return new UserResponse(FAILED, INVALID_PARAMETER);
    }

    @RequestMapping(method = POST,
            value = AUTH_ENDPOINT,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public AuthResponse authenticateUser(@RequestBody AuthRequest authRequest) {

        if (authRequest.getToken() != null) {
            return userService.authUser(authRequest.getToken());
        }
        return new AuthResponse(FAILED, AUTH_FAILED);
    }
}
