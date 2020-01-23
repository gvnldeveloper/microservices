package com.finland.service;

import com.finland.controller.messages.AuthResponse;
import com.finland.controller.messages.UserRequest;
import com.finland.controller.messages.UserResponse;
import com.finland.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUser(int id);

    User findUser(String email, String password);

    UserResponse loginUser(UserRequest userRequest);

    AuthResponse authUser(String token);
}
