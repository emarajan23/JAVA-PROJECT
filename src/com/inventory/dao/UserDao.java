package com.inventory.dao;

import com.inventory.model.NewUsers;


public interface UserDao {
    int saveUser(NewUsers user);
}
