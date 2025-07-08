package com.inventory.service;

import com.inventory.model.User;
import com.inventory.model.UserEntity;

public interface UserService {

    void registerUser(User user, String username, String password);


    UserEntity login(String username, String password);
}
