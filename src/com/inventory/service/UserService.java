package com.inventory.service;

import com.inventory.model.NewUsers;
import com.inventory.model.Users;

public interface UserService {

    void registerUser(NewUsers user, String username, String password);


    Users login(String username, String password);
}
