package com.finland.serviceImpl;

import com.finland.controller.messages.AuthResponse;
import com.finland.controller.messages.UserRequest;
import com.finland.controller.messages.UserResponse;
import com.finland.dao.UserDao;
import com.finland.model.User;
import com.finland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.finland.controller.messages.CommonMessages.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<User> getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public User findUser(String email, String password) {
        return userDao.findUser(email, password);
    }

    @Override
    public UserResponse loginUser(UserRequest userRequest) {
        User user = findUser(userRequest.getEmail(), userRequest.getPassword());
        if (user != null) {
            return new UserResponse(SUCCESS, LOGIN_SUCCESS, user.getToken());
        }
        return new UserResponse(FAILED, LOGIN_FAILED);
    }

    @Override
    public AuthResponse authUser(String token){
        User user = userDao.findUserByToken(token);
        if(user!=null){
            return new AuthResponse(SUCCESS, AUTH_SUCCESS, user.getId(), user.getName(), user.getEmail());
        }
        return new AuthResponse(FAILED, AUTH_FAILED);
    }
}
