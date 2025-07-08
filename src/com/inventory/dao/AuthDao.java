package com.inventory.dao;

import com.inventory.model.UserEntity;

public interface AuthDao {
    void saveCredentials(int userId, String username, String password);
    boolean isUsernameExists(String username);
    UserEntity fetchUserByCredentials(String username, String password);

}