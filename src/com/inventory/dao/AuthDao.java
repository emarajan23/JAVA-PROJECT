package com.inventory.dao;

import com.inventory.model.Users;

public interface AuthDao {
    void saveCredentials(int userId, String username, String password);
    boolean isUsernameExists(String username);
    Users fetchUserByCredentials(String username, String password);

}