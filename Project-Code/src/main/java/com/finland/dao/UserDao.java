package com.finland.dao;

import com.finland.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> getUser(int id);

    User findUser(String username, String password);

    User findUserByToken(String token);
}
